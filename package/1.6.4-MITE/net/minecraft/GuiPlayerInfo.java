/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiPlayerInfo
/*    */ {
/*    */   public final String name;
/*    */   private final String nameinLowerCase;
/*    */   public int responseTime;
/*    */   public int level;
/*    */   
/*    */   public GuiPlayerInfo(String par1Str, int level) {
/* 19 */     this.name = par1Str;
/* 20 */     this.nameinLowerCase = par1Str.toLowerCase();
/* 21 */     this.level = level;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiPlayerInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */