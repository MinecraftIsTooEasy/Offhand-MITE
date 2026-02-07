/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.util.glu.Project;
/*     */ 
/*     */ 
/*     */ public class GuiEnchantment
/*     */   extends GuiContainer
/*     */ {
/*  13 */   private static final ResourceLocation enchantingTableGuiTextures = new ResourceLocation("textures/gui/container/enchanting_table.png");
/*  14 */   private static final ResourceLocation enchantingTableBookTextures = new ResourceLocation("textures/entity/enchanting_table_book.png");
/*     */ 
/*     */   
/*  17 */   private static final ModelBook bookModel = new ModelBook();
/*  18 */   private Random rand = new Random();
/*     */   
/*     */   private ContainerEnchantment containerEnchantment;
/*     */   
/*     */   public int field_74214_o;
/*     */   
/*     */   public float field_74213_p;
/*     */   
/*     */   public float field_74212_q;
/*     */   
/*     */   public float field_74211_r;
/*     */   public float field_74210_s;
/*     */   public float field_74209_t;
/*     */   public float field_74208_u;
/*     */   ItemStack theItemStack;
/*     */   private String field_94079_C;
/*     */   
/*     */   public GuiEnchantment(EntityPlayer player, World par2World, int par3, int par4, int par5, String par6Str) {
/*  36 */     super(new ContainerEnchantment(player, par2World, par3, par4, par5));
/*  37 */     this.containerEnchantment = (ContainerEnchantment)this.inventorySlots;
/*  38 */     this.field_94079_C = par6Str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
/*  49 */     this.fontRenderer.drawString((this.field_94079_C == null) ? I18n.getString("container.enchant") : this.field_94079_C, 12, 6, 2631720);
/*  50 */     this.fontRenderer.drawString(I18n.getString("container.inventory"), 7, this.ySize - 96 + 3, 2631720);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  58 */     super.updateScreen();
/*  59 */     func_74205_h();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int par1, int par2, int par3) {
/*  67 */     super.mouseClicked(par1, par2, par3);
/*     */     
/*  69 */     if (this.mc.theWorld.areSkillsEnabled() && !this.mc.thePlayer.hasSkill(Skill.ENCHANTING)) {
/*     */       return;
/*     */     }
/*  72 */     int var4 = (this.width - this.xSize) / 2;
/*  73 */     int var5 = (this.height - this.ySize) / 2;
/*     */     
/*  75 */     for (int var6 = 0; var6 < 3; var6++) {
/*     */       
/*  77 */       int var7 = par1 - var4 + 60;
/*  78 */       int var8 = par2 - var5 + 14 + 19 * var6;
/*     */       
/*  80 */       if (var7 >= 0 && var8 >= 0 && var7 < 108 && var8 < 19 && this.containerEnchantment.enchantItem(this.mc.thePlayer, var6))
/*     */       {
/*  82 */         this.mc.playerController.sendEnchantPacket(this.containerEnchantment.windowId, var6);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
/*  92 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  93 */     this.mc.getTextureManager().bindTexture(enchantingTableGuiTextures);
/*  94 */     int var4 = (this.width - this.xSize) / 2;
/*  95 */     int var5 = (this.height - this.ySize) / 2;
/*  96 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
/*  97 */     GL11.glPushMatrix();
/*  98 */     GL11.glMatrixMode(5889);
/*  99 */     GL11.glPushMatrix();
/* 100 */     GL11.glLoadIdentity();
/* 101 */     ScaledResolution var6 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
/* 102 */     GL11.glViewport((var6.getScaledWidth() - 320) / 2 * var6.getScaleFactor(), (var6.getScaledHeight() - 240) / 2 * var6.getScaleFactor(), 320 * var6.getScaleFactor(), 240 * var6.getScaleFactor());
/* 103 */     GL11.glTranslatef(-0.34F, 0.23F, 0.0F);
/* 104 */     Project.gluPerspective(90.0F, 1.3333334F, 9.0F, 80.0F);
/* 105 */     float var7 = 1.0F;
/* 106 */     GL11.glMatrixMode(5888);
/* 107 */     GL11.glLoadIdentity();
/* 108 */     RenderHelper.enableStandardItemLighting();
/* 109 */     GL11.glTranslatef(0.0F, 3.3F, -16.0F);
/* 110 */     GL11.glScalef(var7, var7, var7);
/* 111 */     float var8 = 5.0F;
/* 112 */     GL11.glScalef(var8, var8, var8);
/* 113 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 114 */     this.mc.getTextureManager().bindTexture(enchantingTableBookTextures);
/* 115 */     GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/* 116 */     float var9 = this.field_74208_u + (this.field_74209_t - this.field_74208_u) * par1;
/* 117 */     GL11.glTranslatef((1.0F - var9) * 0.2F, (1.0F - var9) * 0.1F, (1.0F - var9) * 0.25F);
/* 118 */     GL11.glRotatef(-(1.0F - var9) * 90.0F - 90.0F, 0.0F, 1.0F, 0.0F);
/* 119 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 120 */     float var10 = this.field_74212_q + (this.field_74213_p - this.field_74212_q) * par1 + 0.25F;
/* 121 */     float var11 = this.field_74212_q + (this.field_74213_p - this.field_74212_q) * par1 + 0.75F;
/* 122 */     var10 = (var10 - MathHelper.truncateDoubleToInt(var10)) * 1.6F - 0.3F;
/* 123 */     var11 = (var11 - MathHelper.truncateDoubleToInt(var11)) * 1.6F - 0.3F;
/*     */     
/* 125 */     if (var10 < 0.0F)
/*     */     {
/* 127 */       var10 = 0.0F;
/*     */     }
/*     */     
/* 130 */     if (var11 < 0.0F)
/*     */     {
/* 132 */       var11 = 0.0F;
/*     */     }
/*     */     
/* 135 */     if (var10 > 1.0F)
/*     */     {
/* 137 */       var10 = 1.0F;
/*     */     }
/*     */     
/* 140 */     if (var11 > 1.0F)
/*     */     {
/* 142 */       var11 = 1.0F;
/*     */     }
/*     */     
/* 145 */     GL11.glEnable(32826);
/* 146 */     bookModel.render((Entity)null, 0.0F, var10, var11, var9, 0.0F, 0.0625F);
/* 147 */     GL11.glDisable(32826);
/* 148 */     RenderHelper.disableStandardItemLighting();
/* 149 */     GL11.glMatrixMode(5889);
/* 150 */     GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
/* 151 */     GL11.glPopMatrix();
/* 152 */     GL11.glMatrixMode(5888);
/* 153 */     GL11.glPopMatrix();
/* 154 */     RenderHelper.disableStandardItemLighting();
/* 155 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 156 */     EnchantmentNameParts.instance.setRandSeed(this.containerEnchantment.nameSeed);
/*     */     
/* 158 */     List<String> tooltips = null;
/*     */     
/* 160 */     for (int var12 = 0; var12 < 3; var12++) {
/*     */       
/* 162 */       String var13 = EnchantmentNameParts.instance.generateRandomEnchantName();
/* 163 */       this.zLevel = 0.0F;
/* 164 */       this.mc.getTextureManager().bindTexture(enchantingTableGuiTextures);
/*     */       
/* 166 */       int var14 = this.containerEnchantment.enchantLevels[var12];
/* 167 */       int experience_cost = Enchantment.getExperienceCost(var14);
/* 168 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/* 170 */       if (var14 == 0) {
/*     */         
/* 172 */         drawTexturedModalRect(var4 + 60, var5 + 14 + 19 * var12, 0, 185, 108, 19);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 177 */         String var15 = "" + experience_cost;
/* 178 */         FontRenderer var16 = this.mc.standardGalacticFontRenderer;
/* 179 */         int var17 = 6839882;
/*     */ 
/*     */ 
/*     */         
/* 183 */         if (this.mc.thePlayer.experience < experience_cost && !this.mc.thePlayer.capabilities.isCreativeMode) {
/*     */           
/* 185 */           drawTexturedModalRect(var4 + 60, var5 + 14 + 19 * var12, 0, 185, 108, 19);
/* 186 */           var16.drawSplitString(var13, var4 + 62, var5 + 16 + 19 * var12, 104, (var17 & 0xFEFEFE) >> 1);
/* 187 */           var16 = this.mc.fontRenderer;
/* 188 */           var17 = 4226832;
/* 189 */           var16.drawStringWithShadow(var15, var4 + 62 + 104 - var16.getStringWidth(var15), var5 + 16 + 19 * var12 + 7, var17);
/*     */         }
/*     */         else {
/*     */           
/* 193 */           int var18 = par2 - var4 + 60;
/* 194 */           int var19 = par3 - var5 + 14 + 19 * var12;
/*     */           
/* 196 */           if (var18 >= 0 && var19 >= 0 && var18 < 108 && var19 < 19) {
/*     */             
/* 198 */             drawTexturedModalRect(var4 + 60, var5 + 14 + 19 * var12, 0, 204, 108, 19);
/* 199 */             var17 = 16777088;
/*     */             
/* 201 */             if (this.mc.theWorld.areSkillsEnabled() && !this.mc.thePlayer.hasSkill(Skill.ENCHANTING))
/*     */             {
/* 203 */               tooltips = new ArrayList();
/* 204 */               tooltips.add(EnumChatFormatting.GRAY + "Requires " + Skill.ENCHANTING.getLocalizedName(true));
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 209 */             drawTexturedModalRect(var4 + 60, var5 + 14 + 19 * var12, 0, 166, 108, 19);
/*     */           } 
/*     */           
/* 212 */           var16.drawSplitString(var13, var4 + 62, var5 + 16 + 19 * var12, 104, var17);
/* 213 */           var16 = this.mc.fontRenderer;
/* 214 */           var17 = 8453920;
/* 215 */           var16.drawStringWithShadow(var15, var4 + 62 + 104 - var16.getStringWidth(var15), var5 + 16 + 19 * var12 + 7, var17);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 220 */     if (tooltips != null) {
/* 221 */       func_102021_a(tooltips, par2, par3);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_74205_h() {
/* 226 */     ItemStack var1 = this.inventorySlots.getSlot(0).getStack();
/*     */     
/* 228 */     if (!ItemStack.areItemStacksEqual(var1, this.theItemStack)) {
/*     */       
/* 230 */       this.theItemStack = var1;
/*     */ 
/*     */       
/*     */       do {
/* 234 */         this.field_74211_r += (this.rand.nextInt(4) - this.rand.nextInt(4));
/*     */       }
/* 236 */       while (this.field_74213_p <= this.field_74211_r + 1.0F && this.field_74213_p >= this.field_74211_r - 1.0F);
/*     */     } 
/*     */     
/* 239 */     this.field_74214_o++;
/* 240 */     this.field_74212_q = this.field_74213_p;
/* 241 */     this.field_74208_u = this.field_74209_t;
/* 242 */     boolean var2 = false;
/*     */     
/* 244 */     for (int var3 = 0; var3 < 3; var3++) {
/*     */       
/* 246 */       if (this.containerEnchantment.enchantLevels[var3] != 0)
/*     */       {
/* 248 */         var2 = true;
/*     */       }
/*     */     } 
/*     */     
/* 252 */     if (var2) {
/*     */       
/* 254 */       this.field_74209_t += 0.2F;
/*     */     }
/*     */     else {
/*     */       
/* 258 */       this.field_74209_t -= 0.2F;
/*     */     } 
/*     */     
/* 261 */     if (this.field_74209_t < 0.0F)
/*     */     {
/* 263 */       this.field_74209_t = 0.0F;
/*     */     }
/*     */     
/* 266 */     if (this.field_74209_t > 1.0F)
/*     */     {
/* 268 */       this.field_74209_t = 1.0F;
/*     */     }
/*     */     
/* 271 */     float var5 = (this.field_74211_r - this.field_74213_p) * 0.4F;
/* 272 */     float var4 = 0.2F;
/*     */     
/* 274 */     if (var5 < -var4)
/*     */     {
/* 276 */       var5 = -var4;
/*     */     }
/*     */     
/* 279 */     if (var5 > var4)
/*     */     {
/* 281 */       var5 = var4;
/*     */     }
/*     */     
/* 284 */     this.field_74210_s += (var5 - this.field_74210_s) * 0.9F;
/* 285 */     this.field_74213_p += this.field_74210_s;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiEnchantment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */