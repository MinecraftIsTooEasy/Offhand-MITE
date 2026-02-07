/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ public class RenderSlime
/*    */   extends RenderLiving
/*    */ {
/*  9 */   private static final ResourceLocation slimeTextures = new ResourceLocation("textures/entity/slime/slime.png");
/*    */   private ModelBase scaleAmount;
/*    */   
/*    */   public RenderSlime(ModelBase modelBase, ModelBase modelBase2, float f) {
/* 13 */     super(modelBase, f);
/* 14 */     this.scaleAmount = modelBase2;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int shouldSlimeRenderPass(EntitySlime entitySlime, int i, float f) {
/* 19 */     if (entitySlime.isInvisible()) {
/* 20 */       return 0;
/*    */     }
/* 22 */     if (i == 0) {
/* 23 */       setRenderPassModel(this.scaleAmount);
/*    */       
/* 25 */       GL11.glEnable(2977);
/* 26 */       GL11.glEnable(3042);
/* 27 */       GL11.glBlendFunc(770, 771);
/*    */       
/* 29 */       return 1;
/*    */     } 
/* 31 */     if (i == 1) {
/* 32 */       GL11.glDisable(3042);
/* 33 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     } 
/* 35 */     return -1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void scaleSlime(EntitySlime entitySlime, float f) {
/* 40 */     float f1 = entitySlime.bR();
/* 41 */     float f2 = (entitySlime.j + (entitySlime.i - entitySlime.j) * f) / (f1 * 0.5F + 1.0F);
/* 42 */     float f3 = 1.0F / (f2 + 1.0F);
/* 43 */     GL11.glScalef(f3 * f1, 1.0F / f3 * f1, f3 * f1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation getSlimeTextures(EntitySlime entitySlime) {
/* 48 */     return slimeTextures;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderSlime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */