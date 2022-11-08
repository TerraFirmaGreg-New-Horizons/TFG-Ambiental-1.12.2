package com.lumintorious.tfcambiental.api;

import com.lumintorious.tfcambiental.modifiers.ItemModifier;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

//Add an example of this into TemperatureRegistry for items you didn't create personally
@FunctionalInterface
public interface IItemTemperatureProvider extends ITemperatureProvider{
	public ItemModifier getModifier(ItemStack stack, EntityPlayer player);
}
