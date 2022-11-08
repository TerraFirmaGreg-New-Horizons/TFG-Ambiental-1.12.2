package com.lumintorious.tfcambiental.api;

import com.lumintorious.tfcambiental.modifiers.TileEntityModifier;

import net.minecraft.entity.player.EntityPlayer;

//Tile entities you create should implement this if necessary
public interface ITileEntityTemperatureOwner {
	public TileEntityModifier getModifier(EntityPlayer player);
}
