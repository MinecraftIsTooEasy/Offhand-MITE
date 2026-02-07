/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ 
/*    */ public class Packet40EntityMetadata
/*    */   extends Packet
/*    */ {
/*    */   public int entityId;
/*    */   private List metadata;
/*    */   
/*    */   public Packet40EntityMetadata() {}
/*    */   
/*    */   public Packet40EntityMetadata(int par1, DataWatcher par2DataWatcher, boolean par3) {
/* 17 */     this.entityId = par1;
/*    */     
/* 19 */     if (par3) {
/*    */       
/* 21 */       this.metadata = par2DataWatcher.getAllWatched();
/*    */     }
/*    */     else {
/*    */       
/* 25 */       this.metadata = par2DataWatcher.unwatchAndReturnAllWatched();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 34 */     this.entityId = par1DataInput.readInt();
/* 35 */     this.metadata = DataWatcher.readWatchableObjects(par1DataInput);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 43 */     par1DataOutput.writeInt(this.entityId);
/* 44 */     DataWatcher.writeObjectsInListToStream(this.metadata, par1DataOutput);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler par1NetHandler) {
/* 52 */     par1NetHandler.handleEntityMetadata(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 61 */     return 4 + DataWatcher.getPacketSizeOfObjectsInListToStream(this.metadata);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getMetadata() {
/* 66 */     return this.metadata;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet40EntityMetadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */