package com.tntp.minecraftmodapi.network;

import io.netty.buffer.ByteBuf;

public abstract class MAInt4<M extends MAInt3<M>> extends MAInt3<M> {
  private int i4;

  public MAInt4(int int1, int int2, int int3, int int4) {
    super(int1, int2, int3);
    i4 = int4;
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    super.fromBytes(buf);
    i4 = buf.readInt();
  }

  @Override
  public void toBytes(ByteBuf buf) {
    super.toBytes(buf);
    buf.writeInt(i4);
  }

  public int getI4() {
    return i4;
  }

}
