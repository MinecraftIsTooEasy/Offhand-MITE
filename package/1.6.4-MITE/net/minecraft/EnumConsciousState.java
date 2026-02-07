/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumConsciousState
/*    */ {
/*  7 */   fully_awake,
/*  8 */   falling_asleep,
/*  9 */   sleeping,
/* 10 */   waking_up;
/*    */ 
/*    */   
/*    */   public void print() {
/* 14 */     if (this == fully_awake) {
/* 15 */       System.out.println("fully awake");
/* 16 */     } else if (this == falling_asleep) {
/* 17 */       System.out.println("falling asleep");
/* 18 */     } else if (this == sleeping) {
/* 19 */       System.out.println("sleeping");
/* 20 */     } else if (this == waking_up) {
/* 21 */       System.out.println("waking up");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumConsciousState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */