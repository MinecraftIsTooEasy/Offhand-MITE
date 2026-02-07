/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaterialTransparent
/*    */   extends Material
/*    */ {
/*    */   public MaterialTransparent(String name, MapColor par1MapColor) {
/* 11 */     super(name, par1MapColor);
/* 12 */     setReplaceable();
/*    */     
/* 14 */     setHarmedByAcid(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSolid() {
/* 19 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MaterialTransparent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */