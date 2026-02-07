/*    */ package net.minecraft;
/*    */ 
/*    */ public class ModelVillager
/*    */   extends ModelBase
/*    */ {
/*    */   public ModelRenderer villagerHead;
/*    */   public ModelRenderer villagerBody;
/*    */   public ModelRenderer villagerArms;
/*    */   
/*    */   public ModelVillager(float f) {
/* 11 */     this(f, 0.0F, 64, 64);
/*    */   }
/*    */   public ModelRenderer rightVillagerLeg; public ModelRenderer leftVillagerLeg; public ModelRenderer villagerNose;
/*    */   public ModelVillager(float f, float g, int i, int j) {
/* 15 */     this.villagerHead = (new ModelRenderer(this)).setTextureSize(i, j);
/* 16 */     this.villagerHead.setRotationPoint(0.0F, 0.0F + g, 0.0F);
/* 17 */     this.villagerHead.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, f);
/*    */     
/* 19 */     this.villagerNose = (new ModelRenderer(this)).setTextureSize(i, j);
/* 20 */     this.villagerNose.setRotationPoint(0.0F, g - 2.0F, 0.0F);
/* 21 */     this.villagerNose.setTextureOffset(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, f);
/* 22 */     this.villagerHead.addChild(this.villagerNose);
/*    */     
/* 24 */     this.villagerBody = (new ModelRenderer(this)).setTextureSize(i, j);
/* 25 */     this.villagerBody.setRotationPoint(0.0F, 0.0F + g, 0.0F);
/* 26 */     this.villagerBody.setTextureOffset(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, f);
/* 27 */     this.villagerBody.setTextureOffset(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, f + 0.5F);
/*    */     
/* 29 */     this.villagerArms = (new ModelRenderer(this)).setTextureSize(i, j);
/* 30 */     this.villagerArms.setRotationPoint(0.0F, 0.0F + g + 2.0F, 0.0F);
/* 31 */     this.villagerArms.setTextureOffset(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, f);
/* 32 */     this.villagerArms.setTextureOffset(44, 22).addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, f);
/* 33 */     this.villagerArms.setTextureOffset(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, f);
/*    */     
/* 35 */     this.rightVillagerLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(i, j);
/* 36 */     this.rightVillagerLeg.setRotationPoint(-2.0F, 12.0F + g, 0.0F);
/* 37 */     this.rightVillagerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, f);
/*    */     
/* 39 */     this.leftVillagerLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(i, j);
/* 40 */     this.leftVillagerLeg.mirror = true;
/* 41 */     this.leftVillagerLeg.setRotationPoint(2.0F, 12.0F + g, 0.0F);
/* 42 */     this.leftVillagerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, f);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
/* 47 */     setRotationAngles(f, g, h, i, j, k, entity);
/*    */     
/* 49 */     this.villagerHead.render(k);
/* 50 */     this.villagerBody.render(k);
/* 51 */     this.rightVillagerLeg.render(k);
/* 52 */     this.leftVillagerLeg.render(k);
/* 53 */     this.villagerArms.render(k);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/* 58 */     this.villagerHead.rotateAngleY = i / 57.295776F;
/* 59 */     this.villagerHead.rotateAngleX = j / 57.295776F;
/*    */     
/* 61 */     this.villagerArms.rotationPointY = 3.0F;
/* 62 */     this.villagerArms.rotationPointZ = -1.0F;
/* 63 */     this.villagerArms.rotateAngleX = -0.75F;
/*    */     
/* 65 */     this.rightVillagerLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * g * 0.5F;
/* 66 */     this.leftVillagerLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * g * 0.5F;
/* 67 */     this.rightVillagerLeg.rotateAngleY = 0.0F;
/* 68 */     this.leftVillagerLeg.rotateAngleY = 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelVillager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */