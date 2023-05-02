package com.tntp.assemblycarts.gui.container;

import com.tntp.assemblycarts.api.AssemblyProcess;
import com.tntp.assemblycarts.api.RequestManager;
import com.tntp.assemblycarts.gui.SContainerRequestManager;
import com.tntp.assemblycarts.gui.SlotDecorative;
import com.tntp.assemblycarts.tileentity.TileAssemblyManager;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerAssemblyManager extends SContainerRequestManager {

    private TileAssemblyManager tile;
    public int selectedProcessID;
    private int processMultiplier;

    public ContainerAssemblyManager(IInventory playerInventory, Object machine) {
        super(playerInventory, 9, (IInventory) machine, 8, 198);
        tile = (TileAssemblyManager) machine;
        processMultiplier = 1;
        selectedProcessID = -1;
    }

    public void setupPlayerInventory(IInventory playerInventory, int playerSlotsX, int playerSlotsY) {

    }

    @Override
    public RequestManager getRequestManager() {
        return tile.getRequestManager();
    }

    @Override
    public void setupMachineSlots(IInventory machine) {
        for (int j = 0; j < 9; j++) {
            Slot bookSlot = new SlotDecorative(machine, j, 8 + j * 18, 129);
            this.addSlotToContainer(bookSlot);
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tile.isUseableByPlayer(player);
    }

    public TileAssemblyManager getTile() {
        return tile;
    }

    public AssemblyProcess getSelectedProcess() {
        if (selectedProcessID < 0)
            return null;
        return tile.getProcessBySlot(selectedProcessID);
    }

    @SideOnly(Side.CLIENT)
    public void selectNextProcess() {
        for (int i = selectedProcessID + 1; i < 9; i++) {
            if (tile.getProcessBySlot(i) != null) {
                selectedProcessID = i;
                return;
            }
        }
        for (int i = 0; i < selectedProcessID; i++) {
            if (tile.getProcessBySlot(i) != null) {
                selectedProcessID = i;
                return;
            }
        }
        selectedProcessID = -1;
    }

    @SideOnly(Side.CLIENT)
    public void selectProcessBySlot(int slot) {
        if (tile.getProcessBySlot(slot) != null)
            selectedProcessID = slot;
    }

    public void setProcessMultiplier(int m) {
        if (m < 1)
            m = 1;
        processMultiplier = m;
    }

    public int getProcessMultiplier() {
        return processMultiplier;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        return null;
    }

    @Override
    public void processSlotClick(int slot, int button) {
        selectedProcessID = slot;
        processMultiplier = button;

        if (tile.getRequestManager().isFulfilled()) {
            tile.startProcess(selectedProcessID, processMultiplier);
        } else {
            tile.cancelProcess();
        }
    }

}
