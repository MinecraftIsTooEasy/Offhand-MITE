/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Arrays;
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
/*     */ public final class ExtendedBlockStorage
/*     */ {
/*     */   private int yBase;
/*     */   private int blockRefCount;
/*     */   private int tickRefCount;
/*     */   public byte[] blockLSBArray;
/*     */   public NibbleArray blockMSBArray;
/*     */   private NibbleArray blockMetadataArray;
/*     */   private NibbleArray blocklightArray;
/*     */   private NibbleArray skylightArray;
/*     */   
/*     */   public ExtendedBlockStorage(int par1, boolean par2) {
/*  49 */     this.yBase = par1;
/*  50 */     this.blockLSBArray = new byte[4096];
/*     */ 
/*     */ 
/*     */     
/*  54 */     this.blockMetadataArray = new NibbleArray(this.blockLSBArray.length);
/*  55 */     this.blocklightArray = new NibbleArray(this.blockLSBArray.length);
/*     */     
/*  57 */     if (par2)
/*     */     {
/*     */       
/*  60 */       this.skylightArray = new NibbleArray(this.blockLSBArray.length);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExtBlockID(int par1, int par2, int par3) {
/*  70 */     int var4 = this.blockLSBArray[par2 << 8 | par3 << 4 | par1] & 0xFF;
/*  71 */     return (this.blockMSBArray != null) ? (this.blockMSBArray.get(par1, par2, par3) << 8 | var4) : var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtBlockID(int par1, int par2, int par3, int par4) {
/*  81 */     int var5 = this.blockLSBArray[par2 << 8 | par3 << 4 | par1] & 0xFF;
/*     */     
/*  83 */     if (this.blockMSBArray != null) {
/*     */       
/*  85 */       var5 |= this.blockMSBArray.get(par1, par2, par3) << 8;
/*  86 */       Debug.setErrorMessage("setExtBlockID: blockMSBArray was not null");
/*     */     } 
/*     */     
/*  89 */     if (var5 == 0 && par4 != 0) {
/*     */       
/*  91 */       this.blockRefCount++;
/*     */       
/*  93 */       if (Block.blocksList[par4] != null && Block.blocksList[par4].getTickRandomly())
/*     */       {
/*  95 */         this.tickRefCount++;
/*     */       }
/*     */     }
/*  98 */     else if (var5 != 0 && par4 == 0) {
/*     */       
/* 100 */       this.blockRefCount--;
/*     */       
/* 102 */       if (Block.blocksList[var5] != null && Block.blocksList[var5].getTickRandomly())
/*     */       {
/* 104 */         this.tickRefCount--;
/*     */       }
/*     */     }
/* 107 */     else if (Block.blocksList[var5] != null && Block.blocksList[var5].getTickRandomly() && (Block.blocksList[par4] == null || !Block.blocksList[par4].getTickRandomly())) {
/*     */       
/* 109 */       this.tickRefCount--;
/*     */     }
/* 111 */     else if ((Block.blocksList[var5] == null || !Block.blocksList[var5].getTickRandomly()) && Block.blocksList[par4] != null && Block.blocksList[par4].getTickRandomly()) {
/*     */       
/* 113 */       this.tickRefCount++;
/*     */     } 
/*     */     
/* 116 */     this.blockLSBArray[par2 << 8 | par3 << 4 | par1] = (byte)(par4 & 0xFF);
/*     */     
/* 118 */     if (par4 > 255) {
/*     */       
/* 120 */       Debug.setErrorMessage("setExtBlockID: condition was true");
/*     */       
/* 122 */       if (this.blockMSBArray == null)
/*     */       {
/*     */         
/* 125 */         this.blockMSBArray = new NibbleArray(this.blockLSBArray.length);
/*     */       }
/*     */       
/* 128 */       this.blockMSBArray.set(par1, par2, par3, (par4 & 0xF00) >> 8);
/*     */     }
/* 130 */     else if (this.blockMSBArray != null) {
/*     */       
/* 132 */       this.blockMSBArray.set(par1, par2, par3, 0);
/* 133 */       Debug.setErrorMessage("setExtBlockID: blockMSBArray was not null (2)");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExtBlockMetadata(int par1, int par2, int par3) {
/* 142 */     return this.blockMetadataArray.get(par1, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtBlockMetadata(int par1, int par2, int par3, int par4) {
/* 150 */     this.blockMetadataArray.set(par1, par2, par3, par4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 158 */     return (this.blockRefCount == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getNeedsRandomTick() {
/* 167 */     return (this.tickRefCount > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getYLocation() {
/* 175 */     return this.yBase;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setExtSkylightValue(int par1, int par2, int par3, int par4) {
/* 184 */     this.skylightArray.set(par1, par2, par3, par4);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setExtSkylightValue(int xz_index, int y, int brightness) {
/* 189 */     this.skylightArray.set(xz_index, y, brightness);
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
/*     */   public void setExtSkylightValue(int index, int y, int brightness, Chunk chunk) {
/* 215 */     this.skylightArray.set(index, y & 0xF, brightness);
/*     */     
/* 217 */     if (brightness == 0) {
/*     */       
/* 219 */       if (chunk.skylight_bottom[index] == y) {
/* 220 */         chunk.skylight_bottom[index] = chunk.recalculateSkylightBottom(index, y + 1);
/*     */       
/*     */       }
/*     */     }
/* 224 */     else if (y < chunk.skylight_bottom[index]) {
/* 225 */       chunk.skylight_bottom[index] = y;
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
/*     */   public void fillSkylightValues(int brightness) {
/* 240 */     Arrays.fill(this.skylightArray.data, (byte)(brightness | brightness << 4));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExtSkylightValue(int par1, int par2, int par3) {
/* 248 */     return this.skylightArray.get(par1, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExtSkylightValue(int xz_index, int y) {
/* 254 */     return this.skylightArray.get(xz_index, y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtBlocklightValue(int par1, int par2, int par3, int par4) {
/* 262 */     this.blocklightArray.set(par1, par2, par3, par4);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExtBlocklightValue(int xz_index, int y, int value) {
/* 267 */     this.blocklightArray.set(xz_index, y, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExtBlocklightValue(int par1, int par2, int par3) {
/* 275 */     return this.blocklightArray.get(par1, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExtBlocklightValue(int xz_index, int y) {
/* 281 */     return this.blocklightArray.get(xz_index, y);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeInvalidBlocks() {
/* 286 */     this.blockRefCount = 0;
/* 287 */     this.tickRefCount = 0;
/*     */     
/* 289 */     for (int var1 = 0; var1 < 16; var1++) {
/*     */       
/* 291 */       for (int var2 = 0; var2 < 16; var2++) {
/*     */         
/* 293 */         for (int var3 = 0; var3 < 16; var3++) {
/*     */           
/* 295 */           int var4 = getExtBlockID(var1, var2, var3);
/*     */           
/* 297 */           if (var4 > 0)
/*     */           {
/* 299 */             if (Block.blocksList[var4] == null) {
/*     */               
/* 301 */               this.blockLSBArray[var2 << 8 | var3 << 4 | var1] = 0;
/*     */               
/* 303 */               if (this.blockMSBArray != null)
/*     */               {
/* 305 */                 this.blockMSBArray.set(var1, var2, var3, 0);
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/* 310 */               this.blockRefCount++;
/*     */               
/* 312 */               if (Block.blocksList[var4].getTickRandomly())
/*     */               {
/* 314 */                 this.tickRefCount++;
/*     */               }
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getBlockLSBArray() {
/* 325 */     return this.blockLSBArray;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearMSBArray() {
/* 330 */     this.blockMSBArray = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NibbleArray getBlockMSBArray() {
/* 338 */     return this.blockMSBArray;
/*     */   }
/*     */ 
/*     */   
/*     */   public NibbleArray getMetadataArray() {
/* 343 */     return this.blockMetadataArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NibbleArray getBlocklightArray() {
/* 351 */     return this.blocklightArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NibbleArray getSkylightArray() {
/* 359 */     return this.skylightArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockLSBArray(byte[] par1ArrayOfByte) {
/* 367 */     this.blockLSBArray = par1ArrayOfByte;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockMSBArray(NibbleArray par1NibbleArray) {
/* 375 */     this.blockMSBArray = par1NibbleArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockMetadataArray(NibbleArray par1NibbleArray) {
/* 383 */     this.blockMetadataArray = par1NibbleArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlocklightArray(NibbleArray par1NibbleArray) {
/* 391 */     this.blocklightArray = par1NibbleArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSkylightArray(NibbleArray par1NibbleArray) {
/* 399 */     this.skylightArray = par1NibbleArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NibbleArray createBlockMSBArray() {
/* 409 */     this.blockMSBArray = new NibbleArray(this.blockLSBArray.length);
/* 410 */     return this.blockMSBArray;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ExtendedBlockStorage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */