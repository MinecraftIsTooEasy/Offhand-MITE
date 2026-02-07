/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderEnchantmentTable
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 10 */   private static final ResourceLocation enchantingTableBookTextures = new ResourceLocation("textures/entity/enchanting_table_book.png");
/* 11 */   private ModelBook enchantmentBook = new ModelBook();
/*    */ 
/*    */   
/*    */   public void renderTileEntityEnchantmentTableAt(TileEntityEnchantmentTable tileEntityEnchantmentTable, double d, double e, double f, float g) {
/* 15 */     GL11.glPushMatrix();
/* 16 */     GL11.glTranslatef((float)d + 0.5F, (float)e + 0.75F, (float)f + 0.5F);
/*    */     
/* 18 */     float f1 = tileEntityEnchantmentTable.tickCount + g;
/*    */     
/* 20 */     GL11.glTranslatef(0.0F, 0.1F + MathHelper.sin(f1 * 0.1F) * 0.01F, 0.0F);
/* 21 */     float f2 = tileEntityEnchantmentTable.bookRotation2 - tileEntityEnchantmentTable.bookRotationPrev;
/* 22 */     while (f2 >= 3.1415927F)
/* 23 */       f2 -= 6.2831855F; 
/* 24 */     while (f2 < -3.1415927F) {
/* 25 */       f2 += 6.2831855F;
/*    */     }
/* 27 */     float f3 = tileEntityEnchantmentTable.bookRotationPrev + f2 * g;
/*    */     
/* 29 */     GL11.glRotatef(-f3 * 180.0F / 3.1415927F, 0.0F, 1.0F, 0.0F);
/* 30 */     GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
/* 31 */     bindTexture(enchantingTableBookTextures);
/*    */     
/* 33 */     float f4 = tileEntityEnchantmentTable.pageFlipPrev + (tileEntityEnchantmentTable.pageFlip - tileEntityEnchantmentTable.pageFlipPrev) * g + 0.25F;
/* 34 */     float f5 = tileEntityEnchantmentTable.pageFlipPrev + (tileEntityEnchantmentTable.pageFlip - tileEntityEnchantmentTable.pageFlipPrev) * g + 0.75F;
/* 35 */     f4 = (f4 - MathHelper.truncateDoubleToInt(f4)) * 1.6F - 0.3F;
/* 36 */     f5 = (f5 - MathHelper.truncateDoubleToInt(f5)) * 1.6F - 0.3F;
/*    */     
/* 38 */     if (f4 < 0.0F) f4 = 0.0F; 
/* 39 */     if (f5 < 0.0F) f5 = 0.0F; 
/* 40 */     if (f4 > 1.0F) f4 = 1.0F; 
/* 41 */     if (f5 > 1.0F) f5 = 1.0F;
/*    */     
/* 43 */     float f6 = tileEntityEnchantmentTable.bookSpreadPrev + (tileEntityEnchantmentTable.bookSpread - tileEntityEnchantmentTable.bookSpreadPrev) * g;
/* 44 */     GL11.glEnable(2884);
/* 45 */     this.enchantmentBook.render(null, f1, f4, f5, f6, 0.0F, 0.0625F);
/* 46 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderEnchantmentTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */