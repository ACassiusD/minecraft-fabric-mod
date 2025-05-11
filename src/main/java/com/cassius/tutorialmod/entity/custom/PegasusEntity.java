package com.cassius.tutorialmod.entity.custom;

import com.cassius.tutorialmod.util.JumpStateHolder;
import com.cassius.tutorialmod.entity.ModEntitiesRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PegasusEntity extends AbstractHorseEntity {

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

    @Override
    public void travel(Vec3d movementInput) {
        System.out.println("isGrounded " + this.isOnGround() + " isTouchingWater" + this.isTouchingWater());

        if (this.isAlive() && this.isTame() && this.getControllingPassenger() instanceof PlayerEntity rider) {
            // always turn off vanilla gravity
            this.setNoGravity(true);

            // WATER / LAVA: allow jump→boost, otherwise let vanilla swim sink you
            if (this.isTouchingWater() || this.isInLava()) {
                if (JumpStateHolder.isJumping(rider.getUuid())) {
                    Vec3d v = this.getVelocity();
                    this.setVelocity(v.x, 0.2, v.z);
                    this.move(MovementType.SELF, this.getVelocity());
                    this.velocityModified = true;
                } else {
                    this.setNoGravity(false);
                    super.travel(movementInput);
                }
                return;
            }

            // ─────────────────────────────────────────────────────────────────
            //  FLYING / AIR MODE (with speed boost)
            // ─────────────────────────────────────────────────────────────────

            // 1) face where the rider is looking
            this.setRotation(rider.getYaw(), rider.getPitch() * 0.5F);

            // 2) read raw inputs
            float  fwd = rider.forwardSpeed;   // W/S
            float  str = rider.sidewaysSpeed;  // A/D

            // 3) build world-space axes
            double yawRad  = Math.toRadians(rider.getYaw());
            Vec3d  forward = new Vec3d(-Math.sin(yawRad), 0, Math.cos(yawRad));
            Vec3d  right   = new Vec3d( Math.cos(yawRad), 0, Math.sin(yawRad));

            // 4) apply your speed attribute + a boost multiplier
            final double SPEED_MULTIPLIER = 2.5;
            double baseSpeed = this.getAttributeValue(EntityAttributes.MOVEMENT_SPEED);
            double spd       = baseSpeed * SPEED_MULTIPLIER;

            Vec3d horiz = forward.multiply(fwd * spd)
                    .add(right.multiply(str * spd));

            // 5) vertical logic unchanged
            final double ASCEND   =  0.2;
            final double DESCEND  = -0.2;
            final double HOVER    = -0.04;

            double vert = JumpStateHolder.isJumping(rider.getUuid())   ? ASCEND
                    : JumpStateHolder.isSprinting(rider.getUuid()) ? DESCEND
                    : HOVER;

            // 6) fire off your custom velocity
            Vec3d vel = horiz.add(0, vert, 0);
            this.setVelocity(vel);
            this.move(MovementType.SELF, vel);
            this.velocityModified = true;

        } else {
            // not ridden → vanilla land logic
            this.setNoGravity(false);
            super.travel(movementInput);
        }
    }


    // kill off all the vanilla jump-charge machinery
    @Override public void setJumpStrength(int strength) { /* no-op */ }
    @Override public boolean canJump()                { return false; }
    @Override public void startJumping(int height)   { /* no-op */ }
    @Override public void stopJumping()               { /* no-op */ }
    @Override public void setJumping(boolean j)       { /* no-op */ }

    private boolean isFlying() {
        return !this.isOnGround() && this.getControllingPassenger() != null && this.isTame();
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

    private void debugPrintTags() {
        Registries.ENTITY_TYPE
                .getEntry(this.getType())
                .streamTags()
                .forEach(tag -> System.out.println("Pegasus has tag: " + tag.id()));

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
}
