/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntitySpider
/*    */   extends EntityArachnid
/*    */ {
/*    */   public EntitySpider(World par1World) {
/*  9 */     super(par1World, 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void applyEntityAttributes() {
/* 14 */     super.applyEntityAttributes();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private EntitySkeleton getMountedSkeleton() {
/*    */     while (true) {
/* 21 */       EntitySkeleton var2 = this.worldObj.isUnderworld() ? new EntityLongdead(this.worldObj) : new EntitySkeleton(this.worldObj);
/* 22 */       var2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
/* 23 */       var2.onSpawnWithEgg((EntityLivingData)null);
/*    */       
/* 25 */       if (var2.getSkeletonType() == 0) {
/* 26 */         return var2;
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
/* 32 */     Object par1EntityLivingData1 = super.onSpawnWithEgg(par1EntityLivingData);
/*    */     
/* 34 */     if (this.worldObj.rand.nextInt(100) == 0) {
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 39 */       EntitySkeleton var2 = getMountedSkeleton();
/* 40 */       this.worldObj.spawnEntityInWorld(var2);
/* 41 */       var2.mountEntity(this);
/*    */     } 
/*    */     
/* 44 */     if (par1EntityLivingData1 == null) {
/*    */       
/* 46 */       par1EntityLivingData1 = new SpiderEffectsGroupData();
/*    */       
/* 48 */       if (this.worldObj.difficultySetting > 2 && this.worldObj.rand.nextFloat() < 0.1F * this.worldObj.getLocationTensionFactor(this.posX, this.posY, this.posZ))
/*    */       {
/* 50 */         ((SpiderEffectsGroupData)par1EntityLivingData1).func_111104_a(this.worldObj.rand);
/*    */       }
/*    */     } 
/*    */     
/* 54 */     if (par1EntityLivingData1 instanceof SpiderEffectsGroupData) {
/*    */       
/* 56 */       int var4 = ((SpiderEffectsGroupData)par1EntityLivingData1).field_111105_a;
/*    */       
/* 58 */       if (var4 > 0 && Potion.potionTypes[var4] != null)
/*    */       {
/* 60 */         addPotionEffect(new PotionEffect(var4, 2147483647));
/*    */       }
/*    */     } 
/*    */     
/* 64 */     return (EntityLivingData)par1EntityLivingData1;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntitySpider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */