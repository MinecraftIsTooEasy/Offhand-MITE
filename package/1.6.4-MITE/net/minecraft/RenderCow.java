/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderCow
/*    */   extends RenderLiving
/*    */ {
/*    */   public static final int body_texture = 0;
/*    */   public static final int body_texture_sick = 1;
/*    */   
/*    */   public RenderCow(ModelBase par1ModelBase, float par2) {
/* 13 */     super(par1ModelBase, par2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setTextures() {
/* 18 */     setTexture(0, "textures/entity/cow/cow");
/* 19 */     setTexture(1, "textures/entity/cow/sick");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getCowTextures(EntityCow par1EntityCow) {
/* 29 */     if (par1EntityCow.isWell()) {
/* 30 */       return this.textures[0];
/*    */     }
/* 32 */     return this.textures[1];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 40 */     return getCowTextures((EntityCow)par1Entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderCow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */