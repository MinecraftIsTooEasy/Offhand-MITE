/*     */ package net.minecraft;
/*     */ 
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
/*     */ class GuiBeaconButton
/*     */   extends GuiButton
/*     */ {
/*     */   private final ResourceLocation buttonTexture;
/*     */   private final int field_82257_l;
/*     */   private final int field_82258_m;
/*     */   private boolean field_82256_n;
/*     */   
/*     */   protected GuiBeaconButton(int i, int j, int k, ResourceLocation resourceLocation, int l, int m) {
/* 186 */     super(i, j, k, 22, 22, "");
/* 187 */     this.buttonTexture = resourceLocation;
/* 188 */     this.field_82257_l = l;
/* 189 */     this.field_82258_m = m;
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawButton(Minecraft minecraft, int i, int j) {
/* 194 */     if (!this.drawButton)
/*     */       return; 
/* 196 */     minecraft.getTextureManager().bindTexture(GuiBeacon.getBeaconGuiTextures());
/* 197 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 199 */     this.field_82253_i = (i >= this.xPosition && j >= this.yPosition && i < this.xPosition + this.width && j < this.yPosition + this.height);
/* 200 */     char c = 'Û';
/* 201 */     int k = 0;
/* 202 */     if (!this.enabled) {
/* 203 */       k += this.width * 2;
/* 204 */     } else if (this.field_82256_n) {
/* 205 */       k += this.width * 1;
/* 206 */     } else if (this.field_82253_i) {
/* 207 */       k += this.width * 3;
/*     */     } 
/*     */     
/* 210 */     drawTexturedModalRect(this.xPosition, this.yPosition, k, c, this.width, this.height);
/*     */     
/* 212 */     if (!GuiBeacon.getBeaconGuiTextures().equals(this.buttonTexture)) {
/* 213 */       minecraft.getTextureManager().bindTexture(this.buttonTexture);
/*     */     }
/*     */     
/* 216 */     drawTexturedModalRect(this.xPosition + 2, this.yPosition + 2, this.field_82257_l, this.field_82258_m, 18, 18);
/*     */   }
/*     */   
/*     */   public boolean func_82255_b() {
/* 220 */     return this.field_82256_n;
/*     */   }
/*     */   
/*     */   public void func_82254_b(boolean bl) {
/* 224 */     this.field_82256_n = bl;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiBeaconButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */