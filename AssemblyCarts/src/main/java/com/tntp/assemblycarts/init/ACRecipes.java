package com.tntp.assemblycarts.init;

import com.tntp.minecraftmodapi.APIiTNTPiston;
import com.tntp.minecraftmodapi.recipe.IRecipeRegisterFactory;

import cpw.mods.fml.common.Loader;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ACRecipes {
    public static void registerRecipes() {

        IRecipeRegisterFactory reg = APIiTNTPiston.newRecipeRegister();

        reg.ofCrafting(ACBlocks.assemblium_block).pattern("AAA,AAA,AAA").input("ingotAssemblium").register();
        reg.ofCrafting(ACBlocks.assembly_worker).pattern("ABA,CDC,AAA").input("plateAssemblium", ACItems.process_book, "dustGlowstone", "blockGlass").register();
        reg.ofCrafting(new ItemStack(ACBlocks.assembly_frame, 4)).pattern("ABA,BCB,ABA").input("plateAssemblium", "dyeYellow", "blockAssemblium").register();
        reg.ofCrafting(ACBlocks.assembly_manager).pattern("ABA,BCB,ABA").input("blockGlass", ACBlocks.assembly_frame, ACItems.process_book).register();
        reg.ofCrafting(ACBlocks.assembly_port).shapeless().input(ACBlocks.assembly_frame, Blocks.piston).register();
        reg.ofCrafting(ACBlocks.assembly_requester).shapeless().input(ACBlocks.assembly_frame, Blocks.hopper).register();
        reg.ofCrafting(ACBlocks.assembly_requester_sticky).shapeless().input(ACBlocks.assembly_requester, "slimeball").register();
        reg.ofCrafting(ACBlocks.assembly_provider).pattern("ABA,A A,AAA").input("plateAssemblium", Blocks.piston).register();
        reg.ofCrafting(ACBlocks.detector_assembly).pattern("AAA,ABA,AAA").input("plateAssemblium", Blocks.stone_pressure_plate).register();

        if (!Loader.isModLoaded("Railcraft")) {
            reg.ofCrafting(ACBlocks.docking_track).pattern("A A,ABA,ACA").input("ingotIron", "stickWood", "plateAssemblium").register();
        }

        reg.ofCrafting(new ItemStack(ACItems.green_quartz, 4)).pattern("ABA,BCB,ABA").input("dyeGreen", "gemQuartz", "gemEmerald").register();
        reg.ofCrafting(new ItemStack(ACItems.ingot_assemblium, 4)).pattern("ABA,B B,ABA").input(ACItems.green_quartz, "ingotIron").register();
        reg.ofCrafting(ACItems.plate_assemblium).pattern("AA").input("ingotAssemblium").register();
        reg.ofCrafting(ACItems.assembly_worker_cart).shapeless().input(ACBlocks.assembly_worker, Items.minecart).register();
        reg.ofCrafting(ACItems.crowbar_assemblium).pattern(" AB,ABA,BA ").input("gemQuartz", "ingotAssemblium").register();
        reg.ofCrafting(ACItems.process_book).shapeless().input(Items.book, "plateAssemblium").register();
    }
}
