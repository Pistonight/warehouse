package com.tntp.minecraftmodapi.util;

import org.lwjgl.input.Keyboard;

public class KeyUtil {
  public static boolean isCtrlDown() {
    return Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL);
  }

  public static boolean isShiftDown() {
    return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
  }
}
