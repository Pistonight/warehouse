package com.tntp.assemblycarts.item;

import java.util.List;

import com.tntp.assemblycarts.core.AssemblyCartsMod;
import com.tntp.assemblycarts.gui.ACEnumGui;
import com.tntp.assemblycarts.item.tag.TagProcessBook;
import com.tntp.minecraftmodapi.gui.EnumGuiHandler;
import com.tntp.minecraftmodapi.item.ItemAPIiTNTPiston;
import com.tntp.minecraftmodapi.util.ItemUtil;
import com.tntp.minecraftmodapi.util.KeyUtil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemProcessBook extends ItemAPIiTNTPiston {

    public ItemProcessBook() {
        this.setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (player.isSneaking()) {
            if (!world.isRemote) {
                EnumGuiHandler.openGuiCurrentItem(ACEnumGui.ProcessBook, AssemblyCartsMod.MODID, player, world);
            }
        }
        return stack;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return stack != null && ItemUtil.hasItemTag(stack, TagProcessBook.TAGNAME);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return hasEffect(stack) ? EnumRarity.uncommon : EnumRarity.common;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean adv) {
        if (stack == null)
            return;
        TagProcessBook tag = ItemUtil.getItemTag(stack, new TagProcessBook());
        if (tag != null) {
            tag.addTooltip(adv, KeyUtil.isShiftDown(), KeyUtil.isCtrlDown(), tooltip);
        }
    }
}
