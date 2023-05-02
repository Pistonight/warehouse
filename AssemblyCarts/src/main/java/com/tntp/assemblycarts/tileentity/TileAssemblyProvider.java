package com.tntp.assemblycarts.tileentity;

import com.tntp.assemblycarts.api.IProvider;
import com.tntp.assemblycarts.api.ProvideManager;
import com.tntp.assemblycarts.block.BlockDockingTrack;
import com.tntp.assemblycarts.entity.EntityMinecartAssemblyWorker;
import com.tntp.assemblycarts.init.ACBlocks;
import com.tntp.minecraftmodapi.tileentity.TileEntityInventoryAPIiTNTPiston;
import com.tntp.minecraftmodapi.util.DirUtil;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;

public class TileAssemblyProvider extends TileEntityInventoryAPIiTNTPiston implements IProvider, ICartStation {
    private ProvideManager provideManager;
    private int trackPowerTimeLeft;

    public TileAssemblyProvider() {
        super(0);
        provideManager = new ProvideManager(this, null);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (worldObj != null && !worldObj.isRemote) {
            updateCart();
        }
    }

    private void updateCart() {
        if (trackPowerTimeLeft > 0) {
            trackPowerTimeLeft--;
            if (trackPowerTimeLeft == 0) {
                setTrackPowered(false);
            }
        } else {
            EntityMinecart cart = getDockedCart();
            if (cart != null) {
                if (!(cart instanceof EntityMinecartAssemblyWorker)) {
                    powerTrack(30);
                } else {
                    EntityMinecartAssemblyWorker c = (EntityMinecartAssemblyWorker) cart;
                    if (c.getRequestManager().isFulfilled()) {
                        // Cart is ready to go
                        powerTrack(30);
                    } else if (!provideToCart(c)) {
                        powerTrack(30);
                    }
                }
            }
        }
    }

    /**
     * Provide to the cart
     * 
     * @param cart
     * @return true if the provide is successful
     */
    public boolean provideToCart(EntityMinecartAssemblyWorker cart) {
        provideManager.setProvideTarget(null);
        for (int dir : DirUtil.ALL_DIR) {
            int[] off = DirUtil.OFFSETS[dir ^ 1];
            if (detectContainer(xCoord + off[0], yCoord + off[1], zCoord + off[2], dir)) {
                if (provideManager.tryProvide(cart.getRequestManager(), dir))
                    return true;
            }
        }
        return false;

    }

    /**
     * Detect container. Side is the the side of the CONTAINER
     */
    public boolean detectContainer(int x, int y, int z, int side) {
        TileEntity t = worldObj.getTileEntity(x, y, z);
        if (t instanceof IInventory) {
            IInventory inv = (IInventory) t;
            int[] slots;
            // copied from TileEntityHopper to detect large chests
            if (inv instanceof TileEntityChest) {
                Block block = worldObj.getBlock(x, y, z);

                if (block instanceof BlockChest) {
                    inv = ((BlockChest) block).func_149951_m(worldObj, x, y, z);
                }
            }
            if (inv instanceof ISidedInventory) {
                slots = ((ISidedInventory) t).getAccessibleSlotsFromSide(side);
            } else {
                slots = new int[inv.getSizeInventory()];
                for (int i = 0; i < slots.length; i++) {
                    slots[i] = i;
                }
            }
            provideManager.setProvidingInventory(inv);
            provideManager.setProvidingSlots(slots);
            return true;
        }
        return false;
    }

    @Override
    public ProvideManager getProvideManager() {
        return provideManager;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        provideManager.writeToNBT(tag);
        tag.setInteger("trackPowerTimeLeft", trackPowerTimeLeft);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        provideManager.readFromNBT(tag);
        trackPowerTimeLeft = tag.getInteger("trackPowerTimeLeft");
    }

    private void powerTrack(int time) {
        trackPowerTimeLeft = time;
        setTrackPowered(true);
    }

    public void setTrackPowered(boolean powered) {
        int[] off = DirUtil.OFFSETS[getBlockMetadata()];
        int x = xCoord + off[0];
        int y = yCoord + off[1];
        int z = zCoord + off[2];
        Block b = worldObj.getBlock(x, y, z);
        if (b == ACBlocks.docking_track) {
            BlockDockingTrack.setPowered(worldObj, x, y, z, powered);
        }
    }

    public EntityMinecart getDockedCart() {
        int[] off = DirUtil.OFFSETS[getBlockMetadata()];
        int x = xCoord + off[0];
        int y = yCoord + off[1];
        int z = zCoord + off[2];
        TileEntity tile = worldObj.getTileEntity(x, y, z);
        if (tile instanceof TileDockingTrack) {
            return ((TileDockingTrack) tile).getDockedCart();
        }
        return null;
    }

    @Override
    public boolean canDock(EntityMinecart cart, int dockingSide) {
        return dockingSide == getBlockMetadata() && (cart instanceof EntityMinecartAssemblyWorker);
    }

}
