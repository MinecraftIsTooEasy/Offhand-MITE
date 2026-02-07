/*     */ package net.minecraft;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Potion
/*     */ {
/*  13 */   public static final Potion[] potionTypes = new Potion[32];
/*  14 */   public static final Potion field_76423_b = null;
/*     */   
/*  16 */   public static final Potion moveSpeed = (new Potion(1, false, 8171462)).setPotionName("potion.moveSpeed").setIconIndex(0, 0).func_111184_a(SharedMonsterAttributes.movementSpeed, "91AEAA56-376B-4498-935B-2F7F68070635", 0.20000000298023224D, 2);
/*     */ 
/*     */   
/*  19 */   public static final Potion moveSlowdown = (new Potion(2, true, 5926017)).setPotionName("potion.moveSlowdown").setIconIndex(1, 0).func_111184_a(SharedMonsterAttributes.movementSpeed, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.20000000298023224D, 2);
/*  20 */   public static final Potion digSpeed = (new Potion(3, false, 14270531)).setPotionName("potion.digSpeed").setIconIndex(2, 0).setEffectiveness(1.5D);
/*  21 */   public static final Potion digSlowdown = (new Potion(4, true, 4866583)).setPotionName("potion.digSlowDown").setIconIndex(3, 0);
/*     */   
/*  23 */   public static final Potion damageBoost = (new PotionAttackDamage(5, false, 9643043)).setPotionName("potion.damageBoost").setIconIndex(4, 0).func_111184_a(SharedMonsterAttributes.attackDamage, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 0.4D, 1);
/*  24 */   public static final Potion heal = (new PotionHealth(6, false, 16262179)).setPotionName("potion.heal");
/*  25 */   public static final Potion harm = (new PotionHealth(7, true, 4393481)).setPotionName("potion.harm");
/*  26 */   public static final Potion jump = (new Potion(8, false, 7889559)).setPotionName("potion.jump").setIconIndex(2, 1);
/*  27 */   public static final Potion confusion = (new Potion(9, true, 5578058)).setPotionName("potion.confusion").setIconIndex(3, 1).setEffectiveness(0.25D);
/*     */ 
/*     */   
/*  30 */   public static final Potion regeneration = (new Potion(10, false, 13458603)).setPotionName("potion.regeneration").setIconIndex(7, 0).setEffectiveness(0.25D);
/*  31 */   public static final Potion resistance = (new Potion(11, false, 10044730)).setPotionName("potion.resistance").setIconIndex(6, 1);
/*     */ 
/*     */   
/*  34 */   public static final Potion fireResistance = (new Potion(12, false, 14981690)).setPotionName("potion.fireResistance").setIconIndex(7, 1);
/*     */ 
/*     */   
/*  37 */   public static final Potion waterBreathing = (new Potion(13, false, 3035801)).setPotionName("potion.waterBreathing").setIconIndex(0, 2);
/*     */ 
/*     */   
/*  40 */   public static final Potion invisibility = (new Potion(14, false, 8356754)).setPotionName("potion.invisibility").setIconIndex(0, 1);
/*     */ 
/*     */   
/*  43 */   public static final Potion blindness = (new Potion(15, true, 2039587)).setPotionName("potion.blindness").setIconIndex(5, 1).setEffectiveness(0.25D);
/*     */ 
/*     */   
/*  46 */   public static final Potion nightVision = (new Potion(16, false, 2039713)).setPotionName("potion.nightVision").setIconIndex(4, 1);
/*     */ 
/*     */   
/*  49 */   public static final Potion hunger = (new Potion(17, true, 5797459)).setPotionName("potion.hunger").setIconIndex(1, 1);
/*     */ 
/*     */ 
/*     */   
/*  53 */   public static final Potion weakness = (new PotionAttackDamage(18, true, 4738376)).setPotionName("potion.weakness").setIconIndex(5, 0).func_111184_a(SharedMonsterAttributes.attackDamage, "22653B89-116E-49DC-9B6B-9971489B5BE5", -0.4D, 1);
/*     */ 
/*     */   
/*  56 */   public static final Potion poison = (new Potion(19, true, 5149489)).setPotionName("potion.poison").setIconIndex(6, 0).setEffectiveness(0.25D);
/*     */ 
/*     */   
/*  59 */   public static final Potion wither = (new Potion(20, true, 3484199)).setPotionName("potion.wither").setIconIndex(1, 2).setEffectiveness(0.25D);
/*  60 */   public static final Potion field_76434_w = (new PotionHealthBoost(21, false, 16284963)).setPotionName("potion.healthBoost").setIconIndex(2, 2).func_111184_a(SharedMonsterAttributes.maxHealth, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 4.0D, 0);
/*  61 */   public static final Potion field_76444_x = (new PotionAbsoption(22, false, 2445989)).setPotionName("potion.absorption").setIconIndex(2, 2);
/*  62 */   public static final Potion field_76443_y = (new PotionHealth(23, false, 16262179)).setPotionName("potion.saturation");
/*  63 */   public static final Potion field_76442_z = null;
/*  64 */   public static final Potion field_76409_A = null;
/*  65 */   public static final Potion field_76410_B = null;
/*  66 */   public static final Potion field_76411_C = null;
/*  67 */   public static final Potion field_76405_D = null;
/*  68 */   public static final Potion field_76406_E = null;
/*  69 */   public static final Potion field_76407_F = null;
/*  70 */   public static final Potion field_76408_G = null;
/*     */   
/*     */   public final int id;
/*     */   
/*  74 */   private final Map field_111188_I = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean isBadEffect;
/*     */ 
/*     */ 
/*     */   
/*     */   private final int liquidColor;
/*     */ 
/*     */   
/*  85 */   private String name = "";
/*     */ 
/*     */   
/*  88 */   private int statusIconIndex = -1;
/*     */   
/*     */   private double effectiveness;
/*     */   
/*     */   private boolean usable;
/*     */   
/*     */   public static final int SUBTYPE_POTION_OF_FIRE_RESISTANCE_I = 8227;
/*     */   
/*     */   public static final int SUBTYPE_POTION_OF_HEALING_I = 8261;
/*     */   
/*     */   public static final int SUBTYPE_SPLASH_POTION_OF_POISON_I = 16388;
/*     */   
/*     */   public static final int SUBTYPE_SPLASH_POTION_OF_WEAKNESS_I = 16424;
/*     */   public static final int SUBTYPE_SPLASH_POTION_OF_SLOWNESS_I = 16426;
/*     */   public static final int SUBTYPE_SPLASH_POTION_OF_HARMING_I = 16460;
/*     */   
/*     */   protected Potion(int par1, boolean par2, int par3) {
/* 105 */     this.id = par1;
/* 106 */     potionTypes[par1] = this;
/* 107 */     this.isBadEffect = par2;
/*     */     
/* 109 */     if (par2) {
/*     */       
/* 111 */       this.effectiveness = 0.5D;
/*     */     }
/*     */     else {
/*     */       
/* 115 */       this.effectiveness = 1.0D;
/*     */     } 
/*     */     
/* 118 */     this.liquidColor = par3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Potion setIconIndex(int par1, int par2) {
/* 126 */     this.statusIconIndex = par1 + par2 * 8;
/* 127 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getId() {
/* 135 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void performEffect(EntityLivingBase par1EntityLivingBase, int par2) {
/* 140 */     if (par1EntityLivingBase.onClient()) {
/*     */       return;
/*     */     }
/* 143 */     if (this.id == regeneration.id) {
/*     */       
/* 145 */       if (par1EntityLivingBase.getHealth() < par1EntityLivingBase.getMaxHealth())
/*     */       {
/* 147 */         par1EntityLivingBase.heal(1.0F);
/*     */       }
/*     */     }
/* 150 */     else if (this.id == poison.id) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 157 */       par1EntityLivingBase.attackEntityFrom(new Damage(DamageSource.poison, 1.0F));
/*     */     }
/* 159 */     else if (this.id == wither.id) {
/*     */ 
/*     */       
/* 162 */       par1EntityLivingBase.attackEntityFrom(new Damage(DamageSource.wither, 1.0F));
/*     */     }
/* 164 */     else if (this.id == hunger.id && par1EntityLivingBase instanceof EntityPlayer) {
/*     */ 
/*     */ 
/*     */       
/* 168 */       if (!par1EntityLivingBase.worldObj.isRemote) {
/* 169 */         ((EntityPlayer)par1EntityLivingBase).addHungerServerSide(0.025F * (par2 + 1));
/*     */       }
/* 171 */     } else if (this.id == field_76443_y.id && par1EntityLivingBase instanceof EntityPlayer) {
/*     */       
/* 173 */       if (!par1EntityLivingBase.worldObj.isRemote)
/*     */       {
/*     */         
/* 176 */         ((EntityPlayer)par1EntityLivingBase).addFoodValue(new ItemFood(par2 + 1, par2 + 1, false, false, false));
/*     */       }
/*     */     }
/* 179 */     else if ((this.id != heal.id || par1EntityLivingBase.isEntityUndead()) && (this.id != harm.id || !par1EntityLivingBase.isEntityUndead())) {
/*     */       
/* 181 */       if ((this.id == harm.id && !par1EntityLivingBase.isEntityUndead()) || (this.id == heal.id && par1EntityLivingBase.isEntityUndead()))
/*     */       {
/*     */         
/* 184 */         par1EntityLivingBase.attackEntityFrom(new Damage(DamageSource.magic, (6 << par2)));
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 189 */       par1EntityLivingBase.heal(Math.max(4 << par2, 0));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void affectEntity(EntityLivingBase par1EntityLivingBase, EntityLivingBase par2EntityLivingBase, int par3, double par4) {
/* 200 */     if ((this.id != heal.id || par2EntityLivingBase.isEntityUndead()) && (this.id != harm.id || !par2EntityLivingBase.isEntityUndead())) {
/*     */       
/* 202 */       if ((this.id == harm.id && !par2EntityLivingBase.isEntityUndead()) || (this.id == heal.id && par2EntityLivingBase.isEntityUndead())) {
/*     */         
/* 204 */         int var6 = (int)(par4 * (6 << par3) + 0.5D);
/*     */         
/* 206 */         if (par1EntityLivingBase == null)
/*     */         {
/*     */           
/* 209 */           par2EntityLivingBase.attackEntityFrom(new Damage(DamageSource.magic, var6));
/*     */         
/*     */         }
/*     */         else
/*     */         {
/* 214 */           par2EntityLivingBase.attackEntityFrom(new Damage(DamageSource.causeIndirectMagicDamage(par2EntityLivingBase, par1EntityLivingBase), var6));
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 220 */       int var6 = (int)(par4 * (4 << par3) + 0.5D);
/* 221 */       par2EntityLivingBase.heal(var6);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInstant() {
/* 230 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEffectInterval(int amplifier) {
/*     */     int interval;
/* 238 */     if (this.id == regeneration.id)
/* 239 */     { interval = 50 >> amplifier; }
/* 240 */     else if (this.id == poison.id)
/* 241 */     { interval = 100 >> amplifier; }
/* 242 */     else if (this.id == wither.id)
/* 243 */     { interval = 40 >> amplifier; }
/* 244 */     else { if (this.id == hunger.id) {
/* 245 */         return 1;
/*     */       }
/* 247 */       return -1; }
/*     */     
/* 249 */     return (interval < 1) ? 1 : interval;
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
/*     */   public boolean isReady(int par1, int par2) {
/* 280 */     int effect_interval = getEffectInterval(par2);
/*     */     
/* 282 */     return (effect_interval == -1) ? false : ((par1 % effect_interval == 0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Potion setPotionName(String par1Str) {
/* 290 */     this.name = par1Str;
/* 291 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 299 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasStatusIcon() {
/* 307 */     return (this.statusIconIndex >= 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStatusIconIndex() {
/* 315 */     return this.statusIconIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBadEffect() {
/* 323 */     return this.isBadEffect;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getDurationString(PotionEffect par0PotionEffect) {
/* 328 */     if (par0PotionEffect.getIsPotionDurationMax())
/*     */     {
/* 330 */       return "**:**";
/*     */     }
/*     */ 
/*     */     
/* 334 */     int var1 = par0PotionEffect.getDuration();
/* 335 */     return StringUtils.ticksToElapsedTime(var1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Potion setEffectiveness(double par1) {
/* 341 */     this.effectiveness = par1;
/* 342 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getEffectiveness() {
/* 347 */     return this.effectiveness;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUsable() {
/* 352 */     return this.usable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLiquidColor() {
/* 360 */     return this.liquidColor;
/*     */   }
/*     */ 
/*     */   
/*     */   public Potion func_111184_a(Attribute par1Attribute, String par2Str, double par3, int par5) {
/* 365 */     AttributeModifier var6 = new AttributeModifier(UUID.fromString(par2Str), getName(), par3, par5);
/* 366 */     this.field_111188_I.put(par1Attribute, var6);
/* 367 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map func_111186_k() {
/* 372 */     return this.field_111188_I;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAttributesModifiersFromEntity(EntityLivingBase par1EntityLivingBase, BaseAttributeMap par2BaseAttributeMap, int par3) {
/* 377 */     Iterator<Map.Entry> var4 = this.field_111188_I.entrySet().iterator();
/*     */     
/* 379 */     while (var4.hasNext()) {
/*     */       
/* 381 */       Map.Entry var5 = var4.next();
/* 382 */       AttributeInstance var6 = par2BaseAttributeMap.getAttributeInstance((Attribute)var5.getKey());
/*     */       
/* 384 */       if (var6 != null)
/*     */       {
/* 386 */         var6.removeModifier((AttributeModifier)var5.getValue());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyAttributesModifiersToEntity(EntityLivingBase par1EntityLivingBase, BaseAttributeMap par2BaseAttributeMap, int par3) {
/* 393 */     Iterator<Map.Entry> var4 = this.field_111188_I.entrySet().iterator();
/*     */     
/* 395 */     while (var4.hasNext()) {
/*     */       
/* 397 */       Map.Entry var5 = var4.next();
/* 398 */       AttributeInstance var6 = par2BaseAttributeMap.getAttributeInstance((Attribute)var5.getKey());
/*     */       
/* 400 */       if (var6 != null) {
/*     */         
/* 402 */         AttributeModifier var7 = (AttributeModifier)var5.getValue();
/* 403 */         var6.removeModifier(var7);
/*     */ 
/*     */         
/* 406 */         if (par1EntityLivingBase instanceof EntityPlayer)
/*     */         {
/* 408 */           if (this == moveSlowdown || this == moveSpeed) {
/*     */             continue;
/*     */           }
/*     */         }
/* 412 */         AttributeModifier attribute_modifier = new AttributeModifier(var7.getID(), getName() + " " + par3, func_111183_a(par3, var7), var7.getOperation());
/*     */         
/* 414 */         if (getName().equals("potion.moveSlowdown")) {
/*     */           
/* 416 */           int free_action = EnchantmentHelper.getFreeActionModifier(par1EntityLivingBase);
/*     */           
/* 418 */           if (free_action > 0)
/*     */           {
/* 420 */             attribute_modifier.setAmount(attribute_modifier.getAmount() * (1.0F - free_action * 0.8F / Enchantment.free_action.getNumLevels()));
/*     */           }
/*     */         } 
/* 423 */         var6.applyModifier(attribute_modifier);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_111183_a(int par1, AttributeModifier par2AttributeModifier) {
/* 430 */     return par2AttributeModifier.getAmount() * (par1 + 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Potion get(int id) {
/* 435 */     return potionTypes[id];
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Potion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */