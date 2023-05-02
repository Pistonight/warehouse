package com.tntp.assemblycarts.gui;

import java.lang.reflect.Constructor;

import com.tntp.minecraftmodapi.gui.IEnumGui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.inventory.IInventory;

public enum ACEnumGui implements IEnumGui {
    UNKNOWN, AssemblyManager, AssemblyManagerBooks, AssemblyPort, AssemblyRequester, AssemblyRequesterMark, MinecartAssemblyWorker, ProcessBook, DetectorMark;

    /** Injected Container Class */
    private Class<?> container;
    /** Injected Gui Class */
    @SideOnly(Side.CLIENT)
    private Class<?> gui;

    @Override
    public boolean isValid() {
        return this != UNKNOWN;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Constructor<?> guiConstructor() {
        try {
            return gui.getConstructor(IInventory.class, Object.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Cannot get Gui Constructor!");
    }

    @Override
    public Constructor<?> containerConstructor() {
        try {
            return container.getConstructor(IInventory.class, Object.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Cannot get Container Constructor!");
    }

    @Override
    public int id() {
        return ordinal();
    }
}
