package com.lumintorious.ambiental;

import com.lumintorious.ambiental.capability.ITemperatureCapability;
import com.lumintorious.ambiental.capability.TemperaturePacket;

import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.capability.DumbStorage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(
        modid = TFCTemperature.MODID,
        name = TFCTemperature.NAME,
        version = TFCTemperature.VERSION,
        dependencies = "required:tfc;"
)
public class TFCTemperature
{
    public static final String MODID = "tfctemperature";
    public static final String NAME = "TFC Temperature";
    public static final String VERSION = "1.0";
    
    @Mod.Instance
    public static TFCTemperature INSTANCE;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
        if (event.getSide() == Side.CLIENT)
        {
            MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
        }

    	CapabilityManager.INSTANCE.register(ITemperatureCapability.class, new DumbStorage(), () -> null);
    	
    	TerraFirmaCraft.getNetwork().registerMessage(new TemperaturePacket.Handler(), TemperaturePacket.class, 0, Side.CLIENT);
    }
}
