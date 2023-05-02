package com.tntp.minecraftmodapi.util;

public class DirUtil {
  public static final int DOWN_MY = 0;
  public static final int UP_PY = 1;
  public static final int NORTH_MZ = 2;
  public static final int SOUTH_PZ = 3;
  public static final int WEST_MX = 4;
  public static final int EAST_PX = 5;
  public static final int[] ALL_DIR = { 0, 1, 2, 3, 4, 5 };
  public static final int[][] OFFSETS = { { 0, -1, 0 }, { 0, 1, 0 }, { 0, 0, -1 }, { 0, 0, 1 }, { -1, 0, 0 },
      { 1, 0, 0 } };
}
