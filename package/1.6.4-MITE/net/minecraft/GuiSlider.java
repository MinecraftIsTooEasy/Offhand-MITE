/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class GuiSlider
/*     */   extends GuiButton
/*     */ {
/*   8 */   public float sliderValue = 1.0F;
/*     */ 
/*     */   
/*     */   public boolean dragging;
/*     */ 
/*     */   
/*     */   private EnumOptions idFloat;
/*     */ 
/*     */   
/*     */   public GuiSlider(int par1, int par2, int par3, EnumOptions par4EnumOptions, String par5Str, float par6) {
/*  18 */     super(par1, par2, par3, 150, 20, par5Str);
/*  19 */     this.idFloat = par4EnumOptions;
/*  20 */     this.sliderValue = par6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getHoverState(boolean par1) {
/*  29 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3) {
/*  37 */     if (!this.enabled) {
/*     */       return;
/*     */     }
/*  40 */     if (this.drawButton) {
/*     */       
/*  42 */       if (this.dragging) {
/*     */         
/*  44 */         this.sliderValue = (par2 - this.xPosition + 4) / (this.width - 8);
/*     */         
/*  46 */         if (this.sliderValue < 0.0F)
/*     */         {
/*  48 */           this.sliderValue = 0.0F;
/*     */         }
/*     */         
/*  51 */         if (this.sliderValue > 1.0F)
/*     */         {
/*  53 */           this.sliderValue = 1.0F;
/*     */         }
/*     */         
/*  56 */         par1Minecraft.gameSettings.setOptionFloatValue(this.idFloat, this.sliderValue);
/*  57 */         this.displayString = par1Minecraft.gameSettings.getKeyBinding(this.idFloat);
/*     */       } 
/*     */       
/*  60 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  61 */       drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (this.width - 8)), this.yPosition, 0, 66, 4, 20);
/*  62 */       drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3) {
/*  72 */     if (super.mousePressed(par1Minecraft, par2, par3)) {
/*     */       
/*  74 */       this.sliderValue = (par2 - this.xPosition + 4) / (this.width - 8);
/*     */       
/*  76 */       if (this.sliderValue < 0.0F)
/*     */       {
/*  78 */         this.sliderValue = 0.0F;
/*     */       }
/*     */       
/*  81 */       if (this.sliderValue > 1.0F)
/*     */       {
/*  83 */         this.sliderValue = 1.0F;
/*     */       }
/*     */       
/*  86 */       par1Minecraft.gameSettings.setOptionFloatValue(this.idFloat, this.sliderValue);
/*  87 */       this.displayString = par1Minecraft.gameSettings.getKeyBinding(this.idFloat);
/*  88 */       this.dragging = true;
/*  89 */       return true;
/*     */     } 
/*     */ 
/*     */     
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(int par1, int par2) {
/* 102 */     this.dragging = false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiSlider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */