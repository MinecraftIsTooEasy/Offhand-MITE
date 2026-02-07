/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderBlackWidowSpider
/*    */   extends RenderArachnid
/*    */ {
/*    */   private float scale;
/*    */   
/*    */   public RenderBlackWidowSpider(float scale) {
/* 13 */     super(new ModelArachnid(), new ModelArachnid(), scale);
/*    */     
/* 15 */     this.scale = scale;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
/* 20 */     super.preRenderCallback(par1EntityLivingBase, par2);
/* 21 */     GL11.glScalef(this.scale, this.scale, this.scale);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSubtypeName() {
/* 26 */     return "black_widow";
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderBlackWidowSpider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */