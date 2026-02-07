/*     */ package net.minecraft;
/*     */ 
/*     */ import com.google.common.primitives.Doubles;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CommandBase
/*     */   implements ICommand
/*     */ {
/*     */   private static IAdminCommand theAdmin;
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  19 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public List getCommandAliases() {
/*  24 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender) {
/*  32 */     return par1ICommandSender.canCommandSenderUseCommand(getRequiredPermissionLevel(), getCommandName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/*  40 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int parseInt(ICommandSender par0ICommandSender, String par1Str) {
/*     */     try {
/*  50 */       return Integer.parseInt(par1Str);
/*     */     }
/*  52 */     catch (NumberFormatException var3) {
/*     */       
/*  54 */       throw new NumberInvalidException("commands.generic.num.invalid", new Object[] { par1Str });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int parseIntWithMin(ICommandSender par0ICommandSender, String par1Str, int par2) {
/*  63 */     return parseIntBounded(par0ICommandSender, par1Str, par2, 2147483647);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int parseIntBounded(ICommandSender par0ICommandSender, String par1Str, int par2, int par3) {
/*  71 */     int var4 = parseInt(par0ICommandSender, par1Str);
/*     */     
/*  73 */     if (var4 < par2)
/*     */     {
/*  75 */       throw new NumberInvalidException("commands.generic.num.tooSmall", new Object[] { Integer.valueOf(var4), Integer.valueOf(par2) });
/*     */     }
/*  77 */     if (var4 > par3)
/*     */     {
/*  79 */       throw new NumberInvalidException("commands.generic.num.tooBig", new Object[] { Integer.valueOf(var4), Integer.valueOf(par3) });
/*     */     }
/*     */ 
/*     */     
/*  83 */     return var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double parseDouble(ICommandSender par0ICommandSender, String par1Str) {
/*     */     try {
/*  94 */       double var2 = Double.parseDouble(par1Str);
/*     */       
/*  96 */       if (!Doubles.isFinite(var2))
/*     */       {
/*  98 */         throw new NumberInvalidException("commands.generic.double.invalid", new Object[] { par1Str });
/*     */       }
/*     */ 
/*     */       
/* 102 */       return var2;
/*     */     
/*     */     }
/* 105 */     catch (NumberFormatException var4) {
/*     */       
/* 107 */       throw new NumberInvalidException("commands.generic.double.invalid", new Object[] { par1Str });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static double func_110664_a(ICommandSender par0ICommandSender, String par1Str, double par2) {
/* 113 */     return func_110661_a(par0ICommandSender, par1Str, par2, Double.MAX_VALUE);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double func_110661_a(ICommandSender par0ICommandSender, String par1Str, double par2, double par4) {
/* 118 */     double var6 = parseDouble(par0ICommandSender, par1Str);
/*     */     
/* 120 */     if (var6 < par2)
/*     */     {
/* 122 */       throw new NumberInvalidException("commands.generic.double.tooSmall", new Object[] { Double.valueOf(var6), Double.valueOf(par2) });
/*     */     }
/* 124 */     if (var6 > par4)
/*     */     {
/* 126 */       throw new NumberInvalidException("commands.generic.double.tooBig", new Object[] { Double.valueOf(var6), Double.valueOf(par4) });
/*     */     }
/*     */ 
/*     */     
/* 130 */     return var6;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean func_110662_c(ICommandSender par0ICommandSender, String par1Str) {
/* 136 */     if (!par1Str.equals("true") && !par1Str.equals("1")) {
/*     */       
/* 138 */       if (!par1Str.equals("false") && !par1Str.equals("0"))
/*     */       {
/* 140 */         throw new CommandException("commands.generic.boolean.invalid", new Object[] { par1Str });
/*     */       }
/*     */ 
/*     */       
/* 144 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 149 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ServerPlayer getCommandSenderAsPlayer(ICommandSender par0ICommandSender) {
/* 158 */     if (par0ICommandSender instanceof ServerPlayer)
/*     */     {
/* 160 */       return (ServerPlayer)par0ICommandSender;
/*     */     }
/*     */ 
/*     */     
/* 164 */     throw new PlayerNotFoundException("You must specify which player you wish to perform this action on.", new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ServerPlayer getPlayer(ICommandSender par0ICommandSender, String par1Str) {
/* 170 */     ServerPlayer var2 = PlayerSelector.matchOnePlayer(par0ICommandSender, par1Str);
/*     */     
/* 172 */     if (var2 != null)
/*     */     {
/* 174 */       return var2;
/*     */     }
/*     */ 
/*     */     
/* 178 */     var2 = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(par1Str);
/*     */     
/* 180 */     if (var2 == null)
/*     */     {
/* 182 */       throw new PlayerNotFoundException();
/*     */     }
/*     */ 
/*     */     
/* 186 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String func_96332_d(ICommandSender par0ICommandSender, String par1Str) {
/* 193 */     ServerPlayer var2 = PlayerSelector.matchOnePlayer(par0ICommandSender, par1Str);
/*     */     
/* 195 */     if (var2 != null)
/*     */     {
/* 197 */       return var2.getEntityName();
/*     */     }
/* 199 */     if (PlayerSelector.hasArguments(par1Str))
/*     */     {
/* 201 */       throw new PlayerNotFoundException();
/*     */     }
/*     */ 
/*     */     
/* 205 */     return par1Str;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String func_82360_a(ICommandSender par0ICommandSender, String[] par1ArrayOfStr, int par2) {
/* 211 */     return func_82361_a(par0ICommandSender, par1ArrayOfStr, par2, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String func_82361_a(ICommandSender par0ICommandSender, String[] par1ArrayOfStr, int par2, boolean par3) {
/* 216 */     StringBuilder var4 = new StringBuilder();
/*     */     
/* 218 */     for (int var5 = par2; var5 < par1ArrayOfStr.length; var5++) {
/*     */       
/* 220 */       if (var5 > par2)
/*     */       {
/* 222 */         var4.append(" ");
/*     */       }
/*     */       
/* 225 */       String var6 = par1ArrayOfStr[var5];
/*     */       
/* 227 */       if (par3) {
/*     */         
/* 229 */         String var7 = PlayerSelector.matchPlayersAsString(par0ICommandSender, var6);
/*     */         
/* 231 */         if (var7 != null) {
/*     */           
/* 233 */           var6 = var7;
/*     */         }
/* 235 */         else if (PlayerSelector.hasArguments(var6)) {
/*     */           
/* 237 */           throw new PlayerNotFoundException();
/*     */         } 
/*     */       } 
/*     */       
/* 241 */       var4.append(var6);
/*     */     } 
/*     */     
/* 244 */     return var4.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double func_110666_a(ICommandSender par0ICommandSender, double par1, String par3Str, int min_xz, int max_xz) {
/* 254 */     return func_110665_a(par0ICommandSender, par1, par3Str, min_xz, max_xz);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double func_110665_a(ICommandSender par0ICommandSender, double par1, String par3Str, int par4, int par5) {
/* 259 */     boolean var6 = par3Str.startsWith("~");
/*     */     
/* 261 */     if (var6 && Double.isNaN(par1))
/*     */     {
/* 263 */       throw new NumberInvalidException("commands.generic.num.invalid", new Object[] { Double.valueOf(par1) });
/*     */     }
/*     */ 
/*     */     
/* 267 */     double var7 = var6 ? par1 : 0.0D;
/*     */     
/* 269 */     if (!var6 || par3Str.length() > 1) {
/*     */       
/* 271 */       boolean var9 = par3Str.contains(".");
/*     */       
/* 273 */       if (var6)
/*     */       {
/* 275 */         par3Str = par3Str.substring(1);
/*     */       }
/*     */       
/* 278 */       var7 += parseDouble(par0ICommandSender, par3Str);
/*     */       
/* 280 */       if (!var9 && !var6)
/*     */       {
/* 282 */         var7 += 0.5D;
/*     */       }
/*     */     } 
/*     */     
/* 286 */     if (par4 != 0 || par5 != 0) {
/*     */       
/* 288 */       if (var7 < par4)
/*     */       {
/* 290 */         throw new NumberInvalidException("commands.generic.double.tooSmall", new Object[] { Double.valueOf(var7), Integer.valueOf(par4) });
/*     */       }
/*     */       
/* 293 */       if (var7 > par5)
/*     */       {
/* 295 */         throw new NumberInvalidException("commands.generic.double.tooBig", new Object[] { Double.valueOf(var7), Integer.valueOf(par5) });
/*     */       }
/*     */     } 
/*     */     
/* 299 */     return var7;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String joinNiceString(Object[] par0ArrayOfObj) {
/* 308 */     StringBuilder var1 = new StringBuilder();
/*     */     
/* 310 */     for (int var2 = 0; var2 < par0ArrayOfObj.length; var2++) {
/*     */       
/* 312 */       String var3 = par0ArrayOfObj[var2].toString();
/*     */       
/* 314 */       if (var2 > 0)
/*     */       {
/* 316 */         if (var2 == par0ArrayOfObj.length - 1) {
/*     */           
/* 318 */           var1.append(" and ");
/*     */         }
/*     */         else {
/*     */           
/* 322 */           var1.append(", ");
/*     */         } 
/*     */       }
/*     */       
/* 326 */       var1.append(var3);
/*     */     } 
/*     */     
/* 329 */     return var1.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String func_96333_a(Collection par0Collection) {
/* 334 */     return joinNiceString(par0Collection.toArray((Object[])new String[par0Collection.size()]));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String func_110663_b(Collection par0Collection) {
/* 339 */     String[] var1 = new String[par0Collection.size()];
/* 340 */     int var2 = 0;
/*     */ 
/*     */     
/* 343 */     for (Iterator<EntityLivingBase> var3 = par0Collection.iterator(); var3.hasNext(); var1[var2++] = var4.getTranslatedEntityName())
/*     */     {
/* 345 */       EntityLivingBase var4 = var3.next();
/*     */     }
/*     */     
/* 348 */     return joinNiceString((Object[])var1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean doesStringStartWith(String par0Str, String par1Str) {
/* 356 */     return par1Str.regionMatches(true, 0, par0Str, 0, par0Str.length());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List getListOfStringsMatchingLastWord(String[] par0ArrayOfStr, String... par1ArrayOfStr) {
/* 365 */     String var2 = par0ArrayOfStr[par0ArrayOfStr.length - 1];
/* 366 */     ArrayList<String> var3 = new ArrayList();
/* 367 */     String[] var4 = par1ArrayOfStr;
/* 368 */     int var5 = par1ArrayOfStr.length;
/*     */     
/* 370 */     for (int var6 = 0; var6 < var5; var6++) {
/*     */       
/* 372 */       String var7 = var4[var6];
/*     */       
/* 374 */       if (doesStringStartWith(var2, var7))
/*     */       {
/* 376 */         var3.add(var7);
/*     */       }
/*     */     } 
/*     */     
/* 380 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List getListOfStringsFromIterableMatchingLastWord(String[] par0ArrayOfStr, Iterable par1Iterable) {
/* 389 */     String var2 = par0ArrayOfStr[par0ArrayOfStr.length - 1];
/* 390 */     ArrayList<String> var3 = new ArrayList();
/* 391 */     Iterator<String> var4 = par1Iterable.iterator();
/*     */     
/* 393 */     while (var4.hasNext()) {
/*     */       
/* 395 */       String var5 = var4.next();
/*     */       
/* 397 */       if (doesStringStartWith(var2, var5))
/*     */       {
/* 399 */         var3.add(var5);
/*     */       }
/*     */     } 
/*     */     
/* 403 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUsernameIndex(String[] par1ArrayOfStr, int par2) {
/* 411 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void notifyAdmins(ICommandSender par0ICommandSender, String par1Str, Object... par2ArrayOfObj) {
/* 416 */     notifyAdmins(par0ICommandSender, 0, par1Str, par2ArrayOfObj);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void notifyAdmins(ICommandSender par0ICommandSender, int par1, String par2Str, Object... par3ArrayOfObj) {
/* 421 */     if (theAdmin != null)
/*     */     {
/* 423 */       theAdmin.notifyAdmins(par0ICommandSender, par1, par2Str, par3ArrayOfObj);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setAdminCommander(IAdminCommand par0IAdminCommand) {
/* 432 */     theAdmin = par0IAdminCommand;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(ICommand par1ICommand) {
/* 440 */     return getCommandName().compareTo(par1ICommand.getCommandName());
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(Object par1Obj) {
/* 445 */     return compareTo((ICommand)par1Obj);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */