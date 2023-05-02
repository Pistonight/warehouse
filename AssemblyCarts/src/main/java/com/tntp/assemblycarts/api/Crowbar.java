package com.tntp.assemblycarts.api;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class Crowbar {

    public static boolean isCrowbar(ItemStack stack) {
        if (stack != null) {
            Item i = stack.getItem();
            if (i instanceof ItemTool) {
                return i.getToolClasses(stack).contains("crowbar");
            }
        }
        return false;
    }
}
