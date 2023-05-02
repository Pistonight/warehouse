package com.tntp.assemblycarts.block;

import com.tntp.assemblycarts.api.Assemblium;
import com.tntp.assemblycarts.block.behavior.BehaviorCrowbar.ICrowbarRotatable;
import com.tntp.assemblycarts.core.AssemblyCartsMod;
import com.tntp.assemblycarts.gui.ACEnumGui;
import com.tntp.assemblycarts.tileentity.TileDetectorAssembly;
import com.tntp.minecraftmodapi.block.BlockBehaviorAPIiTNTPiston;
import com.tntp.minecraftmodapi.gui.EnumGuiHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDetectorAssembly extends BlockBehaviorAPIiTNTPiston implements ICrowbarRotatable, ITileEntityProvider {
    private IIcon port_off;
    private IIcon port_on;

    public BlockDetectorAssembly() {
        super(Assemblium.BLOCK_MATERIAL, 5.0f, 10.0f);
        this.setHarvestLevel("pickaxe", 1);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister reg) {
        blockIcon = reg.registerIcon(AssemblyCartsMod.MODID + ":assemblium_block");
        port_off = reg.registerIcon(getTextureName() + "_off");
        port_on = reg.registerIcon(getTextureName() + "_on");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        int meta = world.getBlockMetadata(x, y, z);
        return side == meta ? ((meta & 8) == 8 ? port_on : port_off) : blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int s, int meta) {
        return s == 3 ? port_on : blockIcon;
    }

    @Override
    public boolean isNormalCube(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @Override
    public boolean canProvidePower() {
        return true;
    }

    @Override
    public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int direction) {
        int meta = world.getBlockMetadata(x, y, z);
        if ((meta & 8) == 8 && (meta & 7) == (direction ^ 1))
            return 15;
        return 0;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int direction) {
        return isProvidingStrongPower(world, x, y, z, direction);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileDetectorAssembly();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ))
            return true;
        if (!world.isRemote) {
            EnumGuiHandler.openGui(ACEnumGui.DetectorMark, AssemblyCartsMod.MODID, player, world, x, y, z);
        }
        return true;

    }

}
