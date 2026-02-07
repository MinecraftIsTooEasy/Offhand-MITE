/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ public class Packet3Chat
/*    */   extends Packet
/*    */ {
/*    */   public String message;
/*    */   private boolean isServer;
/*    */   public boolean permission_override;
/*    */   
/*    */   public Packet3Chat() {
/* 16 */     this.isServer = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Packet3Chat(ChatMessageComponent par1ChatMessageComponent) {
/* 21 */     this(par1ChatMessageComponent.toJson());
/*    */   }
/*    */ 
/*    */   
/*    */   public Packet3Chat(ChatMessageComponent par1ChatMessageComponent, boolean par2) {
/* 26 */     this(par1ChatMessageComponent.toJson(), par2);
/*    */   }
/*    */ 
/*    */   
/*    */   public Packet3Chat(String par1Str) {
/* 31 */     this(par1Str, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public Packet3Chat(String par1Str, boolean par2) {
/* 36 */     this.isServer = true;
/*    */     
/* 38 */     if (par1Str.length() > 32767)
/*    */     {
/* 40 */       par1Str = par1Str.substring(0, 32767);
/*    */     }
/*    */     
/* 43 */     this.message = par1Str;
/* 44 */     this.isServer = par2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 52 */     this.permission_override = par1DataInput.readBoolean();
/* 53 */     this.message = readString(par1DataInput, 32767);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 61 */     par1DataOutput.writeBoolean(this.permission_override);
/* 62 */     writeString(this.message, par1DataOutput);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler par1NetHandler) {
/* 70 */     par1NetHandler.handleChat(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 79 */     return 3 + this.message.length() * 2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getIsServer() {
/* 87 */     return this.isServer;
/*    */   }
/*    */ 
/*    */   
/*    */   public Packet3Chat setPermissionOverride() {
/* 92 */     this.permission_override = true;
/* 93 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet3Chat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */