/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelSilverfish
/*     */   extends ModelBase
/*     */ {
/*     */   private ModelRenderer[] silverfishBodyParts;
/*     */   private ModelRenderer[] silverfishWings;
/*  13 */   private float[] field_78170_c = new float[7];
/*     */   
/*  15 */   private static final int[][] silverfishBoxLength = new int[][] { { 3, 2, 2 }, { 4, 3, 2 }, { 6, 4, 3 }, { 3, 3, 3 }, { 2, 2, 3 }, { 2, 1, 2 }, { 1, 1, 2 } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  32 */   private static final int[][] silverfishTexturePositions = new int[][] { { 0, 0 }, { 0, 4 }, { 0, 9 }, { 0, 16 }, { 0, 22 }, { 11, 0 }, { 13, 4 } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModelSilverfish() {
/*  51 */     this.silverfishBodyParts = new ModelRenderer[7];
/*  52 */     float f = -3.5F;
/*  53 */     for (byte b = 0; b < this.silverfishBodyParts.length; b++) {
/*  54 */       this.silverfishBodyParts[b] = new ModelRenderer(this, silverfishTexturePositions[b][0], silverfishTexturePositions[b][1]);
/*  55 */       this.silverfishBodyParts[b].addBox(silverfishBoxLength[b][0] * -0.5F, 0.0F, silverfishBoxLength[b][2] * -0.5F, silverfishBoxLength[b][0], silverfishBoxLength[b][1], silverfishBoxLength[b][2]);
/*  56 */       this.silverfishBodyParts[b].setRotationPoint(0.0F, (24 - silverfishBoxLength[b][1]), f);
/*  57 */       this.field_78170_c[b] = f;
/*  58 */       if (b < this.silverfishBodyParts.length - 1) {
/*  59 */         f += (silverfishBoxLength[b][2] + silverfishBoxLength[b + 1][2]) * 0.5F;
/*     */       }
/*     */     } 
/*     */     
/*  63 */     this.silverfishWings = new ModelRenderer[3];
/*  64 */     this.silverfishWings[0] = new ModelRenderer(this, 20, 0);
/*  65 */     this.silverfishWings[0].addBox(-5.0F, 0.0F, silverfishBoxLength[2][2] * -0.5F, 10, 8, silverfishBoxLength[2][2]);
/*  66 */     this.silverfishWings[0].setRotationPoint(0.0F, 16.0F, this.field_78170_c[2]);
/*  67 */     this.silverfishWings[1] = new ModelRenderer(this, 20, 11);
/*  68 */     this.silverfishWings[1].addBox(-3.0F, 0.0F, silverfishBoxLength[4][2] * -0.5F, 6, 4, silverfishBoxLength[4][2]);
/*  69 */     this.silverfishWings[1].setRotationPoint(0.0F, 20.0F, this.field_78170_c[4]);
/*  70 */     this.silverfishWings[2] = new ModelRenderer(this, 20, 18);
/*  71 */     this.silverfishWings[2].addBox(-3.0F, 0.0F, silverfishBoxLength[4][2] * -0.5F, 6, 5, silverfishBoxLength[1][2]);
/*  72 */     this.silverfishWings[2].setRotationPoint(0.0F, 19.0F, this.field_78170_c[1]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
/*  81 */     setRotationAngles(f, g, h, i, j, k, entity);
/*     */     byte b;
/*  83 */     for (b = 0; b < this.silverfishBodyParts.length; b++) {
/*  84 */       this.silverfishBodyParts[b].render(k);
/*     */     }
/*  86 */     for (b = 0; b < this.silverfishWings.length; b++) {
/*  87 */       this.silverfishWings[b].render(k);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/*  94 */     for (byte b = 0; b < this.silverfishBodyParts.length; b++) {
/*  95 */       (this.silverfishBodyParts[b]).rotateAngleY = MathHelper.cos(h * 0.9F + b * 0.15F * 3.1415927F) * 3.1415927F * 0.05F * (1 + Math.abs(b - 2));
/*  96 */       (this.silverfishBodyParts[b]).rotationPointX = MathHelper.sin(h * 0.9F + b * 0.15F * 3.1415927F) * 3.1415927F * 0.2F * Math.abs(b - 2);
/*     */     } 
/*     */     
/*  99 */     (this.silverfishWings[0]).rotateAngleY = (this.silverfishBodyParts[2]).rotateAngleY;
/* 100 */     (this.silverfishWings[1]).rotateAngleY = (this.silverfishBodyParts[4]).rotateAngleY;
/* 101 */     (this.silverfishWings[1]).rotationPointX = (this.silverfishBodyParts[4]).rotationPointX;
/* 102 */     (this.silverfishWings[2]).rotateAngleY = (this.silverfishBodyParts[1]).rotateAngleY;
/* 103 */     (this.silverfishWings[2]).rotationPointX = (this.silverfishBodyParts[1]).rotationPointX;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelSilverfish.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */