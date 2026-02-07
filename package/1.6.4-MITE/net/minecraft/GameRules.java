/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class GameRules
/*     */ {
/*   9 */   private TreeMap theGameRules = new TreeMap<Object, Object>();
/*     */ 
/*     */   
/*     */   public GameRules() {
/*  13 */     addGameRule("doFireTick", "true");
/*  14 */     addGameRule("mobGriefing", "true");
/*  15 */     addGameRule("keepInventory", "false");
/*  16 */     addGameRule("doMobSpawning", "true");
/*  17 */     addGameRule("doMobLoot", "true");
/*  18 */     addGameRule("doTileDrops", "true");
/*  19 */     addGameRule("commandBlockOutput", "true");
/*  20 */     addGameRule("naturalRegeneration", "true");
/*  21 */     addGameRule("doDaylightCycle", "true");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addGameRule(String par1Str, String par2Str) {
/*  29 */     this.theGameRules.put(par1Str, new GameRuleValue(par2Str));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOrCreateGameRule(String par1Str, String par2Str) {
/*  34 */     GameRuleValue var3 = (GameRuleValue)this.theGameRules.get(par1Str);
/*     */     
/*  36 */     if (var3 != null) {
/*     */       
/*  38 */       var3.setValue(par2Str);
/*     */     }
/*     */     else {
/*     */       
/*  42 */       addGameRule(par1Str, par2Str);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getGameRuleStringValue(String par1Str) {
/*  51 */     GameRuleValue var2 = (GameRuleValue)this.theGameRules.get(par1Str);
/*  52 */     return (var2 != null) ? var2.getGameRuleStringValue() : "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getGameRuleBooleanValue(String par1Str) {
/*  63 */     return getGameRuleOverrideBooleanValue(par1Str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeGameRulesToNBT() {
/*  71 */     NBTTagCompound var1 = new NBTTagCompound("GameRules");
/*  72 */     Iterator<String> var2 = this.theGameRules.keySet().iterator();
/*     */     
/*  74 */     while (var2.hasNext()) {
/*     */       
/*  76 */       String var3 = var2.next();
/*  77 */       GameRuleValue var4 = (GameRuleValue)this.theGameRules.get(var3);
/*  78 */       var1.setString(var3, var4.getGameRuleStringValue());
/*     */     } 
/*     */     
/*  81 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readGameRulesFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  89 */     Collection var2 = par1NBTTagCompound.getTags();
/*  90 */     Iterator<NBTBase> var3 = var2.iterator();
/*     */     
/*  92 */     while (var3.hasNext()) {
/*     */       
/*  94 */       NBTBase var4 = var3.next();
/*  95 */       String var5 = var4.getName();
/*  96 */       String var6 = par1NBTTagCompound.getString(var4.getName());
/*  97 */       setOrCreateGameRule(var5, var6);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getRules() {
/* 106 */     return (String[])this.theGameRules.keySet().toArray((Object[])new String[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasRule(String par1Str) {
/* 114 */     return this.theGameRules.containsKey(par1Str);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean getGameRuleOverrideBooleanValue(String rule_name) {
/* 119 */     if (rule_name.equals("doFireTick")) {
/* 120 */       return true;
/*     */     }
/* 122 */     if (rule_name.equals("mobGriefing")) {
/* 123 */       return true;
/*     */     }
/* 125 */     if (rule_name.equals("keepInventory")) {
/* 126 */       return false;
/*     */     }
/* 128 */     if (rule_name.equals("doMobSpawning")) {
/* 129 */       return true;
/*     */     }
/* 131 */     if (rule_name.equals("doMobLoot")) {
/* 132 */       return true;
/*     */     }
/* 134 */     if (rule_name.equals("doTileDrops")) {
/* 135 */       return true;
/*     */     }
/* 137 */     if (rule_name.equals("commandBlockOutput")) {
/* 138 */       return true;
/*     */     }
/* 140 */     if (rule_name.equals("naturalRegeneration")) {
/* 141 */       return true;
/*     */     }
/* 143 */     if (rule_name.equals("doDaylightCycle")) {
/* 144 */       return true;
/*     */     }
/* 146 */     Minecraft.setErrorMessage("getGameRuleOverride: unhandled rule " + rule_name);
/* 147 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GameRules.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */