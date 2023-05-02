package com.tntp.minecraftmodapi.recipe;

import com.tntp.minecraftmodapi.IRegister;

public interface IRecipeRegister extends IRegister {
    public IRecipeRegister pattern(String pattern);

    public IRecipeRegister shapeless();

    public IRecipeRegister input(Object... objects);
}
