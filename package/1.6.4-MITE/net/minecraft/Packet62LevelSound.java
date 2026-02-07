/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ 
/*    */ public class Packet62LevelSound
/*    */   extends Packet
/*    */ {
/*    */   private String soundName;
/*    */   private int effectX;
/* 12 */   private int effectY = Integer.MAX_VALUE;
/*    */   
/*    */   private int effectZ;
/*    */   private float volume;
/*    */   private int pitch;
/*    */   
/*    */   public Packet62LevelSound() {}
/*    */   
/*    */   public Packet62LevelSound(String string, double d, double e, double f, float g, float h) {
/* 21 */     this.soundName = string;
/* 22 */     this.effectX = (int)(d * 8.0D);
/* 23 */     this.effectY = (int)(e * 8.0D);
/* 24 */     this.effectZ = (int)(f * 8.0D);
/* 25 */     this.volume = g;
/* 26 */     this.pitch = (int)(h * 63.0F);
/*    */     
/* 28 */     if (this.pitch < 0) this.pitch = 0; 
/* 29 */     if (this.pitch > 255) this.pitch = 255;
/*    */   
/*    */   }
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 34 */     this.soundName = readString(dataInput, 256);
/* 35 */     this.effectX = dataInput.readInt();
/* 36 */     this.effectY = dataInput.readInt();
/* 37 */     this.effectZ = dataInput.readInt();
/* 38 */     this.volume = dataInput.readFloat();
/* 39 */     this.pitch = dataInput.readUnsignedByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 44 */     writeString(this.soundName, dataOutput);
/* 45 */     dataOutput.writeInt(this.effectX);
/* 46 */     dataOutput.writeInt(this.effectY);
/* 47 */     dataOutput.writeInt(this.effectZ);
/* 48 */     dataOutput.writeFloat(this.volume);
/* 49 */     dataOutput.writeByte(this.pitch);
/*    */   }
/*    */   
/*    */   public String getSoundName() {
/* 53 */     return this.soundName;
/*    */   }
/*    */   
/*    */   public double getEffectX() {
/* 57 */     return (this.effectX / 8.0F);
/*    */   }
/*    */   
/*    */   public double getEffectY() {
/* 61 */     return (this.effectY / 8.0F);
/*    */   }
/*    */   
/*    */   public double getEffectZ() {
/* 65 */     return (this.effectZ / 8.0F);
/*    */   }
/*    */   
/*    */   public float getVolume() {
/* 69 */     return this.volume;
/*    */   }
/*    */   
/*    */   public float getPitch() {
/* 73 */     return this.pitch / 63.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 78 */     netHandler.handleLevelSound(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 83 */     return 24;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet62LevelSound.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */