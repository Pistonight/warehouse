package com.tntp.assemblycarts.api.mark;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Represent a marked item. Instances of this interface should be immutable
 *
 */
public interface IMarkItem {

    public boolean matchesStack(ItemStack stack);

    /**
     * This should return a clean copy of the display stack. Changes made on the
     * returned itemstack should not change the mark
     * 
     * @return
     */
    @SideOnly(Side.CLIENT)
    public ItemStack getDisplayStack();

    @SideOnly(Side.CLIENT)
    default void nextDisplayStack() {

    }

    public void writeToNBT(NBTTagCompound tag);

    public IMarkItem readFromNBT(NBTTagCompound tag);

    public int stacksize();

    public String displayName();

    public IMarkItem setStackSize(int size);

    public boolean isMarkEquivalentTo(IMarkItem mark);

}
