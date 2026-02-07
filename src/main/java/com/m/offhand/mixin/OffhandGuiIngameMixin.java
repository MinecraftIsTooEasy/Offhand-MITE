package com.m.offhand.mixin;

import com.m.offhand.api.OffhandAccess;
import net.minecraft.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 在 HUD 层（GuiIngame）渲染副手物品栏格子，显示在热键栏左侧。
 * 同时处理使用副手物品时的热键栏显示问题。
 */
@Mixin(GuiIngame.class)
public abstract class OffhandGuiIngameMixin extends Gui {
    @Shadow
    @Final
    private Minecraft mc;

    @Unique
    private static final ResourceLocation widgetsTexPath = new ResourceLocation("textures/gui/widgets.png");

    @Unique
    private static RenderItem itemRender = new RenderItem();

    // 用于在渲染热键栏槽位时临时替换物品
    @Unique
    private ItemStack miteassistant$tempSwappedItem = null;
    @Unique
    private int miteassistant$tempSwappedSlot = -1;

    /**
     * 在渲染热键栏槽位前，如果正在使用副手物品且是当前槽位，
     * 临时将槽位物品替换为原始主手物品，这样热键栏显示正确的主手物品（如剑）
     */
    @Inject(method = "renderInventorySlot", at = @At("HEAD"))
    private void miteassistant$beforeRenderSlot(int slotIndex, int x, int y, float partialTicks, CallbackInfo ci) {
        if (this.mc == null || this.mc.thePlayer == null) return;
        
        Object playerObj = this.mc.thePlayer;
        if (!(playerObj instanceof OffhandAccess offhandAccess)) return;

        // 如果正在使用副手物品，且这个槽位是当前选中的主手槽位
        if (offhandAccess.miteassistant$isUsingOffhand() && slotIndex == this.mc.thePlayer.inventory.currentItem) {
            // 临时保存当前槽位物品（副手物品被服务端放到了这里），替换为原始主手物品
            miteassistant$tempSwappedSlot = slotIndex;
            miteassistant$tempSwappedItem = this.mc.thePlayer.inventory.mainInventory[slotIndex];
            this.mc.thePlayer.inventory.mainInventory[slotIndex] = offhandAccess.miteassistant$getOriginalMainhand();
        }
    }

    /**
     * 在渲染热键栏槽位后，恢复被临时替换的物品
     */
    @Inject(method = "renderInventorySlot", at = @At("TAIL"))
    private void miteassistant$afterRenderSlot(int slotIndex, int x, int y, float partialTicks, CallbackInfo ci) {
        if (miteassistant$tempSwappedSlot >= 0 && miteassistant$tempSwappedSlot == slotIndex) {
            this.mc.thePlayer.inventory.mainInventory[miteassistant$tempSwappedSlot] = miteassistant$tempSwappedItem;
            miteassistant$tempSwappedItem = null;
            miteassistant$tempSwappedSlot = -1;
        }
    }

    /**
     * 在热键栏渲染后，渲染副手物品栏格子
     */
    @Inject(method = "renderGameOverlay(FZII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;inDevMode()Z", shift = At.Shift.AFTER))
    private void miteassistant$renderOffhandSlot(float partialTicks, boolean par2, int par3, int par4, CallbackInfo ci) {
        if (this.mc == null || this.mc.thePlayer == null) return;
        if (this.mc.theWorld == null) return;

        Object playerObj = this.mc.thePlayer;
        if (!(playerObj instanceof OffhandAccess offhandAccess)) return;

        // 获取副手物品（如果正在使用，从主手槽位获取）
        ItemStack offhand;
        if (offhandAccess.miteassistant$isUsingOffhand()) {
            offhand = this.mc.thePlayer.inventory.getCurrentItemStack();
        } else {
            offhand = offhandAccess.miteassistant$getOffhandStack();
        }

        // 计算屏幕尺寸
        ScaledResolution sr = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        int screenWidth = sr.getScaledWidth();
        int screenHeight = sr.getScaledHeight();

        // 副手格子位置：热键栏左边
        int hotbarX = screenWidth / 2 - 91;
        int hotbarY = screenHeight - 22;
        int offhandSlotX = hotbarX - 29;
        int offhandSlotY = hotbarY;

        // 绑定 widgets 纹理
        this.mc.getTextureManager().bindTexture(widgetsTexPath);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        // 绘制副手格子背景
        this.zLevel = -90.0F;
        drawTexturedModalRect(offhandSlotX, offhandSlotY, 0, 0, 22, 22);

        // 如果有副手物品，渲染物品图标
        if (offhand != null && offhand.stackSize > 0) {
            int itemX = offhandSlotX + 3;
            int itemY = offhandSlotY + 3;

            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.enableGUIStandardItemLighting();
            
            itemRender.renderItemAndEffectIntoGUI(
                this.mc.fontRenderer,
                this.mc.getTextureManager(),
                offhand,
                itemX,
                itemY
            );
            
            itemRender.renderItemOverlayIntoGUI(
                this.mc.fontRenderer,
                this.mc.getTextureManager(),
                offhand,
                itemX,
                itemY
            );

            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        }
    }
}
