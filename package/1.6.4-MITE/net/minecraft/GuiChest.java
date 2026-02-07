/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class GuiChest
/*    */   extends GuiContainer {
/*  7 */   private static final ResourceLocation field_110421_t = new ResourceLocation("textures/gui/container/generic_54.png");
/*    */ 
/*    */   
/*    */   private IInventory upperChestInventory;
/*    */ 
/*    */   
/*    */   private IInventory lowerChestInventory;
/*    */ 
/*    */   
/*    */   private int inventoryRows;
/*    */ 
/*    */   
/*    */   public GuiChest(EntityPlayer player, IInventory par2IInventory) {
/* 20 */     super(new ContainerChest(player, par2IInventory));
/*    */     
/* 22 */     this.upperChestInventory = player.inventory;
/* 23 */     this.lowerChestInventory = par2IInventory;
/* 24 */     this.allowUserInput = false;
/* 25 */     short var3 = 222;
/* 26 */     int var4 = var3 - 108;
/* 27 */     this.inventoryRows = par2IInventory.getSizeInventory() / 9;
/* 28 */     this.ySize = var4 + this.inventoryRows * 18;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
/* 36 */     this.fontRenderer.drawString(this.lowerChestInventory.hasCustomName() ? this.lowerChestInventory.getCustomNameOrUnlocalized() : I18n.getString(this.lowerChestInventory.getCustomNameOrUnlocalized()), 8, 6, 4210752);
/* 37 */     this.fontRenderer.drawString(this.upperChestInventory.hasCustomName() ? this.upperChestInventory.getCustomNameOrUnlocalized() : I18n.getString(this.upperChestInventory.getCustomNameOrUnlocalized()), 8, this.ySize - 96 + 2, 4210752);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
/* 45 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 46 */     this.mc.getTextureManager().bindTexture(field_110421_t);
/* 47 */     int var4 = (this.width - this.xSize) / 2;
/* 48 */     int var5 = (this.height - this.ySize) / 2;
/* 49 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
/* 50 */     drawTexturedModalRect(var4, var5 + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiChest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */