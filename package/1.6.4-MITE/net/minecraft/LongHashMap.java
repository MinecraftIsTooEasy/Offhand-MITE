/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LongHashMap
/*     */ {
/*   7 */   public transient LongHashMapEntry[] hashArray = new LongHashMapEntry[16];
/*     */ 
/*     */ 
/*     */   
/*     */   private transient int numHashElements;
/*     */ 
/*     */ 
/*     */   
/*  15 */   private int capacity = 12;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  20 */   private final float percentUseable = 0.75F;
/*     */ 
/*     */ 
/*     */   
/*     */   private volatile transient int modCount;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getHashedKey(long par0) {
/*  30 */     return hash((int)(par0 ^ par0 >>> 32L));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int hash(int par0) {
/*  38 */     par0 ^= par0 >>> 20 ^ par0 >>> 12;
/*  39 */     return par0 ^ par0 >>> 7 ^ par0 >>> 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getHashIndex(int par0, int par1) {
/*  47 */     return par0 & par1 - 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumHashElements() {
/*  52 */     return this.numHashElements;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValueByKey(long par1) {
/*  60 */     int var3 = getHashedKey(par1);
/*     */     
/*  62 */     for (LongHashMapEntry var4 = this.hashArray[getHashIndex(var3, this.hashArray.length)]; var4 != null; var4 = var4.nextEntry) {
/*     */       
/*  64 */       if (var4.key == par1)
/*     */       {
/*  66 */         return var4.value;
/*     */       }
/*     */     } 
/*     */     
/*  70 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsItem(long par1) {
/*  75 */     return (getEntry(par1) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   final LongHashMapEntry getEntry(long par1) {
/*  80 */     int var3 = getHashedKey(par1);
/*     */     
/*  82 */     for (LongHashMapEntry var4 = this.hashArray[getHashIndex(var3, this.hashArray.length)]; var4 != null; var4 = var4.nextEntry) {
/*     */       
/*  84 */       if (var4.key == par1)
/*     */       {
/*  86 */         return var4;
/*     */       }
/*     */     } 
/*     */     
/*  90 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(long par1, Object par3Obj) {
/*  98 */     int var4 = getHashedKey(par1);
/*  99 */     int var5 = getHashIndex(var4, this.hashArray.length);
/*     */     
/* 101 */     for (LongHashMapEntry var6 = this.hashArray[var5]; var6 != null; var6 = var6.nextEntry) {
/*     */       
/* 103 */       if (var6.key == par1) {
/*     */         
/* 105 */         var6.value = par3Obj;
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 110 */     this.modCount++;
/* 111 */     createKey(var4, par1, par3Obj, var5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resizeTable(int par1) {
/* 119 */     LongHashMapEntry[] var2 = this.hashArray;
/* 120 */     int var3 = var2.length;
/*     */     
/* 122 */     if (var3 == 1073741824) {
/*     */       
/* 124 */       this.capacity = Integer.MAX_VALUE;
/*     */     }
/*     */     else {
/*     */       
/* 128 */       LongHashMapEntry[] var4 = new LongHashMapEntry[par1];
/* 129 */       copyHashTableTo(var4);
/* 130 */       this.hashArray = var4;
/* 131 */       getClass(); this.capacity = (int)(par1 * 0.75F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void copyHashTableTo(LongHashMapEntry[] par1ArrayOfLongHashMapEntry) {
/* 140 */     LongHashMapEntry[] var2 = this.hashArray;
/* 141 */     int var3 = par1ArrayOfLongHashMapEntry.length;
/*     */     
/* 143 */     for (int var4 = 0; var4 < var2.length; var4++) {
/*     */       
/* 145 */       LongHashMapEntry var5 = var2[var4];
/*     */       
/* 147 */       if (var5 != null) {
/*     */         LongHashMapEntry var6;
/* 149 */         var2[var4] = null;
/*     */ 
/*     */ 
/*     */         
/*     */         do {
/* 154 */           var6 = var5.nextEntry;
/* 155 */           int var7 = getHashIndex(var5.hash, var3);
/* 156 */           var5.nextEntry = par1ArrayOfLongHashMapEntry[var7];
/* 157 */           par1ArrayOfLongHashMapEntry[var7] = var5;
/* 158 */           var5 = var6;
/*     */         }
/* 160 */         while (var6 != null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object remove(long par1) {
/* 170 */     LongHashMapEntry var3 = removeKey(par1);
/* 171 */     return (var3 == null) ? null : var3.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final LongHashMapEntry removeKey(long par1) {
/* 179 */     int var3 = getHashedKey(par1);
/* 180 */     int var4 = getHashIndex(var3, this.hashArray.length);
/* 181 */     LongHashMapEntry var5 = this.hashArray[var4];
/*     */     
/*     */     LongHashMapEntry var6;
/*     */     
/* 185 */     for (var6 = var5; var6 != null; var6 = var7) {
/*     */       
/* 187 */       LongHashMapEntry var7 = var6.nextEntry;
/*     */       
/* 189 */       if (var6.key == par1) {
/*     */         
/* 191 */         this.modCount++;
/* 192 */         this.numHashElements--;
/*     */         
/* 194 */         if (var5 == var6) {
/*     */           
/* 196 */           this.hashArray[var4] = var7;
/*     */         }
/*     */         else {
/*     */           
/* 200 */           var5.nextEntry = var7;
/*     */         } 
/*     */         
/* 203 */         return var6;
/*     */       } 
/*     */       
/* 206 */       var5 = var6;
/*     */     } 
/*     */     
/* 209 */     return var6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createKey(int par1, long par2, Object par4Obj, int par5) {
/* 217 */     LongHashMapEntry var6 = this.hashArray[par5];
/* 218 */     this.hashArray[par5] = new LongHashMapEntry(par1, par2, par4Obj, var6);
/*     */     
/* 220 */     if (this.numHashElements++ >= this.capacity)
/*     */     {
/* 222 */       resizeTable(2 * this.hashArray.length);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int getHashCode(long par0) {
/* 231 */     return getHashedKey(par0);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\LongHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */