/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityDireWolf
/*    */   extends EntityWolf
/*    */ {
/*    */   public EntityDireWolf(World par1World) {
/*  9 */     super(par1World);
/*    */     
/* 11 */     setSize(0.6F, 0.8F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void applyEntityAttributes() {
/* 17 */     super.applyEntityAttributes();
/*    */     
/* 19 */     setEntityAttribute(SharedMonsterAttributes.maxHealth, isTamed() ? 24.0D : 16.0D);
/*    */     
/* 21 */     setEntityAttribute(SharedMonsterAttributes.attackDamage, 5.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   protected String getLivingSound() {
/* 26 */     if (!isChild() && !isTamed() && !isInLove() && !this.worldObj.isBlueMoonNight() && (getClosestVulnerablePlayer(4.0D) != null || this.rand.nextFloat() < 0.1F)) {
/* 27 */       return "mob.wolf.growl";
/*    */     }
/* 29 */     return super.getLivingSound();
/*    */   }
/*    */ 
/*    */   
/*    */   protected float getSoundVolume(String sound) {
/* 34 */     return super.getSoundVolume(sound) * 1.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float getSoundPitch(String sound) {
/* 39 */     return super.getSoundPitch(sound) * 0.8F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 44 */     super.onUpdate();
/*    */     
/* 46 */     if (!this.worldObj.isRemote && !isChild() && !isTamed() && !isInLove() && !this.worldObj.isBlueMoonNight() && this.rand.nextFloat() < 0.004F) {
/*    */       
/* 48 */       EntityPlayer player = getClosestVulnerablePlayer(4.0D);
/*    */       
/* 50 */       if (player != null) {
/* 51 */         setAttackTarget(player);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getTamingOutcome(EntityPlayer player) {
/* 64 */     float roll = this.rand.nextFloat();
/*    */     
/* 66 */     if (roll < 0.2F)
/* 67 */       return -1; 
/* 68 */     if (roll < 0.4F)
/* 69 */       return 0; 
/* 70 */     if (roll > 0.95F) {
/* 71 */       return 1;
/*    */     }
/* 73 */     roll += this.rand.nextFloat() * player.getExperienceLevel() * 0.02F;
/*    */     
/* 75 */     return (roll < 0.5F) ? -1 : ((roll < 1.0F) ? 0 : 1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getExperienceValue() {
/* 85 */     return super.getExperienceValue() * 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityDireWolf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */