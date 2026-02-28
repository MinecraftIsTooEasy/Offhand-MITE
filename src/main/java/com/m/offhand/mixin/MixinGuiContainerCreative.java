package com.m.offhand.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.m.offhand.api.core.OffhandUtils;
import com.m.offhand.client.OffhandRenderHelper;
import net.minecraft.Container;
import net.minecraft.CreativeTabs;
import net.minecraft.GuiContainerCreative;
import net.minecraft.InventoryEffectRenderer;
import net.minecraft.Minecraft;
import net.minecraft.SlotCreativeInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuiContainerCreative.class, priority = 1001)
public abstract class MixinGuiContainerCreative extends InventoryEffectRenderer {

	private static final int OFFHAND_BG_X = 85;
	private static final int OFFHAND_BG_Y = 32;
	private static final int OFFHAND_SLOT_X = OFFHAND_BG_X + 1;
	private static final int OFFHAND_SLOT_Y = OFFHAND_BG_Y + 1;
	
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
	
	@Redirect(method = "setCurrentCreativeTab", at = @At(value = "FIELD", target = "Lnet/minecraft/SlotCreativeInventory;xDisplayPosition:I", ordinal = 2))
	private void offhandModifySlotX(SlotCreativeInventory instance, int value, @Local(name = "var5") int var5, @Local(argsOnly = true) CreativeTabs par1CreativeTabs) {
		if (var5 == OffhandUtils.getOffhandSlot(Minecraft.getClientPlayer()) + 9 && par1CreativeTabs == CreativeTabs.tabInventory) {
			instance.xDisplayPosition = OFFHAND_SLOT_X;
		} else {
			instance.xDisplayPosition = value;
		}
	}
	@Redirect(method = "setCurrentCreativeTab", at = @At(value = "FIELD", target = "Lnet/minecraft/SlotCreativeInventory;yDisplayPosition:I", ordinal = 2))
	private void offhandModifySlotY(SlotCreativeInventory instance, int value, @Local(name = "var5") int var5, @Local(argsOnly = true) CreativeTabs par1CreativeTabs) {
		if (var5 == OffhandUtils.getOffhandSlot(Minecraft.getClientPlayer()) + 9 && par1CreativeTabs == CreativeTabs.tabInventory) {
			instance.yDisplayPosition = OFFHAND_SLOT_Y;
		} else {
			instance.yDisplayPosition = value;
		}
	}
	
}
