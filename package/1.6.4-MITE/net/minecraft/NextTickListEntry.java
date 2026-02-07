/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class NextTickListEntry
/*    */   implements Comparable
/*    */ {
/*    */   private static long nextTickEntryID;
/*    */   public int xCoord;
/*    */   public int yCoord;
/* 10 */   private long tickEntryID = nextTickEntryID++; public int zCoord; public int blockID; public long scheduledTime; public int priority;
/*    */   
/*    */   public NextTickListEntry(int i, int j, int k, int l) {
/* 13 */     this.xCoord = i;
/* 14 */     this.yCoord = j;
/* 15 */     this.zCoord = k;
/* 16 */     this.blockID = l;
/*    */   }
/*    */   
/*    */   public boolean equals(Object object) {
/* 20 */     if (object instanceof NextTickListEntry) {
/* 21 */       NextTickListEntry nextTickListEntry = (NextTickListEntry)object;
/* 22 */       return (this.xCoord == nextTickListEntry.xCoord && this.yCoord == nextTickListEntry.yCoord && this.zCoord == nextTickListEntry.zCoord && Block.isAssociatedBlockID(this.blockID, nextTickListEntry.blockID));
/*    */     } 
/* 24 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 28 */     return (this.xCoord * 1024 * 1024 + this.zCoord * 1024 + this.yCoord) * 256;
/*    */   }
/*    */   
/*    */   public NextTickListEntry setScheduledTime(long l) {
/* 32 */     this.scheduledTime = l;
/* 33 */     return this;
/*    */   }
/*    */   
/*    */   public void setPriority(int i) {
/* 37 */     this.priority = i;
/*    */   }
/*    */ 
/*    */   
/*    */   public int comparer(NextTickListEntry nextTickListEntry) {
/* 42 */     if (this.scheduledTime < nextTickListEntry.scheduledTime) return -1; 
/* 43 */     if (this.scheduledTime > nextTickListEntry.scheduledTime) return 1; 
/* 44 */     if (this.priority != nextTickListEntry.priority) return this.priority - nextTickListEntry.priority; 
/* 45 */     if (this.tickEntryID < nextTickListEntry.tickEntryID) return -1; 
/* 46 */     if (this.tickEntryID > nextTickListEntry.tickEntryID) return 1; 
/* 47 */     return 0;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 51 */     return this.blockID + ": (" + this.xCoord + ", " + this.yCoord + ", " + this.zCoord + "), " + this.scheduledTime + ", " + this.priority + ", " + this.tickEntryID;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\NextTickListEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */