package com.tntp.assemblycarts.block;

import com.tntp.assemblycarts.tileentity.TileAssemblyRequester;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAssemblyRequesterSticky extends BlockAssemblyRequester {

    public BlockAssemblyRequesterSticky() {
        super();
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileAssemblyRequester(true);
    }

}
