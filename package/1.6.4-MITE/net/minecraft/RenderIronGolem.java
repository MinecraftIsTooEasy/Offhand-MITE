/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderIronGolem
/*     */   extends RenderLiving
/*     */ {
/*     */   public static final int body_texture = 0;
/*     */   private final ModelIronGolem ironGolemModel;
/*     */   
/*     */   public RenderIronGolem() {
/*  16 */     super(new ModelIronGolem(), 0.5F);
/*  17 */     this.ironGolemModel = (ModelIronGolem)this.mainModel;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setTextures() {
/*  22 */     setTexture(0, "textures/entity/iron_golem");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRenderIronGolem(EntityIronGolem par1EntityIronGolem, double par2, double par4, double par6, float par8, float par9) {
/*  30 */     super.doRenderLiving(par1EntityIronGolem, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getIronGolemTextures(EntityIronGolem par1EntityIronGolem) {
/*  36 */     return this.textures[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void rotateIronGolemCorpse(EntityIronGolem par1EntityIronGolem, float par2, float par3, float par4) {
/*  44 */     super.rotateCorpse(par1EntityIronGolem, par2, par3, par4);
/*     */     
/*  46 */     if (par1EntityIronGolem.limbSwingAmount >= 0.01D) {
/*     */       
/*  48 */       float var5 = 13.0F;
/*  49 */       float var6 = par1EntityIronGolem.limbSwing - par1EntityIronGolem.limbSwingAmount * (1.0F - par4) + 6.0F;
/*  50 */       float var7 = (Math.abs(var6 % var5 - var5 * 0.5F) - var5 * 0.25F) / var5 * 0.25F;
/*  51 */       GL11.glRotatef(6.5F * var7, 0.0F, 0.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderIronGolemEquippedItems(EntityIronGolem par1EntityIronGolem, float par2) {
/*  60 */     super.renderEquippedItems(par1EntityIronGolem, par2);
/*     */     
/*  62 */     if (par1EntityIronGolem.getHoldRoseTick() != 0) {
/*     */       
/*  64 */       GL11.glEnable(32826);
/*  65 */       GL11.glPushMatrix();
/*  66 */       GL11.glRotatef(5.0F + 180.0F * this.ironGolemModel.ironGolemRightArm.rotateAngleX / 3.1415927F, 1.0F, 0.0F, 0.0F);
/*  67 */       GL11.glTranslatef(-0.6875F, 1.25F, -0.9375F);
/*  68 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*  69 */       float var3 = 0.8F;
/*  70 */       GL11.glScalef(var3, -var3, var3);
/*  71 */       int var4 = par1EntityIronGolem.getBrightnessForRender(par2);
/*  72 */       int var5 = var4 % 65536;
/*  73 */       int var6 = var4 / 65536;
/*  74 */       OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var5 / 1.0F, var6 / 1.0F);
/*  75 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  76 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  77 */       bindTexture(TextureMap.locationBlocksTexture);
/*  78 */       this.renderBlocks.renderBlockAsItem(Block.plantRed, 0, 1.0F);
/*  79 */       GL11.glPopMatrix();
/*  80 */       GL11.glDisable(32826);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
/*  86 */     doRenderIronGolem((EntityIronGolem)par1EntityLiving, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2) {
/*  91 */     renderIronGolemEquippedItems((EntityIronGolem)par1EntityLivingBase, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void rotateCorpse(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
/*  96 */     rotateIronGolemCorpse((EntityIronGolem)par1EntityLivingBase, par2, par3, par4);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
/* 101 */     doRenderIronGolem((EntityIronGolem)par1EntityLivingBase, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 109 */     return getIronGolemTextures((EntityIronGolem)par1Entity);
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
/* 120 */     doRenderIronGolem((EntityIronGolem)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderIronGolem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */