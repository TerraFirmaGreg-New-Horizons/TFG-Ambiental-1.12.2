package com.lumintorious.ambiental.api;

import com.lumintorious.ambiental.Ambiental;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import static com.lumintorious.ambiental.Ambiental.MODID;

public class AmbientalItemBase extends Item {

    protected String name;

    public AmbientalItemBase(String name) {
        this.name = name;
        setTranslationKey(MODID + "." + name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.MATERIALS);
    }

    public void registerItemModel() {
        Ambiental.proxy.registerItemRenderer(this, 0, name);
    }

    @Override
    public AmbientalItemBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

}
