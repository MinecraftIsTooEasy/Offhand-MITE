/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NibbleArray
/*     */ {
/*     */   public final byte[] data;
/*     */   
/*     */   public NibbleArray(int par1) {
/*  39 */     this.data = new byte[par1 >> 1];
/*     */   }
/*     */ 
/*     */   
/*     */   public NibbleArray(byte[] par1ArrayOfByte) {
/*  44 */     this.data = par1ArrayOfByte;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int get(int par1, int par2, int par3) {
/*  57 */     int var4 = par2 << 8 | par3 << 4 | par1;
/*  58 */     return ((var4 & 0x1) == 0) ? (this.data[var4 >> 1] & 0xF) : (this.data[var4 >> 1] >> 4 & 0xF);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int get(int xz_index, int y) {
/*  64 */     int var4 = y << 8 | xz_index;
/*  65 */     return ((var4 & 0x1) == 0) ? (this.data[var4 >> 1] & 0xF) : (this.data[var4 >> 1] >> 4 & 0xF);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(int par1, int par2, int par3, int par4) {
/*  86 */     int var5 = par2 << 8 | par3 << 4 | par1;
/*  87 */     int var6 = var5 >> 1;
/*     */     
/*  89 */     if ((var5 & 0x1) == 0) {
/*  90 */       this.data[var6] = (byte)(this.data[var6] & 0xF0 | par4);
/*     */     } else {
/*  92 */       this.data[var6] = (byte)(this.data[var6] & 0xF | par4 << 4);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(int xz_index, int y, int value) {
/* 100 */     int var5 = y << 8 | xz_index;
/* 101 */     int var6 = var5 >> 1;
/*     */     
/* 103 */     if ((var5 & 0x1) == 0) {
/* 104 */       this.data[var6] = (byte)(this.data[var6] & 0xF0 | value);
/*     */     } else {
/* 106 */       this.data[var6] = (byte)(this.data[var6] & 0xF | value << 4);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\NibbleArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */