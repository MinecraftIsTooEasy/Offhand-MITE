/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Curse
/*     */ {
/*  10 */   public static Curse[] cursesList = new Curse[64];
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
/*  31 */   public static final Curse equipment_decays_faster = new Curse(1, "equipmentDecay");
/*  32 */   public static final Curse cannot_hold_breath = new Curse(2, "cantHoldBreath");
/*  33 */   public static final Curse cannot_run = new Curse(3, "cantRun");
/*  34 */   public static final Curse cannot_eat_animals = new Curse(4, "cantEatAnimals");
/*  35 */   public static final Curse cannot_eat_plants = new Curse(5, "cantEatPlants");
/*  36 */   public static final Curse cannot_drink = new Curse(6, "cantDrink");
/*  37 */   public static final Curse endermen_aggro = new Curse(7, "endermenEnemy");
/*  38 */   public static final Curse clumsiness = new Curse(8, "clumsiness");
/*  39 */   public static final Curse entanglement = new Curse(9, "entanglement");
/*  40 */   public static final Curse cannot_wear_armor = new Curse(10, "cantWearArmor");
/*  41 */   public static final Curse cannot_open_chests = new Curse(11, "cantOpenChests");
/*  42 */   public static final Curse cannot_sleep = new Curse(12, "cantSleep");
/*  43 */   public static final Curse fear_of_spiders = new Curse(13, "fearOfSpiders");
/*  44 */   public static final Curse fear_of_wolves = new Curse(14, "fearOfWolves");
/*  45 */   public static final Curse fear_of_creepers = new Curse(15, "fearOfCreepers");
/*  46 */   public static final Curse fear_of_undead = new Curse(16, "fearOfUndead");
/*     */   
/*     */   public String cursed_player_username;
/*     */   
/*     */   public UUID cursing_entity_uuid;
/*     */   
/*     */   public long time_of_realization;
/*     */   
/*     */   public boolean has_been_realized;
/*     */   
/*     */   public boolean effect_known;
/*     */   
/*     */   public boolean effect_has_already_been_learned;
/*     */   
/*     */   public int id;
/*     */   
/*     */   public String key;
/*     */   
/*     */   public Curse(int id, String key) {
/*  65 */     this.id = id;
/*     */ 
/*     */     
/*  68 */     this.key = key;
/*     */     
/*  70 */     if (cursesList[id] != null) {
/*  71 */       Minecraft.setErrorMessage("Curse id=" + id + " already taken!");
/*     */     } else {
/*  73 */       cursesList[id] = this;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Curse(String cursed_player_username, UUID cursing_entity_uuid, Curse curse, long time_of_realization, boolean has_been_realized, boolean effect_known) {
/*  78 */     this.cursed_player_username = cursed_player_username;
/*  79 */     this.cursing_entity_uuid = cursing_entity_uuid;
/*     */     
/*  81 */     this.id = curse.id;
/*     */     
/*  83 */     this.time_of_realization = time_of_realization;
/*  84 */     this.has_been_realized = has_been_realized;
/*  85 */     this.effect_known = effect_known;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Curse getRandomCurse(Random rand) {
/*  90 */     if (Minecraft.inDevMode()) {
/*  91 */       return cannot_drink;
/*     */     }
/*     */     
/*     */     while (true) {
/*  95 */       int index = rand.nextInt(cursesList.length);
/*     */       
/*  97 */       if (cursesList[index] != null) {
/*  98 */         return cursesList[index];
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getTitle() {
/* 104 */     return Translator.get("curse." + this.key + ".name");
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getTooltip() {
/* 109 */     return StringHelper.explode(Translator.get("curse." + this.key + ".desc"), "\\|");
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Curse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */