package com.cassius.tutorialmod.entity.client;

import com.cassius.tutorialmod.TutorialMod;
import com.cassius.tutorialmod.entity.custom.MantisEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.AgeableMobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

/**
 * Client-side renderer for the MantisEntity.
 * Responsible for rendering both adult and baby versions of the entity,
 * handling animation states, and providing the entity's texture.
 */
@Environment(EnvType.CLIENT)
public class MantisEntityRenderer extends AgeableMobEntityRenderer<MantisEntity, MantisEntityRenderState, MantisEntityModel> {

	// Texture used for rendering the entity. Replace with your own texture path when ready.
	private static final Identifier TEXTURE = Identifier.of(TutorialMod.MOD_ID, "textures/entity/mantis/mantis.png");

	/**
	 * Constructor
	 * Constructs the renderer for the MantisEntity.
	 *
	 * @param ctx the rendering context provided by Minecraft
	 */
	public MantisEntityRenderer(EntityRendererFactory.Context ctx) {
		super(
				ctx,
				new MantisEntityModel(ctx.getPart(MantisEntityModel.MANTIS)),
				new MantisEntityModel(ctx.getPart(MantisEntityModel.MANTIS)),
				0.7F
		);
	}

	/**
	 * Creates a new render state for the MantisEntity.
	 * Used to hold animation and pose-related state for the entity during rendering.
	 *
	 * @return a new MantisEntityRenderState instance
	 */
	public MantisEntityRenderState createRenderState() {
		return new MantisEntityRenderState();
	}

	/**
	 * Updates the render state with animation information from the entity.
	 *
	 * @param mantisEntity the entity being rendered
	 * @param mantisEntityRenderState the render state to update
	 * @param f the partial tick (used for interpolation)
	 */
	public void updateRenderState(MantisEntity mantisEntity, MantisEntityRenderState mantisEntityRenderState, float f) {
		super.updateRenderState(mantisEntity, mantisEntityRenderState, f);
		mantisEntityRenderState.idlingAnimationState.copyFrom(mantisEntity.idleAnimationState);
	}

	/**
	 * Returns the texture to use when rendering the entity.
	 *
	 * @param state the current render state
	 * @return the identifier for the texture
	 */
	@Override
	public Identifier getTexture(MantisEntityRenderState state) {
		return TEXTURE;
	}
}
