/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class TileEntityMobSpawnerRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/*    */   public void renderTileEntityMobSpawner(TileEntityMobSpawner par1TileEntityMobSpawner, double par2, double par4, double par6, float par8) {
/*  9 */     GL11.glPushMatrix();
/* 10 */     GL11.glTranslatef((float)par2 + 0.5F, (float)par4, (float)par6 + 0.5F);
/* 11 */     func_98144_a(par1TileEntityMobSpawner.getSpawnerLogic(), par2, par4, par6, par8);
/* 12 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void func_98144_a(MobSpawnerBaseLogic par0MobSpawnerBaseLogic, double par1, double par3, double par5, float par7) {
/* 17 */     Entity var8 = par0MobSpawnerBaseLogic.func_98281_h();
/*    */     
/* 19 */     if (var8 != null) {
/*    */       
/* 21 */       var8.setWorld(par0MobSpawnerBaseLogic.getSpawnerWorld());
/* 22 */       float var9 = 0.4375F;
/* 23 */       GL11.glTranslatef(0.0F, 0.4F, 0.0F);
/* 24 */       GL11.glRotatef((float)(par0MobSpawnerBaseLogic.field_98284_d + (par0MobSpawnerBaseLogic.field_98287_c - par0MobSpawnerBaseLogic.field_98284_d) * par7) * 10.0F, 0.0F, 1.0F, 0.0F);
/* 25 */       GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
/* 26 */       GL11.glTranslatef(0.0F, -0.4F, 0.0F);
/* 27 */       GL11.glScalef(var9, var9, var9);
/* 28 */       var8.setLocationAndAngles(par1, par3, par5, 0.0F, 0.0F);
/* 29 */       RenderManager.instance.renderEntityWithPosYaw(var8, 0.0D, 0.0D, 0.0D, 0.0F, par7);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
/* 35 */     if (par1TileEntity instanceof TileEntityMobSpawner && !((TileEntityMobSpawner)par1TileEntity).getSpawnerLogic().canRun()) {
/*    */       return;
/*    */     }
/* 38 */     renderTileEntityMobSpawner((TileEntityMobSpawner)par1TileEntity, par2, par4, par6, par8);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityMobSpawnerRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */