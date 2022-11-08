package com.lumintorious.tfcambiental.api;

import com.lumintorious.tfcambiental.modifiers.TileEntityModifier;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

// Add an example of this into TemperatureRegistry for tile entities you didn't create personally
@FunctionalInterface
public interface ITileEntityTemperatureProvider extends ITemperatureProvider {
	TileEntityModifier getModifier(TileEntity tile, EntityPlayer player);
}
