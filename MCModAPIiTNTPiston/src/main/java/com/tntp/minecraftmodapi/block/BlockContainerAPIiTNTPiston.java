package com.tntp.minecraftmodapi.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;

public abstract class BlockContainerAPIiTNTPiston extends BlockBehaviorAPIiTNTPiston implements ITileEntityProvider {

    public BlockContainerAPIiTNTPiston(Material mat, float hardness, float resistance) {
        super(mat, hardness, resistance);
        this.isBlockContainer = true;
    }

    @Override
    public void preBlockRegister(IBlockRegister reg) {
        reg.behave(DefaultTileEntityBehavior.INSTANCE);
    }

}
