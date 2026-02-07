/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityDamageSourceIndirect
/*    */   extends EntityDamageSource
/*    */ {
/*    */   private Entity indirectEntity;
/*    */   
/*    */   public EntityDamageSourceIndirect(String string, Entity entity, Entity entity2) {
/* 13 */     super(string, entity);
/* 14 */     this.indirectEntity = entity2;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity getSourceOfDamage() {
/* 19 */     return this.p;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity getEntity() {
/* 24 */     return this.indirectEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   public ChatMessageComponent getDeathMessage(EntityLivingBase entityLivingBase) {
/* 29 */     String str1 = (this.indirectEntity == null) ? this.p.getTranslatedEntityName() : this.indirectEntity.getTranslatedEntityName();
/* 30 */     ItemStack itemStack = (this.indirectEntity instanceof EntityLivingBase) ? ((EntityLivingBase)this.indirectEntity).aZ() : null;
/* 31 */     String str2 = "death.attack." + this.damageType;
/* 32 */     String str3 = str2 + ".item";
/*    */     
/* 34 */     if (itemStack != null && itemStack.hasDisplayName() && StatCollector.func_94522_b(str3)) {
/* 35 */       return ChatMessageComponent.createFromTranslationWithSubstitutions(str3, new Object[] { entityLivingBase.getTranslatedEntityName(), str1, itemStack.getDisplayName() });
/*    */     }
/* 37 */     return ChatMessageComponent.createFromTranslationWithSubstitutions(str2, new Object[] { entityLivingBase.getTranslatedEntityName(), str1 });
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityDamageSourceIndirect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */