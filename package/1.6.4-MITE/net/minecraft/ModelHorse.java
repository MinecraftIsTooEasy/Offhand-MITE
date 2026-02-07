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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelHorse
/*     */   extends ModelBase
/*     */ {
/*     */   private ModelRenderer head;
/*     */   private ModelRenderer mouthTop;
/*     */   private ModelRenderer mouthBottom;
/*     */   private ModelRenderer horseLeftEar;
/*     */   private ModelRenderer horseRightEar;
/*     */   private ModelRenderer field_110703_f;
/*     */   private ModelRenderer field_110704_g;
/*     */   private ModelRenderer neck;
/*     */   private ModelRenderer field_110717_i;
/*     */   private ModelRenderer mane;
/*     */   private ModelRenderer body;
/*     */   private ModelRenderer tailBase;
/*     */   private ModelRenderer tailMiddle;
/*     */   private ModelRenderer tailTip;
/*     */   private ModelRenderer backLeftLeg;
/*     */   private ModelRenderer backLeftShin;
/*     */   private ModelRenderer backLeftHoof;
/*     */   private ModelRenderer backRightLeg;
/*     */   private ModelRenderer backRightShin;
/*     */   private ModelRenderer backRightHoof;
/*     */   private ModelRenderer frontRightLeg;
/*     */   private ModelRenderer frontLeftShin;
/*     */   private ModelRenderer frontLeftHoof;
/*     */   private ModelRenderer field_110684_D;
/*     */   private ModelRenderer frontRightShin;
/*     */   private ModelRenderer frontRightHoof;
/*     */   private ModelRenderer field_110687_G;
/*     */   private ModelRenderer field_110695_H;
/*     */   private ModelRenderer field_110696_I;
/*     */   private ModelRenderer field_110697_J;
/*     */   private ModelRenderer field_110698_K;
/*     */   private ModelRenderer field_110691_L;
/*     */   private ModelRenderer field_110692_M;
/*     */   private ModelRenderer field_110693_N;
/*     */   private ModelRenderer field_110694_O;
/*     */   private ModelRenderer field_110700_P;
/*     */   private ModelRenderer field_110699_Q;
/*     */   private ModelRenderer field_110702_R;
/*     */   private ModelRenderer field_110701_S;
/*     */   
/*     */   public ModelHorse() {
/*  63 */     this.textureWidth = 128;
/*  64 */     this.textureHeight = 128;
/*     */ 
/*     */     
/*  67 */     this.body = new ModelRenderer(this, 0, 34);
/*  68 */     this.body.addBox(-5.0F, -8.0F, -19.0F, 10, 10, 24);
/*  69 */     this.body.setRotationPoint(0.0F, 11.0F, 9.0F);
/*     */     
/*  71 */     this.tailBase = new ModelRenderer(this, 44, 0);
/*  72 */     this.tailBase.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3);
/*  73 */     this.tailBase.setRotationPoint(0.0F, 3.0F, 14.0F);
/*  74 */     func_110682_a(this.tailBase, -1.134464F, 0.0F, 0.0F);
/*     */     
/*  76 */     this.tailMiddle = new ModelRenderer(this, 38, 7);
/*  77 */     this.tailMiddle.addBox(-1.5F, -2.0F, 3.0F, 3, 4, 7);
/*  78 */     this.tailMiddle.setRotationPoint(0.0F, 3.0F, 14.0F);
/*  79 */     func_110682_a(this.tailMiddle, -1.134464F, 0.0F, 0.0F);
/*     */     
/*  81 */     this.tailTip = new ModelRenderer(this, 24, 3);
/*  82 */     this.tailTip.addBox(-1.5F, -4.5F, 9.0F, 3, 4, 7);
/*  83 */     this.tailTip.setRotationPoint(0.0F, 3.0F, 14.0F);
/*  84 */     func_110682_a(this.tailTip, -1.40215F, 0.0F, 0.0F);
/*     */     
/*  86 */     this.backLeftLeg = new ModelRenderer(this, 78, 29);
/*  87 */     this.backLeftLeg.addBox(-2.5F, -2.0F, -2.5F, 4, 9, 5);
/*  88 */     this.backLeftLeg.setRotationPoint(4.0F, 9.0F, 11.0F);
/*     */     
/*  90 */     this.backLeftShin = new ModelRenderer(this, 78, 43);
/*  91 */     this.backLeftShin.addBox(-2.0F, 0.0F, -1.5F, 3, 5, 3);
/*  92 */     this.backLeftShin.setRotationPoint(4.0F, 16.0F, 11.0F);
/*     */     
/*  94 */     this.backLeftHoof = new ModelRenderer(this, 78, 51);
/*  95 */     this.backLeftHoof.addBox(-2.5F, 5.1F, -2.0F, 4, 3, 4);
/*  96 */     this.backLeftHoof.setRotationPoint(4.0F, 16.0F, 11.0F);
/*     */     
/*  98 */     this.backRightLeg = new ModelRenderer(this, 96, 29);
/*  99 */     this.backRightLeg.addBox(-1.5F, -2.0F, -2.5F, 4, 9, 5);
/* 100 */     this.backRightLeg.setRotationPoint(-4.0F, 9.0F, 11.0F);
/*     */     
/* 102 */     this.backRightShin = new ModelRenderer(this, 96, 43);
/* 103 */     this.backRightShin.addBox(-1.0F, 0.0F, -1.5F, 3, 5, 3);
/* 104 */     this.backRightShin.setRotationPoint(-4.0F, 16.0F, 11.0F);
/*     */     
/* 106 */     this.backRightHoof = new ModelRenderer(this, 96, 51);
/* 107 */     this.backRightHoof.addBox(-1.5F, 5.1F, -2.0F, 4, 3, 4);
/* 108 */     this.backRightHoof.setRotationPoint(-4.0F, 16.0F, 11.0F);
/*     */     
/* 110 */     this.frontRightLeg = new ModelRenderer(this, 44, 29);
/* 111 */     this.frontRightLeg.addBox(-1.9F, -1.0F, -2.1F, 3, 8, 4);
/* 112 */     this.frontRightLeg.setRotationPoint(4.0F, 9.0F, -8.0F);
/*     */     
/* 114 */     this.frontLeftShin = new ModelRenderer(this, 44, 41);
/* 115 */     this.frontLeftShin.addBox(-1.9F, 0.0F, -1.6F, 3, 5, 3);
/* 116 */     this.frontLeftShin.setRotationPoint(4.0F, 16.0F, -8.0F);
/*     */     
/* 118 */     this.frontLeftHoof = new ModelRenderer(this, 44, 51);
/* 119 */     this.frontLeftHoof.addBox(-2.4F, 5.1F, -2.1F, 4, 3, 4);
/* 120 */     this.frontLeftHoof.setRotationPoint(4.0F, 16.0F, -8.0F);
/*     */     
/* 122 */     this.field_110684_D = new ModelRenderer(this, 60, 29);
/* 123 */     this.field_110684_D.addBox(-1.1F, -1.0F, -2.1F, 3, 8, 4);
/* 124 */     this.field_110684_D.setRotationPoint(-4.0F, 9.0F, -8.0F);
/*     */     
/* 126 */     this.frontRightShin = new ModelRenderer(this, 60, 41);
/* 127 */     this.frontRightShin.addBox(-1.1F, 0.0F, -1.6F, 3, 5, 3);
/* 128 */     this.frontRightShin.setRotationPoint(-4.0F, 16.0F, -8.0F);
/*     */     
/* 130 */     this.frontRightHoof = new ModelRenderer(this, 60, 51);
/* 131 */     this.frontRightHoof.addBox(-1.6F, 5.1F, -2.1F, 4, 3, 4);
/* 132 */     this.frontRightHoof.setRotationPoint(-4.0F, 16.0F, -8.0F);
/*     */     
/* 134 */     this.head = new ModelRenderer(this, 0, 0);
/* 135 */     this.head.addBox(-2.5F, -10.0F, -1.5F, 5, 5, 7);
/* 136 */     this.head.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 137 */     func_110682_a(this.head, 0.5235988F, 0.0F, 0.0F);
/*     */     
/* 139 */     this.mouthTop = new ModelRenderer(this, 24, 18);
/* 140 */     this.mouthTop.addBox(-2.0F, -10.0F, -7.0F, 4, 3, 6);
/* 141 */     this.mouthTop.setRotationPoint(0.0F, 3.95F, -10.0F);
/* 142 */     func_110682_a(this.mouthTop, 0.5235988F, 0.0F, 0.0F);
/*     */     
/* 144 */     this.mouthBottom = new ModelRenderer(this, 24, 27);
/* 145 */     this.mouthBottom.addBox(-2.0F, -7.0F, -6.5F, 4, 2, 5);
/* 146 */     this.mouthBottom.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 147 */     func_110682_a(this.mouthBottom, 0.5235988F, 0.0F, 0.0F);
/*     */     
/* 149 */     this.head.addChild(this.mouthTop);
/* 150 */     this.head.addChild(this.mouthBottom);
/*     */     
/* 152 */     this.horseLeftEar = new ModelRenderer(this, 0, 0);
/* 153 */     this.horseLeftEar.addBox(0.45F, -12.0F, 4.0F, 2, 3, 1);
/* 154 */     this.horseLeftEar.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 155 */     func_110682_a(this.horseLeftEar, 0.5235988F, 0.0F, 0.0F);
/*     */     
/* 157 */     this.horseRightEar = new ModelRenderer(this, 0, 0);
/* 158 */     this.horseRightEar.addBox(-2.45F, -12.0F, 4.0F, 2, 3, 1);
/* 159 */     this.horseRightEar.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 160 */     func_110682_a(this.horseRightEar, 0.5235988F, 0.0F, 0.0F);
/*     */     
/* 162 */     this.field_110703_f = new ModelRenderer(this, 0, 12);
/* 163 */     this.field_110703_f.addBox(-2.0F, -16.0F, 4.0F, 2, 7, 1);
/* 164 */     this.field_110703_f.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 165 */     func_110682_a(this.field_110703_f, 0.5235988F, 0.0F, 0.2617994F);
/*     */     
/* 167 */     this.field_110704_g = new ModelRenderer(this, 0, 12);
/* 168 */     this.field_110704_g.addBox(0.0F, -16.0F, 4.0F, 2, 7, 1);
/* 169 */     this.field_110704_g.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 170 */     func_110682_a(this.field_110704_g, 0.5235988F, 0.0F, -0.2617994F);
/*     */     
/* 172 */     this.neck = new ModelRenderer(this, 0, 12);
/* 173 */     this.neck.addBox(-2.05F, -9.8F, -2.0F, 4, 14, 8);
/* 174 */     this.neck.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 175 */     func_110682_a(this.neck, 0.5235988F, 0.0F, 0.0F);
/*     */     
/* 177 */     this.field_110687_G = new ModelRenderer(this, 0, 34);
/* 178 */     this.field_110687_G.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3);
/* 179 */     this.field_110687_G.setRotationPoint(-7.5F, 3.0F, 10.0F);
/* 180 */     func_110682_a(this.field_110687_G, 0.0F, 1.570796F, 0.0F);
/*     */     
/* 182 */     this.field_110695_H = new ModelRenderer(this, 0, 47);
/* 183 */     this.field_110695_H.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3);
/* 184 */     this.field_110695_H.setRotationPoint(4.5F, 3.0F, 10.0F);
/* 185 */     func_110682_a(this.field_110695_H, 0.0F, 1.570796F, 0.0F);
/*     */     
/* 187 */     this.field_110696_I = new ModelRenderer(this, 80, 0);
/* 188 */     this.field_110696_I.addBox(-5.0F, 0.0F, -3.0F, 10, 1, 8);
/* 189 */     this.field_110696_I.setRotationPoint(0.0F, 2.0F, 2.0F);
/*     */     
/* 191 */     this.field_110697_J = new ModelRenderer(this, 106, 9);
/* 192 */     this.field_110697_J.addBox(-1.5F, -1.0F, -3.0F, 3, 1, 2);
/* 193 */     this.field_110697_J.setRotationPoint(0.0F, 2.0F, 2.0F);
/*     */     
/* 195 */     this.field_110698_K = new ModelRenderer(this, 80, 9);
/* 196 */     this.field_110698_K.addBox(-4.0F, -1.0F, 3.0F, 8, 1, 2);
/* 197 */     this.field_110698_K.setRotationPoint(0.0F, 2.0F, 2.0F);
/*     */     
/* 199 */     this.field_110692_M = new ModelRenderer(this, 74, 0);
/* 200 */     this.field_110692_M.addBox(-0.5F, 6.0F, -1.0F, 1, 2, 2);
/* 201 */     this.field_110692_M.setRotationPoint(5.0F, 3.0F, 2.0F);
/*     */     
/* 203 */     this.field_110691_L = new ModelRenderer(this, 70, 0);
/* 204 */     this.field_110691_L.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1);
/* 205 */     this.field_110691_L.setRotationPoint(5.0F, 3.0F, 2.0F);
/*     */     
/* 207 */     this.field_110694_O = new ModelRenderer(this, 74, 4);
/* 208 */     this.field_110694_O.addBox(-0.5F, 6.0F, -1.0F, 1, 2, 2);
/* 209 */     this.field_110694_O.setRotationPoint(-5.0F, 3.0F, 2.0F);
/*     */     
/* 211 */     this.field_110693_N = new ModelRenderer(this, 80, 0);
/* 212 */     this.field_110693_N.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1);
/* 213 */     this.field_110693_N.setRotationPoint(-5.0F, 3.0F, 2.0F);
/*     */     
/* 215 */     this.field_110700_P = new ModelRenderer(this, 74, 13);
/* 216 */     this.field_110700_P.addBox(1.5F, -8.0F, -4.0F, 1, 2, 2);
/* 217 */     this.field_110700_P.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 218 */     func_110682_a(this.field_110700_P, 0.5235988F, 0.0F, 0.0F);
/*     */     
/* 220 */     this.field_110699_Q = new ModelRenderer(this, 74, 13);
/* 221 */     this.field_110699_Q.addBox(-2.5F, -8.0F, -4.0F, 1, 2, 2);
/* 222 */     this.field_110699_Q.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 223 */     func_110682_a(this.field_110699_Q, 0.5235988F, 0.0F, 0.0F);
/*     */     
/* 225 */     this.field_110702_R = new ModelRenderer(this, 44, 10);
/* 226 */     this.field_110702_R.addBox(2.6F, -6.0F, -6.0F, 0, 3, 16);
/* 227 */     this.field_110702_R.setRotationPoint(0.0F, 4.0F, -10.0F);
/*     */     
/* 229 */     this.field_110701_S = new ModelRenderer(this, 44, 5);
/* 230 */     this.field_110701_S.addBox(-2.6F, -6.0F, -6.0F, 0, 3, 16);
/* 231 */     this.field_110701_S.setRotationPoint(0.0F, 4.0F, -10.0F);
/*     */     
/* 233 */     this.mane = new ModelRenderer(this, 58, 0);
/* 234 */     this.mane.addBox(-1.0F, -11.5F, 5.0F, 2, 16, 4);
/* 235 */     this.mane.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 236 */     func_110682_a(this.mane, 0.5235988F, 0.0F, 0.0F);
/*     */     
/* 238 */     this.field_110717_i = new ModelRenderer(this, 80, 12);
/* 239 */     this.field_110717_i.addBox(-2.5F, -10.1F, -7.0F, 5, 5, 12, 0.2F);
/* 240 */     this.field_110717_i.setRotationPoint(0.0F, 4.0F, -10.0F);
/* 241 */     func_110682_a(this.field_110717_i, 0.5235988F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
/* 246 */     EntityHorse entityHorse = (EntityHorse)entity;
/*     */     
/* 248 */     int m = entityHorse.getHorseType();
/* 249 */     float f1 = entityHorse.getGrassEatingAmount(0.0F);
/* 250 */     boolean bool = entityHorse.isAdultHorse();
/* 251 */     boolean bool1 = (bool && entityHorse.isHorseSaddled()) ? true : false;
/* 252 */     boolean bool2 = (bool && entityHorse.isChested()) ? true : false;
/* 253 */     boolean bool3 = (m == 1 || m == 2) ? true : false;
/* 254 */     float f2 = entityHorse.getHorseSize();
/*     */     
/* 256 */     boolean bool4 = (entityHorse.riddenByEntity != null) ? true : false;
/*     */     
/* 258 */     if (bool1) {
/* 259 */       this.field_110717_i.render(k);
/* 260 */       this.field_110696_I.render(k);
/* 261 */       this.field_110697_J.render(k);
/* 262 */       this.field_110698_K.render(k);
/* 263 */       this.field_110691_L.render(k);
/* 264 */       this.field_110692_M.render(k);
/* 265 */       this.field_110693_N.render(k);
/* 266 */       this.field_110694_O.render(k);
/* 267 */       this.field_110700_P.render(k);
/* 268 */       this.field_110699_Q.render(k);
/* 269 */       if (bool4) {
/* 270 */         this.field_110702_R.render(k);
/* 271 */         this.field_110701_S.render(k);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 276 */     if (!bool) {
/* 277 */       GL11.glPushMatrix();
/* 278 */       GL11.glScalef(f2, 0.5F + f2 * 0.5F, f2);
/* 279 */       GL11.glTranslatef(0.0F, 0.95F * (1.0F - f2), 0.0F);
/*     */     } 
/* 281 */     this.backLeftLeg.render(k);
/* 282 */     this.backLeftShin.render(k);
/* 283 */     this.backLeftHoof.render(k);
/*     */     
/* 285 */     this.backRightLeg.render(k);
/* 286 */     this.backRightShin.render(k);
/* 287 */     this.backRightHoof.render(k);
/*     */     
/* 289 */     this.frontRightLeg.render(k);
/* 290 */     this.frontLeftShin.render(k);
/* 291 */     this.frontLeftHoof.render(k);
/*     */     
/* 293 */     this.field_110684_D.render(k);
/* 294 */     this.frontRightShin.render(k);
/* 295 */     this.frontRightHoof.render(k);
/* 296 */     if (!bool) {
/* 297 */       GL11.glPopMatrix();
/*     */       
/* 299 */       GL11.glPushMatrix();
/* 300 */       GL11.glScalef(f2, f2, f2);
/* 301 */       GL11.glTranslatef(0.0F, 1.35F * (1.0F - f2), 0.0F);
/*     */     } 
/*     */     
/* 304 */     this.body.render(k);
/* 305 */     this.tailBase.render(k);
/* 306 */     this.tailMiddle.render(k);
/* 307 */     this.tailTip.render(k);
/* 308 */     this.neck.render(k);
/* 309 */     this.mane.render(k);
/* 310 */     if (!bool) {
/* 311 */       GL11.glPopMatrix();
/*     */       
/* 313 */       GL11.glPushMatrix();
/* 314 */       float f3 = 0.5F + f2 * f2 * 0.5F;
/* 315 */       GL11.glScalef(f3, f3, f3);
/* 316 */       if (f1 <= 0.0F) {
/* 317 */         GL11.glTranslatef(0.0F, 1.35F * (1.0F - f2), 0.0F);
/*     */       } else {
/* 319 */         GL11.glTranslatef(0.0F, 0.9F * (1.0F - f2) * f1 + 1.35F * (1.0F - f2) * (1.0F - f1), 0.15F * (1.0F - f2) * f1);
/*     */       } 
/*     */     } 
/*     */     
/* 323 */     if (bool3) {
/* 324 */       this.field_110703_f.render(k);
/* 325 */       this.field_110704_g.render(k);
/*     */     } else {
/* 327 */       this.horseLeftEar.render(k);
/* 328 */       this.horseRightEar.render(k);
/*     */     } 
/* 330 */     this.head.render(k);
/* 331 */     if (!bool) {
/* 332 */       GL11.glPopMatrix();
/*     */     }
/* 334 */     if (bool2) {
/* 335 */       this.field_110687_G.render(k);
/* 336 */       this.field_110695_H.render(k);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void func_110682_a(ModelRenderer modelRenderer, float f, float g, float h) {
/* 341 */     modelRenderer.rotateAngleX = f;
/* 342 */     modelRenderer.rotateAngleY = g;
/* 343 */     modelRenderer.rotateAngleZ = h;
/*     */   }
/*     */   
/*     */   private float func_110683_a(float f, float g, float h) {
/* 347 */     float f1 = g - f;
/* 348 */     while (f1 < -180.0F)
/* 349 */       f1 += 360.0F; 
/* 350 */     while (f1 >= 180.0F)
/* 351 */       f1 -= 360.0F; 
/* 352 */     return f + h * f1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLivingAnimations(EntityLivingBase entityLivingBase, float f, float g, float h) {
/* 357 */     super.setLivingAnimations(entityLivingBase, f, g, h);
/*     */     
/* 359 */     float f1 = func_110683_a(entityLivingBase.prevRenderYawOffset, entityLivingBase.renderYawOffset, h);
/* 360 */     float f2 = func_110683_a(entityLivingBase.prevRotationYawHead, entityLivingBase.rotationYawHead, h);
/* 361 */     float f3 = entityLivingBase.prevRotationPitch + (entityLivingBase.rotationPitch - entityLivingBase.prevRotationPitch) * h;
/* 362 */     float f4 = f2 - f1;
/*     */ 
/*     */     
/* 365 */     float f5 = f3 / 57.29578F;
/* 366 */     if (f4 > 20.0F) {
/* 367 */       f4 = 20.0F;
/*     */     }
/* 369 */     if (f4 < -20.0F) {
/* 370 */       f4 = -20.0F;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 376 */     if (g > 0.2F) {
/* 377 */       f5 += MathHelper.cos(f * 0.4F) * 0.15F * g;
/*     */     }
/*     */     
/* 380 */     EntityHorse entityHorse = (EntityHorse)entityLivingBase;
/* 381 */     float f6 = entityHorse.getGrassEatingAmount(h);
/* 382 */     float f7 = entityHorse.getRearingAmount(h);
/* 383 */     float f8 = 1.0F - f7;
/* 384 */     float f9 = entityHorse.func_110201_q(h);
/* 385 */     boolean bool1 = (entityHorse.field_110278_bp != 0) ? true : false;
/* 386 */     boolean bool = entityHorse.isHorseSaddled();
/* 387 */     boolean bool2 = (entityHorse.riddenByEntity != null) ? true : false;
/* 388 */     float f10 = entityLivingBase.ticksExisted + h;
/*     */     
/* 390 */     float f11 = MathHelper.cos(f * 0.6662F + 3.141593F);
/* 391 */     float f12 = f11 * 0.8F * g;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 397 */     this.head.rotationPointY = 4.0F;
/* 398 */     this.head.rotationPointZ = -10.0F;
/* 399 */     this.tailBase.rotationPointY = 3.0F;
/* 400 */     this.tailMiddle.rotationPointZ = 14.0F;
/* 401 */     this.field_110695_H.rotationPointY = 3.0F;
/* 402 */     this.field_110695_H.rotationPointZ = 10.0F;
/* 403 */     this.body.rotateAngleX = 0.0F;
/*     */ 
/*     */     
/* 406 */     this.head.rotateAngleX = 0.5235988F + f5;
/* 407 */     this.head.rotateAngleY = f4 / 57.29578F;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 412 */     this.head.rotateAngleX = f7 * (0.2617994F + f5) + f6 * 2.18166F + (1.0F - Math.max(f7, f6)) * this.head.rotateAngleX;
/* 413 */     this.head.rotateAngleY = f7 * f4 / 57.29578F + (1.0F - Math.max(f7, f6)) * this.head.rotateAngleY;
/*     */     
/* 415 */     this.head.rotationPointY = f7 * -6.0F + f6 * 11.0F + (1.0F - Math.max(f7, f6)) * this.head.rotationPointY;
/* 416 */     this.head.rotationPointZ = f7 * -1.0F + f6 * -10.0F + (1.0F - Math.max(f7, f6)) * this.head.rotationPointZ;
/*     */     
/* 418 */     this.tailBase.rotationPointY = f7 * 9.0F + f8 * this.tailBase.rotationPointY;
/* 419 */     this.tailMiddle.rotationPointZ = f7 * 18.0F + f8 * this.tailMiddle.rotationPointZ;
/* 420 */     this.field_110695_H.rotationPointY = f7 * 5.5F + f8 * this.field_110695_H.rotationPointY;
/* 421 */     this.field_110695_H.rotationPointZ = f7 * 15.0F + f8 * this.field_110695_H.rotationPointZ;
/* 422 */     this.body.rotateAngleX = f7 * -0.7853981F + f8 * this.body.rotateAngleX;
/*     */ 
/*     */     
/* 425 */     this.horseLeftEar.rotationPointY = this.head.rotationPointY;
/* 426 */     this.horseRightEar.rotationPointY = this.head.rotationPointY;
/* 427 */     this.field_110703_f.rotationPointY = this.head.rotationPointY;
/* 428 */     this.field_110704_g.rotationPointY = this.head.rotationPointY;
/* 429 */     this.neck.rotationPointY = this.head.rotationPointY;
/* 430 */     this.mouthTop.rotationPointY = 0.02F;
/* 431 */     this.mouthBottom.rotationPointY = 0.0F;
/* 432 */     this.mane.rotationPointY = this.head.rotationPointY;
/*     */     
/* 434 */     this.horseLeftEar.rotationPointZ = this.head.rotationPointZ;
/* 435 */     this.horseRightEar.rotationPointZ = this.head.rotationPointZ;
/* 436 */     this.field_110703_f.rotationPointZ = this.head.rotationPointZ;
/* 437 */     this.field_110704_g.rotationPointZ = this.head.rotationPointZ;
/* 438 */     this.neck.rotationPointZ = this.head.rotationPointZ;
/* 439 */     this.mouthTop.rotationPointZ = 0.02F - f9 * 1.0F;
/* 440 */     this.mouthBottom.rotationPointZ = 0.0F + f9 * 1.0F;
/* 441 */     this.mane.rotationPointZ = this.head.rotationPointZ;
/*     */     
/* 443 */     this.horseLeftEar.rotateAngleX = this.head.rotateAngleX;
/* 444 */     this.horseRightEar.rotateAngleX = this.head.rotateAngleX;
/* 445 */     this.field_110703_f.rotateAngleX = this.head.rotateAngleX;
/* 446 */     this.field_110704_g.rotateAngleX = this.head.rotateAngleX;
/* 447 */     this.neck.rotateAngleX = this.head.rotateAngleX;
/* 448 */     this.mouthTop.rotateAngleX = 0.0F - 0.09424778F * f9;
/* 449 */     this.mouthBottom.rotateAngleX = 0.0F + 0.15707964F * f9;
/*     */     
/* 451 */     this.mane.rotateAngleX = this.head.rotateAngleX;
/*     */     
/* 453 */     this.horseLeftEar.rotateAngleY = this.head.rotateAngleY;
/* 454 */     this.horseRightEar.rotateAngleY = this.head.rotateAngleY;
/* 455 */     this.field_110703_f.rotateAngleY = this.head.rotateAngleY;
/* 456 */     this.field_110704_g.rotateAngleY = this.head.rotateAngleY;
/* 457 */     this.neck.rotateAngleY = this.head.rotateAngleY;
/* 458 */     this.mouthTop.rotateAngleY = 0.0F;
/* 459 */     this.mouthBottom.rotateAngleY = 0.0F;
/* 460 */     this.mane.rotateAngleY = this.head.rotateAngleY;
/*     */ 
/*     */     
/* 463 */     this.field_110687_G.rotateAngleX = f12 / 5.0F;
/* 464 */     this.field_110695_H.rotateAngleX = -f12 / 5.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 470 */     float f13 = 1.5707964F;
/* 471 */     float f14 = 4.712389F;
/* 472 */     float f15 = -1.0471976F;
/* 473 */     float f16 = 0.2617994F * f7;
/* 474 */     float f17 = MathHelper.cos(f10 * 0.6F + 3.141593F);
/*     */     
/* 476 */     this.frontRightLeg.rotationPointY = -2.0F * f7 + 9.0F * f8;
/* 477 */     this.frontRightLeg.rotationPointZ = -2.0F * f7 + -8.0F * f8;
/* 478 */     this.field_110684_D.rotationPointY = this.frontRightLeg.rotationPointY;
/* 479 */     this.field_110684_D.rotationPointZ = this.frontRightLeg.rotationPointZ;
/*     */     
/* 481 */     this.backLeftLeg.rotationPointY += MathHelper.sin(1.5707964F + f16 + f8 * -f11 * 0.5F * g) * 7.0F;
/* 482 */     this.backLeftLeg.rotationPointZ += MathHelper.cos(4.712389F + f16 + f8 * -f11 * 0.5F * g) * 7.0F;
/*     */     
/* 484 */     this.backRightLeg.rotationPointY += MathHelper.sin(1.5707964F + f16 + f8 * f11 * 0.5F * g) * 7.0F;
/* 485 */     this.backRightLeg.rotationPointZ += MathHelper.cos(4.712389F + f16 + f8 * f11 * 0.5F * g) * 7.0F;
/*     */     
/* 487 */     float f18 = (-1.0471976F + f17) * f7 + f12 * f8;
/* 488 */     float f19 = (-1.0471976F + -f17) * f7 + -f12 * f8;
/* 489 */     this.frontRightLeg.rotationPointY += MathHelper.sin(1.5707964F + f18) * 7.0F;
/* 490 */     this.frontRightLeg.rotationPointZ += MathHelper.cos(4.712389F + f18) * 7.0F;
/*     */     
/* 492 */     this.field_110684_D.rotationPointY += MathHelper.sin(1.5707964F + f19) * 7.0F;
/* 493 */     this.field_110684_D.rotationPointZ += MathHelper.cos(4.712389F + f19) * 7.0F;
/*     */     
/* 495 */     this.backLeftLeg.rotateAngleX = f16 + -f11 * 0.5F * g * f8;
/* 496 */     this.backLeftShin.rotateAngleX = -0.08726646F * f7 + (-f11 * 0.5F * g - Math.max(0.0F, f11 * 0.5F * g)) * f8;
/* 497 */     this.backLeftHoof.rotateAngleX = this.backLeftShin.rotateAngleX;
/*     */     
/* 499 */     this.backRightLeg.rotateAngleX = f16 + f11 * 0.5F * g * f8;
/* 500 */     this.backRightShin.rotateAngleX = -0.08726646F * f7 + (f11 * 0.5F * g - Math.max(0.0F, -f11 * 0.5F * g)) * f8;
/* 501 */     this.backRightHoof.rotateAngleX = this.backRightShin.rotateAngleX;
/*     */     
/* 503 */     this.frontRightLeg.rotateAngleX = f18;
/* 504 */     this.frontLeftShin.rotateAngleX = (this.frontRightLeg.rotateAngleX + 3.1415927F * Math.max(0.0F, 0.2F + f17 * 0.2F)) * f7 + (f12 + Math.max(0.0F, f11 * 0.5F * g)) * f8;
/* 505 */     this.frontLeftHoof.rotateAngleX = this.frontLeftShin.rotateAngleX;
/*     */     
/* 507 */     this.field_110684_D.rotateAngleX = f19;
/* 508 */     this.frontRightShin.rotateAngleX = (this.field_110684_D.rotateAngleX + 3.1415927F * Math.max(0.0F, 0.2F - f17 * 0.2F)) * f7 + (-f12 + Math.max(0.0F, -f11 * 0.5F * g)) * f8;
/* 509 */     this.frontRightHoof.rotateAngleX = this.frontRightShin.rotateAngleX;
/*     */ 
/*     */     
/* 512 */     this.backLeftHoof.rotationPointY = this.backLeftShin.rotationPointY;
/* 513 */     this.backLeftHoof.rotationPointZ = this.backLeftShin.rotationPointZ;
/* 514 */     this.backRightHoof.rotationPointY = this.backRightShin.rotationPointY;
/* 515 */     this.backRightHoof.rotationPointZ = this.backRightShin.rotationPointZ;
/* 516 */     this.frontLeftHoof.rotationPointY = this.frontLeftShin.rotationPointY;
/* 517 */     this.frontLeftHoof.rotationPointZ = this.frontLeftShin.rotationPointZ;
/* 518 */     this.frontRightHoof.rotationPointY = this.frontRightShin.rotationPointY;
/* 519 */     this.frontRightHoof.rotationPointZ = this.frontRightShin.rotationPointZ;
/*     */     
/* 521 */     if (bool) {
/*     */       
/* 523 */       this.field_110696_I.rotationPointY = f7 * 0.5F + f8 * 2.0F;
/* 524 */       this.field_110696_I.rotationPointZ = f7 * 11.0F + f8 * 2.0F;
/*     */       
/* 526 */       this.field_110697_J.rotationPointY = this.field_110696_I.rotationPointY;
/* 527 */       this.field_110698_K.rotationPointY = this.field_110696_I.rotationPointY;
/* 528 */       this.field_110691_L.rotationPointY = this.field_110696_I.rotationPointY;
/* 529 */       this.field_110693_N.rotationPointY = this.field_110696_I.rotationPointY;
/* 530 */       this.field_110692_M.rotationPointY = this.field_110696_I.rotationPointY;
/* 531 */       this.field_110694_O.rotationPointY = this.field_110696_I.rotationPointY;
/* 532 */       this.field_110687_G.rotationPointY = this.field_110695_H.rotationPointY;
/*     */       
/* 534 */       this.field_110697_J.rotationPointZ = this.field_110696_I.rotationPointZ;
/* 535 */       this.field_110698_K.rotationPointZ = this.field_110696_I.rotationPointZ;
/* 536 */       this.field_110691_L.rotationPointZ = this.field_110696_I.rotationPointZ;
/* 537 */       this.field_110693_N.rotationPointZ = this.field_110696_I.rotationPointZ;
/* 538 */       this.field_110692_M.rotationPointZ = this.field_110696_I.rotationPointZ;
/* 539 */       this.field_110694_O.rotationPointZ = this.field_110696_I.rotationPointZ;
/* 540 */       this.field_110687_G.rotationPointZ = this.field_110695_H.rotationPointZ;
/*     */       
/* 542 */       this.field_110696_I.rotateAngleX = this.body.rotateAngleX;
/* 543 */       this.field_110697_J.rotateAngleX = this.body.rotateAngleX;
/* 544 */       this.field_110698_K.rotateAngleX = this.body.rotateAngleX;
/*     */       
/* 546 */       this.field_110702_R.rotationPointY = this.head.rotationPointY;
/* 547 */       this.field_110701_S.rotationPointY = this.head.rotationPointY;
/* 548 */       this.field_110717_i.rotationPointY = this.head.rotationPointY;
/* 549 */       this.field_110700_P.rotationPointY = this.head.rotationPointY;
/* 550 */       this.field_110699_Q.rotationPointY = this.head.rotationPointY;
/*     */       
/* 552 */       this.field_110702_R.rotationPointZ = this.head.rotationPointZ;
/* 553 */       this.field_110701_S.rotationPointZ = this.head.rotationPointZ;
/* 554 */       this.field_110717_i.rotationPointZ = this.head.rotationPointZ;
/* 555 */       this.field_110700_P.rotationPointZ = this.head.rotationPointZ;
/* 556 */       this.field_110699_Q.rotationPointZ = this.head.rotationPointZ;
/*     */       
/* 558 */       this.field_110702_R.rotateAngleX = f5;
/* 559 */       this.field_110701_S.rotateAngleX = f5;
/* 560 */       this.field_110717_i.rotateAngleX = this.head.rotateAngleX;
/* 561 */       this.field_110700_P.rotateAngleX = this.head.rotateAngleX;
/* 562 */       this.field_110699_Q.rotateAngleX = this.head.rotateAngleX;
/* 563 */       this.field_110717_i.rotateAngleY = this.head.rotateAngleY;
/* 564 */       this.field_110700_P.rotateAngleY = this.head.rotateAngleY;
/* 565 */       this.field_110702_R.rotateAngleY = this.head.rotateAngleY;
/* 566 */       this.field_110699_Q.rotateAngleY = this.head.rotateAngleY;
/* 567 */       this.field_110701_S.rotateAngleY = this.head.rotateAngleY;
/*     */       
/* 569 */       if (bool2) {
/*     */         
/* 571 */         this.field_110691_L.rotateAngleX = -1.0471976F;
/* 572 */         this.field_110692_M.rotateAngleX = -1.0471976F;
/* 573 */         this.field_110693_N.rotateAngleX = -1.0471976F;
/* 574 */         this.field_110694_O.rotateAngleX = -1.0471976F;
/*     */         
/* 576 */         this.field_110691_L.rotateAngleZ = 0.0F;
/* 577 */         this.field_110692_M.rotateAngleZ = 0.0F;
/* 578 */         this.field_110693_N.rotateAngleZ = 0.0F;
/* 579 */         this.field_110694_O.rotateAngleZ = 0.0F;
/*     */       } else {
/* 581 */         this.field_110691_L.rotateAngleX = f12 / 3.0F;
/* 582 */         this.field_110692_M.rotateAngleX = f12 / 3.0F;
/* 583 */         this.field_110693_N.rotateAngleX = f12 / 3.0F;
/* 584 */         this.field_110694_O.rotateAngleX = f12 / 3.0F;
/*     */         
/* 586 */         this.field_110691_L.rotateAngleZ = f12 / 5.0F;
/* 587 */         this.field_110692_M.rotateAngleZ = f12 / 5.0F;
/* 588 */         this.field_110693_N.rotateAngleZ = -f12 / 5.0F;
/* 589 */         this.field_110694_O.rotateAngleZ = -f12 / 5.0F;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 594 */     f13 = -1.3089F + g * 1.5F;
/* 595 */     if (f13 > 0.0F) {
/* 596 */       f13 = 0.0F;
/*     */     }
/*     */     
/* 599 */     if (bool1) {
/* 600 */       this.tailBase.rotateAngleY = MathHelper.cos(f10 * 0.7F);
/* 601 */       f13 = 0.0F;
/*     */     } else {
/* 603 */       this.tailBase.rotateAngleY = 0.0F;
/*     */     } 
/* 605 */     this.tailMiddle.rotateAngleY = this.tailBase.rotateAngleY;
/* 606 */     this.tailTip.rotateAngleY = this.tailBase.rotateAngleY;
/*     */     
/* 608 */     this.tailMiddle.rotationPointY = this.tailBase.rotationPointY;
/* 609 */     this.tailTip.rotationPointY = this.tailBase.rotationPointY;
/* 610 */     this.tailMiddle.rotationPointZ = this.tailBase.rotationPointZ;
/* 611 */     this.tailTip.rotationPointZ = this.tailBase.rotationPointZ;
/*     */ 
/*     */     
/* 614 */     this.tailBase.rotateAngleX = f13;
/* 615 */     this.tailMiddle.rotateAngleX = f13;
/* 616 */     this.tailTip.rotateAngleX = -0.2618F + f13;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelHorse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */