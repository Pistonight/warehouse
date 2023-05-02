package com.tntp.assemblycarts.init;

import com.tntp.assemblycarts.network.ACNtwk;
import com.tntp.assemblycarts.network.MCGuiMarkManager;
import com.tntp.assemblycarts.network.MCGuiProvideManager;
import com.tntp.assemblycarts.network.MCGuiRequestManager;
import com.tntp.assemblycarts.network.MSGuiSlotClick;
import com.tntp.minecraftmodapi.APIiTNTPiston;
import com.tntp.minecraftmodapi.network.IMessageRegisterFactory;
import com.tntp.minecraftmodapi.network.MCChatMsg;

public class ACNetworkInit {
    public static void loadNetwork() {
        IMessageRegisterFactory reg = APIiTNTPiston.newMessageRegister().injectTo(ACNtwk.class);
        reg.of(MCChatMsg.class).register();
        reg.of(MCGuiMarkManager.class).register();
        reg.of(MCGuiProvideManager.class).register();
        reg.of(MCGuiRequestManager.class).register();
        reg.of(MSGuiSlotClick.class).register();
    }
}
