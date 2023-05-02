package com.tntp.assemblycarts.tileentity;

import com.tntp.minecraftmodapi.tileentity.TileEntityAPIiTNTPiston;
import com.tntp.minecraftmodapi.util.DirUtil;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileDockingTrack extends TileEntityAPIiTNTPiston {
    private static final int UPDATE = 20;
    private boolean reversed;
    private boolean occupied;
    private EntityMinecart dockedCart;
    private byte needToUpdateReverse;

    public TileDockingTrack() {
    }

    @Override
    public void updateEntity() {
        if (dockedCart != null && dockedCart.isDead) {
            dockedCart = null;
        }
        if (worldObj != null && !worldObj.isRemote) {
            if (needToUpdateReverse > 0) {
                needToUpdateReverse--;
                if (needToUpdateReverse <= 0)
                    setReversed(reversed);

            }
            if (dockedCart == null || dockedCart.isDead) {
                dockedCart = null;
                occupied = false;
            }
        }
    }

    public boolean isReversed() {
        return reversed;
    }

    public void setReversed(boolean reversed) {
        this.reversed = reversed;
        if (worldObj != null && !worldObj.isRemote)
            this.worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), ACTEEvents.REVERSE_TRACK.ordinal(), reversed ? 1 : 0);
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setBoolean("reversed", reversed);
        tag.setBoolean("occupied", occupied);
        // tag.setBoolean("hasCart", hasCartInThisTick);
    }

    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        reversed = tag.getBoolean("reversed");
        occupied = tag.getBoolean("occupied");
        needToUpdateReverse = UPDATE;
    }

    public boolean canDock(EntityMinecart cart) {
        if (worldObj == null)
            return false;
        for (int d : DirUtil.ALL_DIR) {
            int[] off = DirUtil.OFFSETS[d];
            TileEntity tile = worldObj.getTileEntity(xCoord + off[0], yCoord + off[1], zCoord + off[2]);
            if (tile instanceof ICartStation) {
                return ((ICartStation) tile).canDock(cart, d ^ 1);
            }
        }
        return false;
    }

    public EntityMinecart getDockedCart() {
        return dockedCart;
    }

    public void setDockedCart(EntityMinecart dockedCart) {
        this.dockedCart = dockedCart;
    }

    @Override
    public boolean receiveClientEvent(int event, int param) {
        if (super.receiveClientEvent(event, param))
            return true;
        ACTEEvents e = ACTEEvents.getEventSafe(event);
        if (e == ACTEEvents.REVERSE_TRACK) {
            reversed = param == 1;
            worldObj.markBlockRangeForRenderUpdate(xCoord, yCoord, zCoord, xCoord, yCoord, zCoord);
            return true;
        }
        return false;
    }
}
