/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class RenderFish
/*     */   extends Render
/*     */ {
/*   8 */   private static final ResourceLocation field_110792_a = new ResourceLocation("textures/particle/particles.png");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRenderFishHook(EntityFishHook par1EntityFishHook, double par2, double par4, double par6, float par8, float par9) {
/*  15 */     if (par1EntityFishHook.ticksExisted < 3) {
/*     */       return;
/*     */     }
/*  18 */     if (par1EntityFishHook.angler != Minecraft.getClientPlayer()) {
/*  19 */       par4 -= 0.20000000298023224D;
/*     */     }
/*  21 */     boolean first_person_rendering = (this.renderManager.options.thirdPersonView == 0 && par1EntityFishHook.angler == Minecraft.getClientPlayer());
/*     */     
/*  23 */     if (!first_person_rendering) {
/*  24 */       par4 -= 0.20000000298023224D;
/*     */     }
/*  26 */     GL11.glPushMatrix();
/*  27 */     GL11.glTranslatef((float)par2, (float)par4, (float)par6);
/*  28 */     GL11.glEnable(32826);
/*  29 */     GL11.glScalef(0.5F, 0.5F, 0.5F);
/*  30 */     bindEntityTexture(par1EntityFishHook);
/*  31 */     Tessellator var10 = Tessellator.instance;
/*  32 */     byte var11 = 1;
/*  33 */     byte var12 = 2;
/*  34 */     float var13 = (var11 * 8 + 0) / 128.0F;
/*  35 */     float var14 = (var11 * 8 + 8) / 128.0F;
/*  36 */     float var15 = (var12 * 8 + 0) / 128.0F;
/*  37 */     float var16 = (var12 * 8 + 8) / 128.0F;
/*  38 */     var13 += 1.0E-5F;
/*  39 */     float var17 = 1.0F;
/*  40 */     float var18 = 0.5F;
/*  41 */     float var19 = 0.5F;
/*  42 */     GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/*  43 */     GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/*  44 */     GL11.glTranslatef(-0.06F, 0.0F, 0.0F);
/*  45 */     var10.startDrawingQuads();
/*  46 */     var10.setNormal(0.0F, 1.0F, 0.0F);
/*  47 */     var10.addVertexWithUV((0.0F - var18), (0.0F - var19), 0.0D, var13, var16);
/*  48 */     var10.addVertexWithUV((var17 - var18), (0.0F - var19), 0.0D, var14, var16);
/*  49 */     var10.addVertexWithUV((var17 - var18), (1.0F - var19), 0.0D, var14, var15);
/*  50 */     var10.addVertexWithUV((0.0F - var18), (1.0F - var19), 0.0D, var13, var15);
/*  51 */     var10.draw();
/*  52 */     GL11.glDisable(32826);
/*  53 */     GL11.glPopMatrix();
/*     */     
/*  55 */     if (par1EntityFishHook.angler != null) {
/*     */ 
/*     */       
/*  58 */       float var20 = 0.0F;
/*  59 */       float var21 = MathHelper.sin(MathHelper.sqrt_float(var20) * 3.1415927F);
/*  60 */       Vec3 var22 = par1EntityFishHook.worldObj.getWorldVec3Pool().getVecFromPool(-0.5D, 0.03D, 0.8D);
/*  61 */       var22.rotateAroundX(-(par1EntityFishHook.angler.prevRotationPitch + (par1EntityFishHook.angler.rotationPitch - par1EntityFishHook.angler.prevRotationPitch) * par9) * 3.1415927F / 180.0F);
/*  62 */       var22.rotateAroundY(-(par1EntityFishHook.angler.prevRotationYaw + (par1EntityFishHook.angler.rotationYaw - par1EntityFishHook.angler.prevRotationYaw) * par9) * 3.1415927F / 180.0F);
/*  63 */       var22.rotateAroundY(var21 * 0.5F);
/*  64 */       var22.rotateAroundX(-var21 * 0.7F);
/*  65 */       double var23 = par1EntityFishHook.angler.prevPosX + (par1EntityFishHook.angler.posX - par1EntityFishHook.angler.prevPosX) * par9 + var22.xCoord;
/*  66 */       double var25 = par1EntityFishHook.angler.prevPosY + (par1EntityFishHook.angler.posY - par1EntityFishHook.angler.prevPosY) * par9 + var22.yCoord;
/*  67 */       double var27 = par1EntityFishHook.angler.prevPosZ + (par1EntityFishHook.angler.posZ - par1EntityFishHook.angler.prevPosZ) * par9 + var22.zCoord;
/*  68 */       double var29 = (par1EntityFishHook.angler == (Minecraft.getMinecraft()).thePlayer) ? 0.0D : par1EntityFishHook.angler.getEyeHeight();
/*     */ 
/*     */       
/*  71 */       if (!first_person_rendering) {
/*     */         
/*  73 */         float var31 = (par1EntityFishHook.angler.prevRenderYawOffset + (par1EntityFishHook.angler.renderYawOffset - par1EntityFishHook.angler.prevRenderYawOffset) * par9) * 3.1415927F / 180.0F;
/*  74 */         double var32 = MathHelper.sin(var31);
/*  75 */         double var34 = MathHelper.cos(var31);
/*  76 */         var23 = par1EntityFishHook.angler.prevPosX + (par1EntityFishHook.angler.posX - par1EntityFishHook.angler.prevPosX) * par9 - var34 * 0.35D - var32 * 0.85D;
/*  77 */         var25 = par1EntityFishHook.angler.prevPosY + var29 + (par1EntityFishHook.angler.posY - par1EntityFishHook.angler.prevPosY) * par9 - 0.45D;
/*  78 */         var27 = par1EntityFishHook.angler.prevPosZ + (par1EntityFishHook.angler.posZ - par1EntityFishHook.angler.prevPosZ) * par9 - var32 * 0.35D + var34 * 0.85D;
/*     */       } 
/*     */       
/*  81 */       double var46 = par1EntityFishHook.prevPosX + (par1EntityFishHook.posX - par1EntityFishHook.prevPosX) * par9;
/*  82 */       double var33 = par1EntityFishHook.prevPosY + (par1EntityFishHook.posY - par1EntityFishHook.prevPosY) * par9 + 0.25D;
/*  83 */       double var35 = par1EntityFishHook.prevPosZ + (par1EntityFishHook.posZ - par1EntityFishHook.prevPosZ) * par9;
/*  84 */       double var37 = (float)(var23 - var46);
/*  85 */       double var39 = (float)(var25 - var33);
/*  86 */       double var41 = (float)(var27 - var35);
/*  87 */       GL11.glDisable(3553);
/*  88 */       GL11.glDisable(2896);
/*  89 */       var10.startDrawing(3);
/*  90 */       var10.setColorOpaque_I(0);
/*  91 */       byte var43 = 16;
/*     */       
/*  93 */       for (int var44 = 0; var44 <= var43; var44++) {
/*     */         
/*  95 */         float var45 = var44 / var43;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 101 */         var10.addVertex(par2 + var37 * var45, par4 + var39 * (var45 * var45 + var45) * 0.5D + 0.25D - (0.05F * (1.0F - var45)), par6 + var41 * var45);
/*     */       } 
/*     */       
/* 104 */       var10.draw();
/* 105 */       GL11.glEnable(2896);
/* 106 */       GL11.glEnable(3553);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110791_a(EntityFishHook par1EntityFishHook) {
/* 112 */     return field_110792_a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 120 */     return func_110791_a((EntityFishHook)par1Entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
/* 131 */     doRenderFishHook((EntityFishHook)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderFish.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */