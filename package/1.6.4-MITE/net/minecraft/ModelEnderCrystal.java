/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelEnderCrystal
/*    */   extends ModelBase
/*    */ {
/*    */   private ModelRenderer cube;
/*    */   private ModelRenderer glass;
/*    */   private ModelRenderer base;
/*    */   
/*    */   public ModelEnderCrystal(float f, boolean bl) {
/* 16 */     this.glass = new ModelRenderer(this, "glass");
/* 17 */     this.glass.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
/*    */     
/* 19 */     this.cube = new ModelRenderer(this, "cube");
/* 20 */     this.cube.setTextureOffset(32, 0).addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
/*    */     
/* 22 */     if (bl) {
/* 23 */       this.base = new ModelRenderer(this, "base");
/* 24 */       this.base.setTextureOffset(0, 16).addBox(-6.0F, 0.0F, -6.0F, 12, 4, 12);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
/* 30 */     GL11.glPushMatrix();
/* 31 */     GL11.glScalef(2.0F, 2.0F, 2.0F);
/* 32 */     GL11.glTranslatef(0.0F, -0.5F, 0.0F);
/* 33 */     if (this.base != null) {
/* 34 */       this.base.render(k);
/*    */     }
/* 36 */     GL11.glRotatef(g, 0.0F, 1.0F, 0.0F);
/* 37 */     GL11.glTranslatef(0.0F, 0.8F + h, 0.0F);
/* 38 */     GL11.glRotatef(60.0F, 0.7071F, 0.0F, 0.7071F);
/* 39 */     this.glass.render(k);
/* 40 */     float f1 = 0.875F;
/* 41 */     GL11.glScalef(f1, f1, f1);
/* 42 */     GL11.glRotatef(60.0F, 0.7071F, 0.0F, 0.7071F);
/* 43 */     GL11.glRotatef(g, 0.0F, 1.0F, 0.0F);
/* 44 */     this.glass.render(k);
/* 45 */     GL11.glScalef(f1, f1, f1);
/* 46 */     GL11.glRotatef(60.0F, 0.7071F, 0.0F, 0.7071F);
/* 47 */     GL11.glRotatef(g, 0.0F, 1.0F, 0.0F);
/* 48 */     this.cube.render(k);
/* 49 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelEnderCrystal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */