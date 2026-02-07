/*     */ package net.minecraft;
/*     */ 
/*     */ public class ItemFlintAndSteel
/*     */   extends Item
/*     */   implements IDamageableItem
/*     */ {
/*     */   public ItemFlintAndSteel(int par1) {
/*   8 */     super(par1, Material.flint, "flint_and_steel");
/*   9 */     addMaterial(new Material[] { Material.iron });
/*     */     
/*  11 */     setMaxStackSize(1);
/*     */     
/*  13 */     setMaxDamage(16);
/*  14 */     setCreativeTab(CreativeTabs.tabTools);
/*     */     
/*  16 */     setSkillsetThatCanRepairThis(-1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumComponentsForDurability() {
/*  21 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRepairCost() {
/*  26 */     return 0;
/*     */   }
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
/*     */   private void makeIgniteSoundAndApplyDamage(EntityPlayer player) {
/*  86 */     if (player.onClient()) {
/*     */       
/*  88 */       Minecraft.setErrorMessage("makeIgniteSoundAndApplyDamage: not meant to be called on client");
/*     */       
/*     */       return;
/*     */     } 
/*  92 */     player.worldObj.playSoundAtEntity(player, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
/*  93 */     player.tryDamageHeldItem(DamageSource.generic, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean tryEntityInteraction(Entity entity, EntityPlayer player, ItemStack item_stack) {
/*  98 */     if (player.onServer()) {
/*     */       
/* 100 */       boolean entity_can_be_ignited = false;
/*     */       
/* 102 */       if (entity instanceof EntityChicken) {
/*     */         
/* 104 */         entity_can_be_ignited = true;
/*     */       }
/* 106 */       else if (entity instanceof EntitySheep) {
/*     */         
/* 108 */         EntitySheep sheep = (EntitySheep)entity;
/*     */         
/* 110 */         if (!sheep.getSheared()) {
/* 111 */           entity_can_be_ignited = true;
/*     */         }
/* 113 */       } else if (entity instanceof EntityWolf) {
/*     */         
/* 115 */         if (entity instanceof EntityHellhound) {
/*     */           
/* 117 */           entity.getAsEntityLiving().setTarget(player);
/*     */         }
/* 119 */         else if (entity instanceof EntityDireWolf) {
/*     */           
/* 121 */           if (entity.getAsEntityTameable().isTamed()) {
/* 122 */             entity_can_be_ignited = true;
/*     */           }
/* 124 */           entity.getAsEntityLiving().setTarget(player);
/*     */         }
/*     */         else {
/*     */           
/* 128 */           entity_can_be_ignited = true;
/*     */         } 
/*     */       } 
/*     */       
/* 132 */       if (entity_can_be_ignited) {
/* 133 */         entity.setFire(6);
/*     */       }
/* 135 */       makeIgniteSoundAndApplyDamage(player);
/*     */     } 
/*     */     
/* 138 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 143 */     RaycastCollision rc = player.getSelectedObject(partial_tick, false);
/*     */     
/* 145 */     if (rc == null) {
/* 146 */       return false;
/*     */     }
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
/* 170 */     if (rc.isBlock()) {
/*     */       
/* 172 */       if (rc.getBlockHit() == Block.tnt) {
/*     */         
/* 174 */         if (player.onServer()) {
/* 175 */           BlockTNT.ignite(rc.world, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, player);
/*     */         }
/* 177 */       } else if (rc.isNeighborAirBlock() && rc.canPlayerEditNeighborOfBlockHit(player, player.getHeldItemStack())) {
/*     */         
/* 179 */         if (player.onServer()) {
/* 180 */           rc.setNeighborBlock(Block.spark);
/*     */         }
/*     */       } else {
/*     */         
/* 184 */         return false;
/*     */       } 
/*     */       
/* 187 */       if (player.onClient()) {
/*     */         
/* 189 */         player.swingArm();
/*     */       }
/*     */       else {
/*     */         
/* 193 */         rc.world.playSoundAtEntity(player, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
/* 194 */         player.tryDamageHeldItem(DamageSource.generic, 1);
/*     */       } 
/*     */       
/* 197 */       return true;
/*     */     } 
/*     */     
/* 200 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemFlintAndSteel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */