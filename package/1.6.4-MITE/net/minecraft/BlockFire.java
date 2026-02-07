/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class BlockFire
/*     */   extends Block
/*     */ {
/*  10 */   private int[] chanceToEncourageFire = new int[256];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  16 */   private int[] abilityToCatchFire = new int[256];
/*     */   
/*     */   private Icon[] iconArray;
/*  19 */   private static AxisAlignedBB bounding_box = new AxisAlignedBB();
/*     */ 
/*     */ 
/*     */   
/*     */   protected BlockFire(int par1) {
/*  24 */     super(par1, Material.fire, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
/*  25 */     setTickRandomly(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  30 */     return "All bits used to track fire intensity";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  35 */     return (metadata >= 0 && metadata < 16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initializeBlock() {
/*  44 */     super.initializeBlock();
/*     */     
/*  46 */     setBurnRate(Block.planks.blockID, 5, 20);
/*  47 */     setBurnRate(Block.woodDoubleSlab.blockID, 5, 20);
/*  48 */     setBurnRate(Block.woodSingleSlab.blockID, 5, 20);
/*  49 */     setBurnRate(Block.fence.blockID, 5, 20);
/*  50 */     setBurnRate(Block.stairsWoodOak.blockID, 5, 20);
/*  51 */     setBurnRate(Block.stairsWoodBirch.blockID, 5, 20);
/*  52 */     setBurnRate(Block.stairsWoodSpruce.blockID, 5, 20);
/*  53 */     setBurnRate(Block.stairsWoodJungle.blockID, 5, 20);
/*  54 */     setBurnRate(Block.wood.blockID, 5, 5);
/*  55 */     setBurnRate(Block.leaves.blockID, 30, 60);
/*  56 */     setBurnRate(Block.bookShelf.blockID, 30, 20);
/*  57 */     setBurnRate(Block.tnt.blockID, 15, 100);
/*  58 */     setBurnRate(Block.tallGrass.blockID, 60, 100);
/*  59 */     setBurnRate(Block.cloth.blockID, 30, 60);
/*  60 */     setBurnRate(Block.vine.blockID, 15, 100);
/*  61 */     setBurnRate(Block.coalBlock.blockID, 5, 5);
/*     */     
/*  63 */     setBurnRate(Block.hay.blockID, 60, 100);
/*     */     
/*  65 */     setBurnRate(Block.web.blockID, 60, 100);
/*  66 */     setBurnRate(Block.crops.blockID, 60, 100);
/*  67 */     setBurnRate(Block.reed.blockID, 30, 50);
/*  68 */     setBurnRate(Block.deadBush.blockID, 60, 100);
/*  69 */     setBurnRate(Block.bush.blockID, 30, 50);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setBurnRate(int par1, int par2, int par3) {
/*  79 */     this.chanceToEncourageFire[par1] = par2;
/*  80 */     this.abilityToCatchFire[par1] = par3;
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
/*     */   public int getRenderType() {
/* 114 */     return 3;
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
/*     */   public int tickRate(World par1World) {
/* 130 */     return 30;
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
/*     */   public boolean tryExtinguishByItems(World world, int x, int y, int z) {
/* 148 */     List entities = world.getEntitiesWithinAABB(EntityItem.class, bounding_box.setBounds((x - 0.125F), y, (z - 0.125F), (x + 1.125F), (y + 1), (z + 1.125F)));
/*     */     
/* 150 */     int num_cookable_items = 0;
/*     */     
/* 152 */     Iterator<EntityItem> i = entities.iterator();
/*     */     
/* 154 */     while (i.hasNext()) {
/*     */       
/* 156 */       EntityItem entity_item = i.next();
/* 157 */       ItemStack item_stack = entity_item.getEntityItem();
/* 158 */       Item item = (item_stack == null) ? null : item_stack.getItem();
/*     */       
/* 160 */       if (item instanceof ItemFood) {
/*     */         
/* 162 */         ItemFood item_food = (ItemFood)item;
/*     */         
/* 164 */         if (item_food.getCookedItem() != null) {
/* 165 */           num_cookable_items += item_stack.stackSize;
/*     */         }
/*     */       } 
/*     */     } 
/* 169 */     if (num_cookable_items < 2) {
/* 170 */       return false;
/*     */     }
/* 172 */     if (num_cookable_items > 15) {
/* 173 */       return world.extinguishFire(null, x, y - 1, z, EnumFace.TOP);
/*     */     }
/* 175 */     float chance_of_extinguishing = 0.01F * (float)Math.pow(2.0D, num_cookable_items);
/*     */     
/* 177 */     if (chance_of_extinguishing >= 1.0F || world.rand.nextFloat() < chance_of_extinguishing) {
/* 178 */       return world.extinguishFire(null, x, y - 1, z, EnumFace.TOP);
/*     */     }
/* 180 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 189 */     if (super.updateTick(par1World, par2, par3, par4, par5Random)) {
/* 190 */       return true;
/*     */     }
/* 192 */     boolean changed_state = false;
/*     */     
/* 194 */     if (par1World.getGameRules().getGameRuleBooleanValue("doFireTick")) {
/*     */       
/* 196 */       boolean var6 = (par1World.getBlockId(par2, par3 - 1, par4) == Block.netherrack.blockID);
/*     */       
/* 198 */       if (par1World.provider instanceof WorldProviderEnd && par1World.getBlockId(par2, par3 - 1, par4) == Block.bedrock.blockID)
/*     */       {
/* 200 */         var6 = true;
/*     */       }
/*     */       
/* 203 */       int var7 = par1World.getBlockMetadata(par2, par3, par4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 212 */       if (!var6 && par1World.isPrecipitating(true) && (par1World.canLightningStrikeAt(par2, par3, par4) || par1World.canLightningStrikeAt(par2 - 1, par3, par4) || par1World.canLightningStrikeAt(par2 + 1, par3, par4) || par1World.canLightningStrikeAt(par2, par3, par4 - 1) || par1World.canLightningStrikeAt(par2, par3, par4 + 1))) {
/*     */         
/* 214 */         par1World.setBlockToAir(par2, par3, par4);
/* 215 */         changed_state = true;
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 221 */         if (var7 < 15) {
/*     */           
/* 223 */           par1World.setBlockMetadataWithNotify(par2, par3, par4, var7 + par5Random.nextInt(3) / 2, 4);
/* 224 */           changed_state = true;
/*     */         } 
/*     */         
/* 227 */         par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, tickRate(par1World) + par5Random.nextInt(10));
/*     */         
/* 229 */         if (!var6 && !canNeighborBurn(par1World, par2, par3, par4)) {
/*     */           
/* 231 */           if (!par1World.isBlockTopFlatAndSolid(par2, par3 - 1, par4) || var7 > 3)
/*     */           {
/* 233 */             par1World.setBlockToAir(par2, par3, par4);
/* 234 */             changed_state = true;
/*     */           }
/*     */         
/* 237 */         } else if (!var6 && !canBlockCatchFire(par1World, par2, par3 - 1, par4) && var7 == 15 && par5Random.nextInt(4) == 0) {
/*     */           
/* 239 */           par1World.setBlockToAir(par2, par3, par4);
/* 240 */           changed_state = true;
/*     */         }
/*     */         else {
/*     */           
/* 244 */           boolean var8 = par1World.isBlockHighHumidity(par2, par3, par4);
/* 245 */           byte var9 = 0;
/*     */           
/* 247 */           if (var8)
/*     */           {
/* 249 */             var9 = -50;
/*     */           }
/*     */           
/* 252 */           tryToCatchBlockOnFire(par1World, par2 + 1, par3, par4, 300 + var9, par5Random, var7);
/* 253 */           tryToCatchBlockOnFire(par1World, par2 - 1, par3, par4, 300 + var9, par5Random, var7);
/* 254 */           tryToCatchBlockOnFire(par1World, par2, par3 - 1, par4, 250 + var9, par5Random, var7);
/* 255 */           tryToCatchBlockOnFire(par1World, par2, par3 + 1, par4, 250 + var9, par5Random, var7);
/* 256 */           tryToCatchBlockOnFire(par1World, par2, par3, par4 - 1, 300 + var9, par5Random, var7);
/* 257 */           tryToCatchBlockOnFire(par1World, par2, par3, par4 + 1, 300 + var9, par5Random, var7);
/*     */           
/* 259 */           for (int var10 = par2 - 1; var10 <= par2 + 1; var10++) {
/*     */             
/* 261 */             for (int var11 = par4 - 1; var11 <= par4 + 1; var11++) {
/*     */               
/* 263 */               for (int var12 = par3 - 1; var12 <= par3 + 4; var12++) {
/*     */                 
/* 265 */                 if (var10 != par2 || var12 != par3 || var11 != par4) {
/*     */                   
/* 267 */                   int var13 = 100;
/*     */                   
/* 269 */                   if (var12 > par3 + 1)
/*     */                   {
/* 271 */                     var13 += (var12 - par3 + 1) * 100;
/*     */                   }
/*     */                   
/* 274 */                   int var14 = getChanceOfNeighborsEncouragingFire(par1World, var10, var12, var11);
/*     */                   
/* 276 */                   if (var14 > 0) {
/*     */                     
/* 278 */                     int var15 = (var14 + 40 + par1World.difficultySetting * 7) / (var7 + 30);
/*     */                     
/* 280 */                     if (var8)
/*     */                     {
/* 282 */                       var15 /= 2;
/*     */                     }
/*     */                     
/* 285 */                     if (var15 > 0 && par5Random.nextInt(var13) <= var15 && (!par1World.isPrecipitating(true) || !par1World.canLightningStrikeAt(var10, var12, var11)) && !par1World.canLightningStrikeAt(var10 - 1, var12, par4) && !par1World.canLightningStrikeAt(var10 + 1, var12, var11) && !par1World.canLightningStrikeAt(var10, var12, var11 - 1) && !par1World.canLightningStrikeAt(var10, var12, var11 + 1)) {
/*     */                       
/* 287 */                       int var16 = var7 + par5Random.nextInt(5) / 4;
/*     */                       
/* 289 */                       if (var16 > 15)
/*     */                       {
/* 291 */                         var16 = 15;
/*     */                       }
/*     */                       
/* 294 */                       par1World.setBlock(var10, var12, var11, this.blockID, var16, 3);
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 305 */     return changed_state;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_82506_l() {
/* 310 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean tryToCatchBlockOnFire(World par1World, int par2, int par3, int par4, int par5, Random par6Random, int par7) {
/* 316 */     int var8 = this.abilityToCatchFire[par1World.getBlockId(par2, par3, par4)];
/*     */     
/* 318 */     if (par6Random.nextInt(par5) < var8) {
/*     */       
/* 320 */       boolean var9 = (par1World.getBlockId(par2, par3, par4) == Block.tnt.blockID);
/*     */       
/* 322 */       if (par6Random.nextInt(par7 + 10) < 5 && !par1World.canLightningStrikeAt(par2, par3, par4)) {
/*     */         
/* 324 */         int var10 = par7 + par6Random.nextInt(5) / 4;
/*     */         
/* 326 */         if (var10 > 15)
/*     */         {
/* 328 */           var10 = 15;
/*     */         }
/*     */         
/* 331 */         par1World.setBlock(par2, par3, par4, this.blockID, var10, 3);
/*     */       }
/*     */       else {
/*     */         
/* 335 */         par1World.setBlockToAir(par2, par3, par4);
/*     */       } 
/*     */       
/* 338 */       if (var9)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 344 */         BlockTNT.primeTnt(par1World, par2, par3, par4, 1, null);
/*     */       }
/*     */       
/* 347 */       return true;
/*     */     } 
/*     */     
/* 350 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canNeighborBurn(World par1World, int par2, int par3, int par4) {
/* 359 */     return canBlockCatchFire(par1World, par2 + 1, par3, par4) ? true : (canBlockCatchFire(par1World, par2 - 1, par3, par4) ? true : (canBlockCatchFire(par1World, par2, par3 - 1, par4) ? true : (canBlockCatchFire(par1World, par2, par3 + 1, par4) ? true : (canBlockCatchFire(par1World, par2, par3, par4 - 1) ? true : canBlockCatchFire(par1World, par2, par3, par4 + 1)))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getChanceOfNeighborsEncouragingFire(World par1World, int par2, int par3, int par4) {
/* 367 */     byte var5 = 0;
/*     */     
/* 369 */     if (!par1World.isAirBlock(par2, par3, par4))
/*     */     {
/* 371 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 375 */     int var6 = getChanceToEncourageFire(par1World, par2 + 1, par3, par4, var5);
/* 376 */     var6 = getChanceToEncourageFire(par1World, par2 - 1, par3, par4, var6);
/* 377 */     var6 = getChanceToEncourageFire(par1World, par2, par3 - 1, par4, var6);
/* 378 */     var6 = getChanceToEncourageFire(par1World, par2, par3 + 1, par4, var6);
/* 379 */     var6 = getChanceToEncourageFire(par1World, par2, par3, par4 - 1, var6);
/* 380 */     var6 = getChanceToEncourageFire(par1World, par2, par3, par4 + 1, var6);
/* 381 */     return var6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCollidable() {
/* 390 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBlockCatchFire(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 398 */     if (par1IBlockAccess.getBlockId(par2, par3, par4) == netherrack.blockID) {
/* 399 */       return true;
/*     */     }
/* 401 */     return (this.chanceToEncourageFire[par1IBlockAccess.getBlockId(par2, par3, par4)] > 0);
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
/*     */   public int getChanceToEncourageFire(World par1World, int par2, int par3, int par4, int par5) {
/* 418 */     int var6 = this.chanceToEncourageFire[par1World.getBlockId(par2, par3, par4)];
/* 419 */     return (var6 > par5) ? var6 : par5;
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
/*     */   public boolean isLegalAt(World world, int x, int y, int z, int metadata) {
/* 435 */     Block block_below = world.getBlock(x, y - 1, z);
/*     */     
/* 437 */     if (world.isTheEnd() && block_below == bedrock) {
/* 438 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 443 */     return (block_below == grass || block_below == netherrack || canNeighborBurn(world, x, y, z));
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
/*     */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
/* 464 */     if (par1World.provider.dimensionId > 0 || par1World.getBlockId(par2, par3 - 1, par4) != Block.obsidian.blockID || !Block.portal.tryToCreatePortal(par1World, par2, par3, par4))
/*     */     {
/* 466 */       if (!par1World.isBlockTopFlatAndSolid(par2, par3 - 1, par4) && !canNeighborBurn(par1World, par2, par3, par4)) {
/*     */ 
/*     */         
/* 469 */         par1World.setBlockToAir(par2, par3, par4);
/*     */       }
/*     */       else {
/*     */         
/* 473 */         par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, tickRate(par1World) + par1World.rand.nextInt(10));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 483 */     if (par5Random.nextInt(24) == 0)
/*     */     {
/* 485 */       par1World.playSound((par2 + 0.5F), (par3 + 0.5F), (par4 + 0.5F), "fire.fire", 1.0F + par5Random.nextFloat(), par5Random.nextFloat() * 0.7F + 0.3F, false);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 493 */     if (!par1World.isBlockTopFlatAndSolid(par2, par3 - 1, par4) && !Block.fire.canBlockCatchFire(par1World, par2, par3 - 1, par4)) {
/*     */       
/* 495 */       if (Block.fire.canBlockCatchFire(par1World, par2 - 1, par3, par4))
/*     */       {
/* 497 */         for (int var6 = 0; var6 < 2; var6++) {
/*     */           
/* 499 */           float var7 = par2 + par5Random.nextFloat() * 0.1F;
/* 500 */           float var8 = par3 + par5Random.nextFloat();
/* 501 */           float var9 = par4 + par5Random.nextFloat();
/*     */           
/* 503 */           par1World.spawnParticle(EnumParticle.largesmoke, var7, var8, var9, 0.0D, 0.0D, 0.0D);
/*     */         } 
/*     */       }
/*     */       
/* 507 */       if (Block.fire.canBlockCatchFire(par1World, par2 + 1, par3, par4))
/*     */       {
/* 509 */         for (int var6 = 0; var6 < 2; var6++) {
/*     */           
/* 511 */           float var7 = (par2 + 1) - par5Random.nextFloat() * 0.1F;
/* 512 */           float var8 = par3 + par5Random.nextFloat();
/* 513 */           float var9 = par4 + par5Random.nextFloat();
/*     */           
/* 515 */           par1World.spawnParticle(EnumParticle.largesmoke, var7, var8, var9, 0.0D, 0.0D, 0.0D);
/*     */         } 
/*     */       }
/*     */       
/* 519 */       if (Block.fire.canBlockCatchFire(par1World, par2, par3, par4 - 1))
/*     */       {
/* 521 */         for (int var6 = 0; var6 < 2; var6++) {
/*     */           
/* 523 */           float var7 = par2 + par5Random.nextFloat();
/* 524 */           float var8 = par3 + par5Random.nextFloat();
/* 525 */           float var9 = par4 + par5Random.nextFloat() * 0.1F;
/*     */           
/* 527 */           par1World.spawnParticle(EnumParticle.largesmoke, var7, var8, var9, 0.0D, 0.0D, 0.0D);
/*     */         } 
/*     */       }
/*     */       
/* 531 */       if (Block.fire.canBlockCatchFire(par1World, par2, par3, par4 + 1))
/*     */       {
/* 533 */         for (int var6 = 0; var6 < 2; var6++) {
/*     */           
/* 535 */           float var7 = par2 + par5Random.nextFloat();
/* 536 */           float var8 = par3 + par5Random.nextFloat();
/* 537 */           float var9 = (par4 + 1) - par5Random.nextFloat() * 0.1F;
/*     */           
/* 539 */           par1World.spawnParticle(EnumParticle.largesmoke, var7, var8, var9, 0.0D, 0.0D, 0.0D);
/*     */         } 
/*     */       }
/*     */       
/* 543 */       if (Block.fire.canBlockCatchFire(par1World, par2, par3 + 1, par4))
/*     */       {
/* 545 */         for (int var6 = 0; var6 < 2; var6++)
/*     */         {
/* 547 */           float var7 = par2 + par5Random.nextFloat();
/* 548 */           float var8 = (par3 + 1) - par5Random.nextFloat() * 0.1F;
/* 549 */           float var9 = par4 + par5Random.nextFloat();
/*     */           
/* 551 */           par1World.spawnParticle(EnumParticle.largesmoke, var7, var8, var9, 0.0D, 0.0D, 0.0D);
/*     */         }
/*     */       
/*     */       }
/*     */     } else {
/*     */       
/* 557 */       for (int var6 = 0; var6 < 3; var6++) {
/*     */         
/* 559 */         float var7 = par2 + par5Random.nextFloat();
/* 560 */         float var8 = par3 + par5Random.nextFloat() * 0.5F + 0.5F;
/* 561 */         float var9 = par4 + par5Random.nextFloat();
/*     */         
/* 563 */         par1World.spawnParticle(EnumParticle.largesmoke, var7, var8, var9, 0.0D, 0.0D, 0.0D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 574 */     this.iconArray = new Icon[] { par1IconRegister.registerIcon(getTextureName() + "_layer_0"), par1IconRegister.registerIcon(getTextureName() + "_layer_1") };
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getFireIcon(int par1) {
/* 579 */     return this.iconArray[par1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/* 587 */     return this.iconArray[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeCarried() {
/* 592 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 597 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSolid(boolean[] is_solid, int metadata) {
/* 602 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
/* 607 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean canBlockCatchFire(Block block) {
/* 613 */     return (Block.fire.abilityToCatchFire[block.blockID] > 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockFire.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */