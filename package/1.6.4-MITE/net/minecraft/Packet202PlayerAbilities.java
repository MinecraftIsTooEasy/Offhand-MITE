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
/*     */ public class Packet202PlayerAbilities
/*     */   extends Packet
/*     */ {
/*     */   private boolean disableDamage;
/*     */   private boolean isFlying;
/*     */   private boolean allowFlying;
/*     */   private boolean isCreativeMode;
/*     */   private float flySpeed;
/*     */   private float walkSpeed;
/*     */   
/*     */   public Packet202PlayerAbilities() {}
/*     */   
/*     */   public Packet202PlayerAbilities(PlayerCapabilities playerCapabilities) {
/*  26 */     setDisableDamage(playerCapabilities.disableDamage);
/*  27 */     setFlying(playerCapabilities.isFlying);
/*  28 */     setAllowFlying(playerCapabilities.allowFlying);
/*  29 */     setCreativeMode(playerCapabilities.isCreativeMode);
/*  30 */     setFlySpeed(playerCapabilities.getFlySpeed());
/*  31 */     setWalkSpeed(playerCapabilities.getWalkSpeed());
/*     */   }
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput dataInput) {
/*  36 */     byte b = dataInput.readByte();
/*     */     
/*  38 */     setDisableDamage(((b & 0x1) > 0));
/*  39 */     setFlying(((b & 0x2) > 0));
/*  40 */     setAllowFlying(((b & 0x4) > 0));
/*  41 */     setCreativeMode(((b & 0x8) > 0));
/*  42 */     setFlySpeed(dataInput.readFloat());
/*  43 */     setWalkSpeed(dataInput.readFloat());
/*     */   }
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput dataOutput) {
/*  48 */     byte b = 0;
/*     */     
/*  50 */     if (getDisableDamage()) b = (byte)(b | 0x1); 
/*  51 */     if (getFlying()) b = (byte)(b | 0x2); 
/*  52 */     if (getAllowFlying()) b = (byte)(b | 0x4); 
/*  53 */     if (isCreativeMode()) b = (byte)(b | 0x8);
/*     */     
/*  55 */     dataOutput.writeByte(b);
/*  56 */     dataOutput.writeFloat(this.flySpeed);
/*  57 */     dataOutput.writeFloat(this.walkSpeed);
/*     */   }
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler netHandler) {
/*  62 */     netHandler.handlePlayerAbilities(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/*  67 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getDisableDamage() {
/*  76 */     return this.disableDamage;
/*     */   }
/*     */   
/*     */   public void setDisableDamage(boolean bl) {
/*  80 */     this.disableDamage = bl;
/*     */   }
/*     */   
/*     */   public boolean getFlying() {
/*  84 */     return this.isFlying;
/*     */   }
/*     */   
/*     */   public void setFlying(boolean bl) {
/*  88 */     this.isFlying = bl;
/*     */   }
/*     */   
/*     */   public boolean getAllowFlying() {
/*  92 */     return this.allowFlying;
/*     */   }
/*     */   
/*     */   public void setAllowFlying(boolean bl) {
/*  96 */     this.allowFlying = bl;
/*     */   }
/*     */   
/*     */   public boolean isCreativeMode() {
/* 100 */     return this.isCreativeMode;
/*     */   }
/*     */   
/*     */   public void setCreativeMode(boolean bl) {
/* 104 */     this.isCreativeMode = bl;
/*     */   }
/*     */   
/*     */   public float getFlySpeed() {
/* 108 */     return this.flySpeed;
/*     */   }
/*     */   
/*     */   public void setFlySpeed(float f) {
/* 112 */     this.flySpeed = f;
/*     */   }
/*     */   
/*     */   public float getWalkSpeed() {
/* 116 */     return this.walkSpeed;
/*     */   }
/*     */   
/*     */   public void setWalkSpeed(float f) {
/* 120 */     this.walkSpeed = f;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRealPacket() {
/* 125 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsSameEntityIDAs(Packet packet) {
/* 130 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet202PlayerAbilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */