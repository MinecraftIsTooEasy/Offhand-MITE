/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class EntityLeashKnot
/*     */   extends EntityHanging
/*     */ {
/*     */   public EntityLeashKnot(World par1World) {
/*  10 */     super(par1World);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLeashKnot(World par1World, int par2, int par3, int par4) {
/*  15 */     super(par1World, par2, par3, par4, 0);
/*  16 */     setPosition(par2 + 0.5D, par3 + 0.5D, par4 + 0.5D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  21 */     super.entityInit();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDirection(int par1) {}
/*     */   
/*     */   public int getWidthPixels() {
/*  28 */     return 9;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeightPixels() {
/*  33 */     return 9;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInRangeToRenderDist(double par1) {
/*  42 */     return (par1 < 1024.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBroken(Entity par1Entity) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isWrittenToChunkNBT() {
/*  52 */     return false;
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
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}
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
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {}
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
/*     */   public boolean onEntityRightClicked(EntityPlayer player, ItemStack item_stack) {
/* 140 */     if (player.onClient()) {
/* 141 */       player.swingArm();
/*     */     }
/* 143 */     if (!ItemLeash.transferLeashedEntitiesToAnotherEntity(player, this, true)) {
/*     */       
/* 145 */       ItemLeash.unleashEntitiesThatAreLeashedToEntity(this, !player.inCreativeMode(), true);
/* 146 */       setDead();
/*     */     } 
/*     */     
/* 149 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onValidSurface() {
/* 157 */     int var1 = this.worldObj.getBlockId(this.xPosition, this.yPosition, this.zPosition);
/* 158 */     return (Block.blocksList[var1] != null && Block.blocksList[var1].getRenderType() == 11);
/*     */   }
/*     */ 
/*     */   
/*     */   public static EntityLeashKnot func_110129_a(World par0World, int par1, int par2, int par3) {
/* 163 */     EntityLeashKnot var4 = new EntityLeashKnot(par0World, par1, par2, par3);
/* 164 */     var4.forceSpawn = true;
/* 165 */     par0World.spawnEntityInWorld(var4);
/* 166 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   public static EntityLeashKnot getKnotForBlock(World par0World, int par1, int par2, int par3) {
/* 171 */     List var4 = par0World.getEntitiesWithinAABB(EntityLeashKnot.class, AxisAlignedBB.getAABBPool().getAABB(par1 - 1.0D, par2 - 1.0D, par3 - 1.0D, par1 + 1.0D, par2 + 1.0D, par3 + 1.0D));
/* 172 */     Object var5 = null;
/*     */     
/* 174 */     if (var4 != null) {
/*     */       
/* 176 */       Iterator<EntityLeashKnot> var6 = var4.iterator();
/*     */       
/* 178 */       while (var6.hasNext()) {
/*     */         
/* 180 */         EntityLeashKnot var7 = var6.next();
/*     */         
/* 182 */         if (var7.xPosition == par1 && var7.yPosition == par2 && var7.zPosition == par3)
/*     */         {
/* 184 */           return var7;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 189 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getModelItem() {
/* 194 */     return Item.leash;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityLeashKnot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */