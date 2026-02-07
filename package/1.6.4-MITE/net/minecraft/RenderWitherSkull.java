/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderWitherSkull
/*    */   extends Render
/*    */ {
/* 11 */   private static final ResourceLocation invulnerableWitherTextures = new ResourceLocation("textures/entity/wither/wither_invulnerable.png");
/* 12 */   private static final ResourceLocation witherTextures = new ResourceLocation("textures/entity/wither/wither.png");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 17 */   private final ModelSkeletonHead skeletonHeadModel = new ModelSkeletonHead();
/*    */ 
/*    */   
/*    */   private float func_82400_a(float f, float g, float h) {
/* 21 */     float f1 = g - f;
/* 22 */     while (f1 < -180.0F)
/* 23 */       f1 += 360.0F; 
/* 24 */     while (f1 >= 180.0F)
/* 25 */       f1 -= 360.0F; 
/* 26 */     return f + h * f1;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_82399_a(EntityWitherSkull entityWitherSkull, double d, double e, double f, float g, float h) {
/* 32 */     GL11.glPushMatrix();
/* 33 */     GL11.glDisable(2884);
/*    */     
/* 35 */     float f1 = func_82400_a(entityWitherSkull.prevRotationYaw, entityWitherSkull.rotationYaw, h);
/* 36 */     float f2 = entityWitherSkull.prevRotationPitch + (entityWitherSkull.rotationPitch - entityWitherSkull.prevRotationPitch) * h;
/*    */     
/* 38 */     GL11.glTranslatef((float)d, (float)e, (float)f);
/*    */     
/* 40 */     float f3 = 0.0625F;
/* 41 */     GL11.glEnable(32826);
/* 42 */     GL11.glScalef(-1.0F, -1.0F, 1.0F);
/*    */     
/* 44 */     GL11.glEnable(3008);
/*    */     
/* 46 */     bindEntityTexture(entityWitherSkull);
/*    */     
/* 48 */     this.skeletonHeadModel.render(entityWitherSkull, 0.0F, 0.0F, 0.0F, f1, f2, f3);
/*    */     
/* 50 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_110809_a(EntityWitherSkull entityWitherSkull) {
/* 55 */     return entityWitherSkull.isInvulnerable() ? invulnerableWitherTextures : witherTextures;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderWitherSkull.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */