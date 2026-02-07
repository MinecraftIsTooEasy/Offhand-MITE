package com.m.offhand.mixin;

import com.m.offhand.api.OffhandAccess;
import net.minecraft.*;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 第一人称左手显示副手物品。
 * 当使用副手物品时，在左手位置渲染（带使用动画），主手显示原始主手物品。
 */
@Mixin(ItemRenderer.class)
public abstract class OffhandItemRendererMixin {
    @Shadow
    private Minecraft mc;

    @Shadow
    private ItemStack itemToRender;

    @Shadow
    private float equippedProgress;

    @Shadow
    private float prevEquippedProgress;

    @Shadow
    protected abstract void renderItem(EntityLivingBase entityLivingBase, ItemStack itemStack, int renderPass);

    // 临时保存使用副手时的物品，用于左手渲染
    @Unique
    private ItemStack miteassistant$savedOffhandItem = null;

    // 临时保存原始渲染状态，用于恢复
    @Unique
    private ItemStack miteassistant$savedItemToRender = null;
    @Unique
    private float miteassistant$savedEquippedProgress = -1;
    @Unique
    private float miteassistant$savedPrevEquippedProgress = -1;

    /**
     * 在渲染开始前，如果正在使用副手物品，将 itemToRender 替换为原始主手物品
     * 这样原版渲染会显示原始主手物品（如剑），而不是空手或副手物品
     */
    @Inject(method = "renderItemInFirstPerson(F)V", at = @At("HEAD"))
    private void miteassistant$beforeRender(float partialTicks, CallbackInfo ci) {
        miteassistant$savedOffhandItem = null;
        miteassistant$savedItemToRender = null;
        miteassistant$savedEquippedProgress = -1;
        miteassistant$savedPrevEquippedProgress = -1;
        
        if (this.mc == null || this.mc.thePlayer == null) return;
        
        Object playerObj = this.mc.thePlayer;
        if (!(playerObj instanceof OffhandAccess offhandAccess)) return;

        // 如果正在使用副手物品
        if (offhandAccess.miteassistant$isUsingOffhand()) {
            // 保存副手物品（从主手槽位获取，因为服务端已将副手物品临时放到主手）
            miteassistant$savedOffhandItem = this.mc.thePlayer.inventory.getCurrentItemStack();
            
            // 保存原始渲染状态
            miteassistant$savedItemToRender = this.itemToRender;
            miteassistant$savedEquippedProgress = this.equippedProgress;
            miteassistant$savedPrevEquippedProgress = this.prevEquippedProgress;
            
            // 将右手渲染替换为原始主手物品（如剑），强制装备进度为1避免抖动
            this.itemToRender = offhandAccess.miteassistant$getOriginalMainhand();
            this.equippedProgress = 1.0F;
            this.prevEquippedProgress = 1.0F;
        }
    }

    /**
     * 在正常渲染结束后，恢复渲染状态并渲染副手物品在左手位置
     */
    @Inject(method = "renderItemInFirstPerson(F)V", at = @At("TAIL"))
    private void miteassistant$renderOffhandFirstPerson(float partialTicks, CallbackInfo ci) {
        if (this.mc == null || this.mc.thePlayer == null) return;
        
        Object playerObj = this.mc.thePlayer;
        if (!(playerObj instanceof OffhandAccess offhandAccess)) return;

        EntityClientPlayerMP player = this.mc.thePlayer;
        boolean isUsingOffhand = offhandAccess.miteassistant$isUsingOffhand();
        
        ItemStack itemToRenderInOffhand;
        if (isUsingOffhand) {
            // 正在使用副手物品，使用之前保存的物品或直接从主手获取
            itemToRenderInOffhand = miteassistant$savedOffhandItem;
            if (itemToRenderInOffhand == null) {
                itemToRenderInOffhand = player.inventory.getCurrentItemStack();
            }
            miteassistant$savedOffhandItem = null;
            
            // 恢复原始渲染状态，防止 updateEquippedItem 检测到异常变化
            if (miteassistant$savedItemToRender != null || miteassistant$savedEquippedProgress >= 0) {
                this.itemToRender = miteassistant$savedItemToRender;
                this.equippedProgress = miteassistant$savedEquippedProgress;
                this.prevEquippedProgress = miteassistant$savedPrevEquippedProgress;
                miteassistant$savedItemToRender = null;
                miteassistant$savedEquippedProgress = -1;
                miteassistant$savedPrevEquippedProgress = -1;
            }
        } else {
            // 不在使用，从副手槽位获取
            itemToRenderInOffhand = offhandAccess.miteassistant$getOffhandStack();
        }

        if (itemToRenderInOffhand == null || itemToRenderInOffhand.stackSize <= 0) return;

        // 渲染副手物品在左手位置
        if (isUsingOffhand) {
            renderOffhandItemInUse(partialTicks, itemToRenderInOffhand, player);
        } else {
            renderOffhandItemStatic(partialTicks, itemToRenderInOffhand, player);
        }
    }

    /**
     * 静态渲染副手物品在左手位置（不在使用时）
     */
    private void renderOffhandItemStatic(float partialTicks, ItemStack offhand, EntityClientPlayerMP player) {
        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        try {
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            
            // 设置光照
            int lightValue = this.mc.theWorld.getLightBrightnessForSkyBlocks(
                MathHelper.floor_double(player.posX),
                MathHelper.floor_double(player.posY),
                MathHelper.floor_double(player.posZ), 0
            );
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 
                (lightValue % 65536) / 1.0F, (lightValue / 65536) / 1.0F);
            
            // 获取物品颜色（修复草方块等物品颜色不对的问题）
            int itemColor = Item.itemsList[offhand.itemID].getColorFromItemStack(offhand, 0);
            float colorR = (itemColor >> 16 & 0xFF) / 255.0F;
            float colorG = (itemColor >> 8 & 0xFF) / 255.0F;
            float colorB = (itemColor & 0xFF) / 255.0F;
            GL11.glColor4f(colorR, colorG, colorB, 1.0F);

            // 左手渲染：镜像右手
            GL11.glScalef(-1.0F, 1.0F, 1.0F);
            GL11.glTranslatef(0.56F, -0.52F, -0.72F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScalef(0.40F, 0.40F, 0.40F);
            GL11.glCullFace(GL11.GL_FRONT);
            
            // 检查物品是否需要旋转渲染（修复钓鱼竿、打火石等物品反着的问题）
            if (offhand.getItem().shouldRotateAroundWhenRendering()) {
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            }

            // 渲染物品
            if (offhand.getItem().requiresMultipleRenderPasses()) {
                this.renderItem(player, offhand, 0);
                int pass1Color = Item.itemsList[offhand.itemID].getColorFromItemStack(offhand, 1);
                float pass1R = (pass1Color >> 16 & 0xFF) / 255.0F;
                float pass1G = (pass1Color >> 8 & 0xFF) / 255.0F;
                float pass1B = (pass1Color & 0xFF) / 255.0F;
                GL11.glColor4f(pass1R, pass1G, pass1B, 1.0F);
                this.renderItem(player, offhand, 1);
            } else {
                this.renderItem(player, offhand, 0);
            }
        } finally {
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }
    }

    /**
     * 在左手位置渲染正在使用的副手物品（带使用动画）
     */
    private void renderOffhandItemInUse(float partialTicks, ItemStack itemInUse, EntityClientPlayerMP player) {
        // 计算装备进度
        float equippedProgressInterp = this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * partialTicks;

        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        
        // 启用深度测试和混合
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        // 设置光照
        int lightValue = this.mc.theWorld.getLightBrightnessForSkyBlocks(
            MathHelper.floor_double(player.posX),
            MathHelper.floor_double(player.posY),
            MathHelper.floor_double(player.posZ), 0
        );
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 
            (lightValue % 65536) / 1.0F, (lightValue / 65536) / 1.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        // 设置物品颜色
        int itemColor = Item.itemsList[itemInUse.itemID].getColorFromItemStack(itemInUse, 0);
        float colorR = (itemColor >> 16 & 0xFF) / 255.0F;
        float colorG = (itemColor >> 8 & 0xFF) / 255.0F;
        float colorB = (itemColor & 0xFF) / 255.0F;
        GL11.glColor4f(colorR, colorG, colorB, 1.0F);

        // 左手镜像渲染
        GL11.glScalef(-1.0F, 1.0F, 1.0F);
        GL11.glCullFace(GL11.GL_FRONT);

        // 获取使用动作类型
        EnumItemInUseAction useAction = itemInUse.getItemInUseAction(player);

        // 客户端没有真实的 itemInUseCount（hook 返回 0 以防止右手动画）
        // 用 ticksExisted 驱动左手动画，产生平滑的重复进食/喝药水效果
        int maxDuration = itemInUse.getMaxItemUseDuration();
        int fakeCount = maxDuration > 0 ? (maxDuration - (player.ticksExisted % maxDuration)) : 1;

        {
            // 正在使用物品
            if (useAction == EnumItemInUseAction.EAT || useAction == EnumItemInUseAction.DRINK) {
                // 进食/喝水动画（tick 驱动）
                float f2 = fakeCount - partialTicks + 1.0F;
                float f1 = 1.0F - f2 / (float) maxDuration;
                float f3 = 1.0F - f1;
                f3 = f3 * f3 * f3;
                f3 = f3 * f3 * f3;
                f3 = f3 * f3 * f3;
                float progress = 1.0F - f3;
                GL11.glTranslatef(0.0F, MathHelper.abs(MathHelper.cos(f2 / 4.0F * 3.1415927F) * 0.1F) * (f1 > 0.2F ? 1 : 0), 0.0F);
                GL11.glTranslatef(progress * 0.6F, -progress * 0.5F, 0.0F);
                GL11.glRotatef(progress * 90.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(progress * 10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(progress * 30.0F, 0.0F, 0.0F, 1.0F);
            } else if (useAction == EnumItemInUseAction.BOW) {
                // 弓蓄力动画（X轴已镜像，所以位移反转）
                GL11.glRotatef(-18.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-12.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(-0.4F, 0.2F, 0.0F);
                
                float ticksUsed = itemInUse.getMaxItemUseDuration() - fakeCount - partialTicks + 1.0F;
                float pullProgress = ticksUsed / ItemBow.getTicksForMaxPull(itemInUse);
                pullProgress = (pullProgress * pullProgress + pullProgress * 2.0F) / 3.0F;
                if (pullProgress > 1.0F) pullProgress = 1.0F;
                
                if (pullProgress > 0.1F) {
                    GL11.glTranslatef(0.0F, MathHelper.sin((ticksUsed - 0.1F) * 1.3F) * 0.01F * (pullProgress - 0.1F), 0.0F);
                }
                
                GL11.glTranslatef(0.0F, 0.0F, pullProgress * 0.1F);
                GL11.glRotatef(-335.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glTranslatef(0.0F, 0.5F, 0.0F);
                float bowScale = 1.0F + pullProgress * 0.2F;
                GL11.glScalef(1.0F, 1.0F, bowScale);
                GL11.glTranslatef(0.0F, -0.5F, 0.0F);
                GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
            } else if (useAction == EnumItemInUseAction.BLOCK) {
                // 格挡动画
                GL11.glTranslatef(-0.5F, 0.2F, 0.0F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
            }
        }

        // 基础位置（和静态渲染一致）
        GL11.glTranslatef(0.56F, -0.52F - (1.0F - equippedProgressInterp) * 0.6F, -0.72F);
        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
        GL11.glEnable(32826); // GL_RESCALE_NORMAL
        
        float itemScale = 0.4F;
        GL11.glScalef(itemScale, itemScale, itemScale);

        // 渲染物品
        if (itemInUse.getItem().shouldRotateAroundWhenRendering()) {
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        }

        if (itemInUse.getItem().requiresMultipleRenderPasses()) {
            this.renderItem(player, itemInUse, 0);
            int pass1Color = Item.itemsList[itemInUse.itemID].getColorFromItemStack(itemInUse, 1);
            float pass1R = (pass1Color >> 16 & 0xFF) / 255.0F;
            float pass1G = (pass1Color >> 8 & 0xFF) / 255.0F;
            float pass1B = (pass1Color & 0xFF) / 255.0F;
            GL11.glColor4f(pass1R, pass1G, pass1B, 1.0F);
            this.renderItem(player, itemInUse, 1);
        } else {
            this.renderItem(player, itemInUse, 0);
        }

        GL11.glDisable(32826);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }
}
