/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ScorePlayerTeam
/*     */   extends Team
/*     */ {
/*     */   private final Scoreboard theScoreboard;
/*     */   private final String field_96675_b;
/*  16 */   private final Set membershipSet = new HashSet();
/*     */   private String field_96673_d;
/*  18 */   private String field_96674_e = "";
/*  19 */   private String colorSuffix = "";
/*     */   private boolean allowFriendlyFire = true;
/*     */   private boolean field_98301_h = true;
/*     */   
/*     */   public ScorePlayerTeam(Scoreboard scoreboard, String string) {
/*  24 */     this.theScoreboard = scoreboard;
/*  25 */     this.field_96675_b = string;
/*  26 */     this.field_96673_d = string;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_96661_b() {
/*  34 */     return this.field_96675_b;
/*     */   }
/*     */   
/*     */   public String func_96669_c() {
/*  38 */     return this.field_96673_d;
/*     */   }
/*     */   
/*     */   public void setTeamName(String string) {
/*  42 */     if (string == null) throw new IllegalArgumentException("Name cannot be null"); 
/*  43 */     this.field_96673_d = string;
/*  44 */     this.theScoreboard.func_96538_b(this);
/*     */   }
/*     */   
/*     */   public Collection getMembershipCollection() {
/*  48 */     return this.membershipSet;
/*     */   }
/*     */   
/*     */   public String getColorPrefix() {
/*  52 */     return this.field_96674_e;
/*     */   }
/*     */   
/*     */   public void setNamePrefix(String string) {
/*  56 */     if (string == null) throw new IllegalArgumentException("Prefix cannot be null"); 
/*  57 */     this.field_96674_e = string;
/*  58 */     this.theScoreboard.func_96538_b(this);
/*     */   }
/*     */   
/*     */   public String getColorSuffix() {
/*  62 */     return this.colorSuffix;
/*     */   }
/*     */   
/*     */   public void setNameSuffix(String string) {
/*  66 */     if (string == null) throw new IllegalArgumentException("Suffix cannot be null"); 
/*  67 */     this.colorSuffix = string;
/*  68 */     this.theScoreboard.func_96538_b(this);
/*     */   }
/*     */   
/*     */   public String func_142053_d(String string) {
/*  72 */     return getColorPrefix() + string + getColorSuffix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String formatPlayerName(Team team, String string) {
/*  80 */     if (team == null) return string; 
/*  81 */     return team.func_142053_d(string);
/*     */   }
/*     */   
/*     */   public boolean getAllowFriendlyFire() {
/*  85 */     return this.allowFriendlyFire;
/*     */   }
/*     */   
/*     */   public void setAllowFriendlyFire(boolean bl) {
/*  89 */     this.allowFriendlyFire = bl;
/*  90 */     this.theScoreboard.func_96538_b(this);
/*     */   }
/*     */   
/*     */   public boolean func_98297_h() {
/*  94 */     return this.field_98301_h;
/*     */   }
/*     */   
/*     */   public void setSeeFriendlyInvisiblesEnabled(boolean bl) {
/*  98 */     this.field_98301_h = bl;
/*  99 */     this.theScoreboard.func_96538_b(this);
/*     */   }
/*     */   
/*     */   public int func_98299_i() {
/* 103 */     int i = 0;
/*     */     
/* 105 */     if (getAllowFriendlyFire()) i |= 0x1; 
/* 106 */     if (func_98297_h()) i |= 0x2;
/*     */     
/* 108 */     return i;
/*     */   }
/*     */   
/*     */   public void func_98298_a(int i) {
/* 112 */     setAllowFriendlyFire(((i & 0x1) > 0));
/* 113 */     setSeeFriendlyInvisiblesEnabled(((i & 0x2) > 0));
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ScorePlayerTeam.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */