package com.cassius.tutorialmod;

import com.cassius.tutorialmod.Item.ModItems;
import com.cassius.tutorialmod.entity.ModEntitiesRegistry;
import com.cassius.tutorialmod.entity.client.PegasusEntityRenderer;
import com.cassius.tutorialmod.entity.custom.MantisEntity;
import com.cassius.tutorialmod.entity.custom.PegasusEntity;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.data.tag.vanilla.VanillaEntityTypeTagProvider;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.network.packet.PlayPackets;
import net.minecraft.network.packet.c2s.play.PlayerInputC2SPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Map<UUID, Boolean> JUMPING_MAP = new ConcurrentHashMap<>();


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
		FabricDefaultAttributeRegistry.register(ModEntitiesRegistry.PEGASUS, AbstractHorseEntity.createBaseHorseAttributes());
	}
}