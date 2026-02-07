/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class EntitySenses
/*     */ {
/*     */   EntityLiving entityObj;
/*  15 */   HashMap sensing_cache_when_leaves_block_LOS = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  20 */   HashMap sensing_cache_when_leaves_allow_LOS = new HashMap<Object, Object>();
/*     */ 
/*     */   
/*     */   public EntitySenses(EntityLiving par1EntityLiving) {
/*  24 */     this.entityObj = par1EntityLiving;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearSensingCache() {
/*  35 */     this.sensing_cache_when_leaves_block_LOS.clear();
/*  36 */     this.sensing_cache_when_leaves_allow_LOS.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSee(Entity entity) {
/*  41 */     return canSee(entity, false);
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
/*     */   public boolean canSee(Entity entity, boolean ignore_leaves) {
/*  83 */     if (entity == null) {
/*  84 */       return false;
/*     */     }
/*  86 */     HashMap<Entity, Boolean> sensing_cache = ignore_leaves ? this.sensing_cache_when_leaves_allow_LOS : this.sensing_cache_when_leaves_block_LOS;
/*     */     
/*  88 */     Boolean seen_obj = (Boolean)sensing_cache.get(entity);
/*     */     
/*  90 */     if (seen_obj == null) {
/*     */       
/*  92 */       this.entityObj.worldObj.theProfiler.startSection("canSee");
/*  93 */       boolean seen = this.entityObj.canSeeEntity(entity, ignore_leaves);
/*  94 */       this.entityObj.worldObj.theProfiler.endSection();
/*     */       
/*  96 */       sensing_cache.put(entity, Boolean.valueOf(seen));
/*     */       
/*  98 */       return seen;
/*     */     } 
/*     */     
/* 101 */     return seen_obj.booleanValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntitySenses.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */