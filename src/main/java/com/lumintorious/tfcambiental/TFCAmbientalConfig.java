package com.lumintorious.tfcambiental;

import net.minecraftforge.common.config.Config;

import static com.lumintorious.tfcambiental.TFCAmbiental.MODID;

@Config(modid = MODID)
public class TFCAmbientalConfig {

	@Config.Comment("Client Settings")
	public static final ClientCFG CLIENT = new ClientCFG();

	@Config.Comment("General Settings")
	public static final GeneralCFG GENERAL = new GeneralCFG();

	public static class ClientCFG {

		@Config.Comment("If true, you will get extra details about your temperature when sneaking, when false they are always visible.")
		public boolean sneakyDetails = false;

		@Config.Comment("Overlay when you burning or freezing.")
		public boolean enableOverlay = true;
	}

	public static class GeneralCFG {

		@Config.Comment("Allowed Dims")
		public int[] allowedDims = new int[] {0};

		@Config.Comment("If true, you will start taking damage when below freezing or above burning temperatures. Default = true")
		public boolean takeDamage = true;

		@Config.Comment("If true, you will start losing hunger when below cold temperatures and losing thirst when above hot temperatures. Default = true")
		public boolean loseHungerThirst = true;

		@Config.Comment("If true, temperate areas won't be as mild. Default = true")
		public boolean harsherTemperateAreas = true;

		@Config.Comment("How fast does temperature change when it's going towards the average. Default = 5")
		public float positiveModifier = 5f;

		@Config.Comment("How fast does temperature change when it's going away from the average. If you think you are giving yourself a challenge by increasing this number, think twice. It makes it so that you have to warm yourself up every so often. Default = 1")
		public float negativeModifier = 1f;

		@Config.Comment("How quickly temperature rises and decreases. Default = 1.0")
		public float temperatureMultiplier = 1.0f;

		@Config.Comment("How potent are multipliers with more than one instance. (Eg. 2 fire pits nearby means they have 2 * this effectiveness). Default = 0.7")
		public float diminishedModifierMultiplier = 0.7f;

		@Config.Comment("If harsherTemperateAreas is true, environmental temperatures going away from the average are multiplied by this number. (The less temperate an area is, the less the modifier affects it). Default = 1.2 ")
		public float harsherMultiplier = 1.20f;

		@Config.Comment("The temperature at which you are at equilibrium. It's advisable to not change this by a lot since the entire ecosystem revolves around this. Default = 15")
		public float averageTemperature = 17f;

		@Config.Comment("The temperature at which your screen starts heating. It's advisable to not change this by a lot since the entire ecosystem revolves around this. Default = 20")
		public float hotTemperature = 27f;

		@Config.Comment("The temperature at which your screen starts freezing. It's advisable to not change this by a lot since the entire ecosystem revolves around this. Default = 10")
		public float coldTemperature = 10f;

		@Config.Comment("The temperature at which you start burning and taking damage. It's advisable to not change this by a lot since the entire ecosystem revolves around this. Default = 25")
		public float burningTemperature = 37f;

		@Config.Comment("The temperature at which you start freezing and taking damage. It's advisable to not change this by a lot since the entire ecosystem revolves around this. Default = 5")
		public float freezingTemperature = 5f;

		@Config.Comment("How many modifiers of the same type until they stop adding together. Default = 4")
		public int modifierCap = 10;

		@Config.Comment("The temperature that nano or quantum armor will do when you are in a full set.")
		public int nanoOrQuarkTemp = 20;
	}
}
