package com.tntp.minecraftmodapi.block;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.tntp.minecraftmodapi.APIiTNTPiston;
import com.tntp.minecraftmodapi.SuperRegister;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class RegBlock extends SuperRegister implements IBlockRegisterFactory {
    private CreativeTabs tab;

    public RegBlock() {
        APIiTNTPiston.log.info("Registering Blocks for " + modid);
    }

    @Override
    public IBlockRegisterFactory creativeTabs(CreativeTabs tab) {
        this.tab = tab;
        APIiTNTPiston.log.info("[Block Registry] Registered CreativeTabs >> " + tab);
        return this;
    }

    @Override
    public IBlockRegister of(Block block, String name) {
        APIiTNTPiston.log.info("[Block Registry] Block >> " + name);
        IBlockRegister reg = new Reg(block, name);
        if (block instanceof BlockAPIiTNTPiston)
            ((BlockAPIiTNTPiston) block).preBlockRegister(reg);
        return reg;
    }

    private static void injectBehavior(List<IBlockBehavior> behaviors, Block b) throws Exception {
        if (b instanceof BlockBehaviorAPIiTNTPiston) {
            Field field = BlockBehaviorAPIiTNTPiston.class.getDeclaredField("behaviors");
            field.setAccessible(true);
            IBlockBehavior[] be = new IBlockBehavior[behaviors.size()];
            be = behaviors.toArray(be);
            field.set(b, be);
            APIiTNTPiston.log.info("[Block Registry] >> Behaviors Injected.");
        }
    }

    private class Reg implements IBlockRegister {
        private Block block;
        private String name;
        private String textureName;
        private Class<? extends ItemBlock> itemBlock;
        private List<IBlockBehavior> behaviorList;
        private boolean hide = false;

        private Reg(Block b, String n) {
            block = b;
            name = n;
            behaviorList = new ArrayList<>();
        }

        @Override
        public IBlockRegister item(Class<? extends ItemBlock> itemBlockClass) {
            itemBlock = itemBlockClass;
            return this;
        }

        @Override
        public IBlockRegister texture(String name) {
            this.textureName = name;
            return this;
        }

        @Override
        public IBlockRegister hide() {
            hide = true;
            return this;
        }

        @Override
        public void register() {

            block.setBlockName(name);
            if (textureName == null)
                textureName = name;
            block.setBlockTextureName(modid + ":" + textureName);
            if (!hide && tab != null)
                block.setCreativeTab(tab);

            try {
                injectBehavior(behaviorList, block);
            } catch (Exception e) {
                e.printStackTrace();
                APIiTNTPiston.log.warn("[Block Registry] Behaviors were not injected!");
            }

            if (itemBlock != null) {
                GameRegistry.registerBlock(block, itemBlock, name);
            } else {
                GameRegistry.registerBlock(block, name);
            }
            APIiTNTPiston.log.info("[Block Registry] Registered.");

        }

        @Override
        public IBlockRegister behave(IBlockBehavior behavior) {
            if (!(block instanceof BlockBehaviorAPIiTNTPiston)) {
                APIiTNTPiston.log.error("[Block Registry] Not Behavior Block!");
                return this;
            }
            behaviorList.add(behavior);
            APIiTNTPiston.log.info("[Block Registry] >> BEHAVE " + behavior.getClass().getSimpleName());
            return this;
        }

    }

}
