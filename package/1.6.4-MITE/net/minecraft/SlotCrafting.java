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
/*     */ public class SlotCrafting
/*     */   extends Slot
/*     */ {
/*     */   private final IInventory craftMatrix;
/*     */   private EntityPlayer thePlayer;
/*     */   private int amountCrafted;
/*     */   public CraftingResult crafting_result;
/*     */   public int crafting_result_index;
/*     */   
/*     */   public SlotCrafting(EntityPlayer par1EntityPlayer, IInventory par2IInventory, IInventory par3IInventory, int par4, int par5, int par6) {
/*  23 */     super(par3IInventory, par4, par5, par6);
/*  24 */     this.thePlayer = par1EntityPlayer;
/*  25 */     this.craftMatrix = par2IInventory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isItemValid(ItemStack par1ItemStack) {
/*  33 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int par1) {
/*  42 */     if (getHasStack())
/*     */     {
/*  44 */       this.amountCrafted += Math.min(par1, (getStack()).stackSize);
/*     */     }
/*     */     
/*  47 */     return super.decrStackSize(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onCrafting(ItemStack par1ItemStack, int par2) {
/*  56 */     this.amountCrafted += par2;
/*  57 */     onCrafting(par1ItemStack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onCrafting(ItemStack par1ItemStack) {
/*  65 */     par1ItemStack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.amountCrafted);
/*  66 */     this.amountCrafted = 0;
/*     */     
/*  68 */     Item item = par1ItemStack.getItem();
/*  69 */     Block block = (item instanceof ItemBlock) ? ((ItemBlock)item).getBlock() : null;
/*     */     
/*  71 */     if (block instanceof BlockFurnace && ((BlockFurnace)block).isOven()) {
/*     */       
/*  73 */       this.thePlayer.addStat(AchievementList.buildOven, 1);
/*     */     }
/*  75 */     else if (par1ItemStack.itemID == Block.workbench.blockID) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  81 */       Material tool_material = BlockWorkbench.getToolMaterial(par1ItemStack.getItemSubtype());
/*     */       
/*  83 */       if (tool_material.isMetal()) {
/*  84 */         this.thePlayer.addStat(AchievementList.betterTools, 1);
/*     */       } else {
/*  86 */         this.thePlayer.addStat(AchievementList.buildWorkBench, 1);
/*     */       }
/*     */     
/*     */     }
/*  90 */     else if (block == Block.torchWood) {
/*     */       
/*  92 */       this.thePlayer.addStat(AchievementList.buildTorches, 1);
/*     */     
/*     */     }
/*  95 */     else if (item == Item.pickaxeCopper || item == Item.pickaxeSilver || item == Item.pickaxeGold) {
/*     */       
/*  97 */       if (!this.thePlayer.worldObj.isRemote) {
/*  98 */         DedicatedServer.checkForTournamentWinner(this.thePlayer, EnumTournamentType.pickaxe);
/*     */       }
/* 100 */       this.thePlayer.addStat(AchievementList.buildPickaxe, 1);
/*     */     }
/* 102 */     else if (par1ItemStack.itemID == Block.furnaceIdle.blockID) {
/*     */ 
/*     */       
/* 105 */       this.thePlayer.addStat(AchievementList.buildFurnace, 1);
/*     */     }
/* 107 */     else if (par1ItemStack.itemID == Block.furnaceObsidianIdle.blockID) {
/*     */       
/* 109 */       this.thePlayer.triggerAchievement(AchievementList.obsidianFurnace);
/*     */     }
/* 111 */     else if (par1ItemStack.itemID == Block.furnaceNetherrackIdle.blockID) {
/*     */       
/* 113 */       this.thePlayer.triggerAchievement(AchievementList.netherrackFurnace);
/*     */ 
/*     */     
/*     */     }
/* 117 */     else if (item instanceof ItemHoe || item instanceof ItemMattock) {
/*     */       
/* 119 */       this.thePlayer.addStat(AchievementList.buildHoe, 1);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 125 */     else if (par1ItemStack.itemID == Item.cake.itemID) {
/*     */       
/* 127 */       this.thePlayer.addStat(AchievementList.bakeCake, 1);
/*     */ 
/*     */     
/*     */     }
/* 131 */     else if (item instanceof ItemTool && item.getAsTool().isEffectiveAgainstBlock(Block.obsidian, 0)) {
/*     */       
/* 133 */       this.thePlayer.addStat(AchievementList.buildBetterPickaxe, 1);
/*     */       
/* 135 */       if (this.thePlayer.worldObj instanceof WorldServer) {
/* 136 */         this.thePlayer.worldObj.worldInfo.fullfillVillageCondition(16, (WorldServer)this.thePlayer.worldObj);
/*     */       }
/* 138 */       if (item.getAsTool().isEffectiveAgainstBlock(Block.blockMithril, 0)) {
/* 139 */         this.thePlayer.triggerAchievement(AchievementList.crystalBreaker);
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 146 */     else if (item == Item.hatchetFlint || item == Item.knifeFlint) {
/*     */       
/* 148 */       this.thePlayer.addStat(AchievementList.cuttingEdge, 1);
/*     */     
/*     */     }
/* 151 */     else if (item == Item.clubWood) {
/*     */ 
/*     */       
/* 154 */       this.thePlayer.addStat(AchievementList.buildClub, 1);
/*     */     }
/* 156 */     else if (item instanceof ItemAxe && !(item instanceof ItemHatchet)) {
/*     */       
/* 158 */       this.thePlayer.addStat(AchievementList.buildAxe, 1);
/*     */     
/*     */     }
/* 161 */     else if (par1ItemStack.itemID == Block.enchantmentTable.blockID || par1ItemStack.itemID == Block.enchantmentTableEmerald.blockID) {
/*     */       
/* 163 */       this.thePlayer.addStat(AchievementList.enchantments, 1);
/*     */     }
/* 165 */     else if (par1ItemStack.itemID == Block.bookShelf.blockID) {
/*     */       
/* 167 */       this.thePlayer.addStat(AchievementList.bookcase, 1);
/*     */     }
/* 169 */     else if (item instanceof ItemShovel && !(item instanceof ItemMattock)) {
/*     */       
/* 171 */       this.thePlayer.addStat(AchievementList.buildShovel, 1);
/*     */     }
/* 173 */     else if (item instanceof ItemScythe) {
/*     */       
/* 175 */       this.thePlayer.addStat(AchievementList.buildScythe, 1);
/*     */     }
/* 177 */     else if (item instanceof ItemArmor && ((ItemArmor)item).isChainMail()) {
/*     */       
/* 179 */       this.thePlayer.addStat(AchievementList.buildChainMail, 1);
/*     */     }
/* 181 */     else if (item instanceof ItemFishingRod) {
/*     */       
/* 183 */       this.thePlayer.triggerAchievement(AchievementList.fishingRod);
/*     */     }
/* 185 */     else if (item == Item.flour) {
/*     */       
/* 187 */       this.thePlayer.triggerAchievement(AchievementList.flour);
/*     */     }
/* 189 */     else if (item instanceof ItemBowl && (item == Item.bowlSalad || ItemBowl.isSoupOrStew(item))) {
/*     */       
/* 191 */       this.thePlayer.triggerAchievement(AchievementList.fineDining);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack) {
/* 197 */     int consumption = this.crafting_result.consumption;
/*     */     
/* 199 */     this.amountCrafted = par2ItemStack.stackSize;
/* 200 */     onCrafting(par2ItemStack);
/*     */     
/* 202 */     par1EntityPlayer.inventory.addItemStackToInventoryOrDropIt(par2ItemStack.copy());
/*     */     
/* 204 */     int xp_reclaimed = 0;
/*     */     
/* 206 */     for (int var3 = 0; var3 < this.craftMatrix.getSizeInventory(); var3++) {
/*     */       
/* 208 */       ItemStack var4 = this.craftMatrix.getStackInSlot(var3);
/*     */       
/* 210 */       if (var4 != null) {
/*     */         
/* 212 */         Item item = var4.getItem();
/*     */         
/* 214 */         if (item instanceof ItemCoin) {
/*     */           
/* 216 */           ItemCoin coin = (ItemCoin)item;
/* 217 */           xp_reclaimed += coin.getExperienceValue();
/*     */         } 
/*     */ 
/*     */         
/* 221 */         this.craftMatrix.decrStackSize(var3, consumption);
/*     */         
/* 223 */         if (var4.getItem().hasContainerItem()) {
/*     */           
/* 225 */           ItemStack var5 = new ItemStack(var4.getItem().getContainerItem());
/*     */           
/* 227 */           Item container_item = var5.getItem();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 233 */           if (container_item.getClass() != par2ItemStack.getItem().getClass())
/*     */           {
/*     */             
/* 236 */             if (!var4.getItem().doesContainerItemLeaveCraftingGrid(var4) || !this.thePlayer.inventory.addItemStackToInventory(var5))
/*     */             {
/* 238 */               if (this.craftMatrix.getStackInSlot(var3) == null) {
/*     */                 
/* 240 */                 this.craftMatrix.setInventorySlotContents(var3, var5);
/*     */               }
/*     */               else {
/*     */                 
/* 244 */                 this.thePlayer.dropPlayerItem(var5);
/*     */               } 
/*     */             }
/*     */           }
/* 248 */         } else if (var4.itemID == Block.workbench.blockID) {
/*     */           
/* 250 */           this.thePlayer.inventory.addItemStackToInventoryOrDropIt(BlockWorkbench.getBlockComponent(var4.getItemSubtype()));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 255 */     if (xp_reclaimed > 0) {
/* 256 */       par1EntityPlayer.addExperience(xp_reclaimed, true, false);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
/* 261 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPlayerCraftItem(EntityPlayer player) {
/* 267 */     if (getStack() == null) {
/* 268 */       return false;
/*     */     }
/* 270 */     if (player.isUpperBodyInWeb()) {
/* 271 */       return false;
/*     */     }
/* 273 */     Item item = getStack().getItem();
/*     */     
/* 275 */     if (player.worldObj.areSkillsEnabled() && !player.hasSkillsForCraftingResult(this.crafting_result) && (!item.hasQuality() || this.crafting_result.recipe instanceof RecipesArmorDyes)) {
/* 276 */       return false;
/*     */     }
/* 278 */     if (item instanceof ItemCoin) {
/*     */       
/* 280 */       ItemCoin coin = (ItemCoin)item;
/*     */       
/* 282 */       if (player.experience < coin.getExperienceValue() * (getStack()).stackSize) {
/* 283 */         return false;
/*     */       }
/*     */     } 
/* 286 */     if (player.openContainer.repair_fail_condition != 0) {
/* 287 */       return false;
/*     */     }
/* 289 */     if ((getContainer()).crafting_result_shown_but_prevented) {
/* 290 */       return false;
/*     */     }
/* 292 */     if (getContainer() instanceof MITEContainerCrafting) {
/*     */       
/* 294 */       MITEContainerCrafting container = (MITEContainerCrafting)getContainer();
/*     */       
/* 296 */       if (container.craft_matrix.hasDamagedItem() && !container.current_crafting_result.isRepair()) {
/* 297 */         return false;
/*     */       }
/*     */     } 
/* 300 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onSlotClicked(EntityPlayer player, int button, Container container) {
/* 305 */     if (getStack() == null) {
/*     */       return;
/*     */     }
/* 308 */     if (button == 0) {
/*     */       
/* 310 */       if (!canPlayerCraftItem(player)) {
/*     */         return;
/*     */       }
/* 313 */       if (player instanceof EntityClientPlayerMP)
/*     */       {
/* 315 */         EntityClientPlayerMP entity_client_player_mp = (EntityClientPlayerMP)player;
/*     */ 
/*     */         
/* 318 */         entity_client_player_mp.crafting_proceed = true;
/*     */         
/* 320 */         entity_client_player_mp.hasCurse(Curse.clumsiness, true);
/*     */       }
/*     */     
/* 323 */     } else if (button == 1) {
/*     */       
/* 325 */       tryIncrementCraftingResultIndex(player);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void tryIncrementCraftingResultIndex(EntityPlayer player) {
/* 332 */     int num_crafting_results = getNumCraftingResults(player);
/*     */     
/* 334 */     if (num_crafting_results <= 1) {
/*     */       return;
/*     */     }
/* 337 */     Item item = getStack().getItem();
/*     */     
/* 339 */     if (item.hasQuality()) {
/*     */       
/* 341 */       if (this.crafting_result.quality_override != null) {
/*     */         return;
/*     */       }
/* 344 */       if (this.crafting_result_index + 1 <= player.getMaxCraftingQuality(this.crafting_result.getUnmodifiedDifficulty(), item, this.crafting_result.applicable_skillsets).ordinal()) {
/* 345 */         setCraftingResultIndex(this.crafting_result_index + 1, player);
/*     */       } else {
/* 347 */         setCraftingResultIndex(player.getMinCraftingQuality(item, this.crafting_result.applicable_skillsets).ordinal(), player);
/*     */       }
/*     */     
/*     */     }
/* 351 */     else if (this.crafting_result_index + 1 < num_crafting_results) {
/* 352 */       setCraftingResultIndex(this.crafting_result_index + 1, player);
/*     */     } else {
/* 354 */       setCraftingResultIndex(0, player);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getMinCraftingResultIndex(EntityPlayer player) {
/* 360 */     if (this.crafting_result == null || this.crafting_result.item_stack == null || this.crafting_result.item_stack.getItem() == null) {
/* 361 */       return 0;
/*     */     }
/* 363 */     ItemStack item_stack = this.crafting_result.item_stack;
/* 364 */     Item item = item_stack.getItem();
/*     */     
/* 366 */     if (item.hasQuality()) {
/* 367 */       return player.getMinCraftingQuality(item, this.crafting_result.applicable_skillsets).ordinal();
/*     */     }
/* 369 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getMaxCraftingResultIndex(EntityPlayer player) {
/* 374 */     if (this.crafting_result == null || this.crafting_result.item_stack == null || this.crafting_result.item_stack.getItem() == null) {
/* 375 */       return 0;
/*     */     }
/* 377 */     ItemStack item_stack = this.crafting_result.item_stack;
/* 378 */     Item item = item_stack.getItem();
/*     */     
/* 380 */     if (item.hasQuality()) {
/*     */       
/* 382 */       if (this.crafting_result.quality_override != null) {
/* 383 */         return this.crafting_result.quality_override.ordinal();
/*     */       }
/* 385 */       return player.getMaxCraftingQuality(this.crafting_result.getUnmodifiedDifficulty(), item, this.crafting_result.applicable_skillsets).ordinal();
/*     */     } 
/* 387 */     if (item instanceof ItemRunestone)
/*     */     {
/*     */       
/* 390 */       return item.getNumSubtypes() - 1;
/*     */     }
/*     */     
/* 393 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkCraftingResultIndex(EntityPlayer player) {
/* 399 */     int previous_crafting_result_index = this.crafting_result_index;
/*     */     
/* 401 */     setCraftingResultIndex(this.crafting_result_index, player);
/*     */     
/* 403 */     return (this.crafting_result_index != previous_crafting_result_index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setCraftingResultIndex(int crafting_result_index, EntityPlayer player) {
/* 409 */     if (this.crafting_result == null || !getHasStack()) {
/*     */       
/* 411 */       player.clearCrafting();
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 417 */     if (this.crafting_result.quality_override == null) {
/* 418 */       crafting_result_index = MathHelper.clamp_int(crafting_result_index, getMinCraftingResultIndex(player), getMaxCraftingResultIndex(player));
/*     */     } else {
/* 420 */       crafting_result_index = this.crafting_result.quality_override.ordinal();
/*     */     } 
/* 422 */     if (crafting_result_index != this.crafting_result_index) {
/*     */       
/* 424 */       this.crafting_result_index = crafting_result_index;
/* 425 */       player.resetCraftingProgress();
/*     */     } 
/*     */     
/* 428 */     modifyStackForRightClicks(player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void modifyStackForRightClicks(EntityPlayer player) {
/* 434 */     ItemStack item_stack = getStack();
/* 435 */     Item item = item_stack.getItem();
/*     */     
/* 437 */     if (item.hasQuality()) {
/*     */       
/* 439 */       if (this.crafting_result.quality_override != null) {
/* 440 */         this.crafting_result_index = this.crafting_result.quality_override.ordinal();
/*     */       }
/* 442 */       item_stack.setQuality(EnumQuality.values()[this.crafting_result_index]);
/*     */     }
/* 444 */     else if (item instanceof ItemRunestone) {
/*     */       
/* 446 */       item_stack.setItemSubtype(this.crafting_result_index);
/*     */     }
/* 448 */     else if (getNumCraftingResults(player) > 1) {
/*     */       
/* 450 */       Minecraft.setErrorMessage("onSlotClicked: multiple crafting results not handled for " + item);
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
/*     */ 
/*     */     
/* 465 */     updatePlayerCrafting(player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void updatePlayerCrafting(EntityPlayer player) {
/* 471 */     if (player instanceof EntityClientPlayerMP) {
/*     */       
/* 473 */       ItemStack item_stack = getStack();
/* 474 */       Item item = item_stack.getItem();
/*     */       
/* 476 */       EntityClientPlayerMP entity_client_player_mp = (EntityClientPlayerMP)player;
/*     */       
/* 478 */       entity_client_player_mp.crafting_item = item;
/*     */       
/* 480 */       float quality_adjusted_crafting_difficulty = this.crafting_result.getQualityAdjustedDifficulty(item_stack.getQuality());
/*     */       
/* 482 */       entity_client_player_mp.crafting_period = entity_client_player_mp.getCraftingPeriod(quality_adjusted_crafting_difficulty);
/*     */       
/* 484 */       entity_client_player_mp.crafting_experience_cost = (!item.hasQuality() || item_stack.getQuality().isAverageOrLower() || this.crafting_result.is_experience_cost_exempt) ? 0 : player.getCraftingExperienceCost(quality_adjusted_crafting_difficulty);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getNumCraftingResults(EntityPlayer player) {
/* 491 */     if (this.crafting_result == null || this.crafting_result.item_stack == null) {
/* 492 */       return 0;
/*     */     }
/* 494 */     ItemStack item_stack = this.crafting_result.item_stack;
/* 495 */     Item item = item_stack.getItem();
/*     */     
/* 497 */     if (item == null) {
/* 498 */       return 0;
/*     */     }
/* 500 */     if (item.hasQuality()) {
/*     */       
/* 502 */       if (this.crafting_result.quality_override != null) {
/* 503 */         return 1;
/*     */       }
/* 505 */       return player.getMaxCraftingQuality(this.crafting_result.getUnmodifiedDifficulty(), item, this.crafting_result.applicable_skillsets).ordinal() - player.getMinCraftingQuality(item, this.crafting_result.applicable_skillsets).ordinal() + 1;
/*     */     } 
/* 507 */     if (item instanceof ItemRunestone)
/*     */     {
/*     */       
/* 510 */       return item.getNumSubtypes();
/*     */     }
/*     */     
/* 513 */     return 1;
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
/*     */   protected void setInitialItemStack(EntityPlayer player, MITEContainerCrafting container) {
/* 529 */     this.crafting_result = container.current_crafting_result;
/*     */     
/* 531 */     if (this.crafting_result == null || this.crafting_result.item_stack == null || this.crafting_result.item_stack.getItem() == null) {
/*     */       
/* 533 */       setCraftingResultIndex(0, player);
/* 534 */       this.inventory.setInventorySlotContents(this.slotNumber, null);
/*     */       
/*     */       return;
/*     */     } 
/* 538 */     ItemStack item_stack = this.crafting_result.item_stack.copy();
/* 539 */     Item item = item_stack.getItem();
/*     */     
/* 541 */     this.inventory.setInventorySlotContents(this.slotNumber, item_stack);
/*     */     
/* 543 */     if (item.hasQuality()) {
/*     */       
/* 545 */       if (this.crafting_result.quality_override == null) {
/* 546 */         setCraftingResultIndex(player.getMinCraftingQuality(item, this.crafting_result.applicable_skillsets).ordinal(), player);
/*     */       } else {
/* 548 */         setCraftingResultIndex(this.crafting_result.quality_override.ordinal(), player);
/*     */       } 
/*     */     } else {
/*     */       
/* 552 */       setCraftingResultIndex(0, player);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SlotCrafting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */