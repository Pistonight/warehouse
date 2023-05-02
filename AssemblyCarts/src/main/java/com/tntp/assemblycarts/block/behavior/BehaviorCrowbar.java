package com.tntp.assemblycarts.block.behavior;

import com.tntp.assemblycarts.api.Crowbar;
import com.tntp.assemblycarts.core.AssemblyCartsMod;
import com.tntp.assemblycarts.gui.ACEnumGui;
import com.tntp.assemblycarts.init.ACBlocks;
import com.tntp.assemblycarts.tileentity.TileDockingTrack;
import com.tntp.minecraftmodapi.Turnary;
import com.tntp.minecraftmodapi.block.IBlockBehavior;
import com.tntp.minecraftmodapi.gui.EnumGuiHandler;
import com.tntp.minecraftmodapi.util.ClientUtil;
import com.tntp.minecraftmodapi.util.LocalUtil;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BehaviorCrowbar implements IBlockBehavior {
    @Override
    @FirstCertain
    public Turnary onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        ItemStack item = player.getCurrentEquippedItem();
        if (item != null && Crowbar.isCrowbar(item)) {
            if (crowBarHit(world, world.getBlock(x, y, z), x, y, z, world.getBlockMetadata(x, y, z), side, player))
                return Turnary.TRUE;
        }
        return Turnary.UNCERTAIN;
    }

    private boolean crowBarHit(World world, Block b, int x, int y, int z, int meta, int side, EntityPlayer player) {
        if (b instanceof ICrowbarRotatable) {
            if (meta == side)
                side ^= 1;
            world.setBlockMetadataWithNotify(x, y, z, (meta & 8) + side, 2);
            if (world.isRemote) {
                world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
                ClientUtil.printChatMessage(LocalUtil.localize("ac.message.side_arg_s", LocalUtil.localize("ac.message.side_" + side)));
            }
            return true;
        } else if (b == ACBlocks.assembly_manager) {
            EnumGuiHandler.openGui(ACEnumGui.AssemblyManagerBooks, AssemblyCartsMod.MODID, player, world, x, y, z);
            return true;
        } else if (b == ACBlocks.docking_track) {
            TileDockingTrack tile = (TileDockingTrack) world.getTileEntity(x, y, z);
            tile.setReversed(!tile.isReversed());
            if (world.isRemote) {
                world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
            }
            return true;
        }
        return false;
    }

    public interface ICrowbarRotatable {

    }
}
