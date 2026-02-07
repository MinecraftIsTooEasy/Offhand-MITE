/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class Packet71Weather
/*    */   extends Packet
/*    */ {
/*    */   public int entityID;
/*    */   public int scaled_pos_x;
/*    */   public int scaled_pos_y;
/*    */   public int scaled_pos_z;
/*    */   public int isLightningBolt;
/*    */   
/*    */   public Packet71Weather() {}
/*    */   
/*    */   public Packet71Weather(Entity par1Entity) {
/* 19 */     this.entityID = par1Entity.entityId;
/*    */ 
/*    */ 
/*    */     
/* 23 */     this.scaled_pos_x = SpatialScaler.getScaledPosX(par1Entity);
/* 24 */     this.scaled_pos_y = SpatialScaler.getScaledPosY(par1Entity);
/* 25 */     this.scaled_pos_z = SpatialScaler.getScaledPosZ(par1Entity);
/*    */     
/* 27 */     if (par1Entity instanceof EntityLightningBolt)
/*    */     {
/* 29 */       this.isLightningBolt = 1;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 38 */     this.entityID = par1DataInput.readInt();
/* 39 */     this.isLightningBolt = par1DataInput.readByte();
/* 40 */     this.scaled_pos_x = par1DataInput.readInt();
/* 41 */     this.scaled_pos_y = par1DataInput.readInt();
/* 42 */     this.scaled_pos_z = par1DataInput.readInt();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 50 */     par1DataOutput.writeInt(this.entityID);
/* 51 */     par1DataOutput.writeByte(this.isLightningBolt);
/* 52 */     par1DataOutput.writeInt(this.scaled_pos_x);
/* 53 */     par1DataOutput.writeInt(this.scaled_pos_y);
/* 54 */     par1DataOutput.writeInt(this.scaled_pos_z);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler par1NetHandler) {
/* 62 */     par1NetHandler.handleWeather(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 70 */     return 17;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet71Weather.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */