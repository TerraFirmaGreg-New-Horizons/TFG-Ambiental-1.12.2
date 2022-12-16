package com.lumintorious.ambiental.item.material;

import com.lumintorious.ambiental.item.ITemperatureAlteringMaterial;
import com.lumintorious.ambiental.modifier.TempModifier;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import static com.lumintorious.ambiental.Ambiental.MODID;

public class StrawClothesMaterialI implements ITemperatureAlteringMaterial {
    public static final ArmorMaterial STRAW_CLOTHES = EnumHelper.addArmorMaterial("straw", MODID +":straw", 100, new int[]{0, 0, 0, 0}, 1, SoundEvents.BLOCK_GRASS_BREAK, 0F);

    @Override
    public TempModifier getTempModifier(ItemStack stack) {
        return new TempModifier(stack.getItem().getRegistryName().toString(), 0, -0.1f);
    }
}
