package com.tntp.minecraftmodapi.block;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public interface IBlockRegisterFactory {
    /**
     * Set the creative tabs for the blocks
     * 
     * @param tab
     * @return
     */
    IBlockRegisterFactory creativeTabs(CreativeTabs tab);

    /**
     * Start the registration by prividing a block and its registration name
     * 
     * @param b
     * @return
     */
    IBlockRegister of(Block b, String name);
}
