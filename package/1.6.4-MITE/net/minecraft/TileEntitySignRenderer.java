/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileEntitySignRenderer
/*    */   extends TileEntitySpecialRenderer
/*    */ {
/* 12 */   private static final ResourceLocation field_110638_a = new ResourceLocation("textures/entity/sign.png");
/*    */   
/* 14 */   private final ModelSign modelSign = new ModelSign();
/*    */ 
/*    */   
/*    */   public void renderTileEntitySignAt(TileEntitySign tileEntitySign, double d, double e, double f, float g) {
/* 18 */     Block block = tileEntitySign.getBlockType();
/*    */     
/* 20 */     GL11.glPushMatrix();
/* 21 */     float f1 = 0.6666667F;
/* 22 */     if (block == Block.signPost) {
/* 23 */       GL11.glTranslatef((float)d + 0.5F, (float)e + 0.75F * f1, (float)f + 0.5F);
/* 24 */       float f3 = (tileEntitySign.getBlockMetadata() * 360) / 16.0F;
/* 25 */       GL11.glRotatef(-f3, 0.0F, 1.0F, 0.0F);
/* 26 */       this.modelSign.signStick.showModel = true;
/*    */     } else {
/* 28 */       int i = tileEntitySign.getBlockMetadata();
/* 29 */       float f3 = 0.0F;
/*    */       
/* 31 */       if (i == 2) f3 = 180.0F; 
/* 32 */       if (i == 4) f3 = 90.0F; 
/* 33 */       if (i == 5) f3 = -90.0F;
/*    */       
/* 35 */       GL11.glTranslatef((float)d + 0.5F, (float)e + 0.75F * f1, (float)f + 0.5F);
/* 36 */       GL11.glRotatef(-f3, 0.0F, 1.0F, 0.0F);
/* 37 */       GL11.glTranslatef(0.0F, -0.3125F, -0.4375F);
/*    */       
/* 39 */       this.modelSign.signStick.showModel = false;
/*    */     } 
/*    */     
/* 42 */     bindTexture(field_110638_a);
/*    */     
/* 44 */     GL11.glPushMatrix();
/* 45 */     GL11.glScalef(f1, -f1, -f1);
/* 46 */     this.modelSign.renderSign();
/* 47 */     GL11.glPopMatrix();
/* 48 */     FontRenderer fontRenderer = getFontRenderer();
/*    */     
/* 50 */     float f2 = 0.016666668F * f1;
/* 51 */     GL11.glTranslatef(0.0F, 0.5F * f1, 0.07F * f1);
/* 52 */     GL11.glScalef(f2, -f2, f2);
/* 53 */     GL11.glNormal3f(0.0F, 0.0F, -1.0F * f2);
/* 54 */     GL11.glDepthMask(false);
/*    */     
/* 56 */     boolean bool = false;
/* 57 */     for (byte b = 0; b < tileEntitySign.signText.length; b++) {
/* 58 */       String str = tileEntitySign.signText[b];
/* 59 */       if (b == tileEntitySign.lineBeingEdited) {
/* 60 */         str = "> " + str + " <";
/* 61 */         fontRenderer.drawString(str, -fontRenderer.getStringWidth(str) / 2, b * 10 - tileEntitySign.signText.length * 5, bool);
/*    */       } else {
/* 63 */         fontRenderer.drawString(str, -fontRenderer.getStringWidth(str) / 2, b * 10 - tileEntitySign.signText.length * 5, bool);
/*    */       } 
/*    */     } 
/* 66 */     GL11.glDepthMask(true);
/* 67 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 68 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntitySignRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */