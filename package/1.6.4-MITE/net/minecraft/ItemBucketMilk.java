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
/*     */ public class ItemBucketMilk
/*     */   extends ItemVessel
/*     */ {
/*     */   public ItemBucketMilk(int id, Material material) {
/*  52 */     super(id, material, Material.milk, 4, 8, 1, "buckets/" + material.name + "/milk");
/*     */     
/*  54 */     setCreativeTab(CreativeTabs.tabMisc);
/*     */     
/*  56 */     setFoodValue(0, 4, true, false, false);
/*  57 */     setAnimalProduct();
/*  58 */     setAlwaysEdible();
/*     */     
/*  60 */     setCraftingDifficultyAsComponent(100.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
/*  65 */     if (player.onServer()) {
/*     */       
/*  67 */       player.clearActivePotions();
/*  68 */       player.foodStats.addFoodValue(this);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  73 */     super.onItemUseFinish(item_stack, world, player);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/*  78 */     return 32;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/*  83 */     RaycastCollision rc = player.getSelectedObject(partial_tick, true);
/*     */     
/*  85 */     if (rc != null)
/*     */     {
/*  87 */       if (rc.isBlock())
/*     */       {
/*  89 */         if (rc.getNeighborOfBlockHit() == Block.fire) {
/*     */           
/*  91 */           if (player.onServer()) {
/*     */             
/*  93 */             rc.world.douseFire(rc.neighbor_block_x, rc.neighbor_block_y, rc.neighbor_block_z, null);
/*     */             
/*  95 */             if (!player.inCreativeMode()) {
/*  96 */               player.convertOneOfHeldItem(new ItemStack(getContainerItem()));
/*     */             }
/*     */           } 
/*  99 */           return true;
/*     */         } 
/*     */       }
/*     */     }
/*     */     
/* 104 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemVessel getPeer(Material vessel_material, Material contents) {
/* 109 */     return ItemBucket.getPeer(vessel_material, contents);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemVessel getPeerForContents(Material contents) {
/* 114 */     return getPeer(getVesselMaterial(), contents);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemVessel getPeerForVesselMaterial(Material vessel_material) {
/* 119 */     return getPeer(vessel_material, getContents());
/*     */   }
/*     */ 
/*     */   
/*     */   public float getCompostingValue() {
/* 124 */     return 0.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemBucketMilk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */