/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public abstract class InventoryEffectRenderer
/*     */   extends GuiContainer
/*     */ {
/*     */   private boolean field_74222_o;
/*     */   private int initial_tick;
/*  15 */   private static final ResourceLocation sugar_icon = new ResourceLocation("textures/items/sugar.png");
/*     */ 
/*     */   
/*     */   public InventoryEffectRenderer(Container par1Container) {
/*  19 */     super(par1Container);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  27 */     super.initGui();
/*     */ 
/*     */ 
/*     */     
/*  31 */     if (!this.mc.thePlayer.getActivePotionEffects().isEmpty() || this.mc.thePlayer.isMalnourished() || this.mc.thePlayer.isInsulinResistant() || this.mc.thePlayer.is_cursed) {
/*     */       
/*  33 */       this.guiLeft = 160 + (this.width - this.xSize - 200) / 2;
/*  34 */       this.field_74222_o = true;
/*     */     } 
/*     */     
/*  37 */     this.initial_tick = (int)this.mc.theWorld.getTotalWorldTime();
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawMalnourishedBoxTooltip(int mouse_x, int mouse_y) {
/*  42 */     int malnourished_box_left = this.guiLeft - 128;
/*  43 */     int malnourished_box_top = this.guiTop;
/*  44 */     int malnourished_box_right = malnourished_box_left + 123;
/*  45 */     int malnourished_box_bottom = malnourished_box_top + 31;
/*     */ 
/*     */     
/*  48 */     if (this.mc.thePlayer.isMalnourished() && mouse_x >= malnourished_box_left && mouse_x <= malnourished_box_right && mouse_y >= malnourished_box_top && mouse_y <= malnourished_box_bottom) {
/*     */       
/*  50 */       List<String> list = new ArrayList();
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
/*  67 */       Translator.addToList(EnumChatFormatting.GRAY, "effect.malnourished.general", list);
/*     */       
/*  69 */       list.add(EnumChatFormatting.GRAY + "");
/*     */       
/*  71 */       Translator.addToList(EnumChatFormatting.GRAY, "effect.malnourished." + (this.mc.thePlayer.is_malnourished_in_protein ? "protein" : "phytonutrients"), list);
/*     */ 
/*     */ 
/*     */       
/*  75 */       func_102021_a(list, mouse_x, mouse_y, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawInsulinResistantBoxTooltip(int mouse_x, int mouse_y) {
/*  81 */     int box_left = this.guiLeft - 128;
/*  82 */     int box_top = this.guiTop;
/*  83 */     int box_right = box_left + 123;
/*  84 */     int box_bottom = box_top + 31;
/*     */     
/*  86 */     if (this.mc.thePlayer.isMalnourished()) {
/*     */       
/*  88 */       box_top += 33;
/*  89 */       box_bottom += 33;
/*     */     } 
/*     */     
/*  92 */     if (this.mc.thePlayer.isInsulinResistant() && mouse_x >= box_left && mouse_x <= box_right && mouse_y >= box_top && mouse_y <= box_bottom) {
/*     */       
/*  94 */       List list = new ArrayList();
/*     */       
/*  96 */       EnumInsulinResistanceLevel insulin_resistance_level = this.mc.thePlayer.getInsulinResistanceLevel();
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
/* 130 */       Translator.addToList(EnumChatFormatting.GRAY, "effect.insulinResistance." + insulin_resistance_level.getUnlocalizedName(), list);
/*     */       
/* 132 */       func_102021_a(list, mouse_x, mouse_y, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawCurseBoxTooltip(int mouse_x, int mouse_y) {
/* 138 */     int cursed_box_left = this.guiLeft - 128;
/* 139 */     int cursed_box_top = this.guiTop;
/* 140 */     int cursed_box_right = cursed_box_left + 123;
/* 141 */     int cursed_box_bottom = cursed_box_top + 31;
/*     */ 
/*     */     
/* 144 */     if (this.mc.thePlayer.isMalnourished()) {
/*     */       
/* 146 */       cursed_box_top += 33;
/* 147 */       cursed_box_bottom += 33;
/*     */     } 
/*     */     
/* 150 */     if (this.mc.thePlayer.isInsulinResistant()) {
/*     */       
/* 152 */       cursed_box_top += 33;
/* 153 */       cursed_box_bottom += 33;
/*     */     } 
/*     */     
/* 156 */     if (this.mc.thePlayer.is_cursed && mouse_x >= cursed_box_left && mouse_x <= cursed_box_right && mouse_y >= cursed_box_top && mouse_y <= cursed_box_bottom) {
/*     */       
/* 158 */       Curse curse = Curse.cursesList[this.mc.thePlayer.curse_id];
/*     */       
/* 160 */       if (this.mc.thePlayer.curse_effect_known) {
/*     */         
/* 162 */         List list = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 167 */         ItemStack.addTooltipsToList(EnumChatFormatting.GRAY, curse.getTooltip(), list);
/*     */         
/* 169 */         func_102021_a(list, mouse_x, mouse_y, false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/* 179 */     super.drawScreen(par1, par2, par3);
/*     */     
/* 181 */     if (this.field_74222_o)
/*     */     {
/* 183 */       displayDebuffEffects();
/*     */     }
/*     */     
/* 186 */     drawMalnourishedBoxTooltip(par1, par2);
/* 187 */     drawInsulinResistantBoxTooltip(par1, par2);
/* 188 */     drawCurseBoxTooltip(par1, par2);
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
/*     */   private void displayDebuffEffects() {
/* 251 */     int var1 = this.guiLeft - 128;
/* 252 */     int var2 = this.guiTop;
/* 253 */     boolean var3 = true;
/* 254 */     Collection var4 = this.mc.thePlayer.getActivePotionEffects();
/*     */     
/* 256 */     int num_effects = var4.size();
/*     */ 
/*     */     
/* 259 */     if (this.mc.thePlayer.isMalnourished()) {
/* 260 */       num_effects++;
/*     */     }
/* 262 */     if (this.mc.thePlayer.isInsulinResistant()) {
/* 263 */       num_effects++;
/*     */     }
/* 265 */     if (this.mc.thePlayer.is_cursed) {
/* 266 */       num_effects++;
/*     */     }
/* 268 */     if (num_effects > 0) {
/*     */       
/* 270 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 271 */       GL11.glDisable(2896);
/* 272 */       int var5 = 33;
/*     */       
/* 274 */       if (num_effects > 5)
/*     */       {
/* 276 */         var5 = 132 / (num_effects - 1);
/*     */       }
/*     */ 
/*     */       
/* 280 */       if (this.mc.thePlayer.isMalnourished()) {
/*     */         
/* 282 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 283 */         this.mc.getTextureManager().bindTexture(field_110408_a);
/* 284 */         drawTexturedModalRect(var1, var2, 0, 166, 140, 32);
/*     */         
/* 286 */         this.mc.getTextureManager().bindTexture(GuiIngame.MITE_icons);
/* 287 */         drawTexturedModalRect(var1 + 6, var2 + 7, 18, 198, 18, 18);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 292 */         String var11 = I18n.getString("effect.malnourished");
/*     */         
/* 294 */         this.fontRenderer.drawStringWithShadow(var11, var1 + 10 + 18 - 1, var2 + 6 + 1, 16777215);
/*     */         
/* 296 */         String var10 = (((int)this.mc.theWorld.getTotalWorldTime() - this.initial_tick) / 100 % 2 == 0) ? I18n.getString("effect.malnourished.slowHealing") : I18n.getString("effect.malnourished.plus50PercentHunger");
/*     */         
/* 298 */         this.fontRenderer.drawStringWithShadow(var10, var1 + 10 + 18 - 1, var2 + 6 + 10 + 1, 8355711);
/*     */         
/* 300 */         var2 += var5;
/*     */       } 
/*     */       
/* 303 */       if (this.mc.thePlayer.isInsulinResistant()) {
/*     */         
/* 305 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 306 */         this.mc.getTextureManager().bindTexture(field_110408_a);
/* 307 */         drawTexturedModalRect(var1, var2, 0, 166, 140, 32);
/*     */         
/* 309 */         this.mc.getTextureManager().bindTexture(sugar_icon);
/* 310 */         drawTexturedModalRect2(var1 + 7, var2 + 8, 16, 16);
/*     */ 
/*     */ 
/*     */         
/* 314 */         EnumInsulinResistanceLevel insulin_resistance_level = this.mc.thePlayer.getInsulinResistanceLevel();
/*     */         
/* 316 */         GL11.glColor4f(insulin_resistance_level.getRedAsFloat(), insulin_resistance_level.getGreenAsFloat(), insulin_resistance_level.getBlueAsFloat(), 1.0F);
/*     */         
/* 318 */         this.mc.getTextureManager().bindTexture(GuiIngame.MITE_icons);
/* 319 */         drawTexturedModalRect(var1 + 6, var2 + 7, 54, 198, 18, 18);
/*     */         
/* 321 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */ 
/*     */ 
/*     */         
/* 325 */         String var11 = I18n.getString("effect.insulinResistance");
/*     */         
/* 327 */         this.fontRenderer.drawStringWithShadow(var11, var1 + 10 + 18 - 1, var2 + 6 + 1, 16777215);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 333 */         String var10 = StringUtils.ticksToElapsedTime(this.mc.thePlayer.getInsulinResistance());
/*     */         
/* 335 */         this.fontRenderer.drawStringWithShadow(var10, var1 + 10 + 18 - 1, var2 + 6 + 10 + 1, 8355711);
/*     */         
/* 337 */         var2 += var5;
/*     */       } 
/*     */       
/* 340 */       if (this.mc.thePlayer.is_cursed) {
/*     */         
/* 342 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 343 */         this.mc.getTextureManager().bindTexture(field_110408_a);
/* 344 */         drawTexturedModalRect(var1, var2, 0, 166, 140, 32);
/*     */         
/* 346 */         this.mc.getTextureManager().bindTexture(GuiIngame.MITE_icons);
/* 347 */         drawTexturedModalRect(var1 + 6, var2 + 7, 0, 198, 18, 18);
/*     */         
/* 349 */         String var11 = I18n.getString("effect.cursed");
/*     */         
/* 351 */         this.fontRenderer.drawStringWithShadow(var11, var1 + 10 + 18 - 1, var2 + 6 + 1, 16777215);
/* 352 */         String var10 = this.mc.thePlayer.curse_effect_known ? (EnumChatFormatting.DARK_PURPLE + this.mc.thePlayer.getCurse().getTitle()) : Translator.get("curse.unknown");
/* 353 */         this.fontRenderer.drawStringWithShadow(var10, var1 + 10 + 18 - 1, var2 + 6 + 10 + 1, 8355711);
/*     */         
/* 355 */         var2 += var5;
/*     */       } 
/*     */       
/* 358 */       for (Iterator<PotionEffect> var6 = this.mc.thePlayer.getActivePotionEffects().iterator(); var6.hasNext(); var2 += var5) {
/*     */         
/* 360 */         PotionEffect var7 = var6.next();
/* 361 */         Potion var8 = Potion.potionTypes[var7.getPotionID()];
/* 362 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 363 */         this.mc.getTextureManager().bindTexture(field_110408_a);
/* 364 */         drawTexturedModalRect(var1, var2, 0, 166, 140, 32);
/*     */         
/* 366 */         if (var8.hasStatusIcon()) {
/*     */           
/* 368 */           int var9 = var8.getStatusIconIndex();
/* 369 */           drawTexturedModalRect(var1 + 6, var2 + 7, 0 + var9 % 8 * 18, 198 + var9 / 8 * 18, 18, 18);
/*     */         } 
/*     */         
/* 372 */         String var11 = I18n.getString(var8.getName());
/*     */         
/* 374 */         if (var7.getAmplifier() == 1) {
/*     */           
/* 376 */           var11 = var11 + " II";
/*     */         }
/* 378 */         else if (var7.getAmplifier() == 2) {
/*     */           
/* 380 */           var11 = var11 + " III";
/*     */         }
/* 382 */         else if (var7.getAmplifier() == 3) {
/*     */           
/* 384 */           var11 = var11 + " IV";
/*     */         } 
/*     */         
/* 387 */         this.fontRenderer.drawStringWithShadow(var11, var1 + 10 + 18 - 1, var2 + 6 + 1, 16777215);
/* 388 */         String var10 = Potion.getDurationString(var7);
/* 389 */         this.fontRenderer.drawStringWithShadow(var10, var1 + 10 + 18 - 1, var2 + 6 + 10 + 1, 8355711);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\InventoryEffectRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */