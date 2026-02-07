/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileEntityPiston
/*     */   extends TileEntity
/*     */ {
/*     */   private int storedBlockID;
/*     */   private int storedMetadata;
/*     */   private int storedOrientation;
/*     */   private boolean extending;
/*     */   private boolean shouldHeadBeRendered;
/*     */   private float progress;
/*     */   private float lastProgress;
/*  22 */   private List pushedObjects = new ArrayList();
/*     */ 
/*     */   
/*     */   public TileEntityPiston() {}
/*     */   
/*     */   public TileEntityPiston(int par1, int par2, int par3, boolean par4, boolean par5) {
/*  28 */     this.storedBlockID = par1;
/*  29 */     this.storedMetadata = par2;
/*  30 */     this.storedOrientation = par3;
/*  31 */     this.extending = par4;
/*  32 */     this.shouldHeadBeRendered = par5;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStoredBlockID() {
/*  37 */     return this.storedBlockID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBlockMetadata() {
/*  45 */     return this.storedMetadata;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isExtending() {
/*  53 */     return this.extending;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPistonOrientation() {
/*  61 */     return this.storedOrientation;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldRenderHead() {
/*  66 */     return this.shouldHeadBeRendered;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getProgress(float par1) {
/*  75 */     if (par1 > 1.0F)
/*     */     {
/*  77 */       par1 = 1.0F;
/*     */     }
/*     */     
/*  80 */     return this.lastProgress + (this.progress - this.lastProgress) * par1;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getOffsetX(float par1) {
/*  85 */     return this.extending ? ((getProgress(par1) - 1.0F) * Facing.offsetsXForSide[this.storedOrientation]) : ((1.0F - getProgress(par1)) * Facing.offsetsXForSide[this.storedOrientation]);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getOffsetY(float par1) {
/*  90 */     return this.extending ? ((getProgress(par1) - 1.0F) * Facing.offsetsYForSide[this.storedOrientation]) : ((1.0F - getProgress(par1)) * Facing.offsetsYForSide[this.storedOrientation]);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getOffsetZ(float par1) {
/*  95 */     return this.extending ? ((getProgress(par1) - 1.0F) * Facing.offsetsZForSide[this.storedOrientation]) : ((1.0F - getProgress(par1)) * Facing.offsetsZForSide[this.storedOrientation]);
/*     */   }
/*     */ 
/*     */   
/*     */   private void updatePushedObjects(float par1, float par2) {
/* 100 */     if (this.extending) {
/*     */       
/* 102 */       par1 = 1.0F - par1;
/*     */     }
/*     */     else {
/*     */       
/* 106 */       par1--;
/*     */     } 
/*     */     
/* 109 */     AxisAlignedBB var3 = Block.pistonMoving.getAxisAlignedBB(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.storedBlockID, par1, this.storedOrientation);
/*     */     
/* 111 */     if (var3 != null) {
/*     */       
/* 113 */       List var4 = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)null, var3);
/*     */       
/* 115 */       if (!var4.isEmpty()) {
/*     */         
/* 117 */         this.pushedObjects.addAll(var4);
/* 118 */         Iterator<Entity> var5 = this.pushedObjects.iterator();
/*     */         
/* 120 */         while (var5.hasNext()) {
/*     */           
/* 122 */           Entity var6 = var5.next();
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 127 */           double dx = 0.0D;
/* 128 */           double dy = 0.0D;
/* 129 */           double dz = 0.0D;
/*     */           
/* 131 */           if (this.storedOrientation == 0) {
/*     */             
/* 133 */             dy = var3.minY - var6.boundingBox.maxY;
/*     */             
/* 135 */             if (dy > 0.0D) {
/* 136 */               dy = 0.0D;
/*     */             }
/* 138 */           } else if (this.storedOrientation == 1) {
/*     */             
/* 140 */             dy = var3.maxY - var6.boundingBox.minY;
/*     */             
/* 142 */             if (dy < 0.0D) {
/* 143 */               dy = 0.0D;
/*     */             }
/* 145 */           } else if (this.storedOrientation == 2) {
/*     */             
/* 147 */             dz = var3.minZ - var6.boundingBox.maxZ;
/*     */             
/* 149 */             if (dz > 0.0D) {
/* 150 */               dz = 0.0D;
/*     */             }
/* 152 */           } else if (this.storedOrientation == 3) {
/*     */             
/* 154 */             dz = var3.maxZ - var6.boundingBox.minZ;
/*     */             
/* 156 */             if (dz < 0.0D) {
/* 157 */               dz = 0.0D;
/*     */             }
/* 159 */           } else if (this.storedOrientation == 4) {
/*     */             
/* 161 */             dx = var3.minX - var6.boundingBox.maxX;
/*     */             
/* 163 */             if (dx > 0.0D) {
/* 164 */               dx = 0.0D;
/*     */             }
/* 166 */           } else if (this.storedOrientation == 5) {
/*     */             
/* 168 */             dx = var3.maxX - var6.boundingBox.minX;
/*     */             
/* 170 */             if (dx < 0.0D) {
/* 171 */               dx = 0.0D;
/*     */             }
/*     */           } 
/* 174 */           var6.moveEntity(dx, dy, dz);
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 179 */         this.pushedObjects.clear();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearPistonTileEntity() {
/* 189 */     if (this.lastProgress < 1.0F && this.worldObj != null) {
/*     */       
/* 191 */       this.lastProgress = this.progress = 1.0F;
/* 192 */       this.worldObj.removeBlockTileEntity(this.xCoord, this.yCoord, this.zCoord);
/* 193 */       invalidate();
/*     */       
/* 195 */       if (this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord) == Block.pistonMoving.blockID) {
/*     */         
/* 197 */         this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, this.storedBlockID, this.storedMetadata, 3);
/* 198 */         this.worldObj.notifyBlockOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.storedBlockID);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateEntity() {
/* 209 */     this.lastProgress = this.progress;
/*     */     
/* 211 */     if (this.lastProgress >= 1.0F) {
/*     */ 
/*     */       
/* 214 */       updatePushedObjects(1.0F, this.extending ? 1.0F : 0.25F);
/* 215 */       this.worldObj.removeBlockTileEntity(this.xCoord, this.yCoord, this.zCoord);
/* 216 */       invalidate();
/*     */       
/* 218 */       if (this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord) == Block.pistonMoving.blockID)
/*     */       {
/* 220 */         this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, this.storedBlockID, this.storedMetadata, 3);
/* 221 */         this.worldObj.notifyBlockOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.storedBlockID);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 226 */       this.progress += 0.5F;
/*     */       
/* 228 */       if (this.progress >= 1.0F)
/*     */       {
/* 230 */         this.progress = 1.0F;
/*     */       }
/*     */       
/* 233 */       if (this.extending)
/*     */       {
/*     */         
/* 236 */         updatePushedObjects(this.progress, 0.25F + this.progress * 0.75F);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 246 */     super.readFromNBT(par1NBTTagCompound);
/* 247 */     this.storedBlockID = par1NBTTagCompound.getInteger("blockId");
/* 248 */     this.storedMetadata = par1NBTTagCompound.getInteger("blockData");
/* 249 */     this.storedOrientation = par1NBTTagCompound.getInteger("facing");
/* 250 */     this.lastProgress = this.progress = par1NBTTagCompound.getFloat("progress");
/* 251 */     this.extending = par1NBTTagCompound.getBoolean("extending");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
/* 259 */     super.writeToNBT(par1NBTTagCompound);
/* 260 */     par1NBTTagCompound.setInteger("blockId", this.storedBlockID);
/* 261 */     par1NBTTagCompound.setInteger("blockData", this.storedMetadata);
/* 262 */     par1NBTTagCompound.setInteger("facing", this.storedOrientation);
/* 263 */     par1NBTTagCompound.setFloat("progress", this.lastProgress);
/* 264 */     par1NBTTagCompound.setBoolean("extending", this.extending);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityPiston.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */