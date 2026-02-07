/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet90BroadcastToAssociatedPlayers
/*    */   extends Packet
/*    */ {
/*    */   int enveloped_packet_id;
/*    */   public Packet packet;
/*    */   public boolean include_sender;
/*    */   
/*    */   public Packet90BroadcastToAssociatedPlayers() {}
/*    */   
/*    */   public Packet90BroadcastToAssociatedPlayers(Packet packet, boolean broadcast_to_sending_player) {
/* 23 */     this.enveloped_packet_id = packet.getPacketId();
/* 24 */     this.packet = packet;
/* 25 */     this.include_sender = broadcast_to_sending_player;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 30 */     this.enveloped_packet_id = par1DataInput.readUnsignedByte();
/*    */     
/* 32 */     this.packet = Packet.getNewPacket(null, this.enveloped_packet_id);
/*    */     
/* 34 */     this.packet.readPacketData(par1DataInput);
/* 35 */     this.include_sender = par1DataInput.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 40 */     par1DataOutput.writeByte(this.enveloped_packet_id);
/* 41 */     this.packet.writePacketData(par1DataOutput);
/* 42 */     par1DataOutput.writeBoolean(this.include_sender);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler net_handler) {
/* 48 */     net_handler.handleBroadcastToAssociatedPlayers(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 53 */     return 1 + this.packet.getPacketSize() + 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet90BroadcastToAssociatedPlayers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */