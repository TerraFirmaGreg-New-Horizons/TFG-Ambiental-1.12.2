package com.lumintorious.ambiental.capability;

import com.lumintorious.ambiental.AmbientalDamage;
import com.lumintorious.ambiental.TFCAmbientalConfig;
import com.lumintorious.ambiental.api.*;
import com.lumintorious.ambiental.modifier.TempModifier;
import com.lumintorious.ambiental.modifier.EnvironmentalModifier;
import com.lumintorious.ambiental.modifier.TempModifierStorage;

import gregtech.common.items.MetaItems;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.capability.food.FoodStatsTFC;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class TemperatureCapability implements ICapabilitySerializable<NBTTagCompound> {

	public static final Capability<TemperatureCapability> CAPABILITY = null;

	private int tick = 0;
	private int damageTick = 0;
	private int durabilityTick = 0;
	private EntityPlayer player;
	public float target = AVERAGE;
	public float potency = 1f;
	public float bodyTemperature = AVERAGE;

	public static final int tickInterval = 20;

	public static final float BAD_MULTIPLIER = 0.001f;
	public static final float GOOD_MULTIPLIER = 0.002f;
	public static final float CHANGE_CAP = 7.5f;
	public static final float HIGH_CHANGE = 0.20f;


    public TemperatureCapability(EntityPlayer player)
    {
        this.player = player;
    }

	public boolean isRising;
	
	public static float AVERAGE = TFCAmbientalConfig.GENERAL.averageTemperature;
	public static float HOT_THRESHOLD = TFCAmbientalConfig.GENERAL.hotThreshold;
	public static float COOL_THRESHOLD = TFCAmbientalConfig.GENERAL.coolThreshold;
	public static float BURN_THRESHOLD = TFCAmbientalConfig.GENERAL.burnThreshold;
	public static float FREEZE_THRESHOLD = TFCAmbientalConfig.GENERAL.freezeThreshold;
	public static float NANO_QUARK_ARMOR_TEMP = TFCAmbientalConfig.GENERAL.nanoOrQuarkTemp;
	
	public TempModifierStorage modifiers = new TempModifierStorage();


	public void clearModifiers() {
		this.modifiers = new TempModifierStorage();
	}
	
	public void evaluateModifiers() {
		TempModifierStorage previousStorage = modifiers;
		this.clearModifiers();
		IItemTemperatureProvider.evaluateAll(player, modifiers);
		EnvironmentalModifier.evaluateAll(player, modifiers);
		IBlockTemperatureProvider.evaluateAll(player, modifiers);
		ITileEntityTemperatureProvider.evaluateAll(player, modifiers);
		IEquipmentTemperatureProvider.evaluateAll(player, modifiers);
		this.modifiers.keepOnlyNEach(3);

		target = modifiers.getTargetTemperature();
		potency = modifiers.getTotalPotency();
}

	public float getTargetTemperature() {
		return target;
	}

	public float getPotency() {
		return potency;
	}

	public static boolean hasNanoOrQuarkArmorProtection(EntityPlayer player) {
		Item head = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem();
		Item chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem();
		Item legs = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem();
		Item feet = player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem();

		Item nanoHelmet = MetaItems.NANO_HELMET.getStackForm().getItem();
		Item nanoChestplate = MetaItems.NANO_CHESTPLATE.getStackForm().getItem();
		Item nanoAdvancedChestplate = MetaItems.NANO_CHESTPLATE_ADVANCED.getStackForm().getItem();
		Item nanoLeggings = MetaItems.NANO_LEGGINGS.getStackForm().getItem();
		Item nanoBoots = MetaItems.NANO_BOOTS.getStackForm().getItem();

		Item quantumHelmet = MetaItems.QUANTUM_HELMET.getStackForm().getItem();
		Item quantumChestplate = MetaItems.QUANTUM_CHESTPLATE.getStackForm().getItem();
		Item quantumAdvancedChestplate = MetaItems.QUANTUM_CHESTPLATE_ADVANCED.getStackForm().getItem();
		Item quantumLeggings = MetaItems.QUANTUM_LEGGINGS.getStackForm().getItem();
		Item quantumBoots = MetaItems.QUANTUM_BOOTS.getStackForm().getItem();

		// Nano Armor
		if (
				head.equals(nanoHelmet) &&
						(chest.equals(nanoChestplate) || chest.equals(nanoAdvancedChestplate)) &&
						legs.equals(nanoLeggings) &&
						feet.equals(nanoBoots)
		) {
			return true;
		}

		// Quark Armor
		if (
				head.equals(quantumHelmet) &&
						(chest.equals(quantumChestplate) || chest.equals(quantumAdvancedChestplate)) &&
						legs.equals(quantumLeggings) &&
						feet.equals(quantumBoots)
		) {
			return true;
		}

		return false;
	}

	public float getTemperatureChange() {
		float target = getTargetTemperature();
		float speed = getPotency() * TFCAmbientalConfig.GENERAL.temperatureChangeSpeed;
		float change = Math.min(CHANGE_CAP, Math.max(-CHANGE_CAP, target - bodyTemperature));
		float newTemp = bodyTemperature + change;
		boolean isRising = true;
		if ((bodyTemperature < AVERAGE && newTemp > bodyTemperature) || (bodyTemperature > AVERAGE && newTemp < bodyTemperature)) {
			speed *= GOOD_MULTIPLIER * TFCAmbientalConfig.GENERAL.goodTemperatureChangeSpeed;
		}
		else {
			speed *= BAD_MULTIPLIER * TFCAmbientalConfig.GENERAL.badTemperatureChangeSpeed;
		}
		return (change * speed);
	}

	public void update() {
		if (!player.world.isRemote) {
			if (hasNanoOrQuarkArmorProtection(player)) {
				this.setTemperature(NANO_QUARK_ARMOR_TEMP);
			}
			else {
				this.setTemperature(this.getTemperature() + this.getTemperatureChange() / tickInterval);
			}

			if (tick <= tickInterval) {
				tick++;
				return;
			}
			else {
				tick = 0;
				if (TFCAmbientalConfig.GENERAL.takeDamage) {
					if (this.getTemperature() > BURN_THRESHOLD) {
						player.attackEntityFrom(AmbientalDamage.HEAT,  4f);
					} else if (this.getTemperature() < FREEZE_THRESHOLD) {
						player.attackEntityFrom(AmbientalDamage.COLD, 4f);
					}
				}
				if (TFCAmbientalConfig.GENERAL.loseHungerThirst) {
					if(player.getFoodStats() instanceof FoodStatsTFC) {
						FoodStatsTFC stats = (FoodStatsTFC)player.getFoodStats();
						if (this.getTemperature() > BURN_THRESHOLD) {
							stats.addThirst(-8);
						} else if (this.getTemperature() < FREEZE_THRESHOLD){
							stats.setFoodLevel(stats.getFoodLevel() - 1);
						}
					}
				}
			}

			this.evaluateModifiers();
			updateAndSync();
		}

	}
	
	public String toString() {
		String str = "";
		for(TempModifier modifier : modifiers) {
			str += modifier.getUnlocalizedName() + " -> " + modifier.getChange() + " @ " + modifier.getPotency() + "\n";
		}
		return String.format(
				"Body: %.1f ( %.4f )\n"
				+ "Target: %.1f \n"
				+ "Potency: %.4f",
				bodyTemperature,
				this.getTemperatureChange(),
				this.getTargetTemperature(),
				modifiers.getTotalPotency()
				) + "\n"+str;
	}


	public float getTemperature() {
		return bodyTemperature;
	}

	public void setTemperature(float newTemp) {
		if (newTemp < this.getTemperature()) {
			isRising = false;
		}
		else {
			isRising = true;
		}
		this.bodyTemperature = newTemp;
	}


	public EntityPlayer getPlayer() {
		return player;
	}


	public float getChange() {
		return getTemperatureChange();
	}
	
	public float getChangeSpeed() {
		return getPotency();
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setFloat("temperature", this.getTemperature());
		tag.setFloat("target", this.getTargetTemperature());
		tag.setFloat("potency", this.getPotency());
		return tag;
	}

	@Override
	public void deserializeNBT(NBTTagCompound tag) {
		if(tag.hasKey("temperature")) {
			this.setTemperature(tag.getFloat("temperature"));
			this.target = (tag.getFloat("target"));
			this.potency = (tag.getFloat("potency"));

		}else {
			this.setTemperature(23.4f);
		}
	}
	
	public void updateAndSync() {
		EntityPlayer player = getPlayer();
        if (player instanceof EntityPlayerMP)
        {
            TerraFirmaCraft.getNetwork().sendTo(new TemperaturePacket(serializeNBT()), (EntityPlayerMP) player);
        }
	}

	public void sync() {
		EntityPlayer player = getPlayer();
		if (player instanceof EntityPlayerMP)
		{
			TemperaturePacket packet = new TemperaturePacket(serializeNBT());
			TerraFirmaCraft.getNetwork().sendTo(packet, (EntityPlayerMP) player);
		}
	}
    
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability != null && capability == CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == CAPABILITY ? (T)(this) : null;
    }
}
