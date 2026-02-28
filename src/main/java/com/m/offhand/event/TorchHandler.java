package com.m.offhand.event;

import com.m.offhand.Offhand;
import net.minecraft.*;

public class TorchHandler {

    public static boolean shouldPlace(ItemStack mainhandStack, ItemStack offhandStack) {
        Offhand.debug("[Offhand-TorchHandler] shouldPlace called");

        if (offhandStack == null || offhandStack.stackSize <= 0) {
            Offhand.debug("[Offhand-TorchHandler] offhandStack is null or empty, returning false");
            return false;
        }

        Item offhandItem = offhandStack.getItem();
        if (offhandItem == null) {
            Offhand.debug("[Offhand-TorchHandler] offhandItem is null, returning false");
            return false;
        }

        boolean isTorch = offhandItem instanceof ItemBlock
            && ((ItemBlock) offhandItem).getBlock() instanceof BlockTorch;

        // Torch placement should not depend on mainhand item type.
        Offhand.debug("[Offhand-TorchHandler] offhandItem class={}, isTorch={}, allow=true",
            offhandItem.getClass().getSimpleName(), Boolean.valueOf(isTorch));
        return true;
    }

    public static boolean isItemBlock(ItemStack stack) {
        if (stack == null) return false;
        return stack.getItem() instanceof ItemBlock;
    }
}
