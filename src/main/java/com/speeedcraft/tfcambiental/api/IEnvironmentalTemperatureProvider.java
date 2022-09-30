package com.speeedcraft.tfcambiental.api;

import com.speeedcraft.tfcambiental.modifiers.EnvironmentalModifier;
import com.speeedcraft.tfcambiental.modifiers.ModifierStorage;

import net.minecraft.entity.player.EntityPlayer;

//Add an example of this into TemperatureRegistry for general modifiers
@FunctionalInterface
public interface IEnvironmentalTemperatureProvider extends ITemperatureProvider{
	public EnvironmentalModifier getModifier(EntityPlayer player);
}
