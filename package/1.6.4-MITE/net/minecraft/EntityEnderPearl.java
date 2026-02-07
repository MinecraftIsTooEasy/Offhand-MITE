/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityEnderPearl
/*    */   extends EntityThrowable
/*    */ {
/*    */   public EntityEnderPearl(World par1World) {
/*  7 */     super(par1World);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityEnderPearl(World par1World, EntityLivingBase par2EntityLivingBase) {
/* 12 */     super(par1World, par2EntityLivingBase);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityEnderPearl(World par1World, double par2, double par4, double par6) {
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
/* 31 */       rc.getEntityHit().attackEntityFrom((new Damage(DamageSource.causeThrownDamage(this, getThrower()), 1.0F)).setKnockbackOnly());
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 36 */     for (int var2 = 0; var2 < 32; var2++)
/*    */     {
/*    */       
/* 39 */       this.worldObj.spawnParticle(EnumParticle.portal_underworld, this.posX, this.posY + this.rand.nextDouble() * 2.0D, this.posZ, this.rand.nextGaussian(), 0.0D, this.rand.nextGaussian());
/*    */     }
/*    */     
/* 42 */     if (!this.worldObj.isRemote) {
/*    */       
/* 44 */       if (getThrower() != null && getThrower() instanceof ServerPlayer) {
/*    */         
/* 46 */         ServerPlayer var3 = (ServerPlayer)getThrower();
/*    */         
/* 48 */         if (!var3.playerNetServerHandler.connectionClosed && var3.worldObj == this.worldObj) {
/*    */           
/* 50 */           if (getThrower().isRiding())
/*    */           {
/* 52 */             getThrower().mountEntity((Entity)null);
/*    */           }
/*    */           
/* 55 */           getThrower().setPositionAndUpdate(this.posX, this.posY, this.posZ);
/* 56 */           (getThrower()).fallDistance = 0.0F;
/*    */ 
/*    */           
/* 59 */           playSound("mob.endermen.portal", 1.0F, 1.0F);
/*    */         } 
/*    */       } 
/*    */       
/* 63 */       setDead();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Item getModelItem() {
/* 69 */     return Item.enderPearl;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityEnderPearl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */