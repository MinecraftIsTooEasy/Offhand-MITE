/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemInWorldManager
/*     */ {
/*     */   public World theWorld;
/*     */   public ServerPlayer thisPlayerMP;
/*     */   private EnumGameType gameType;
/*     */   private boolean tree_felling_in_progress;
/*     */   
/*     */   public ItemInWorldManager(World par1World) {
/*  37 */     this.gameType = EnumGameType.NOT_SET;
/*     */     
/*  39 */     this.theWorld = par1World;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGameType(EnumGameType par1EnumGameType) {
/*  44 */     if (!Minecraft.inDevMode()) {
/*  45 */       par1EnumGameType = EnumGameType.SURVIVAL;
/*     */     }
/*  47 */     this.gameType = par1EnumGameType;
/*  48 */     par1EnumGameType.configurePlayerCapabilities(this.thisPlayerMP.capabilities);
/*  49 */     this.thisPlayerMP.sendPlayerAbilities();
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumGameType getGameType() {
/*  54 */     if (!Minecraft.inDevMode()) {
/*  55 */       this.gameType = EnumGameType.SURVIVAL;
/*     */     }
/*  57 */     return this.gameType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCreative() {
/*  65 */     if (!Minecraft.inDevMode()) {
/*  66 */       return false;
/*     */     }
/*  68 */     return this.gameType.isCreative();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initializeGameType(EnumGameType par1EnumGameType) {
/*  76 */     if (!Minecraft.inDevMode()) {
/*  77 */       par1EnumGameType = EnumGameType.SURVIVAL;
/*     */     }
/*  79 */     if (this.gameType == EnumGameType.NOT_SET)
/*     */     {
/*  81 */       this.gameType = par1EnumGameType;
/*     */     }
/*     */     
/*  84 */     setGameType(this.gameType);
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
/*     */   public void onBlockClicked(int par1, int par2, int par3, EnumFace face) {
/* 181 */     if (!this.gameType.isAdventure() || this.thisPlayerMP.isCurrentToolAdventureModeExempt(par1, par2, par3))
/*     */     {
/* 183 */       if (isCreative()) {
/*     */ 
/*     */         
/* 186 */         if (!this.theWorld.extinguishFire((EntityPlayer)null, par1, par2, par3, face))
/*     */         {
/* 188 */           tryHarvestBlock(par1, par2, par3);
/*     */         
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 194 */         this.theWorld.extinguishFire((EntityPlayer)null, par1, par2, par3, face);
/*     */         
/* 196 */         float var5 = 1.0F;
/* 197 */         int var6 = this.theWorld.getBlockId(par1, par2, par3);
/*     */         
/* 199 */         if (var6 > 0) {
/*     */           
/* 201 */           Block.blocksList[var6].onBlockClicked(this.theWorld, par1, par2, par3, this.thisPlayerMP);
/*     */           
/* 203 */           var5 = this.thisPlayerMP.getDamageVsBlock(par1, par2, par3, true);
/*     */         } 
/*     */         
/* 206 */         if (var6 > 0 && var5 >= 1.0F)
/*     */         {
/* 208 */           tryHarvestBlock(par1, par2, par3);
/*     */         }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean removeBlock(int par1, int par2, int par3) {
/* 281 */     Block var4 = Block.blocksList[this.theWorld.getBlockId(par1, par2, par3)];
/* 282 */     int var5 = this.theWorld.getBlockMetadata(par1, par2, par3);
/*     */     
/* 284 */     if (var4 != null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 290 */     boolean var6 = this.theWorld.setBlockToAir(par1, par2, par3);
/*     */     
/* 292 */     if (var4 != null && var6)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 298 */       if (var4.isAlwaysOpaqueStandardFormCube() && var4.blockMaterial.requiresTool(var4, var5) && this.theWorld.rand.nextInt(100) == 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 304 */         int ran = this.theWorld.rand.nextInt(6);
/*     */         
/* 306 */         int dx = (ran == 0) ? -1 : ((ran == 1) ? 1 : 0);
/* 307 */         int dy = (ran == 2) ? -1 : ((ran == 3) ? 1 : 0);
/* 308 */         int dz = (ran == 4) ? -1 : ((ran == 5) ? 1 : 0);
/*     */         
/* 310 */         int x = par1 + dx;
/* 311 */         int y = par2 + dy;
/* 312 */         int z = par3 + dz;
/*     */         
/* 314 */         if (this.theWorld.getBlock(x, y, z) == Block.stone) {
/*     */ 
/*     */ 
/*     */           
/* 318 */           int num_non_stone_blocks = 0;
/*     */           
/* 320 */           for (int i = 0; i < 6; i++) {
/*     */             
/* 322 */             dx = (i == 0) ? -1 : ((i == 1) ? 1 : 0);
/* 323 */             dy = (i == 2) ? -1 : ((i == 3) ? 1 : 0);
/* 324 */             dz = (i == 4) ? -1 : ((i == 5) ? 1 : 0);
/*     */             
/* 326 */             if (this.theWorld.getBlock(x + dx, y + dy, z + dz) != Block.stone && ++num_non_stone_blocks > 1) {
/*     */               break;
/*     */             }
/*     */           } 
/* 330 */           if (num_non_stone_blocks == 1) {
/*     */             
/* 332 */             this.theWorld.setBlockToAir(x, y, z);
/*     */             
/* 334 */             EntityOoze ooze = new EntityOoze(this.theWorld);
/*     */             
/* 336 */             ooze.setSize(1);
/* 337 */             ooze.setLocationAndAngles(x + 0.5D, y + 0.25D, z + 0.5D, this.theWorld.rand.nextFloat() * 360.0F, 0.0F);
/* 338 */             this.theWorld.spawnEntityInWorld(ooze);
/*     */             
/* 340 */             ooze.playSound(ooze.getJumpSound(), ooze.getSoundVolume(ooze.getJumpSound()), ((ooze.rand.nextFloat() - ooze.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 348 */     return var6;
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
/*     */   public boolean tryHarvestBlock(int x, int y, int z) {
/* 489 */     if (this.theWorld.isRemote) {
/* 490 */       Minecraft.setErrorMessage("tryHarvestBlock: called on client?");
/*     */     }
/* 492 */     if (this.gameType.isAdventure() && !this.thisPlayerMP.isCurrentToolAdventureModeExempt(x, y, z)) {
/* 493 */       return false;
/*     */     }
/* 495 */     if (this.gameType.isCreative() && this.thisPlayerMP.getHeldItemStack() != null && this.thisPlayerMP.getHeldItemStack().getItem() instanceof ItemSword) {
/* 496 */       return false;
/*     */     }
/* 498 */     Block block = this.theWorld.getBlock(x, y, z);
/*     */     
/* 500 */     if (block == null) {
/* 501 */       return false;
/*     */     }
/* 503 */     block.onBlockAboutToBeBroken((new BlockBreakInfo(this.theWorld, x, y, z)).setHarvestedBy(this.thisPlayerMP));
/*     */     
/* 505 */     if (this.theWorld.getBlock(x, y, z) == null) {
/* 506 */       return false;
/*     */     }
/* 508 */     BlockBreakInfo block_break_info = (new BlockBreakInfo(this.theWorld, x, y, z)).setHarvestedBy(this.thisPlayerMP);
/*     */     
/* 510 */     block = block_break_info.block;
/*     */     
/* 512 */     if (block == null) {
/* 513 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 521 */     boolean player_can_damage_block = (this.thisPlayerMP.getCurrentPlayerStrVsBlock(x, y, z, true) > 0.0F);
/*     */     
/* 523 */     int data = block_break_info.block_id + (block_break_info.getMetadata() << 12);
/*     */     
/* 525 */     if (block_break_info.wasSilkHarvested())
/*     */     {
/* 527 */       data |= RenderGlobal.SFX_2001_WAS_SILK_HARVESTED;
/*     */     }
/*     */     
/* 530 */     this.theWorld.playAuxSFXAtEntity(this.thisPlayerMP, 2001, x, y, z, data);
/* 531 */     boolean block_was_removed = removeBlock(x, y, z);
/*     */     
/* 533 */     if (isCreative()) {
/*     */ 
/*     */       
/* 536 */       this.thisPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet53BlockChange(x, y, z, this.theWorld));
/*     */     }
/*     */     else {
/*     */       
/* 540 */       ItemStack held_item_stack = this.thisPlayerMP.getHeldItemStack();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 546 */       if (held_item_stack != null) {
/*     */         
/* 548 */         Item item = held_item_stack.getItem();
/*     */         
/* 550 */         if (item.onBlockDestroyed(block_break_info)) {
/* 551 */           this.thisPlayerMP.addStat(StatList.objectUseStats[item.itemID], 1);
/*     */         }
/*     */       } 
/* 554 */       if (block_was_removed && player_can_damage_block) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 587 */         this.thisPlayerMP.addStat(StatList.mineBlockStatArray[block.blockID], 1);
/*     */         
/* 589 */         if (block_break_info.wasSilkHarvested()) {
/* 590 */           block.dropBlockAsItself(block_break_info);
/*     */         } else {
/* 592 */           block.dropBlockAsEntityItem(block_break_info);
/*     */         } 
/*     */ 
/*     */         
/* 596 */         if (block == Block.wood && !this.tree_felling_in_progress) {
/*     */           
/* 598 */           int felling = EnchantmentHelper.getTreeFellingModifier(this.thisPlayerMP);
/* 599 */           this.tree_felling_in_progress = true;
/*     */           
/* 601 */           for (int dy = 1; dy <= felling;) {
/*     */             
/* 603 */             if (this.theWorld.getBlockId(x, y + dy, z) == Block.wood.blockID) {
/* 604 */               tryHarvestBlock(x, y + dy, z);
/*     */               
/*     */               dy++;
/*     */             } 
/*     */           } 
/* 609 */           this.tree_felling_in_progress = false;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 616 */     if (block_was_removed && !(block instanceof BlockTorch)) {
/*     */       
/* 618 */       int block_above_id = this.theWorld.getBlockId(x, y + 1, z);
/*     */       
/* 620 */       if (Block.blocksList[block_above_id] != null) {
/* 621 */         Block.blocksList[block_above_id].onUnderminedByPlayer(this.theWorld, this.thisPlayerMP, x, y + 1, z);
/*     */       }
/* 623 */       int[] dx = { 0, 1, 0, -1 };
/* 624 */       int[] dz = { -1, 0, 1, 0 };
/*     */       
/* 626 */       for (int i = 0; i < dx.length; i++) {
/*     */         
/* 628 */         int block_id2 = this.theWorld.getBlockId(x + dx[i], y, z + dz[i]);
/*     */         
/* 630 */         if (Block.blocksList[block_id2] != null) {
/* 631 */           Block.blocksList[block_id2].onUnderminedByPlayer(this.theWorld, this.thisPlayerMP, x + dx[i], y, z + dz[i]);
/*     */         }
/*     */       } 
/*     */     } 
/* 635 */     if (block_was_removed && this.theWorld.isUnderworld() && y < 6)
/*     */     {
/* 637 */       for (int i = 0; i < (EnumDirection.values()).length; i++) {
/*     */         
/* 639 */         EnumDirection direction = EnumDirection.get(i);
/*     */         
/* 641 */         Block neighbor = this.theWorld.getNeighborBlock(x, y, z, direction);
/*     */         
/* 643 */         if (neighbor == Block.mantleOrCore) {
/*     */           
/* 645 */           this.thisPlayerMP.triggerAchievement(AchievementList.portalToNether);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/* 651 */     return block_was_removed;
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
/*     */   public boolean XactivateBlockOrUseItem(EntityPlayer par1EntityPlayer, World par2World, ItemStack par3ItemStack, int par4, int par5, int par6, EnumFace face, float par8, float par9, float par10) {
/* 708 */     Minecraft.setErrorMessage("activateBlockOrUseItem: this function shouldn't be in use anymore");
/* 709 */     return false;
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
/*     */   public void setWorld(WorldServer par1WorldServer) {
/* 750 */     this.theWorld = par1WorldServer;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemInWorldManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */