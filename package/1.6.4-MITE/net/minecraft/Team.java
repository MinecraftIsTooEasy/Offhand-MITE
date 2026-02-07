/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Team
/*    */ {
/*    */   public boolean isSameTeam(Team team) {
/*  8 */     if (team == null) {
/*  9 */       return false;
/*    */     }
/* 11 */     if (this == team) {
/* 12 */       return true;
/*    */     }
/* 14 */     return false;
/*    */   }
/*    */   
/*    */   public abstract String func_96661_b();
/*    */   
/*    */   public abstract String func_142053_d(String paramString);
/*    */   
/*    */   public abstract boolean func_98297_h();
/*    */   
/*    */   public abstract boolean getAllowFriendlyFire();
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Team.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */