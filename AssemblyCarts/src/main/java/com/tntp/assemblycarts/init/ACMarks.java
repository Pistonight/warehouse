package com.tntp.assemblycarts.init;

import com.tntp.assemblycarts.api.mark.MarkItemStack;
import com.tntp.assemblycarts.api.mark.MarkerUtil;
import com.tntp.assemblycarts.api.mark.OreItemStack;

public class ACMarks {
    public static void registerMarkClasses() {
        MarkerUtil.register(MarkItemStack.class);
        MarkerUtil.register(OreItemStack.class);
    }
}
