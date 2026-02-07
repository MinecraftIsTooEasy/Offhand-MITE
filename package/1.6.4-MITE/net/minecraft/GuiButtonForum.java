/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiButtonForum
/*    */   extends GuiButton
/*    */ {
/*    */   public GuiButtonForum(int par1, int par2, int par3) {
/* 11 */     super(par1, par2, par3, 20, 20, "");
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawButton(Minecraft par1Minecraft, int par2, int par3) {
/* 16 */     if (this.drawButton) {
/*    */       
/* 18 */       par1Minecraft.getTextureManager().bindTexture(GuiButton.buttonTextures);
/* 19 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 20 */       boolean var4 = (par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height);
/* 21 */       int var5 = 106;
/*    */       
/* 23 */       if (var4)
/*    */       {
/* 25 */         var5 += this.height;
/*    */       }
/*    */       
/* 28 */       drawTexturedModalRect(this.xPosition, this.yPosition, 0 + this.width, var5, this.width, this.height);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiButtonForum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */