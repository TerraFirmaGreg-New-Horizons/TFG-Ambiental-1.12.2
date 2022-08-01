package com.lumintorious.ambiental.modifiers.compat;

import com.eerussianguy.firmalife.te.TEOven;
import com.lumintorious.ambiental.modifiers.BlockModifier;
import com.lumintorious.ambiental.modifiers.TileEntityModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import org.apache.commons.lang3.reflect.FieldUtils;

import static com.lumintorious.ambiental.modifiers.TileEntityModifier.hasLeatherArmorProtection;

public class FirmaLifeTileEntityModifier extends BlockModifier {
    public FirmaLifeTileEntityModifier(String name) {
        super(name);
    }

    public FirmaLifeTileEntityModifier(String name, float change, float potency) {
        super(name, change, potency);
    }

    public FirmaLifeTileEntityModifier(String name, float change, float potency, boolean affectedByDistance) {
        super(name, change, potency, affectedByDistance);
    }

    public static TileEntityModifier handleClayOven(TileEntity tile, EntityPlayer player) {
        if (tile instanceof TEOven) {
            TEOven oven = (TEOven) tile;

            boolean isBurning = false;

            try {
                isBurning = (boolean) FieldUtils.readField(oven, "isBurning", true);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            float change =  0.0f;
            float potency = 1.0f;

            if (isBurning) {
                change =  8f;
                potency = 4f;

                if (hasLeatherArmorProtection(player)) {
                    change = TileEntityModifier.mod;
                }
            }


            return new TileEntityModifier("firmalife_oven", change, potency);
        }
        else {
            return null;
        }
    }
}
