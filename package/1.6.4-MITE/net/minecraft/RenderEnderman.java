/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderEnderman
/*     */   extends RenderLiving
/*     */ {
/*     */   public static final int body_texture = 0;
/*     */   private ModelEnderman endermanModel;
/*  16 */   private Random rnd = new Random();
/*     */ 
/*     */   
/*     */   public RenderEnderman() {
/*  20 */     super(new ModelEnderman(), 0.5F);
/*  21 */     this.endermanModel = (ModelEnderman)this.mainModel;
/*  22 */     setRenderPassModel(this.endermanModel);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setTextures() {
/*  27 */     setTexture(0, "textures/entity/enderman/enderman");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderEnderman(EntityEnderman par1EntityEnderman, double par2, double par4, double par6, float par8, float par9) {
/*  35 */     this.endermanModel.isCarrying = (par1EntityEnderman.getCarried() > 0);
/*  36 */     this.endermanModel.isAttacking = par1EntityEnderman.isScreaming();
/*     */     
/*  38 */     if (par1EntityEnderman.isScreaming()) {
/*     */       
/*  40 */       double var10 = 0.02D;
/*  41 */       par2 += this.rnd.nextGaussian() * var10;
/*  42 */       par6 += this.rnd.nextGaussian() * var10;
/*     */     } 
/*     */     
/*  45 */     super.doRenderLiving(par1EntityEnderman, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEndermanTextures(EntityEnderman par1EntityEnderman) {
/*  51 */     return this.textures[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderCarrying(EntityEnderman par1EntityEnderman, float par2) {
/*  59 */     super.renderEquippedItems(par1EntityEnderman, par2);
/*     */     
/*  61 */     if (par1EntityEnderman.getCarried() > 0) {
/*     */       
/*  63 */       GL11.glEnable(32826);
/*  64 */       GL11.glPushMatrix();
/*  65 */       float var3 = 0.5F;
/*  66 */       GL11.glTranslatef(0.0F, 0.6875F, -0.75F);
/*  67 */       var3 *= 1.0F;
/*  68 */       GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/*  69 */       GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*  70 */       GL11.glScalef(-var3, -var3, var3);
/*  71 */       int var4 = par1EntityEnderman.getBrightnessForRender(par2);
/*  72 */       int var5 = var4 % 65536;
/*  73 */       int var6 = var4 / 65536;
/*  74 */       OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var5 / 1.0F, var6 / 1.0F);
/*  75 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  76 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  77 */       bindTexture(TextureMap.locationBlocksTexture);
/*  78 */       this.renderBlocks.renderBlockAsItem(Block.blocksList[par1EntityEnderman.getCarried()], par1EntityEnderman.getCarryingData(), 1.0F);
/*  79 */       GL11.glPopMatrix();
/*  80 */       GL11.glDisable(32826);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
/* 124 */     renderEnderman((EntityEnderman)par1EntityLiving, par2, par4, par6, par8, par9);
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
/*     */   protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2) {
/* 137 */     renderCarrying((EntityEnderman)par1EntityLivingBase, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
/* 142 */     renderEnderman((EntityEnderman)par1EntityLivingBase, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 150 */     return getEndermanTextures((EntityEnderman)par1Entity);
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
/* 161 */     renderEnderman((EntityEnderman)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderEnderman.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */