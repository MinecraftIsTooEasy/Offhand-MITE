/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TournamentStanding
/*     */ {
/*     */   public boolean caught_a_fish;
/*     */   public boolean killed_a_skeleton;
/*     */   public boolean killed_a_zombie;
/*     */   public boolean killed_a_spider;
/*     */   public boolean killed_a_wood_spider;
/*     */   public boolean killed_a_creeper;
/*     */   public boolean killed_a_large_slime;
/*     */   public boolean killed_a_ghoul;
/*     */   public boolean killed_a_wight;
/*     */   public boolean killed_an_invisible_stalker;
/*     */   public boolean killed_a_witch;
/*     */   public boolean killed_a_shadow;
/*     */   public boolean killed_a_hellhound;
/*     */   public boolean killed_a_demon_spider;
/*     */   public int copper_nuggets_harvested;
/*     */   public int silver_nuggets_harvested;
/*     */   public int gold_nuggets_harvested;
/*     */   public int mithril_nuggets_harvested;
/*     */   public int adamantium_nuggets_harvested;
/*     */   public int copper_ore_harvested;
/*     */   public int silver_ore_harvested;
/*     */   public int gold_ore_harvested;
/*     */   public int iron_ore_harvested;
/*     */   public int mithril_ore_harvested;
/*     */   public int adamantium_ore_harvested;
/*     */   public int experience;
/*     */   
/*     */   public TournamentStanding readFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  40 */     this.caught_a_fish = par1NBTTagCompound.getBoolean("caught_a_fish");
/*     */     
/*  42 */     this.killed_a_skeleton = par1NBTTagCompound.getBoolean("killed_a_skeleton");
/*  43 */     this.killed_a_zombie = par1NBTTagCompound.getBoolean("killed_a_zombie");
/*  44 */     this.killed_a_spider = par1NBTTagCompound.getBoolean("killed_a_spider");
/*  45 */     this.killed_a_wood_spider = par1NBTTagCompound.getBoolean("killed_a_wood_spider");
/*  46 */     this.killed_a_creeper = par1NBTTagCompound.getBoolean("killed_a_creeper");
/*  47 */     this.killed_a_large_slime = par1NBTTagCompound.getBoolean("killed_a_large_slime");
/*  48 */     this.killed_a_ghoul = par1NBTTagCompound.getBoolean("killed_a_ghoul");
/*  49 */     this.killed_a_wight = par1NBTTagCompound.getBoolean("killed_a_wight");
/*  50 */     this.killed_an_invisible_stalker = par1NBTTagCompound.getBoolean("killed_an_invisible_stalker");
/*  51 */     this.killed_a_witch = par1NBTTagCompound.getBoolean("killed_a_witch");
/*  52 */     this.killed_a_shadow = par1NBTTagCompound.getBoolean("killed_a_shadow");
/*  53 */     this.killed_a_hellhound = par1NBTTagCompound.getBoolean("killed_a_hellhound");
/*  54 */     this.killed_a_demon_spider = par1NBTTagCompound.getBoolean("killed_a_demon_spider");
/*     */     
/*  56 */     this.copper_nuggets_harvested = par1NBTTagCompound.getInteger("copper_nuggets_harvested");
/*  57 */     this.silver_nuggets_harvested = par1NBTTagCompound.getInteger("silver_nuggets_harvested");
/*  58 */     this.gold_nuggets_harvested = par1NBTTagCompound.getInteger("gold_nuggets_harvested");
/*  59 */     this.mithril_nuggets_harvested = par1NBTTagCompound.getInteger("mithril_nuggets_harvested");
/*  60 */     this.adamantium_nuggets_harvested = par1NBTTagCompound.getInteger("adamantium_nuggets_harvested");
/*     */     
/*  62 */     this.copper_ore_harvested = par1NBTTagCompound.getInteger("copper_ore_smelted");
/*  63 */     this.silver_ore_harvested = par1NBTTagCompound.getInteger("silver_ore_smelted");
/*  64 */     this.gold_ore_harvested = par1NBTTagCompound.getInteger("gold_ore_smelted");
/*  65 */     this.iron_ore_harvested = par1NBTTagCompound.getInteger("iron_ore_smelted");
/*  66 */     this.mithril_ore_harvested = par1NBTTagCompound.getInteger("mithril_ore_harvested");
/*  67 */     this.adamantium_ore_harvested = par1NBTTagCompound.getInteger("adamantium_ore_harvested");
/*     */     
/*  69 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
/*  74 */     par1NBTTagCompound.setBoolean("caught_a_fish", this.caught_a_fish);
/*     */     
/*  76 */     par1NBTTagCompound.setBoolean("killed_a_skeleton", this.killed_a_skeleton);
/*  77 */     par1NBTTagCompound.setBoolean("killed_a_zombie", this.killed_a_zombie);
/*  78 */     par1NBTTagCompound.setBoolean("killed_a_spider", this.killed_a_spider);
/*  79 */     par1NBTTagCompound.setBoolean("killed_a_wood_spider", this.killed_a_wood_spider);
/*  80 */     par1NBTTagCompound.setBoolean("killed_a_creeper", this.killed_a_creeper);
/*  81 */     par1NBTTagCompound.setBoolean("killed_a_large_slime", this.killed_a_large_slime);
/*  82 */     par1NBTTagCompound.setBoolean("killed_a_ghoul", this.killed_a_ghoul);
/*  83 */     par1NBTTagCompound.setBoolean("killed_a_wight", this.killed_a_wight);
/*  84 */     par1NBTTagCompound.setBoolean("killed_an_invisible_stalker", this.killed_an_invisible_stalker);
/*  85 */     par1NBTTagCompound.setBoolean("killed_a_witch", this.killed_a_witch);
/*  86 */     par1NBTTagCompound.setBoolean("killed_a_shadow", this.killed_a_shadow);
/*  87 */     par1NBTTagCompound.setBoolean("killed_a_hellhound", this.killed_a_hellhound);
/*  88 */     par1NBTTagCompound.setBoolean("killed_a_demon_spider", this.killed_a_demon_spider);
/*     */     
/*  90 */     par1NBTTagCompound.setInteger("copper_nuggets_harvested", this.copper_nuggets_harvested);
/*  91 */     par1NBTTagCompound.setInteger("silver_nuggets_harvested", this.silver_nuggets_harvested);
/*  92 */     par1NBTTagCompound.setInteger("gold_nuggets_harvested", this.gold_nuggets_harvested);
/*  93 */     par1NBTTagCompound.setInteger("mithril_nuggets_harvested", this.mithril_nuggets_harvested);
/*  94 */     par1NBTTagCompound.setInteger("adamantium_nuggets_harvested", this.adamantium_nuggets_harvested);
/*     */     
/*  96 */     par1NBTTagCompound.setInteger("copper_ore_smelted", this.copper_ore_harvested);
/*  97 */     par1NBTTagCompound.setInteger("silver_ore_smelted", this.silver_ore_harvested);
/*  98 */     par1NBTTagCompound.setInteger("gold_ore_smelted", this.gold_ore_harvested);
/*  99 */     par1NBTTagCompound.setInteger("iron_ore_smelted", this.iron_ore_harvested);
/* 100 */     par1NBTTagCompound.setInteger("mithril_ore_harvested", this.mithril_ore_harvested);
/* 101 */     par1NBTTagCompound.setInteger("adamantium_ore_harvested", this.adamantium_ore_harvested);
/*     */   }
/*     */ 
/*     */   
/*     */   public int calcScore() {
/* 106 */     int score = 0;
/*     */     
/* 108 */     score += this.caught_a_fish ? 300 : 0;
/*     */     
/* 110 */     score += this.killed_a_skeleton ? 100 : 0;
/* 111 */     score += this.killed_a_zombie ? 200 : 0;
/* 112 */     score += this.killed_a_spider ? 200 : 0;
/* 113 */     score += this.killed_a_wood_spider ? 200 : 0;
/* 114 */     score += this.killed_a_creeper ? 200 : 0;
/* 115 */     score += this.killed_a_large_slime ? 300 : 0;
/* 116 */     score += this.killed_a_ghoul ? 300 : 0;
/* 117 */     score += this.killed_a_wight ? 400 : 0;
/* 118 */     score += this.killed_an_invisible_stalker ? 300 : 0;
/* 119 */     score += this.killed_a_witch ? 500 : 0;
/* 120 */     score += this.killed_a_shadow ? 500 : 0;
/* 121 */     score += this.killed_a_hellhound ? 700 : 0;
/* 122 */     score += this.killed_a_demon_spider ? 1000 : 0;
/*     */     
/* 124 */     score += this.copper_nuggets_harvested * 100;
/* 125 */     score += this.silver_nuggets_harvested * 200;
/* 126 */     score += this.gold_nuggets_harvested * 300;
/* 127 */     score += this.mithril_nuggets_harvested * 500;
/* 128 */     score += this.adamantium_nuggets_harvested * 900;
/*     */     
/* 130 */     score += this.copper_ore_harvested * 900;
/* 131 */     score += this.silver_ore_harvested * 1800;
/* 132 */     score += this.gold_ore_harvested * 2700;
/* 133 */     score += this.iron_ore_harvested * 2700;
/* 134 */     score += this.mithril_ore_harvested * 4500;
/* 135 */     score += this.adamantium_ore_harvested * 8100;
/*     */     
/* 137 */     score += this.experience;
/*     */     
/* 139 */     return score;
/*     */   }
/*     */ 
/*     */   
/*     */   private String getKilledMobString(String mob_name, boolean killed) {
/* 144 */     if (!killed) {
/* 145 */       return "";
/*     */     }
/* 147 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 149 */     sb.append(StringHelper.startsWithVowel(mob_name) ? "an" : "a");
/* 150 */     sb.append(" ");
/* 151 */     sb.append(mob_name);
/*     */     
/* 153 */     sb.append(", ");
/*     */     
/* 155 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private String getHarvestedItemString(Item item, int number_harvested) {
/* 160 */     if (number_harvested == 0) {
/* 161 */       return "";
/*     */     }
/* 163 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 165 */     sb.append(number_harvested);
/* 166 */     sb.append(" ");
/* 167 */     sb.append(item.getItemDisplayName().toLowerCase());
/*     */     
/* 169 */     if (number_harvested > 1) {
/* 170 */       sb.append("s");
/*     */     }
/* 172 */     sb.append(", ");
/*     */     
/* 174 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString(String username) {
/* 179 */     StringBuffer line = new StringBuffer();
/* 180 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 182 */     line.append(calcScore() + ": " + username + " has killed ");
/*     */     
/* 184 */     sb.append(getKilledMobString("skeleton", this.killed_a_skeleton));
/* 185 */     sb.append(getKilledMobString("zombie", this.killed_a_zombie));
/* 186 */     sb.append(getKilledMobString("spider", this.killed_a_spider));
/* 187 */     sb.append(getKilledMobString("wood_spider", this.killed_a_wood_spider));
/* 188 */     sb.append(getKilledMobString("creeper", this.killed_a_creeper));
/* 189 */     sb.append(getKilledMobString("large slime", this.killed_a_large_slime));
/* 190 */     sb.append(getKilledMobString("ghoul", this.killed_a_ghoul));
/* 191 */     sb.append(getKilledMobString("wight", this.killed_a_wight));
/* 192 */     sb.append(getKilledMobString("invisible stalker", this.killed_an_invisible_stalker));
/* 193 */     sb.append(getKilledMobString("witch", this.killed_a_witch));
/* 194 */     sb.append(getKilledMobString("shadow", this.killed_a_shadow));
/* 195 */     sb.append(getKilledMobString("hellhound", this.killed_a_hellhound));
/* 196 */     sb.append(getKilledMobString("demon spider", this.killed_a_demon_spider));
/*     */     
/* 198 */     if (sb.length() == 0) {
/* 199 */       line.append("no mobs, ");
/*     */     } else {
/* 201 */       line.append(sb.toString());
/*     */     } 
/* 203 */     line.append("has harvested ");
/*     */     
/* 205 */     sb.setLength(0);
/*     */     
/* 207 */     sb.append(getHarvestedItemString(Item.copperNugget, this.copper_nuggets_harvested));
/* 208 */     sb.append(getHarvestedItemString(Item.silverNugget, this.silver_nuggets_harvested));
/* 209 */     sb.append(getHarvestedItemString(Item.goldNugget, this.gold_nuggets_harvested));
/* 210 */     sb.append(getHarvestedItemString(Item.mithrilNugget, this.mithril_nuggets_harvested));
/* 211 */     sb.append(getHarvestedItemString(Item.adamantiumNugget, this.adamantium_nuggets_harvested));
/*     */     
/* 213 */     sb.append(getHarvestedItemString(Item.getItem(Block.oreCopper), this.copper_ore_harvested));
/* 214 */     sb.append(getHarvestedItemString(Item.getItem(Block.oreSilver), this.silver_ore_harvested));
/* 215 */     sb.append(getHarvestedItemString(Item.getItem(Block.oreGold), this.gold_ore_harvested));
/* 216 */     sb.append(getHarvestedItemString(Item.getItem(Block.oreIron), this.iron_ore_harvested));
/* 217 */     sb.append(getHarvestedItemString(Item.getItem(Block.oreMithril), this.mithril_ore_harvested));
/* 218 */     sb.append(getHarvestedItemString(Item.getItem(Block.oreAdamantium), this.adamantium_ore_harvested));
/*     */     
/* 220 */     if (sb.length() == 0) {
/* 221 */       line.append("no scoring items, ");
/*     */     } else {
/* 223 */       line.append(sb.toString());
/*     */     } 
/* 225 */     if (this.caught_a_fish) {
/* 226 */       line.append("has caught a fish, ");
/*     */     }
/* 228 */     line.append("and has " + this.experience + " experience.");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 250 */     return line.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TournamentStanding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */