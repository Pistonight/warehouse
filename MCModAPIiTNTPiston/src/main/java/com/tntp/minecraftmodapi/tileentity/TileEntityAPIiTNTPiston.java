package com.tntp.minecraftmodapi.tileentity;

import net.minecraft.tileentity.TileEntity;

/**
 * Super class of (hopefully) all tileentities
 * 
 * @author iTNTPiston
 *
 */
public class TileEntityAPIiTNTPiston extends TileEntity {

    public boolean isValidInWorld() {
        return this.hasWorldObj() && this.worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && !this.isInvalid();
    }

    /**
     * Called by breakBlock() before items are dropped, useful for setting
     * decorative slots to null
     */
    public void onBreakingContainer() {

    }
}
