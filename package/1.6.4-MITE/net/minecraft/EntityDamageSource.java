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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityDamageSource
/*     */   extends DamageSource
/*     */ {
/*     */   private Entity immediate_entity;
/*     */   private Entity responsible_entity;
/*     */   
/*     */   public EntityDamageSource(String par1Str, Entity entity) {
/*  41 */     this(par1Str, entity, entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityDamageSource(String par1Str, Entity immediate_entity, Entity responsible_entity) {
/*  46 */     super(par1Str);
/*  47 */     this.immediate_entity = immediate_entity;
/*  48 */     this.responsible_entity = responsible_entity;
/*     */     
/*  50 */     if (immediate_entity instanceof EntityThrowable) {
/*     */       
/*  52 */       EntityThrowable throwable = (EntityThrowable)immediate_entity;
/*     */       
/*  54 */       if (throwable.isMagical()) {
/*  55 */         setMagicAspect();
/*     */       }
/*  57 */     } else if (immediate_entity instanceof EntityArrow) {
/*     */       
/*  59 */       EntityArrow entity_arrow = (EntityArrow)immediate_entity;
/*     */       
/*  61 */       if (entity_arrow.item_arrow.hasMaterial(Material.silver)) {
/*  62 */         setSilverAspect();
/*     */       }
/*  64 */       if (entity_arrow.isBurning()) {
/*  65 */         setFireAspect();
/*     */       }
/*  67 */       if (entity_arrow.getLauncher() != null && entity_arrow.getLauncher().isItemEnchanted()) {
/*  68 */         setMagicAspect();
/*     */       }
/*  70 */     } else if (!(immediate_entity instanceof EntityFireball)) {
/*     */ 
/*     */ 
/*     */       
/*  74 */       if (responsible_entity instanceof EntityLivingBase) {
/*     */         
/*  76 */         EntityLivingBase entity_living_base = (EntityLivingBase)responsible_entity;
/*     */         
/*  78 */         ItemStack held_item = entity_living_base.getHeldItemStack();
/*     */         
/*  80 */         if (held_item != null) {
/*     */           
/*  82 */           if (held_item.hasMaterial(Material.silver)) {
/*  83 */             setSilverAspect();
/*     */           }
/*  85 */           if (held_item.isItemEnchanted())
/*  86 */             setMagicAspect(); 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Entity getImmediateEntity() {
/*  93 */     return this.immediate_entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity getResponsibleEntity() {
/*  98 */     return this.responsible_entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isProjectile() {
/* 103 */     return (this.immediate_entity != null && (this.immediate_entity instanceof IProjectile || this.immediate_entity instanceof EntityFireball));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isIndirect() {
/* 108 */     return (this.responsible_entity != null && this.responsible_entity != this.immediate_entity && !isProjectile());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChatMessageComponent getDeathMessage(EntityLivingBase par1EntityLivingBase) {
/* 116 */     Entity entity = (this.responsible_entity == null) ? this.immediate_entity : this.responsible_entity;
/*     */     
/* 118 */     ItemStack var2 = (entity instanceof EntityLivingBase) ? ((EntityLivingBase)entity).getHeldItemStack() : null;
/*     */     
/* 120 */     String var3 = "death.attack." + (this.is_hand_damage ? "hand_damage" : this.damageType);
/* 121 */     String var4 = var3 + ".item";
/* 122 */     return (var2 != null && var2.hasDisplayName() && StatCollector.func_94522_b(var4)) ? ChatMessageComponent.createFromTranslationWithSubstitutions(var4, new Object[] { par1EntityLivingBase.getTranslatedEntityName(), entity.getTranslatedEntityName(), var2.getDisplayName() }) : ChatMessageComponent.createFromTranslationWithSubstitutions(var3, new Object[] { par1EntityLivingBase.getTranslatedEntityName(), entity.getTranslatedEntityName() });
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityDamageSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */