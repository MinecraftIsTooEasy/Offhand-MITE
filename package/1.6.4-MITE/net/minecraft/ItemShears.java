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
/*     */ public class ItemShears
/*     */   extends ItemTool
/*     */ {
/*     */   public ItemShears(int par1, Material material) {
/*  48 */     super(par1, material);
/*  49 */     addMaterialsEffectiveAgainst(new Material[] { Material.cloth, Material.tree_leaves, Material.plants, Material.vine, Material.web });
/*  50 */     addBlocksEffectiveAgainst(new Block[] { Block.tripWire });
/*     */     
/*  52 */     setReachBonus(0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToolType() {
/*  57 */     return "shears";
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBaseHarvestEfficiency(Block block) {
/*  62 */     return 4.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBaseDamageVsEntity() {
/*  67 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBlock() {
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumComponentsForDurability() {
/*  77 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBaseDecayRateForBreakingBlock(Block block) {
/*  82 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBaseDecayRateForAttackingEntity(ItemStack item_stack) {
/*  87 */     return 2.0F;
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
/*     */   public boolean tryEntityInteraction(Entity entity, EntityPlayer player, ItemStack item_stack) {
/* 143 */     if (entity instanceof EntitySheep) {
/*     */       
/* 145 */       EntitySheep sheep = (EntitySheep)entity;
/*     */       
/* 147 */       if (!sheep.getSheared() && !sheep.isChild()) {
/*     */         
/* 149 */         if (player.onServer()) {
/*     */           
/* 151 */           sheep.setSheared(true);
/* 152 */           int num_drops = 1 + sheep.rand.nextInt(3);
/*     */           
/* 154 */           for (int i = 0; i < num_drops; i++) {
/*     */             
/* 156 */             EntityItem entity_item = sheep.dropItemStack(new ItemStack(Block.cloth.blockID, 1, sheep.getFleeceColor()), 1.0F);
/*     */             
/* 158 */             entity_item.motionY += (sheep.rand.nextFloat() * 0.05F);
/* 159 */             entity_item.motionX += ((sheep.rand.nextFloat() - sheep.rand.nextFloat()) * 0.1F);
/* 160 */             entity_item.motionZ += ((sheep.rand.nextFloat() - sheep.rand.nextFloat()) * 0.1F);
/*     */           } 
/*     */           
/* 163 */           player.tryDamageHeldItem(DamageSource.generic, 50);
/* 164 */           sheep.playSound("mob.sheep.shear", 1.0F, 1.0F);
/*     */         } 
/*     */         
/* 167 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 171 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 176 */     RaycastCollision rc = player.getSelectedObject(partial_tick, false);
/*     */     
/* 178 */     if (rc != null && rc.isBlock() && rc.canPlayerEditBlockHit(player, player.getHeldItemStack())) {
/*     */       
/* 180 */       Block block = rc.getBlockHit();
/*     */ 
/*     */       
/* 183 */       if (!block.canSilkHarvest(rc.block_hit_metadata) || !isEffectiveAgainstBlock(block, rc.block_hit_metadata)) {
/* 184 */         return false;
/*     */       }
/* 186 */       if (player.onClient()) {
/*     */         
/* 188 */         player.swingArm();
/*     */       }
/*     */       else {
/*     */         
/* 192 */         World world = player.getWorld();
/*     */         
/* 194 */         int x = rc.block_hit_x;
/* 195 */         int y = rc.block_hit_y;
/* 196 */         int z = rc.block_hit_z;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 202 */         BlockBreakInfo info = (new BlockBreakInfo(world, x, y, z)).setHarvestedBy(player);
/*     */         
/* 204 */         info.dropBlockAsItself(true);
/*     */         
/* 206 */         world.playSoundAtBlock(x, y, z, "mob.sheep.shear", 1.0F, 1.0F);
/*     */         
/* 208 */         player.tryDamageHeldItem(DamageSource.generic, getToolDecayFromBreakingBlock(info));
/*     */       } 
/*     */       
/* 211 */       return true;
/*     */     } 
/*     */     
/* 214 */     return false;
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
/*     */   public boolean hasWoodenHandle() {
/* 230 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemShears.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */