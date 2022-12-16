package com.lumintorious.ambiental.item.material;

import com.lumintorious.ambiental.item.ITemperatureAlteringMaterial;
import com.lumintorious.ambiental.modifier.TempModifier;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import static com.lumintorious.ambiental.Ambiental.MODID;

public class WoolClothesMaterialI implements ITemperatureAlteringMaterial {
    public static final ArmorMaterial WOOL_CLOTHES = EnumHelper.addArmorMaterial(":wool_cloth", MODID +":wool_cloth", 3000, new int[]{0, 0, 0, 0}, 0, SoundEvents.BLOCK_WOOD_PLACE, 0F);

    @Override
    public TempModifier getTempModifier(ItemStack stack) {
        return new TempModifier(stack.getItem().getRegistryName().toString(), 4f, -0.15f);
    }
}
