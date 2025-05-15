/**
 * Register client-side logic
 * Client Side in minecraft modding means everything the player sees/hears/interacts with visually, i.e. Entity models & animations
 * Block and item rendering, HUD overlays (like health bars), Screens (inventory, custom GUIs) etc.
 */
package com.cassius.tutorialmod;

import com.cassius.tutorialmod.entity.ModEntitiesRegistry;
import com.cassius.tutorialmod.entity.client.MantisEntityModel;
import com.cassius.tutorialmod.entity.client.MantisEntityRenderer;
import com.cassius.tutorialmod.entity.client.PegasusEntityModel;
import com.cassius.tutorialmod.entity.client.PegasusEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class TutorialModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        //Registers the main layer 3D model for our Mantis Entity (default layer - meaning the base model)
        EntityModelLayerRegistry.registerModelLayer(MantisEntityModel.MANTIS, MantisEntityModel::getTexturedModelData);
        //Registers the renderer used to visualize mantis entity
        EntityRendererRegistry.register(ModEntitiesRegistry.MANTIS, MantisEntityRenderer::new);

        //Register custom model layer for Pegasus
        EntityModelLayerRegistry.registerModelLayer(
                PegasusEntityModel.PEGASUS_LAYER, // id
                PegasusEntityModel::getTexturedModelData // how to build its geometry/UVs
        );

        //Registers the main layer of the 3D model for Pegasus Entity (default layer - meaning the base model)
//        EntityModelLayerRegistry.registerModelLayer(PegasusEntityModel.PE, PegasusEntityModel::getTexturedModelData);

        //Registers the renderer to use for pegasus entity
        EntityRendererRegistry.register(ModEntitiesRegistry.PEGASUS, PegasusEntityRenderer::new);

    }
}
