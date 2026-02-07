/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemMap
/*     */   extends ItemMapBase
/*     */ {
/*     */   public static final int highest_map_index = 31999;
/*     */   
/*     */   protected ItemMap(int par1, String texture) {
/*  14 */     super(par1, texture);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MapData getMPMapData(short par0, World par1World) {
/*  22 */     String var2 = "map_" + par0;
/*  23 */     MapData var3 = (MapData)par1World.loadItemData(MapData.class, var2);
/*     */     
/*  25 */     if (var3 == null) {
/*     */       
/*  27 */       var3 = new MapData(var2);
/*  28 */       par1World.setItemData(var2, var3);
/*     */     } 
/*     */     
/*  31 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public MapData getMapData(ItemStack par1ItemStack, World par2World) {
/*  36 */     String var3 = "map_" + par1ItemStack.getItemSubtype();
/*  37 */     MapData var4 = (MapData)par2World.loadItemData(MapData.class, var3);
/*     */     
/*  39 */     if (var4 == null && !par2World.isRemote) {
/*     */       
/*  41 */       par1ItemStack.setItemSubtype(par2World.getUniqueDataId("map"));
/*  42 */       var3 = "map_" + par1ItemStack.getItemSubtype();
/*  43 */       var4 = new MapData(var3);
/*  44 */       var4.scale = 3;
/*  45 */       int var5 = 128 * (1 << var4.scale);
/*  46 */       var4.xCenter = Math.round(par2World.getWorldInfo().getSpawnX() / var5) * var5;
/*  47 */       var4.zCenter = Math.round((par2World.getWorldInfo().getSpawnZ() / var5)) * var5;
/*  48 */       var4.dimension = (byte)par2World.provider.dimensionId;
/*  49 */       var4.markDirty();
/*  50 */       par2World.setItemData(var3, var4);
/*     */     } 
/*     */     
/*  53 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateMapData(World par1World, Entity par2Entity, MapData par3MapData) {
/*  58 */     if (par1World.provider.dimensionId == par3MapData.dimension && par2Entity instanceof EntityPlayer) {
/*     */       
/*  60 */       short var4 = 128;
/*  61 */       short var5 = 128;
/*  62 */       int var6 = 1 << par3MapData.scale;
/*  63 */       int var7 = par3MapData.xCenter;
/*  64 */       int var8 = par3MapData.zCenter;
/*  65 */       int var9 = MathHelper.floor_double(par2Entity.posX - var7) / var6 + var4 / 2;
/*  66 */       int var10 = MathHelper.floor_double(par2Entity.posZ - var8) / var6 + var5 / 2;
/*  67 */       int var11 = 128 / var6;
/*     */       
/*  69 */       if (par1World.provider.hasNoSky)
/*     */       {
/*  71 */         var11 /= 2;
/*     */       }
/*     */       
/*  74 */       MapInfo var12 = par3MapData.func_82568_a((EntityPlayer)par2Entity);
/*     */       
/*  76 */       if (var12.field_82569_d >= 60 && !var12.world_map_survey_finished && par1World instanceof WorldServer)
/*     */       {
/*     */ 
/*     */ 
/*     */         
/*  81 */         var12.world_map_survey_finished = (((WorldServer)par1World).addWorldMapSurvey(par3MapData.xCenter, par3MapData.zCenter, 128, true) == 0);
/*     */       }
/*     */       
/*  84 */       var12.field_82569_d++;
/*     */       
/*  86 */       for (int var13 = var9 - var11 + 1; var13 < var9 + var11; var13++) {
/*     */         
/*  88 */         if ((var13 & 0xF) == (var12.field_82569_d & 0xF)) {
/*     */           
/*  90 */           int var14 = 255;
/*  91 */           int var15 = 0;
/*  92 */           double var16 = 0.0D;
/*     */           
/*  94 */           for (int var18 = var10 - var11 - 1; var18 < var10 + var11; var18++) {
/*     */ 
/*     */ 
/*     */             
/*  98 */             if (var13 >= 0 && var18 >= -1 && var13 < var4 && var18 < var5) {
/*     */               
/* 100 */               int var19 = var13 - var9;
/* 101 */               int var20 = var18 - var10;
/* 102 */               boolean var21 = (var19 * var19 + var20 * var20 > (var11 - 2) * (var11 - 2));
/* 103 */               int var22 = (var7 / var6 + var13 - var4 / 2) * var6;
/* 104 */               int var23 = (var8 / var6 + var18 - var5 / 2) * var6;
/* 105 */               int[] var24 = new int[256];
/* 106 */               Chunk var25 = par1World.getChunkFromBlockCoords(var22, var23);
/*     */               
/* 108 */               if (!var25.isEmpty()) {
/*     */                 
/* 110 */                 int var26 = var22 & 0xF;
/* 111 */                 int var27 = var23 & 0xF;
/* 112 */                 int var28 = 0;
/* 113 */                 double var29 = 0.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 119 */                 if (par1World.provider.hasNoSky) {
/*     */                   
/* 121 */                   int var31 = var22 + var23 * 231871;
/* 122 */                   var31 = var31 * var31 * 31287121 + var31 * 11;
/*     */                   
/* 124 */                   if ((var31 >> 20 & 0x1) == 0) {
/*     */                     
/* 126 */                     var24[Block.dirt.blockID] = var24[Block.dirt.blockID] + 10;
/*     */                   }
/*     */                   else {
/*     */                     
/* 130 */                     var24[Block.stone.blockID] = var24[Block.stone.blockID] + 10;
/*     */                   } 
/*     */                   
/* 133 */                   var29 = 100.0D;
/*     */                 }
/*     */                 else {
/*     */                   
/* 137 */                   for (int var31 = 0; var31 < var6; var31++) {
/*     */                     
/* 139 */                     for (int j = 0; j < var6; j++) {
/*     */                       
/* 141 */                       int k = var25.getHeightValue(var31 + var26, j + var27) + 1;
/* 142 */                       int var34 = 0;
/*     */                       
/* 144 */                       if (k > 1) {
/*     */                         boolean var35;
/*     */ 
/*     */ 
/*     */                         
/*     */                         do {
/* 150 */                           var35 = true;
/* 151 */                           var34 = var25.getBlockID(var31 + var26, k - 1, j + var27);
/*     */                           
/* 153 */                           if (var34 == 0) {
/*     */                             
/* 155 */                             var35 = false;
/*     */                           }
/* 157 */                           else if (k > 0 && var34 > 0 && (Block.blocksList[var34]).blockMaterial.map_color == MapColor.airColor) {
/*     */                             
/* 159 */                             var35 = false;
/*     */                           } 
/*     */                           
/* 162 */                           if (var35)
/*     */                             continue; 
/* 164 */                           k--;
/*     */                           
/* 166 */                           if (k <= 0) {
/*     */                             break;
/*     */                           }
/*     */ 
/*     */                           
/* 171 */                           var34 = var25.getBlockID(var31 + var26, k - 1, j + var27);
/*     */                         
/*     */                         }
/* 174 */                         while (k > 0 && !var35);
/*     */                         
/* 176 */                         if (k > 0 && var34 != 0 && (Block.blocksList[var34]).blockMaterial.isLiquid()) {
/*     */                           
/* 178 */                           int var43, m = k - 1;
/* 179 */                           boolean var37 = false;
/*     */ 
/*     */ 
/*     */                           
/*     */                           do {
/* 184 */                             var43 = var25.getBlockID(var31 + var26, m--, j + var27);
/* 185 */                             var28++;
/*     */                           }
/* 187 */                           while (m > 0 && var43 != 0 && (Block.blocksList[var43]).blockMaterial.isLiquid());
/*     */                         } 
/*     */                       } 
/*     */                       
/* 191 */                       var29 += k / (var6 * var6);
/* 192 */                       var24[var34] = var24[var34] + 1;
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */                 
/* 197 */                 var28 /= var6 * var6;
/* 198 */                 int i = 0;
/* 199 */                 int var32 = 0;
/*     */                 
/* 201 */                 for (int var33 = 0; var33 < 256; var33++) {
/*     */                   
/* 203 */                   if (var24[var33] > i) {
/*     */                     
/* 205 */                     var32 = var33;
/* 206 */                     i = var24[var33];
/*     */                   } 
/*     */                 } 
/*     */                 
/* 210 */                 double var40 = (var29 - var16) * 4.0D / (var6 + 4) + ((var13 + var18 & 0x1) - 0.5D) * 0.4D;
/* 211 */                 byte var39 = 1;
/*     */                 
/* 213 */                 if (var40 > 0.6D)
/*     */                 {
/* 215 */                   var39 = 2;
/*     */                 }
/*     */                 
/* 218 */                 if (var40 < -0.6D)
/*     */                 {
/* 220 */                   var39 = 0;
/*     */                 }
/*     */                 
/* 223 */                 int var36 = 0;
/*     */                 
/* 225 */                 if (var32 > 0) {
/*     */                   
/* 227 */                   MapColor var42 = (Block.blocksList[var32]).blockMaterial.map_color;
/*     */                   
/* 229 */                   if (var42 == MapColor.waterColor) {
/*     */                     
/* 231 */                     var40 = var28 * 0.1D + (var13 + var18 & 0x1) * 0.2D;
/* 232 */                     var39 = 1;
/*     */                     
/* 234 */                     if (var40 < 0.5D)
/*     */                     {
/* 236 */                       var39 = 2;
/*     */                     }
/*     */                     
/* 239 */                     if (var40 > 0.9D)
/*     */                     {
/* 241 */                       var39 = 0;
/*     */                     }
/*     */                   } 
/*     */                   
/* 245 */                   var36 = var42.colorIndex;
/*     */                 } 
/*     */                 
/* 248 */                 var16 = var29;
/*     */                 
/* 250 */                 if (var18 >= 0 && var19 * var19 + var20 * var20 < var11 * var11 && (!var21 || (var13 + var18 & 0x1) != 0)) {
/*     */                   
/* 252 */                   byte var41 = par3MapData.colors[var13 + var18 * var4];
/*     */                   
/* 254 */                   byte var38 = (byte)(var36 * 4 + var39);
/*     */                   
/* 256 */                   if (var41 != var38) {
/*     */                     
/* 258 */                     if (var14 > var18)
/*     */                     {
/* 260 */                       var14 = var18;
/*     */                     }
/*     */                     
/* 263 */                     if (var15 < var18)
/*     */                     {
/* 265 */                       var15 = var18;
/*     */                     }
/*     */                     
/* 268 */                     par3MapData.colors[var13 + var18 * var4] = var38;
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
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
/* 304 */           if (var14 <= var15)
/*     */           {
/* 306 */             par3MapData.setColumnDirty(var13, var14, var15);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
/* 319 */     if (!par2World.isRemote) {
/*     */       
/* 321 */       MapData var6 = getMapData(par1ItemStack, par2World);
/*     */       
/* 323 */       if (par3Entity instanceof EntityPlayer) {
/*     */         
/* 325 */         EntityPlayer var7 = (EntityPlayer)par3Entity;
/* 326 */         var6.updateVisiblePlayers(var7, par1ItemStack);
/*     */       } 
/*     */       
/* 329 */       if (par5)
/*     */       {
/* 331 */         updateMapData(par2World, par3Entity, var6);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet createMapDataPacket(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
/* 341 */     byte[] var4 = getMapData(par1ItemStack, par2World).getUpdatePacketData(par1ItemStack, par2World, par3EntityPlayer);
/* 342 */     return (var4 == null) ? null : new Packet131MapData((short)Item.map.itemID, (short)par1ItemStack.getItemSubtype(), var4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
/* 350 */     if (par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().getBoolean("map_is_scaling")) {
/*     */       
/* 352 */       MapData var4 = Item.map.getMapData(par1ItemStack, par2World);
/* 353 */       par1ItemStack.setItemSubtype(par2World.getUniqueDataId("map"));
/* 354 */       MapData var5 = new MapData("map_" + par1ItemStack.getItemSubtype());
/* 355 */       var5.scale = (byte)(var4.scale + 1);
/*     */       
/* 357 */       if (var5.scale > 4)
/*     */       {
/* 359 */         var5.scale = 4;
/*     */       }
/*     */       
/* 362 */       var5.xCenter = var4.xCenter;
/* 363 */       var5.zCenter = var4.zCenter;
/* 364 */       var5.dimension = var4.dimension;
/* 365 */       var5.markDirty();
/* 366 */       par2World.setItemData("map_" + par1ItemStack.getItemSubtype(), var5);
/*     */       
/* 368 */       par1ItemStack.getTagCompound().removeTag("map_is_scaling");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isBeingExtended(ItemStack item_stack) {
/* 374 */     if (item_stack.getItem() != Item.map) {
/* 375 */       return false;
/*     */     }
/* 377 */     if (!item_stack.hasTagCompound()) {
/* 378 */       return false;
/*     */     }
/* 380 */     return item_stack.getTagCompound().getBoolean("map_is_scaling");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4, Slot slot) {
/* 389 */     MapData var5 = getMapData(par1ItemStack, par2EntityPlayer.worldObj);
/*     */     
/* 391 */     if (par4)
/*     */     {
/* 393 */       if (var5 == null) {
/*     */         
/* 395 */         par3List.add("Unknown map");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 407 */         par3List.add(Translator.getFormatted("item.tooltip.mapScaling", new Object[] { Integer.valueOf(1 << var5.scale + (isBeingExtended(par1ItemStack) ? 1 : 0)) }));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isAnotherMapIdAvailable(World world) {
/* 414 */     return (world.peekUniqueDataId("map") <= 31999);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */