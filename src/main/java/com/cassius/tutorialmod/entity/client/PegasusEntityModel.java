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

    // Wing parts for flap animation
   public  ModelPart leftWing;
   public  ModelPart rightWing;

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
                // Initialize wings
        ModelPart wings = root.getChild("Wings");
        this.leftWing = wings.getChild("Wing3");
        this.rightWing = wings.getChild("Wing2");
    }

    //Define the model shape and layout to be registered in TutorialModClient.java
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData Body = modelPartData.addChild("Body", ModelPartBuilder.create().uv(0, 32).cuboid(-5.0F, -8.0F, -20.0F, 10.0F, 10.0F, 22.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 11.0F, 9.0F));

        ModelPartData Wings = modelPartData.addChild("Wings", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 11.0F, 9.0F));

        ModelPartData Wing3 = Wings.addChild("Wing3", ModelPartBuilder.create().uv(19, 45).mirrored().cuboid(5.0F, -8.0F, -19.0F, 5.0F, 2.0F, 10.0F, new Dilation(0.0F)).mirrored(false)
                .uv(25, 53).mirrored().cuboid(5.0F, -8.0F, -19.0F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(0.0F, 0.0F, 0.0F));

        ModelPartData Wing114_r1 = Wing3.addChild("Wing114_r1", ModelPartBuilder.create().uv(20, 53).mirrored().cuboid(-4.5F, -21.0F, -11.0F, 10.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(20.0F, 13.0F, -13.0F, 0.0F, 0.7418F, 0.0F));

        ModelPartData Wing113_r1 = Wing3.addChild("Wing113_r1", ModelPartBuilder.create().uv(6, 53).mirrored().cuboid(-5.0F, -21.0F, -11.0F, 24.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(24.0F, 13.0F, -15.0F, 0.0F, 0.1745F, 0.0F));

        ModelPartData Wing111_r1 = Wing3.addChild("Wing111_r1", ModelPartBuilder.create().uv(26, 50).mirrored().cuboid(-8.0F, -21.0F, -13.0F, 4.0F, 2.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(47.0F, 13.0F, -16.0F, 0.0F, 0.1745F, 0.0F));

        ModelPartData Wing110_r1 = Wing3.addChild("Wing110_r1", ModelPartBuilder.create().uv(16, 44).mirrored().cuboid(-5.0F, -21.0F, -13.0F, 5.0F, 2.0F, 11.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(40.0F, 13.0F, -15.0F, 0.0F, 0.2182F, 0.0F));

        ModelPartData Wing19_r1 = Wing3.addChild("Wing19_r1", ModelPartBuilder.create().uv(12, 41).mirrored().cuboid(-7.0F, -21.0F, -14.5F, 8.0F, 2.0F, 14.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(35.0F, 13.0F, -13.0F, 0.0F, 0.2182F, 0.0F));

        ModelPartData Wing18_r1 = Wing3.addChild("Wing18_r1", ModelPartBuilder.create().uv(9, 38).mirrored().cuboid(-6.0F, -21.0F, -13.5F, 7.0F, 2.0F, 17.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(29.0F, 13.0F, -13.0F, 0.0F, 0.2182F, 0.0F));

        ModelPartData Wing121_r1 = Wing3.addChild("Wing121_r1", ModelPartBuilder.create().uv(8, 36).mirrored().cuboid(-6.0F, -21.0F, -12.5F, 6.0F, 2.0F, 19.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(24.0F, 13.0F, -13.0F, 0.0F, 0.3054F, 0.0F));

        ModelPartData Wing17_r1 = Wing3.addChild("Wing17_r1", ModelPartBuilder.create().uv(14, 41).mirrored().cuboid(-5.0F, -21.0F, -9.0F, 4.0F, 2.0F, 14.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(19.0F, 13.0F, -13.0F, 0.0F, 0.3054F, 0.0F));

        ModelPartData Wing16_r1 = Wing3.addChild("Wing16_r1", ModelPartBuilder.create().uv(16, 44).mirrored().cuboid(-6.0F, -21.0F, -9.0F, 6.0F, 2.0F, 11.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(15.0F, 13.0F, -10.0F, 0.0F, 0.1745F, 0.0F));

        ModelPartData Wing2 = Wings.addChild("Wing2", ModelPartBuilder.create().uv(19, 45).cuboid(-10.0F, -8.0F, -20.0F, 5.0F, 2.0F, 10.0F, new Dilation(0.0F))
                .uv(25, 53).cuboid(-10.0F, -8.0F, -20.0F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 1.0F));

        ModelPartData Wing114_r2 = Wing2.addChild("Wing114_r2", ModelPartBuilder.create().uv(20, 53).cuboid(-5.5F, -21.0F, -11.0F, 10.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-20.0F, 13.0F, -14.0F, 0.0F, -0.7418F, 0.0F));

        ModelPartData Wing113_r2 = Wing2.addChild("Wing113_r2", ModelPartBuilder.create().uv(6, 53).cuboid(-19.0F, -21.0F, -11.0F, 24.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-24.0F, 13.0F, -16.0F, 0.0F, -0.1745F, 0.0F));

        ModelPartData Wing111_r2 = Wing2.addChild("Wing111_r2", ModelPartBuilder.create().uv(26, 50).cuboid(4.0F, -21.0F, -13.0F, 4.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-47.0F, 13.0F, -17.0F, 0.0F, -0.1745F, 0.0F));

        ModelPartData Wing110_r2 = Wing2.addChild("Wing110_r2", ModelPartBuilder.create().uv(16, 44).cuboid(0.0F, -21.0F, -13.0F, 5.0F, 2.0F, 11.0F, new Dilation(0.0F)), ModelTransform.of(-40.0F, 13.0F, -16.0F, 0.0F, -0.2182F, 0.0F));

        ModelPartData Wing19_r2 = Wing2.addChild("Wing19_r2", ModelPartBuilder.create().uv(12, 41).cuboid(-1.0F, -21.0F, -14.5F, 8.0F, 2.0F, 14.0F, new Dilation(0.0F)), ModelTransform.of(-35.0F, 13.0F, -14.0F, 0.0F, -0.2182F, 0.0F));

        ModelPartData Wing18_r2 = Wing2.addChild("Wing18_r2", ModelPartBuilder.create().uv(9, 38).cuboid(-1.0F, -21.0F, -13.5F, 7.0F, 2.0F, 17.0F, new Dilation(0.0F)), ModelTransform.of(-29.0F, 13.0F, -14.0F, 0.0F, -0.2182F, 0.0F));

        ModelPartData Wing121_r2 = Wing2.addChild("Wing121_r2", ModelPartBuilder.create().uv(8, 36).cuboid(0.0F, -21.0F, -12.5F, 6.0F, 2.0F, 19.0F, new Dilation(0.0F)), ModelTransform.of(-24.0F, 13.0F, -14.0F, 0.0F, -0.3054F, 0.0F));

        ModelPartData Wing17_r2 = Wing2.addChild("Wing17_r2", ModelPartBuilder.create().uv(14, 41).cuboid(1.0F, -21.0F, -9.0F, 4.0F, 2.0F, 14.0F, new Dilation(0.0F)), ModelTransform.of(-19.0F, 13.0F, -14.0F, 0.0F, -0.3054F, 0.0F));

        ModelPartData Wing16_r2 = Wing2.addChild("Wing16_r2", ModelPartBuilder.create().uv(16, 44).cuboid(0.0F, -21.0F, -9.0F, 6.0F, 2.0F, 11.0F, new Dilation(0.0F)), ModelTransform.of(-15.0F, 13.0F, -11.0F, 0.0F, -0.1745F, 0.0F));

        ModelPartData TailA = modelPartData.addChild("TailA", ModelPartBuilder.create().uv(42, 36).cuboid(-1.5F, 0.0F, -2.0F, 3.0F, 14.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 4.0F, 11.0F, 0.5236F, 0.0F, 0.0F));

        ModelPartData Leg1A = modelPartData.addChild("Leg1A", ModelPartBuilder.create().uv(48, 21).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(3.0F, 13.0F, 9.0F));

        ModelPartData Leg2A = modelPartData.addChild("Leg2A", ModelPartBuilder.create().uv(48, 21).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(-3.0F, 13.0F, 9.0F));

        ModelPartData Leg3A = modelPartData.addChild("Leg3A", ModelPartBuilder.create().uv(48, 21).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(3.0F, 13.0F, -9.0F));

        ModelPartData Leg4A = modelPartData.addChild("Leg4A", ModelPartBuilder.create().uv(48, 21).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 11.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(-3.0F, 13.0F, -9.0F));

        ModelPartData Head = modelPartData.addChild("Head", ModelPartBuilder.create().uv(0, 13).cuboid(-3.0F, -5.0F, -6.0F, 6.0F, 5.0F, 7.0F, new Dilation(0.0F))
                .uv(0, 25).cuboid(-2.0F, -5.0F, -11.0F, 4.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.0F, -11.0F, 0.5236F, 0.0F, 0.0F));

        ModelPartData Horn = modelPartData.addChild("Horn", ModelPartBuilder.create().uv(32, 1).cuboid(-1.0F, -24.0F, -4.0F, 2.0F, 19.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.0F, -11.0F, 0.5236F, 0.0F, 0.0F));

        ModelPartData Ear1 = modelPartData.addChild("Ear1", ModelPartBuilder.create().uv(19, 16).mirrored().cuboid(-0.5F, -18.0F, 2.99F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 7.0F, -8.0F, 0.5236F, 0.0F, 0.0873F));

        ModelPartData Ear2 = modelPartData.addChild("Ear2", ModelPartBuilder.create().uv(19, 16).cuboid(-1.5F, -18.0F, 2.99F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 7.0F, -8.0F, 0.5236F, 0.0F, -0.0873F));

        ModelPartData Neck = modelPartData.addChild("Neck", ModelPartBuilder.create().uv(0, 35).cuboid(-2.0F, -11.0F, -3.0F, 4.0F, 12.0F, 7.0F, new Dilation(0.0F))
                .uv(56, 36).cuboid(-1.0F, -16.0F, 4.0F, 2.0F, 16.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 7.0F, -8.0F, 0.5236F, 0.0F, 0.0F));
        /**
         * This line of code is important. It scales the model the same way the vanilla horse model is scaled.
         * Check net/minecraft/client/render/entity/model/EntityModels.java HORSE Registration for reference
         */
        ModelTransformer modelTransformer = ModelTransformer.scaling(1.1F);

        return TexturedModelData.of(modelData, 64, 64).transform(modelTransformer);
    }

    @Override
    public void setAngles(PegasusEntityRenderState state) {
        super.setAngles(state);

        if (!state.flapEnabled) {
            leftWing .roll = rightWing.roll = 0f;
        } else {
            // ─── timings ────────────────────────────────────────
            float downTicks = 35f;
            float upTicks   =  6f;
            float cycleLen  = downTicks + upTicks;
            // ─── asymmetrical amplitudes ────────────────────────
            float maxDown = (float)Math.toRadians(30);  // how far down
            float maxUp   = (float)Math.toRadians(45);  // how far up
            // ─── compute position in [0,cycleLen) ──────────────
            float t = state.age % cycleLen;
            float stroke;
            if (t < downTicks) {
                // from +maxUp → –maxDown
                float f = t / downTicks;
                stroke = maxUp + ( -maxDown - maxUp ) * f;
            } else {
                // from –maxDown → +maxUp
                float f = (t - downTicks) / upTicks;
                stroke = -maxDown + ( maxUp + maxDown ) * f;
            }

            leftWing .roll =  stroke;
            rightWing.roll = -stroke;
        }

        // lock all other axes
        leftWing .pitch = rightWing.pitch = 0f;
        leftWing .yaw   = rightWing.yaw   = 0f;
    }



}






