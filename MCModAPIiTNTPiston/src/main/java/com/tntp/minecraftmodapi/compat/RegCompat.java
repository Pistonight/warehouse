package com.tntp.minecraftmodapi.compat;

import java.util.HashMap;
import java.util.Map.Entry;

import com.tntp.minecraftmodapi.APIiTNTPiston;
import com.tntp.minecraftmodapi.SuperRegister;

import cpw.mods.fml.common.Loader;

public class RegCompat extends SuperRegister implements ICompatRegisterFactory, ICompatLoader {
    private String packageName;
    private HashMap<String, String> map = new HashMap<>();

    public RegCompat() {
        APIiTNTPiston.log.info("Registering Compats for " + modid);
    }

    @Override
    public ICompatRegister of(String modid) {
        return new Reg(modid);
    }

    private class Reg implements ICompatRegister {
        private String mod;
        private String clazz;

        private Reg(String modid) {
            mod = modid;
        }

        @Override
        public void register() {
            map.put(mod, packageName + "." + clazz);
            APIiTNTPiston.log.info("[Compat Registry] >> " + mod + " -> " + clazz);
        }

        @Override
        public ICompatRegister name(String clazz) {
            this.clazz = clazz;
            return this;
        }

    }

    @Override
    public void loadAll(boolean client) {
        for (Entry<String, String> compat : map.entrySet()) {
            if (Loader.isModLoaded(compat.getKey())) {
                try {
                    ICompat comp = (ICompat) Class.forName(compat.getValue()).newInstance();
                    APIiTNTPiston.log.info("[Compat Loader]Loading " + compat.getKey() + " Compat");
                    comp.loadCommon();
                    if (client) {
                        APIiTNTPiston.log.info("[Compat Loader]Loading " + compat.getKey() + " Compat Client");
                        comp.loadClient();
                    }
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    APIiTNTPiston.log.error("[Compat Loader]Fail to load " + compat.getKey() + " Compat");
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public ICompatLoader loader() {
        return this;
    }

    @Override
    public ICompatRegisterFactory packageName(String pack) {
        packageName = pack;
        return this;
    }

}
