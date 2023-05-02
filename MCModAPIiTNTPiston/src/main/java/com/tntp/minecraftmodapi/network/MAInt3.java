package com.tntp.minecraftmodapi.network;

import io.netty.buffer.ByteBuf;

public abstract class MAInt3<M extends MAInt2<M>> extends MAInt2<M> {
  private int i3;

  public MAInt3(int int1, int int2, int int3) {
    super(int1, int2);
    i3 = int3;
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    super.fromBytes(buf);
    i3 = buf.readInt();
  }

  @Override
  public void toBytes(ByteBuf buf) {
    super.toBytes(buf);
    buf.writeInt(i3);
  }

  public int getI3() {
    return i3;
  }

}
