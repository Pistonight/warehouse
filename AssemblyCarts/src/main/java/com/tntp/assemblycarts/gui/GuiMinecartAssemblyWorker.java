package com.tntp.assemblycarts.gui;

import org.lwjgl.opengl.GL11;

import com.tntp.assemblycarts.api.mark.IMarkItem;
import com.tntp.assemblycarts.entity.EntityMinecartAssemblyWorker;
import com.tntp.assemblycarts.gui.container.ContainerMinecartAssemblyWorker;

import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiMinecartAssemblyWorker extends SGui {
    private static final ResourceLocation background = GuiProcessBook.background;

    public GuiMinecartAssemblyWorker(IInventory playerInventory, Object cart) {
        super(new ContainerMinecartAssemblyWorker(playerInventory, cart), ((IInventory) cart).getInventoryName());
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
        EntityMinecartAssemblyWorker cart = ((ContainerMinecartAssemblyWorker) this.inventorySlots).getCart();
        IMarkItem main = cart.getRequestManager().getCraftingTarget();
        if (main == null)
            main = cart.getProvideManager().getProvideTarget();

        drawBigStack(main, mx, my, 1);
        drawRequestManagerStacks(cart.getRequestManager(), mx, my);
    }
}
