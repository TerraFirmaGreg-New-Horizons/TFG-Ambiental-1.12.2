package com.lumintorious.tfcambiental.api;

import com.lumintorious.tfcambiental.modifiers.EnvironmentalModifier;

import net.minecraft.entity.player.EntityPlayer;

//Add an example of this into TemperatureRegistry for general modifiers
@FunctionalInterface
public interface IEnvironmentalTemperatureProvider extends ITemperatureProvider{
	public EnvironmentalModifier getModifier(EntityPlayer player);
}
