package com.tntp.minecraftmodapi.network;

import io.netty.buffer.ByteBuf;

public abstract class MAInt2<M extends MAInt2<M>> extends MAInt1<M> {
  private int i2;

  public MAInt2(int int1, int int2) {
    super(int1);
    i2 = int2;
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    super.fromBytes(buf);
    i2 = buf.readInt();
  }

  @Override
  public void toBytes(ByteBuf buf) {
    super.toBytes(buf);
    buf.writeInt(i2);
  }

  public int getI2() {
    return i2;
  }

}
