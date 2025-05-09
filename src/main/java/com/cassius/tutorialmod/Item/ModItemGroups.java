//package com.cassius.tutorialmod.Item;
//
//import com.cassius.tutorialmod.TutorialMod;
//import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
//import net.minecraft.item.ItemGroup;
//import net.minecraft.item.ItemStack;
//import net.minecraft.registry.Registries;
//import net.minecraft.registry.Registry;
//import net.minecraft.text.Text;
//import net.minecraft.util.Identifier;
//
///**
// * Define Custom Item Group for the Mod
// * Item groups define a creative inventory tab where your mod’s items show up in-game.
// */
//public class ModItemGroups {
//    public static final ItemGroup PINK_GARNET_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
//        Identifier.of(TutorialMod.MOD_ID, "mantis_items"),
//        FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.PINK_GARNET))
//            .displayName(Text.translatable("itemgroup.tutorialmod.mantis_items"))
//            .entries((displayContext, entries) -> {
//                entries.add(ModItems.PINK_GARNET);
//            }).build()
//    );
//
//
//    public static void registerItemGroups() {
//        TutorialMod.LOGGER.info("Registering Item Groups for " + TutorialMod.MOD_ID);
//    }
//}