/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ScoreboardSaveData
/*     */   extends WorldSavedData
/*     */ {
/*     */   private Scoreboard theScoreboard;
/*     */   private NBTTagCompound field_96506_b;
/*     */   
/*     */   public ScoreboardSaveData() {
/*  19 */     this("scoreboard");
/*     */   }
/*     */   
/*     */   public ScoreboardSaveData(String string) {
/*  23 */     super(string);
/*     */   }
/*     */   
/*     */   public void func_96499_a(Scoreboard scoreboard) {
/*  27 */     this.theScoreboard = scoreboard;
/*     */     
/*  29 */     if (this.field_96506_b != null) {
/*  30 */       readFromNBT(this.field_96506_b);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound nBTTagCompound) {
/*  36 */     if (this.theScoreboard == null) {
/*  37 */       this.field_96506_b = nBTTagCompound;
/*     */       
/*     */       return;
/*     */     } 
/*  41 */     func_96501_b(nBTTagCompound.getTagList("Objectives"));
/*  42 */     func_96500_c(nBTTagCompound.getTagList("PlayerScores"));
/*     */     
/*  44 */     if (nBTTagCompound.hasKey("DisplaySlots")) {
/*  45 */       func_96504_c(nBTTagCompound.getCompoundTag("DisplaySlots"));
/*     */     }
/*     */     
/*  48 */     if (nBTTagCompound.hasKey("Teams")) {
/*  49 */       func_96498_a(nBTTagCompound.getTagList("Teams"));
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_96498_a(NBTTagList nBTTagList) {
/*  54 */     for (byte b = 0; b < nBTTagList.tagCount(); b++) {
/*  55 */       NBTTagCompound nBTTagCompound = (NBTTagCompound)nBTTagList.tagAt(b);
/*     */       
/*  57 */       ScorePlayerTeam scorePlayerTeam = this.theScoreboard.createTeam(nBTTagCompound.getString("Name"));
/*  58 */       scorePlayerTeam.setTeamName(nBTTagCompound.getString("DisplayName"));
/*  59 */       scorePlayerTeam.setNamePrefix(nBTTagCompound.getString("Prefix"));
/*  60 */       scorePlayerTeam.setNameSuffix(nBTTagCompound.getString("Suffix"));
/*  61 */       if (nBTTagCompound.hasKey("AllowFriendlyFire")) scorePlayerTeam.setAllowFriendlyFire(nBTTagCompound.getBoolean("AllowFriendlyFire")); 
/*  62 */       if (nBTTagCompound.hasKey("SeeFriendlyInvisibles")) scorePlayerTeam.setSeeFriendlyInvisiblesEnabled(nBTTagCompound.getBoolean("SeeFriendlyInvisibles"));
/*     */       
/*  64 */       func_96502_a(scorePlayerTeam, nBTTagCompound.getTagList("Players"));
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void func_96502_a(ScorePlayerTeam scorePlayerTeam, NBTTagList nBTTagList) {
/*  69 */     for (byte b = 0; b < nBTTagList.tagCount(); b++) {
/*  70 */       this.theScoreboard.addPlayerToTeam(((NBTTagString)nBTTagList.tagAt(b)).data, scorePlayerTeam);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_96504_c(NBTTagCompound nBTTagCompound) {
/*  75 */     for (byte b = 0; b < 3; b++) {
/*  76 */       if (nBTTagCompound.hasKey("slot_" + b)) {
/*  77 */         String str = nBTTagCompound.getString("slot_" + b);
/*  78 */         ScoreObjective scoreObjective = this.theScoreboard.getObjective(str);
/*  79 */         this.theScoreboard.func_96530_a(b, scoreObjective);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void func_96501_b(NBTTagList nBTTagList) {
/*  85 */     for (byte b = 0; b < nBTTagList.tagCount(); b++) {
/*  86 */       NBTTagCompound nBTTagCompound = (NBTTagCompound)nBTTagList.tagAt(b);
/*     */       
/*  88 */       ScoreObjectiveCriteria scoreObjectiveCriteria = (ScoreObjectiveCriteria)ScoreObjectiveCriteria.field_96643_a.get(nBTTagCompound.getString("CriteriaName"));
/*  89 */       ScoreObjective scoreObjective = this.theScoreboard.func_96535_a(nBTTagCompound.getString("Name"), scoreObjectiveCriteria);
/*  90 */       scoreObjective.setDisplayName(nBTTagCompound.getString("DisplayName"));
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void func_96500_c(NBTTagList nBTTagList) {
/*  95 */     for (byte b = 0; b < nBTTagList.tagCount(); b++) {
/*  96 */       NBTTagCompound nBTTagCompound = (NBTTagCompound)nBTTagList.tagAt(b);
/*     */       
/*  98 */       ScoreObjective scoreObjective = this.theScoreboard.getObjective(nBTTagCompound.getString("Objective"));
/*  99 */       Score score = this.theScoreboard.func_96529_a(nBTTagCompound.getString("Name"), scoreObjective);
/* 100 */       score.func_96647_c(nBTTagCompound.getInteger("Score"));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound nBTTagCompound) {
/* 106 */     if (this.theScoreboard == null) {
/* 107 */       MinecraftServer.getServer().getLogAgent().logWarning("Tried to save scoreboard without having a scoreboard...");
/*     */       
/*     */       return;
/*     */     } 
/* 111 */     nBTTagCompound.setTag("Objectives", func_96505_b());
/* 112 */     nBTTagCompound.setTag("PlayerScores", func_96503_e());
/* 113 */     nBTTagCompound.setTag("Teams", func_96496_a());
/*     */     
/* 115 */     func_96497_d(nBTTagCompound);
/*     */   }
/*     */   
/*     */   protected NBTTagList func_96496_a() {
/* 119 */     NBTTagList nBTTagList = new NBTTagList();
/* 120 */     Collection collection = this.theScoreboard.func_96525_g();
/*     */     
/* 122 */     for (ScorePlayerTeam scorePlayerTeam : collection) {
/* 123 */       NBTTagCompound nBTTagCompound = new NBTTagCompound();
/*     */       
/* 125 */       nBTTagCompound.setString("Name", scorePlayerTeam.func_96661_b());
/* 126 */       nBTTagCompound.setString("DisplayName", scorePlayerTeam.func_96669_c());
/* 127 */       nBTTagCompound.setString("Prefix", scorePlayerTeam.getColorPrefix());
/* 128 */       nBTTagCompound.setString("Suffix", scorePlayerTeam.getColorSuffix());
/* 129 */       nBTTagCompound.setBoolean("AllowFriendlyFire", scorePlayerTeam.getAllowFriendlyFire());
/* 130 */       nBTTagCompound.setBoolean("SeeFriendlyInvisibles", scorePlayerTeam.func_98297_h());
/*     */       
/* 132 */       NBTTagList nBTTagList1 = new NBTTagList();
/*     */       
/* 134 */       for (String str : scorePlayerTeam.getMembershipCollection()) {
/* 135 */         nBTTagList1.appendTag(new NBTTagString("", str));
/*     */       }
/*     */       
/* 138 */       nBTTagCompound.setTag("Players", nBTTagList1);
/*     */       
/* 140 */       nBTTagList.appendTag(nBTTagCompound);
/*     */     } 
/*     */     
/* 143 */     return nBTTagList;
/*     */   }
/*     */   
/*     */   protected void func_96497_d(NBTTagCompound nBTTagCompound) {
/* 147 */     NBTTagCompound nBTTagCompound1 = new NBTTagCompound();
/* 148 */     boolean bool = false;
/*     */     
/* 150 */     for (byte b = 0; b < 3; b++) {
/* 151 */       ScoreObjective scoreObjective = this.theScoreboard.func_96539_a(b);
/*     */       
/* 153 */       if (scoreObjective != null) {
/* 154 */         nBTTagCompound1.setString("slot_" + b, scoreObjective.getName());
/* 155 */         bool = true;
/*     */       } 
/*     */     } 
/*     */     
/* 159 */     if (bool) nBTTagCompound.setCompoundTag("DisplaySlots", nBTTagCompound1); 
/*     */   }
/*     */   
/*     */   protected NBTTagList func_96505_b() {
/* 163 */     NBTTagList nBTTagList = new NBTTagList();
/* 164 */     Collection collection = this.theScoreboard.getScoreObjectives();
/*     */     
/* 166 */     for (ScoreObjective scoreObjective : collection) {
/* 167 */       NBTTagCompound nBTTagCompound = new NBTTagCompound();
/*     */       
/* 169 */       nBTTagCompound.setString("Name", scoreObjective.getName());
/* 170 */       nBTTagCompound.setString("CriteriaName", scoreObjective.getCriteria().func_96636_a());
/* 171 */       nBTTagCompound.setString("DisplayName", scoreObjective.getDisplayName());
/*     */       
/* 173 */       nBTTagList.appendTag(nBTTagCompound);
/*     */     } 
/*     */     
/* 176 */     return nBTTagList;
/*     */   }
/*     */   
/*     */   protected NBTTagList func_96503_e() {
/* 180 */     NBTTagList nBTTagList = new NBTTagList();
/* 181 */     Collection collection = this.theScoreboard.func_96528_e();
/*     */     
/* 183 */     for (Score score : collection) {
/* 184 */       NBTTagCompound nBTTagCompound = new NBTTagCompound();
/*     */       
/* 186 */       nBTTagCompound.setString("Name", score.getPlayerName());
/* 187 */       nBTTagCompound.setString("Objective", score.func_96645_d().getName());
/* 188 */       nBTTagCompound.setInteger("Score", score.getScorePoints());
/*     */       
/* 190 */       nBTTagList.appendTag(nBTTagCompound);
/*     */     } 
/*     */     
/* 193 */     return nBTTagList;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ScoreboardSaveData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */