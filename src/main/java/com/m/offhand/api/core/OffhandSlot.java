package com.m.offhand.api.core;

import com.m.offhand.OffhandConfig;
import net.minecraft.*;

public class OffhandSlot extends Slot {

    public OffhandSlot(IInventory inventory, int slotIndex, int xPosition, int yPosition) {
        super(inventory, slotIndex, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack == null || !OffhandConfig.isOffhandBlacklisted(stack);
    }

    @Override
    public int getSlotStackLimit() {
        return 64;
    }
}
