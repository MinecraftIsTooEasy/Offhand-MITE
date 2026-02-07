/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ public class GuiButtonLanguage
/*    */   extends GuiButton
/*    */ {
/*    */   public GuiButtonLanguage(int i, int j, int k) {
/* 10 */     super(i, j, k, 20, 20, "");
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawButton(Minecraft minecraft, int i, int j) {
/* 15 */     if (!this.drawButton)
/*    */       return; 
/* 17 */     minecraft.getTextureManager().bindTexture(GuiButton.buttonTextures);
/* 18 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 20 */     boolean bool = (i >= this.xPosition && j >= this.yPosition && i < this.xPosition + this.width && j < this.yPosition + this.height) ? true : false;
/* 21 */     int k = 106;
/* 22 */     if (bool) {
/* 23 */       k += this.height;
/*    */     }
/*    */     
/* 26 */     drawTexturedModalRect(this.xPosition, this.yPosition, 0, k, this.width, this.height);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiButtonLanguage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */