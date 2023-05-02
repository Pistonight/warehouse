package com.tntp.assemblycarts.block;

import com.tntp.assemblycarts.api.Crowbar;
import com.tntp.assemblycarts.block.behavior.BehaviorCrowbar.ICrowbarRotatable;
import com.tntp.assemblycarts.entity.EntityMinecartAssemblyWorker;
import com.tntp.assemblycarts.render.RenderTrack;
import com.tntp.assemblycarts.tileentity.TileDockingTrack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockDockingTrack extends BlockRailBase implements ITileEntityProvider, ICrowbarRotatable {
    public IIcon powered;
    public IIcon base;

    public BlockDockingTrack() {
        super(true);
        this.setHarvestLevel("crowbar", 0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return (p_149691_2_ & 8) == 0 ? this.blockIcon : this.powered;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        super.registerBlockIcons(reg);
        this.powered = reg.registerIcon(this.getTextureName() + "_powered");
        this.base = reg.registerIcon("minecraft:rail_normal");
    }

    public static void setPowered(World world, int x, int y, int z, boolean power) {
        int oldMeta = world.getBlockMetadata(x, y, z);
        int newMeta = power ? (oldMeta | 8) : (oldMeta & 7);
        TileDockingTrack tile = (TileDockingTrack) world.getTileEntity(x, y, z);
        boolean reversed = tile.isReversed();
        world.setBlockMetadataWithNotify(x, y, z, newMeta, 3);
        tile = (TileDockingTrack) world.getTileEntity(x, y, z);
        tile.setReversed(reversed);
    }

    public static boolean isPowered(World world, int x, int y, int z) {
        return (world.getBlockMetadata(x, y, z) & 8) == 8;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        // change direction
        ItemStack item = player.getCurrentEquippedItem();
        if (item != null && Crowbar.isCrowbar(item)) {
            TileDockingTrack tile = (TileDockingTrack) world.getTileEntity(x, y, z);
            tile.setReversed(!tile.isReversed());
            if (world.isRemote) {
                world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
            }
            return true;
        }
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderType() {
        return RenderTrack.renderID;
    }

    /**
     * Returns the max speed of the rail at the specified position.
     * 
     * @param world The world.
     * @param cart  The cart on the rail, may be null.
     * @param x     The rail X coordinate.
     * @param y     The rail Y coordinate.
     * @param z     The rail Z coordinate.
     * @return The max speed of the current rail.
     */
    @Override
    public float getRailMaxSpeed(World world, EntityMinecart cart, int x, int y, int z) {
        TileDockingTrack tile = (TileDockingTrack) world.getTileEntity(x, y, z);
        if (tile.isOccupied())
            return 0;
        if (!isPowered(world, x, y, z) && tile.canDock(cart))
            return 0;
        return super.getRailMaxSpeed(world, cart, x, y, z);
    }

    /**
     * This function is called by any minecart that passes over this rail.
     * It is called once per update tick that the minecart is on the rail.
     * 
     * @param world The world.
     * @param cart  The cart on the rail.
     * @param x     The rail X coordinate.
     * @param y     The rail Y coordinate.
     * @param z     The rail Z coordinate.
     */
    @Override
    public void onMinecartPass(World world, EntityMinecart cart, int x, int y, int z) {
        // if (!world.isRemote) {

        if (isPowered(world, x, y, z)) {
            double accel = 0.03;
            int meta = world.getBlockMetadata(x, y, z) & 7;
            TileDockingTrack tile = (TileDockingTrack) world.getTileEntity(x, y, z);
            if (tile.isReversed()) {
                accel = -accel;
            }
            if (meta == 0) {
                cart.motionZ += accel;
            } else if (meta == 1) {
                cart.motionX -= accel;
            }
            tile.setOccupied(false);
            tile.setDockedCart(null);
        } else {
            TileDockingTrack tile = (TileDockingTrack) world.getTileEntity(x, y, z);
            if (tile.canDock(cart)) {
                if (!tile.isOccupied() || tile.getDockedCart() == cart) {
                    cart.motionX = 0;
                    cart.motionZ = 0;
                    cart.posX = x + 0.5;
                    cart.posZ = z + 0.5;
                    tile.setOccupied(true);
                    tile.setDockedCart((EntityMinecartAssemblyWorker) cart);
                } else {
                    if (tile.getDockedCart() == null) {
                        tile.setDockedCart((EntityMinecartAssemblyWorker) cart);
                    } else {
                        // bounce the cart back
                        cart.motionX = -cart.motionX / 2;
                        cart.motionZ = -cart.motionZ / 2;
                    }
                }
            } else {
                if (tile.isOccupied()) {
                    // bounce the cart back
                    cart.motionX = -cart.motionX / 2;
                    cart.motionZ = -cart.motionZ / 2;
                }
            }

        }
        // }
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileDockingTrack();
    }

    @Override
    public boolean onBlockEventReceived(World world, int x, int y, int z, int p_149696_5_, int p_149696_6_) {
        super.onBlockEventReceived(world, x, y, z, p_149696_5_, p_149696_6_);
        if (this.hasTileEntity(world.getBlockMetadata(x, y, z))) {
            TileEntity tileentity = world.getTileEntity(x, y, z);
            return tileentity != null ? tileentity.receiveClientEvent(p_149696_5_, p_149696_6_) : false;
        }
        return false;
    }

}
