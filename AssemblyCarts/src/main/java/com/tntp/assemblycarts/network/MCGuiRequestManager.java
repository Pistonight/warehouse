package com.tntp.assemblycarts.network;

import com.tntp.assemblycarts.api.IRequester;
import com.tntp.assemblycarts.api.RequestManager;
import com.tntp.minecraftmodapi.network.MANBT1;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class MCGuiRequestManager extends MANBT1<MCGuiRequestManager> {
    protected int windowID;

    public MCGuiRequestManager(int windowID, NBTTagCompound requestManager) {
        super(requestManager);
        this.windowID = windowID;
    }

    public MCGuiRequestManager() {
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
    public IMessage onMessage(MCGuiRequestManager message, MessageContext ctx) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        if (player.openContainer.windowId == message.windowID) {
            if (player.openContainer instanceof IRequester) {
                RequestManager rm = ((IRequester) player.openContainer).getRequestManager();
                rm.readFromNBT(message.getNBT1());
            }
        }
        return null;
    }

}
