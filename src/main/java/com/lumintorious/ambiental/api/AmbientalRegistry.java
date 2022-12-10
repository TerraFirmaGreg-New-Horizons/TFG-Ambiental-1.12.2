package com.lumintorious.ambiental.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;

import com.lumintorious.ambiental.compat.TFC;
import com.lumintorious.ambiental.modifier.*;

import com.lumintorious.ambiental.compat.Cellars;
import com.lumintorious.ambiental.compat.FirmaLife;
import com.lumintorious.ambiental.compat.TFCTech;
import net.minecraft.init.Blocks;
import net.minecraft.world.EnumSkyBlock;


public class AmbientalRegistry<Type> implements Iterable<Type> {
	public static final AmbientalRegistry<IItemTemperatureProvider> ITEMS = new AmbientalRegistry<>();
	public static final AmbientalRegistry<IBlockTemperatureProvider> BLOCKS = new AmbientalRegistry<>();
	public static final AmbientalRegistry<ITileEntityTemperatureProvider> TILE_ENTITIES = new AmbientalRegistry<>();
	public static final AmbientalRegistry<IEnvironmentalTemperatureProvider> ENVIRONMENT = new AmbientalRegistry<>();

	public static final AmbientalRegistry<IEquipmentTemperatureProvider> EQUIPMENT = new AmbientalRegistry<>();


	static {
		BLOCKS.register((player, pos, state) -> Optional.of(new TempModifier("torch", 3f, 0f)).filter((mod) -> state.getBlock() == Blocks.TORCH));
		BLOCKS.register((player, pos, state) -> Optional.of(new TempModifier("fire", 3f, 0f)).filter((mod) -> state.getBlock() == Blocks.FIRE));
		BLOCKS.register((player, pos, state) -> Optional.of(new TempModifier("lava", 6f, 0f)).filter((mod) -> state.getBlock() == Blocks.LAVA));
		BLOCKS.register((player, pos, state) -> Optional.of(new TempModifier("flowing_lava", 4f, 0f)).filter((mod) -> state.getBlock() == Blocks.FLOWING_LAVA));
		BLOCKS.register((player, pos, state) -> Optional.of(new TempModifier("snow_layer", -1.5f, 0.2f)).filter((mod) -> state.getBlock() == Blocks.SNOW_LAYER));
		BLOCKS.register((player, pos, state) -> Optional.of(new TempModifier("snow", -0.5f, 0.2f)).filter((mod) -> state.getBlock() == Blocks.SNOW && player.world.getLightFor(EnumSkyBlock.SKY, pos) == 15));

		// TFC-Tech
		TILE_ENTITIES.register(TFCTech::handleSmelteryFirebox); // Топило для печи для стекла
		TILE_ENTITIES.register(TFCTech::handleSmelteryCauldron); // Печь для стекла
		TILE_ENTITIES.register(TFCTech::handleElectricForge); // Тигель
		TILE_ENTITIES.register(TFCTech::handleInductionCrucible); // Кузня
		TILE_ENTITIES.register(TFCTech::handleFridge); // Холодос

		// Cellar
		TILE_ENTITIES.register(Cellars::handleCellar); // Подвал

		// Firmalife
		TILE_ENTITIES.register(FirmaLife::handleClayOven); // Oven

		// TFC
		TILE_ENTITIES.register(TFC::handleCharcoalForge); // Угольная кузня
		TILE_ENTITIES.register(TFC::handleFirePit); // Костер
		TILE_ENTITIES.register(TFC::handleBloomery); // Доменка
		TILE_ENTITIES.register(TFC::handleLamps); // Лампа
		TILE_ENTITIES.register(TFC::handleCrucible); // Тигель

		ENVIRONMENT.register(EnvironmentalModifier::handleGeneralTemperature);
		ENVIRONMENT.register(EnvironmentalModifier::handleTimeOfDay);
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
	private AmbientalRegistry() {}

	public void register(Type type) {
		list.add(type);
	}
	
	public boolean has(Type type) {
		return map.containsValue(type) || list.contains(type);
	}

	@Override
	public Iterator<Type> iterator() {
		return list.iterator();
	}
	
	
}
