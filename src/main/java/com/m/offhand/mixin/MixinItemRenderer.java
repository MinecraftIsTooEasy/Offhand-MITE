package com.m.offhand.mixin;

import com.m.offhand.api.core.OffhandUtils;
import com.m.offhand.client.ItemRendererHooks;
import net.minecraft.EntityClientPlayerMP;
import net.minecraft.EntityLivingBase;
import net.minecraft.Icon;
import net.minecraft.Item;
import net.minecraft.ItemRenderer;
import net.minecraft.ItemStack;
import net.minecraft.Minecraft;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class MixinItemRenderer {

    @Unique
    private boolean offhand$suppressedMainhandSwing = false;

    @Unique
    private float offhand$prevSwingBackup = 0.0F;

    @Unique
    private float offhand$swingBackup = 0.0F;

    @Unique
    private boolean offhand$compassMirrorFixActive = false;

    @Inject(method = "renderItemInFirstPerson", at = @At("HEAD"))
    private void offhand$beforeRenderItemInFirstPerson(float frame, CallbackInfo ci) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc == null) {
            return;
        }

        EntityClientPlayerMP player = mc.thePlayer;
        if (player == null) {
            return;
        }

        if (OffhandUtils.isClientOffhandRenderContext()) {
            return;
        }

        if (!OffhandUtils.shouldSuppressClientMainhandRender(player)) {
            return;
        }

        this.offhand$suppressedMainhandSwing = true;
        this.offhand$prevSwingBackup = player.prevSwingProgress;
        this.offhand$swingBackup = player.swingProgress;
        player.prevSwingProgress = 0.0F;
        player.swingProgress = 0.0F;
    }

    @Inject(method = "renderItemInFirstPerson", at = @At("RETURN"))
    private void offhand$afterRenderItemInFirstPerson(float frame, CallbackInfo ci) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc != null && this.offhand$suppressedMainhandSwing && mc.thePlayer != null) {
            mc.thePlayer.prevSwingProgress = this.offhand$prevSwingBackup;
            mc.thePlayer.swingProgress = this.offhand$swingBackup;
        }
        this.offhand$suppressedMainhandSwing = false;

        ItemRendererHooks.renderOffhandReturn(frame);
    }

    @Inject(method = "renderItem", at = @At("HEAD"))
    private void offhand$beforeRenderItem(EntityLivingBase entity, ItemStack stack, int pass, CallbackInfo ci) {
        if (!OffhandUtils.isClientOffhandRenderContext() || stack == null || stack.getItem() != Item.compass) {
            return;
        }

        this.offhand$compassMirrorFixActive = true;

        Icon icon = entity == null ? null : entity.getItemIcon(stack, pass);
        if (icon == null) {
            this.offhand$compassMirrorFixActive = false;
            return;
        }

        // Offhand first-person rendering mirrors model space.
        // For compass we mirror only the sampled icon UVs to avoid changing item screen position.
        GL11.glMatrixMode(GL11.GL_TEXTURE);
        GL11.glPushMatrix();
        GL11.glTranslatef(icon.getMinU() + icon.getMaxU(), 0.0F, 0.0F);
        GL11.glScalef(-1.0F, 1.0F, 1.0F);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    @Inject(method = "renderItem", at = @At("RETURN"))
    private void offhand$afterRenderItem(EntityLivingBase entity, ItemStack stack, int pass, CallbackInfo ci) {
        if (!this.offhand$compassMirrorFixActive) {
            return;
        }

        GL11.glMatrixMode(GL11.GL_TEXTURE);
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        this.offhand$compassMirrorFixActive = false;
    }
}

