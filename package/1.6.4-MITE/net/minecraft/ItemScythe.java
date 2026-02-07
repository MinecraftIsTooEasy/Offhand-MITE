/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemScythe
/*    */   extends ItemTool
/*    */ {
/*    */   protected ItemScythe(int par1, Material material) {
/*  9 */     super(par1, material);
/*    */     
/* 11 */     addBlocksEffectiveAgainst(new Block[] { Block.tallGrass, Block.crops });
/*    */     
/* 13 */     setReachBonus(1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getToolType() {
/* 18 */     return "scythe";
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDamageVsEntity() {
/* 23 */     return 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumComponentsForDurability() {
/* 28 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDecayRateForBreakingBlock(Block block) {
/* 33 */     return (block == Block.tallGrass || block == Block.crops) ? 0.5F : 2.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDecayRateForAttackingEntity(ItemStack item_stack) {
/* 38 */     if (EnchantmentHelper.hasEnchantment(item_stack, Enchantment.vampiric))
/* 39 */       return 1.0F; 
/* 40 */     if (EnchantmentHelper.hasEnchantment(item_stack, Enchantment.sharpness)) {
/* 41 */       return 2.0F;
/*    */     }
/* 43 */     return 4.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onBlockDestroyed(BlockBreakInfo info) {
/* 48 */     if (!info.world.isRemote) {
/*    */       
/* 50 */       Block block = Block.getBlock(info.block_id);
/*    */       
/* 52 */       if (block.getClass() == BlockCrops.class) {
/*    */         
/* 54 */         BlockCrops crops = (BlockCrops)block;
/*    */         
/* 56 */         if (crops.isMature(info.getMetadata()) && Math.random() < EnchantmentHelper.getEnchantmentLevelFraction(Enchantment.fertility, info.getHarvesterItemStack())) {
/* 57 */           BlockFarmland.setFertilized(info.world, info.x, info.y - 1, info.z, true);
/*    */         }
/*    */       } 
/*    */     } 
/* 61 */     return super.onBlockDestroyed(info);
/*    */   }
/*    */ 
/*    */   
/*    */   public Class[] getSimilarClasses() {
/* 66 */     return new Class[] { ItemSword.class, ItemDagger.class, ItemKnife.class };
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemScythe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */