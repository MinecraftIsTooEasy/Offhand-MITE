/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MapStorage
/*     */ {
/*     */   private ISaveHandler saveHandler;
/*  19 */   private Map loadedDataMap = new HashMap<Object, Object>();
/*     */ 
/*     */   
/*  22 */   private List loadedDataList = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  27 */   private Map idCounts = new HashMap<Object, Object>();
/*     */ 
/*     */   
/*     */   public MapStorage(ISaveHandler par1ISaveHandler) {
/*  31 */     this.saveHandler = par1ISaveHandler;
/*  32 */     loadIdCounts();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldSavedData loadData(Class<WorldSavedData> par1Class, String par2Str) {
/*  41 */     WorldSavedData var3 = (WorldSavedData)this.loadedDataMap.get(par2Str);
/*     */     
/*  43 */     if (var3 != null)
/*     */     {
/*  45 */       return var3;
/*     */     }
/*     */ 
/*     */     
/*  49 */     if (this.saveHandler != null) {
/*     */       
/*     */       try {
/*     */         
/*  53 */         File var4 = this.saveHandler.getMapFileFromName(par2Str);
/*     */         
/*  55 */         if (var4 != null && var4.exists())
/*     */         {
/*     */           
/*     */           try {
/*  59 */             var3 = par1Class.getConstructor(new Class[] { String.class }).newInstance(new Object[] { par2Str });
/*     */           }
/*  61 */           catch (Exception var7) {
/*     */             
/*  63 */             throw new RuntimeException("Failed to instantiate " + par1Class.toString(), var7);
/*     */           } 
/*     */           
/*  66 */           FileInputStream var5 = new FileInputStream(var4);
/*  67 */           NBTTagCompound var6 = CompressedStreamTools.readCompressed(var5);
/*  68 */           var5.close();
/*  69 */           var3.readFromNBT(var6.getCompoundTag("data"));
/*     */         }
/*     */       
/*  72 */       } catch (Exception var8) {
/*     */         
/*  74 */         var8.printStackTrace();
/*     */       } 
/*     */     }
/*     */     
/*  78 */     if (var3 != null) {
/*     */       
/*  80 */       this.loadedDataMap.put(par2Str, var3);
/*  81 */       this.loadedDataList.add(var3);
/*     */     } 
/*     */     
/*  84 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setData(String par1Str, WorldSavedData par2WorldSavedData) {
/*  93 */     if (par2WorldSavedData == null)
/*     */     {
/*  95 */       throw new RuntimeException("Can't set null data");
/*     */     }
/*     */ 
/*     */     
/*  99 */     if (this.loadedDataMap.containsKey(par1Str))
/*     */     {
/* 101 */       this.loadedDataList.remove(this.loadedDataMap.remove(par1Str));
/*     */     }
/*     */     
/* 104 */     this.loadedDataMap.put(par1Str, par2WorldSavedData);
/* 105 */     this.loadedDataList.add(par2WorldSavedData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveAllData() {
/* 114 */     for (int var1 = 0; var1 < this.loadedDataList.size(); var1++) {
/*     */       
/* 116 */       WorldSavedData var2 = this.loadedDataList.get(var1);
/*     */       
/* 118 */       if (var2.isDirty()) {
/*     */         
/* 120 */         saveData(var2);
/* 121 */         var2.setDirty(false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void saveData(WorldSavedData par1WorldSavedData) {
/* 131 */     if (this.saveHandler != null) {
/*     */       
/*     */       try {
/*     */         
/* 135 */         File var2 = this.saveHandler.getMapFileFromName(par1WorldSavedData.mapName);
/*     */         
/* 137 */         if (var2 != null)
/*     */         {
/* 139 */           NBTTagCompound var3 = new NBTTagCompound();
/* 140 */           par1WorldSavedData.writeToNBT(var3);
/* 141 */           NBTTagCompound var4 = new NBTTagCompound();
/* 142 */           var4.setCompoundTag("data", var3);
/* 143 */           FileOutputStream var5 = new FileOutputStream(var2);
/* 144 */           CompressedStreamTools.writeCompressed(var4, var5);
/* 145 */           var5.close();
/*     */         }
/*     */       
/* 148 */       } catch (Exception var6) {
/*     */         
/* 150 */         var6.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void loadIdCounts() {
/*     */     try {
/* 162 */       this.idCounts.clear();
/*     */       
/* 164 */       if (this.saveHandler == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 169 */       File var1 = this.saveHandler.getMapFileFromName("idcounts");
/*     */       
/* 171 */       if (var1 != null && var1.exists()) {
/*     */         
/* 173 */         DataInputStream var2 = new DataInputStream(new FileInputStream(var1));
/* 174 */         NBTTagCompound var3 = CompressedStreamTools.read(var2);
/* 175 */         var2.close();
/* 176 */         Iterator<NBTBase> var4 = var3.getTags().iterator();
/*     */         
/* 178 */         while (var4.hasNext()) {
/*     */           
/* 180 */           NBTBase var5 = var4.next();
/*     */           
/* 182 */           if (var5 instanceof NBTTagShort)
/*     */           {
/* 184 */             NBTTagShort var6 = (NBTTagShort)var5;
/* 185 */             String var7 = var6.getName();
/* 186 */             short var8 = var6.data;
/* 187 */             this.idCounts.put(var7, Short.valueOf(var8));
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/* 192 */     } catch (Exception var9) {
/*     */       
/* 194 */       var9.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int peekUniqueDataId(String prefix) {
/* 201 */     Short var2 = (Short)this.idCounts.get(prefix);
/*     */     
/* 203 */     return (var2 == null) ? 0 : Short.valueOf((short)(var2.shortValue() + 1)).shortValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUniqueDataId(World world, String prefix, short value) {
/* 209 */     this.idCounts.put(prefix, Short.valueOf(value));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 214 */     if (world instanceof WorldServer && "map".equals(prefix)) {
/* 215 */       world.getAsWorldServer().sendPacketToAllPlayersInAllDimensions((new Packet85SimpleSignal(EnumSignal.last_issued_map_id)).setShort(value));
/*     */     }
/* 217 */     if (Minecraft.inDevMode() && world instanceof WorldServer && !"map".equals(prefix)) {
/* 218 */       Minecraft.setErrorMessage("setUniqueDataId: prefix of \"" + prefix + "\" used, did you want to propagate it to client?");
/*     */     }
/* 220 */     if (this.saveHandler == null) {
/*     */       return;
/*     */     }
/*     */     
/*     */     try {
/* 225 */       File var3 = this.saveHandler.getMapFileFromName("idcounts");
/*     */       
/* 227 */       if (var3 != null)
/*     */       {
/* 229 */         NBTTagCompound var4 = new NBTTagCompound();
/* 230 */         Iterator<String> var5 = this.idCounts.keySet().iterator();
/*     */         
/* 232 */         while (var5.hasNext()) {
/*     */           
/* 234 */           String var6 = var5.next();
/* 235 */           short var7 = ((Short)this.idCounts.get(var6)).shortValue();
/* 236 */           var4.setShort(var6, var7);
/*     */         } 
/*     */         
/* 239 */         DataOutputStream var9 = new DataOutputStream(new FileOutputStream(var3));
/* 240 */         CompressedStreamTools.write(var4, var9);
/* 241 */         var9.close();
/*     */       }
/*     */     
/* 244 */     } catch (Exception var8) {
/*     */       
/* 246 */       var8.printStackTrace();
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
/*     */   public int getUniqueDataId(World world, String par1Str) {
/* 259 */     Short var2 = (Short)this.idCounts.get(par1Str);
/*     */     
/* 261 */     if (var2 == null) {
/*     */       
/* 263 */       var2 = Short.valueOf((short)0);
/*     */     }
/*     */     else {
/*     */       
/* 267 */       var2 = Short.valueOf((short)(var2.shortValue() + 1));
/*     */     } 
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
/* 307 */     setUniqueDataId(world, par1Str, var2.shortValue());
/*     */     
/* 309 */     return var2.shortValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MapStorage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */