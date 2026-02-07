/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class GuiDispenser
/*    */   extends GuiContainer {
/*  7 */   private static final ResourceLocation dispenserGuiTextures = new ResourceLocation("textures/gui/container/dispenser.png");
/*    */ 
/*    */   
/*    */   public TileEntityDispenser theDispenser;
/*    */ 
/*    */   
/*    */   public GuiDispenser(EntityPlayer player, TileEntityDispenser par2TileEntityDispenser) {
/* 14 */     super(new ContainerDispenser(player, par2TileEntityDispenser));
/* 15 */     this.theDispenser = par2TileEntityDispenser;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
/* 23 */     String var3 = this.theDispenser.hasCustomName() ? this.theDispenser.getCustomNameOrUnlocalized() : I18n.getString(this.theDispenser.getCustomNameOrUnlocalized());
/*    */ 
/*    */ 
/*    */     
/* 27 */     this.fontRenderer.drawString(var3, this.xSize / 2 - this.fontRenderer.getStringWidth(var3) / 2, 6, 2631720);
/* 28 */     this.fontRenderer.drawString(I18n.getString("container.inventory"), 7, this.ySize - 96 + 3, 2631720);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
/* 36 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 37 */     this.mc.getTextureManager().bindTexture(dispenserGuiTextures);
/* 38 */     int var4 = (this.width - this.xSize) / 2;
/* 39 */     int var5 = (this.height - this.ySize) / 2;
/* 40 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiDispenser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */