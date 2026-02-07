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
/*     */ public class Packet2ClientProtocol
/*     */   extends Packet
/*     */ {
/*     */   private int protocolVersion;
/*     */   private String username;
/*     */   private String serverHost;
/*     */   private int serverPort;
/*     */   public String MC_version;
/*     */   public String MITE_release_number;
/*     */   private static final String delimiter = ":";
/*     */   private static final String delimiter_for_regexp = "\\:";
/*     */   
/*     */   public Packet2ClientProtocol() {}
/*     */   
/*     */   public Packet2ClientProtocol(int par1, String par2Str, String par3Str, int par4) {
/*  28 */     this.protocolVersion = par1;
/*     */ 
/*     */     
/*  31 */     this.username = par2Str + ":" + "1.6.4" + ":" + "R" + 'Ä';
/*  32 */     this.serverHost = par3Str;
/*  33 */     this.serverPort = par4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/*  41 */     this.protocolVersion = par1DataInput.readByte();
/*     */     
/*  43 */     this.username = readString(par1DataInput, 32);
/*  44 */     this.serverHost = readString(par1DataInput, 255);
/*  45 */     this.serverPort = par1DataInput.readInt();
/*     */ 
/*     */ 
/*     */     
/*  49 */     String[] parts = this.username.split("\\:");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  56 */     if (parts.length > 1) {
/*  57 */       this.MC_version = parts[1];
/*     */     }
/*  59 */     if (parts.length > 2) {
/*  60 */       this.MITE_release_number = parts[2];
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/*  73 */     par1DataOutput.writeByte(this.protocolVersion);
/*  74 */     writeString(this.username, par1DataOutput);
/*  75 */     writeString(this.serverHost, par1DataOutput);
/*  76 */     par1DataOutput.writeInt(this.serverPort);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler par1NetHandler) {
/*  84 */     par1NetHandler.handleClientProtocol(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/*  92 */     return 3 + 2 * this.username.length();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getProtocolVersion() {
/* 100 */     return this.protocolVersion;
/*     */   }
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
/*     */   public String getUsername() {
/* 113 */     return this.username.split("\\:")[0];
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet2ClientProtocol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */