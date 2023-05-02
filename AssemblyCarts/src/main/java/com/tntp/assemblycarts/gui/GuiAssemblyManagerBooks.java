package com.tntp.assemblycarts.gui;

import org.lwjgl.opengl.GL11;

import com.tntp.assemblycarts.core.AssemblyCartsMod;
import com.tntp.assemblycarts.gui.container.ContainerAssemblyManagerBooks;

import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiAssemblyManagerBooks extends SGui {
    private static final ResourceLocation background = new ResourceLocation(AssemblyCartsMod.MODID, "textures/guis/assembly_manager_book.png");

    public GuiAssemblyManagerBooks(IInventory player, Object manager) {
        super(new ContainerAssemblyManagerBooks(player, manager), "ac.gui.assembly_manager_books");
        xSize = 176;
        ySize = 132;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        tooltips.clear();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

}
