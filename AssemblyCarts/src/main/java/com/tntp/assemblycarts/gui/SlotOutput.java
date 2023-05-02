package com.tntp.assemblycarts.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Cannot put anything in
 * 
 * @author iTNTPiston
 *
 */
public class SlotOutput extends Slot {

  public SlotOutput(IInventory inv, int index, int x, int y) {
    super(inv, index, x, y);
  }

  public boolean isItemValid(ItemStack stack) {
    return false;
  }

}
