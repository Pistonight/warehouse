package com.tntp.assemblycarts.tileentity;

import java.util.List;

import com.tntp.assemblycarts.api.mark.IMarker;
import com.tntp.assemblycarts.api.mark.MarkManager;
import com.tntp.assemblycarts.entity.EntityMinecartAssemblyWorker;
import com.tntp.minecraftmodapi.tileentity.TileEntityInventoryAPIiTNTPiston;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

public class TileDetectorAssembly extends TileEntityInventoryAPIiTNTPiston implements IMarker {
    private static final float SENSITIVITY = 0.1f;
    private static final int DELAY = 10;
    private static final int UPDATE_INTERVAL = 5;
    private int powerTime = 0;
    private int updateTime = 0;

    private MarkManager markManager;

    public TileDetectorAssembly() {
        super(0);
        markManager = new MarkManager(9);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (worldObj != null && !worldObj.isRemote) {
            if (powerTime > 0) {
                powerTime--;
            } else if (updateTime <= 0) {
                updateCarts();
                updateTime = UPDATE_INTERVAL;
            } else {
                updateTime--;
            }
        }
    }

    private void updateCarts() {
        List<EntityMinecartAssemblyWorker> list = worldObj.getEntitiesWithinAABB(EntityMinecartAssemblyWorker.class,
                AxisAlignedBB.getBoundingBox(xCoord - SENSITIVITY, yCoord - 1 + SENSITIVITY, zCoord - SENSITIVITY, xCoord + 1 + SENSITIVITY, yCoord + 2 - SENSITIVITY, zCoord + 1 + SENSITIVITY));
        int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        boolean powered = (meta & 8) == 8;
        boolean newPowered = detectCarts(list);
        if (newPowered != powered) {
            if (newPowered) {
                meta = meta | 8;
                powerTime = DELAY;
            } else {
                meta = meta & 7;
            }
            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, meta, 2);
            worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, this.getBlockType());
        }
    }

    private boolean detectCarts(List<EntityMinecartAssemblyWorker> list) {
        if (list.isEmpty())
            return false;
        if (markManager.hasMark()) {
            for (EntityMinecartAssemblyWorker cart : list) {
                if (markManager.isMarked(cart.getProvideManager().getProvideTarget()) || markManager.isMarked(cart.getRequestManager().getCraftingTarget())) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("pwTime", powerTime);
        tag.setInteger("upTime", updateTime);
        markManager.writeToNBT(tag);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        powerTime = tag.getInteger("pwTime");
        updateTime = tag.getInteger("upTime");
        markManager.readFromNBT(tag);
    }

    @Override
    public MarkManager getMarkManager() {
        return markManager;
    }
}
