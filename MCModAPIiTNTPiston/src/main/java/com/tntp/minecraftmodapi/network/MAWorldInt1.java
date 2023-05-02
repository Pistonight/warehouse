package com.tntp.minecraftmodapi.network;

import io.netty.buffer.ByteBuf;

public abstract class MAWorldInt1<M extends MAInt4<M>> extends MAInt4<M> {
    private int int1;

    public MAWorldInt1(int dim, int x, int y, int z, int int1) {
        super(dim, x, y, z);
        this.int1 = int1;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        super.fromBytes(buf);
        int1 = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        super.toBytes(buf);
        buf.writeInt(int1);
    }

    public int getDimensionID() {
        return super.getI1();
    }

    public int getX() {
        return super.getI2();
    }

    public int getY() {
        return super.getI3();
    }

    public int getZ() {
        return super.getI4();
    }

    public int getInt1() {
        return int1;
    }

    public int getI1() {
        return int1;
    }

}
