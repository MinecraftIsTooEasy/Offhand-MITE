/*     */ package net.minecraft;
/*     */ 
/*     */ public abstract class EntityAgeable
/*     */   extends EntityCreature {
/*   5 */   private float field_98056_d = -1.0F;
/*     */   
/*     */   private float field_98057_e;
/*     */   
/*     */   public EntityAgeable(World par1World) {
/*  10 */     super(par1World);
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
/*     */   public abstract EntityAgeable createChild(EntityAgeable paramEntityAgeable);
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
/*     */   protected void entityInit() {
/*  67 */     super.entityInit();
/*  68 */     this.dataWatcher.addObject(12, new Integer(0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGrowingAge() {
/*  78 */     return this.dataWatcher.getWatchableObjectInt(12);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addGrowth(int par1) {
/*  87 */     int var2 = getGrowingAge();
/*     */     
/*  89 */     var2 += par1 * 5;
/*     */     
/*  91 */     if (var2 > 0)
/*     */     {
/*  93 */       var2 = 0;
/*     */     }
/*     */     
/*  96 */     setGrowingAge(var2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGrowingAge(int par1) {
/* 105 */     boolean was_child = isChild();
/*     */     
/* 107 */     if (was_child && par1 >= 0 && this.worldObj.getBlock(getBlockPosX(), getBlockPosY(), getBlockPosZ()) == Block.cauldron)
/*     */     {
/* 109 */       if (!this.worldObj.isAirOrPassableBlock(getBlockPosX(), getBlockPosY() + 1, getBlockPosZ(), true)) {
/* 110 */         par1 = -1;
/*     */       }
/*     */     }
/* 113 */     this.dataWatcher.updateObject(12, Integer.valueOf(par1));
/* 114 */     setScaleForAge(isChild());
/*     */     
/* 116 */     if (was_child && !isChild()) {
/* 117 */       pushOutOfBlocks();
/*     */     }
/*     */   }
/*     */   
/*     */   public static int getGrowingAgeOfNewborn() {
/* 122 */     return -64000;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGrowingAgeToNewborn() {
/* 127 */     setGrowingAge(getGrowingAgeOfNewborn());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGrowingAgeAfterBreeding() {
/* 132 */     setGrowingAge(16000);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 140 */     super.writeEntityToNBT(par1NBTTagCompound);
/* 141 */     par1NBTTagCompound.setInteger("Age", getGrowingAge());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 149 */     super.readEntityFromNBT(par1NBTTagCompound);
/* 150 */     setGrowingAge(par1NBTTagCompound.getInteger("Age"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 159 */     super.onLivingUpdate();
/*     */     
/* 161 */     if (this.worldObj.isRemote) {
/*     */       
/* 163 */       setScaleForAge(isChild());
/*     */     }
/*     */     else {
/*     */       
/* 167 */       int var1 = getGrowingAge();
/*     */       
/* 169 */       if (var1 < 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 174 */         if (this instanceof EntityHorse) {
/*     */           
/* 176 */           if (getTicksExistedWithOffset() % 200 == 0) {
/* 177 */             setGrowingAge(Math.min(var1 + 200, 0));
/*     */           }
/*     */         } else {
/*     */           
/* 181 */           var1++;
/* 182 */           setGrowingAge(var1);
/*     */         }
/*     */       
/* 185 */       } else if (var1 > 0) {
/*     */         
/* 187 */         var1--;
/* 188 */         setGrowingAge(var1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isChild() {
/* 198 */     return (getGrowingAge() < 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setScaleForAge(boolean par1) {
/* 206 */     setScale(par1 ? 0.5F : 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void setSize(float par1, float par2) {
/* 214 */     boolean var3 = (this.field_98056_d > 0.0F);
/* 215 */     this.field_98056_d = par1;
/* 216 */     this.field_98057_e = par2;
/*     */     
/* 218 */     if (!var3)
/*     */     {
/* 220 */       setScale(1.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void setScale(float par1) {
/* 226 */     super.setSize(this.field_98056_d * par1, this.field_98057_e * par1);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAgeable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */