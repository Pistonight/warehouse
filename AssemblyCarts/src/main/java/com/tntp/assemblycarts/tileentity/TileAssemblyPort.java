package com.tntp.assemblycarts.tileentity;

import com.tntp.assemblycarts.api.mark.IMarkItem;
import com.tntp.assemblycarts.api.mark.IMarker;
import com.tntp.assemblycarts.api.mark.MarkManager;
import com.tntp.minecraftmodapi.tileentity.TileEntityInventoryAPIiTNTPiston;
import com.tntp.minecraftmodapi.util.DirUtil;
import com.tntp.minecraftmodapi.util.ItemUtil;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileAssemblyPort extends TileEntityInventoryAPIiTNTPiston implements IAssemblyStructure, ISidedInventory, IMarker {
    private int[] cachedSlots;
    private int iteratingSlot;
    private MarkManager markManager;
    public boolean bypassInsertionSide;

    public TileAssemblyPort() {
        super(9);
        iteratingSlot = -1;
        markManager = new MarkManager(9);
        bypassInsertionSide = false;

    }

    public void setMarkedItemStack(int i, IMarkItem s) {
        markManager.setMarkedItem(i, s);
        markDirty();
    }

    public IMarkItem getMarkedItemStack(int i) {
        return markManager.getMarkedItem(i);
    }

    public IMarkItem[] getMarkedItemStacks() {
        return markManager.getAllMarked();
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (worldObj != null && !worldObj.isRemote) {
            // importFromManager();
            int outputSide = this.getBlockMetadata();
            int[] off = DirUtil.OFFSETS[outputSide];
            IInventory inv = detectContainer(xCoord + off[0], yCoord + off[1], zCoord + off[2], outputSide ^ 1);
            if (inv != null) {
                nextSlot();
                exportToContainer(inv, outputSide ^ 1);
            }
        }
    }

//    private void importFromManager() {
//        TileAssemblyManager manager = getManager();
//        if (manager == null)
//            return;
//        bypassInsertionSide = true;
//        manager.supplyToPort(this);
//        bypassInsertionSide = false;
//    }

    private void exportToContainer(IInventory inv, int side) {
        ItemStack stackInSlot = getStackInSlot(iteratingSlot);
        if (stackInSlot == null)
            return;

        ItemStack sup = stackInSlot.splitStack(stackInSlot.stackSize);

        ItemUtil.addToInventory(sup, inv, cachedSlots, side);

        if (sup.stackSize > 0) {// supply failed, add the stack back
            stackInSlot.stackSize++;
        } else {
            // supply succeeded, apply change to inventory
            if (stackInSlot.stackSize == 0)
                stackInSlot = null;
            setInventorySlotContents(iteratingSlot, stackInSlot);
            markDirty();
        }
    }

    private void nextSlot() {
        if (iteratingSlot < 0 || iteratingSlot >= getSizeInventory() - 1)
            iteratingSlot = 0;
        else
            iteratingSlot++;
    }

    /**
     * Detect container. Side is the the side of the CONTAINER
     */
    private IInventory detectContainer(int x, int y, int z, int side) {
        TileEntity t = worldObj.getTileEntity(x, y, z);
        if (t instanceof IInventory) {
            int[] slots;
            if (t instanceof ISidedInventory) {
                slots = ((ISidedInventory) t).getAccessibleSlotsFromSide(side);
                if (slots == null || slots.length == 0)
                    return null;
            } else {
                slots = new int[((IInventory) t).getSizeInventory()];
                for (int i = 0; i < slots.length; i++) {
                    slots[i] = i;
                }
            }
            cachedSlots = slots;
            return (IInventory) t;
        }
        return null;
    }

    private TileAssemblyManager manager;

    @Override
    public TileAssemblyManager getManager() {
        if (manager != null && !manager.isValidInWorld())
            manager = null;
        return manager;
    }

    @Override
    public void setManager(TileAssemblyManager tile) {
        manager = tile;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("iterSlot", iteratingSlot);
        markManager.writeToNBT(tag);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        iteratingSlot = tag.getInteger("iterSlot");
        markManager.readFromNBT(tag);
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        if (!markManager.hasMark())
            return bypassInsertionSide;
        IMarkItem mark = markManager.getMarkedItem(slot);
        if (mark == null)
            return false;
        return stack != null && mark.matchesStack(stack);
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return bypassInsertionSide;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return side == getBlockMetadata();
    }

    @Override
    public MarkManager getMarkManager() {
        return markManager;
    }

}
