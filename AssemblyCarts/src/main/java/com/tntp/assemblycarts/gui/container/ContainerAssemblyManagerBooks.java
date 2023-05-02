package com.tntp.assemblycarts.gui.container;

import com.tntp.assemblycarts.gui.SContainer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerAssemblyManagerBooks extends SContainer {
    private IInventory machine;

    public ContainerAssemblyManagerBooks(IInventory playerInventory, Object machine) {
        super(playerInventory, 9, (IInventory) machine, 8, 50);
        this.machine = (IInventory) machine;
    }

    @Override
    public void setupMachineSlots(IInventory machine) {
        for (int j = 0; j < 9; j++) {
            Slot bookSlot = new Slot(machine, j, 8 + j * 18, 18);
            this.addSlotToContainer(bookSlot);
        }

    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return machine.isUseableByPlayer(player);
    }

}
