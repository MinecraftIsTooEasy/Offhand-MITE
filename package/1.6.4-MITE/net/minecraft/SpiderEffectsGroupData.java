/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ public class SpiderEffectsGroupData
/*    */   implements EntityLivingData
/*    */ {
/*    */   public int field_111105_a;
/*    */   
/*    */   public void func_111104_a(Random par1Random) {
/* 12 */     int var2 = par1Random.nextInt(4);
/*    */     
/* 14 */     if (var2 <= 1) {
/*    */       
/* 16 */       this.field_111105_a = Potion.moveSpeed.id;
/*    */     }
/* 18 */     else if (var2 <= 2) {
/*    */       
/* 20 */       this.field_111105_a = Potion.damageBoost.id;
/*    */     }
/* 22 */     else if (var2 <= 3) {
/*    */       
/* 24 */       this.field_111105_a = Potion.regeneration.id;
/*    */     }
/* 26 */     else if (var2 <= 4) {
/*    */       
/* 28 */       this.field_111105_a = Potion.invisibility.id;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SpiderEffectsGroupData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */