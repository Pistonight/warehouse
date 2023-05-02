package com.tntp.assemblycarts.gui.container;

import com.tntp.assemblycarts.api.IProvider;
import com.tntp.assemblycarts.api.ProvideManager;
import com.tntp.assemblycarts.api.RequestManager;
import com.tntp.assemblycarts.entity.EntityMinecartAssemblyWorker;
import com.tntp.assemblycarts.gui.SContainerRequestManager;
import com.tntp.assemblycarts.gui.SlotDecorative;
import com.tntp.assemblycarts.network.ACNtwk;
import com.tntp.assemblycarts.network.MCGuiProvideManager;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;

public class ContainerMinecartAssemblyWorker extends SContainerRequestManager implements IProvider {
    private EntityMinecartAssemblyWorker cart;

    public ContainerMinecartAssemblyWorker(IInventory playerInventory, Object cart) {
        super(playerInventory, ((EntityMinecartAssemblyWorker) cart).getSizeInventory(), (IInventory) cart, 8, 140);
        this.cart = (EntityMinecartAssemblyWorker) cart;
    }

    public EntityMinecartAssemblyWorker getCart() {
        return cart;
    }

    public void sendProvideManager(EntityPlayerMP player) {
        NBTTagCompound tag = new NBTTagCompound();
        getProvideManager().writeToNBT(tag);
        ACNtwk.sendTo(new MCGuiProvideManager(this.windowId, tag), player);
    }

    @Override
    public void addCraftingToCrafters(ICrafting ic) {
        super.addCraftingToCrafters(ic);
        if (ic instanceof EntityPlayerMP) {
            sendProvideManager((EntityPlayerMP) ic);
        }
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (getProvideManager().update) {
            for (int j = 0; j < this.crafters.size(); ++j) {
                ICrafting ic = (ICrafting) this.crafters.get(j);
                if (ic instanceof EntityPlayerMP) {
                    sendProvideManager((EntityPlayerMP) ic);
                }
            }
            getProvideManager().update = false;
        }
    }

    @Override
    public void setupMachineSlots(IInventory machine) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new SlotDecorative(machine, i * 9 + j, 8 + j * 18, 90 + i * 18));
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return cart.isUseableByPlayer(player);
    }

    @Override
    public RequestManager getRequestManager() {
        return cart.getRequestManager();
    }

    @Override
    public ProvideManager getProvideManager() {
        return cart.getProvideManager();
    }

}
