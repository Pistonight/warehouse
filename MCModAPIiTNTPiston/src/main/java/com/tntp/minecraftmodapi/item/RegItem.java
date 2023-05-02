package com.tntp.minecraftmodapi.item;

import com.tntp.minecraftmodapi.APIiTNTPiston;
import com.tntp.minecraftmodapi.SuperRegister;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class RegItem extends SuperRegister implements IItemRegisterFactory {
    private CreativeTabs tab;

    public RegItem() {
        APIiTNTPiston.log.info("Registering Items for " + modid);
    }

    @Override
    public IItemRegisterFactory creativeTabs(CreativeTabs tab) {
        this.tab = tab;
        APIiTNTPiston.log.info("[Item Registry] Registered CreativeTabs >> " + tab);
        return this;
    }

    @Override
    public IItemRegister of(Item item, String name) {
        APIiTNTPiston.log.info("[Item Registry] Item >> " + name);

        return new Reg(item, name);
    }

    private class Reg implements IItemRegister {
        private Item item;
        private String name;
        private String textureName;
        private boolean hide = false;

        private Reg(Item i, String n) {
            item = i;
            name = n;
        }

        @Override
        public IItemRegister texture(String name) {
            this.textureName = name;
            return this;
        }

        @Override
        public IItemRegister hide() {
            hide = true;
            return this;
        }

        @Override
        public void register() {
            item.setUnlocalizedName(name);
            if (textureName == null)
                textureName = name;
            item.setTextureName(modid + ":" + textureName);
            if (!hide && tab != null)
                item.setCreativeTab(tab);
            GameRegistry.registerItem(item, name);
            APIiTNTPiston.log.info("[Item Registry] Registered.");
        }

    }

}
