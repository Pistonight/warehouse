package com.tntp.minecraftmodapi.tileentity;

import com.tntp.minecraftmodapi.APIiTNTPiston;
import com.tntp.minecraftmodapi.SuperRegister;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.tileentity.TileEntity;

public class RegTileEntity extends SuperRegister implements ITileEntityRegisterFactory {
    public RegTileEntity() {
        APIiTNTPiston.log.info("Registering TileEntities for " + modid);
    }

    @Override
    public ITileEntityRegister ofTE(Class<? extends TileEntity> teClass) {
        return new Reg(teClass);
    }

    private class Reg implements ITileEntityRegister {
        private Class<? extends TileEntity> tile;

        private Reg(Class<? extends TileEntity> tileClass) {
            tile = tileClass;
        }

        @Override
        public void register() {
            String name = modid + "." + tile.getSimpleName();
            GameRegistry.registerTileEntity(tile, name);
            APIiTNTPiston.log.info("[TileEntity Registry] >> " + name);
        }

    }

}
