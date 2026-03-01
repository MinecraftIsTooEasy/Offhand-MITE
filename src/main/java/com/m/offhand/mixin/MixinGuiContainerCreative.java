package com.m.offhand.mixin;

import com.m.offhand.api.core.OffhandUtils;
import com.m.offhand.client.OffhandRenderHelper;
import net.minecraft.Container;
import net.minecraft.CreativeTabs;
import net.minecraft.GuiContainerCreative;
import net.minecraft.InventoryEffectRenderer;
import net.minecraft.Minecraft;
import net.minecraft.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = GuiContainerCreative.class, priority = 1001)
public abstract class MixinGuiContainerCreative extends InventoryEffectRenderer {

	@Unique private static final int OFFHAND_BG_X = 85;
	@Unique private static final int OFFHAND_BG_Y = 32;
	@Unique private static final int OFFHAND_SLOT_X = OFFHAND_BG_X + 1;
	@Unique private static final int OFFHAND_SLOT_Y = OFFHAND_BG_Y + 1;

	@Shadow private static int selectedTabIndex;

	public MixinGuiContainerCreative(Container par1Container) {
		super(par1Container);
	}

	@Inject(method = "drawGuiContainerBackgroundLayer", at = @At("TAIL"))
	protected void offhand$drawOffhandSlot(float partialTicks, int mouseX, int mouseY, CallbackInfo ci) {
		if (selectedTabIndex == CreativeTabs.tabInventory.getTabIndex()) {
			OffhandRenderHelper.drawOffhandSlotBackground(guiLeft + OFFHAND_BG_X, guiTop + OFFHAND_BG_Y, 18, OffhandRenderHelper.OFFHAND_SLOT_TEXTURE);
		}
	}

    @SuppressWarnings("rawtypes")
	@Inject(method = "setCurrentCreativeTab", at = @At("TAIL"))
	private void offhand$repositionOffhandSlot(CreativeTabs par1CreativeTabs, CallbackInfo ci) {
		if (par1CreativeTabs != CreativeTabs.tabInventory) {
			return;
		}

		Minecraft mc = Minecraft.getMinecraft();
		if (mc == null || mc.thePlayer == null) {
			return;
		}

		int offhandInvSlot = OffhandUtils.getOffhandSlot(mc.thePlayer);

		List slots = this.inventorySlots.inventorySlots;
        for (Object raw : slots) {
            if (!(raw instanceof Slot slot)) {
                continue;
            }
            if (slot.isSlotInInventory(mc.thePlayer.inventory, offhandInvSlot)) {
                slot.xDisplayPosition = OFFHAND_SLOT_X;
                slot.yDisplayPosition = OFFHAND_SLOT_Y;
                break;
            }
        }
	}
}