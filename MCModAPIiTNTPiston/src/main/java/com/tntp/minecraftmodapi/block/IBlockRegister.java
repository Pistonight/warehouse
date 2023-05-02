package com.tntp.minecraftmodapi.block;

import com.tntp.minecraftmodapi.IRegister;

import net.minecraft.item.ItemBlock;

public interface IBlockRegister extends IRegister {

    /**
     * Set the itemblock of this block
     * 
     * @param itemBlockClass
     * @return
     */
    IBlockRegister item(Class<? extends ItemBlock> itemBlockClass);

    /**
     * Set the texture name (Only when different from block name)
     * 
     * @param name
     * @return
     */
    IBlockRegister texture(String name);

    /**
     * Hide from creative tab
     * 
     * @return
     */
    IBlockRegister hide();

    /**
     * Add a behavior
     * 
     * @param behavior
     * @return
     */
    IBlockRegister behave(IBlockBehavior behavior);

}
