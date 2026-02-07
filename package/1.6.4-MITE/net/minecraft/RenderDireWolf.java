/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderDireWolf
/*    */   extends RenderWolf
/*    */ {
/*    */   private float scale;
/*    */   
/*    */   public RenderDireWolf(ModelBase par1ModelBase, ModelBase par2ModelBase, float par2, float scale) {
/* 13 */     super(par1ModelBase, par2ModelBase, par2 * scale);
/* 14 */     this.scale = scale;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setTextures() {
/* 19 */     setTexture(0, "textures/entity/dire_wolf/neutral");
/* 20 */     setTexture(1, "textures/entity/dire_wolf/tame");
/* 21 */     setTexture(2, "textures/entity/dire_wolf/angry");
/* 22 */     setTexture(3, "textures/entity/dire_wolf/collar");
/*    */   }
/*    */ 
/*    */   
/*    */   protected void preRenderScale(EntityDireWolf par1EntityDireWolf, float par2) {
/* 27 */     GL11.glScalef(this.scale, this.scale, this.scale);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
/* 32 */     preRenderScale((EntityDireWolf)par1EntityLivingBase, par2);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderDireWolf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */