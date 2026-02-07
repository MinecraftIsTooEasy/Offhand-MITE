/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class GuiScreenHorseInventory
/*    */   extends GuiContainer {
/*  7 */   private static final ResourceLocation horseGuiTextures = new ResourceLocation("textures/gui/container/horse.png");
/*    */   
/*    */   private IInventory field_110413_u;
/*    */   
/*    */   private IInventory field_110412_v;
/*    */   
/*    */   private EntityHorse field_110411_w;
/*    */   private float field_110416_x;
/*    */   private float field_110415_y;
/*    */   
/*    */   public GuiScreenHorseInventory(EntityPlayer player, IInventory par2IInventory, EntityHorse par3EntityHorse) {
/* 18 */     super(new ContainerHorseInventory(player, par2IInventory, par3EntityHorse));
/*    */     
/* 20 */     this.field_110413_u = player.inventory;
/* 21 */     this.field_110412_v = par2IInventory;
/* 22 */     this.field_110411_w = par3EntityHorse;
/* 23 */     this.allowUserInput = false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
/* 31 */     this.fontRenderer.drawString(this.field_110412_v.hasCustomName() ? this.field_110412_v.getCustomNameOrUnlocalized() : I18n.getString(this.field_110412_v.getCustomNameOrUnlocalized()), 8, 6, 4210752);
/* 32 */     this.fontRenderer.drawString(this.field_110413_u.hasCustomName() ? this.field_110413_u.getCustomNameOrUnlocalized() : I18n.getString(this.field_110413_u.getCustomNameOrUnlocalized()), 8, this.ySize - 96 + 2, 4210752);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
/* 40 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 41 */     this.mc.getTextureManager().bindTexture(horseGuiTextures);
/* 42 */     int var4 = (this.width - this.xSize) / 2;
/* 43 */     int var5 = (this.height - this.ySize) / 2;
/* 44 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
/*    */     
/* 46 */     if (this.field_110411_w.isChested())
/*    */     {
/* 48 */       drawTexturedModalRect(var4 + 79, var5 + 17, 0, this.ySize, 90, 54);
/*    */     }
/*    */ 
/*    */     
/* 52 */     if (this.field_110411_w.isNormalHorse())
/*    */     {
/* 54 */       drawTexturedModalRect(var4 + 7, var5 + 35, 0, this.ySize + 54, 18, 18);
/*    */     }
/*    */     
/* 57 */     GuiInventory.func_110423_a(var4 + 51, var5 + 60, 17, (var4 + 51) - this.field_110416_x, (var5 + 75 - 50) - this.field_110415_y, this.field_110411_w);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int par1, int par2, float par3) {
/* 65 */     this.field_110416_x = par1;
/* 66 */     this.field_110415_y = par2;
/* 67 */     super.drawScreen(par1, par2, par3);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenHorseInventory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */