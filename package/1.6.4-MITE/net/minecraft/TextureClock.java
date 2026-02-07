/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TextureClock
/*    */   extends TextureAtlasSprite
/*    */ {
/*    */   private double field_94239_h;
/*    */   private double field_94240_i;
/*    */   
/*    */   public TextureClock(String string) {
/* 12 */     super(string);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateAnimation() {
/* 19 */     if (this.framesTextureData.isEmpty()) {
/*    */       return;
/*    */     }
/*    */     
/* 23 */     Minecraft minecraft = Minecraft.getMinecraft();
/*    */     
/* 25 */     double d1 = 0.0D;
/* 26 */     if (minecraft.theWorld != null && minecraft.thePlayer != null) {
/* 27 */       float f = minecraft.theWorld.getCelestialAngle(1.0F);
/* 28 */       d1 = f;
/* 29 */       if (!minecraft.theWorld.provider.isSurfaceWorld()) {
/* 30 */         d1 = Math.random();
/*    */       }
/*    */     } 
/*    */     
/* 34 */     double d2 = d1 - this.field_94239_h;
/* 35 */     while (d2 < -0.5D)
/* 36 */       d2++; 
/* 37 */     while (d2 >= 0.5D)
/* 38 */       d2--; 
/* 39 */     if (d2 < -1.0D) d2 = -1.0D; 
/* 40 */     if (d2 > 1.0D) d2 = 1.0D; 
/* 41 */     this.field_94240_i += d2 * 0.1D;
/* 42 */     this.field_94240_i *= 0.8D;
/*    */     
/* 44 */     this.field_94239_h += this.field_94240_i;
/*    */     
/* 46 */     int i = (int)((this.field_94239_h + 1.0D) * this.framesTextureData.size()) % this.framesTextureData.size();
/* 47 */     while (i < 0) {
/* 48 */       i = (i + this.framesTextureData.size()) % this.framesTextureData.size();
/*    */     }
/* 50 */     if (i != this.frameCounter) {
/* 51 */       this.frameCounter = i;
/* 52 */       TextureUtil.uploadTextureSub(this.framesTextureData.get(this.frameCounter), this.width, this.height, this.originX, this.originY, false, false);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TextureClock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */