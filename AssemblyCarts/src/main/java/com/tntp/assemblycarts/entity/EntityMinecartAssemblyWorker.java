package com.tntp.assemblycarts.entity;

import java.util.List;

import com.tntp.assemblycarts.api.Crowbar;
import com.tntp.assemblycarts.api.IProvider;
import com.tntp.assemblycarts.api.IRequester;
import com.tntp.assemblycarts.api.ProvideManager;
import com.tntp.assemblycarts.api.RequestManager;
import com.tntp.assemblycarts.api.mark.IMarkItem;
import com.tntp.assemblycarts.core.AssemblyCartsMod;
import com.tntp.assemblycarts.gui.ACEnumGui;
import com.tntp.assemblycarts.init.ACBlocks;
import com.tntp.minecraftmodapi.gui.EnumGuiHandler;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecartContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMinecartAssemblyWorker extends EntityMinecartContainer implements IProvider, IRequester {

    private RequestManager requestManager;
    private ProvideManager provideManager;

    public EntityMinecartAssemblyWorker(World world) {
        super(world);
        initManagers();
    }

    public EntityMinecartAssemblyWorker(World world, double x, double y, double z) {
        super(world, x, y, z);
        initManagers();
    }

    private void initManagers() {
        requestManager = new RequestManager(this, 0, 17);
        int[] slots = new int[18];
        for (int i = 0; i < slots.length; i++)
            slots[i] = i;
        provideManager = new ProvideManager(this, slots);
    }

    public void setTarget(IMarkItem stack, List<IMarkItem> need) {
        requestManager.initRequestDirectly(stack, need);
        provideManager.setProvideTarget(stack);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (worldObj != null && !worldObj.isRemote) {
            if (requestManager.isFulfilled()) {
                boolean isEmpty = true;
                for (int i = 0; i < getSizeInventory(); i++) {
                    if (getStackInSlot(i) != null) {
                        isEmpty = false;
                        break;
                    }
                }
                if (isEmpty) {
                    provideManager.setProvideTarget(null);
                }
            }

        }
    }

    public int getDefaultDisplayTileData() {
        return 2;
    }

    @Override
    public Block func_145817_o() {
        return ACBlocks.assembly_worker;
    }

    @Override
    public void killMinecart(DamageSource p_94095_1_) {
        if (!worldObj.isRemote)
            super.killMinecart(p_94095_1_);
        this.func_145778_a(Item.getItemFromBlock(ACBlocks.assembly_worker), 1, 0.0F);
    }

    @Override
    public void setDead() {
        this.isDead = true;
    }

    @Override
    public int getSizeInventory() {
        return 18;
    }

    @Override
    public int getMinecartType() {
        return -1;
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        requestManager.writeToNBT(tag);
        provideManager.writeToNBT(tag);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        requestManager.readFromNBT(tag);
        provideManager.readFromNBT(tag);
    }

    @Override
    public RequestManager getRequestManager() {
        return requestManager;
    }

    @Override
    public ProvideManager getProvideManager() {
        return provideManager;
    }

    @Override
    public String getInventoryName() {
        return "item.assembly_worker_cart.name";
    }

    @Override
    public boolean interactFirst(EntityPlayer player) {
        if (!Crowbar.isCrowbar(player.getCurrentEquippedItem())) {
            if (!this.worldObj.isRemote) {
                EnumGuiHandler.openGuiEntity(ACEnumGui.MinecartAssemblyWorker, AssemblyCartsMod.MODID, player, worldObj, this.getEntityId());
            }
            return true;
        }
        return false;
    }

}
