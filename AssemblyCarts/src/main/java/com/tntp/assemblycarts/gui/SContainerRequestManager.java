package com.tntp.assemblycarts.gui;

import com.tntp.assemblycarts.api.IRequester;
import com.tntp.assemblycarts.network.ACNtwk;
import com.tntp.assemblycarts.network.MCGuiRequestManager;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;

public abstract class SContainerRequestManager extends SContainer implements IRequester {

    public SContainerRequestManager(IInventory playerInventory, int slots, IInventory machine, int playerSlotsX, int playerSlotsY) {
        super(playerInventory, slots, machine, playerSlotsX, playerSlotsY);
    }

    @Override
    public void addCraftingToCrafters(ICrafting ic) {
        super.addCraftingToCrafters(ic);
        if (ic instanceof EntityPlayerMP) {
            sendRequestManager((EntityPlayerMP) ic);
        }
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (getRequestManager().update) {
            for (int j = 0; j < this.crafters.size(); ++j) {
                ICrafting ic = (ICrafting) this.crafters.get(j);
                if (ic instanceof EntityPlayerMP) {
                    sendRequestManager((EntityPlayerMP) ic);
                }
            }
            getRequestManager().update = false;
        }
    }

    public void sendRequestManager(EntityPlayerMP player) {
        NBTTagCompound tag = new NBTTagCompound();
        getRequestManager().writeToNBT(tag);
        ACNtwk.sendTo(new MCGuiRequestManager(this.windowId, tag), player);
    }

}
