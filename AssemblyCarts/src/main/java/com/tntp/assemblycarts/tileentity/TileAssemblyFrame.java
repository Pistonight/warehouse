package com.tntp.assemblycarts.tileentity;

import com.tntp.minecraftmodapi.tileentity.TileEntityAPIiTNTPiston;

public class TileAssemblyFrame extends TileEntityAPIiTNTPiston implements IAssemblyStructure {
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

}
