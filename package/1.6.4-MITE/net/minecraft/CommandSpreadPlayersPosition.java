/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
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
/*     */ class CommandSpreadPlayersPosition
/*     */ {
/*     */   double field_111101_a;
/*     */   double field_111100_b;
/*     */   
/*     */   CommandSpreadPlayersPosition() {}
/*     */   
/*     */   CommandSpreadPlayersPosition(double d, double e) {
/* 235 */     this.field_111101_a = d;
/* 236 */     this.field_111100_b = e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   double func_111099_a(CommandSpreadPlayersPosition commandSpreadPlayersPosition) {
/* 245 */     double d1 = this.field_111101_a - commandSpreadPlayersPosition.field_111101_a;
/* 246 */     double d2 = this.field_111100_b - commandSpreadPlayersPosition.field_111100_b;
/*     */     
/* 248 */     return Math.sqrt(d1 * d1 + d2 * d2);
/*     */   }
/*     */   
/*     */   void func_111095_a() {
/* 252 */     double d = func_111096_b();
/* 253 */     this.field_111101_a /= d;
/* 254 */     this.field_111100_b /= d;
/*     */   }
/*     */   
/*     */   float func_111096_b() {
/* 258 */     return MathHelper.sqrt_double(this.field_111101_a * this.field_111101_a + this.field_111100_b * this.field_111100_b);
/*     */   }
/*     */   
/*     */   public void func_111094_b(CommandSpreadPlayersPosition commandSpreadPlayersPosition) {
/* 262 */     this.field_111101_a -= commandSpreadPlayersPosition.field_111101_a;
/* 263 */     this.field_111100_b -= commandSpreadPlayersPosition.field_111100_b;
/*     */   }
/*     */   
/*     */   public boolean func_111093_a(double d, double e, double f, double g) {
/* 267 */     boolean bool = false;
/*     */     
/* 269 */     if (this.field_111101_a < d) {
/* 270 */       this.field_111101_a = d;
/* 271 */       bool = true;
/* 272 */     } else if (this.field_111101_a > f) {
/* 273 */       this.field_111101_a = f;
/* 274 */       bool = true;
/*     */     } 
/*     */     
/* 277 */     if (this.field_111100_b < e) {
/* 278 */       this.field_111100_b = e;
/* 279 */       bool = true;
/* 280 */     } else if (this.field_111100_b > g) {
/* 281 */       this.field_111100_b = g;
/* 282 */       bool = true;
/*     */     } 
/*     */     
/* 285 */     return bool;
/*     */   }
/*     */   
/*     */   public int func_111092_a(World world) {
/* 289 */     int i = MathHelper.floor_double(this.field_111101_a);
/* 290 */     int j = MathHelper.floor_double(this.field_111100_b);
/*     */     
/* 292 */     for (char c = 'Ā'; c > '\000'; c--) {
/* 293 */       int k = world.getBlockId(i, c, j);
/*     */       
/* 295 */       if (k != 0) {
/* 296 */         return c + 1;
/*     */       }
/*     */     } 
/*     */     
/* 300 */     return 257;
/*     */   }
/*     */   
/*     */   public boolean func_111098_b(World world) {
/* 304 */     int i = MathHelper.floor_double(this.field_111101_a);
/* 305 */     int j = MathHelper.floor_double(this.field_111100_b);
/*     */     
/* 307 */     for (char c = 'Ā'; c > '\000'; c--) {
/* 308 */       int k = world.getBlockId(i, c, j);
/*     */       
/* 310 */       if (k != 0) {
/* 311 */         Material material = (Block.blocksList[k]).blockMaterial;
/*     */         
/* 313 */         return (!material.isLiquid() && material != Material.fire);
/*     */       } 
/*     */     } 
/*     */     
/* 317 */     return false;
/*     */   }
/*     */   
/*     */   public void func_111097_a(Random random, double d, double e, double f, double g) {
/* 321 */     this.field_111101_a = MathHelper.getRandomDoubleInRange(random, d, f);
/* 322 */     this.field_111100_b = MathHelper.getRandomDoubleInRange(random, e, g);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandSpreadPlayersPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */