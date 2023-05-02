package com.tntp.assemblycarts.init;

import com.tntp.assemblycarts.core.AssemblyCartsMod;
import com.tntp.minecraftmodapi.APIiTNTPiston;
import com.tntp.minecraftmodapi.compat.ICompatRegisterFactory;

/**
 * Core class for loading compatibilities
 * 
 * @author iTNTPiston
 *
 */
public class ACCompats {
    public static void loadCompats(boolean clientSide) {
        ICompatRegisterFactory reg = APIiTNTPiston.newCompatRegister().packageName("com.tntp." + AssemblyCartsMod.MODID + ".compat");
        reg.of("Railcraft").name("CompatRailcraft").register();

        reg.loader().loadAll(clientSide);
    }
}
