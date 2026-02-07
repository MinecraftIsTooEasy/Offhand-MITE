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
/*    */ public class ItemSnowball
/*    */   extends Item
/*    */ {
/*    */   public ItemSnowball(int id) {
/* 36 */     super(id, Material.snow, "snowball");
/* 37 */     setMaxStackSize(16);
/* 38 */     setCraftingDifficultyAsComponent(25.0F);
/* 39 */     setCreativeTab(CreativeTabs.tabMisc);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 44 */     if (player.onClient()) {
/*    */       
/* 46 */       player.bobItem();
/*    */     }
/*    */     else {
/*    */       
/* 50 */       if (!player.inCreativeMode()) {
/* 51 */         player.convertOneOfHeldItem((ItemStack)null);
/*    */       }
/* 53 */       WorldServer world = player.getWorldServer();
/*    */       
/* 55 */       world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
/* 56 */       world.spawnEntityInWorld(new EntitySnowball(world, player));
/*    */     } 
/*    */     
/* 59 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemSnowball.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */