package com.tntp.assemblycarts.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.tntp.assemblycarts.api.AssemblyProcess;
import com.tntp.assemblycarts.api.RequestManager;
import com.tntp.assemblycarts.api.mark.IMarkItem;
import com.tntp.assemblycarts.api.mark.MarkerUtil;
import com.tntp.minecraftmodapi.util.LocalUtil;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class SGui extends GuiContainer {

    private String unlocalizedTitle;

    protected int tooltipX;
    protected int tooltipY;
    protected List<String> tooltips;

    private int nextDisplayTick;

    public SGui(Container container, String title) {
        super(container);

        tooltips = new ArrayList<String>();
        unlocalizedTitle = title;
    }

    public void updateScreen() {
        super.updateScreen();
        if (nextDisplayTick <= 0)
            nextDisplayTick = 20;
        nextDisplayTick--;
    }

    public boolean isNextDisplayTick() {
        return nextDisplayTick == 0;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mx, int my) {
        super.drawGuiContainerForegroundLayer(mx, my);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.fontRendererObj.drawString(getLocalizedTitle(unlocalizedTitle), 8, 6, 0);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        tooltips.clear();
    }

    @Override
    public void drawScreen(int mx, int my, float p_73863_3_) {
        super.drawScreen(mx, my, p_73863_3_);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        RenderHelper.disableStandardItemLighting();
        GL11.glPushMatrix();
        GL11.glTranslatef((float) guiLeft, (float) guiTop, 0.0F);
        drawGuiContainerTopLayer(mx, my);
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        RenderHelper.enableStandardItemLighting();
    }

    protected void drawGuiContainerTopLayer(int mx, int my) {
        if (!tooltips.isEmpty()) {
            drawHoveringText(tooltips, tooltipX, tooltipY, fontRendererObj);
        }
    }

    public boolean withIn(int c1, int w1, int c2, int w2) {
        return (c2 >= c1 && c2 <= c1 + w1) || (c2 + w2 >= c1 && c2 + w2 <= c1 + w1);
    }

    public boolean withInRect(int x, int y, int fromX, int fromY, int w, int h) {
        return x >= fromX && y >= fromY && x <= fromX + w && y <= fromY + h;
    }

    protected void drawMarkItem(IMarkItem mark, int x, int y, int mx, int my, List<String> extraTooltip, int multiplier) {
        if (isNextDisplayTick() && mark != null)
            mark.nextDisplayStack();
        ItemStack stack = MarkerUtil.getDisplayStackSafe(mark);
        if (stack != null)
            stack.stackSize *= multiplier;
        drawItemStack(stack, x, y, mx, my, extraTooltip);// TODO: fluid stack
    }

    protected void drawItemStack(ItemStack stack, int x, int y, int mx, int my, List<String> extraTooltip) {
        if (stack != null) {
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            itemRender.renderItemAndEffectIntoGUI(fontRendererObj, this.mc.getTextureManager(), stack, x, y);
            itemRender.renderItemOverlayIntoGUI(fontRendererObj, this.mc.getTextureManager(), stack, x, y);
            GL11.glEnable(GL11.GL_BLEND);
            RenderHelper.disableStandardItemLighting();
            if (withInRect(mx, my, x + guiLeft, y + guiTop, 16, 16)) {
                this.setStackTooltip(stack);
                tooltips.addAll(extraTooltip);
                tooltipX = mx - guiLeft;
                tooltipY = my - guiTop;
            }
        }
        if (withInRect(mx, my, x + guiLeft, y + guiTop, 16, 16)) {
            drawHighlightRect(x, y);
        }
    }

    protected void drawHighlightRect(int x, int y) {
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glColorMask(true, true, true, false);
        this.drawGradientRect(x, y, x + 16, y + 16, -2130706433, -2130706433);
        GL11.glColorMask(true, true, true, true);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    protected void drawCenteredStringNoShadow(FontRenderer fr, String text, int x, int y, int color) {
        fr.drawString(text, x - fr.getStringWidth(text) / 2, y, color);
    }

    protected void playButtonSound() {
        mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
    }

    /**
     * Add wheel events.
     */
    @Override
    public void handleMouseInput() {
        super.handleMouseInput();
        int delta = Mouse.getEventDWheel();
        if (delta != 0) {
            int x = Mouse.getEventX() * this.width / this.mc.displayWidth;
            int y = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
            mouseWheeled(x, y, (int) Math.signum(delta));
        }
    }

    protected void mouseWheeled(int x, int y, int wheel) {

    }

    protected String getLocalizedTitle(String unlocalizedTitle) {
        return LocalUtil.localize(unlocalizedTitle);
    }

    @SuppressWarnings("rawtypes")
    protected void setStackTooltip(ItemStack stack) {
        List list = stack.getTooltip(this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips);

        for (int k = 0; k < list.size(); ++k) {
            if (k == 0) {
                tooltips.add(stack.getRarity().rarityColor + (String) list.get(k));
            } else {
                tooltips.add(EnumChatFormatting.GRAY + (String) list.get(k));
            }
        }
    }

    protected void drawProcess(AssemblyProcess process, int mouseX, int mouseY, int multiplier) {
//        ItemStack main = null;
//        if (process != null) {
//            IMarkItem mark = process.getMainOutput();
//            if (mark != null && isNextDisplayTick()) {
//                mark.nextDisplayStack();
//            }
//            main = MarkerUtil.getDisplayStackSafe(mark);
//            if (main != null)
//                main.stackSize *= multiplier;
//        }

        drawBigStack(process == null ? null : process.getMainOutput(), mouseX, mouseY, multiplier);

        // GL11.glTranslatef(-guiLeft, -guiTop, 0);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                this.drawMarkItem(process == null ? null : process.getInput(i * 6 + j), 62 + j * 18, 18 + i * 18, mouseX, mouseY, Collections.EMPTY_LIST, multiplier);
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                this.drawMarkItem(process == null ? null : process.getOtherOutput(i * 9 + j), 8 + j * 18, 90 + i * 18, mouseX, mouseY, Collections.EMPTY_LIST, multiplier);
            }
        }
        RenderHelper.enableGUIStandardItemLighting();
    }

    protected void drawBigStack(IMarkItem mark, int mx, int my, int multiplier) {
        if (isNextDisplayTick() && mark != null)
            mark.nextDisplayStack();
        ItemStack stack = MarkerUtil.getDisplayStackSafe(mark);
        if (stack != null)
            stack.stackSize *= multiplier;
        if (stack != null) {
            GL11.glPushMatrix();
            GL11.glTranslatef(15, 28, 0);
            GL11.glScalef(2, 2, 1);
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            itemRender.renderItemAndEffectIntoGUI(fontRendererObj, this.mc.getTextureManager(), stack, 0, 0);
            itemRender.renderItemOverlayIntoGUI(fontRendererObj, this.mc.getTextureManager(), stack, 0, 0);
            GL11.glEnable(GL11.GL_BLEND);
            RenderHelper.disableStandardItemLighting();
            if (withInRect(mx, my, 15 + guiLeft, 28 + guiTop, 32, 32)) {
                this.setStackTooltip(stack);
                tooltipX = mx - guiLeft;
                tooltipY = my - guiTop;
            }
            GL11.glPopMatrix();
        }
        if (withInRect(mx, my, 14 + guiLeft, 27 + guiTop, 34, 34)) {
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glColorMask(true, true, true, false);
            this.drawGradientRect(14, 27, 48, 61, -2130706433, -2130706433);
            GL11.glColorMask(true, true, true, true);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
        }
        RenderHelper.enableGUIStandardItemLighting();
    }

    protected void drawRequestManagerStacks(RequestManager rm, int mx, int my) {
        List<IMarkItem> need = rm.getNeed();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                int index = i * 6 + j;
                if (index < need.size())
                    this.drawMarkItem(need.get(index), 62 + j * 18, 18 + i * 18, mx, my, Collections.EMPTY_LIST, 1);
                else
                    this.drawItemStack(null, 62 + j * 18, 18 + i * 18, mx, my, Collections.EMPTY_LIST);
            }
        }
        RenderHelper.enableGUIStandardItemLighting();
    }

}
