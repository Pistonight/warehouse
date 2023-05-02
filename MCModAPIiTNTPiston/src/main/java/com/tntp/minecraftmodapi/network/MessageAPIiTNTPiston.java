package com.tntp.minecraftmodapi.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import io.netty.buffer.ByteBuf;

/**
 * Super class for all messages, they are themselves handlers if the message is
 * received on server side, otherwise a separate handler class is required
 * 
 * @author iTNTPiston
 *
 * @param <REQ>
 */
public abstract class MessageAPIiTNTPiston<REQ extends MessageAPIiTNTPiston<REQ>> implements IMessage, IMessageHandler<REQ, IMessage> {
  protected static void writeStringToBuf(ByteBuf buf, String str) {
    buf.writeInt(str.length());
    for (char c : str.toCharArray()) {
      buf.writeChar(c);
    }
  }

  protected static String readStringFromBuf(ByteBuf buf) {
    int length = buf.readInt();
    char[] arr = new char[length];
    for (int i = 0; i < length; i++) {
      arr[i] = buf.readChar();
    }
    return String.valueOf(arr);
  }
}
