/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Skill
/*     */ {
/*     */   public static final int ANY = -1;
/*     */   public static final int ALL = -1;
/*     */   public static final int NONE = 0;
/*  12 */   public static Skill[] list = new Skill[32];
/*     */ 
/*     */   
/*     */   private static int num_skills;
/*     */   
/*  17 */   public static Skill FIGHTING = new Skill("fighting");
/*  18 */   public static Skill ARCHERY = new Skill("archery");
/*  19 */   public static Skill MINING = new Skill("mining");
/*  20 */   public static Skill FOOD_PREPARATION = new Skill("foodPreparation");
/*  21 */   public static Skill MASONRY = new Skill("masonry");
/*  22 */   public static Skill FARMING = new Skill("farming");
/*  23 */   public static Skill TINKERING = new Skill("tinkering");
/*  24 */   public static Skill CARPENTRY = new Skill("carpentry");
/*  25 */   public static Skill BLACKSMITHING = new Skill("blacksmithing");
/*  26 */   public static Skill BREWING = new Skill("brewing");
/*  27 */   public static Skill FINE_ARTS = new Skill("fineArts");
/*  28 */   public static Skill ENCHANTING = new Skill("enchanting");
/*  29 */   public static Skill FISHING = new Skill("fishing");
/*     */   
/*     */   final int id;
/*     */   
/*     */   final String unlocalized_name;
/*     */   
/*     */   public Skill(String unlocalized_name) {
/*  36 */     this.id = 1 << num_skills;
/*     */     
/*  38 */     list[num_skills++] = this;
/*     */     
/*  40 */     this.unlocalized_name = "skill." + unlocalized_name;
/*     */   }
/*     */ 
/*     */   
/*     */   static Skill getById(int id) {
/*  45 */     for (int i = 0; i < num_skills; i++) {
/*     */       
/*  47 */       if (((list[i]).id & id) != 0) {
/*  48 */         return list[i];
/*     */       }
/*     */     } 
/*  51 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   static Skill[] getSkillsByIds(int ids) {
/*  56 */     int num_skills_present = getNumSkills(ids);
/*     */     
/*  58 */     if (num_skills_present == 0) {
/*  59 */       return null;
/*     */     }
/*  61 */     Skill[] skills = new Skill[num_skills_present];
/*     */     
/*  63 */     int j = 0;
/*     */     
/*  65 */     for (int i = 0; i < num_skills; i++) {
/*     */       
/*  67 */       if (BitHelper.isBitSet(ids, (list[i]).id)) {
/*  68 */         skills[j++] = list[i];
/*     */       }
/*     */     } 
/*  71 */     return (j == 0) ? null : skills;
/*     */   }
/*     */ 
/*     */   
/*     */   static Skill getByLocalizedName(String localized_name, boolean profession_name) {
/*  76 */     for (int i = 0; i < num_skills; i++) {
/*     */       
/*  78 */       if (list[i].getLocalizedName(profession_name).equalsIgnoreCase(localized_name)) {
/*  79 */         return list[i];
/*     */       }
/*     */     } 
/*  82 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   static String getSkillsString(int ids, boolean profession_names, String delimiter) {
/*  87 */     StringBuffer sb = new StringBuffer();
/*     */     
/*  89 */     for (int i = 0; i < num_skills; i++) {
/*     */       
/*  91 */       Skill skill = list[i];
/*     */       
/*  93 */       if (BitHelper.isBitSet(ids, skill.id)) {
/*  94 */         sb.append(skill.getLocalizedName(profession_names) + delimiter);
/*     */       }
/*     */     } 
/*  97 */     String s = sb.toString();
/*     */ 
/*     */     
/* 100 */     return s.isEmpty() ? null : StringHelper.left(s, -delimiter.length());
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getSkillsetsString(int[] skillsets, boolean profession_names) {
/* 105 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 107 */     String delimiter = " or ";
/*     */     
/* 109 */     for (int i = 0; i < skillsets.length; i++) {
/* 110 */       sb.append(getSkillsString(skillsets[i], profession_names, profession_names ? " / " : " + ") + delimiter);
/*     */     }
/* 112 */     String s = sb.toString();
/*     */     
/* 114 */     return s.isEmpty() ? null : StringHelper.left(s, -delimiter.length());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getNumSkills() {
/* 119 */     return num_skills;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getNumSkills(int ids) {
/* 124 */     int num = 0;
/*     */     
/* 126 */     for (int i = 0; i < num_skills; i++) {
/*     */       
/* 128 */       if (BitHelper.isBitSet(ids, (list[i]).id)) {
/* 129 */         num++;
/*     */       }
/*     */     } 
/* 132 */     return num;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean skillExistsIn(Skill skill, int ids) {
/* 137 */     return BitHelper.isBitSet(ids, skill.id);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 142 */     return getLocalizedName(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocalizedName(boolean profession_name) {
/* 147 */     return StatCollector.translateToLocal(profession_name ? (this.unlocalized_name + ".profession") : this.unlocalized_name);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocalizedDescription() {
/* 152 */     return StatCollector.translateToLocal(this.unlocalized_name + ".description");
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Skill.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */