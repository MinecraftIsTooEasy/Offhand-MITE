/*    */ package net.minecraft;
/*    */ 
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AnimationMetadataSection
/*    */   implements MetadataSection
/*    */ {
/*    */   private final List animationFrames;
/*    */   private final int frameWidth;
/*    */   private final int frameHeight;
/*    */   private final int frameTime;
/*    */   
/*    */   public AnimationMetadataSection(List list, int i, int j, int k) {
/* 22 */     this.animationFrames = list;
/* 23 */     this.frameWidth = i;
/* 24 */     this.frameHeight = j;
/* 25 */     this.frameTime = k;
/*    */   }
/*    */   
/*    */   public int getFrameHeight() {
/* 29 */     return this.frameHeight;
/*    */   }
/*    */   
/*    */   public int getFrameWidth() {
/* 33 */     return this.frameWidth;
/*    */   }
/*    */   
/*    */   public int getFrameCount() {
/* 37 */     return this.animationFrames.size();
/*    */   }
/*    */   
/*    */   public int getFrameTime() {
/* 41 */     return this.frameTime;
/*    */   }
/*    */   
/*    */   private AnimationFrame getAnimationFrame(int i) {
/* 45 */     return this.animationFrames.get(i);
/*    */   }
/*    */   
/*    */   public int getFrameTimeSingle(int i) {
/* 49 */     AnimationFrame animationFrame = getAnimationFrame(i);
/*    */     
/* 51 */     if (animationFrame.hasNoTime()) {
/* 52 */       return this.frameTime;
/*    */     }
/* 54 */     return animationFrame.getFrameTime();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean frameHasTime(int i) {
/* 59 */     return !((AnimationFrame)this.animationFrames.get(i)).hasNoTime();
/*    */   }
/*    */   
/*    */   public int getFrameIndex(int i) {
/* 63 */     return ((AnimationFrame)this.animationFrames.get(i)).getFrameIndex();
/*    */   }
/*    */   
/*    */   public Set getFrameIndexSet() {
/* 67 */     HashSet<Integer> hashSet = Sets.newHashSet();
/* 68 */     for (AnimationFrame animationFrame : this.animationFrames) {
/* 69 */       hashSet.add(Integer.valueOf(animationFrame.getFrameIndex()));
/*    */     }
/*    */     
/* 72 */     return hashSet;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\AnimationMetadataSection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */