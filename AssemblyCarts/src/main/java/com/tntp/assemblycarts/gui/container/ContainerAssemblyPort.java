package com.tntp.assemblycarts.gui.container;

import com.tntp.assemblycarts.api.mark.IMarkItem;
import com.tntp.assemblycarts.api.mark.MarkManager;
import com.tntp.assemblycarts.gui.SContainerMarkManager;
import com.tntp.assemblycarts.tileentity.TileAssemblyPort;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerAssemblyPort extends SContainerMarkManager {
    private TileAssemblyPort tile;
    private InventoryPlayer playerInv;

    public ContainerAssemblyPort(IInventory playerInventory, Object machine) {
        super(playerInventory, ((IInventory) machine).getSizeInventory(), (IInventory) machine, 8, 86);
        tile = (TileAssemblyPort) machine;
        playerInv = (InventoryPlayer) playerInventory;
    }

    public TileAssemblyPort getTile() {
        return tile;
    }

    @Override
    public void setupMachineSlots(IInventory machine) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.addSlotToContainer(new Slot(machine, i * 3 + j, 98 + j * 18, 18 + i * 18));
            }
        }

    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tile.isUseableByPlayer(player);
    }

    @Override
    public void processSlotClick(int slotID, int mouseButton) {
        if (slotID >= 0 && slotID < 9) {
            IMarkItem mark = this.processMarkSlotClick(mouseButton, playerInv.getItemStack(), tile.getMarkedItemStack(slotID));
            tile.setMarkedItemStack(slotID, mark);
        }
    }

    @Override
    public MarkManager getMarkManager() {
        return tile.getMarkManager();
    }

}
