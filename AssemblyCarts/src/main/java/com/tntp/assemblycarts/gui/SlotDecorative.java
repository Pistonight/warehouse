package com.tntp.assemblycarts.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotDecorative extends Slot {

  public SlotDecorative(IInventory inv, int index, int x, int y) {
    super(inv, index, x, y);
  }

  public boolean isItemValid(ItemStack p_75214_1_) {
    return false;
  }

  public ItemStack decrStackSize(int p_75209_1_) {
    return null;
  }

  public boolean canTakeStack(EntityPlayer p_82869_1_) {
    return false;
  }

}
