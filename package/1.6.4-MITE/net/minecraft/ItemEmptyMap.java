/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class ItemEmptyMap
/*    */   extends ItemMapBase
/*    */ {
/*    */   protected ItemEmptyMap(int par1) {
/*  8 */     super(par1, "map_empty");
/*  9 */     setCreativeTab(CreativeTabs.tabMisc);
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
/* 46 */     if (player.onClient()) {
/* 47 */       return true;
/*    */     }
/* 49 */     WorldServer world = player.getWorldServer();
/*    */     
/* 51 */     if (!ItemMap.isAnotherMapIdAvailable(world)) {
/* 52 */       return false;
/*    */     }
/* 54 */     ItemStack new_item_stack = new ItemStack(Item.map, 1, world.getUniqueDataId("map"));
/* 55 */     String map_name = "map_" + new_item_stack.getItemSubtype();
/* 56 */     MapData map_data = new MapData(map_name);
/* 57 */     world.setItemData(map_name, map_data);
/* 58 */     map_data.scale = 0;
/* 59 */     int var7 = 128 * (1 << map_data.scale);
/* 60 */     map_data.xCenter = (int)(Math.round(player.posX / var7) * var7);
/* 61 */     map_data.zCenter = (int)(Math.round(player.posZ / var7) * var7);
/* 62 */     map_data.dimension = (byte)world.provider.dimensionId;
/* 63 */     map_data.markDirty();
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
/* 81 */     player.inventory.convertOneOfCurrentItem(new_item_stack);
/*    */     
/* 83 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemEmptyMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */