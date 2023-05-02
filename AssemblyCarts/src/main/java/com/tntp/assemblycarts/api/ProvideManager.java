package com.tntp.assemblycarts.api;

import com.tntp.assemblycarts.api.mark.IMarkItem;
import com.tntp.assemblycarts.api.mark.MarkerUtil;
import com.tntp.minecraftmodapi.util.UniversalUtil;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ProvideManager {
    public boolean update;
    private IMarkItem target;
    private IInventory providingInventory;
    private int[] providingSlots;

    public ProvideManager(IInventory inv, int[] slots) {
        providingInventory = inv;
        setProvidingSlots(slots);
    }

    public IInventory getProvidingInventory() {
        return providingInventory;
    }

    public void setProvidingInventory(IInventory inv) {
        providingInventory = inv;
    }

    /**
     * A null return value represents that this provider can provide to any target
     * 
     * @return
     */
    public IMarkItem getProvideTarget() {
        return target;
    }

    public void setProvideTarget(IMarkItem t) {
        target = t;
        update = true;
    }

    public boolean canProvideTo(RequestManager rm) {
        if (target == null)
            return true;
        IMarkItem requestTarget = rm.getCraftingTarget();
        if (requestTarget != null && requestTarget.isMarkEquivalentTo(target))
            return true;
        return false;
    }

    /**
     * Called to try to provide 1 item (stacksize=1) to the request manager. Does
     * not check provide type
     * 
     * @param rm
     * @param side the side of the INVENTORY that is providing, or -1 if the
     *             side doesn't apply
     * @return true if any item is provided
     */
    public boolean tryProvide(RequestManager rm, int side) {
        boolean sided = side >= 0 && side < 6 && providingInventory instanceof ISidedInventory;
        for (int slotsID = 0; slotsID < providingSlots.length; slotsID++) {
            int i = providingSlots[slotsID];
            if (i < 0 || i >= providingInventory.getSizeInventory())
                continue;
            ItemStack stackInSlot = providingInventory.getStackInSlot(i);

            // side check
            if (sided) {
                if (!((ISidedInventory) providingInventory).canExtractItem(i, stackInSlot, side))
                    continue;
            }
            if (stackInSlot != null && rm.isRequesting(stackInSlot)) {
                ItemStack sup = stackInSlot.splitStack(1);
                rm.supply(sup);
                if (sup.stackSize > 0) {// supply failed, add the stack back
                    stackInSlot.stackSize++;
                } else {
                    // supply succeeded, apply change to inventory
                    if (stackInSlot.stackSize == 0)
                        stackInSlot = null;
                    providingInventory.setInventorySlotContents(i, stackInSlot);
                    providingInventory.markDirty();
                    return true;
                }

            }
        }
        return false;
    }

    public void readFromNBT(NBTTagCompound tag) {
        NBTTagCompound provideTag = tag.getCompoundTag("provideTarget");
        target = MarkerUtil.readFromNBT(provideTag);
        providingSlots = tag.getIntArray("providingSlots");
    }

    public void writeToNBT(NBTTagCompound tag) {
        NBTTagCompound provideTag = new NBTTagCompound();

        if (target != null) {
            MarkerUtil.writeToNBT(provideTag, target);
            tag.setTag("provideTarget", provideTag);
        }
        tag.setIntArray("providingSlots", providingSlots);
    }

    public int[] getProvidingSlots() {
        return providingSlots;
    }

    public void setProvidingSlots(int[] providingSlots) {
        if (providingSlots == null)
            providingSlots = UniversalUtil.EMPTY_INT_ARRAY;
        this.providingSlots = providingSlots;
    }

}
