/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderEnderCrystal
/*    */   extends Render
/*    */ {
/* 11 */   private static final ResourceLocation enderCrystalTextures = new ResourceLocation("textures/entity/endercrystal/endercrystal.png");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   private ModelBase field_76995_b = new ModelEnderCrystal(0.0F, true);
/*    */ 
/*    */ 
/*    */   
/*    */   public void doRenderEnderCrystal(EntityEnderCrystal entityEnderCrystal, double d, double e, double f, float g, float h) {
/* 23 */     float f1 = entityEnderCrystal.innerRotation + h;
/* 24 */     GL11.glPushMatrix();
/* 25 */     GL11.glTranslatef((float)d, (float)e, (float)f);
/*    */     
/* 27 */     bindTexture(enderCrystalTextures);
/* 28 */     float f2 = MathHelper.sin(f1 * 0.2F) / 2.0F + 0.5F;
/* 29 */     f2 = f2 * f2 + f2;
/* 30 */     this.field_76995_b.render(entityEnderCrystal, 0.0F, f1 * 3.0F, f2 * 0.2F, 0.0F, 0.0F, 0.0625F);
/* 31 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEnderCrystalTextures(EntityEnderCrystal entityEnderCrystal) {
/* 36 */     return enderCrystalTextures;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderEnderCrystal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */