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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WeightedRandomMinecart
/*     */   extends WeightedRandomItem
/*     */ {
/*     */   public final NBTTagCompound field_98222_b;
/*     */   public final String minecartName;
/*     */   
/*     */   public WeightedRandomMinecart(MobSpawnerBaseLogic mobSpawnerBaseLogic, NBTTagCompound nBTTagCompound) {
/* 272 */     super(nBTTagCompound.getInteger("Weight"));
/*     */     
/* 274 */     NBTTagCompound nBTTagCompound1 = nBTTagCompound.getCompoundTag("Properties");
/* 275 */     String str = nBTTagCompound.getString("Type");
/*     */     
/* 277 */     if (str.equals("Minecart")) {
/* 278 */       if (nBTTagCompound1 != null) {
/* 279 */         switch (nBTTagCompound1.getInteger("Type")) {
/*     */           case 1:
/* 281 */             str = "MinecartChest";
/*     */             break;
/*     */           case 2:
/* 284 */             str = "MinecartFurnace";
/*     */             break;
/*     */           case 0:
/* 287 */             str = "MinecartRideable";
/*     */             break;
/*     */         } 
/*     */       } else {
/* 291 */         str = "MinecartRideable";
/*     */       } 
/*     */     }
/*     */     
/* 295 */     this.field_98222_b = nBTTagCompound1;
/* 296 */     this.minecartName = str;
/*     */   }
/*     */   
/*     */   public WeightedRandomMinecart(MobSpawnerBaseLogic mobSpawnerBaseLogic, NBTTagCompound nBTTagCompound, String string) {
/* 300 */     super(1);
/*     */     
/* 302 */     if (string.equals("Minecart")) {
/* 303 */       if (nBTTagCompound != null) {
/* 304 */         switch (nBTTagCompound.getInteger("Type")) {
/*     */           case 1:
/* 306 */             string = "MinecartChest";
/*     */             break;
/*     */           case 2:
/* 309 */             string = "MinecartFurnace";
/*     */             break;
/*     */           case 0:
/* 312 */             string = "MinecartRideable";
/*     */             break;
/*     */         } 
/*     */       } else {
/* 316 */         string = "MinecartRideable";
/*     */       } 
/*     */     }
/*     */     
/* 320 */     this.field_98222_b = nBTTagCompound;
/* 321 */     this.minecartName = string;
/*     */   }
/*     */   
/*     */   public NBTTagCompound func_98220_a() {
/* 325 */     NBTTagCompound nBTTagCompound = new NBTTagCompound();
/*     */     
/* 327 */     nBTTagCompound.setCompoundTag("Properties", this.field_98222_b);
/* 328 */     nBTTagCompound.setString("Type", this.minecartName);
/* 329 */     nBTTagCompound.setInteger("Weight", this.itemWeight);
/*     */     
/* 331 */     return nBTTagCompound;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WeightedRandomMinecart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */