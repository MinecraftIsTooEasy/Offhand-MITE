/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemBrick
/*    */   extends Item
/*    */ {
/*    */   public ItemBrick(int id, Material material, String texture) {
/* 11 */     super(id, material, texture);
/* 12 */     setMaxStackSize(8);
/* 13 */     setCraftingDifficultyAsComponent(100.0F);
/* 14 */     setCreativeTab(CreativeTabs.tabMaterials);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 19 */     if (player.onServer()) {
/*    */       
/* 21 */       if (!player.inCreativeMode()) {
/*    */         
/* 23 */         player.convertOneOfHeldItem((ItemStack)null);
/* 24 */         player.addHungerServerSide(0.25F * EnchantmentHelper.getEnduranceModifier(player));
/*    */       } 
/*    */       
/* 27 */       WorldServer world = player.getWorldServer();
/*    */       
/* 29 */       world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
/* 30 */       world.spawnEntityInWorld(new EntityBrick(world, player, this));
/*    */     }
/*    */     else {
/*    */       
/* 34 */       player.bobItem();
/*    */     } 
/*    */     
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addInformation(ItemStack item_stack, EntityPlayer player, List<String> info, boolean extended_info, Slot slot) {
/* 42 */     if (extended_info) {
/*    */       
/* 44 */       info.add("");
/*    */       
/* 46 */       info.add(EnumChatFormatting.BLUE + Translator.getFormatted("item.tooltip.missileDamage", new Object[] { Integer.valueOf(1) }));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemBrick.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */