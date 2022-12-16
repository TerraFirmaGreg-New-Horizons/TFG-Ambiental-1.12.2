package com.lumintorious.ambiental.item.material;

import com.lumintorious.ambiental.item.ITemperatureAlteringMaterial;
import com.lumintorious.ambiental.modifier.TempModifier;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import static com.lumintorious.ambiental.Ambiental.MODID;

public class SilkClothesMaterialI implements ITemperatureAlteringMaterial {
    public static final ArmorMaterial SILK_CLOTHES = EnumHelper.addArmorMaterial("silk_cloth", MODID +":silk_cloth", 3000, new int[]{0, 0, 0, 0}, 1, SoundEvents.BLOCK_WOOD_PLACE, 0F);

    @Override
    public TempModifier getTempModifier(ItemStack stack) {
        return new TempModifier(stack.getItem().getRegistryName().toString(), -1f, -0.1f);
    }
}
