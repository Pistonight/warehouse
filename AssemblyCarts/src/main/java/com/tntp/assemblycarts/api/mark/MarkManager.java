package com.tntp.assemblycarts.api.mark;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants.NBT;

public class MarkManager {
    private IMarkItem[] markedItems;

    public MarkManager(int markSize) {
        markedItems = new IMarkItem[markSize];
    }

    /**
     * Test if anything is marked at all
     * 
     * @return true if at least 1 itemstack in the mark contents is not null
     */
    public boolean hasMark() {
        for (IMarkItem s : markedItems) {
            if (s != null)
                return true;
        }
        return false;
    }

    public boolean isMarked(ItemStack stack) {
        if (stack == null)
            return false;
        for (int i = 0; i < markedItems.length; i++) {
            if (markedItems[i] != null && markedItems[i].matchesStack(stack))
                return true;
        }
        return false;
    }

    public boolean isMarked(IMarkItem mark) {
        if (mark == null)
            return false;
        for (int i = 0; i < markedItems.length; i++) {
            if (markedItems[i] != null && markedItems[i].isMarkEquivalentTo(mark))
                return true;
        }
        return false;
    }

    public int getSizeMark() {
        return markedItems.length;
    }

    public IMarkItem getMarkedItem(int i) {
        return markedItems[i];
    }

    public IMarkItem[] getAllMarked() {
        return markedItems;
    }

    public void setMarkedItem(int i, IMarkItem s) {
        markedItems[i] = s;
    }

    public void writeToNBT(NBTTagCompound tag) {
        NBTTagList markedList = new NBTTagList();
        for (int i = 0; i < markedItems.length; i++) {
            if (markedItems[i] != null) {
                NBTTagCompound entry = new NBTTagCompound();
                entry.setInteger("slot", i);
                NBTTagCompound stack = new NBTTagCompound();
                MarkerUtil.writeToNBT(stack, markedItems[i]);
                entry.setTag("item", stack);
                markedList.appendTag(entry);
            }
        }
        tag.setTag("markedItems", markedList);
    }

    public void readFromNBT(NBTTagCompound tag) {
        for (int i = 0; i < markedItems.length; i++) {
            markedItems[i] = null;
        }
        NBTTagList markedList = tag.getTagList("markedItems", NBT.TAG_COMPOUND);
        for (int i = 0; i < markedList.tagCount(); i++) {
            NBTTagCompound entry = markedList.getCompoundTagAt(i);
            int slot = entry.getInteger("slot");
            if (slot >= 0 && slot < markedItems.length) {
                IMarkItem item = MarkerUtil.readFromNBT(entry.getCompoundTag("item"));
                markedItems[slot] = item;
            }
        }
    }
}
