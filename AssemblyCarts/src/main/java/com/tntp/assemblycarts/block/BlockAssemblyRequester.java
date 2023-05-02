package com.tntp.assemblycarts.block;

import com.tntp.assemblycarts.api.Assemblium;
import com.tntp.assemblycarts.block.behavior.BehaviorCrowbar.ICrowbarRotatable;
import com.tntp.assemblycarts.core.AssemblyCartsMod;
import com.tntp.assemblycarts.gui.ACEnumGui;
import com.tntp.assemblycarts.tileentity.TileAssemblyRequester;
import com.tntp.minecraftmodapi.block.BlockContainerAPIiTNTPiston;
import com.tntp.minecraftmodapi.gui.EnumGuiHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockAssemblyRequester extends BlockContainerAPIiTNTPiston implements ICrowbarRotatable {
    private IIcon port;
    private IIcon back;

    public BlockAssemblyRequester() {
        super(Assemblium.BLOCK_MATERIAL, 5.0f, 10.0f);
        this.setHarvestLevel("pickaxe", 1);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileAssemblyRequester();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ))
            return true;
        if (!world.isRemote) {
            int meta = world.getBlockMetadata(x, y, z);
            ACEnumGui e;
            if (meta == (side ^ 1)) {
                e = ACEnumGui.AssemblyRequesterMark;
            } else {
                e = ACEnumGui.AssemblyRequester;
            }
            EnumGuiHandler.openGui(e, AssemblyCartsMod.MODID, player, world, x, y, z);
        }
        return true;

    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int s, int meta) {
        if (s == meta)
            return port;
        if ((s ^ 1) == meta)
            return back;
        return blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        blockIcon = reg.registerIcon(this.getTextureName() + "_base");
        port = reg.registerIcon(AssemblyCartsMod.MODID + ":assembly_requester_port");
        back = reg.registerIcon(AssemblyCartsMod.MODID + ":assembly_requester_back");
    }

}
