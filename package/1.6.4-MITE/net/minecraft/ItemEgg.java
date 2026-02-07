/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemEgg
/*    */   extends ItemFood
/*    */ {
/*    */   public ItemEgg(int par1) {
/* 36 */     super(par1, Material.meat, 1, 3, true, MITEConstant.egg_has_essential_fats, false, "egg");
/* 37 */     setMaxStackSize(16);
/* 38 */     setCraftingDifficultyAsComponent(25.0F);
/* 39 */     setCreativeTab(CreativeTabs.tabMaterials);
/*    */     
/* 41 */     setAnimalProduct();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 46 */     if (player.onServer()) {
/*    */       
/* 48 */       if (!player.inCreativeMode()) {
/* 49 */         player.convertOneOfHeldItem((ItemStack)null);
/*    */       }
/* 51 */       WorldServer world = player.getWorldServer();
/*    */       
/* 53 */       world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
/* 54 */       world.spawnEntityInWorld(new EntityEgg(world, player));
/*    */     }
/*    */     else {
/*    */       
/* 58 */       player.bobItem();
/*    */     } 
/*    */     
/* 61 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
/* 66 */     player.triggerAchievement(AchievementList.eggs);
/* 67 */     super.onItemUseFinish(item_stack, world, player);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasIngestionPriority(ItemStack item_stack, boolean ctrl_is_down) {
/* 72 */     return !ctrl_is_down;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getCompostingValue() {
/* 77 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemEgg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */