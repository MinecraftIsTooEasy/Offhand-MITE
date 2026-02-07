/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityEgg
/*    */   extends EntityThrowable
/*    */ {
/*    */   public EntityEgg(World par1World) {
/*  7 */     super(par1World);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityEgg(World par1World, EntityLivingBase par2EntityLivingBase) {
/* 12 */     super(par1World, par2EntityLivingBase);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityEgg(World par1World, double par2, double par4, double par6) {
/* 17 */     super(par1World, par2, par4, par6);
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
/*    */   protected void onImpact(RaycastCollision rc) {
/* 30 */     if (rc.isEntity()) {
/* 31 */       rc.getEntityHit().attackEntityFrom(new Damage(DamageSource.causeThrownDamage(this, getThrower()), 1.0F));
/*    */     }
/* 33 */     if (!this.worldObj.isRemote && this.rand.nextInt(8) == 0) {
/*    */       
/* 35 */       byte var2 = 1;
/*    */       
/* 37 */       if (this.rand.nextInt(32) == 0)
/*    */       {
/* 39 */         var2 = 4;
/*    */       }
/*    */       
/* 42 */       for (int var3 = 0; var3 < var2; var3++) {
/*    */         
/* 44 */         EntityChicken var4 = new EntityChicken(this.worldObj);
/*    */         
/* 46 */         var4.setGrowingAgeToNewborn();
/* 47 */         var4.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
/* 48 */         this.worldObj.spawnEntityInWorld(var4);
/*    */       } 
/*    */     } 
/*    */     
/* 52 */     for (int var5 = 0; var5 < 8; var5++)
/*    */     {
/*    */       
/* 55 */       this.worldObj.spawnParticle(EnumParticle.snowballpoof, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
/*    */     }
/*    */     
/* 58 */     if (!this.worldObj.isRemote)
/*    */     {
/* 60 */       setDead();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public Item getModelItem() {
/* 66 */     return Item.egg;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityEgg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */