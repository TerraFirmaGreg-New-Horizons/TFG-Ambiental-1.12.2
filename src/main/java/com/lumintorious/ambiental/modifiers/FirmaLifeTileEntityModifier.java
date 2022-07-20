package com.lumintorious.ambiental.modifiers;

import com.eerussianguy.firmalife.te.TEOven;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.apache.commons.lang3.reflect.FieldUtils;

import static com.lumintorious.ambiental.modifiers.TFCTileEntityModifier.hasProtection;

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

    public static TFCTileEntityModifier handleClayOven(TileEntity tile, EntityPlayer player) {
        if (tile instanceof TEOven) {
            TEOven oven = (TEOven) tile;

            boolean isBurning = false;

            try {
                isBurning = (boolean) FieldUtils.readField(oven, "isBurning", true);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            float change =  1.0f;
            float potency = 1.0f;

            if (isBurning) {
                change =  12f;
                potency = 4f;
            }

            if (hasProtection(player)) {
                change = 1.0F;
            }
            return new TFCTileEntityModifier("firmalife_oven", change, potency);
        }
        else {
            return null;
        }
    }
}
