/**
 * Register the Custom Entity
 */
package com.cassius.tutorialmod.entity;

import com.cassius.tutorialmod.TutorialMod;
import com.cassius.tutorialmod.entity.custom.MantisEntity;
import com.cassius.tutorialmod.entity.custom.PegasusEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntitiesRegistry {

    // DECLARE REGISTRY KEYS FOR CUSTOM ENTITIES
    public static final RegistryKey<EntityType<?>> MANTIS_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(TutorialMod.MOD_ID, "mantis"));
    public static final RegistryKey<EntityType<?>> PEGASUS_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(TutorialMod.MOD_ID, "pegasus"));


    // CREATE AND REGISTER ENTITIES
    public static final EntityType<MantisEntity> MANTIS = Registry.register(
            Registries.ENTITY_TYPE, //Registry
            MANTIS_KEY, //Registry Key
            EntityType.Builder.create(MantisEntity::new, SpawnGroup.CREATURE).dimensions(1f, 2.5f).build(MANTIS_KEY) //entry
    );
    public static final EntityType<PegasusEntity> PEGASUS = Registry.register(
            Registries.ENTITY_TYPE, //Registry we are writing to
            PEGASUS_KEY, //ID
            EntityType.Builder.create(PegasusEntity::new, SpawnGroup.CREATURE) //Entry
                    .dimensions(1.3964844F, 1.6F)
                    .eyeHeight(1.52F)
                    .passengerAttachments(1.44375F)
                    .maxTrackingRange(10).build(PEGASUS_KEY)
    );

    // TRIGGER REGISTRATION
    public static void registerModEntities() {
        TutorialMod.LOGGER.info("Registering Mod Entities for " + TutorialMod.MOD_ID);
    }

}
