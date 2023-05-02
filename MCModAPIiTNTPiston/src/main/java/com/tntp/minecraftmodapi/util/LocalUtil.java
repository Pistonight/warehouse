package com.tntp.minecraftmodapi.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.resources.I18n;

@SideOnly(Side.CLIENT)
public class LocalUtil {
  public static List<String> localizeList(String key, Object... objects) {
    ArrayList<String> list = new ArrayList<String>();
    for (int i = 0;; i++) {
      String k = key + i;
      String loc = I18n.format(k, objects);
      if (k.equals(loc))
        break;
      list.add(loc);
    }
    if (list.isEmpty())
      reportMissingLocalization(key);
    return list;
  }

  public static String localize(String key) {
    return localize(key, new Object[0]);
  }

  public static String localize(String key, Object... objects) {
    String localized = I18n.format(key, objects);
    if (localized.equals(key))
      reportMissingLocalization(key);
    return localized;
  }

  private static void reportMissingLocalization(String missing) {
    File f = new File("missing_local");
    if (!f.exists())
      f.mkdirs();
    f = f.toPath().resolve(missing).toFile();
    if (!f.exists())
      try {
        f.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
  }
}
