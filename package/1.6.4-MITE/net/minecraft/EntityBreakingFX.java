/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityBreakingFX
/*    */   extends EntityFX
/*    */ {
/*    */   private int fx_layer;
/*    */   
/*    */   public EntityBreakingFX(World par1World, double par2, double par4, double par6, Item par8Item) {
/*  9 */     this(par1World, par2, par4, par6, par8Item, 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityBreakingFX(World par1World, double par2, double par4, double par6, Item par8Item, int par9) {
/* 14 */     super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/* 15 */     this.fx_layer = (par8Item.itemID < 256) ? 1 : 2;
/* 16 */     setParticleIcon(par8Item.getIconFromSubtype(par9));
/* 17 */     this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
/* 18 */     this.particleGravity = Block.blockSnow.blockParticleGravity;
/* 19 */     this.particleScale /= 2.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityBreakingFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, Item par14Item, int par15) {
/* 24 */     this(par1World, par2, par4, par6, par14Item, par15);
/* 25 */     this.motionX *= 0.10000000149011612D;
/* 26 */     this.motionY *= 0.10000000149011612D;
/* 27 */     this.motionZ *= 0.10000000149011612D;
/* 28 */     this.motionX += par8;
/* 29 */     this.motionY += par10;
/* 30 */     this.motionZ += par12;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFXLayer() {
/* 36 */     return this.fx_layer;
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
/* 41 */     float var8 = (this.particleTextureIndexX + this.particleTextureJitterX / 4.0F) / 16.0F;
/* 42 */     float var9 = var8 + 0.015609375F;
/* 43 */     float var10 = (this.particleTextureIndexY + this.particleTextureJitterY / 4.0F) / 16.0F;
/* 44 */     float var11 = var10 + 0.015609375F;
/* 45 */     float var12 = 0.1F * this.particleScale;
/*    */     
/* 47 */     if (this.particleIcon != null) {
/*    */       
/* 49 */       var8 = this.particleIcon.getInterpolatedU((this.particleTextureJitterX / 4.0F * 16.0F));
/* 50 */       var9 = this.particleIcon.getInterpolatedU(((this.particleTextureJitterX + 1.0F) / 4.0F * 16.0F));
/* 51 */       var10 = this.particleIcon.getInterpolatedV((this.particleTextureJitterY / 4.0F * 16.0F));
/* 52 */       var11 = this.particleIcon.getInterpolatedV(((this.particleTextureJitterY + 1.0F) / 4.0F * 16.0F));
/*    */     } 
/*    */     
/* 55 */     float var13 = (float)(this.prevPosX + (this.posX - this.prevPosX) * par2 - interpPosX);
/* 56 */     float var14 = (float)(this.prevPosY + (this.posY - this.prevPosY) * par2 - interpPosY);
/* 57 */     float var15 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * par2 - interpPosZ);
/* 58 */     float var16 = 1.0F;
/* 59 */     par1Tessellator.setColorOpaque_F(var16 * this.particleRed, var16 * this.particleGreen, var16 * this.particleBlue);
/* 60 */     par1Tessellator.addVertexWithUV((var13 - par3 * var12 - par6 * var12), (var14 - par4 * var12), (var15 - par5 * var12 - par7 * var12), var8, var11);
/* 61 */     par1Tessellator.addVertexWithUV((var13 - par3 * var12 + par6 * var12), (var14 + par4 * var12), (var15 - par5 * var12 + par7 * var12), var8, var10);
/* 62 */     par1Tessellator.addVertexWithUV((var13 + par3 * var12 + par6 * var12), (var14 + par4 * var12), (var15 + par5 * var12 + par7 * var12), var9, var10);
/* 63 */     par1Tessellator.addVertexWithUV((var13 + par3 * var12 - par6 * var12), (var14 - par4 * var12), (var15 + par5 * var12 - par7 * var12), var9, var11);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityBreakingFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */