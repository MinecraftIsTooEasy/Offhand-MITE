/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderWither
/*     */   extends RenderLiving
/*     */ {
/*     */   public static final int body_texture = 0;
/*     */   public static final int body_texture_invulnerable = 1;
/*     */   private int field_82419_a;
/*     */   
/*     */   public RenderWither() {
/*  17 */     super(new ModelWither(), 1.0F);
/*  18 */     this.field_82419_a = ((ModelWither)this.mainModel).func_82903_a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setTextures() {
/*  23 */     setTexture(0, "textures/entity/wither/wither");
/*  24 */     setTexture(1, "textures/entity/wither/wither_invulnerable");
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82418_a(EntityWither par1EntityWither, double par2, double par4, double par6, float par8, float par9) {
/*  29 */     BossStatus.setBossStatus(par1EntityWither, true);
/*  30 */     int var10 = ((ModelWither)this.mainModel).func_82903_a();
/*     */     
/*  32 */     if (var10 != this.field_82419_a) {
/*     */       
/*  34 */       this.field_82419_a = var10;
/*  35 */       this.mainModel = new ModelWither();
/*     */     } 
/*     */     
/*  38 */     super.doRenderLiving(par1EntityWither, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110911_a(EntityWither par1EntityWither) {
/*  43 */     int var2 = par1EntityWither.func_82212_n();
/*     */     
/*  45 */     return (var2 > 0 && (var2 > 80 || var2 / 5 % 2 != 1)) ? this.textures[1] : this.textures[0];
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_82415_a(EntityWither par1EntityWither, float par2) {
/*  50 */     int var3 = par1EntityWither.func_82212_n();
/*     */     
/*  52 */     if (var3 > 0) {
/*     */       
/*  54 */       float var4 = 2.0F - (var3 - par2) / 220.0F * 0.5F;
/*  55 */       GL11.glScalef(var4, var4, var4);
/*     */     }
/*     */     else {
/*     */       
/*  59 */       GL11.glScalef(2.0F, 2.0F, 2.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_82417_a(EntityWither par1EntityWither, int par2, float par3) {
/*  65 */     if (par1EntityWither.isArmored()) {
/*     */       
/*  67 */       if (par1EntityWither.isInvisible()) {
/*     */         
/*  69 */         GL11.glDepthMask(false);
/*     */       }
/*     */       else {
/*     */         
/*  73 */         GL11.glDepthMask(true);
/*     */       } 
/*     */       
/*  76 */       if (par2 == 1) {
/*     */         
/*  78 */         float var4 = par1EntityWither.ticksExisted + par3;
/*     */         
/*  80 */         bindTexture(this.textures[1]);
/*  81 */         GL11.glMatrixMode(5890);
/*  82 */         GL11.glLoadIdentity();
/*  83 */         float var5 = MathHelper.cos(var4 * 0.02F) * 3.0F;
/*  84 */         float var6 = var4 * 0.01F;
/*  85 */         GL11.glTranslatef(var5, var6, 0.0F);
/*  86 */         setRenderPassModel(this.mainModel);
/*  87 */         GL11.glMatrixMode(5888);
/*  88 */         GL11.glEnable(3042);
/*  89 */         float var7 = 0.5F;
/*  90 */         GL11.glColor4f(var7, var7, var7, 1.0F);
/*  91 */         GL11.glDisable(2896);
/*  92 */         GL11.glBlendFunc(1, 1);
/*  93 */         GL11.glTranslatef(0.0F, -0.01F, 0.0F);
/*  94 */         GL11.glScalef(1.1F, 1.1F, 1.1F);
/*  95 */         return 1;
/*     */       } 
/*     */       
/*  98 */       if (par2 == 2) {
/*     */         
/* 100 */         GL11.glMatrixMode(5890);
/* 101 */         GL11.glLoadIdentity();
/* 102 */         GL11.glMatrixMode(5888);
/* 103 */         GL11.glEnable(2896);
/* 104 */         GL11.glDisable(3042);
/*     */       } 
/*     */     } 
/*     */     
/* 108 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_82416_b(EntityWither par1EntityWither, int par2, float par3) {
/* 113 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
/* 118 */     func_82418_a((EntityWither)par1EntityLiving, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
/* 127 */     func_82415_a((EntityWither)par1EntityLivingBase, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
/* 135 */     return func_82417_a((EntityWither)par1EntityLivingBase, par2, par3);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int inheritRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
/* 140 */     return func_82416_b((EntityWither)par1EntityLivingBase, par2, par3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
/* 145 */     func_82418_a((EntityWither)par1EntityLivingBase, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 153 */     return func_110911_a((EntityWither)par1Entity);
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
/* 164 */     func_82418_a((EntityWither)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderWither.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */