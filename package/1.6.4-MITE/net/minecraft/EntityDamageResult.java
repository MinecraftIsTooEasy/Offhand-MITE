/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityDamageResult
/*     */ {
/*     */   public final Entity entity;
/*     */   private boolean entity_was_affected;
/*     */   private boolean entity_was_knocked_back;
/*     */   private boolean entity_lost_health;
/*     */   private boolean entity_was_destroyed;
/*     */   private float amount_of_health_lost;
/*     */   private boolean entity_held_item_was_affected;
/*     */   private boolean entity_held_item_lost_durability;
/*     */   private boolean entity_held_item_was_destroyed;
/*     */   private boolean entity_armor_was_affected;
/*     */   private boolean entity_armor_lost_durability;
/*     */   private boolean entity_armor_was_destroyed;
/*     */   private float health_before;
/*     */   
/*     */   public EntityDamageResult(Entity entity) {
/*  36 */     this.entity = entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean entityWasNegativelyAffected() {
/*  41 */     return (this.entity_was_knocked_back || this.entity_lost_health || this.entity_was_destroyed || this.entity_held_item_lost_durability || this.entity_held_item_was_destroyed || this.entity_armor_lost_durability || this.entity_armor_was_destroyed);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean entityWasNegativelyAffectedButNotDestroyed() {
/*  46 */     return (entityWasNegativelyAffected() && !entityWasDestroyed());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityDamageResult setEntityWasAffected() {
/*  56 */     this.entity_was_affected = true;
/*     */     
/*  58 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean entityWasAffected() {
/*  63 */     return this.entity_was_affected;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityDamageResult setEntityWasKnockedBack() {
/*  68 */     this.entity_was_knocked_back = true;
/*  69 */     return setEntityWasAffected();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean entityWasKnockedBack() {
/*  74 */     return this.entity_was_knocked_back;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityDamageResult setEntityLostHealth(float amount) {
/*  80 */     if (amount <= 0.0F) {
/*  81 */       return this;
/*     */     }
/*  83 */     this.entity_lost_health = true;
/*  84 */     this.amount_of_health_lost = amount;
/*     */     
/*  86 */     return setEntityWasAffected();
/*     */   }
/*     */ 
/*     */   
/*     */   public void startTrackingHealth(float health_before) {
/*  91 */     this.health_before = health_before;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityDamageResult finishTrackingHealth(float health_after) {
/*  96 */     float amount_lost = this.health_before - health_after;
/*     */     
/*  98 */     if (amount_lost < 0.0F)
/*  99 */       return setEntityWasAffected(); 
/* 100 */     if (amount_lost == 0.0F) {
/* 101 */       return this;
/*     */     }
/* 103 */     return setEntityLostHealth(amount_lost);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean entityLostHealth() {
/* 108 */     return this.entity_lost_health;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAmountOfHealthLost() {
/* 113 */     return this.amount_of_health_lost;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityDamageResult setEntityWasDestroyed() {
/* 118 */     this.entity_was_destroyed = true;
/* 119 */     return setEntityWasAffected();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean entityWasDestroyed() {
/* 124 */     return this.entity_was_destroyed;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean entityLostHealthButWasNotDestroyed() {
/* 129 */     return (entityLostHealth() && !entityWasDestroyed());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityDamageResult setEntityArmorWasAffected() {
/* 135 */     this.entity_armor_was_affected = true;
/* 136 */     return setEntityWasAffected();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean entityArmorWasAffected() {
/* 141 */     return this.entity_armor_was_affected;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityDamageResult setEntityArmorLostDurability() {
/* 146 */     this.entity_armor_lost_durability = true;
/* 147 */     return setEntityArmorWasAffected();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean entityArmorLostDurability() {
/* 152 */     return this.entity_armor_lost_durability;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityDamageResult setEntityArmorWasDestroyed() {
/* 158 */     this.entity_armor_was_destroyed = true;
/* 159 */     return setEntityArmorWasAffected();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean entityArmorWasDestroyed() {
/* 165 */     return this.entity_armor_was_destroyed;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityDamageResult setEntityHeldItemWasAffected() {
/* 171 */     this.entity_held_item_was_affected = true;
/* 172 */     return setEntityWasAffected();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean entityHeldItemWasAffected() {
/* 177 */     return this.entity_held_item_was_affected;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityDamageResult setEntityHeldItemLostDurability() {
/* 182 */     this.entity_held_item_lost_durability = true;
/* 183 */     return setEntityHeldItemWasAffected();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean entityHeldItemLostDurability() {
/* 188 */     return this.entity_held_item_lost_durability;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityDamageResult setEntityHeldItemWasDestroyed() {
/* 193 */     this.entity_held_item_was_destroyed = true;
/* 194 */     return setEntityHeldItemWasAffected();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean entityHeldItemWasDestroyed() {
/* 199 */     return this.entity_held_item_was_destroyed;
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
/*     */   public boolean hadNoEffect() {
/* 221 */     return (!this.entity_was_affected && !this.entity_held_item_was_affected && !this.entity_armor_was_affected);
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
/*     */   public EntityDamageResult applyHeldItemDamageResult(ItemDamageResult result) {
/* 236 */     if (result != null) {
/*     */       
/* 238 */       if (result.itemLostDurability()) {
/* 239 */         setEntityHeldItemLostDurability();
/*     */       }
/* 241 */       if (result.itemWasDestroyed()) {
/* 242 */         setEntityHeldItemWasDestroyed();
/*     */       }
/*     */     } 
/* 245 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityDamageResult applyArmorDamageResult(ItemDamageResult result) {
/* 250 */     if (result != null) {
/*     */       
/* 252 */       if (result.itemLostDurability()) {
/* 253 */         setEntityArmorLostDurability();
/*     */       }
/* 255 */       if (result.itemWasDestroyed()) {
/* 256 */         setEntityArmorWasDestroyed();
/*     */       }
/*     */     } 
/* 259 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityDamageResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */