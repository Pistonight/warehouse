package com.tntp.assemblycarts.init;

import com.tntp.assemblycarts.core.AssemblyCartsMod;
import com.tntp.assemblycarts.entity.EntityMinecartAssemblyWorker;
import com.tntp.minecraftmodapi.entity.RegEntity;

public class ACEntities {
    public static void loadEntities() {
        AssemblyCartsMod.log.info("Registering Entities");
        RegEntity reg = new RegEntity();
        reg.of(EntityMinecartAssemblyWorker.class).register();
    }
}
