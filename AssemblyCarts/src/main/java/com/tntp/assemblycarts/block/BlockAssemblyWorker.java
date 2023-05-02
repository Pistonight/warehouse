package com.tntp.assemblycarts.block;

import com.tntp.assemblycarts.api.Assemblium;
import com.tntp.assemblycarts.block.behavior.BehaviorCrowbar.ICrowbarRotatable;
import com.tntp.assemblycarts.core.AssemblyCartsMod;
import com.tntp.minecraftmodapi.block.BlockBehaviorAPIiTNTPiston;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockAssemblyWorker extends BlockBehaviorAPIiTNTPiston implements ICrowbarRotatable {
    public BlockAssemblyWorker() {
        super(Assemblium.BLOCK_MATERIAL, 5.0f, 10.0f);
        this.setHarvestLevel("pickaxe", 1);
    }

    private IIcon front;
    private IIcon base;

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int s, int meta) {
        return ((s == 0 || s == 1) ? blockIcon : (s == meta || (s ^ 1) == meta) ? front : base);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        blockIcon = reg.registerIcon(AssemblyCartsMod.MODID + ":assemblium_block");
        front = reg.registerIcon(this.getTextureName() + "_front");
        base = reg.registerIcon(this.getTextureName() + "_base");
    }

}
