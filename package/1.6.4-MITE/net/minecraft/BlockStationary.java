/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BlockStationary
/*     */   extends BlockFluid
/*     */ {
/*     */   protected BlockStationary(int par1, Material par2Material) {
/*  11 */     super(par1, par2Material);
/*  12 */     setTickRandomly(false);
/*     */     
/*  14 */     if (par2Material == Material.lava)
/*     */     {
/*  16 */       setTickRandomly(true);
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
/*     */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/*  41 */     if (super.onNeighborBlockChange(world, x, y, z, neighbor_block_id)) {
/*  42 */       return true;
/*     */     }
/*  44 */     if (world.getBlockId(x, y, z) == this.blockID) {
/*     */       
/*  46 */       setNotStationary(world, x, y, z);
/*  47 */       return true;
/*     */     } 
/*     */     
/*  50 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setNotStationary(World par1World, int par2, int par3, int par4) {
/*  58 */     int var5 = par1World.getBlockMetadata(par2, par3, par4);
/*  59 */     par1World.setBlock(par2, par3, par4, this.blockID - 1, var5, 2);
/*  60 */     par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID - 1, tickRate(par1World));
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
/*     */   public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/*  81 */     if (this.blockMaterial == Material.lava) {
/*     */ 
/*     */ 
/*     */       
/*  85 */       int rarity_of_spawning_fire_elemental = par1World.hasSkylight() ? (16 + par3) : 16;
/*     */       
/*  87 */       if (par1World.rand.nextInt(rarity_of_spawning_fire_elemental) == 0 && par1World.getEntitiesWithinAABB(EntityFireElemental.class, par1World.getBoundingBoxFromPool(par2, par3, par4).expand(16.0D, 16.0D, 16.0D)).size() < 2) {
/*     */         
/*  89 */         boolean spawn_fire_elemental = false;
/*     */         
/*  91 */         if (par1World.isTheNether() && par1World.getBlockMaterial(par2, par3 - 1, par4) != Material.lava && par1World.getBlockMaterial(par2, par3, par4) == Material.lava && par1World.isAirBlock(par2, par3 + 1, par4) && par1World.rand.nextInt(4) == 0)
/*     */         {
/*  93 */           if (!BlockFluid.isFullLavaBlock(par1World, par2, par3, par4, false)) {
/*  94 */             spawn_fire_elemental = par1World.isPlayerNearby((par2 + 0.5F), (par3 + 0.5F), (par4 + 0.5F), 64.0D);
/*  95 */           } else if (par1World.rand.nextInt(4) == 0) {
/*  96 */             spawn_fire_elemental = par1World.isPlayerNearby((par2 + 0.5F), (par3 + 0.5F), (par4 + 0.5F), 16.0D);
/*     */           } 
/*     */         }
/*  99 */         if (!spawn_fire_elemental && par1World.rand.nextInt(16) == 0) {
/* 100 */           spawn_fire_elemental = (BlockFluid.isFullLavaBlock(par1World, par2, par3 + 1, par4, true) && par1World.isAirBlock(par2, par3 + 2, par4) && par1World.isAirBlock(par2, par3 + 3, par4) && par1World.isPlayerNearby((par2 + 0.5F), (par3 + 0.5F), (par4 + 0.5F), 16.0D));
/*     */         }
/* 102 */         if (spawn_fire_elemental) {
/*     */           
/* 104 */           EntityFireElemental fire_elemental = new EntityFireElemental(par1World);
/* 105 */           fire_elemental.setLocationAndAngles((par2 + 0.5F), (par3 + 0.1F), (par4 + 0.5F), 0.0F, 0.0F);
/*     */           
/* 107 */           par1World.spawnEntityInWorld(fire_elemental);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 113 */       int var6 = par5Random.nextInt(3);
/*     */       
/*     */       int var7;
/*     */       
/* 117 */       for (var7 = 0; var7 < var6; var7++) {
/*     */         
/* 119 */         par2 += par5Random.nextInt(3) - 1;
/* 120 */         par3++;
/* 121 */         par4 += par5Random.nextInt(3) - 1;
/* 122 */         int var8 = par1World.getBlockId(par2, par3, par4);
/*     */         
/* 124 */         if (var8 == 0) {
/*     */           
/* 126 */           this; if (!isFlammable(par1World, par2 - 1, par3, par4)) { this; if (!isFlammable(par1World, par2 + 1, par3, par4)) { this; if (!isFlammable(par1World, par2, par3, par4 - 1)) { this; if (!isFlammable(par1World, par2, par3, par4 + 1)) { this; if (!isFlammable(par1World, par2, par3 - 1, par4)) { this; if (isFlammable(par1World, par2, par3 + 1, par4))
/*     */                     
/* 128 */                     { par1World.setBlock(par2, par3, par4, Block.fire.blockID);
/*     */                       
/* 130 */                       return false; }  } else { par1World.setBlock(par2, par3, par4, Block.fire.blockID); return false; }  } else { par1World.setBlock(par2, par3, par4, Block.fire.blockID); return false; }  } else { par1World.setBlock(par2, par3, par4, Block.fire.blockID); return false; }  } else { par1World.setBlock(par2, par3, par4, Block.fire.blockID); return false; }  } else { par1World.setBlock(par2, par3, par4, Block.fire.blockID); return false; }
/*     */ 
/*     */         
/*     */         }
/* 134 */         else if (getBlock(var8).isSolid(par1World, par2, par3, par4)) {
/*     */ 
/*     */           
/* 137 */           return false;
/*     */         } 
/*     */       } 
/*     */       
/* 141 */       if (var6 == 0) {
/*     */         
/* 143 */         var7 = par2;
/* 144 */         int var8 = par4;
/*     */         
/* 146 */         for (int var9 = 0; var9 < 3; var9++) {
/*     */           
/* 148 */           par2 = var7 + par5Random.nextInt(3) - 1;
/* 149 */           par4 = var8 + par5Random.nextInt(3) - 1;
/*     */           
/* 151 */           if (par1World.isAirBlock(par2, par3 + 1, par4)) { this; if (isFlammable(par1World, par2, par3, par4))
/*     */             {
/* 153 */               par1World.setBlock(par2, par3 + 1, par4, Block.fire.blockID);
/*     */             } }
/*     */         
/*     */         } 
/*     */       } 
/*     */     } 
/* 159 */     return false;
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
/*     */   public static boolean isFlammable(World par1World, int par2, int par3, int par4) {
/* 171 */     Block block = par1World.getBlock(par2, par3, par4);
/*     */     
/* 173 */     if (block == null || block.blockMaterial == Material.netherrack) {
/* 174 */       return false;
/*     */     }
/* 176 */     return block.blockMaterial.canCatchFire();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 181 */     return "still";
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockStationary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */