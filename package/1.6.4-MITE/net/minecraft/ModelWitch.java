/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelWitch
/*    */   extends ModelVillager
/*    */ {
/*    */   public boolean field_82900_g;
/*    */   private ModelRenderer field_82901_h;
/*    */   private ModelRenderer witchHat;
/*    */   
/*    */   public ModelWitch(float f) {
/* 13 */     super(f, 0.0F, 64, 128);
/*    */     
/* 15 */     this.field_82901_h = (new ModelRenderer(this)).setTextureSize(64, 128);
/* 16 */     this.field_82901_h.setRotationPoint(0.0F, -2.0F, 0.0F);
/* 17 */     this.field_82901_h.setTextureOffset(0, 0).addBox(0.0F, 3.0F, -6.75F, 1, 1, 1, -0.25F);
/* 18 */     this.villagerNose.addChild(this.field_82901_h);
/*    */     
/* 20 */     this.witchHat = (new ModelRenderer(this)).setTextureSize(64, 128);
/* 21 */     this.witchHat.setRotationPoint(-5.0F, -10.03125F, -5.0F);
/* 22 */     this.witchHat.setTextureOffset(0, 64).addBox(0.0F, 0.0F, 0.0F, 10, 2, 10);
/* 23 */     this.villagerHead.addChild(this.witchHat);
/*    */     
/* 25 */     ModelRenderer modelRenderer1 = (new ModelRenderer(this)).setTextureSize(64, 128);
/* 26 */     modelRenderer1.setRotationPoint(1.75F, -4.0F, 2.0F);
/* 27 */     modelRenderer1.setTextureOffset(0, 76).addBox(0.0F, 0.0F, 0.0F, 7, 4, 7);
/* 28 */     modelRenderer1.rotateAngleX = -0.05235988F;
/* 29 */     modelRenderer1.rotateAngleZ = 0.02617994F;
/* 30 */     this.witchHat.addChild(modelRenderer1);
/*    */     
/* 32 */     ModelRenderer modelRenderer2 = (new ModelRenderer(this)).setTextureSize(64, 128);
/* 33 */     modelRenderer2.setRotationPoint(1.75F, -4.0F, 2.0F);
/* 34 */     modelRenderer2.setTextureOffset(0, 87).addBox(0.0F, 0.0F, 0.0F, 4, 4, 4);
/* 35 */     modelRenderer2.rotateAngleX = -0.10471976F;
/* 36 */     modelRenderer2.rotateAngleZ = 0.05235988F;
/* 37 */     modelRenderer1.addChild(modelRenderer2);
/*    */     
/* 39 */     ModelRenderer modelRenderer3 = (new ModelRenderer(this)).setTextureSize(64, 128);
/* 40 */     modelRenderer3.setRotationPoint(1.75F, -2.0F, 2.0F);
/* 41 */     modelRenderer3.setTextureOffset(0, 95).addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.25F);
/* 42 */     modelRenderer3.rotateAngleX = -0.20943952F;
/* 43 */     modelRenderer3.rotateAngleZ = 0.10471976F;
/* 44 */     modelRenderer2.addChild(modelRenderer3);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/* 49 */     super.setRotationAngles(f, g, h, i, j, k, entity);
/*    */     
/* 51 */     this.villagerNose.offsetX = this.villagerNose.offsetY = this.villagerNose.offsetZ = 0.0F;
/*    */     
/* 53 */     float f1 = 0.01F * (entity.entityId % 10);
/* 54 */     this.villagerNose.rotateAngleX = MathHelper.sin(entity.ticksExisted * f1) * 4.5F * 3.1415927F / 180.0F;
/* 55 */     this.villagerNose.rotateAngleY = 0.0F;
/* 56 */     this.villagerNose.rotateAngleZ = MathHelper.cos(entity.ticksExisted * f1) * 2.5F * 3.1415927F / 180.0F;
/*    */     
/* 58 */     if (this.field_82900_g) {
/* 59 */       this.villagerNose.rotateAngleX = -0.9F;
/* 60 */       this.villagerNose.offsetZ = -0.09375F;
/* 61 */       this.villagerNose.offsetY = 0.1875F;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelWitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */