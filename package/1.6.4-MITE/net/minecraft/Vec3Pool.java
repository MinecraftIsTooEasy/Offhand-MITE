/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Vec3Pool
/*     */ {
/*     */   private final int truncateArrayResetThreshold;
/*     */   private final int minimumSize;
/*  12 */   private final List vec3Cache = new ArrayList();
/*     */   
/*     */   private int nextFreeSpace;
/*     */   private int maximumSizeSinceLastTruncation;
/*     */   private int resetCount;
/*     */   
/*     */   public Vec3Pool(int par1, int par2) {
/*  19 */     this.truncateArrayResetThreshold = par1;
/*  20 */     this.minimumSize = par2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getVecFromPool(double par1, double par3, double par5) {
/*     */     Vec3 var7;
/*  28 */     if (func_82589_e())
/*     */     {
/*  30 */       return new Vec3(this, par1, par3, par5);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  36 */     if (this.nextFreeSpace >= this.vec3Cache.size()) {
/*     */       
/*  38 */       var7 = new Vec3(this, par1, par3, par5);
/*  39 */       this.vec3Cache.add(var7);
/*     */     }
/*     */     else {
/*     */       
/*  43 */       var7 = this.vec3Cache.get(this.nextFreeSpace);
/*  44 */       var7.setComponents(par1, par3, par5);
/*     */     } 
/*     */     
/*  47 */     this.nextFreeSpace++;
/*  48 */     return var7;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getVecFromPool(Vec3 vec3_to_copy) {
/*  54 */     return getVecFromPool(vec3_to_copy.xCoord, vec3_to_copy.yCoord, vec3_to_copy.zCoord);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/*  62 */     if (!func_82589_e()) {
/*     */       
/*  64 */       if (this.nextFreeSpace > this.maximumSizeSinceLastTruncation)
/*     */       {
/*  66 */         this.maximumSizeSinceLastTruncation = this.nextFreeSpace;
/*     */       }
/*     */       
/*  69 */       if (this.resetCount++ == this.truncateArrayResetThreshold) {
/*     */         
/*  71 */         int var1 = Math.max(this.maximumSizeSinceLastTruncation, this.vec3Cache.size() - this.minimumSize);
/*     */         
/*  73 */         while (this.vec3Cache.size() > var1)
/*     */         {
/*  75 */           this.vec3Cache.remove(var1);
/*     */         }
/*     */         
/*  78 */         this.maximumSizeSinceLastTruncation = 0;
/*  79 */         this.resetCount = 0;
/*     */       } 
/*     */       
/*  82 */       this.nextFreeSpace = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearAndFreeCache() {
/*  88 */     if (!func_82589_e()) {
/*     */       
/*  90 */       this.nextFreeSpace = 0;
/*  91 */       this.vec3Cache.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPoolSize() {
/*  97 */     return this.vec3Cache.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_82590_d() {
/* 102 */     return this.nextFreeSpace;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_82589_e() {
/* 107 */     return (this.minimumSize < 0 || this.truncateArrayResetThreshold < 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Vec3Pool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */