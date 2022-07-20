package com.lumintorious.ambiental.api;

import com.lumintorious.ambiental.modifiers.TFCTileEntityModifier;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

// Add an example of this into TemperatureRegistry for tile entities you didn't create personally
@FunctionalInterface
public interface ITileEntityTemperatureProvider extends ITemperatureProvider {
	TFCTileEntityModifier getModifier(TileEntity tile, EntityPlayer player);
}
