/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityCrit2FX
/*    */   extends EntityFX
/*    */ {
/*    */   private Entity theEntity;
/*    */   private int currentLife;
/*    */   private int maximumLife;
/*    */   EnumParticle particle;
/*    */   
/*    */   public EntityCrit2FX(World par1World, Entity par2Entity) {
/* 15 */     this(par1World, par2Entity, EnumParticle.crit);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityCrit2FX(World par1World, Entity par2Entity, EnumParticle particle) {
/* 21 */     super(par1World, par2Entity.posX, par2Entity.boundingBox.minY + (par2Entity.height / 2.0F), par2Entity.posZ, par2Entity.motionX, par2Entity.motionY, par2Entity.motionZ);
/* 22 */     this.theEntity = par2Entity;
/* 23 */     this.maximumLife = 3;
/*    */     
/* 25 */     this.particle = particle;
/* 26 */     onUpdate();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 36 */     for (int var1 = 0; var1 < 16; var1++) {
/*    */       
/* 38 */       double var2 = (this.rand.nextFloat() * 2.0F - 1.0F);
/* 39 */       double var4 = (this.rand.nextFloat() * 2.0F - 1.0F);
/* 40 */       double var6 = (this.rand.nextFloat() * 2.0F - 1.0F);
/*    */       
/* 42 */       if (var2 * var2 + var4 * var4 + var6 * var6 <= 1.0D) {
/*    */         
/* 44 */         double var8 = this.theEntity.posX + var2 * this.theEntity.width / 4.0D;
/* 45 */         double var10 = this.theEntity.boundingBox.minY + (this.theEntity.height / 2.0F) + var4 * this.theEntity.height / 4.0D;
/* 46 */         double var12 = this.theEntity.posZ + var6 * this.theEntity.width / 4.0D;
/*    */         
/* 48 */         this.worldObj.spawnParticle(this.particle, var8, var10, var12, var2, var4 + 0.2D, var6);
/*    */       } 
/*    */     } 
/*    */     
/* 52 */     this.currentLife++;
/*    */     
/* 54 */     if (this.currentLife >= this.maximumLife)
/*    */     {
/* 56 */       setDead();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFXLayer() {
/* 62 */     return 3;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityCrit2FX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */