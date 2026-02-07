/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class EntityHugeExplodeFX
/*    */   extends EntityFX
/*    */ {
/*    */   private int timeSinceStart;
/*  8 */   private int maximumTime = 8;
/*    */ 
/*    */   
/*    */   public EntityHugeExplodeFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
/* 12 */     super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 22 */     for (int var1 = 0; var1 < 6; var1++) {
/*    */       
/* 24 */       double var2 = this.posX + (this.rand.nextDouble() - this.rand.nextDouble()) * 4.0D;
/* 25 */       double var4 = this.posY + (this.rand.nextDouble() - this.rand.nextDouble()) * 4.0D;
/* 26 */       double var6 = this.posZ + (this.rand.nextDouble() - this.rand.nextDouble()) * 4.0D;
/*    */       
/* 28 */       this.worldObj.spawnParticle(EnumParticle.largeexplode, var2, var4, var6, (this.timeSinceStart / this.maximumTime), 0.0D, 0.0D);
/*    */     } 
/*    */     
/* 31 */     this.timeSinceStart++;
/*    */     
/* 33 */     if (this.timeSinceStart == this.maximumTime)
/*    */     {
/* 35 */       setDead();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFXLayer() {
/* 41 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityHugeExplodeFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */