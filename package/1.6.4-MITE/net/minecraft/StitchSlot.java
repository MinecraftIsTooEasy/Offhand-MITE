/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StitchSlot
/*     */ {
/*     */   private final int originX;
/*     */   private final int originY;
/*     */   private final int width;
/*     */   private final int height;
/*     */   private List subSlots;
/*     */   private StitchHolder holder;
/*     */   
/*     */   public StitchSlot(int i, int j, int k, int l) {
/* 296 */     this.originX = i;
/* 297 */     this.originY = j;
/* 298 */     this.width = k;
/* 299 */     this.height = l;
/*     */   }
/*     */   
/*     */   public StitchHolder getStitchHolder() {
/* 303 */     return this.holder;
/*     */   }
/*     */   
/*     */   public int getOriginX() {
/* 307 */     return this.originX;
/*     */   }
/*     */   
/*     */   public int getOriginY() {
/* 311 */     return this.originY;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addSlot(StitchHolder stitchHolder) {
/* 316 */     if (this.holder != null) {
/* 317 */       return false;
/*     */     }
/*     */     
/* 320 */     int i = stitchHolder.getWidth();
/* 321 */     int j = stitchHolder.getHeight();
/*     */ 
/*     */     
/* 324 */     if (i > this.width || j > this.height) {
/* 325 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 329 */     if (i == this.width && j == this.height) {
/*     */       
/* 331 */       this.holder = stitchHolder;
/* 332 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 336 */     if (this.subSlots == null) {
/* 337 */       this.subSlots = new ArrayList(1);
/*     */ 
/*     */       
/* 340 */       this.subSlots.add(new StitchSlot(this.originX, this.originY, i, j));
/*     */       
/* 342 */       int k = this.width - i;
/* 343 */       int m = this.height - j;
/*     */       
/* 345 */       if (m > 0 && k > 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 360 */         int n = Math.max(this.height, k);
/* 361 */         int i1 = Math.max(this.width, m);
/* 362 */         if (n >= i1) {
/* 363 */           this.subSlots.add(new StitchSlot(this.originX, this.originY + j, i, m));
/* 364 */           this.subSlots.add(new StitchSlot(this.originX + i, this.originY, k, this.height));
/*     */         } else {
/* 366 */           this.subSlots.add(new StitchSlot(this.originX + i, this.originY, k, j));
/* 367 */           this.subSlots.add(new StitchSlot(this.originX, this.originY + j, this.width, m));
/*     */         }
/*     */       
/* 370 */       } else if (k == 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 380 */         this.subSlots.add(new StitchSlot(this.originX, this.originY + j, i, m));
/* 381 */       } else if (m == 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 392 */         this.subSlots.add(new StitchSlot(this.originX + i, this.originY, k, j));
/*     */       } 
/*     */     } 
/*     */     
/* 396 */     for (StitchSlot stitchSlot : this.subSlots) {
/* 397 */       if (stitchSlot.addSlot(stitchHolder)) {
/* 398 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 402 */     return false;
/*     */   }
/*     */   
/*     */   public void getAllStitchSlots(List<StitchSlot> list) {
/* 406 */     if (this.holder != null) {
/* 407 */       list.add(this);
/* 408 */     } else if (this.subSlots != null) {
/* 409 */       for (StitchSlot stitchSlot : this.subSlots) {
/* 410 */         stitchSlot.getAllStitchSlots(list);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 417 */     return "Slot{originX=" + this.originX + ", originY=" + this.originY + ", width=" + this.width + ", height=" + this.height + ", texture=" + this.holder + ", subSlots=" + this.subSlots + '}';
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StitchSlot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */