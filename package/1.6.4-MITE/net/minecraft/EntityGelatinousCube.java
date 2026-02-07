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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class EntityGelatinousCube
/*     */   extends EntityCubic
/*     */ {
/*     */   private int block_feeding_countdown;
/*     */   private int item_feeding_countdown;
/*     */   private int data_object_id_is_feeding;
/*     */   private static final int OFFSET_BLOCK_ID = 0;
/*     */   private static final int OFFSET_BLOCK_DIMENSION = 1;
/*     */   private static final int OFFSET_BLOCK_X = 2;
/*     */   private static final int OFFSET_BLOCK_Y = 3;
/*     */   private static final int OFFSET_BLOCK_Z = 4;
/*     */   private static final int OFFSET_BLOCK_PROGRESS = 5;
/*     */   private static final int DISSOLVING_BLOCK_FIELDS = 6;
/*     */   private static final int MAX_NUM_DISSOLVING_BLOCKS = 64;
/*  31 */   private int[] dissolving_blocks_info = new int[384];
/*     */   
/*  33 */   private List extended_dissolving_blocks_info = new ArrayList();
/*     */   
/*     */   private int ticks_until_next_fizz_sound;
/*     */ 
/*     */   
/*     */   public EntityGelatinousCube(World world) {
/*  39 */     super(world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  44 */     super.entityInit();
/*     */     
/*  46 */     this.data_object_id_is_feeding = this.dataWatcher.addObject(this.dataWatcher.getNextAvailableId(), new Byte((byte)0));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean setIsFeeding(boolean is_feeding) {
/*  51 */     this.dataWatcher.updateObject(this.data_object_id_is_feeding, Byte.valueOf((byte)(is_feeding ? -1 : 0)));
/*  52 */     return is_feeding;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFeeding() {
/*  57 */     return (this.dataWatcher.getWatchableObjectByte(this.data_object_id_is_feeding) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateIsFeedingFlag() {
/*  62 */     setIsFeeding((this.block_feeding_countdown > 0 || this.item_feeding_countdown > 0));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBlockFeedingCountdownAboveZero() {
/*  67 */     return (this.block_feeding_countdown > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockFeedingCountdown(int block_feeding_countdown) {
/*  72 */     this.block_feeding_countdown = MathHelper.clamp_int(block_feeding_countdown, 0, 20);
/*  73 */     updateIsFeedingFlag();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemFeedingCountdown(int item_feeding_countdown) {
/*  78 */     this.item_feeding_countdown = MathHelper.clamp_int(item_feeding_countdown, 0, 20);
/*  79 */     updateIsFeedingFlag();
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/*  84 */     super.writeEntityToNBT(par1NBTTagCompound);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  91 */     par1NBTTagCompound.setIntArray("dissolving_blocks_info", this.dissolving_blocks_info);
/*     */     
/*  93 */     if (!this.extended_dissolving_blocks_info.isEmpty()) {
/*     */       
/*  95 */       int[] spliced = new int[this.extended_dissolving_blocks_info.size() * 6];
/*     */       
/*  97 */       int index = -1;
/*     */       
/*  99 */       Iterator<int[]> i = this.extended_dissolving_blocks_info.iterator();
/*     */       
/* 101 */       while (i.hasNext()) {
/*     */         
/* 103 */         int[] info = i.next();
/*     */         
/* 105 */         for (int offset = 0; offset < 6; offset++) {
/* 106 */           spliced[++index] = info[offset];
/*     */         }
/*     */       } 
/* 109 */       par1NBTTagCompound.setIntArray("extended_dissolving_blocks_info", spliced);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 117 */     super.readEntityFromNBT(par1NBTTagCompound);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     if (par1NBTTagCompound.hasKey("dissolving_blocks_info")) {
/*     */       
/* 126 */       this.dissolving_blocks_info = par1NBTTagCompound.getIntArray("dissolving_blocks_info");
/*     */ 
/*     */       
/* 129 */       if (this.dissolving_blocks_info.length < 384) {
/*     */         
/* 131 */         int[] temp = this.dissolving_blocks_info;
/*     */         
/* 133 */         this.dissolving_blocks_info = new int[384];
/*     */         
/* 135 */         for (int i = 0; i < temp.length; i++) {
/* 136 */           this.dissolving_blocks_info[i] = temp[i];
/*     */         }
/*     */       } 
/*     */     } 
/* 140 */     if (par1NBTTagCompound.hasKey("extended_dissolving_blocks_info")) {
/*     */       
/* 142 */       int[] spliced = par1NBTTagCompound.getIntArray("extended_dissolving_blocks_info");
/*     */       
/* 144 */       int num_infos = spliced.length / 6;
/*     */       
/* 146 */       for (int i = 0; i < num_infos; i++) {
/*     */         
/* 148 */         int base_offset = i * 6;
/*     */         
/* 150 */         int[] info = new int[6];
/*     */         
/* 152 */         info[0] = spliced[base_offset + 0];
/* 153 */         info[1] = spliced[base_offset + 1];
/* 154 */         info[2] = spliced[base_offset + 2];
/* 155 */         info[3] = spliced[base_offset + 3];
/* 156 */         info[4] = spliced[base_offset + 4];
/* 157 */         info[5] = spliced[base_offset + 5];
/*     */         
/* 159 */         this.extended_dissolving_blocks_info.add(info);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private BlockInfo[] getBlocksOccupiedWithStandardExpansion() {
/* 166 */     return getBlocksOccupied(0.01F, 0.01F, 0.01F, 0.01F, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getJumpDelay(Entity target) {
/* 171 */     if (target == null) {
/* 172 */       return this.rand.nextInt(81) + 40;
/*     */     }
/* 174 */     return 10;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateEntityActionState() {
/* 183 */     BlockInfo[] infos = getBlocksOccupiedWithStandardExpansion();
/*     */     
/* 185 */     for (int i = 0; i < infos.length; i++) {
/*     */       
/* 187 */       BlockInfo info = infos[i];
/*     */       
/* 189 */       if (info != null) {
/*     */ 
/*     */         
/* 192 */         Block block = info.block;
/*     */         
/* 194 */         int x = info.x;
/* 195 */         int y = info.y;
/* 196 */         int z = info.z;
/*     */         
/* 198 */         int dissolve_period = getDissolvePeriod(info.block, x, y, z);
/*     */         
/* 200 */         if (dissolve_period == 0 || block instanceof BlockTorch || block instanceof BlockCrops) {
/*     */ 
/*     */           
/* 203 */           if (this.worldObj.isBlockSolid(x, y, z))
/*     */           {
/*     */ 
/*     */ 
/*     */             
/* 208 */             this.worldObj.destroyBlockWithoutDroppingItem(x, y, z, isAcidic() ? EnumBlockFX.smoke_and_steam : EnumBlockFX.steam);
/*     */           }
/*     */           else
/*     */           {
/* 212 */             onCollidedWithBlock(this.worldObj, block, x, y, z);
/*     */           }
/*     */         
/*     */         }
/* 216 */         else if (dissolve_period > 0) {
/*     */           
/* 218 */           setBlockFeedingCountdown(20);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
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
/*     */     
/* 245 */     super.updateEntityActionState();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onCollidedWithBlock(World world, Block block, int x, int y, int z) {
/* 250 */     if (onServer() && block.doRenderBoundsIntersectWith(world, x, y, z, this.boundingBox)) {
/*     */       
/* 252 */       if (block instanceof BlockTorch) {
/*     */         
/* 254 */         world.destroyBlockWithoutDroppingItem(x, y, z, EnumBlockFX.smoke_and_steam);
/* 255 */         attackEntityFrom(new Damage(DamageSource.inFire, 1.0F));
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 260 */       if (dissolvesBlockInstantly(x, y, z)) {
/*     */         
/* 262 */         world.destroyBlockWithoutDroppingItem(x, y, z, isAcidic() ? EnumBlockFX.smoke_and_steam : EnumBlockFX.steam);
/*     */         return;
/*     */       } 
/* 265 */       if (block instanceof BlockCrops) {
/*     */         
/* 267 */         BlockCrops crops = (BlockCrops)block;
/* 268 */         crops.setBlighted(world, x, y, z, true);
/*     */       } 
/*     */     } 
/*     */     
/* 272 */     super.onCollidedWithBlock(world, block, x, y, z);
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
/*     */   private void setDissolvingBlock(Block block, int x, int y, int z, int dissolving_progress) {
/* 284 */     for (int i = 0; i < 64; ) {
/*     */       
/* 286 */       int base_offset = i * 6;
/*     */       
/* 288 */       if (this.dissolving_blocks_info[base_offset + 0] != 0) {
/*     */         i++; continue;
/*     */       } 
/* 291 */       this.dissolving_blocks_info[base_offset + 0] = block.blockID;
/* 292 */       this.dissolving_blocks_info[base_offset + 1] = this.worldObj.getDimensionId();
/* 293 */       this.dissolving_blocks_info[base_offset + 2] = x;
/* 294 */       this.dissolving_blocks_info[base_offset + 3] = y;
/* 295 */       this.dissolving_blocks_info[base_offset + 4] = z;
/* 296 */       this.dissolving_blocks_info[base_offset + 5] = dissolving_progress;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 301 */     Debug.setErrorMessage("setDissolvingBlock: wasn't able to add another block");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int isDissolvingBlock(Block block, int x, int y, int z) {
/* 309 */     for (int i = 0; i < 64; i++) {
/*     */       
/* 311 */       int base_offset = i * 6;
/*     */       
/* 313 */       if (this.dissolving_blocks_info[base_offset + 0] == block.blockID)
/*     */       {
/*     */         
/* 316 */         if (this.dissolving_blocks_info[base_offset + 1] == this.worldObj.getDimensionId())
/*     */         {
/*     */           
/* 319 */           if (this.dissolving_blocks_info[base_offset + 2] == x)
/*     */           {
/*     */             
/* 322 */             if (this.dissolving_blocks_info[base_offset + 3] == y)
/*     */             {
/*     */               
/* 325 */               if (this.dissolving_blocks_info[base_offset + 4] == z)
/*     */               {
/*     */                 
/* 328 */                 return i; }  }  }  } 
/*     */       }
/*     */     } 
/* 331 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int isDissolvingBlock(BlockInfo info) {
/* 337 */     return isDissolvingBlock(info.block, info.x, info.y, info.z);
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
/*     */   public int getDissolvePeriod(Block block, int x, int y, int z) {
/* 371 */     int ticks = block.getDissolvePeriod(this.worldObj, x, y, z, getDamageTypeVsItems());
/*     */     
/* 373 */     return (ticks >= 0) ? (ticks / 20) : -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canDissolveBlockGradually(int x, int y, int z) {
/* 378 */     Block block = this.worldObj.getBlock(x, y, z);
/*     */     
/* 380 */     return (block != null && getDissolvePeriod(block, x, y, z) > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canDissolveBlockGradually(BlockInfo info) {
/* 385 */     return canDissolveBlockGradually(info.x, info.y, info.z);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean dissolvesBlockInstantly(int x, int y, int z) {
/* 390 */     Block block = this.worldObj.getBlock(x, y, z);
/*     */     
/* 392 */     return (block != null && getDissolvePeriod(block, x, y, z) == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isSameBlock(int index, BlockInfo info) {
/* 398 */     if (info == null) {
/* 399 */       return false;
/*     */     }
/* 401 */     int base_offset = index * 6;
/*     */     
/* 403 */     if (this.dissolving_blocks_info[base_offset + 0] != info.block.blockID) {
/* 404 */       return false;
/*     */     }
/* 406 */     if (this.dissolving_blocks_info[base_offset + 1] != this.worldObj.getDimensionId()) {
/* 407 */       return false;
/*     */     }
/* 409 */     if (this.dissolving_blocks_info[base_offset + 2] != info.x) {
/* 410 */       return false;
/*     */     }
/* 412 */     if (this.dissolving_blocks_info[base_offset + 3] != info.y) {
/* 413 */       return false;
/*     */     }
/* 415 */     if (this.dissolving_blocks_info[base_offset + 4] != info.z) {
/* 416 */       return false;
/*     */     }
/* 418 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isBlockFoundInBlockInfos(int index, BlockInfo[] infos) {
/* 423 */     for (int i = 0; i < infos.length; i++) {
/*     */       
/* 425 */       if (isSameBlock(index, infos[i])) {
/* 426 */         return true;
/*     */       }
/*     */     } 
/* 429 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void storeExtendedDissolvingBlockInfo(int base_offset) {
/* 434 */     Iterator<int[]> i = this.extended_dissolving_blocks_info.iterator();
/*     */     
/* 436 */     while (i.hasNext()) {
/*     */       
/* 438 */       int[] arrayOfInt = i.next();
/*     */       
/* 440 */       boolean matches = true;
/*     */       
/* 442 */       for (int j = 0; j < 5; j++) {
/*     */         
/* 444 */         if (arrayOfInt[j] != this.dissolving_blocks_info[base_offset + j]) {
/*     */           
/* 446 */           matches = false;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 451 */       if (matches) {
/*     */         
/* 453 */         arrayOfInt[5] = this.dissolving_blocks_info[base_offset + 5];
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 458 */     int[] info = new int[6];
/*     */     
/* 460 */     for (int offset = 0; offset < 6; offset++) {
/* 461 */       info[offset] = this.dissolving_blocks_info[base_offset + offset];
/*     */     }
/* 463 */     this.extended_dissolving_blocks_info.add(info);
/*     */   }
/*     */ 
/*     */   
/*     */   private int getProgressFromExtendedDissolvingBlockInfo(Block block, int block_dimension, int x, int y, int z) {
/* 468 */     Iterator<int[]> i = this.extended_dissolving_blocks_info.iterator();
/*     */     
/* 470 */     while (i.hasNext()) {
/*     */       
/* 472 */       int[] info = i.next();
/*     */       
/* 474 */       if (info[0] != block.blockID) {
/*     */         continue;
/*     */       }
/* 477 */       if (info[1] != block_dimension) {
/*     */         continue;
/*     */       }
/* 480 */       if (info[2] != x) {
/*     */         continue;
/*     */       }
/* 483 */       if (info[3] != y) {
/*     */         continue;
/*     */       }
/* 486 */       if (info[4] != z) {
/*     */         continue;
/*     */       }
/* 489 */       return info[5];
/*     */     } 
/*     */     
/* 492 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private void pruneExtendedDissolvingBlockInfo() {
/* 497 */     Iterator<int[]> i = this.extended_dissolving_blocks_info.iterator();
/*     */     
/* 499 */     while (i.hasNext()) {
/*     */       
/* 501 */       int[] info = i.next();
/*     */       
/* 503 */       int x = info[2];
/* 504 */       int y = info[3];
/* 505 */       int z = info[4];
/*     */       
/* 507 */       if (this.worldObj.getDimensionId() == info[1] && getDistanceSqToBlock(x, y, z) < 256.0D && this.worldObj.getBlock(x, y, z) == Block.getBlock(info[0])) {
/*     */         continue;
/*     */       }
/* 510 */       i.remove();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void spentTickInLava() {
/* 516 */     super.spentTickInLava();
/*     */     
/* 518 */     if (!this.isDead)
/*     */     {
/* 520 */       if (onClient()) {
/*     */         
/* 522 */         spawnSteamParticles(5);
/* 523 */         spawnLargeSmokeParticles(5);
/*     */ 
/*     */       
/*     */       }
/* 527 */       else if (--this.ticks_until_next_fizz_sound <= 0) {
/*     */         
/* 529 */         playSound("random.fizz", 0.7F, 1.6F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
/* 530 */         this.ticks_until_next_fizz_sound = this.rand.nextInt(7) + 2;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 538 */     if (onServer()) {
/*     */       
/* 540 */       if (this.block_feeding_countdown > 0) {
/*     */         
/* 542 */         this.block_feeding_countdown--;
/* 543 */         updateIsFeedingFlag();
/*     */       } 
/*     */       
/* 546 */       if (this.item_feeding_countdown > 0) {
/*     */         
/* 548 */         this.item_feeding_countdown--;
/* 549 */         updateIsFeedingFlag();
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 554 */       if (getTicksExistedWithOffset() % 20 == 0) {
/*     */         
/* 556 */         pruneExtendedDissolvingBlockInfo();
/*     */ 
/*     */         
/* 559 */         BlockInfo[] infos = getBlocksOccupiedWithStandardExpansion();
/*     */         
/* 561 */         for (int i = 0; i < 64; i++) {
/*     */           
/* 563 */           int base_offset = i * 6;
/*     */           
/* 565 */           if (this.dissolving_blocks_info[base_offset + 0] != 0)
/*     */           {
/*     */             
/* 568 */             if (!isBlockFoundInBlockInfos(i, infos)) {
/*     */               
/* 570 */               storeExtendedDissolvingBlockInfo(base_offset);
/* 571 */               this.dissolving_blocks_info[base_offset + 0] = 0;
/*     */             } 
/*     */           }
/*     */         } 
/* 575 */         boolean clear_block_feeding_counter = true;
/*     */         
/* 577 */         for (int j = 0; j < infos.length; j++) {
/*     */           
/* 579 */           BlockInfo info = infos[j];
/*     */           
/* 581 */           if (info != null) {
/*     */ 
/*     */             
/* 584 */             Block block = info.block;
/* 585 */             int x = info.x;
/* 586 */             int y = info.y;
/* 587 */             int z = info.z;
/*     */             
/* 589 */             if (this.worldObj.getBlock(x, y, z) != block) {
/*     */               
/* 591 */               int index = isDissolvingBlock(info);
/*     */               
/* 593 */               if (index >= 0) {
/* 594 */                 this.dissolving_blocks_info[index * 6 + 0] = 0;
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/* 599 */               int dissolve_period = getDissolvePeriod(block, x, y, z);
/*     */               
/* 601 */               if (dissolve_period >= 1) {
/*     */ 
/*     */                 
/* 604 */                 int index = isDissolvingBlock(info);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 638 */                 if (index < 0) {
/*     */                   
/* 640 */                   int progress = getProgressFromExtendedDissolvingBlockInfo(block, this.worldObj.getDimensionId(), x, y, z);
/*     */                   
/* 642 */                   setDissolvingBlock(block, x, y, z, progress);
/*     */                   
/* 644 */                   index = isDissolvingBlock(info);
/*     */                 } 
/*     */                 
/* 647 */                 int base_offset = index * 6;
/*     */                 
/* 649 */                 this.dissolving_blocks_info[base_offset + 5] = this.dissolving_blocks_info[base_offset + 5] + getSize();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 657 */                 if (this.dissolving_blocks_info[base_offset + 5] >= dissolve_period)
/*     */                 
/*     */                 { 
/*     */ 
/*     */                   
/* 662 */                   this.worldObj.destroyBlockWithoutDroppingItem(x, y, z, isAcidic() ? EnumBlockFX.smoke_and_steam : EnumBlockFX.steam);
/*     */                   
/* 664 */                   this.dissolving_blocks_info[base_offset + 0] = 0; }
/*     */                 
/*     */                 else
/*     */                 
/* 668 */                 { this.worldObj.blockFX(EnumBlockFX.steam_particles_only, x, y, z);
/* 669 */                   clear_block_feeding_counter = false; } 
/*     */               } 
/*     */             } 
/*     */           } 
/* 673 */         }  setBlockFeedingCountdown(clear_block_feeding_counter ? 0 : 20);
/*     */ 
/*     */ 
/*     */         
/* 677 */         List<EntityItem> entity_items = this.worldObj.getEntitiesWithinAABB(EntityItem.class, this.boundingBox);
/*     */         
/* 679 */         boolean refresh_counter = false;
/*     */         
/* 681 */         if (entity_items != null)
/*     */         {
/* 683 */           for (int k = 0; k < entity_items.size(); k++) {
/*     */             
/* 685 */             EntityItem entity_item = entity_items.get(k);
/*     */             
/* 687 */             if (!entity_item.isDead) {
/*     */ 
/*     */               
/* 690 */               if (entity_item.delayBeforeCanPickup < 60) {
/* 691 */                 entity_item.delayBeforeCanPickup = 60;
/*     */               }
/* 693 */               if (entity_item.isVessel()) {
/*     */                 
/* 695 */                 ItemVessel item_vessel = (ItemVessel)entity_item.getEntityItem().getItem();
/*     */                 
/* 697 */                 if (!item_vessel.isEmpty()) {
/*     */                   
/* 699 */                   Material contents = item_vessel.getContents();
/*     */                   
/* 701 */                   if (contents == Material.lava) {
/*     */                     
/* 703 */                     attackEntityFrom(new Damage(DamageSource.lava, 5.0F));
/* 704 */                     this.worldObj.blockFX(EnumBlockFX.smoke_and_steam, entity_item.getBlockPosX(), entity_item.getBlockPosY(), entity_item.getBlockPosZ());
/* 705 */                     entity_item.convertItem(item_vessel.getPeerForContents(Material.stone));
/*     */                   }
/* 707 */                   else if (contents == Material.water) {
/*     */                     
/* 709 */                     entity_item.convertItem(item_vessel.getEmptyVessel());
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 718 */               EntityDamageResult result = entity_item.attackEntityFrom(new Damage(getDamageTypeVsItems(), 1.0F));
/*     */               
/* 720 */               if (result != null && result.entityWasNegativelyAffectedButNotDestroyed())
/* 721 */                 refresh_counter = true; 
/*     */             } 
/*     */           } 
/*     */         }
/* 725 */         setItemFeedingCountdown(refresh_counter ? 20 : 0);
/*     */       } 
/*     */     } 
/*     */     
/* 729 */     super.onLivingUpdate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract DamageSource getDamageTypeVsItems();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onMeleeAttacked(EntityLivingBase attacker, EntityDamageResult result) {
/* 744 */     super.onMeleeAttacked(attacker, result);
/*     */     
/* 746 */     if (attacker.isEntityPlayer() && attacker.hasHeldItem()) {
/*     */       
/* 748 */       EntityPlayer player = attacker.getAsPlayer();
/*     */       
/* 750 */       if (player.inventory.takeDamage(player.getHeldItemStack(), getDamageTypeVsItems(), getAttackStrengthMultiplierForType())) {
/* 751 */         player.entityFX(EnumEntityFX.steam_with_hiss);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void tryAddArrowToContainedItems(EntityArrow entity_arrow) {
/* 757 */     if (isAcidic()) {
/*     */       
/* 759 */       ItemArrow item_arrow = entity_arrow.item_arrow;
/*     */       
/* 761 */       if (item_arrow.isHarmedByAcid()) {
/*     */         
/* 763 */         entity_arrow.entityFX(EnumEntityFX.steam_with_hiss);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 768 */     super.tryAddArrowToContainedItems(entity_arrow);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final int getDropItemId() {
/* 773 */     return (getSize() == 1) ? Item.slimeBall.itemID : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getClimbingSpeed() {
/* 778 */     return 0.2F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/* 783 */     return getSize() * (getAttackStrengthMultiplierForType() + (isAcidic() ? 1 : 0));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canDouseFire() {
/* 788 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canCatchFire() {
/* 793 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean attacksAnimals() {
/* 798 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean attacksVillagers() {
/* 803 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityGelatinousCube.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */