package com.cassius.tutorialmod.entity.custom;

import com.cassius.tutorialmod.util.JumpStateHolder;
import com.cassius.tutorialmod.entity.ModEntitiesRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
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
        if (this.isAlive() && this.isTame() && this.getControllingPassenger() instanceof PlayerEntity rider) {
            // face where the rider is looking
            this.setRotation(rider.getYaw(), rider.getPitch() * 0.5F);

            // horizontal movement
            Vec3d horiz = new Vec3d(rider.sidewaysSpeed, 0, rider.forwardSpeed)
                    .normalize()
                    .multiply(this.getAttributeValue(EntityAttributes.MOVEMENT_SPEED));

            // vertical: space = up, ctrl (sneak) = down
            double vert = JumpStateHolder.isJumping(rider.getUuid())   ? 0.2
                    : JumpStateHolder.isSprinting(rider.getUuid())  ? -0.2
                    : 0;

            // fly
            this.setNoGravity(true);
            Vec3d vel = horiz.add(0, vert, 0);
            this.setVelocity(vel);
            this.move(MovementType.SELF, vel);
            this.velocityModified = true;
        } else {
            // back to normal when not flying
            this.setNoGravity(false);
            super.travel(movementInput);
        }
    }



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
