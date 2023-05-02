package com.tntp.minecraftmodapi.network;

import com.tntp.minecraftmodapi.IRegister;

import cpw.mods.fml.relauncher.Side;

public interface IMessageRegister extends IRegister {
    /**
     * Specify the side this message should be RECEIVED on
     * 
     * @param side
     */
    IMessageRegister side(Side side);
}
