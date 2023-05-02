package com.tntp.assemblycarts.gui;

import org.lwjgl.opengl.GL11;

import com.tntp.assemblycarts.api.AssemblyProcess;
import com.tntp.assemblycarts.api.RequestManager;
import com.tntp.assemblycarts.core.AssemblyCartsMod;
import com.tntp.assemblycarts.gui.container.ContainerAssemblyManager;
import com.tntp.assemblycarts.network.ACNtwk;
import com.tntp.assemblycarts.network.MSGuiSlotClick;
import com.tntp.minecraftmodapi.util.LocalUtil;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiAssemblyManager extends SGui {
    private static final ResourceLocation background = new ResourceLocation(AssemblyCartsMod.MODID, "textures/guis/assembly_manager.png");
    private static final int[] INCREMENTS = { 1, 16, 64, 256 };

    private static class MultiplierButton extends GuiButton {
        int index;

        MultiplierButton(int i, int guiLeft, int guiTop) {
            super(i, 26 * (i % INCREMENTS.length) + 64 + guiLeft, 153 + (i / 4 * 41) + guiTop, 24, 20, (i >= INCREMENTS.length ? "" : "+") + String.valueOf(change(i)));
            index = i;
        }

        int getChange() {
            return change(index);
        }

        static int change(int index) {
            if (index >= INCREMENTS.length)
                return -INCREMENTS[index - INCREMENTS.length];
            else
                return INCREMENTS[index];
        }
    }

    private GuiTextField textField;
    private GuiButton startButton;
    private GuiButton cancelButton;

    public GuiAssemblyManager(IInventory player, Object tile) {
        super(new ContainerAssemblyManager(player, tile), ((IInventory) tile).getInventoryName());
        xSize = 176;
        ySize = 222;

    }

    @Override
    public void initGui() {
        super.initGui();
        if (((ContainerAssemblyManager) this.inventorySlots).getSelectedProcess() == null)
            ((ContainerAssemblyManager) this.inventorySlots).selectNextProcess();
        for (int i = 0; i < INCREMENTS.length * 2; i++) {
            MultiplierButton button = new MultiplierButton(i, guiLeft, guiTop);
            this.buttonList.add(button);
        }
        startButton = new GuiButton(INCREMENTS.length * 2, 8 + guiLeft, 153 + guiTop, 52, 20, LocalUtil.localize("ac.gui.start"));
        cancelButton = new GuiButton(INCREMENTS.length * 2 + 1, 8 + guiLeft, 194 + guiTop, 52, 20, LocalUtil.localize("ac.gui.cancel"));
        this.buttonList.add(startButton);
        this.buttonList.add(cancelButton);
        textField = new GuiTextField(this.fontRendererObj, 74 + guiLeft, 178 + guiTop, 52, 11);
        textField.setText("1");
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        ContainerAssemblyManager container = (ContainerAssemblyManager) this.inventorySlots;
        AssemblyProcess p = container.getSelectedProcess();
        RequestManager rm = container.getTile().getRequestManager();

        startButton.enabled = rm.isFulfilled() && p != null;
        cancelButton.enabled = !rm.isFulfilled();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        tooltips.clear();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        ContainerAssemblyManager container = (ContainerAssemblyManager) this.inventorySlots;
        if (container.selectedProcessID >= 0) {
            this.drawTexturedModalRect(guiLeft + 7 + container.selectedProcessID * 18, guiTop + 128, xSize, 0, 18, 18);
        }
        textField.drawTextBox();
        int color = 0xFF000000;
        this.fontRendererObj.drawString(LocalUtil.localize("ac.gui.processes"), 8 + guiLeft, 178 + guiTop, color);
        this.fontRendererObj.drawString(LocalUtil.localize("ac.gui.times"), 130 + guiLeft, 178 + guiTop, color);

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mx, int my) {
        super.drawGuiContainerForegroundLayer(mx, my);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        ContainerAssemblyManager container = (ContainerAssemblyManager) this.inventorySlots;
        RequestManager rm = container.getTile().getRequestManager();
        if (rm.isFulfilled()) {
            int multiplier = container.getProcessMultiplier();
            AssemblyProcess process = null;
            if (withInRect(mx - guiLeft, my - guiTop, 7, 128, 162, 18)) {
                int i = (mx - guiLeft - 7) / 18;
                process = container.getTile().getProcessBySlot(i);
            }
            if (process == null) {
                process = container.getSelectedProcess();
            }

            drawProcess(process, mx, my, multiplier);
        } else {
            drawBigStack(rm.getCraftingTarget(), mx, my, 1);
            drawRequestManagerStacks(rm, mx, my);
        }
        // GL11.glTranslatef(guiLeft, guiTop, 0);
    }

    @Override
    protected void mouseClicked(int x, int y, int button) {
        super.mouseClicked(x, y, button);
        textField.mouseClicked(x, y, button);
        x -= guiLeft;
        y -= guiTop;
        if (withInRect(x, y, 14, 27, 32, 32)) {
            this.playButtonSound();
            ((ContainerAssemblyManager) this.inventorySlots).selectNextProcess();
        } else if (withInRect(x, y, 7, 128, 162, 18)) {
            this.playButtonSound();
            int i = (x - 7) / 18;
            ((ContainerAssemblyManager) this.inventorySlots).selectProcessBySlot(i);
        }

    }

    @Override
    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);
        ContainerAssemblyManager container = (ContainerAssemblyManager) this.inventorySlots;
        if (button instanceof MultiplierButton) {
            int multiplier = container.getProcessMultiplier();
            int change = ((MultiplierButton) button).getChange();
            if (multiplier == 1 && change != 1)
                multiplier = 0;
            multiplier += change;
            container.setProcessMultiplier(multiplier);
            textField.setText(String.valueOf(container.getProcessMultiplier()));
        } else if (button == startButton) {
            System.out.println("start");
            ACNtwk.sendToServer(new MSGuiSlotClick(container.windowId, container.selectedProcessID, container.getProcessMultiplier()));
        } else if (button == cancelButton) {
            System.out.println("cancel");
            ACNtwk.sendToServer(new MSGuiSlotClick(container.windowId, -1, container.getProcessMultiplier()));
        }
    }

    protected void keyTyped(char c, int i) {
        if (textField.textboxKeyTyped(c, i)) {
            try {
                int m = Integer.parseInt(textField.getText());
                ((ContainerAssemblyManager) this.inventorySlots).setProcessMultiplier(m);
            } catch (NumberFormatException e) {
                ((ContainerAssemblyManager) this.inventorySlots).setProcessMultiplier(1);
                textField.setText("1");
            }
            return;
        }
        super.keyTyped(c, i);
    }

}
