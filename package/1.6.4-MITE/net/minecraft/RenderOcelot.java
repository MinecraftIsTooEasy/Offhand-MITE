/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderOcelot
/*     */   extends RenderLiving
/*     */ {
/*     */   public static final int body_texture_ocelot = 0;
/*     */   public static final int body_texture_black = 1;
/*     */   public static final int body_texture_red = 2;
/*     */   public static final int body_texture_siamese = 3;
/*     */   
/*     */   public RenderOcelot(ModelBase par1ModelBase, float par2) {
/*  19 */     super(par1ModelBase, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setTextures() {
/*  24 */     setTexture(0, "textures/entity/cat/ocelot");
/*  25 */     setTexture(1, "textures/entity/cat/black");
/*  26 */     setTexture(2, "textures/entity/cat/red");
/*  27 */     setTexture(3, "textures/entity/cat/siamese");
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderLivingOcelot(EntityOcelot par1EntityOcelot, double par2, double par4, double par6, float par8, float par9) {
/*  32 */     super.doRenderLiving(par1EntityOcelot, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110874_a(EntityOcelot par1EntityOcelot) {
/*  37 */     switch (par1EntityOcelot.getTameSkin()) {
/*     */ 
/*     */ 
/*     */       
/*     */       default:
/*  42 */         return this.textures[0];
/*     */ 
/*     */       
/*     */       case 1:
/*  46 */         return this.textures[1];
/*     */ 
/*     */       
/*     */       case 2:
/*  50 */         return this.textures[2];
/*     */       case 3:
/*     */         break;
/*     */     } 
/*  54 */     return this.textures[3];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preRenderOcelot(EntityOcelot par1EntityOcelot, float par2) {
/*  63 */     super.preRenderCallback(par1EntityOcelot, par2);
/*     */     
/*  65 */     if (par1EntityOcelot.isTamed())
/*     */     {
/*  67 */       GL11.glScalef(0.8F, 0.8F, 0.8F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
/*  73 */     renderLivingOcelot((EntityOcelot)par1EntityLiving, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
/*  82 */     preRenderOcelot((EntityOcelot)par1EntityLivingBase, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
/*  87 */     renderLivingOcelot((EntityOcelot)par1EntityLivingBase, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/*  95 */     return func_110874_a((EntityOcelot)par1Entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
/* 106 */     renderLivingOcelot((EntityOcelot)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderOcelot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */