/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ServerList
/*     */ {
/*     */   private final Minecraft mc;
/*  17 */   private final List servers = new ArrayList();
/*     */   
/*  19 */   private final String servers_dat_filepath = "MITE/servers.dat";
/*     */   
/*  21 */   public static final List preset_server_ips = new ArrayList();
/*     */ 
/*     */   
/*     */   public static final String public_servers_dir = "MITE/public_servers/";
/*     */   
/*     */   public static final String public_servers_filepath = "MITE/public_servers/public_servers.txt";
/*     */ 
/*     */   
/*     */   private void loadPresetServerIPs() {
/*     */     try {
/*  31 */       BufferedReader br = new BufferedReader(new FileReader(new File("MITE/public_servers/public_servers.txt")));
/*     */       
/*     */       String line;
/*  34 */       while ((line = br.readLine()) != null) {
/*     */         int i; float f;
/*  36 */         line = line.trim();
/*     */         
/*  38 */         if (line.isEmpty() || line.startsWith("#")) {
/*     */           continue;
/*     */         }
/*  41 */         KeyedValuesString kvs = new KeyedValuesString(line);
/*     */         
/*  43 */         String server_address = kvs.getValue("server_address", true);
/*  44 */         String world_name = kvs.getValue("world_name", true);
/*  45 */         String start_date = kvs.getValue("start_date", true);
/*  46 */         String description = kvs.getValue("description", true);
/*  47 */         String website = kvs.getValue("website", true);
/*  48 */         String image_url = kvs.getValue("image_url", true);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/*  54 */           String color_string = kvs.getValue("theme_color", true);
/*  55 */           i = (color_string == null) ? 8947848 : Integer.parseInt(color_string, 16);
/*     */         }
/*  57 */         catch (NumberFormatException e) {
/*     */           
/*  59 */           i = 8947848;
/*  60 */           e.printStackTrace();
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/*  67 */           String backdrop_opacity_string = kvs.getValue("backdrop_opacity", true);
/*  68 */           f = (backdrop_opacity_string == null) ? 0.5F : Float.parseFloat(backdrop_opacity_string);
/*     */         }
/*  70 */         catch (NumberFormatException e) {
/*     */           
/*  72 */           f = 0.5F;
/*  73 */           e.printStackTrace();
/*     */         } 
/*     */         
/*  76 */         this.servers.add((new ServerData((world_name == null) ? "Public MITE Server" : world_name, server_address, true)).setInfo(start_date, description, website, image_url, i, f));
/*     */       } 
/*     */       
/*  79 */       br.close();
/*     */     }
/*  81 */     catch (Exception e) {}
/*     */   }
/*     */ 
/*     */   
/*     */   public ServerList(Minecraft par1Minecraft) {
/*  86 */     this.mc = par1Minecraft;
/*  87 */     loadServerList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadServerList() {
/*     */     try {
/*  98 */       this.servers.clear();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 106 */       NBTTagCompound var1 = CompressedStreamTools.read(new File(this.mc.mcDataDir, "MITE/servers.dat"));
/*     */       
/* 108 */       if (var1 == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 113 */       NBTTagList var2 = var1.getTagList("servers");
/*     */       
/* 115 */       for (int var3 = 0; var3 < var2.tagCount(); var3++)
/*     */       {
/* 117 */         this.servers.add(ServerData.getServerDataFromNBTCompound((NBTTagCompound)var2.tagAt(var3)));
/*     */       }
/*     */     }
/* 120 */     catch (Exception var4) {}
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
/*     */   public void saveServerList() {
/*     */     try {
/* 135 */       NBTTagList var1 = new NBTTagList();
/* 136 */       Iterator<ServerData> var2 = this.servers.iterator();
/*     */       
/* 138 */       while (var2.hasNext()) {
/*     */         
/* 140 */         ServerData var3 = var2.next();
/*     */         
/* 142 */         if (var3.is_preset) {
/*     */           continue;
/*     */         }
/* 145 */         var1.appendTag(var3.getNBTCompound());
/*     */       } 
/*     */       
/* 148 */       NBTTagCompound var5 = new NBTTagCompound();
/* 149 */       var5.setTag("servers", var1);
/*     */       
/* 151 */       CompressedStreamTools.safeWrite(var5, new File(this.mc.mcDataDir, "MITE/servers.dat"));
/*     */     }
/* 153 */     catch (Exception var4) {
/*     */       
/* 155 */       var4.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ServerData getServerData(int par1) {
/* 164 */     return this.servers.get(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeServerData(int par1) {
/* 172 */     this.servers.remove(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addServerData(ServerData par1ServerData) {
/* 180 */     this.servers.add(par1ServerData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int countServers() {
/* 188 */     return this.servers.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void swapServers(int par1, int par2) {
/* 196 */     ServerData var3 = getServerData(par1);
/* 197 */     this.servers.set(par1, getServerData(par2));
/* 198 */     this.servers.set(par2, var3);
/* 199 */     saveServerList();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ServerList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */