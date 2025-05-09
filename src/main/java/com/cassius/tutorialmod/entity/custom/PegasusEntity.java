package com.cassius.tutorialmod.entity.custom;

import com.cassius.tutorialmod.entity.ModEntitiesRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PegasusEntity extends AbstractHorseEntity {

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
    public void tickMovement() {
        super.tickMovement();

        if (this.isFlying()) {
            this.setVelocity(this.getVelocity().add(0, 0.08, 0)); // simple lift
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

//    @Override
//    protected SoundEvent getAmbientSound() {
//        return SoundEvents.ENTITY_HORSE_AMBIENT;
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource source) {
//        return SoundEvents.ENTITY_HORSE_HURT;
//    }
//
//    @Override
//    protected SoundEvent getDeathSound() {
//        return SoundEvents.ENTITY_HORSE_DEATH;
//    }
//
//    @Override
//    protected void playJumpSound() {
//        this.playSound(SoundEvents.ENTITY_HORSE_JUMP, 0.4F, 1.0F);
//    }

}
