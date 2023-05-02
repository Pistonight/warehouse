package com.tntp.minecraftmodapi.network;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

public interface IMessageRegisterFactory {
    /**
     * Start the registration of a message
     * 
     * @param clazz
     * @return
     */
    <REQ extends MessageAPIiTNTPiston<REQ>> IMessageRegister of(Class<REQ> clazz);

    /**
     * Inject the network channel to a static field in that class with ChannelHolder
     * annotation
     * 
     * @param channelHolder
     */
    IMessageRegisterFactory injectTo(Class<?> channelHolder);

    @Retention(value = RUNTIME)
    public @interface ChannelHolder {

    }

}
