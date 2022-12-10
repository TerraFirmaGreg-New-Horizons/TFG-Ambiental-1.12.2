package com.lumintorious.ambiental;

import com.lumintorious.ambiental.capability.TemperatureCapability;
import com.lumintorious.ambiental.capability.TemperaturePacket;

import com.lumintorious.ambiental.item.AmbientalItems;
import com.lumintorious.ambiental.proxy.CommonProxy;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.capability.DumbStorage;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(
        modid = Ambiental.MODID,
        name = Ambiental.NAME,
        version = Ambiental.VERSION,
        dependencies = Ambiental.DEPENDENCIES
)
public class Ambiental
{
    public static final String MODID = "tfcambiental";
    public static final String NAME = "TFG Ambiental";
    public static final String VERSION = "2.3";

    public static final String DEPENDENCIES =
            "required:tfc;" +
            "required:gregtech;" +
            "required:firmalife;" +
            "required:cellars;" +
            "required:tfctech;";

    @Mod.Instance(MODID)
    public static Ambiental INSTANCE;

    @SidedProxy(clientSide = "com.lumintorious.ambiental.proxy.ClientProxy", serverSide = "com.lumintorious.ambiental.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void addItems(RegistryEvent.Register<Item> event) {
            AmbientalItems.register(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerItems(ModelRegistryEvent event) {
            AmbientalItems.registerModels();
        }

    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // Common Events
        MinecraftForge.EVENT_BUS.register(new AmbientalEventHandler());

        // Client Events
        if (event.getSide() == Side.CLIENT) {
            MinecraftForge.EVENT_BUS.register(new AmbientalGuiRenderer());

        }

        // Capability Registry
    	CapabilityManager.INSTANCE.register(TemperatureCapability.class, new DumbStorage(), () -> null);
    	
    	TerraFirmaCraft.getNetwork().registerMessage(new TemperaturePacket.Handler(), TemperaturePacket.class, 0, Side.CLIENT);
    }
}
