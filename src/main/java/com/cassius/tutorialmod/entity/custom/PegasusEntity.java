package com.cassius.tutorialmod.entity.custom;

import com.cassius.tutorialmod.util.JumpStateHolder;
import com.cassius.tutorialmod.entity.ModEntitiesRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PegasusEntity extends AbstractHorseEntity {

    /** true as soon as we start ascending; reset only when onGround() */
    private boolean flightMode = false;

    // ───── Flight tuning ─────
    /** Horizontal speed multiplier when flying (vs. ground speed). */
    private static final double FLIGHT_HORIZONTAL_MULT = 2.0;
    /** Upward velocity when ascending (jump key). */
    private static final double FLIGHT_ASCEND_VEL      = 0.3;
    /** Downward velocity when descending (sprint key). */
    private static final double FLIGHT_DESCEND_VEL     = -0.3;
    /** “Hover” gravity when neither ascending nor descending. */
    private static final double FLIGHT_HOVER_GRAVITY   = -0.04;
    // ──────────────────────────

    // ── Double-tap jump detection ──
    private boolean previousJumpState = false;
    private long    lastJumpTapTime   = 0;
    private boolean manualFlightOff   = false;
    private static final long DOUBLE_TAP_THRESHOLD = 250; // ms
    // ───────────────────────────────

    private static final EntityDimensions BABY_BASE_DIMENSIONS = EntityType.HORSE
            .getDimensions()
            .withAttachments(EntityAttachments.builder().add(EntityAttachmentType.PASSENGER, 0.0F, EntityType.HORSE.getHeight() + 0.125F, 0.0F))
            .scaled(0.5F);

    @Override
    public EntityDimensions getBaseDimensions(EntityPose pose) {
        return this.isBaby() ? BABY_BASE_DIMENSIONS : super.getBaseDimensions(pose);
    }

    //Required Default Constructor
    public PegasusEntity(EntityType<? extends AbstractHorseEntity> entityType, World world) {
        super(entityType, world);
    }

    //That method only modifies attributes after the entity is created,
    @Override
    protected void initAttributes(Random random) {
        this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(getChildHealthBonus(random::nextInt));
        this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(getChildMovementSpeedBonus(random::nextDouble));
        this.getAttributeInstance(EntityAttributes.JUMP_STRENGTH).setBaseValue(getChildJumpStrengthBonus(random::nextDouble));
    }

    @Override
    public boolean canUseSlot(EquipmentSlot slot) {
        return true;
    }

    /**
     * 1. Enter flight mode as soon as you press jump.
     * 2. Exit flight mode only once you touch ground and release jump.
     * 3. Toggle-off flight mid-air with a quick double-tap of the jump button.
     * 4. Manual flight-off persists until you press jump again.
     * 5. Always apply vanilla gravity when not in flight (or descend mode).
     * 6. Swim/Lava still fall back to vanilla swim controls + one-time hover on jump.
     * 7. Horizontal speed is FLIGHT_HORIZONTAL_MULT × ground speed.
     * 8. Vertical speeds: ASCEND = +FLIGHT_ASCEND_VEL, DESCEND = FLIGHT_DESCEND_VEL, HOVER = FLIGHT_HOVER_GRAVITY.
     *
     * @param movementInput represents the sidewaysSpeed, upwardSpeed, and forwardSpeed of the entity.
     */
    @Override
    public void travel(Vec3d movementInput) {
        // debug
        System.out.println("isGrounded=" + this.isOnGround()
                + " isTouchingWater=" + this.isTouchingWater()
                + " flightMode=" + flightMode);

        if (this.isAlive() && this.isTame() &&
                this.getControllingPassenger() instanceof PlayerEntity rider) {

            // ── SWIM / LAVA MODE ──
            if (this.isTouchingWater() || this.isInLava()) {
                flightMode = false;
                this.setNoGravity(false);
                super.travel(movementInput);
                if (JumpStateHolder.isJumping(rider.getUuid())) {
                    Vec3d v = this.getVelocity();
                    this.setVelocity(v.x, FLIGHT_ASCEND_VEL, v.z);
                }
                return;
            }

            // ── DOUBLE-TAP JUMP DETECTION ──
            boolean jumpPressed = JumpStateHolder.isJumping(rider.getUuid());
            if (jumpPressed && !previousJumpState) {
                long now = System.currentTimeMillis();
                if (flightMode && now - lastJumpTapTime < DOUBLE_TAP_THRESHOLD) {
                    // quick second tap → force exit flight mode
                    flightMode = false;
                    manualFlightOff = true;
                    this.setNoGravity(false);
                }
                lastJumpTapTime = now;
            }
            previousJumpState = jumpPressed;
            if (!jumpPressed) {
                // once you release Jump, allow flight toggles again
                manualFlightOff = false;
            }

            // ── FLIGHT MODE LATCH ──
            if (this.isOnGround() && !jumpPressed) {
                flightMode = false;
            }
            // only auto–enter flight when you press Jump if you haven't double‐tapped off
            if (jumpPressed && !manualFlightOff) {
                flightMode = true;
            }

            boolean descending = JumpStateHolder.isSprinting(rider.getUuid());

            if (flightMode || descending) {
                // ── FLY / AIR MODE ──

                // 1) face where the rider looks
                this.setRotation(rider.getYaw(), rider.getPitch() * 0.5F);

                // 2) local inputs
                float fwdI = rider.forwardSpeed;
                float strI = rider.sidewaysSpeed;
                double baseSpeed = this.getAttributeValue(EntityAttributes.MOVEMENT_SPEED);
                double speed     = baseSpeed * FLIGHT_HORIZONTAL_MULT;

                // 3) world-space axes
                double yawRad = Math.toRadians(rider.getYaw());
                Vec3d fwd   = new Vec3d(-Math.sin(yawRad), 0, Math.cos(yawRad));
                Vec3d right = new Vec3d( Math.cos(yawRad), 0, Math.sin(yawRad));

                // 4) horizontal velocity
                Vec3d horiz = fwd.multiply(fwdI * speed)
                        .add(right.multiply(strI * speed));

                // 5) vertical velocity
                double vert = jumpPressed
                        ? FLIGHT_ASCEND_VEL
                        : (descending ? FLIGHT_DESCEND_VEL : FLIGHT_HOVER_GRAVITY);

                // 6) disable vanilla gravity
                this.setNoGravity(true);

                // 7) apply
                Vec3d vel = horiz.add(0, vert, 0);
                this.setVelocity(vel);
                this.move(MovementType.SELF, vel);
                this.velocityModified = true;

            } else {
                // ── GROUND MODE ──
                this.setNoGravity(false);
                super.travel(movementInput);
            }

        } else {
            // ── UNMOUNTED / UNTAMED ──
            super.travel(movementInput);
        }
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity otherParentEntity) {
        if (otherParentEntity instanceof PegasusEntity) {
            PegasusEntity child = ModEntitiesRegistry.PEGASUS.create(world, SpawnReason.BREEDING);
            this.setChildAttributes(otherParentEntity, child);
            return child;
        }

        return null;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {

        debugPrintTags(); // DEBUG: print tags on interaction

        boolean bl = !this.isBaby() && this.isTame() && player.shouldCancelInteraction();
        if (!this.hasPassengers() && !bl) {
            ItemStack itemStack = player.getStackInHand(hand);
            if (!itemStack.isEmpty()) {
                if (this.isBreedingItem(itemStack)) {
                    return this.interactHorse(player, itemStack);
                }

                if (!this.isTame()) {
                    this.playAngrySound();
                    return ActionResult.SUCCESS;
                }
            }

            return super.interactMob(player, hand);
        } else {
            return super.interactMob(player, hand);
        }
    }

    /**
     * Log the Tags attached to this entity
     */
    private void debugPrintTags() {
        Registries.ENTITY_TYPE
                .getEntry(this.getType())
                .streamTags()
                .forEach(tag -> System.out.println("Pegasus has tag: " + tag.id()));

    }

    // kill off all the vanilla jump-charge machinery
    @Override public void setJumpStrength(int strength) { /* no-op */ }
    @Override public boolean canJump()                { return false; }
    @Override public void startJumping(int height)   { /* no-op */ }
    @Override public void stopJumping()               { /* no-op */ }
    @Override public void setJumping(boolean j)       { /* no-op */ }
    @Override
    public boolean handleFallDamage(double fallDistance, float damageMultiplier, DamageSource source) {
        // Pegasus is immune to fall damage
        return false;
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
        // do nothing
    }

}
