package com.tntp.minecraftmodapi.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public interface IItemRegisterFactory {
    public IItemRegister of(Item item, String name);

    public IItemRegisterFactory creativeTabs(CreativeTabs tab);
}
