/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public class BlockTripWire
/*     */   extends Block
/*     */ {
/*     */   private static final int pulled_bit = 1;
/*     */   private static final int raised_bit = 2;
/*     */   private static final int taut_bit = 4;
/*     */   
/*     */   public BlockTripWire(int par1) {
/*  16 */     super(par1, Material.circuits, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
/*  17 */     setBlockBoundsForAllThreads(0.0D, 0.0D, 0.0D, 1.0D, 0.15625D, 1.0D);
/*  18 */     setTickRandomly(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  23 */     return "Bit 1 set if entity has collided with wire, bit 2 set if wire is not on ground, and bit 4 set if wire is attached to hooks";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  28 */     return (metadata >= 0 && metadata < 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int tickRate(World par1World) {
/*  36 */     return 10;
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
/*     */   public int getRenderBlockPass() {
/*  71 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/*  79 */     return 30;
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
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/*  95 */     return Item.silk.itemID;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isPulled(int metadata) {
/* 101 */     return BitHelper.isBitSet(metadata, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isRaised(int metadata) {
/* 106 */     return BitHelper.isBitSet(metadata, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isTaut(int metadata) {
/* 112 */     return BitHelper.isBitSet(metadata, 4);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
/* 117 */     if (isRaised(metadata)) {
/* 118 */       return true;
/*     */     }
/* 120 */     return (block_below != null && block_below.isTopFlatAndSolid(block_below_metadata));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 125 */     return isLegalAt(world, x, y, z, 0) ? 0 : 2;
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
/*     */   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 161 */     int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
/* 162 */     boolean var6 = ((var5 & 0x4) == 4);
/* 163 */     boolean var7 = ((var5 & 0x2) == 2);
/*     */     
/* 165 */     if (!var7) {
/*     */       
/* 167 */       setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, 0.09375D, 1.0D);
/*     */     }
/* 169 */     else if (!var6) {
/*     */       
/* 171 */       setBlockBoundsForCurrentThread(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
/*     */     }
/*     */     else {
/*     */       
/* 175 */       setBlockBoundsForCurrentThread(0.0D, 0.0625D, 0.0D, 1.0D, 0.15625D, 1.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
/* 184 */     int var5 = par1World.isBlockTopFlatAndSolid(par2, par3 - 1, par4) ? 0 : 2;
/* 185 */     par1World.setBlockMetadataWithNotify(par2, par3, par4, var5, 3);
/* 186 */     func_72149_e(par1World, par2, par3, par4, var5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
/* 196 */     func_72149_e(par1World, par2, par3, par4, par6 | 0x1);
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
/*     */   private void func_72149_e(World par1World, int par2, int par3, int par4, int par5) {
/* 215 */     int var6 = 0;
/*     */     
/* 217 */     while (var6 < 2) {
/*     */       
/* 219 */       int var7 = 1;
/*     */ 
/*     */ 
/*     */       
/* 223 */       while (var7 < 42) {
/*     */         
/* 225 */         int var8 = par2 + Direction.offsetX[var6] * var7;
/* 226 */         int var9 = par4 + Direction.offsetZ[var6] * var7;
/* 227 */         int var10 = par1World.getBlockId(var8, par3, var9);
/*     */         
/* 229 */         if (var10 == Block.tripWireSource.blockID) {
/*     */           
/* 231 */           int var11 = par1World.getBlockMetadata(var8, par3, var9) & 0x3;
/*     */           
/* 233 */           if (var11 == Direction.rotateOpposite[var6])
/*     */           {
/* 235 */             Block.tripWireSource.func_72143_a(par1World, var8, par3, var9, var10, par1World.getBlockMetadata(var8, par3, var9), true, var7, par5); } 
/*     */           break;
/*     */         } 
/* 238 */         if (var10 == Block.tripWire.blockID)
/*     */         {
/* 240 */           var7++;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 245 */       var6++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
/* 256 */     if (!par1World.isRemote)
/*     */     {
/* 258 */       if ((par1World.getBlockMetadata(par2, par3, par4) & 0x1) != 1)
/*     */       {
/* 260 */         updateTripWireState(par1World, par2, par3, par4);
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
/*     */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/* 288 */     if (super.updateTick(world, x, y, z, random)) {
/* 289 */       return true;
/*     */     }
/* 291 */     if (isPulled(world.getBlockMetadata(x, y, z))) {
/* 292 */       return updateTripWireState(world, x, y, z);
/*     */     }
/* 294 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean updateTripWireState(World par1World, int par2, int par3, int par4) {
/* 300 */     boolean changed_state = false;
/*     */     
/* 302 */     int var5 = par1World.getBlockMetadata(par2, par3, par4);
/* 303 */     boolean var6 = ((var5 & 0x1) == 1);
/* 304 */     boolean var7 = false;
/*     */     
/* 306 */     int index = Minecraft.getThreadIndex();
/*     */     
/* 308 */     List var8 = par1World.getEntitiesWithinAABBExcludingEntity((Entity)null, AxisAlignedBB.getAABBPool().getAABB(par2 + this.minX[index], par3 + this.minY[index], par4 + this.minZ[index], par2 + this.maxX[index], par3 + this.maxY[index], par4 + this.maxZ[index]));
/*     */     
/* 310 */     if (!var8.isEmpty()) {
/*     */       
/* 312 */       Iterator<Entity> var9 = var8.iterator();
/*     */       
/* 314 */       while (var9.hasNext()) {
/*     */         
/* 316 */         Entity var10 = var9.next();
/*     */         
/* 318 */         if (!var10.doesEntityNotTriggerPressurePlate()) {
/*     */           
/* 320 */           var7 = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 326 */     if (var7 && !var6)
/*     */     {
/* 328 */       var5 |= 0x1;
/*     */     }
/*     */     
/* 331 */     if (!var7 && var6)
/*     */     {
/* 333 */       var5 &= 0xFFFFFFFE;
/*     */     }
/*     */     
/* 336 */     if (var7 != var6) {
/*     */       
/* 338 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, var5, 3);
/* 339 */       changed_state = true;
/*     */       
/* 341 */       func_72149_e(par1World, par2, par3, par4, var5);
/*     */     } 
/*     */     
/* 344 */     if (var7)
/*     */     {
/* 346 */       par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, tickRate(par1World));
/*     */     }
/*     */     
/* 349 */     return changed_state;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_72148_a(IBlockAccess par0IBlockAccess, int par1, int par2, int par3, int par4, int par5) {
/* 354 */     int var6 = par1 + Direction.offsetX[par5];
/* 355 */     int var8 = par3 + Direction.offsetZ[par5];
/* 356 */     int var9 = par0IBlockAccess.getBlockId(var6, par2, var8);
/* 357 */     boolean var10 = ((par4 & 0x2) == 2);
/*     */ 
/*     */     
/* 360 */     if (var9 == Block.tripWireSource.blockID) {
/*     */       
/* 362 */       int var11 = par0IBlockAccess.getBlockMetadata(var6, par2, var8);
/* 363 */       int var13 = var11 & 0x3;
/* 364 */       return (var13 == Direction.rotateOpposite[par5]);
/*     */     } 
/* 366 */     if (var9 == Block.tripWire.blockID) {
/*     */       
/* 368 */       int var11 = par0IBlockAccess.getBlockMetadata(var6, par2, var8);
/* 369 */       boolean var12 = ((var11 & 0x2) == 2);
/* 370 */       return (var10 == var12);
/*     */     } 
/*     */ 
/*     */     
/* 374 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCarried() {
/* 380 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 387 */     return dropBlockAsEntityItem(info, Item.silk);
/*     */   }
/*     */ 
/*     */   
/*     */   public void makeSoundWhenPlaced(World world, int x, int y, int z, int metadata) {
/* 392 */     if (this.stepSound != null) {
/* 393 */       world.playSoundAtBlock(x, y, z, this.stepSound.getPlaceSound(), this.stepSound.getVolume() / 4.0F, this.stepSound.getPitch() * 0.8F);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isSolid(boolean[] is_solid, int metadata) {
/* 398 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 403 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockTripWire.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */