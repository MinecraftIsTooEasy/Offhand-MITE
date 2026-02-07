/*     */ package net.minecraft;
/*     */ 
/*     */ public enum EnumGameType
/*     */ {
/*   5 */   NOT_SET(-1, ""),
/*   6 */   SURVIVAL(0, "survival"),
/*   7 */   CREATIVE(1, "creative"),
/*   8 */   ADVENTURE(2, "adventure");
/*     */   
/*     */   int id;
/*     */   String name;
/*     */   
/*     */   EnumGameType(int par3, String par4Str) {
/*  14 */     this.id = par3;
/*  15 */     this.name = par4Str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getID() {
/*  26 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  37 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void configurePlayerCapabilities(PlayerCapabilities par1PlayerCapabilities) {
/*  46 */     if (isCreative()) {
/*     */       
/*  48 */       par1PlayerCapabilities.allowFlying = true;
/*  49 */       par1PlayerCapabilities.isCreativeMode = true;
/*  50 */       par1PlayerCapabilities.disableDamage = true;
/*     */     }
/*     */     else {
/*     */       
/*  54 */       par1PlayerCapabilities.allowFlying = false;
/*  55 */       par1PlayerCapabilities.isCreativeMode = false;
/*  56 */       par1PlayerCapabilities.disableDamage = false;
/*  57 */       par1PlayerCapabilities.isFlying = false;
/*     */     } 
/*     */     
/*  60 */     par1PlayerCapabilities.allowEdit = !isAdventure();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdventure() {
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCreative() {
/*  78 */     return (this == CREATIVE && Minecraft.inDevMode());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSurvivalOrAdventure() {
/*  86 */     if (!Minecraft.inDevMode()) {
/*  87 */       return true;
/*     */     }
/*  89 */     return (this == SURVIVAL || this == ADVENTURE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static EnumGameType getByID(int par0) {
/*  97 */     if (!Minecraft.inDevMode()) {
/*  98 */       return SURVIVAL;
/*     */     }
/* 100 */     EnumGameType[] var1 = values();
/* 101 */     int var2 = var1.length;
/*     */     
/* 103 */     for (int var3 = 0; var3 < var2; var3++) {
/*     */       
/* 105 */       EnumGameType var4 = var1[var3];
/*     */       
/* 107 */       if (var4.id == par0)
/*     */       {
/* 109 */         return var4;
/*     */       }
/*     */     } 
/*     */     
/* 113 */     return SURVIVAL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static EnumGameType getByName(String par0Str) {
/* 121 */     if (!Minecraft.inDevMode()) {
/* 122 */       return SURVIVAL;
/*     */     }
/* 124 */     EnumGameType[] var1 = values();
/* 125 */     int var2 = var1.length;
/*     */     
/* 127 */     for (int var3 = 0; var3 < var2; var3++) {
/*     */       
/* 129 */       EnumGameType var4 = var1[var3];
/*     */       
/* 131 */       if (var4.name.equals(par0Str))
/*     */       {
/* 133 */         return var4;
/*     */       }
/*     */     } 
/*     */     
/* 137 */     return SURVIVAL;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumGameType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */