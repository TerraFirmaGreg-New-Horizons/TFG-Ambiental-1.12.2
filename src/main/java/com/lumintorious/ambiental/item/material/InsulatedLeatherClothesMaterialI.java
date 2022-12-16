package com.lumintorious.ambiental.item.material;

import com.lumintorious.ambiental.item.ITemperatureAlteringMaterial;
import com.lumintorious.ambiental.modifier.TempModifier;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import static com.lumintorious.ambiental.Ambiental.MODID;

public class InsulatedLeatherClothesMaterialI implements ITemperatureAlteringMaterial {
    public static final ArmorMaterial INSULATED_LEATHER_CLOTHES = EnumHelper.addArmorMaterial("insulated_leather", MODID + ":insulated_leather", 2500, new int[]{0, 0, 0, 0}, 1, SoundEvents.BLOCK_WOOD_PLACE, 0F);

    @Override
    public TempModifier getTempModifier(ItemStack stack) {
        return new TempModifier(stack.getItem().getRegistryName().toString(), 2f, -0.15f);
    }
}
