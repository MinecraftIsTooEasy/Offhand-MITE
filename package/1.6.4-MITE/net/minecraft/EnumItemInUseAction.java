/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumItemInUseAction
/*    */ {
/*  8 */   EAT,
/*  9 */   DRINK,
/* 10 */   BLOCK,
/* 11 */   BOW;
/*    */ 
/*    */   
/*    */   boolean isIngestion() {
/* 15 */     return (this == EAT || this == DRINK);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumItemInUseAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */