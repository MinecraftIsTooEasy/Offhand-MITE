/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.InputStreamReader;
/*     */ import java.math.BigInteger;
/*     */ import java.net.URL;
/*     */ import java.net.URLEncoder;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ThreadLoginVerifier
/*     */   extends Thread
/*     */ {
/*     */   ThreadLoginVerifier(NetLoginHandler netLoginHandler) {}
/*     */   
/*     */   public void run() {
/*     */     try {
/* 130 */       String str1 = (new BigInteger(CryptManager.getServerIdHash(NetLoginHandler.getServerId(this.loginHandler), NetLoginHandler.getLoginMinecraftServer(this.loginHandler).getKeyPair().getPublic(), NetLoginHandler.getSharedKey(this.loginHandler)))).toString(16);
/* 131 */       URL uRL = new URL("http://session.minecraft.net/game/checkserver.jsp?user=" + URLEncoder.encode(NetLoginHandler.getClientUsername(this.loginHandler), "UTF-8") + "&serverId=" + URLEncoder.encode(str1, "UTF-8"));
/* 132 */       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(uRL.openConnection(NetLoginHandler.getLoginMinecraftServer(this.loginHandler).getServerProxy()).getInputStream()));
/* 133 */       String str2 = bufferedReader.readLine();
/* 134 */       bufferedReader.close();
/*     */       
/* 136 */       if (!"YES".equals(str2)) {
/* 137 */         this.loginHandler.raiseErrorAndDisconnect("Failed to verify username!");
/*     */         return;
/*     */       } 
/* 140 */       NetLoginHandler.func_72531_a(this.loginHandler, true);
/* 141 */     } catch (Exception exception) {
/* 142 */       this.loginHandler.raiseErrorAndDisconnect("Failed to verify username! [internal error " + exception + "]");
/* 143 */       exception.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ThreadLoginVerifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */