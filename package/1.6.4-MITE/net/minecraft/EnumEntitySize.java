/*     */ package net.minecraft;
/*     */ 
/*     */ public enum EnumEntitySize
/*     */ {
/*   5 */   SIZE_1,
/*   6 */   SIZE_2,
/*   7 */   SIZE_3,
/*   8 */   SIZE_4,
/*   9 */   SIZE_5,
/*  10 */   SIZE_6;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int multiplyByNAndRound(double par1, int n) {
/* 101 */     double var3 = par1 - MathHelper.floor_double(par1) + 0.5D;
/*     */     
/* 103 */     switch (EnumEntitySizeHelper.field_96565_a[ordinal()]) {
/*     */       
/*     */       case 1:
/* 106 */         if (var3 < 0.0D) {
/*     */           
/* 108 */           if (var3 < -0.3125D)
/*     */           {
/* 110 */             return MathHelper.ceiling_double_int(par1 * n);
/*     */           }
/*     */         }
/* 113 */         else if (var3 < 0.3125D) {
/*     */           
/* 115 */           return MathHelper.ceiling_double_int(par1 * n);
/*     */         } 
/*     */         
/* 118 */         return MathHelper.floor_double(par1 * n);
/*     */       
/*     */       case 2:
/* 121 */         if (var3 < 0.0D) {
/*     */           
/* 123 */           if (var3 < -0.3125D)
/*     */           {
/* 125 */             return MathHelper.floor_double(par1 * n);
/*     */           }
/*     */         }
/* 128 */         else if (var3 < 0.3125D) {
/*     */           
/* 130 */           return MathHelper.floor_double(par1 * n);
/*     */         } 
/*     */         
/* 133 */         return MathHelper.ceiling_double_int(par1 * n);
/*     */       
/*     */       case 3:
/* 136 */         if (var3 > 0.0D)
/*     */         {
/* 138 */           return MathHelper.floor_double(par1 * n);
/*     */         }
/*     */         
/* 141 */         return MathHelper.ceiling_double_int(par1 * n);
/*     */       
/*     */       case 4:
/* 144 */         if (var3 < 0.0D) {
/*     */           
/* 146 */           if (var3 < -0.1875D)
/*     */           {
/* 148 */             return MathHelper.ceiling_double_int(par1 * n);
/*     */           }
/*     */         }
/* 151 */         else if (var3 < 0.1875D) {
/*     */           
/* 153 */           return MathHelper.ceiling_double_int(par1 * n);
/*     */         } 
/*     */         
/* 156 */         return MathHelper.floor_double(par1 * n);
/*     */       
/*     */       case 5:
/* 159 */         if (var3 < 0.0D) {
/*     */           
/* 161 */           if (var3 < -0.1875D)
/*     */           {
/* 163 */             return MathHelper.floor_double(par1 * n);
/*     */           }
/*     */         }
/* 166 */         else if (var3 < 0.1875D) {
/*     */           
/* 168 */           return MathHelper.floor_double(par1 * n);
/*     */         } 
/*     */         
/* 171 */         return MathHelper.ceiling_double_int(par1 * n);
/*     */     } 
/*     */ 
/*     */     
/* 175 */     if (var3 > 0.0D)
/*     */     {
/* 177 */       return MathHelper.ceiling_double_int(par1 * n);
/*     */     }
/*     */ 
/*     */     
/* 181 */     return MathHelper.floor_double(par1 * n);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumEntitySize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */