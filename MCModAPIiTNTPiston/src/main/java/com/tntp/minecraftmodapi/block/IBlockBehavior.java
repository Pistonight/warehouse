package com.tntp.minecraftmodapi.block;

import java.util.List;
import java.util.Random;

import com.tntp.minecraftmodapi.ExeResult;
import com.tntp.minecraftmodapi.Turnary;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
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

public interface IBlockBehavior {
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
    @FirstNonNull
    @SideOnly(Side.CLIENT)
    default IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        return null;
    }

    /**
     * Gets the block's texture. Args: side, meta
     * Behavior: Apply first non-null
     */
    @FirstNonNull
    @SideOnly(Side.CLIENT)
    default IIcon getIcon(int side, int meta) {
        return null;
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes
     * to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     * Behavior: Overlap
     */
    @Overlap
    default ExeResult addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List<Object> collisionBoxes, Entity collidingEntity) {
        return ExeResult.CONTINUE;
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box
     * can change after the pool has been
     * cleared to be reused)
     * Behavior: Apply first non-null
     */
    @FirstNonNull
    default AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     * Behavior: Apply first non-null
     * 
     */
    @FirstNonNull
    @SideOnly(Side.CLIENT)
    default AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    /**
     * Ticks the block if it's been scheduled
     * Behavior: Overlap
     */
    @Overlap
    default ExeResult updateTick(World world, int x, int y, int z, Random rand) {
        return ExeResult.CONTINUE;
    }

    /**
     * A randomly called display update to be able to add particles or other items
     * for display
     */
    @Overlap
    @SideOnly(Side.CLIENT)
    default ExeResult randomDisplayTick(World world, int x, int y, int z, Random rand) {
        return ExeResult.CONTINUE;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    @Overlap
    default ExeResult onBlockAdded(World world, int x, int y, int z) {
        return ExeResult.CONTINUE;
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z,
     * side, hitX, hitY, hitZ, block metadata. Returns meta
     */
    @FirstNonNeg
    default int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
        return -1;
    }

    /**
     * Called when the block is placed in the world.
     */
    @Overlap
    default ExeResult onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        return ExeResult.CONTINUE;
    }

    /**
     * Called after a block is placed
     */
    @Overlap
    default ExeResult onPostBlockPlaced(World world, int x, int y, int z, int meta) {
        return ExeResult.CONTINUE;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which
     * neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    @Overlap
    default ExeResult onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        return ExeResult.CONTINUE;
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
    default ExeResult onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
        return ExeResult.CONTINUE;
    }

    /**
     * Called whenever an entity is walking on top of this block. Args: world, x, y,
     * z, entity
     */
    @Overlap
    default ExeResult onEntityWalking(World world, int x, int y, int z, Entity entity) {
        return ExeResult.CONTINUE;
    }

    /**
     * Called when a player hits the block. Args: world, x, y, z, player
     */
    @Overlap
    default ExeResult onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        return ExeResult.CONTINUE;
    }

    @FirstCertain
    default Turnary onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        return Turnary.UNCERTAIN;
    }

    /**
     * Called on server worlds only when the block is about to be replaced by a
     * different block or the same block with a
     * different metadata value. Args: world, x, y, z, old metadata
     */
    @Overlap
    default ExeResult onBlockPreDestroy(World world, int x, int y, int z, int oldmeta) {
        return ExeResult.CONTINUE;
    }

    /**
     * Called right before the block is destroyed by a player. Args: world, x, y, z,
     * metaData
     */
    @Overlap
    default ExeResult onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
        return ExeResult.CONTINUE;
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    @Overlap
    default ExeResult onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion) {
        return ExeResult.CONTINUE;
    }

    /**
     * Called when the block is attempted to be harvested
     */
    @Overlap
    default ExeResult onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) {
        return ExeResult.CONTINUE;
    }

    @Overlap
    default ExeResult breakBlock(World world, int x, int y, int z, Block block, int meta) {
        return ExeResult.CONTINUE;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     * Behavior: return -1 to ignore this function
     */
    @FirstNonNeg
    default int quantityDropped(Random rand) {
        return -1;
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
    default Item getItemDropped(int meta, Random rand, int fortune) {
        return null;
    }

    /**
     * Can add to the passed in vector for a movement vector to be applied to the
     * entity. Args: x, y, z, entity, vec3d
     */
    @Overlap
    default ExeResult velocityToAddToEntity(World world, int x, int y, int z, Entity entity, Vec3 vec) {
        return ExeResult.CONTINUE;
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    @Overlap
    default ExeResult setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        return ExeResult.CONTINUE;
    }

    /**
     * Return weak power provided
     * 
     * @return
     */
    @FirstNonNeg
    default int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int direction) {
        return -1;
    }

    /**
     * Return strong power provided
     * 
     * @return
     */
    @FirstNonNeg
    default int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int direction) {
        return -1;
    }

    /**
     * If hasComparatorInputOverride returns true, the return value from this is
     * used instead of the redstone signal
     * strength when this block inputs to a comparator.
     */
    @FirstNonNeg
    default int getComparatorInputOverride(World world, int x, int y, int z, int direction) {
        return -1;
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the
     * block). Args: world, x, y, z, entity
     */
    @Overlap
    default ExeResult onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        return ExeResult.CONTINUE;
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
    @Overlap
    default ExeResult setBlockBoundsForItemRender() {
        return ExeResult.CONTINUE;
    }

    /**
     * Can this block stay at this position. Similar to canPlaceBlockAt except gets
     * checked often with plants.
     */
    @FirstCertain
    default Turnary canBlockStay(World world, int x, int y, int z) {
        return Turnary.UNCERTAIN;
    }

    @FirstTrue
    default boolean onBlockEventReceived(Block b, World world, int x, int y, int z, int event, int param) {
        return false;
    }

    /**
     * Block's chance to react to an entity falling on it.
     */
    @Overlap
    default ExeResult onFallenUpon(World world, int x, int y, int z, Entity entity, float fallDistance) {
        return ExeResult.CONTINUE;
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    @FirstNonNeg
    default int getDamageValue(World world, int x, int y, int z) {
        return -1;
    }

    /**
     * currently only used by BlockCauldron to incrament meta-data during rain
     */
    @Overlap
    default ExeResult fillWithRain(World world, int x, int y, int z) {
        return ExeResult.CONTINUE;
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
    default int getLightValue(IBlockAccess world, int x, int y, int z) {
        return -1;
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
    default Turnary isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {
        return Turnary.UNCERTAIN;
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
    default Turnary canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        return Turnary.UNCERTAIN;
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
    default Turnary rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis) {
        return Turnary.UNCERTAIN;
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
    default Turnary recolourBlock(World world, int x, int y, int z, ForgeDirection side, int colour) {
        return Turnary.UNCERTAIN;
    }

    public @interface Overlap {

    }

    public @interface FirstNonNull {

    }

    public @interface FirstNonNeg {

    }

    public @interface FirstCertain {

    }

    public @interface FirstTrue {

    }
}
