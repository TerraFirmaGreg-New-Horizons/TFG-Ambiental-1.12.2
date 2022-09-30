package com.speeedcraft.tfcambiental.modifiers;

import com.speeedcraft.tfcambiental.capability.TemperatureCapability;
import net.dries007.tfc.objects.te.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;

public class TileEntityModifier extends BlockModifier {
	
	public TileEntityModifier(String unlocalizedName) {
		super(unlocalizedName);
	}
	
	public TileEntityModifier(String unlocalizedName, float change, float potency) {
		super(unlocalizedName, change, potency);
	}
	
	public TileEntityModifier(String unlocalizedName, float change, float potency, boolean affectedByDistance) {
		super(unlocalizedName, change, potency, affectedByDistance);
	}

	public static final float mod = 0.5F;

	public static boolean hasLeatherArmorProtection(EntityPlayer player) {
		Item head = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem();
		Item chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem();
		Item legs = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem();
		Item feet = player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem();

		Item leatherHelmet = Items.LEATHER_HELMET;
		Item leatherChestplate = Items.LEATHER_CHESTPLATE;
		Item leatherLeggings = Items.LEATHER_LEGGINGS;
		Item leatherBoots = Items.LEATHER_BOOTS;

		// Leather Armor
		return head.equals(leatherHelmet) &&
				chest.equals(leatherChestplate) &&
				legs.equals(leatherLeggings) &&
				feet.equals(leatherBoots);
	}

	public static TileEntityModifier handleCharcoalForge(TileEntity tile, EntityPlayer player) {
		if (tile instanceof TECharcoalForge) {
			TECharcoalForge forge = (TECharcoalForge)tile;
			float temp = forge.getField(TECharcoalForge.FIELD_TEMPERATURE);
			float change =  temp / 100f; // 140
			float potency = temp / 350f;
			if (hasLeatherArmorProtection(player)) {
				change = mod;
			}
			return new TileEntityModifier("charcoal_forge", change, potency);
		}
		else {
			return null;
		}
	}
	
	public static TileEntityModifier handleFirePit(TileEntity tile, EntityPlayer player) {
		if(tile instanceof TEFirePit) {
			TEFirePit pit = (TEFirePit)tile;
			float temp = pit.getField(TEFirePit.FIELD_TEMPERATURE);
			float change =  temp / 100f;
			float potency = temp / 350f;
			if (hasLeatherArmorProtection(player)) {
				change = mod;
			}
			return new TileEntityModifier("fire_pit", Math.min(6f, change), potency);
		}
		else {
			return null;
		}
	}
	
	public static TileEntityModifier handleBloomery(TileEntity tile, EntityPlayer player) {
		if (tile instanceof TEBloomery) {
			TEBloomery bloomery = (TEBloomery)tile;
			float change = bloomery.getRemainingTicks() > 0 ? 4f : 0f;
			float potency = change;
			if(hasLeatherArmorProtection(player)) {
				change = mod;
			}
			return new TileEntityModifier("bloomery", change, potency);
		}
		else {
			return null;
		}
	}
	
	public static TileEntityModifier handleLamps(TileEntity tile, EntityPlayer player) {
		if (tile instanceof TELamp) {
			TELamp lamp = (TELamp)tile;
			if (EnvironmentalModifier.getEnvironmentTemperature(player) < TemperatureCapability.AVERAGE) {
				float change = (lamp.isPowered() && lamp.getFuel() > 0) ? 1f : 0f;
				float potency = 0f;
				return new TileEntityModifier("lamp", change, potency, false);
			}
		}
		return null;
	}

	public static TileEntityModifier handleCrucible(TileEntity tile, EntityPlayer player) {
		if (tile instanceof TECrucible) {
			TECrucible crucible = (TECrucible) tile;
			float temp = crucible.getField(TECrucible.FIELD_TEMPERATURE);
			float change =  temp / 100f;
			float potency = temp / 350f;
			if (hasLeatherArmorProtection(player)) {
				change = mod;
			}
			return new TileEntityModifier("crucible", change, potency);
		} else {
			return null;
		}
	}
}
