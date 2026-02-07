/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TextureCompass
/*    */   extends TextureAtlasSprite
/*    */ {
/*    */   public double currentAngle;
/*    */   public double angleDelta;
/*    */   
/*    */   public TextureCompass(String string) {
/* 15 */     super(string);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateAnimation() {
/* 20 */     Minecraft minecraft = Minecraft.getMinecraft();
/*    */     
/* 22 */     if (minecraft.theWorld != null && minecraft.thePlayer != null) {
/* 23 */       updateCompass(minecraft.theWorld, minecraft.thePlayer.posX, minecraft.thePlayer.posZ, minecraft.thePlayer.rotationYaw, false, false);
/*    */     } else {
/* 25 */       updateCompass((World)null, 0.0D, 0.0D, 0.0D, true, false);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateCompass(World world, double d, double e, double f, boolean bl, boolean bl2) {
/* 32 */     if (this.framesTextureData.isEmpty()) {
/*    */       return;
/*    */     }
/*    */     
/* 36 */     double d1 = 0.0D;
/* 37 */     if (world != null && !bl) {
/* 38 */       ChunkCoordinates chunkCoordinates = world.getSpawnPoint();
/* 39 */       double d2 = chunkCoordinates.posX - d;
/* 40 */       double d3 = chunkCoordinates.posZ - e;
/* 41 */       f %= 360.0D;
/* 42 */       d1 = -((f - 90.0D) * Math.PI / 180.0D - Math.atan2(d3, d2));
/* 43 */       if (!world.provider.isSurfaceWorld()) {
/* 44 */         d1 = Math.random() * 3.1415927410125732D * 2.0D;
/*    */       }
/*    */     } 
/*    */     
/* 48 */     if (bl2) {
/* 49 */       this.currentAngle = d1;
/*    */     } else {
/* 51 */       double d2 = d1 - this.currentAngle;
/* 52 */       while (d2 < -3.141592653589793D)
/* 53 */         d2 += 6.283185307179586D; 
/* 54 */       while (d2 >= Math.PI)
/* 55 */         d2 -= 6.283185307179586D; 
/* 56 */       if (d2 < -1.0D) d2 = -1.0D; 
/* 57 */       if (d2 > 1.0D) d2 = 1.0D; 
/* 58 */       this.angleDelta += d2 * 0.1D;
/* 59 */       this.angleDelta *= 0.8D;
/* 60 */       this.currentAngle += this.angleDelta;
/*    */     } 
/*    */     
/* 63 */     int i = (int)((this.currentAngle / 6.283185307179586D + 1.0D) * this.framesTextureData.size()) % this.framesTextureData.size();
/* 64 */     while (i < 0) {
/* 65 */       i = (i + this.framesTextureData.size()) % this.framesTextureData.size();
/*    */     }
/* 67 */     if (i != this.frameCounter) {
/* 68 */       this.frameCounter = i;
/* 69 */       TextureUtil.uploadTextureSub(this.framesTextureData.get(this.frameCounter), this.width, this.height, this.originX, this.originY, false, false);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TextureCompass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */