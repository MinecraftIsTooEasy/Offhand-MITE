/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketTimeoutException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RConThreadClient
/*     */   extends RConThreadBase
/*     */ {
/*     */   private boolean loggedIn;
/*     */   private Socket clientSocket;
/*  21 */   private byte[] buffer = new byte[1460];
/*     */ 
/*     */   
/*     */   private String rconPassword;
/*     */ 
/*     */   
/*     */   RConThreadClient(IServer par1IServer, Socket par2Socket) {
/*  28 */     super(par1IServer);
/*  29 */     this.clientSocket = par2Socket;
/*     */ 
/*     */     
/*     */     try {
/*  33 */       this.clientSocket.setSoTimeout(0);
/*     */     }
/*  35 */     catch (Exception var4) {
/*     */       
/*  37 */       this.running = false;
/*     */     } 
/*     */     
/*  40 */     this.rconPassword = par1IServer.getStringProperty("rcon.password", "");
/*  41 */     logInfo("Rcon connection from: " + par2Socket.getInetAddress());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     try {
/*  50 */       while (this.running)
/*     */       {
/*     */ 
/*     */ 
/*     */         
/*  55 */         BufferedInputStream var1 = new BufferedInputStream(this.clientSocket.getInputStream());
/*  56 */         int var2 = var1.read(this.buffer, 0, 1460);
/*     */         
/*  58 */         if (10 > var2) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/*  63 */         byte var3 = 0;
/*  64 */         int var4 = RConUtils.getBytesAsLEInt(this.buffer, 0, var2);
/*     */         
/*  66 */         if (var4 == var2 - 4) {
/*     */           String var7;
/*  68 */           int var10000, var21 = var3 + 4;
/*  69 */           int var5 = RConUtils.getBytesAsLEInt(this.buffer, var21, var2);
/*  70 */           var21 += 4;
/*  71 */           int var6 = RConUtils.getRemainingBytesAsLEInt(this.buffer, var21);
/*  72 */           var21 += 4;
/*     */           
/*  74 */           switch (var6) {
/*     */             
/*     */             case 2:
/*  77 */               if (this.loggedIn) {
/*     */                 
/*  79 */                 String var8 = RConUtils.getBytesAsString(this.buffer, var21, var2);
/*     */ 
/*     */ 
/*     */                 
/*     */                 try {
/*  84 */                   sendMultipacketResponse(var5, this.server.executeCommand(var8, false));
/*     */                 }
/*  86 */                 catch (Exception var16) {
/*     */                   
/*  88 */                   sendMultipacketResponse(var5, "Error executing: " + var8 + " (" + var16.getMessage() + ")");
/*     */                 } 
/*     */                 
/*     */                 continue;
/*     */               } 
/*     */               
/*  94 */               sendLoginFailedResponse();
/*     */               continue;
/*     */             
/*     */             case 3:
/*  98 */               var7 = RConUtils.getBytesAsString(this.buffer, var21, var2);
/*  99 */               var10000 = var21 + var7.length();
/*     */               
/* 101 */               if (0 != var7.length() && var7.equals(this.rconPassword)) {
/*     */                 
/* 103 */                 this.loggedIn = true;
/* 104 */                 sendResponse(var5, 2, "");
/*     */                 
/*     */                 continue;
/*     */               } 
/* 108 */               this.loggedIn = false;
/* 109 */               sendLoginFailedResponse();
/*     */               continue;
/*     */           } 
/*     */           
/* 113 */           sendMultipacketResponse(var5, String.format("Unknown request %s", new Object[] { Integer.toHexString(var6) }));
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 119 */     catch (SocketTimeoutException var17) {
/*     */ 
/*     */     
/* 122 */     } catch (IOException var18) {
/*     */ 
/*     */     
/* 125 */     } catch (Exception var19) {
/*     */       
/* 127 */       System.out.println(var19);
/*     */     }
/*     */     finally {
/*     */       
/* 131 */       closeSocket();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sendResponse(int par1, int par2, String par3Str) throws IOException {
/* 140 */     ByteArrayOutputStream var4 = new ByteArrayOutputStream(1248);
/* 141 */     DataOutputStream var5 = new DataOutputStream(var4);
/* 142 */     byte[] var6 = par3Str.getBytes("UTF-8");
/* 143 */     var5.writeInt(Integer.reverseBytes(var6.length + 10));
/* 144 */     var5.writeInt(Integer.reverseBytes(par1));
/* 145 */     var5.writeInt(Integer.reverseBytes(par2));
/* 146 */     var5.write(var6);
/* 147 */     var5.write(0);
/* 148 */     var5.write(0);
/* 149 */     this.clientSocket.getOutputStream().write(var4.toByteArray());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sendLoginFailedResponse() throws IOException {
/* 157 */     sendResponse(-1, 2, "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sendMultipacketResponse(int par1, String par2Str) throws IOException {
/* 165 */     int var3 = par2Str.length();
/*     */ 
/*     */     
/*     */     do {
/* 169 */       int var4 = (4096 <= var3) ? 4096 : var3;
/* 170 */       sendResponse(par1, 0, par2Str.substring(0, var4));
/* 171 */       par2Str = par2Str.substring(var4);
/* 172 */       var3 = par2Str.length();
/*     */     }
/* 174 */     while (0 != var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void closeSocket() {
/* 182 */     if (null != this.clientSocket) {
/*     */ 
/*     */       
/*     */       try {
/* 186 */         this.clientSocket.close();
/*     */       }
/* 188 */       catch (IOException var2) {
/*     */         
/* 190 */         logWarning("IO: " + var2.getMessage());
/*     */       } 
/*     */       
/* 193 */       this.clientSocket = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RConThreadClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */