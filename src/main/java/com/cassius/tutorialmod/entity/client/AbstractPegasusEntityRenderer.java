package com.cassius.tutorialmod.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.AgeableMobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingHorseEntityRenderState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.passive.AbstractHorseEntity;

@Environment(EnvType.CLIENT)
public abstract class AbstractPegasusEntityRenderer<T extends AbstractHorseEntity, S extends LivingHorseEntityRenderState, M extends EntityModel<? super S>>
        extends AgeableMobEntityRenderer<T, S, M> {
    public AbstractPegasusEntityRenderer(EntityRendererFactory.Context context, M model, M babyModel) {
        super(context, model, babyModel, 0.75F);
    }

    public void updateRenderState(T abstractHorseEntity, S livingHorseEntityRenderState, float f) {
        super.updateRenderState(abstractHorseEntity, livingHorseEntityRenderState, f);
        livingHorseEntityRenderState.saddleStack = abstractHorseEntity.getEquippedStack(EquipmentSlot.SADDLE).copy();
        livingHorseEntityRenderState.hasPassengers = abstractHorseEntity.hasPassengers();
        livingHorseEntityRenderState.eatingGrassAnimationProgress = abstractHorseEntity.getEatingGrassAnimationProgress(f);
        livingHorseEntityRenderState.angryAnimationProgress = abstractHorseEntity.getAngryAnimationProgress(f);
        livingHorseEntityRenderState.eatingAnimationProgress = abstractHorseEntity.getEatingAnimationProgress(f);
        livingHorseEntityRenderState.waggingTail = abstractHorseEntity.tailWagTicks > 0;
    }
}
