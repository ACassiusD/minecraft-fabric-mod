package com.cassius.tutorialmod.entity.client;

import com.cassius.tutorialmod.TutorialMod;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

/**
 * Defines How the Mantis is going to look
 */
public class MantisEntityModel extends EntityModel<MantisEntityRenderState> {

    //EntityModelLayer is a key used to uniquely identify the baked model
    //Layer is just a named group of model parts — like a category.
    //i.e. "main" → the base body model, "armor" → an extra visual layer drawn on top, "glow" → a glowing effect layer
    public static final EntityModelLayer MANTIS = new EntityModelLayer(Identifier.of(TutorialMod.MOD_ID, "mantis"), "main");
    private final ModelPart root;
    private final ModelPart mantis;
    private final ModelPart head;

    public MantisEntityModel(ModelPart root) {
        super(root);
        this.root = root.getChild("root");
        this.mantis = this.root.getChild("mantis");
        this.head = this.mantis.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData mantis = root.addChild("mantis", ModelPartBuilder.create(), ModelTransform.of(-0.5F, -3.0F, -28.0F, 0.3927F, 0.0F, 0.0F));

        ModelPartData body = mantis.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-2.5F, -1.0F, 8.75F, 5.0F, 2.0F, 25.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-3.5F, -2.0F, -51.25F, 7.0F, 4.0F, 60.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -12.0F, 26.25F));

        ModelPartData head = mantis.addChild("head", ModelPartBuilder.create().uv(31, 27).cuboid(-3.5F, -1.0F, 0.0F, 7.0F, 4.0F, 3.0F, new Dilation(0.0F))
                .uv(35, 20).cuboid(-2.5F, 3.0F, 0.0F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.0F, 60.0F, 0.3491F, 0.0F, 0.0F));

        ModelPartData antenna1 = head.addChild("antenna1", ModelPartBuilder.create().uv(56, 71).cuboid(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 20.0F, new Dilation(0.0F)), ModelTransform.of(-1.5F, -1.0F, 3.0F, 0.4363F, 0.0F, 0.0F));

        ModelPartData antenna2 = head.addChild("antenna2", ModelPartBuilder.create().uv(56, 71).cuboid(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 20.0F, new Dilation(0.0F)), ModelTransform.of(1.5F, -1.0F, 3.0F, 0.4363F, 0.0F, 0.0F));

        ModelPartData mouth1 = head.addChild("mouth1", ModelPartBuilder.create().uv(14, 0).cuboid(-2.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.origin(2.5F, 5.0F, 1.0F));

        ModelPartData mouth2 = head.addChild("mouth2", ModelPartBuilder.create().uv(6, 0).cuboid(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.origin(-2.5F, 5.0F, 1.0F));

        ModelPartData wing1 = mantis.addChild("wing1", ModelPartBuilder.create().uv(56, 0).cuboid(-3.5F, 0.0F, -60.0F, 7.0F, 0.0F, 60.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -14.04F, 35.0F));

        ModelPartData wing2 = mantis.addChild("wing2", ModelPartBuilder.create().uv(56, 0).mirrored().cuboid(-3.5F, 0.0F, -60.0F, 7.0F, 0.0F, 60.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(0.0F, -14.03F, 35.0F));

        ModelPartData wing3 = mantis.addChild("wing3", ModelPartBuilder.create().uv(28, 0).cuboid(-3.5F, 0.0F, -60.0F, 7.0F, 0.0F, 60.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -14.02F, 35.0F));

        ModelPartData wing4 = mantis.addChild("wing4", ModelPartBuilder.create().uv(28, 0).mirrored().cuboid(-3.5F, 0.0F, -60.0F, 7.0F, 0.0F, 60.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(0.0F, -14.01F, 35.0F));

        ModelPartData arm1 = mantis.addChild("arm1", ModelPartBuilder.create().uv(8, 0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 20.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(-2.5F, -11.0F, 51.0F));

        ModelPartData arm1seg2 = arm1.addChild("arm1seg2", ModelPartBuilder.create().uv(0, 64).cuboid(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 25.0F, new Dilation(0.0F))
                .uv(31, 62).cuboid(0.0F, 1.5F, -2.0F, 0.0F, 6.0F, 20.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 19.5F, 0.0F));

        ModelPartData arm1seg3 = arm1seg2.addChild("arm1seg3", ModelPartBuilder.create().uv(10, 27).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 20.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 24).cuboid(0.0F, 0.0F, -3.5F, 0.0F, 20.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.5F, 21.5F));

        ModelPartData arm2 = mantis.addChild("arm2", ModelPartBuilder.create().uv(8, 0).mirrored().cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 20.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(2.5F, -11.0F, 51.0F));

        ModelPartData arm2seg2 = arm2.addChild("arm2seg2", ModelPartBuilder.create().uv(0, 64).mirrored().cuboid(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 25.0F, new Dilation(0.0F)).mirrored(false)
                .uv(31, 62).cuboid(0.0F, 1.5F, -2.0F, 0.0F, 6.0F, 20.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 19.5F, 0.0F));

        ModelPartData arm2seg3 = arm2seg2.addChild("arm2seg3", ModelPartBuilder.create().uv(10, 27).mirrored().cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 20.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(0, 25).cuboid(0.0F, 0.0F, -3.5F, 0.0F, 20.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.5F, 21.5F));

        ModelPartData frontLeg1 = mantis.addChild("frontLeg1", ModelPartBuilder.create().uv(39, 34).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-3.5F, -10.0F, 27.0F, -0.7854F, 0.0F, 0.0F));

        ModelPartData frontLeg1seg2 = frontLeg1.addChild("frontLeg1seg2", ModelPartBuilder.create().uv(54, 94).cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 18.0F, new Dilation(0.0F))
                .uv(36, 76).cuboid(0.0F, 0.5F, 0.0F, 0.0F, 5.0F, 18.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 5.5F, 0.0F, 0.3927F, -0.4363F, 0.0F));

        ModelPartData frontLeg1seg3 = frontLeg1seg2.addChild("frontLeg1seg3", ModelPartBuilder.create().uv(18, 27).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 18.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 17.5F, 0.2618F, 0.0F, 0.0F));

        ModelPartData frontLeg2 = mantis.addChild("frontLeg2", ModelPartBuilder.create().uv(39, 34).mirrored().cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(3.5F, -10.0F, 27.0F, -0.7854F, 0.0F, 0.0F));

        ModelPartData frontLeg2seg2 = frontLeg2.addChild("frontLeg2seg2", ModelPartBuilder.create().uv(54, 94).mirrored().cuboid(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 18.0F, new Dilation(0.0F)).mirrored(false)
                .uv(36, 76).cuboid(0.0F, 0.5F, 0.0F, 0.0F, 5.0F, 18.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 5.5F, 0.0F, 0.3927F, 0.4363F, 0.0F));

        ModelPartData frontLeg2seg3 = frontLeg2seg2.addChild("frontLeg2seg3", ModelPartBuilder.create().uv(18, 27).mirrored().cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 18.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.5F, 17.5F, 0.2618F, 0.0F, 0.0F));

        ModelPartData backLeg1 = mantis.addChild("backLeg1", ModelPartBuilder.create().uv(35, 0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-3.5F, -10.0F, 20.0F, -0.7854F, 0.0F, 0.0F));

        ModelPartData backLeg1seg2 = backLeg1.addChild("backLeg1seg2", ModelPartBuilder.create().uv(83, 65).cuboid(-0.5F, -0.5F, -25.0F, 1.0F, 1.0F, 25.0F, new Dilation(0.0F))
                .uv(31, 45).cuboid(0.0F, 0.5F, -25.0F, 0.0F, 6.0F, 25.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 5.5F, 0.0F, 0.0F, 0.4363F, 0.5236F));

        ModelPartData backLeg1seg3 = backLeg1seg2.addChild("backLeg1seg3", ModelPartBuilder.create().uv(4, 97).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 35.0F, 1.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.5F, -24.5F));

        ModelPartData backLeg2 = mantis.addChild("backLeg2", ModelPartBuilder.create().uv(35, 0).mirrored().cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(3.5F, -10.0F, 20.0F, -0.7854F, 0.0F, 0.0F));

        ModelPartData backLeg2seg2 = backLeg2.addChild("backLeg2seg2", ModelPartBuilder.create().uv(83, 65).mirrored().cuboid(-0.5F, -0.5F, -25.0F, 1.0F, 1.0F, 25.0F, new Dilation(0.0F)).mirrored(false)
                .uv(31, 45).cuboid(0.0F, 0.5F, -25.0F, 0.0F, 6.0F, 25.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 5.5F, 0.0F, 0.0F, -0.4363F, -0.5236F));

        ModelPartData backLeg2seg3 = backLeg2seg2.addChild("backLeg2seg3", ModelPartBuilder.create().uv(4, 97).mirrored().cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 35.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(0.0F, 0.5F, -24.5F));
        return TexturedModelData.of(modelData, 256, 256);
    }

    /**
     * A function used for animating a model, particularly for entities, by controlling the rotation of its parts
     * Poses the model for this frame.
     * Applies head rotation, walking animation, and idle animation based on entity state.
     * Called every render tick to update model part angles.
     *
     * Here we also supply are all possible animations the entity might be playing.
     * Each AnimationState (like sittingAnimationState, dashingAnimationState, etc.) tracks if it's active or not.
     *
     * @param mantisEntityRenderState The current render state of the entity.
     */
    @Override
    public void setAngles(MantisEntityRenderState mantisEntityRenderState) {
        super.setAngles(mantisEntityRenderState);

        // Rotates the head to follow look direction.
        this.setHeadAngles(mantisEntityRenderState, mantisEntityRenderState.relativeHeadYaw, mantisEntityRenderState.pitch);

        //Calculates limb rotation angles from the walking motion (limb swing and amplitude) and applies them to model parts.
        this.animateWalking(MantisAnimations.ANIM_MANTIS_WALK, mantisEntityRenderState.limbSwingAnimationProgress, mantisEntityRenderState.limbSwingAmplitude, 2.0F, 2.5F);

        // Applies looping idle animation (e.g., breathing or antenna twitch) while standing still.
        // Rotates (sets angles on) model parts based on a keyframed animation file.
        this.animate(mantisEntityRenderState.idlingAnimationState, MantisAnimations.ANIM_MANTIS_IDLE, mantisEntityRenderState.age, 1.0F);
    }

    /**
     * Move the head, copied from CamelEntity
     * @param state
     * @param headYaw
     * @param headPitch
     */
    private void setHeadAngles(MantisEntityRenderState state, float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.head.yaw = headYaw * (float) (Math.PI / 180.0);
        this.head.pitch = headPitch * (float) (Math.PI / 180.0);
    }
}
