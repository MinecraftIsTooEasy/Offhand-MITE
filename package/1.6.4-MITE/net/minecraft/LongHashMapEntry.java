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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class LongHashMapEntry
/*     */ {
/*     */   final long key;
/*     */   Object value;
/*     */   LongHashMapEntry nextEntry;
/*     */   final int hash;
/*     */   
/*     */   LongHashMapEntry(int i, long l, Object object, LongHashMapEntry longHashMapEntry) {
/* 182 */     this.value = object;
/* 183 */     this.nextEntry = longHashMapEntry;
/* 184 */     this.key = l;
/* 185 */     this.hash = i;
/*     */   }
/*     */   
/*     */   public final long getKey() {
/* 189 */     return this.key;
/*     */   }
/*     */   
/*     */   public final Object getValue() {
/* 193 */     return this.value;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean equals(Object object) {
/* 198 */     if (!(object instanceof LongHashMapEntry)) return false; 
/* 199 */     LongHashMapEntry longHashMapEntry = (LongHashMapEntry)object;
/* 200 */     Long long_1 = Long.valueOf(getKey());
/* 201 */     Long long_2 = Long.valueOf(longHashMapEntry.getKey());
/* 202 */     if (long_1 == long_2 || (long_1 != null && long_1.equals(long_2))) {
/* 203 */       Object object1 = getValue();
/* 204 */       Object object2 = longHashMapEntry.getValue();
/* 205 */       if (object1 == object2 || (object1 != null && object1.equals(object2))) return true; 
/*     */     } 
/* 207 */     return false;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/* 211 */     return LongHashMap.getHashCode(this.key);
/*     */   }
/*     */   
/*     */   public final String toString() {
/* 215 */     return getKey() + "=" + getValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\LongHashMapEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */