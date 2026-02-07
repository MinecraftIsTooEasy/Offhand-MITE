/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class AnimationFrame
/*    */ {
/*    */   private final int frameIndex;
/*    */   private final int frameTime;
/*    */   
/*    */   public AnimationFrame(int i) {
/* 10 */     this(i, -1);
/*    */   }
/*    */   
/*    */   public AnimationFrame(int i, int j) {
/* 14 */     this.frameIndex = i;
/* 15 */     this.frameTime = j;
/*    */   }
/*    */   
/*    */   public boolean hasNoTime() {
/* 19 */     return (this.frameTime == -1);
/*    */   }
/*    */   
/*    */   public int getFrameTime() {
/* 23 */     return this.frameTime;
/*    */   }
/*    */   
/*    */   public int getFrameIndex() {
/* 27 */     return this.frameIndex;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\AnimationFrame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */