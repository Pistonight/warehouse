package com.tntp.assemblycarts.api.mark;

import com.tntp.minecraftmodapi.util.ItemUtil;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class MarkItemStack implements IMarkItem {
    private final ItemStack wrapped;

    public MarkItemStack(ItemStack s, int stackSize) {
        wrapped = ItemStack.copyItemStack(s);
        if (wrapped != null)
            wrapped.stackSize = stackSize;
    }

    public MarkItemStack(ItemStack s) {
        this(s, s == null ? 0 : s.stackSize);
    }

    public MarkItemStack() {
        this(null, 0);
    }

    @Override
    public boolean matchesStack(ItemStack stack) {
        return ItemUtil.areItemAndTagEqual(wrapped, stack);
    }

    @Override
    public ItemStack getDisplayStack() {
        return ItemStack.copyItemStack(wrapped);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        if (wrapped != null) {
            ItemUtil.writeItemStackToNBTWithIntSize(wrapped, tag);
        }
    }

    @Override
    public IMarkItem readFromNBT(NBTTagCompound tag) {
        return new MarkItemStack(ItemUtil.readItemStackFromNBTWithIntSize(tag));
    }

    @Override
    public int stacksize() {
        return wrapped.stackSize;
    }

    @Override
    public IMarkItem setStackSize(int size) {
        return new MarkItemStack(wrapped, size);
    }

    @Override
    public String displayName() {
        if (wrapped != null)
            return wrapped.getDisplayName();
        return "";
    }

    @Override
    public boolean isMarkEquivalentTo(IMarkItem mark) {
        if (mark instanceof MarkItemStack) {
            return ItemUtil.areItemAndTagEqual(wrapped, ((MarkItemStack) mark).wrapped);
        }
        return false;
    }

}
