package com.tntp.assemblycarts.gui;

import org.lwjgl.opengl.GL11;

import com.tntp.assemblycarts.api.AssemblyProcess;
import com.tntp.assemblycarts.core.AssemblyCartsMod;
import com.tntp.assemblycarts.gui.container.ContainerProcessBook;
import com.tntp.assemblycarts.network.ACNtwk;
import com.tntp.assemblycarts.network.MSGuiSlotClick;

import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiProcessBook extends SGui {
    static final ResourceLocation background = new ResourceLocation(AssemblyCartsMod.MODID, "textures/guis/process_book.png");

    public GuiProcessBook(IInventory playerInventory, Object proc) {
        super(new ContainerProcessBook(playerInventory, proc), "ac.gui.processbook");
        xSize = 176;
        ySize = 222;

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
        AssemblyProcess process = ((ContainerProcessBook) this.inventorySlots).getProcess();
        drawProcess(process, mx, my, 1);
    }

    @Override
    protected void mouseClicked(int x, int y, int button) {
        super.mouseClicked(x, y, button);
        int processSlotID = -1;
        x -= guiLeft;
        y -= guiTop;
        if (withInRect(x, y, 14, 27, 32, 32)) {
            processSlotID = 0;
        } else if (withInRect(x, y, 61, 17, 108, 54)) {
            x -= 61;
            y -= 17;
            int i = y / 18;
            int j = x / 18;
            processSlotID = 1 + i * 6 + j;
        } else if (withInRect(x, y, 7, 89, 162, 36)) {
            x -= 7;
            y -= 89;
            int i = y / 18;
            int j = x / 18;
            processSlotID = 19 + i * 9 + j;
        }
        if (processSlotID != -1) {
            ((ContainerProcessBook) this.inventorySlots).processSlotClick(processSlotID, button);
            ACNtwk.sendToServer(new MSGuiSlotClick(this.inventorySlots.windowId, processSlotID, button));
        }
        // System.out.println(processSlotID);
    }

}
