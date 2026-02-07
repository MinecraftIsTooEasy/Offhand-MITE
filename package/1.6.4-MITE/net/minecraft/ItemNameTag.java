/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class ItemNameTag
/*    */   extends Item
/*    */ {
/*    */   public ItemNameTag(int par1) {
/*  8 */     super(par1, Material.leather, "name_tag");
/*  9 */     setCreativeTab(CreativeTabs.tabTools);
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
/*    */   public boolean tryEntityInteraction(Entity entity, EntityPlayer player, ItemStack item_stack) {
/* 37 */     if (!item_stack.hasDisplayName()) {
/* 38 */       return false;
/*    */     }
/* 40 */     if (entity instanceof EntityLiving) {
/*    */       
/* 42 */       if (player.onServer()) {
/*    */         
/* 44 */         EntityLiving entity_living = entity.getAsEntityLiving();
/*    */         
/* 46 */         entity_living.setCustomNameTag(item_stack.getDisplayName());
/* 47 */         entity_living.func_110163_bv();
/*    */         
/* 49 */         if (!player.inCreativeMode()) {
/* 50 */           player.convertOneOfHeldItem(null);
/*    */         }
/*    */       } 
/* 53 */       return true;
/*    */     } 
/*    */     
/* 56 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemNameTag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */