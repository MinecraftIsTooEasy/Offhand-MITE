/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityExpBottle
/*    */   extends EntityThrowable
/*    */ {
/*    */   public EntityExpBottle(World par1World) {
/*  7 */     super(par1World);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityExpBottle(World par1World, EntityLivingBase par2EntityLivingBase) {
/* 12 */     super(par1World, par2EntityLivingBase);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityExpBottle(World par1World, double par2, double par4, double par6) {
/* 17 */     super(par1World, par2, par4, par6);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected float getGravityVelocity() {
/* 25 */     return 0.07F;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float func_70182_d() {
/* 30 */     return 0.7F;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float func_70183_g() {
/* 35 */     return -20.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void onImpact(RaycastCollision rc) {
/* 43 */     if (!this.worldObj.isRemote) {
/*    */       
/* 45 */       this.worldObj.playAuxSFX(2002, (int)Math.round(this.posX), (int)Math.round(this.posY), (int)Math.round(this.posZ), 0);
/*    */       
/* 47 */       int var2 = Enchantment.getExperienceCost(2);
/*    */       
/* 49 */       while (var2 > 0) {
/*    */         
/* 51 */         int var3 = EntityXPOrb.getXPSplit(var2);
/* 52 */         var2 -= var3;
/*    */         
/* 54 */         this.worldObj.spawnEntityInWorld((new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, var3)).setCreatedByBottleOfEnchanting());
/*    */       } 
/*    */       
/* 57 */       setDead();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Item getModelItem() {
/* 63 */     return Item.expBottle;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityExpBottle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */