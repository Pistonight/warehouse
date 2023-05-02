package com.tntp.minecraftmodapi.util;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockUtil {
    public static byte getRotationalMetaFromBlocks(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z - 1);
        Block block1 = world.getBlock(x, y, z + 1);
        Block block2 = world.getBlock(x - 1, y, z);
        Block block3 = world.getBlock(x + 1, y, z);
        byte b0 = 3;

        if (block.isAir(world, x, y, z) && !block1.isAir(world, x, y, z)) {
            b0 = 3;
        }

        if (block1.isAir(world, x, y, z) && !block.isAir(world, x, y, z)) {
            b0 = 2;
        }

        if (block2.isAir(world, x, y, z) && !block3.isAir(world, x, y, z)) {
            b0 = 5;
        }

        if (block3.isAir(world, x, y, z) && !block2.isAir(world, x, y, z)) {
            b0 = 4;
        }
        return b0;
    }

    public static byte getRotationalMetaFromEntityPlacing(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        int l = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0) {
            return 2;
        }

        if (l == 1) {
            return 5;
        }

        if (l == 2) {
            return 3;
        }

        if (l == 3) {
            return 4;
        }
        return -1;
    }

}
