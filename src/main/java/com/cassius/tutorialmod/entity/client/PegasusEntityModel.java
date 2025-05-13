package com.cassius.tutorialmod.entity.client;

import com.cassius.tutorialmod.TutorialMod;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.ModelTransformer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

/**
 * Defines a new custom model for the Pegasus that was exported with BlockBench in getTexturedModelData
 * Extends the AbstractPegasusEntityModel to get vanilla horse animations
 * Wire the custom model parts the same way they are for vanilla horses so animations map perfectly
 */
public class PegasusEntityModel extends AbstractPegasusEntityModel<PegasusEntityRenderState> {

    //Define the main layer for Pegasus Model
    public static final EntityModelLayer PEGASUS_LAYER = new EntityModelLayer(Identifier.of(TutorialMod.MOD_ID, "pegasus"), "main");

    //Constructor
    public PegasusEntityModel(ModelPart root) {
        super(root);
    }

    //Define the model shape and layout to be registered in TutorialModClient.java
    public static TexturedModelData getTexturedModelData() {
        ModelTransformer modelTransformer = ModelTransformer.scaling(1.1F);
        return TexturedModelData.of(AbstractPegasusEntityModel.getModelData(Dilation.NONE), 64, 64).transform(modelTransformer);
    }

}










