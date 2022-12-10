package com.lumintorious.ambiental.item;

import com.lumintorious.ambiental.modifier.TempModifier;
import net.minecraft.item.ItemStack;

public interface TemperatureAlteringMaterial {
    TempModifier getTempModifier(ItemStack stack);
}
