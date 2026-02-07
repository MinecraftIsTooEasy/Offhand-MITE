/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum EnumTournamentType
/*     */ {
/*   7 */   open(true, false, false, true, 384, 0),
/*   8 */   pickaxe(true, false, true, true, 384, 0),
/*   9 */   diamond(true, false, true, true, 384, 0),
/*  10 */   nether(true, false, true, true, 384, 0),
/*  11 */   score(true, false, true, true, 384, 12),
/*  12 */   wonder(false, true, false, true, 2048, 432);
/*     */   
/*     */   final boolean has_safe_zone;
/*     */   
/*     */   final boolean uses_allotted_times;
/*     */   
/*     */   final boolean allows_animal_spawning;
/*     */   
/*     */   final boolean prevents_time_forwarding;
/*     */   
/*     */   final int arena_radius;
/*     */   
/*     */   final int time_limit_in_days;
/*     */   
/*     */   EnumTournamentType(boolean has_safe_zone, boolean uses_allotted_times, boolean allows_animal_spawning, boolean prevents_time_forwarding, int arena_radius, int time_limit_in_days) {
/*  27 */     this.has_safe_zone = has_safe_zone;
/*  28 */     this.uses_allotted_times = uses_allotted_times;
/*  29 */     this.allows_animal_spawning = allows_animal_spawning;
/*  30 */     this.prevents_time_forwarding = prevents_time_forwarding;
/*     */     
/*  32 */     this.arena_radius = arena_radius;
/*  33 */     this.time_limit_in_days = time_limit_in_days;
/*     */   }
/*     */ 
/*     */   
/*     */   static EnumTournamentType get(int ordinal) {
/*  38 */     return values()[ordinal];
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumTournamentType getTournamentType(String tournament_type) {
/*  43 */     if (tournament_type == null)
/*  44 */       return null; 
/*  45 */     if (tournament_type.equalsIgnoreCase("open"))
/*  46 */       return open; 
/*  47 */     if (tournament_type.equalsIgnoreCase("pickaxe"))
/*  48 */       return pickaxe; 
/*  49 */     if (tournament_type.equalsIgnoreCase("diamond"))
/*  50 */       return diamond; 
/*  51 */     if (tournament_type.equalsIgnoreCase("nether"))
/*  52 */       return nether; 
/*  53 */     if (tournament_type.equalsIgnoreCase("score"))
/*  54 */       return score; 
/*  55 */     if (tournament_type.equalsIgnoreCase("wonder")) {
/*  56 */       return wonder;
/*     */     }
/*  58 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getTournamentObjective(EnumTournamentType tournament_type) {
/*     */     String objective;
/*  65 */     if (tournament_type == pickaxe)
/*  66 */     { objective = "craft a pickaxe"; }
/*  67 */     else if (tournament_type == diamond)
/*  68 */     { objective = "collect a diamond"; }
/*  69 */     else if (tournament_type == nether)
/*  70 */     { objective = "enter the netherworld"; }
/*  71 */     else { if (tournament_type == score)
/*     */       {
/*     */ 
/*     */         
/*  75 */         return "The player that accumulates the highest score wins! Increased your score by killing one of each mob, obtaining nuggets from gravel, harvesting metal ores, catching a fish, and gaining experience. The tournament will run until the end of day " + ((DedicatedServer.tournament_type == null) ? 0 : DedicatedServer.tournament_type.time_limit_in_days) + "."; } 
/*  76 */       if (tournament_type == wonder) {
/*  77 */         return "The server that builds a sacred pyramid in the least number of world ticks wins! Combine sandstone with gold nuggets to produce sacred sandstone. The pyramid must be " + DedicatedServer.getRequiredPyramidHeight() + " blocks tall and the outside made of sacred sandstone.";
/*     */       }
/*  79 */       return null; }
/*     */     
/*  81 */     return "The first player to " + objective + " wins!";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getTournamentVictoryMessage(EntityPlayer player, EnumTournamentType tournament_type) {
/*     */     String objective;
/*  88 */     if (tournament_type == pickaxe)
/*  89 */     { objective = "crafted a pickaxe"; }
/*  90 */     else if (tournament_type == diamond)
/*  91 */     { objective = "collected a diamond"; }
/*  92 */     else if (tournament_type == nether)
/*  93 */     { objective = "entered the netherworld"; }
/*  94 */     else { if (tournament_type == score)
/*  95 */         return "Notice: The tournament has concluded and the winner will be announced shortly."; 
/*  96 */       if (tournament_type == wonder) {
/*  97 */         return "Notice: The sacred pyramid has been completed! It took " + DedicatedServer.getTickOfWorld() + " world ticks! Congratulations!";
/*     */       }
/*  99 */       return null; }
/*     */     
/* 101 */     return "Notice: " + player.username + " has " + objective + " and wins the tournament! Congratulations!";
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumTournamentType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */