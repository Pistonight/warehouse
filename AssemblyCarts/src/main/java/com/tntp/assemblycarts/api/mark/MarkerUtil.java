package com.tntp.assemblycarts.api.mark;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class MarkerUtil {
    private static final HashMap<String, Integer> markTypeToId = new HashMap<>();
    private static final ArrayList<IMarkItem> idToMarkType = new ArrayList<>();

    public static void register(Class<? extends IMarkItem> clazz) {
        markTypeToId.put(clazz.getCanonicalName(), idToMarkType.size());
        try {
            Constructor<? extends IMarkItem> constructor = clazz.getConstructor();
            idToMarkType.add(constructor.newInstance());
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("AssemblyCarts cannot register a mark type!");
        }
    }

    /**
     * Write the IMarkItem to a NBT. This NBT also contains the type
     * 
     * @param tag
     * @param mark
     */
    public static void writeToNBT(NBTTagCompound tag, IMarkItem mark) {
        NBTTagCompound wrapped = new NBTTagCompound();
        mark.writeToNBT(wrapped);
        tag.setInteger("markType", markTypeToId.get(mark.getClass().getCanonicalName()));
        tag.setTag("wrapped", wrapped);
    }

    public static IMarkItem readFromNBT(NBTTagCompound tag) {
        if (tag == null || !tag.hasKey("markType"))
            return null;
        NBTTagCompound wrapped = tag.getCompoundTag("wrapped");
        IMarkItem template = idToMarkType.get(tag.getInteger("markType"));
        return template.readFromNBT(wrapped);
    }

    public static IMarkItem stackToMark(ItemStack stack) {
        if (stack == null)
            return null;
        return new MarkItemStack(stack);
    }

    public static ItemStack getDisplayStackSafe(IMarkItem mark) {
        return mark == null ? null : mark.getDisplayStack();
    }
}
