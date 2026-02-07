/*      */ package net.minecraft;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CommandHandler
/*      */   implements ICommandManager
/*      */ {
/*   18 */   private final Map commandMap = new HashMap<Object, Object>();
/*      */ 
/*      */   
/*   21 */   private final Set commandSet = new HashSet();
/*      */   
/*   23 */   private String[] privileged_users = new String[] { "Vuce", "Roninpawn", "ShadowKnight1234" };
/*      */   
/*      */   public static boolean spawning_disabled = false;
/*      */ 
/*      */   
/*      */   private boolean isUserPrivileged(EntityPlayer player) {
/*   29 */     if (Minecraft.inDevMode()) {
/*   30 */       return true;
/*      */     }
/*   32 */     if (player == null || player.username == null) {
/*   33 */       return false;
/*      */     }
/*   35 */     if (player.isZevimrgvInTournament()) {
/*   36 */       return true;
/*      */     }
/*   38 */     for (int i = 0; i < this.privileged_users.length; i++) {
/*      */       
/*   40 */       if (player.username.equalsIgnoreCase(this.privileged_users[i])) {
/*   41 */         return true;
/*      */       }
/*      */     } 
/*   44 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int executeCommand(ICommandSender par1ICommandSender, String par2Str, boolean permission_override) {
/*   50 */     par2Str = par2Str.trim();
/*      */     
/*   52 */     if (par2Str.startsWith("/"))
/*      */     {
/*   54 */       par2Str = par2Str.substring(1);
/*      */     }
/*      */     
/*   57 */     MinecraftServer mc_server = MinecraftServer.getServer();
/*      */     
/*   59 */     WorldServer world = (WorldServer)par1ICommandSender.getEntityWorld();
/*   60 */     ServerPlayer player = (ServerPlayer)world.getPlayerEntityByName(par1ICommandSender.getCommandSenderName());
/*      */     
/*   62 */     EnumCommand command = EnumCommand.get(par2Str);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   71 */     if (command == EnumCommand.version) {
/*      */       
/*   73 */       par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("You are playing MITE 1.6.4 R196" + (Minecraft.inDevMode() ? (EnumChatFormatting.RED + " DEV") : "")).setColor(EnumChatFormatting.YELLOW));
/*      */       
/*   75 */       return 1;
/*      */     } 
/*   77 */     if (command == EnumCommand.versions) {
/*      */       
/*   79 */       WorldInfo world_info = (par1ICommandSender.getEntityWorld()).worldInfo;
/*      */       
/*   81 */       if (world_info.getEarliestMITEReleaseRunIn() == world_info.getLatestMITEReleaseRunIn()) {
/*   82 */         par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("This world has been played in MITE R" + world_info.getEarliestMITEReleaseRunIn() + " only").setColor(EnumChatFormatting.YELLOW));
/*      */       } else {
/*   84 */         par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("This world has been played in MITE releases R" + world_info.getEarliestMITEReleaseRunIn() + " to R" + world_info.getLatestMITEReleaseRunIn()).setColor(EnumChatFormatting.YELLOW));
/*      */       } 
/*   86 */       return 1;
/*      */     } 
/*   88 */     if (command == EnumCommand.villages) {
/*      */       
/*   90 */       WorldInfo world_info = (par1ICommandSender.getEntityWorld()).worldInfo;
/*      */       
/*   92 */       if (world_info.getVillageConditions() < WorldInfo.getVillagePrerequisites()) {
/*      */         
/*   94 */         par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Villages can generate after the following conditions are met:").setColor(EnumChatFormatting.YELLOW));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  108 */         if (!BitHelper.isBitSet(world_info.getVillageConditions(), 16)) {
/*  109 */           par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("- Craft an iron pickaxe or war hammer (or better)").setColor(EnumChatFormatting.YELLOW));
/*      */         }
/*  111 */       } else if (mc_server.worldServers[0].getDayOfWorld() < 60) {
/*      */         
/*  113 */         par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Villages can generate at day 60").setColor(EnumChatFormatting.YELLOW));
/*      */       }
/*      */       else {
/*      */         
/*  117 */         par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Villages can generate").setColor(EnumChatFormatting.YELLOW));
/*      */       } 
/*      */       
/*  120 */       return 1;
/*      */     } 
/*  122 */     if (command == EnumCommand.tournament) {
/*      */       
/*  124 */       if (DedicatedServer.isTournament()) {
/*      */         
/*  126 */         par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(DedicatedServer.getTournamentObjective()).setColor(EnumChatFormatting.YELLOW));
/*  127 */         return 1;
/*      */       } 
/*      */     } else {
/*  130 */       if (command == EnumCommand.day) {
/*      */ 
/*      */         
/*  133 */         par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("It is day " + mc_server.worldServers[0].getDayOfWorld() + " of this world").setColor(EnumChatFormatting.YELLOW));
/*  134 */         return 1;
/*      */       } 
/*  136 */       if (command == EnumCommand.mem) {
/*      */         
/*  138 */         par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("The server is using " + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024L / 1024L) + "MB of memory (" + (Runtime.getRuntime().totalMemory() / 1024L / 1024L) + "MB is allocated)").setColor(EnumChatFormatting.YELLOW));
/*  139 */         return 1;
/*      */       } 
/*  141 */       if (command == EnumCommand.load) {
/*      */         
/*  143 */         int load = (int)(mc_server.getLoadOnServer() * 100.0F);
/*      */         
/*  145 */         if (load < 0) {
/*  146 */           par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("The load on the server is still being calculated").setColor(EnumChatFormatting.YELLOW));
/*      */         } else {
/*  148 */           par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("The server is at " + load + "% of its processing limit").setColor(EnumChatFormatting.YELLOW));
/*      */         } 
/*  150 */         return 1;
/*      */       } 
/*  152 */       if (command == EnumCommand.chunks) {
/*      */         
/*  154 */         int num_active_chunks = 0;
/*      */         
/*  156 */         WorldServer[] world_servers = mc_server.worldServers;
/*      */         
/*  158 */         for (int i = 0; i < world_servers.length; i++) {
/*      */           
/*  160 */           if (world_servers[i] != null) {
/*  161 */             num_active_chunks += (world_servers[i]).activeChunkSet.size();
/*      */           }
/*      */         } 
/*  164 */         par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("There are " + num_active_chunks + " chunks loaded").setColor(EnumChatFormatting.YELLOW));
/*      */         
/*  166 */         return 1;
/*      */       } 
/*  168 */       if (command == EnumCommand.commands) {
/*      */         
/*  170 */         player.sendPacket(new Packet85SimpleSignal(EnumSignal.list_commands));
/*  171 */         return 1;
/*      */       } 
/*  173 */       if (command == EnumCommand.skills) {
/*      */         
/*  175 */         if (world.areSkillsEnabled()) {
/*      */           
/*  177 */           StringBuffer sb = new StringBuffer("Available skills are: ");
/*      */           
/*  179 */           sb.append(Skill.getSkillsString(-1, false, ", "));
/*      */           
/*  181 */           par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(sb.toString()).setColor(EnumChatFormatting.YELLOW));
/*  182 */           par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Type /skill <skill> for more information").setColor(EnumChatFormatting.GRAY));
/*      */         }
/*      */         else {
/*      */           
/*  186 */           par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Skills are not enabled").setColor(EnumChatFormatting.RED));
/*      */         } 
/*      */         
/*  189 */         return 1;
/*      */       } 
/*  191 */       if (par2Str.startsWith("skill ")) {
/*      */         
/*  193 */         if (world.areSkillsEnabled()) {
/*      */           
/*  195 */           String skill_name = par2Str.substring(6);
/*      */           
/*  197 */           Skill skill = Skill.getByLocalizedName(skill_name, false);
/*      */           
/*  199 */           if (skill == null) {
/*  200 */             skill = Skill.getByLocalizedName(skill_name, true);
/*      */           }
/*  202 */           if (skill == null) {
/*      */             
/*  204 */             par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Invalid skill name \"" + skill_name + "\"").setColor(EnumChatFormatting.RED));
/*      */           }
/*      */           else {
/*      */             
/*  208 */             par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(skill.getLocalizedDescription()).setColor(EnumChatFormatting.YELLOW));
/*      */             
/*  210 */             if (player != null)
/*      */             {
/*  212 */               if (player.hasSkill(skill)) {
/*  213 */                 par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Type /abandon <skill> to forget a skill").setColor(EnumChatFormatting.GRAY));
/*      */               } else {
/*  215 */                 par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Type /learn <skill> to gain a skill").setColor(EnumChatFormatting.GRAY));
/*      */               } 
/*      */             }
/*      */           } 
/*      */         } else {
/*      */           
/*  221 */           par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Skills are not enabled").setColor(EnumChatFormatting.RED));
/*      */         } 
/*      */         
/*  224 */         return 1;
/*      */       } 
/*  226 */       if (Minecraft.inDevMode() && par2Str.equals("skills on")) {
/*      */         
/*  228 */         par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(world.areSkillsEnabled() ? "Skills are already enabled" : "Skills are now enabled").setColor(EnumChatFormatting.YELLOW));
/*      */         
/*  230 */         if (!world.worldInfo.areSkillsEnabled()) {
/*      */           
/*  232 */           world.worldInfo.setSkillsEnabled(true);
/*  233 */           player.sendPacket((new Packet85SimpleSignal(EnumSignal.skills)).setBoolean(true));
/*      */         } 
/*      */         
/*  236 */         return 1;
/*      */       } 
/*  238 */       if (Minecraft.inDevMode() && par2Str.equals("skills off")) {
/*      */         
/*  240 */         par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(world.areSkillsEnabled() ? "Skills are now disabled" : "Skills are already disabled").setColor(EnumChatFormatting.YELLOW));
/*      */         
/*  242 */         if (world.worldInfo.areSkillsEnabled()) {
/*      */           
/*  244 */           world.worldInfo.setSkillsEnabled(false);
/*  245 */           player.sendPacket((new Packet85SimpleSignal(EnumSignal.skills)).setBoolean(false));
/*      */         } 
/*      */         
/*  248 */         return 1;
/*      */       } 
/*  250 */       if ("mute".equals(par2Str)) {
/*      */         
/*  252 */         player.sendPacket(new Packet85SimpleSignal(EnumSignal.toggle_mute));
/*      */         
/*  254 */         return 1;
/*      */       } 
/*  256 */       if ((player == null || Minecraft.inDevMode()) && par2Str.equals("time")) {
/*      */         
/*  258 */         StringBuffer sb = new StringBuffer("Time Progressing? ");
/*      */         int i;
/*  260 */         for (i = 0; i < mc_server.worldServers.length; i++) {
/*      */           
/*  262 */           if (i > 0) {
/*  263 */             sb.append(", ");
/*      */           }
/*  265 */           WorldServer world_server = mc_server.worldServers[i];
/*      */           
/*  267 */           sb.append(world_server.getDimensionName());
/*  268 */           sb.append("=");
/*  269 */           sb.append(StringHelper.capitalize(StringHelper.yesOrNo(world_server.shouldTimeProgress())));
/*      */         } 
/*      */         
/*  272 */         par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(sb.toString()).setColor(EnumChatFormatting.YELLOW));
/*      */         
/*  274 */         sb = new StringBuffer("Random Block Ticks? ");
/*      */         
/*  276 */         for (i = 0; i < mc_server.worldServers.length; i++) {
/*      */           
/*  278 */           if (i > 0) {
/*  279 */             sb.append(", ");
/*      */           }
/*  281 */           WorldServer world_server = mc_server.worldServers[i];
/*      */           
/*  283 */           sb.append(world_server.getDimensionName());
/*  284 */           sb.append("=");
/*  285 */           sb.append(StringHelper.capitalize(StringHelper.yesOrNo(world_server.shouldRandomBlockTicksBePerformed())));
/*      */         } 
/*      */         
/*  288 */         par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(sb.toString()).setColor(EnumChatFormatting.YELLOW));
/*      */         
/*  290 */         sb = new StringBuffer("Time Forwarding? ");
/*      */         
/*  292 */         for (i = 0; i < mc_server.worldServers.length; i++) {
/*      */           
/*  294 */           if (i > 0) {
/*  295 */             sb.append(", ");
/*      */           }
/*  297 */           WorldServer world_server = mc_server.worldServers[i];
/*      */           
/*  299 */           sb.append(world_server.getDimensionName());
/*  300 */           sb.append("=");
/*  301 */           sb.append(StringHelper.capitalize(StringHelper.yesOrNo(!world_server.shouldTimeForwardingBeSkipped())));
/*      */         } 
/*      */         
/*  304 */         par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(sb.toString()).setColor(EnumChatFormatting.YELLOW));
/*      */         
/*  306 */         sb = new StringBuffer("Total World Time: ");
/*      */         
/*  308 */         for (i = 0; i < mc_server.worldServers.length; i++) {
/*      */           
/*  310 */           if (i > 0) {
/*  311 */             sb.append(", ");
/*      */           }
/*  313 */           WorldServer world_server = mc_server.worldServers[i];
/*      */           
/*  315 */           sb.append(world_server.getDimensionName());
/*  316 */           sb.append("=");
/*  317 */           sb.append(world_server.getTotalWorldTime());
/*      */         } 
/*      */         
/*  320 */         par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(sb.toString()).setColor(EnumChatFormatting.YELLOW));
/*      */         
/*  322 */         return 1;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  384 */       if ((player == null || Minecraft.inDevMode()) && par2Str.equals("village")) {
/*      */         
/*  386 */         par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Village Conditions: " + mc_server.getVillageConditions()).setColor(EnumChatFormatting.YELLOW));
/*      */         
/*  388 */         return 1;
/*      */       } 
/*  390 */       if (player == null) {
/*      */ 
/*      */ 
/*      */         
/*  394 */         if (command != EnumCommand.version)
/*      */         {
/*      */ 
/*      */           
/*  398 */           if (Minecraft.inDevMode())
/*      */           {
/*  400 */             if (par2Str.equals("hour"))
/*      */             {
/*  402 */               mc_server.addTotalTimeForAllWorlds(1000);
/*  403 */               par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("The time is now " + world.getHourOfDayAMPM()).setColor(EnumChatFormatting.YELLOW));
/*      */               
/*  405 */               return 1;
/*      */             }
/*      */           
/*      */           }
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/*  413 */         if (command == EnumCommand.xp) {
/*      */           
/*  415 */           par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("You have " + player.experience + " experience").setColor(EnumChatFormatting.YELLOW));
/*  416 */           return 1;
/*      */         } 
/*  418 */         if (command == EnumCommand.syncpos) {
/*      */           
/*  420 */           player.sendPacket((new Packet85SimpleSignal(EnumSignal.sync_pos)).setExactPosition(player.posX, player.getEyePosY(), player.posZ));
/*  421 */           return 1;
/*      */         } 
/*  423 */         if (par2Str.equals("pushout")) {
/*      */           
/*  425 */           if (player.isNearToBlock(Block.bed, 2, 2) || Minecraft.inDevMode()) {
/*  426 */             player.try_push_out_of_blocks = true;
/*      */           } else {
/*  428 */             par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("This command only works if you are near a bed").setColor(EnumChatFormatting.YELLOW));
/*      */           } 
/*  430 */           return 1;
/*      */         } 
/*  432 */         if (command == EnumCommand.ground) {
/*      */           
/*  434 */           double pos_y = player.posY;
/*  435 */           int y = (int)pos_y;
/*  436 */           double pos_y_fraction = pos_y - y;
/*      */           
/*  438 */           if (pos_y_fraction > 0.8999999761581421D) {
/*  439 */             y++;
/*      */           }
/*  441 */           if (!world.isAirOrPassableBlock(player.getBlockPosX(), y - 1, player.getBlockPosZ(), true)) {
/*  442 */             player.setPositionAndUpdate(player.posX, y, player.posZ);
/*      */           }
/*  444 */           return 1;
/*      */         } 
/*  446 */         if (command == EnumCommand.stats) {
/*      */           
/*  448 */           player.sendPacket(EntityStatsDump.generatePacketFor(player));
/*      */           
/*  450 */           return 1;
/*      */         } 
/*  452 */         if (world.areSkillsEnabled() && par2Str.startsWith("learn ")) {
/*      */           
/*  454 */           String skill_name = par2Str.substring(6);
/*      */           
/*  456 */           Skill skill = Skill.getByLocalizedName(skill_name, false);
/*      */           
/*  458 */           if (skill == null) {
/*  459 */             skill = Skill.getByLocalizedName(skill_name, true);
/*      */           }
/*  461 */           if (skill == null) {
/*      */             
/*  463 */             par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Invalid skill name \"" + skill_name + "\"").setColor(EnumChatFormatting.RED));
/*  464 */             return 1;
/*      */           } 
/*      */           
/*  467 */           if (player.hasSkill(skill)) {
/*      */             
/*  469 */             par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("You are already " + StringHelper.aOrAn(skill.getLocalizedName(true))).setColor(EnumChatFormatting.YELLOW));
/*  470 */             return 1;
/*      */           } 
/*      */           
/*  473 */           int num_skills = player.getNumSkills();
/*      */           
/*  475 */           int level_cost = 5 * (num_skills + 1);
/*  476 */           int xp_cost = EntityPlayer.getExperienceRequired(level_cost);
/*      */           
/*  478 */           if (player.experience < xp_cost) {
/*      */             
/*  480 */             par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("You must reach level " + level_cost + " before learning " + ((num_skills == 0) ? "a" : ((num_skills == 1) ? "a second" : ((num_skills == 2) ? "a third" : ((num_skills == 3) ? "a fourth" : ((num_skills == 4) ? "a fifth" : "another"))))) + " profession").setColor(EnumChatFormatting.YELLOW));
/*  481 */             return 1;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  499 */           if (player.last_skill_learned_on_day >= world.getDayOfWorld()) {
/*      */ 
/*      */             
/*  502 */             par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("You can only learn one skill per day").setColor(EnumChatFormatting.YELLOW));
/*  503 */             return 1;
/*      */           } 
/*      */           
/*  506 */           player.addSkill(skill);
/*      */           
/*  508 */           player.addExperience(-xp_cost);
/*      */           
/*  510 */           par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("You are now " + (player.hasSkills() ? StringHelper.aOrAn(player.getSkillsString(true)) : StatCollector.translateToLocal("skill.none").toLowerCase())).setColor(EnumChatFormatting.YELLOW));
/*      */           
/*  512 */           player.last_skill_learned_on_day = world.getDayOfWorld();
/*      */           
/*  514 */           return 1;
/*      */         } 
/*  516 */         if (world.areSkillsEnabled() && par2Str.startsWith("abandon ")) {
/*      */           
/*  518 */           String skill_name = par2Str.substring(8);
/*      */           
/*  520 */           Skill skill = Skill.getByLocalizedName(skill_name, false);
/*      */           
/*  522 */           if (skill == null) {
/*  523 */             skill = Skill.getByLocalizedName(skill_name, true);
/*      */           }
/*  525 */           if (skill == null) {
/*      */             
/*  527 */             par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Invalid skill name \"" + skill_name + "\"").setColor(EnumChatFormatting.RED));
/*      */           }
/*  529 */           else if (player.hasSkill(skill)) {
/*      */             
/*  531 */             player.removeSkill(skill);
/*      */             
/*  533 */             world.playSoundAtEntity(player, "imported.random.level_drain");
/*      */             
/*  535 */             par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("You are now " + (player.hasSkills() ? StringHelper.aOrAn(player.getSkillsString(true)) : StatCollector.translateToLocal("skill.none").toLowerCase())).setColor(EnumChatFormatting.YELLOW));
/*      */           }
/*      */           else {
/*      */             
/*  539 */             par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("You don't have that skill").setColor(EnumChatFormatting.YELLOW));
/*      */           } 
/*      */           
/*  542 */           return 1;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  549 */         if (par2Str.equals("hunger")) {
/*      */ 
/*      */ 
/*      */           
/*  553 */           RaycastCollision rc = player.getSelectedObject(1.0F, false);
/*      */           
/*  555 */           if (rc != null && rc.isEntity() && rc.getEntityHit() instanceof EntityLivestock) {
/*      */             
/*  557 */             EntityLivestock livestock = (EntityLivestock)rc.getEntityHit();
/*      */             
/*  559 */             livestock.setFood(0.0F);
/*      */             
/*  561 */             return 1;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  566 */           par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Your metabolic hunger rate is x" + player.getWetnessAndMalnourishmentHungerMultiplier()).setColor(EnumChatFormatting.YELLOW));
/*  567 */           return 1;
/*      */         } 
/*  569 */         if (isUserPrivileged(player)) {
/*      */           
/*  571 */           if (par2Str.startsWith("decoy ")) {
/*      */             
/*  573 */             String entity_class_name = par2Str.substring(6).replaceAll(" ", "");
/*      */             
/*  575 */             Entity entity = EntityList.getEntityInstanceByNameCaseInsensitive(entity_class_name, world);
/*      */             
/*  577 */             if (entity instanceof EntityLiving) {
/*  578 */               world.spawnDecoy(entity.getClass(), player);
/*      */             } else {
/*  580 */               par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Invalid entity name \"" + par2Str.substring(6) + "\"").setColor(EnumChatFormatting.RED));
/*      */             } 
/*  582 */             return 1;
/*      */           } 
/*  584 */           if (par2Str.equals("decoy")) {
/*      */             
/*  586 */             List<EntityLiving> entities = world.getEntitiesWithinAABB(EntityLiving.class, player.boundingBox.expand(2.0D, 2.0D, 2.0D));
/*      */             
/*  588 */             for (int i = 0; i < entities.size(); i++) {
/*      */               
/*  590 */               EntityLiving entity_living = entities.get(i);
/*      */               
/*  592 */               if (entity_living.isDecoy()) {
/*  593 */                 entity_living.setDead();
/*      */               }
/*      */             } 
/*  596 */             return 1;
/*      */           } 
/*  598 */           if (par2Str.equals("grace")) {
/*      */             
/*  600 */             if (DedicatedServer.disconnection_penalty_enabled) {
/*      */               
/*  602 */               DedicatedServer.disconnection_penalty_enabled = false;
/*  603 */               par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Disconnection penalties are now disabled").setColor(EnumChatFormatting.YELLOW));
/*      */             }
/*      */             else {
/*      */               
/*  607 */               DedicatedServer.disconnection_penalty_enabled = true;
/*  608 */               par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Disconnection penalties are now enabled").setColor(EnumChatFormatting.YELLOW));
/*      */             } 
/*      */             
/*  611 */             return 1;
/*      */           } 
/*  613 */           if (Minecraft.inDevMode() || player.isZevimrgvInTournament()) {
/*      */             
/*  615 */             if (par2Str.startsWith("level ")) {
/*      */               
/*  617 */               int level = MathHelper.clamp_int(Integer.valueOf(par2Str.substring(6)).intValue(), -40, EntityPlayer.getHighestPossibleLevel());
/*      */               
/*  619 */               int experience = ServerPlayer.getExperienceRequired(level);
/*      */               
/*  621 */               player.addExperience(experience - player.experience);
/*      */               
/*  623 */               return 1;
/*      */             } 
/*  625 */             if (par2Str.equals("heal")) {
/*      */               
/*  627 */               player.setHealth(player.getMaxHealth());
/*      */               
/*  629 */               return 1;
/*      */             } 
/*  631 */             if (par2Str.startsWith("health ")) {
/*      */               
/*  633 */               int health = Integer.valueOf(par2Str.substring(7)).intValue();
/*      */               
/*  635 */               player.setHealth(health);
/*      */               
/*  637 */               return 1;
/*      */             } 
/*  639 */             if (par2Str.equals("fill")) {
/*      */ 
/*      */ 
/*      */               
/*  643 */               player.foodStats.addSatiation(20);
/*  644 */               player.foodStats.addNutrition(20);
/*      */               
/*  646 */               return 1;
/*      */             } 
/*  648 */             if (par2Str.equals("nourish")) {
/*      */               
/*  650 */               player.setProtein(160000);
/*  651 */               player.setEssentialFats(160000);
/*  652 */               player.setPhytonutrients(160000);
/*      */               
/*  654 */               return 1;
/*      */             } 
/*  656 */             if (par2Str.equals("starve")) {
/*      */               
/*  658 */               player.foodStats.setSatiation(0, false);
/*  659 */               player.foodStats.setNutrition(0, false);
/*      */               
/*  661 */               return 1;
/*      */             } 
/*  663 */             if (par2Str.startsWith("satiation ")) {
/*      */               
/*  665 */               int satiation = Integer.valueOf(par2Str.substring(10)).intValue();
/*      */               
/*  667 */               player.foodStats.setSatiation(satiation, true);
/*      */               
/*  669 */               return 1;
/*      */             } 
/*  671 */             if (par2Str.startsWith("nutrition ")) {
/*      */               
/*  673 */               int nutrition = Integer.valueOf(par2Str.substring(10)).intValue();
/*      */               
/*  675 */               player.foodStats.setNutrition(nutrition, true);
/*      */               
/*  677 */               return 1;
/*      */             } 
/*  679 */             if (par2Str.equals("night vision")) {
/*      */ 
/*      */ 
/*      */               
/*  683 */               player.sendPacket(new Packet85SimpleSignal(EnumSignal.toggle_night_vision_override));
/*      */               
/*  685 */               return 1;
/*      */             } 
/*  687 */             if (par2Str.equals("test")) {
/*      */               
/*  689 */               player.in_test_mode = !player.in_test_mode;
/*      */               
/*  691 */               par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Test mode toggled " + (player.in_test_mode ? "on" : "off")).setColor(EnumChatFormatting.YELLOW));
/*      */               
/*  693 */               return 1;
/*      */             } 
/*  695 */             if (par2Str.equals("bolt")) {
/*      */               
/*  697 */               if (world.isThundering(true) && world.canLightningStrikeAt(player.getBlockPosX(), player.getBlockPosY(), player.getBlockPosZ())) {
/*  698 */                 world.addWeatherEffect(new EntityLightningBolt(world, player.posX, player.posY, player.posZ));
/*      */               } else {
/*  700 */                 par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Lightning cannot strike your position").setColor(EnumChatFormatting.YELLOW));
/*      */               } 
/*  702 */               return 1;
/*      */             } 
/*  704 */             if (par2Str.equals("damage armor")) {
/*      */               
/*  706 */               player.inventory.tryDamageArmor(DamageSource.generic, 10.0F, null);
/*  707 */               return 1;
/*      */             } 
/*  709 */             if (par2Str.startsWith("xp ")) {
/*      */               
/*  711 */               int experience = Integer.valueOf(par2Str.substring(3)).intValue();
/*      */               
/*  713 */               player.addExperience(experience - player.experience);
/*      */               
/*  715 */               return 1;
/*      */             } 
/*  717 */             if (par2Str.startsWith("day ")) {
/*      */               
/*  719 */               int new_total_world_time = (Integer.valueOf(par2Str.substring(4)).intValue() - 1) * 24000;
/*      */               
/*  721 */               world.setTotalWorldTime(new_total_world_time, true);
/*      */               
/*  723 */               return 1;
/*      */             } 
/*  725 */             if (par2Str.equals("end")) {
/*      */               
/*  727 */               if (world.provider.dimensionId != 1)
/*      */               {
/*      */                 
/*  730 */                 player.travelToDimension(1);
/*      */               }
/*  732 */               return 1;
/*      */             } 
/*  734 */             if (par2Str.startsWith("metadata ")) {
/*      */               
/*  736 */               int metadata = Integer.valueOf(par2Str.substring(9)).intValue();
/*      */               
/*  738 */               RaycastCollision rc = player.getSelectedObject(1.0F, true);
/*      */               
/*  740 */               if (rc != null && rc.isBlock())
/*      */               {
/*  742 */                 if (rc.getNeighborOfBlockHitMaterial().isLiquid()) {
/*  743 */                   world.setBlockMetadataWithNotify(rc.neighbor_block_x, rc.neighbor_block_y, rc.neighbor_block_z, metadata, 3);
/*      */                 } else {
/*  745 */                   world.setBlockMetadataWithNotify(rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, metadata, 3);
/*      */                 } 
/*      */               }
/*  748 */               return 1;
/*      */             } 
/*  750 */             if (par2Str.equals("damage item")) {
/*      */               
/*  752 */               ItemStack item_stack = player.getHeldItemStack();
/*      */               
/*  754 */               if (item_stack != null) {
/*  755 */                 item_stack.applyRandomItemStackDamageForChest();
/*      */               }
/*  757 */               return 1;
/*      */             } 
/*  759 */             if (par2Str.startsWith("damage item ")) {
/*      */               
/*  761 */               int amount = Integer.valueOf(par2Str.substring(12)).intValue();
/*      */               
/*  763 */               player.tryDamageHeldItem(DamageSource.generic, amount);
/*      */               
/*  765 */               return 1;
/*      */             } 
/*  767 */             if (par2Str.equals("repair item")) {
/*      */               
/*  769 */               ItemStack held_item_stack = player.getHeldItemStack();
/*      */               
/*  771 */               if (held_item_stack != null && held_item_stack.isItemDamaged()) {
/*  772 */                 held_item_stack.setItemDamage(0);
/*      */               }
/*  774 */               return 1;
/*      */             } 
/*  776 */             if (par2Str.equals("thirst")) {
/*      */               
/*  778 */               RaycastCollision rc = player.getSelectedObject(1.0F, false);
/*      */               
/*  780 */               if (rc != null && rc.isEntity() && rc.getEntityHit() instanceof EntityLivestock) {
/*      */                 
/*  782 */                 EntityLivestock livestock = (EntityLivestock)rc.getEntityHit();
/*      */                 
/*  784 */                 livestock.setWater(0.0F);
/*      */               } 
/*      */               
/*  787 */               return 1;
/*      */             } 
/*  789 */             if (par2Str.equals("grow")) {
/*      */               
/*  791 */               List<EntityAgeable> entities = world.getEntitiesWithinAABB(EntityAgeable.class, player.boundingBox.expand(4.0D, 2.0D, 4.0D));
/*      */               
/*  793 */               for (int i = 0; i < entities.size(); i++) {
/*      */                 
/*  795 */                 EntityAgeable entity = entities.get(i);
/*      */                 
/*  797 */                 if (entity.isChild()) {
/*  798 */                   entity.setGrowingAge(0);
/*      */                 }
/*      */               } 
/*  801 */               return 1;
/*      */             } 
/*  803 */             if (par2Str.equals("tame")) {
/*      */               
/*  805 */               RaycastCollision rc = player.getSelectedObject(1.0F, false);
/*      */               
/*  807 */               if (rc != null && rc.isEntity() && rc.getEntityHit() instanceof EntityHorse) {
/*      */                 
/*  809 */                 EntityHorse horse = (EntityHorse)rc.getEntityHit();
/*      */                 
/*  811 */                 horse.setTamedBy(player);
/*      */               } 
/*      */               
/*  814 */               return 1;
/*      */             } 
/*  816 */             if (par2Str.equals("clear books")) {
/*      */               
/*  818 */               player.referenced_books_read.clear();
/*      */               
/*  820 */               return 1;
/*      */             } 
/*  822 */             if (par2Str.startsWith("clear ")) {
/*      */               boolean include_permanent_blocks;
/*  824 */               int x = player.getBlockPosX();
/*  825 */               int y = player.getBlockPosY();
/*  826 */               int z = player.getBlockPosZ();
/*      */ 
/*      */ 
/*      */               
/*  830 */               if (par2Str.endsWith("!")) {
/*      */                 
/*  832 */                 par2Str = StringHelper.stripTrailing("!", par2Str);
/*  833 */                 include_permanent_blocks = true;
/*      */               }
/*      */               else {
/*      */                 
/*  837 */                 include_permanent_blocks = false;
/*      */               } 
/*      */               
/*  840 */               int radius = Integer.valueOf(par2Str.substring(6)).intValue();
/*      */               
/*  842 */               for (int dx = -radius; dx <= radius; dx++) {
/*      */                 
/*  844 */                 for (int dy = -radius; dy <= radius; dy++) {
/*      */                   
/*  846 */                   for (int dz = -radius; dz <= radius; dz++) {
/*      */                     
/*  848 */                     if (include_permanent_blocks) {
/*      */                       
/*  850 */                       world.setBlockToAir(x + dx, y + dy, z + dz);
/*      */                     }
/*      */                     else {
/*      */                       
/*  854 */                       Block block = world.getBlock(x + dx, y + dy, z + dz);
/*      */                       
/*  856 */                       if (block != Block.bedrock && block != Block.mantleOrCore) {
/*  857 */                         world.setBlockToAir(x + dx, y + dy, z + dz);
/*      */                       }
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*      */               } 
/*  863 */               return 1;
/*      */             } 
/*  865 */             if (par2Str.startsWith("timber")) {
/*      */               
/*  867 */               int x = player.getBlockPosX();
/*  868 */               int y = player.getBlockPosY();
/*  869 */               int z = player.getBlockPosZ();
/*      */               
/*  871 */               int radius = 10;
/*      */               
/*  873 */               for (int dx = -radius; dx <= radius; dx++) {
/*      */                 
/*  875 */                 for (int dy = -radius; dy <= radius; dy++) {
/*      */                   
/*  877 */                   for (int dz = -radius; dz <= radius; dz++) {
/*      */                     
/*  879 */                     Block block = world.getBlock(x + dx, y + dy, z + dz);
/*      */                     
/*  881 */                     if (block == Block.wood || block == Block.leaves || block == Block.vine) {
/*  882 */                       world.setBlockToAir(x + dx, y + dy, z + dz);
/*      */                     }
/*      */                   } 
/*      */                 } 
/*      */               } 
/*  887 */               return 1;
/*      */             } 
/*  889 */             if (par2Str.startsWith("fill ")) {
/*      */               
/*  891 */               int x = player.getBlockPosX();
/*  892 */               int y = player.getBlockPosY();
/*  893 */               int z = player.getBlockPosZ();
/*      */               
/*  895 */               int radius = Integer.valueOf(par2Str.substring(5)).intValue();
/*      */               
/*  897 */               for (int dx = -radius; dx <= radius; dx++) {
/*      */ 
/*      */                 
/*  900 */                 for (int dy = 1; dy <= radius; dy++) {
/*      */                   
/*  902 */                   for (int dz = -radius; dz <= radius; dz++) {
/*  903 */                     world.setBlock(x + dx, y + dy, z + dz, Block.woodSingleSlab.blockID, 8, 3);
/*      */                   }
/*      */                 } 
/*      */               } 
/*  907 */               return 1;
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  914 */             if (par2Str.equals("mobs")) {
/*      */               
/*  916 */               par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("There are " + world.countEntities(IMob.class) + " mobs loaded").setColor(EnumChatFormatting.YELLOW));
/*  917 */               return 1;
/*      */             } 
/*  919 */             if (par2Str.equals("peaceful")) {
/*      */               
/*  921 */               (player.getWorldServer()).decreased_hostile_mob_spawning_counter = 4000;
/*  922 */               return 1;
/*      */             } 
/*  924 */             if (par2Str.equals("hostile")) {
/*      */               
/*  926 */               (player.getWorldServer()).increased_hostile_mob_spawning_counter = 2000;
/*  927 */               return 1;
/*      */             } 
/*  929 */             if (par2Str.equals("malnourish")) {
/*      */               
/*  931 */               player.setProtein(0);
/*  932 */               player.setEssentialFats(0);
/*  933 */               player.setPhytonutrients(0);
/*      */               
/*  935 */               return 1;
/*      */             } 
/*  937 */             if (par2Str.startsWith("protein ")) {
/*      */               
/*  939 */               player.setProtein(Integer.valueOf(par2Str.substring(8)).intValue());
/*  940 */               return 1;
/*      */             } 
/*  942 */             if (par2Str.startsWith("essential fats ")) {
/*      */               
/*  944 */               player.setEssentialFats(Integer.valueOf(par2Str.substring(15)).intValue());
/*  945 */               return 1;
/*      */             } 
/*  947 */             if (par2Str.startsWith("phytonutrients ")) {
/*      */               
/*  949 */               player.setPhytonutrients(Integer.valueOf(par2Str.substring(15)).intValue());
/*  950 */               return 1;
/*      */             } 
/*  952 */             if (par2Str.startsWith("insulin ")) {
/*      */               
/*  954 */               player.setInsulinResistance(Integer.valueOf(par2Str.substring(8)).intValue());
/*  955 */               return 1;
/*      */             } 
/*  957 */             if (par2Str.equals("slaughter")) {
/*      */               
/*  959 */               List<Entity> loadedEntities = world.loadedEntityList;
/*      */               
/*  961 */               int num_animals_slaughtered = 0;
/*  962 */               int num_squids_slaughtered = 0;
/*  963 */               int num_bats_slaughtered = 0;
/*      */               
/*  965 */               for (int i = 0; i < loadedEntities.size(); i++) {
/*      */                 
/*  967 */                 Entity entity = loadedEntities.get(i);
/*      */                 
/*  969 */                 Class<?> c = entity.getClass();
/*      */                 
/*  971 */                 if (EntityAnimal.class.isAssignableFrom(c) || EntityWaterMob.class.isAssignableFrom(c) || EntityAmbientCreature.class.isAssignableFrom(c)) {
/*      */                   
/*  973 */                   if (entity instanceof EntityHorse) {
/*      */                     
/*  975 */                     EntityHorse entity_horse = (EntityHorse)entity;
/*      */                     
/*  977 */                     if (entity_horse.isHorseSaddled()) {
/*      */                       continue;
/*      */                     }
/*      */                   } 
/*      */ 
/*      */                   
/*  983 */                   entity.setDead();
/*      */                   
/*  985 */                   if (EntityAnimal.class.isAssignableFrom(c)) {
/*  986 */                     num_animals_slaughtered++;
/*  987 */                   } else if (EntityWaterMob.class.isAssignableFrom(c)) {
/*  988 */                     num_squids_slaughtered++;
/*  989 */                   } else if (EntityAmbientCreature.class.isAssignableFrom(c)) {
/*  990 */                     num_bats_slaughtered++;
/*      */                   } 
/*      */                 }  continue;
/*      */               } 
/*  994 */               par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(num_animals_slaughtered + " animals were slaughtered, " + num_squids_slaughtered + " squids and " + num_bats_slaughtered + " bats").setColor(EnumChatFormatting.YELLOW));
/*      */               
/*  996 */               return 1;
/*      */             } 
/*      */             
/*  999 */             if (par2Str.equals("spawning")) {
/*      */               
/* 1001 */               par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Random mob spawning is now " + (spawning_disabled ? "enabled" : "disabled")).setColor(EnumChatFormatting.YELLOW));
/*      */               
/* 1003 */               spawning_disabled = !spawning_disabled;
/*      */               
/* 1005 */               return 1;
/*      */             } 
/* 1007 */             if (par2Str.equals("killall")) {
/*      */               
/* 1009 */               List<Entity> loadedEntities = world.loadedEntityList;
/*      */               
/* 1011 */               int num_killed = 0;
/*      */               
/* 1013 */               for (int i = 0; i < loadedEntities.size(); i++) {
/*      */                 
/* 1015 */                 Entity entity = loadedEntities.get(i);
/*      */                 
/* 1017 */                 if (EntityLiving.class.isAssignableFrom(entity.getClass())) {
/*      */                   
/* 1019 */                   entity.setDead();
/* 1020 */                   num_killed++;
/*      */                 } 
/*      */               } 
/*      */               
/* 1024 */               par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(num_killed + " living entities were killed").setColor(EnumChatFormatting.YELLOW));
/*      */               
/* 1026 */               return 1;
/*      */             } 
/* 1028 */             if (par2Str.equals("killmobs")) {
/*      */               
/* 1030 */               List<Entity> loadedEntities = world.loadedEntityList;
/*      */               
/* 1032 */               int num_killed = 0;
/*      */               
/* 1034 */               for (int i = 0; i < loadedEntities.size(); i++) {
/*      */                 
/* 1036 */                 Entity entity = loadedEntities.get(i);
/*      */                 
/* 1038 */                 if (IMob.class.isAssignableFrom(entity.getClass())) {
/*      */                   
/* 1040 */                   if (entity instanceof EntityWitch) {
/* 1041 */                     world.removeCursesForWitch((EntityWitch)entity);
/*      */                   }
/* 1043 */                   entity.setDead();
/* 1044 */                   num_killed++;
/*      */                 } 
/*      */               } 
/*      */               
/* 1048 */               par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(num_killed + " living entities were killed").setColor(EnumChatFormatting.YELLOW));
/*      */               
/* 1050 */               return 1;
/*      */             } 
/* 1052 */             if (par2Str.equals("nomobs")) {
/*      */               
/* 1054 */               if (!spawning_disabled) {
/* 1055 */                 executeCommand(par1ICommandSender, "spawning", permission_override);
/*      */               }
/* 1057 */               executeCommand(par1ICommandSender, "killmobs", permission_override);
/*      */               
/* 1059 */               return 1;
/*      */             } 
/* 1061 */             if (par2Str.equals("recall") && player.worldObj.provider.dimensionId == 0) {
/*      */               
/* 1063 */               WorldInfo info = world.getWorldInfo();
/*      */               
/* 1065 */               player.travelInsideDimension((info.getSpawnX() + 0.5F), (info.getSpawnY() + 0.1F), (info.getSpawnZ() + 0.5F));
/*      */               
/* 1067 */               return 1;
/*      */             } 
/* 1069 */             if (par2Str.equals("corrupt")) {
/*      */               
/* 1071 */               (player.getChunkFromPosition()).invalidate_checksum = true;
/* 1072 */               (player.getChunkFromPosition()).isModified = true;
/*      */               
/* 1074 */               return 1;
/*      */             } 
/* 1076 */             if (par2Str.equals("crash")) {
/*      */               
/* 1078 */               Object o = new Object();
/*      */               
/* 1080 */               boolean b = true;
/*      */               
/* 1082 */               if (b) {
/* 1083 */                 o = null;
/*      */               }
/* 1085 */               System.out.println(o.toString());
/*      */               
/* 1087 */               return 1;
/*      */             } 
/* 1089 */             if (par2Str.startsWith("achievement ")) {
/*      */               
/* 1091 */               boolean recursive = par2Str.endsWith("!");
/*      */               
/* 1093 */               if (recursive) {
/* 1094 */                 par2Str = StringHelper.left(par2Str, par2Str.length() - 1);
/*      */               }
/* 1096 */               int id = Integer.valueOf(par2Str.substring(12)).intValue();
/*      */               
/* 1098 */               Achievement achievement = AchievementList.getAchievementForId(id);
/*      */               
/* 1100 */               if (achievement == null) {
/*      */                 
/* 1102 */                 par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Invalid achievement id \"" + par2Str.substring(12) + "\"").setColor(EnumChatFormatting.RED));
/* 1103 */                 return 1;
/*      */               } 
/*      */               
/* 1106 */               if (recursive) {
/*      */                 
/* 1108 */                 List<Achievement> achievements = new ArrayList();
/*      */                 
/* 1110 */                 while (achievement != null) {
/*      */                   
/* 1112 */                   achievements.add(achievement);
/* 1113 */                   achievement = achievement.parentAchievement;
/*      */                 } 
/*      */ 
/*      */ 
/*      */                 
/* 1118 */                 for (int i = achievements.size() - 1; i >= 0; i--) {
/* 1119 */                   player.triggerAchievement(achievements.get(i));
/*      */                 }
/*      */               } 
/* 1122 */               player.triggerAchievement(achievement);
/*      */               
/* 1124 */               return 1;
/*      */             } 
/* 1126 */             if (par2Str.equals("books")) {
/*      */               
/* 1128 */               for (int i = 1; i <= 9; i++) {
/*      */                 
/* 1130 */                 ItemStack book = ItemReferencedBook.generateBook(i);
/*      */                 
/* 1132 */                 if (!world.worldInfo.hasSignatureBeenAdded(book.getSignature())) {
/*      */                   
/* 1134 */                   world.worldInfo.addSignature(book.getSignature());
/* 1135 */                   player.inventory.addItemStackToInventoryOrDropIt(book);
/*      */                 } 
/*      */               } 
/*      */               
/* 1139 */               return 1;
/*      */             } 
/* 1141 */             if (par2Str.equals("layer air")) {
/*      */               
/* 1143 */               int x = player.getBlockPosX();
/* 1144 */               int y = player.getFootBlockPosY() - 1;
/* 1145 */               int z = player.getBlockPosZ();
/*      */               
/* 1147 */               int range = 64;
/*      */               
/* 1149 */               for (int dx = -range; dx <= range; dx++) {
/*      */                 
/* 1151 */                 for (int dz = -range; dz <= range; dz++) {
/* 1152 */                   world.setBlock(x + dx, y, z + dz, 0, 0, 2);
/*      */                 }
/*      */               } 
/* 1155 */               world.setBlock(x, y, z, Block.obsidian.blockID);
/*      */               
/* 1157 */               return 1;
/*      */             } 
/* 1159 */             if (par2Str.startsWith("layer ")) {
/*      */               Block block;
/* 1161 */               String param = par2Str.substring(6);
/*      */ 
/*      */               
/* 1164 */               if (param.isEmpty()) {
/* 1165 */                 block = null;
/* 1166 */               } else if (StringHelper.startsWithDigit(param)) {
/* 1167 */                 block = Block.getBlock(Integer.valueOf(param).intValue());
/* 1168 */               } else if (param.equalsIgnoreCase("grass")) {
/* 1169 */                 block = Block.grass;
/* 1170 */               } else if (param.equalsIgnoreCase("tall grass")) {
/* 1171 */                 block = Block.tallGrass;
/* 1172 */               } else if (param.equalsIgnoreCase("slab") || param.equalsIgnoreCase("wooden slab")) {
/* 1173 */                 block = Block.woodSingleSlab;
/* 1174 */               } else if (param.equalsIgnoreCase("stone slab")) {
/* 1175 */                 block = Block.stoneSingleSlab;
/* 1176 */               } else if (param.equalsIgnoreCase("stairs")) {
/* 1177 */                 block = Block.stairsWoodOak;
/*      */               } else {
/* 1179 */                 block = Block.getBlock(param);
/*      */               } 
/* 1181 */               if (block == null) {
/*      */                 
/* 1183 */                 par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Invalid block id \"" + param + "\"").setColor(EnumChatFormatting.RED));
/*      */               } else {
/*      */                 int metadata;
/*      */ 
/*      */ 
/*      */                 
/* 1189 */                 if (block == Block.tallGrass) {
/* 1190 */                   metadata = 1;
/*      */                 } else {
/* 1192 */                   metadata = 0;
/*      */                 } 
/* 1194 */                 int x = player.getBlockPosX();
/* 1195 */                 int y = player.getFootBlockPosY() - 1;
/* 1196 */                 int z = player.getBlockPosZ();
/*      */                 
/* 1198 */                 int range = 64;
/*      */                 
/* 1200 */                 for (int dx = -range; dx <= range; dx++) {
/*      */                   
/* 1202 */                   for (int dz = -range; dz <= range; dz++) {
/*      */ 
/*      */                     
/* 1205 */                     if (block.canBePlacedAt(world, x + dx, y, z + dz, metadata)) {
/* 1206 */                       world.setBlock(x + dx, y, z + dz, block.blockID, metadata, 2);
/*      */                     }
/*      */                   } 
/*      */                 } 
/* 1210 */                 world.setBlock(x, y, z, Block.obsidian.blockID);
/*      */               } 
/*      */               
/* 1213 */               return 1;
/*      */             } 
/* 1215 */             if (par2Str.startsWith("layermd ")) {
/*      */               
/* 1217 */               String param = par2Str.substring(8);
/* 1218 */               int metadata = Integer.valueOf(param).intValue();
/*      */               
/* 1220 */               int x = player.getBlockPosX();
/* 1221 */               int y = player.getFootBlockPosY() - 1;
/* 1222 */               int z = player.getBlockPosZ();
/*      */               
/* 1224 */               int range = 64;
/*      */               
/* 1226 */               for (int dx = -range; dx <= range; dx++) {
/*      */                 
/* 1228 */                 for (int dz = -range; dz <= range; dz++) {
/* 1229 */                   world.setBlockMetadataWithNotify(x + dx, y, z + dz, metadata, 2);
/*      */                 }
/*      */               } 
/* 1232 */               return 1;
/*      */             } 
/* 1234 */             if (par2Str.equals("noliquid")) {
/*      */               
/* 1236 */               int x = player.getBlockPosX();
/* 1237 */               int z = player.getBlockPosZ();
/* 1238 */               int y = player.getFootBlockPosY();
/*      */               
/* 1240 */               int range = 64;
/*      */               
/* 1242 */               for (int dx = -range; dx <= range; dx++) {
/*      */                 
/* 1244 */                 for (int dz = -range; dz <= range; dz++) {
/*      */                   
/* 1246 */                   for (int dy = -range; dy <= range; dy++) {
/*      */                     
/* 1248 */                     if (world.getBlockMaterial(x + dx, y + dy, z + dz).isLiquid()) {
/* 1249 */                       world.setBlock(x + dx, y + dy, z + dz, Block.stone.blockID, 0, 2);
/*      */                     }
/*      */                   } 
/*      */                 } 
/*      */               } 
/* 1254 */               return 1;
/*      */             } 
/* 1256 */             if (par2Str.equals("spawn animals")) {
/*      */               
/* 1258 */               int num_spawned = world.getAnimalSpawner().trySpawningPeacefulMobs(world, EnumCreatureType.animal);
/*      */               
/* 1260 */               par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(num_spawned + " animals were spawned").setColor(EnumChatFormatting.YELLOW));
/*      */               
/* 1262 */               return 1;
/*      */             } 
/* 1264 */             if (par2Str.equals("spawn squids")) {
/*      */               
/* 1266 */               int num_spawned = world.getAnimalSpawner().trySpawningPeacefulMobs(world, EnumCreatureType.aquatic);
/*      */               
/* 1268 */               par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(num_spawned + " squids were spawned").setColor(EnumChatFormatting.YELLOW));
/*      */               
/* 1270 */               return 1;
/*      */             } 
/* 1272 */             if (par2Str.equals("spawn bats")) {
/*      */               
/* 1274 */               int num_spawned = world.getAnimalSpawner().trySpawningPeacefulMobs(world, EnumCreatureType.ambient);
/*      */               
/* 1276 */               par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(num_spawned + " bats were spawned").setColor(EnumChatFormatting.YELLOW));
/*      */               
/* 1278 */               return 1;
/*      */             } 
/* 1280 */             if (par2Str.equals("spawn mobs")) {
/*      */               
/* 1282 */               int num_spawned = world.getAnimalSpawner().trySpawningHostileMobs(world, false);
/*      */               
/* 1284 */               num_spawned += world.getAnimalSpawner().trySpawningHostileMobs(world, true);
/*      */               
/* 1286 */               par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(num_spawned + " mobs were spawned").setColor(EnumChatFormatting.YELLOW));
/*      */               
/* 1288 */               return 1;
/*      */             } 
/* 1290 */             if (par2Str.equals("bb")) {
/*      */               
/* 1292 */               Entity.apply_MITE_bb_limits_checking = !Entity.apply_MITE_bb_limits_checking;
/*      */               
/* 1294 */               par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("MITE BB limits checking is now " + (Entity.apply_MITE_bb_limits_checking ? "enabled" : "disabled")).setColor(EnumChatFormatting.YELLOW));
/*      */               
/* 1296 */               return 1;
/*      */             } 
/* 1298 */             if (par2Str.equals("tile entities")) {
/*      */               
/* 1300 */               TileEntity.printTileEntitiesList("Loaded Entities on Server", world.loadedTileEntityList);
/*      */               
/* 1302 */               player.sendPacket(new Packet85SimpleSignal(EnumSignal.loaded_tile_entities));
/*      */               
/* 1304 */               return 1;
/*      */             } 
/* 1306 */             if (par2Str.equals("weather")) {
/*      */               
/* 1308 */               int day = world.getDayOfWorld();
/*      */               
/* 1310 */               world.generateWeatherReport(day, day + 31);
/*      */               
/* 1312 */               return 1;
/*      */             } 
/* 1314 */             if (par2Str.equals("cavern")) {
/*      */               
/* 1316 */               long seed = world.rand.nextLong();
/*      */               
/* 1318 */               Debug.println("Using seed " + seed);
/*      */               
/* 1320 */               CaveNetworkGenerator cg = new CaveNetworkGenerator(new CaveNetworkStub(0, 0, 64, 48, 64, seed, (world.rand.nextInt(2) == 0), true, true));
/*      */               
/* 1322 */               cg.apply(world, player.getBlockPosX(), player.getFootBlockPosY(), player.getBlockPosZ());
/*      */               
/* 1324 */               return 1;
/*      */             } 
/* 1326 */             if (par2Str.equals("cavern+")) {
/*      */               
/* 1328 */               long seed = world.rand.nextLong();
/*      */               
/* 1330 */               seed = 6160391524653987290L;
/*      */ 
/*      */               
/* 1333 */               Debug.println("Using seed " + seed);
/*      */               
/* 1335 */               CaveNetworkGenerator cg = new CaveNetworkGenerator(new CaveNetworkStub(0, 0, 64, 48, 64, seed, true, true, true));
/*      */               
/* 1337 */               cg.apply(world, player.getBlockPosX(), player.getFootBlockPosY(), player.getBlockPosZ());
/*      */               
/* 1339 */               return 1;
/*      */             } 
/* 1341 */             if (par2Str.equals("cavern!")) {
/*      */               
/* 1343 */               CaveNetworkGenerator cg = new CaveNetworkGenerator(new CaveNetworkStub(-14, 29, 64, 48, 64, 2617667064333438329L, true, true, true));
/*      */               
/* 1345 */               cg.apply(world, player.getBlockPosX(), player.getFootBlockPosY(), player.getBlockPosZ());
/*      */               
/* 1347 */               return 1;
/*      */             } 
/* 1349 */             if (par2Str.equals("drill")) {
/*      */               
/* 1351 */               for (int y = 10; y < player.getFootBlockPosY(); y++) {
/* 1352 */                 world.setBlockToAir(player.getBlockPosX(), y, player.getBlockPosZ());
/*      */               }
/* 1354 */               player.setPositionAndUpdate(player.getBlockPosX() + 0.5D, 10.5D, player.getBlockPosZ() + 0.5D);
/*      */               
/* 1356 */               int x = player.getBlockPosX();
/* 1357 */               int i = player.getBlockPosY();
/* 1358 */               int z = player.getBlockPosZ();
/*      */               
/* 1360 */               int radius = 5;
/*      */               int dx;
/* 1362 */               for (dx = -radius; dx <= radius; dx++) {
/*      */                 
/* 1364 */                 for (int dy = -radius; dy <= radius; dy++) {
/*      */                   
/* 1366 */                   for (int dz = -radius; dz <= radius; dz++) {
/* 1367 */                     world.setBlockToAir(x + dx, i + dy, z + dz);
/*      */                   }
/*      */                 } 
/*      */               } 
/*      */               
/* 1372 */               player.inventory.addItemStackToInventoryOrDropIt(new ItemStack(Item.flintAndSteel));
/*      */               
/* 1374 */               for (dx = 0; dx < 4; dx++) {
/*      */                 
/* 1376 */                 for (int dy = 0; dy < 5; dy++) {
/*      */                   
/* 1378 */                   if (dx == 0 || dx == 3 || dy == 0 || dy == 4) {
/* 1379 */                     world.setBlock(x - 1 + dx, i - 5 + dy, z - 5, Block.obsidian.blockID);
/*      */                   }
/*      */                 } 
/*      */               } 
/* 1383 */               return 1;
/*      */             } 
/* 1385 */             if (par2Str.equals("teleport")) {
/*      */               
/* 1387 */               player.setPosition(-2404.5D, 53.0D, -613.5D);
/*      */               
/* 1389 */               return 1;
/*      */             } 
/* 1391 */             if (player.isZevimrgvInTournament())
/*      */             {
/* 1393 */               if (par2Str.equals("see")) {
/*      */                 
/* 1395 */                 player.sendPacket(new Packet85SimpleSignal(EnumSignal.see));
/* 1396 */                 return 1;
/*      */               } 
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1421 */     String[] var3 = par2Str.split(" ");
/* 1422 */     String var4 = var3[0];
/* 1423 */     var3 = dropFirstString(var3);
/* 1424 */     ICommand var5 = (ICommand)this.commandMap.get(var4);
/* 1425 */     int var6 = getUsernameIndex(var5, var3);
/* 1426 */     int var7 = 0;
/*      */     
/* 1428 */     boolean permission_always_denied = false;
/*      */     
/* 1430 */     if (var5 instanceof CommandTime || var5 instanceof CommandGameMode || var5 instanceof CommandDifficulty || var5 instanceof CommandDefaultGameMode || var5 instanceof CommandToggleDownfall || var5 instanceof CommandWeather || var5 instanceof CommandXP || var5 instanceof CommandEffect || var5 instanceof CommandEnchant || var5 instanceof CommandGameRule || var5 instanceof CommandClearInventory || var5 instanceof CommandGive) {
/* 1431 */       permission_always_denied = true;
/*      */     }
/* 1433 */     if (Minecraft.inDevMode()) {
/* 1434 */       permission_always_denied = false;
/*      */     }
/*      */     
/*      */     try {
/* 1438 */       if (var5 == null)
/*      */       {
/* 1440 */         throw new CommandNotFoundException();
/*      */       }
/*      */ 
/*      */       
/* 1444 */       if ((permission_override || var5.canCommandSenderUseCommand(par1ICommandSender)) && !permission_always_denied) {
/*      */         
/* 1446 */         if (var6 > -1)
/*      */         {
/* 1448 */           ServerPlayer[] var8 = PlayerSelector.matchPlayers(par1ICommandSender, var3[var6]);
/* 1449 */           String var9 = var3[var6];
/* 1450 */           ServerPlayer[] var10 = var8;
/* 1451 */           int var11 = var8.length;
/*      */           
/* 1453 */           for (int var12 = 0; var12 < var11; var12++) {
/*      */             
/* 1455 */             ServerPlayer var13 = var10[var12];
/* 1456 */             var3[var6] = var13.getEntityName();
/*      */ 
/*      */             
/*      */             try {
/* 1460 */               var5.processCommand(par1ICommandSender, var3);
/* 1461 */               var7++;
/*      */             }
/* 1463 */             catch (CommandException var15) {
/*      */               
/* 1465 */               par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions(var15.getMessage(), var15.getErrorOjbects()).setColor(EnumChatFormatting.RED));
/*      */             } 
/*      */           } 
/*      */           
/* 1469 */           var3[var6] = var9;
/*      */         }
/*      */         else
/*      */         {
/* 1473 */           var5.processCommand(par1ICommandSender, var3);
/* 1474 */           var7++;
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1479 */         par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.generic.permission").setColor(EnumChatFormatting.RED));
/*      */       }
/*      */     
/* 1482 */     } catch (WrongUsageException var16) {
/*      */       
/* 1484 */       par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.generic.usage", new Object[] { ChatMessageComponent.createFromTranslationWithSubstitutions(var16.getMessage(), var16.getErrorOjbects()) }).setColor(EnumChatFormatting.RED));
/*      */     }
/* 1486 */     catch (CommandException var17) {
/*      */       
/* 1488 */       par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions(var17.getMessage(), var17.getErrorOjbects()).setColor(EnumChatFormatting.RED));
/*      */     }
/* 1490 */     catch (Throwable var18) {
/*      */       
/* 1492 */       par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.generic.exception").setColor(EnumChatFormatting.RED));
/* 1493 */       var18.printStackTrace();
/*      */     } 
/*      */     
/* 1496 */     return var7;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ICommand registerCommand(ICommand par1ICommand) {
/* 1504 */     List var2 = par1ICommand.getCommandAliases();
/* 1505 */     this.commandMap.put(par1ICommand.getCommandName(), par1ICommand);
/* 1506 */     this.commandSet.add(par1ICommand);
/*      */     
/* 1508 */     if (var2 != null) {
/*      */       
/* 1510 */       Iterator<String> var3 = var2.iterator();
/*      */       
/* 1512 */       while (var3.hasNext()) {
/*      */         
/* 1514 */         String var4 = var3.next();
/* 1515 */         ICommand var5 = (ICommand)this.commandMap.get(var4);
/*      */         
/* 1517 */         if (var5 == null || !var5.getCommandName().equals(var4))
/*      */         {
/* 1519 */           this.commandMap.put(var4, par1ICommand);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1524 */     return par1ICommand;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String[] dropFirstString(String[] par0ArrayOfStr) {
/* 1532 */     String[] var1 = new String[par0ArrayOfStr.length - 1];
/*      */     
/* 1534 */     for (int var2 = 1; var2 < par0ArrayOfStr.length; var2++)
/*      */     {
/* 1536 */       var1[var2 - 1] = par0ArrayOfStr[var2];
/*      */     }
/*      */     
/* 1539 */     return var1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List getPossibleCommands(ICommandSender par1ICommandSender, String par2Str) {
/* 1547 */     String[] var3 = par2Str.split(" ", -1);
/* 1548 */     String var4 = var3[0];
/*      */     
/* 1550 */     if (var3.length == 1) {
/*      */       
/* 1552 */       ArrayList var8 = new ArrayList();
/* 1553 */       Iterator<Map.Entry> var6 = this.commandMap.entrySet().iterator();
/*      */       
/* 1555 */       while (var6.hasNext()) {
/*      */         
/* 1557 */         Map.Entry var7 = var6.next();
/*      */         
/* 1559 */         if (CommandBase.doesStringStartWith(var4, (String)var7.getKey()) && ((ICommand)var7.getValue()).canCommandSenderUseCommand(par1ICommandSender))
/*      */         {
/* 1561 */           var8.add(var7.getKey());
/*      */         }
/*      */       } 
/*      */       
/* 1565 */       return var8;
/*      */     } 
/*      */ 
/*      */     
/* 1569 */     if (var3.length > 1) {
/*      */       
/* 1571 */       ICommand var5 = (ICommand)this.commandMap.get(var4);
/*      */       
/* 1573 */       if (var5 != null)
/*      */       {
/* 1575 */         return var5.addTabCompletionOptions(par1ICommandSender, dropFirstString(var3));
/*      */       }
/*      */     } 
/*      */     
/* 1579 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List getPossibleCommands(ICommandSender par1ICommandSender) {
/* 1588 */     ArrayList<ICommand> var2 = new ArrayList();
/* 1589 */     Iterator<ICommand> var3 = this.commandSet.iterator();
/*      */     
/* 1591 */     while (var3.hasNext()) {
/*      */       
/* 1593 */       ICommand var4 = var3.next();
/*      */       
/* 1595 */       if (var4.canCommandSenderUseCommand(par1ICommandSender))
/*      */       {
/* 1597 */         var2.add(var4);
/*      */       }
/*      */     } 
/*      */     
/* 1601 */     return var2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Map getCommands() {
/* 1609 */     return this.commandMap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getUsernameIndex(ICommand par1ICommand, String[] par2ArrayOfStr) {
/* 1617 */     if (par1ICommand == null)
/*      */     {
/* 1619 */       return -1;
/*      */     }
/*      */ 
/*      */     
/* 1623 */     for (int var3 = 0; var3 < par2ArrayOfStr.length; var3++) {
/*      */       
/* 1625 */       if (par1ICommand.isUsernameIndex(par2ArrayOfStr, var3) && PlayerSelector.matchesMultiplePlayers(par2ArrayOfStr[var3]))
/*      */       {
/* 1627 */         return var3;
/*      */       }
/*      */     } 
/*      */     
/* 1631 */     return -1;
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */