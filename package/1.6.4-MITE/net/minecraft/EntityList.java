/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityList
/*     */ {
/*  15 */   private static Map stringToClassMapping = new HashMap<Object, Object>();
/*     */ 
/*     */   
/*  18 */   private static Map classToStringMapping = new HashMap<Object, Object>();
/*     */ 
/*     */   
/*  21 */   private static Map IDtoClassMapping = new HashMap<Object, Object>();
/*     */ 
/*     */   
/*  24 */   private static Map classToIDMapping = new HashMap<Object, Object>();
/*     */ 
/*     */   
/*  27 */   private static Map stringToIDMapping = new HashMap<Object, Object>();
/*     */ 
/*     */   
/*  30 */   public static HashMap entityEggs = new LinkedHashMap<Object, Object>();
/*     */   
/*  32 */   public static List entries = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void addMapping(Class<?> par0Class, String par1Str, int par2) {
/*  39 */     stringToClassMapping.put(par1Str, par0Class);
/*  40 */     classToStringMapping.put(par0Class, par1Str);
/*  41 */     IDtoClassMapping.put(Integer.valueOf(par2), par0Class);
/*  42 */     classToIDMapping.put(par0Class, Integer.valueOf(par2));
/*  43 */     stringToIDMapping.put(par1Str, Integer.valueOf(par2));
/*     */     
/*  45 */     entries.add(new EntityListEntry(par0Class, par1Str, par2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void addMapping(Class par0Class, String par1Str, int par2, int par3, int par4) {
/*  53 */     addMapping(par0Class, par1Str, par2);
/*  54 */     entityEggs.put(Integer.valueOf(par2), new EntityEggInfo(par2, par3, par4));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Entity createEntityByName(String par0Str, World par1World) {
/*  62 */     Entity var2 = null;
/*     */ 
/*     */     
/*     */     try {
/*  66 */       Class<Entity> var3 = (Class)stringToClassMapping.get(par0Str);
/*     */       
/*  68 */       if (var3 != null)
/*     */       {
/*  70 */         var2 = var3.getConstructor(new Class[] { World.class }).newInstance(new Object[] { par1World });
/*     */       }
/*     */     }
/*  73 */     catch (Exception var4) {
/*     */       
/*  75 */       var4.printStackTrace();
/*     */     } 
/*     */     
/*  78 */     return var2;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Entity getEntityInstanceByNameCaseInsensitive(String par0Str, World par1World) {
/*  98 */     if ("Horse".equalsIgnoreCase(par0Str)) {
/*  99 */       par0Str = "EntityHorse";
/*     */     }
/* 101 */     Class<Entity> _class = null;
/*     */     
/* 103 */     Iterator<Map.Entry> i = stringToClassMapping.entrySet().iterator();
/*     */     
/* 105 */     while (i.hasNext()) {
/*     */       
/* 107 */       Map.Entry entry = i.next();
/*     */       
/* 109 */       if (((String)entry.getKey()).equalsIgnoreCase(par0Str)) {
/*     */         
/* 111 */         _class = (Class)entry.getValue();
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 116 */     if (_class != null) {
/*     */       
/*     */       try {
/*     */         
/* 120 */         if (_class != null) {
/* 121 */           return _class.getConstructor(new Class[] { World.class }).newInstance(new Object[] { par1World });
/*     */         }
/* 123 */       } catch (Exception var4) {
/*     */         
/* 125 */         var4.printStackTrace();
/*     */       } 
/*     */     }
/*     */     
/* 129 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Entity createEntityFromNBT(NBTTagCompound par0NBTTagCompound, World par1World) {
/* 137 */     Entity var2 = null;
/*     */     
/* 139 */     if ("Minecart".equals(par0NBTTagCompound.getString("id"))) {
/*     */       
/* 141 */       switch (par0NBTTagCompound.getInteger("Type")) {
/*     */         
/*     */         case 0:
/* 144 */           par0NBTTagCompound.setString("id", "MinecartRideable");
/*     */           break;
/*     */         
/*     */         case 1:
/* 148 */           par0NBTTagCompound.setString("id", "MinecartChest");
/*     */           break;
/*     */         
/*     */         case 2:
/* 152 */           par0NBTTagCompound.setString("id", "MinecartFurnace");
/*     */           break;
/*     */       } 
/* 155 */       par0NBTTagCompound.removeTag("Type");
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 160 */       Class<Entity> var3 = (Class)stringToClassMapping.get(par0NBTTagCompound.getString("id"));
/*     */       
/* 162 */       if (var3 != null)
/*     */       {
/* 164 */         var2 = var3.getConstructor(new Class[] { World.class }).newInstance(new Object[] { par1World });
/*     */       }
/*     */     }
/* 167 */     catch (Exception var4) {
/*     */       
/* 169 */       var4.printStackTrace();
/*     */     } 
/*     */     
/* 172 */     if (var2 != null) {
/*     */       
/* 174 */       var2.readFromNBT(par0NBTTagCompound);
/*     */     }
/*     */     else {
/*     */       
/* 178 */       par1World.getWorldLogAgent().logWarning("Skipping Entity with id " + par0NBTTagCompound.getString("id"));
/*     */     } 
/*     */     
/* 181 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Entity createEntityByID(int par0, World par1World) {
/* 189 */     Entity var2 = null;
/*     */ 
/*     */     
/*     */     try {
/* 193 */       Class<Entity> var3 = getClassFromID(par0);
/*     */       
/* 195 */       if (var3 != null)
/*     */       {
/* 197 */         var2 = var3.getConstructor(new Class[] { World.class }).newInstance(new Object[] { par1World });
/*     */       }
/*     */     }
/* 200 */     catch (Exception var4) {
/*     */       
/* 202 */       var4.printStackTrace();
/*     */     } 
/*     */     
/* 205 */     if (var2 == null)
/*     */     {
/* 207 */       par1World.getWorldLogAgent().logWarning("Skipping Entity with id " + par0);
/*     */     }
/*     */     
/* 210 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getEntityID(Entity par0Entity) {
/* 218 */     Class<?> var1 = par0Entity.getClass();
/* 219 */     return classToIDMapping.containsKey(var1) ? ((Integer)classToIDMapping.get(var1)).intValue() : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getEntityID(Class var1) {
/* 224 */     return classToIDMapping.containsKey(var1) ? ((Integer)classToIDMapping.get(var1)).intValue() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Class getClassFromID(int par0) {
/* 232 */     return (Class)IDtoClassMapping.get(Integer.valueOf(par0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getEntityString(Entity par0Entity) {
/* 240 */     return (String)classToStringMapping.get(par0Entity.getClass());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getStringFromID(int par0) {
/* 248 */     Class var1 = getClassFromID(par0);
/* 249 */     return (var1 != null) ? (String)classToStringMapping.get(var1) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 254 */     addMapping(EntityItem.class, "Item", 1);
/* 255 */     addMapping(EntityXPOrb.class, "XPOrb", 2);
/* 256 */     addMapping(EntityLeashKnot.class, "LeashKnot", 8);
/* 257 */     addMapping(EntityPainting.class, "Painting", 9);
/* 258 */     addMapping(EntityArrow.class, "Arrow", 10);
/* 259 */     addMapping(EntitySnowball.class, "Snowball", 11);
/* 260 */     addMapping(EntityLargeFireball.class, "Fireball", 12);
/* 261 */     addMapping(EntitySmallFireball.class, "SmallFireball", 13);
/* 262 */     addMapping(EntityEnderPearl.class, "ThrownEnderpearl", 14);
/* 263 */     addMapping(EntityEnderEye.class, "EyeOfEnderSignal", 15);
/* 264 */     addMapping(EntityPotion.class, "ThrownPotion", 16);
/* 265 */     addMapping(EntityExpBottle.class, "ThrownExpBottle", 17);
/* 266 */     addMapping(EntityItemFrame.class, "ItemFrame", 18);
/* 267 */     addMapping(EntityWitherSkull.class, "WitherSkull", 19);
/* 268 */     addMapping(EntityTNTPrimed.class, "PrimedTnt", 20);
/* 269 */     addMapping(EntityFallingSand.class, "FallingSand", 21);
/* 270 */     addMapping(EntityFireworkRocket.class, "FireworksRocketEntity", 22);
/* 271 */     addMapping(EntityBoat.class, "Boat", 41);
/* 272 */     addMapping(EntityMinecartEmpty.class, "MinecartRideable", 42);
/* 273 */     addMapping(EntityMinecartChest.class, "MinecartChest", 43);
/* 274 */     addMapping(EntityMinecartFurnace.class, "MinecartFurnace", 44);
/* 275 */     addMapping(EntityMinecartTNT.class, "MinecartTNT", 45);
/* 276 */     addMapping(EntityMinecartHopper.class, "MinecartHopper", 46);
/* 277 */     addMapping(EntityMinecartMobSpawner.class, "MinecartSpawner", 47);
/* 278 */     addMapping(EntityLiving.class, "Mob", 48);
/* 279 */     addMapping(EntityMob.class, "Monster", 49);
/* 280 */     addMapping(EntityCreeper.class, "Creeper", 50, 894731, 0);
/* 281 */     addMapping(EntitySkeleton.class, "Skeleton", 51, 12698049, 4802889);
/* 282 */     addMapping(EntitySpider.class, "Spider", 52, 3419431, 11013646);
/* 283 */     addMapping(EntityGiantZombie.class, "Giant", 53);
/* 284 */     addMapping(EntityZombie.class, "Zombie", 54, 44975, 7969893);
/* 285 */     addMapping(EntitySlime.class, "Slime", 55, 5349438, 8306542);
/* 286 */     addMapping(EntityGhast.class, "Ghast", 56, 16382457, 12369084);
/* 287 */     addMapping(EntityPigZombie.class, "PigZombie", 57, 15373203, 5009705);
/* 288 */     addMapping(EntityEnderman.class, "Enderman", 58, 1447446, 0);
/* 289 */     addMapping(EntityCaveSpider.class, "CaveSpider", 59, 803406, 11013646);
/* 290 */     addMapping(EntitySilverfish.class, "Silverfish", 60, 7237230, 3158064);
/* 291 */     addMapping(EntityBlaze.class, "Blaze", 61, 16167425, 16775294);
/* 292 */     addMapping(EntityMagmaCube.class, "LavaSlime", 62, 3407872, 16579584);
/* 293 */     addMapping(EntityDragon.class, "EnderDragon", 63);
/* 294 */     addMapping(EntityWither.class, "WitherBoss", 64);
/* 295 */     addMapping(EntityBat.class, "Bat", 65, 4996656, 986895);
/* 296 */     addMapping(EntityWitch.class, "Witch", 66, 3407872, 5349438);
/* 297 */     addMapping(EntityPig.class, "Pig", 90, 15771042, 14377823);
/* 298 */     addMapping(EntitySheep.class, "Sheep", 91, 15198183, 16758197);
/* 299 */     addMapping(EntityCow.class, "Cow", 92, 4470310, 10592673);
/* 300 */     addMapping(EntityChicken.class, "Chicken", 93, 10592673, 16711680);
/* 301 */     addMapping(EntitySquid.class, "Squid", 94, 2243405, 7375001);
/* 302 */     addMapping(EntityWolf.class, "Wolf", 95, 14144467, 13545366);
/* 303 */     addMapping(EntityMooshroom.class, "MushroomCow", 96, 10489616, 12040119);
/* 304 */     addMapping(EntitySnowman.class, "SnowMan", 97);
/* 305 */     addMapping(EntityOcelot.class, "Ozelot", 98, 15720061, 5653556);
/* 306 */     addMapping(EntityIronGolem.class, "VillagerGolem", 99);
/* 307 */     addMapping(EntityHorse.class, "EntityHorse", 100, 12623485, 15656192);
/*     */ 
/*     */ 
/*     */     
/* 311 */     int id = 512;
/*     */     
/* 313 */     addMapping(EntityGhoul.class, "Ghoul", id++, 6127744, 5199946);
/* 314 */     addMapping(EntityWight.class, "Wight", id++, 5789784, 16777215);
/* 315 */     addMapping(EntityInvisibleStalker.class, "InvisibleStalker", id++, 8947848, 7829367);
/* 316 */     addMapping(EntityDemonSpider.class, "DemonSpider", id++, 4066304, 11013646);
/* 317 */     addMapping(EntityHellhound.class, "Hellhound", id++, 1513239, 14954030);
/* 318 */     addMapping(EntityDireWolf.class, "DireWolf", id++, 9802643, 6770494);
/* 319 */     addMapping(EntityWoodSpider.class, "WoodSpider", id++, 4733734, 11013646);
/* 320 */     addMapping(EntityInfernalCreeper.class, "InfernalCreeper", id++, 11599885, 0);
/* 321 */     addMapping(EntityShadow.class, "Shadow", id++, 592137, 2894892);
/* 322 */     addMapping(EntityFireElemental.class, "FireElemental", id++, 11484928, 14259731);
/* 323 */     addMapping(EntityBlackWidowSpider.class, "BlackWidowSpider", id++, 1513239, 11013646);
/*     */     
/* 325 */     addMapping(EntityRevenant.class, "Revenant", id++, 44975, 7969893);
/* 326 */     addMapping(EntityEarthElemental.class, "EarthElemental", id++, 5658198, 10066329);
/* 327 */     addMapping(EntityJelly.class, "Jelly", id++, 9924660, 12097379);
/* 328 */     addMapping(EntityBlob.class, "Blob", id++, 10430241, 12474193);
/* 329 */     addMapping(EntityOoze.class, "Ooze", id++, 7237230, 9868950);
/* 330 */     addMapping(EntityPudding.class, "Pudding", id++, 1314564, 2762010);
/* 331 */     addMapping(EntityVampireBat.class, "VampireBat", id++, 4996656, 5900553);
/* 332 */     addMapping(EntityGiantVampireBat.class, "GiantVampireBat", id++, 4996656, 5900553);
/* 333 */     addMapping(EntityLongdead.class, "Longdead", id++, 13422277, 7699821);
/* 334 */     addMapping(EntityLongdeadGuardian.class, "LongdeadGuardian", id++, 13422277, 7699821);
/* 335 */     addMapping(EntityNightwing.class, "Nightwing", id++, 592137, 2894892);
/* 336 */     addMapping(EntityNetherspawn.class, "Netherspawn", id++, 8464671, 5444623);
/* 337 */     addMapping(EntityCopperspine.class, "Copperspine", id++, 10049792, 6434048);
/* 338 */     addMapping(EntityHoarySilverfish.class, "HoarySilverfish", id++, 6647137, 2567971);
/* 339 */     addMapping(EntityClayGolem.class, "ClayGolem", id++, 10856889, 10133675);
/* 340 */     addMapping(EntityBoneLord.class, "BoneLord", id++, 12698049, 4802889);
/* 341 */     addMapping(EntityAncientBoneLord.class, "AncientBoneLord", id++, 13422277, 7699821);
/* 342 */     addMapping(EntityPhaseSpider.class, "PhaseSpider", id++, 1922130, 512600);
/*     */ 
/*     */ 
/*     */     
/* 346 */     addMapping(EntityVillager.class, "Villager", 120, 5651507, 12422002);
/* 347 */     addMapping(EntityEnderCrystal.class, "EnderCrystal", 200);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */