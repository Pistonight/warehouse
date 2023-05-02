package com.tntp.minecraftmodapi.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants.NBT;

public class TileEntityInventoryAPIiTNTPiston extends TileEntityAPIiTNTPiston implements IInventory {
    private ItemStack[] inventory;

    public TileEntityInventoryAPIiTNTPiston(int size) {
        inventory = new ItemStack[size];
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int size) {
        if (inventory[slot] == null)
            return null;
        ItemStack stack;
        if (inventory[slot].stackSize <= size) {
            stack = inventory[slot];
            inventory[slot] = null;
        } else {
            stack = inventory[slot].splitStack(size);
            if (inventory[slot].stackSize == 0)
                inventory[slot] = null;
        }
        markDirty();
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack s = inventory[slot];
        inventory[slot] = null;
        return s;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inventory[slot] = stack;
        markDirty();
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return isValidInWorld();
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return true;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        NBTTagList items = new NBTTagList();

        for (int i = 0; i < inventory.length; ++i) {
            if (inventory[i] != null) {
                NBTTagCompound item = new NBTTagCompound();
                item.setByte("Slot", (byte) i);
                inventory[i].writeToNBT(item);
                items.appendTag(item);
            }
        }

        tag.setTag("Items", items);
        tag.setInteger("inventory_size", inventory.length);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        NBTTagList items = tag.getTagList("Items", NBT.TAG_COMPOUND);
        int size = tag.getInteger("inventory_size");
        inventory = new ItemStack[size];

        for (int i = 0; i < items.tagCount(); ++i) {
            NBTTagCompound item = items.getCompoundTagAt(i);
            int j = item.getByte("Slot") & 255;

            if (j >= 0 && j < inventory.length) {
                inventory[j] = ItemStack.loadItemStackFromNBT(item);
            }
        }
    }

    @Override
    public String getInventoryName() {
        return getBlockType().getUnlocalizedName() + ".name";
    }
}
