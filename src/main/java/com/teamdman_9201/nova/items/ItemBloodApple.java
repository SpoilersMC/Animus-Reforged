package com.teamdman_9201.nova.items;

import com.teamdman_9201.nova.NOVA;
import com.teamdman_9201.nova.handlers.NOVABloodappleHandler;

import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;

import net.minecraftforge.common.util.FakePlayer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by TeamDman on 2015-07-31.
 */
public class ItemBloodApple extends ItemFood {
    @SideOnly(Side.CLIENT)
    IIcon icon;

    public ItemBloodApple(int heal, float sat, boolean wolfy) {
        super(heal, sat, wolfy);
    }

    @Override @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int p_77617_1_) {
        return icon;
    }

    @Override
    public ItemStack onEaten(ItemStack food, World world, EntityPlayer player) {
        if(!world.isRemote && player instanceof EntityPlayerMP) {
            if(player instanceof FakePlayer) {
                return super.onEaten(food, world, player);
            }
            // if within range of an altar, will add blood to the altar and give the player debuffs, otherwise is normal food
            // adds 150lp to the player's Soul Network
            if(NOVABloodappleHandler.addBloodToAltar(player) == false)
                SoulNetworkHandler.addCurrentEssenceToMaximum(SoulNetworkHandler.getUsername(player), 150, 2500);
        }
        return super.onEaten(food, world, player);
    }

    @Override @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg) {
        icon = reg.registerIcon(NOVA.MODID + ":itemBoundApple");
    }
}