package com.tntp.assemblycarts.block;

import com.tntp.assemblycarts.api.Assemblium;
import com.tntp.assemblycarts.block.behavior.BehaviorCrowbar.ICrowbarRotatable;
import com.tntp.assemblycarts.core.AssemblyCartsMod;
import com.tntp.assemblycarts.gui.ACEnumGui;
import com.tntp.assemblycarts.tileentity.TileAssemblyPort;
import com.tntp.minecraftmodapi.block.BlockContainerAPIiTNTPiston;
import com.tntp.minecraftmodapi.gui.EnumGuiHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAssemblyPort extends BlockContainerAPIiTNTPiston implements ICrowbarRotatable {
    public BlockAssemblyPort() {
        super(Assemblium.BLOCK_MATERIAL, 5.0f, 10.0f);
        this.setHarvestLevel("pickaxe", 1);
    }

    private IIcon base;

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ))
            return true;
        if (!world.isRemote) {
            EnumGuiHandler.openGui(ACEnumGui.AssemblyPort, AssemblyCartsMod.MODID, player, world, x, y, z);
        }
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        int meta = world.getBlockMetadata(x, y, z);
        return side == meta ? blockIcon : base;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int s, int meta) {
        return s == 4 ? blockIcon : base;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        super.registerBlockIcons(reg);
        base = reg.registerIcon(AssemblyCartsMod.MODID + ":assembly_frame");
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileAssemblyPort();
    }

}
