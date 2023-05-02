package com.tntp.assemblycarts.tileentity;

import net.minecraft.entity.item.EntityMinecart;

public interface ICartStation {
    /**
     * 
     * @param cart
     * @param dockingSide side of THIS station
     * @return
     */
    public boolean canDock(EntityMinecart cart, int dockingSide);
}
