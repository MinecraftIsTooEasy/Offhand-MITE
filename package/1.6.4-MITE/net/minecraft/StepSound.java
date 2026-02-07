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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StepSound
/*    */ {
/*    */   public final String stepSoundName;
/*    */   public final float stepSoundVolume;
/*    */   public final float stepSoundPitch;
/*    */   
/*    */   public StepSound(String string, float f, float g) {
/* 50 */     this.stepSoundName = string;
/* 51 */     this.stepSoundVolume = f;
/* 52 */     this.stepSoundPitch = g;
/*    */   }
/*    */   
/*    */   public float getVolume() {
/* 56 */     return this.stepSoundVolume;
/*    */   }
/*    */   
/*    */   public float getPitch() {
/* 60 */     return this.stepSoundPitch;
/*    */   }
/*    */   
/*    */   public String getBreakSound() {
/* 64 */     return "dig." + this.stepSoundName;
/*    */   }
/*    */   
/*    */   public String getStepSound() {
/* 68 */     return "step." + this.stepSoundName;
/*    */   }
/*    */   
/*    */   public String getPlaceSound() {
/* 72 */     return getBreakSound();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StepSound.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */