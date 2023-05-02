package com.tntp.assemblycarts.block;

import com.tntp.assemblycarts.api.Assemblium;
import com.tntp.assemblycarts.core.AssemblyCartsMod;
import com.tntp.assemblycarts.gui.ACEnumGui;
import com.tntp.assemblycarts.tileentity.TileAssemblyManager;
import com.tntp.minecraftmodapi.block.BlockContainerAPIiTNTPiston;
import com.tntp.minecraftmodapi.gui.EnumGuiHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockAssemblyManager extends BlockContainerAPIiTNTPiston {
    private IIcon on;

    public BlockAssemblyManager() {
        super(Assemblium.BLOCK_MATERIAL, 5.0f, 10.0f);
        this.setHarvestLevel("pickaxe", 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return meta == 0 ? blockIcon : on;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        super.registerBlockIcons(reg);
        this.on = reg.registerIcon(this.getTextureName() + "_on");
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileAssemblyManager();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ))
            return true;
        if (!world.isRemote) {
            EnumGuiHandler.openGui(ACEnumGui.AssemblyManager, AssemblyCartsMod.MODID, player, world, x, y, z);
        }
        return true;
    }

}
