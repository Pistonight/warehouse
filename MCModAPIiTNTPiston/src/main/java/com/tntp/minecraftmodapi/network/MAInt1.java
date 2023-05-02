package com.tntp.minecraftmodapi.network;

import io.netty.buffer.ByteBuf;

public abstract class MAInt1<M extends MAInt1<M>> extends MessageAPIiTNTPiston<M> {
  private int i1;

  public MAInt1(int int1) {
    i1 = int1;
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    i1 = buf.readInt();
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeInt(i1);
  }

  public int getI1() {
    return i1;
  }

}
