package com.lumintorious.ambiental;

import net.minecraft.util.DamageSource;

public abstract class TFCAmbientalDamage {
	public static final DamageSource HEAT = new DamageSource("hyperthermia").setDamageBypassesArmor().setDamageIsAbsolute();
	public static final DamageSource COLD = new DamageSource("hypothermia").setDamageBypassesArmor().setDamageIsAbsolute();
}
