/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderBoat
/*    */   extends Render
/*    */ {
/* 12 */   private static final ResourceLocation boatTextures = new ResourceLocation("textures/entity/boat.png");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 17 */   protected ModelBase modelBoat = new ModelBoat();
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderBoat(EntityBoat entityBoat, double d, double e, double f, float g, float h) {
/* 22 */     GL11.glPushMatrix();
/*    */     
/* 24 */     GL11.glTranslatef((float)d, (float)e, (float)f);
/*    */     
/* 26 */     GL11.glRotatef(180.0F - g, 0.0F, 1.0F, 0.0F);
/* 27 */     float f1 = entityBoat.getTimeSinceHit() - h;
/* 28 */     float f2 = entityBoat.getDamageTaken() - h;
/* 29 */     if (f2 < 0.0F) f2 = 0.0F; 
/* 30 */     if (f1 > 0.0F) {
/* 31 */       GL11.glRotatef(MathHelper.sin(f1) * f1 * f2 / 10.0F * entityBoat.getForwardDirection(), 1.0F, 0.0F, 0.0F);
/*    */     }
/*    */     
/* 34 */     float f3 = 0.75F;
/* 35 */     GL11.glScalef(f3, f3, f3);
/* 36 */     GL11.glScalef(1.0F / f3, 1.0F / f3, 1.0F / f3);
/*    */     
/* 38 */     bindEntityTexture(entityBoat);
/* 39 */     GL11.glScalef(-1.0F, -1.0F, 1.0F);
/* 40 */     this.modelBoat.render(entityBoat, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/* 41 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation getBoatTextures(EntityBoat entityBoat) {
/* 46 */     return boatTextures;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderBoat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */