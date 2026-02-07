/*    */ package net.minecraft;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ public class Packet89PlaySoundOnServerAtEntity extends Packet {
/*    */   public enum_sound sound;
/*    */   public int entity_id;
/*    */   public float volume;
/*    */   public float pitch;
/*    */   
/*    */   enum enum_sound {
/* 12 */     boat_bump;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Packet89PlaySoundOnServerAtEntity() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public Packet89PlaySoundOnServerAtEntity(enum_sound sound, Entity entity, float volume, float pitch) {
/* 22 */     this.sound = sound;
/* 23 */     this.entity_id = entity.entityId;
/* 24 */     this.volume = volume;
/* 25 */     this.pitch = pitch;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 30 */     this.sound = enum_sound.values()[par1DataInput.readByte()];
/* 31 */     this.entity_id = par1DataInput.readInt();
/* 32 */     this.volume = par1DataInput.readFloat();
/* 33 */     this.pitch = par1DataInput.readFloat();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 38 */     par1DataOutput.writeByte(this.sound.ordinal());
/* 39 */     par1DataOutput.writeInt(this.entity_id);
/* 40 */     par1DataOutput.writeFloat(this.volume);
/* 41 */     par1DataOutput.writeFloat(this.pitch);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler net_handler) {
/* 46 */     net_handler.handlePlaySoundOnServerAtEntity(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 51 */     return 13;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet89PlaySoundOnServerAtEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */