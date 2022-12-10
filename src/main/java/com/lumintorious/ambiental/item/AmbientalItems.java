package com.lumintorious.ambiental.item;

import com.lumintorious.ambiental.item.material.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class AmbientalItems {

    //  Armor
    public static ClothesItem LEATHER_APRON = new ClothesItem(LeatherApronMaterial.LEATHER_APRON, 2, EntityEquipmentSlot.LEGS, "leather_apron");
    public static ClothesItem STRAW_HAT = new ClothesItem(StrawClothesMaterial.STRAW_CLOTHES, 1, EntityEquipmentSlot.HEAD, "straw_hat");

    public static ClothesItem WOOL_HAT = new ClothesItem(WoolClothesMaterial.WOOL_CLOTHES, 1, EntityEquipmentSlot.HEAD, "wool_hat");
    public static ClothesItem WOOL_SWEATER = new ClothesItem(WoolClothesMaterial.WOOL_CLOTHES, 1, EntityEquipmentSlot.CHEST, "wool_sweater");
    public static ClothesItem WOOL_PANTS = new ClothesItem(WoolClothesMaterial.WOOL_CLOTHES, 2, EntityEquipmentSlot.LEGS, "wool_pants");
    public static ClothesItem WOOL_BOOTS = new ClothesItem(WoolClothesMaterial.WOOL_CLOTHES, 1, EntityEquipmentSlot.FEET, "wool_boots");

    public static ClothesItem SILK_COWL = new ClothesItem(SilkClothesMaterial.SILK_CLOTHES, 1, EntityEquipmentSlot.HEAD, "silk_cowl");
    public static ClothesItem SILK_SHIRT = new ClothesItem(SilkClothesMaterial.SILK_CLOTHES, 1, EntityEquipmentSlot.CHEST, "silk_shirt");
    public static ClothesItem SILK_PANTS = new ClothesItem(SilkClothesMaterial.SILK_CLOTHES, 2, EntityEquipmentSlot.LEGS, "silk_pants");
    public static ClothesItem SILK_SHOES = new ClothesItem(SilkClothesMaterial.SILK_CLOTHES, 1, EntityEquipmentSlot.FEET, "silk_shoes");

    public static ClothesItem BURLAP_COWL = new ClothesItem(BurlapClothesMaterial.BURLAP_CLOTHES, 1, EntityEquipmentSlot.HEAD, "burlap_cowl");
    public static ClothesItem BURLAP_SHIRT = new ClothesItem(BurlapClothesMaterial.BURLAP_CLOTHES, 1, EntityEquipmentSlot.CHEST, "burlap_shirt");
    public static ClothesItem BURLAP_PANTS = new ClothesItem(BurlapClothesMaterial.BURLAP_CLOTHES, 2, EntityEquipmentSlot.LEGS, "burlap_pants");
    public static ClothesItem BURLAP_SHOES = new ClothesItem(BurlapClothesMaterial.BURLAP_CLOTHES, 1, EntityEquipmentSlot.FEET, "burlap_shoes");

    public static ClothesItem LEATHER_HAT = new ClothesItem(InsulatedLeatherClothesMaterial.INSULATED_LEATHER_CLOTHES, 1, EntityEquipmentSlot.HEAD, "insulated_leather_hat");
    public static ClothesItem LEATHER_TUNIC = new ClothesItem(InsulatedLeatherClothesMaterial.INSULATED_LEATHER_CLOTHES, 1, EntityEquipmentSlot.CHEST, "insulated_leather_tunic");
    public static ClothesItem LEATHER_PANTS = new ClothesItem(InsulatedLeatherClothesMaterial.INSULATED_LEATHER_CLOTHES, 2, EntityEquipmentSlot.LEGS, "insulated_leather_pants");
    public static ClothesItem LEATHER_BOOTS = new ClothesItem(InsulatedLeatherClothesMaterial.INSULATED_LEATHER_CLOTHES, 1, EntityEquipmentSlot.FEET, "insulated_leather_boots");

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                LEATHER_APRON, STRAW_HAT,
                WOOL_HAT, WOOL_SWEATER, WOOL_PANTS, WOOL_BOOTS,
                SILK_COWL, SILK_SHIRT, SILK_PANTS, SILK_SHOES,
                BURLAP_COWL, BURLAP_SHIRT, BURLAP_PANTS, BURLAP_SHOES,
                LEATHER_HAT, LEATHER_TUNIC, LEATHER_PANTS, LEATHER_BOOTS
        );
    }

    public static void registerModels() {
        LEATHER_APRON.registerItemModel();
        STRAW_HAT.registerItemModel();

        WOOL_HAT.registerItemModel();
        WOOL_SWEATER.registerItemModel();
        WOOL_PANTS.registerItemModel();
        WOOL_BOOTS.registerItemModel();

        SILK_COWL.registerItemModel();
        SILK_SHIRT.registerItemModel();
        SILK_PANTS.registerItemModel();
        SILK_SHOES.registerItemModel();

        BURLAP_COWL.registerItemModel();
        BURLAP_SHIRT.registerItemModel();
        BURLAP_PANTS.registerItemModel();
        BURLAP_SHOES.registerItemModel();

        LEATHER_HAT.registerItemModel();
        LEATHER_TUNIC.registerItemModel();
        LEATHER_PANTS.registerItemModel();
        LEATHER_BOOTS.registerItemModel();
    }
}
