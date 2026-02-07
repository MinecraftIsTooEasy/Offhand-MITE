/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityLargeExplodeFX
/*    */   extends EntityFX
/*    */ {
/* 16 */   private static final ResourceLocation field_110127_a = new ResourceLocation("textures/entity/explosion.png");
/*    */   private int field_70581_a;
/*    */   private int field_70584_aq;
/*    */   private TextureManager theRenderEngine;
/*    */   private float field_70582_as;
/*    */   
/*    */   public EntityLargeExplodeFX(TextureManager textureManager, World world, double d, double e, double f, double g, double h, double i) {
/* 23 */     super(world, d, e, f, 0.0D, 0.0D, 0.0D);
/* 24 */     this.theRenderEngine = textureManager;
/* 25 */     this.field_70584_aq = 6 + this.rand.nextInt(4);
/* 26 */     this.particleRed = this.particleGreen = this.particleBlue = this.rand.nextFloat() * 0.6F + 0.4F;
/* 27 */     this.field_70582_as = 1.0F - (float)g * 0.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderParticle(Tessellator tessellator, float f, float g, float h, float i, float j, float k) {
/* 32 */     int m = (int)((this.field_70581_a + f) * 15.0F / this.field_70584_aq);
/* 33 */     if (m > 15)
/* 34 */       return;  this.theRenderEngine.bindTexture(field_110127_a);
/*    */     
/* 36 */     float f1 = (m % 4) / 4.0F;
/* 37 */     float f2 = f1 + 0.24975F;
/* 38 */     float f3 = (m / 4) / 4.0F;
/* 39 */     float f4 = f3 + 0.24975F;
/*    */     
/* 41 */     float f5 = 2.0F * this.field_70582_as;
/*    */     
/* 43 */     float f6 = (float)(this.prevPosX + (this.posX - this.prevPosX) * f - interpPosX);
/* 44 */     float f7 = (float)(this.prevPosY + (this.posY - this.prevPosY) * f - interpPosY);
/* 45 */     float f8 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * f - interpPosZ);
/* 46 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 47 */     GL11.glDisable(2896);
/* 48 */     RenderHelper.disableStandardItemLighting();
/* 49 */     tessellator.startDrawingQuads();
/* 50 */     tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, 1.0F);
/* 51 */     tessellator.setNormal(0.0F, 1.0F, 0.0F);
/* 52 */     tessellator.setBrightness(240);
/* 53 */     tessellator.addVertexWithUV((f6 - g * f5 - j * f5), (f7 - h * f5), (f8 - i * f5 - k * f5), f2, f4);
/* 54 */     tessellator.addVertexWithUV((f6 - g * f5 + j * f5), (f7 + h * f5), (f8 - i * f5 + k * f5), f2, f3);
/* 55 */     tessellator.addVertexWithUV((f6 + g * f5 + j * f5), (f7 + h * f5), (f8 + i * f5 + k * f5), f1, f3);
/* 56 */     tessellator.addVertexWithUV((f6 + g * f5 - j * f5), (f7 - h * f5), (f8 + i * f5 - k * f5), f1, f4);
/* 57 */     tessellator.draw();
/* 58 */     GL11.glPolygonOffset(0.0F, 0.0F);
/* 59 */     GL11.glEnable(2896);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBrightnessForRender(float f) {
/* 64 */     return 61680;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 69 */     this.prevPosX = this.posX;
/* 70 */     this.prevPosY = this.posY;
/* 71 */     this.prevPosZ = this.posZ;
/* 72 */     this.field_70581_a++;
/* 73 */     if (this.field_70581_a == this.field_70584_aq) setDead();
/*    */   
/*    */   }
/*    */   
/*    */   public int getFXLayer() {
/* 78 */     return 3;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityLargeExplodeFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */