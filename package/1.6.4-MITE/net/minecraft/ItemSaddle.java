/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class ItemSaddle
/*    */   extends Item
/*    */ {
/*    */   public ItemSaddle(int par1) {
/*  8 */     super(par1, Material.leather, "saddle");
/*    */     
/* 10 */     setMaxStackSize(1);
/* 11 */     setCreativeTab(CreativeTabs.tabTransport);
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
/* 39 */     if (entity instanceof EntityPig) {
/*    */       
/* 41 */       EntityPig pig = (EntityPig)entity;
/*    */       
/* 43 */       if (!pig.getSaddled() && !pig.isChild()) {
/*    */         
/* 45 */         if (player.onServer()) {
/*    */           
/* 47 */           pig.setSaddled(true);
/*    */           
/* 49 */           if (!player.inCreativeMode()) {
/* 50 */             player.convertOneOfHeldItem(null);
/*    */           }
/*    */         } 
/* 53 */         return true;
/*    */       } 
/*    */     } 
/*    */     
/* 57 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemSaddle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */