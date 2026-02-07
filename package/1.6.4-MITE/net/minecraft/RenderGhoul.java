/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderGhoul
/*    */   extends RenderBiped
/*    */ {
/*    */   public static final int texture_normal = 0;
/*    */   
/*    */   public RenderGhoul() {
/* 12 */     super(new ModelInvisibleStalker(), 0.5F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setTextures() {
/* 17 */     setTexture(0, "textures/entity/ghoul");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getGhoulTextures(EntityGhoul ghoul) {
/* 24 */     return this.textures[0];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 32 */     return getGhoulTextures((EntityGhoul)par1Entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderGhoul.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */