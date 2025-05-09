package com.cassius.tutorialmod.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
public class MantisEntityRenderState extends LivingEntityRenderState {
	public final AnimationState idlingAnimationState = new AnimationState();
}
