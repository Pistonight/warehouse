package com.tntp.minecraftmodapi.entity;

import net.minecraft.entity.Entity;

public interface IEntityRegisterFactory {
    IEntityRegister of(Class<? extends Entity> clazz);
}
