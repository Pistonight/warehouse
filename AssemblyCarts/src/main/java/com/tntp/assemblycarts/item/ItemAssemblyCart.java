package com.tntp.assemblycarts.item;

import com.tntp.assemblycarts.entity.EntityMinecartAssemblyWorker;
import com.tntp.minecraftmodapi.item.ItemAPIiTNTPiston;

import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAssemblyCart extends ItemAPIiTNTPiston {

    public ItemAssemblyCart() {
        this.maxStackSize = 3;
    }

    /**
     * Callback for item usage. If the item does something special on right
     * clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (BlockRailBase.func_150051_a(world.getBlock(x, y, z))) {
            if (!world.isRemote) {
                EntityMinecart entityminecart = new EntityMinecartAssemblyWorker(world, x + 0.5, y + 0.5, z + 0.5);

                if (stack.hasDisplayName()) {
                    entityminecart.setMinecartName(stack.getDisplayName());
                }

                world.spawnEntityInWorld(entityminecart);
            }

            --stack.stackSize;
            return true;
        } else {
            return false;
        }
    }

}
