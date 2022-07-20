package com.lumintorious.ambiental.api;

import com.lumintorious.ambiental.modifiers.TFCTileEntityModifier;

import net.minecraft.entity.player.EntityPlayer;

//Tile entities you create should implement this if necessary
public interface ITileEntityTemperatureOwner {
	public TFCTileEntityModifier getModifier(EntityPlayer player);
}
