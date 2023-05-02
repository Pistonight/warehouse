package com.tntp.assemblycarts.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.tntp.assemblycarts.api.mark.IMarkItem;
import com.tntp.assemblycarts.api.mark.MarkerUtil;
import com.tntp.minecraftmodapi.util.ItemUtil;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants.NBT;

public class RequestManager {
    public boolean update;
    private IMarkItem target;
    private ArrayList<IMarkItem> need;
    private IInventory requestingInventory;
    private int startSlot;
    private int endSlot;

    public RequestManager(IInventory inv, int startSlot, int endSlot) {
        requestingInventory = inv;
        setStartSlot(startSlot);
        setEndSlot(endSlot);
        need = new ArrayList<>();
    }

    public void initRequestWithProcess(AssemblyProcess proc, int multiplier) {
        need.clear();
        for (Iterator<IMarkItem> iter = proc.inputIterator(); iter.hasNext();) {
            IMarkItem input = iter.next();
            IMarkItem clone = input.setStackSize(input.stacksize() * multiplier);
            need.add(clone);
        }
        if (!need.isEmpty()) {
            IMarkItem mark = proc.getMainOutput();
            target = mark.setStackSize(mark.stacksize() * multiplier);
        }
        update = true;
    }

    public void initRequestDirectly(IMarkItem tar, List<IMarkItem> request) {
        need.clear();
        for (IMarkItem mark : request) {
            if (mark != null)
                need.add(mark);
        }
        target = tar;
        update = true;
    }

    public void cancelRequest() {
        need.clear();
        target = null;
        update = true;
    }

    public void setStartSlot(int s) {
        startSlot = s;
    }

    public void setEndSlot(int s) {
        endSlot = s;
    }

    public boolean isFulfilled() {
        return need.isEmpty() || target == null;
    }

    public IMarkItem getCraftingTarget() {
        return target;
    }

    public List<IMarkItem> getNeed() {
        return Collections.unmodifiableList(need);
    }

    /**
     * Test if the requester wants this stack
     * 
     * @param stack
     * @return
     */
    public boolean isRequesting(ItemStack stack) {
        for (IMarkItem is : need) {
            if (is.matchesStack(stack))
                return true;
        }
        return false;
    }

    /**
     * Actually give the stack to the requester. The stacksize should decrease as
     * the requester takes the items
     * 
     * @param stack
     */
    public void supply(ItemStack stack) {
        System.out.println("Sup " + stack.stackSize);
        int stackSizeBefore = stack.stackSize;
        ItemUtil.addToInventory(stack, requestingInventory, startSlot, endSlot, -1);

        int stackSizeChange = stack.stackSize - stackSizeBefore;// change is negative
        System.out.println(stack.stackSize);
        System.out.println(stackSizeChange);
        if (stackSizeChange < 0) {

            for (int i = 0; i < need.size(); i++) {// Iterator<IMarkItem> iter = need.iterator(); iter.hasNext();) {
                IMarkItem needStack = need.get(i);
                if (needStack.matchesStack(stack)) {
                    System.out.println("need before " + needStack.stacksize());
                    needStack = needStack.setStackSize(needStack.stacksize() + stackSizeChange);
                    need.set(i, needStack);
                    System.out.println("need after " + needStack.stacksize());
                    update = true;
                    if (needStack.stacksize() <= 0) {
                        need.remove(i); // remove the need;
                        i--;
                    }
                    break;
                }
            }
            if (need.isEmpty()) {
                target = null;
            }
        }

    }

    public void readFromNBT(NBTTagCompound tag) {
        target = MarkerUtil.readFromNBT(tag.getCompoundTag("target"));

        NBTTagList needList = tag.getTagList("need", NBT.TAG_COMPOUND);
        need.clear();
        for (int i = 0; i < needList.tagCount(); i++) {
            IMarkItem s = MarkerUtil.readFromNBT(needList.getCompoundTagAt(i));
            if (s != null)
                need.add(s);
        }

        startSlot = tag.getInteger("startSlot");
        endSlot = tag.getInteger("endSlot");
    }

    public void writeToNBT(NBTTagCompound tag) {

        if (target != null) {
            NBTTagCompound targetTag = new NBTTagCompound();
            MarkerUtil.writeToNBT(targetTag, target);
            tag.setTag("target", targetTag);
        }

        NBTTagList needList = new NBTTagList();
        for (IMarkItem needStack : need) {
            if (needStack != null && needStack.stacksize() > 0) {
                NBTTagCompound needTag = new NBTTagCompound();
                MarkerUtil.writeToNBT(needTag, needStack);
                needList.appendTag(needTag);
            }
        }
        tag.setTag("need", needList);

        tag.setInteger("startSlot", startSlot);
        tag.setInteger("endSlot", endSlot);
    }
}
