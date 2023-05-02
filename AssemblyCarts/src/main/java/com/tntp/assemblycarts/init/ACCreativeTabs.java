package com.tntp.assemblycarts.init;

import com.tntp.assemblycarts.core.AssemblyCartsMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ACCreativeTabs extends CreativeTabs {
    public static final ACCreativeTabs instance = new ACCreativeTabs();

    public ACCreativeTabs() {
        super(AssemblyCartsMod.MODID);
    }

    @Override
    public Item getTabIconItem() {
        return ACItems.assembly_worker_cart;
    }

}
