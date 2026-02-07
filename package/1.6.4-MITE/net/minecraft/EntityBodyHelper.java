/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityBodyHelper
/*    */ {
/*    */   private EntityLivingBase theLiving;
/*    */   private int field_75666_b;
/*    */   private float field_75667_c;
/*    */   private boolean has_been_initialized;
/*    */   
/*    */   public EntityBodyHelper(EntityLivingBase par1EntityLivingBase) {
/* 14 */     this.theLiving = par1EntityLivingBase;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75664_a() {
/* 19 */     double var1 = this.theLiving.posX - this.theLiving.prevPosX;
/* 20 */     double var3 = this.theLiving.posZ - this.theLiving.prevPosZ;
/*    */ 
/*    */     
/* 23 */     if (!this.has_been_initialized || var1 * var1 + var3 * var3 > 2.500000277905201E-7D) {
/*    */       
/* 25 */       this.theLiving.renderYawOffset = this.theLiving.rotationYaw;
/* 26 */       this.theLiving.rotationYawHead = func_75665_a(this.theLiving.renderYawOffset, this.theLiving.rotationYawHead, 75.0F);
/* 27 */       this.field_75667_c = this.theLiving.rotationYawHead;
/* 28 */       this.field_75666_b = 0;
/*    */       
/* 30 */       this.has_been_initialized = true;
/*    */     }
/*    */     else {
/*    */       
/* 34 */       float var5 = 75.0F;
/*    */       
/* 36 */       if (Math.abs(this.theLiving.rotationYawHead - this.field_75667_c) > 15.0F) {
/*    */         
/* 38 */         this.field_75666_b = 0;
/* 39 */         this.field_75667_c = this.theLiving.rotationYawHead;
/*    */       }
/*    */       else {
/*    */         
/* 43 */         this.field_75666_b++;
/* 44 */         boolean var6 = true;
/*    */         
/* 46 */         if (this.field_75666_b > 10)
/*    */         {
/* 48 */           var5 = Math.max(1.0F - (this.field_75666_b - 10) / 10.0F, 0.0F) * 75.0F;
/*    */         }
/*    */       } 
/*    */       
/* 52 */       this.theLiving.renderYawOffset = func_75665_a(this.theLiving.rotationYawHead, this.theLiving.renderYawOffset, var5);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private float func_75665_a(float par1, float par2, float par3) {
/* 58 */     float var4 = MathHelper.wrapAngleTo180_float(par1 - par2);
/*    */     
/* 60 */     if (var4 < -par3)
/*    */     {
/* 62 */       var4 = -par3;
/*    */     }
/*    */     
/* 65 */     if (var4 >= par3)
/*    */     {
/* 67 */       var4 = par3;
/*    */     }
/*    */     
/* 70 */     return par1 - var4;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityBodyHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */