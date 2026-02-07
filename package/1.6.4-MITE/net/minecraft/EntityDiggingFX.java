/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityDiggingFX
/*     */   extends EntityFX
/*     */ {
/*     */   private Block blockInstance;
/*     */   public long visible_on_tick;
/*     */   
/*     */   public EntityDiggingFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, Block par14Block, int par15) {
/*  13 */     super(par1World, par2, par4, par6, par8, par10, par12);
/*  14 */     this.blockInstance = par14Block;
/*  15 */     setParticleIcon(par14Block.getIcon(0, par15));
/*  16 */     this.particleGravity = par14Block.blockParticleGravity;
/*  17 */     this.particleRed = this.particleGreen = this.particleBlue = 0.6F;
/*  18 */     this.particleScale /= 2.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityDiggingFX applyColourMultiplier(int par1, int par2, int par3) {
/*  30 */     if (this.blockInstance == Block.grass)
/*     */     {
/*  32 */       return this;
/*     */     }
/*     */ 
/*     */     
/*  36 */     int var4 = this.blockInstance.colorMultiplier(this.worldObj, par1, par2, par3);
/*  37 */     this.particleRed *= (var4 >> 16 & 0xFF) / 255.0F;
/*  38 */     this.particleGreen *= (var4 >> 8 & 0xFF) / 255.0F;
/*  39 */     this.particleBlue *= (var4 & 0xFF) / 255.0F;
/*  40 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityDiggingFX applyRenderColor(int par1) {
/*  49 */     if (this.blockInstance == Block.grass)
/*     */     {
/*  51 */       return this;
/*     */     }
/*     */ 
/*     */     
/*  55 */     int var2 = this.blockInstance.getRenderColor(par1);
/*  56 */     this.particleRed *= (var2 >> 16 & 0xFF) / 255.0F;
/*  57 */     this.particleGreen *= (var2 >> 8 & 0xFF) / 255.0F;
/*  58 */     this.particleBlue *= (var2 & 0xFF) / 255.0F;
/*  59 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFXLayer() {
/*  65 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
/*  70 */     if (this.visible_on_tick > 0L && Minecraft.theMinecraft.theWorld.getTotalWorldTime() < this.visible_on_tick) {
/*     */       return;
/*     */     }
/*  73 */     float var8 = (this.particleTextureIndexX + this.particleTextureJitterX / 4.0F) / 16.0F;
/*  74 */     float var9 = var8 + 0.015609375F;
/*  75 */     float var10 = (this.particleTextureIndexY + this.particleTextureJitterY / 4.0F) / 16.0F;
/*  76 */     float var11 = var10 + 0.015609375F;
/*  77 */     float var12 = 0.1F * this.particleScale;
/*     */     
/*  79 */     if (this.particleIcon != null) {
/*     */       
/*  81 */       var8 = this.particleIcon.getInterpolatedU((this.particleTextureJitterX / 4.0F * 16.0F));
/*  82 */       var9 = this.particleIcon.getInterpolatedU(((this.particleTextureJitterX + 1.0F) / 4.0F * 16.0F));
/*  83 */       var10 = this.particleIcon.getInterpolatedV((this.particleTextureJitterY / 4.0F * 16.0F));
/*  84 */       var11 = this.particleIcon.getInterpolatedV(((this.particleTextureJitterY + 1.0F) / 4.0F * 16.0F));
/*     */     } 
/*     */     
/*  87 */     float var13 = (float)(this.prevPosX + (this.posX - this.prevPosX) * par2 - interpPosX);
/*  88 */     float var14 = (float)(this.prevPosY + (this.posY - this.prevPosY) * par2 - interpPosY);
/*  89 */     float var15 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * par2 - interpPosZ);
/*  90 */     float var16 = 1.0F;
/*  91 */     par1Tessellator.setColorOpaque_F(var16 * this.particleRed, var16 * this.particleGreen, var16 * this.particleBlue);
/*  92 */     par1Tessellator.addVertexWithUV((var13 - par3 * var12 - par6 * var12), (var14 - par4 * var12), (var15 - par5 * var12 - par7 * var12), var8, var11);
/*  93 */     par1Tessellator.addVertexWithUV((var13 - par3 * var12 + par6 * var12), (var14 + par4 * var12), (var15 - par5 * var12 + par7 * var12), var8, var10);
/*  94 */     par1Tessellator.addVertexWithUV((var13 + par3 * var12 + par6 * var12), (var14 + par4 * var12), (var15 + par5 * var12 + par7 * var12), var9, var10);
/*  95 */     par1Tessellator.addVertexWithUV((var13 + par3 * var12 - par6 * var12), (var14 - par4 * var12), (var15 + par5 * var12 - par7 * var12), var9, var11);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityDiggingFX setVisibleOnTick(long visible_on_tick) {
/* 100 */     this.visible_on_tick = visible_on_tick;
/* 101 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 106 */     if (Minecraft.theMinecraft != null && Minecraft.theMinecraft.theWorld != null && 
/* 107 */       this.visible_on_tick > 0L && Minecraft.theMinecraft.theWorld.getTotalWorldTime() < this.visible_on_tick) {
/*     */       return;
/*     */     }
/* 110 */     super.onUpdate();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityDiggingFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */