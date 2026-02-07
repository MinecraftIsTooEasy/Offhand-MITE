/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class GuiFurnace
/*     */   extends GuiContainer {
/*   7 */   private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("textures/gui/container/furnace.png");
/*     */ 
/*     */   
/*     */   private TileEntityFurnace furnaceInventory;
/*     */ 
/*     */   
/*     */   public GuiFurnace(EntityPlayer player, TileEntityFurnace par2TileEntityFurnace) {
/*  14 */     super(new ContainerFurnace(player, par2TileEntityFurnace));
/*  15 */     this.furnaceInventory = par2TileEntityFurnace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
/*  23 */     String var3 = this.furnaceInventory.hasCustomName() ? this.furnaceInventory.getCustomNameOrUnlocalized() : I18n.getString(this.furnaceInventory.getCustomNameOrUnlocalized());
/*  24 */     this.fontRenderer.drawString(var3, this.xSize / 2 - this.fontRenderer.getStringWidth(var3) / 2, 6, 4210752);
/*  25 */     this.fontRenderer.drawString(I18n.getString("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
/*  33 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  34 */     this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
/*  35 */     int var4 = (this.width - this.xSize) / 2;
/*  36 */     int var5 = (this.height - this.ySize) / 2;
/*  37 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
/*     */ 
/*     */     
/*  40 */     if (this.furnaceInventory.isBurning()) {
/*     */       
/*  42 */       int i = this.furnaceInventory.getBurnTimeRemainingScaled(12);
/*  43 */       drawTexturedModalRect(var4 + 56, var5 + 36 + 12 - i, 176, 12 - i, 14, i + 2);
/*     */     } 
/*     */     
/*  46 */     int var6 = this.furnaceInventory.getCookProgressScaled(24);
/*  47 */     drawTexturedModalRect(var4 + 79, var5 + 34, 176, 14, var6 + 1, 16);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouse_x, int mouse_y, float par3) {
/*  52 */     super.drawScreen(mouse_x, mouse_y, par3);
/*     */     
/*  54 */     if (this.inventorySlots.slot_that_did_not_accept_item == null) {
/*     */       return;
/*     */     }
/*  57 */     ItemStack mouse_item_stack = this.mc.thePlayer.inventory.getItemStack();
/*     */     
/*  59 */     if (mouse_item_stack == null) {
/*     */       return;
/*     */     }
/*  62 */     Slot slot = getSlotThatMouseIsOver(mouse_x, mouse_y);
/*     */     
/*  64 */     if (slot == null) {
/*     */       
/*  66 */       this.inventorySlots.slot_that_did_not_accept_item = null;
/*     */       
/*     */       return;
/*     */     } 
/*  70 */     if (slot == null || slot.isItemValid(mouse_item_stack)) {
/*     */       return;
/*     */     }
/*  73 */     if (!slot.accepts_large_items && Slot.isLargeItem(mouse_item_stack.getItem())) {
/*     */ 
/*     */       
/*  76 */       drawCreativeTabHoveringText(EnumChatFormatting.GOLD + Translator.get("container.furnace.wontFit"), mouse_x, mouse_y);
/*     */       
/*     */       return;
/*     */     } 
/*  80 */     if (slot == this.inventorySlots.getSlot(0)) {
/*     */       
/*  82 */       if (!FurnaceRecipes.smelting().doesSmeltingRecipeExistFor(mouse_item_stack)) {
/*     */ 
/*     */         
/*  85 */         drawCreativeTabHoveringText(EnumChatFormatting.GOLD + Translator.get("container.furnace.cantSmelt"), mouse_x, mouse_y);
/*     */         
/*     */         return;
/*     */       } 
/*  89 */     } else if (slot == this.inventorySlots.getSlot(1)) {
/*     */       
/*  91 */       if (this.furnaceInventory.getItemHeatLevel(mouse_item_stack) < 1) {
/*     */ 
/*     */         
/*  94 */         drawCreativeTabHoveringText(EnumChatFormatting.GOLD + Translator.get("container.furnace.notFuel"), mouse_x, mouse_y);
/*     */         
/*     */         return;
/*     */       } 
/*  98 */       if (this.furnaceInventory.getItemHeatLevel(mouse_item_stack) > this.furnaceInventory.getMaxHeatLevel())
/*     */       {
/*     */         
/* 101 */         drawCreativeTabHoveringText(EnumChatFormatting.GOLD + Translator.get("container.furnace.tooHot"), mouse_x, mouse_y);
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiFurnace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */