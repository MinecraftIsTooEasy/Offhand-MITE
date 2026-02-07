/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import org.lwjgl.input.Mouse;
/*     */ 
/*     */ 
/*     */ abstract class GuiSlotStats
/*     */   extends GuiSlot
/*     */ {
/*     */   protected int field_77262_g;
/*     */   protected List field_77266_h;
/*     */   protected Comparator field_77267_i;
/*     */   protected int field_77264_j;
/*     */   protected int field_77265_k;
/*     */   final GuiStats statsGui;
/*     */   
/*     */   protected GuiSlotStats(GuiStats par1GuiStats) {
/*  20 */     super(GuiStats.getMinecraft1(par1GuiStats), par1GuiStats.width, par1GuiStats.height, 32, par1GuiStats.height - 64, 20);
/*  21 */     this.statsGui = par1GuiStats;
/*  22 */     this.field_77262_g = -1;
/*  23 */     this.field_77264_j = -1;
/*  24 */     setShowSelectionBox(false);
/*  25 */     func_77223_a(true, 20);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void elementClicked(int par1, boolean par2) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isSelected(int par1) {
/*  38 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawBackground() {
/*  43 */     this.statsGui.drawDefaultBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_77222_a(int par1, int par2, Tessellator par3Tessellator) {
/*  48 */     if (!Mouse.isButtonDown(0))
/*     */     {
/*  50 */       this.field_77262_g = -1;
/*     */     }
/*     */     
/*  53 */     if (this.field_77262_g == 0) {
/*     */       
/*  55 */       GuiStats.drawSprite(this.statsGui, par1 + 115 - 18, par2 + 1, 0, 0);
/*     */     }
/*     */     else {
/*     */       
/*  59 */       GuiStats.drawSprite(this.statsGui, par1 + 115 - 18, par2 + 1, 0, 18);
/*     */     } 
/*     */     
/*  62 */     if (this.field_77262_g == 1) {
/*     */       
/*  64 */       GuiStats.drawSprite(this.statsGui, par1 + 165 - 18, par2 + 1, 0, 0);
/*     */     }
/*     */     else {
/*     */       
/*  68 */       GuiStats.drawSprite(this.statsGui, par1 + 165 - 18, par2 + 1, 0, 18);
/*     */     } 
/*     */     
/*  71 */     if (this.field_77262_g == 2) {
/*     */       
/*  73 */       GuiStats.drawSprite(this.statsGui, par1 + 215 - 18, par2 + 1, 0, 0);
/*     */     }
/*     */     else {
/*     */       
/*  77 */       GuiStats.drawSprite(this.statsGui, par1 + 215 - 18, par2 + 1, 0, 18);
/*     */     } 
/*     */     
/*  80 */     if (this.field_77264_j != -1) {
/*     */       
/*  82 */       short var4 = 79;
/*  83 */       byte var5 = 18;
/*     */       
/*  85 */       if (this.field_77264_j == 1) {
/*     */         
/*  87 */         var4 = 129;
/*     */       }
/*  89 */       else if (this.field_77264_j == 2) {
/*     */         
/*  91 */         var4 = 179;
/*     */       } 
/*     */       
/*  94 */       if (this.field_77265_k == 1)
/*     */       {
/*  96 */         var5 = 36;
/*     */       }
/*     */       
/*  99 */       GuiStats.drawSprite(this.statsGui, par1 + var4, par2 + 1, var5, 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_77224_a(int par1, int par2) {
/* 105 */     this.field_77262_g = -1;
/*     */     
/* 107 */     if (par1 >= 79 && par1 < 115) {
/*     */       
/* 109 */       this.field_77262_g = 0;
/*     */     }
/* 111 */     else if (par1 >= 129 && par1 < 165) {
/*     */       
/* 113 */       this.field_77262_g = 1;
/*     */     }
/* 115 */     else if (par1 >= 179 && par1 < 215) {
/*     */       
/* 117 */       this.field_77262_g = 2;
/*     */     } 
/*     */     
/* 120 */     if (this.field_77262_g >= 0) {
/*     */       
/* 122 */       func_77261_e(this.field_77262_g);
/* 123 */       (GuiStats.getMinecraft2(this.statsGui)).sndManager.playSoundFX("random.click", 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final int getSize() {
/* 132 */     return this.field_77266_h.size();
/*     */   }
/*     */ 
/*     */   
/*     */   protected final StatCrafting func_77257_d(int par1) {
/* 137 */     return this.field_77266_h.get(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract String func_77258_c(int paramInt);
/*     */ 
/*     */   
/*     */   protected void func_77260_a(StatCrafting par1StatCrafting, int par2, int par3, boolean par4) {
/* 146 */     if (par1StatCrafting != null) {
/*     */       
/* 148 */       String var5 = par1StatCrafting.func_75968_a(GuiStats.getStatsFileWriter(this.statsGui).writeStat(par1StatCrafting));
/*     */       
/* 150 */       if (GuiStats.this_world_only) {
/* 151 */         var5 = par1StatCrafting.func_75968_a(PlayerStatsHelper.getValueOnClient(par1StatCrafting.statId));
/*     */       }
/* 153 */       this.statsGui.drawString(GuiStats.getFontRenderer4(this.statsGui), var5, par2 - GuiStats.getFontRenderer5(this.statsGui).getStringWidth(var5), par3 + 5, par4 ? 16777215 : 9474192);
/*     */     }
/*     */     else {
/*     */       
/* 157 */       String var5 = "-";
/* 158 */       this.statsGui.drawString(GuiStats.getFontRenderer6(this.statsGui), var5, par2 - GuiStats.getFontRenderer7(this.statsGui).getStringWidth(var5), par3 + 5, par4 ? 16777215 : 9474192);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_77215_b(int par1, int par2) {
/* 164 */     if (par2 >= this.top && par2 <= this.bottom) {
/*     */       
/* 166 */       int var3 = func_77210_c(par1, par2);
/* 167 */       int var4 = this.statsGui.width / 2 - 92 - 16;
/*     */       
/* 169 */       if (var3 >= 0) {
/*     */         
/* 171 */         if (par1 < var4 + 40 || par1 > var4 + 40 + 20) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/* 176 */         StatCrafting var5 = func_77257_d(var3);
/* 177 */         func_77259_a(var5, par1, par2);
/*     */       }
/*     */       else {
/*     */         
/* 181 */         String var9 = "";
/*     */         
/* 183 */         if (par1 >= var4 + 115 - 18 && par1 <= var4 + 115) {
/*     */           
/* 185 */           var9 = func_77258_c(0);
/*     */         }
/* 187 */         else if (par1 >= var4 + 165 - 18 && par1 <= var4 + 165) {
/*     */           
/* 189 */           var9 = func_77258_c(1);
/*     */         }
/*     */         else {
/*     */           
/* 193 */           if (par1 < var4 + 215 - 18 || par1 > var4 + 215) {
/*     */             return;
/*     */           }
/*     */ 
/*     */           
/* 198 */           var9 = func_77258_c(2);
/*     */         } 
/*     */         
/* 201 */         var9 = ("" + I18n.getString(var9)).trim();
/*     */         
/* 203 */         if (var9.length() > 0) {
/*     */           
/* 205 */           int var6 = par1 + 12;
/* 206 */           int var7 = par2 - 12;
/* 207 */           int var8 = GuiStats.getFontRenderer8(this.statsGui).getStringWidth(var9);
/* 208 */           GuiStats.drawGradientRect(this.statsGui, var6 - 3, var7 - 3, var6 + var8 + 3, var7 + 8 + 3, -1073741824, -1073741824);
/* 209 */           GuiStats.getFontRenderer9(this.statsGui).drawStringWithShadow(var9, var6, var7, -1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_77259_a(StatCrafting par1StatCrafting, int par2, int par3) {
/* 217 */     if (par1StatCrafting != null) {
/*     */       
/* 219 */       Item var4 = Item.itemsList[par1StatCrafting.getItemID()];
/* 220 */       String var5 = ("" + I18n.getString(var4.getUnlocalizedName() + ".name")).trim();
/*     */       
/* 222 */       if (var5.length() > 0) {
/*     */         
/* 224 */         int var6 = par2 + 12;
/* 225 */         int var7 = par3 - 12;
/* 226 */         int var8 = GuiStats.getFontRenderer10(this.statsGui).getStringWidth(var5);
/* 227 */         GuiStats.drawGradientRect1(this.statsGui, var6 - 3, var7 - 3, var6 + var8 + 3, var7 + 8 + 3, -1073741824, -1073741824);
/* 228 */         GuiStats.getFontRenderer11(this.statsGui).drawStringWithShadow(var5, var6, var7, -1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_77261_e(int par1) {
/* 235 */     if (par1 != this.field_77264_j) {
/*     */       
/* 237 */       this.field_77264_j = par1;
/* 238 */       this.field_77265_k = -1;
/*     */     }
/* 240 */     else if (this.field_77265_k == -1) {
/*     */       
/* 242 */       this.field_77265_k = 1;
/*     */     }
/*     */     else {
/*     */       
/* 246 */       this.field_77264_j = -1;
/* 247 */       this.field_77265_k = 0;
/*     */     } 
/*     */     
/* 250 */     Collections.sort(this.field_77266_h, this.field_77267_i);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiSlotStats.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */