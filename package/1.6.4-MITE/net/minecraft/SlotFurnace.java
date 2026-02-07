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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SlotFurnace
/*     */   extends SlotCraftingBase
/*     */ {
/*     */   public SlotFurnace(EntityPlayer player, IInventory inventory, int slot_index, int display_x, int display_y) {
/*  95 */     super(player, inventory, slot_index, display_x, display_y);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPickupFromSlot(EntityPlayer player, ItemStack item_stack) {
/* 100 */     onCrafting(item_stack);
/* 101 */     super.onPickupFromSlot(player, item_stack);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onCrafting(ItemStack item_stack) {
/* 106 */     if (!this.player.worldObj.isRemote) {
/*     */       
/* 108 */       int xp_reward = item_stack.getExperienceReward(this.quantity_taken);
/*     */       
/* 110 */       if (xp_reward > 0) {
/* 111 */         this.player.worldObj.spawnEntityInWorld(new EntityXPOrb(this.player.worldObj, this.player.posX, this.player.posY + 0.5D, this.player.posZ + 0.5D, xp_reward));
/*     */       }
/*     */     } 
/* 114 */     super.onCrafting(item_stack);
/*     */     
/* 116 */     Item item = item_stack.getItem();
/*     */     
/* 118 */     if (item == Item.ingotIron) {
/* 119 */       this.player.addStat(AchievementList.acquireIron, 1);
/* 120 */     } else if (item == Item.fishCooked || item == Item.fishLargeCooked) {
/* 121 */       this.player.addStat(AchievementList.cookFish, 1);
/* 122 */     } else if (item == Item.bread) {
/* 123 */       this.player.addStat(AchievementList.makeBread, 1);
/* 124 */     } else if (item == Item.ingotMithril) {
/* 125 */       this.player.triggerAchievement(AchievementList.mithrilIngot);
/* 126 */     } else if (item == Item.ingotAdamantium) {
/* 127 */       this.player.triggerAchievement(AchievementList.adamantiumIngot);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SlotFurnace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */