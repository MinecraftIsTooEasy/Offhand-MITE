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
/*     */ public class Packet63WorldParticles
/*     */   extends Packet
/*     */ {
/*     */   private String particleName;
/*     */   private float posX;
/*     */   private float posY;
/*     */   private float posZ;
/*     */   private float offsetX;
/*     */   private float offsetY;
/*     */   private float offsetZ;
/*     */   private float speed;
/*     */   private int quantity;
/*     */   
/*     */   public void readPacketData(DataInput dataInput) {
/*  35 */     this.particleName = readString(dataInput, 64);
/*  36 */     this.posX = dataInput.readFloat();
/*  37 */     this.posY = dataInput.readFloat();
/*  38 */     this.posZ = dataInput.readFloat();
/*  39 */     this.offsetX = dataInput.readFloat();
/*  40 */     this.offsetY = dataInput.readFloat();
/*  41 */     this.offsetZ = dataInput.readFloat();
/*  42 */     this.speed = dataInput.readFloat();
/*  43 */     this.quantity = dataInput.readInt();
/*     */   }
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput dataOutput) {
/*  48 */     writeString(this.particleName, dataOutput);
/*  49 */     dataOutput.writeFloat(this.posX);
/*  50 */     dataOutput.writeFloat(this.posY);
/*  51 */     dataOutput.writeFloat(this.posZ);
/*  52 */     dataOutput.writeFloat(this.offsetX);
/*  53 */     dataOutput.writeFloat(this.offsetY);
/*  54 */     dataOutput.writeFloat(this.offsetZ);
/*  55 */     dataOutput.writeFloat(this.speed);
/*  56 */     dataOutput.writeInt(this.quantity);
/*     */   }
/*     */   
/*     */   public String getParticleName() {
/*  60 */     return this.particleName;
/*     */   }
/*     */   
/*     */   public double getPositionX() {
/*  64 */     return this.posX;
/*     */   }
/*     */   
/*     */   public double getPositionY() {
/*  68 */     return this.posY;
/*     */   }
/*     */   
/*     */   public double getPositionZ() {
/*  72 */     return this.posZ;
/*     */   }
/*     */   
/*     */   public float getOffsetX() {
/*  76 */     return this.offsetX;
/*     */   }
/*     */   
/*     */   public float getOffsetY() {
/*  80 */     return this.offsetY;
/*     */   }
/*     */   
/*     */   public float getOffsetZ() {
/*  84 */     return this.offsetZ;
/*     */   }
/*     */   
/*     */   public float getSpeed() {
/*  88 */     return this.speed;
/*     */   }
/*     */   
/*     */   public int getQuantity() {
/*  92 */     return this.quantity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler netHandler) {
/*  97 */     netHandler.a(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/* 102 */     return 64;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet63WorldParticles.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */