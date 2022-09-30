package com.speeedcraft.tfcambiental.api;

import com.speeedcraft.tfcambiental.modifiers.BlockModifier;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

// Blocks you create should implement this if necessary
public interface IBlockTemperatureOwner {
	public BlockModifier getModifier(IBlockState state, BlockPos blockPos, EntityPlayer player);
}
