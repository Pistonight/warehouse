package com.tntp.minecraftmodapi.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IRecipeRegisterFactory {
    default IRecipeRegister ofCrafting(Block output) {
        return ofCrafting(new ItemStack(output));
    }

    default IRecipeRegister ofCrafting(Item output) {
        return ofCrafting(new ItemStack(output));
    }

    IRecipeRegister ofCrafting(ItemStack output);

    default ISmeltingRegister ofSmelting(Block output) {
        return ofSmelting(new ItemStack(output));
    }

    default ISmeltingRegister ofSmelting(Item output) {
        return ofSmelting(new ItemStack(output));
    }

    ISmeltingRegister ofSmelting(ItemStack output);
}
