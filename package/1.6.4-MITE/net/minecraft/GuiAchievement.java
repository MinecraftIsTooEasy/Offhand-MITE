/*     */ package net.minecraft;
/*     */ 
/*     */ import net.minecraft.client.main.Main;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class GuiAchievement
/*     */   extends Gui
/*     */ {
/*  10 */   private static final ResourceLocation achievementTextures = new ResourceLocation("textures/gui/achievement/achievement_background.png");
/*     */ 
/*     */   
/*     */   private Minecraft theGame;
/*     */ 
/*     */   
/*     */   private int achievementWindowWidth;
/*     */ 
/*     */   
/*     */   private int achievementWindowHeight;
/*     */   
/*     */   private String achievementGetLocalText;
/*     */   
/*     */   private String achievementStatName;
/*     */   
/*     */   private Achievement theAchievement;
/*     */   
/*     */   private long achievementTime;
/*     */   
/*     */   private RenderItem itemRender;
/*     */   
/*     */   private boolean haveAchiement;
/*     */ 
/*     */   
/*     */   public GuiAchievement(Minecraft par1Minecraft) {
/*  35 */     this.theGame = par1Minecraft;
/*  36 */     this.itemRender = new RenderItem();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void queueTakenAchievement(Achievement par1Achievement) {
/*  44 */     this.achievementGetLocalText = I18n.getString("achievement.get");
/*  45 */     this.achievementStatName = I18n.getString(par1Achievement.getName());
/*  46 */     this.achievementTime = Minecraft.getSystemTime();
/*  47 */     this.theAchievement = par1Achievement;
/*  48 */     this.haveAchiement = false;
/*     */     
/*  50 */     this.theGame.thePlayer.open_inventory_suppressed_countdown = 60;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void queueAchievementInformation(Achievement par1Achievement) {
/*  58 */     this.achievementGetLocalText = I18n.getString(par1Achievement.getName());
/*  59 */     this.achievementStatName = par1Achievement.getDescription();
/*  60 */     this.achievementTime = Minecraft.getSystemTime() - 2500L;
/*  61 */     this.theAchievement = par1Achievement;
/*  62 */     this.haveAchiement = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateAchievementWindowScale() {
/*  70 */     GL11.glViewport(0, 0, this.theGame.displayWidth, this.theGame.displayHeight);
/*  71 */     GL11.glMatrixMode(5889);
/*  72 */     GL11.glLoadIdentity();
/*  73 */     GL11.glMatrixMode(5888);
/*  74 */     GL11.glLoadIdentity();
/*  75 */     this.achievementWindowWidth = this.theGame.displayWidth;
/*  76 */     this.achievementWindowHeight = this.theGame.displayHeight;
/*  77 */     ScaledResolution var1 = new ScaledResolution(this.theGame.gameSettings, this.theGame.displayWidth, this.theGame.displayHeight);
/*  78 */     this.achievementWindowWidth = var1.getScaledWidth();
/*  79 */     this.achievementWindowHeight = var1.getScaledHeight();
/*  80 */     GL11.glClear(256);
/*  81 */     GL11.glMatrixMode(5889);
/*  82 */     GL11.glLoadIdentity();
/*  83 */     GL11.glOrtho(0.0D, this.achievementWindowWidth, this.achievementWindowHeight, 0.0D, 1000.0D, 3000.0D);
/*  84 */     GL11.glMatrixMode(5888);
/*  85 */     GL11.glLoadIdentity();
/*  86 */     GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateAchievementWindow() {
/*  94 */     if (Minecraft.theMinecraft.gameSettings.gui_mode != 0 || Main.is_MITE_DS) {
/*     */       return;
/*     */     }
/*  97 */     if (this.theAchievement != null && this.achievementTime != 0L) {
/*     */       
/*  99 */       double var1 = (Minecraft.getSystemTime() - this.achievementTime) / 3000.0D;
/*     */       
/* 101 */       if (!this.haveAchiement && (var1 < 0.0D || var1 > 1.0D)) {
/*     */         
/* 103 */         this.achievementTime = 0L;
/*     */       }
/*     */       else {
/*     */         
/* 107 */         updateAchievementWindowScale();
/* 108 */         GL11.glDisable(2929);
/* 109 */         GL11.glDepthMask(false);
/* 110 */         double var3 = var1 * 2.0D;
/*     */         
/* 112 */         if (var3 > 1.0D)
/*     */         {
/* 114 */           var3 = 2.0D - var3;
/*     */         }
/*     */         
/* 117 */         var3 *= 4.0D;
/* 118 */         var3 = 1.0D - var3;
/*     */         
/* 120 */         if (var3 < 0.0D)
/*     */         {
/* 122 */           var3 = 0.0D;
/*     */         }
/*     */         
/* 125 */         var3 *= var3;
/* 126 */         var3 *= var3;
/* 127 */         int var5 = this.achievementWindowWidth - 160;
/* 128 */         int var6 = 0 - (int)(var3 * 36.0D);
/* 129 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 130 */         GL11.glEnable(3553);
/* 131 */         this.theGame.getTextureManager().bindTexture(achievementTextures);
/* 132 */         GL11.glDisable(2896);
/* 133 */         drawTexturedModalRect(var5, var6, 96, 202, 160, 32);
/*     */         
/* 135 */         if (this.haveAchiement) {
/*     */           
/* 137 */           this.theGame.fontRenderer.drawSplitString(this.achievementStatName, var5 + 30, var6 + 7, 120, -1);
/*     */         }
/*     */         else {
/*     */           
/* 141 */           this.theGame.fontRenderer.drawString(this.achievementGetLocalText, var5 + 30, var6 + 7, -256);
/* 142 */           this.theGame.fontRenderer.drawString(this.achievementStatName, var5 + 30, var6 + 18, -1);
/*     */         } 
/*     */         
/* 145 */         RenderHelper.enableGUIStandardItemLighting();
/* 146 */         GL11.glDisable(2896);
/* 147 */         GL11.glEnable(32826);
/* 148 */         GL11.glEnable(2903);
/* 149 */         GL11.glEnable(2896);
/* 150 */         this.itemRender.renderItemAndEffectIntoGUI(this.theGame.fontRenderer, this.theGame.getTextureManager(), this.theAchievement.theItemStack, var5 + 8, var6 + 8);
/* 151 */         GL11.glDisable(2896);
/* 152 */         GL11.glDepthMask(true);
/* 153 */         GL11.glEnable(2929);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiAchievement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */