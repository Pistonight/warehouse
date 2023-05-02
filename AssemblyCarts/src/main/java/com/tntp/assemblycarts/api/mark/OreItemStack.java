package com.tntp.assemblycarts.api.mark;

import java.util.List;

import com.tntp.minecraftmodapi.util.ItemUtil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

public class OreItemStack implements IMarkItem {
    private final String oreDictEntry;
    private final int stacksize;
    @SideOnly(Side.CLIENT)
    private int displayIndex;
    @SideOnly(Side.CLIENT)
    private ItemStack[] cachedDisplayStack;

    public OreItemStack(String ore, int stacksize) {
        oreDictEntry = ore;
        this.stacksize = stacksize;
    }

    public OreItemStack() {
        this(null, 0);
    }

    @Override
    public boolean matchesStack(ItemStack stack) {
        return ItemUtil.isItemInOreDict(stack, oreDictEntry);
    }

    @Override
    public ItemStack getDisplayStack() {
        if (cachedDisplayStack == null) {
            List<ItemStack> ores = OreDictionary.getOres(oreDictEntry);
            cachedDisplayStack = ores.toArray(new ItemStack[ores.size()]);
            displayIndex = 0;
        }
        if (cachedDisplayStack.length == 0)
            return null;
        if (displayIndex < 0 || displayIndex >= cachedDisplayStack.length)
            displayIndex = 0;

        ItemStack s = ItemStack.copyItemStack(cachedDisplayStack[displayIndex]);
        s.stackSize = stacksize;
        return s;
    }

    @SideOnly(Side.CLIENT)
    public void nextDisplayStack() {
        if (cachedDisplayStack != null && cachedDisplayStack.length > 0) {
            if (displayIndex >= cachedDisplayStack.length) {
                displayIndex = 0;
            } else {
                displayIndex++;
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        tag.setString("ore", oreDictEntry);
        tag.setInteger("stacksize", stacksize);
    }

    @Override
    public IMarkItem readFromNBT(NBTTagCompound tag) {
        return new OreItemStack(tag.getString("ore"), tag.getInteger("stacksize"));
    }

    @Override
    public int stacksize() {
        return stacksize;
    }

    @Override
    public IMarkItem setStackSize(int size) {
        return new OreItemStack(oreDictEntry, size);
    }

    @Override
    public String displayName() {
        return oreDictEntry;
    }

    @Override
    public boolean isMarkEquivalentTo(IMarkItem mark) {
        if (mark instanceof OreItemStack) {
            if (oreDictEntry == null)
                return ((OreItemStack) mark).oreDictEntry == null;
            else
                return oreDictEntry.equals(((OreItemStack) mark).oreDictEntry);
        }
        return false;
    }

}
