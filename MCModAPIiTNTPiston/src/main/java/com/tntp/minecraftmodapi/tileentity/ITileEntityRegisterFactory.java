package com.tntp.minecraftmodapi.tileentity;

import net.minecraft.tileentity.TileEntity;

public interface ITileEntityRegisterFactory {
    ITileEntityRegister ofTE(Class<? extends TileEntity> teClass);
}
