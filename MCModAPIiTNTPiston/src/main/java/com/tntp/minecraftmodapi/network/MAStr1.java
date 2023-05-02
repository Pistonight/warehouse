package com.tntp.minecraftmodapi.network;

import io.netty.buffer.ByteBuf;

public abstract class MAStr1<M extends MAStr1<M>> extends MessageAPIiTNTPiston<M> {
  private String str1;

  public MAStr1(String str1) {
    this.str1 = str1;
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    str1 = readStringFromBuf(buf);
  }

  @Override
  public void toBytes(ByteBuf buf) {
    writeStringToBuf(buf, str1);
  }

  public String getStr1() {
    return str1;
  }

}
