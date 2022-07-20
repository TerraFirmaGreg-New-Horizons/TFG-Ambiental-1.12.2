package com.lumintorious.ambiental.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.lumintorious.ambiental.modifiers.*;

import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.VariantBlock;
import net.minecraft.init.Blocks;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.fml.common.Loader;

public class TemperatureRegistry<Type extends ITemperatureProvider> implements Iterable<Type>{
	// Add functions that provide modifiers, you can use checks and return null to skip
	// Use the "ModifierOwner" interfaces for self implemented objects
	// Use the these only for objects out of your control
	public static final TemperatureRegistry<IItemTemperatureProvider> ITEMS = new TemperatureRegistry<>();
	public static final TemperatureRegistry<IBlockTemperatureProvider> BLOCKS = new TemperatureRegistry<>();
	public static final TemperatureRegistry<ITileEntityTemperatureProvider> TILE_ENTITIES = new TemperatureRegistry<>();
	public static final TemperatureRegistry<IEnvironmentalTemperatureProvider> ENVIRONMENT = new TemperatureRegistry<>();

	static {

		// TFC-Tech
		if (Loader.isModLoaded("tfctech")) {
			TILE_ENTITIES.register(TFCTechTileEntityModifier::handleSmelteryFirebox); // Топило для печи для стекла
			TILE_ENTITIES.register(TFCTechTileEntityModifier::handleSmelteryCauldron); // Печь для стекла
			TILE_ENTITIES.register(TFCTechTileEntityModifier::handleElectricForge); // Тигель
			TILE_ENTITIES.register(TFCTechTileEntityModifier::handleInductionCrucible); // Кузня
			TILE_ENTITIES.register(TFCTechTileEntityModifier::handleFridge); // Холодос
		}

		// FirmaLife
		if (Loader.isModLoaded("firmalife")) {
			TILE_ENTITIES.register(FirmaLifeTileEntityModifier::handleClayOven); // Oven
		}

		// GTCEu
		if (Loader.isModLoaded("gregtech")) {
			// Катушки
		}

		// TFC
		TILE_ENTITIES.register(TFCTileEntityModifier::handleCharcoalForge); // Угольная кузня
		TILE_ENTITIES.register(TFCTileEntityModifier::handleFirePit); // Костер
		TILE_ENTITIES.register(TFCTileEntityModifier::handleBloomery); // Доменка
		TILE_ENTITIES.register(TFCTileEntityModifier::handleLamps); // Лампа
		TILE_ENTITIES.register(TFCTileEntityModifier::handleCrucible); // Тигель
		BLOCKS.register((state, pos, player) -> state.getBlock() == Blocks.TORCH ? new BlockModifier("torch", 3f, 3f) : null);
		BLOCKS.register((state, pos, player) -> state.getBlock() == Blocks.FIRE ? new BlockModifier("fire", 3f, 3f) : null);
		BLOCKS.register((state, pos, player) -> state.getBlock() == Blocks.LAVA ? new BlockModifier("lava", 3f, 3f) : null);
		BLOCKS.register((state, pos, player) -> state.getBlock() == Blocks.FLOWING_LAVA ? new BlockModifier("lava", 3f, 3f) : null);
		BLOCKS.register((state, pos, player) -> (state.getBlock() == Blocks.SNOW_LAYER && player.world.getLightFor(EnumSkyBlock.SKY, pos) == 15) ? new BlockModifier("snow", -1.5f, 0.2f, false) : null);
		
		ENVIRONMENT.register(EnvironmentalModifier::handleGeneralTemperature);
		ENVIRONMENT.register(EnvironmentalModifier::handleShade);
		ENVIRONMENT.register(EnvironmentalModifier::handleCozy);
		ENVIRONMENT.register(EnvironmentalModifier::handleThirst);
		ENVIRONMENT.register(EnvironmentalModifier::handleFood);
		ENVIRONMENT.register(EnvironmentalModifier::handleDiet);
		ENVIRONMENT.register(EnvironmentalModifier::handleFire);
		ENVIRONMENT.register(EnvironmentalModifier::handleWater);
		ENVIRONMENT.register(EnvironmentalModifier::handleRain);
		ENVIRONMENT.register(EnvironmentalModifier::handleSprinting);
		ENVIRONMENT.register(EnvironmentalModifier::handleUnderground);
		ENVIRONMENT.register(EnvironmentalModifier::handlePotionEffects);
	}
	
	private final ArrayList<Type> list = new ArrayList<>();
	private final HashMap<String, Type> map = new HashMap<>();
	private TemperatureRegistry() {}

	public void register(Type type) {
		list.add(type);
	}
	
	public boolean has(Type type) {
		return map.containsValue(type) || list.contains(type);
	}

	@Override
	public Iterator<Type> iterator() {
		return new Iterator<Type>(){
			private Iterator listIterator = list.iterator();
			@Override
			public boolean hasNext() {
				return listIterator.hasNext();
			}

			@Override
			public Type next() {
				return (Type) listIterator.next();
			}
			
		};
	}
	
	
}
