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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityFootStepFX
/*    */   extends EntityFX
/*    */ {
/* 19 */   private static final ResourceLocation field_110126_a = new ResourceLocation("textures/particle/footprint.png");
/*    */   private int footstepAge;
/*    */   private int footstepMaxAge;
/*    */   private TextureManager currentFootSteps;
/*    */   
/*    */   public EntityFootStepFX(TextureManager textureManager, World world, double d, double e, double f) {
/* 25 */     super(world, d, e, f, 0.0D, 0.0D, 0.0D);
/* 26 */     this.currentFootSteps = textureManager;
/* 27 */     this.motionX = this.motionY = this.motionZ = 0.0D;
/* 28 */     this.footstepMaxAge = 200;
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderParticle(Tessellator tessellator, float f, float g, float h, float i, float j, float k) {
/* 33 */     float f1 = (this.footstepAge + f) / this.footstepMaxAge;
/* 34 */     f1 *= f1;
/*    */     
/* 36 */     float f2 = 2.0F - f1 * 2.0F;
/* 37 */     if (f2 > 1.0F) f2 = 1.0F; 
/* 38 */     f2 *= 0.2F;
/*    */     
/* 40 */     GL11.glDisable(2896);
/* 41 */     float f3 = 0.125F;
/*    */     
/* 43 */     float f4 = (float)(this.posX - interpPosX);
/* 44 */     float f5 = (float)(this.posY - interpPosY);
/* 45 */     float f6 = (float)(this.posZ - interpPosZ);
/*    */     
/* 47 */     float f7 = this.worldObj.getLightBrightness(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
/*    */     
/* 49 */     this.currentFootSteps.bindTexture(field_110126_a);
/* 50 */     GL11.glEnable(3042);
/* 51 */     GL11.glBlendFunc(770, 771);
/*    */     
/* 53 */     tessellator.startDrawingQuads();
/* 54 */     tessellator.setColorRGBA_F(f7, f7, f7, f2);
/* 55 */     tessellator.addVertexWithUV((f4 - f3), f5, (f6 + f3), 0.0D, 1.0D);
/* 56 */     tessellator.addVertexWithUV((f4 + f3), f5, (f6 + f3), 1.0D, 1.0D);
/* 57 */     tessellator.addVertexWithUV((f4 + f3), f5, (f6 - f3), 1.0D, 0.0D);
/* 58 */     tessellator.addVertexWithUV((f4 - f3), f5, (f6 - f3), 0.0D, 0.0D);
/* 59 */     tessellator.draw();
/*    */     
/* 61 */     GL11.glDisable(3042);
/* 62 */     GL11.glEnable(2896);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 67 */     this.footstepAge++;
/* 68 */     if (this.footstepAge == this.footstepMaxAge) setDead();
/*    */   
/*    */   }
/*    */   
/*    */   public int getFXLayer() {
/* 73 */     return 3;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityFootStepFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */