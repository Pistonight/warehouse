package com.tntp.assemblycarts.core;

import com.tntp.assemblycarts.gui.ACEnumGui;
import com.tntp.assemblycarts.init.ACEvents;
import com.tntp.assemblycarts.init.ACRender;
import com.tntp.minecraftmodapi.gui.EnumGuiInjector;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends Proxy {
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        ACRender.register();
        AssemblyCartsMod.log.info("Registering Guis Client");
        EnumGuiInjector.injectGui("com.tntp." + AssemblyCartsMod.MODID + ".gui.Gui", ACEnumGui.values());
    }

    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        ACEvents.loadClientEvents();

    }

}
