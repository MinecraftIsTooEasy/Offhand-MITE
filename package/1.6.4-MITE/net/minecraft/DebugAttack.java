/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DebugAttack
/*     */ {
/*     */   public static DebugAttack instance;
/*     */   public EntityLivingBase attacker;
/*     */   public Entity target;
/*     */   public DamageSource damage_source;
/*     */   public float raw_damage;
/*     */   public float target_protection;
/*     */   public float damage_dealt_to_armor;
/*     */   public float piercing;
/*     */   public float resulting_damage;
/*     */   public float health_before;
/*     */   public float health_after;
/*     */   public ItemStack item_attacked_with;
/*     */   
/*     */   private DebugAttack(Entity target, Damage damage) {
/*  22 */     Entity responsible_entity = damage.getResponsibleEntity();
/*     */     
/*  24 */     this.attacker = (responsible_entity instanceof EntityLivingBase) ? responsible_entity.getAsEntityLivingBase() : null;
/*  25 */     this.target = target;
/*  26 */     this.damage_source = damage.getSource();
/*     */     
/*  28 */     if (target instanceof EntityLivingBase) {
/*  29 */       this.health_before = target.getAsEntityLivingBase().getHealth();
/*     */     }
/*  31 */     this.raw_damage = damage.getAmount();
/*  32 */     this.item_attacked_with = damage.getItemAttackedWith();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void start(Entity target, Damage damage) {
/*  37 */     if (target.onClient()) {
/*  38 */       Minecraft.setErrorMessage("DebugAttack.start: called on client?");
/*     */     }
/*  40 */     if (!Minecraft.inDevMode()) {
/*     */       return;
/*     */     }
/*  43 */     if (instance != null) {
/*  44 */       flush();
/*     */     }
/*  46 */     instance = new DebugAttack(target, damage);
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
/*     */   public static void setTargetProtection(float target_protection) {
/*  67 */     if (instance == null) {
/*     */       return;
/*     */     }
/*  70 */     instance.target_protection = target_protection;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setDamageDealtToArmor(float damage_dealt_to_armor) {
/*  75 */     if (instance == null) {
/*     */       return;
/*     */     }
/*  78 */     instance.damage_dealt_to_armor = damage_dealt_to_armor;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setPiercing(float piercing) {
/*  83 */     if (instance == null) {
/*     */       return;
/*     */     }
/*  86 */     instance.piercing = piercing;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setResultingDamage(float resulting_damage) {
/*  91 */     if (instance == null) {
/*     */       return;
/*     */     }
/*  94 */     instance.resulting_damage = resulting_damage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setHealthAfter(float health_after) {
/* 104 */     if (instance == null) {
/*     */       return;
/*     */     }
/* 107 */     instance.health_after = health_after;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 112 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 114 */     String target_descriptor = this.target.getEntityName() + " (id=" + this.target.entityId + ")";
/*     */     
/* 116 */     if (this.attacker != null) {
/* 117 */       sb.append((this.attacker.isEntityPlayer() ? "Player" : this.attacker.getEntityName()) + " attacked " + this.target.getEntityName() + ((this.item_attacked_with == null) ? "" : (" with " + this.item_attacked_with.getDisplayName())) + " dealing ");
/* 118 */     } else if (this.damage_source.getImmediateEntity() instanceof EntityArrow) {
/* 119 */       sb.append(target_descriptor + " hit by " + ((EntityArrow)this.damage_source.getImmediateEntity()).getModelItem().getItemDisplayName() + " taking ");
/* 120 */     } else if (this.damage_source.getImmediateEntity() != null) {
/* 121 */       sb.append(target_descriptor + " hit by " + this.damage_source.getImmediateEntity().getEntityName() + " taking ");
/*     */     } else {
/* 123 */       sb.append(target_descriptor + " hit by " + this.damage_source + " taking ");
/*     */     } 
/* 125 */     sb.append(StringHelper.formatFloat(this.raw_damage, 1, 1) + " raw damage " + ((this.piercing == 0.0F) ? "" : ("(+" + this.piercing + " piercing) ")) + "vs " + StringHelper.formatFloat(this.target_protection, 1, 1) + " protection, resulting in a loss of " + StringHelper.formatFloat(this.resulting_damage, 1, 1) + " health: " + StringHelper.formatFloat(this.health_before, 1, 1) + "->" + StringHelper.formatFloat(this.health_after, 1, 1));
/*     */     
/* 127 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void flush() {
/* 132 */     instance.flushInstance();
/* 133 */     instance = null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void flushInstance() {
/* 138 */     if (this.target.onClient()) {
/* 139 */       Minecraft.setErrorMessage("flushInstance: called on client?");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 144 */     if (this.damage_dealt_to_armor != 0.0F || this.resulting_damage != 0.0F)
/* 145 */       System.out.println(this); 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DebugAttack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */