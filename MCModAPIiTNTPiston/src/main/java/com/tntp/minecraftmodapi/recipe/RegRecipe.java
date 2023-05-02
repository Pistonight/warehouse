package com.tntp.minecraftmodapi.recipe;

import java.util.ArrayList;
import java.util.HashSet;

import com.tntp.minecraftmodapi.APIiTNTPiston;
import com.tntp.minecraftmodapi.SuperRegister;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RegRecipe extends SuperRegister implements IRecipeRegisterFactory {
    public RegRecipe() {
        APIiTNTPiston.log.info("Registering Recipes for " + modid);
    }

    @Override
    public IRecipeRegister ofCrafting(ItemStack output) {
        return new RegCrafting(output);
    }

    @Override
    public ISmeltingRegister ofSmelting(ItemStack output) {
        return new RegSmelting(output);
    }

    private class RegCrafting implements IRecipeRegister {
        private String pattern;
        private ItemStack output;
        private Object[] inputs;

        RegCrafting(ItemStack out) {
            output = out;
            pattern = null;
        }

        @Override
        public void register() {
            IRecipe recipe;
            if (pattern != null) {
                APIiTNTPiston.log.info("[Recipe Registry] SHAPED >> " + output.getItem().getUnlocalizedName());
                ArrayList<Object> list = new ArrayList<Object>();
                String[] pat = pattern.split(",");
                for (String p : pat) {
                    list.add(p);
                }
                HashSet<Character> set = new HashSet<Character>();
                int objIndex = 0;
                for (int i = 0; i < pattern.length(); i++) {
                    char c = pattern.charAt(i);
                    if (c == ',' || c == ' ')
                        continue;
                    if (set.contains(c))
                        continue;
                    list.add(c);
                    list.add(inputs[objIndex++]);
                    set.add(c);
                }
                Object[] param = new Object[list.size()];
                for (int i = 0; i < param.length; i++) {
                    param[i] = list.get(i);
                }
                recipe = new ShapedOreRecipe(output, param);
            } else {
                APIiTNTPiston.log.info("[Recipe Registry] SHAPELESS >> " + output.getItem().getUnlocalizedName());
                recipe = new ShapelessOreRecipe(output, inputs);
            }
            GameRegistry.addRecipe(recipe);
        }

        @Override
        public IRecipeRegister pattern(String pattern) {
            this.pattern = pattern;
            return this;
        }

        @Override
        public IRecipeRegister shapeless() {
            this.pattern = null;
            return this;
        }

        @Override
        public IRecipeRegister input(Object... objects) {
            this.inputs = objects;
            return this;
        }

    }

    private class RegSmelting implements ISmeltingRegister {
        private ArrayList<ItemStack> inputs;
        private ItemStack output;
        private float xp;

        RegSmelting(ItemStack out) {
            this.output = out;
            inputs = new ArrayList<>();
        }

        @Override
        public IRecipeRegister pattern(String pattern) {
            APIiTNTPiston.log.warn("[Recipe Registry]Invalid Call on pattern for smelting!");
            return this;
        }

        @Override
        public IRecipeRegister shapeless() {
            APIiTNTPiston.log.warn("[Recipe Registry]Invalid Call on shapeless for smelting!");
            return this;
        }

        @Override
        public IRecipeRegister input(Object... objects) {
            for (Object obj : objects) {
                if (obj instanceof ItemStack) {
                    inputs.add((ItemStack) obj);
                } else if (obj instanceof Item) {
                    inputs.add(new ItemStack((Item) obj));
                } else if (obj instanceof Block) {
                    inputs.add(new ItemStack((Item) obj));
                } else if (obj instanceof ItemStack[]) {
                    for (ItemStack is : (ItemStack[]) obj) {
                        if (is != null)
                            inputs.add(is);
                    }
                } else if (obj instanceof String) {
                    this.inputs.addAll(OreDictionary.getOres((String) obj));
                }
            }
            return this;
        }

        @Override
        public ISmeltingRegister xp(float xp) {
            this.xp = xp;
            return this;
        }

        @Override
        public void register() {
            for (ItemStack input : inputs) {
                APIiTNTPiston.log.info("[Recipe Registry] SMELT >> " + input.getUnlocalizedName() + " -> " + output.getUnlocalizedName());
                GameRegistry.addSmelting(input, output, xp);
            }

        }
    }

}
