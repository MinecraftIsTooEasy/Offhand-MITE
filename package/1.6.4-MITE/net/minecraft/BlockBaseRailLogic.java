/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockBaseRailLogic
/*     */ {
/*     */   private World logicWorld;
/*     */   private int railX;
/*     */   private int railY;
/*     */   private int railZ;
/*     */   private final boolean isStraightRail;
/*     */   private List railChunkPosition;
/*     */   final BlockRailBase theRail;
/*     */   
/*     */   public BlockBaseRailLogic(BlockRailBase par1BlockRailBase, World par2World, int par3, int par4, int par5) {
/*  21 */     this.theRail = par1BlockRailBase;
/*  22 */     this.railChunkPosition = new ArrayList();
/*  23 */     this.logicWorld = par2World;
/*  24 */     this.railX = par3;
/*  25 */     this.railY = par4;
/*  26 */     this.railZ = par5;
/*  27 */     int var6 = par2World.getBlockId(par3, par4, par5);
/*  28 */     int var7 = par2World.getBlockMetadata(par3, par4, par5);
/*     */     
/*  30 */     if (((BlockRailBase)Block.blocksList[var6]).isPowered) {
/*     */       
/*  32 */       this.isStraightRail = true;
/*  33 */       var7 &= 0xFFFFFFF7;
/*     */     }
/*     */     else {
/*     */       
/*  37 */       this.isStraightRail = false;
/*     */     } 
/*     */     
/*  40 */     setBasicRail(var7);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setBasicRail(int par1) {
/*  45 */     this.railChunkPosition.clear();
/*     */     
/*  47 */     if (par1 == 0) {
/*     */       
/*  49 */       this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ - 1));
/*  50 */       this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ + 1));
/*     */     }
/*  52 */     else if (par1 == 1) {
/*     */       
/*  54 */       this.railChunkPosition.add(new ChunkPosition(this.railX - 1, this.railY, this.railZ));
/*  55 */       this.railChunkPosition.add(new ChunkPosition(this.railX + 1, this.railY, this.railZ));
/*     */     }
/*  57 */     else if (par1 == 2) {
/*     */       
/*  59 */       this.railChunkPosition.add(new ChunkPosition(this.railX - 1, this.railY, this.railZ));
/*  60 */       this.railChunkPosition.add(new ChunkPosition(this.railX + 1, this.railY + 1, this.railZ));
/*     */     }
/*  62 */     else if (par1 == 3) {
/*     */       
/*  64 */       this.railChunkPosition.add(new ChunkPosition(this.railX - 1, this.railY + 1, this.railZ));
/*  65 */       this.railChunkPosition.add(new ChunkPosition(this.railX + 1, this.railY, this.railZ));
/*     */     }
/*  67 */     else if (par1 == 4) {
/*     */       
/*  69 */       this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY + 1, this.railZ - 1));
/*  70 */       this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ + 1));
/*     */     }
/*  72 */     else if (par1 == 5) {
/*     */       
/*  74 */       this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ - 1));
/*  75 */       this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY + 1, this.railZ + 1));
/*     */     }
/*  77 */     else if (par1 == 6) {
/*     */       
/*  79 */       this.railChunkPosition.add(new ChunkPosition(this.railX + 1, this.railY, this.railZ));
/*  80 */       this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ + 1));
/*     */     }
/*  82 */     else if (par1 == 7) {
/*     */       
/*  84 */       this.railChunkPosition.add(new ChunkPosition(this.railX - 1, this.railY, this.railZ));
/*  85 */       this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ + 1));
/*     */     }
/*  87 */     else if (par1 == 8) {
/*     */       
/*  89 */       this.railChunkPosition.add(new ChunkPosition(this.railX - 1, this.railY, this.railZ));
/*  90 */       this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ - 1));
/*     */     }
/*  92 */     else if (par1 == 9) {
/*     */       
/*  94 */       this.railChunkPosition.add(new ChunkPosition(this.railX + 1, this.railY, this.railZ));
/*  95 */       this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ - 1));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void refreshConnectedTracks() {
/* 101 */     for (int var1 = 0; var1 < this.railChunkPosition.size(); var1++) {
/*     */       
/* 103 */       BlockBaseRailLogic var2 = getRailLogic(this.railChunkPosition.get(var1));
/*     */       
/* 105 */       if (var2 != null && var2.isRailChunkPositionCorrect(this)) {
/*     */         
/* 107 */         this.railChunkPosition.set(var1, new ChunkPosition(var2.railX, var2.railY, var2.railZ));
/*     */       }
/*     */       else {
/*     */         
/* 111 */         this.railChunkPosition.remove(var1--);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isMinecartTrack(int par1, int par2, int par3) {
/* 118 */     return BlockRailBase.isRailBlockAt(this.logicWorld, par1, par2, par3) ? true : (BlockRailBase.isRailBlockAt(this.logicWorld, par1, par2 + 1, par3) ? true : BlockRailBase.isRailBlockAt(this.logicWorld, par1, par2 - 1, par3));
/*     */   }
/*     */ 
/*     */   
/*     */   private BlockBaseRailLogic getRailLogic(ChunkPosition par1ChunkPosition) {
/* 123 */     return BlockRailBase.isRailBlockAt(this.logicWorld, par1ChunkPosition.x, par1ChunkPosition.y, par1ChunkPosition.z) ? new BlockBaseRailLogic(this.theRail, this.logicWorld, par1ChunkPosition.x, par1ChunkPosition.y, par1ChunkPosition.z) : (BlockRailBase.isRailBlockAt(this.logicWorld, par1ChunkPosition.x, par1ChunkPosition.y + 1, par1ChunkPosition.z) ? new BlockBaseRailLogic(this.theRail, this.logicWorld, par1ChunkPosition.x, par1ChunkPosition.y + 1, par1ChunkPosition.z) : (BlockRailBase.isRailBlockAt(this.logicWorld, par1ChunkPosition.x, par1ChunkPosition.y - 1, par1ChunkPosition.z) ? new BlockBaseRailLogic(this.theRail, this.logicWorld, par1ChunkPosition.x, par1ChunkPosition.y - 1, par1ChunkPosition.z) : null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isRailChunkPositionCorrect(BlockBaseRailLogic par1BlockBaseRailLogic) {
/* 131 */     for (int var2 = 0; var2 < this.railChunkPosition.size(); var2++) {
/*     */       
/* 133 */       ChunkPosition var3 = this.railChunkPosition.get(var2);
/*     */       
/* 135 */       if (var3.x == par1BlockBaseRailLogic.railX && var3.z == par1BlockBaseRailLogic.railZ)
/*     */       {
/* 137 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 141 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isPartOfTrack(int par1, int par2, int par3) {
/* 146 */     for (int var4 = 0; var4 < this.railChunkPosition.size(); var4++) {
/*     */       
/* 148 */       ChunkPosition var5 = this.railChunkPosition.get(var4);
/*     */       
/* 150 */       if (var5.x == par1 && var5.z == par3)
/*     */       {
/* 152 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 156 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getNumberOfAdjacentTracks() {
/* 161 */     int var1 = 0;
/*     */     
/* 163 */     if (isMinecartTrack(this.railX, this.railY, this.railZ - 1))
/*     */     {
/* 165 */       var1++;
/*     */     }
/*     */     
/* 168 */     if (isMinecartTrack(this.railX, this.railY, this.railZ + 1))
/*     */     {
/* 170 */       var1++;
/*     */     }
/*     */     
/* 173 */     if (isMinecartTrack(this.railX - 1, this.railY, this.railZ))
/*     */     {
/* 175 */       var1++;
/*     */     }
/*     */     
/* 178 */     if (isMinecartTrack(this.railX + 1, this.railY, this.railZ))
/*     */     {
/* 180 */       var1++;
/*     */     }
/*     */     
/* 183 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean canConnectTo(BlockBaseRailLogic par1BlockBaseRailLogic) {
/* 188 */     return isRailChunkPositionCorrect(par1BlockBaseRailLogic) ? true : ((this.railChunkPosition.size() == 2) ? false : (this.railChunkPosition.isEmpty() ? true : true));
/*     */   }
/*     */ 
/*     */   
/*     */   private void connectToNeighbor(BlockBaseRailLogic par1BlockBaseRailLogic) {
/* 193 */     this.railChunkPosition.add(new ChunkPosition(par1BlockBaseRailLogic.railX, par1BlockBaseRailLogic.railY, par1BlockBaseRailLogic.railZ));
/* 194 */     boolean var2 = isPartOfTrack(this.railX, this.railY, this.railZ - 1);
/* 195 */     boolean var3 = isPartOfTrack(this.railX, this.railY, this.railZ + 1);
/* 196 */     boolean var4 = isPartOfTrack(this.railX - 1, this.railY, this.railZ);
/* 197 */     boolean var5 = isPartOfTrack(this.railX + 1, this.railY, this.railZ);
/* 198 */     byte var6 = -1;
/*     */     
/* 200 */     if (var2 || var3)
/*     */     {
/* 202 */       var6 = 0;
/*     */     }
/*     */     
/* 205 */     if (var4 || var5)
/*     */     {
/* 207 */       var6 = 1;
/*     */     }
/*     */     
/* 210 */     if (!this.isStraightRail) {
/*     */       
/* 212 */       if (var3 && var5 && !var2 && !var4)
/*     */       {
/* 214 */         var6 = 6;
/*     */       }
/*     */       
/* 217 */       if (var3 && var4 && !var2 && !var5)
/*     */       {
/* 219 */         var6 = 7;
/*     */       }
/*     */       
/* 222 */       if (var2 && var4 && !var3 && !var5)
/*     */       {
/* 224 */         var6 = 8;
/*     */       }
/*     */       
/* 227 */       if (var2 && var5 && !var3 && !var4)
/*     */       {
/* 229 */         var6 = 9;
/*     */       }
/*     */     } 
/*     */     
/* 233 */     if (var6 == 0) {
/*     */       
/* 235 */       if (BlockRailBase.isRailBlockAt(this.logicWorld, this.railX, this.railY + 1, this.railZ - 1))
/*     */       {
/* 237 */         var6 = 4;
/*     */       }
/*     */       
/* 240 */       if (BlockRailBase.isRailBlockAt(this.logicWorld, this.railX, this.railY + 1, this.railZ + 1))
/*     */       {
/* 242 */         var6 = 5;
/*     */       }
/*     */     } 
/*     */     
/* 246 */     if (var6 == 1) {
/*     */       
/* 248 */       if (BlockRailBase.isRailBlockAt(this.logicWorld, this.railX + 1, this.railY + 1, this.railZ))
/*     */       {
/* 250 */         var6 = 2;
/*     */       }
/*     */       
/* 253 */       if (BlockRailBase.isRailBlockAt(this.logicWorld, this.railX - 1, this.railY + 1, this.railZ))
/*     */       {
/* 255 */         var6 = 3;
/*     */       }
/*     */     } 
/*     */     
/* 259 */     if (var6 < 0)
/*     */     {
/* 261 */       var6 = 0;
/*     */     }
/*     */     
/* 264 */     int var7 = var6;
/*     */     
/* 266 */     if (this.isStraightRail)
/*     */     {
/* 268 */       var7 = this.logicWorld.getBlockMetadata(this.railX, this.railY, this.railZ) & 0x8 | var6;
/*     */     }
/*     */     
/* 271 */     this.logicWorld.setBlockMetadataWithNotify(this.railX, this.railY, this.railZ, var7, 3);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean canConnectFrom(int par1, int par2, int par3) {
/* 276 */     BlockBaseRailLogic var4 = getRailLogic(new ChunkPosition(par1, par2, par3));
/*     */     
/* 278 */     if (var4 == null)
/*     */     {
/* 280 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 284 */     var4.refreshConnectedTracks();
/* 285 */     return var4.canConnectTo(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_94511_a(boolean par1, boolean par2) {
/* 291 */     boolean var3 = canConnectFrom(this.railX, this.railY, this.railZ - 1);
/* 292 */     boolean var4 = canConnectFrom(this.railX, this.railY, this.railZ + 1);
/* 293 */     boolean var5 = canConnectFrom(this.railX - 1, this.railY, this.railZ);
/* 294 */     boolean var6 = canConnectFrom(this.railX + 1, this.railY, this.railZ);
/* 295 */     byte var7 = -1;
/*     */     
/* 297 */     if ((var3 || var4) && !var5 && !var6)
/*     */     {
/* 299 */       var7 = 0;
/*     */     }
/*     */     
/* 302 */     if ((var5 || var6) && !var3 && !var4)
/*     */     {
/* 304 */       var7 = 1;
/*     */     }
/*     */     
/* 307 */     if (!this.isStraightRail) {
/*     */       
/* 309 */       if (var4 && var6 && !var3 && !var5)
/*     */       {
/* 311 */         var7 = 6;
/*     */       }
/*     */       
/* 314 */       if (var4 && var5 && !var3 && !var6)
/*     */       {
/* 316 */         var7 = 7;
/*     */       }
/*     */       
/* 319 */       if (var3 && var5 && !var4 && !var6)
/*     */       {
/* 321 */         var7 = 8;
/*     */       }
/*     */       
/* 324 */       if (var3 && var6 && !var4 && !var5)
/*     */       {
/* 326 */         var7 = 9;
/*     */       }
/*     */     } 
/*     */     
/* 330 */     if (var7 == -1) {
/*     */       
/* 332 */       if (var3 || var4)
/*     */       {
/* 334 */         var7 = 0;
/*     */       }
/*     */       
/* 337 */       if (var5 || var6)
/*     */       {
/* 339 */         var7 = 1;
/*     */       }
/*     */       
/* 342 */       if (!this.isStraightRail)
/*     */       {
/* 344 */         if (par1) {
/*     */           
/* 346 */           if (var4 && var6)
/*     */           {
/* 348 */             var7 = 6;
/*     */           }
/*     */           
/* 351 */           if (var5 && var4)
/*     */           {
/* 353 */             var7 = 7;
/*     */           }
/*     */           
/* 356 */           if (var6 && var3)
/*     */           {
/* 358 */             var7 = 9;
/*     */           }
/*     */           
/* 361 */           if (var3 && var5)
/*     */           {
/* 363 */             var7 = 8;
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 368 */           if (var3 && var5)
/*     */           {
/* 370 */             var7 = 8;
/*     */           }
/*     */           
/* 373 */           if (var6 && var3)
/*     */           {
/* 375 */             var7 = 9;
/*     */           }
/*     */           
/* 378 */           if (var5 && var4)
/*     */           {
/* 380 */             var7 = 7;
/*     */           }
/*     */           
/* 383 */           if (var4 && var6)
/*     */           {
/* 385 */             var7 = 6;
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 391 */     if (var7 == 0) {
/*     */       
/* 393 */       if (BlockRailBase.isRailBlockAt(this.logicWorld, this.railX, this.railY + 1, this.railZ - 1))
/*     */       {
/* 395 */         var7 = 4;
/*     */       }
/*     */       
/* 398 */       if (BlockRailBase.isRailBlockAt(this.logicWorld, this.railX, this.railY + 1, this.railZ + 1))
/*     */       {
/* 400 */         var7 = 5;
/*     */       }
/*     */     } 
/*     */     
/* 404 */     if (var7 == 1) {
/*     */       
/* 406 */       if (BlockRailBase.isRailBlockAt(this.logicWorld, this.railX + 1, this.railY + 1, this.railZ))
/*     */       {
/* 408 */         var7 = 2;
/*     */       }
/*     */       
/* 411 */       if (BlockRailBase.isRailBlockAt(this.logicWorld, this.railX - 1, this.railY + 1, this.railZ))
/*     */       {
/* 413 */         var7 = 3;
/*     */       }
/*     */     } 
/*     */     
/* 417 */     if (var7 < 0) {
/*     */       return;
/*     */     }
/* 420 */     if (var7 < 0)
/*     */     {
/* 422 */       var7 = 0;
/*     */     }
/*     */     
/* 425 */     setBasicRail(var7);
/* 426 */     int var8 = var7;
/*     */     
/* 428 */     if (this.isStraightRail)
/*     */     {
/* 430 */       var8 = this.logicWorld.getBlockMetadata(this.railX, this.railY, this.railZ) & 0x8 | var7;
/*     */     }
/*     */     
/* 433 */     if (par2 || this.logicWorld.getBlockMetadata(this.railX, this.railY, this.railZ) != var8) {
/*     */       
/* 435 */       this.logicWorld.setBlockMetadataWithNotify(this.railX, this.railY, this.railZ, var8, 3);
/*     */       
/* 437 */       for (int var9 = 0; var9 < this.railChunkPosition.size(); var9++) {
/*     */         
/* 439 */         BlockBaseRailLogic var10 = getRailLogic(this.railChunkPosition.get(var9));
/*     */         
/* 441 */         if (var10 != null) {
/*     */           
/* 443 */           var10.refreshConnectedTracks();
/*     */           
/* 445 */           if (var10.canConnectTo(this))
/*     */           {
/* 447 */             var10.connectToNeighbor(this);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockBaseRailLogic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */