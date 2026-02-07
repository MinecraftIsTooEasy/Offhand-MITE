/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class ItemEnderPearl
/*    */   extends Item
/*    */ {
/*    */   public ItemEnderPearl(int par1) {
/*  8 */     super(par1, Material.ender_pearl, "ender_pearl");
/*    */     
/* 10 */     setMaxStackSize(16);
/* 11 */     setCreativeTab(CreativeTabs.tabMisc);
/*    */     
/* 13 */     setCraftingDifficultyAsComponent(100.0F);
/*    */   }
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
/*    */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 41 */     if (player.onClient()) {
/*    */       
/* 43 */       player.bobItem();
/*    */     }
/*    */     else {
/*    */       
/* 47 */       if (!player.inCreativeMode()) {
/* 48 */         player.convertOneOfHeldItem((ItemStack)null);
/*    */       }
/* 50 */       WorldServer world = player.getWorldServer();
/*    */       
/* 52 */       world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
/* 53 */       world.spawnEntityInWorld(new EntityEnderPearl(world, player));
/*    */     } 
/*    */     
/* 56 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemEnderPearl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */