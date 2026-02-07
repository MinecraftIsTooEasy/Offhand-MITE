/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderInvisibleStalker
/*    */   extends RenderBiped
/*    */ {
/*    */   public static final int body_texture = 0;
/*    */   
/*    */   public RenderInvisibleStalker() {
/* 12 */     super(new ModelInvisibleStalker(), 0.5F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setTextures() {
/* 17 */     setTexture(0, "textures/entity/wight");
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving) {
/* 22 */     return this.textures[0];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 30 */     return this.textures[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public float getModelOpacity(Entity entity) {
/* 35 */     return 0.05F;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderInvisibleStalker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */