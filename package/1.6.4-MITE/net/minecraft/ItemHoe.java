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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemHoe
/*     */   extends ItemTool
/*     */ {
/*     */   protected ItemHoe(int par1, Material material) {
/*  67 */     super(par1, material);
/*     */     
/*  69 */     addMaterialsEffectiveAgainst(new Material[] { Material.cake, Material.craftedSnow, Material.grass, Material.dirt, Material.sand, Material.snow });
/*  70 */     addBlocksEffectiveAgainst(new Block[] { Block.carrot, Block.potato, Block.onions });
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToolType() {
/*  75 */     return "hoe";
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBaseHarvestEfficiency(Block block) {
/*  80 */     return super.getBaseHarvestEfficiency(block) * 0.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBaseDamageVsEntity() {
/*  85 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBlock() {
/*  90 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumComponentsForDurability() {
/*  95 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBaseDecayRateForBreakingBlock(Block block) {
/* 100 */     return 2.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBaseDecayRateForAttackingEntity(ItemStack item_stack) {
/* 105 */     return 2.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean tryTillSoil(World world, int x, int y, int z, EnumFace face, EntityPlayer player, ItemStack item_stack) {
/* 110 */     if (!player.canPlayerEdit(x, y, z, item_stack)) {
/* 111 */       return false;
/*     */     }
/* 113 */     if (face.isBottom()) {
/* 114 */       return false;
/*     */     }
/* 116 */     if (!world.isAirBlock(x, y + 1, z) && world.getBlockWithRefreshedBounds(x, y + 1, z).getBlockBoundsMinY(Minecraft.getThreadIndex()) <= 0.0D) {
/* 117 */       return false;
/*     */     }
/* 119 */     Block block = world.getBlock(x, y, z);
/*     */     
/* 121 */     if (block != Block.grass && block != Block.dirt) {
/* 122 */       return false;
/*     */     }
/* 124 */     if (player.onClient()) {
/*     */       
/* 126 */       player.swingArm();
/* 127 */       Minecraft.theMinecraft.playerController.setUseButtonDelayOverride(200);
/*     */     }
/*     */     else {
/*     */       
/* 131 */       world.playSoundAtBlock(x, y, z, Block.tilledField.stepSound.getStepSound(), (Block.tilledField.stepSound.getVolume() + 1.0F) / 2.0F, Block.tilledField.stepSound.getPitch() * 0.8F);
/*     */       
/* 133 */       player.tryDamageHeldItem(DamageSource.generic, 50);
/*     */       
/* 135 */       player.addHungerServerSide(world.getBlockHardness(x, y, z) / 2.0F * EnchantmentHelper.getEnduranceModifier(player));
/*     */       
/* 137 */       world.setBlock(x, y, z, Block.tilledField.blockID);
/*     */       
/* 139 */       if (Math.random() < EnchantmentHelper.getEnchantmentLevelFraction(Enchantment.fertility, item_stack)) {
/* 140 */         BlockFarmland.setFertilized(world, x, y, z, true);
/*     */       }
/*     */     } 
/* 143 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 148 */     RaycastCollision rc = player.getSelectedObject(partial_tick, true);
/*     */     
/* 150 */     if (rc == null || !rc.isBlock()) {
/* 151 */       return false;
/*     */     }
/* 153 */     if (rc.face_hit.isTop())
/*     */     {
/*     */       
/* 156 */       return tryTillSoil(rc.world, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, rc.face_hit, player, player.getHeldItemStack());
/*     */     }
/*     */     
/* 159 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockDestroyed(BlockBreakInfo info) {
/* 164 */     if (!info.world.isRemote) {
/*     */       
/* 166 */       Block block = Block.getBlock(info.block_id);
/*     */       
/* 168 */       if (block instanceof BlockCrops && isEffectiveAgainstBlock(block, info.getMetadata())) {
/*     */         
/* 170 */         BlockCrops crops = (BlockCrops)block;
/*     */         
/* 172 */         if (!crops.isDead() && crops.isMature(info.getMetadata()) && Math.random() < EnchantmentHelper.getEnchantmentLevelFraction(Enchantment.fertility, info.getHarvesterItemStack())) {
/* 173 */           BlockFarmland.setFertilized(info.world, info.x, info.y - 1, info.z, true);
/*     */         }
/*     */       } 
/*     */     } 
/* 177 */     return super.onBlockDestroyed(info);
/*     */   }
/*     */ 
/*     */   
/*     */   public Class[] getSimilarClasses() {
/* 182 */     return new Class[] { ItemMattock.class, ItemShovel.class };
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemHoe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */