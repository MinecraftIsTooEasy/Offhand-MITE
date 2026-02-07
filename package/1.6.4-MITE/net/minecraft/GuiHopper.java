/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class GuiHopper
/*    */   extends GuiContainer {
/*  7 */   private static final ResourceLocation hopperGuiTextures = new ResourceLocation("textures/gui/container/hopper.png");
/*    */   
/*    */   private IInventory field_94081_r;
/*    */   
/*    */   private IInventory field_94080_s;
/*    */ 
/*    */   
/*    */   public GuiHopper(EntityPlayer player, IInventory par2IInventory) {
/* 15 */     super(new ContainerHopper(player, par2IInventory));
/*    */     
/* 17 */     this.field_94081_r = player.inventory;
/* 18 */     this.field_94080_s = par2IInventory;
/* 19 */     this.allowUserInput = false;
/* 20 */     this.ySize = 133;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
/* 31 */     String text = this.field_94080_s.hasCustomName() ? this.field_94080_s.getCustomNameOrUnlocalized() : I18n.getString(this.field_94080_s.getCustomNameOrUnlocalized());
/* 32 */     this.fontRenderer.drawString(text, this.xSize / 2 - this.fontRenderer.getStringWidth(text) / 2, 9, 2631720);
/* 33 */     this.fontRenderer.drawString(this.field_94081_r.hasCustomName() ? this.field_94081_r.getCustomNameOrUnlocalized() : I18n.getString(this.field_94081_r.getCustomNameOrUnlocalized()), 7, this.ySize - 96 + 3, 2631720);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
/* 41 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 42 */     this.mc.getTextureManager().bindTexture(hopperGuiTextures);
/* 43 */     int var4 = (this.width - this.xSize) / 2;
/* 44 */     int var5 = (this.height - this.ySize) / 2;
/* 45 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiHopper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */