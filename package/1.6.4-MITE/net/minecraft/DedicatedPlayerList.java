/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ public class DedicatedPlayerList
/*     */   extends ServerConfigurationManager
/*     */ {
/*     */   private File opsList;
/*     */   private File whiteList;
/*     */   
/*     */   public DedicatedPlayerList(DedicatedServer par1DedicatedServer) {
/*  18 */     super(par1DedicatedServer);
/*  19 */     this.opsList = par1DedicatedServer.getFile("ops.txt");
/*  20 */     this.whiteList = par1DedicatedServer.getFile("white-list.txt");
/*  21 */     this.viewDistance = par1DedicatedServer.getIntProperty("view-distance", 10);
/*  22 */     this.maxPlayers = par1DedicatedServer.getIntProperty("max-players", 20);
/*     */ 
/*     */     
/*  25 */     if (!par1DedicatedServer.isSinglePlayer()) {
/*     */       
/*  27 */       getBannedPlayers().setListActive(true);
/*  28 */       getBannedIPs().setListActive(true);
/*     */     } 
/*     */     
/*  31 */     getBannedPlayers().loadBanList();
/*  32 */     getBannedPlayers().saveToFileWithHeader();
/*  33 */     getBannedIPs().loadBanList();
/*  34 */     getBannedIPs().saveToFileWithHeader();
/*  35 */     loadOpsList();
/*  36 */     readWhiteList();
/*  37 */     saveOpsList();
/*     */     
/*  39 */     if (!this.whiteList.exists()) {
/*     */       
/*  41 */       getWhiteListedPlayers().add("*");
/*  42 */       saveWhiteList();
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void addOp(String par1Str) {
/*  58 */     super.addOp(par1Str);
/*  59 */     saveOpsList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeOp(String par1Str) {
/*  67 */     super.removeOp(par1Str);
/*  68 */     saveOpsList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeFromWhitelist(String par1Str) {
/*  76 */     super.removeFromWhitelist(par1Str);
/*  77 */     saveWhiteList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addToWhiteList(String par1Str) {
/*  85 */     super.addToWhiteList(par1Str);
/*  86 */     saveWhiteList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadWhiteList() {
/*  94 */     readWhiteList();
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
/*     */   private void loadOpsList() {
/* 120 */     getOps().clear();
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
/*     */ 
/*     */ 
/*     */   
/*     */   private void saveOpsList() {}
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
/*     */   private void readWhiteList() {
/*     */     try {
/* 150 */       getWhiteListedPlayers().clear();
/* 151 */       BufferedReader var1 = new BufferedReader(new FileReader(this.whiteList));
/* 152 */       String var2 = "";
/*     */       
/* 154 */       while ((var2 = var1.readLine()) != null)
/*     */       {
/* 156 */         getWhiteListedPlayers().add(var2.trim().toLowerCase());
/*     */       }
/*     */       
/* 159 */       var1.close();
/*     */     }
/* 161 */     catch (Exception var3) {
/*     */       
/* 163 */       getDedicatedServerInstance().getLogAgent().logWarning("Failed to load white-list: " + var3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void saveWhiteList() {
/*     */     try {
/* 171 */       PrintWriter var1 = new PrintWriter(new FileWriter(this.whiteList, false));
/* 172 */       Iterator<String> var2 = getWhiteListedPlayers().iterator();
/*     */       
/* 174 */       while (var2.hasNext()) {
/*     */         
/* 176 */         String var3 = var2.next();
/* 177 */         var1.println(var3);
/*     */       } 
/*     */       
/* 180 */       var1.close();
/*     */     }
/* 182 */     catch (Exception var4) {
/*     */       
/* 184 */       getDedicatedServerInstance().getLogAgent().logWarning("Failed to save white-list: " + var4);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAllowedToLogin(String par1Str) {
/* 193 */     par1Str = par1Str.trim().toLowerCase();
/*     */     
/* 195 */     return (!isWhiteListEnabled() || isPlayerOpped(par1Str) || isPlayerWhiteListed(par1Str));
/*     */   }
/*     */ 
/*     */   
/*     */   public DedicatedServer getDedicatedServerInstance() {
/* 200 */     return (DedicatedServer)super.getServerInstance();
/*     */   }
/*     */ 
/*     */   
/*     */   public MinecraftServer getServerInstance() {
/* 205 */     return getDedicatedServerInstance();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DedicatedPlayerList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */