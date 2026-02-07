/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityBrick
/*    */   extends EntityThrowable
/*    */ {
/*    */   private Item item;
/*    */   
/*    */   public EntityBrick(World world, Item item) {
/* 13 */     super(world);
/* 14 */     this.item = item;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityBrick(World world, EntityLivingBase thrower, Item item) {
/* 19 */     super(world, thrower);
/* 20 */     this.item = item;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityBrick(World world, double pos_x, double pos_y, double pos_z, Item item) {
/* 25 */     super(world, pos_x, pos_y, pos_z);
/* 26 */     this.item = item;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float getGravityVelocity() {
/* 31 */     return 0.07F;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void onImpact(RaycastCollision rc) {
/* 36 */     if (rc.isEntity()) {
/* 37 */       rc.getEntityHit().attackEntityFrom(new Damage(DamageSource.causeThrownDamage(this, getThrower()), 2.0F));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 42 */     if (onServer() && rc.isBlock())
/*    */     {
/* 44 */       if (rc.getBlockHit() == Block.thinGlass) {
/* 45 */         this.worldObj.destroyBlock(new BlockBreakInfo(this.worldObj, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z), true);
/*    */       } else {
/* 47 */         rc.getBlockHit().onEntityCollidedWithBlock(this.worldObj, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, this);
/*    */       } 
/*    */     }
/* 50 */     for (int var5 = 0; var5 < 8; var5++) {
/* 51 */       this.worldObj.spawnParticle((this.item == Item.netherrackBrick) ? EnumParticle.brickpoof_netherrack : EnumParticle.brickpoof, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
/*    */     }
/* 53 */     if (!this.worldObj.isRemote) {
/* 54 */       setDead();
/*    */     }
/*    */   }
/*    */   
/*    */   public Item getModelItem() {
/* 59 */     return this.item;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityBrick.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */