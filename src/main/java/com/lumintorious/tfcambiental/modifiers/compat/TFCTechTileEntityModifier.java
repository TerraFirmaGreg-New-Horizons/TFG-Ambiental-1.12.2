package com.lumintorious.tfcambiental.modifiers.compat;

import com.lumintorious.tfcambiental.modifiers.BlockModifier;
import com.lumintorious.tfcambiental.modifiers.TileEntityModifier;
import net.dries007.tfc.objects.te.TECrucible;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import tfctech.objects.tileentities.*;

import static com.lumintorious.tfcambiental.modifiers.TileEntityModifier.hasLeatherArmorProtection;

public class TFCTechTileEntityModifier extends BlockModifier {

    public TFCTechTileEntityModifier(String name) {
        super(name);
    }

    public TFCTechTileEntityModifier(String name, float change, float potency) {
        super(name, change, potency);
    }

    public TFCTechTileEntityModifier(String name, float change, float potency, boolean affectedByDistance) {
        super(name, change, potency, affectedByDistance);
    }

    public static TileEntityModifier handleElectricForge(TileEntity tile, EntityPlayer player) {
        if (tile instanceof TEElectricForge) {
            TEElectricForge electricForge = (TEElectricForge) tile;
            float temp = electricForge.getField(TECrucible.FIELD_TEMPERATURE);
            float change =  temp / 100f;
            float potency = temp / 350f;
            if (hasLeatherArmorProtection(player)) {
                change = TileEntityModifier.mod;
            }
            return new TileEntityModifier("electric_forge", change, potency);
        } else {
            return null;
        }
    }

    public static TileEntityModifier handleInductionCrucible(TileEntity tile, EntityPlayer player) {
        if (tile instanceof TEInductionCrucible) {
            TEInductionCrucible inductionCrucible = (TEInductionCrucible) tile;
            float temp = inductionCrucible.getField(TECrucible.FIELD_TEMPERATURE);
            float change =  temp / 100f;
            float potency = temp / 350f;
            if (hasLeatherArmorProtection(player)) {
                change = TileEntityModifier.mod;
            }
            return new TileEntityModifier("induction_crucible", change, potency);
        } else {
            return null;
        }
    }

    public static TileEntityModifier handleSmelteryCauldron(TileEntity tile, EntityPlayer player) {
        if (tile instanceof TESmelteryCauldron) {
            TESmelteryCauldron smelteryCauldron = (TESmelteryCauldron) tile;
            float temp = smelteryCauldron.getField(TECrucible.FIELD_TEMPERATURE);
            float change =  temp / 120f;
            float potency = temp / 370f;
            if (hasLeatherArmorProtection(player)) {
                change = TileEntityModifier.mod;
            }
            return new TileEntityModifier("smeltery_cauldron", change, potency);
        } else {
            return null;
        }
    }

    public static TileEntityModifier handleSmelteryFirebox(TileEntity tile, EntityPlayer player) {
        if (tile instanceof TESmelteryFirebox) {
            TESmelteryFirebox smelteryFirebox = (TESmelteryFirebox) tile;
            float temp = smelteryFirebox.getField(TECrucible.FIELD_TEMPERATURE);
            float change =  temp / 120f;
            float potency = temp / 370f;
            if (hasLeatherArmorProtection(player)) {
                change = TileEntityModifier.mod;
            }
            return new TileEntityModifier("smeltery_firebox", change, potency);
        } else {
            return null;
        }
    }

    public static TileEntityModifier handleFridge(TileEntity tile, EntityPlayer player) {
        if (tile instanceof TEFridge) {
            TEFridge fridge = (TEFridge) tile;

            float change =  0f;
            float potency = 0f;

            if (fridge.isOpen()) {
                change =  -10f;
                potency = -0.7f;
            }

            return new TileEntityModifier("fridge", change, potency);
        } else {
            return null;
        }
    }
}
