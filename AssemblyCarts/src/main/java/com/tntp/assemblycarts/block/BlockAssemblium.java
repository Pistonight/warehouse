package com.tntp.assemblycarts.block;

import com.tntp.assemblycarts.api.Assemblium;
import com.tntp.minecraftmodapi.block.BlockAPIiTNTPiston;

public class BlockAssemblium extends BlockAPIiTNTPiston {

    public BlockAssemblium() {
        super(Assemblium.BLOCK_MATERIAL, 5.0f, 10.0f);
        this.setHarvestLevel("pickaxe", 1);
    }

}
