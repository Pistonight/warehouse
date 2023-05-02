package com.tntp.minecraftmodapi.compat;

public interface ICompatRegisterFactory {
    ICompatRegister of(String modid);

    ICompatRegisterFactory packageName(String pack);

    ICompatLoader loader();
}
