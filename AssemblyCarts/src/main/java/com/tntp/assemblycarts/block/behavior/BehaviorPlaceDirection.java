package com.tntp.assemblycarts.block.behavior;

import com.tntp.minecraftmodapi.ExeResult;
import com.tntp.minecraftmodapi.block.IBlockBehavior;

import net.minecraft.block.BlockPistonBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BehaviorPlaceDirection implements IBlockBehavior {
    @Overlap
    public ExeResult onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        int l = BlockPistonBase.determineOrientation(world, x, y, z, entity);
        world.setBlockMetadataWithNotify(x, y, z, l, 2);
        return ExeResult.BREAK;
    }
}
