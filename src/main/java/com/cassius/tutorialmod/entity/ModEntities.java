/**
 * Register the Custom Entity
 */
package com.cassius.tutorialmod.entity;

import com.cassius.tutorialmod.TutorialMod;
import com.cassius.tutorialmod.entity.custom.MantisEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {

    // Define a unique registry key for the Mantis entity
    // RegistryKey<EntityType<?>> -
    public static final RegistryKey<EntityType<?>> MANTIS_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(TutorialMod.MOD_ID, "mantis"));

    // Create a static instance of the Mantis entity type with its spawn group and size
    public static final EntityType<MantisEntity> MANTIS = Registry.register(
            Registries.ENTITY_TYPE, //Registry
            MANTIS_KEY, //Registry Key
            EntityType.Builder.create(MantisEntity::new, SpawnGroup.CREATURE).dimensions(1f, 2.5f).build(MANTIS_KEY) //entry
    );

    // Called to log or trigger mod entity registration
    public static void registerModEntities() {
        TutorialMod.LOGGER.info("Registering Mod Entities for " + TutorialMod.MOD_ID);
    }

}
