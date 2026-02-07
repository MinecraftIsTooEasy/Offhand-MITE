/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelRenderer
/*     */ {
/*  16 */   public float textureWidth = 64.0F;
/*  17 */   public float textureHeight = 32.0F;
/*     */   private int textureOffsetX;
/*     */   private int textureOffsetY;
/*     */   public float rotationPointX;
/*     */   public float rotationPointY;
/*     */   public float rotationPointZ;
/*     */   public float rotateAngleX;
/*     */   public float rotateAngleY;
/*     */   public float rotateAngleZ;
/*     */   private boolean compiled;
/*     */   private int displayList;
/*     */   public boolean mirror;
/*     */   public boolean showModel = true;
/*     */   public boolean isHidden;
/*  31 */   public List cubeList = new ArrayList();
/*     */   
/*     */   public List childModels;
/*     */   
/*     */   public final String boxName;
/*     */   private ModelBase baseModel;
/*     */   
/*     */   public ModelRenderer(ModelBase modelBase, String string) {
/*  39 */     this.baseModel = modelBase;
/*  40 */     modelBase.boxList.add(this);
/*  41 */     this.boxName = string;
/*  42 */     setTextureSize(modelBase.textureWidth, modelBase.textureHeight);
/*     */   }
/*     */   public float offsetX; public float offsetY; public float offsetZ;
/*     */   public ModelRenderer(ModelBase modelBase) {
/*  46 */     this(modelBase, null);
/*     */   }
/*     */   
/*     */   public ModelRenderer(ModelBase modelBase, int i, int j) {
/*  50 */     this(modelBase);
/*  51 */     setTextureOffset(i, j);
/*     */   }
/*     */   
/*     */   public void addChild(ModelRenderer modelRenderer) {
/*  55 */     if (this.childModels == null) this.childModels = new ArrayList(); 
/*  56 */     this.childModels.add(modelRenderer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModelRenderer setTextureOffset(int i, int j) {
/*  65 */     this.textureOffsetX = i;
/*  66 */     this.textureOffsetY = j;
/*  67 */     return this;
/*     */   }
/*     */   
/*     */   public ModelRenderer addBox(String string, float f, float g, float h, int i, int j, int k) {
/*  71 */     string = this.boxName + "." + string;
/*  72 */     TextureOffset textureOffset = this.baseModel.getTextureOffset(string);
/*  73 */     setTextureOffset(textureOffset.textureOffsetX, textureOffset.textureOffsetY);
/*  74 */     this.cubeList.add((new ModelBox(this, this.textureOffsetX, this.textureOffsetY, f, g, h, i, j, k, 0.0F)).func_78244_a(string));
/*  75 */     return this;
/*     */   }
/*     */   
/*     */   public ModelRenderer addBox(float f, float g, float h, int i, int j, int k) {
/*  79 */     this.cubeList.add(new ModelBox(this, this.textureOffsetX, this.textureOffsetY, f, g, h, i, j, k, 0.0F));
/*  80 */     return this;
/*     */   }
/*     */   
/*     */   public void addBox(float f, float g, float h, int i, int j, int k, float l) {
/*  84 */     this.cubeList.add(new ModelBox(this, this.textureOffsetX, this.textureOffsetY, f, g, h, i, j, k, l));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationPoint(float f, float g, float h) {
/*  92 */     this.rotationPointX = f;
/*  93 */     this.rotationPointY = g;
/*  94 */     this.rotationPointZ = h;
/*     */   }
/*     */   
/*     */   public void render(float f) {
/*  98 */     if (this.isHidden)
/*  99 */       return;  if (!this.showModel)
/* 100 */       return;  if (!this.compiled) compileDisplayList(f);
/*     */     
/* 102 */     GL11.glTranslatef(this.offsetX, this.offsetY, this.offsetZ);
/*     */     
/* 104 */     if (this.rotateAngleX != 0.0F || this.rotateAngleY != 0.0F || this.rotateAngleZ != 0.0F) {
/* 105 */       GL11.glPushMatrix();
/* 106 */       GL11.glTranslatef(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);
/* 107 */       if (this.rotateAngleZ != 0.0F) GL11.glRotatef(this.rotateAngleZ * 57.295776F, 0.0F, 0.0F, 1.0F); 
/* 108 */       if (this.rotateAngleY != 0.0F) GL11.glRotatef(this.rotateAngleY * 57.295776F, 0.0F, 1.0F, 0.0F); 
/* 109 */       if (this.rotateAngleX != 0.0F) GL11.glRotatef(this.rotateAngleX * 57.295776F, 1.0F, 0.0F, 0.0F); 
/* 110 */       GL11.glCallList(this.displayList);
/* 111 */       if (this.childModels != null) {
/* 112 */         for (byte b = 0; b < this.childModels.size(); b++) {
/* 113 */           ((ModelRenderer)this.childModels.get(b)).render(f);
/*     */         }
/*     */       }
/* 116 */       GL11.glPopMatrix();
/* 117 */     } else if (this.rotationPointX != 0.0F || this.rotationPointY != 0.0F || this.rotationPointZ != 0.0F) {
/* 118 */       GL11.glTranslatef(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);
/* 119 */       GL11.glCallList(this.displayList);
/* 120 */       if (this.childModels != null) {
/* 121 */         for (byte b = 0; b < this.childModels.size(); b++) {
/* 122 */           ((ModelRenderer)this.childModels.get(b)).render(f);
/*     */         }
/*     */       }
/* 125 */       GL11.glTranslatef(-this.rotationPointX * f, -this.rotationPointY * f, -this.rotationPointZ * f);
/*     */     } else {
/* 127 */       GL11.glCallList(this.displayList);
/* 128 */       if (this.childModels != null) {
/* 129 */         for (byte b = 0; b < this.childModels.size(); b++) {
/* 130 */           ((ModelRenderer)this.childModels.get(b)).render(f);
/*     */         }
/*     */       }
/*     */     } 
/*     */     
/* 135 */     GL11.glTranslatef(-this.offsetX, -this.offsetY, -this.offsetZ);
/*     */   }
/*     */   
/*     */   public void renderWithRotation(float f) {
/* 139 */     if (this.isHidden)
/* 140 */       return;  if (!this.showModel)
/* 141 */       return;  if (!this.compiled) compileDisplayList(f);
/*     */     
/* 143 */     GL11.glPushMatrix();
/* 144 */     GL11.glTranslatef(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);
/* 145 */     if (this.rotateAngleY != 0.0F) GL11.glRotatef(this.rotateAngleY * 57.295776F, 0.0F, 1.0F, 0.0F); 
/* 146 */     if (this.rotateAngleX != 0.0F) GL11.glRotatef(this.rotateAngleX * 57.295776F, 1.0F, 0.0F, 0.0F); 
/* 147 */     if (this.rotateAngleZ != 0.0F) GL11.glRotatef(this.rotateAngleZ * 57.295776F, 0.0F, 0.0F, 1.0F); 
/* 148 */     GL11.glCallList(this.displayList);
/* 149 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public void postRender(float f) {
/* 154 */     if (this.isHidden)
/* 155 */       return;  if (!this.showModel)
/* 156 */       return;  if (!this.compiled) compileDisplayList(f);
/*     */     
/* 158 */     if (this.rotateAngleX != 0.0F || this.rotateAngleY != 0.0F || this.rotateAngleZ != 0.0F) {
/* 159 */       GL11.glTranslatef(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);
/* 160 */       if (this.rotateAngleZ != 0.0F) GL11.glRotatef(this.rotateAngleZ * 57.295776F, 0.0F, 0.0F, 1.0F); 
/* 161 */       if (this.rotateAngleY != 0.0F) GL11.glRotatef(this.rotateAngleY * 57.295776F, 0.0F, 1.0F, 0.0F); 
/* 162 */       if (this.rotateAngleX != 0.0F) GL11.glRotatef(this.rotateAngleX * 57.295776F, 1.0F, 0.0F, 0.0F); 
/* 163 */     } else if (this.rotationPointX != 0.0F || this.rotationPointY != 0.0F || this.rotationPointZ != 0.0F) {
/* 164 */       GL11.glTranslatef(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void compileDisplayList(float f) {
/* 169 */     this.displayList = GLAllocation.generateDisplayLists(1);
/*     */     
/* 171 */     GL11.glNewList(this.displayList, 4864);
/* 172 */     Tessellator tessellator = Tessellator.instance;
/* 173 */     for (byte b = 0; b < this.cubeList.size(); b++) {
/* 174 */       ((ModelBox)this.cubeList.get(b)).render(tessellator, f);
/*     */     }
/* 176 */     GL11.glEndList();
/*     */     
/* 178 */     this.compiled = true;
/*     */   }
/*     */   
/*     */   public ModelRenderer setTextureSize(int i, int j) {
/* 182 */     this.textureWidth = i;
/* 183 */     this.textureHeight = j;
/* 184 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */