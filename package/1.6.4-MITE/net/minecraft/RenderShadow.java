/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderShadow
/*    */   extends RenderBiped
/*    */ {
/*    */   public static final int body_texture = 0;
/*    */   
/*    */   public RenderShadow() {
/* 11 */     super(new ModelInvisibleStalker(), 0.5F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setTextures() {
/* 16 */     setTexture(0, "textures/entity/shadow");
/*    */   }
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
/*    */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 29 */     return this.textures[0];
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderShadow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */