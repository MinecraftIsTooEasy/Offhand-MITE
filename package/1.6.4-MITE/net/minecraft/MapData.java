/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ public class MapData
/*     */   extends WorldSavedData
/*     */ {
/*     */   public int xCenter;
/*     */   public int zCenter;
/*     */   public byte dimension;
/*     */   public byte scale;
/*  17 */   public byte[] colors = new byte[16384];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  22 */   public List playersArrayList = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  27 */   private Map playersHashMap = new HashMap<Object, Object>();
/*  28 */   public Map playersVisibleOnMap = new LinkedHashMap<Object, Object>();
/*     */ 
/*     */   
/*     */   public MapData(String par1Str) {
/*  32 */     super(par1Str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  40 */     this.dimension = par1NBTTagCompound.getByte("dimension");
/*  41 */     this.xCenter = par1NBTTagCompound.getInteger("xCenter");
/*  42 */     this.zCenter = par1NBTTagCompound.getInteger("zCenter");
/*  43 */     this.scale = par1NBTTagCompound.getByte("scale");
/*     */     
/*  45 */     if (this.scale < 0)
/*     */     {
/*  47 */       this.scale = 0;
/*     */     }
/*     */     
/*  50 */     if (this.scale > 4)
/*     */     {
/*  52 */       this.scale = 4;
/*     */     }
/*     */     
/*  55 */     short var2 = par1NBTTagCompound.getShort("width");
/*  56 */     short var3 = par1NBTTagCompound.getShort("height");
/*     */     
/*  58 */     if (var2 == 128 && var3 == 128) {
/*     */       
/*  60 */       this.colors = par1NBTTagCompound.getByteArray("colors");
/*     */     }
/*     */     else {
/*     */       
/*  64 */       byte[] var4 = par1NBTTagCompound.getByteArray("colors");
/*  65 */       this.colors = new byte[16384];
/*  66 */       int var5 = (128 - var2) / 2;
/*  67 */       int var6 = (128 - var3) / 2;
/*     */       
/*  69 */       for (int var7 = 0; var7 < var3; var7++) {
/*     */         
/*  71 */         int var8 = var7 + var6;
/*     */         
/*  73 */         if (var8 >= 0 || var8 < 128)
/*     */         {
/*  75 */           for (int var9 = 0; var9 < var2; var9++) {
/*     */             
/*  77 */             int var10 = var9 + var5;
/*     */             
/*  79 */             if (var10 >= 0 || var10 < 128)
/*     */             {
/*  81 */               this.colors[var10 + var8 * 128] = var4[var9 + var7 * var2];
/*     */             }
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
/*     */   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
/*  94 */     par1NBTTagCompound.setByte("dimension", this.dimension);
/*  95 */     par1NBTTagCompound.setInteger("xCenter", this.xCenter);
/*  96 */     par1NBTTagCompound.setInteger("zCenter", this.zCenter);
/*  97 */     par1NBTTagCompound.setByte("scale", this.scale);
/*  98 */     par1NBTTagCompound.setShort("width", (short)128);
/*  99 */     par1NBTTagCompound.setShort("height", (short)128);
/* 100 */     par1NBTTagCompound.setByteArray("colors", this.colors);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateVisiblePlayers(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack) {
/* 108 */     if (!this.playersHashMap.containsKey(par1EntityPlayer)) {
/*     */       
/* 110 */       MapInfo var3 = new MapInfo(this, par1EntityPlayer);
/* 111 */       this.playersHashMap.put(par1EntityPlayer, var3);
/* 112 */       this.playersArrayList.add(var3);
/*     */     } 
/*     */     
/* 115 */     if (!par1EntityPlayer.inventory.hasItemStack(par2ItemStack))
/*     */     {
/* 117 */       this.playersVisibleOnMap.remove(par1EntityPlayer.getCommandSenderName());
/*     */     }
/*     */     
/* 120 */     for (int var5 = 0; var5 < this.playersArrayList.size(); var5++) {
/*     */       
/* 122 */       MapInfo var4 = this.playersArrayList.get(var5);
/*     */       
/* 124 */       if (!var4.entityplayerObj.isDead && (var4.entityplayerObj.inventory.hasItemStack(par2ItemStack) || par2ItemStack.isOnItemFrame())) {
/*     */         
/* 126 */         if (!par2ItemStack.isOnItemFrame() && var4.entityplayerObj.dimension == this.dimension)
/*     */         {
/* 128 */           func_82567_a(0, var4.entityplayerObj.worldObj, var4.entityplayerObj.getCommandSenderName(), var4.entityplayerObj.posX, var4.entityplayerObj.posZ, var4.entityplayerObj.rotationYaw);
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 133 */         this.playersHashMap.remove(var4.entityplayerObj);
/* 134 */         this.playersArrayList.remove(var4);
/*     */       } 
/*     */     } 
/*     */     
/* 138 */     if (par2ItemStack.isOnItemFrame())
/*     */     {
/* 140 */       func_82567_a(1, par1EntityPlayer.worldObj, "frame-" + (par2ItemStack.getItemFrame()).entityId, (par2ItemStack.getItemFrame()).xPosition, (par2ItemStack.getItemFrame()).zPosition, ((par2ItemStack.getItemFrame()).hangingDirection * 90));
/*     */     }
/*     */   }
/*     */   
/*     */   private void func_82567_a(int par1, World par2World, String par3Str, double par4, double par6, double par8) {
/*     */     byte var15;
/* 146 */     int var10 = 1 << this.scale;
/* 147 */     float var11 = (float)(par4 - this.xCenter) / var10;
/* 148 */     float var12 = (float)(par6 - this.zCenter) / var10;
/* 149 */     byte var13 = (byte)(int)((var11 * 2.0F) + 0.5D);
/* 150 */     byte var14 = (byte)(int)((var12 * 2.0F) + 0.5D);
/* 151 */     byte var16 = 63;
/*     */ 
/*     */     
/* 154 */     if (var11 >= -var16 && var12 >= -var16 && var11 <= var16 && var12 <= var16) {
/*     */       
/* 156 */       par8 += (par8 < 0.0D) ? -8.0D : 8.0D;
/* 157 */       var15 = (byte)(int)(par8 * 16.0D / 360.0D);
/*     */       
/* 159 */       if (this.dimension < 0)
/*     */       {
/*     */         
/* 162 */         int var17 = par2World.getTimeOfDay() / 10;
/* 163 */         var15 = (byte)(var17 * var17 * 34187121 + var17 * 121 >> 15 & 0xF);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 168 */       if (Math.abs(var11) >= 320.0F || Math.abs(var12) >= 320.0F) {
/*     */         
/* 170 */         this.playersVisibleOnMap.remove(par3Str);
/*     */         
/*     */         return;
/*     */       } 
/* 174 */       par1 = 6;
/* 175 */       var15 = 0;
/*     */       
/* 177 */       if (var11 <= -var16)
/*     */       {
/* 179 */         var13 = (byte)(int)((var16 * 2) + 2.5D);
/*     */       }
/*     */       
/* 182 */       if (var12 <= -var16)
/*     */       {
/* 184 */         var14 = (byte)(int)((var16 * 2) + 2.5D);
/*     */       }
/*     */       
/* 187 */       if (var11 >= var16)
/*     */       {
/* 189 */         var13 = (byte)(var16 * 2 + 1);
/*     */       }
/*     */       
/* 192 */       if (var12 >= var16)
/*     */       {
/* 194 */         var14 = (byte)(var16 * 2 + 1);
/*     */       }
/*     */     } 
/*     */     
/* 198 */     this.playersVisibleOnMap.put(par3Str, new MapCoord(this, (byte)par1, var13, var14, var15));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getUpdatePacketData(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
/* 206 */     MapInfo var4 = (MapInfo)this.playersHashMap.get(par3EntityPlayer);
/* 207 */     return (var4 == null) ? null : var4.getPlayersOnMap(par1ItemStack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColumnDirty(int par1, int par2, int par3) {
/* 216 */     markDirty();
/*     */     
/* 218 */     for (int var4 = 0; var4 < this.playersArrayList.size(); var4++) {
/*     */       
/* 220 */       MapInfo var5 = this.playersArrayList.get(var4);
/*     */       
/* 222 */       if (var5.field_76209_b[par1] < 0 || var5.field_76209_b[par1] > par2)
/*     */       {
/* 224 */         var5.field_76209_b[par1] = par2;
/*     */       }
/*     */       
/* 227 */       if (var5.field_76210_c[par1] < 0 || var5.field_76210_c[par1] < par3)
/*     */       {
/* 229 */         var5.field_76210_c[par1] = par3;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateMPMapData(byte[] par1ArrayOfByte) {
/* 241 */     if (par1ArrayOfByte[0] == 0) {
/*     */       
/* 243 */       int var2 = par1ArrayOfByte[1] & 0xFF;
/* 244 */       int var3 = par1ArrayOfByte[2] & 0xFF;
/*     */       
/* 246 */       for (int var4 = 0; var4 < par1ArrayOfByte.length - 3; var4++)
/*     */       {
/* 248 */         this.colors[(var4 + var3) * 128 + var2] = par1ArrayOfByte[var4 + 3];
/*     */       }
/*     */       
/* 251 */       markDirty();
/*     */     }
/* 253 */     else if (par1ArrayOfByte[0] == 1) {
/*     */       
/* 255 */       this.playersVisibleOnMap.clear();
/*     */       
/* 257 */       for (int var2 = 0; var2 < (par1ArrayOfByte.length - 1) / 3; var2++)
/*     */       {
/* 259 */         byte var7 = (byte)(par1ArrayOfByte[var2 * 3 + 1] >> 4);
/* 260 */         byte var8 = par1ArrayOfByte[var2 * 3 + 2];
/* 261 */         byte var5 = par1ArrayOfByte[var2 * 3 + 3];
/* 262 */         byte var6 = (byte)(par1ArrayOfByte[var2 * 3 + 1] & 0xF);
/* 263 */         this.playersVisibleOnMap.put("icon-" + var2, new MapCoord(this, var7, var8, var5, var6));
/*     */       }
/*     */     
/* 266 */     } else if (par1ArrayOfByte[0] == 2) {
/*     */       
/* 268 */       this.scale = par1ArrayOfByte[1];
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public MapInfo func_82568_a(EntityPlayer par1EntityPlayer) {
/* 274 */     MapInfo var2 = (MapInfo)this.playersHashMap.get(par1EntityPlayer);
/*     */     
/* 276 */     if (var2 == null) {
/*     */       
/* 278 */       var2 = new MapInfo(this, par1EntityPlayer);
/* 279 */       this.playersHashMap.put(par1EntityPlayer, var2);
/* 280 */       this.playersArrayList.add(var2);
/*     */     } 
/*     */     
/* 283 */     return var2;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MapData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */