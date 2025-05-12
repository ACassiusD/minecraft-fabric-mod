package com.cassius.tutorialmod.entity.client;

import com.cassius.tutorialmod.TutorialMod;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelLayer;
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
        this.body = root.getChild("Body");
        this.tail = root.getChild("TailA");
        this.leftHindLeg = root.getChild("Leg1A");
        this.rightHindLeg = root.getChild("Leg2A");
        this.leftFrontLeg = root.getChild("Leg3A");
        this.rightFrontLeg = root.getChild("Leg4A");
        this.head = root.getChild("Head");
    }

    //Define the model shape and layout to be registered in TutorialModClient.java
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData Body = modelPartData.addChild("Body", ModelPartBuilder.create().uv(0, 32).cuboid(-5.0F, -8.0F, -20.0F, 10.0F, 10.0F, 22.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 11.0F, 9.0F));

        ModelPartData TailA = modelPartData.addChild("TailA", ModelPartBuilder.create().uv(42, 36).cuboid(-1.5F, 0.0F, -2.0F, 3.0F, 14.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 4.0F, 11.0F, 0.5236F, 0.0F, 0.0F));

        ModelPartData Leg1A = modelPartData.addChild("Leg1A", ModelPartBuilder.create().uv(48, 21).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(3.0F, 13.0F, 9.0F));

        ModelPartData Leg2A = modelPartData.addChild("Leg2A", ModelPartBuilder.create().uv(48, 21).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(-3.0F, 13.0F, 9.0F));

        ModelPartData Leg3A = modelPartData.addChild("Leg3A", ModelPartBuilder.create().uv(48, 21).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(3.0F, 13.0F, -9.0F));

        ModelPartData Leg4A = modelPartData.addChild("Leg4A", ModelPartBuilder.create().uv(48, 21).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(-3.0F, 13.0F, -9.0F));

        ModelPartData Head = modelPartData.addChild("Head", ModelPartBuilder.create().uv(0, 13).cuboid(-3.0F, -5.0F, -6.0F, 6.0F, 5.0F, 7.0F, new Dilation(0.0F))
                .uv(0, 25).cuboid(-2.0F, -5.0F, -11.0F, 4.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.0F, -11.0F, 0.5236F, 0.0F, 0.0F));

        ModelPartData Ear1 = modelPartData.addChild("Ear1", ModelPartBuilder.create().uv(19, 16).mirrored().cuboid(-0.5F, -18.0F, 2.99F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 7.0F, -8.0F, 0.5236F, 0.0F, 0.0873F));

        ModelPartData Ear2 = modelPartData.addChild("Ear2", ModelPartBuilder.create().uv(19, 16).cuboid(-1.5F, -18.0F, 2.99F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 7.0F, -8.0F, 0.5236F, 0.0F, -0.0873F));

        ModelPartData MuleEarL = modelPartData.addChild("MuleEarL", ModelPartBuilder.create().uv(0, 12).mirrored().cuboid(-3.0F, -22.0F, 2.99F, 2.0F, 7.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 7.0F, -8.0F, 0.5236F, 0.0F, 0.2618F));

        ModelPartData MuleEarR = modelPartData.addChild("MuleEarR", ModelPartBuilder.create().uv(0, 12).cuboid(1.0F, -22.0F, 2.99F, 2.0F, 7.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 7.0F, -8.0F, 0.5236F, 0.0F, -0.2618F));

        ModelPartData Neck = modelPartData.addChild("Neck", ModelPartBuilder.create().uv(0, 35).cuboid(-2.0F, -11.0F, -3.0F, 4.0F, 12.0F, 7.0F, new Dilation(0.0F))
                .uv(56, 36).cuboid(-1.0F, -16.0F, 4.0F, 2.0F, 16.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 7.0F, -8.0F, 0.5236F, 0.0F, 0.0F));

        ModelPartData Bag1 = modelPartData.addChild("Bag1", ModelPartBuilder.create().uv(26, 21).cuboid(-9.0F, 0.0F, 0.0F, 8.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, 3.0F, 11.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData Bag2 = modelPartData.addChild("Bag2", ModelPartBuilder.create().uv(26, 21).mirrored().cuboid(1.0F, 0.0F, 0.0F, 8.0F, 8.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(5.0F, 3.0F, 11.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData Saddle = modelPartData.addChild("Saddle", ModelPartBuilder.create().uv(26, 0).cuboid(-5.0F, 1.0F, -5.5F, 10.0F, 9.0F, 9.0F, new Dilation(0.5F)), ModelTransform.origin(0.0F, 2.0F, 2.0F));

        ModelPartData SaddleMouthL = modelPartData.addChild("SaddleMouthL", ModelPartBuilder.create().uv(29, 5).cuboid(2.0F, -14.0F, -6.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 7.0F, -8.0F, 0.5236F, 0.0F, 0.0F));

        ModelPartData SaddleMouthR = modelPartData.addChild("SaddleMouthR", ModelPartBuilder.create().uv(29, 5).cuboid(-3.0F, -14.0F, -6.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 7.0F, -8.0F, 0.5236F, 0.0F, 0.0F));

        ModelPartData SaddleMouthLine = modelPartData.addChild("SaddleMouthLine", ModelPartBuilder.create().uv(32, 2).cuboid(3.1F, -10.0F, -11.5F, 0.0F, 3.0F, 16.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 7.0F, -8.0F));

        ModelPartData SaddleMouthLineR = modelPartData.addChild("SaddleMouthLineR", ModelPartBuilder.create().uv(32, 2).cuboid(-3.1F, -10.0F, -11.5F, 0.0F, 3.0F, 16.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 7.0F, -8.0F));

        ModelPartData HeadSaddle = modelPartData.addChild("HeadSaddle", ModelPartBuilder.create().uv(19, 0).cuboid(-2.0F, -16.0F, -5.0F, 4.0F, 5.0F, 2.0F, new Dilation(0.25F))
                .uv(0, 0).cuboid(-3.0F, -16.0F, -3.0F, 6.0F, 5.0F, 7.0F, new Dilation(0.25F)), ModelTransform.of(0.0F, 7.0F, -8.0F, 0.5236F, 0.0F, 0.0F));

        ModelPartData Horn = modelPartData.addChild("Horn", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 24.0F, 0.0F));

        ModelPartData Horn_r1 = Horn.addChild("Horn_r1", ModelPartBuilder.create().uv(7, 18).cuboid(-1.0F, -8.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -32.0F, -17.0F, 0.7418F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

}
