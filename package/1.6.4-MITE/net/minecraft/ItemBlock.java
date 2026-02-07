/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ public class ItemBlock
/*     */   extends Item
/*     */ {
/*     */   private int blockID;
/*     */   private Icon field_94588_b;
/*     */   
/*     */   public ItemBlock(Block block) {
/*  13 */     super(block.blockID - 256, (String)null, block.getNumSubBlocks());
/*  14 */     this.blockID = block.blockID;
/*     */     
/*  16 */     getBlock().addItemBlockMaterials(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBlockID() {
/*  24 */     return this.blockID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSpriteNumber() {
/*  32 */     return (Block.blocksList[this.blockID].getItemIconName() != null) ? 1 : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIconFromSubtype(int par1) {
/*  40 */     return (this.field_94588_b != null) ? this.field_94588_b : Block.blocksList[this.blockID].getBlockTextureFromSide(1);
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
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 191 */     if (player.inCreativeMode() && ctrl_is_down && this.itemID == Block.web.blockID) {
/*     */       
/* 193 */       if (player.onServer()) {
/*     */         
/* 195 */         if (!player.inCreativeMode()) {
/*     */           
/* 197 */           player.convertOneOfHeldItem((ItemStack)null);
/* 198 */           player.addHungerServerSide(0.25F * EnchantmentHelper.getEnduranceModifier(player));
/*     */         } 
/*     */         
/* 201 */         WorldServer world = player.getWorldServer();
/*     */         
/* 203 */         world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
/* 204 */         world.spawnEntityInWorld(new EntityWeb(world, player));
/*     */       }
/*     */       else {
/*     */         
/* 208 */         player.bobItem();
/*     */       } 
/*     */       
/* 211 */       return true;
/*     */     } 
/*     */     
/* 214 */     RaycastCollision rc = player.getSelectedObject(partial_tick, false);
/*     */     
/* 216 */     if (rc == null || !rc.isBlock()) {
/* 217 */       return false;
/*     */     }
/* 219 */     if (player.worldObj.areSkillsEnabled())
/*     */     {
/* 221 */       if (getBlock() instanceof BlockMushroom && !player.hasSkill(Skill.FARMING)) {
/* 222 */         return false;
/*     */       }
/*     */     }
/* 225 */     if (player.onClient() && System.currentTimeMillis() < (player.getAsEntityClientPlayerMP()).prevent_block_placement_due_to_picking_up_held_item_until) {
/* 226 */       return false;
/*     */     }
/* 228 */     return player.tryPlaceHeldItemAsBlock(rc, Block.getBlock(this.blockID));
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
/*     */   public String getUnlocalizedName(ItemStack par1ItemStack) {
/* 284 */     return Block.blocksList[this.blockID].getUnlocalizedName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnlocalizedName() {
/* 292 */     return Block.blocksList[this.blockID].getUnlocalizedName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CreativeTabs getCreativeTab() {
/* 300 */     return Block.blocksList[this.blockID].getCreativeTabToDisplayOn();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
/* 308 */     Block.blocksList[this.blockID].getItemStacks(par1, par2CreativeTabs, par3List);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 313 */     String var2 = Block.blocksList[this.blockID].getItemIconName();
/*     */     
/* 315 */     if (var2 != null)
/*     */     {
/* 317 */       this.field_94588_b = par1IconRegister.registerIcon(var2);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getBlock() {
/* 323 */     return Block.blocksList[getBlockID()];
/*     */   }
/*     */ 
/*     */   
/*     */   public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
/* 328 */     Block block = getBlock();
/*     */     
/* 330 */     if (player.onServer())
/*     */     {
/* 332 */       if (isIngestable(item_stack)) {
/*     */         
/* 334 */         player.addFoodValue(this);
/*     */         
/* 336 */         if (isEatable(item_stack)) {
/* 337 */           world.playSoundAtEntity(player, "random.burp", 0.5F, player.rand.nextFloat() * 0.1F + 0.9F);
/*     */         }
/* 339 */         onEaten(item_stack, world, player);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 345 */     super.onItemUseFinish(item_stack, world, player);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer player) {
/* 350 */     if (player.onServer())
/*     */     {
/* 352 */       if (getBlock() == Block.mushroomRed) {
/*     */         
/* 354 */         player.addPotionEffect(new PotionEffect(Potion.poison.id, 200, 0));
/* 355 */         player.addPotionEffect(new PotionEffect(Potion.confusion.id, 1200, 4));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/* 362 */     return 32;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEatable(int item_subtype) {
/* 367 */     return (getBlock() instanceof BlockMushroom || getBlock() instanceof BlockCake);
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
/*     */   public int getSimilarityToItem(Item item) {
/* 401 */     if (getBlock() == Block.gravel && item instanceof ItemShovel)
/*     */     {
/* 403 */       return 1;
/*     */     }
/*     */     
/* 406 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int subtype) {
/* 411 */     return getBlock().getNameDisambiguationForReferenceFile(subtype);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBurnTime(ItemStack item_stack) {
/* 416 */     if (!canBurnAsFuelSource()) {
/* 417 */       return 0;
/*     */     }
/* 419 */     Block block = getBlock();
/*     */     
/* 421 */     if (block == Block.wood)
/* 422 */       return 1600; 
/* 423 */     if (block == Block.planks || block == Block.woodDoubleSlab || block == Block.woodenButton)
/* 424 */       return 400; 
/* 425 */     if (block == Block.woodSingleSlab || block == Block.sapling || block == Block.deadBush)
/* 426 */       return 200; 
/* 427 */     if (block == Block.torchWood || block instanceof BlockRedstoneTorch)
/* 428 */       return 800; 
/* 429 */     if (block.blockMaterial == Material.wood)
/* 430 */       return 400; 
/* 431 */     if (block == Block.coalBlock) {
/* 432 */       return 16000;
/*     */     }
/* 434 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeatLevel(ItemStack item_stack) {
/* 439 */     Block block = getBlock();
/*     */     
/* 441 */     return (block == Block.coalBlock) ? 2 : super.getHeatLevel(item_stack);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canCatchFire() {
/* 447 */     Block block = getBlock();
/*     */ 
/*     */ 
/*     */     
/* 451 */     if (block instanceof BlockTorch) {
/* 452 */       return true;
/*     */     }
/* 454 */     return block.blockMaterial.canCatchFire();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBurnAsFuelSource() {
/* 460 */     Block block = getBlock();
/*     */ 
/*     */ 
/*     */     
/* 464 */     if (block instanceof BlockTorch || block instanceof BlockSapling) {
/* 465 */       return true;
/*     */     }
/* 467 */     if (block == Block.woodenButton || block == Block.deadBush) {
/* 468 */       return true;
/*     */     }
/* 470 */     return block.blockMaterial.canBurnAsFuelSource();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 475 */     Block block = getBlock();
/*     */ 
/*     */ 
/*     */     
/* 479 */     if (block instanceof BlockTorch) {
/* 480 */       return true;
/*     */     }
/* 482 */     return block.blockMaterial.isHarmedByFire();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByLava() {
/* 487 */     Block block = getBlock();
/*     */ 
/*     */ 
/*     */     
/* 491 */     if (block instanceof BlockTorch)
/* 492 */       return true; 
/* 493 */     if (block == Block.oreAdamantium) {
/* 494 */       return false;
/*     */     }
/* 496 */     return block.blockMaterial.isHarmedByLava();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasIngestionPriority(ItemStack item_stack, boolean ctrl_is_down) {
/* 501 */     Block block = getBlock();
/*     */     
/* 503 */     return !(block instanceof BlockMushroom);
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
/*     */   public float getCraftingDifficultyAsComponent(ItemStack item_stack) {
/* 517 */     if (item_stack == null) {
/* 518 */       return super.getCraftingDifficultyAsComponent(null);
/*     */     }
/* 520 */     float difficulty = getBlock().getCraftingDifficultyAsComponent(item_stack.getItemSubtype());
/*     */     
/* 522 */     if (difficulty < 0.0F) {
/* 523 */       return super.getCraftingDifficultyAsComponent(item_stack);
/*     */     }
/* 525 */     return difficulty;
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
/*     */   public void addInformation(ItemStack item_stack, EntityPlayer player, List<String> info, boolean extended_info, Slot slot) {
/* 539 */     if (player.inCreativeMode() && extended_info) {
/*     */       
/* 541 */       Block block = getBlock();
/* 542 */       int metadata = item_stack.getItemSubtype();
/*     */       
/* 544 */       int min_harvest_level = block.getMinHarvestLevel(metadata);
/*     */       
/* 546 */       info.add(EnumChatFormatting.GRAY + "Hardness: " + StringHelper.formatFloat(block.getBlockHardness(metadata), 1, 2) + ((min_harvest_level == 0) ? "" : (" (" + min_harvest_level + ")")));
/* 547 */       info.add(EnumChatFormatting.GRAY + "Material: " + block.blockMaterial.getCapitalizedName());
/*     */     } 
/*     */     
/* 550 */     super.addInformation(item_stack, player, info, extended_info, slot);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItemStackForStatsIcon() {
/* 555 */     Block block = getBlock();
/*     */     
/* 557 */     int id = block.blockID;
/*     */     
/* 559 */     if (block == Block.flowerPot || block == Block.flowerPotMulti) {
/* 560 */       id = Item.flowerPot.itemID;
/*     */     }
/* 562 */     int subtype = 0;
/*     */     
/* 564 */     if (block == Block.tallGrass) {
/* 565 */       subtype = 1;
/*     */     }
/* 567 */     return new ItemStack(id, 1, subtype);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getItemDisplayName(ItemStack item_stack) {
/* 572 */     if (item_stack != null)
/*     */     {
/* 574 */       if (getBlock() == Block.workbench) {
/* 575 */         return Translator.get("tile.toolbench." + (BlockWorkbench.getToolMaterial(item_stack.getItemSubtype())).name + ".name");
/*     */       }
/*     */     }
/* 578 */     return super.getItemDisplayName(item_stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getCompostingValue() {
/* 583 */     Block block = getBlock();
/*     */     
/* 585 */     if (block == Block.hay)
/* 586 */       return Item.wheat.getCompostingValue() * 9.0F; 
/* 587 */     if (block == Block.leaves || block == Block.vine)
/* 588 */       return 1.0F; 
/* 589 */     if (block == Block.melon || block instanceof BlockPumpkin)
/* 590 */       return 2.0F; 
/* 591 */     if (block instanceof BlockMushroom || block == Block.tallGrass || block == Block.waterlily)
/* 592 */       return 0.5F; 
/* 593 */     if (block instanceof BlockFlower)
/* 594 */       return 0.25F; 
/* 595 */     if (block == Block.cake) {
/* 596 */       return 2.4F;
/*     */     }
/* 598 */     return super.getCompostingValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getCompostingRemains(ItemStack item_stack) {
/* 604 */     Block block = getBlock();
/*     */     
/* 606 */     return (block == Block.melon) ? Item.melonSeeds : ((block == Block.pumpkin || block == Block.pumpkinLantern) ? Item.pumpkinSeeds : null);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */