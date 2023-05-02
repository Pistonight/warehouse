package com.tntp.assemblycarts.item.tag;

import java.util.List;

import com.tntp.assemblycarts.api.AssemblyProcess;
import com.tntp.assemblycarts.api.mark.IMarkItem;
import com.tntp.assemblycarts.core.AssemblyCartsMod;
import com.tntp.assemblycarts.init.ACItems;
import com.tntp.minecraftmodapi.item.IItemTag;
import com.tntp.minecraftmodapi.util.LocalUtil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;

public class TagProcessBook implements IItemTag {
    public static final String TAGNAME = AssemblyCartsMod.MODID + "|ProcessBook";
    public AssemblyProcess process;

    @Override
    public boolean isValidFor(Item item) {
        return item == ACItems.process_book;
    }

    @Override
    public boolean isTagValid() {
        return process != null && !process.isEmpty();
    }

    @Override
    public String getTagName() {
        return TAGNAME;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        if (process != null)
            process.writeToNBT(tag);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        process = AssemblyProcess.loadProcessFromNBT(tag, false);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addTooltip(boolean adv, boolean shift, boolean ctrl, List<String> tooltip) {
        if (process == null)
            return;
        if (shift) {
            IMarkItem main = process.getMainOutput();
            if (main != null) {
                tooltip.add(LocalUtil.localize("ac.tooltip.process_book.craft") + " " + LocalUtil.localize("ac.tooltip.process_book.stack_arg_s_d", main.displayName(), main.stacksize()));
            }
            if (ctrl) {
                tooltip.add(LocalUtil.localize("ac.tooltip.process_book.input"));
                for (int i = 0; i < 18; i++) {
                    IMarkItem in = process.getInput(i);
                    if (in == null)
                        break;
                    tooltip.add(LocalUtil.localize("ac.tooltip.process_book.stack_arg_s_d", in.displayName(), in.stacksize()));
                }
                tooltip.add(LocalUtil.localize("ac.tooltip.process_book.other_output"));
                for (int i = 0; i < 18; i++) {
                    IMarkItem out = process.getOtherOutput(i);
                    if (out == null)
                        break;
                    tooltip.add(LocalUtil.localize("ac.tooltip.process_book.stack_arg_s_d", out.displayName(), out.stacksize()));
                }
            } else {
                tooltip.add(LocalUtil.localize("ac.tooltip.hold_ctrl"));
            }
        } else {
            tooltip.add(LocalUtil.localize("ac.tooltip.hold_shift"));
        }
    }

}
