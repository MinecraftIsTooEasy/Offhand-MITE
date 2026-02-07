/*    */ package net.minecraft;
/*    */ 
/*    */ import java.net.InetAddress;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LanServerList
/*    */ {
/* 17 */   private ArrayList listOfLanServers = new ArrayList();
/*    */   boolean wasUpdated;
/*    */   
/*    */   public synchronized boolean getWasUpdated() {
/* 21 */     return this.wasUpdated;
/*    */   }
/*    */   
/*    */   public synchronized void setWasNotUpdated() {
/* 25 */     this.wasUpdated = false;
/*    */   }
/*    */   
/*    */   public synchronized List getLanServers() {
/* 29 */     return Collections.unmodifiableList(this.listOfLanServers);
/*    */   }
/*    */   
/*    */   public synchronized void func_77551_a(String string, InetAddress inetAddress) {
/* 33 */     String str1 = ThreadLanServerPing.getMotdFromPingResponse(string);
/* 34 */     String str2 = ThreadLanServerPing.getAdFromPingResponse(string);
/* 35 */     if (str2 == null) {
/*    */       return;
/*    */     }
/*    */     
/* 39 */     str2 = inetAddress.getHostAddress() + ":" + str2;
/*    */     
/* 41 */     boolean bool = false;
/* 42 */     for (LanServer lanServer : this.listOfLanServers) {
/* 43 */       if (lanServer.getServerIpPort().equals(str2)) {
/* 44 */         lanServer.updateLastSeen();
/* 45 */         bool = true;
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 50 */     if (!bool) {
/* 51 */       this.listOfLanServers.add(new LanServer(str1, str2));
/* 52 */       this.wasUpdated = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\LanServerList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */