/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderMinecartMobSpawner
/*    */   extends RenderMinecart
/*    */ {
/*    */   protected void func_98192_a(EntityMinecartMobSpawner entityMinecartMobSpawner, float f, Block block, int i) {
/* 10 */     super.renderBlockInMinecart(entityMinecartMobSpawner, f, block, i);
/*    */     
/* 12 */     if (block == Block.mobSpawner)
/* 13 */       TileEntityMobSpawnerRenderer.func_98144_a(entityMinecartMobSpawner.func_98039_d(), entityMinecartMobSpawner.posX, entityMinecartMobSpawner.posY, entityMinecartMobSpawner.posZ, f); 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderMinecartMobSpawner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */