/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ItemVessel
/*     */   extends Item
/*     */ {
/*     */   private Material vessel_material;
/*     */   private Material contents;
/*     */   private int standard_volume;
/*     */   
/*     */   public ItemVessel(int id, Material vessel_material, Material contents_material, int standard_volume, int max_stack_size_empty, int max_stack_size_full, String texture) {
/*  14 */     super(id, vessel_material, texture);
/*     */     
/*  16 */     this.vessel_material = vessel_material;
/*     */     
/*  18 */     if (contents_material == null) {
/*     */       
/*  20 */       setMaxStackSize(max_stack_size_empty);
/*     */     }
/*     */     else {
/*     */       
/*  24 */       this.contents = contents_material;
/*  25 */       addMaterial(new Material[] { contents_material });
/*  26 */       setContainerItem(getEmptyVessel());
/*  27 */       setMaxStackSize(max_stack_size_full);
/*     */     } 
/*     */     
/*  30 */     this.standard_volume = standard_volume;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getVesselMaterial() {
/*  35 */     return this.vessel_material;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStandardVolume() {
/*  40 */     return this.standard_volume;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  45 */     return (getContents() == null);
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getContents() {
/*  50 */     return this.contents;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(Material material) {
/*  55 */     return (getContents() == material);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEatable(int item_subtype) {
/*  60 */     return (getContents() != null && getContents().isEdible());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDrinkable(int item_subtype) {
/*  65 */     return (getContents() != null && getContents().isDrinkable());
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemVessel setAnimalProduct() {
/*  70 */     super.setAnimalProduct();
/*  71 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemVessel setPlantProduct() {
/*  76 */     super.setPlantProduct();
/*  77 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canContentsDouseFire() {
/*  82 */     return (this.contents != null && this.contents.canDouseFire());
/*     */   }
/*     */ 
/*     */   
/*     */   public final ItemVessel getEmptyVessel() {
/*  87 */     return getPeerForContents((Material)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemProducedOnItemUseFinish() {
/*  98 */     return getContainerItem();
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItemProducedWhenDestroyed(ItemStack item_stack, DamageSource damage_source) {
/* 103 */     return this.vessel_material.isHarmedBy(damage_source) ? null : new ItemStack(getContainerItem(), item_stack.stackSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSimilarityToItem(Item item) {
/* 111 */     if (item instanceof ItemVessel) {
/*     */       
/* 113 */       ItemVessel vessel = (ItemVessel)item;
/*     */       
/* 115 */       if (vessel.getEmptyVessel() == getEmptyVessel()) {
/* 116 */         return 1;
/*     */       }
/*     */     } 
/* 119 */     return super.getSimilarityToItem(item);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean tryEntityInteraction(Entity entity, EntityPlayer player, ItemStack item_stack) {
/* 124 */     if (entity instanceof EntityLivestock) {
/*     */       
/* 126 */       if (isEmpty()) {
/*     */         
/* 128 */         if (entity instanceof EntityCow) {
/*     */           
/* 130 */           EntityCow cow = (EntityCow)entity;
/*     */           
/* 132 */           if (cow.getMilk() >= this.standard_volume * 25)
/*     */           {
/* 134 */             if (player.onServer()) {
/*     */               
/* 136 */               cow.setMilk(cow.getMilk() - this.standard_volume * 25);
/*     */               
/* 138 */               if (!player.inCreativeMode()) {
/* 139 */                 player.convertOneOfHeldItem(new ItemStack(getPeerForContents(Material.milk)));
/*     */               }
/*     */             } 
/* 142 */             return true;
/*     */           }
/*     */         
/*     */         } 
/* 146 */       } else if (contains(Material.water)) {
/*     */         
/* 148 */         EntityLivestock livestock = (EntityLivestock)entity;
/*     */         
/* 150 */         if (livestock.isThirsty())
/*     */         {
/* 152 */           if (player.onServer()) {
/*     */             
/* 154 */             livestock.addWater(0.25F * this.standard_volume);
/*     */             
/* 156 */             if (!player.inCreativeMode()) {
/* 157 */               player.convertOneOfHeldItem(new ItemStack(getEmptyVessel()));
/*     */             }
/*     */           } 
/* 160 */           return true;
/*     */         }
/*     */       
/*     */       } 
/* 164 */     } else if (canContentsDouseFire()) {
/*     */       
/* 166 */       if (entity instanceof EntityFireElemental) {
/*     */         
/* 168 */         if (player.onServer()) {
/*     */           
/* 170 */           entity.attackEntityFrom(new Damage(DamageSource.water, (this instanceof ItemBucket) ? 20.0F : 5.0F));
/* 171 */           entity.causeQuenchEffect();
/*     */           
/* 173 */           if (!player.inCreativeMode()) {
/* 174 */             player.convertOneOfHeldItem(new ItemStack(getEmptyVessel()));
/*     */           }
/*     */         } 
/* 177 */         return true;
/*     */       } 
/* 179 */       if (entity instanceof EntityNetherspawn) {
/*     */         
/* 181 */         if (player.onServer()) {
/*     */           
/* 183 */           entity.attackEntityFrom(new Damage(DamageSource.water, (this instanceof ItemBucket) ? 8.0F : 2.0F));
/* 184 */           entity.causeQuenchEffect();
/*     */           
/* 186 */           if (!player.inCreativeMode()) {
/* 187 */             player.convertOneOfHeldItem(new ItemStack(getEmptyVessel()));
/*     */           }
/*     */         } 
/* 190 */         return true;
/*     */       } 
/* 192 */       if (entity instanceof EntityEarthElemental) {
/*     */         
/* 194 */         EntityEarthElemental elemental = (EntityEarthElemental)entity;
/*     */         
/* 196 */         if (elemental.isMagma()) {
/*     */           
/* 198 */           if (player.onServer()) {
/*     */             
/* 200 */             elemental.convertToNormal(true);
/*     */             
/* 202 */             if (!player.inCreativeMode()) {
/* 203 */               player.convertOneOfHeldItem(new ItemStack(getEmptyVessel()));
/*     */             }
/*     */           } 
/* 206 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 211 */     return super.tryEntityInteraction(entity, player, item_stack);
/*     */   }
/*     */   
/*     */   public abstract ItemVessel getPeerForContents(Material paramMaterial);
/*     */   
/*     */   public abstract ItemVessel getPeerForVesselMaterial(Material paramMaterial);
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemVessel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */