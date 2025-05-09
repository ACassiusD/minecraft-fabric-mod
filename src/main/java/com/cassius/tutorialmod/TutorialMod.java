package com.cassius.tutorialmod;

import com.cassius.tutorialmod.Item.ModItems;
import com.cassius.tutorialmod.entity.ModEntities;
import com.cassius.tutorialmod.entity.custom.MantisEntity;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	// Main entry point for mod logic
	// This code runs as soon as Minecraft is in a mod-load-ready state.
	// However, some things (like resources) may still be uninitialized.
	@Override
	public void onInitialize() {

		ModItems.registerModItems();

		//ModItemGroups.registerItemGroups();

		ModEntities.registerModEntities();



		LOGGER.info("Hello Fabric world!");

		//Register the default attributes for the Mantis Entity -- Tells minecraft what stats our mob has
		FabricDefaultAttributeRegistry.register(ModEntities.MANTIS, MantisEntity.createAttributes());
	}
}