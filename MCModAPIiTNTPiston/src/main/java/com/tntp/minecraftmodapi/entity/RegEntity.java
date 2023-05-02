package com.tntp.minecraftmodapi.entity;

import com.tntp.minecraftmodapi.APIiTNTPiston;
import com.tntp.minecraftmodapi.SuperRegister;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;

public class RegEntity extends SuperRegister implements IEntityRegisterFactory {
    private int id;

    public RegEntity() {
        id = 0;
        APIiTNTPiston.log.info("Registering Entities for " + modid);
    }

    @Override
    public IEntityRegister of(Class<? extends Entity> clazz) {
        APIiTNTPiston.log.info("[Entity Registry] Entity >> " + clazz.getCanonicalName());
        return new Reg<>(clazz);
    }

    private class Reg<E extends Entity> implements IEntityRegister {
        Class<E> clazz;
        private int trackRange = 64;
        private int frequency = 1;
        private boolean updateV = true;

        Reg(Class<E> entityClass) {
            clazz = entityClass;
        }

        @Override
        public void register() {
            EntityRegistry.registerModEntity(clazz, modid + "." + clazz.getSimpleName(), id++, modid, trackRange, frequency, updateV);
            APIiTNTPiston.log.info("[Entity Registry] Registered");

        }

        @Override
        public IEntityRegister trackRange(int trackRange) {
            this.trackRange = trackRange;
            return this;
        }

        @Override
        public IEntityRegister freq(int updateFrequency) {
            frequency = updateFrequency;
            return this;
        }

        @Override
        public IEntityRegister noUpdateV() {
            updateV = false;
            return this;
        }

    }

}
