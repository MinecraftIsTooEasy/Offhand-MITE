/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class GuiButtonNextPage
/*    */   extends GuiButton
/*    */ {
/*    */   private final boolean nextPage;
/*    */   
/*    */   public GuiButtonNextPage(int par1, int par2, int par3, boolean par4) {
/* 14 */     super(par1, par2, par3, 23, 13, "");
/* 15 */     this.nextPage = par4;
/*    */ 
/*    */     
/* 18 */     setClickedSound((String)null, 0.5F, 1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawButton(Minecraft par1Minecraft, int par2, int par3) {
/* 26 */     if (this.drawButton) {
/*    */       
/* 28 */       boolean var4 = (par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height);
/* 29 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 30 */       par1Minecraft.getTextureManager().bindTexture(GuiScreenBook.func_110404_g());
/* 31 */       int var5 = 0;
/* 32 */       int var6 = 192;
/*    */       
/* 34 */       if (var4)
/*    */       {
/* 36 */         var5 += 23;
/*    */       }
/*    */       
/* 39 */       if (!this.nextPage)
/*    */       {
/* 41 */         var6 += 13;
/*    */       }
/*    */       
/* 44 */       drawTexturedModalRect(this.xPosition, this.yPosition, var5, var6, 23, 13);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiButtonNextPage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */