/*    */ package net.minecraft;
/*    */ 
/*    */ public class ChatLine {
/*    */   private final int updateCounterCreated;
/*    */   private final String lineString;
/*    */   private final int chatLineID;
/*    */   
/*    */   public ChatLine(int i, String string, int j) {
/*  9 */     this.lineString = string;
/* 10 */     this.updateCounterCreated = i;
/* 11 */     this.chatLineID = j;
/*    */   }
/*    */   
/*    */   public String getChatLineString() {
/* 15 */     return this.lineString;
/*    */   }
/*    */   
/*    */   public int getUpdatedCounter() {
/* 19 */     return this.updateCounterCreated;
/*    */   }
/*    */   
/*    */   public int getChatLineID() {
/* 23 */     return this.chatLineID;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ChatLine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */