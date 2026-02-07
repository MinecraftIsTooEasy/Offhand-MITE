/*    */ package net.minecraft;
/*    */ 
/*    */ public abstract class EntityWeatherEffect
/*    */   extends Entity
/*    */ {
/*    */   public EntityWeatherEffect(World par1World) {
/*  7 */     super(par1World);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canCatchFire() {
/* 12 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isHarmedByFire() {
/* 17 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isHarmedByLava() {
/* 22 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityWeatherEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */