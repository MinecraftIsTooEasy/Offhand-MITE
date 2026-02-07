/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityLargeFireball
/*    */   extends EntityFireball {
/*  5 */   public int field_92057_e = 1;
/*    */ 
/*    */   
/*    */   public EntityLargeFireball(World par1World) {
/*  9 */     super(par1World);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityLargeFireball(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
/* 14 */     super(par1World, par2, par4, par6, par8, par10, par12);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityLargeFireball(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7) {
/* 19 */     super(par1World, par2EntityLivingBase, par3, par5, par7);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityLargeFireball(World world, EntityLivingBase shooter, Vec3 target, float initial_distance) {
/* 25 */     super(world, shooter, target, initial_distance);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityLargeFireball(World world, EntityLivingBase shooter, Vec3 origin, Vec3 target, float initial_distance) {
/* 31 */     super(world, shooter, origin, target, initial_distance);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void onImpact(RaycastCollision rc) {
/* 39 */     if (!this.worldObj.isRemote) {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 46 */       if (rc.isEntity()) {
/* 47 */         rc.getEntityHit().attackEntityFrom(new Damage(DamageSource.causeFireballDamage(this, this.shootingEntity), 6.0F));
/*    */       }
/*    */       
/* 50 */       this.worldObj.newExplosion((Entity)null, this.posX, this.posY, this.posZ, this.field_92057_e, this.field_92057_e, true, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
/* 51 */       setDead();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 60 */     super.writeEntityToNBT(par1NBTTagCompound);
/* 61 */     par1NBTTagCompound.setInteger("ExplosionPower", this.field_92057_e);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 69 */     super.readEntityFromNBT(par1NBTTagCompound);
/*    */     
/* 71 */     if (par1NBTTagCompound.hasKey("ExplosionPower"))
/*    */     {
/* 73 */       this.field_92057_e = par1NBTTagCompound.getInteger("ExplosionPower");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCollisionPolicies(Raycast raycast) {
/* 79 */     raycast.setForBluntProjectile(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityLargeFireball.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */