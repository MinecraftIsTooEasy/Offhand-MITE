/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockFalling
/*     */   extends Block
/*     */ {
/*     */   public static boolean fallInstantly;
/*     */   
/*     */   public BlockFalling(int par1, Material material, BlockConstants constants) {
/*  14 */     super(par1, material, constants);
/*  15 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
/*  20 */     par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, tickRate(par1World));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/*  25 */     world.scheduleBlockUpdate(x, y, z, this.blockID, tickRate(world));
/*     */     
/*  27 */     return (Minecraft.allow_new_sand_physics && super.onNeighborBlockChange(world, x, y, z, neighbor_block_id));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/*  32 */     return tryToFall(par1World, par2, par3, par4);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity) {
/*  37 */     if (!par1World.isRemote) {
/*  38 */       tryToFall(par1World, par2, par3, par4);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final boolean tryToFall(World par1World, int par2, int par3, int par4) {
/*  73 */     int metadata = par1World.getBlockMetadata(par2, par3, par4);
/*     */ 
/*     */     
/*  76 */     if (canFallDownTo(par1World, par2, par3 - 1, par4, metadata) && par3 >= 0) {
/*     */       
/*  78 */       byte var8 = 32;
/*     */       
/*  80 */       if (!fallInstantly && par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8)) {
/*     */         
/*  82 */         if (!par1World.isRemote)
/*     */         {
/*  84 */           EntityFallingSand var9 = new EntityFallingSand(par1World, (par2 + 0.5F), (par3 + 0.5F), (par4 + 0.5F), this.blockID, par1World.getBlockMetadata(par2, par3, par4));
/*     */           
/*  86 */           onStartFalling(par1World, par2, par3, par4, var9);
/*  87 */           par1World.spawnEntityInWorld(var9);
/*     */           
/*  89 */           Block block = Block.blocksList[par1World.getBlockId(par2, par3 + 1, par4)];
/*     */           
/*  91 */           if (block instanceof BlockUnderminable) {
/*     */             
/*  93 */             BlockUnderminable block_underminable = (BlockUnderminable)block;
/*  94 */             block_underminable.scheduleUndermine(par1World, par2, par3 + 1, par4);
/*     */           } 
/*     */           
/*  97 */           return true;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 102 */         par1World.setBlockToAir(par2, par3, par4);
/*     */ 
/*     */         
/* 105 */         while (canFallDownTo(par1World, par2, par3 - 1, par4, metadata) && par3 > 0)
/*     */         {
/* 107 */           par3--;
/*     */         }
/*     */         
/* 110 */         if (par3 > 0)
/*     */         {
/*     */           
/* 113 */           par1World.setBlock(par2, par3, par4, this.blockID, metadata, 2);
/*     */         }
/*     */         
/* 116 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 120 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onStartFalling(World world, int x, int y, int z, EntityFallingSand entity_falling_sand) {}
/*     */   
/*     */   public int tickRate(World par1World) {
/* 127 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canFallDownTo(World world, int x, int y, int z, int metadata) {
/* 133 */     Block block_below = world.getBlock(x, y, z);
/* 134 */     int block_below_metadata = world.getBlockMetadata(x, y, z);
/*     */ 
/*     */ 
/*     */     
/* 138 */     return (block_below == null || !block_below.isSolid(block_below_metadata) || EntityFallingSand.canDislodgeOrCrushBlockAt(world, this, metadata, x, y, z));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onFinishFalling(World par1World, int par2, int par3, int par4, int par5, EntityFallingSand entity_falling_sand) {
/* 143 */     if (usesNewSandPhysics()) {
/* 144 */       checkIfNotLegal(par1World, par2, par3, par4);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isLegalAt(World world, int x, int y, int z, int metadata) {
/* 149 */     if (usesNewSandPhysics() && (!canFallDownTo(world, x, y - 1, z, metadata) || y < 0))
/*     */     {
/* 151 */       for (int dx = -1; dx <= 1; dx++) {
/*     */         
/* 153 */         for (int dz = -1; dz <= 1; dz++) {
/*     */           
/* 155 */           if (dx != 0 && dz != 0) {
/*     */             continue;
/*     */           }
/* 158 */           if (dx != 0 || dz != 0)
/*     */           {
/*     */             
/* 161 */             if (world.doesBlockBlockFluids(x + dx, y, z + dz)) {
/*     */               continue;
/*     */             }
/*     */           }
/* 165 */           if (!world.isBlockTopFlatAndSolid(x + dx, y - 1, z + dz))
/* 166 */             return false; 
/*     */           continue;
/*     */         } 
/*     */       } 
/*     */     }
/* 171 */     return super.isLegalAt(world, x, y, z, metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onNotLegal(World world, int x, int y, int z, int metadata) {
/* 176 */     if (!usesNewSandPhysics()) {
/* 177 */       return super.onNotLegal(world, x, y, z, metadata);
/*     */     }
/* 179 */     if (world.isRemote) {
/* 180 */       Minecraft.setErrorMessage("onNotLegal: not meant to be called on client");
/*     */     }
/* 182 */     world.destroyBlock((new BlockBreakInfo(world, x, y, z)).setWasNotLegal(), true, true);
/*     */     
/* 184 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
/* 189 */     tryToFall(world, x, y, z);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockFalling.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */