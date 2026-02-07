/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class WorldRenderer
/*     */ {
/*     */   public World worldObj;
/*  14 */   private int glRenderList = -1;
/*     */ 
/*     */   
/*     */   public static int chunksUpdated;
/*     */ 
/*     */   
/*     */   private int posX;
/*     */ 
/*     */   
/*     */   private int posY;
/*     */   
/*     */   private int posZ;
/*     */   
/*     */   public int posXMinus;
/*     */   
/*     */   public int posYMinus;
/*     */   
/*     */   public int posZMinus;
/*     */   
/*     */   public int posXClip;
/*     */   
/*     */   public int posYClip;
/*     */   
/*     */   public int posZClip;
/*     */   
/*     */   public boolean isInFrustum;
/*     */   
/*  41 */   public boolean[] skipRenderPass = new boolean[2];
/*     */ 
/*     */   
/*     */   public int posXPlus;
/*     */ 
/*     */   
/*     */   public int posYPlus;
/*     */ 
/*     */   
/*     */   public int posZPlus;
/*     */ 
/*     */   
/*     */   public boolean needsUpdate;
/*     */ 
/*     */   
/*     */   public AxisAlignedBB rendererBoundingBox;
/*     */ 
/*     */   
/*     */   public int chunkIndex;
/*     */ 
/*     */   
/*     */   public boolean isVisible = true;
/*     */ 
/*     */   
/*     */   public boolean isWaitingOnOcclusionQuery;
/*     */ 
/*     */   
/*     */   public int glOcclusionQuery;
/*     */ 
/*     */   
/*     */   public boolean isChunkLit;
/*     */ 
/*     */   
/*     */   public boolean isInitialized;
/*     */   
/*  76 */   public List tileEntityRenderers = new ArrayList();
/*     */ 
/*     */   
/*     */   private List tileEntities;
/*     */ 
/*     */   
/*     */   private int bytesDrawn;
/*     */ 
/*     */   
/*     */   public WorldRenderer(World par1World, List par2List, int par3, int par4, int par5, int par6) {
/*  86 */     this.worldObj = par1World;
/*  87 */     this.tileEntities = par2List;
/*  88 */     this.glRenderList = par6;
/*  89 */     this.posX = -999;
/*  90 */     setPosition(par3, par4, par5);
/*  91 */     this.needsUpdate = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPosition(int par1, int par2, int par3) {
/*  99 */     if (par1 != this.posX || par2 != this.posY || par3 != this.posZ) {
/*     */       
/* 101 */       setDontDraw();
/* 102 */       this.posX = par1;
/* 103 */       this.posY = par2;
/* 104 */       this.posZ = par3;
/* 105 */       this.posXPlus = par1 + 8;
/* 106 */       this.posYPlus = par2 + 8;
/* 107 */       this.posZPlus = par3 + 8;
/* 108 */       this.posXClip = par1 & 0x3FF;
/* 109 */       this.posYClip = par2;
/* 110 */       this.posZClip = par3 & 0x3FF;
/* 111 */       this.posXMinus = par1 - this.posXClip;
/* 112 */       this.posYMinus = par2 - this.posYClip;
/* 113 */       this.posZMinus = par3 - this.posZClip;
/* 114 */       float var4 = 6.0F;
/* 115 */       this.rendererBoundingBox = AxisAlignedBB.getBoundingBox((par1 - var4), (par2 - var4), (par3 - var4), ((par1 + 16) + var4), ((par2 + 16) + var4), ((par3 + 16) + var4));
/* 116 */       GL11.glNewList(this.glRenderList + 2, 4864);
/* 117 */       RenderItem.renderAABB(AxisAlignedBB.getAABBPool().getAABB((this.posXClip - var4), (this.posYClip - var4), (this.posZClip - var4), ((this.posXClip + 16) + var4), ((this.posYClip + 16) + var4), ((this.posZClip + 16) + var4)));
/* 118 */       GL11.glEndList();
/* 119 */       markDirty();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setupGLTranslation() {
/* 125 */     GL11.glTranslatef(this.posXClip, this.posYClip, this.posZClip);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateRenderer() {
/* 133 */     if (this.needsUpdate) {
/*     */       
/* 135 */       Chunk chunk = this.worldObj.getChunkFromBlockCoords(this.posX, this.posZ);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 145 */       this.needsUpdate = false;
/* 146 */       int var1 = this.posX;
/* 147 */       int var2 = this.posY;
/* 148 */       int var3 = this.posZ;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 153 */       int var4 = this.posX + 15;
/* 154 */       int var5 = this.posY + 15;
/* 155 */       int var6 = this.posZ + 15;
/*     */       
/* 157 */       for (int var7 = 0; var7 < 2; var7++)
/*     */       {
/* 159 */         this.skipRenderPass[var7] = true;
/*     */       }
/*     */       
/* 162 */       Chunk.isLit = false;
/* 163 */       HashSet<?> var21 = new HashSet();
/* 164 */       var21.addAll(this.tileEntityRenderers);
/* 165 */       this.tileEntityRenderers.clear();
/* 166 */       byte var8 = 1;
/* 167 */       ChunkCache var9 = new ChunkCache(this.worldObj, var1 - var8, var2 - var8, var3 - var8, var4 + var8, var5 + var8, var6 + var8, var8);
/*     */       
/* 169 */       Tessellator tessellator = Tessellator.instance;
/*     */       
/* 171 */       if (!var9.extendedLevelsInChunkCache()) {
/*     */         
/* 173 */         chunksUpdated++;
/* 174 */         RenderBlocks var10 = new RenderBlocks(var9);
/* 175 */         this.bytesDrawn = 0;
/*     */         
/* 177 */         for (int var11 = 0; var11 < 2; var11++) {
/*     */           
/* 179 */           boolean var12 = false;
/* 180 */           boolean var13 = false;
/* 181 */           boolean var14 = false;
/*     */ 
/*     */           
/* 184 */           for (int var15 = var2; var15 <= var5; var15++) {
/*     */ 
/*     */             
/* 187 */             for (int var16 = var3; var16 <= var6; var16++) {
/*     */ 
/*     */               
/* 190 */               for (int var17 = var1; var17 <= var4; var17++) {
/*     */ 
/*     */                 
/* 193 */                 int var18 = chunk.getBlockID(var17 & 0xF, var15, var16 & 0xF);
/*     */                 
/* 195 */                 if (var18 > 0) {
/*     */                   
/* 197 */                   if (!var14) {
/*     */                     
/* 199 */                     var14 = true;
/* 200 */                     GL11.glNewList(this.glRenderList + var11, 4864);
/* 201 */                     GL11.glPushMatrix();
/* 202 */                     setupGLTranslation();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/* 208 */                     float var19 = 1.00001F;
/* 209 */                     GL11.glTranslatef(-8.0F, -8.0F, -8.0F);
/* 210 */                     GL11.glScalef(var19, var19, var19);
/* 211 */                     GL11.glTranslatef(8.0F, 8.0F, 8.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/* 216 */                     tessellator.startDrawingQuads();
/* 217 */                     tessellator.setTranslation(-this.posX, -this.posY, -this.posZ);
/*     */                   } 
/*     */                   
/* 220 */                   Block var23 = Block.blocksList[var18];
/*     */                   
/* 222 */                   if (var23 != null) {
/*     */                     
/* 224 */                     if (var11 == 0 && var23.hasTileEntity()) {
/*     */                       
/* 226 */                       TileEntity var20 = var9.getBlockTileEntity(var17, var15, var16);
/*     */                       
/* 228 */                       if (TileEntityRenderer.instance.hasSpecialRenderer(var20))
/*     */                       {
/* 230 */                         this.tileEntityRenderers.add(var20);
/*     */                       }
/*     */                     } 
/*     */                     
/* 234 */                     int var24 = var23.getRenderBlockPass();
/*     */                     
/* 236 */                     if (var24 != var11) {
/*     */                       
/* 238 */                       var12 = true;
/*     */                     }
/* 240 */                     else if (var24 == var11) {
/*     */                       
/* 242 */                       var13 |= var10.renderBlockByRenderType(var23, var17, var15, var16);
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 250 */           if (var14) {
/*     */             
/* 252 */             this.bytesDrawn += tessellator.draw();
/* 253 */             GL11.glPopMatrix();
/* 254 */             GL11.glEndList();
/* 255 */             tessellator.setTranslation(0.0D, 0.0D, 0.0D);
/*     */           }
/*     */           else {
/*     */             
/* 259 */             var13 = false;
/*     */           } 
/*     */           
/* 262 */           if (var13)
/*     */           {
/* 264 */             this.skipRenderPass[var11] = false;
/*     */           }
/*     */           
/* 267 */           if (!var12) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 274 */       HashSet var22 = new HashSet();
/* 275 */       var22.addAll(this.tileEntityRenderers);
/* 276 */       var22.removeAll(var21);
/* 277 */       this.tileEntities.addAll(var22);
/* 278 */       var21.removeAll(this.tileEntityRenderers);
/* 279 */       this.tileEntities.removeAll(var21);
/* 280 */       this.isChunkLit = Chunk.isLit;
/* 281 */       this.isInitialized = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceToEntitySquared(Entity par1Entity) {
/* 291 */     float var2 = (float)(par1Entity.posX - this.posXPlus);
/* 292 */     float var3 = (float)(par1Entity.posY - this.posYPlus);
/* 293 */     float var4 = (float)(par1Entity.posZ - this.posZPlus);
/* 294 */     return var2 * var2 + var3 * var3 + var4 * var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDontDraw() {
/* 302 */     for (int var1 = 0; var1 < 2; var1++)
/*     */     {
/* 304 */       this.skipRenderPass[var1] = true;
/*     */     }
/*     */     
/* 307 */     this.isInFrustum = false;
/* 308 */     this.isInitialized = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void stopRendering() {
/* 313 */     setDontDraw();
/* 314 */     this.worldObj = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGLCallListForPass(int par1) {
/* 322 */     return !this.isInFrustum ? -1 : (!this.skipRenderPass[par1] ? (this.glRenderList + par1) : -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateInFrustum(ICamera par1ICamera) {
/* 327 */     this.isInFrustum = par1ICamera.isBoundingBoxInFrustum(this.rendererBoundingBox);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callOcclusionQueryList() {
/* 335 */     GL11.glCallList(this.glRenderList + 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean skipAllRenderPasses() {
/* 343 */     return !this.isInitialized ? false : ((this.skipRenderPass[0] && this.skipRenderPass[1]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void markDirty() {
/* 351 */     this.needsUpdate = true;
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
/*     */   public boolean isRenderingCoords(int x, int y, int z) {
/* 405 */     return (x >= this.posX && x < this.posX + 16 && y >= this.posY && y < this.posY + 16 && z >= this.posZ && z < this.posZ + 16);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */