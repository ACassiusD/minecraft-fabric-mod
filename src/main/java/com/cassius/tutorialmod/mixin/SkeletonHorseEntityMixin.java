package com.cassius.tutorialmod.mixin;

import com.cassius.tutorialmod.entity.custom.PegasusEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.DonkeyEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.mob.SkeletonHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

/**
 * Mix into the vanilla SkeletonHorseEntity so it can breed like a normal horse.
 */
@Mixin(SkeletonHorseEntity.class)
public abstract class SkeletonHorseEntityMixin extends AbstractHorseEntity {

    // Match the constructor signature so your mixin is valid
    protected SkeletonHorseEntityMixin(EntityType<? extends AbstractHorseEntity> type, World world) {
        super(type, world);
    }

    /**
     * SkeletonHorseEntity does not have a canBreedWith method, but AbstractHorseEntity does.
     * Inject the method and make sure its overriding the one in AbstractHorseEntity
     *
     * Override inherited canBreedWith to allow skeleton horses to breed.
     */
    @Unique
    @Override
    public boolean canBreedWith(AnimalEntity other) {
        if (other == this) return false;
        boolean ok = other instanceof PegasusEntity;
        // call the inherited protected method directly:
        return ok && this.canBreed() && ((AbstractHorseEntity)other).canBreed();
    }

    /**
     * Mixin method intended to override interactMob in SkeletonHorseEntity
     * @author cassius
     * @reason To allow interactHorse to be called which feeds the horse and allows it to breed
     */
    @Overwrite
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
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
     //     * If you want baby skeleton horses rather than vanilla behavior:
     //     */
//    @Overwrite
//    @Override
//    public PassiveEntity createChild(ServerWorld world, PassiveEntity mate) {
//        return EntityType.SKELETON_HORSE.create(world, SpawnReason.BREEDING);
//    }

}
