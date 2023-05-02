package com.tntp.minecraftmodapi.util;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;

public class NBTUtil {
  /**
   * Writes a compressed NBTTagCompound to this buffer
   */
  public static void writeNBTTagCompoundToBuffer(ByteBuf buf, NBTTagCompound p_150786_1_) {
    if (p_150786_1_ == null) {
      buf.writeShort(-1);
    } else {
      byte[] abyte;
      try {
        abyte = CompressedStreamTools.compress(p_150786_1_);
        buf.writeShort((short) abyte.length);
        buf.writeBytes(abyte);
      } catch (IOException e) {
        buf.writeShort((short) -1);
        e.printStackTrace();
      }

    }
  }

  /**
   * Reads a compressed NBTTagCompound from this buffer
   */
  public static NBTTagCompound readNBTTagCompoundFromBuffer(ByteBuf buf) {
    short short1 = buf.readShort();
    if (short1 < 0) {
      return null;
    } else {
      byte[] abyte = new byte[short1];
      buf.readBytes(abyte);
      try {
        return CompressedStreamTools.func_152457_a(abyte, new NBTSizeTracker(2097152L));
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
    }
  }
}
