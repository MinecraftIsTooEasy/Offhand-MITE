/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileEntityEnderChestRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 11 */   private static final ResourceLocation field_110637_a = new ResourceLocation("textures/entity/chest/ender.png");
/* 12 */   private ModelChest theEnderChestModel = new ModelChest();
/*    */ 
/*    */   
/*    */   public void renderEnderChest(TileEntityEnderChest tileEntityEnderChest, double d, double e, double f, float g) {
/* 16 */     int i = 0;
/*    */     
/* 18 */     if (tileEntityEnderChest.hasWorldObj()) {
/* 19 */       i = tileEntityEnderChest.getBlockMetadata();
/*    */     }
/*    */     
/* 22 */     bindTexture(field_110637_a);
/*    */     
/* 24 */     GL11.glPushMatrix();
/* 25 */     GL11.glEnable(32826);
/* 26 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 27 */     GL11.glTranslatef((float)d, (float)e + 1.0F, (float)f + 1.0F);
/* 28 */     GL11.glScalef(1.0F, -1.0F, -1.0F);
/*    */     
/* 30 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/* 31 */     short s = 0;
/* 32 */     if (i == 2) s = 180; 
/* 33 */     if (i == 3) s = 0; 
/* 34 */     if (i == 4) s = 90; 
/* 35 */     if (i == 5) s = -90;
/*    */     
/* 37 */     GL11.glRotatef(s, 0.0F, 1.0F, 0.0F);
/* 38 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*    */     
/* 40 */     float f1 = tileEntityEnderChest.prevLidAngle + (tileEntityEnderChest.lidAngle - tileEntityEnderChest.prevLidAngle) * g;
/* 41 */     f1 = 1.0F - f1;
/* 42 */     f1 = 1.0F - f1 * f1 * f1;
/*    */     
/* 44 */     this.theEnderChestModel.chestLid.rotateAngleX = -(f1 * 3.1415927F / 2.0F);
/* 45 */     this.theEnderChestModel.renderAll();
/* 46 */     GL11.glDisable(32826);
/* 47 */     GL11.glPopMatrix();
/* 48 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityEnderChestRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */