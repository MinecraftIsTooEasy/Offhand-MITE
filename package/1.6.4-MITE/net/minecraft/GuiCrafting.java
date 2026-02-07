/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class GuiCrafting
/*    */   extends InventoryEffectRenderer
/*    */ {
/*  8 */   private static final ResourceLocation craftingTableGuiTextures = new ResourceLocation("textures/gui/container/crafting_table.png");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public GuiCrafting(EntityPlayer player, World par2World, int par3, int par4, int par5) {
/* 18 */     super(new ContainerWorkbench(player, par3, par4, par5));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
/* 34 */     ContainerWorkbench container_workbench = (ContainerWorkbench)this.inventorySlots;
/*    */     
/* 36 */     String var3 = Translator.get("tile.toolbench." + BlockWorkbench.getToolMaterial(container_workbench.getBlockMetadata()) + ".name");
/* 37 */     this.fontRenderer.drawString(var3, 29, 6, 4210752);
/* 38 */     this.fontRenderer.drawString(I18n.getString("container.inventory"), 7, this.ySize - 96 + 3, 4210752);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
/* 48 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 49 */     this.mc.getTextureManager().bindTexture(craftingTableGuiTextures);
/*    */ 
/*    */     
/* 52 */     int var4 = this.guiLeft;
/* 53 */     int var5 = this.guiTop;
/* 54 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
/*    */     
/* 56 */     EntityClientPlayerMP player = this.mc.thePlayer;
/*    */ 
/*    */     
/* 59 */     if (player.crafting_ticks > 0) {
/* 60 */       drawTexturedModalRect(var4 + 90, var5 + 34, 176, 0, player.crafting_ticks * 23 / player.crafting_period, 16);
/*    */     }
/* 62 */     SlotCrafting slot_crafting = (SlotCrafting)this.inventorySlots.getSlot(0);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 67 */     if (slot_crafting.getNumCraftingResults(player) > 1) {
/*    */       
/* 69 */       this.mc.getTextureManager().bindTexture(GuiIngame.MITE_icons);
/*    */       
/* 71 */       float grey = 0.54509807F;
/* 72 */       GL11.glColor4f(grey, grey, grey, 1.0F);
/*    */       
/* 74 */       drawTexturedModalRect(var4 + 147, var5 + 31, 16, 0, 3, 3);
/*    */       
/* 76 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiCrafting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */