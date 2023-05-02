package com.tntp.assemblycarts.gui;

import java.util.Arrays;

import org.lwjgl.opengl.GL11;

import com.tntp.assemblycarts.core.AssemblyCartsMod;
import com.tntp.assemblycarts.gui.container.ContainerAssemblyPort;
import com.tntp.assemblycarts.network.ACNtwk;
import com.tntp.assemblycarts.network.MSGuiSlotClick;
import com.tntp.assemblycarts.tileentity.TileAssemblyPort;
import com.tntp.minecraftmodapi.util.LocalUtil;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiAssemblyPort extends SGui {
    private static final ResourceLocation background = new ResourceLocation(AssemblyCartsMod.MODID, "textures/guis/assembly_port.png");

    public GuiAssemblyPort(IInventory player, Object tile) {
        super(new ContainerAssemblyPort(player, tile), ((IInventory) tile).getInventoryName());
        xSize = 176;
        ySize = 168;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        tooltips.clear();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mx, int my) {
        super.drawGuiContainerForegroundLayer(mx, my);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        TileAssemblyPort tile = ((ContainerAssemblyPort) this.inventorySlots).getTile();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.drawMarkItem(tile.getMarkedItemStack(i * 3 + j), 26 + j * 18, 18 + i * 18, mx, my, Arrays.asList(LocalUtil.localize("ac.tooltip.mark")), 1);
            }
        }

        RenderHelper.enableGUIStandardItemLighting();

    }

    @Override
    protected void mouseClicked(int x, int y, int button) {
        super.mouseClicked(x, y, button);
        x -= guiLeft;
        y -= guiTop;
        if (withInRect(x, y, 25, 17, 54, 54)) {
            x -= 25;
            y -= 17;
            int i = y / 18;
            int j = x / 18;
            int processSlotID = i * 3 + j;
            ((ContainerAssemblyPort) this.inventorySlots).processSlotClick(processSlotID, button);
            ACNtwk.sendToServer(new MSGuiSlotClick(this.inventorySlots.windowId, processSlotID, button));

        }
        // System.out.println(processSlotID);
    }

}
