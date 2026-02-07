/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemBucket
/*     */   extends ItemVessel
/*     */ {
/*     */   public ItemBucket(int id, Material material, Material contents) {
/*  12 */     super(id, material, contents, 4, 8, 1, "buckets/" + material.name + "/" + ((contents == null) ? "empty" : ((contents == Material.water) ? "water" : ((contents == Material.lava) ? "lava" : "stone"))));
/*     */     
/*  14 */     setCraftingDifficultyAsComponent(25.0F);
/*  15 */     setCreativeTab(CreativeTabs.tabMisc);
/*     */   }
/*     */   
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/*     */     int x, y, z;
/*  20 */     RaycastCollision rc = player.getSelectedObject(partial_tick, true);
/*     */     
/*  22 */     if (rc == null || !rc.isBlock()) {
/*  23 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  27 */     if (isEmpty()) {
/*     */       Material material;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  65 */       if (rc.getBlockHitMaterial().isLiquid()) {
/*     */         
/*  67 */         x = rc.block_hit_x;
/*  68 */         y = rc.block_hit_y;
/*  69 */         z = rc.block_hit_z;
/*     */         
/*  71 */         material = rc.getBlockHitMaterial();
/*     */       }
/*     */       else {
/*     */         
/*  75 */         x = rc.neighbor_block_x;
/*  76 */         y = rc.neighbor_block_y;
/*  77 */         z = rc.neighbor_block_z;
/*     */         
/*  79 */         material = rc.getNeighborOfBlockHitMaterial();
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  85 */       if (material == null || !material.isLiquid()) {
/*  86 */         return false;
/*     */       }
/*  88 */       if (player.inCreativeMode() && !player.canMineAndEditBlock(x, y, z)) {
/*  89 */         return false;
/*     */       }
/*  91 */       if (player.onServer()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  98 */         if (player.inCreativeMode() || ctrl_is_down) {
/*  99 */           rc.world.setBlockToAir(x, y, z);
/*     */         }
/* 101 */         if (!player.inCreativeMode())
/*     */         {
/*     */ 
/*     */           
/* 105 */           if (material == Material.lava && rc.world.rand.nextFloat() < getChanceOfMeltingWhenFilledWithLava()) {
/*     */             
/* 107 */             player.addStat(StatList.objectBreakStats[this.itemID], 1);
/*     */             
/* 109 */             ItemStack held_item_stack = player.getHeldItemStack();
/*     */             
/* 111 */             ItemStack itemStack1 = getItemProducedWhenDestroyed(held_item_stack, DamageSource.lava);
/*     */             
/* 113 */             if (itemStack1 == null)
/*     */             {
/*     */               
/* 116 */               rc.world.blockFX(EnumBlockFX.item_consumed_by_lava, x, y, z);
/*     */             }
/*     */             
/* 119 */             player.convertOneOfHeldItem(itemStack1);
/*     */             
/* 121 */             if (!player.hasHeldItem()) {
/* 122 */               (player.getAsEntityPlayerMP()).prevent_item_pickup_due_to_held_item_breaking_until = System.currentTimeMillis() + 1500L;
/*     */             }
/*     */           } else {
/*     */             
/* 126 */             player.convertOneOfHeldItem(new ItemStack(getPeerForContents(material)));
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 136 */       return true;
/*     */     } 
/* 138 */     if (contains(Material.stone))
/*     */     {
/* 140 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 144 */     ItemStack item_stack = player.getHeldItemStack();
/*     */     
/* 146 */     if (contains(Material.water)) {
/*     */       
/* 148 */       Block block = rc.getBlockHit();
/*     */       
/* 150 */       x = rc.block_hit_x;
/* 151 */       y = rc.block_hit_y;
/* 152 */       z = rc.block_hit_z;
/*     */       
/* 154 */       EnumFace face_hit = rc.face_hit;
/*     */       
/* 156 */       if (rc.world.getBlock(x, y - 1, z) == Block.tilledField) {
/*     */         
/* 158 */         block = rc.world.getBlock(x, --y, z);
/* 159 */         face_hit = EnumFace.TOP;
/*     */       } 
/*     */       
/* 162 */       if (block == Block.tilledField && face_hit == EnumFace.TOP) {
/*     */         
/* 164 */         if (BlockFarmland.fertilize(rc.world, x, y, z, player.getHeldItemStack(), player)) {
/*     */           
/* 166 */           if (player.onServer() && !player.inCreativeMode()) {
/* 167 */             player.convertOneOfHeldItem(new ItemStack(getEmptyVessel()));
/*     */           }
/* 169 */           return true;
/*     */         } 
/*     */         
/* 172 */         return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 202 */     if (!player.inCreativeMode() && (rc.getBlockHitMaterial() == getContents() || rc.getNeighborOfBlockHitMaterial() == getContents())) {
/*     */       
/* 204 */       if (player.onServer()) {
/* 205 */         player.convertOneOfHeldItem(new ItemStack(getEmptyVessel()));
/*     */       }
/* 207 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 211 */     if (rc.getBlockHit().isLiquid() || rc.isBlockHitReplaceableBy(getBlockForContents(), 0)) {
/*     */       
/* 213 */       x = rc.block_hit_x;
/* 214 */       y = rc.block_hit_y;
/* 215 */       z = rc.block_hit_z;
/*     */     }
/*     */     else {
/*     */       
/* 219 */       x = rc.neighbor_block_x;
/* 220 */       y = rc.neighbor_block_y;
/* 221 */       z = rc.neighbor_block_z;
/*     */     } 
/*     */     
/* 224 */     if (!player.canPlayerEdit(x, y, z, item_stack)) {
/* 225 */       return false;
/*     */     }
/* 227 */     if (tryPlaceContainedLiquid(rc.world, player, x, y, z, shouldContainedLiquidBePlacedAsSourceBlock(player, ctrl_is_down))) {
/*     */       
/* 229 */       if (player.onServer() && !player.inCreativeMode()) {
/* 230 */         player.convertOneOfHeldItem(new ItemStack(getEmptyVessel()));
/*     */       }
/* 232 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 236 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean shouldContainedLiquidBePlacedAsSourceBlock(EntityPlayer player, boolean ctrl_is_down) {
/* 241 */     if (player == null) {
/* 242 */       return false;
/*     */     }
/* 244 */     if (player.inCreativeMode()) {
/* 245 */       return true;
/*     */     }
/* 247 */     return (ctrl_is_down && player.experience >= 100);
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getBlockForContents() {
/* 252 */     if (contains(Material.water))
/* 253 */       return Block.waterMoving; 
/* 254 */     if (contains(Material.lava)) {
/* 255 */       return Block.lavaMoving;
/*     */     }
/* 257 */     Minecraft.setErrorMessage("getBlockForContents: no handler for contents " + getContents());
/* 258 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean tryPlaceContainedLiquid(World world, EntityPlayer player, int x, int y, int z, boolean allow_placement_of_source_block) {
/* 263 */     if (isEmpty()) {
/*     */       
/* 265 */       Minecraft.setErrorMessage("tryPlaceContainedLiquid: bucket is empty");
/* 266 */       return false;
/*     */     } 
/*     */     
/* 269 */     Material material_in_bucket = getContents();
/*     */     
/* 271 */     if (material_in_bucket == null) {
/*     */       
/* 273 */       Minecraft.setErrorMessage("tryPlaceContainedLiquid: material in bucket is null");
/* 274 */       return false;
/*     */     } 
/*     */     
/* 277 */     Material target_block_material = world.getBlockMaterial(x, y, z);
/*     */ 
/*     */     
/* 280 */     if (target_block_material.isSolid()) {
/* 281 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 285 */     boolean placement_prevented = false;
/*     */     
/* 287 */     if (material_in_bucket.canDouseFire() && world.getBlock(x, y, z) == Block.fire) {
/*     */       
/* 289 */       if (!world.isRemote) {
/* 290 */         world.douseFire(x, y, z, null);
/*     */       }
/* 292 */       placement_prevented = true;
/*     */     }
/* 294 */     else if (material_in_bucket == Material.water && world.provider.isHellWorld) {
/*     */       
/* 296 */       if (!world.isRemote) {
/* 297 */         world.blockFX(EnumBlockFX.steam, x, y, z);
/*     */       }
/* 299 */       placement_prevented = true;
/*     */     } 
/*     */     
/* 302 */     if (!placement_prevented) {
/*     */       
/* 304 */       if (player != null && !player.inCreativeMode() && material_in_bucket == target_block_material) {
/* 305 */         return true;
/*     */       }
/* 307 */       if (!world.isRemote) {
/*     */         
/* 309 */         WorldServer world_server = (WorldServer)world;
/*     */         
/* 311 */         if (!target_block_material.isSolid() && !target_block_material.isLiquid() && !world.isAirBlock(x, y, z)) {
/* 312 */           world.destroyBlock((new BlockBreakInfo(world, x, y, z)).setFlooded((BlockFluid)getBlockForContents()), true);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 319 */         if (material_in_bucket == Material.water && world.getBlockMaterial(x, y, z) == Material.lava) {
/*     */           
/* 321 */           world.tryConvertLavaToCobblestoneOrObsidian(x, y, z);
/*     */         } else {
/* 323 */           if (material_in_bucket == Material.water && world.getBlock(x, y - 1, z) == Block.mantleOrCore) {
/*     */             
/* 325 */             world.blockFX(EnumBlockFX.steam, x, y, z);
/* 326 */             return true;
/*     */           } 
/* 328 */           if (material_in_bucket == Material.lava && world.getBlockMaterial(x, y, z) == Material.water) {
/*     */             
/* 330 */             world.tryConvertWaterToCobblestone(x, y, z);
/*     */ 
/*     */           
/*     */           }
/*     */           else {
/*     */ 
/*     */ 
/*     */             
/* 338 */             if (player == null || !player.inCreativeMode())
/*     */             {
/* 340 */               if (material_in_bucket == Material.water) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 354 */                 if (!allow_placement_of_source_block) {
/*     */                   
/* 356 */                   world.scheduleBlockChange(x, y, z, Block.waterStill.blockID, (getBlockForContents()).blockID, 1, 16);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*     */                 }
/* 363 */                 else if (!player.inCreativeMode()) {
/* 364 */                   player.addExperience(-100);
/*     */                 }
/*     */               
/* 367 */               } else if (material_in_bucket == Material.lava) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 373 */                 if (!allow_placement_of_source_block) {
/*     */                   
/* 375 */                   world.scheduleBlockChange(x, y, z, Block.lavaMoving.blockID, (getBlockForContents()).blockID, 1, 48);
/*     */ 
/*     */                 
/*     */                 }
/* 379 */                 else if (!player.inCreativeMode()) {
/* 380 */                   player.addExperience(-100);
/*     */                 } 
/*     */               } 
/*     */             }
/*     */ 
/*     */ 
/*     */             
/* 387 */             world.setBlock(x, y, z, (getBlockForContents()).blockID, 0, 3);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 394 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSimilarityToItem(Item item) {
/* 399 */     if (item instanceof ItemBucket) {
/*     */       
/* 401 */       ItemBucket item_bucket = (ItemBucket)item;
/*     */       
/* 403 */       if (item_bucket.getContents() == getContents())
/* 404 */         return 99; 
/* 405 */       if (item_bucket.isEmpty() || isEmpty()) {
/* 406 */         return 100 - ((getVesselMaterial() == item_bucket.getVesselMaterial()) ? 2 : 3);
/*     */       }
/*     */     } 
/* 409 */     return super.getSimilarityToItem(item);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumItemInUseAction getItemInUseAction(ItemStack par1ItemStack, EntityPlayer player) {
/* 416 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBurnTime(ItemStack item_stack) {
/* 421 */     if (contains(Material.lava)) {
/* 422 */       return 3200;
/*     */     }
/* 424 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeatLevel(ItemStack item_stack) {
/* 429 */     if (contains(Material.lava)) {
/* 430 */       return 3;
/*     */     }
/* 432 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemVessel getPeer(Material vessel_material, Material contents) {
/* 437 */     if (contents == null) {
/*     */       
/* 439 */       if (vessel_material == Material.copper)
/* 440 */         return Item.bucketCopperEmpty; 
/* 441 */       if (vessel_material == Material.silver)
/* 442 */         return Item.bucketSilverEmpty; 
/* 443 */       if (vessel_material == Material.gold)
/* 444 */         return Item.bucketGoldEmpty; 
/* 445 */       if (vessel_material == Material.iron)
/* 446 */         return Item.bucketIronEmpty; 
/* 447 */       if (vessel_material == Material.mithril)
/* 448 */         return Item.bucketMithrilEmpty; 
/* 449 */       if (vessel_material == Material.adamantium)
/* 450 */         return Item.bucketAdamantiumEmpty; 
/* 451 */       if (vessel_material == Material.ancient_metal) {
/* 452 */         return Item.bucketAncientMetalEmpty;
/*     */       }
/* 454 */       return null;
/*     */     } 
/* 456 */     if (contents == Material.water) {
/*     */       
/* 458 */       if (vessel_material == Material.copper)
/* 459 */         return Item.bucketCopperWater; 
/* 460 */       if (vessel_material == Material.silver)
/* 461 */         return Item.bucketSilverWater; 
/* 462 */       if (vessel_material == Material.gold)
/* 463 */         return Item.bucketGoldWater; 
/* 464 */       if (vessel_material == Material.iron)
/* 465 */         return Item.bucketIronWater; 
/* 466 */       if (vessel_material == Material.mithril)
/* 467 */         return Item.bucketMithrilWater; 
/* 468 */       if (vessel_material == Material.adamantium)
/* 469 */         return Item.bucketAdamantiumWater; 
/* 470 */       if (vessel_material == Material.ancient_metal) {
/* 471 */         return Item.bucketAncientMetalWater;
/*     */       }
/* 473 */       return null;
/*     */     } 
/* 475 */     if (contents == Material.lava) {
/*     */       
/* 477 */       if (vessel_material == Material.copper)
/* 478 */         return Item.bucketCopperLava; 
/* 479 */       if (vessel_material == Material.silver)
/* 480 */         return Item.bucketSilverLava; 
/* 481 */       if (vessel_material == Material.gold)
/* 482 */         return Item.bucketGoldLava; 
/* 483 */       if (vessel_material == Material.iron)
/* 484 */         return Item.bucketIronLava; 
/* 485 */       if (vessel_material == Material.mithril)
/* 486 */         return Item.bucketMithrilLava; 
/* 487 */       if (vessel_material == Material.adamantium)
/* 488 */         return Item.bucketAdamantiumLava; 
/* 489 */       if (vessel_material == Material.ancient_metal) {
/* 490 */         return Item.bucketAncientMetalLava;
/*     */       }
/* 492 */       return null;
/*     */     } 
/* 494 */     if (contents == Material.milk) {
/*     */       
/* 496 */       if (vessel_material == Material.copper)
/* 497 */         return Item.bucketCopperMilk; 
/* 498 */       if (vessel_material == Material.silver)
/* 499 */         return Item.bucketSilverMilk; 
/* 500 */       if (vessel_material == Material.gold)
/* 501 */         return Item.bucketGoldMilk; 
/* 502 */       if (vessel_material == Material.iron)
/* 503 */         return Item.bucketIronMilk; 
/* 504 */       if (vessel_material == Material.mithril)
/* 505 */         return Item.bucketMithrilMilk; 
/* 506 */       if (vessel_material == Material.adamantium)
/* 507 */         return Item.bucketAdamantiumMilk; 
/* 508 */       if (vessel_material == Material.ancient_metal) {
/* 509 */         return Item.bucketAncientMetalMilk;
/*     */       }
/* 511 */       return null;
/*     */     } 
/* 513 */     if (contents == Material.stone) {
/*     */       
/* 515 */       if (vessel_material == Material.copper)
/* 516 */         return Item.bucketCopperStone; 
/* 517 */       if (vessel_material == Material.silver)
/* 518 */         return Item.bucketSilverStone; 
/* 519 */       if (vessel_material == Material.gold)
/* 520 */         return Item.bucketGoldStone; 
/* 521 */       if (vessel_material == Material.iron)
/* 522 */         return Item.bucketIronStone; 
/* 523 */       if (vessel_material == Material.mithril)
/* 524 */         return Item.bucketMithrilStone; 
/* 525 */       if (vessel_material == Material.adamantium)
/* 526 */         return Item.bucketAdamantiumStone; 
/* 527 */       if (vessel_material == Material.ancient_metal) {
/* 528 */         return Item.bucketAncientMetalStone;
/*     */       }
/* 530 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 534 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemVessel getPeerForContents(Material contents) {
/* 539 */     return getPeer(getVesselMaterial(), contents);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemVessel getPeerForVesselMaterial(Material vessel_material) {
/* 544 */     return getPeer(vessel_material, getContents());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/* 549 */     return 32;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBehaviorDispenseItem getDispenserBehavior() {
/* 554 */     return isEmpty() ? new DispenserBehaviorEmptyBucket(this) : ((getContents() == Material.water || getContents() == Material.lava) ? new DispenserBehaviorFilledBucket((ItemBucket)getEmptyVessel()) : null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getChanceOfMeltingWhenFilledWithLava() {
/* 560 */     Material material = getVesselMaterial();
/*     */     
/* 562 */     return (material == Material.adamantium) ? 0.0F : ((material == Material.gold) ? 0.2F : (0.01F * Material.mithril.durability / material.durability));
/*     */   }
/*     */ 
/*     */   
/*     */   public void addInformation(ItemStack item_stack, EntityPlayer player, List<String> info, boolean extended_info, Slot slot) {
/* 567 */     if (extended_info && player != null && player.experience >= 100 && (contains(Material.water) || contains(Material.lava)))
/*     */     {
/* 569 */       info.add((contains(Material.water) ? (String)EnumChatFormatting.BLUE : (String)EnumChatFormatting.RED) + Translator.get("item.tooltip.placeBucketAsSource"));
/*     */     }
/* 571 */     if (extended_info && contains(Material.lava)) {
/*     */       
/* 573 */       int chance_of_breaking = (int)(getChanceOfMeltingWhenFilledWithLava() * 100.0F);
/*     */       
/* 575 */       if (chance_of_breaking > 0) {
/*     */         
/* 577 */         info.add("");
/*     */ 
/*     */ 
/*     */         
/* 581 */         info.add(EnumChatFormatting.DARK_PURPLE + Translator.get("item.tooltip.whenBucketFilled"));
/* 582 */         info.add(EnumChatFormatting.RED + Translator.getFormatted("item.tooltip.chanceOfBucketMelting", new Object[] { Integer.valueOf(chance_of_breaking) }));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemBucket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */