/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenMinable
/*     */   extends WorldGenerator
/*     */ {
/*     */   private int minableBlockId;
/*     */   private int minable_block_metadata;
/*     */   private int numberOfBlocks;
/*     */   private int blockToReplace;
/*     */   private boolean vein_size_increases_with_depth;
/*     */   
/*     */   public WorldGenMinable(int par1, int par2) {
/*  22 */     this(par1, par2, Block.stone.blockID);
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldGenMinable(int par1, int par2, int par3) {
/*  27 */     this.minableBlockId = par1;
/*  28 */     this.numberOfBlocks = par2;
/*  29 */     this.blockToReplace = par3;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldGenMinable setMinableBlockMetadata(int metadata) {
/*  89 */     this.minable_block_metadata = metadata;
/*  90 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinableBlockId() {
/* 100 */     return this.minableBlockId;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
/* 105 */     return generate(par1World, par2Random, par3, par4, par5, false);
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
/*     */   public int growVein(World world, Random rand, int blocks_to_grow, int x, int y, int z, boolean must_be_supported, boolean is_dirt) {
/* 140 */     if (blocks_to_grow < 1 || !world.blockExists(x, y, z) || world.getBlockId(x, y, z) != this.blockToReplace) {
/* 141 */       return 0;
/*     */     }
/* 143 */     if (must_be_supported && (y < 1 || world.isAirOrPassableBlock(x, y - 1, z, true))) {
/* 144 */       return 0;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 149 */     if (is_dirt && world.canBlockSeeTheSky(x, y + 1, z)) {
/*     */       
/* 151 */       BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
/*     */       
/* 153 */       world.setBlock(x, y, z, (biome == BiomeGenBase.desert || biome == BiomeGenBase.desertHills) ? Block.sand.blockID : Block.grass.blockID, 0, 2);
/*     */     }
/*     */     else {
/*     */       
/* 157 */       world.setBlock(x, y, z, this.minableBlockId, this.minable_block_metadata, 2);
/*     */     } 
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
/* 169 */     int ore_blocks_grown = 1;
/*     */     
/* 171 */     for (int attempts = 0; attempts < 16; attempts++) {
/*     */       
/* 173 */       int dx = 0;
/* 174 */       int dy = 0;
/* 175 */       int dz = 0;
/*     */       
/* 177 */       int axis = rand.nextInt(3);
/*     */       
/* 179 */       if (axis == 0) {
/* 180 */         dx = (rand.nextInt(2) == 0) ? -1 : 1;
/* 181 */       } else if (axis == 1) {
/* 182 */         dy = (rand.nextInt(2) == 0) ? -1 : 1;
/*     */       } else {
/* 184 */         dz = (rand.nextInt(2) == 0) ? -1 : 1;
/*     */       } 
/* 186 */       ore_blocks_grown += growVein(world, rand, blocks_to_grow - ore_blocks_grown, x + dx, y + dy, z + dz, must_be_supported, is_dirt);
/*     */       
/* 188 */       if (ore_blocks_grown == blocks_to_grow) {
/*     */         break;
/*     */       }
/*     */     } 
/* 192 */     return ore_blocks_grown;
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
/*     */   public boolean generate(World world, Random rand, int x, int y, int z, boolean vein_size_increases_with_depth) {
/* 424 */     if (!world.blockExists(x, y, z) || world.getBlockId(x, y, z) != this.blockToReplace) {
/* 425 */       return false;
/*     */     }
/* 427 */     int block_id = this.minableBlockId;
/* 428 */     int vein_size = this.numberOfBlocks;
/*     */     
/* 430 */     Block block = Block.blocksList[block_id];
/*     */     
/* 432 */     float scale = 1.0F;
/*     */     
/* 434 */     while (rand.nextInt(2) == 0) {
/* 435 */       scale = (float)(scale * (rand.nextFloat() * 0.6D + 0.699999988079071D));
/*     */     }
/* 437 */     scale = Math.min(scale, 4.0F);
/*     */     
/* 439 */     if (vein_size_increases_with_depth) {
/*     */       
/* 441 */       int range = getMaxVeinHeight(world) - getMinVeinHeight(world);
/*     */       
/* 443 */       if (range > 16) {
/*     */         
/* 445 */         float relative_height = (y - getMinVeinHeight(world)) / range;
/* 446 */         scale *= 1.0F - relative_height + 0.5F;
/*     */       } 
/*     */     } 
/*     */     
/* 450 */     if (vein_size * scale <= 3.0F && rand.nextInt(2) == 0)
/*     */     {
/* 452 */       if (rand.nextInt(2) == 0) {
/* 453 */         scale *= 2.0F;
/*     */       } else {
/* 455 */         return true;
/*     */       } 
/*     */     }
/* 458 */     vein_size = (int)(vein_size * scale);
/*     */     
/* 460 */     if (vein_size < 1)
/*     */     {
/* 462 */       return true;
/*     */     }
/* 464 */     if (vein_size == 1) {
/*     */       
/* 466 */       if (rand.nextInt(3) == 0) {
/* 467 */         vein_size = 3;
/*     */       } else {
/* 469 */         return true;
/*     */       } 
/* 471 */     } else if (vein_size == 2) {
/*     */       
/* 473 */       if (rand.nextInt(3) != 0) {
/* 474 */         vein_size = 3;
/*     */       } else {
/* 476 */         return true;
/*     */       } 
/* 478 */     } else if (vein_size > 32) {
/*     */       
/* 480 */       vein_size = 32;
/*     */     } 
/*     */     
/* 483 */     boolean must_be_supported = (world.isUnderworld() && getMinableBlockId() == Block.gravel.blockID);
/* 484 */     boolean is_dirt = (this.minableBlockId == Block.dirt.blockID);
/*     */     
/* 486 */     int number_of_blocks_placed = growVein(world, rand, vein_size, x, y, z, must_be_supported, is_dirt);
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
/* 514 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxVeinHeight(World world) {
/* 519 */     if (world.isUnderworld()) {
/* 520 */       return 255;
/*     */     }
/* 522 */     Block block = Block.blocksList[this.minableBlockId];
/*     */     
/* 524 */     if (block == Block.dirt)
/* 525 */       return 128; 
/* 526 */     if (block == Block.gravel)
/* 527 */       return 128; 
/* 528 */     if (block == Block.oreCoal)
/* 529 */       return 96; 
/* 530 */     if (block == Block.oreCopper)
/* 531 */       return 128; 
/* 532 */     if (block == Block.oreSilver)
/* 533 */       return 96; 
/* 534 */     if (block == Block.oreGold)
/* 535 */       return 48; 
/* 536 */     if (block == Block.oreIron)
/* 537 */       return 64; 
/* 538 */     if (block == Block.oreMithril)
/* 539 */       return 32; 
/* 540 */     if (block == Block.oreAdamantium || block == Block.silverfish)
/* 541 */       return 24; 
/* 542 */     if (block == Block.oreRedstone)
/* 543 */       return 24; 
/* 544 */     if (block == Block.oreDiamond)
/* 545 */       return 32; 
/* 546 */     if (block == Block.oreLapis) {
/* 547 */       return 40;
/*     */     }
/* 549 */     Minecraft.setErrorMessage("WorldGenMinable: unknown ore id " + block.blockID);
/* 550 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinVeinHeight(World world) {
/* 555 */     if (world.isUnderworld()) {
/* 556 */       return 0;
/*     */     }
/* 558 */     Block block = Block.blocksList[this.minableBlockId];
/*     */     
/* 560 */     if (block == Block.dirt)
/* 561 */       return 32; 
/* 562 */     if (block == Block.gravel)
/* 563 */       return 24; 
/* 564 */     if (block == Block.oreCoal)
/* 565 */       return 16; 
/* 566 */     if (block == Block.oreCopper)
/* 567 */       return 0; 
/* 568 */     if (block == Block.oreSilver)
/* 569 */       return 0; 
/* 570 */     if (block == Block.oreGold)
/* 571 */       return 0; 
/* 572 */     if (block == Block.oreIron)
/* 573 */       return 0; 
/* 574 */     if (block == Block.oreMithril)
/* 575 */       return 0; 
/* 576 */     if (block == Block.oreAdamantium || block == Block.silverfish)
/* 577 */       return 0; 
/* 578 */     if (block == Block.oreRedstone)
/* 579 */       return 0; 
/* 580 */     if (block == Block.oreDiamond)
/* 581 */       return 0; 
/* 582 */     if (block == Block.oreLapis) {
/* 583 */       return 8;
/*     */     }
/* 585 */     Minecraft.setErrorMessage("WorldGenMinable: unknown ore id " + block.blockID);
/* 586 */     return -1;
/*     */   }
/*     */   
/*     */   public int getRandomVeinHeight(World world, Random rand) {
/*     */     float relative_height;
/* 591 */     Block block = Block.blocksList[this.minableBlockId];
/*     */     
/* 593 */     if (world.isUnderworld()) {
/*     */       
/* 595 */       if (world.underworld_y_offset != 0) {
/*     */         
/* 597 */         if (block == Block.oreAdamantium) {
/* 598 */           return rand.nextInt(16 + world.underworld_y_offset);
/*     */         }
/* 600 */         if (block instanceof BlockOre && rand.nextFloat() < 0.75F) {
/* 601 */           return rand.nextInt(16 + world.underworld_y_offset);
/*     */         }
/*     */       } 
/* 604 */       return rand.nextInt(256);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 609 */     if (block == Block.dirt) {
/*     */       
/*     */       do
/*     */       {
/* 613 */         relative_height = rand.nextFloat();
/*     */       }
/* 615 */       while (relative_height <= rand.nextFloat());
/*     */ 
/*     */     
/*     */     }
/* 619 */     else if (block == Block.gravel) {
/*     */       
/*     */       do
/*     */       {
/* 623 */         relative_height = rand.nextFloat();
/*     */       }
/* 625 */       while (relative_height <= rand.nextFloat());
/*     */ 
/*     */     
/*     */     }
/* 629 */     else if (block == Block.oreCoal) {
/*     */       
/*     */       do
/*     */       {
/* 633 */         relative_height = rand.nextFloat();
/*     */       }
/* 635 */       while (relative_height <= rand.nextFloat());
/*     */ 
/*     */     
/*     */     }
/* 639 */     else if (block == Block.oreCopper) {
/*     */       
/* 641 */       if (rand.nextInt(2) == 0) {
/*     */         
/* 643 */         relative_height = rand.nextFloat() * 0.6F + 0.4F;
/*     */       } else {
/*     */         
/*     */         do
/*     */         {
/*     */           
/* 649 */           relative_height = rand.nextFloat();
/*     */         }
/* 651 */         while (relative_height >= rand.nextFloat());
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 656 */     else if (block == Block.oreSilver) {
/*     */       
/*     */       do
/*     */       {
/* 660 */         relative_height = rand.nextFloat();
/*     */       }
/* 662 */       while (relative_height >= rand.nextFloat());
/*     */ 
/*     */     
/*     */     }
/* 666 */     else if (block == Block.oreGold) {
/*     */       
/*     */       do
/*     */       {
/* 670 */         relative_height = rand.nextFloat();
/*     */       }
/* 672 */       while (relative_height >= rand.nextFloat());
/*     */ 
/*     */     
/*     */     }
/* 676 */     else if (block == Block.oreIron) {
/*     */       
/*     */       do
/*     */       {
/* 680 */         relative_height = rand.nextFloat();
/*     */       }
/* 682 */       while (relative_height >= rand.nextFloat());
/*     */ 
/*     */     
/*     */     }
/* 686 */     else if (block == Block.oreMithril) {
/*     */       
/*     */       do
/*     */       {
/* 690 */         relative_height = rand.nextFloat();
/*     */       }
/* 692 */       while (relative_height >= rand.nextFloat());
/*     */ 
/*     */     
/*     */     }
/* 696 */     else if (block == Block.oreAdamantium || block == Block.silverfish) {
/*     */       
/*     */       do
/*     */       {
/* 700 */         relative_height = rand.nextFloat();
/*     */       }
/* 702 */       while (relative_height >= rand.nextFloat());
/*     */ 
/*     */     
/*     */     }
/* 706 */     else if (block == Block.oreRedstone) {
/*     */       
/*     */       do
/*     */       {
/* 710 */         relative_height = rand.nextFloat();
/*     */       }
/* 712 */       while (relative_height >= rand.nextFloat());
/*     */ 
/*     */     
/*     */     }
/* 716 */     else if (block == Block.oreDiamond) {
/*     */       
/*     */       do
/*     */       {
/* 720 */         relative_height = rand.nextFloat();
/*     */       }
/* 722 */       while (relative_height >= rand.nextFloat());
/*     */ 
/*     */     
/*     */     }
/* 726 */     else if (block == Block.oreLapis) {
/*     */       
/* 728 */       relative_height = (rand.nextFloat() + rand.nextFloat()) / 2.0F;
/*     */     }
/*     */     else {
/*     */       
/* 732 */       Minecraft.setErrorMessage("WorldGenMinable: unknown ore id " + this.minableBlockId);
/* 733 */       return -1;
/*     */     } 
/*     */     
/* 736 */     int min_height = getMinVeinHeight(world);
/* 737 */     int height_range = getMaxVeinHeight(world) - min_height + 1;
/*     */     
/* 739 */     return min_height + (int)(relative_height * height_range);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenMinable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */