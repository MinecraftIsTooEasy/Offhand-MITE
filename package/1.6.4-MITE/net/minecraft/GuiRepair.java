/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class GuiRepair
/*     */   extends GuiContainer
/*     */   implements ICrafting {
/*  10 */   private static final ResourceLocation anvilGuiTextures = new ResourceLocation("textures/gui/container/anvil.png");
/*     */   
/*     */   private ContainerRepair repairContainer;
/*     */   
/*     */   private GuiTextField itemNameField;
/*     */   
/*     */   private InventoryPlayer field_82325_q;
/*     */   
/*     */   private TileEntityAnvil tile_entity_anvil;
/*     */   
/*     */   private int x;
/*     */   private int y;
/*     */   private int z;
/*     */   
/*     */   public GuiRepair(EntityPlayer player, int par3, int par4, int par5) {
/*  25 */     super(new ContainerRepair(player, par3, par4, par5));
/*     */     
/*  27 */     this.field_82325_q = player.inventory;
/*  28 */     this.repairContainer = (ContainerRepair)this.inventorySlots;
/*     */     
/*  30 */     this.x = par3;
/*  31 */     this.y = par4;
/*  32 */     this.z = par5;
/*     */     
/*  34 */     this.tile_entity_anvil = (TileEntityAnvil)player.worldObj.getBlockTileEntity(this.x, this.y, this.z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  42 */     super.initGui();
/*  43 */     Keyboard.enableRepeatEvents(true);
/*  44 */     int var1 = (this.width - this.xSize) / 2;
/*  45 */     int var2 = (this.height - this.ySize) / 2;
/*  46 */     this.itemNameField = new GuiTextField(this.fontRenderer, var1 + 62, var2 + 24, 103, 12);
/*  47 */     this.itemNameField.setTextColor(-1);
/*  48 */     this.itemNameField.setDisabledTextColour(-1);
/*  49 */     this.itemNameField.setEnableBackgroundDrawing(false);
/*  50 */     this.itemNameField.setMaxStringLength(40);
/*  51 */     this.inventorySlots.removeCraftingFromCrafters(this);
/*  52 */     this.inventorySlots.addCraftingToCrafters(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/*  60 */     super.onGuiClosed();
/*  61 */     Keyboard.enableRepeatEvents(false);
/*  62 */     this.inventorySlots.removeCraftingFromCrafters(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
/*  70 */     GL11.glDisable(2896);
/*     */     
/*  72 */     this.fontRenderer.drawString(this.tile_entity_anvil.getCustomInvNameOrTranslated(), 60, 6, 4210752);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 116 */     GL11.glEnable(2896);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char par1, int par2) {
/* 125 */     if (par1 != '"' && this.itemNameField.textboxKeyTyped(par1, par2)) {
/*     */       
/* 127 */       func_135015_g();
/*     */     }
/*     */     else {
/*     */       
/* 131 */       super.keyTyped(par1, par2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean getItemStackHasCustomName() {
/* 137 */     Slot slot = this.repairContainer.getSlot(0);
/*     */     
/* 139 */     return (slot != null && slot.getHasStack() && slot.getStack().hasDisplayName());
/*     */   }
/*     */ 
/*     */   
/*     */   private String getItemStackDisplayName() {
/* 144 */     Slot slot = this.repairContainer.getSlot(0);
/*     */     
/* 146 */     return (slot == null || !slot.getHasStack()) ? null : slot.getStack().getDisplayName();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_135015_g() {
/* 151 */     String var1 = this.itemNameField.getText();
/* 152 */     Slot var2 = this.repairContainer.getSlot(0);
/*     */     
/* 154 */     if (var2 != null && var2.getHasStack() && !var2.getStack().hasDisplayName() && var1.equals(var2.getStack().getDisplayName()))
/*     */     {
/* 156 */       var1 = "";
/*     */     }
/*     */     
/* 159 */     this.repairContainer.updateItemName(var1);
/* 160 */     this.mc.thePlayer.sendQueue.addToSendQueue(new Packet250CustomPayload("MC|ItemName", var1.getBytes()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int par1, int par2, int par3) {
/* 168 */     boolean is_focused = this.itemNameField.isFocused();
/*     */     
/* 170 */     super.mouseClicked(par1, par2, par3);
/* 171 */     this.itemNameField.mouseClicked(par1, par2, par3);
/*     */     
/* 173 */     if (this.itemNameField.isFocused() && !is_focused && !getItemStackHasCustomName() && this.itemNameField.getText().equals(getItemStackDisplayName())) {
/* 174 */       this.itemNameField.setText("");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/* 182 */     super.drawScreen(par1, par2, par3);
/* 183 */     GL11.glDisable(2896);
/* 184 */     this.itemNameField.drawTextBox();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canBeRenamed(ItemStack item_stack) {
/* 220 */     return (item_stack != null && item_stack.canBeRenamed());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
/* 228 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 229 */     this.mc.getTextureManager().bindTexture(anvilGuiTextures);
/* 230 */     int var4 = (this.width - this.xSize) / 2;
/* 231 */     int var5 = (this.height - this.ySize) / 2;
/* 232 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
/*     */     
/* 234 */     drawTexturedModalRect(var4 + 59, var5 + 20, 0, this.ySize + (canBeRenamed(this.repairContainer.getSlot(0).getStack()) ? 0 : 16), 110, 16);
/*     */     
/* 236 */     if ((this.repairContainer.getSlot(0).getHasStack() || this.repairContainer.getSlot(1).getHasStack()) && !this.repairContainer.getSlot(2).getHasStack())
/*     */     {
/* 238 */       drawTexturedModalRect(var4 + 99, var5 + 45, this.xSize, 0, 28, 21);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendContainerAndContentsToPlayer(Container par1Container, List par2List) {
/* 244 */     sendSlotContents(par1Container, 0, par1Container.getSlot(0).getStack());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendSlotContents(Container par1Container, int par2, ItemStack par3ItemStack) {
/* 253 */     if (par2 == 0) {
/*     */       
/* 255 */       this.itemNameField.setText((par3ItemStack == null) ? "" : par3ItemStack.getDisplayName());
/*     */       
/* 257 */       this.itemNameField.setEnabled(canBeRenamed(par3ItemStack));
/*     */       
/* 259 */       if (par3ItemStack != null)
/*     */       {
/*     */         
/* 262 */         func_135015_g();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendProgressBarUpdate(Container par1Container, int par2, int par3) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean allowsImposedChat() {
/* 276 */     return !this.itemNameField.isFocused();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiRepair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */