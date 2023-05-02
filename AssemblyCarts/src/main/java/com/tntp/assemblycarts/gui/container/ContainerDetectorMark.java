package com.tntp.assemblycarts.gui.container;

import com.tntp.assemblycarts.api.mark.IMarkItem;
import com.tntp.assemblycarts.api.mark.MarkManager;
import com.tntp.assemblycarts.gui.SContainerMarkManager;
import com.tntp.assemblycarts.tileentity.TileDetectorAssembly;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;

public class ContainerDetectorMark extends SContainerMarkManager {
    private TileDetectorAssembly tile;
    private InventoryPlayer playerInv;

    public ContainerDetectorMark(IInventory playerInventory, Object machine) {
        super(playerInventory, 0, (IInventory) machine, 8, 86);
        tile = (TileDetectorAssembly) machine;
        playerInv = (InventoryPlayer) playerInventory;
    }

    @Override
    public MarkManager getMarkManager() {
        return tile.getMarkManager();
    }

    @Override
    public void setupMachineSlots(IInventory machine) {

    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tile.isUseableByPlayer(player);
    }

    @Override
    public void processSlotClick(int slotID, int mouseButton) {
        if (slotID >= 0 && slotID < 9) {
            IMarkItem mark = this.processMarkSlotClick(mouseButton, playerInv.getItemStack(), tile.getMarkManager().getMarkedItem(slotID));
            tile.getMarkManager().setMarkedItem(slotID, mark);
        }
    }
}
