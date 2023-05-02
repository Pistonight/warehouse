package com.tntp.assemblycarts.gui.container;

import com.tntp.assemblycarts.api.AssemblyProcess;
import com.tntp.assemblycarts.api.mark.IMarkItem;
import com.tntp.assemblycarts.gui.SContainer;
import com.tntp.assemblycarts.gui.SlotDecorative;
import com.tntp.assemblycarts.item.tag.TagProcessBook;
import com.tntp.minecraftmodapi.util.ItemUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerProcessBook extends SContainer {
    private AssemblyProcess process;
    private InventoryPlayer playerInv;

    public ContainerProcessBook(IInventory playerInventory, Object proc) {
        super(playerInventory, 0, null, 8, 140);
        process = (AssemblyProcess) proc;
        playerInv = (InventoryPlayer) playerInventory;
    }

    public void setupPlayerInventory(IInventory playerInventory, int playerSlotsX, int playerSlotsY) {
        for (int j = 0; j < 9; j++) {
            Slot s;
            if (j == ((InventoryPlayer) playerInventory).currentItem) {
                s = new SlotDecorative(playerInventory, j, playerSlotsX + j * 18, playerSlotsY + 58);
            } else
                s = new Slot(playerInventory, j, playerSlotsX + j * 18, playerSlotsY + 58);
            this.addSlotToContainer(s);
        }
        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(playerInventory, j + 9 + k * 9, playerSlotsX + j * 18, playerSlotsY + k * 18));
            }
        }
    }

    public AssemblyProcess getProcess() {
        return process;
    }

    @Override
    public void setupMachineSlots(IInventory machine) {

    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }

    public void processSlotClick(int slotID, int mouseButton) {
        // both server and client
        ItemStack current = playerInv.getItemStack();
        if (slotID == 0) {
            IMarkItem mark = this.processMarkSlotClick(mouseButton, current, process.getMainOutput());
            process.setMainOutput(mark);
        } else if (slotID <= 18) {
            slotID--;// match list id
            IMarkItem mark = this.processMarkSlotClick(mouseButton, current, process.getInput(slotID));
            process.setInput(slotID, mark);
        } else if (slotID <= 36) {
            slotID -= 19;// match list id
            IMarkItem mark = this.processMarkSlotClick(mouseButton, current, process.getOtherOutput(slotID));
            process.setOtherOutput(slotID, mark);
        }
    }

    /**
     * Called when the container is closed.
     */
    @Override
    public void onContainerClosed(EntityPlayer player) {
        ItemStack current = player.inventory.getCurrentItem();
        if (current != null) {
            TagProcessBook tag = new TagProcessBook();
            tag.process = process;
            ItemUtil.setItemTagTo(current, tag);
            player.inventory.setInventorySlotContents(player.inventory.currentItem, current);
        }
        super.onContainerClosed(player);
    }

}
