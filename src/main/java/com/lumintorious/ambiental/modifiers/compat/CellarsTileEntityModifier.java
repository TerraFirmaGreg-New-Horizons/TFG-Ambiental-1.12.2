package com.lumintorious.ambiental.modifiers.compat;

import com.eerussianguy.firmalife.te.TEOven;
import com.lumintorious.ambiental.modifiers.BlockModifier;
import com.lumintorious.ambiental.modifiers.TileEntityModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.sharkbark.cellars.blocks.tileentity.TEIceBunker;
import org.apache.commons.lang3.reflect.FieldUtils;

import static com.lumintorious.ambiental.modifiers.TileEntityModifier.hasLeatherArmorProtection;

public class CellarsTileEntityModifier extends BlockModifier {
    public CellarsTileEntityModifier(String name) {
        super(name);
    }

    public CellarsTileEntityModifier(String name, float change, float potency) {
        super(name, change, potency);
    }

    public CellarsTileEntityModifier(String name, float change, float potency, boolean affectedByDistance) {
        super(name, change, potency, affectedByDistance);
    }

    public static TileEntityModifier handleCellar(TileEntity tile, EntityPlayer player) {
        if (tile instanceof TEIceBunker) {
            TEIceBunker iceBunker = (TEIceBunker) tile;

            boolean isComplete = false;
            float temperature = 0.0f;

            try {
                isComplete = (boolean) FieldUtils.readField(iceBunker, "isComplete", true);
                temperature = (float) FieldUtils.readField(iceBunker, "temperature", true);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            float change =  0.0f;
            float potency = 0.0f;

            if (isComplete) {

                if (temperature < 10) {
                    change = -2f * (12 - temperature);
                    potency = -0.5f;
                }

                if (hasLeatherArmorProtection(player)) {
                    change = TileEntityModifier.mod;
                }
            }


            return new TileEntityModifier("cellar", change, potency);
        }
        else {
            return null;
        }
    }
}
