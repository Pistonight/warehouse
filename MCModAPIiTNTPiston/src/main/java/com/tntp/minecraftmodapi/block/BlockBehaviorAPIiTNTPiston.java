package com.tntp.minecraftmodapi.block;

import java.util.List;
import java.util.Random;

import com.tntp.minecraftmodapi.ExeResult;
import com.tntp.minecraftmodapi.Turnary;
import com.tntp.minecraftmodapi.block.IBlockBehavior.FirstCertain;
import com.tntp.minecraftmodapi.block.IBlockBehavior.FirstNonNeg;
import com.tntp.minecraftmodapi.block.IBlockBehavior.FirstNonNull;
import com.tntp.minecraftmodapi.block.IBlockBehavior.FirstTrue;
import com.tntp.minecraftmodapi.block.IBlockBehavior.Overlap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockBehaviorAPIiTNTPiston extends BlockAPIiTNTPiston {
    private IBlockBehavior[] behaviors;
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockBehaviorAPIiTNTPiston(Material material, float hardness, float resistance) {
        super(material, hardness, resistance);
    }

    @SideOnly(Side.CLIENT)
    protected IIcon getIconByIndex(int i) {
        if (i == 0 || icons == null)
            return blockIcon;
        i--;
        if (i >= 0 && i < icons.length)
            return icons[i];
        return blockIcon;
    }

    /**
     * Automatically instantiate the array to fit the index. Icon with the biggest
     * index
     * must be registered first.
     * 
     * @param i
     * @param icon
     */
    @SideOnly(Side.CLIENT)
    protected void setIconByIndex(int i, IIcon icon) {
        if (i == 0)
            blockIcon = icon;
        i--;
        if (icons == null) {
            icons = new IIcon[i + 1];
        }
        icons[i] = icon;
    }

    /**
     * Get the icon of the block based on world state
     * Behavior: Apply first non-null
     * 
     * @param world
     * @param x
     * @param y
     * @param z
     * @param side
     * @return
     */
    @Override
    @FirstNonNull
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                IIcon icon = b.getIcon(world, x, y, z, side);
                if (icon != null)
                    return icon;
            }
        return super.getIcon(world, x, y, z, side);
    }

    /**
     * Gets the block's texture. Args: side, meta
     * Behavior: Apply first non-null
     */
    @FirstNonNull
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                IIcon icon = b.getIcon(side, meta);
                if (icon != null)
                    return icon;
            }
        return super.getIcon(side, meta);
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes
     * to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     * Behavior: Overlap
     */
    @Override
    @Overlap
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List collisionBoxes, Entity collidingEntity) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                if (b.addCollisionBoxesToList(world, x, y, z, mask, collisionBoxes, collidingEntity) == ExeResult.BREAK)
                    return;
            }
        super.addCollisionBoxesToList(world, x, y, z, mask, collisionBoxes, collidingEntity);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box
     * can change after the pool has been
     * cleared to be reused)
     * Behavior: Apply first non-null
     */
    @Override
    @FirstNonNull
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                AxisAlignedBB aabb = b.getCollisionBoundingBoxFromPool(world, x, y, z);
                if (aabb != null)
                    return aabb;
            }
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     * Behavior: Apply first non-null
     * 
     */
    @FirstNonNull
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                AxisAlignedBB aabb = b.getSelectedBoundingBoxFromPool(world, x, y, z);
                if (aabb != null)
                    return aabb;
            }
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    /**
     * Ticks the block if it's been scheduled
     * Behavior: Overlap
     */
    @Overlap
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                if (b.updateTick(world, x, y, z, rand) == ExeResult.BREAK)
                    return;
            }
        super.updateTick(world, x, y, z, rand);
    }

    /**
     * A randomly called display update to be able to add particles or other items
     * for display
     */
    @Overlap
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                if (b.randomDisplayTick(world, x, y, z, rand) == ExeResult.BREAK)
                    return;
            }
        super.randomDisplayTick(world, x, y, z, rand);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    @Overlap
    public void onBlockAdded(World world, int x, int y, int z) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                if (b.onBlockAdded(world, x, y, z) == ExeResult.BREAK)
                    return;
            }
        super.onBlockAdded(world, x, y, z);
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z,
     * side, hitX, hitY, hitZ, block metadata. Returns meta
     */
    @FirstNonNeg
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                int i = b.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, meta);
                if (i >= 0)
                    return i;
            }
        return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, meta);
    }

    /**
     * Called when the block is placed in the world.
     */
    @Overlap
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                if (b.onBlockPlacedBy(world, x, y, z, entity, stack) == ExeResult.BREAK)
                    return;
            }
        super.onBlockPlacedBy(world, x, y, z, entity, stack);
    }

    /**
     * Called after a block is placed
     */
    @Overlap
    public void onPostBlockPlaced(World world, int x, int y, int z, int meta) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                if (b.onPostBlockPlaced(world, x, y, z, meta) == ExeResult.BREAK)
                    return;
            }
        super.onPostBlockPlaced(world, x, y, z, meta);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which
     * neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    @Overlap
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                if (b.onNeighborBlockChange(world, x, y, z, neighbor) == ExeResult.BREAK)
                    return;
            }
        super.onNeighborBlockChange(world, x, y, z, neighbor);
    }

    /**
     * [Forge]Called when a tile entity on a side of this block changes is created
     * or is destroyed.
     * 
     * @param world The world
     * @param x     The x position of this block instance
     * @param y     The y position of this block instance
     * @param z     The z position of this block instance
     * @param tileX The x position of the tile that changed
     * @param tileY The y position of the tile that changed
     * @param tileZ The z position of the tile that changed
     */
    @Overlap
    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                if (b.onNeighborChange(world, x, y, z, tileX, tileY, tileZ) == ExeResult.BREAK)
                    return;
            }
        super.onNeighborChange(world, x, y, z, tileX, tileY, tileZ);
    }

    /**
     * Called whenever an entity is walking on top of this block. Args: world, x, y,
     * z, entity
     */
    @Overlap
    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                if (b.onEntityWalking(world, x, y, z, entity) == ExeResult.BREAK)
                    return;
            }
        super.onEntityWalking(world, x, y, z, entity);
    }

    /**
     * Called when a player hits the block. Args: world, x, y, z, player
     */
    @Overlap
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                if (b.onBlockClicked(world, x, y, z, player) == ExeResult.BREAK)
                    return;
            }
        super.onBlockClicked(world, x, y, z, player);
    }

    @FirstCertain
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                Turnary t = b.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
                if (t != Turnary.UNCERTAIN)
                    return t.value();
            }
        return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
    }

    /**
     * Called on server worlds only when the block is about to be replaced by a
     * different block or the same block with a
     * different metadata value. Args: world, x, y, z, old metadata
     */
    @Overlap
    public void onBlockPreDestroy(World world, int x, int y, int z, int oldmeta) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                if (b.onBlockPreDestroy(world, x, y, z, oldmeta) == ExeResult.BREAK)
                    return;
            }
        super.onBlockPreDestroy(world, x, y, z, oldmeta);
    }

    /**
     * Called right before the block is destroyed by a player. Args: world, x, y, z,
     * metaData
     */
    @Overlap
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                if (b.onBlockDestroyedByPlayer(world, x, y, z, meta) == ExeResult.BREAK)
                    return;
            }
        super.onBlockDestroyedByPlayer(world, x, y, z, meta);
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    @Overlap
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                if (b.onBlockDestroyedByExplosion(world, x, y, z, explosion) == ExeResult.BREAK)
                    return;
            }
        super.onBlockDestroyedByExplosion(world, x, y, z, explosion);
    }

    /**
     * Called when the block is attempted to be harvested
     */
    @Overlap
    public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                if (b.onBlockHarvested(world, x, y, z, meta, player) == ExeResult.BREAK)
                    return;
            }
        super.onBlockHarvested(world, x, y, z, meta, player);
    }

    @Overlap
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                if (b.breakBlock(world, x, y, z, block, meta) == ExeResult.BREAK)
                    return;
            }
        super.breakBlock(world, x, y, z, block, meta);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     * Behavior: return -1 to ignore this function
     */
    @FirstNonNeg
    public int quantityDropped(Random rand) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                int i = b.quantityDropped(rand);
                if (i >= 0)
                    return i;
            }
        return super.quantityDropped(rand);
    }

    /**
     * Behavior:
     * 
     * @param meta
     * @param rand
     * @param fortune
     * @return
     */
    @FirstNonNull
    public Item getItemDropped(int meta, Random rand, int fortune) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                Item item = b.getItemDropped(meta, rand, fortune);
                if (item != null)
                    return item;
            }
        return super.getItemDropped(meta, rand, fortune);
    }

    /**
     * Can add to the passed in vector for a movement vector to be applied to the
     * entity. Args: x, y, z, entity, vec3d
     */
    @Overlap
    public void velocityToAddToEntity(World world, int x, int y, int z, Entity entity, Vec3 vec) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                if (b.velocityToAddToEntity(world, x, y, z, entity, vec) == ExeResult.BREAK)
                    return;
            }
        super.velocityToAddToEntity(world, x, y, z, entity, vec);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    @Overlap
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                if (b.setBlockBoundsBasedOnState(world, x, y, z) == ExeResult.BREAK)
                    return;
            }
        super.setBlockBoundsBasedOnState(world, x, y, z);
    }

    /**
     * Return weak power provided
     * 
     * @return
     */
    @FirstNonNeg
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int direction) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                int i = b.isProvidingWeakPower(world, x, y, z, direction);
                if (i >= 0)
                    return i;
            }
        return super.isProvidingWeakPower(world, x, y, z, direction);
    }

    /**
     * Return strong power provided
     * 
     * @return
     */
    @FirstNonNeg
    public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int direction) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                int i = b.isProvidingStrongPower(world, x, y, z, direction);
                if (i >= 0)
                    return i;
            }
        return super.isProvidingStrongPower(world, x, y, z, direction);
    }

    /**
     * If hasComparatorInputOverride returns true, the return value from this is
     * used instead of the redstone signal
     * strength when this block inputs to a comparator.
     */
    @FirstNonNeg
    public int getComparatorInputOverride(World world, int x, int y, int z, int direction) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                int i = b.getComparatorInputOverride(world, x, y, z, direction);
                if (i >= 0)
                    return i;
            }
        return super.getComparatorInputOverride(world, x, y, z, direction);
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the
     * block). Args: world, x, y, z, entity
     */
    @Overlap
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                if (b.onEntityCollidedWithBlock(world, x, y, z, entity) == ExeResult.BREAK)
                    return;
            }
        super.onEntityCollidedWithBlock(world, x, y, z, entity);
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
    @Overlap
    public void setBlockBoundsForItemRender() {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                if (b.setBlockBoundsForItemRender() == ExeResult.BREAK)
                    return;
            }
        super.setBlockBoundsForItemRender();
    }

    /**
     * Can this block stay at this position. Similar to canPlaceBlockAt except gets
     * checked often with plants.
     */
    @FirstCertain
    public boolean canBlockStay(World world, int x, int y, int z) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                Turnary t = b.canBlockStay(world, x, y, z);
                if (t != Turnary.UNCERTAIN)
                    return t.value();
            }
        return super.canBlockStay(world, x, y, z);
    }

    @FirstTrue
    public boolean onBlockEventReceived(World world, int x, int y, int z, int event, int param) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                if (b.onBlockEventReceived(this, world, x, y, z, event, param))
                    return true;
            }
        return super.onBlockEventReceived(world, x, y, z, event, param);
    }

    /**
     * Block's chance to react to an entity falling on it.
     */
    @Overlap
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float fallDistance) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                if (b.onFallenUpon(world, x, y, z, entity, fallDistance) == ExeResult.BREAK)
                    return;
            }
        super.onFallenUpon(world, x, y, z, entity, fallDistance);
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    @FirstNonNeg
    public int getDamageValue(World world, int x, int y, int z) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                int i = b.getDamageValue(world, x, y, z);
                if (i >= 0)
                    return i;
            }
        return super.getDamageValue(world, x, y, z);
    }

    /**
     * currently only used by BlockCauldron to incrament meta-data during rain
     */
    @Overlap
    public void fillWithRain(World world, int x, int y, int z) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                if (b.fillWithRain(world, x, y, z) == ExeResult.BREAK)
                    return;
            }
        super.fillWithRain(world, x, y, z);
    }

    /**
     * [Forge]Get a light value for the block at the specified coordinates, normal
     * ranges are between 0 and 15
     *
     * @param world The current world
     * @param x     X Position
     * @param y     Y position
     * @param z     Z position
     * @return The light value
     */
    @FirstNonNeg
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                int i = b.getLightValue(world, x, y, z);
                if (i >= 0)
                    return i;
            }
        return super.getLightValue(world, x, y, z);
    }

    /**
     * [Forge]Checks if a player or entity can use this block to 'climb' like a
     * ladder.
     *
     * @param world  The current world
     * @param x      X Position
     * @param y      Y position
     * @param z      Z position
     * @param entity The entity trying to use the ladder, CAN be null.
     * @return True if the block should act like a ladder
     */
    @FirstCertain
    public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                Turnary t = b.isLadder(world, x, y, z, entity);
                if (t != Turnary.UNCERTAIN)
                    return t.value();
            }
        return super.isLadder(world, x, y, z, entity);
    }

    /**
     * [Forge]Determines if a specified mob type can spawn on this block, returning
     * false
     * will
     * prevent any mob from spawning on the block.
     *
     * @param type  The Mob Category Type
     * @param world The current world
     * @param x     The X Position
     * @param y     The Y Position
     * @param z     The Z Position
     * @return True to allow a mob of the specified category to spawn, false to
     *         prevent it.
     */
    @FirstCertain
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                Turnary t = b.canCreatureSpawn(type, world, x, y, z);
                if (t != Turnary.UNCERTAIN)
                    return t.value();
            }
        return super.canCreatureSpawn(type, world, x, y, z);
    }

    /**
     * [Forge]Rotate the block. For vanilla blocks this rotates around the axis
     * passed in (generally, it should be the "face" that was hit).
     * Note: for mod blocks, this is up to the block and modder to decide. It is not
     * mandated that it be a rotation around the
     * face, but could be a rotation to orient *to* that face, or a visiting of
     * possible rotations.
     * The method should return true if the rotation was successful though.
     *
     * @param worldObj The world
     * @param x        X position
     * @param y        Y position
     * @param z        Z position
     * @param axis     The axis to rotate around
     * @return True if the rotation was successful, False if the rotation failed, or
     *         is not possible
     */
    @FirstCertain
    public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                Turnary t = b.rotateBlock(worldObj, x, y, z, axis);
                if (t != Turnary.UNCERTAIN)
                    return t.value();
            }
        return super.rotateBlock(worldObj, x, y, z, axis);
    }

    /**
     * [Forge]Common way to recolour a block with an external tool
     * 
     * @param world  The world
     * @param x      X
     * @param y      Y
     * @param z      Z
     * @param side   The side hit with the colouring tool
     * @param colour The colour to change to
     * @return If the recolouring was successful
     */
    @FirstCertain
    public boolean recolourBlock(World world, int x, int y, int z, ForgeDirection side, int colour) {
        if (behaviors != null)
            for (IBlockBehavior b : behaviors) {
                Turnary t = b.recolourBlock(world, x, y, z, side, colour);
                if (t != Turnary.UNCERTAIN)
                    return t.value();
            }
        return super.recolourBlock(world, x, y, z, side, colour);
    }

}
