package com.tntp.assemblycarts.tileentity;

import com.tntp.assemblycarts.api.AssemblyProcess;
import com.tntp.assemblycarts.api.IProvider;
import com.tntp.assemblycarts.api.IRequester;
import com.tntp.assemblycarts.api.ProvideManager;
import com.tntp.assemblycarts.api.RequestManager;
import com.tntp.assemblycarts.api.mark.IMarker;
import com.tntp.assemblycarts.api.mark.MarkManager;
import com.tntp.assemblycarts.block.BlockDockingTrack;
import com.tntp.assemblycarts.entity.EntityMinecartAssemblyWorker;
import com.tntp.assemblycarts.init.ACBlocks;
import com.tntp.assemblycarts.item.tag.TagProcessBook;
import com.tntp.minecraftmodapi.tileentity.TileEntityInventoryAPIiTNTPiston;
import com.tntp.minecraftmodapi.util.DirUtil;
import com.tntp.minecraftmodapi.util.ItemUtil;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileAssemblyRequester extends TileEntityInventoryAPIiTNTPiston implements IAssemblyStructure, IRequester, IProvider, IMarker, ICartStation {

    private int trackPowerTimeLeft;
    /**
     * Stop untargetted carts if itself is untargetted
     */
    private boolean sticky;

    public TileAssemblyRequester() {
        this(false);
    }

    public TileAssemblyRequester(boolean sticky) {
        super(19);// 0 for book
        requestManager = new RequestManager(this, 1, 18);
        int[] slots = new int[18];
        for (int i = 0; i < slots.length; i++)
            slots[i] = i + 1;
        provideManager = new ProvideManager(this, slots);
        markManager = new MarkManager(9);
        this.sticky = sticky;

    }

    private void supplyToManager() {
        TileAssemblyManager manager = getManager();
        if (manager == null)
            return;
        manager.supplyFromRequester(this);
    }

    private void updateBook() {
        if (getManager() == null && requestManager.getCraftingTarget() == null) {
            ItemStack stack = getStackInSlot(0);
            if (stack != null) {
                TagProcessBook tag = ItemUtil.getItemTag(stack, new TagProcessBook());
                AssemblyProcess process = tag.process;
                if (process.getMainOutput() != null) {
                    requestManager.initRequestWithProcess(process, 1);
                    markDirty();
                }
            }
        }
    }

    private void updateCart() {
        if (trackPowerTimeLeft > 0) {
            trackPowerTimeLeft--;
            if (trackPowerTimeLeft == 0) {
                setTrackPowered(false);
            }
        } else {
            EntityMinecart c = getDockedCart();
            if (c != null) {
                if (!(c instanceof EntityMinecartAssemblyWorker)) {
                    powerTrack(30);
                } else {
                    EntityMinecartAssemblyWorker cart = (EntityMinecartAssemblyWorker) c;
                    if (requestManager.isFulfilled()) {

                        if (!sticky) {
                            // Let all passed carts go in this case
                            powerTrack(30);
                        } else {
                            // If the cart has a target, let go
                            if (cart.getRequestManager().getCraftingTarget() != null) {
                                powerTrack(30);
                            }
                        }
                    } else if (cart.getProvideManager().canProvideTo(requestManager)) {
                        if (cart.getProvideManager().getProvideTarget() == null) {
                            // If the cart doesn't have a target, set the target
                            cart.setTarget(requestManager.getCraftingTarget(), requestManager.getNeed());
                        } else if (!cart.getProvideManager().tryProvide(requestManager, -1)) {
                            // Let the cart provide. If cannot provide then let the cart go.
                            powerTrack(30);
                        }
                    } else {
                        powerTrack(30);
                    }
                }
            }
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (worldObj != null && !worldObj.isRemote) {
            supplyToManager();
            updateBook();
            updateCart();

        }
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

    private TileAssemblyManager manager;

    @Override
    public TileAssemblyManager getManager() {
        if (manager != null && !manager.isValidInWorld())
            manager = null;
        return manager;
    }

    @Override
    public void setManager(TileAssemblyManager tile) {
        manager = tile;
    }

    private RequestManager requestManager;

    @Override
    public RequestManager getRequestManager() {
        return requestManager;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        requestManager.writeToNBT(tag);
        provideManager.writeToNBT(tag);
        markManager.writeToNBT(tag);
        tag.setInteger("trackPowerTimeLeft", trackPowerTimeLeft);
        tag.setBoolean("sticky", sticky);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        requestManager.readFromNBT(tag);
        provideManager.readFromNBT(tag);
        markManager.readFromNBT(tag);
        trackPowerTimeLeft = tag.getInteger("trackPowerTimeLeft");
        sticky = tag.getBoolean("sticky");
    }

    private ProvideManager provideManager;

    @Override
    public ProvideManager getProvideManager() {
        return provideManager;
    }

    private MarkManager markManager;

    @Override
    public MarkManager getMarkManager() {
        return markManager;
    }

    @Override
    public boolean canDock(EntityMinecart cart, int dockingSide) {
        return dockingSide == getBlockMetadata() && (cart instanceof EntityMinecartAssemblyWorker);
    }

}
