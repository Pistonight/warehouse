package com.tntp.assemblycarts.api;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class Assemblium {
    public static final ToolMaterial ASSEMBLIUM = EnumHelper.addToolMaterial("ASSEMBLIUM", 2, 2000, 8, 0, 7);
    public static final Material BLOCK_MATERIAL = new MaterialAssemblium();

    private static class MaterialAssemblium extends Material {

        public MaterialAssemblium() {
            super(MapColor.greenColor);
            setRequiresTool();
        }

    }
}
