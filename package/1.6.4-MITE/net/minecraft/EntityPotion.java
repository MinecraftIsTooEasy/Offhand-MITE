/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityPotion
/*     */   extends EntityThrowable
/*     */ {
/*     */   private ItemStack potionType;
/*     */   
/*     */   public EntityPotion(World par1World) {
/*  15 */     super(par1World);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPotion(World par1World, EntityLivingBase par2EntityLivingBase, int par3) {
/*  20 */     this(par1World, par2EntityLivingBase, new ItemStack(Item.potion, 1, par3));
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPotion(World par1World, EntityLivingBase par2EntityLivingBase, ItemStack par3ItemStack) {
/*  25 */     super(par1World, par2EntityLivingBase);
/*  26 */     this.potionType = par3ItemStack;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPotion(World par1World, double par2, double par4, double par6, int par8) {
/*  31 */     this(par1World, par2, par4, par6, new ItemStack(Item.potion, 1, par8));
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPotion(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack) {
/*  36 */     super(par1World, par2, par4, par6);
/*  37 */     this.potionType = par8ItemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getGravityVelocity() {
/*  45 */     return 0.05F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70182_d() {
/*  50 */     return 0.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70183_g() {
/*  55 */     return -20.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPotionType(int par1) {
/*  60 */     if (this.potionType == null)
/*     */     {
/*  62 */       this.potionType = new ItemStack(Item.potion, 1, 0);
/*     */     }
/*     */     
/*  65 */     this.potionType.setItemSubtype(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPotionType() {
/*  73 */     if (this.potionType == null)
/*     */     {
/*  75 */       this.potionType = new ItemStack(Item.potion, 1, 0);
/*     */     }
/*     */     
/*  78 */     return this.potionType.getItemSubtype();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onImpact(RaycastCollision rc) {
/*  86 */     if (!this.worldObj.isRemote) {
/*     */       
/*  88 */       List var2 = Item.potion.getEffects(this.potionType);
/*     */       
/*  90 */       if (var2 != null && !var2.isEmpty()) {
/*     */         
/*  92 */         AxisAlignedBB var3 = this.boundingBox.expand(4.0D, 2.0D, 4.0D);
/*  93 */         List var4 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, var3);
/*     */         
/*  95 */         if (var4 != null && !var4.isEmpty()) {
/*     */           
/*  97 */           Iterator<EntityLivingBase> var5 = var4.iterator();
/*     */           
/*  99 */           while (var5.hasNext()) {
/*     */             
/* 101 */             EntityLivingBase var6 = var5.next();
/* 102 */             double var7 = getDistanceSqToEntity(var6);
/*     */             
/* 104 */             if (var7 < 16.0D) {
/*     */               
/* 106 */               double var9 = 1.0D - Math.sqrt(var7) / 4.0D;
/*     */ 
/*     */               
/* 109 */               if (var6 == rc.getEntityHit())
/*     */               {
/* 111 */                 var9 = 1.0D;
/*     */               }
/*     */               
/* 114 */               Iterator<PotionEffect> var11 = var2.iterator();
/*     */               
/* 116 */               while (var11.hasNext()) {
/*     */                 
/* 118 */                 PotionEffect var12 = var11.next();
/* 119 */                 int var13 = var12.getPotionID();
/*     */                 
/* 121 */                 Potion potion = Potion.get(var13);
/*     */                 
/* 123 */                 boolean apply_effect = true;
/*     */                 
/* 125 */                 if (potion.isBadEffect()) {
/*     */                   
/* 127 */                   EntityLivingBase thrower = getThrower();
/*     */                   
/* 129 */                   if (var6 == thrower && !thrower.isEntityPlayer()) {
/* 130 */                     apply_effect = false;
/* 131 */                   } else if (thrower instanceof EntityWitch && var6 instanceof EntityWolf && ((EntityWolf)var6).is_witch_ally) {
/* 132 */                     apply_effect = false;
/*     */                   } 
/*     */                 } 
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
/* 149 */                 if (apply_effect) {
/*     */                   
/* 151 */                   if (Potion.potionTypes[var13].isInstant()) {
/*     */                     
/* 153 */                     Potion.potionTypes[var13].affectEntity(getThrower(), var6, var12.getAmplifier(), var9);
/*     */                     
/*     */                     continue;
/*     */                   } 
/* 157 */                   int var14 = (int)(var9 * var12.getDuration() + 0.5D);
/*     */                   
/* 159 */                   if (var14 > 20)
/*     */                   {
/* 161 */                     var6.addPotionEffect(new PotionEffect(var13, var14, var12.getAmplifier()));
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 171 */       this.worldObj.playAuxSFX(2002, (int)Math.round(this.posX), (int)Math.round(this.posY), (int)Math.round(this.posZ), getPotionType());
/* 172 */       setDead();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 181 */     super.readEntityFromNBT(par1NBTTagCompound);
/*     */     
/* 183 */     if (par1NBTTagCompound.hasKey("Potion")) {
/*     */       
/* 185 */       this.potionType = ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("Potion"));
/*     */     }
/*     */     else {
/*     */       
/* 189 */       setPotionType(par1NBTTagCompound.getInteger("potionValue"));
/*     */     } 
/*     */     
/* 192 */     if (this.potionType == null)
/*     */     {
/* 194 */       setDead();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 203 */     super.writeEntityToNBT(par1NBTTagCompound);
/*     */     
/* 205 */     if (this.potionType != null)
/*     */     {
/* 207 */       par1NBTTagCompound.setCompoundTag("Potion", this.potionType.writeToNBT(new NBTTagCompound()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getModelItem() {
/* 213 */     return Item.potion;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityPotion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */