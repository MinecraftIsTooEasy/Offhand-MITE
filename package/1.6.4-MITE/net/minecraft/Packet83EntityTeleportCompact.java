/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet83EntityTeleportCompact
/*    */   extends Packet34EntityTeleport
/*    */ {
/*    */   public Packet83EntityTeleportCompact() {}
/*    */   
/*    */   public Packet83EntityTeleportCompact(Entity entity) {
/* 15 */     super(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 20 */     this.entity_id = par1DataInput.readInt();
/* 21 */     this.scaled_pos_x = par1DataInput.readShort();
/* 22 */     this.scaled_pos_y = par1DataInput.readShort();
/* 23 */     this.scaled_pos_z = par1DataInput.readShort();
/* 24 */     this.scaled_yaw = par1DataInput.readByte();
/* 25 */     this.scaled_pitch = par1DataInput.readByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 30 */     par1DataOutput.writeInt(this.entity_id);
/* 31 */     par1DataOutput.writeShort(this.scaled_pos_x);
/* 32 */     par1DataOutput.writeShort(this.scaled_pos_y);
/* 33 */     par1DataOutput.writeShort(this.scaled_pos_z);
/* 34 */     par1DataOutput.write(this.scaled_yaw);
/* 35 */     par1DataOutput.write(this.scaled_pitch);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 40 */     return 12;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet83EntityTeleportCompact.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */