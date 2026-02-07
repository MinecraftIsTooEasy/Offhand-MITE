/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
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
/*     */ public class Packet209SetPlayerTeam
/*     */   extends Packet
/*     */ {
/*  20 */   public String teamName = "";
/*  21 */   public String teamDisplayName = "";
/*  22 */   public String teamPrefix = "";
/*  23 */   public String teamSuffix = "";
/*  24 */   public Collection playerNames = new ArrayList();
/*     */   
/*     */   public int mode;
/*     */   public int friendlyFire;
/*     */   
/*     */   public Packet209SetPlayerTeam() {}
/*     */   
/*     */   public Packet209SetPlayerTeam(ScorePlayerTeam scorePlayerTeam, int i) {
/*  32 */     this.teamName = scorePlayerTeam.func_96661_b();
/*  33 */     this.mode = i;
/*     */     
/*  35 */     if (i == 0 || i == 2) {
/*  36 */       this.teamDisplayName = scorePlayerTeam.func_96669_c();
/*  37 */       this.teamPrefix = scorePlayerTeam.getColorPrefix();
/*  38 */       this.teamSuffix = scorePlayerTeam.getColorSuffix();
/*  39 */       this.friendlyFire = scorePlayerTeam.func_98299_i();
/*     */     } 
/*  41 */     if (i == 0) {
/*  42 */       this.playerNames.addAll(scorePlayerTeam.getMembershipCollection());
/*     */     }
/*     */   }
/*     */   
/*     */   public Packet209SetPlayerTeam(ScorePlayerTeam scorePlayerTeam, Collection collection, int i) {
/*  47 */     if (i != 3 && i != 4) throw new IllegalArgumentException("Method must be join or leave for player constructor"); 
/*  48 */     if (collection == null || collection.isEmpty()) throw new IllegalArgumentException("Players cannot be null/empty");
/*     */     
/*  50 */     this.mode = i;
/*  51 */     this.teamName = scorePlayerTeam.func_96661_b();
/*  52 */     this.playerNames.addAll(collection);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput dataInput) {
/*  57 */     this.teamName = readString(dataInput, 16);
/*  58 */     this.mode = dataInput.readByte();
/*     */     
/*  60 */     if (this.mode == 0 || this.mode == 2) {
/*  61 */       this.teamDisplayName = readString(dataInput, 32);
/*  62 */       this.teamPrefix = readString(dataInput, 16);
/*  63 */       this.teamSuffix = readString(dataInput, 16);
/*  64 */       this.friendlyFire = dataInput.readByte();
/*     */     } 
/*     */     
/*  67 */     if (this.mode == 0 || this.mode == 3 || this.mode == 4) {
/*  68 */       short s = dataInput.readShort();
/*     */       
/*  70 */       for (byte b = 0; b < s; b++) {
/*  71 */         this.playerNames.add(readString(dataInput, 16));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput dataOutput) {
/*  78 */     writeString(this.teamName, dataOutput);
/*  79 */     dataOutput.writeByte(this.mode);
/*     */     
/*  81 */     if (this.mode == 0 || this.mode == 2) {
/*  82 */       writeString(this.teamDisplayName, dataOutput);
/*  83 */       writeString(this.teamPrefix, dataOutput);
/*  84 */       writeString(this.teamSuffix, dataOutput);
/*  85 */       dataOutput.writeByte(this.friendlyFire);
/*     */     } 
/*     */     
/*  88 */     if (this.mode == 0 || this.mode == 3 || this.mode == 4) {
/*  89 */       dataOutput.writeShort(this.playerNames.size());
/*     */       
/*  91 */       for (String str : this.playerNames) {
/*  92 */         writeString(str, dataOutput);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler netHandler) {
/*  99 */     netHandler.handleSetPlayerTeam(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/* 104 */     return 3 + this.teamName.length();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet209SetPlayerTeam.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */