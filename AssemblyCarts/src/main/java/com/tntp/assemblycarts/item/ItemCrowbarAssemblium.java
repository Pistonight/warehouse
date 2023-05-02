package com.tntp.assemblycarts.item;

import java.util.Arrays;
import cpw.mods.fml.common.Optional;
import java.util.HashSet;

import com.tntp.assemblycarts.api.Assemblium;

import mods.railcraft.api.core.items.IToolCrowbar;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;

@Optional.Interface(iface = "IToolCrowbar", modid = "Railcraft")
public class ItemCrowbarAssemblium extends ItemTool implements IToolCrowbar {

    public ItemCrowbarAssemblium() {
        super(5, Assemblium.ASSEMBLIUM, new HashSet<Block>(Arrays.asList(new Block[] { Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail })));
        setHarvestLevel("crowbar", 2);
    }

    @Override
    public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player) {
        return true;
    }

    @Override
    @Optional.Method(modid = "Railcraft")
    public boolean canBoost(EntityPlayer player, ItemStack arg1, EntityMinecart arg2) {
        return !player.isSneaking();
    }

    @Override
    @Optional.Method(modid = "Railcraft")
    public boolean canLink(EntityPlayer player, ItemStack arg1, EntityMinecart arg2) {
        return player.isSneaking();
    }

    @Override
    @Optional.Method(modid = "Railcraft")
    public boolean canWhack(EntityPlayer player, ItemStack arg1, int arg2, int arg3, int arg4) {
        return !player.isSneaking();
    }

    @Override
    @Optional.Method(modid = "Railcraft")
    public void onBoost(EntityPlayer arg0, ItemStack arg1, EntityMinecart arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    @Optional.Method(modid = "Railcraft")
    public void onLink(EntityPlayer arg0, ItemStack arg1, EntityMinecart arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onWhack(EntityPlayer arg0, ItemStack arg1, int arg2, int arg3, int arg4) {
        // TODO Auto-generated method stub

    }
}
