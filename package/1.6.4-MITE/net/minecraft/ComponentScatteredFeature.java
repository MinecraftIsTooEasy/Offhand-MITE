/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class ComponentScatteredFeature
/*    */   extends StructureComponent
/*    */ {
/*    */   protected int scatteredFeatureSizeX;
/*    */   protected int scatteredFeatureSizeY;
/*    */   protected int scatteredFeatureSizeZ;
/* 27 */   protected int field_74936_d = -1;
/*    */ 
/*    */   
/*    */   public ComponentScatteredFeature() {}
/*    */ 
/*    */   
/*    */   protected ComponentScatteredFeature(Random random, int i, int j, int k, int l, int m, int n) {
/* 34 */     super(0);
/*    */     
/* 36 */     this.scatteredFeatureSizeX = l;
/* 37 */     this.scatteredFeatureSizeY = m;
/* 38 */     this.scatteredFeatureSizeZ = n;
/*    */     
/* 40 */     this.coordBaseMode = random.nextInt(4);
/*    */     
/* 42 */     switch (this.coordBaseMode) {
/*    */       case 0:
/*    */       case 2:
/* 45 */         this.boundingBox = new StructureBoundingBox(i, j, k, i + l - 1, j + m - 1, k + n - 1);
/*    */         return;
/*    */     } 
/* 48 */     this.boundingBox = new StructureBoundingBox(i, j, k, i + n - 1, j + m - 1, k + l - 1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_143012_a(NBTTagCompound nBTTagCompound) {
/* 55 */     nBTTagCompound.setInteger("Width", this.scatteredFeatureSizeX);
/* 56 */     nBTTagCompound.setInteger("Height", this.scatteredFeatureSizeY);
/* 57 */     nBTTagCompound.setInteger("Depth", this.scatteredFeatureSizeZ);
/* 58 */     nBTTagCompound.setInteger("HPos", this.field_74936_d);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_143011_b(NBTTagCompound nBTTagCompound) {
/* 63 */     this.scatteredFeatureSizeX = nBTTagCompound.getInteger("Width");
/* 64 */     this.scatteredFeatureSizeY = nBTTagCompound.getInteger("Height");
/* 65 */     this.scatteredFeatureSizeZ = nBTTagCompound.getInteger("Depth");
/* 66 */     this.field_74936_d = nBTTagCompound.getInteger("HPos");
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean func_74935_a(World world, StructureBoundingBox structureBoundingBox, int i) {
/* 71 */     if (this.field_74936_d >= 0) {
/* 72 */       return true;
/*    */     }
/*    */     
/* 75 */     int j = 0;
/* 76 */     byte b = 0;
/* 77 */     for (int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; k++) {
/* 78 */       for (int m = this.boundingBox.minX; m <= this.boundingBox.maxX; m++) {
/* 79 */         if (structureBoundingBox.isVecInside(m, 64, k)) {
/* 80 */           j += Math.max(world.getTopSolidOrLiquidBlock(m, k), world.provider.getAverageGroundLevel());
/* 81 */           b++;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 86 */     if (b == 0) {
/* 87 */       return false;
/*    */     }
/* 89 */     this.field_74936_d = j / b;
/* 90 */     this.boundingBox.offset(0, this.field_74936_d - this.boundingBox.minY + i, 0);
/* 91 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentScatteredFeature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */