/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PotionHelper
/*     */ {
/*     */   public static final String field_77924_a;
/*     */   public static final String sugarEffect;
/*     */   public static final String ghastTearEffect;
/*     */   public static final String spiderEyeEffect;
/*     */   public static final String fermentedSpiderEyeEffect;
/*     */   public static final String speckledMelonEffect;
/*     */   public static final String blazePowderEffect;
/*     */   public static final String magmaCreamEffect;
/*     */   public static final String redstoneEffect;
/*     */   public static final String glowstoneEffect;
/*     */   public static final String gunpowderEffect;
/*     */   public static final String goldenCarrotEffect;
/*  50 */   private static final HashMap potionRequirements = new HashMap<Object, Object>();
/*  51 */   private static final HashMap potionAmplifiers = new HashMap<Object, Object>();
/*     */   private static final HashMap field_77925_n;
/*     */   private static final String[] potionPrefixes;
/*     */   
/*     */   static
/*     */   {
/*  57 */     field_77924_a = null;
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
/*  79 */     ghastTearEffect = "+0-1-2-3&4-4+13";
/*  80 */     potionRequirements.put(Integer.valueOf(Potion.regeneration.getId()), "0 & !1 & !2 & !3 & 0+6");
/*     */     
/*  82 */     sugarEffect = "-0+1-2-3&4-4+13";
/*  83 */     potionRequirements.put(Integer.valueOf(Potion.moveSpeed.getId()), "!0 & 1 & !2 & !3 & 1+6");
/*     */     
/*  85 */     magmaCreamEffect = "+0+1-2-3&4-4+13";
/*  86 */     potionRequirements.put(Integer.valueOf(Potion.fireResistance.getId()), "0 & 1 & !2 & !3 & 0+6");
/*     */     
/*  88 */     speckledMelonEffect = "+0-1+2-3&4-4+13";
/*  89 */     potionRequirements.put(Integer.valueOf(Potion.heal.getId()), "0 & !1 & 2 & !3");
/*     */     
/*  91 */     spiderEyeEffect = "-0-1+2-3&4-4+13";
/*  92 */     potionRequirements.put(Integer.valueOf(Potion.poison.getId()), "!0 & !1 & 2 & !3 & 2+6");
/*     */     
/*  94 */     fermentedSpiderEyeEffect = "-0+3-4+13";
/*  95 */     potionRequirements.put(Integer.valueOf(Potion.weakness.getId()), "!0 & !1 & !2 & 3 & 3+6");
/*  96 */     potionRequirements.put(Integer.valueOf(Potion.harm.getId()), "!0 & !1 & 2 & 3");
/*  97 */     potionRequirements.put(Integer.valueOf(Potion.moveSlowdown.getId()), "!0 & 1 & !2 & 3 & 3+6");
/*     */     
/*  99 */     blazePowderEffect = "+0-1-2+3&4-4+13";
/* 100 */     potionRequirements.put(Integer.valueOf(Potion.damageBoost.getId()), "0 & !1 & !2 & 3 & 3+6");
/*     */     
/* 102 */     goldenCarrotEffect = "-0+1+2-3+13&4-4";
/* 103 */     potionRequirements.put(Integer.valueOf(Potion.nightVision.getId()), "!0 & 1 & 2 & !3 & 2+6");
/*     */ 
/*     */     
/* 106 */     potionRequirements.put(Integer.valueOf(Potion.invisibility.getId()), "!0 & 1 & 2 & 3 & 2+6");
/*     */ 
/*     */     
/* 109 */     glowstoneEffect = "+5-6-7";
/* 110 */     potionAmplifiers.put(Integer.valueOf(Potion.moveSpeed.getId()), "5");
/* 111 */     potionAmplifiers.put(Integer.valueOf(Potion.digSpeed.getId()), "5");
/* 112 */     potionAmplifiers.put(Integer.valueOf(Potion.damageBoost.getId()), "5");
/* 113 */     potionAmplifiers.put(Integer.valueOf(Potion.regeneration.getId()), "5");
/* 114 */     potionAmplifiers.put(Integer.valueOf(Potion.harm.getId()), "5");
/* 115 */     potionAmplifiers.put(Integer.valueOf(Potion.heal.getId()), "5");
/* 116 */     potionAmplifiers.put(Integer.valueOf(Potion.resistance.getId()), "5");
/* 117 */     potionAmplifiers.put(Integer.valueOf(Potion.poison.getId()), "5");
/*     */ 
/*     */     
/* 120 */     redstoneEffect = "-5+6-7";
/*     */ 
/*     */ 
/*     */     
/* 124 */     gunpowderEffect = "+14&13-13";
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
/*     */     
/* 231 */     field_77925_n = new HashMap<Object, Object>();
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
/* 252 */     potionPrefixes = new String[] { "potion.prefix.mundane", "potion.prefix.uninteresting", "potion.prefix.bland", "potion.prefix.clear", "potion.prefix.milky", "potion.prefix.diffuse", "potion.prefix.artless", "potion.prefix.thin", "potion.prefix.awkward", "potion.prefix.flat", "potion.prefix.bulky", "potion.prefix.bungling", "potion.prefix.buttered", "potion.prefix.smooth", "potion.prefix.suave", "potion.prefix.debonair", "potion.prefix.thick", "potion.prefix.elegant", "potion.prefix.fancy", "potion.prefix.charming", "potion.prefix.dashing", "potion.prefix.refined", "potion.prefix.cordial", "potion.prefix.sparkling", "potion.prefix.potent", "potion.prefix.foul", "potion.prefix.odorless", "potion.prefix.rank", "potion.prefix.harsh", "potion.prefix.acrid", "potion.prefix.gross", "potion.prefix.stinky" };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean checkFlag(int i, int j) {
/*     */     return ((i & 1 << j) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int isFlagSet(int i, int j) {
/*     */     return checkFlag(i, j) ? 1 : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int isFlagUnset(int i, int j) {
/*     */     return checkFlag(i, j) ? 0 : 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String func_77905_c(int i) {
/* 289 */     int j = func_77909_a(i);
/* 290 */     return potionPrefixes[j];
/*     */   }
/*     */   public static int func_77909_a(int i) { return func_77908_a(i, 5, 4, 3, 2, 1); }
/*     */   public static int calcPotionLiquidColor(Collection collection) { int i = 3694022; if (collection == null || collection.isEmpty())
/*     */       return i;  float f1 = 0.0F; float f2 = 0.0F; float f3 = 0.0F; float f4 = 0.0F; for (PotionEffect potionEffect : collection) { int j = Potion.potionTypes[potionEffect.getPotionID()].getLiquidColor(); for (byte b = 0; b <= potionEffect.getAmplifier(); b++) { f1 += (j >> 16 & 0xFF) / 255.0F; f2 += (j >> 8 & 0xFF) / 255.0F; f3 += (j >> 0 & 0xFF) / 255.0F; f4++; }  }  f1 = f1 / f4 * 255.0F; f2 = f2 / f4 * 255.0F; f3 = f3 / f4 * 255.0F; return (int)f1 << 16 | (int)f2 << 8 | (int)f3; }
/*     */   public static boolean func_82817_b(Collection collection) { for (PotionEffect potionEffect : collection) { if (!potionEffect.getIsAmbient())
/*     */         return false;  }
/*     */      return true; } public static int func_77915_a(int i, boolean bl) { if (!bl) { if (field_77925_n.containsKey(Integer.valueOf(i)))
/*     */         return ((Integer)field_77925_n.get(Integer.valueOf(i))).intValue();  int j = calcPotionLiquidColor(getPotionEffects(i, false)); field_77925_n.put(Integer.valueOf(i), Integer.valueOf(j)); return j; }
/* 299 */      return calcPotionLiquidColor(getPotionEffects(i, bl)); } private static int func_77904_a(boolean bl, boolean bl2, boolean bl3, int i, int j, int k, int l) { int m = 0;
/* 300 */     if (bl) {
/* 301 */       m = isFlagUnset(l, j);
/* 302 */     } else if (i != -1) {
/* 303 */       if (i == 0 && countSetFlags(l) == j) {
/* 304 */         m = 1;
/* 305 */       } else if (i == 1 && countSetFlags(l) > j) {
/* 306 */         m = 1;
/* 307 */       } else if (i == 2 && countSetFlags(l) < j) {
/* 308 */         m = 1;
/*     */       } 
/*     */     } else {
/* 311 */       m = isFlagSet(l, j);
/*     */     } 
/* 313 */     if (bl2) {
/* 314 */       m *= k;
/*     */     }
/* 316 */     if (bl3) {
/* 317 */       m *= -1;
/*     */     }
/* 319 */     return m; }
/*     */ 
/*     */   
/*     */   private static int countSetFlags(int i) {
/* 323 */     byte b = 0;
/* 324 */     for (; i > 0; b++) {
/* 325 */       i &= i - 1;
/*     */     }
/* 327 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int parsePotionEffects(String string, int i, int j, int k) {
/* 332 */     if (i >= string.length() || j < 0 || i >= j) {
/* 333 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 337 */     int m = string.indexOf('|', i);
/* 338 */     if (m >= 0 && m < j) {
/* 339 */       int i5 = parsePotionEffects(string, i, m - 1, k);
/* 340 */       if (i5 > 0) {
/* 341 */         return i5;
/*     */       }
/*     */       
/* 344 */       int i6 = parsePotionEffects(string, m + 1, j, k);
/* 345 */       if (i6 > 0) {
/* 346 */         return i6;
/*     */       }
/* 348 */       return 0;
/*     */     } 
/*     */     
/* 351 */     int n = string.indexOf('&', i);
/* 352 */     if (n >= 0 && n < j) {
/* 353 */       int i5 = parsePotionEffects(string, i, n - 1, k);
/* 354 */       if (i5 <= 0) {
/* 355 */         return 0;
/*     */       }
/*     */       
/* 358 */       int i6 = parsePotionEffects(string, n + 1, j, k);
/* 359 */       if (i6 <= 0) {
/* 360 */         return 0;
/*     */       }
/*     */       
/* 363 */       if (i5 > i6) {
/* 364 */         return i5;
/*     */       }
/* 366 */       return i6;
/*     */     } 
/*     */     
/* 369 */     boolean bool1 = false;
/* 370 */     boolean bool2 = false;
/* 371 */     boolean bool3 = false;
/* 372 */     boolean bool4 = false;
/* 373 */     boolean bool5 = false;
/* 374 */     byte b = -1;
/* 375 */     int i1 = 0;
/* 376 */     int i2 = 0;
/* 377 */     int i3 = 0;
/* 378 */     for (int i4 = i; i4 < j; i4++) {
/*     */       
/* 380 */       char c = string.charAt(i4);
/* 381 */       if (c >= '0' && c <= '9') {
/* 382 */         if (bool1) {
/* 383 */           i2 = c - 48;
/* 384 */           bool2 = true;
/*     */         } else {
/* 386 */           i1 *= 10;
/* 387 */           i1 += c - 48;
/* 388 */           bool3 = true;
/*     */         } 
/* 390 */       } else if (c == '*') {
/* 391 */         bool1 = true;
/* 392 */       } else if (c == '!') {
/* 393 */         if (bool3) {
/* 394 */           i3 += func_77904_a(bool4, bool2, bool5, b, i1, i2, k);
/* 395 */           bool3 = bool2 = bool1 = bool5 = bool4 = false;
/* 396 */           i1 = i2 = 0;
/* 397 */           b = -1;
/*     */         } 
/*     */         
/* 400 */         bool4 = true;
/* 401 */       } else if (c == '-') {
/* 402 */         if (bool3) {
/* 403 */           i3 += func_77904_a(bool4, bool2, bool5, b, i1, i2, k);
/* 404 */           bool3 = bool2 = bool1 = bool5 = bool4 = false;
/* 405 */           i1 = i2 = 0;
/* 406 */           b = -1;
/*     */         } 
/*     */         
/* 409 */         bool5 = true;
/* 410 */       } else if (c == '=' || c == '<' || c == '>') {
/* 411 */         if (bool3) {
/* 412 */           i3 += func_77904_a(bool4, bool2, bool5, b, i1, i2, k);
/* 413 */           bool3 = bool2 = bool1 = bool5 = bool4 = false;
/* 414 */           i1 = i2 = 0;
/* 415 */           b = -1;
/*     */         } 
/*     */         
/* 418 */         if (c == '=') {
/* 419 */           b = 0;
/* 420 */         } else if (c == '<') {
/* 421 */           b = 2;
/* 422 */         } else if (c == '>') {
/* 423 */           b = 1;
/*     */         } 
/* 425 */       } else if (c == '+' && 
/* 426 */         bool3) {
/* 427 */         i3 += func_77904_a(bool4, bool2, bool5, b, i1, i2, k);
/* 428 */         bool3 = bool2 = bool1 = bool5 = bool4 = false;
/* 429 */         i1 = i2 = 0;
/* 430 */         b = -1;
/*     */       } 
/*     */     } 
/*     */     
/* 434 */     if (bool3) {
/* 435 */       i3 += func_77904_a(bool4, bool2, bool5, b, i1, i2, k);
/*     */     }
/*     */     
/* 438 */     return i3;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List getPotionEffects(int i, boolean bl) {
/* 443 */     ArrayList<PotionEffect> arrayList = null;
/*     */     
/* 445 */     for (Potion potion : Potion.potionTypes) {
/* 446 */       if (potion != null && (!potion.isUsable() || bl)) {
/*     */ 
/*     */         
/* 449 */         String str = (String)potionRequirements.get(Integer.valueOf(potion.getId()));
/* 450 */         if (str != null) {
/*     */ 
/*     */ 
/*     */           
/* 454 */           int j = parsePotionEffects(str, 0, str.length(), i);
/* 455 */           if (j > 0) {
/* 456 */             int k = 0;
/* 457 */             String str1 = (String)potionAmplifiers.get(Integer.valueOf(potion.getId()));
/* 458 */             if (str1 != null) {
/* 459 */               k = parsePotionEffects(str1, 0, str1.length(), i);
/* 460 */               if (k < 0) {
/* 461 */                 k = 0;
/*     */               }
/*     */             } 
/*     */             
/* 465 */             if (potion.isInstant()) {
/* 466 */               j = 1;
/*     */             } else {
/*     */               
/* 469 */               j = 1200 * (j * 3 + (j - 1) * 2);
/* 470 */               j >>= k;
/* 471 */               j = (int)Math.round(j * potion.getEffectiveness());
/*     */               
/* 473 */               if ((i & 0x4000) != 0) {
/* 474 */                 j = (int)Math.round(j * 0.75D + 0.5D);
/*     */               }
/*     */             } 
/*     */             
/* 478 */             if (arrayList == null) {
/* 479 */               arrayList = new ArrayList();
/*     */             }
/* 481 */             PotionEffect potionEffect = new PotionEffect(potion.getId(), j, k);
/* 482 */             if ((i & 0x4000) != 0) potionEffect.setSplashPotion(true); 
/* 483 */             arrayList.add(potionEffect);
/*     */           } 
/*     */         } 
/*     */       } 
/* 487 */     }  return arrayList;
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
/*     */ 
/*     */ 
/*     */   
/*     */   private static int brewBitOperations(int i, int j, boolean bl, boolean bl2, boolean bl3) {
/* 573 */     if (bl3) {
/* 574 */       if (!checkFlag(i, j)) {
/* 575 */         return 0;
/*     */       }
/* 577 */     } else if (bl) {
/* 578 */       i &= 1 << j ^ 0xFFFFFFFF;
/* 579 */     } else if (bl2) {
/* 580 */       if ((i & 1 << j) == 0) {
/* 581 */         i |= 1 << j;
/*     */       } else {
/* 583 */         i &= 1 << j ^ 0xFFFFFFFF;
/*     */       } 
/*     */     } else {
/* 586 */       i |= 1 << j;
/*     */     } 
/* 588 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int applyIngredient(int i, String string) {
/* 593 */     byte b1 = 0;
/* 594 */     int j = string.length();
/*     */     
/* 596 */     boolean bool1 = false;
/* 597 */     boolean bool2 = false;
/* 598 */     boolean bool3 = false;
/* 599 */     boolean bool4 = false;
/* 600 */     int k = 0;
/* 601 */     for (byte b2 = b1; b2 < j; b2++) {
/*     */       
/* 603 */       char c = string.charAt(b2);
/* 604 */       if (c >= '0' && c <= '9') {
/* 605 */         k *= 10;
/* 606 */         k += c - 48;
/* 607 */         bool1 = true;
/* 608 */       } else if (c == '!') {
/* 609 */         if (bool1) {
/* 610 */           i = brewBitOperations(i, k, bool3, bool2, bool4);
/* 611 */           bool1 = bool3 = bool2 = bool4 = false;
/* 612 */           k = 0;
/*     */         } 
/*     */         
/* 615 */         bool2 = true;
/* 616 */       } else if (c == '-') {
/* 617 */         if (bool1) {
/* 618 */           i = brewBitOperations(i, k, bool3, bool2, bool4);
/* 619 */           bool1 = bool3 = bool2 = bool4 = false;
/* 620 */           k = 0;
/*     */         } 
/*     */         
/* 623 */         bool3 = true;
/* 624 */       } else if (c == '+') {
/* 625 */         if (bool1) {
/* 626 */           i = brewBitOperations(i, k, bool3, bool2, bool4);
/* 627 */           bool1 = bool3 = bool2 = bool4 = false;
/* 628 */           k = 0;
/*     */         } 
/* 630 */       } else if (c == '&') {
/* 631 */         if (bool1) {
/* 632 */           i = brewBitOperations(i, k, bool3, bool2, bool4);
/* 633 */           bool1 = bool3 = bool2 = bool4 = false;
/* 634 */           k = 0;
/*     */         } 
/* 636 */         bool4 = true;
/*     */       } 
/*     */     } 
/* 639 */     if (bool1) {
/* 640 */       i = brewBitOperations(i, k, bool3, bool2, bool4);
/*     */     }
/*     */     
/* 643 */     return i & 0x7FFF;
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
/*     */   public static int func_77908_a(int i, int j, int k, int l, int m, int n) {
/* 658 */     return (checkFlag(i, j) ? 16 : 0) | (checkFlag(i, k) ? 8 : 0) | (checkFlag(i, l) ? 4 : 0) | (checkFlag(i, m) ? 2 : 0) | (checkFlag(i, n) ? 1 : 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PotionHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */