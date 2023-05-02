package com.tntp.assemblycarts.init;

import com.tntp.assemblycarts.render.RenderTrack;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ACRender {
    public static void register() {
        RenderTrack render = new RenderTrack();
        int id = RenderingRegistry.getNextAvailableRenderId();
        RenderTrack.renderID = id;
        RenderingRegistry.registerBlockHandler(id, render);
    }
}
