/**
 * My first custom mob
 * https://www.youtube.com/watch?v=rQdXWM8Ud90&ab_channel=ModdingbyKaupenjoe
 */
package com.cassius.tutorialmod.entity.custom;

import com.cassius.tutorialmod.entity.ModEntitiesRegistry;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MantisEntity extends AnimalEntity {

    //Constructor
    public MantisEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    //Idle Animation Vars
    public final AnimationState idleAnimationState = new AnimationState();
    private  int idleAnimationTimeout = 0;

    //Set Goals
    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new AnimalMateGoal(this, 1.150));
        this.goalSelector.add(2, new TemptGoal(this, 1.250, Ingredient.ofItems(Items.APPLE), false));
        this.goalSelector.add(3, new FollowParentGoal(this, 1.10));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.00));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 4.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
    }

    //Set Attributes/Stats
    public static DefaultAttributeContainer.Builder createAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 18)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.35)
                .add(EntityAttributes.ATTACK_DAMAGE, 1)
                .add(EntityAttributes.FOLLOW_RANGE, 20).add(EntityAttributes.TEMPT_RANGE, 10);

    }

    //Animation

    //Restart the animation every 40 ticks
    private void setupAnimationStates(){
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            //this.age = number of ticks the entity has been alive.
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    //Every tick, set up the animation states
    @Override
    public void tick(){
        super.tick();

        if(this.getWorld().isClient()) {
            this.setupAnimationStates();
        }
    }


    //Returns true if is apple, otherwise returns false
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.APPLE);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntitiesRegistry.MANTIS.create(world, SpawnReason.BREEDING);
    }
}
