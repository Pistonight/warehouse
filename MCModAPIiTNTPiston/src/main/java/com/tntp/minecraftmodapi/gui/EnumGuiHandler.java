package com.tntp.minecraftmodapi.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class EnumGuiHandler implements IGuiHandler {

    public static final int GUI_ENTITY = -1;
    public static final int GUI_CURRENT_ITEM = -2;
    public static final int GUI_OTHER = -3;

    public static void openGui(IEnumGui gui, String mod, EntityPlayer player, World world, int x, int y, int z) {
        player.openGui(mod, gui.id(), world, x, y, z);
    }

    public static void openGuiEntity(IEnumGui gui, String mod, EntityPlayer player, World world, int entityID) {
        openGui(gui, mod, player, world, entityID, GUI_ENTITY, GUI_ENTITY);
    }

    public static void openGuiCurrentItem(IEnumGui gui, String mod, EntityPlayer player, World world) {
        openGui(gui, mod, player, world, GUI_CURRENT_ITEM, GUI_CURRENT_ITEM, GUI_CURRENT_ITEM);
    }

    public static void openGuiOther(IEnumGui gui, String mod, EntityPlayer player, World world, int oGuiID, int oGuiParam) {
        openGui(gui, mod, player, world, oGuiID, GUI_OTHER, oGuiParam);
    }

    @Override
    public final Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        IEnumGui gui = getEnumGuiWithID(ID);
        switch (y) {
        case GUI_ENTITY:
            return getContainerFromEntity(gui, player, world, x);
        case GUI_CURRENT_ITEM:
            return getContainerFromCurrentItem(gui, player.getCurrentEquippedItem(), player, world);
        case GUI_OTHER:
            return getContainerOther(gui, player, world, x, z);
        default:
            return getContainer(gui, player, world, x, y, z);
        }

    }

    @Override
    public final Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        IEnumGui gui = getEnumGuiWithID(ID);
        switch (y) {
        case GUI_ENTITY:
            return getGuiFromEntity(gui, player, world, x);
        case GUI_CURRENT_ITEM:
            return getGuiFromCurrentItem(gui, player.getCurrentEquippedItem(), player, world);
        case GUI_OTHER:
            return getGuiOther(gui, player, world, x, z);
        default:
            return getGui(gui, player, world, x, y, z);
        }
    }

    public Object getContainer(IEnumGui gui, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    public Object getContainerFromEntity(IEnumGui gui, EntityPlayer player, World world, int entityID) {
        return null;
    }

    public Object getContainerFromCurrentItem(IEnumGui gui, ItemStack mainHand, EntityPlayer player, World world) {
        return null;
    }

    public Object getContainerOther(IEnumGui gui, EntityPlayer player, World world, int oGuiID, int oGuiParam) {
        return null;
    }

    public Object getGui(IEnumGui gui, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    public Object getGuiFromEntity(IEnumGui gui, EntityPlayer player, World world, int entityID) {
        return null;
    }

    public Object getGuiFromCurrentItem(IEnumGui gui, ItemStack mainHand, EntityPlayer player, World world) {
        return null;
    }

    public Object getGuiOther(IEnumGui gui, EntityPlayer player, World world, int oGuiID, int oGuiParam) {
        return null;
    }

    private IEnumGui getEnumGuiWithID(int ID) {
        IEnumGui[] enumGuis = getEnumClass().getEnumConstants();
        if (ID < 0 || ID >= enumGuis.length)
            throw new ArrayIndexOutOfBoundsException("EnumGui with ID=" + ID);
        return enumGuis[ID];
    }

    public abstract Class<? extends IEnumGui> getEnumClass();
}
