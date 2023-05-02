package com.tntp.assemblycarts.network;

import com.tntp.assemblycarts.api.mark.IMarker;
import com.tntp.assemblycarts.api.mark.MarkManager;
import com.tntp.minecraftmodapi.network.MANBT1;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class MCGuiMarkManager extends MANBT1<MCGuiMarkManager> {
    protected int windowID;

    public MCGuiMarkManager(int windowID, NBTTagCompound markManager) {
        super(markManager);
        this.windowID = windowID;
    }

    public MCGuiMarkManager() {
        super(null);
    }

    public void toBytes(ByteBuf buf) {
        super.toBytes(buf);
        buf.writeInt(windowID);
    }

    public void fromBytes(ByteBuf buf) {
        super.fromBytes(buf);
        windowID = buf.readInt();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(MCGuiMarkManager message, MessageContext ctx) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        if (player.openContainer.windowId == message.windowID) {
            if (player.openContainer instanceof IMarker) {
                MarkManager markManager = ((IMarker) player.openContainer).getMarkManager();
                markManager.readFromNBT(message.getNBT1());
            }
        }
        return null;
    }

}
