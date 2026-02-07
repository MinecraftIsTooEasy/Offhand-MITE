/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class GuiContainerCreative
/*     */   extends InventoryEffectRenderer
/*     */ {
/*  15 */   private static final ResourceLocation field_110424_t = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
/*  16 */   private static InventoryBasic inventory = new InventoryBasic("tmp", true, 45);
/*     */ 
/*     */   
/*  19 */   private static int selectedTabIndex = CreativeTabs.tabBlock.getTabIndex();
/*     */ 
/*     */   
/*     */   private float currentScroll;
/*     */ 
/*     */   
/*     */   private boolean isScrolling;
/*     */ 
/*     */   
/*     */   private boolean wasClicking;
/*     */ 
/*     */   
/*     */   private GuiTextField searchField;
/*     */ 
/*     */   
/*     */   private List backupContainerSlots;
/*     */   
/*     */   private Slot field_74235_v;
/*     */   
/*     */   private boolean field_74234_w;
/*     */   
/*     */   private CreativeCrafting field_82324_x;
/*     */ 
/*     */   
/*     */   public GuiContainerCreative(EntityPlayer par1EntityPlayer) {
/*  44 */     super(new ContainerCreative(par1EntityPlayer));
/*  45 */     par1EntityPlayer.openContainer = this.inventorySlots;
/*  46 */     this.allowUserInput = true;
/*  47 */     par1EntityPlayer.addStat(AchievementList.openInventory, 1);
/*  48 */     par1EntityPlayer.incrementStatForThisWorldFromClient(AchievementList.openInventory);
/*     */     
/*  50 */     this.ySize = 136;
/*  51 */     this.xSize = 195;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  59 */     if (!this.mc.playerController.isInCreativeMode())
/*     */     {
/*  61 */       this.mc.displayGuiScreen(new GuiInventory(this.mc.thePlayer));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void handleMouseClick(Slot par1Slot, int par2, int par3, int par4) {
/*  67 */     this.field_74234_w = true;
/*  68 */     boolean var5 = (par4 == 1);
/*  69 */     par4 = (par2 == -999 && par4 == 0) ? 4 : par4;
/*     */ 
/*     */ 
/*     */     
/*  73 */     if (par1Slot == null && selectedTabIndex != CreativeTabs.tabInventory.getTabIndex() && par4 != 5) {
/*     */       
/*  75 */       InventoryPlayer var11 = this.mc.thePlayer.inventory;
/*     */       
/*  77 */       if (var11.getItemStack() != null)
/*     */       {
/*  79 */         if (par3 == 0) {
/*     */           
/*  81 */           this.mc.thePlayer.dropPlayerItem(var11.getItemStack());
/*  82 */           this.mc.playerController.func_78752_a(var11.getItemStack());
/*  83 */           var11.setItemStack((ItemStack)null);
/*     */         } 
/*     */         
/*  86 */         if (par3 == 1)
/*     */         {
/*  88 */           ItemStack var7 = var11.getItemStack().splitStack(1);
/*  89 */           this.mc.thePlayer.dropPlayerItem(var7);
/*  90 */           this.mc.playerController.func_78752_a(var7);
/*     */           
/*  92 */           if ((var11.getItemStack()).stackSize == 0)
/*     */           {
/*  94 */             var11.setItemStack((ItemStack)null);
/*     */           
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 103 */     else if (par1Slot == this.field_74235_v && var5) {
/*     */       
/* 105 */       for (int var10 = 0; var10 < this.mc.thePlayer.inventoryContainer.getInventory().size(); var10++)
/*     */       {
/* 107 */         this.mc.playerController.sendSlotPacket((ItemStack)null, var10);
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 114 */     else if (selectedTabIndex == CreativeTabs.tabInventory.getTabIndex()) {
/*     */       
/* 116 */       if (par1Slot == this.field_74235_v)
/*     */       {
/* 118 */         this.mc.thePlayer.inventory.setItemStack((ItemStack)null);
/*     */       }
/* 120 */       else if (par4 == 4 && par1Slot != null && par1Slot.getHasStack())
/*     */       {
/* 122 */         ItemStack var6 = par1Slot.decrStackSize((par3 == 0) ? 1 : par1Slot.getStack().getMaxStackSize());
/* 123 */         this.mc.thePlayer.dropPlayerItem(var6);
/* 124 */         this.mc.playerController.func_78752_a(var6);
/*     */       }
/* 126 */       else if (par4 == 4 && this.mc.thePlayer.inventory.getItemStack() != null)
/*     */       {
/* 128 */         this.mc.thePlayer.dropPlayerItem(this.mc.thePlayer.inventory.getItemStack());
/* 129 */         this.mc.playerController.func_78752_a(this.mc.thePlayer.inventory.getItemStack());
/* 130 */         this.mc.thePlayer.inventory.setItemStack((ItemStack)null);
/*     */       
/*     */       }
/*     */       else
/*     */       {
/* 135 */         this.mc.thePlayer.inventoryContainer.slotClick((par1Slot == null) ? par2 : (SlotCreativeInventory.func_75240_a((SlotCreativeInventory)par1Slot)).slotNumber, par3, par4, GuiScreen.isShiftKeyDown(), this.mc.thePlayer);
/* 136 */         this.mc.thePlayer.inventoryContainer.detectAndSendChanges();
/*     */       }
/*     */     
/* 139 */     } else if (par4 != 5 && par1Slot.inventory == inventory) {
/*     */       
/* 141 */       InventoryPlayer var11 = this.mc.thePlayer.inventory;
/* 142 */       ItemStack var7 = var11.getItemStack();
/* 143 */       ItemStack var8 = par1Slot.getStack();
/*     */ 
/*     */       
/* 146 */       if (par4 == 2) {
/*     */         
/* 148 */         if (var8 != null && par3 >= 0 && par3 < 9) {
/*     */           
/* 150 */           ItemStack var9 = var8.copy();
/* 151 */           var9.stackSize = var9.getMaxStackSize();
/* 152 */           this.mc.thePlayer.inventory.setInventorySlotContents(par3, var9);
/* 153 */           this.mc.thePlayer.inventoryContainer.detectAndSendChanges();
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 159 */       if (par4 == 3) {
/*     */         
/* 161 */         if (var11.getItemStack() == null && par1Slot.getHasStack()) {
/*     */           
/* 163 */           ItemStack var9 = par1Slot.getStack().copy();
/* 164 */           var9.stackSize = var9.getMaxStackSize();
/* 165 */           var11.setItemStack(var9);
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 171 */       if (par4 == 4) {
/*     */         
/* 173 */         if (var8 != null) {
/*     */           
/* 175 */           ItemStack var9 = var8.copy();
/* 176 */           var9.stackSize = (par3 == 0) ? 1 : var9.getMaxStackSize();
/* 177 */           this.mc.thePlayer.dropPlayerItem(var9);
/* 178 */           this.mc.playerController.func_78752_a(var9);
/*     */         } 
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 185 */       if (var7 != null && var8 != null && var7.isItemStackEqual(var8, true, false, false, true)) {
/*     */         
/* 187 */         if (par3 == 0) {
/*     */           
/* 189 */           if (var5)
/*     */           {
/* 191 */             var7.stackSize = var7.getMaxStackSize();
/*     */           }
/* 193 */           else if (var7.stackSize < var7.getMaxStackSize())
/*     */           {
/* 195 */             var7.stackSize++;
/*     */           }
/*     */         
/* 198 */         } else if (var7.stackSize <= 1) {
/*     */           
/* 200 */           var11.setItemStack((ItemStack)null);
/*     */         }
/*     */         else {
/*     */           
/* 204 */           var7.stackSize--;
/*     */         }
/*     */       
/* 207 */       } else if (var8 != null && var7 == null) {
/*     */         
/* 209 */         var11.setItemStack(ItemStack.copyItemStack(var8));
/* 210 */         var7 = var11.getItemStack();
/*     */         
/* 212 */         if (var5)
/*     */         {
/* 214 */           var7.stackSize = var7.getMaxStackSize();
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 219 */         var11.setItemStack((ItemStack)null);
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 225 */       this.inventorySlots.slotClick((par1Slot == null) ? par2 : par1Slot.slotNumber, par3, par4, GuiScreen.isShiftKeyDown(), this.mc.thePlayer);
/*     */       
/* 227 */       if (Container.func_94532_c(par3) == 2) {
/*     */         
/* 229 */         for (int var10 = 0; var10 < 9; var10++)
/*     */         {
/* 231 */           this.mc.playerController.sendSlotPacket(this.inventorySlots.getSlot(45 + var10).getStack(), 36 + var10);
/*     */         }
/*     */       }
/* 234 */       else if (par1Slot != null) {
/*     */         
/* 236 */         ItemStack var6 = this.inventorySlots.getSlot(par1Slot.slotNumber).getStack();
/* 237 */         this.mc.playerController.sendSlotPacket(var6, par1Slot.slotNumber - this.inventorySlots.inventorySlots.size() + 9 + 36);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/* 249 */     if (this.mc.playerController.isInCreativeMode()) {
/*     */       
/* 251 */       super.initGui();
/* 252 */       this.buttonList.clear();
/* 253 */       Keyboard.enableRepeatEvents(true);
/* 254 */       this.searchField = new GuiTextField(this.fontRenderer, this.guiLeft + 82, this.guiTop + 6, 89, this.fontRenderer.FONT_HEIGHT);
/* 255 */       this.searchField.setMaxStringLength(15);
/* 256 */       this.searchField.setEnableBackgroundDrawing(false);
/* 257 */       this.searchField.setVisible(false);
/* 258 */       this.searchField.setTextColor(16777215);
/* 259 */       int var1 = selectedTabIndex;
/* 260 */       selectedTabIndex = -1;
/* 261 */       setCurrentCreativeTab(CreativeTabs.creativeTabArray[var1]);
/* 262 */       this.field_82324_x = new CreativeCrafting(this.mc);
/* 263 */       this.mc.thePlayer.inventoryContainer.addCraftingToCrafters(this.field_82324_x);
/*     */     }
/*     */     else {
/*     */       
/* 267 */       this.mc.displayGuiScreen(new GuiInventory(this.mc.thePlayer));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/* 276 */     super.onGuiClosed();
/*     */     
/* 278 */     if (this.mc.thePlayer != null && this.mc.thePlayer.inventory != null)
/*     */     {
/* 280 */       this.mc.thePlayer.inventoryContainer.removeCraftingFromCrafters(this.field_82324_x);
/*     */     }
/*     */     
/* 283 */     Keyboard.enableRepeatEvents(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char par1, int par2) {
/* 291 */     if (selectedTabIndex != CreativeTabs.tabAllSearch.getTabIndex()) {
/*     */       
/* 293 */       if (GameSettings.isKeyDown(this.mc.gameSettings.keyBindChat))
/*     */       {
/* 295 */         setCurrentCreativeTab(CreativeTabs.tabAllSearch);
/*     */       }
/*     */       else
/*     */       {
/* 299 */         super.keyTyped(par1, par2);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 304 */       if (this.field_74234_w) {
/*     */         
/* 306 */         this.field_74234_w = false;
/* 307 */         this.searchField.setText("");
/*     */       } 
/*     */       
/* 310 */       if (!checkHotbarKeys(par2))
/*     */       {
/* 312 */         if (this.searchField.textboxKeyTyped(par1, par2)) {
/*     */           
/* 314 */           updateCreativeSearch();
/*     */         }
/*     */         else {
/*     */           
/* 318 */           super.keyTyped(par1, par2);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateCreativeSearch() {
/* 326 */     ContainerCreative var1 = (ContainerCreative)this.inventorySlots;
/* 327 */     var1.itemList.clear();
/* 328 */     Item[] var2 = Item.itemsList;
/* 329 */     int var3 = var2.length;
/*     */     
/*     */     int var4;
/* 332 */     for (var4 = 0; var4 < var3; var4++) {
/*     */       
/* 334 */       Item var5 = var2[var4];
/*     */       
/* 336 */       if (var5 != null && var5.getCreativeTab() != null)
/*     */       {
/*     */         
/* 339 */         var5.getSubItems(var5.itemID, CreativeTabs.tabAllSearch, var1.itemList);
/*     */       }
/*     */     } 
/*     */     
/* 343 */     Enchantment[] var8 = Enchantment.enchantmentsList;
/* 344 */     var3 = var8.length;
/*     */     
/* 346 */     for (var4 = 0; var4 < var3; var4++) {
/*     */       
/* 348 */       Enchantment var12 = var8[var4];
/*     */ 
/*     */       
/* 351 */       if (var12 != null)
/*     */       {
/* 353 */         Item.enchantedBook.func_92113_a(var12, var1.itemList);
/*     */       }
/*     */     } 
/*     */     
/* 357 */     Iterator<ItemStack> var9 = var1.itemList.iterator();
/* 358 */     String var10 = this.searchField.getText().toLowerCase();
/*     */     
/* 360 */     while (var9.hasNext()) {
/*     */       
/* 362 */       ItemStack var11 = var9.next();
/* 363 */       boolean var13 = false;
/*     */       
/* 365 */       Iterator<String> var6 = var11.getTooltip(this.mc.thePlayer, true, null).iterator();
/*     */ 
/*     */ 
/*     */       
/* 369 */       while (var6.hasNext()) {
/*     */         
/* 371 */         String var7 = var6.next();
/*     */         
/* 373 */         if (!var7.toLowerCase().contains(var10)) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */         
/* 378 */         var13 = true;
/*     */       } 
/*     */       
/* 381 */       if (!var13)
/*     */       {
/* 383 */         var9.remove();
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 390 */     this.currentScroll = 0.0F;
/* 391 */     var1.scrollTo(0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
/* 399 */     CreativeTabs var3 = CreativeTabs.creativeTabArray[selectedTabIndex];
/*     */     
/* 401 */     if (var3.drawInForegroundOfTab())
/*     */     {
/* 403 */       this.fontRenderer.drawString(I18n.getString(var3.getTranslatedTabLabel()), 8, 6, 4210752);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int par1, int par2, int par3) {
/* 412 */     if (par3 == 0) {
/*     */       
/* 414 */       int var4 = par1 - this.guiLeft;
/* 415 */       int var5 = par2 - this.guiTop;
/* 416 */       CreativeTabs[] var6 = CreativeTabs.creativeTabArray;
/* 417 */       int var7 = var6.length;
/*     */       
/* 419 */       for (int var8 = 0; var8 < var7; var8++) {
/*     */         
/* 421 */         CreativeTabs var9 = var6[var8];
/*     */         
/* 423 */         if (func_74232_a(var9, var4, var5)) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 430 */     super.mouseClicked(par1, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseMovedOrUp(int par1, int par2, int par3) {
/* 439 */     if (par3 == 0) {
/*     */       
/* 441 */       int var4 = par1 - this.guiLeft;
/* 442 */       int var5 = par2 - this.guiTop;
/* 443 */       CreativeTabs[] var6 = CreativeTabs.creativeTabArray;
/* 444 */       int var7 = var6.length;
/*     */       
/* 446 */       for (int var8 = 0; var8 < var7; var8++) {
/*     */         
/* 448 */         CreativeTabs var9 = var6[var8];
/*     */         
/* 450 */         if (func_74232_a(var9, var4, var5)) {
/*     */           
/* 452 */           setCurrentCreativeTab(var9);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/* 458 */     super.mouseMovedOrUp(par1, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean needsScrollBars() {
/* 466 */     return (selectedTabIndex != CreativeTabs.tabInventory.getTabIndex() && CreativeTabs.creativeTabArray[selectedTabIndex].shouldHidePlayerInventory() && ((ContainerCreative)this.inventorySlots).hasMoreThan1PageOfItemsInList());
/*     */   }
/*     */ 
/*     */   
/*     */   private void setCurrentCreativeTab(CreativeTabs par1CreativeTabs) {
/* 471 */     int var2 = selectedTabIndex;
/* 472 */     selectedTabIndex = par1CreativeTabs.getTabIndex();
/* 473 */     ContainerCreative var3 = (ContainerCreative)this.inventorySlots;
/* 474 */     this.field_94077_p.clear();
/* 475 */     var3.itemList.clear();
/* 476 */     par1CreativeTabs.displayAllReleventItems(var3.itemList);
/*     */     
/* 478 */     if (par1CreativeTabs == CreativeTabs.tabInventory) {
/*     */       
/* 480 */       Container var4 = this.mc.thePlayer.inventoryContainer;
/*     */       
/* 482 */       if (this.backupContainerSlots == null)
/*     */       {
/* 484 */         this.backupContainerSlots = var3.inventorySlots;
/*     */       }
/*     */       
/* 487 */       var3.inventorySlots = new ArrayList();
/*     */       
/* 489 */       for (int var5 = 0; var5 < var4.inventorySlots.size(); var5++) {
/*     */         
/* 491 */         SlotCreativeInventory var6 = new SlotCreativeInventory(this, var4.inventorySlots.get(var5), var5);
/* 492 */         var3.inventorySlots.add(var6);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 497 */         if (var5 >= 5 && var5 < 9) {
/*     */           
/* 499 */           int var7 = var5 - 5;
/* 500 */           int var8 = var7 / 2;
/* 501 */           int var9 = var7 % 2;
/* 502 */           var6.xDisplayPosition = 9 + var8 * 54;
/* 503 */           var6.yDisplayPosition = 6 + var9 * 27;
/*     */         }
/* 505 */         else if (var5 >= 0 && var5 < 5) {
/*     */           
/* 507 */           var6.yDisplayPosition = -2000;
/* 508 */           var6.xDisplayPosition = -2000;
/*     */         }
/* 510 */         else if (var5 < var4.inventorySlots.size()) {
/*     */           
/* 512 */           int var7 = var5 - 9;
/* 513 */           int var8 = var7 % 9;
/* 514 */           int var9 = var7 / 9;
/* 515 */           var6.xDisplayPosition = 9 + var8 * 18;
/*     */           
/* 517 */           if (var5 >= 36) {
/*     */             
/* 519 */             var6.yDisplayPosition = 112;
/*     */           }
/*     */           else {
/*     */             
/* 523 */             var6.yDisplayPosition = 54 + var9 * 18;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 528 */       this.field_74235_v = new Slot(inventory, 0, 173, 112);
/* 529 */       var3.inventorySlots.add(this.field_74235_v);
/*     */     }
/* 531 */     else if (var2 == CreativeTabs.tabInventory.getTabIndex()) {
/*     */       
/* 533 */       var3.inventorySlots = this.backupContainerSlots;
/* 534 */       this.backupContainerSlots = null;
/*     */     } 
/*     */     
/* 537 */     if (this.searchField != null)
/*     */     {
/* 539 */       if (par1CreativeTabs == CreativeTabs.tabAllSearch) {
/*     */         
/* 541 */         this.searchField.setVisible(true);
/* 542 */         this.searchField.setCanLoseFocus(false);
/* 543 */         this.searchField.setFocused(true);
/* 544 */         this.searchField.setText("");
/* 545 */         updateCreativeSearch();
/*     */       }
/*     */       else {
/*     */         
/* 549 */         this.searchField.setVisible(false);
/* 550 */         this.searchField.setCanLoseFocus(true);
/* 551 */         this.searchField.setFocused(false);
/*     */       } 
/*     */     }
/*     */     
/* 555 */     this.currentScroll = 0.0F;
/* 556 */     var3.scrollTo(0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleMouseInput() {
/* 564 */     super.handleMouseInput();
/* 565 */     int var1 = Mouse.getEventDWheel();
/*     */     
/* 567 */     if (var1 != 0 && needsScrollBars()) {
/*     */       
/* 569 */       int var2 = ((ContainerCreative)this.inventorySlots).itemList.size() / 9 - 5 + 1;
/*     */       
/* 571 */       if (var1 > 0)
/*     */       {
/* 573 */         var1 = 1;
/*     */       }
/*     */       
/* 576 */       if (var1 < 0)
/*     */       {
/* 578 */         var1 = -1;
/*     */       }
/*     */       
/* 581 */       this.currentScroll = (float)(this.currentScroll - var1 / var2);
/*     */       
/* 583 */       if (this.currentScroll < 0.0F)
/*     */       {
/* 585 */         this.currentScroll = 0.0F;
/*     */       }
/*     */       
/* 588 */       if (this.currentScroll > 1.0F)
/*     */       {
/* 590 */         this.currentScroll = 1.0F;
/*     */       }
/*     */       
/* 593 */       ((ContainerCreative)this.inventorySlots).scrollTo(this.currentScroll);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/* 602 */     boolean var4 = Mouse.isButtonDown(0);
/* 603 */     int var5 = this.guiLeft;
/* 604 */     int var6 = this.guiTop;
/* 605 */     int var7 = var5 + 175;
/* 606 */     int var8 = var6 + 18;
/* 607 */     int var9 = var7 + 14;
/* 608 */     int var10 = var8 + 112;
/*     */     
/* 610 */     if (!this.wasClicking && var4 && par1 >= var7 && par2 >= var8 && par1 < var9 && par2 < var10)
/*     */     {
/* 612 */       this.isScrolling = needsScrollBars();
/*     */     }
/*     */     
/* 615 */     if (!var4)
/*     */     {
/* 617 */       this.isScrolling = false;
/*     */     }
/*     */     
/* 620 */     this.wasClicking = var4;
/*     */     
/* 622 */     if (this.isScrolling) {
/*     */       
/* 624 */       this.currentScroll = ((par2 - var8) - 7.5F) / ((var10 - var8) - 15.0F);
/*     */       
/* 626 */       if (this.currentScroll < 0.0F)
/*     */       {
/* 628 */         this.currentScroll = 0.0F;
/*     */       }
/*     */       
/* 631 */       if (this.currentScroll > 1.0F)
/*     */       {
/* 633 */         this.currentScroll = 1.0F;
/*     */       }
/*     */       
/* 636 */       ((ContainerCreative)this.inventorySlots).scrollTo(this.currentScroll);
/*     */     } 
/*     */     
/* 639 */     super.drawScreen(par1, par2, par3);
/* 640 */     CreativeTabs[] var11 = CreativeTabs.creativeTabArray;
/* 641 */     int var12 = var11.length;
/*     */     
/* 643 */     for (int var13 = 0; var13 < var12; var13++) {
/*     */       
/* 645 */       CreativeTabs var14 = var11[var13];
/*     */       
/* 647 */       if (renderCreativeInventoryHoveringText(var14, par1, par2)) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 653 */     if (this.field_74235_v != null && selectedTabIndex == CreativeTabs.tabInventory.getTabIndex() && isPointInRegion(this.field_74235_v.xDisplayPosition, this.field_74235_v.yDisplayPosition, 16, 16, par1, par2))
/*     */     {
/* 655 */       drawCreativeTabHoveringText(I18n.getString("inventory.binSlot"), par1, par2);
/*     */     }
/*     */     
/* 658 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 659 */     GL11.glDisable(2896);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawItemStackTooltip(ItemStack par1ItemStack, int par2, int par3) {
/* 664 */     if (selectedTabIndex == CreativeTabs.tabAllSearch.getTabIndex()) {
/*     */ 
/*     */       
/* 667 */       List<String> var4 = par1ItemStack.getTooltip(this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips, null);
/* 668 */       CreativeTabs var5 = par1ItemStack.getItem().getCreativeTab();
/*     */       
/* 670 */       if (var5 == null && par1ItemStack.itemID == Item.enchantedBook.itemID) {
/*     */ 
/*     */         
/* 673 */         Map var6 = EnchantmentHelper.getStoredEnchantmentsMap(par1ItemStack);
/*     */         
/* 675 */         if (var6.size() == 1) {
/*     */           
/* 677 */           Enchantment var7 = Enchantment.enchantmentsList[((Integer)var6.keySet().iterator().next()).intValue()];
/* 678 */           CreativeTabs[] var8 = CreativeTabs.creativeTabArray;
/* 679 */           int var9 = var8.length;
/*     */           
/* 681 */           for (int var10 = 0; var10 < var9; var10++) {
/*     */             
/* 683 */             CreativeTabs var11 = var8[var10];
/*     */ 
/*     */             
/* 686 */             if (var7.isOnCreativeTab(var11)) {
/*     */               
/* 688 */               var5 = var11;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 695 */       if (var5 != null)
/*     */       {
/* 697 */         var4.add(1, "" + EnumChatFormatting.BOLD + EnumChatFormatting.BLUE + I18n.getString(var5.getTranslatedTabLabel()));
/*     */       }
/*     */       
/* 700 */       for (int var12 = 0; var12 < var4.size(); var12++) {
/*     */         
/* 702 */         if (var12 == 0) {
/*     */           
/* 704 */           var4.set(var12, "§" + Integer.toHexString((par1ItemStack.getRarity()).rarityColor) + (String)var4.get(var12));
/*     */         }
/*     */         else {
/*     */           
/* 708 */           var4.set(var12, EnumChatFormatting.GRAY + (String)var4.get(var12));
/*     */         } 
/*     */       } 
/*     */       
/* 712 */       func_102021_a(var4, par2, par3);
/*     */     }
/*     */     else {
/*     */       
/* 716 */       super.drawItemStackTooltip(par1ItemStack, par2, par3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
/* 725 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 726 */     RenderHelper.enableGUIStandardItemLighting();
/* 727 */     CreativeTabs var4 = CreativeTabs.creativeTabArray[selectedTabIndex];
/* 728 */     CreativeTabs[] var5 = CreativeTabs.creativeTabArray;
/* 729 */     int var6 = var5.length;
/*     */     
/*     */     int var7;
/* 732 */     for (var7 = 0; var7 < var6; var7++) {
/*     */       
/* 734 */       CreativeTabs var8 = var5[var7];
/* 735 */       this.mc.getTextureManager().bindTexture(field_110424_t);
/*     */       
/* 737 */       if (var8.getTabIndex() != selectedTabIndex)
/*     */       {
/* 739 */         renderCreativeTab(var8);
/*     */       }
/*     */     } 
/*     */     
/* 743 */     this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/creative_inventory/tab_" + var4.getBackgroundImageName()));
/* 744 */     drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
/* 745 */     this.searchField.drawTextBox();
/* 746 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 747 */     int var9 = this.guiLeft + 175;
/* 748 */     var6 = this.guiTop + 18;
/* 749 */     var7 = var6 + 112;
/* 750 */     this.mc.getTextureManager().bindTexture(field_110424_t);
/*     */     
/* 752 */     if (var4.shouldHidePlayerInventory())
/*     */     {
/* 754 */       drawTexturedModalRect(var9, var6 + (int)((var7 - var6 - 17) * this.currentScroll), 232 + (needsScrollBars() ? 0 : 12), 0, 12, 15);
/*     */     }
/*     */     
/* 757 */     renderCreativeTab(var4);
/*     */     
/* 759 */     if (var4 == CreativeTabs.tabInventory)
/*     */     {
/* 761 */       GuiInventory.func_110423_a(this.guiLeft + 43, this.guiTop + 45, 20, (this.guiLeft + 43 - par2), (this.guiTop + 45 - 30 - par3), this.mc.thePlayer);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_74232_a(CreativeTabs par1CreativeTabs, int par2, int par3) {
/* 767 */     int var7, var4 = par1CreativeTabs.getTabColumn();
/* 768 */     int var5 = 28 * var4;
/* 769 */     byte var6 = 0;
/*     */     
/* 771 */     if (var4 == 5) {
/*     */       
/* 773 */       var5 = this.xSize - 28 + 2;
/*     */     }
/* 775 */     else if (var4 > 0) {
/*     */       
/* 777 */       var5 += var4;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 782 */     if (par1CreativeTabs.isTabInFirstRow()) {
/*     */       
/* 784 */       var7 = var6 - 32;
/*     */     }
/*     */     else {
/*     */       
/* 788 */       var7 = var6 + this.ySize;
/*     */     } 
/*     */     
/* 791 */     return (par2 >= var5 && par2 <= var5 + 28 && par3 >= var7 && par3 <= var7 + 32);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean renderCreativeInventoryHoveringText(CreativeTabs par1CreativeTabs, int par2, int par3) {
/* 800 */     int var7, var4 = par1CreativeTabs.getTabColumn();
/* 801 */     int var5 = 28 * var4;
/* 802 */     byte var6 = 0;
/*     */     
/* 804 */     if (var4 == 5) {
/*     */       
/* 806 */       var5 = this.xSize - 28 + 2;
/*     */     }
/* 808 */     else if (var4 > 0) {
/*     */       
/* 810 */       var5 += var4;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 815 */     if (par1CreativeTabs.isTabInFirstRow()) {
/*     */       
/* 817 */       var7 = var6 - 32;
/*     */     }
/*     */     else {
/*     */       
/* 821 */       var7 = var6 + this.ySize;
/*     */     } 
/*     */     
/* 824 */     if (isPointInRegion(var5 + 3, var7 + 3, 23, 27, par2, par3)) {
/*     */       
/* 826 */       drawCreativeTabHoveringText(I18n.getString(par1CreativeTabs.getTranslatedTabLabel()), par2, par3);
/* 827 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 831 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderCreativeTab(CreativeTabs par1CreativeTabs) {
/* 840 */     boolean var2 = (par1CreativeTabs.getTabIndex() == selectedTabIndex);
/* 841 */     boolean var3 = par1CreativeTabs.isTabInFirstRow();
/* 842 */     int var4 = par1CreativeTabs.getTabColumn();
/* 843 */     int var5 = var4 * 28;
/* 844 */     int var6 = 0;
/* 845 */     int var7 = this.guiLeft + 28 * var4;
/* 846 */     int var8 = this.guiTop;
/* 847 */     byte var9 = 32;
/*     */     
/* 849 */     if (var2)
/*     */     {
/* 851 */       var6 += 32;
/*     */     }
/*     */     
/* 854 */     if (var4 == 5) {
/*     */       
/* 856 */       var7 = this.guiLeft + this.xSize - 28;
/*     */     }
/* 858 */     else if (var4 > 0) {
/*     */       
/* 860 */       var7 += var4;
/*     */     } 
/*     */     
/* 863 */     if (var3) {
/*     */       
/* 865 */       var8 -= 28;
/*     */     }
/*     */     else {
/*     */       
/* 869 */       var6 += 64;
/* 870 */       var8 += this.ySize - 4;
/*     */     } 
/*     */     
/* 873 */     GL11.glDisable(2896);
/* 874 */     drawTexturedModalRect(var7, var8, var5, var6, 28, var9);
/* 875 */     this.zLevel = 100.0F;
/* 876 */     itemRenderer.zLevel = 100.0F;
/* 877 */     var7 += 6;
/* 878 */     var8 += 8 + (var3 ? 1 : -1);
/* 879 */     GL11.glEnable(2896);
/* 880 */     GL11.glEnable(32826);
/* 881 */     ItemStack var10 = new ItemStack(par1CreativeTabs.getTabIconItem());
/* 882 */     itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.getTextureManager(), var10, var7, var8);
/* 883 */     itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.getTextureManager(), var10, var7, var8);
/* 884 */     GL11.glDisable(2896);
/* 885 */     itemRenderer.zLevel = 0.0F;
/* 886 */     this.zLevel = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/* 894 */     if (par1GuiButton.id == 0)
/*     */     {
/* 896 */       this.mc.displayGuiScreen(new GuiAchievements(this.mc.statFileWriter));
/*     */     }
/*     */     
/* 899 */     if (par1GuiButton.id == 1)
/*     */     {
/* 901 */       this.mc.displayGuiScreen(new GuiStats(this, this.mc.statFileWriter));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCurrentTabIndex() {
/* 910 */     return selectedTabIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static InventoryBasic getInventory() {
/* 918 */     return inventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean allowsImposedChat() {
/* 923 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiContainerCreative.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */