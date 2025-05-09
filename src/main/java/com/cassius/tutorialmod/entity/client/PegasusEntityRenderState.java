package com.cassius.tutorialmod.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.LivingHorseEntityRenderState;
import net.minecraft.entity.passive.HorseColor;
import net.minecraft.entity.passive.HorseMarking;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
public class PegasusEntityRenderState  extends LivingHorseEntityRenderState {
    public HorseColor color = HorseColor.WHITE;
    public HorseMarking marking = HorseMarking.NONE;
    public ItemStack armor = ItemStack.EMPTY;
}

