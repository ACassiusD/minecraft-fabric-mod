package com.cassius.tutorialmod.Item;

import com.cassius.tutorialmod.TutorialMod;
import com.cassius.tutorialmod.entity.ModEntities;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

/**
 * Centralized area to define and register all custom items for the mod
 */
public class ModItems {

    //Registering items using Register Item helper method
    public static final Item PINK_GARNET = registerItem(
        "pink_garnet",
        new Item(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID, "pink_garnet"))))
    );

    //Register a new item, with new settings. setting the registry key
    public static final Item RAW_PINK_GARNET = registerItem(
            "raw_pink_garnet",
            new Item(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID, "raw_pink_garnet"))))
    );

    public static final Item MANTIS_SPAWN_EGG = registerItem(
            "mantis_spawn_egg",
            new SpawnEggItem(ModEntities.MANTIS, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID, "mantis_spawn_egg"))))
    );


    //Register Item Helper Method
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name), item);
    }

    //Method used to call class in main method to initialize all the items
    public static void registerModItems() {
        TutorialMod.LOGGER.info("Registering Mod Items for " + TutorialMod.MOD_ID);

        //Add the Pink Garnet to the Ingredients Creative mode Tab
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(PINK_GARNET);
            entries.add(RAW_PINK_GARNET);
            entries.add(MANTIS_SPAWN_EGG);
        });
    }
}