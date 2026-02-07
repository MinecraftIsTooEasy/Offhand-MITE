/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class EntityAIFollowParent
/*    */   extends EntityAIBase
/*    */ {
/*    */   EntityAnimal childAnimal;
/*    */   EntityAnimal parentAnimal;
/*    */   double field_75347_c;
/*    */   private int field_75345_d;
/*    */   
/*    */   public EntityAIFollowParent(EntityAnimal entityAnimal, double d) {
/* 14 */     this.childAnimal = entityAnimal;
/* 15 */     this.field_75347_c = d;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 20 */     if (this.childAnimal.getGrowingAge() >= 0) return false;
/*    */     
/* 22 */     List list = this.childAnimal.worldObj.getEntitiesWithinAABB(this.childAnimal.getClass(), this.childAnimal.boundingBox.expand(8.0D, 4.0D, 8.0D));
/*    */     
/* 24 */     EntityAnimal entityAnimal = null;
/* 25 */     double d = Double.MAX_VALUE;
/* 26 */     for (EntityAnimal entityAnimal1 : list) {
/* 27 */       if (entityAnimal1.getGrowingAge() < 0)
/* 28 */         continue;  double d1 = this.childAnimal.getDistanceSqToEntity(entityAnimal1);
/* 29 */       if (d1 > d)
/* 30 */         continue;  d = d1;
/* 31 */       entityAnimal = entityAnimal1;
/*    */     } 
/*    */     
/* 34 */     if (entityAnimal == null) return false; 
/* 35 */     if (d < 9.0D) return false; 
/* 36 */     this.parentAnimal = entityAnimal;
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 42 */     if (!this.parentAnimal.isEntityAlive()) return false; 
/* 43 */     double d = this.childAnimal.getDistanceSqToEntity(this.parentAnimal);
/* 44 */     if (d < 9.0D || d > 256.0D) return false; 
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 50 */     this.field_75345_d = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 55 */     this.parentAnimal = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 60 */     if (--this.field_75345_d > 0)
/* 61 */       return;  this.field_75345_d = 10;
/* 62 */     this.childAnimal.getNavigator().tryMoveToEntityLiving(this.parentAnimal, this.field_75347_c);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIFollowParent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */