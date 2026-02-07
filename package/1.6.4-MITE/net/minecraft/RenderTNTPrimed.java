/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class RenderTNTPrimed
/*    */   extends Render {
/*  7 */   private RenderBlocks blockRenderer = new RenderBlocks();
/*    */ 
/*    */   
/*    */   public RenderTNTPrimed() {
/* 11 */     this.shadowSize = 0.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderPrimedTNT(EntityTNTPrimed par1EntityTNTPrimed, double par2, double par4, double par6, float par8, float par9) {
/* 16 */     GL11.glPushMatrix();
/* 17 */     GL11.glTranslatef((float)par2, (float)par4, (float)par6);
/*    */ 
/*    */     
/* 20 */     if (par1EntityTNTPrimed.fuse - par9 + 1.0F < 10.0F) {
/*    */       
/* 22 */       float f1 = 1.0F - (par1EntityTNTPrimed.fuse - par9 + 1.0F) / 10.0F;
/*    */       
/* 24 */       if (f1 < 0.0F)
/*    */       {
/* 26 */         f1 = 0.0F;
/*    */       }
/*    */       
/* 29 */       if (f1 > 1.0F)
/*    */       {
/* 31 */         f1 = 1.0F;
/*    */       }
/*    */       
/* 34 */       f1 *= f1;
/* 35 */       f1 *= f1;
/* 36 */       float var11 = 1.0F + f1 * 0.3F;
/* 37 */       GL11.glScalef(var11, var11, var11);
/*    */     } 
/*    */     
/* 40 */     float var10 = (1.0F - (par1EntityTNTPrimed.fuse - par9 + 1.0F) / 100.0F) * 0.8F;
/* 41 */     bindEntityTexture(par1EntityTNTPrimed);
/* 42 */     this.blockRenderer.renderBlockAsItem(Block.tnt, 0, par1EntityTNTPrimed.getBrightness(par9));
/*    */     
/* 44 */     if (par1EntityTNTPrimed.fuse / 5 % 2 == 0) {
/*    */       
/* 46 */       GL11.glDisable(3553);
/* 47 */       GL11.glDisable(2896);
/* 48 */       GL11.glEnable(3042);
/* 49 */       GL11.glBlendFunc(770, 772);
/* 50 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, var10);
/* 51 */       this.blockRenderer.renderBlockAsItem(Block.tnt, 0, 1.0F);
/* 52 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 53 */       GL11.glDisable(3042);
/* 54 */       GL11.glEnable(2896);
/* 55 */       GL11.glEnable(3553);
/*    */     } 
/*    */     
/* 58 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_110808_a(EntityTNTPrimed par1EntityTNTPrimed) {
/* 63 */     return TextureMap.locationBlocksTexture;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 71 */     return func_110808_a((EntityTNTPrimed)par1Entity);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
/* 82 */     renderPrimedTNT((EntityTNTPrimed)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderTNTPrimed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */