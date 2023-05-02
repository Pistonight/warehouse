package com.tntp.assemblycarts.item.tag;

import java.util.List;

import com.tntp.assemblycarts.core.AssemblyCartsMod;
import com.tntp.assemblycarts.init.ACItems;
import com.tntp.minecraftmodapi.item.IItemTag;
import com.tntp.minecraftmodapi.util.LocalUtil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;

public class TagOreLookupTable implements IItemTag {
    public static final String TAGNAME = AssemblyCartsMod.MODID + "|OreLookupTable";
    public String oreDictEntry;

    @Override
    public boolean isValidFor(Item item) {
        return item == ACItems.ore_lookup_table;
    }

    @Override
    public boolean isTagValid() {
        return oreDictEntry != null && oreDictEntry.length() > 0;
    }

    @Override
    public String getTagName() {
        return TAGNAME;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        if (isTagValid())
            tag.setString("ore", oreDictEntry);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        oreDictEntry = tag.getString("ore");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addTooltip(boolean adv, boolean shift, boolean ctrl, List<String> tooltip) {
        if (isTagValid()) {
            tooltip.add(LocalUtil.localize("ac.tooltip.ore_dict_entry_arg_s", oreDictEntry));
        }
    }

}
