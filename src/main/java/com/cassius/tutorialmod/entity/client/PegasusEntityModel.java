package com.cassius.tutorialmod.entity.client;

import com.cassius.tutorialmod.TutorialMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.AbstractHorseEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.state.LivingHorseEntityRenderState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

/**
 * Defines a custom model and layer for the Pegasus entity. To be Registered in TutorialModClient.java
 *
 * Pegasus's Model is a modified version of the vanilla horse model that adds a pair of wings to the standard horse mesh.
 * Wings share the vanilla animations and are given a simple flap.
 */
@Environment(EnvType.CLIENT)
public class PegasusEntityModel extends AbstractHorseEntityModel<LivingHorseEntityRenderState> {

    //Define the model layer for the Pegasus entity to be registered
    public static final EntityModelLayer PEGASUS_LAYER =
            new EntityModelLayer(Identifier.of(TutorialMod.MOD_ID, "pegasus"), "main");

    // References to the newly added wing parts in the model tree.
    private final ModelPart leftWing;
    private final ModelPart rightWing;

    /**
     * Constructor: takes the root model part (horse + wings) and finds our wing children by name.
     */
    public PegasusEntityModel(ModelPart root) {
        super(root);  // Initialize parent horse fields (body, head, legs, tail)
        this.leftWing  = root.getChild("left_wing");   // Retrieve the left wing
        this.rightWing = root.getChild("right_wing");  // Retrieve the right wing
    }

    /**
     * Defines the model's shape and layout to be registered in TutorialModClient.java
     * Builds the horse + wings mesh and UV layout.
     * Called once at startup so Minecraft knows how to bake our model.
     */
    public static TexturedModelData getTexturedModelData() {
        // Step 1: Get vanilla horse shape (body, legs, head, tail)
        ModelData data = AbstractHorseEntityModel.getModelData(Dilation.NONE);
        ModelPartData root = data.getRoot();

        // Step 2: Define left wing as a flat cuboid
        root.addChild(
                "left_wing",                   // identifier for lookup in constructor
                ModelPartBuilder.create()
                        .uv(0, 54)                 // UV origin on a 64×64 texture
                        .cuboid(
                                /* x */ -1.0F, /* y */ -1.0F, /* z */  0.0F,
                                /*  width */ 16, /* height */  1, /* depth */  8,
                                Dilation.NONE            // no extra padding
                        ),
                ModelTransform.of(
                        /* pivotX */  5.0F, /* pivotY */  5.0F, /* pivotZ */ -10.0F,
                        /* rotX */   0,   /* rotY */   0,   /* rotZ */  0
                )
        );

        // Step 3: Mirror same shape for right wing
        root.addChild(
                "right_wing",
                ModelPartBuilder.create()
                        .uv(0, 54)
                        .mirrored()                  // flip UVs for symmetry
                        .cuboid(
                                /* x */ -15.0F, /* y */ -1.0F, /* z */  0.0F,
                                /*  width */ 16, /* height */  1, /* depth */  8,
                                Dilation.NONE
                        ),
                ModelTransform.of(
                        /* pivotX */ -5.0F, /* pivotY */  5.0F, /* pivotZ */ -10.0F,
                        /* rotX */    0,    /* rotY */    0,    /* rotZ */    0
                )
        );

        // Step 4: Return the combined horse+wing model, using a 64×64 texture
        return TexturedModelData.of(data, /* texWidth */ 64, /* texHeight */ 64);
    }

    /**
     * Called every render tick to apply rotations to parts based on entity state.
     * Wings will flap using a simple cosine wave tied to the horse's age (tick count).
     */
    @Override
    public void setAngles(LivingHorseEntityRenderState state) {
        super.setAngles(state);  // Apply vanilla horse animations first

        // Calculate a flap angle: varies smoothly from -0.4 to +0.4 radians.
        float flap = MathHelper.cos(state.age * 0.3F) * 0.4F;

        // Pitch the wings up/down in opposite directions for a flapping effect
        this.leftWing.pitch  = flap;
        this.rightWing.pitch = -flap;
    }
}