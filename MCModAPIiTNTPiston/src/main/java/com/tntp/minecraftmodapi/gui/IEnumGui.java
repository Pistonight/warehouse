package com.tntp.minecraftmodapi.gui;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IEnumGui {
    @SideOnly(Side.CLIENT)
    default Object buildGui(Object... args) {
        Constructor<?> c = guiConstructor();
        if (c == null)
            throw new IllegalArgumentException("Gui Constructor is null!");
        try {
            return c.newInstance(args);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Exception occured when building Gui!");
    }

    default Object buildContainer(Object... args) {
        Constructor<?> c = containerConstructor();
        if (c == null)
            throw new IllegalArgumentException("Container Constructor is null!");
        try {
            return c.newInstance(args);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Exception occured when building Container!");
    }

    @SideOnly(Side.CLIENT)
    Constructor<?> guiConstructor();

    Constructor<?> containerConstructor();

    boolean isValid();

    String name();

    int id();
}
