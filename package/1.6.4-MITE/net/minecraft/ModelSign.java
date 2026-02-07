/*    */ package net.minecraft;
/*    */ 
/*    */ public class ModelSign
/*    */   extends ModelBase
/*    */ {
/*    */   public ModelRenderer signBoard;
/*    */   public ModelRenderer signStick;
/*    */   
/*    */   public ModelSign() {
/* 10 */     this.signBoard = new ModelRenderer(this, 0, 0);
/* 11 */     this.signBoard.addBox(-12.0F, -14.0F, -1.0F, 24, 12, 2, 0.0F);
/*    */     
/* 13 */     this.signStick = new ModelRenderer(this, 0, 14);
/* 14 */     this.signStick.addBox(-1.0F, -2.0F, -1.0F, 2, 14, 2, 0.0F);
/*    */   }
/*    */   
/*    */   public void renderSign() {
/* 18 */     this.signBoard.render(0.0625F);
/* 19 */     this.signStick.render(0.0625F);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelSign.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */