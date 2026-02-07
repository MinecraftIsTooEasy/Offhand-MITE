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
/*     */ public class StitchHolder
/*     */   implements Comparable
/*     */ {
/*     */   private final TextureAtlasSprite theTexture;
/*     */   private final int width;
/*     */   private final int height;
/*     */   private boolean rotated;
/* 215 */   private float scaleFactor = 1.0F;
/*     */   
/*     */   public StitchHolder(TextureAtlasSprite textureAtlasSprite) {
/* 218 */     this.theTexture = textureAtlasSprite;
/* 219 */     this.width = textureAtlasSprite.getIconWidth();
/* 220 */     this.height = textureAtlasSprite.getIconHeight();
/*     */     
/* 222 */     this.rotated = (ceil16(this.height) > ceil16(this.width));
/*     */   }
/*     */   
/*     */   public TextureAtlasSprite getAtlasSprite() {
/* 226 */     return this.theTexture;
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 230 */     return this.rotated ? ceil16((int)(this.height * this.scaleFactor)) : ceil16((int)(this.width * this.scaleFactor));
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 234 */     return this.rotated ? ceil16((int)(this.width * this.scaleFactor)) : ceil16((int)(this.height * this.scaleFactor));
/*     */   }
/*     */   
/*     */   public void rotate() {
/* 238 */     this.rotated = !this.rotated;
/*     */   }
/*     */   
/*     */   public boolean isRotated() {
/* 242 */     return this.rotated;
/*     */   }
/*     */   
/*     */   private int ceil16(int i) {
/* 246 */     return (i >> 0) + (((i & 0x0) == 0) ? 0 : 1) << 0;
/*     */   }
/*     */   
/*     */   public void setNewDimension(int i) {
/* 250 */     if (this.width <= i || this.height <= i) {
/*     */       return;
/*     */     }
/*     */     
/* 254 */     this.scaleFactor = i / Math.min(this.width, this.height);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 259 */     return "Holder{width=" + this.width + ", height=" + this.height + '}';
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareToStitchHolder(StitchHolder stitchHolder) {
/*     */     boolean bool;
/* 268 */     if (getHeight() == stitchHolder.getHeight()) {
/* 269 */       if (getWidth() == stitchHolder.getWidth()) {
/* 270 */         if (this.theTexture.getIconName() == null) {
/* 271 */           return (stitchHolder.theTexture.getIconName() == null) ? 0 : -1;
/*     */         }
/* 273 */         return this.theTexture.getIconName().compareTo(stitchHolder.theTexture.getIconName());
/*     */       } 
/* 275 */       bool = (getWidth() < stitchHolder.getWidth()) ? true : true;
/*     */     } else {
/* 277 */       bool = (getHeight() < stitchHolder.getHeight()) ? true : true;
/*     */     } 
/* 279 */     return bool;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StitchHolder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */