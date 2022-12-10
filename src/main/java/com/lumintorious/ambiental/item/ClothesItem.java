package com.lumintorious.ambiental.item;

import com.lumintorious.ambiental.Ambiental;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import static com.lumintorious.ambiental.Ambiental.MODID;
import static net.dries007.tfc.objects.CreativeTabsTFC.CT_MISC;


public class ClothesItem extends ItemArmor  {

    protected String name;
    private final ArmorMaterial material;
    private final EntityEquipmentSlot slot;

    public ClothesItem(ArmorMaterial material, int renderIndexIn, EntityEquipmentSlot slot, String name) {
        super(material, renderIndexIn, slot);
        this.setRegistryName(name);
        this.setTranslationKey(MODID + "." + name);
        this.name = name;
        this.slot = slot;
        this.material = material;
        setCreativeTab(CT_MISC);
    }

    public void registerItemModel() {
        Ambiental.proxy.registerItemRenderer(this, 0, name);
    }

    public ArmorMaterial getMaterial() {
        return material;
    }

    public EntityEquipmentSlot getEquivalentSlot() {
        return this.slot;
    }

    @Override
    public int getDamage(ItemStack stack) {
        return super.getDamage(stack);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return material.getDurability(getEquivalentSlot());
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }
}
