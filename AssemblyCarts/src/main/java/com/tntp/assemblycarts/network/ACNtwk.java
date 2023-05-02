package com.tntp.assemblycarts.network;

import com.tntp.minecraftmodapi.network.IMessageRegisterFactory.ChannelHolder;
import com.tntp.minecraftmodapi.network.MessageAPIiTNTPiston;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraft.entity.player.EntityPlayerMP;

public abstract class ACNtwk {
    @ChannelHolder
    private static SimpleNetworkWrapper network = null;

    public static void sendToServer(MessageAPIiTNTPiston<?> mes) {
        network.sendToServer(mes);
    }

    public static void sendToAll(MessageAPIiTNTPiston<?> mes) {
        network.sendToAll(mes);
    }

    public static void sendToAllAround(MessageAPIiTNTPiston<?> mes, int dimension, double x, double y, double z, double range) {
        network.sendToAllAround(mes, new TargetPoint(dimension, x, y, z, range));
    }

    public static void sendTo(MessageAPIiTNTPiston<?> mes, EntityPlayerMP player) {
        network.sendTo(mes, player);
    }

    public static void sendToDimension(MessageAPIiTNTPiston<?> mes, int dimension) {
        network.sendToDimension(mes, dimension);
    }

}
