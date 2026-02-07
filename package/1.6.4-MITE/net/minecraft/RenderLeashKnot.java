/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderLeashKnot
/*    */   extends Render
/*    */ {
/* 10 */   private static final ResourceLocation leashKnotTextures = new ResourceLocation("textures/entity/lead_knot.png");
/*    */   
/* 12 */   private ModelLeashKnot leashKnotModel = new ModelLeashKnot();
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_110799_a(EntityLeashKnot entityLeashKnot, double d, double e, double f, float g, float h) {
/* 17 */     GL11.glPushMatrix();
/* 18 */     GL11.glDisable(2884);
/*    */     
/* 20 */     GL11.glTranslatef((float)d, (float)e, (float)f);
/*    */     
/* 22 */     float f1 = 0.0625F;
/* 23 */     GL11.glEnable(32826);
/* 24 */     GL11.glScalef(-1.0F, -1.0F, 1.0F);
/*    */     
/* 26 */     GL11.glEnable(3008);
/*    */     
/* 28 */     bindEntityTexture(entityLeashKnot);
/* 29 */     this.leashKnotModel.render(entityLeashKnot, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, f1);
/*    */     
/* 31 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation getLeashKnotTextures(EntityLeashKnot entityLeashKnot) {
/* 36 */     return leashKnotTextures;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderLeashKnot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */