package com.tntp.assemblycarts.gui.container;

import com.tntp.assemblycarts.api.RequestManager;
import com.tntp.assemblycarts.gui.SContainerRequestManager;
import com.tntp.assemblycarts.tileentity.TileAssemblyRequester;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerAssemblyRequester extends SContainerRequestManager {
    private TileAssemblyRequester tile;

    public ContainerAssemblyRequester(IInventory playerInventory, Object machine) {
        super(playerInventory, ((IInventory) machine).getSizeInventory(), (IInventory) machine, 8, 140);
        tile = (TileAssemblyRequester) machine;
    }

    public TileAssemblyRequester getTile() {
        return tile;
    }

    @Override
    public void setupMachineSlots(IInventory machine) {
        Slot bookSlot = new Slot(machine, 0, 26, 67);
        this.addSlotToContainer(bookSlot);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(machine, 1 + i * 9 + j, 8 + j * 18, 90 + i * 18));
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tile.isUseableByPlayer(player);
    }

    @Override
    public RequestManager getRequestManager() {
        return tile.getRequestManager();
    }

}
