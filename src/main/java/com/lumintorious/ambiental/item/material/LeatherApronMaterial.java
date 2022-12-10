package com.lumintorious.ambiental.item.material;

import com.lumintorious.ambiental.item.TemperatureAlteringMaterial;
import com.lumintorious.ambiental.modifier.TempModifier;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import static com.lumintorious.ambiental.Ambiental.MODID;

public class LeatherApronMaterial implements TemperatureAlteringMaterial {
    public static final ArmorMaterial LEATHER_APRON = EnumHelper.addArmorMaterial("leather_apron", MODID +":leather_apron", 1000, new int[]{0, 0, 0, 0}, 0, SoundEvents.ENTITY_LEASHKNOT_BREAK, 0F);

    @Override
    public TempModifier getTempModifier(ItemStack stack) {
        return new TempModifier(stack.getItem().getRegistryName().toString(), 0, -0.35f);
    }
}
