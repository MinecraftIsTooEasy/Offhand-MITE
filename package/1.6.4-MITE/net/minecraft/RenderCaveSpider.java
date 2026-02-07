/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderCaveSpider
/*    */   extends RenderArachnid
/*    */ {
/*    */   public RenderCaveSpider() {
/* 11 */     super(new ModelArachnid(), new ModelArachnid(), 1.0F);
/*    */     
/* 13 */     this.shadowSize *= 0.7F;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void scaleSpider(EntityCaveSpider par1EntityCaveSpider, float par2) {
/* 18 */     GL11.glScalef(0.7F, 0.7F, 0.7F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
/* 27 */     scaleSpider((EntityCaveSpider)par1EntityLivingBase, par2);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSubtypeName() {
/* 32 */     return "cave_spider";
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderCaveSpider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */