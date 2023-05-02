package com.tntp.assemblycarts.network;

import com.tntp.assemblycarts.gui.SContainer;
import com.tntp.minecraftmodapi.network.MAInt3;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Handles Gui slot click for all gui classes
 * 
 * @author iTNTPiston
 *
 */
public class MSGuiSlotClick extends MAInt3<MSGuiSlotClick> {

    public MSGuiSlotClick(int windowID, int slot, int mouse) {
        super(windowID, slot, mouse);
    }

    public MSGuiSlotClick() {
        super(0, 0, 0);
    }

    @Override
    public IMessage onMessage(MSGuiSlotClick message, MessageContext ctx) {
        EntityPlayer player = ctx.getServerHandler().playerEntity;
        if (player.openContainer.windowId == message.getI1()) {
            SContainer cont = (SContainer) player.openContainer;
            cont.processSlotClick(message.getI2(), message.getI3());
        }
        return null;
    }

}
