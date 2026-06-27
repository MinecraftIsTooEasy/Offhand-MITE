package com.m.offhand.api.core;

import com.m.offhand.Offhand;
import net.minecraft.Block;
import net.minecraft.EntityPlayer;
import net.minecraft.Item;
import net.minecraft.ItemCudgel;
import net.minecraft.ItemStack;
import net.minecraft.ItemSword;
import net.minecraft.ItemTool;

import java.util.Map;
import java.util.WeakHashMap;

public final class OffhandActionHelper {

    private static final Map<EntityPlayer, DualMiningBlock> DUAL_MINING_BLOCKS = new WeakHashMap<EntityPlayer, DualMiningBlock>();

    private OffhandActionHelper() {
    }

    public static boolean hasUsableStack(ItemStack stack) {
        return stack != null && stack.stackSize > 0 && stack.getItem() != null;
    }

    public static boolean hasDifferentUsableOffhand(EntityPlayer player, ItemStack mainhand, ItemStack offhand) {
        if (player == null || !hasUsableStack(offhand) || offhand == mainhand) {
            return false;
        }

        return !Offhand.isOffhandBlacklisted(offhand);
    }

    public static boolean canDualMineWithTools(EntityPlayer player, Block block, int metadata, ItemStack mainhand, ItemStack offhand) {
        if (block == null || !hasDifferentUsableOffhand(player, mainhand, offhand)) {
            return false;
        }

        return isMiningTool(mainhand, block, metadata) && isMiningTool(offhand, block, metadata);
    }

    public static boolean canOffhandAttack(EntityPlayer player, ItemStack mainhand, ItemStack offhand) {
        return hasDifferentUsableOffhand(player, mainhand, offhand) && isMeleeAttackItem(offhand);
    }

    public static void recordDualMiningBlock(
        EntityPlayer player,
        int x,
        int y,
        int z,
        Block block,
        int metadata,
        ItemStack mainhand,
        ItemStack offhand) {
        if (canDualMineWithTools(player, block, metadata, mainhand, offhand)) {
            DUAL_MINING_BLOCKS.put(player, new DualMiningBlock(x, y, z, block, metadata, offhand));
        }
    }

    public static DualMiningBlock consumeDualMiningBlock(
        EntityPlayer player,
        int x,
        int y,
        int z,
        ItemStack offhand) {
        DualMiningBlock block = DUAL_MINING_BLOCKS.remove(player);
        if (block == null
            || block.x != x
            || block.y != y
            || block.z != z
            || block.offhand != offhand) {
            return null;
        }

        return block;
    }

    private static boolean isMiningTool(ItemStack stack, Block block, int metadata) {
        if (!hasUsableStack(stack)) {
            return false;
        }

        Item item = stack.getItem();
        return item instanceof ItemTool
            && !(item instanceof ItemSword)
            && !(item instanceof ItemCudgel)
            && stack.getStrVsBlock(block, metadata) > 1.0F;
    }

    private static boolean isMeleeAttackItem(ItemStack stack) {
        return hasUsableStack(stack) && stack.getMeleeDamageBonus() > 0.0F;
    }

    public static final class DualMiningBlock {
        public final int x;
        public final int y;
        public final int z;
        public final Block block;
        public final int metadata;
        private final ItemStack offhand;

        private DualMiningBlock(int x, int y, int z, Block block, int metadata, ItemStack offhand) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.block = block;
            this.metadata = metadata;
            this.offhand = offhand;
        }
    }
}
