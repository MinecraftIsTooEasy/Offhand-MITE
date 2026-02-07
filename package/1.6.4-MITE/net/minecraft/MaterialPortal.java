/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class MaterialPortal
/*    */   extends Material
/*    */ {
/*    */   public MaterialPortal(MapColor par1MapColor) {
/*  8 */     super("portal", par1MapColor);
/*    */     
/* 10 */     setHarmedByAcid(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSolid() {
/* 15 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MaterialPortal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */