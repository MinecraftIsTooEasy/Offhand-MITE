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
/*     */ public class AABBPool
/*     */ {
/*     */   private final int maxNumCleans;
/*     */   private final int numEntriesToRemove;
/*  19 */   private final List listAABB = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*     */   private int nextPoolIndex;
/*     */ 
/*     */   
/*     */   private int maxPoolIndex;
/*     */ 
/*     */   
/*     */   private int numCleans;
/*     */ 
/*     */ 
/*     */   
/*     */   public AABBPool(int par1, int par2) {
/*  34 */     this.maxNumCleans = par1;
/*  35 */     this.numEntriesToRemove = par2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getAABB(double par1, double par3, double par5, double par7, double par9, double par11) {
/*     */     AxisAlignedBB var13;
/*  46 */     if (this.nextPoolIndex >= this.listAABB.size()) {
/*     */       
/*  48 */       var13 = new AxisAlignedBB(par1, par3, par5, par7, par9, par11);
/*  49 */       this.listAABB.add(var13);
/*     */     }
/*     */     else {
/*     */       
/*  53 */       var13 = this.listAABB.get(this.nextPoolIndex);
/*  54 */       var13.setBounds(par1, par3, par5, par7, par9, par11);
/*     */     } 
/*     */     
/*  57 */     this.nextPoolIndex++;
/*  58 */     return var13;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getAABB() {
/*  64 */     return getAABB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cleanPool() {
/*  73 */     if (this.nextPoolIndex > this.maxPoolIndex)
/*     */     {
/*  75 */       this.maxPoolIndex = this.nextPoolIndex;
/*     */     }
/*     */     
/*  78 */     if (this.numCleans++ == this.maxNumCleans) {
/*     */       
/*  80 */       int var1 = Math.max(this.maxPoolIndex, this.listAABB.size() - this.numEntriesToRemove);
/*     */       
/*  82 */       while (this.listAABB.size() > var1)
/*     */       {
/*  84 */         this.listAABB.remove(var1);
/*     */       }
/*     */       
/*  87 */       this.maxPoolIndex = 0;
/*  88 */       this.numCleans = 0;
/*     */     } 
/*     */     
/*  91 */     this.nextPoolIndex = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearPool() {
/*  99 */     this.nextPoolIndex = 0;
/* 100 */     this.listAABB.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getlistAABBsize() {
/* 105 */     return this.listAABB.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getnextPoolIndex() {
/* 110 */     return this.nextPoolIndex;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\AABBPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */