package com.tntp.minecraftmodapi.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;

/**
 * 
 *
 */
public interface IItemTag {
    /*
     * The NBT structure will be like this:
     * The stack has a NBTTagCompound, and IItemTag can be converted to a
     * NBTTagCompound
     * which will be under the stack's tag
     */
    /**
     * Test if the item can have this tag
     * 
     * @return
     */
    boolean isValidFor(Item item);

    /**
     * If this method returns false, setItemTagTo method will remove the tag from
     * the stack
     * 
     * @return
     */
    boolean isTagValid();

    /**
     * Get the tag name. This tag must be unique across the entire Minecraft
     * 
     * @return
     */
    String getTagName();

    /**
     * This tag is specifically for this IItemTag!
     * You do not need to wrap this tag in another tag
     * 
     * @param tag
     */
    void writeToNBT(NBTTagCompound tag);

    void readFromNBT(NBTTagCompound tag);

    @SideOnly(Side.CLIENT)
    default void addTooltip(boolean adv, boolean shift, boolean ctrl, List<String> tooltip) {

    }

}
