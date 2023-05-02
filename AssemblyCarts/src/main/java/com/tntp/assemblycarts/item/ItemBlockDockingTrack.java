package com.tntp.assemblycarts.item;

import com.tntp.assemblycarts.block.BlockDockingTrack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;

public class ItemBlockDockingTrack extends ItemBlock {

    public ItemBlockDockingTrack(Block b) {
        super(b);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    /**
     * Gets an icon index based on an item's damage value and the given render pass
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamageForRenderPass(int damage, int pass) {
        if (pass == 0)
            return ((BlockDockingTrack) this.field_150939_a).base;
        return this.getIconFromDamage(damage);
    }

}
