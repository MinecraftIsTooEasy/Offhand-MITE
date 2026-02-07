/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityNetherspawn
/*    */   extends EntitySilverfish
/*    */ {
/*    */   private int ticks_until_next_fizz_sound;
/*    */   
/*    */   public EntityNetherspawn(World world) {
/* 11 */     super(world);
/*    */     
/* 13 */     getNavigator().setAvoidsWater(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onLivingUpdate() {
/* 18 */     if (onClient()) {
/*    */       
/* 20 */       if (isWet()) {
/* 21 */         spawnSteamParticles(this.inWater ? 10 : 1);
/*    */       }
/* 23 */       this.worldObj.spawnParticle(EnumParticle.largesmoke, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, 0.0D, 0.0D, 0.0D);
/*    */ 
/*    */     
/*    */     }
/* 27 */     else if (isWet()) {
/*    */       
/* 29 */       if (--this.ticks_until_next_fizz_sound <= 0) {
/*    */         
/* 31 */         playSound("random.fizz", 0.7F, 1.6F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
/* 32 */         this.ticks_until_next_fizz_sound = this.rand.nextInt(7) + 2;
/*    */         
/* 34 */         if (this.rand.nextInt(this.inWater ? 1 : 4) == 0) {
/* 35 */           attackEntityFrom(new Damage(DamageSource.water, 1.0F));
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 40 */     super.onLivingUpdate();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isHarmedByFire() {
/* 45 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFragParticle() {
/* 50 */     return Item.fragsNetherspawn.itemID;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldExplodeBlock(Explosion explosion, World world, int x, int y, int z, int block_id, float force) {
/* 55 */     if (block_id == Block.netherrack.blockID || block_id == Block.oreNetherQuartz.blockID || block_id == Block.oreGold.blockID) {
/* 56 */       return false;
/*    */     }
/* 58 */     return super.shouldExplodeBlock(explosion, world, x, y, z, block_id, force);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canTakeDamageFromPlayerThrownSnowballs() {
/* 63 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 68 */     EntityDamageResult result = super.attackEntityFrom(damage);
/*    */     
/* 70 */     if (result != null && result.entityWasDestroyed())
/*    */     {
/* 72 */       if (!isWet() && !isInsideOfMaterial(Material.lava) && !damage.isSnowball() && damage.getSource() != DamageSource.water) {
/*    */         
/* 74 */         entityFX(EnumEntityFX.frags);
/*    */         
/* 76 */         this.worldObj.createExplosion(this, this.posX, this.posY + (this.height / 4.0F), this.posZ, 1.0F, 1.0F, true);
/*    */         
/* 78 */         setDead();
/*    */       } 
/*    */     }
/*    */     
/* 82 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityNetherspawn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */