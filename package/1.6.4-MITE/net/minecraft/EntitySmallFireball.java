/*     */ package net.minecraft;
/*     */ 
/*     */ public class EntitySmallFireball
/*     */   extends EntityFireball
/*     */ {
/*     */   public EntitySmallFireball(World par1World) {
/*   7 */     super(par1World);
/*   8 */     setSize(0.3125F, 0.3125F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySmallFireball(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7) {
/*  13 */     super(par1World, par2EntityLivingBase, par3, par5, par7);
/*  14 */     setSize(0.3125F, 0.3125F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySmallFireball(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
/*  19 */     super(par1World, par2, par4, par6, par8, par10, par12);
/*  20 */     setSize(0.3125F, 0.3125F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onImpact(RaycastCollision rc) {
/*  28 */     if (!this.worldObj.isRemote) {
/*     */ 
/*     */       
/*  31 */       if (rc.isEntity()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  38 */         rc.getEntityHit().attackEntityFrom(new Damage(DamageSource.causeFireballDamage(this, this.shootingEntity), 2.0F));
/*     */         
/*  40 */         rc.getEntityHit().setFire(5);
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
/*     */       }
/*  82 */       else if (rc.isNeighborAirBlock()) {
/*  83 */         rc.setNeighborBlock(Block.fire);
/*     */       } 
/*     */       
/*  86 */       setDead();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/*  95 */     return false;
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
/*     */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 110 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCollisionPolicies(Raycast raycast) {
/* 116 */     raycast.setForPiercingProjectile(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntitySmallFireball.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */