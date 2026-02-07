/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Scoreboard
/*     */ {
/*  15 */   private final Map scoreObjectives = new HashMap<Object, Object>();
/*  16 */   private final Map field_96543_b = new HashMap<Object, Object>();
/*  17 */   private final Map field_96544_c = new HashMap<Object, Object>();
/*  18 */   private final ScoreObjective[] field_96541_d = new ScoreObjective[3];
/*  19 */   private final Map field_96542_e = new HashMap<Object, Object>();
/*  20 */   private final Map teamMemberships = new HashMap<Object, Object>();
/*     */   
/*     */   public ScoreObjective getObjective(String string) {
/*  23 */     return (ScoreObjective)this.scoreObjectives.get(string);
/*     */   }
/*     */   
/*     */   public ScoreObjective func_96535_a(String string, ScoreObjectiveCriteria scoreObjectiveCriteria) {
/*  27 */     ScoreObjective scoreObjective = getObjective(string);
/*  28 */     if (scoreObjective != null) throw new IllegalArgumentException("An objective with the name '" + string + "' already exists!");
/*     */     
/*  30 */     scoreObjective = new ScoreObjective(this, string, scoreObjectiveCriteria);
/*     */     
/*  32 */     List<ScoreObjective> list = (List)this.field_96543_b.get(scoreObjectiveCriteria);
/*     */     
/*  34 */     if (list == null) {
/*  35 */       list = new ArrayList();
/*  36 */       this.field_96543_b.put(scoreObjectiveCriteria, list);
/*     */     } 
/*     */     
/*  39 */     list.add(scoreObjective);
/*  40 */     this.scoreObjectives.put(string, scoreObjective);
/*  41 */     func_96522_a(scoreObjective);
/*     */     
/*  43 */     return scoreObjective;
/*     */   }
/*     */   
/*     */   public Collection func_96520_a(ScoreObjectiveCriteria scoreObjectiveCriteria) {
/*  47 */     Collection<?> collection = (Collection)this.field_96543_b.get(scoreObjectiveCriteria);
/*     */     
/*  49 */     return (collection == null) ? new ArrayList() : new ArrayList(collection);
/*     */   }
/*     */   
/*     */   public Score func_96529_a(String string, ScoreObjective scoreObjective) {
/*  53 */     Map<Object, Object> map = (Map)this.field_96544_c.get(string);
/*     */     
/*  55 */     if (map == null) {
/*  56 */       map = new HashMap<Object, Object>();
/*  57 */       this.field_96544_c.put(string, map);
/*     */     } 
/*     */     
/*  60 */     Score score = (Score)map.get(scoreObjective);
/*     */     
/*  62 */     if (score == null) {
/*  63 */       score = new Score(this, scoreObjective, string);
/*  64 */       map.put(scoreObjective, score);
/*     */     } 
/*     */     
/*  67 */     return score;
/*     */   }
/*     */   
/*     */   public Collection func_96534_i(ScoreObjective scoreObjective) {
/*  71 */     ArrayList<Score> arrayList = new ArrayList();
/*     */     
/*  73 */     for (Map map : this.field_96544_c.values()) {
/*  74 */       Score score = (Score)map.get(scoreObjective);
/*  75 */       if (score != null) arrayList.add(score);
/*     */     
/*     */     } 
/*  78 */     Collections.sort(arrayList, Score.field_96658_a);
/*     */     
/*  80 */     return arrayList;
/*     */   }
/*     */   
/*     */   public Collection getScoreObjectives() {
/*  84 */     return this.scoreObjectives.values();
/*     */   }
/*     */   
/*     */   public Collection getObjectiveNames() {
/*  88 */     return this.field_96544_c.keySet();
/*     */   }
/*     */   
/*     */   public void func_96515_c(String string) {
/*  92 */     Map map = (Map)this.field_96544_c.remove(string);
/*     */     
/*  94 */     if (map != null) {
/*  95 */       func_96516_a(string);
/*     */     }
/*     */   }
/*     */   
/*     */   public Collection func_96528_e() {
/* 100 */     Collection collection = this.field_96544_c.values();
/* 101 */     ArrayList arrayList = new ArrayList();
/*     */     
/* 103 */     for (Map map : collection) {
/* 104 */       arrayList.addAll(map.values());
/*     */     }
/*     */     
/* 107 */     return arrayList;
/*     */   }
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
/*     */   public Map func_96510_d(String string) {
/* 123 */     Map<Object, Object> map = (Map)this.field_96544_c.get(string);
/* 124 */     if (map == null) map = new HashMap<Object, Object>(); 
/* 125 */     return map;
/*     */   }
/*     */   
/*     */   public void func_96519_k(ScoreObjective scoreObjective) {
/* 129 */     this.scoreObjectives.remove(scoreObjective.getName());
/*     */     
/* 131 */     for (byte b = 0; b < 3; b++) {
/* 132 */       if (func_96539_a(b) == scoreObjective) func_96530_a(b, null);
/*     */     
/*     */     } 
/* 135 */     List list = (List)this.field_96543_b.get(scoreObjective.getCriteria());
/* 136 */     if (list != null) list.remove(scoreObjective);
/*     */     
/* 138 */     for (Map map : this.field_96544_c.values()) {
/* 139 */       map.remove(scoreObjective);
/*     */     }
/*     */     
/* 142 */     func_96533_c(scoreObjective);
/*     */   }
/*     */   
/*     */   public void func_96530_a(int i, ScoreObjective scoreObjective) {
/* 146 */     this.field_96541_d[i] = scoreObjective;
/*     */   }
/*     */   
/*     */   public ScoreObjective func_96539_a(int i) {
/* 150 */     return this.field_96541_d[i];
/*     */   }
/*     */   
/*     */   public ScorePlayerTeam func_96508_e(String string) {
/* 154 */     return (ScorePlayerTeam)this.field_96542_e.get(string);
/*     */   }
/*     */   
/*     */   public ScorePlayerTeam createTeam(String string) {
/* 158 */     ScorePlayerTeam scorePlayerTeam = func_96508_e(string);
/* 159 */     if (scorePlayerTeam != null) throw new IllegalArgumentException("An objective with the name '" + string + "' already exists!");
/*     */     
/* 161 */     scorePlayerTeam = new ScorePlayerTeam(this, string);
/* 162 */     this.field_96542_e.put(string, scorePlayerTeam);
/* 163 */     func_96523_a(scorePlayerTeam);
/*     */     
/* 165 */     return scorePlayerTeam;
/*     */   }
/*     */   
/*     */   public void func_96511_d(ScorePlayerTeam scorePlayerTeam) {
/* 169 */     this.field_96542_e.remove(scorePlayerTeam.func_96661_b());
/*     */ 
/*     */ 
/*     */     
/* 173 */     for (String str : scorePlayerTeam.getMembershipCollection()) {
/* 174 */       this.teamMemberships.remove(str);
/*     */     }
/*     */     
/* 177 */     func_96513_c(scorePlayerTeam);
/*     */   }
/*     */   
/*     */   public void addPlayerToTeam(String string, ScorePlayerTeam scorePlayerTeam) {
/* 181 */     if (getPlayersTeam(string) != null) {
/* 182 */       removePlayerFromTeams(string);
/*     */     }
/*     */     
/* 185 */     this.teamMemberships.put(string, scorePlayerTeam);
/* 186 */     scorePlayerTeam.getMembershipCollection().add(string);
/*     */   }
/*     */   
/*     */   public boolean removePlayerFromTeams(String string) {
/* 190 */     ScorePlayerTeam scorePlayerTeam = getPlayersTeam(string);
/*     */     
/* 192 */     if (scorePlayerTeam != null) {
/* 193 */       removePlayerFromTeam(string, scorePlayerTeam);
/* 194 */       return true;
/*     */     } 
/* 196 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removePlayerFromTeam(String string, ScorePlayerTeam scorePlayerTeam) {
/* 201 */     if (getPlayersTeam(string) != scorePlayerTeam) {
/* 202 */       throw new IllegalStateException("Player is either on another team or not on any team. Cannot remove from team '" + scorePlayerTeam.func_96661_b() + "'.");
/*     */     }
/*     */     
/* 205 */     this.teamMemberships.remove(string);
/* 206 */     scorePlayerTeam.getMembershipCollection().remove(string);
/*     */   }
/*     */   
/*     */   public Collection func_96531_f() {
/* 210 */     return this.field_96542_e.keySet();
/*     */   }
/*     */   
/*     */   public Collection func_96525_g() {
/* 214 */     return this.field_96542_e.values();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ScorePlayerTeam getPlayersTeam(String string) {
/* 222 */     return (ScorePlayerTeam)this.teamMemberships.get(string);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96522_a(ScoreObjective scoreObjective) {}
/*     */ 
/*     */   
/*     */   public void func_96532_b(ScoreObjective scoreObjective) {}
/*     */ 
/*     */   
/*     */   public void func_96533_c(ScoreObjective scoreObjective) {}
/*     */ 
/*     */   
/*     */   public void func_96536_a(Score score) {}
/*     */ 
/*     */   
/*     */   public void func_96516_a(String string) {}
/*     */ 
/*     */   
/*     */   public void func_96523_a(ScorePlayerTeam scorePlayerTeam) {}
/*     */ 
/*     */   
/*     */   public void func_96538_b(ScorePlayerTeam scorePlayerTeam) {}
/*     */ 
/*     */   
/*     */   public void func_96513_c(ScorePlayerTeam scorePlayerTeam) {}
/*     */   
/*     */   public static String getObjectiveDisplaySlot(int i) {
/* 250 */     switch (i) {
/*     */       case 0:
/* 252 */         return "list";
/*     */       case 1:
/* 254 */         return "sidebar";
/*     */       case 2:
/* 256 */         return "belowName";
/*     */     } 
/* 258 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getObjectiveDisplaySlotNumber(String string) {
/* 263 */     if (string.equalsIgnoreCase("list"))
/* 264 */       return 0; 
/* 265 */     if (string.equalsIgnoreCase("sidebar"))
/* 266 */       return 1; 
/* 267 */     if (string.equalsIgnoreCase("belowName")) {
/* 268 */       return 2;
/*     */     }
/* 270 */     return -1;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Scoreboard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */