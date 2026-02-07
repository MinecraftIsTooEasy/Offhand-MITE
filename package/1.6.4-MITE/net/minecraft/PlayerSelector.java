/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerSelector
/*     */ {
/*  18 */   private static final Pattern tokenPattern = Pattern.compile("^@([parf])(?:\\[([\\w=,!-]*)\\])?$");
/*  19 */   private static final Pattern intListPattern = Pattern.compile("\\G([-!]?[\\w-]*)(?:$|,)");
/*  20 */   private static final Pattern keyValueListPattern = Pattern.compile("\\G(\\w+)=([-!]?[\\w-]*)(?:$|,)");
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
/*     */   
/*     */   public static ServerPlayer matchOnePlayer(ICommandSender iCommandSender, String string) {
/*  43 */     ServerPlayer[] arrayOfServerPlayer = matchPlayers(iCommandSender, string);
/*     */     
/*  45 */     if (arrayOfServerPlayer == null || arrayOfServerPlayer.length != 1) return null;
/*     */     
/*  47 */     return arrayOfServerPlayer[0];
/*     */   }
/*     */   
/*     */   public static String matchPlayersAsString(ICommandSender iCommandSender, String string) {
/*  51 */     ServerPlayer[] arrayOfServerPlayer = matchPlayers(iCommandSender, string);
/*  52 */     if (arrayOfServerPlayer == null || arrayOfServerPlayer.length == 0) return null; 
/*  53 */     String[] arrayOfString = new String[arrayOfServerPlayer.length];
/*     */     
/*  55 */     for (byte b = 0; b < arrayOfString.length; b++) {
/*  56 */       arrayOfString[b] = arrayOfServerPlayer[b].getTranslatedEntityName();
/*     */     }
/*     */     
/*  59 */     return CommandBase.joinNiceString((Object[])arrayOfString);
/*     */   }
/*     */   
/*     */   public static ServerPlayer[] matchPlayers(ICommandSender iCommandSender, String string) {
/*  63 */     Matcher matcher = tokenPattern.matcher(string);
/*     */     
/*  65 */     if (matcher.matches()) {
/*  66 */       Map map1 = getArgumentMap(matcher.group(2));
/*  67 */       String str1 = matcher.group(1);
/*  68 */       int i = getDefaultMinimumRange(str1);
/*  69 */       int j = getDefaultMaximumRange(str1);
/*  70 */       int k = getDefaultMinimumLevel(str1);
/*  71 */       int m = getDefaultMaximumLevel(str1);
/*  72 */       int n = getDefaultCount(str1);
/*  73 */       int i1 = EnumGameType.NOT_SET.getID();
/*  74 */       ChunkCoordinates chunkCoordinates = iCommandSender.getPlayerCoordinates();
/*  75 */       Map map2 = func_96560_a(map1);
/*  76 */       String str2 = null;
/*  77 */       String str3 = null;
/*  78 */       boolean bool = false;
/*     */       
/*  80 */       if (map1.containsKey("rm")) {
/*  81 */         i = MathHelper.parseIntWithDefault((String)map1.get("rm"), i);
/*  82 */         bool = true;
/*     */       } 
/*  84 */       if (map1.containsKey("r")) {
/*  85 */         j = MathHelper.parseIntWithDefault((String)map1.get("r"), j);
/*  86 */         bool = true;
/*     */       } 
/*  88 */       if (map1.containsKey("lm")) {
/*  89 */         k = MathHelper.parseIntWithDefault((String)map1.get("lm"), k);
/*     */       }
/*  91 */       if (map1.containsKey("l")) {
/*  92 */         m = MathHelper.parseIntWithDefault((String)map1.get("l"), m);
/*     */       }
/*  94 */       if (map1.containsKey("x")) {
/*  95 */         chunkCoordinates.posX = MathHelper.parseIntWithDefault((String)map1.get("x"), chunkCoordinates.posX);
/*  96 */         bool = true;
/*     */       } 
/*  98 */       if (map1.containsKey("y")) {
/*  99 */         chunkCoordinates.posY = MathHelper.parseIntWithDefault((String)map1.get("y"), chunkCoordinates.posY);
/* 100 */         bool = true;
/*     */       } 
/* 102 */       if (map1.containsKey("z")) {
/* 103 */         chunkCoordinates.posZ = MathHelper.parseIntWithDefault((String)map1.get("z"), chunkCoordinates.posZ);
/* 104 */         bool = true;
/*     */       } 
/* 106 */       if (map1.containsKey("m")) {
/* 107 */         i1 = MathHelper.parseIntWithDefault((String)map1.get("m"), i1);
/*     */       }
/* 109 */       if (map1.containsKey("c")) {
/* 110 */         n = MathHelper.parseIntWithDefault((String)map1.get("c"), n);
/*     */       }
/* 112 */       if (map1.containsKey("team")) {
/* 113 */         str3 = (String)map1.get("team");
/*     */       }
/* 115 */       if (map1.containsKey("name")) {
/* 116 */         str2 = (String)map1.get("name");
/*     */       }
/*     */       
/* 119 */       World world = bool ? iCommandSender.getEntityWorld() : null;
/*     */       
/* 121 */       if (str1.equals("p") || str1.equals("a")) {
/* 122 */         List list = MinecraftServer.getServer().getConfigurationManager().findPlayers(chunkCoordinates, i, j, n, i1, k, m, map2, str2, str3, world);
/* 123 */         return (list == null || list.isEmpty()) ? new ServerPlayer[0] : (ServerPlayer[])list.toArray((Object[])new ServerPlayer[0]);
/* 124 */       }  if (str1.equals("r")) {
/* 125 */         List<?> list = MinecraftServer.getServer().getConfigurationManager().findPlayers(chunkCoordinates, i, j, 0, i1, k, m, map2, str2, str3, world);
/* 126 */         Collections.shuffle(list);
/* 127 */         list = list.subList(0, Math.min(n, list.size()));
/* 128 */         return (list == null || list.isEmpty()) ? new ServerPlayer[0] : list.<ServerPlayer>toArray(new ServerPlayer[0]);
/*     */       } 
/* 130 */       return null;
/*     */     } 
/*     */     
/* 133 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Map func_96560_a(Map map) {
/* 138 */     HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
/*     */     
/* 140 */     for (String str : map.keySet()) {
/* 141 */       if (str.startsWith("score_") && str.length() > "score_".length()) {
/* 142 */         String str1 = str.substring("score_".length());
/* 143 */         hashMap.put(str1, Integer.valueOf(MathHelper.parseIntWithDefault((String)map.get(str), 1)));
/*     */       } 
/*     */     } 
/*     */     
/* 147 */     return hashMap;
/*     */   }
/*     */   
/*     */   public static boolean matchesMultiplePlayers(String string) {
/* 151 */     Matcher matcher = tokenPattern.matcher(string);
/*     */     
/* 153 */     if (matcher.matches()) {
/* 154 */       Map map = getArgumentMap(matcher.group(2));
/* 155 */       String str = matcher.group(1);
/* 156 */       int i = getDefaultCount(str);
/* 157 */       if (map.containsKey("c")) i = MathHelper.parseIntWithDefault((String)map.get("c"), i); 
/* 158 */       return (i != 1);
/*     */     } 
/*     */     
/* 161 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean hasTheseArguments(String string, String string2) {
/* 165 */     Matcher matcher = tokenPattern.matcher(string);
/*     */     
/* 167 */     if (matcher.matches()) {
/* 168 */       String str = matcher.group(1);
/* 169 */       if (string2 != null && !string2.equals(str)) return false;
/*     */       
/* 171 */       return true;
/*     */     } 
/*     */     
/* 174 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean hasArguments(String string) {
/* 178 */     return hasTheseArguments(string, null);
/*     */   }
/*     */   
/*     */   private static final int getDefaultMinimumRange(String string) {
/* 182 */     return 0;
/*     */   }
/*     */   
/*     */   private static final int getDefaultMaximumRange(String string) {
/* 186 */     return 0;
/*     */   }
/*     */   
/*     */   private static final int getDefaultMaximumLevel(String string) {
/* 190 */     return Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */   private static final int getDefaultMinimumLevel(String string) {
/* 194 */     return 0;
/*     */   }
/*     */   
/*     */   private static final int getDefaultCount(String string) {
/* 198 */     if (string.equals("a")) {
/* 199 */       return 0;
/*     */     }
/* 201 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Map getArgumentMap(String string) {
/* 206 */     HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
/* 207 */     if (string == null) return hashMap; 
/* 208 */     Matcher matcher = intListPattern.matcher(string);
/* 209 */     byte b = 0;
/* 210 */     int i = -1;
/*     */     
/* 212 */     while (matcher.find()) {
/* 213 */       String str = null;
/*     */       
/* 215 */       switch (b++) {
/*     */         case 0:
/* 217 */           str = "x";
/*     */           break;
/*     */         case 1:
/* 220 */           str = "y";
/*     */           break;
/*     */         case 2:
/* 223 */           str = "z";
/*     */           break;
/*     */         case 3:
/* 226 */           str = "r";
/*     */           break;
/*     */       } 
/*     */       
/* 230 */       if (str != null && matcher.group(1).length() > 0) hashMap.put(str, matcher.group(1)); 
/* 231 */       i = matcher.end();
/*     */     } 
/*     */     
/* 234 */     if (i < string.length()) {
/* 235 */       matcher = keyValueListPattern.matcher((i == -1) ? string : string.substring(i));
/*     */       
/* 237 */       while (matcher.find()) {
/* 238 */         hashMap.put(matcher.group(1), matcher.group(2));
/*     */       }
/*     */     } 
/*     */     
/* 242 */     return hashMap;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PlayerSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */