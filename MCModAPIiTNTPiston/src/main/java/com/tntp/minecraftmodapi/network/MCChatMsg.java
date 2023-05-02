package com.tntp.minecraftmodapi.network;

import com.tntp.minecraftmodapi.util.ClientUtil;
import com.tntp.minecraftmodapi.util.LocalUtil;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;

public class MCChatMsg extends MAStr1<MCChatMsg> {
    private boolean localized;

    public MCChatMsg(String str1, boolean localized) {
        super(str1);
        this.localized = localized;
    }

    public MCChatMsg() {
        super(null);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        super.toBytes(buf);
        buf.writeBoolean(localized);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        super.fromBytes(buf);
        localized = buf.readBoolean();
    }

    public boolean isLocalized() {
        return localized;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(MCChatMsg message, MessageContext ctx) {
        String mes = message.isLocalized() ? message.getStr1() : LocalUtil.localize(message.getStr1());
        ClientUtil.printChatMessage(mes);
        return null;
    }

}
