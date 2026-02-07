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
/*     */ public class Packet8UpdateHealth
/*     */   extends Packet
/*     */ {
/*     */   public float healthMP;
/*     */   public int satiation;
/*     */   public int nutrition;
/*     */   public float vision_dimming;
/*     */   
/*     */   public Packet8UpdateHealth() {}
/*     */   
/*     */   public Packet8UpdateHealth(float health, int satiation, int nutrition, float vision_dimming) {
/*  76 */     this.healthMP = health;
/*  77 */     this.satiation = satiation;
/*  78 */     this.nutrition = nutrition;
/*  79 */     this.vision_dimming = vision_dimming;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/*  84 */     this.healthMP = par1DataInput.readFloat();
/*  85 */     this.satiation = par1DataInput.readByte();
/*  86 */     this.nutrition = par1DataInput.readByte();
/*  87 */     this.vision_dimming = par1DataInput.readFloat();
/*     */   }
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/*  92 */     par1DataOutput.writeFloat(this.healthMP);
/*  93 */     par1DataOutput.writeByte(this.satiation);
/*  94 */     par1DataOutput.writeByte(this.nutrition);
/*  95 */     par1DataOutput.writeFloat(this.vision_dimming);
/*     */   }
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler par1NetHandler) {
/* 100 */     par1NetHandler.handleUpdateHealth(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/* 105 */     return 10;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRealPacket() {
/* 110 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsSameEntityIDAs(Packet par1Packet) {
/* 115 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet8UpdateHealth.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */