package com.lumintorious.ambiental.item.material;

import com.lumintorious.ambiental.item.TemperatureAlteringMaterial;
import com.lumintorious.ambiental.modifier.TempModifier;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import static com.lumintorious.ambiental.Ambiental.MODID;

public class BurlapClothesMaterial implements TemperatureAlteringMaterial {
    public static final ArmorMaterial BURLAP_CLOTHES = EnumHelper.addArmorMaterial("burlap_cloth", MODID + ":burlap_cloth", 3000, new int[]{0, 0, 0, 0}, 1, SoundEvents.BLOCK_WOOD_PLACE, 0.0F);

    @Override
    public TempModifier getTempModifier(ItemStack stack) {
        return new TempModifier(stack.getItem().getRegistryName().toString(), -0.5f, -0.25f);
    }
}
