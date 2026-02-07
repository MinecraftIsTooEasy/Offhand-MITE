/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.util.Iterator;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class GuiBeacon
/*     */   extends GuiContainer {
/*  10 */   private static final ResourceLocation beaconGuiTextures = new ResourceLocation("textures/gui/container/beacon.png");
/*     */   
/*     */   private TileEntityBeacon beacon;
/*     */   
/*     */   private GuiBeaconButtonConfirm beaconConfirmButton;
/*     */   
/*     */   private boolean buttonsNotDrawn;
/*     */   
/*     */   public GuiBeacon(EntityPlayer player, TileEntityBeacon par2TileEntityBeacon) {
/*  19 */     super(new ContainerBeacon(player, par2TileEntityBeacon));
/*  20 */     this.beacon = par2TileEntityBeacon;
/*  21 */     this.xSize = 230;
/*  22 */     this.ySize = 219;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  30 */     super.initGui();
/*  31 */     this.buttonList.add(this.beaconConfirmButton = new GuiBeaconButtonConfirm(this, -1, this.guiLeft + 164, this.guiTop + 107));
/*  32 */     this.buttonList.add(new GuiBeaconButtonCancel(this, -2, this.guiLeft + 190, this.guiTop + 107));
/*  33 */     this.buttonsNotDrawn = true;
/*  34 */     this.beaconConfirmButton.enabled = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  42 */     super.updateScreen();
/*     */     
/*  44 */     if (this.buttonsNotDrawn && this.beacon.getLevels() >= 0) {
/*     */       
/*  46 */       this.buttonsNotDrawn = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  53 */       for (int var1 = 0; var1 <= 2; var1++) {
/*     */         
/*  55 */         int i = (TileEntityBeacon.effectsList[var1]).length;
/*  56 */         int j = i * 22 + (i - 1) * 2;
/*     */         
/*  58 */         for (int k = 0; k < i; k++) {
/*     */           
/*  60 */           int var5 = (TileEntityBeacon.effectsList[var1][k]).id;
/*  61 */           GuiBeaconButtonPower var6 = new GuiBeaconButtonPower(this, var1 << 8 | var5, this.guiLeft + 76 + k * 24 - j / 2, this.guiTop + 22 + var1 * 25, var5, var1);
/*  62 */           this.buttonList.add(var6);
/*     */           
/*  64 */           if (var1 >= this.beacon.getLevels()) {
/*     */             
/*  66 */             var6.enabled = false;
/*     */           }
/*  68 */           else if (var5 == this.beacon.getPrimaryEffect()) {
/*     */             
/*  70 */             var6.func_82254_b(true);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  75 */       byte var7 = 3;
/*  76 */       int var2 = (TileEntityBeacon.effectsList[var7]).length + 1;
/*  77 */       int var3 = var2 * 22 + (var2 - 1) * 2;
/*     */       
/*  79 */       for (int var4 = 0; var4 < var2 - 1; var4++) {
/*     */         
/*  81 */         int var5 = (TileEntityBeacon.effectsList[var7][var4]).id;
/*  82 */         GuiBeaconButtonPower var6 = new GuiBeaconButtonPower(this, var7 << 8 | var5, this.guiLeft + 167 + var4 * 24 - var3 / 2, this.guiTop + 47, var5, var7);
/*  83 */         this.buttonList.add(var6);
/*     */         
/*  85 */         if (var7 >= this.beacon.getLevels()) {
/*     */           
/*  87 */           var6.enabled = false;
/*     */         }
/*  89 */         else if (var5 == this.beacon.getSecondaryEffect()) {
/*     */           
/*  91 */           var6.func_82254_b(true);
/*     */         } 
/*     */       } 
/*     */       
/*  95 */       if (this.beacon.getPrimaryEffect() > 0) {
/*     */         
/*  97 */         GuiBeaconButtonPower var8 = new GuiBeaconButtonPower(this, var7 << 8 | this.beacon.getPrimaryEffect(), this.guiLeft + 167 + (var2 - 1) * 24 - var3 / 2, this.guiTop + 47, this.beacon.getPrimaryEffect(), var7);
/*  98 */         this.buttonList.add(var8);
/*     */         
/* 100 */         if (var7 >= this.beacon.getLevels()) {
/*     */           
/* 102 */           var8.enabled = false;
/*     */         }
/* 104 */         else if (this.beacon.getPrimaryEffect() == this.beacon.getSecondaryEffect()) {
/*     */           
/* 106 */           var8.func_82254_b(true);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 111 */     this.beaconConfirmButton.enabled = (this.beacon.getStackInSlot(0) != null && this.beacon.getPrimaryEffect() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/* 119 */     if (par1GuiButton.id == -2) {
/*     */       
/* 121 */       this.mc.displayGuiScreen((GuiScreen)null);
/*     */     }
/* 123 */     else if (par1GuiButton.id == -1) {
/*     */       
/* 125 */       String var2 = "MC|Beacon";
/* 126 */       ByteArrayOutputStream var3 = new ByteArrayOutputStream();
/* 127 */       DataOutputStream var4 = new DataOutputStream(var3);
/*     */ 
/*     */       
/*     */       try {
/* 131 */         var4.writeInt(this.beacon.getPrimaryEffect());
/* 132 */         var4.writeInt(this.beacon.getSecondaryEffect());
/* 133 */         this.mc.getNetHandler().addToSendQueue(new Packet250CustomPayload(var2, var3.toByteArray()));
/*     */       }
/* 135 */       catch (Exception var6) {
/*     */         
/* 137 */         var6.printStackTrace();
/*     */       } 
/*     */       
/* 140 */       this.mc.displayGuiScreen((GuiScreen)null);
/*     */     }
/* 142 */     else if (par1GuiButton instanceof GuiBeaconButtonPower) {
/*     */       
/* 144 */       if (((GuiBeaconButtonPower)par1GuiButton).func_82255_b()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 149 */       int var7 = par1GuiButton.id;
/* 150 */       int var8 = var7 & 0xFF;
/* 151 */       int var9 = var7 >> 8;
/*     */       
/* 153 */       if (var9 < 3) {
/*     */         
/* 155 */         this.beacon.setPrimaryEffect(var8);
/*     */       }
/*     */       else {
/*     */         
/* 159 */         this.beacon.setSecondaryEffect(var8);
/*     */       } 
/*     */       
/* 162 */       this.buttonList.clear();
/* 163 */       initGui();
/* 164 */       updateScreen();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
/* 173 */     RenderHelper.disableStandardItemLighting();
/* 174 */     drawCenteredString(this.fontRenderer, I18n.getString("tile.beacon.primary"), 62, 10, 14737632);
/* 175 */     drawCenteredString(this.fontRenderer, I18n.getString("tile.beacon.secondary"), 169, 10, 14737632);
/* 176 */     Iterator<GuiButton> var3 = this.buttonList.iterator();
/*     */     
/* 178 */     while (var3.hasNext()) {
/*     */       
/* 180 */       GuiButton var4 = var3.next();
/*     */       
/* 182 */       if (var4.func_82252_a()) {
/*     */         
/* 184 */         var4.func_82251_b(par1 - this.guiLeft, par2 - this.guiTop);
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 189 */     RenderHelper.enableGUIStandardItemLighting();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
/* 197 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 198 */     this.mc.getTextureManager().bindTexture(beaconGuiTextures);
/* 199 */     int var4 = (this.width - this.xSize) / 2;
/* 200 */     int var5 = (this.height - this.ySize) / 2;
/* 201 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
/* 202 */     itemRenderer.zLevel = 100.0F;
/* 203 */     itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.getTextureManager(), new ItemStack(Item.emerald), var4 + 42, var5 + 109);
/* 204 */     itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.getTextureManager(), new ItemStack(Item.diamond), var4 + 42 + 22, var5 + 109);
/* 205 */     itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.getTextureManager(), new ItemStack(Item.ingotGold), var4 + 42 + 44, var5 + 109);
/* 206 */     itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.getTextureManager(), new ItemStack(Item.ingotIron), var4 + 42 + 66, var5 + 109);
/* 207 */     itemRenderer.zLevel = 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   static ResourceLocation getBeaconGuiTextures() {
/* 212 */     return beaconGuiTextures;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiBeacon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */