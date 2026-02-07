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
/*     */ 
/*     */ class IntHashMapEntry
/*     */ {
/*     */   final int hashEntry;
/*     */   Object valueEntry;
/*     */   IntHashMapEntry nextEntry;
/*     */   final int slotHash;
/*     */   
/*     */   IntHashMapEntry(int i, int j, Object object, IntHashMapEntry intHashMapEntry) {
/* 183 */     this.valueEntry = object;
/* 184 */     this.nextEntry = intHashMapEntry;
/* 185 */     this.hashEntry = j;
/* 186 */     this.slotHash = i;
/*     */   }
/*     */   
/*     */   public final int getHash() {
/* 190 */     return this.hashEntry;
/*     */   }
/*     */   
/*     */   public final Object getValue() {
/* 194 */     return this.valueEntry;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean equals(Object object) {
/* 199 */     if (!(object instanceof IntHashMapEntry)) return false; 
/* 200 */     IntHashMapEntry intHashMapEntry = (IntHashMapEntry)object;
/* 201 */     Integer integer1 = Integer.valueOf(getHash());
/* 202 */     Integer integer2 = Integer.valueOf(intHashMapEntry.getHash());
/* 203 */     if (integer1 == integer2 || (integer1 != null && integer1.equals(integer2))) {
/* 204 */       Object object1 = getValue();
/* 205 */       Object object2 = intHashMapEntry.getValue();
/* 206 */       if (object1 == object2 || (object1 != null && object1.equals(object2))) return true; 
/*     */     } 
/* 208 */     return false;
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/* 212 */     return IntHashMap.getHash(this.hashEntry);
/*     */   }
/*     */   
/*     */   public final String toString() {
/* 216 */     return getHash() + "=" + getValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\IntHashMapEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */