package com.tntp.minecraftmodapi.network;

import com.tntp.minecraftmodapi.util.NBTUtil;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;

public abstract class MANBT1<M extends MANBT1<M>> extends MessageAPIiTNTPiston<M> {
    private NBTTagCompound nbt1;

    public MANBT1(NBTTagCompound nbt1) {
        this.nbt1 = nbt1;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        nbt1 = NBTUtil.readNBTTagCompoundFromBuffer(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        NBTUtil.writeNBTTagCompoundToBuffer(buf, nbt1);
    }

    public NBTTagCompound getNBT1() {
        return nbt1;
    }

}
