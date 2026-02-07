/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ public class Packet25EntityPainting
/*    */   extends Packet {
/*    */   public int entityId;
/*    */   public int xPosition;
/*    */   public int yPosition;
/*    */   public int zPosition;
/*    */   public int direction;
/*    */   public String title;
/*    */   
/*    */   public Packet25EntityPainting() {}
/*    */   
/*    */   public Packet25EntityPainting(EntityPainting entityPainting) {
/* 18 */     this.entityId = entityPainting.entityId;
/* 19 */     this.xPosition = entityPainting.xPosition;
/* 20 */     this.yPosition = entityPainting.yPosition;
/* 21 */     this.zPosition = entityPainting.zPosition;
/* 22 */     this.direction = entityPainting.hangingDirection;
/* 23 */     this.title = entityPainting.art.title;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 28 */     this.entityId = dataInput.readInt();
/* 29 */     this.title = readString(dataInput, EnumArt.maxArtTitleLength);
/* 30 */     this.xPosition = dataInput.readInt();
/* 31 */     this.yPosition = dataInput.readInt();
/* 32 */     this.zPosition = dataInput.readInt();
/* 33 */     this.direction = dataInput.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 38 */     dataOutput.writeInt(this.entityId);
/* 39 */     writeString(this.title, dataOutput);
/* 40 */     dataOutput.writeInt(this.xPosition);
/* 41 */     dataOutput.writeInt(this.yPosition);
/* 42 */     dataOutput.writeInt(this.zPosition);
/* 43 */     dataOutput.writeInt(this.direction);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 48 */     netHandler.handleEntityPainting(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 53 */     return 24;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet25EntityPainting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */