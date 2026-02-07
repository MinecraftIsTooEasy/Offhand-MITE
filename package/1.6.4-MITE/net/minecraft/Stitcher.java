/*     */ package net.minecraft;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Stitcher
/*     */ {
/*     */   private final Set setStitchHolders;
/*     */   private final List stitchSlots;
/*     */   private int currentWidth;
/*     */   private int currentHeight;
/*     */   private final int maxWidth;
/*     */   private final int maxHeight;
/*     */   private final boolean forcePowerOf2;
/*     */   private final int maxTileDimension;
/*     */   
/*     */   public Stitcher(int par1, int par2, boolean par3) {
/*  27 */     this(par1, par2, par3, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public Stitcher(int par1, int par2, boolean par3, int par4) {
/*  32 */     this.setStitchHolders = new HashSet(256);
/*  33 */     this.stitchSlots = new ArrayList(256);
/*  34 */     this.maxWidth = par1;
/*  35 */     this.maxHeight = par2;
/*  36 */     this.forcePowerOf2 = par3;
/*  37 */     this.maxTileDimension = par4;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrentWidth() {
/*  42 */     return this.currentWidth;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrentHeight() {
/*  47 */     return this.currentHeight;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addSprite(TextureAtlasSprite par1TextureAtlasSprite) {
/*  52 */     StitchHolder var2 = new StitchHolder(par1TextureAtlasSprite);
/*     */     
/*  54 */     if (this.maxTileDimension > 0)
/*     */     {
/*  56 */       var2.setNewDimension(this.maxTileDimension);
/*     */     }
/*     */     
/*  59 */     this.setStitchHolders.add(var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doStitch() {
/*  64 */     StitchHolder[] var1 = (StitchHolder[])this.setStitchHolders.toArray((Object[])new StitchHolder[this.setStitchHolders.size()]);
/*  65 */     Arrays.sort((Object[])var1);
/*  66 */     StitchHolder[] var2 = var1;
/*  67 */     int var3 = var1.length;
/*     */     
/*  69 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/*  71 */       StitchHolder var5 = var2[var4];
/*     */       
/*  73 */       if (!allocateSlot(var5)) {
/*     */         
/*  75 */         String var6 = String.format("Unable to fit: %s - size: %dx%d - Maybe try a lowerresolution texturepack?", new Object[] { var5.getAtlasSprite().getIconName(), Integer.valueOf(var5.getAtlasSprite().getIconWidth()), Integer.valueOf(var5.getAtlasSprite().getIconHeight()) });
/*  76 */         throw new StitcherException(var5, var6);
/*     */       } 
/*     */     } 
/*     */     
/*  80 */     if (this.forcePowerOf2) {
/*     */       
/*  82 */       this.currentWidth = getCeilPowerOf2(this.currentWidth);
/*  83 */       this.currentHeight = getCeilPowerOf2(this.currentHeight);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List getStichSlots() {
/*  89 */     ArrayList var1 = Lists.newArrayList();
/*  90 */     Iterator<StitchSlot> var2 = this.stitchSlots.iterator();
/*     */     
/*  92 */     while (var2.hasNext()) {
/*     */       
/*  94 */       StitchSlot var3 = var2.next();
/*  95 */       var3.getAllStitchSlots(var1);
/*     */     } 
/*     */     
/*  98 */     ArrayList<TextureAtlasSprite> var7 = Lists.newArrayList();
/*  99 */     Iterator<StitchSlot> var8 = var1.iterator();
/*     */     
/* 101 */     while (var8.hasNext()) {
/*     */       
/* 103 */       StitchSlot var4 = var8.next();
/* 104 */       StitchHolder var5 = var4.getStitchHolder();
/* 105 */       TextureAtlasSprite var6 = var5.getAtlasSprite();
/* 106 */       var6.initSprite(this.currentWidth, this.currentHeight, var4.getOriginX(), var4.getOriginY(), var5.isRotated());
/* 107 */       var7.add(var6);
/*     */     } 
/*     */     
/* 110 */     return var7;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getCeilPowerOf2(int par1) {
/* 118 */     int var2 = par1 - 1;
/* 119 */     var2 |= var2 >> 1;
/* 120 */     var2 |= var2 >> 2;
/* 121 */     var2 |= var2 >> 4;
/* 122 */     var2 |= var2 >> 8;
/* 123 */     var2 |= var2 >> 16;
/* 124 */     return var2 + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean allocateSlot(StitchHolder par1StitchHolder) {
/* 132 */     for (int var2 = 0; var2 < this.stitchSlots.size(); var2++) {
/*     */       
/* 134 */       if (((StitchSlot)this.stitchSlots.get(var2)).addSlot(par1StitchHolder))
/*     */       {
/* 136 */         return true;
/*     */       }
/*     */       
/* 139 */       par1StitchHolder.rotate();
/*     */       
/* 141 */       if (((StitchSlot)this.stitchSlots.get(var2)).addSlot(par1StitchHolder))
/*     */       {
/* 143 */         return true;
/*     */       }
/*     */       
/* 146 */       par1StitchHolder.rotate();
/*     */     } 
/*     */     
/* 149 */     return expandAndAllocateSlot(par1StitchHolder);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean expandAndAllocateSlot(StitchHolder par1StitchHolder) {
/*     */     boolean var4;
/*     */     StitchSlot var15;
/* 157 */     int var2 = Math.min(par1StitchHolder.getHeight(), par1StitchHolder.getWidth());
/* 158 */     boolean var3 = (this.currentWidth == 0 && this.currentHeight == 0);
/*     */ 
/*     */     
/* 161 */     if (this.forcePowerOf2) {
/*     */       
/* 163 */       int var5 = getCeilPowerOf2(this.currentWidth);
/* 164 */       int var6 = getCeilPowerOf2(this.currentHeight);
/* 165 */       int var7 = getCeilPowerOf2(this.currentWidth + var2);
/* 166 */       int var8 = getCeilPowerOf2(this.currentHeight + var2);
/* 167 */       boolean var9 = (var7 <= this.maxWidth);
/* 168 */       boolean var10 = (var8 <= this.maxHeight);
/*     */       
/* 170 */       if (!var9 && !var10)
/*     */       {
/* 172 */         return false;
/*     */       }
/*     */       
/* 175 */       int var11 = Math.max(par1StitchHolder.getHeight(), par1StitchHolder.getWidth());
/*     */       
/* 177 */       if (var3 && !var9 && getCeilPowerOf2(this.currentHeight + var11) > this.maxHeight)
/*     */       {
/* 179 */         return false;
/*     */       }
/*     */       
/* 182 */       boolean var12 = (var5 != var7);
/* 183 */       boolean var13 = (var6 != var8);
/*     */       
/* 185 */       if (var12 ^ var13)
/*     */       {
/* 187 */         var4 = (var12 && var9);
/*     */       }
/*     */       else
/*     */       {
/* 191 */         var4 = (var9 && var5 <= var6);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 196 */       boolean var14 = (this.currentWidth + var2 <= this.maxWidth);
/* 197 */       boolean var16 = (this.currentHeight + var2 <= this.maxHeight);
/*     */       
/* 199 */       if (!var14 && !var16)
/*     */       {
/* 201 */         return false;
/*     */       }
/*     */       
/* 204 */       var4 = ((var3 || this.currentWidth <= this.currentHeight) && var14);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 209 */     if (var4) {
/*     */       
/* 211 */       if (par1StitchHolder.getWidth() > par1StitchHolder.getHeight())
/*     */       {
/* 213 */         par1StitchHolder.rotate();
/*     */       }
/*     */       
/* 216 */       if (this.currentHeight == 0)
/*     */       {
/* 218 */         this.currentHeight = par1StitchHolder.getHeight();
/*     */       }
/*     */       
/* 221 */       var15 = new StitchSlot(this.currentWidth, 0, par1StitchHolder.getWidth(), this.currentHeight);
/* 222 */       this.currentWidth += par1StitchHolder.getWidth();
/*     */     }
/*     */     else {
/*     */       
/* 226 */       var15 = new StitchSlot(0, this.currentHeight, this.currentWidth, par1StitchHolder.getHeight());
/* 227 */       this.currentHeight += par1StitchHolder.getHeight();
/*     */     } 
/*     */     
/* 230 */     var15.addSlot(par1StitchHolder);
/* 231 */     this.stitchSlots.add(var15);
/* 232 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Stitcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */