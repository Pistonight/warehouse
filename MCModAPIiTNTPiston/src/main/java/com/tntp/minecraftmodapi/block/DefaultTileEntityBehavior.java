package com.tntp.minecraftmodapi.block;

import com.tntp.minecraftmodapi.ExeResult;
import com.tntp.minecraftmodapi.tileentity.TileEntityAPIiTNTPiston;
import com.tntp.minecraftmodapi.util.RandomUtil;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class DefaultTileEntityBehavior implements IBlockBehavior {
    public static final IBlockBehavior INSTANCE = new DefaultTileEntityBehavior();

    @Overlap
    public ExeResult breakBlock(World world, int x, int y, int z, Block block, int meta) {
        // drop all items
        TileEntity tile = (TileEntity) world.getTileEntity(x, y, z);

        if (tile instanceof IInventory) {
            if (tile instanceof TileEntityAPIiTNTPiston) {
                ((TileEntityAPIiTNTPiston) tile).onBreakingContainer();
            }
            IInventory inventory = (IInventory) tile;
            for (int i1 = 0; i1 < inventory.getSizeInventory(); ++i1) {
                ItemStack itemstack = inventory.getStackInSlot(i1);

                if (itemstack != null) {
                    float posX = RandomUtil.RAND.nextFloat() * 0.8F + 0.1F;
                    float posY = RandomUtil.RAND.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityitem;

                    for (float f2 = RandomUtil.RAND.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem)) {
                        int size = RandomUtil.RAND.nextInt(21) + 10;

                        if (size > itemstack.stackSize) {
                            size = itemstack.stackSize;
                        }

                        itemstack.stackSize -= size;
                        entityitem = new EntityItem(world, (double) ((float) x + posX), (double) ((float) y + posY), (double) ((float) z + f2),
                                new ItemStack(itemstack.getItem(), size, itemstack.getItemDamage()));
                        float f3 = 0.05F;
                        entityitem.motionX = (double) ((float) RandomUtil.RAND.nextGaussian() * f3);
                        entityitem.motionY = (double) ((float) RandomUtil.RAND.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double) ((float) RandomUtil.RAND.nextGaussian() * f3);

                        if (itemstack.hasTagCompound()) {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                        }
                    }
                }
            }

            world.func_147453_f(x, y, z, block);
        }
        return ExeResult.CONTINUE;
    }

    @Override
    @FirstTrue
    public boolean onBlockEventReceived(Block block, World world, int x, int y, int z, int event, int param) {
        if (block.hasTileEntity(world.getBlockMetadata(x, y, z))) {
            TileEntity tileentity = world.getTileEntity(x, y, z);
            return tileentity != null ? tileentity.receiveClientEvent(event, param) : false;
        }
        return false;
    }
}
