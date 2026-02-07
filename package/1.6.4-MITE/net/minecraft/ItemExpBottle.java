/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemExpBottle
/*    */   extends Item
/*    */ {
/*    */   public static final int enchantment_levels_worth_of_experience = 2;
/*    */   
/*    */   public ItemExpBottle(int par1) {
/* 14 */     super(par1, Material.glass, "experience_bottle");
/* 15 */     setCreativeTab(CreativeTabs.tabMisc);
/*    */     
/* 17 */     setCraftingDifficultyAsComponent(25.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasEffect(ItemStack par1ItemStack) {
/* 22 */     return true;
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
/*    */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 47 */     if (player.onServer()) {
/*    */       
/* 49 */       if (!player.inCreativeMode()) {
/* 50 */         player.convertOneOfHeldItem((ItemStack)null);
/*    */       }
/* 52 */       WorldServer world = player.getWorldServer();
/*    */       
/* 54 */       world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
/* 55 */       world.spawnEntityInWorld(new EntityExpBottle(world, player));
/*    */     } 
/*    */     
/* 58 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addInformation(ItemStack item_stack, EntityPlayer player, List<String> info, boolean extended_info, Slot slot) {
/* 63 */     if (extended_info) {
/*    */       
/* 65 */       info.add("");
/*    */       
/* 67 */       info.add(EnumChatFormatting.BLUE + Translator.getFormatted("item.tooltip.XP", new Object[] { Integer.valueOf(Enchantment.getExperienceCost(2)) }));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemExpBottle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */