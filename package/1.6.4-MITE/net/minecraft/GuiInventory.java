/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.lwjgl.opengl.GL11;
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
/*     */ public class GuiInventory
/*     */   extends InventoryEffectRenderer
/*     */ {
/*     */   private float xSize_lo;
/*     */   private float ySize_lo;
/*     */   
/*     */   public GuiInventory(EntityPlayer par1EntityPlayer) {
/*  23 */     super(par1EntityPlayer.inventoryContainer);
/*  24 */     this.allowUserInput = true;
/*  25 */     par1EntityPlayer.addStat(AchievementList.openInventory, 1);
/*  26 */     par1EntityPlayer.incrementStatForThisWorldFromClient(AchievementList.openInventory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  34 */     if (this.mc.playerController.isInCreativeMode())
/*     */     {
/*  36 */       this.mc.displayGuiScreen(new GuiContainerCreative(this.mc.thePlayer));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  45 */     this.buttonList.clear();
/*     */     
/*  47 */     if (this.mc.playerController.isInCreativeMode()) {
/*     */       
/*  49 */       this.mc.displayGuiScreen(new GuiContainerCreative(this.mc.thePlayer));
/*     */     }
/*     */     else {
/*     */       
/*  53 */       super.initGui();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
/*  64 */     this.fontRenderer.drawString(I18n.getString("container.crafting"), 87, 15, 4210752);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/*  73 */     this.xSize_lo = par1;
/*  74 */     this.ySize_lo = par2;
/*  75 */     super.drawScreen(par1, par2, par3);
/*     */     
/*  77 */     if (GuiScreen.isShiftKeyDown()) {
/*  78 */       drawProfessionsTooltip(par1, par2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
/*  89 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  90 */     this.mc.getTextureManager().bindTexture(field_110408_a);
/*  91 */     int var4 = this.guiLeft;
/*  92 */     int var5 = this.guiTop;
/*  93 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
/*     */ 
/*     */     
/*  96 */     if (this.mc.thePlayer.crafting_ticks > 0) {
/*  97 */       drawTexturedModalRect(var4 + 125, var5 + 36, 176, 0, this.mc.thePlayer.crafting_ticks * 17 / this.mc.thePlayer.crafting_period, 14);
/*     */     }
/*  99 */     func_110423_a(var4 + 51, var5 + 75, 30, (var4 + 51) - this.xSize_lo, (var5 + 75 - 50) - this.ySize_lo, this.mc.thePlayer);
/*     */     
/* 101 */     SlotCrafting slot_crafting = (SlotCrafting)this.inventorySlots.getSlot(0);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 106 */     if (slot_crafting.getNumCraftingResults(this.mc.thePlayer) > 1) {
/*     */       
/* 108 */       this.mc.getTextureManager().bindTexture(GuiIngame.MITE_icons);
/*     */       
/* 110 */       float grey = 0.54509807F;
/* 111 */       GL11.glColor4f(grey, grey, grey, 1.0F);
/*     */       
/* 113 */       drawTexturedModalRect(var4 + 163, var5 + 36, 16, 0, 3, 3);
/*     */       
/* 115 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_110423_a(int par0, int par1, int par2, float par3, float par4, EntityLivingBase par5EntityLivingBase) {
/* 121 */     GL11.glEnable(2903);
/* 122 */     GL11.glPushMatrix();
/* 123 */     GL11.glTranslatef(par0, par1, 50.0F);
/* 124 */     GL11.glScalef(-par2, par2, par2);
/* 125 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 126 */     float var6 = par5EntityLivingBase.renderYawOffset;
/* 127 */     float var7 = par5EntityLivingBase.rotationYaw;
/* 128 */     float var8 = par5EntityLivingBase.rotationPitch;
/* 129 */     float var9 = par5EntityLivingBase.prevRotationYawHead;
/* 130 */     float var10 = par5EntityLivingBase.rotationYawHead;
/* 131 */     GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
/* 132 */     RenderHelper.enableStandardItemLighting();
/* 133 */     GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
/* 134 */     GL11.glRotatef(-((float)Math.atan((par4 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
/* 135 */     par5EntityLivingBase.renderYawOffset = (float)Math.atan((par3 / 40.0F)) * 20.0F;
/* 136 */     par5EntityLivingBase.rotationYaw = (float)Math.atan((par3 / 40.0F)) * 40.0F;
/* 137 */     par5EntityLivingBase.rotationPitch = -((float)Math.atan((par4 / 40.0F))) * 20.0F;
/* 138 */     par5EntityLivingBase.rotationYawHead = par5EntityLivingBase.rotationYaw;
/* 139 */     par5EntityLivingBase.prevRotationYawHead = par5EntityLivingBase.rotationYaw;
/* 140 */     GL11.glTranslatef(0.0F, par5EntityLivingBase.yOffset, 0.0F);
/* 141 */     RenderManager.instance.playerViewY = 180.0F;
/* 142 */     par5EntityLivingBase.disable_shadow = true;
/* 143 */     RenderManager.instance.renderEntityWithPosYaw(par5EntityLivingBase, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
/* 144 */     par5EntityLivingBase.disable_shadow = false;
/* 145 */     par5EntityLivingBase.renderYawOffset = var6;
/* 146 */     par5EntityLivingBase.rotationYaw = var7;
/* 147 */     par5EntityLivingBase.rotationPitch = var8;
/* 148 */     par5EntityLivingBase.prevRotationYawHead = var9;
/* 149 */     par5EntityLivingBase.rotationYawHead = var10;
/* 150 */     GL11.glPopMatrix();
/* 151 */     RenderHelper.disableStandardItemLighting();
/* 152 */     GL11.glDisable(32826);
/* 153 */     OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
/* 154 */     GL11.glDisable(3553);
/* 155 */     OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/* 163 */     if (par1GuiButton.id == 0)
/*     */     {
/* 165 */       this.mc.displayGuiScreen(new GuiAchievements(this.mc.statFileWriter));
/*     */     }
/*     */     
/* 168 */     if (par1GuiButton.id == 1)
/*     */     {
/* 170 */       this.mc.displayGuiScreen(new GuiStats(this, this.mc.statFileWriter));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawProfessionsTooltip(int mouse_x, int mouse_y) {
/* 176 */     if (!this.mc.theWorld.areSkillsEnabled()) {
/*     */       return;
/*     */     }
/* 179 */     int cursed_box_left = this.guiLeft + 26;
/* 180 */     int cursed_box_top = this.guiTop + 8;
/* 181 */     int cursed_box_right = cursed_box_left + 51;
/* 182 */     int cursed_box_bottom = cursed_box_top + 69;
/*     */     
/* 184 */     if (mouse_x >= cursed_box_left && mouse_x <= cursed_box_right && mouse_y >= cursed_box_top && mouse_y <= cursed_box_bottom) {
/*     */       
/* 186 */       String professions = this.mc.thePlayer.getSkillsString(true);
/*     */       
/* 188 */       if (professions == null) {
/*     */         return;
/*     */       }
/* 191 */       List<String> list = new ArrayList();
/*     */       
/* 193 */       list.add(professions);
/*     */       
/* 195 */       func_102021_a(list, mouse_x, mouse_y);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiInventory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */