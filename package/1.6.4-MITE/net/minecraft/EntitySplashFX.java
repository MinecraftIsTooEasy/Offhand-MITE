/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntitySplashFX
/*    */   extends EntityRainFX
/*    */ {
/*    */   public EntitySplashFX(World world, double d, double e, double f, double g, double h, double i) {
/*  7 */     super(world, d, e, f);
/*  8 */     this.particleGravity = 0.04F;
/*  9 */     nextTextureIndexX();
/* 10 */     if (h == 0.0D && (g != 0.0D || i != 0.0D)) {
/* 11 */       this.motionX = g;
/* 12 */       this.motionY = h + 0.1D;
/* 13 */       this.motionZ = i;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntitySplashFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */