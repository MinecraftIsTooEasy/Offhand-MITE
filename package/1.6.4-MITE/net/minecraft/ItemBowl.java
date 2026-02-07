/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemBowl
/*     */   extends ItemVessel
/*     */ {
/*     */   public ItemBowl(int id, Material contents, String texture) {
/*  11 */     super(id, Material.wood, contents, 1, 16, 4, "bowls/" + texture);
/*     */     
/*  13 */     setCraftingDifficultyAsComponent(25.0F);
/*     */     
/*  15 */     setCreativeTab(CreativeTabs.tabMisc);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
/*  20 */     if (player.onServer()) {
/*     */       
/*  22 */       if (contains(Material.milk)) {
/*  23 */         player.clearActivePotions();
/*     */       }
/*     */       
/*  26 */       player.addFoodValue(this);
/*     */       
/*  28 */       if (isEatable(item_stack)) {
/*  29 */         world.playSoundAtEntity(player, "random.burp", 0.5F, player.rand.nextFloat() * 0.1F + 0.9F);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  35 */     super.onItemUseFinish(item_stack, world, player);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/*  40 */     return 32;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumItemInUseAction getItemInUseAction(ItemStack item_stack, EntityPlayer player) {
/*  45 */     if (!isIngestable(item_stack)) {
/*  46 */       return null;
/*     */     }
/*  48 */     return super.getItemInUseAction(item_stack, player);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/*  53 */     RaycastCollision rc = player.getSelectedObject(partial_tick, true);
/*     */     
/*  55 */     if (rc != null)
/*     */     {
/*  57 */       if (rc.isBlock())
/*     */       {
/*  59 */         if (isEmpty()) {
/*     */           
/*  61 */           if (rc.getBlockHitMaterial() == Material.water || rc.getNeighborOfBlockHitMaterial() == Material.water)
/*     */           {
/*  63 */             if (player.onServer()) {
/*  64 */               player.convertOneOfHeldItem(new ItemStack(getPeerForContents(Material.water)));
/*     */             }
/*  66 */             return true;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/*  71 */           if (rc.getNeighborOfBlockHit() == Block.fire && canContentsDouseFire()) {
/*     */             
/*  73 */             if (player.onServer()) {
/*     */               
/*  75 */               rc.world.douseFire(rc.neighbor_block_x, rc.neighbor_block_y, rc.neighbor_block_z, null);
/*  76 */               player.convertOneOfHeldItem(new ItemStack(getEmptyVessel()));
/*     */             } 
/*     */             
/*  79 */             return true;
/*     */           } 
/*  81 */           if (contains(Material.water)) {
/*     */             
/*  83 */             Block block = rc.getBlockHit();
/*     */             
/*  85 */             int x = rc.block_hit_x;
/*  86 */             int y = rc.block_hit_y;
/*  87 */             int z = rc.block_hit_z;
/*     */             
/*  89 */             EnumFace face_hit = rc.face_hit;
/*     */             
/*  91 */             if (block instanceof BlockCrops || block instanceof BlockStem || block == Block.mushroomBrown) {
/*     */               
/*  93 */               block = rc.world.getBlock(x, --y, z);
/*  94 */               face_hit = EnumFace.TOP;
/*     */             } 
/*     */             
/*  97 */             if (block == Block.tilledField && face_hit == EnumFace.TOP && BlockFarmland.fertilize(rc.world, x, y, z, player.getHeldItemStack(), player)) {
/*     */               
/*  99 */               if (player.onServer() && !player.inCreativeMode()) {
/* 100 */                 player.convertOneOfHeldItem(new ItemStack(getEmptyVessel()));
/*     */               }
/* 102 */               return true;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */     }
/*     */     
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSimilarityToItem(Item item) {
/* 118 */     if (item instanceof ItemBowl) {
/*     */       
/* 120 */       ItemBowl item_bowl = (ItemBowl)item;
/*     */       
/* 122 */       if (item_bowl.isEmpty() || isEmpty()) {
/* 123 */         return 2;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 128 */     return super.getSimilarityToItem(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemBowl setAnimalProduct() {
/* 133 */     super.setAnimalProduct();
/* 134 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemBowl setPlantProduct() {
/* 139 */     super.setPlantProduct();
/* 140 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBurnTime(ItemStack item_stack) {
/* 145 */     return isEmpty() ? 200 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemVessel getPeer(Material vessel_material, Material contents) {
/* 150 */     if (vessel_material == Material.wood) {
/*     */       
/* 152 */       if (contents == null)
/* 153 */         return bowlEmpty; 
/* 154 */       if (contents == Material.mushroom_stew)
/* 155 */         return bowlMushroomStew; 
/* 156 */       if (contents == Material.milk)
/* 157 */         return bowlMilk; 
/* 158 */       if (contents == Material.water)
/* 159 */         return bowlWater; 
/* 160 */       if (contents == Material.beef_stew)
/* 161 */         return bowlBeefStew; 
/* 162 */       if (contents == Material.chicken_soup)
/* 163 */         return bowlChickenSoup; 
/* 164 */       if (contents == Material.vegetable_soup)
/* 165 */         return bowlVegetableSoup; 
/* 166 */       if (contents == Material.ice_cream)
/* 167 */         return bowlIceCream; 
/* 168 */       if (contents == Material.salad)
/* 169 */         return bowlSalad; 
/* 170 */       if (contents == Material.cream_of_mushroom_soup)
/* 171 */         return bowlCreamOfMushroomSoup; 
/* 172 */       if (contents == Material.cream_of_vegetable_soup)
/* 173 */         return bowlCreamOfVegetableSoup; 
/* 174 */       if (contents == Material.mashed_potato)
/* 175 */         return bowlMashedPotato; 
/* 176 */       if (contents == Material.porridge)
/* 177 */         return bowlPorridge; 
/* 178 */       if (contents == Material.cereal) {
/* 179 */         return bowlCereal;
/*     */       }
/*     */     } 
/*     */     
/* 183 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemVessel getPeerForContents(Material contents) {
/* 188 */     return getPeer(getVesselMaterial(), contents);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemVessel getPeerForVesselMaterial(Material vessel_material) {
/* 193 */     return getPeer(vessel_material, getContents());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasIngestionPriority(ItemStack item_stack, boolean ctrl_is_down) {
/* 198 */     return !contains(Material.water);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSoupOrStew(Item item) {
/* 203 */     if (item instanceof ItemBowl) {
/*     */       
/* 205 */       Material contents = ((ItemBowl)item).getContents();
/*     */       
/* 207 */       return (contents instanceof MaterialSoup || contents instanceof MaterialStew);
/*     */     } 
/*     */     
/* 210 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getCompostingValue() {
/* 215 */     if (this == bowlMilk) {
/* 216 */       return 0.0F;
/*     */     }
/* 218 */     return super.getCompostingValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getCompostingRemains(ItemStack item_stack) {
/* 223 */     return getEmptyVessel();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemBowl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */