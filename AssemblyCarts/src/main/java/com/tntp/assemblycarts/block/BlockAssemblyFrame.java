package com.tntp.assemblycarts.block;

import com.tntp.assemblycarts.api.Assemblium;
import com.tntp.assemblycarts.tileentity.TileAssemblyFrame;
import com.tntp.minecraftmodapi.block.BlockBehaviorAPIiTNTPiston;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAssemblyFrame extends BlockBehaviorAPIiTNTPiston implements ITileEntityProvider {

    public BlockAssemblyFrame() {
        super(Assemblium.BLOCK_MATERIAL, 5.0f, 10.0f);
        this.setHarvestLevel("pickaxe", 1);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileAssemblyFrame();
    }

}
