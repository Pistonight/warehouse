package com.tntp.assemblycarts.network;

import com.tntp.assemblycarts.api.IProvider;
import com.tntp.assemblycarts.api.ProvideManager;
import com.tntp.minecraftmodapi.network.MANBT1;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class MCGuiProvideManager extends MANBT1<MCGuiProvideManager> {
    protected int windowID;

    public MCGuiProvideManager(int windowID, NBTTagCompound provideManager) {
        super(provideManager);
        this.windowID = windowID;
    }

    public MCGuiProvideManager() {
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
    public IMessage onMessage(MCGuiProvideManager message, MessageContext ctx) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        if (player.openContainer.windowId == message.windowID) {
            if (player.openContainer instanceof IProvider) {
                ProvideManager pm = ((IProvider) player.openContainer).getProvideManager();
                pm.readFromNBT(message.getNBT1());
            }
        }
        return null;
    }

}
