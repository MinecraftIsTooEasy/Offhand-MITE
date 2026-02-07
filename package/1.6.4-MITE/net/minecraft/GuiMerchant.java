/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class GuiMerchant
/*     */   extends GuiContainer
/*     */ {
/*  10 */   private static final ResourceLocation merchantGuiTextures = new ResourceLocation("textures/gui/container/villager.png");
/*     */   
/*     */   private IMerchant theIMerchant;
/*     */   
/*     */   private GuiButtonMerchant nextRecipeButtonIndex;
/*     */   
/*     */   private GuiButtonMerchant previousRecipeButtonIndex;
/*     */   
/*     */   private int currentRecipeIndex;
/*     */   
/*     */   private String field_94082_v;
/*     */   
/*     */   public GuiMerchant(EntityPlayer player, IMerchant par2IMerchant, String par4Str) {
/*  23 */     super(new ContainerMerchant(player, par2IMerchant));
/*  24 */     this.theIMerchant = par2IMerchant;
/*  25 */     this.field_94082_v = (par4Str != null && par4Str.length() >= 1) ? par4Str : I18n.getString("entity.Villager.name");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  33 */     super.initGui();
/*  34 */     int var1 = (this.width - this.xSize) / 2;
/*  35 */     int var2 = (this.height - this.ySize) / 2;
/*  36 */     this.buttonList.add(this.nextRecipeButtonIndex = new GuiButtonMerchant(1, var1 + 120 + 27, var2 + 24 - 1, true));
/*  37 */     this.buttonList.add(this.previousRecipeButtonIndex = new GuiButtonMerchant(2, var1 + 36 - 19, var2 + 24 - 1, false));
/*  38 */     this.nextRecipeButtonIndex.enabled = false;
/*  39 */     this.previousRecipeButtonIndex.enabled = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
/*  47 */     this.fontRenderer.drawString(this.field_94082_v, this.xSize / 2 - this.fontRenderer.getStringWidth(this.field_94082_v) / 2, 6, 4210752);
/*  48 */     this.fontRenderer.drawString(I18n.getString("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  56 */     super.updateScreen();
/*  57 */     MerchantRecipeList var1 = this.theIMerchant.getRecipes(this.mc.thePlayer);
/*     */     
/*  59 */     if (var1 != null) {
/*     */       
/*  61 */       this.nextRecipeButtonIndex.enabled = (this.currentRecipeIndex < var1.size() - 1);
/*  62 */       this.previousRecipeButtonIndex.enabled = (this.currentRecipeIndex > 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/*  71 */     boolean var2 = false;
/*     */     
/*  73 */     if (par1GuiButton == this.nextRecipeButtonIndex) {
/*     */       
/*  75 */       this.currentRecipeIndex++;
/*  76 */       var2 = true;
/*     */     }
/*  78 */     else if (par1GuiButton == this.previousRecipeButtonIndex) {
/*     */       
/*  80 */       this.currentRecipeIndex--;
/*  81 */       var2 = true;
/*     */     } 
/*     */     
/*  84 */     if (var2) {
/*     */       
/*  86 */       ((ContainerMerchant)this.inventorySlots).setCurrentRecipeIndex(this.currentRecipeIndex);
/*  87 */       ByteArrayOutputStream var3 = new ByteArrayOutputStream();
/*  88 */       DataOutputStream var4 = new DataOutputStream(var3);
/*     */ 
/*     */       
/*     */       try {
/*  92 */         var4.writeInt(this.currentRecipeIndex);
/*  93 */         this.mc.getNetHandler().addToSendQueue(new Packet250CustomPayload("MC|TrSel", var3.toByteArray()));
/*     */       }
/*  95 */       catch (Exception var6) {
/*     */         
/*  97 */         var6.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
/* 107 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 108 */     this.mc.getTextureManager().bindTexture(merchantGuiTextures);
/* 109 */     int var4 = (this.width - this.xSize) / 2;
/* 110 */     int var5 = (this.height - this.ySize) / 2;
/* 111 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
/* 112 */     MerchantRecipeList var6 = this.theIMerchant.getRecipes(this.mc.thePlayer);
/*     */     
/* 114 */     if (var6 != null && !var6.isEmpty()) {
/*     */       
/* 116 */       int var7 = this.currentRecipeIndex;
/* 117 */       MerchantRecipe var8 = (MerchantRecipe)var6.get(var7);
/*     */       
/* 119 */       if (var8.func_82784_g()) {
/*     */         
/* 121 */         this.mc.getTextureManager().bindTexture(merchantGuiTextures);
/* 122 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 123 */         GL11.glDisable(2896);
/* 124 */         drawTexturedModalRect(this.guiLeft + 83, this.guiTop + 21, 212, 0, 28, 21);
/* 125 */         drawTexturedModalRect(this.guiLeft + 83, this.guiTop + 51, 212, 0, 28, 21);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/* 135 */     super.drawScreen(par1, par2, par3);
/* 136 */     MerchantRecipeList var4 = this.theIMerchant.getRecipes(this.mc.thePlayer);
/*     */     
/* 138 */     if (var4 != null && !var4.isEmpty()) {
/*     */       
/* 140 */       int var5 = (this.width - this.xSize) / 2;
/* 141 */       int var6 = (this.height - this.ySize) / 2;
/* 142 */       int var7 = this.currentRecipeIndex;
/* 143 */       MerchantRecipe var8 = (MerchantRecipe)var4.get(var7);
/* 144 */       GL11.glPushMatrix();
/* 145 */       ItemStack var9 = var8.getItemToBuy();
/* 146 */       ItemStack var10 = var8.getSecondItemToBuy();
/* 147 */       ItemStack var11 = var8.getItemToSell();
/* 148 */       RenderHelper.enableGUIStandardItemLighting();
/* 149 */       GL11.glDisable(2896);
/* 150 */       GL11.glEnable(32826);
/* 151 */       GL11.glEnable(2903);
/* 152 */       GL11.glEnable(2896);
/* 153 */       itemRenderer.zLevel = 100.0F;
/* 154 */       itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.getTextureManager(), var9, var5 + 36, var6 + 24);
/* 155 */       itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.getTextureManager(), var9, var5 + 36, var6 + 24);
/*     */       
/* 157 */       if (var10 != null) {
/*     */         
/* 159 */         itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.getTextureManager(), var10, var5 + 62, var6 + 24);
/* 160 */         itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.getTextureManager(), var10, var5 + 62, var6 + 24);
/*     */       } 
/*     */       
/* 163 */       itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.getTextureManager(), var11, var5 + 120, var6 + 24);
/* 164 */       itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.getTextureManager(), var11, var5 + 120, var6 + 24);
/* 165 */       itemRenderer.zLevel = 0.0F;
/* 166 */       GL11.glDisable(2896);
/*     */       
/* 168 */       if (isPointInRegion(36, 24, 16, 16, par1, par2)) {
/*     */         
/* 170 */         drawItemStackTooltip(var9, par1, par2);
/*     */       }
/* 172 */       else if (var10 != null && isPointInRegion(62, 24, 16, 16, par1, par2)) {
/*     */         
/* 174 */         drawItemStackTooltip(var10, par1, par2);
/*     */       }
/* 176 */       else if (isPointInRegion(120, 24, 16, 16, par1, par2)) {
/*     */         
/* 178 */         drawItemStackTooltip(var11, par1, par2);
/*     */       } 
/*     */       
/* 181 */       GL11.glPopMatrix();
/* 182 */       GL11.glEnable(2896);
/* 183 */       GL11.glEnable(2929);
/* 184 */       RenderHelper.enableStandardItemLighting();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IMerchant getIMerchant() {
/* 193 */     return this.theIMerchant;
/*     */   }
/*     */ 
/*     */   
/*     */   static ResourceLocation func_110417_h() {
/* 198 */     return merchantGuiTextures;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiMerchant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */