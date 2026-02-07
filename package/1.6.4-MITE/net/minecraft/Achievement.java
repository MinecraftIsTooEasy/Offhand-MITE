/*     */ package net.minecraft;
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
/*     */ public class Achievement
/*     */   extends StatBase
/*     */ {
/*     */   public final int displayColumn;
/*     */   public final int displayRow;
/*     */   public final Achievement parentAchievement;
/*     */   private final String achievementDescription;
/*     */   private IStatStringFormat statStringFormatter;
/*     */   public final ItemStack theItemStack;
/*     */   private boolean isSpecial;
/*     */   private boolean is_flipped;
/*     */   private Achievement second_parent;
/*  46 */   private int tooltip_width = 120;
/*     */ 
/*     */   
/*     */   public Achievement(int par1, String par2Str, int par3, int par4, Item par5Item, Achievement par6Achievement) {
/*  50 */     this(par1, par2Str, par3, par4, new ItemStack(par5Item), par6Achievement);
/*     */   }
/*     */ 
/*     */   
/*     */   public Achievement(int par1, String par2Str, int par3, int par4, Block par5Block, Achievement par6Achievement) {
/*  55 */     this(par1, par2Str, par3, par4, new ItemStack(par5Block), par6Achievement);
/*     */   }
/*     */ 
/*     */   
/*     */   public Achievement(int par1, String par2Str, int par3, int par4, ItemStack par5ItemStack, Achievement par6Achievement) {
/*  60 */     super(5242880 + par1, "achievement." + par2Str);
/*  61 */     this.theItemStack = par5ItemStack;
/*  62 */     this.achievementDescription = "achievement." + par2Str + ".desc";
/*  63 */     this.displayColumn = par3;
/*  64 */     this.displayRow = par4;
/*     */     
/*  66 */     if (par3 < AchievementList.minDisplayColumn)
/*     */     {
/*  68 */       AchievementList.minDisplayColumn = par3;
/*     */     }
/*     */     
/*  71 */     if (par4 < AchievementList.minDisplayRow)
/*     */     {
/*  73 */       AchievementList.minDisplayRow = par4;
/*     */     }
/*     */     
/*  76 */     if (par3 > AchievementList.maxDisplayColumn)
/*     */     {
/*  78 */       AchievementList.maxDisplayColumn = par3;
/*     */     }
/*     */     
/*  81 */     if (par4 > AchievementList.maxDisplayRow)
/*     */     {
/*  83 */       AchievementList.maxDisplayRow = par4;
/*     */     }
/*     */     
/*  86 */     this.parentAchievement = par6Achievement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Achievement setIndependent() {
/*  95 */     this.isIndependent = true;
/*  96 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Achievement setSpecial() {
/* 105 */     this.isSpecial = true;
/* 106 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Achievement registerAchievement() {
/* 114 */     super.registerStat();
/* 115 */     AchievementList.achievementList.add(this);
/* 116 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAchievement() {
/* 124 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 132 */     return (this.statStringFormatter != null) ? this.statStringFormatter.formatString(StatCollector.translateToLocal(this.achievementDescription)) : StatCollector.translateToLocal(this.achievementDescription);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Achievement setStatStringFormatter(IStatStringFormat par1IStatStringFormat) {
/* 140 */     this.statStringFormatter = par1IStatStringFormat;
/* 141 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getSpecial() {
/* 150 */     return this.isSpecial;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StatBase registerStat() {
/* 158 */     return registerAchievement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StatBase initIndependentStat() {
/* 167 */     return setIndependent();
/*     */   }
/*     */ 
/*     */   
/*     */   public Achievement setFlipped() {
/* 172 */     this.is_flipped = true;
/* 173 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFlipped() {
/* 178 */     return this.is_flipped;
/*     */   }
/*     */ 
/*     */   
/*     */   public Achievement setSecondParent(Achievement second_parent) {
/* 183 */     this.second_parent = second_parent;
/* 184 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Achievement getSecondParent() {
/* 189 */     return this.second_parent;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasSecondParent() {
/* 194 */     return (this.second_parent != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Achievement setTooltipWidth(int tooltip_width) {
/* 200 */     this.tooltip_width = tooltip_width;
/* 201 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTooltipWidth() {
/* 206 */     return this.tooltip_width;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Achievement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */