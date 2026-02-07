/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public class ChunkLoader
/*     */ {
/*     */   public static AnvilConverterData load(NBTTagCompound par0NBTTagCompound) {
/*   7 */     int var1 = par0NBTTagCompound.getInteger("xPos");
/*   8 */     int var2 = par0NBTTagCompound.getInteger("zPos");
/*     */     
/*  10 */     AnvilConverterData var3 = new AnvilConverterData(var1, var2);
/*     */     
/*  12 */     var3.blocks = par0NBTTagCompound.getByteArray("BlockData");
/*  13 */     var3.data = new NibbleArrayReader(par0NBTTagCompound.getByteArray("Data"), 7);
/*  14 */     var3.skyLight = new NibbleArrayReader(par0NBTTagCompound.getByteArray("SkyLight"), 7);
/*  15 */     var3.blockLight = new NibbleArrayReader(par0NBTTagCompound.getByteArray("BlockLight"), 7);
/*  16 */     var3.heightmap = par0NBTTagCompound.getByteArray("HeightMap");
/*  17 */     var3.terrainPopulated = par0NBTTagCompound.getBoolean("TerrainPopulated");
/*     */     
/*  19 */     var3.entities = par0NBTTagCompound.getTagList("EntityData");
/*     */     
/*  21 */     var3.tileEntities = par0NBTTagCompound.getTagList("TileEntityData");
/*  22 */     var3.tileTicks = par0NBTTagCompound.getTagList("TileTicks");
/*     */ 
/*     */     
/*     */     try {
/*  26 */       var3.lastUpdated = par0NBTTagCompound.getLong("LastUpdate");
/*     */     }
/*  28 */     catch (ClassCastException var5) {
/*     */       
/*  30 */       var3.lastUpdated = par0NBTTagCompound.getInteger("LastUpdate");
/*     */     } 
/*     */     
/*  33 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void convertToAnvilFormat(AnvilConverterData par0AnvilConverterData, NBTTagCompound par1NBTTagCompound, WorldChunkManager par2WorldChunkManager) {
/*  38 */     par1NBTTagCompound.setInteger("xPos", par0AnvilConverterData.x);
/*  39 */     par1NBTTagCompound.setInteger("zPos", par0AnvilConverterData.z);
/*     */     
/*  41 */     par1NBTTagCompound.setLong("LastUpdate", par0AnvilConverterData.lastUpdated);
/*  42 */     int[] var3 = new int[par0AnvilConverterData.heightmap.length];
/*     */     
/*  44 */     for (int var4 = 0; var4 < par0AnvilConverterData.heightmap.length; var4++)
/*     */     {
/*  46 */       var3[var4] = par0AnvilConverterData.heightmap[var4];
/*     */     }
/*     */     
/*  49 */     par1NBTTagCompound.setIntArray("HeightMap", var3);
/*  50 */     par1NBTTagCompound.setBoolean("TerrainPopulated", par0AnvilConverterData.terrainPopulated);
/*  51 */     NBTTagList var16 = new NBTTagList("Sections");
/*     */ 
/*     */     
/*  54 */     for (int var5 = 0; var5 < 8; var5++) {
/*     */       
/*  56 */       boolean var6 = true;
/*     */       
/*  58 */       for (int var7 = 0; var7 < 16 && var6; var7++) {
/*     */         
/*  60 */         int var8 = 0;
/*     */         
/*  62 */         while (var8 < 16 && var6) {
/*     */           
/*  64 */           int var9 = 0;
/*     */ 
/*     */ 
/*     */           
/*  68 */           while (var9 < 16) {
/*     */             
/*  70 */             int var10 = var7 << 11 | var9 << 7 | var8 + (var5 << 4);
/*  71 */             byte var11 = par0AnvilConverterData.blocks[var10];
/*     */             
/*  73 */             if (var11 == 0) {
/*     */               
/*  75 */               var9++;
/*     */               
/*     */               continue;
/*     */             } 
/*  79 */             var6 = false;
/*     */           } 
/*     */           
/*  82 */           var8++;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  88 */       if (!var6) {
/*     */         
/*  90 */         byte[] var19 = new byte[4096];
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  95 */         NibbleArray var20 = new NibbleArray(var19.length);
/*  96 */         NibbleArray var21 = new NibbleArray(var19.length);
/*  97 */         NibbleArray var23 = new NibbleArray(var19.length);
/*     */         
/*  99 */         for (int var22 = 0; var22 < 16; var22++) {
/*     */           
/* 101 */           for (int var12 = 0; var12 < 16; var12++) {
/*     */             
/* 103 */             for (int var13 = 0; var13 < 16; var13++) {
/*     */               
/* 105 */               int var14 = var22 << 11 | var13 << 7 | var12 + (var5 << 4);
/* 106 */               byte var15 = par0AnvilConverterData.blocks[var14];
/* 107 */               var19[var12 << 8 | var13 << 4 | var22] = (byte)(var15 & 0xFF);
/* 108 */               var20.set(var22, var12, var13, par0AnvilConverterData.data.get(var22, var12 + (var5 << 4), var13));
/* 109 */               var21.set(var22, var12, var13, par0AnvilConverterData.skyLight.get(var22, var12 + (var5 << 4), var13));
/* 110 */               var23.set(var22, var12, var13, par0AnvilConverterData.blockLight.get(var22, var12 + (var5 << 4), var13));
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 115 */         NBTTagCompound var24 = new NBTTagCompound();
/* 116 */         var24.setByte("Y", (byte)(var5 & 0xFF));
/*     */         
/* 118 */         var24.setByteArray("BlockData", var19);
/* 119 */         var24.setByteArray("Data", var20.data);
/* 120 */         var24.setByteArray("SkyLight", var21.data);
/* 121 */         var24.setByteArray("BlockLight", var23.data);
/* 122 */         var16.appendTag(var24);
/*     */       } 
/*     */     } 
/*     */     
/* 126 */     par1NBTTagCompound.setTag("Sections", var16);
/* 127 */     byte[] var17 = new byte[256];
/*     */     
/* 129 */     for (int var18 = 0; var18 < 16; var18++) {
/*     */       
/* 131 */       for (int var7 = 0; var7 < 16; var7++)
/*     */       {
/* 133 */         var17[var7 << 4 | var18] = (byte)((par2WorldChunkManager.getBiomeGenAt(par0AnvilConverterData.x << 4 | var18, par0AnvilConverterData.z << 4 | var7)).biomeID & 0xFF);
/*     */       }
/*     */     } 
/*     */     
/* 137 */     par1NBTTagCompound.setByteArray("Biomes", var17);
/*     */     
/* 139 */     par1NBTTagCompound.setTag("EntityData", par0AnvilConverterData.entities);
/*     */     
/* 141 */     par1NBTTagCompound.setTag("TileEntityData", par0AnvilConverterData.tileEntities);
/*     */     
/* 143 */     if (par0AnvilConverterData.tileTicks != null)
/*     */     {
/* 145 */       par1NBTTagCompound.setTag("TileTicks", par0AnvilConverterData.tileTicks);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ChunkLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */