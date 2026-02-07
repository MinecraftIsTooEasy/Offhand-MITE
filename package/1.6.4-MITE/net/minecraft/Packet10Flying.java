/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
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
/*     */ public class Packet10Flying
/*     */   extends Packet
/*     */ {
/*     */   public double xPosition;
/*     */   public double yPosition;
/*     */   public double zPosition;
/*     */   public double stance;
/*     */   public float yaw;
/*     */   public float pitch;
/*     */   public boolean onGround;
/*     */   public boolean moving;
/*     */   public boolean rotating;
/*     */   
/*     */   public Packet10Flying() {}
/*     */   
/*     */   public Packet10Flying(boolean bl) {
/* 131 */     this.onGround = bl;
/*     */   }
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler netHandler) {
/* 136 */     netHandler.handleFlying(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput dataInput) {
/* 141 */     this.onGround = (dataInput.readUnsignedByte() != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput dataOutput) {
/* 146 */     dataOutput.write(this.onGround ? 1 : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/* 151 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRealPacket() {
/* 156 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsSameEntityIDAs(Packet packet) {
/* 161 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet10Flying.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */