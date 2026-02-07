/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IntHashMap
/*     */ {
/*     */   private transient IntHashMapEntry[] slots;
/*     */   private transient int count;
/*     */   private int threshold;
/*     */   private final float growFactor;
/*     */   private volatile transient int versionStamp;
/*  17 */   private Set keySet = new HashSet();
/*     */ 
/*     */   
/*     */   public IntHashMap() {
/*  21 */     this.growFactor = 0.75F;
/*  22 */     this.threshold = 12;
/*  23 */     this.slots = new IntHashMapEntry[16];
/*     */   }
/*     */   
/*     */   private static int computeHash(int i) {
/*  27 */     i ^= i >>> 20 ^ i >>> 12;
/*  28 */     return i ^ i >>> 7 ^ i >>> 4;
/*     */   }
/*     */   
/*     */   private static int getSlotIndex(int i, int j) {
/*  32 */     return i & j - 1;
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
/*     */   public Object lookup(int i) {
/*  44 */     int j = computeHash(i);
/*  45 */     for (IntHashMapEntry intHashMapEntry = this.slots[getSlotIndex(j, this.slots.length)]; intHashMapEntry != null; intHashMapEntry = intHashMapEntry.nextEntry) {
/*  46 */       if (intHashMapEntry.hashEntry == i) return intHashMapEntry.valueEntry; 
/*     */     } 
/*  48 */     return null;
/*     */   }
/*     */   
/*     */   public boolean containsItem(int i) {
/*  52 */     return (lookupEntry(i) != null);
/*     */   }
/*     */   
/*     */   final IntHashMapEntry lookupEntry(int i) {
/*  56 */     int j = computeHash(i);
/*  57 */     for (IntHashMapEntry intHashMapEntry = this.slots[getSlotIndex(j, this.slots.length)]; intHashMapEntry != null; intHashMapEntry = intHashMapEntry.nextEntry) {
/*  58 */       if (intHashMapEntry.hashEntry == i) return intHashMapEntry; 
/*     */     } 
/*  60 */     return null;
/*     */   }
/*     */   
/*     */   public void addKey(int i, Object object) {
/*  64 */     this.keySet.add(Integer.valueOf(i));
/*  65 */     int j = computeHash(i);
/*  66 */     int k = getSlotIndex(j, this.slots.length);
/*  67 */     for (IntHashMapEntry intHashMapEntry = this.slots[k]; intHashMapEntry != null; intHashMapEntry = intHashMapEntry.nextEntry) {
/*  68 */       if (intHashMapEntry.hashEntry == i) {
/*  69 */         intHashMapEntry.valueEntry = object;
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*  74 */     this.versionStamp++;
/*  75 */     insert(j, i, object, k);
/*     */   }
/*     */ 
/*     */   
/*     */   private void grow(int i) {
/*  80 */     IntHashMapEntry[] arrayOfIntHashMapEntry1 = this.slots;
/*  81 */     int j = arrayOfIntHashMapEntry1.length;
/*  82 */     if (j == 1073741824) {
/*  83 */       this.threshold = Integer.MAX_VALUE;
/*     */       
/*     */       return;
/*     */     } 
/*  87 */     IntHashMapEntry[] arrayOfIntHashMapEntry2 = new IntHashMapEntry[i];
/*  88 */     copyTo(arrayOfIntHashMapEntry2);
/*  89 */     this.slots = arrayOfIntHashMapEntry2;
/*  90 */     this.threshold = (int)(i * this.growFactor);
/*     */   }
/*     */   
/*     */   private void copyTo(IntHashMapEntry[] intHashMapEntrys) {
/*  94 */     IntHashMapEntry[] arrayOfIntHashMapEntry = this.slots;
/*  95 */     int i = intHashMapEntrys.length;
/*  96 */     for (byte b = 0; b < arrayOfIntHashMapEntry.length; b++) {
/*  97 */       IntHashMapEntry intHashMapEntry = arrayOfIntHashMapEntry[b];
/*  98 */       if (intHashMapEntry != null) {
/*  99 */         arrayOfIntHashMapEntry[b] = null;
/*     */         do {
/* 101 */           IntHashMapEntry intHashMapEntry1 = intHashMapEntry.nextEntry;
/* 102 */           int j = getSlotIndex(intHashMapEntry.slotHash, i);
/* 103 */           intHashMapEntry.nextEntry = intHashMapEntrys[j];
/* 104 */           intHashMapEntrys[j] = intHashMapEntry;
/* 105 */           intHashMapEntry = intHashMapEntry1;
/* 106 */         } while (intHashMapEntry != null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object removeObject(int i) {
/* 112 */     this.keySet.remove(Integer.valueOf(i));
/* 113 */     IntHashMapEntry intHashMapEntry = removeEntry(i);
/* 114 */     return (intHashMapEntry == null) ? null : intHashMapEntry.valueEntry;
/*     */   }
/*     */   
/*     */   final IntHashMapEntry removeEntry(int i) {
/* 118 */     int j = computeHash(i);
/* 119 */     int k = getSlotIndex(j, this.slots.length);
/* 120 */     IntHashMapEntry intHashMapEntry1 = this.slots[k];
/* 121 */     IntHashMapEntry intHashMapEntry2 = intHashMapEntry1;
/*     */     
/* 123 */     while (intHashMapEntry2 != null) {
/* 124 */       IntHashMapEntry intHashMapEntry = intHashMapEntry2.nextEntry;
/* 125 */       if (intHashMapEntry2.hashEntry == i) {
/* 126 */         this.versionStamp++;
/* 127 */         this.count--;
/* 128 */         if (intHashMapEntry1 == intHashMapEntry2) { this.slots[k] = intHashMapEntry; }
/* 129 */         else { intHashMapEntry1.nextEntry = intHashMapEntry; }
/* 130 */          return intHashMapEntry2;
/*     */       } 
/* 132 */       intHashMapEntry1 = intHashMapEntry2;
/* 133 */       intHashMapEntry2 = intHashMapEntry;
/*     */     } 
/*     */     
/* 136 */     return intHashMapEntry2;
/*     */   }
/*     */   
/*     */   public void clearMap() {
/* 140 */     this.versionStamp++;
/* 141 */     IntHashMapEntry[] arrayOfIntHashMapEntry = this.slots;
/* 142 */     for (byte b = 0; b < arrayOfIntHashMapEntry.length; b++)
/* 143 */       arrayOfIntHashMapEntry[b] = null; 
/* 144 */     this.count = 0;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void insert(int i, int j, Object object, int k) {
/* 221 */     IntHashMapEntry intHashMapEntry = this.slots[k];
/* 222 */     this.slots[k] = new IntHashMapEntry(i, j, object, intHashMapEntry);
/* 223 */     if (this.count++ >= this.threshold) grow(2 * this.slots.length); 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\IntHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */