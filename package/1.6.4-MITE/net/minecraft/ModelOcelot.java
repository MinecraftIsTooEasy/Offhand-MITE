/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelOcelot
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer ocelotBackLeftLeg;
/*     */   ModelRenderer ocelotBackRightLeg;
/*     */   ModelRenderer ocelotFrontLeftLeg;
/*     */   ModelRenderer ocelotFrontRightLeg;
/*     */   ModelRenderer ocelotTail;
/*     */   ModelRenderer ocelotTail2;
/*     */   ModelRenderer ocelotHead;
/*     */   ModelRenderer ocelotBody;
/*  22 */   int field_78163_i = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModelOcelot() {
/*  43 */     setTextureOffset("head.main", 0, 0);
/*  44 */     setTextureOffset("head.nose", 0, 24);
/*  45 */     setTextureOffset("head.ear1", 0, 10);
/*  46 */     setTextureOffset("head.ear2", 6, 10);
/*     */     
/*  48 */     this.ocelotHead = new ModelRenderer(this, "head");
/*  49 */     this.ocelotHead.addBox("main", -2.5F, -2.0F, -3.0F, 5, 4, 5);
/*  50 */     this.ocelotHead.addBox("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2);
/*  51 */     this.ocelotHead.addBox("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2);
/*  52 */     this.ocelotHead.addBox("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2);
/*  53 */     this.ocelotHead.setRotationPoint(0.0F, 15.0F, -9.0F);
/*     */     
/*  55 */     this.ocelotBody = new ModelRenderer(this, 20, 0);
/*  56 */     this.ocelotBody.addBox(-2.0F, 3.0F, -8.0F, 4, 16, 6, 0.0F);
/*  57 */     this.ocelotBody.setRotationPoint(0.0F, 12.0F, -10.0F);
/*     */     
/*  59 */     this.ocelotTail = new ModelRenderer(this, 0, 15);
/*  60 */     this.ocelotTail.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 1);
/*  61 */     this.ocelotTail.rotateAngleX = 0.9F;
/*  62 */     this.ocelotTail.setRotationPoint(0.0F, 15.0F, 8.0F);
/*     */     
/*  64 */     this.ocelotTail2 = new ModelRenderer(this, 4, 15);
/*  65 */     this.ocelotTail2.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 1);
/*  66 */     this.ocelotTail2.setRotationPoint(0.0F, 20.0F, 14.0F);
/*     */     
/*  68 */     this.ocelotBackLeftLeg = new ModelRenderer(this, 8, 13);
/*  69 */     this.ocelotBackLeftLeg.addBox(-1.0F, 0.0F, 1.0F, 2, 6, 2);
/*  70 */     this.ocelotBackLeftLeg.setRotationPoint(1.1F, 18.0F, 5.0F);
/*     */     
/*  72 */     this.ocelotBackRightLeg = new ModelRenderer(this, 8, 13);
/*  73 */     this.ocelotBackRightLeg.addBox(-1.0F, 0.0F, 1.0F, 2, 6, 2);
/*  74 */     this.ocelotBackRightLeg.setRotationPoint(-1.1F, 18.0F, 5.0F);
/*     */     
/*  76 */     this.ocelotFrontLeftLeg = new ModelRenderer(this, 40, 0);
/*  77 */     this.ocelotFrontLeftLeg.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2);
/*  78 */     this.ocelotFrontLeftLeg.setRotationPoint(1.2F, 13.8F, -5.0F);
/*     */     
/*  80 */     this.ocelotFrontRightLeg = new ModelRenderer(this, 40, 0);
/*  81 */     this.ocelotFrontRightLeg.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2);
/*  82 */     this.ocelotFrontRightLeg.setRotationPoint(-1.2F, 13.8F, -5.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
/*  87 */     setRotationAngles(f, g, h, i, j, k, entity);
/*  88 */     if (this.isChild) {
/*  89 */       float f1 = 2.0F;
/*  90 */       GL11.glPushMatrix();
/*  91 */       GL11.glScalef(1.5F / f1, 1.5F / f1, 1.5F / f1);
/*  92 */       GL11.glTranslatef(0.0F, 10.0F * k, 4.0F * k);
/*  93 */       this.ocelotHead.render(k);
/*  94 */       GL11.glPopMatrix();
/*  95 */       GL11.glPushMatrix();
/*  96 */       GL11.glScalef(1.0F / f1, 1.0F / f1, 1.0F / f1);
/*  97 */       GL11.glTranslatef(0.0F, 24.0F * k, 0.0F);
/*  98 */       this.ocelotBody.render(k);
/*  99 */       this.ocelotBackLeftLeg.render(k);
/* 100 */       this.ocelotBackRightLeg.render(k);
/* 101 */       this.ocelotFrontLeftLeg.render(k);
/* 102 */       this.ocelotFrontRightLeg.render(k);
/* 103 */       this.ocelotTail.render(k);
/* 104 */       this.ocelotTail2.render(k);
/* 105 */       GL11.glPopMatrix();
/*     */     } else {
/* 107 */       this.ocelotHead.render(k);
/* 108 */       this.ocelotBody.render(k);
/* 109 */       this.ocelotTail.render(k);
/* 110 */       this.ocelotTail2.render(k);
/* 111 */       this.ocelotBackLeftLeg.render(k);
/* 112 */       this.ocelotBackRightLeg.render(k);
/* 113 */       this.ocelotFrontLeftLeg.render(k);
/* 114 */       this.ocelotFrontRightLeg.render(k);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/* 152 */     this.ocelotHead.rotateAngleX = j / 57.295776F;
/* 153 */     this.ocelotHead.rotateAngleY = i / 57.295776F;
/*     */     
/* 155 */     if (this.field_78163_i != 3) {
/* 156 */       this.ocelotBody.rotateAngleX = 1.5707964F;
/* 157 */       if (this.field_78163_i == 2) {
/* 158 */         this.ocelotBackLeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.0F * g;
/* 159 */         this.ocelotBackRightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + 0.3F) * 1.0F * g;
/* 160 */         this.ocelotFrontLeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.1415927F + 0.3F) * 1.0F * g;
/* 161 */         this.ocelotFrontRightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.0F * g;
/* 162 */         this.ocelotTail2.rotateAngleX = 1.7278761F + 0.31415927F * MathHelper.cos(f) * g;
/*     */       } else {
/* 164 */         this.ocelotBackLeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.0F * g;
/* 165 */         this.ocelotBackRightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.0F * g;
/* 166 */         this.ocelotFrontLeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.0F * g;
/* 167 */         this.ocelotFrontRightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.0F * g;
/*     */         
/* 169 */         if (this.field_78163_i == 1) { this.ocelotTail2.rotateAngleX = 1.7278761F + 0.7853982F * MathHelper.cos(f) * g; }
/* 170 */         else { this.ocelotTail2.rotateAngleX = 1.7278761F + 0.47123894F * MathHelper.cos(f) * g; }
/*     */       
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setLivingAnimations(EntityLivingBase entityLivingBase, float f, float g, float h) {
/* 177 */     EntityOcelot entityOcelot = (EntityOcelot)entityLivingBase;
/*     */     
/* 179 */     this.ocelotBody.rotationPointY = 12.0F;
/* 180 */     this.ocelotBody.rotationPointZ = -10.0F;
/* 181 */     this.ocelotHead.rotationPointY = 15.0F;
/* 182 */     this.ocelotHead.rotationPointZ = -9.0F;
/* 183 */     this.ocelotTail.rotationPointY = 15.0F;
/* 184 */     this.ocelotTail.rotationPointZ = 8.0F;
/* 185 */     this.ocelotTail2.rotationPointY = 20.0F;
/* 186 */     this.ocelotTail2.rotationPointZ = 14.0F;
/* 187 */     this.ocelotFrontRightLeg.rotationPointY = 13.8F;
/* 188 */     this.ocelotFrontRightLeg.rotationPointZ = -5.0F;
/* 189 */     this.ocelotBackRightLeg.rotationPointY = 18.0F;
/* 190 */     this.ocelotBackRightLeg.rotationPointZ = 5.0F;
/* 191 */     this.ocelotTail.rotateAngleX = 0.9F;
/*     */     
/* 193 */     if (entityOcelot.isSneaking()) {
/* 194 */       this.ocelotBody.rotationPointY++;
/* 195 */       this.ocelotHead.rotationPointY += 2.0F;
/* 196 */       this.ocelotTail.rotationPointY++;
/* 197 */       this.ocelotTail2.rotationPointY += -4.0F;
/* 198 */       this.ocelotTail2.rotationPointZ += 2.0F;
/* 199 */       this.ocelotTail.rotateAngleX = 1.5707964F;
/* 200 */       this.ocelotTail2.rotateAngleX = 1.5707964F;
/* 201 */       this.field_78163_i = 0;
/* 202 */     } else if (entityOcelot.isSprinting()) {
/* 203 */       this.ocelotTail2.rotationPointY = this.ocelotTail.rotationPointY;
/* 204 */       this.ocelotTail2.rotationPointZ += 2.0F;
/* 205 */       this.ocelotTail.rotateAngleX = 1.5707964F;
/* 206 */       this.ocelotTail2.rotateAngleX = 1.5707964F;
/* 207 */       this.field_78163_i = 2;
/* 208 */     } else if (entityOcelot.isSitting()) {
/* 209 */       this.ocelotBody.rotateAngleX = 0.7853982F;
/* 210 */       this.ocelotBody.rotationPointY += -4.0F;
/* 211 */       this.ocelotBody.rotationPointZ += 5.0F;
/* 212 */       this.ocelotHead.rotationPointY += -3.3F;
/* 213 */       this.ocelotHead.rotationPointZ++;
/*     */       
/* 215 */       this.ocelotTail.rotationPointY += 8.0F;
/* 216 */       this.ocelotTail.rotationPointZ += -2.0F;
/* 217 */       this.ocelotTail2.rotationPointY += 2.0F;
/* 218 */       this.ocelotTail2.rotationPointZ += -0.8F;
/* 219 */       this.ocelotTail.rotateAngleX = 1.7278761F;
/* 220 */       this.ocelotTail2.rotateAngleX = 2.670354F;
/*     */       
/* 222 */       this.ocelotFrontRightLeg.rotateAngleX = -0.15707964F;
/* 223 */       this.ocelotFrontRightLeg.rotationPointY = 15.8F;
/* 224 */       this.ocelotFrontRightLeg.rotationPointZ = -7.0F;
/*     */       
/* 226 */       this.ocelotBackRightLeg.rotateAngleX = -1.5707964F;
/* 227 */       this.ocelotBackRightLeg.rotationPointY = 21.0F;
/* 228 */       this.ocelotBackRightLeg.rotationPointZ = 1.0F;
/* 229 */       this.field_78163_i = 3;
/*     */     } else {
/* 231 */       this.field_78163_i = 1;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelOcelot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */