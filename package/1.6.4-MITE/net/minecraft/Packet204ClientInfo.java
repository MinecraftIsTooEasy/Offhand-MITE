/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ 
/*     */ public class Packet204ClientInfo
/*     */   extends Packet
/*     */ {
/*     */   private String language;
/*     */   private int renderDistance;
/*     */   private int chatVisisble;
/*     */   private boolean chatColours;
/*     */   private int gameDifficulty;
/*     */   private boolean showCape;
/*     */   
/*     */   public Packet204ClientInfo() {}
/*     */   
/*     */   public Packet204ClientInfo(String par1Str, int par2, int par3, boolean par4, int par5, boolean par6) {
/*  20 */     this.language = par1Str;
/*  21 */     this.renderDistance = par2;
/*  22 */     this.chatVisisble = par3;
/*  23 */     this.chatColours = par4;
/*  24 */     this.gameDifficulty = par5;
/*  25 */     this.showCape = par6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/*  33 */     this.language = readString(par1DataInput, 7);
/*  34 */     this.renderDistance = par1DataInput.readByte();
/*  35 */     byte var2 = par1DataInput.readByte();
/*  36 */     this.chatVisisble = var2 & 0x7;
/*  37 */     this.chatColours = ((var2 & 0x8) == 8);
/*  38 */     this.gameDifficulty = par1DataInput.readByte();
/*  39 */     this.showCape = par1DataInput.readBoolean();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/*  47 */     writeString(this.language, par1DataOutput);
/*  48 */     par1DataOutput.writeByte(this.renderDistance);
/*  49 */     par1DataOutput.writeByte(this.chatVisisble | (this.chatColours ? 1 : 0) << 3);
/*  50 */     par1DataOutput.writeByte(this.gameDifficulty);
/*  51 */     par1DataOutput.writeBoolean(this.showCape);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler par1NetHandler) {
/*  59 */     par1NetHandler.handleClientInfo(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/*  67 */     return 7;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLanguage() {
/*  72 */     return this.language;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRenderDistance() {
/*  77 */     return this.renderDistance;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getChatVisibility() {
/*  82 */     return this.chatVisisble;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getChatColours() {
/*  87 */     return this.chatColours;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDifficulty() {
/*  93 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getShowCape() {
/*  98 */     return this.showCape;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRealPacket() {
/* 106 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsSameEntityIDAs(Packet par1Packet) {
/* 115 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet204ClientInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */