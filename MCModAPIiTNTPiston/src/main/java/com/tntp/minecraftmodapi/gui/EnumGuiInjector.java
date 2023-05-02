package com.tntp.minecraftmodapi.gui;

import java.lang.reflect.Field;

import com.tntp.minecraftmodapi.APIiTNTPiston;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EnumGuiInjector {
    @SideOnly(Side.CLIENT)
    public static void injectGui(String qualifiedPrefix, IEnumGui... enumGuis) {
        APIiTNTPiston.log.info("Injecting GUIs");
        for (IEnumGui e : enumGuis) {
            injectGui(qualifiedPrefix, e);
        }
        APIiTNTPiston.log.info("Injected GUIs");
    }

    @SideOnly(Side.CLIENT)
    private static void injectGui(String qualifiedPrefix, IEnumGui injectTarget) {
        if (injectTarget.isValid()) {
            String fullName = qualifiedPrefix + injectTarget.name();
            try {
                Class<?> guiClass = Class.forName(fullName);
                Field guiField = injectTarget.getClass().getDeclaredField("gui");
                guiField.setAccessible(true);
                guiField.set(injectTarget, guiClass);
                APIiTNTPiston.log.info("[Gui Injecter] >> " + fullName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                APIiTNTPiston.log.error("[Gui Injecter] Cannot Inject GUI: " + fullName);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                APIiTNTPiston.log.error("[Gui Injecter] Cannot Inject GUI: " + fullName);
                APIiTNTPiston.log.error("[Gui Injecter] Field gui Not Found.");
            } catch (SecurityException e) {
                e.printStackTrace();
                APIiTNTPiston.log.error("[Gui Injecter] Cannot Inject GUI: " + fullName);
                APIiTNTPiston.log.error("[Gui Injecter] Security Error.");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            APIiTNTPiston.log.info("Skipping " + injectTarget.name());
        }
    }

    public static void injectContainer(String qualifiedPrefix, IEnumGui... enumGuis) {
        APIiTNTPiston.log.info("Injecting Containers");
        for (IEnumGui e : enumGuis) {
            injectContainer(qualifiedPrefix, e);
        }
        APIiTNTPiston.log.info("Injected Containers");
    }

    private static void injectContainer(String qualifiedPrefix, IEnumGui injectTarget) {
        if (injectTarget.isValid()) {
            String fullName = qualifiedPrefix + injectTarget.name();
            try {
                Class<?> guiClass = Class.forName(fullName);
                Field guiField = injectTarget.getClass().getDeclaredField("container");
                guiField.setAccessible(true);
                guiField.set(injectTarget, guiClass);
                APIiTNTPiston.log.info("[Container Injecter] >> " + fullName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                APIiTNTPiston.log.error("[Container Injecter] Cannot Inject Container: " + fullName);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                APIiTNTPiston.log.error("[Container Injecter] Cannot Inject Container: " + fullName);
                APIiTNTPiston.log.error("[Container Injecter] Field container Not Found.");
            } catch (SecurityException e) {
                e.printStackTrace();
                APIiTNTPiston.log.error("[Container Injecter] Cannot Inject Container: " + fullName);
                APIiTNTPiston.log.error("[Container Injecter] Security Error.");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            APIiTNTPiston.log.info("Skipping " + injectTarget.name());
        }
    }
}
