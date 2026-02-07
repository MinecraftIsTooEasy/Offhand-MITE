/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.apache.commons.io.Charsets;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class GuiWinGame
/*     */   extends GuiScreen {
/*  13 */   private static final ResourceLocation minecraftLogoTexture = new ResourceLocation("textures/gui/title/minecraft.png");
/*  14 */   private static final ResourceLocation field_110361_b = new ResourceLocation("textures/misc/vignette.png");
/*     */   
/*     */   private int updateCounter;
/*     */   
/*     */   private List lines;
/*     */   
/*     */   private int field_73989_c;
/*     */   
/*  22 */   private float field_73987_d = 0.5F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  29 */     this.updateCounter++;
/*  30 */     float var1 = (this.field_73989_c + this.height + this.height + 24) / this.field_73987_d;
/*     */     
/*  32 */     if (this.updateCounter > var1)
/*     */     {
/*  34 */       respawnPlayer();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char par1, int par2) {
/*  43 */     if (par2 == 1)
/*     */     {
/*  45 */       respawnPlayer();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void respawnPlayer() {
/*  54 */     SoundManager.muted = false;
/*     */     
/*  56 */     this.mc.thePlayer.sendQueue.addToSendQueue(new Packet205ClientCommand(1));
/*  57 */     this.mc.displayGuiScreen((GuiScreen)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesGuiPauseGame() {
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  73 */     if (this.lines == null) {
/*     */       
/*  75 */       this.lines = new ArrayList();
/*     */ 
/*     */       
/*     */       try {
/*  79 */         String var1 = "";
/*  80 */         String var2 = "" + EnumChatFormatting.WHITE + EnumChatFormatting.OBFUSCATED + EnumChatFormatting.GREEN + EnumChatFormatting.AQUA;
/*  81 */         short var3 = 274;
/*     */         
/*  83 */         BufferedReader var4 = new BufferedReader(new InputStreamReader(this.mc.getResourceManager().getResource(new ResourceLocation("texts/profound.txt")).getInputStream(), Charsets.UTF_8));
/*  84 */         Random var5 = new Random(8124371L);
/*     */ 
/*     */         
/*  87 */         while ((var1 = var4.readLine()) != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  92 */           for (var1 = var1.replaceAll("PLAYERNAME", this.mc.getSession().getUsername()); var1.contains(var2); var1 = var7 + EnumChatFormatting.WHITE + EnumChatFormatting.OBFUSCATED + "XXXXXXXX".substring(0, var5.nextInt(4) + 3) + var8) {
/*     */             
/*  94 */             int i = var1.indexOf(var2);
/*  95 */             String var7 = var1.substring(0, i);
/*  96 */             String var8 = var1.substring(i + var2.length());
/*     */           } 
/*     */           
/*  99 */           this.lines.addAll(this.mc.fontRenderer.listFormattedStringToWidth(var1, var3));
/* 100 */           this.lines.add("");
/*     */         } 
/*     */         int var6;
/* 103 */         for (var6 = 0; var6 < 8; var6++)
/*     */         {
/* 105 */           this.lines.add("");
/*     */         }
/*     */         
/* 108 */         var4 = new BufferedReader(new InputStreamReader(this.mc.getResourceManager().getResource(new ResourceLocation("texts/end.txt")).getInputStream(), Charsets.UTF_8));
/*     */         
/* 110 */         while ((var1 = var4.readLine()) != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 115 */           for (var1 = var1.replaceAll("PLAYERNAME", this.mc.getSession().getUsername()); var1.contains(var2); var1 = var7 + EnumChatFormatting.WHITE + EnumChatFormatting.OBFUSCATED + "XXXXXXXX".substring(0, var5.nextInt(4) + 3) + var8) {
/*     */             
/* 117 */             var6 = var1.indexOf(var2);
/* 118 */             String var7 = var1.substring(0, var6);
/* 119 */             String var8 = var1.substring(var6 + var2.length());
/*     */           } 
/*     */           
/* 122 */           this.lines.addAll(this.mc.fontRenderer.listFormattedStringToWidth(var1, var3));
/* 123 */           this.lines.add("");
/*     */         } 
/*     */         
/* 126 */         for (var6 = 0; var6 < 8; var6++)
/*     */         {
/* 128 */           this.lines.add("");
/*     */         }
/*     */         
/* 131 */         var4 = new BufferedReader(new InputStreamReader(this.mc.getResourceManager().getResource(new ResourceLocation("texts/credits.txt")).getInputStream(), Charsets.UTF_8));
/*     */         
/* 133 */         while ((var1 = var4.readLine()) != null) {
/*     */           
/* 135 */           var1 = var1.replaceAll("PLAYERNAME", this.mc.getSession().getUsername());
/* 136 */           var1 = var1.replaceAll("\t", "    ");
/* 137 */           this.lines.addAll(this.mc.fontRenderer.listFormattedStringToWidth(var1, var3));
/* 138 */           this.lines.add("");
/*     */         } 
/*     */         
/* 141 */         this.field_73989_c = this.lines.size() * 12;
/*     */       }
/* 143 */       catch (Exception var9) {
/*     */         
/* 145 */         var9.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_73986_b(int par1, int par2, float par3) {
/* 152 */     Tessellator var4 = Tessellator.instance;
/* 153 */     this.mc.getTextureManager().bindTexture(Gui.optionsBackground);
/* 154 */     var4.startDrawingQuads();
/* 155 */     var4.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
/* 156 */     int var5 = this.width;
/* 157 */     float var6 = 0.0F - (this.updateCounter + par3) * 0.5F * this.field_73987_d;
/* 158 */     float var7 = this.height - (this.updateCounter + par3) * 0.5F * this.field_73987_d;
/* 159 */     float var8 = 0.015625F;
/* 160 */     float var9 = (this.updateCounter + par3 - 0.0F) * 0.02F;
/* 161 */     float var10 = (this.field_73989_c + this.height + this.height + 24) / this.field_73987_d;
/* 162 */     float var11 = (var10 - 20.0F - this.updateCounter + par3) * 0.005F;
/*     */     
/* 164 */     if (var11 < var9)
/*     */     {
/* 166 */       var9 = var11;
/*     */     }
/*     */     
/* 169 */     if (var9 > 1.0F)
/*     */     {
/* 171 */       var9 = 1.0F;
/*     */     }
/*     */     
/* 174 */     var9 *= var9;
/* 175 */     var9 = var9 * 96.0F / 255.0F;
/* 176 */     var4.setColorOpaque_F(var9, var9, var9);
/* 177 */     var4.addVertexWithUV(0.0D, this.height, this.zLevel, 0.0D, (var6 * var8));
/* 178 */     var4.addVertexWithUV(var5, this.height, this.zLevel, (var5 * var8), (var6 * var8));
/* 179 */     var4.addVertexWithUV(var5, 0.0D, this.zLevel, (var5 * var8), (var7 * var8));
/* 180 */     var4.addVertexWithUV(0.0D, 0.0D, this.zLevel, 0.0D, (var7 * var8));
/* 181 */     var4.draw();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/* 189 */     SoundManager.muted = true;
/*     */     
/* 191 */     func_73986_b(par1, par2, par3);
/* 192 */     Tessellator var4 = Tessellator.instance;
/* 193 */     short var5 = 274;
/* 194 */     int var6 = this.width / 2 - var5 / 2;
/* 195 */     int var7 = this.height + 50;
/* 196 */     float var8 = -(this.updateCounter + par3) * this.field_73987_d;
/* 197 */     GL11.glPushMatrix();
/* 198 */     GL11.glTranslatef(0.0F, var8, 0.0F);
/* 199 */     this.mc.getTextureManager().bindTexture(minecraftLogoTexture);
/* 200 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 201 */     drawTexturedModalRect(var6, var7, 0, 0, 155, 44);
/* 202 */     drawTexturedModalRect(var6 + 155, var7, 0, 45, 155, 44);
/* 203 */     var4.setColorOpaque_I(16777215);
/* 204 */     int var9 = var7 + 200;
/*     */     
/*     */     int var10;
/* 207 */     for (var10 = 0; var10 < this.lines.size(); var10++) {
/*     */       
/* 209 */       if (var10 == this.lines.size() - 1) {
/*     */         
/* 211 */         float var11 = var9 + var8 - (this.height / 2 - 6);
/*     */         
/* 213 */         if (var11 < 0.0F)
/*     */         {
/* 215 */           GL11.glTranslatef(0.0F, -var11, 0.0F);
/*     */         }
/*     */       } 
/*     */       
/* 219 */       if (var9 + var8 + 12.0F + 8.0F > 0.0F && var9 + var8 < this.height) {
/*     */         
/* 221 */         String var12 = this.lines.get(var10);
/*     */         
/* 223 */         if (var12.startsWith("[C]")) {
/*     */           
/* 225 */           this.fontRenderer.drawStringWithShadow(var12.substring(3), var6 + (var5 - this.fontRenderer.getStringWidth(var12.substring(3))) / 2, var9, 16777215);
/*     */         }
/*     */         else {
/*     */           
/* 229 */           this.fontRenderer.fontRandom.setSeed(var10 * 4238972211L + (this.updateCounter / 4));
/* 230 */           this.fontRenderer.drawStringWithShadow(var12, var6, var9, 16777215);
/*     */         } 
/*     */       } 
/*     */       
/* 234 */       var9 += 12;
/*     */     } 
/*     */     
/* 237 */     GL11.glPopMatrix();
/* 238 */     this.mc.getTextureManager().bindTexture(field_110361_b);
/* 239 */     GL11.glEnable(3042);
/* 240 */     GL11.glBlendFunc(0, 769);
/* 241 */     var4.startDrawingQuads();
/* 242 */     var4.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
/* 243 */     var10 = this.width;
/* 244 */     int var13 = this.height;
/* 245 */     var4.addVertexWithUV(0.0D, var13, this.zLevel, 0.0D, 1.0D);
/* 246 */     var4.addVertexWithUV(var10, var13, this.zLevel, 1.0D, 1.0D);
/* 247 */     var4.addVertexWithUV(var10, 0.0D, this.zLevel, 1.0D, 0.0D);
/* 248 */     var4.addVertexWithUV(0.0D, 0.0D, this.zLevel, 0.0D, 0.0D);
/* 249 */     var4.draw();
/* 250 */     GL11.glDisable(3042);
/* 251 */     super.drawScreen(par1, par2, par3);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiWinGame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */