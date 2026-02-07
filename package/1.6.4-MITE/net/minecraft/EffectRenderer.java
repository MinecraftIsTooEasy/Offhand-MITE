/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public final class EffectRenderer
/*     */ {
/*  10 */   private static final ResourceLocation particleTextures = new ResourceLocation("textures/particle/particles.png");
/*     */   
/*     */   protected World worldObj;
/*     */   
/*  14 */   private List[] fxLayers = new List[4];
/*     */   
/*     */   private TextureManager renderer;
/*     */   
/*  18 */   private Random rand = new Random();
/*     */ 
/*     */   
/*     */   public EffectRenderer(World par1World, TextureManager par2TextureManager) {
/*  22 */     if (par1World != null)
/*     */     {
/*  24 */       this.worldObj = par1World;
/*     */     }
/*     */     
/*  27 */     this.renderer = par2TextureManager;
/*     */     
/*  29 */     for (int var3 = 0; var3 < 4; var3++)
/*     */     {
/*  31 */       this.fxLayers[var3] = new ArrayList();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEffect(EntityFX par1EntityFX) {
/*  37 */     int var2 = par1EntityFX.getFXLayer();
/*     */     
/*  39 */     if (this.fxLayers[var2].size() >= 4000)
/*     */     {
/*  41 */       this.fxLayers[var2].remove(0);
/*     */     }
/*     */     
/*  44 */     this.fxLayers[var2].add(par1EntityFX);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateEffects() {
/*  49 */     for (int var1 = 0; var1 < 4; var1++) {
/*     */       
/*  51 */       for (int var2 = 0; var2 < this.fxLayers[var1].size(); var2++) {
/*     */         
/*  53 */         EntityFX var3 = this.fxLayers[var1].get(var2);
/*  54 */         var3.onUpdate();
/*     */         
/*  56 */         if (var3.isDead)
/*     */         {
/*  58 */           this.fxLayers[var1].remove(var2--);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderParticles(Entity par1Entity, float par2) {
/*  69 */     float var3 = ActiveRenderInfo.rotationX;
/*  70 */     float var4 = ActiveRenderInfo.rotationZ;
/*  71 */     float var5 = ActiveRenderInfo.rotationYZ;
/*  72 */     float var6 = ActiveRenderInfo.rotationXY;
/*  73 */     float var7 = ActiveRenderInfo.rotationXZ;
/*  74 */     EntityFX.interpPosX = par1Entity.lastTickPosX + (par1Entity.posX - par1Entity.lastTickPosX) * par2;
/*  75 */     EntityFX.interpPosY = par1Entity.lastTickPosY + (par1Entity.posY - par1Entity.lastTickPosY) * par2;
/*  76 */     EntityFX.interpPosZ = par1Entity.lastTickPosZ + (par1Entity.posZ - par1Entity.lastTickPosZ) * par2;
/*     */     
/*  78 */     for (int var8 = 0; var8 < 3; var8++) {
/*     */       
/*  80 */       if (!this.fxLayers[var8].isEmpty()) {
/*     */         
/*  82 */         switch (var8) {
/*     */ 
/*     */           
/*     */           default:
/*  86 */             this.renderer.bindTexture(particleTextures);
/*     */             break;
/*     */           
/*     */           case 1:
/*  90 */             this.renderer.bindTexture(TextureMap.locationBlocksTexture);
/*     */             break;
/*     */           
/*     */           case 2:
/*  94 */             this.renderer.bindTexture(TextureMap.locationItemsTexture);
/*     */             break;
/*     */         } 
/*  97 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */         
/*  99 */         GL11.glDepthMask(true);
/* 100 */         GL11.glEnable(3042);
/* 101 */         GL11.glBlendFunc(770, 771);
/*     */         
/* 103 */         GL11.glAlphaFunc(516, 0.003921569F);
/* 104 */         Tessellator var9 = Tessellator.instance;
/* 105 */         var9.startDrawingQuads();
/*     */         
/* 107 */         for (int var10 = 0; var10 < this.fxLayers[var8].size(); var10++) {
/*     */           
/* 109 */           EntityFX var11 = this.fxLayers[var8].get(var10);
/* 110 */           var9.setBrightness(var11.getBrightnessForRender(par2));
/* 111 */           var11.renderParticle(var9, par2, var3, var7, var4, var5, var6);
/*     */         } 
/*     */         
/* 114 */         var9.draw();
/* 115 */         GL11.glDisable(3042);
/* 116 */         GL11.glDepthMask(true);
/* 117 */         GL11.glAlphaFunc(516, 0.1F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderLitParticles(Entity par1Entity, float par2) {
/* 124 */     float var3 = 0.017453292F;
/* 125 */     float var4 = MathHelper.cos(par1Entity.rotationYaw * 0.017453292F);
/* 126 */     float var5 = MathHelper.sin(par1Entity.rotationYaw * 0.017453292F);
/* 127 */     float var6 = -var5 * MathHelper.sin(par1Entity.rotationPitch * 0.017453292F);
/* 128 */     float var7 = var4 * MathHelper.sin(par1Entity.rotationPitch * 0.017453292F);
/* 129 */     float var8 = MathHelper.cos(par1Entity.rotationPitch * 0.017453292F);
/* 130 */     byte var9 = 3;
/* 131 */     List<EntityFX> var10 = this.fxLayers[var9];
/*     */     
/* 133 */     if (!var10.isEmpty()) {
/*     */       
/* 135 */       Tessellator var11 = Tessellator.instance;
/*     */       
/* 137 */       for (int var12 = 0; var12 < var10.size(); var12++) {
/*     */         
/* 139 */         EntityFX var13 = var10.get(var12);
/* 140 */         var11.setBrightness(var13.getBrightnessForRender(par2));
/* 141 */         var13.renderParticle(var11, par2, var4, var8, var5, var6, var7);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearEffects(World par1World) {
/* 148 */     this.worldObj = par1World;
/*     */     
/* 150 */     for (int var2 = 0; var2 < 4; var2++)
/*     */     {
/* 152 */       this.fxLayers[var2].clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void addBlockDestroyEffectsForSnow(int par1, int par2, int par3, int par4, int par5) {
/* 158 */     if (par4 != 0) {
/*     */       
/* 160 */       Block var6 = Block.blocksList[par4];
/* 161 */       byte var7 = 4;
/*     */       
/* 163 */       for (int var8 = 0; var8 < var7; var8++) {
/*     */         
/* 165 */         for (int var9 = 0; var9 < var7; var9++) {
/*     */           
/* 167 */           for (int var10 = 0; var10 < var7; var10++) {
/*     */             float offset_y;
/* 169 */             double var11 = par1 + (var8 + 0.5D) / var7;
/* 170 */             double var13 = par2 + (var9 + 0.5D) / var7;
/* 171 */             double var15 = par3 + (var10 + 0.5D) / var7;
/*     */             
/* 173 */             float max_offset_y = (par5 + 1) * 0.125F;
/*     */ 
/*     */             
/*     */             do {
/* 177 */               offset_y = this.rand.nextFloat();
/*     */             }
/* 179 */             while (offset_y > max_offset_y);
/*     */             
/* 181 */             var13 = (par2 + offset_y);
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 186 */             EntityDiggingFX fx = new EntityDiggingFX(this.worldObj, var11, var13, var15, var11 - par1 - 0.5D, var13 - par2 - 0.5D, var15 - par3 - 0.5D, var6, par5);
/*     */             
/* 188 */             fx.motionX *= 0.4000000059604645D;
/* 189 */             fx.motionY = 0.0D;
/* 190 */             fx.motionZ *= 0.4000000059604645D;
/*     */             
/* 192 */             fx.particleScale *= 0.8F;
/*     */             
/* 194 */             fx.visible_on_tick = Minecraft.theMinecraft.theWorld.getTotalWorldTime() + 1L;
/*     */             
/* 196 */             addEffect(fx.applyColourMultiplier(par1, par2, par3));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addBlockDestroyEffectsForPortalDamage(int par1, int par2, int par3, int par4, int par5) {
/* 205 */     if (par4 != 0) {
/*     */       
/* 207 */       Block var6 = this.worldObj.getBlock(par1, par2, par3);
/*     */       
/* 209 */       if (var6 == null || !PathFinder.isWoodenPortal(var6.blockID)) {
/*     */         return;
/*     */       }
/* 212 */       var6.setBlockBoundsBasedOnStateAndNeighbors(this.worldObj, par1, par2, par3);
/*     */       
/* 214 */       int index = Minecraft.getThreadIndex();
/*     */       
/* 216 */       for (int i = 0; i < 4; i++) {
/*     */         
/* 218 */         double var11 = par1 + var6.minX[index] + this.rand.nextFloat() * (var6.maxX[index] - var6.minX[index]);
/* 219 */         double var13 = par2 + var6.minY[index] + this.rand.nextFloat() * (var6.maxY[index] - var6.minY[index]);
/* 220 */         double var15 = par3 + var6.minZ[index] + this.rand.nextFloat() * (var6.maxZ[index] - var6.minZ[index]);
/*     */         
/* 222 */         EntityDiggingFX fx = new EntityDiggingFX(this.worldObj, var11, var13, var15, var11 - par1 - 0.5D, var13 - par2 - 0.5D, var15 - par3 - 0.5D, var6, par5);
/*     */         
/* 224 */         fx.motionX *= 0.4000000059604645D;
/* 225 */         fx.motionY = 0.0D;
/* 226 */         fx.motionZ *= 0.4000000059604645D;
/*     */         
/* 228 */         fx.particleScale *= 0.5F;
/*     */         
/* 230 */         addEffect(fx.applyColourMultiplier(par1, par2, par3));
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
/*     */   public void addBlockDestroyEffects(int x, int y, int z, int block_id, int metadata, int aux_data) {
/* 261 */     addBlockDestroyEffects(x, y, z, block_id, metadata, aux_data, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addBlockDestroyEffects(int x, int y, int z, int block_id, int metadata, int aux_data, AxisAlignedBB bounds_of_exclusion) {
/* 267 */     if (block_id == 0) {
/*     */       return;
/*     */     }
/* 270 */     Block block = Block.getBlock(block_id);
/*     */     
/* 272 */     long visible_on_tick = this.worldObj.getTotalWorldTime() + 1L;
/*     */     
/* 274 */     boolean was_not_legal = BitHelper.isBitSet(aux_data, RenderGlobal.SFX_2001_WAS_NOT_LEGAL);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 281 */     block.setBlockBoundsBasedOnStateAndNeighbors(this.worldObj, x, y, z);
/*     */     
/* 283 */     int index = Minecraft.getThreadIndex();
/*     */     
/* 285 */     double min_x = MathHelper.clamp_double(block.getBlockBoundsMinX(index), 0.125D, 0.875D);
/* 286 */     double max_x = MathHelper.clamp_double(block.getBlockBoundsMaxX(index), 0.125D, 0.875D);
/* 287 */     double min_y = MathHelper.clamp_double(block.getBlockBoundsMinY(index), 0.125D, 0.875D);
/* 288 */     double max_y = MathHelper.clamp_double(block.getBlockBoundsMaxY(index), 0.125D, 0.875D);
/* 289 */     double min_z = MathHelper.clamp_double(block.getBlockBoundsMinZ(index), 0.125D, 0.875D);
/* 290 */     double max_z = MathHelper.clamp_double(block.getBlockBoundsMaxZ(index), 0.125D, 0.875D);
/*     */     
/* 292 */     double range_x = max_x - min_x;
/* 293 */     double range_y = max_y - min_y;
/* 294 */     double range_z = max_z - min_z;
/*     */     
/* 296 */     int num_divisions_x = 2 + MathHelper.ceiling_double_int(range_x * 2.0D);
/* 297 */     int num_divisions_y = 2 + MathHelper.ceiling_double_int(range_y * 2.0D);
/* 298 */     int num_divisions_z = 2 + MathHelper.ceiling_double_int(range_z * 2.0D);
/*     */ 
/*     */ 
/*     */     
/* 302 */     for (int var8 = 0; var8 < num_divisions_x; var8++) {
/*     */       
/* 304 */       for (int var9 = 0; var9 < num_divisions_y; var9++) {
/*     */         
/* 306 */         for (int var10 = 0; var10 < num_divisions_z; var10++) {
/*     */           
/* 308 */           double var11 = x + min_x + range_x * var8 / (num_divisions_x - 1);
/* 309 */           double var13 = y + min_y + range_y * var9 / (num_divisions_y - 1);
/* 310 */           double var15 = z + min_z + range_z * var10 / (num_divisions_z - 1);
/*     */           
/* 312 */           if (bounds_of_exclusion != null)
/*     */           {
/* 314 */             if (var11 >= bounds_of_exclusion.minX && var11 < bounds_of_exclusion.maxX)
/*     */             {
/* 316 */               if (var13 >= bounds_of_exclusion.minY && var13 < bounds_of_exclusion.maxY)
/*     */               {
/* 318 */                 if (var15 >= bounds_of_exclusion.minZ && var15 < bounds_of_exclusion.maxZ) {
/*     */                   continue;
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
/* 330 */           double motion_x = var11 - x - (max_x + min_x) * 0.5D;
/* 331 */           double motion_y = var13 - y - (max_y + min_y) * 0.5D;
/* 332 */           double motion_z = var15 - z - (max_z + min_z) * 0.5D;
/*     */           
/* 334 */           double scaler_x = 1.0D / MathHelper.clamp_double(block.getBlockBoundsMaxX(index) - block.getBlockBoundsMinX(index), 0.8D, 1.0D);
/* 335 */           double scaler_z = 1.0D / MathHelper.clamp_double(block.getBlockBoundsMaxZ(index) - block.getBlockBoundsMinZ(index), 0.8D, 1.0D);
/*     */ 
/*     */ 
/*     */           
/* 339 */           EntityFX fx = (new EntityDiggingFX(this.worldObj, var11, var13, var15, motion_x, motion_y, motion_z, block, metadata)).applyColourMultiplier(x, y, z).setVisibleOnTick(visible_on_tick);
/*     */           
/* 341 */           if (was_not_legal) {
/*     */             
/* 343 */             fx.motionX *= 0.699999988079071D;
/* 344 */             fx.motionZ *= 0.699999988079071D;
/* 345 */             fx.motionY *= 0.30000001192092896D;
/*     */           } 
/*     */ 
/*     */           
/* 349 */           fx.motionX *= scaler_x;
/* 350 */           fx.motionZ *= scaler_z;
/*     */           
/* 352 */           addEffect(fx);
/*     */           continue;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addBlockDestroyEffectsForReplace(int x, int y, int z, int block_id, int metadata, int successor_block_id, int successor_metadata) {
/* 364 */     if (block_id == 0) {
/*     */       return;
/*     */     }
/* 367 */     Block successor_block = Block.getBlock(successor_block_id);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 372 */     if (!successor_block.isSolid(successor_metadata)) {
/*     */       
/* 374 */       addBlockDestroyEffects(x, y, z, block_id, metadata, 0);
/*     */       
/*     */       return;
/*     */     } 
/* 378 */     int original_metadata = this.worldObj.getBlockMetadata(x, y, z);
/*     */     
/* 380 */     this.worldObj.setBlockMetadataWithNotify(x, y, z, successor_metadata, 0);
/*     */ 
/*     */ 
/*     */     
/* 384 */     AxisAlignedBB bb = successor_block.getCollisionBoundsCombined(this.worldObj, x, y, z, null, true);
/*     */     
/* 386 */     this.worldObj.setBlockMetadataWithNotify(x, y, z, original_metadata, 0);
/*     */     
/* 388 */     addBlockDestroyEffects(x, y, z, block_id, metadata, 0, bb);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addBlockHitEffects(int par1, int par2, int par3, EnumFace face_hit) {
/* 397 */     int var5 = this.worldObj.getBlockId(par1, par2, par3);
/*     */     
/* 399 */     if (var5 != 0) {
/*     */       
/* 401 */       Block var6 = Block.blocksList[var5];
/* 402 */       float var7 = 0.1F;
/*     */       
/* 404 */       int index = Minecraft.getThreadIndex();
/*     */       
/* 406 */       double var8 = par1 + this.rand.nextDouble() * (var6.getBlockBoundsMaxX(index) - var6.getBlockBoundsMinX(index) - (var7 * 2.0F)) + var7 + var6.getBlockBoundsMinX(index);
/* 407 */       double var10 = par2 + this.rand.nextDouble() * (var6.getBlockBoundsMaxY(index) - var6.getBlockBoundsMinY(index) - (var7 * 2.0F)) + var7 + var6.getBlockBoundsMinY(index);
/* 408 */       double var12 = par3 + this.rand.nextDouble() * (var6.getBlockBoundsMaxZ(index) - var6.getBlockBoundsMinZ(index) - (var7 * 2.0F)) + var7 + var6.getBlockBoundsMinZ(index);
/*     */ 
/*     */       
/* 411 */       if (face_hit == EnumFace.BOTTOM)
/*     */       {
/* 413 */         var10 = par2 + var6.getBlockBoundsMinY(index) - var7;
/*     */       }
/*     */ 
/*     */       
/* 417 */       if (face_hit == EnumFace.TOP)
/*     */       {
/* 419 */         var10 = par2 + var6.getBlockBoundsMaxY(index) + var7;
/*     */       }
/*     */ 
/*     */       
/* 423 */       if (face_hit == EnumFace.NORTH)
/*     */       {
/* 425 */         var12 = par3 + var6.getBlockBoundsMinZ(index) - var7;
/*     */       }
/*     */ 
/*     */       
/* 429 */       if (face_hit == EnumFace.SOUTH)
/*     */       {
/* 431 */         var12 = par3 + var6.getBlockBoundsMaxZ(index) + var7;
/*     */       }
/*     */ 
/*     */       
/* 435 */       if (face_hit == EnumFace.WEST)
/*     */       {
/* 437 */         var8 = par1 + var6.getBlockBoundsMinX(index) - var7;
/*     */       }
/*     */ 
/*     */       
/* 441 */       if (face_hit == EnumFace.EAST)
/*     */       {
/* 443 */         var8 = par1 + var6.getBlockBoundsMaxX(index) + var7;
/*     */       }
/*     */       
/* 446 */       addEffect((new EntityDiggingFX(this.worldObj, var8, var10, var12, 0.0D, 0.0D, 0.0D, var6, this.worldObj.getBlockMetadata(par1, par2, par3))).applyColourMultiplier(par1, par2, par3).multiplyVelocity(0.2F).multipleParticleScaleBy(0.6F));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStatistics() {
/* 452 */     return "" + (this.fxLayers[0].size() + this.fxLayers[1].size() + this.fxLayers[2].size());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStatsString() {
/* 457 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 459 */     for (int i = 0; i < this.fxLayers.length; i++) {
/*     */       
/* 461 */       int size = this.fxLayers[i].size();
/*     */       
/* 463 */       if (i == 0 || size != 0) {
/* 464 */         sb.append("[" + i + "]: " + size + " ");
/*     */       }
/*     */     } 
/* 467 */     return StringHelper.stripTrailing(" ", sb.toString());
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EffectRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */