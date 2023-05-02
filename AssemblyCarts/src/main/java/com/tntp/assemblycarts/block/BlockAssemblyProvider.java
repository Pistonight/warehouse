package com.tntp.assemblycarts.block;

import com.tntp.assemblycarts.api.Assemblium;
import com.tntp.assemblycarts.block.behavior.BehaviorCrowbar.ICrowbarRotatable;
import com.tntp.assemblycarts.core.AssemblyCartsMod;
import com.tntp.assemblycarts.tileentity.TileAssemblyProvider;
import com.tntp.minecraftmodapi.block.BlockContainerAPIiTNTPiston;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockAssemblyProvider extends BlockContainerAPIiTNTPiston implements ICrowbarRotatable {
    private IIcon port;
    private IIcon side;

    public BlockAssemblyProvider() {
        super(Assemblium.BLOCK_MATERIAL, 5.0f, 10.0f);
        this.setHarvestLevel("pickaxe", 1);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileAssemblyProvider();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int s, int meta) {
        if (s == meta)
            return port;
        switch (s) {
        case 0:
        case 1:
            return blockIcon;
        }
        return side;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        blockIcon = reg.registerIcon(AssemblyCartsMod.MODID + ":assemblium_block");
        side = reg.registerIcon(this.getTextureName() + "_side");
        port = reg.registerIcon(this.getTextureName() + "_port");
    }

}
