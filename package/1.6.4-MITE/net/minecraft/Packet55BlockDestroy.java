/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
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
/*     */ public class Packet55BlockDestroy
/*     */   extends Packet
/*     */ {
/*     */   private int entityId;
/*     */   private int posX;
/*     */   private int posY;
/*     */   private int posZ;
/*     */   private int destroyedStage;
/*     */   
/*     */   public Packet55BlockDestroy() {}
/*     */   
/*     */   public Packet55BlockDestroy(int par1, int par2, int par3, int par4, int par5) {
/*  28 */     this.entityId = par1;
/*  29 */     this.posX = par2;
/*  30 */     this.posY = par3;
/*  31 */     this.posZ = par4;
/*  32 */     this.destroyedStage = par5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/*  40 */     this.entityId = par1DataInput.readInt();
/*  41 */     this.posX = par1DataInput.readInt();
/*  42 */     this.posY = par1DataInput.readInt();
/*  43 */     this.posZ = par1DataInput.readInt();
/*  44 */     this.destroyedStage = par1DataInput.readUnsignedByte();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/*  52 */     par1DataOutput.writeInt(this.entityId);
/*  53 */     par1DataOutput.writeInt(this.posX);
/*  54 */     par1DataOutput.writeInt(this.posY);
/*  55 */     par1DataOutput.writeInt(this.posZ);
/*  56 */     par1DataOutput.write(this.destroyedStage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler par1NetHandler) {
/*  64 */     par1NetHandler.handleBlockDestroy(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/*  73 */     return 17;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEntityId() {
/*  81 */     return this.entityId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPosX() {
/*  89 */     return this.posX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPosY() {
/*  97 */     return this.posY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPosZ() {
/* 105 */     return this.posZ;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDestroyedStage() {
/* 113 */     return this.destroyedStage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRealPacket() {
/* 121 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsSameEntityIDAs(Packet par1Packet) {
/* 130 */     Packet55BlockDestroy var2 = (Packet55BlockDestroy)par1Packet;
/* 131 */     return (var2.entityId == this.entityId);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet55BlockDestroy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */