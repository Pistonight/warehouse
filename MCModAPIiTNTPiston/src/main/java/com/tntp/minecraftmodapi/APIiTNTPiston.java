package com.tntp.minecraftmodapi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tntp.minecraftmodapi.block.IBlockRegisterFactory;
import com.tntp.minecraftmodapi.block.RegBlock;
import com.tntp.minecraftmodapi.compat.ICompatRegisterFactory;
import com.tntp.minecraftmodapi.compat.RegCompat;
import com.tntp.minecraftmodapi.entity.IEntityRegisterFactory;
import com.tntp.minecraftmodapi.entity.RegEntity;
import com.tntp.minecraftmodapi.item.IItemRegisterFactory;
import com.tntp.minecraftmodapi.item.RegItem;
import com.tntp.minecraftmodapi.network.IMessageRegisterFactory;
import com.tntp.minecraftmodapi.network.RegMessage;
import com.tntp.minecraftmodapi.recipe.IRecipeRegisterFactory;
import com.tntp.minecraftmodapi.recipe.RegRecipe;
import com.tntp.minecraftmodapi.tileentity.ITileEntityRegisterFactory;
import com.tntp.minecraftmodapi.tileentity.RegTileEntity;

/**
 * The iTNTPison API
 *
 */
public abstract class APIiTNTPiston {
    public static Logger log = LogManager.getLogger("iTNTPiston API");

    public static IBlockRegisterFactory newBlockRegister() {
        return new RegBlock();
    }

    public static ITileEntityRegisterFactory newTileRegister() {
        return new RegTileEntity();
    }

    public static IEntityRegisterFactory newEntityRegister() {
        return new RegEntity();
    }

    public static IItemRegisterFactory newItemRegister() {
        return new RegItem();
    }

    public static IMessageRegisterFactory newMessageRegister() {
        return new RegMessage();
    }

    public static ICompatRegisterFactory newCompatRegister() {
        return new RegCompat();
    }

    public static IRecipeRegisterFactory newRecipeRegister() {
        return new RegRecipe();
    }

}
