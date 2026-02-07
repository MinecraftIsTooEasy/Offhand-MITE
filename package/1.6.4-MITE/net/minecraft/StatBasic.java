/*    */ package net.minecraft;
/*    */ 
/*    */ public class StatBasic
/*    */   extends StatBase {
/*    */   public StatBasic(int i, String string, IStatType iStatType) {
/*  6 */     super(i, string, iStatType);
/*    */   }
/*    */   
/*    */   public StatBasic(int i, String string) {
/* 10 */     super(i, string);
/*    */   }
/*    */ 
/*    */   
/*    */   public StatBase registerStat() {
/* 15 */     super.registerStat();
/*    */     
/* 17 */     StatList.generalStats.add(this);
/*    */     
/* 19 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StatBasic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */