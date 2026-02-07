/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ServerCommandScoreboard
/*     */   extends CommandBase
/*     */ {
/*     */   public String getCommandName() {
/*  23 */     return "scoreboard";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  28 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender iCommandSender) {
/*  33 */     return "commands.scoreboard.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender iCommandSender, String[] strings) {
/*  38 */     if (strings.length >= 1) {
/*  39 */       if (strings[0].equalsIgnoreCase("objectives")) {
/*  40 */         if (strings.length == 1)
/*  41 */           throw new WrongUsageException("commands.scoreboard.objectives.usage", new Object[0]); 
/*  42 */         if (strings[1].equalsIgnoreCase("list")) {
/*  43 */           getObjectivesList(iCommandSender);
/*  44 */         } else if (strings[1].equalsIgnoreCase("add")) {
/*  45 */           if (strings.length >= 4) {
/*  46 */             addObjective(iCommandSender, strings, 2);
/*     */           } else {
/*  48 */             throw new WrongUsageException("commands.scoreboard.objectives.add.usage", new Object[0]);
/*     */           } 
/*  50 */         } else if (strings[1].equalsIgnoreCase("remove")) {
/*  51 */           if (strings.length == 3) {
/*  52 */             removeObjective(iCommandSender, strings[2]);
/*     */           } else {
/*  54 */             throw new WrongUsageException("commands.scoreboard.objectives.remove.usage", new Object[0]);
/*     */           } 
/*  56 */         } else if (strings[1].equalsIgnoreCase("setdisplay")) {
/*  57 */           if (strings.length == 3 || strings.length == 4) {
/*  58 */             setObjectivesDisplay(iCommandSender, strings, 2);
/*     */           } else {
/*  60 */             throw new WrongUsageException("commands.scoreboard.objectives.setdisplay.usage", new Object[0]);
/*     */           } 
/*     */         } else {
/*  63 */           throw new WrongUsageException("commands.scoreboard.objectives.usage", new Object[0]);
/*     */         } 
/*     */         return;
/*     */       } 
/*  67 */       if (strings[0].equalsIgnoreCase("players")) {
/*  68 */         if (strings.length == 1)
/*  69 */           throw new WrongUsageException("commands.scoreboard.players.usage", new Object[0]); 
/*  70 */         if (strings[1].equalsIgnoreCase("list")) {
/*  71 */           if (strings.length <= 3) {
/*  72 */             listPlayers(iCommandSender, strings, 2);
/*     */           } else {
/*  74 */             throw new WrongUsageException("commands.scoreboard.players.list.usage", new Object[0]);
/*     */           } 
/*  76 */         } else if (strings[1].equalsIgnoreCase("add")) {
/*  77 */           if (strings.length == 5) {
/*  78 */             setPlayerScore(iCommandSender, strings, 2);
/*     */           } else {
/*  80 */             throw new WrongUsageException("commands.scoreboard.players.add.usage", new Object[0]);
/*     */           } 
/*  82 */         } else if (strings[1].equalsIgnoreCase("remove")) {
/*  83 */           if (strings.length == 5) {
/*  84 */             setPlayerScore(iCommandSender, strings, 2);
/*     */           } else {
/*  86 */             throw new WrongUsageException("commands.scoreboard.players.remove.usage", new Object[0]);
/*     */           } 
/*  88 */         } else if (strings[1].equalsIgnoreCase("set")) {
/*  89 */           if (strings.length == 5) {
/*  90 */             setPlayerScore(iCommandSender, strings, 2);
/*     */           } else {
/*  92 */             throw new WrongUsageException("commands.scoreboard.players.set.usage", new Object[0]);
/*     */           } 
/*  94 */         } else if (strings[1].equalsIgnoreCase("reset")) {
/*  95 */           if (strings.length == 3) {
/*  96 */             resetPlayerScore(iCommandSender, strings, 2);
/*     */           } else {
/*  98 */             throw new WrongUsageException("commands.scoreboard.players.reset.usage", new Object[0]);
/*     */           } 
/*     */         } else {
/* 101 */           throw new WrongUsageException("commands.scoreboard.players.usage", new Object[0]);
/*     */         } 
/*     */         return;
/*     */       } 
/* 105 */       if (strings[0].equalsIgnoreCase("teams")) {
/* 106 */         if (strings.length == 1)
/* 107 */           throw new WrongUsageException("commands.scoreboard.teams.usage", new Object[0]); 
/* 108 */         if (strings[1].equalsIgnoreCase("list")) {
/* 109 */           if (strings.length <= 3) {
/* 110 */             getTeamList(iCommandSender, strings, 2);
/*     */           } else {
/* 112 */             throw new WrongUsageException("commands.scoreboard.teams.list.usage", new Object[0]);
/*     */           } 
/* 114 */         } else if (strings[1].equalsIgnoreCase("add")) {
/* 115 */           if (strings.length >= 3) {
/* 116 */             addTeam(iCommandSender, strings, 2);
/*     */           } else {
/* 118 */             throw new WrongUsageException("commands.scoreboard.teams.add.usage", new Object[0]);
/*     */           } 
/* 120 */         } else if (strings[1].equalsIgnoreCase("remove")) {
/* 121 */           if (strings.length == 3) {
/* 122 */             removeTeam(iCommandSender, strings, 2);
/*     */           } else {
/* 124 */             throw new WrongUsageException("commands.scoreboard.teams.remove.usage", new Object[0]);
/*     */           } 
/* 126 */         } else if (strings[1].equalsIgnoreCase("empty")) {
/* 127 */           if (strings.length == 3) {
/* 128 */             emptyTeam(iCommandSender, strings, 2);
/*     */           } else {
/* 130 */             throw new WrongUsageException("commands.scoreboard.teams.empty.usage", new Object[0]);
/*     */           } 
/* 132 */         } else if (strings[1].equalsIgnoreCase("join")) {
/* 133 */           if (strings.length >= 4 || (strings.length == 3 && iCommandSender instanceof EntityPlayer)) {
/* 134 */             joinTeam(iCommandSender, strings, 2);
/*     */           } else {
/* 136 */             throw new WrongUsageException("commands.scoreboard.teams.join.usage", new Object[0]);
/*     */           } 
/* 138 */         } else if (strings[1].equalsIgnoreCase("leave")) {
/* 139 */           if (strings.length >= 3 || iCommandSender instanceof EntityPlayer) {
/* 140 */             leaveTeam(iCommandSender, strings, 2);
/*     */           } else {
/* 142 */             throw new WrongUsageException("commands.scoreboard.teams.leave.usage", new Object[0]);
/*     */           } 
/* 144 */         } else if (strings[1].equalsIgnoreCase("option")) {
/* 145 */           if (strings.length == 4 || strings.length == 5) {
/* 146 */             setTeamOption(iCommandSender, strings, 2);
/*     */           } else {
/* 148 */             throw new WrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
/*     */           } 
/*     */         } else {
/* 151 */           throw new WrongUsageException("commands.scoreboard.teams.usage", new Object[0]);
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/* 158 */     throw new WrongUsageException("commands.scoreboard.usage", new Object[0]);
/*     */   }
/*     */   
/*     */   protected Scoreboard getScoreboardFromWorldServer() {
/* 162 */     return MinecraftServer.getServer().worldServerForDimension(0).getScoreboard();
/*     */   }
/*     */   
/*     */   protected ScoreObjective getScoreObjective(String string, boolean bl) {
/* 166 */     Scoreboard scoreboard = getScoreboardFromWorldServer();
/* 167 */     ScoreObjective scoreObjective = scoreboard.getObjective(string);
/*     */     
/* 169 */     if (scoreObjective == null)
/* 170 */       throw new CommandException("commands.scoreboard.objectiveNotFound", new Object[] { string }); 
/* 171 */     if (bl && scoreObjective.getCriteria().isReadOnly()) {
/* 172 */       throw new CommandException("commands.scoreboard.objectiveReadOnly", new Object[] { string });
/*     */     }
/*     */     
/* 175 */     return scoreObjective;
/*     */   }
/*     */   
/*     */   protected ScorePlayerTeam getTeam(String string) {
/* 179 */     Scoreboard scoreboard = getScoreboardFromWorldServer();
/* 180 */     ScorePlayerTeam scorePlayerTeam = scoreboard.func_96508_e(string);
/*     */     
/* 182 */     if (scorePlayerTeam == null) {
/* 183 */       throw new CommandException("commands.scoreboard.teamNotFound", new Object[] { string });
/*     */     }
/*     */     
/* 186 */     return scorePlayerTeam;
/*     */   }
/*     */   
/*     */   protected void addObjective(ICommandSender iCommandSender, String[] strings, int i) {
/* 190 */     String str1 = strings[i++];
/* 191 */     String str2 = strings[i++];
/* 192 */     Scoreboard scoreboard = getScoreboardFromWorldServer();
/* 193 */     ScoreObjectiveCriteria scoreObjectiveCriteria = (ScoreObjectiveCriteria)ScoreObjectiveCriteria.field_96643_a.get(str2);
/*     */     
/* 195 */     if (scoreObjectiveCriteria == null) {
/* 196 */       String[] arrayOfString = (String[])ScoreObjectiveCriteria.field_96643_a.keySet().toArray((Object[])new String[0]);
/* 197 */       throw new WrongUsageException("commands.scoreboard.objectives.add.wrongType", new Object[] { joinNiceString(arrayOfString) });
/*     */     } 
/* 199 */     if (scoreboard.getObjective(str1) != null) {
/* 200 */       throw new CommandException("commands.scoreboard.objectives.add.alreadyExists", new Object[] { str1 });
/*     */     }
/* 202 */     if (str1.length() > 16) {
/* 203 */       throw new SyntaxErrorException("commands.scoreboard.objectives.add.tooLong", new Object[] { str1, Integer.valueOf(16) });
/*     */     }
/* 205 */     if (str1.length() == 0) {
/* 206 */       throw new WrongUsageException("commands.scoreboard.objectives.add.usage", new Object[0]);
/*     */     }
/*     */     
/* 209 */     if (strings.length > i) {
/* 210 */       String str = func_82360_a(iCommandSender, strings, i);
/*     */       
/* 212 */       if (str.length() > 32) {
/* 213 */         throw new SyntaxErrorException("commands.scoreboard.objectives.add.displayTooLong", new Object[] { str, Integer.valueOf(32) });
/*     */       }
/* 215 */       if (str.length() > 0) {
/* 216 */         scoreboard.func_96535_a(str1, scoreObjectiveCriteria).setDisplayName(str);
/*     */       } else {
/* 218 */         scoreboard.func_96535_a(str1, scoreObjectiveCriteria);
/*     */       } 
/*     */     } else {
/* 221 */       scoreboard.func_96535_a(str1, scoreObjectiveCriteria);
/*     */     } 
/*     */     
/* 224 */     notifyAdmins(iCommandSender, "commands.scoreboard.objectives.add.success", new Object[] { str1 });
/*     */   }
/*     */   
/*     */   protected void addTeam(ICommandSender iCommandSender, String[] strings, int i) {
/* 228 */     String str = strings[i++];
/* 229 */     Scoreboard scoreboard = getScoreboardFromWorldServer();
/*     */     
/* 231 */     if (scoreboard.func_96508_e(str) != null) {
/* 232 */       throw new CommandException("commands.scoreboard.teams.add.alreadyExists", new Object[] { str });
/*     */     }
/* 234 */     if (str.length() > 16) {
/* 235 */       throw new SyntaxErrorException("commands.scoreboard.teams.add.tooLong", new Object[] { str, Integer.valueOf(16) });
/*     */     }
/* 237 */     if (str.length() == 0) {
/* 238 */       throw new WrongUsageException("commands.scoreboard.teams.add.usage", new Object[0]);
/*     */     }
/*     */     
/* 241 */     if (strings.length > i) {
/* 242 */       String str1 = func_82360_a(iCommandSender, strings, i);
/*     */       
/* 244 */       if (str1.length() > 32) {
/* 245 */         throw new SyntaxErrorException("commands.scoreboard.teams.add.displayTooLong", new Object[] { str1, Integer.valueOf(32) });
/*     */       }
/* 247 */       if (str1.length() > 0) {
/* 248 */         scoreboard.createTeam(str).setTeamName(str1);
/*     */       } else {
/* 250 */         scoreboard.createTeam(str);
/*     */       } 
/*     */     } else {
/* 253 */       scoreboard.createTeam(str);
/*     */     } 
/*     */     
/* 256 */     notifyAdmins(iCommandSender, "commands.scoreboard.teams.add.success", new Object[] { str });
/*     */   }
/*     */   
/*     */   protected void setTeamOption(ICommandSender iCommandSender, String[] strings, int i) {
/* 260 */     ScorePlayerTeam scorePlayerTeam = getTeam(strings[i++]);
/* 261 */     String str1 = strings[i++].toLowerCase();
/*     */     
/* 263 */     if (!str1.equalsIgnoreCase("color") && !str1.equalsIgnoreCase("friendlyfire") && !str1.equalsIgnoreCase("seeFriendlyInvisibles")) {
/* 264 */       throw new WrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
/*     */     }
/*     */     
/* 267 */     if (strings.length == 4) {
/* 268 */       if (str1.equalsIgnoreCase("color"))
/* 269 */         throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] { str1, func_96333_a(EnumChatFormatting.func_96296_a(true, false)) }); 
/* 270 */       if (str1.equalsIgnoreCase("friendlyfire") || str1.equalsIgnoreCase("seeFriendlyInvisibles")) {
/* 271 */         throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] { str1, func_96333_a(Arrays.asList(new String[] { "true", "false" })) });
/*     */       }
/*     */       
/* 274 */       throw new WrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
/*     */     } 
/*     */     
/* 277 */     String str2 = strings[i++];
/*     */     
/* 279 */     if (str1.equalsIgnoreCase("color")) {
/* 280 */       EnumChatFormatting enumChatFormatting = EnumChatFormatting.func_96300_b(str2);
/* 281 */       if (str2 == null) throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] { str1, func_96333_a(EnumChatFormatting.func_96296_a(true, false)) }); 
/* 282 */       scorePlayerTeam.setNamePrefix(enumChatFormatting.toString());
/* 283 */       scorePlayerTeam.setNameSuffix(EnumChatFormatting.RESET.toString());
/* 284 */     } else if (str1.equalsIgnoreCase("friendlyfire")) {
/* 285 */       if (!str2.equalsIgnoreCase("true") && !str2.equalsIgnoreCase("false")) throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] { str1, func_96333_a(Arrays.asList(new String[] { "true", "false" })) }); 
/* 286 */       scorePlayerTeam.setAllowFriendlyFire(str2.equalsIgnoreCase("true"));
/* 287 */     } else if (str1.equalsIgnoreCase("seeFriendlyInvisibles")) {
/* 288 */       if (!str2.equalsIgnoreCase("true") && !str2.equalsIgnoreCase("false")) throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[] { str1, func_96333_a(Arrays.asList(new String[] { "true", "false" })) }); 
/* 289 */       scorePlayerTeam.setSeeFriendlyInvisiblesEnabled(str2.equalsIgnoreCase("true"));
/*     */     } 
/*     */     
/* 292 */     notifyAdmins(iCommandSender, "commands.scoreboard.teams.option.success", new Object[] { str1, scorePlayerTeam.func_96661_b(), str2 });
/*     */   }
/*     */   
/*     */   protected void removeTeam(ICommandSender iCommandSender, String[] strings, int i) {
/* 296 */     Scoreboard scoreboard = getScoreboardFromWorldServer();
/* 297 */     ScorePlayerTeam scorePlayerTeam = getTeam(strings[i++]);
/*     */     
/* 299 */     scoreboard.func_96511_d(scorePlayerTeam);
/*     */     
/* 301 */     notifyAdmins(iCommandSender, "commands.scoreboard.teams.remove.success", new Object[] { scorePlayerTeam.func_96661_b() });
/*     */   }
/*     */   
/*     */   protected void getTeamList(ICommandSender iCommandSender, String[] strings, int i) {
/* 305 */     Scoreboard scoreboard = getScoreboardFromWorldServer();
/*     */     
/* 307 */     if (strings.length > i) {
/* 308 */       ScorePlayerTeam scorePlayerTeam = getTeam(strings[i++]);
/* 309 */       Collection collection = scorePlayerTeam.getMembershipCollection();
/*     */       
/* 311 */       if (collection.size() > 0) {
/* 312 */         iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.scoreboard.teams.list.player.count", new Object[] { Integer.valueOf(collection.size()), scorePlayerTeam.func_96661_b() }).setColor(EnumChatFormatting.DARK_GREEN));
/* 313 */         iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(joinNiceString(collection.toArray())));
/*     */       } else {
/* 315 */         throw new CommandException("commands.scoreboard.teams.list.player.empty", new Object[] { scorePlayerTeam.func_96661_b() });
/*     */       } 
/*     */     } else {
/* 318 */       Collection collection = scoreboard.func_96525_g();
/*     */       
/* 320 */       if (collection.size() > 0) {
/* 321 */         iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.scoreboard.teams.list.count", new Object[] { Integer.valueOf(collection.size()) }).setColor(EnumChatFormatting.DARK_GREEN));
/*     */         
/* 323 */         for (ScorePlayerTeam scorePlayerTeam : collection) {
/* 324 */           iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.scoreboard.teams.list.entry", new Object[] { scorePlayerTeam.func_96661_b(), scorePlayerTeam.func_96669_c(), Integer.valueOf(scorePlayerTeam.getMembershipCollection().size()) }));
/*     */         } 
/*     */       } else {
/* 327 */         throw new CommandException("commands.scoreboard.teams.list.empty", new Object[0]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void joinTeam(ICommandSender iCommandSender, String[] strings, int i) {
/* 333 */     Scoreboard scoreboard = getScoreboardFromWorldServer();
/* 334 */     ScorePlayerTeam scorePlayerTeam = scoreboard.func_96508_e(strings[i++]);
/* 335 */     HashSet<String> hashSet = new HashSet();
/*     */     
/* 337 */     if (iCommandSender instanceof EntityPlayer && i == strings.length) {
/* 338 */       String str = getCommandSenderAsPlayer(iCommandSender).getEntityName();
/*     */       
/* 340 */       scoreboard.addPlayerToTeam(str, scorePlayerTeam);
/* 341 */       hashSet.add(str);
/*     */     } else {
/* 343 */       while (i < strings.length) {
/* 344 */         String str = func_96332_d(iCommandSender, strings[i++]);
/*     */         
/* 346 */         scoreboard.addPlayerToTeam(str, scorePlayerTeam);
/* 347 */         hashSet.add(str);
/*     */       } 
/*     */     } 
/*     */     
/* 351 */     if (!hashSet.isEmpty()) notifyAdmins(iCommandSender, "commands.scoreboard.teams.join.success", new Object[] { Integer.valueOf(hashSet.size()), scorePlayerTeam.func_96661_b(), joinNiceString(hashSet.toArray((Object[])new String[0])) }); 
/*     */   }
/*     */   
/*     */   protected void leaveTeam(ICommandSender iCommandSender, String[] strings, int i) {
/* 355 */     Scoreboard scoreboard = getScoreboardFromWorldServer();
/* 356 */     HashSet<String> hashSet1 = new HashSet();
/* 357 */     HashSet<String> hashSet2 = new HashSet();
/*     */     
/* 359 */     if (iCommandSender instanceof EntityPlayer && i == strings.length) {
/* 360 */       String str = getCommandSenderAsPlayer(iCommandSender).getEntityName();
/*     */       
/* 362 */       if (scoreboard.removePlayerFromTeams(str)) {
/* 363 */         hashSet1.add(str);
/*     */       } else {
/* 365 */         hashSet2.add(str);
/*     */       } 
/*     */     } else {
/* 368 */       while (i < strings.length) {
/* 369 */         String str = func_96332_d(iCommandSender, strings[i++]);
/*     */         
/* 371 */         if (scoreboard.removePlayerFromTeams(str)) {
/* 372 */           hashSet1.add(str); continue;
/*     */         } 
/* 374 */         hashSet2.add(str);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 379 */     if (!hashSet1.isEmpty()) notifyAdmins(iCommandSender, "commands.scoreboard.teams.leave.success", new Object[] { Integer.valueOf(hashSet1.size()), joinNiceString(hashSet1.toArray((Object[])new String[0])) }); 
/* 380 */     if (!hashSet2.isEmpty()) throw new CommandException("commands.scoreboard.teams.leave.failure", new Object[] { Integer.valueOf(hashSet2.size()), joinNiceString(hashSet2.toArray(new String[0])) }); 
/*     */   }
/*     */   
/*     */   protected void emptyTeam(ICommandSender iCommandSender, String[] strings, int i) {
/* 384 */     Scoreboard scoreboard = getScoreboardFromWorldServer();
/* 385 */     ScorePlayerTeam scorePlayerTeam = getTeam(strings[i++]);
/* 386 */     ArrayList arrayList = new ArrayList(scorePlayerTeam.getMembershipCollection());
/*     */     
/* 388 */     if (arrayList.isEmpty()) {
/* 389 */       throw new CommandException("commands.scoreboard.teams.empty.alreadyEmpty", new Object[] { scorePlayerTeam.func_96661_b() });
/*     */     }
/*     */     
/* 392 */     for (String str : arrayList) {
/* 393 */       scoreboard.removePlayerFromTeam(str, scorePlayerTeam);
/*     */     }
/*     */     
/* 396 */     notifyAdmins(iCommandSender, "commands.scoreboard.teams.empty.success", new Object[] { Integer.valueOf(arrayList.size()), scorePlayerTeam.func_96661_b() });
/*     */   }
/*     */   
/*     */   protected void removeObjective(ICommandSender iCommandSender, String string) {
/* 400 */     Scoreboard scoreboard = getScoreboardFromWorldServer();
/* 401 */     ScoreObjective scoreObjective = getScoreObjective(string, false);
/*     */     
/* 403 */     scoreboard.func_96519_k(scoreObjective);
/*     */     
/* 405 */     notifyAdmins(iCommandSender, "commands.scoreboard.objectives.remove.success", new Object[] { string });
/*     */   }
/*     */   
/*     */   protected void getObjectivesList(ICommandSender iCommandSender) {
/* 409 */     Scoreboard scoreboard = getScoreboardFromWorldServer();
/* 410 */     Collection collection = scoreboard.getScoreObjectives();
/*     */     
/* 412 */     if (collection.size() > 0) {
/* 413 */       iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.scoreboard.objectives.list.count", new Object[] { Integer.valueOf(collection.size()) }).setColor(EnumChatFormatting.DARK_GREEN));
/*     */       
/* 415 */       for (ScoreObjective scoreObjective : collection) {
/* 416 */         iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.scoreboard.objectives.list.entry", new Object[] { scoreObjective.getName(), scoreObjective.getDisplayName(), scoreObjective.getCriteria().func_96636_a() }));
/*     */       } 
/*     */     } else {
/* 419 */       throw new CommandException("commands.scoreboard.objectives.list.empty", new Object[0]);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void setObjectivesDisplay(ICommandSender iCommandSender, String[] strings, int i) {
/* 424 */     Scoreboard scoreboard = getScoreboardFromWorldServer();
/* 425 */     String str = strings[i++];
/* 426 */     int j = Scoreboard.getObjectiveDisplaySlotNumber(str);
/* 427 */     ScoreObjective scoreObjective = null;
/*     */     
/* 429 */     if (strings.length == 4) {
/* 430 */       scoreObjective = getScoreObjective(strings[i++], false);
/*     */     }
/*     */     
/* 433 */     if (j < 0) {
/* 434 */       throw new CommandException("commands.scoreboard.objectives.setdisplay.invalidSlot", new Object[] { str });
/*     */     }
/*     */     
/* 437 */     scoreboard.func_96530_a(j, scoreObjective);
/*     */     
/* 439 */     if (scoreObjective != null) {
/* 440 */       notifyAdmins(iCommandSender, "commands.scoreboard.objectives.setdisplay.successSet", new Object[] { Scoreboard.getObjectiveDisplaySlot(j), scoreObjective.getName() });
/*     */     } else {
/* 442 */       notifyAdmins(iCommandSender, "commands.scoreboard.objectives.setdisplay.successCleared", new Object[] { Scoreboard.getObjectiveDisplaySlot(j) });
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void listPlayers(ICommandSender iCommandSender, String[] strings, int i) {
/* 447 */     Scoreboard scoreboard = getScoreboardFromWorldServer();
/*     */     
/* 449 */     if (strings.length > i) {
/* 450 */       String str = func_96332_d(iCommandSender, strings[i++]);
/* 451 */       Map map = scoreboard.func_96510_d(str);
/*     */       
/* 453 */       if (map.size() > 0) {
/* 454 */         iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.scoreboard.players.list.player.count", new Object[] { Integer.valueOf(map.size()), str }).setColor(EnumChatFormatting.DARK_GREEN));
/*     */         
/* 456 */         for (Score score : map.values()) {
/* 457 */           iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.scoreboard.players.list.player.entry", new Object[] { Integer.valueOf(score.getScorePoints()), score.func_96645_d().getDisplayName(), score.func_96645_d().getName() }));
/*     */         } 
/*     */       } else {
/* 460 */         throw new CommandException("commands.scoreboard.players.list.player.empty", new Object[] { str });
/*     */       } 
/*     */     } else {
/* 463 */       Collection collection = scoreboard.getObjectiveNames();
/*     */       
/* 465 */       if (collection.size() > 0) {
/* 466 */         iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.scoreboard.players.list.count", new Object[] { Integer.valueOf(collection.size()) }).setColor(EnumChatFormatting.DARK_GREEN));
/* 467 */         iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(joinNiceString(collection.toArray())));
/*     */       } else {
/* 469 */         throw new CommandException("commands.scoreboard.players.list.empty", new Object[0]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void setPlayerScore(ICommandSender iCommandSender, String[] strings, int i) {
/* 475 */     String str1 = strings[i - 1];
/* 476 */     String str2 = func_96332_d(iCommandSender, strings[i++]);
/* 477 */     ScoreObjective scoreObjective = getScoreObjective(strings[i++], true);
/* 478 */     int j = str1.equalsIgnoreCase("set") ? parseInt(iCommandSender, strings[i++]) : parseIntWithMin(iCommandSender, strings[i++], 1);
/* 479 */     Scoreboard scoreboard = getScoreboardFromWorldServer();
/* 480 */     Score score = scoreboard.func_96529_a(str2, scoreObjective);
/*     */     
/* 482 */     if (str1.equalsIgnoreCase("set")) {
/* 483 */       score.func_96647_c(j);
/* 484 */     } else if (str1.equalsIgnoreCase("add")) {
/* 485 */       score.func_96649_a(j);
/*     */     } else {
/* 487 */       score.func_96646_b(j);
/*     */     } 
/*     */     
/* 490 */     notifyAdmins(iCommandSender, "commands.scoreboard.players.set.success", new Object[] { scoreObjective.getName(), str2, Integer.valueOf(score.getScorePoints()) });
/*     */   }
/*     */   
/*     */   protected void resetPlayerScore(ICommandSender iCommandSender, String[] strings, int i) {
/* 494 */     Scoreboard scoreboard = getScoreboardFromWorldServer();
/* 495 */     String str = func_96332_d(iCommandSender, strings[i++]);
/*     */     
/* 497 */     scoreboard.func_96515_c(str);
/*     */     
/* 499 */     notifyAdmins(iCommandSender, "commands.scoreboard.players.reset.success", new Object[] { str });
/*     */   }
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender iCommandSender, String[] strings) {
/* 504 */     if (strings.length == 1) {
/* 505 */       return getListOfStringsMatchingLastWord(strings, new String[] { "objectives", "players", "teams" });
/*     */     }
/*     */     
/* 508 */     if (strings[0].equalsIgnoreCase("objectives")) {
/* 509 */       if (strings.length == 2) {
/* 510 */         return getListOfStringsMatchingLastWord(strings, new String[] { "list", "add", "remove", "setdisplay" });
/*     */       }
/*     */       
/* 513 */       if (strings[1].equalsIgnoreCase("add")) {
/* 514 */         if (strings.length == 4) {
/* 515 */           return getListOfStringsFromIterableMatchingLastWord(strings, ScoreObjectiveCriteria.field_96643_a.keySet());
/*     */         }
/* 517 */       } else if (strings[1].equalsIgnoreCase("remove")) {
/* 518 */         if (strings.length == 3) {
/* 519 */           return getListOfStringsFromIterableMatchingLastWord(strings, getScoreObjectivesList(false));
/*     */         }
/* 521 */       } else if (strings[1].equalsIgnoreCase("setdisplay")) {
/* 522 */         if (strings.length == 3)
/* 523 */           return getListOfStringsMatchingLastWord(strings, new String[] { "list", "sidebar", "belowName" }); 
/* 524 */         if (strings.length == 4) {
/* 525 */           return getListOfStringsFromIterableMatchingLastWord(strings, getScoreObjectivesList(false));
/*     */         }
/*     */       } 
/* 528 */     } else if (strings[0].equalsIgnoreCase("players")) {
/* 529 */       if (strings.length == 2) {
/* 530 */         return getListOfStringsMatchingLastWord(strings, new String[] { "set", "add", "remove", "reset", "list" });
/*     */       }
/*     */       
/* 533 */       if (strings[1].equalsIgnoreCase("set") || strings[1].equalsIgnoreCase("add") || strings[1].equalsIgnoreCase("remove")) {
/* 534 */         if (strings.length == 3)
/* 535 */           return getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames()); 
/* 536 */         if (strings.length == 4) {
/* 537 */           return getListOfStringsFromIterableMatchingLastWord(strings, getScoreObjectivesList(true));
/*     */         }
/* 539 */       } else if ((strings[1].equalsIgnoreCase("reset") || strings[1].equalsIgnoreCase("list")) && 
/* 540 */         strings.length == 3) {
/* 541 */         return getListOfStringsFromIterableMatchingLastWord(strings, getScoreboardFromWorldServer().getObjectiveNames());
/*     */       }
/*     */     
/* 544 */     } else if (strings[0].equalsIgnoreCase("teams")) {
/* 545 */       if (strings.length == 2) {
/* 546 */         return getListOfStringsMatchingLastWord(strings, new String[] { "add", "remove", "join", "leave", "empty", "list", "option" });
/*     */       }
/*     */       
/* 549 */       if (strings[1].equalsIgnoreCase("join")) {
/* 550 */         if (strings.length == 3)
/* 551 */           return getListOfStringsFromIterableMatchingLastWord(strings, getScoreboardFromWorldServer().func_96531_f()); 
/* 552 */         if (strings.length >= 4)
/* 553 */           return getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames()); 
/*     */       } else {
/* 555 */         if (strings[1].equalsIgnoreCase("leave"))
/* 556 */           return getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames()); 
/* 557 */         if (strings[1].equalsIgnoreCase("empty") || strings[1].equalsIgnoreCase("list") || strings[1].equalsIgnoreCase("remove")) {
/* 558 */           if (strings.length == 3) {
/* 559 */             return getListOfStringsFromIterableMatchingLastWord(strings, getScoreboardFromWorldServer().func_96531_f());
/*     */           }
/* 561 */         } else if (strings[1].equalsIgnoreCase("option")) {
/* 562 */           if (strings.length == 3)
/* 563 */             return getListOfStringsFromIterableMatchingLastWord(strings, getScoreboardFromWorldServer().func_96531_f()); 
/* 564 */           if (strings.length == 4)
/* 565 */             return getListOfStringsMatchingLastWord(strings, new String[] { "color", "friendlyfire", "seeFriendlyInvisibles" }); 
/* 566 */           if (strings.length == 5) {
/* 567 */             if (strings[3].equalsIgnoreCase("color"))
/* 568 */               return getListOfStringsFromIterableMatchingLastWord(strings, EnumChatFormatting.func_96296_a(true, false)); 
/* 569 */             if (strings[3].equalsIgnoreCase("friendlyfire") || strings[3].equalsIgnoreCase("seeFriendlyInvisibles")) {
/* 570 */               return getListOfStringsMatchingLastWord(strings, new String[] { "true", "false" });
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 576 */     return null;
/*     */   }
/*     */   
/*     */   protected List getScoreObjectivesList(boolean bl) {
/* 580 */     Collection collection = getScoreboardFromWorldServer().getScoreObjectives();
/* 581 */     ArrayList<String> arrayList = new ArrayList();
/*     */     
/* 583 */     for (ScoreObjective scoreObjective : collection) {
/* 584 */       if (!bl || !scoreObjective.getCriteria().isReadOnly()) arrayList.add(scoreObjective.getName());
/*     */     
/*     */     } 
/* 587 */     return arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUsernameIndex(String[] strings, int i) {
/* 592 */     if (strings[0].equalsIgnoreCase("players"))
/* 593 */       return (i == 2); 
/* 594 */     if (strings[0].equalsIgnoreCase("teams")) {
/* 595 */       return (i == 2 || i == 3);
/*     */     }
/*     */     
/* 598 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ServerCommandScoreboard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */