/*      */ package net.minecraft;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import org.lwjgl.input.Keyboard;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ 
/*      */ 
/*      */ public abstract class GuiContainer
/*      */   extends GuiScreen
/*      */ {
/*   16 */   protected static final ResourceLocation field_110408_a = new ResourceLocation("textures/gui/container/inventory.png");
/*      */ 
/*      */   
/*   19 */   protected static RenderItem itemRenderer = new RenderItem();
/*      */ 
/*      */   
/*   22 */   protected int xSize = 176;
/*      */ 
/*      */   
/*   25 */   protected int ySize = 166;
/*      */ 
/*      */   
/*      */   public Container inventorySlots;
/*      */ 
/*      */   
/*      */   protected int guiLeft;
/*      */ 
/*      */   
/*      */   protected int guiTop;
/*      */   
/*      */   private Slot theSlot;
/*      */   
/*      */   private Slot clickedSlot;
/*      */   
/*      */   private boolean isRightMouseClick;
/*      */   
/*      */   private ItemStack draggedStack;
/*      */   
/*      */   private int field_85049_r;
/*      */   
/*      */   private int field_85048_s;
/*      */   
/*      */   private Slot returningStackDestSlot;
/*      */   
/*      */   private long returningStackTime;
/*      */   
/*      */   private ItemStack returningStack;
/*      */   
/*      */   private Slot field_92033_y;
/*      */   
/*      */   private long field_92032_z;
/*      */   
/*   58 */   protected final Set field_94077_p = new HashSet();
/*      */   
/*      */   protected boolean field_94076_q;
/*      */   private int field_94071_C;
/*      */   private int field_94067_D;
/*      */   private boolean field_94068_E;
/*      */   private int field_94069_F;
/*      */   private long field_94070_G;
/*      */   private Slot field_94072_H;
/*      */   private int field_94073_I;
/*      */   private boolean field_94074_J;
/*      */   private ItemStack field_94075_K;
/*      */   
/*      */   public GuiContainer(Container par1Container) {
/*   72 */     this.inventorySlots = par1Container;
/*   73 */     this.field_94068_E = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void initGui() {
/*   81 */     super.initGui();
/*   82 */     this.mc.thePlayer.openContainer = this.inventorySlots;
/*   83 */     this.guiLeft = (this.width - this.xSize) / 2;
/*   84 */     this.guiTop = (this.height - this.ySize) / 2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawScreen(int par1, int par2, float par3) {
/*   93 */     drawDefaultBackground();
/*   94 */     int var4 = this.guiLeft;
/*   95 */     int var5 = this.guiTop;
/*   96 */     drawGuiContainerBackgroundLayer(par3, par1, par2);
/*   97 */     GL11.glDisable(32826);
/*   98 */     RenderHelper.disableStandardItemLighting();
/*   99 */     GL11.glDisable(2896);
/*  100 */     GL11.glDisable(2929);
/*  101 */     super.drawScreen(par1, par2, par3);
/*  102 */     RenderHelper.enableGUIStandardItemLighting();
/*  103 */     GL11.glPushMatrix();
/*  104 */     GL11.glTranslatef(var4, var5, 0.0F);
/*  105 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  106 */     GL11.glEnable(32826);
/*  107 */     this.theSlot = null;
/*  108 */     short var6 = 240;
/*  109 */     short var7 = 240;
/*  110 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var6 / 1.0F, var7 / 1.0F);
/*  111 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */ 
/*      */     
/*  114 */     for (int var13 = 0; var13 < this.inventorySlots.inventorySlots.size(); var13++) {
/*      */       
/*  116 */       Slot var14 = this.inventorySlots.inventorySlots.get(var13);
/*  117 */       drawSlotInventory(var14);
/*      */       
/*  119 */       if (isMouseOverSlot(var14, par1, par2) && var14.func_111238_b()) {
/*      */         
/*  121 */         this.theSlot = var14;
/*  122 */         GL11.glDisable(2896);
/*  123 */         GL11.glDisable(2929);
/*  124 */         int var8 = var14.xDisplayPosition;
/*  125 */         int var9 = var14.yDisplayPosition;
/*  126 */         drawGradientRect(var8, var9, var8 + 16, var9 + 16, -2130706433, -2130706433);
/*  127 */         GL11.glEnable(2896);
/*  128 */         GL11.glEnable(2929);
/*      */       } 
/*      */     } 
/*      */     
/*  132 */     boolean lighting_enabled = GL11.glGetBoolean(2896);
/*      */     
/*  134 */     if (lighting_enabled) {
/*  135 */       GL11.glDisable(2896);
/*      */     }
/*  137 */     drawGuiContainerForegroundLayer(par1, par2);
/*      */     
/*  139 */     if (lighting_enabled) {
/*  140 */       GL11.glEnable(2896);
/*      */     }
/*  142 */     InventoryPlayer var15 = this.mc.thePlayer.inventory;
/*  143 */     ItemStack var16 = (this.draggedStack == null) ? var15.getItemStack() : this.draggedStack;
/*      */     
/*  145 */     if (var16 != null) {
/*      */       
/*  147 */       byte var18 = 8;
/*  148 */       int var9 = (this.draggedStack == null) ? 8 : 16;
/*  149 */       String var10 = null;
/*      */       
/*  151 */       if (this.draggedStack != null && this.isRightMouseClick) {
/*      */         
/*  153 */         var16 = var16.copy();
/*  154 */         var16.stackSize = MathHelper.ceiling_float_int(var16.stackSize / 2.0F);
/*      */       }
/*  156 */       else if (this.field_94076_q && this.field_94077_p.size() > 1) {
/*      */         
/*  158 */         var16 = var16.copy();
/*  159 */         var16.stackSize = this.field_94069_F;
/*      */         
/*  161 */         if (var16.stackSize == 0)
/*      */         {
/*  163 */           var10 = "" + EnumChatFormatting.YELLOW + "0";
/*      */         }
/*      */       } 
/*      */       
/*  167 */       drawItemStack(var16, par1 - var4 - var18, par2 - var5 - var9, var10);
/*      */     } 
/*      */     
/*  170 */     if (this.returningStack != null) {
/*      */       
/*  172 */       float var17 = (float)(Minecraft.getSystemTime() - this.returningStackTime) / 100.0F;
/*      */       
/*  174 */       if (var17 >= 1.0F) {
/*      */         
/*  176 */         var17 = 1.0F;
/*  177 */         this.returningStack = null;
/*      */       } 
/*      */       
/*  180 */       int var9 = this.returningStackDestSlot.xDisplayPosition - this.field_85049_r;
/*  181 */       int var20 = this.returningStackDestSlot.yDisplayPosition - this.field_85048_s;
/*  182 */       int var11 = this.field_85049_r + (int)(var9 * var17);
/*  183 */       int var12 = this.field_85048_s + (int)(var20 * var17);
/*  184 */       drawItemStack(this.returningStack, var11, var12, (String)null);
/*      */     } 
/*      */     
/*  187 */     GL11.glPopMatrix();
/*      */ 
/*      */     
/*  190 */     if (this.theSlot != null && this.theSlot.getHasStack() && (var15.getItemStack() == null || this.theSlot instanceof SlotCrafting)) {
/*      */       
/*  192 */       ItemStack var19 = this.theSlot.getStack();
/*      */       
/*  194 */       drawItemStackTooltip(var19, par1, par2, this.theSlot);
/*      */     } 
/*      */     
/*  197 */     GL11.glEnable(2896);
/*  198 */     GL11.glEnable(2929);
/*  199 */     RenderHelper.enableStandardItemLighting();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawItemStack(ItemStack par1ItemStack, int par2, int par3, String par4Str) {
/*  209 */     GL11.glTranslatef(0.0F, 0.0F, 32.0F);
/*  210 */     this.zLevel = 200.0F;
/*  211 */     itemRenderer.zLevel = 200.0F;
/*  212 */     itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.getTextureManager(), par1ItemStack, par2, par3);
/*  213 */     itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.getTextureManager(), par1ItemStack, par2, par3 - ((this.draggedStack == null) ? 0 : 8), par4Str);
/*  214 */     this.zLevel = 0.0F;
/*  215 */     itemRenderer.zLevel = 0.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void drawItemStackTooltip(ItemStack par1ItemStack, int par2, int par3) {
/*  220 */     drawItemStackTooltip(par1ItemStack, par2, par3, (Slot)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void drawItemStackTooltip(ItemStack par1ItemStack, int par2, int par3, Slot slot) {
/*  227 */     List<String> var4 = par1ItemStack.getTooltip(this.mc.thePlayer, GuiScreen.isShiftKeyDown(), slot);
/*      */     
/*  229 */     for (int var5 = 0; var5 < var4.size(); var5++) {
/*      */       
/*  231 */       if (var5 == 0) {
/*      */         
/*  233 */         var4.set(var5, "§" + Integer.toHexString((par1ItemStack.getRarity()).rarityColor) + (String)var4.get(var5));
/*      */       }
/*      */       else {
/*      */         
/*  237 */         var4.set(var5, EnumChatFormatting.GRAY + (String)var4.get(var5));
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  263 */     if (slot instanceof SlotCrafting) {
/*      */       
/*  265 */       this.inventorySlots.crafting_result_shown_but_prevented = false;
/*      */       
/*  267 */       Item item = par1ItemStack.getItem();
/*      */       
/*  269 */       IRecipe recipe = ((MITEContainerCrafting)this.inventorySlots).getRecipe();
/*      */       
/*  271 */       Material material_to_check_tool_bench_hardness_against = (recipe == null) ? item.getHardestMetalMaterial() : recipe.getMaterialToCheckToolBenchHardnessAgainst();
/*      */       
/*  273 */       boolean upper_body_in_web = this.mc.thePlayer.isUpperBodyInWeb();
/*      */       
/*  275 */       if (material_to_check_tool_bench_hardness_against != null || upper_body_in_web) {
/*      */         
/*  277 */         List<String> tooltips = new ArrayList();
/*      */ 
/*      */         
/*  280 */         if (upper_body_in_web) {
/*      */           
/*  282 */           tooltips.add(EnumChatFormatting.GOLD + Translator.get("container.crafting.prevented"));
/*  283 */           this.inventorySlots.crafting_result_shown_but_prevented = true;
/*      */         }
/*  285 */         else if (this.inventorySlots instanceof ContainerWorkbench) {
/*      */           
/*  287 */           ContainerWorkbench container_workbench = (ContainerWorkbench)this.inventorySlots;
/*      */           
/*  289 */           Material tool_material = BlockWorkbench.getToolMaterial(container_workbench.getBlockMetadata());
/*      */           
/*  291 */           if (tool_material == null)
/*      */           {
/*      */             
/*  294 */             tooltips.add(EnumChatFormatting.GOLD + Translator.get("container.crafting.needsTools"));
/*  295 */             this.inventorySlots.crafting_result_shown_but_prevented = true;
/*      */ 
/*      */           
/*      */           }
/*  299 */           else if (material_to_check_tool_bench_hardness_against.durability > tool_material.durability)
/*      */           {
/*      */ 
/*      */             
/*  303 */             tooltips.add(EnumChatFormatting.GOLD + Translator.get("container.crafting.needsBetterTools"));
/*  304 */             this.inventorySlots.crafting_result_shown_but_prevented = true;
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  311 */           tooltips.add(EnumChatFormatting.GOLD + Translator.get("container.crafting.needsToolBench"));
/*  312 */           this.inventorySlots.crafting_result_shown_but_prevented = true;
/*      */         } 
/*      */         
/*  315 */         if (tooltips.size() > 0) {
/*      */           
/*  317 */           if (var4.size() > 1) {
/*  318 */             var4.add("");
/*      */           }
/*  320 */           var4.addAll(tooltips);
/*      */           
/*  322 */           func_102021_a(var4, par2, par3);
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/*      */       
/*  328 */       CraftingResult crafting_result = ((MITEContainerCrafting)slot.getContainer()).current_crafting_result;
/*      */       
/*  330 */       if (this.mc.theWorld.areSkillsEnabled() && !this.mc.thePlayer.hasSkillsForCraftingResult(crafting_result)) {
/*      */         
/*  332 */         if (var4.size() > 2) {
/*  333 */           var4.add("");
/*      */         }
/*  335 */         if (item.hasQuality() && !(crafting_result.recipe instanceof RecipesArmorDyes)) {
/*  336 */           var4.add(EnumChatFormatting.DARK_GRAY + Skill.getSkillsetsString(crafting_result.applicable_skillsets, false));
/*      */         } else {
/*  338 */           var4.add(EnumChatFormatting.DARK_GRAY + "Requires " + Skill.getSkillsetsString(crafting_result.applicable_skillsets, true));
/*      */         } 
/*  340 */       } else if (item instanceof ItemCoin && this.mc.thePlayer.experience < ((ItemCoin)item).getExperienceValue() * par1ItemStack.stackSize) {
/*      */ 
/*      */ 
/*      */         
/*  344 */         var4.add(EnumChatFormatting.GOLD + Translator.get("container.crafting.requiresExperience"));
/*  345 */         this.inventorySlots.crafting_result_shown_but_prevented = true;
/*      */       }
/*  347 */       else if (this.inventorySlots instanceof MITEContainerCrafting && ((MITEContainerCrafting)this.inventorySlots).craft_matrix.hasDamagedItem() && !((MITEContainerCrafting)this.inventorySlots).current_crafting_result.isRepair()) {
/*      */ 
/*      */         
/*  350 */         var4.add(EnumChatFormatting.GOLD + Translator.get("container.crafting.damagedComponent"));
/*  351 */         this.inventorySlots.crafting_result_shown_but_prevented = true;
/*      */       } 
/*      */     } 
/*      */     
/*  355 */     if (slot instanceof SlotCrafting || slot instanceof ContainerRepairINNER2)
/*      */     {
/*  357 */       if (this.inventorySlots.repair_fail_condition == 1) {
/*      */         
/*  359 */         var4.add("");
/*      */ 
/*      */         
/*  362 */         var4.add(EnumChatFormatting.GOLD + Translator.get("container.repair.insufficientSkill"));
/*      */       }
/*  364 */       else if (this.inventorySlots.repair_fail_condition == 2) {
/*      */         
/*  366 */         var4.add("");
/*      */ 
/*      */ 
/*      */         
/*  370 */         var4.add(EnumChatFormatting.GOLD + Translator.get("container.repair.needsHarderAnvil"));
/*      */       } 
/*      */     }
/*      */     
/*  374 */     func_102021_a(var4, par2, par3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void drawCreativeTabHoveringText(String par1Str, int par2, int par3) {
/*  383 */     func_102021_a(Arrays.asList(new String[] { par1Str }, ), par2, par3);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_102021_a(List par1List, int par2, int par3) {
/*  388 */     func_102021_a(par1List, par2, par3, true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_102021_a(List<String> par1List, int par2, int par3, boolean has_title) {
/*  394 */     if (!par1List.isEmpty()) {
/*      */       
/*  396 */       GL11.glDisable(32826);
/*  397 */       RenderHelper.disableStandardItemLighting();
/*  398 */       GL11.glDisable(2896);
/*  399 */       GL11.glDisable(2929);
/*  400 */       int var4 = 0;
/*  401 */       Iterator<String> var5 = par1List.iterator();
/*      */       
/*  403 */       while (var5.hasNext()) {
/*      */         
/*  405 */         String var6 = var5.next();
/*  406 */         int var7 = this.fontRenderer.getStringWidth(var6);
/*      */         
/*  408 */         if (var7 > var4)
/*      */         {
/*  410 */           var4 = var7;
/*      */         }
/*      */       } 
/*      */       
/*  414 */       int var14 = par2 + 12;
/*  415 */       int var15 = par3 - 12;
/*  416 */       int var8 = 8;
/*      */       
/*  418 */       if (par1List.size() > 1)
/*      */       {
/*  420 */         var8 += 2 + (par1List.size() - 1) * 10;
/*      */       }
/*      */       
/*  423 */       if (!has_title) {
/*  424 */         var8 -= 2;
/*      */       }
/*  426 */       if (var14 + var4 > this.width)
/*      */       {
/*  428 */         var14 -= 28 + var4;
/*      */       }
/*      */       
/*  431 */       if (var15 + var8 + 6 > this.height)
/*      */       {
/*  433 */         var15 = this.height - var8 - 6;
/*      */       }
/*      */       
/*  436 */       this.zLevel = 300.0F;
/*  437 */       itemRenderer.zLevel = 300.0F;
/*  438 */       int var9 = -267386864;
/*  439 */       var9 = var9 & 0xFFFFFF | 0xEA000000;
/*  440 */       drawGradientRect(var14 - 3, var15 - 4, var14 + var4 + 3, var15 - 3, var9, var9);
/*  441 */       drawGradientRect(var14 - 3, var15 + var8 + 3, var14 + var4 + 3, var15 + var8 + 4, var9, var9);
/*  442 */       drawGradientRect(var14 - 3, var15 - 3, var14 + var4 + 3, var15 + var8 + 3, var9, var9);
/*  443 */       drawGradientRect(var14 - 4, var15 - 3, var14 - 3, var15 + var8 + 3, var9, var9);
/*  444 */       drawGradientRect(var14 + var4 + 3, var15 - 3, var14 + var4 + 4, var15 + var8 + 3, var9, var9);
/*  445 */       int var10 = 1347420415;
/*  446 */       int var11 = (var10 & 0xFEFEFE) >> 1 | var10 & 0xFF000000;
/*  447 */       drawGradientRect(var14 - 3, var15 - 3 + 1, var14 - 3 + 1, var15 + var8 + 3 - 1, var10, var11);
/*  448 */       drawGradientRect(var14 + var4 + 2, var15 - 3 + 1, var14 + var4 + 3, var15 + var8 + 3 - 1, var10, var11);
/*  449 */       drawGradientRect(var14 - 3, var15 - 3, var14 + var4 + 3, var15 - 3 + 1, var10, var10);
/*  450 */       drawGradientRect(var14 - 3, var15 + var8 + 2, var14 + var4 + 3, var15 + var8 + 3, var11, var11);
/*      */       
/*  452 */       for (int var12 = 0; var12 < par1List.size(); var12++) {
/*      */         
/*  454 */         String var13 = par1List.get(var12);
/*  455 */         this.fontRenderer.drawStringWithShadow(var13, var14, var15, -1);
/*      */ 
/*      */         
/*  458 */         if (var12 == 0 && has_title)
/*      */         {
/*  460 */           var15 += 2;
/*      */         }
/*      */         
/*  463 */         var15 += 10;
/*      */       } 
/*      */       
/*  466 */       this.zLevel = 0.0F;
/*  467 */       itemRenderer.zLevel = 0.0F;
/*  468 */       GL11.glEnable(2896);
/*  469 */       GL11.glEnable(2929);
/*  470 */       RenderHelper.enableStandardItemLighting();
/*  471 */       GL11.glEnable(32826);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void drawGuiContainerForegroundLayer(int par1, int par2) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract void drawGuiContainerBackgroundLayer(float paramFloat, int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawSlotInventory(Slot par1Slot) {
/*  490 */     int var2 = par1Slot.xDisplayPosition;
/*  491 */     int var3 = par1Slot.yDisplayPosition;
/*  492 */     ItemStack var4 = par1Slot.getStack();
/*  493 */     boolean var5 = false;
/*  494 */     boolean var6 = (par1Slot == this.clickedSlot && this.draggedStack != null && !this.isRightMouseClick);
/*  495 */     ItemStack var7 = this.mc.thePlayer.inventory.getItemStack();
/*  496 */     String var8 = null;
/*      */     
/*  498 */     if (par1Slot == this.clickedSlot && this.draggedStack != null && this.isRightMouseClick && var4 != null) {
/*      */       
/*  500 */       var4 = var4.copy();
/*  501 */       var4.stackSize /= 2;
/*      */     }
/*  503 */     else if (this.field_94076_q && this.field_94077_p.contains(par1Slot) && var7 != null) {
/*      */       
/*  505 */       if (this.field_94077_p.size() == 1) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/*  510 */       if (Container.func_94527_a(par1Slot, var7, true) && this.inventorySlots.canDragIntoSlot(par1Slot)) {
/*      */         
/*  512 */         var4 = var7.copy();
/*  513 */         var5 = true;
/*  514 */         Container.func_94525_a(this.field_94077_p, this.field_94071_C, var4, (par1Slot.getStack() == null) ? 0 : (par1Slot.getStack()).stackSize);
/*      */         
/*  516 */         if (var4.stackSize > var4.getMaxStackSize()) {
/*      */           
/*  518 */           var8 = EnumChatFormatting.YELLOW + "" + var4.getMaxStackSize();
/*  519 */           var4.stackSize = var4.getMaxStackSize();
/*      */         } 
/*      */         
/*  522 */         if (var4.stackSize > par1Slot.getSlotStackLimit())
/*      */         {
/*  524 */           var8 = EnumChatFormatting.YELLOW + "" + par1Slot.getSlotStackLimit();
/*  525 */           var4.stackSize = par1Slot.getSlotStackLimit();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  530 */         this.field_94077_p.remove(par1Slot);
/*  531 */         func_94066_g();
/*      */       } 
/*      */     } 
/*      */     
/*  535 */     this.zLevel = 100.0F;
/*  536 */     itemRenderer.zLevel = 100.0F;
/*      */     
/*  538 */     if (var4 == null) {
/*      */       
/*  540 */       Icon var9 = par1Slot.getBackgroundIconIndex();
/*      */       
/*  542 */       if (var9 != null) {
/*      */         
/*  544 */         GL11.glDisable(2896);
/*  545 */         this.mc.getTextureManager().bindTexture(TextureMap.locationItemsTexture);
/*  546 */         drawTexturedModelRectFromIcon(var2, var3, var9, 16, 16);
/*  547 */         GL11.glEnable(2896);
/*  548 */         var6 = true;
/*      */       } 
/*      */     } 
/*      */     
/*  552 */     if (!var6) {
/*      */       
/*  554 */       if (var5)
/*      */       {
/*  556 */         drawRect(var2, var3, var2 + 16, var3 + 16, -2130706433);
/*      */       }
/*      */       
/*  559 */       GL11.glEnable(2929);
/*  560 */       itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.getTextureManager(), var4, var2, var3);
/*  561 */       itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.getTextureManager(), var4, var2, var3, var8);
/*      */     } 
/*      */     
/*  564 */     itemRenderer.zLevel = 0.0F;
/*  565 */     this.zLevel = 0.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_94066_g() {
/*  570 */     ItemStack var1 = this.mc.thePlayer.inventory.getItemStack();
/*      */     
/*  572 */     if (var1 != null && this.field_94076_q) {
/*      */       
/*  574 */       this.field_94069_F = var1.stackSize;
/*      */ 
/*      */ 
/*      */       
/*  578 */       for (Iterator<Slot> var2 = this.field_94077_p.iterator(); var2.hasNext(); this.field_94069_F -= var4.stackSize - var5) {
/*      */         
/*  580 */         Slot var3 = var2.next();
/*  581 */         ItemStack var4 = var1.copy();
/*  582 */         int var5 = (var3.getStack() == null) ? 0 : (var3.getStack()).stackSize;
/*  583 */         Container.func_94525_a(this.field_94077_p, this.field_94071_C, var4, var5);
/*      */         
/*  585 */         if (var4.stackSize > var4.getMaxStackSize())
/*      */         {
/*  587 */           var4.stackSize = var4.getMaxStackSize();
/*      */         }
/*      */         
/*  590 */         if (var4.stackSize > var3.getSlotStackLimit())
/*      */         {
/*  592 */           var4.stackSize = var3.getSlotStackLimit();
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Slot getSlotAtPosition(int par1, int par2) {
/*  603 */     for (int var3 = 0; var3 < this.inventorySlots.inventorySlots.size(); var3++) {
/*      */       
/*  605 */       Slot var4 = this.inventorySlots.inventorySlots.get(var3);
/*      */       
/*  607 */       if (isMouseOverSlot(var4, par1, par2))
/*      */       {
/*  609 */         return var4;
/*      */       }
/*      */     } 
/*      */     
/*  613 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void mouseClicked(int par1, int par2, int par3) {
/*  621 */     super.mouseClicked(par1, par2, par3);
/*  622 */     boolean var4 = (par3 == this.mc.gameSettings.keyBindPickBlock.keyCode + 100);
/*  623 */     Slot var5 = getSlotAtPosition(par1, par2);
/*  624 */     long var6 = Minecraft.getSystemTime();
/*  625 */     this.field_94074_J = (this.field_94072_H == var5 && var6 - this.field_94070_G < 250L && this.field_94073_I == par3);
/*  626 */     this.field_94068_E = false;
/*      */     
/*  628 */     if (par3 == 0 || par3 == 1 || var4) {
/*      */       
/*  630 */       int var8 = this.guiLeft;
/*  631 */       int var9 = this.guiTop;
/*  632 */       boolean var10 = (par1 < var8 || par2 < var9 || par1 >= var8 + this.xSize || par2 >= var9 + this.ySize);
/*  633 */       int var11 = -1;
/*      */       
/*  635 */       if (var5 != null)
/*      */       {
/*  637 */         var11 = var5.slotNumber;
/*      */       }
/*      */       
/*  640 */       if (var10)
/*      */       {
/*  642 */         var11 = -999;
/*      */       }
/*      */       
/*  645 */       if (this.mc.gameSettings.touchscreen && var10 && this.mc.thePlayer.inventory.getItemStack() == null) {
/*      */         
/*  647 */         this.mc.displayGuiScreen((GuiScreen)null);
/*      */         
/*      */         return;
/*      */       } 
/*  651 */       if (var11 != -1)
/*      */       {
/*  653 */         if (this.mc.gameSettings.touchscreen) {
/*      */           
/*  655 */           if (var5 != null && var5.getHasStack())
/*      */           {
/*  657 */             this.clickedSlot = var5;
/*  658 */             this.draggedStack = null;
/*  659 */             this.isRightMouseClick = (par3 == 1);
/*      */           }
/*      */           else
/*      */           {
/*  663 */             this.clickedSlot = null;
/*      */           }
/*      */         
/*  666 */         } else if (!this.field_94076_q) {
/*      */           
/*  668 */           if (this.mc.thePlayer.inventory.getItemStack() == null) {
/*      */             
/*  670 */             if (par3 == this.mc.gameSettings.keyBindPickBlock.keyCode + 100) {
/*      */               
/*  672 */               handleMouseClick(var5, var11, par3, 3);
/*      */             }
/*      */             else {
/*      */               
/*  676 */               boolean var12 = (var11 != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54)));
/*  677 */               byte var13 = 0;
/*      */               
/*  679 */               if (var12) {
/*      */                 
/*  681 */                 this.field_94075_K = (var5 != null && var5.getHasStack()) ? var5.getStack() : null;
/*  682 */                 var13 = 1;
/*      */               }
/*  684 */               else if (var11 == -999) {
/*      */                 
/*  686 */                 var13 = 4;
/*      */               } 
/*      */               
/*  689 */               handleMouseClick(var5, var11, par3, var13);
/*      */             } 
/*      */             
/*  692 */             this.field_94068_E = true;
/*      */           }
/*      */           else {
/*      */             
/*  696 */             this.field_94076_q = true;
/*  697 */             this.field_94067_D = par3;
/*  698 */             this.field_94077_p.clear();
/*      */             
/*  700 */             if (par3 == 0) {
/*      */               
/*  702 */               this.field_94071_C = 0;
/*      */             }
/*  704 */             else if (par3 == 1) {
/*      */               
/*  706 */               this.field_94071_C = 1;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  713 */     this.field_94072_H = var5;
/*  714 */     this.field_94070_G = var6;
/*  715 */     this.field_94073_I = par3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void mouseClickMove(int par1, int par2, int par3, long par4) {
/*  724 */     Slot var6 = getSlotAtPosition(par1, par2);
/*  725 */     ItemStack var7 = this.mc.thePlayer.inventory.getItemStack();
/*      */     
/*  727 */     if (this.clickedSlot != null && this.mc.gameSettings.touchscreen) {
/*      */       
/*  729 */       if (par3 == 0 || par3 == 1)
/*      */       {
/*  731 */         if (this.draggedStack == null) {
/*      */           
/*  733 */           if (var6 != this.clickedSlot)
/*      */           {
/*  735 */             this.draggedStack = this.clickedSlot.getStack().copy();
/*      */           }
/*      */         }
/*  738 */         else if (this.draggedStack.stackSize > 1 && var6 != null && Container.func_94527_a(var6, this.draggedStack, false)) {
/*      */           
/*  740 */           long var8 = Minecraft.getSystemTime();
/*      */           
/*  742 */           if (this.field_92033_y == var6) {
/*      */             
/*  744 */             if (var8 - this.field_92032_z > 500L)
/*      */             {
/*  746 */               handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, 0, 0);
/*  747 */               handleMouseClick(var6, var6.slotNumber, 1, 0);
/*  748 */               handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, 0, 0);
/*  749 */               this.field_92032_z = var8 + 750L;
/*  750 */               this.draggedStack.stackSize--;
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/*  755 */             this.field_92033_y = var6;
/*  756 */             this.field_92032_z = var8;
/*      */           }
/*      */         
/*      */         } 
/*      */       }
/*  761 */     } else if (this.field_94076_q && var6 != null && var7 != null && var7.stackSize > this.field_94077_p.size() && Container.func_94527_a(var6, var7, true) && var6.isItemValid(var7) && this.inventorySlots.canDragIntoSlot(var6)) {
/*      */       
/*  763 */       this.field_94077_p.add(var6);
/*  764 */       func_94066_g();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void mouseMovedOrUp(int par1, int par2, int par3) {
/*  774 */     Slot var4 = getSlotAtPosition(par1, par2);
/*  775 */     int var5 = this.guiLeft;
/*  776 */     int var6 = this.guiTop;
/*  777 */     boolean var7 = (par1 < var5 || par2 < var6 || par1 >= var5 + this.xSize || par2 >= var6 + this.ySize);
/*  778 */     int var8 = -1;
/*      */     
/*  780 */     if (var4 != null)
/*      */     {
/*  782 */       var8 = var4.slotNumber;
/*      */     }
/*      */     
/*  785 */     if (var7)
/*      */     {
/*  787 */       var8 = -999;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  793 */     if (this.field_94074_J && var4 != null && par3 == 0 && this.inventorySlots.func_94530_a((ItemStack)null, var4)) {
/*      */       
/*  795 */       if (isShiftKeyDown()) {
/*      */         
/*  797 */         if (var4 != null && var4.inventory != null && this.field_94075_K != null) {
/*      */           
/*  799 */           Iterator<Slot> var11 = this.inventorySlots.inventorySlots.iterator();
/*      */           
/*  801 */           while (var11.hasNext())
/*      */           {
/*  803 */             Slot var10 = var11.next();
/*      */             
/*  805 */             if (var10 != null && var10.canTakeStack(this.mc.thePlayer) && var10.getHasStack() && var10.inventory == var4.inventory && Container.func_94527_a(var10, this.field_94075_K, true))
/*      */             {
/*  807 */               handleMouseClick(var10, var10.slotNumber, par3, 1);
/*      */             }
/*      */           }
/*      */         
/*      */         } 
/*      */       } else {
/*      */         
/*  814 */         handleMouseClick(var4, var8, par3, 6);
/*      */       } 
/*      */       
/*  817 */       this.field_94074_J = false;
/*  818 */       this.field_94070_G = 0L;
/*      */     }
/*      */     else {
/*      */       
/*  822 */       if (this.field_94076_q && this.field_94067_D != par3) {
/*      */         
/*  824 */         this.field_94076_q = false;
/*  825 */         this.field_94077_p.clear();
/*  826 */         this.field_94068_E = true;
/*      */         
/*      */         return;
/*      */       } 
/*  830 */       if (this.field_94068_E) {
/*      */         
/*  832 */         this.field_94068_E = false;
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  838 */       if (this.clickedSlot != null && this.mc.gameSettings.touchscreen) {
/*      */         
/*  840 */         if (par3 == 0 || par3 == 1)
/*      */         {
/*  842 */           if (this.draggedStack == null && var4 != this.clickedSlot)
/*      */           {
/*  844 */             this.draggedStack = this.clickedSlot.getStack();
/*      */           }
/*      */           
/*  847 */           boolean var9 = Container.func_94527_a(var4, this.draggedStack, false);
/*      */           
/*  849 */           if (var8 != -1 && this.draggedStack != null && var9) {
/*      */             
/*  851 */             handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, par3, 0);
/*  852 */             handleMouseClick(var4, var8, 0, 0);
/*      */             
/*  854 */             if (this.mc.thePlayer.inventory.getItemStack() != null)
/*      */             {
/*  856 */               handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, par3, 0);
/*  857 */               this.field_85049_r = par1 - var5;
/*  858 */               this.field_85048_s = par2 - var6;
/*  859 */               this.returningStackDestSlot = this.clickedSlot;
/*  860 */               this.returningStack = this.draggedStack;
/*  861 */               this.returningStackTime = Minecraft.getSystemTime();
/*      */             }
/*      */             else
/*      */             {
/*  865 */               this.returningStack = null;
/*      */             }
/*      */           
/*  868 */           } else if (this.draggedStack != null) {
/*      */             
/*  870 */             this.field_85049_r = par1 - var5;
/*  871 */             this.field_85048_s = par2 - var6;
/*  872 */             this.returningStackDestSlot = this.clickedSlot;
/*  873 */             this.returningStack = this.draggedStack;
/*  874 */             this.returningStackTime = Minecraft.getSystemTime();
/*      */           } 
/*      */           
/*  877 */           this.draggedStack = null;
/*  878 */           this.clickedSlot = null;
/*      */         }
/*      */       
/*  881 */       } else if (this.field_94076_q && !this.field_94077_p.isEmpty()) {
/*      */         
/*  883 */         handleMouseClick((Slot)null, -999, Container.func_94534_d(0, this.field_94071_C), 5);
/*  884 */         Iterator<Slot> var11 = this.field_94077_p.iterator();
/*      */         
/*  886 */         while (var11.hasNext()) {
/*      */           
/*  888 */           Slot var10 = var11.next();
/*  889 */           handleMouseClick(var10, var10.slotNumber, Container.func_94534_d(1, this.field_94071_C), 5);
/*      */         } 
/*      */         
/*  892 */         handleMouseClick((Slot)null, -999, Container.func_94534_d(2, this.field_94071_C), 5);
/*      */       }
/*  894 */       else if (this.mc.thePlayer.inventory.getItemStack() != null) {
/*      */         
/*  896 */         if (par3 == this.mc.gameSettings.keyBindPickBlock.keyCode + 100) {
/*      */           
/*  898 */           handleMouseClick(var4, var8, par3, 3);
/*      */         }
/*      */         else {
/*      */           
/*  902 */           boolean var9 = (var8 != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54)));
/*      */           
/*  904 */           if (var9)
/*      */           {
/*  906 */             this.field_94075_K = (var4 != null && var4.getHasStack()) ? var4.getStack() : null;
/*      */           }
/*      */           
/*  909 */           handleMouseClick(var4, var8, par3, var9 ? 1 : 0);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  914 */     if (this.mc.thePlayer.inventory.getItemStack() == null)
/*      */     {
/*  916 */       this.field_94070_G = 0L;
/*      */     }
/*      */     
/*  919 */     this.field_94076_q = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isMouseOverSlot(Slot par1Slot, int par2, int par3) {
/*  927 */     return isPointInRegion(par1Slot.xDisplayPosition, par1Slot.yDisplayPosition, 16, 16, par2, par3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isPointInRegion(int par1, int par2, int par3, int par4, int par5, int par6) {
/*  936 */     int var7 = this.guiLeft;
/*  937 */     int var8 = this.guiTop;
/*  938 */     par5 -= var7;
/*  939 */     par6 -= var8;
/*  940 */     return (par5 >= par1 - 1 && par5 < par1 + par3 + 1 && par6 >= par2 - 1 && par6 < par2 + par4 + 1);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void handleMouseClick(Slot par1Slot, int par2, int par3, int par4) {
/*  945 */     if (par1Slot != null)
/*      */     {
/*  947 */       par2 = par1Slot.slotNumber;
/*      */     }
/*      */     
/*  950 */     this.mc.playerController.windowClick(this.inventorySlots.windowId, par2, par3, par4, this.mc.thePlayer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void keyTyped(char par1, int par2) {
/*  958 */     if (par2 == 1 || par2 == this.mc.gameSettings.keyBindInventory.keyCode)
/*      */     {
/*  960 */       this.mc.thePlayer.closeScreen();
/*      */     }
/*      */     
/*  963 */     checkHotbarKeys(par2);
/*      */     
/*  965 */     if (this.theSlot != null && this.theSlot.getHasStack())
/*      */     {
/*  967 */       if (par2 == this.mc.gameSettings.keyBindPickBlock.keyCode) {
/*      */         
/*  969 */         handleMouseClick(this.theSlot, this.theSlot.slotNumber, 0, 3);
/*      */       }
/*  971 */       else if (par2 == this.mc.gameSettings.keyBindDrop.keyCode) {
/*      */         
/*  973 */         handleMouseClick(this.theSlot, this.theSlot.slotNumber, isCtrlKeyDown() ? 1 : 0, 4);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean checkHotbarKeys(int par1) {
/*  983 */     if (this.mc.thePlayer.inventory.getItemStack() == null && this.theSlot != null)
/*      */     {
/*  985 */       for (int var2 = 0; var2 < 9; var2++) {
/*      */         
/*  987 */         if (par1 == 2 + var2) {
/*      */           
/*  989 */           handleMouseClick(this.theSlot, this.theSlot.slotNumber, var2, 2);
/*  990 */           return true;
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*  995 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onGuiClosed() {
/* 1003 */     if (this.mc.thePlayer != null) {
/*      */       
/* 1005 */       this.inventorySlots.onContainerClosed(this.mc.thePlayer);
/*      */       
/* 1007 */       if (this instanceof GuiMerchant || this instanceof GuiScreenHorseInventory) {
/* 1008 */         Minecraft.disable_clicks_until = System.currentTimeMillis() + 1000L;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean doesGuiPauseGame() {
/* 1017 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateScreen() {
/* 1025 */     super.updateScreen();
/*      */     
/* 1027 */     if (!this.mc.thePlayer.isEntityAlive() || this.mc.thePlayer.isDead)
/*      */     {
/* 1029 */       this.mc.thePlayer.closeScreen();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public Slot getSlotThatMouseIsOver(int mouse_x, int mouse_y) {
/* 1035 */     for (int i = 0; i < this.inventorySlots.inventorySlots.size(); i++) {
/*      */       
/* 1037 */       if (isMouseOverSlot(this.inventorySlots.getSlot(i), mouse_x, mouse_y)) {
/* 1038 */         return this.inventorySlots.getSlot(i);
/*      */       }
/*      */     } 
/* 1041 */     return null;
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */