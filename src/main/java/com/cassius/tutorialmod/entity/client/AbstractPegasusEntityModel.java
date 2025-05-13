package com.cassius.tutorialmod.entity.client;

import java.util.Set;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.render.entity.model.BabyModelTransformer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.ModelTransformer;
import net.minecraft.client.render.entity.state.LivingHorseEntityRenderState;
import net.minecraft.util.math.MathHelper;

/**
 * A clone of AbstractHorseEntityModel used for the Pegasus
 * Only chane is making the fields for ModelPart public like body, head, rightHindLeg, leftHindLeg, rightFrontLeg, leftFrontLeg, tail
 * This allows us to keep vanilla horse animations
 * And also allows us to update the model being used in classes that extend this class like PegasusEntityModel
 */

@Environment(EnvType.CLIENT)
public abstract class AbstractPegasusEntityModel<T extends LivingHorseEntityRenderState> extends EntityModel<T> {
    private static final float EATING_GRASS_ANIMATION_HEAD_BASE_PITCH = 2.1816616F;
    private static final float ANGRY_ANIMATION_FRONT_LEG_PITCH_MULTIPLIER = (float) (Math.PI / 3);
    private static final float ANGRY_ANIMATION_BODY_PITCH_MULTIPLIER = (float) (Math.PI / 4);
    private static final float HEAD_TAIL_BASE_PITCH = (float) (Math.PI / 6);
    private static final float ANGRY_ANIMATION_HIND_LEG_PITCH_MULTIPLIER = (float) (Math.PI / 12);
    /**
     * The key of the model part containing the head model parts, whose value is {@value}.
     */
    protected static final String HEAD_PARTS = "head_parts";
    protected static final ModelTransformer BABY_TRANSFORMER = new BabyModelTransformer(true, 16.2F, 1.36F, 2.7272F, 2.0F, 20.0F, Set.of("head_parts"));
    public ModelPart body;
    public ModelPart head;
    public ModelPart rightHindLeg;
    public ModelPart leftHindLeg;
    public ModelPart rightFrontLeg;
    public ModelPart leftFrontLeg;
    public ModelPart tail;

    public AbstractPegasusEntityModel(ModelPart modelPart) {
        super(modelPart);
        this.body = modelPart.getChild(EntityModelPartNames.BODY);
        this.head = modelPart.getChild("head_parts");
        this.rightHindLeg = modelPart.getChild(EntityModelPartNames.RIGHT_HIND_LEG);
        this.leftHindLeg = modelPart.getChild(EntityModelPartNames.LEFT_HIND_LEG);
        this.rightFrontLeg = modelPart.getChild(EntityModelPartNames.RIGHT_FRONT_LEG);
        this.leftFrontLeg = modelPart.getChild(EntityModelPartNames.LEFT_FRONT_LEG);
        this.tail = this.body.getChild(EntityModelPartNames.TAIL);
    }

    public static ModelData getModelData(Dilation dilation) {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData modelPartData2 = modelPartData.addChild(
                EntityModelPartNames.BODY,
                ModelPartBuilder.create().uv(0, 32).cuboid(-5.0F, -8.0F, -17.0F, 10.0F, 10.0F, 22.0F, new Dilation(0.05F)),
                ModelTransform.origin(0.0F, 11.0F, 5.0F)
        );
        ModelPartData modelPartData3 = modelPartData.addChild(
                "head_parts",
                ModelPartBuilder.create().uv(0, 35).cuboid(-2.05F, -6.0F, -2.0F, 4.0F, 12.0F, 7.0F),
                ModelTransform.of(0.0F, 4.0F, -12.0F, (float) (Math.PI / 6), 0.0F, 0.0F)
        );
        ModelPartData modelPartData4 = modelPartData3.addChild(
                EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 13).cuboid(-3.0F, -11.0F, -2.0F, 6.0F, 5.0F, 7.0F, dilation), ModelTransform.NONE
        );
        modelPartData3.addChild(
                EntityModelPartNames.MANE, ModelPartBuilder.create().uv(56, 36).cuboid(-1.0F, -11.0F, 5.01F, 2.0F, 16.0F, 2.0F, dilation), ModelTransform.NONE
        );
        modelPartData3.addChild("upper_mouth", ModelPartBuilder.create().uv(0, 25).cuboid(-2.0F, -11.0F, -7.0F, 4.0F, 5.0F, 5.0F, dilation), ModelTransform.NONE);
        modelPartData.addChild(
                EntityModelPartNames.LEFT_HIND_LEG,
                ModelPartBuilder.create().uv(48, 21).mirrored().cuboid(-3.0F, -1.01F, -1.0F, 4.0F, 11.0F, 4.0F, dilation),
                ModelTransform.origin(4.0F, 14.0F, 7.0F)
        );
        modelPartData.addChild(
                EntityModelPartNames.RIGHT_HIND_LEG,
                ModelPartBuilder.create().uv(48, 21).cuboid(-1.0F, -1.01F, -1.0F, 4.0F, 11.0F, 4.0F, dilation),
                ModelTransform.origin(-4.0F, 14.0F, 7.0F)
        );
        modelPartData.addChild(
                EntityModelPartNames.LEFT_FRONT_LEG,
                ModelPartBuilder.create().uv(48, 21).mirrored().cuboid(-3.0F, -1.01F, -1.9F, 4.0F, 11.0F, 4.0F, dilation),
                ModelTransform.origin(4.0F, 14.0F, -10.0F)
        );
        modelPartData.addChild(
                EntityModelPartNames.RIGHT_FRONT_LEG,
                ModelPartBuilder.create().uv(48, 21).cuboid(-1.0F, -1.01F, -1.9F, 4.0F, 11.0F, 4.0F, dilation),
                ModelTransform.origin(-4.0F, 14.0F, -10.0F)
        );
        modelPartData2.addChild(
                EntityModelPartNames.TAIL,
                ModelPartBuilder.create().uv(42, 36).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 14.0F, 4.0F, dilation),
                ModelTransform.of(0.0F, -5.0F, 2.0F, (float) (Math.PI / 6), 0.0F, 0.0F)
        );
        modelPartData4.addChild(
                EntityModelPartNames.LEFT_EAR,
                ModelPartBuilder.create().uv(19, 16).cuboid(0.55F, -13.0F, 4.0F, 2.0F, 3.0F, 1.0F, new Dilation(-0.001F)),
                ModelTransform.NONE
        );
        modelPartData4.addChild(
                EntityModelPartNames.RIGHT_EAR,
                ModelPartBuilder.create().uv(19, 16).cuboid(-2.55F, -13.0F, 4.0F, 2.0F, 3.0F, 1.0F, new Dilation(-0.001F)),
                ModelTransform.NONE
        );
        return modelData;
    }

    public static ModelData getBabyHorseModelData(Dilation dilation) {
        return BABY_TRANSFORMER.apply(getBabyModelData(dilation));
    }

    protected static ModelData getBabyModelData(Dilation dilation) {
        ModelData modelData = getModelData(dilation);
        ModelPartData modelPartData = modelData.getRoot();
        Dilation dilation2 = dilation.add(0.0F, 5.5F, 0.0F);
        modelPartData.addChild(
                EntityModelPartNames.LEFT_HIND_LEG,
                ModelPartBuilder.create().uv(48, 21).mirrored().cuboid(-3.0F, -1.01F, -1.0F, 4.0F, 11.0F, 4.0F, dilation2),
                ModelTransform.origin(4.0F, 14.0F, 7.0F)
        );
        modelPartData.addChild(
                EntityModelPartNames.RIGHT_HIND_LEG,
                ModelPartBuilder.create().uv(48, 21).cuboid(-1.0F, -1.01F, -1.0F, 4.0F, 11.0F, 4.0F, dilation2),
                ModelTransform.origin(-4.0F, 14.0F, 7.0F)
        );
        modelPartData.addChild(
                EntityModelPartNames.LEFT_FRONT_LEG,
                ModelPartBuilder.create().uv(48, 21).mirrored().cuboid(-3.0F, -1.01F, -1.9F, 4.0F, 11.0F, 4.0F, dilation2),
                ModelTransform.origin(4.0F, 14.0F, -10.0F)
        );
        modelPartData.addChild(
                EntityModelPartNames.RIGHT_FRONT_LEG,
                ModelPartBuilder.create().uv(48, 21).cuboid(-1.0F, -1.01F, -1.9F, 4.0F, 11.0F, 4.0F, dilation2),
                ModelTransform.origin(-4.0F, 14.0F, -10.0F)
        );
        return modelData;
    }

    public void setAngles(T livingHorseEntityRenderState) {
        super.setAngles(livingHorseEntityRenderState);
        float f = MathHelper.clamp(livingHorseEntityRenderState.relativeHeadYaw, -20.0F, 20.0F);
        float g = livingHorseEntityRenderState.pitch * (float) (Math.PI / 180.0);
        float h = livingHorseEntityRenderState.limbSwingAmplitude;
        float i = livingHorseEntityRenderState.limbSwingAnimationProgress;
        if (h > 0.2F) {
            g += MathHelper.cos(i * 0.8F) * 0.15F * h;
        }

        float j = livingHorseEntityRenderState.eatingGrassAnimationProgress;
        float k = livingHorseEntityRenderState.angryAnimationProgress;
        float l = 1.0F - k;
        float m = livingHorseEntityRenderState.eatingAnimationProgress;
        boolean bl = livingHorseEntityRenderState.waggingTail;
        this.head.pitch = (float) (Math.PI / 6) + g;
        this.head.yaw = f * (float) (Math.PI / 180.0);
        float n = livingHorseEntityRenderState.touchingWater ? 0.2F : 1.0F;
        float o = MathHelper.cos(n * i * 0.6662F + (float) Math.PI);
        float p = o * 0.8F * h;
        float q = (1.0F - Math.max(k, j)) * ((float) (Math.PI / 6) + g + m * MathHelper.sin(livingHorseEntityRenderState.age) * 0.05F);
        this.head.pitch = k * ((float) (Math.PI / 12) + g) + j * (2.1816616F + MathHelper.sin(livingHorseEntityRenderState.age) * 0.05F) + q;
        this.head.yaw = k * f * (float) (Math.PI / 180.0) + (1.0F - Math.max(k, j)) * this.head.yaw;
        float r = livingHorseEntityRenderState.ageScale;
        this.head.originY = this.head.originY + MathHelper.lerp(j, MathHelper.lerp(k, 0.0F, -8.0F * r), 7.0F * r);
        this.head.originZ = MathHelper.lerp(k, this.head.originZ, -4.0F * r);
        this.body.pitch = k * (float) (-Math.PI / 4) + l * this.body.pitch;
        float s = (float) (Math.PI / 12) * k;
        float t = MathHelper.cos(livingHorseEntityRenderState.age * 0.6F + (float) Math.PI);
        this.leftFrontLeg.originY -= 12.0F * r * k;
        this.leftFrontLeg.originZ += 4.0F * r * k;
        this.rightFrontLeg.originY = this.leftFrontLeg.originY;
        this.rightFrontLeg.originZ = this.leftFrontLeg.originZ;
        float u = ((float) (-Math.PI / 3) + t) * k + p * l;
        float v = ((float) (-Math.PI / 3) - t) * k - p * l;
        this.leftHindLeg.pitch = s - o * 0.5F * h * l;
        this.rightHindLeg.pitch = s + o * 0.5F * h * l;
        this.leftFrontLeg.pitch = u;
        this.rightFrontLeg.pitch = v;
        this.tail.pitch = (float) (Math.PI / 6) + h * 0.75F;
        this.tail.originY += h * r;
        this.tail.originZ += h * 2.0F * r;
        if (bl) {
            this.tail.yaw = MathHelper.cos(livingHorseEntityRenderState.age * 0.7F);
        } else {
            this.tail.yaw = 0.0F;
        }
    }
}
