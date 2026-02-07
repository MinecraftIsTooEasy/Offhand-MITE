/*     */ package net.minecraft;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ public class CommandSpreadPlayers
/*     */   extends CommandBase
/*     */ {
/*     */   public String getCommandName() {
/*  19 */     return "spreadplayers";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  27 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender par1ICommandSender) {
/*  32 */     return "commands.spreadplayers.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/*  37 */     if (par2ArrayOfStr.length < 6)
/*     */     {
/*  39 */       throw new WrongUsageException("commands.spreadplayers.usage", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  43 */     byte var3 = 0;
/*  44 */     int var17 = var3 + 1;
/*     */ 
/*     */ 
/*     */     
/*  48 */     var17++;
/*     */     
/*  50 */     double var8 = func_110664_a(par1ICommandSender, par2ArrayOfStr[var17++], 0.0D);
/*  51 */     double var10 = func_110664_a(par1ICommandSender, par2ArrayOfStr[var17++], var8 + 1.0D);
/*  52 */     boolean var12 = func_110662_c(par1ICommandSender, par2ArrayOfStr[var17++]);
/*  53 */     ArrayList<? super ServerPlayer> var13 = Lists.newArrayList();
/*     */ 
/*     */ 
/*     */     
/*  57 */     while (var17 < par2ArrayOfStr.length) {
/*     */       
/*  59 */       String var14 = par2ArrayOfStr[var17++];
/*     */       
/*  61 */       if (PlayerSelector.hasArguments(var14)) {
/*     */         
/*  63 */         ServerPlayer[] var18 = PlayerSelector.matchPlayers(par1ICommandSender, var14);
/*     */         
/*  65 */         if (var18 == null || var18.length == 0)
/*     */         {
/*  67 */           throw new PlayerNotFoundException();
/*     */         }
/*     */         
/*  70 */         Collections.addAll(var13, var18);
/*     */         
/*     */         continue;
/*     */       } 
/*  74 */       ServerPlayer var15 = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(var14);
/*     */       
/*  76 */       if (var15 == null)
/*     */       {
/*  78 */         throw new PlayerNotFoundException();
/*     */       }
/*     */       
/*  81 */       var13.add(var15);
/*     */     } 
/*     */ 
/*     */     
/*  85 */     if (var13.isEmpty())
/*     */     {
/*  87 */       throw new PlayerNotFoundException();
/*     */     }
/*     */     
/*  90 */     ServerPlayer player = var13.get(0);
/*     */     
/*  92 */     World world = player.worldObj;
/*     */     
/*  94 */     double var4 = func_110666_a(par1ICommandSender, Double.NaN, par2ArrayOfStr[0], MathHelper.floor_double(world.min_entity_pos_xz), MathHelper.floor_double(world.max_entity_pos_xz));
/*  95 */     double var6 = func_110666_a(par1ICommandSender, Double.NaN, par2ArrayOfStr[1], MathHelper.floor_double(world.min_entity_pos_xz), MathHelper.floor_double(world.max_entity_pos_xz));
/*     */     
/*  97 */     par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.spreadplayers.spreading." + (var12 ? "teams" : "players"), new Object[] { func_110663_b(var13), Double.valueOf(var4), Double.valueOf(var6), Double.valueOf(var8), Double.valueOf(var10) }));
/*  98 */     func_110669_a(par1ICommandSender, var13, new CommandSpreadPlayersPosition(var4, var6), var8, var10, ((EntityLivingBase)var13.get(0)).worldObj, var12);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_110669_a(ICommandSender par1ICommandSender, List par2List, CommandSpreadPlayersPosition par3CommandSpreadPlayersPosition, double par4, double par6, World par8World, boolean par9) {
/* 106 */     Random var10 = new Random();
/* 107 */     double var11 = par3CommandSpreadPlayersPosition.field_111101_a - par6;
/* 108 */     double var13 = par3CommandSpreadPlayersPosition.field_111100_b - par6;
/* 109 */     double var15 = par3CommandSpreadPlayersPosition.field_111101_a + par6;
/* 110 */     double var17 = par3CommandSpreadPlayersPosition.field_111100_b + par6;
/* 111 */     CommandSpreadPlayersPosition[] var19 = func_110670_a(var10, par9 ? func_110667_a(par2List) : par2List.size(), var11, var13, var15, var17);
/* 112 */     int var20 = func_110668_a(par3CommandSpreadPlayersPosition, par4, par8World, var10, var11, var13, var15, var17, var19, par9);
/* 113 */     double var21 = func_110671_a(par2List, par8World, var19, par9);
/* 114 */     notifyAdmins(par1ICommandSender, "commands.spreadplayers.success." + (par9 ? "teams" : "players"), new Object[] { Integer.valueOf(var19.length), Double.valueOf(par3CommandSpreadPlayersPosition.field_111101_a), Double.valueOf(par3CommandSpreadPlayersPosition.field_111100_b) });
/*     */     
/* 116 */     if (var19.length > 1)
/*     */     {
/* 118 */       par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.spreadplayers.info." + (par9 ? "teams" : "players"), new Object[] { String.format("%.2f", new Object[] { Double.valueOf(var21) }), Integer.valueOf(var20) }));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_110667_a(List par1List) {
/* 124 */     HashSet<Team> var2 = Sets.newHashSet();
/* 125 */     Iterator<EntityLivingBase> var3 = par1List.iterator();
/*     */     
/* 127 */     while (var3.hasNext()) {
/*     */       
/* 129 */       EntityLivingBase var4 = var3.next();
/*     */       
/* 131 */       if (var4 instanceof EntityPlayer) {
/*     */         
/* 133 */         var2.add(((EntityPlayer)var4).getTeam());
/*     */         
/*     */         continue;
/*     */       } 
/* 137 */       var2.add(null);
/*     */     } 
/*     */ 
/*     */     
/* 141 */     return var2.size();
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_110668_a(CommandSpreadPlayersPosition par1CommandSpreadPlayersPosition, double par2, World par4World, Random par5Random, double par6, double par8, double par10, double par12, CommandSpreadPlayersPosition[] par14ArrayOfCommandSpreadPlayersPosition, boolean par15) {
/* 146 */     boolean var16 = true;
/* 147 */     double var18 = 3.4028234663852886E38D;
/*     */     
/*     */     int var17;
/* 150 */     for (var17 = 0; var17 < 10000 && var16; var17++) {
/*     */       
/* 152 */       var16 = false;
/* 153 */       var18 = 3.4028234663852886E38D;
/*     */ 
/*     */ 
/*     */       
/* 157 */       for (int var20 = 0; var20 < par14ArrayOfCommandSpreadPlayersPosition.length; var20++) {
/*     */         
/* 159 */         CommandSpreadPlayersPosition var21 = par14ArrayOfCommandSpreadPlayersPosition[var20];
/* 160 */         int var22 = 0;
/* 161 */         CommandSpreadPlayersPosition var23 = new CommandSpreadPlayersPosition();
/*     */         
/* 163 */         for (int var24 = 0; var24 < par14ArrayOfCommandSpreadPlayersPosition.length; var24++) {
/*     */           
/* 165 */           if (var20 != var24) {
/*     */             
/* 167 */             CommandSpreadPlayersPosition var25 = par14ArrayOfCommandSpreadPlayersPosition[var24];
/* 168 */             double var26 = var21.func_111099_a(var25);
/* 169 */             var18 = Math.min(var26, var18);
/*     */             
/* 171 */             if (var26 < par2) {
/*     */               
/* 173 */               var22++;
/* 174 */               var23.field_111101_a += var25.field_111101_a - var21.field_111101_a;
/* 175 */               var23.field_111100_b += var25.field_111100_b - var21.field_111100_b;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 180 */         if (var22 > 0) {
/*     */           
/* 182 */           var23.field_111101_a /= var22;
/* 183 */           var23.field_111100_b /= var22;
/* 184 */           double var30 = var23.func_111096_b();
/*     */           
/* 186 */           if (var30 > 0.0D) {
/*     */             
/* 188 */             var23.func_111095_a();
/* 189 */             var21.func_111094_b(var23);
/*     */           }
/*     */           else {
/*     */             
/* 193 */             var21.func_111097_a(par5Random, par6, par8, par10, par12);
/*     */           } 
/*     */           
/* 196 */           var16 = true;
/*     */         } 
/*     */         
/* 199 */         if (var21.func_111093_a(par6, par8, par10, par12))
/*     */         {
/* 201 */           var16 = true;
/*     */         }
/*     */       } 
/*     */       
/* 205 */       if (!var16) {
/*     */         
/* 207 */         CommandSpreadPlayersPosition[] var28 = par14ArrayOfCommandSpreadPlayersPosition;
/* 208 */         int var29 = par14ArrayOfCommandSpreadPlayersPosition.length;
/*     */         
/* 210 */         for (int var22 = 0; var22 < var29; var22++) {
/*     */           
/* 212 */           CommandSpreadPlayersPosition var23 = var28[var22];
/*     */           
/* 214 */           if (!var23.func_111098_b(par4World)) {
/*     */             
/* 216 */             var23.func_111097_a(par5Random, par6, par8, par10, par12);
/* 217 */             var16 = true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 223 */     if (var17 >= 10000)
/*     */     {
/* 225 */       throw new CommandException("commands.spreadplayers.failure." + (par15 ? "teams" : "players"), new Object[] { Integer.valueOf(par14ArrayOfCommandSpreadPlayersPosition.length), Double.valueOf(par1CommandSpreadPlayersPosition.field_111101_a), Double.valueOf(par1CommandSpreadPlayersPosition.field_111100_b), String.format("%.2f", new Object[] { Double.valueOf(var18) }) });
/*     */     }
/*     */ 
/*     */     
/* 229 */     return var17;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private double func_110671_a(List<EntityLivingBase> par1List, World par2World, CommandSpreadPlayersPosition[] par3ArrayOfCommandSpreadPlayersPosition, boolean par4) {
/* 235 */     double var5 = 0.0D;
/* 236 */     int var7 = 0;
/* 237 */     HashMap<Team, CommandSpreadPlayersPosition> var8 = Maps.newHashMap();
/*     */     
/* 239 */     for (int var9 = 0; var9 < par1List.size(); var9++) {
/*     */       CommandSpreadPlayersPosition var11;
/* 241 */       EntityLivingBase var10 = par1List.get(var9);
/*     */ 
/*     */       
/* 244 */       if (par4) {
/*     */         
/* 246 */         Team var12 = (var10 instanceof EntityPlayer) ? ((EntityPlayer)var10).getTeam() : null;
/*     */         
/* 248 */         if (!var8.containsKey(var12))
/*     */         {
/* 250 */           var8.put(var12, par3ArrayOfCommandSpreadPlayersPosition[var7++]);
/*     */         }
/*     */         
/* 253 */         var11 = var8.get(var12);
/*     */       }
/*     */       else {
/*     */         
/* 257 */         var11 = par3ArrayOfCommandSpreadPlayersPosition[var7++];
/*     */       } 
/*     */       
/* 260 */       var10.setPositionAndUpdate((MathHelper.floor_double(var11.field_111101_a) + 0.5F), var11.func_111092_a(par2World), MathHelper.floor_double(var11.field_111100_b) + 0.5D);
/* 261 */       double var17 = Double.MAX_VALUE;
/*     */       
/* 263 */       for (int var14 = 0; var14 < par3ArrayOfCommandSpreadPlayersPosition.length; var14++) {
/*     */         
/* 265 */         if (var11 != par3ArrayOfCommandSpreadPlayersPosition[var14]) {
/*     */           
/* 267 */           double var15 = var11.func_111099_a(par3ArrayOfCommandSpreadPlayersPosition[var14]);
/* 268 */           var17 = Math.min(var15, var17);
/*     */         } 
/*     */       } 
/*     */       
/* 272 */       var5 += var17;
/*     */     } 
/*     */     
/* 275 */     var5 /= par1List.size();
/* 276 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   private CommandSpreadPlayersPosition[] func_110670_a(Random par1Random, int par2, double par3, double par5, double par7, double par9) {
/* 281 */     CommandSpreadPlayersPosition[] var11 = new CommandSpreadPlayersPosition[par2];
/*     */     
/* 283 */     for (int var12 = 0; var12 < var11.length; var12++) {
/*     */       
/* 285 */       CommandSpreadPlayersPosition var13 = new CommandSpreadPlayersPosition();
/* 286 */       var13.func_111097_a(par1Random, par3, par5, par7, par9);
/* 287 */       var11[var12] = var13;
/*     */     } 
/*     */     
/* 290 */     return var11;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandSpreadPlayers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */