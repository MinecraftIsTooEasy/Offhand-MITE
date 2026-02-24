package com.m.offhand.event;

import com.m.offhand.Offhand;
import net.minecraft.*;

public class TorchHandler {

    public static boolean shouldPlace(ItemStack mainhandStack, ItemStack offhandStack) {
        Offhand.debug("[Offhand-TorchHandler] shouldPlace called");
        Offhand.debug("[Offhand-TorchHandler] mainhandStack={}, offhandStack={}",
            mainhandStack != null ? mainhandStack.getDisplayName() : "null",
            offhandStack != null ? offhandStack.getDisplayName() : "null");
        
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

        Offhand.debug("[Offhand-TorchHandler] offhandItem class={}, isTorch={}",
            offhandItem.getClass().getSimpleName(), isTorch);

        if (!isTorch) {
            Offhand.debug("[Offhand-TorchHandler] Not a torch, returning true (allow placement)");
            return true;
        }

        if (mainhandStack == null || mainhandStack.stackSize <= 0) {
            Offhand.debug("[Offhand-TorchHandler] Torch in offhand but mainhand is empty, returning false");
            return false;
        }
        
        Item mainItem = mainhandStack.getItem();
        boolean isTool = isTool(mainItem);
        
        Offhand.debug("[Offhand-TorchHandler] mainItem class={}, isTool={}",
            mainItem != null ? mainItem.getClass().getSimpleName() : "null", isTool);
        
        if (!isTool) {
            Offhand.debug("[Offhand-TorchHandler] Torch in offhand but mainhand is not a tool, returning false");
            return false;
        }

        Offhand.debug("[Offhand-TorchHandler] Torch in offhand and mainhand is tool, returning true");
        return true;
    }
    
    private static boolean isTool(Item item) {
        if (item == null) return false;
        return item instanceof ItemTool || item instanceof ItemSword || item instanceof ItemHoe;
    }

    public static boolean isItemBlock(ItemStack stack) {
        if (stack == null) return false;
        return stack.getItem() instanceof ItemBlock;
    }
}
