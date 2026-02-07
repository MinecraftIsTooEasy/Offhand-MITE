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
/*    */ 
/*    */ 
/*    */ public class Packet9Respawn
/*    */   extends Packet
/*    */ {
/*    */   public int respawnDimension;
/*    */   public int difficulty;
/*    */   public int worldHeight;
/*    */   public EnumGameType gameType;
/*    */   public WorldType terrainType;
/*    */   public long world_creation_time;
/*    */   public long total_world_time;
/*    */   
/*    */   public Packet9Respawn() {}
/*    */   
/*    */   public Packet9Respawn(int par1, byte par2, WorldType par3WorldType, int par4, EnumGameType par5EnumGameType, long world_creation_time, long total_world_time) {
/* 29 */     this.respawnDimension = par1;
/* 30 */     this.difficulty = par2;
/* 31 */     this.worldHeight = par4;
/* 32 */     this.gameType = par5EnumGameType;
/* 33 */     this.terrainType = par3WorldType;
/*    */     
/* 35 */     this.world_creation_time = world_creation_time;
/* 36 */     this.total_world_time = total_world_time;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler par1NetHandler) {
/* 44 */     par1NetHandler.handleRespawn(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 52 */     this.respawnDimension = par1DataInput.readInt();
/* 53 */     this.difficulty = par1DataInput.readByte();
/* 54 */     this.gameType = EnumGameType.getByID(par1DataInput.readByte());
/* 55 */     this.worldHeight = par1DataInput.readShort();
/* 56 */     String var2 = readString(par1DataInput, 16);
/* 57 */     this.terrainType = WorldType.parseWorldType(var2);
/*    */     
/* 59 */     if (this.terrainType == null)
/*    */     {
/* 61 */       this.terrainType = WorldType.DEFAULT;
/*    */     }
/*    */     
/* 64 */     this.world_creation_time = par1DataInput.readLong();
/* 65 */     this.total_world_time = par1DataInput.readLong();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 73 */     par1DataOutput.writeInt(this.respawnDimension);
/* 74 */     par1DataOutput.writeByte(this.difficulty);
/* 75 */     par1DataOutput.writeByte(this.gameType.getID());
/* 76 */     par1DataOutput.writeShort(this.worldHeight);
/* 77 */     writeString(this.terrainType.getWorldTypeName(), par1DataOutput);
/*    */     
/* 79 */     par1DataOutput.writeLong(this.world_creation_time);
/* 80 */     par1DataOutput.writeLong(this.total_world_time);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 88 */     return 8 + ((this.terrainType == null) ? 0 : this.terrainType.getWorldTypeName().length()) + 16;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet9Respawn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */