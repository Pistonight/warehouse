package com.tntp.minecraftmodapi.util;

import java.util.List;

import javax.annotation.Nonnull;

import com.tntp.minecraftmodapi.item.IItemTag;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.oredict.OreDictionary;

public class ItemUtil {

    public static void writeItemStackToNBTWithIntSize(@Nonnull ItemStack stack, @Nonnull NBTTagCompound tag) {
        if (stack == null || tag == null)
            throw new IllegalArgumentException();
        NBTTagCompound wrapped = new NBTTagCompound();
        stack.writeToNBT(wrapped);
        tag.setTag("wrappedItemStack", wrapped);
        tag.setInteger("size", stack.stackSize);
    }

    public static ItemStack readItemStackFromNBTWithIntSize(@Nonnull NBTTagCompound tag) {
        if (tag == null)
            throw new IllegalArgumentException();
        if (tag.hasKey("size") && tag.hasKey("wrappedItemStack")) {
            NBTTagCompound wrapped = tag.getCompoundTag("wrappedItemStack");
            if (wrapped != null) {
                ItemStack stack = ItemStack.loadItemStackFromNBT(wrapped);
                if (stack != null) {
                    stack.stackSize = tag.getInteger("size");
                    return stack;
                }
            }
        }
        return null;
    }

    public static boolean isItemInOreDict(ItemStack stack, String oreDictEntry) {
        List<ItemStack> ores = OreDictionary.getOres(oreDictEntry);
        for (ItemStack ore : ores) {
            if (OreDictionary.itemMatches(ore, stack, false))
                return true;
        }
        return false;
    }

    /**
     * See if the two ItemStack are the same except for the stacksize
     * 
     * @param stack1
     * @param stack2
     * @return
     */
    public static boolean areItemAndTagEqual(ItemStack stack1, ItemStack stack2) {
        if (stack1 == null && stack2 == null)
            return true;
        if (ItemStack.areItemStackTagsEqual(stack1, stack2)) {
            return stack1.isItemEqual(stack2);
        }
        return false;
    }

    public static boolean addToInventory(ItemStack stackToPut, IInventory inventory, int start, int end, int side) {
        int[] slots = new int[end - start + 1];
        for (int i = 0; i < slots.length; i++) {
            slots[i] = i + start;
        }
        return addToInventory(stackToPut, inventory, slots, -1);
    }

    /**
     * Add the stack to the inventory. The stacksize of the stack will decrease when
     * items are put into the inventory
     * 
     * @param stackToPut
     * @param inventory  The inventory to put the stack
     * @param slots      An array containing the slots to put the stacks
     */
    public static boolean addToInventory(ItemStack stackToPut, IInventory inventory, int[] slots, int side) {
        boolean inventoryChanged = false;
        boolean sided = side >= 0 && side < 6 && inventory instanceof ISidedInventory;
        for (int slotsID = 0; slotsID < slots.length && stackToPut.stackSize > 0; slotsID++) {
            int i = slots[slotsID];
            if (i < 0 || i >= inventory.getSizeInventory())
                continue;

            if (sided) {
                if (!((ISidedInventory) inventory).canInsertItem(i, stackToPut, side))
                    continue;
            }

            ItemStack stackInInventory = inventory.getStackInSlot(i);
            int maxStackSize = Math.min(inventory.getInventoryStackLimit(), stackToPut.getMaxStackSize());
            if (stackInInventory == null) {
                if (inventory.isItemValidForSlot(i, stackToPut)) {
                    // Can directly putin the stack
                    int putInSize;
                    if (stackToPut.stackSize <= maxStackSize) {
                        // put the whole stack
                        putInSize = stackToPut.stackSize;
                    } else {
                        // put in max
                        putInSize = maxStackSize;
                    }

                    inventory.setInventorySlotContents(i, stackToPut.splitStack(putInSize));
                    inventoryChanged = true;
                }
            } else {
                // There is already a stack in the slot
                if (ItemUtil.areItemAndTagEqual(stackToPut, stackInInventory)) {
                    // If the the stack in slot is equal to stack to put
                    int putInSize;
                    if (stackInInventory.stackSize + stackToPut.stackSize <= maxStackSize) {
                        // Can putin the whole stack
                        putInSize = stackToPut.stackSize;
                        stackToPut.stackSize = 0;
                    } else {
                        // put in to max
                        putInSize = maxStackSize - stackInInventory.stackSize;
                        stackToPut.stackSize -= putInSize;
                    }
                    // stackToPut.splitStack(putInSize);
                    stackInInventory.stackSize += putInSize;
                    inventoryChanged = true;
                }
            }
        }
        if (inventoryChanged)
            inventory.markDirty();
        return inventoryChanged;
    }

    /**
     * Set the tag to the stack. If the tag is invalid, it will be removed from the
     * stack.
     * 
     * @param stack
     * @param tag
     */
    public static void setItemTagTo(@Nonnull ItemStack stack, @Nonnull IItemTag tag) {
        if (stack == null || tag == null)
            throw new IllegalArgumentException();
        if (tag.isTagValid()) {
            addItemTagTo(stack, tag);
        } else {
            removeItemTagFrom(stack, tag.getTagName());
        }
    }

    /**
     * Add the item tag to the stack. The stack must have the valid item/
     * This will override any existing tag with the same name
     * 
     * @param stack
     * @param tag
     * @return true if the tag is added
     */
    public static boolean addItemTagTo(@Nonnull ItemStack stack, @Nonnull IItemTag tag) {
        if (stack == null || tag == null)
            throw new IllegalArgumentException();
        if (stack.getItem() == null || !tag.isValidFor(stack.getItem()))
            return false;
        NBTTagCompound stackTag = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
        NBTTagCompound itemTag = new NBTTagCompound();
        tag.writeToNBT(itemTag);
        stackTag.setTag(tag.getTagName(), itemTag);
        stack.setTagCompound(stackTag);
        return true;
    }

    public static void removeItemTagFrom(@Nonnull ItemStack stack, String tagName) {
        if (stack == null)
            throw new IllegalArgumentException();
        if (tagName == null)
            return;
        if (stack.hasTagCompound()) {
            NBTTagCompound stackTag = stack.getTagCompound();
            stackTag.removeTag(tagName);
            return;
        }
        return;
    }

    /**
     * Get the tag from the stack.
     * 
     * @param stack
     * @param emptyTag
     * @return null if the tag doesn't exist. Otherwise the tag will be written to
     *         the second argument and returned
     */
    public static <TAG extends IItemTag> TAG getItemTag(@Nonnull ItemStack stack, @Nonnull TAG emptyTag) {
        if (stack == null || emptyTag == null)
            throw new IllegalArgumentException();
        if (stack.getItem() == null || !emptyTag.isValidFor(stack.getItem()))
            return null;
        if (stack.hasTagCompound()) {
            NBTTagCompound stackTag = stack.getTagCompound();
            if (stackTag.hasKey(emptyTag.getTagName())) {
                NBTTagCompound itemTag = stackTag.getCompoundTag(emptyTag.getTagName());
                emptyTag.readFromNBT(itemTag);
                return emptyTag;
            }
        }
        return null;
    }

    public static boolean hasItemTag(@Nonnull ItemStack stack, String tag) {
        if (stack == null)
            throw new IllegalArgumentException();
        if (tag == null)
            return false;
        if (stack.hasTagCompound()) {
            NBTTagCompound stackTag = stack.getTagCompound();
            if (stackTag.hasKey(tag)) {
                return true;
            }
        }
        return false;
    }

    public static String toTwoDigitStackSizeDisplay(int stacksize) {
        if (stacksize == 0)
            return EnumChatFormatting.GRAY + "" + stacksize;
        if (stacksize < 100)
            return String.valueOf(stacksize);
        if (stacksize < 1000)
            return stacksize / 100 + "!";
        if (stacksize < 10000)
            return EnumChatFormatting.YELLOW + "" + stacksize / 1000 + "K";
        if (stacksize < 100000)
            return EnumChatFormatting.GREEN + "" + stacksize / 10000 + "W";
        if (stacksize < 1000000)
            return EnumChatFormatting.AQUA + "" + stacksize / 100000 + "L";
        if (stacksize < 10000000)
            return EnumChatFormatting.LIGHT_PURPLE + "" + stacksize / 1000000 + "M";
        if (stacksize < 100000000)
            return EnumChatFormatting.LIGHT_PURPLE + "" + stacksize / 10000000 + "U";
        if (stacksize < 1000000000)
            return EnumChatFormatting.LIGHT_PURPLE + "" + stacksize / 100000000 + "V";
        return EnumChatFormatting.LIGHT_PURPLE + "?G";
    }
}
