package com.tntp.minecraftmodapi.network;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.tntp.minecraftmodapi.APIiTNTPiston;
import com.tntp.minecraftmodapi.SuperRegister;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class RegMessage extends SuperRegister implements IMessageRegisterFactory {
    private SimpleNetworkWrapper wrapper;
    private int id;

    public RegMessage() {
        APIiTNTPiston.log.info("Registering Messages for " + modid);
        wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(modid);
        id = 0;
    }

    @Override
    public <REQ extends MessageAPIiTNTPiston<REQ>> IMessageRegister of(Class<REQ> clazz) {
        APIiTNTPiston.log.info("[Ntwk Registry] Message >> " + clazz.getCanonicalName());
        return new Reg<>(clazz);
    }

    private <REQ extends MessageAPIiTNTPiston<REQ>> void registerMessage(Class<REQ> c, Side side) throws Exception {
        try {
            REQ req = c.newInstance();
            wrapper.registerMessage(req, c, id++, side);
        } catch (Exception e) {
            APIiTNTPiston.log.error("[Ntwk Registry] Message Must Have Default Constructor: " + c.getSimpleName());
            throw e;
        }
    }

    private class Reg<REQ extends MessageAPIiTNTPiston<REQ>> implements IMessageRegister {
        Class<REQ> clazz;
        Side s = null;

        Reg(Class<REQ> c) {
            clazz = c;
        }

        @Override
        public void register() {
            if (s == null) {
                if (clazz.getSimpleName().startsWith("MC"))
                    s = Side.CLIENT;
                else if (clazz.getSimpleName().startsWith("MS"))
                    s = Side.SERVER;
                APIiTNTPiston.log.info("[Ntwk Registry] Auto-Set-Side >> " + s);
            }
            if (s == null) {
                APIiTNTPiston.log.error("[Ntwk Registry] Side Not Specified: " + clazz.getCanonicalName());
                throw new RuntimeException("Message Registration Failed!");
            }
            try {
                registerMessage(clazz, s);
            } catch (Exception e) {
                throw new RuntimeException("Message Registration Failed!");
            }
        }

        @Override
        public IMessageRegister side(Side side) {
            s = side;
            return this;
        }

    }

    @Override
    public RegMessage injectTo(Class<?> channelHolder) {
        APIiTNTPiston.log.info("[Ntwk Injector] Injecting Network Wrapper to " + channelHolder.getCanonicalName());
        try {
            Field[] fields = channelHolder.getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);
                Annotation[] annos = f.getDeclaredAnnotations();
                for (Annotation a : annos) {
                    if (a.annotationType() == ChannelHolder.class) {
                        APIiTNTPiston.log.info("[Ntwk Injector] >> " + f.toString());
                        // Remove final modifier
                        Field modifiers = Field.class.getDeclaredField("modifiers");
                        modifiers.setAccessible(true);
                        modifiers.setInt(f, f.getModifiers() & (~Modifier.FINAL));
                        f.set(null, wrapper);
                        // Add final modifier
                        modifiers.setInt(f, f.getModifiers() & Modifier.FINAL);
                        return this;
                    }
                }
            }
            APIiTNTPiston.log.warn("[Ntwk Injector] Annotation Not Found!");
            return this;
        } catch (Exception e) {
            e.printStackTrace();
            APIiTNTPiston.log.error("[Ntwk Injector] Exception: " + e.getClass().getCanonicalName());
            APIiTNTPiston.log.error("[Ntwk Injector] Network Wrapper not injected!");
            throw new RuntimeException("Network Wrapper Failed to be Injected");
        } finally {
            APIiTNTPiston.log.info("[Ntwk Injector] Injected Network Wrapper");
        }

    }

}
