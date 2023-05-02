package com.tntp.assemblycarts.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Blocks;

@SideOnly(Side.CLIENT)
public class RenderAssemblyCarts extends RenderMinecart {
    @Override
    protected void func_147910_a(EntityMinecart p_147910_1_, float p_147910_2_, Block p_147910_3_, int p_147910_4_) {

        System.out.println("called");
        float f1 = p_147910_1_.getBrightness(p_147910_2_);
        GL11.glPushMatrix();
        this.field_94145_f.renderBlockAsItem(Blocks.bedrock, 0, f1);
        GL11.glPopMatrix();
    }

}
