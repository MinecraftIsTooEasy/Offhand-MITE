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
/*     */ public class RenderBat
/*     */   extends RenderLiving
/*     */ {
/*     */   public static final int texture_normal = 0;
/*     */   public static final int texture_vampire = 1;
/*     */   public static final int texture_nightwing = 2;
/*     */   private int renderedBatSize;
/*     */   
/*     */   public RenderBat() {
/*  22 */     super(new ModelBat(), 0.25F);
/*  23 */     this.renderedBatSize = ((ModelBat)this.mainModel).getBatSize();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setTextures() {
/*  28 */     setTexture(0, "textures/entity/bat");
/*  29 */     setTexture(1, "textures/entity/bat/vampire");
/*  30 */     setTexture(2, "textures/entity/bat/nightwing");
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82443_a(EntityBat par1EntityBat, double par2, double par4, double par6, float par8, float par9) {
/*  35 */     int var10 = ((ModelBat)this.mainModel).getBatSize();
/*     */     
/*  37 */     if (var10 != this.renderedBatSize) {
/*     */       
/*  39 */       this.renderedBatSize = var10;
/*  40 */       this.mainModel = new ModelBat();
/*     */     } 
/*     */     
/*  43 */     super.doRenderLiving(par1EntityBat, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getBatTextures(EntityBat par1EntityBat) {
/*  50 */     return this.textures[par1EntityBat.isVampireBat() ? 1 : (par1EntityBat.isNightwing() ? 2 : 0)];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_82442_a(EntityBat par1EntityBat, float par2) {
/*  57 */     float scale = 0.35F * par1EntityBat.getScaleFactor();
/*     */     
/*  59 */     GL11.glScalef(scale, scale, scale);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_82445_a(EntityBat par1EntityBat, double par2, double par4, double par6) {
/*  64 */     super.renderLivingAt(par1EntityBat, par2, par4, par6);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_82444_a(EntityBat par1EntityBat, float par2, float par3, float par4) {
/*  69 */     if (!par1EntityBat.getIsBatHanging()) {
/*     */       
/*  71 */       GL11.glTranslatef(0.0F, MathHelper.cos(par2 * 0.3F) * 0.1F, 0.0F);
/*     */     }
/*     */     else {
/*     */       
/*  75 */       GL11.glTranslatef(0.0F, -0.1F, 0.0F);
/*     */     } 
/*     */     
/*  78 */     super.rotateCorpse(par1EntityBat, par2, par3, par4);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
/*  83 */     func_82443_a((EntityBat)par1EntityLiving, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
/*  92 */     func_82442_a((EntityBat)par1EntityLivingBase, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void rotateCorpse(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
/*  97 */     func_82444_a((EntityBat)par1EntityLivingBase, par2, par3, par4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderLivingAt(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6) {
/* 105 */     func_82445_a((EntityBat)par1EntityLivingBase, par2, par4, par6);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
/* 110 */     func_82443_a((EntityBat)par1EntityLivingBase, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 118 */     return getBatTextures((EntityBat)par1Entity);
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
/* 129 */     func_82443_a((EntityBat)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderBat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */