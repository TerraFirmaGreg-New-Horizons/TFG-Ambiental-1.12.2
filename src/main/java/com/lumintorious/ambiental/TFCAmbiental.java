package com.lumintorious.ambiental;

import com.lumintorious.ambiental.capability.TemperatureCapability;
import com.lumintorious.ambiental.capability.TemperaturePacket;

import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.capability.DumbStorage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = TFCAmbiental.MODID,
        name = TFCAmbiental.NAME,
        version = TFCAmbiental.VERSION,
        dependencies = TFCAmbiental.DEPENDENCIES
)
public class TFCAmbiental
{
    public static final String MODID = "tfcambiental";
    public static final String NAME = "TFC Ambiental";
    public static final String VERSION = "2.3";

    public static final String DEPENDENCIES =
            "required:tfc;" +
            "required:gregtech;" +
            "required:firmalife;" +
            "required:cellars;" +
            "required:tfctech;";
    
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // Common Events
        MinecraftForge.EVENT_BUS.register(new TFCAmbientalEventHandler());

        // Client Events
        if (event.getSide() == Side.CLIENT) {
            MinecraftForge.EVENT_BUS.register(new TFCAmbientalGuiRenderer());
        }

        // Capability Registry
    	CapabilityManager.INSTANCE.register(TemperatureCapability.class, new DumbStorage(), () -> null);
    	
    	TerraFirmaCraft.getNetwork().registerMessage(new TemperaturePacket.Handler(), TemperaturePacket.class, 0, Side.CLIENT);
    }
}
