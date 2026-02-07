/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityLongdeadGuardian
/*     */   extends EntityLongdead
/*     */ {
/*     */   ItemStack stowed_item_stack;
/*     */   
/*     */   public EntityLongdeadGuardian(World world) {
/*  11 */     super(world);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addRandomWeapon() {
/*  16 */     super.addRandomWeapon();
/*     */     
/*  18 */     if (getHeldItem() instanceof ItemBow) {
/*  19 */       this.stowed_item_stack = (new ItemStack(Item.daggerAncientMetal)).randomizeForMob(this, true);
/*     */     }
/*     */   }
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  24 */     super.readEntityFromNBT(par1NBTTagCompound);
/*     */     
/*  26 */     if (par1NBTTagCompound.hasKey("stowed_item_stack")) {
/*  27 */       this.stowed_item_stack = ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("stowed_item_stack"));
/*     */     } else {
/*  29 */       this.stowed_item_stack = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/*  34 */     super.writeEntityToNBT(par1NBTTagCompound);
/*     */     
/*  36 */     if (this.stowed_item_stack != null) {
/*     */       
/*  38 */       NBTTagCompound compound = new NBTTagCompound();
/*  39 */       this.stowed_item_stack.writeToNBT(compound);
/*     */       
/*  41 */       par1NBTTagCompound.setCompoundTag("stowed_item_stack", compound);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getStowedItemStack() {
/*  47 */     return this.stowed_item_stack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void swapHeldItemStackWithStowed() {
/*  52 */     ItemStack item_stack = this.stowed_item_stack;
/*     */     
/*  54 */     this.stowed_item_stack = getHeldItemStack();
/*     */     
/*  56 */     setHeldItemStack(item_stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canStowItem(Item item) {
/*  61 */     if (getSkeletonType() != 0) {
/*  62 */       return false;
/*     */     }
/*  64 */     return (item instanceof ItemDagger || item instanceof ItemBow);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canNeverPickUpItem(Item item) {
/*  69 */     return (getSkeletonType() == 0 && !canStowItem(item));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHoldingRangedWeapon() {
/*  74 */     return getHeldItem() instanceof ItemBow;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  79 */     super.onLivingUpdate();
/*     */     
/*  81 */     if (this.stowed_item_stack != null && (getHeldItemStack() == null || getTicksExistedWithOffset() % 10 == 0))
/*     */     {
/*  83 */       if (getHeldItemStack() == null) {
/*     */         
/*  85 */         swapHeldItemStackWithStowed();
/*     */       }
/*     */       else {
/*     */         
/*  89 */         Entity target = getTarget();
/*     */         
/*  91 */         if (target != null && canSeeTarget(true)) {
/*     */           
/*  93 */           double distance = getDistanceToEntity(target);
/*     */           
/*  95 */           if (isHoldingRangedWeapon()) {
/*     */             
/*  97 */             if (distance < 5.0D) {
/*  98 */               swapHeldItemStackWithStowed();
/*     */             
/*     */             }
/*     */           }
/* 102 */           else if (distance > 6.0D) {
/* 103 */             swapHeldItemStackWithStowed();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityLongdeadGuardian.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */