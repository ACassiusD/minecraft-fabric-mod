package com.cassius.tutorialmod;

import com.cassius.tutorialmod.Item.ModItems;
import com.cassius.tutorialmod.entity.ModEntitiesRegistry;
import com.cassius.tutorialmod.entity.client.PegasusEntityRenderer;
import com.cassius.tutorialmod.entity.custom.MantisEntity;
import com.cassius.tutorialmod.entity.custom.PegasusEntity;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.passive.AbstractHorseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Mod Entry Point
	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		//Register Custom Mobs and Items
		ModItems.registerModItems();
		ModEntitiesRegistry.registerModEntities();
		//ModItemGroups.registerItemGroups();

		//Register default attributes entities
		FabricDefaultAttributeRegistry.register(ModEntitiesRegistry.MANTIS, MantisEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntitiesRegistry.PEGASUS, AbstractHorseEntity.createBaseHorseAttributes().build());
	}
}