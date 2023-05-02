package com.tntp.minecraftmodapi;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;

public abstract class SuperRegister {
    protected String modName;
    protected String modid;

    public SuperRegister() {
        ModContainer mod = Loader.instance().activeModContainer();
        modid = mod.getModId();
        modName = mod.getName();
    }
}
