package com.tntp.minecraftmodapi.entity;

import com.tntp.minecraftmodapi.IRegister;

public interface IEntityRegister extends IRegister {
    /**
     * Set the track range. Default 64
     * 
     * @param trackRange
     * @return
     */
    IEntityRegister trackRange(int trackRange);

    /**
     * Set update frequency, default 1 (most frequent)
     */
    IEntityRegister freq(int updateFrequency);

    /**
     * Send velocity updates
     * 
     * @return
     */
    IEntityRegister noUpdateV();

}
