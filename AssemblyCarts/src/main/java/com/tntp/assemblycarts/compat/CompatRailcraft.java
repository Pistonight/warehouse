package com.tntp.assemblycarts.compat;

import com.tntp.assemblycarts.init.ACBlocks;
import com.tntp.assemblycarts.init.ACItems;
import com.tntp.minecraftmodapi.APIiTNTPiston;
import com.tntp.minecraftmodapi.compat.ICompat;
import com.tntp.minecraftmodapi.recipe.IRecipeRegisterFactory;

import mods.railcraft.api.crafting.RailcraftCraftingManager;
import mods.railcraft.common.items.ItemRail;
import mods.railcraft.common.items.ItemRailbed;
import mods.railcraft.common.items.RailcraftItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CompatRailcraft implements ICompat {

    @Override
    public void loadClient() {
    }

    @Override
    public void loadCommon() {
        // Load rolling machine recipes
        RailcraftCraftingManager.rollingMachine.addRecipe(new ItemStack(ACItems.plate_assemblium, 4), "AA", "AA", 'A', ACItems.ingot_assemblium);

        IRecipeRegisterFactory reg = APIiTNTPiston.newRecipeRegister();
        ItemStack railBed = RailcraftItem.railbed.getStack(ItemRailbed.EnumRailbed.WOOD);
        ItemStack rail = RailcraftItem.rail.getStack(ItemRail.EnumRail.STANDARD);
        reg.ofCrafting(new ItemStack(ACBlocks.docking_track, 16)).pattern("A A,ABA,ACA").input(rail, railBed, "plateAssemblium").register();
    }

}
