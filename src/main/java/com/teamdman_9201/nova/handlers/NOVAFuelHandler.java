package com.teamdman_9201.nova.handlers;

import com.teamdman_9201.nova.NOVA;

import cpw.mods.fml.common.IFuelHandler;

import net.minecraft.item.ItemStack;

public class NOVAFuelHandler implements IFuelHandler {

    @Override
    public int getBurnTime(ItemStack fuel) {
        if(fuel.isItemEqual(new ItemStack(NOVA.itemUnstableCoal)) == true) {
            return 1280000;
        }
        return 0;
    }
}