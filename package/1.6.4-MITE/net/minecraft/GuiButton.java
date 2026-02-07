/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class GuiButton
/*     */   extends Gui {
/*   7 */   protected static final ResourceLocation buttonTextures = new ResourceLocation("textures/gui/widgets.png");
/*     */ 
/*     */   
/*     */   protected int width;
/*     */ 
/*     */   
/*     */   protected int height;
/*     */ 
/*     */   
/*     */   public int xPosition;
/*     */ 
/*     */   
/*     */   public int yPosition;
/*     */ 
/*     */   
/*     */   public String displayString;
/*     */ 
/*     */   
/*     */   public int id;
/*     */ 
/*     */   
/*     */   public boolean enabled;
/*     */   
/*     */   public boolean drawButton;
/*     */   
/*     */   protected boolean field_82253_i;
/*     */   
/*  34 */   private String clicked_sound = "random.click";
/*  35 */   private float clicked_sound_volume = 1.0F;
/*  36 */   private float clicked_sound_pitch = 1.0F;
/*     */   
/*     */   public boolean is_disconnect_button;
/*     */ 
/*     */   
/*     */   public GuiButton(int par1, int par2, int par3, String par4Str) {
/*  42 */     this(par1, par2, par3, 200, 20, par4Str);
/*     */   }
/*     */ 
/*     */   
/*     */   public GuiButton(int par1, int par2, int par3, int par4, int par5, String par6Str) {
/*  47 */     this.width = 200;
/*  48 */     this.height = 20;
/*  49 */     this.enabled = true;
/*  50 */     this.drawButton = true;
/*  51 */     this.id = par1;
/*  52 */     this.xPosition = par2;
/*  53 */     this.yPosition = par3;
/*  54 */     this.width = par4;
/*  55 */     this.height = par5;
/*  56 */     this.displayString = par6Str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getHoverState(boolean par1) {
/*  65 */     byte var2 = 1;
/*     */     
/*  67 */     if (!this.enabled) {
/*     */       
/*  69 */       var2 = 0;
/*     */     }
/*  71 */     else if (par1) {
/*     */       
/*  73 */       var2 = 2;
/*     */     } 
/*     */     
/*  76 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawButton(Minecraft par1Minecraft, int par2, int par3) {
/*  84 */     if (this.drawButton) {
/*     */       
/*  86 */       FontRenderer var4 = par1Minecraft.fontRenderer;
/*  87 */       par1Minecraft.getTextureManager().bindTexture(buttonTextures);
/*  88 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  89 */       this.field_82253_i = (par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height);
/*  90 */       int var5 = getHoverState(this.field_82253_i);
/*     */ 
/*     */ 
/*     */       
/*  94 */       drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + var5 * 20, this.width / 2, this.height);
/*  95 */       drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width - this.width / 2, 46 + var5 * 20, this.width - this.width / 2, this.height);
/*     */       
/*  97 */       mouseDragged(par1Minecraft, par2, par3);
/*  98 */       int var6 = 14737632;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 103 */       if (!this.enabled) {
/*     */         
/* 105 */         var6 = -6250336;
/*     */       }
/* 107 */       else if (this.field_82253_i) {
/*     */         
/* 109 */         var6 = 16777120;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 115 */       drawCenteredString(var4, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, var6);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(int par1, int par2) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3) {
/* 135 */     return (this.enabled && this.drawButton && par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_82252_a() {
/* 140 */     return this.field_82253_i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82251_b(int par1, int par2) {}
/*     */   
/*     */   public boolean isClickable() {
/* 147 */     return (this.drawButton && this.enabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public GuiButton setClickedSound(String clicked_sound, float volume, float pitch) {
/* 152 */     this.clicked_sound = clicked_sound;
/* 153 */     this.clicked_sound_volume = volume;
/* 154 */     this.clicked_sound_pitch = pitch;
/*     */     
/* 156 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void playClickedSound(SoundManager sound_manager) {
/* 161 */     if (this.clicked_sound != null)
/* 162 */       sound_manager.playSoundFX(this.clicked_sound, this.clicked_sound_volume, this.clicked_sound_pitch); 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */