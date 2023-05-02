package com.tntp.assemblycarts.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryDisplay implements IInventory {
  ItemStack[] stacks;

  public InventoryDisplay(ItemStack... stacks) {
    this.stacks = stacks;
  }

  @Override
  public int getSizeInventory() {
    return stacks.length;
  }

  @Override
  public ItemStack getStackInSlot(int i) {
    return stacks[i];
  }

  @Override
  public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
    return null;
  }

  @Override
  public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
    return null;
  }

  @Override
  public void setInventorySlotContents(int i, ItemStack stack) {
    stacks[i] = stack;
  }

  @Override
  public String getInventoryName() {
    return "";
  }

  @Override
  public boolean hasCustomInventoryName() {
    return false;
  }

  @Override
  public int getInventoryStackLimit() {
    return 0;
  }

  @Override
  public void markDirty() {
  }

  @Override
  public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
    return true;
  }

  @Override
  public void openInventory() {
  }

  @Override
  public void closeInventory() {
  }

  @Override
  public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
    return false;
  }
}
