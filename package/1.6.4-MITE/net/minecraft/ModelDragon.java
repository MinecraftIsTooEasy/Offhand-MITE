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
/*     */ public class ModelDragon
/*     */   extends ModelBase
/*     */ {
/*     */   private ModelRenderer head;
/*     */   private ModelRenderer spine;
/*     */   private ModelRenderer jaw;
/*     */   private ModelRenderer body;
/*     */   private ModelRenderer rearLeg;
/*     */   private ModelRenderer frontLeg;
/*     */   private ModelRenderer rearLegTip;
/*     */   private ModelRenderer frontLegTip;
/*     */   private ModelRenderer rearFoot;
/*     */   private ModelRenderer frontFoot;
/*     */   private ModelRenderer wing;
/*     */   private ModelRenderer wingTip;
/*     */   private float partialTicks;
/*     */   
/*     */   public ModelDragon(float f) {
/*  30 */     this.textureWidth = 256;
/*  31 */     this.textureHeight = 256;
/*     */     
/*  33 */     setTextureOffset("body.body", 0, 0);
/*  34 */     setTextureOffset("wing.skin", -56, 88);
/*  35 */     setTextureOffset("wingtip.skin", -56, 144);
/*  36 */     setTextureOffset("rearleg.main", 0, 0);
/*  37 */     setTextureOffset("rearfoot.main", 112, 0);
/*  38 */     setTextureOffset("rearlegtip.main", 196, 0);
/*  39 */     setTextureOffset("head.upperhead", 112, 30);
/*  40 */     setTextureOffset("wing.bone", 112, 88);
/*  41 */     setTextureOffset("head.upperlip", 176, 44);
/*  42 */     setTextureOffset("jaw.jaw", 176, 65);
/*  43 */     setTextureOffset("frontleg.main", 112, 104);
/*  44 */     setTextureOffset("wingtip.bone", 112, 136);
/*  45 */     setTextureOffset("frontfoot.main", 144, 104);
/*  46 */     setTextureOffset("neck.box", 192, 104);
/*  47 */     setTextureOffset("frontlegtip.main", 226, 138);
/*  48 */     setTextureOffset("body.scale", 220, 53);
/*  49 */     setTextureOffset("head.scale", 0, 0);
/*  50 */     setTextureOffset("neck.scale", 48, 0);
/*  51 */     setTextureOffset("head.nostril", 112, 0);
/*     */     
/*  53 */     float f1 = -16.0F;
/*  54 */     this.head = new ModelRenderer(this, "head");
/*  55 */     this.head.addBox("upperlip", -6.0F, -1.0F, -8.0F + f1, 12, 5, 16);
/*  56 */     this.head.addBox("upperhead", -8.0F, -8.0F, 6.0F + f1, 16, 16, 16);
/*  57 */     this.head.mirror = true;
/*  58 */     this.head.addBox("scale", -5.0F, -12.0F, 12.0F + f1, 2, 4, 6);
/*  59 */     this.head.addBox("nostril", -5.0F, -3.0F, -6.0F + f1, 2, 2, 4);
/*  60 */     this.head.mirror = false;
/*  61 */     this.head.addBox("scale", 3.0F, -12.0F, 12.0F + f1, 2, 4, 6);
/*  62 */     this.head.addBox("nostril", 3.0F, -3.0F, -6.0F + f1, 2, 2, 4);
/*     */     
/*  64 */     this.jaw = new ModelRenderer(this, "jaw");
/*  65 */     this.jaw.setRotationPoint(0.0F, 4.0F, 8.0F + f1);
/*  66 */     this.jaw.addBox("jaw", -6.0F, 0.0F, -16.0F, 12, 4, 16);
/*  67 */     this.head.addChild(this.jaw);
/*     */     
/*  69 */     this.spine = new ModelRenderer(this, "neck");
/*  70 */     this.spine.addBox("box", -5.0F, -5.0F, -5.0F, 10, 10, 10);
/*  71 */     this.spine.addBox("scale", -1.0F, -9.0F, -3.0F, 2, 4, 6);
/*     */     
/*  73 */     this.body = new ModelRenderer(this, "body");
/*  74 */     this.body.setRotationPoint(0.0F, 4.0F, 8.0F);
/*  75 */     this.body.addBox("body", -12.0F, 0.0F, -16.0F, 24, 24, 64);
/*  76 */     this.body.addBox("scale", -1.0F, -6.0F, -10.0F, 2, 6, 12);
/*  77 */     this.body.addBox("scale", -1.0F, -6.0F, 10.0F, 2, 6, 12);
/*  78 */     this.body.addBox("scale", -1.0F, -6.0F, 30.0F, 2, 6, 12);
/*     */     
/*  80 */     this.wing = new ModelRenderer(this, "wing");
/*  81 */     this.wing.setRotationPoint(-12.0F, 5.0F, 2.0F);
/*  82 */     this.wing.addBox("bone", -56.0F, -4.0F, -4.0F, 56, 8, 8);
/*  83 */     this.wing.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
/*  84 */     this.wingTip = new ModelRenderer(this, "wingtip");
/*  85 */     this.wingTip.setRotationPoint(-56.0F, 0.0F, 0.0F);
/*  86 */     this.wingTip.addBox("bone", -56.0F, -2.0F, -2.0F, 56, 4, 4);
/*  87 */     this.wingTip.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
/*  88 */     this.wing.addChild(this.wingTip);
/*     */     
/*  90 */     this.frontLeg = new ModelRenderer(this, "frontleg");
/*  91 */     this.frontLeg.setRotationPoint(-12.0F, 20.0F, 2.0F);
/*  92 */     this.frontLeg.addBox("main", -4.0F, -4.0F, -4.0F, 8, 24, 8);
/*  93 */     this.frontLegTip = new ModelRenderer(this, "frontlegtip");
/*  94 */     this.frontLegTip.setRotationPoint(0.0F, 20.0F, -1.0F);
/*  95 */     this.frontLegTip.addBox("main", -3.0F, -1.0F, -3.0F, 6, 24, 6);
/*  96 */     this.frontLeg.addChild(this.frontLegTip);
/*  97 */     this.frontFoot = new ModelRenderer(this, "frontfoot");
/*  98 */     this.frontFoot.setRotationPoint(0.0F, 23.0F, 0.0F);
/*  99 */     this.frontFoot.addBox("main", -4.0F, 0.0F, -12.0F, 8, 4, 16);
/* 100 */     this.frontLegTip.addChild(this.frontFoot);
/*     */     
/* 102 */     this.rearLeg = new ModelRenderer(this, "rearleg");
/* 103 */     this.rearLeg.setRotationPoint(-16.0F, 16.0F, 42.0F);
/* 104 */     this.rearLeg.addBox("main", -8.0F, -4.0F, -8.0F, 16, 32, 16);
/* 105 */     this.rearLegTip = new ModelRenderer(this, "rearlegtip");
/* 106 */     this.rearLegTip.setRotationPoint(0.0F, 32.0F, -4.0F);
/* 107 */     this.rearLegTip.addBox("main", -6.0F, -2.0F, 0.0F, 12, 32, 12);
/* 108 */     this.rearLeg.addChild(this.rearLegTip);
/* 109 */     this.rearFoot = new ModelRenderer(this, "rearfoot");
/* 110 */     this.rearFoot.setRotationPoint(0.0F, 31.0F, 4.0F);
/* 111 */     this.rearFoot.addBox("main", -9.0F, 0.0F, -20.0F, 18, 6, 24);
/* 112 */     this.rearLegTip.addChild(this.rearFoot);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLivingAnimations(EntityLivingBase entityLivingBase, float f, float g, float h) {
/* 117 */     this.partialTicks = h;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
/* 122 */     GL11.glPushMatrix();
/* 123 */     EntityDragon entityDragon = (EntityDragon)entity;
/* 124 */     float f1 = entityDragon.prevAnimTime + (entityDragon.animTime - entityDragon.prevAnimTime) * this.partialTicks;
/* 125 */     this.jaw.rotateAngleX = (float)(Math.sin((f1 * 3.1415927F * 2.0F)) + 1.0D) * 0.2F;
/*     */     
/* 127 */     float f2 = (float)(Math.sin((f1 * 3.1415927F * 2.0F - 1.0F)) + 1.0D);
/* 128 */     f2 = (f2 * f2 * 1.0F + f2 * 2.0F) * 0.05F;
/*     */     
/* 130 */     GL11.glTranslatef(0.0F, f2 - 2.0F, -3.0F);
/* 131 */     GL11.glRotatef(f2 * 2.0F, 1.0F, 0.0F, 0.0F);
/*     */     
/* 133 */     float f3 = -30.0F;
/*     */     
/* 135 */     float f5 = 0.0F;
/*     */     
/* 137 */     float f6 = 1.5F;
/*     */     
/* 139 */     double[] arrayOfDouble1 = entityDragon.getMovementOffsets(6, this.partialTicks);
/*     */     
/* 141 */     float f7 = updateRotations(entityDragon.getMovementOffsets(5, this.partialTicks)[0] - entityDragon.getMovementOffsets(10, this.partialTicks)[0]);
/* 142 */     float f8 = updateRotations(entityDragon.getMovementOffsets(5, this.partialTicks)[0] + (f7 / 2.0F));
/*     */     
/* 144 */     f3 += 2.0F;
/*     */     
/* 146 */     float f9 = f1 * 3.1415927F * 2.0F;
/* 147 */     f3 = 20.0F;
/* 148 */     float f4 = -12.0F;
/* 149 */     for (byte b1 = 0; b1 < 5; b1++) {
/* 150 */       double[] arrayOfDouble = entityDragon.getMovementOffsets(5 - b1, this.partialTicks);
/* 151 */       float f11 = (float)Math.cos((b1 * 0.45F + f9)) * 0.15F;
/* 152 */       this.spine.rotateAngleY = updateRotations(arrayOfDouble[0] - arrayOfDouble1[0]) * 3.1415927F / 180.0F * f6;
/* 153 */       this.spine.rotateAngleX = f11 + (float)(arrayOfDouble[1] - arrayOfDouble1[1]) * 3.1415927F / 180.0F * f6 * 5.0F;
/* 154 */       this.spine.rotateAngleZ = -updateRotations(arrayOfDouble[0] - f8) * 3.1415927F / 180.0F * f6;
/*     */       
/* 156 */       this.spine.rotationPointY = f3;
/* 157 */       this.spine.rotationPointZ = f4;
/* 158 */       this.spine.rotationPointX = f5;
/* 159 */       f3 = (float)(f3 + Math.sin(this.spine.rotateAngleX) * 10.0D);
/* 160 */       f4 = (float)(f4 - Math.cos(this.spine.rotateAngleY) * Math.cos(this.spine.rotateAngleX) * 10.0D);
/* 161 */       f5 = (float)(f5 - Math.sin(this.spine.rotateAngleY) * Math.cos(this.spine.rotateAngleX) * 10.0D);
/* 162 */       this.spine.render(k);
/*     */     } 
/*     */     
/* 165 */     this.head.rotationPointY = f3;
/* 166 */     this.head.rotationPointZ = f4;
/* 167 */     this.head.rotationPointX = f5;
/* 168 */     double[] arrayOfDouble2 = entityDragon.getMovementOffsets(0, this.partialTicks);
/* 169 */     this.head.rotateAngleY = updateRotations(arrayOfDouble2[0] - arrayOfDouble1[0]) * 3.1415927F / 180.0F * 1.0F;
/* 170 */     this.head.rotateAngleZ = -updateRotations(arrayOfDouble2[0] - f8) * 3.1415927F / 180.0F * 1.0F;
/* 171 */     this.head.render(k);
/* 172 */     GL11.glPushMatrix();
/* 173 */     GL11.glTranslatef(0.0F, 1.0F, 0.0F);
/* 174 */     GL11.glRotatef(-f7 * f6 * 1.0F, 0.0F, 0.0F, 1.0F);
/* 175 */     GL11.glTranslatef(0.0F, -1.0F, 0.0F);
/* 176 */     this.body.rotateAngleZ = 0.0F;
/* 177 */     this.body.render(k);
/*     */     
/* 179 */     for (byte b2 = 0; b2 < 2; b2++) {
/* 180 */       GL11.glEnable(2884);
/* 181 */       float f11 = f1 * 3.1415927F * 2.0F;
/* 182 */       this.wing.rotateAngleX = 0.125F - (float)Math.cos(f11) * 0.2F;
/* 183 */       this.wing.rotateAngleY = 0.25F;
/* 184 */       this.wing.rotateAngleZ = (float)(Math.sin(f11) + 0.125D) * 0.8F;
/* 185 */       this.wingTip.rotateAngleZ = -((float)(Math.sin((f11 + 2.0F)) + 0.5D)) * 0.75F;
/*     */       
/* 187 */       this.rearLeg.rotateAngleX = 1.0F + f2 * 0.1F;
/* 188 */       this.rearLegTip.rotateAngleX = 0.5F + f2 * 0.1F;
/* 189 */       this.rearFoot.rotateAngleX = 0.75F + f2 * 0.1F;
/*     */       
/* 191 */       this.frontLeg.rotateAngleX = 1.3F + f2 * 0.1F;
/* 192 */       this.frontLegTip.rotateAngleX = -0.5F - f2 * 0.1F;
/* 193 */       this.frontFoot.rotateAngleX = 0.75F + f2 * 0.1F;
/* 194 */       this.wing.render(k);
/* 195 */       this.frontLeg.render(k);
/* 196 */       this.rearLeg.render(k);
/* 197 */       GL11.glScalef(-1.0F, 1.0F, 1.0F);
/*     */       
/* 199 */       if (b2 == 0) {
/* 200 */         GL11.glCullFace(1028);
/*     */       }
/*     */     } 
/* 203 */     GL11.glPopMatrix();
/* 204 */     GL11.glCullFace(1029);
/* 205 */     GL11.glDisable(2884);
/*     */     
/* 207 */     float f10 = -((float)Math.sin((f1 * 3.1415927F * 2.0F))) * 0.0F;
/* 208 */     f9 = f1 * 3.1415927F * 2.0F;
/* 209 */     f3 = 10.0F;
/* 210 */     f4 = 60.0F;
/* 211 */     f5 = 0.0F;
/* 212 */     arrayOfDouble1 = entityDragon.getMovementOffsets(11, this.partialTicks);
/* 213 */     for (byte b3 = 0; b3 < 12; b3++) {
/* 214 */       arrayOfDouble2 = entityDragon.getMovementOffsets(12 + b3, this.partialTicks);
/* 215 */       f10 = (float)(f10 + Math.sin((b3 * 0.45F + f9)) * 0.05000000074505806D);
/* 216 */       this.spine.rotateAngleY = (updateRotations(arrayOfDouble2[0] - arrayOfDouble1[0]) * f6 + 180.0F) * 3.1415927F / 180.0F;
/* 217 */       this.spine.rotateAngleX = f10 + (float)(arrayOfDouble2[1] - arrayOfDouble1[1]) * 3.1415927F / 180.0F * f6 * 5.0F;
/* 218 */       this.spine.rotateAngleZ = updateRotations(arrayOfDouble2[0] - f8) * 3.1415927F / 180.0F * f6;
/* 219 */       this.spine.rotationPointY = f3;
/* 220 */       this.spine.rotationPointZ = f4;
/* 221 */       this.spine.rotationPointX = f5;
/* 222 */       f3 = (float)(f3 + Math.sin(this.spine.rotateAngleX) * 10.0D);
/* 223 */       f4 = (float)(f4 - Math.cos(this.spine.rotateAngleY) * Math.cos(this.spine.rotateAngleX) * 10.0D);
/* 224 */       f5 = (float)(f5 - Math.sin(this.spine.rotateAngleY) * Math.cos(this.spine.rotateAngleX) * 10.0D);
/* 225 */       this.spine.render(k);
/*     */     } 
/* 227 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private float updateRotations(double d) {
/* 231 */     while (d >= 180.0D)
/* 232 */       d -= 360.0D; 
/* 233 */     while (d < -180.0D)
/* 234 */       d += 360.0D; 
/* 235 */     return (float)d;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelDragon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */