package com.speeedcraft.tfcambiental.api;

import com.speeedcraft.tfcambiental.modifiers.BaseModifier;
import com.speeedcraft.tfcambiental.modifiers.ItemModifier;
import com.speeedcraft.tfcambiental.modifiers.ModifierStorage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

//Items you create should implement this if necessary
public interface IItemTemperatureOwner {
	public ItemModifier getModifier(ItemStack stack, EntityPlayer player);
}
