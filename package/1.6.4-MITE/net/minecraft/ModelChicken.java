/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class ModelChicken extends ModelBase {
/*    */   public ModelRenderer head;
/*    */   public ModelRenderer body;
/*    */   public ModelRenderer rightLeg;
/*    */   public ModelRenderer leftLeg;
/*    */   public ModelRenderer rightWing;
/*    */   public ModelRenderer leftWing;
/*    */   public ModelRenderer bill;
/*    */   public ModelRenderer chin;
/*    */   
/*    */   public ModelChicken() {
/* 16 */     byte b = 16;
/* 17 */     this.head = new ModelRenderer(this, 0, 0);
/* 18 */     this.head.addBox(-2.0F, -6.0F, -2.0F, 4, 6, 3, 0.0F);
/* 19 */     this.head.setRotationPoint(0.0F, (-1 + b), -4.0F);
/*    */     
/* 21 */     this.bill = new ModelRenderer(this, 14, 0);
/* 22 */     this.bill.addBox(-2.0F, -4.0F, -4.0F, 4, 2, 2, 0.0F);
/* 23 */     this.bill.setRotationPoint(0.0F, (-1 + b), -4.0F);
/*    */     
/* 25 */     this.chin = new ModelRenderer(this, 14, 4);
/* 26 */     this.chin.addBox(-1.0F, -2.0F, -3.0F, 2, 2, 2, 0.0F);
/* 27 */     this.chin.setRotationPoint(0.0F, (-1 + b), -4.0F);
/*    */     
/* 29 */     this.body = new ModelRenderer(this, 0, 9);
/* 30 */     this.body.addBox(-3.0F, -4.0F, -3.0F, 6, 8, 6, 0.0F);
/* 31 */     this.body.setRotationPoint(0.0F, b, 0.0F);
/*    */     
/* 33 */     this.rightLeg = new ModelRenderer(this, 26, 0);
/* 34 */     this.rightLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
/* 35 */     this.rightLeg.setRotationPoint(-2.0F, (3 + b), 1.0F);
/*    */     
/* 37 */     this.leftLeg = new ModelRenderer(this, 26, 0);
/* 38 */     this.leftLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
/* 39 */     this.leftLeg.setRotationPoint(1.0F, (3 + b), 1.0F);
/*    */     
/* 41 */     this.rightWing = new ModelRenderer(this, 24, 13);
/* 42 */     this.rightWing.addBox(0.0F, 0.0F, -3.0F, 1, 4, 6);
/* 43 */     this.rightWing.setRotationPoint(-4.0F, (-3 + b), 0.0F);
/*    */     
/* 45 */     this.leftWing = new ModelRenderer(this, 24, 13);
/* 46 */     this.leftWing.addBox(-1.0F, 0.0F, -3.0F, 1, 4, 6);
/* 47 */     this.leftWing.setRotationPoint(4.0F, (-3 + b), 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
/* 52 */     setRotationAngles(f, g, h, i, j, k, entity);
/*    */     
/* 54 */     if (this.isChild) {
/* 55 */       float f1 = 2.0F;
/* 56 */       GL11.glPushMatrix();
/* 57 */       GL11.glTranslatef(0.0F, 5.0F * k, 2.0F * k);
/* 58 */       this.head.render(k);
/* 59 */       this.bill.render(k);
/* 60 */       this.chin.render(k);
/* 61 */       GL11.glPopMatrix();
/* 62 */       GL11.glPushMatrix();
/* 63 */       GL11.glScalef(1.0F / f1, 1.0F / f1, 1.0F / f1);
/* 64 */       GL11.glTranslatef(0.0F, 24.0F * k, 0.0F);
/* 65 */       this.body.render(k);
/* 66 */       this.rightLeg.render(k);
/* 67 */       this.leftLeg.render(k);
/* 68 */       this.rightWing.render(k);
/* 69 */       this.leftWing.render(k);
/* 70 */       GL11.glPopMatrix();
/*    */     } else {
/* 72 */       this.head.render(k);
/* 73 */       this.bill.render(k);
/* 74 */       this.chin.render(k);
/* 75 */       this.body.render(k);
/* 76 */       this.rightLeg.render(k);
/* 77 */       this.leftLeg.render(k);
/* 78 */       this.rightWing.render(k);
/* 79 */       this.leftWing.render(k);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/* 85 */     this.head.rotateAngleX = j / 57.295776F;
/* 86 */     this.head.rotateAngleY = i / 57.295776F;
/*    */     
/* 88 */     this.bill.rotateAngleX = this.head.rotateAngleX;
/* 89 */     this.bill.rotateAngleY = this.head.rotateAngleY;
/*    */     
/* 91 */     this.chin.rotateAngleX = this.head.rotateAngleX;
/* 92 */     this.chin.rotateAngleY = this.head.rotateAngleY;
/*    */     
/* 94 */     this.body.rotateAngleX = 1.5707964F;
/*    */     
/* 96 */     this.rightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * g;
/* 97 */     this.leftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
/* 98 */     this.rightWing.rotateAngleZ = h;
/* 99 */     this.leftWing.rotateAngleZ = -h;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelChicken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */