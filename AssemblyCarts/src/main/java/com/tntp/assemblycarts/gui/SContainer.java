package com.tntp.assemblycarts.gui;

import com.tntp.assemblycarts.api.mark.IMarkItem;
import com.tntp.assemblycarts.api.mark.MarkerUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class SContainer extends Container {
    private int machineSlots;

    public SContainer(IInventory playerInventory, int slots, IInventory machine, int playerSlotsX, int playerSlotsY) {
        machineSlots = slots;
        this.setupMachineSlots(machine);
        setupPlayerInventory(playerInventory, playerSlotsX, playerSlotsY);

    }

    public void setupPlayerInventory(IInventory playerInventory, int playerSlotsX, int playerSlotsY) {
        for (int j = 0; j < 9; j++) {
            this.addSlotToContainer(new Slot(playerInventory, j, playerSlotsX + j * 18, playerSlotsY + 58));
        }
        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(playerInventory, j + 9 + k * 9, playerSlotsX + j * 18, playerSlotsY + k * 18));
            }
        }
    }

    public abstract void setupMachineSlots(IInventory machine);

    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < machineSlots) {
                if (!this.mergeItemStack(itemstack1, machineSlots, this.inventorySlots.size(), true)) {
                    return null;
                }
            } else {
                int start = 0;
                int end = machineSlots;
                if (!this.mergeItemStack(itemstack1, start, end, false)) {
                    return null;
                }
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    /**
     * merges provided ItemStack with the first avaliable one in the
     * container/player inventory
     */
    // add slot validity check
    // add slot stack limit check
    protected boolean mergeItemStack(ItemStack stack, int start, int end, boolean increasingOrder) {
        boolean putIn = false;
        int k = start;

        if (increasingOrder) {
            k = end - 1;
        }

        Slot slot;
        ItemStack itemstack1;

        if (stack.isStackable()) {
            while (stack.stackSize > 0 && (!increasingOrder && k < end || increasingOrder && k >= start)) {
                slot = (Slot) this.inventorySlots.get(k);
                itemstack1 = slot.getStack();
                if (itemstack1 != null && itemstack1.getItem() == stack.getItem() && (!stack.getHasSubtypes() || stack.getItemDamage() == itemstack1.getItemDamage())
                        && ItemStack.areItemStackTagsEqual(stack, itemstack1)) {
                    int l = itemstack1.stackSize + stack.stackSize;
                    int maxSize = Math.min(stack.getMaxStackSize(), slot.getSlotStackLimit());// add: respect slot limit

                    if (l <= maxSize) {
                        stack.stackSize = 0;
                        itemstack1.stackSize = l;
                        slot.onSlotChanged();
                        putIn = true;
                    } else if (itemstack1.stackSize < maxSize) {
                        stack.stackSize -= maxSize - itemstack1.stackSize;
                        itemstack1.stackSize = maxSize;
                        slot.onSlotChanged();
                        putIn = true;
                    }
                }

                if (increasingOrder) {
                    --k;
                } else {
                    ++k;
                }
            }
        }

        if (stack.stackSize > 0) {
            if (increasingOrder) {
                k = end - 1;
            } else {
                k = start;
            }

            while (stack.stackSize > 0 && (!increasingOrder && k < end || increasingOrder && k >= start)) {
                slot = (Slot) this.inventorySlots.get(k);
                itemstack1 = slot.getStack();

                if (itemstack1 == null && slot.isItemValid(stack))// add
                {
                    ItemStack putStack = stack.copy();
                    int putSize = Math.min(slot.getSlotStackLimit(), stack.stackSize);
                    putStack.stackSize = putSize;
                    slot.putStack(putStack);
                    slot.onSlotChanged();
                    stack.stackSize -= putSize;
                    putIn = true;
                    break;
                }

                if (increasingOrder) {
                    --k;
                } else {
                    ++k;
                }
            }
        }

        return putIn;
    }

    /**
     * Override to handle decorative slots
     */
    @Override
    public ItemStack slotClick(int slotID, int p_75144_2_, int p_75144_3_, EntityPlayer p_75144_4_) {
        if (slotID >= 0) {
            Slot slot = (Slot) this.inventorySlots.get(slotID);
            if (slot instanceof SlotDecorative)
                return null;
        }
        return super.slotClick(slotID, p_75144_2_, p_75144_3_, p_75144_4_);
    }

    public IMarkItem processMarkSlotClick(int mouseButton, ItemStack playerHolding, IMarkItem markedStack) {
        // both server and client
        IMarkItem returnStack = markedStack;
        int returnSize = -1;
        if (markedStack == null || (playerHolding != null && !markedStack.matchesStack(playerHolding))) {
            returnStack = MarkerUtil.stackToMark(playerHolding);
            if (mouseButton == 1)
                returnSize = 1;
        } else {
            if (mouseButton == 0) {
                if (markedStack.stacksize() == 1)
                    returnStack = null;
                else
                    returnSize = markedStack.stacksize() - 1;
            } else if (mouseButton == 1) {
                returnSize = markedStack.stacksize() + 1;
            } else {
                returnStack = null;
            }
        }

        if (returnStack != null && returnSize != -1) {
            returnStack = returnStack.setStackSize(returnSize);
        }
        return returnStack;
    }

    public void processSlotClick(int slotID, int mouseButton) {

    }

}
