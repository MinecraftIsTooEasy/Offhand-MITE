/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderCreeper
/*     */   extends RenderLiving
/*     */ {
/*     */   public static final int body_texture = 0;
/*     */   public static final int armored_texture = 1;
/*     */   protected float scale;
/*  16 */   private ModelBase creeperModel = new ModelCreeper(2.0F);
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderCreeper() {
/*  21 */     super(new ModelCreeper(), EntityCreeper.getScale() * 0.5F);
/*     */     
/*  23 */     this.scale = EntityCreeper.getScale();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setTextures() {
/*  28 */     setTexture(0, "textures/entity/creeper/" + getSubtypeName());
/*  29 */     setTexture(1, "textures/entity/creeper/creeper_armor");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateCreeperScale(EntityCreeper par1EntityCreeper, float par2) {
/*  37 */     float var3 = par1EntityCreeper.getCreeperFlashIntensity(par2);
/*  38 */     float var4 = 1.0F + MathHelper.sin(var3 * 100.0F) * var3 * 0.01F;
/*     */     
/*  40 */     if (var3 < 0.0F)
/*     */     {
/*  42 */       var3 = 0.0F;
/*     */     }
/*     */     
/*  45 */     if (var3 > 1.0F)
/*     */     {
/*  47 */       var3 = 1.0F;
/*     */     }
/*     */     
/*  50 */     var3 *= var3;
/*  51 */     var3 *= var3;
/*  52 */     float var5 = (1.0F + var3 * 0.4F) * var4;
/*  53 */     float var6 = (1.0F + var3 * 0.1F) / var4;
/*     */     
/*  55 */     GL11.glScalef(var5 * this.scale, var6 * this.scale, var5 * this.scale);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int updateCreeperColorMultiplier(EntityCreeper par1EntityCreeper, float par2, float par3) {
/*  63 */     float var4 = par1EntityCreeper.getCreeperFlashIntensity(par3);
/*     */     
/*  65 */     if ((int)(var4 * 10.0F) % 2 == 0)
/*     */     {
/*  67 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*  71 */     int var5 = (int)(var4 * 0.2F * 255.0F);
/*     */     
/*  73 */     if (var5 < 0)
/*     */     {
/*  75 */       var5 = 0;
/*     */     }
/*     */     
/*  78 */     if (var5 > 255)
/*     */     {
/*  80 */       var5 = 255;
/*     */     }
/*     */     
/*  83 */     short var6 = 255;
/*  84 */     short var7 = 255;
/*  85 */     short var8 = 255;
/*  86 */     return var5 << 24 | var6 << 16 | var7 << 8 | var8;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int renderCreeperPassModel(EntityCreeper par1EntityCreeper, int par2, float par3) {
/*  95 */     if (par1EntityCreeper.getPowered()) {
/*     */       
/*  97 */       if (par1EntityCreeper.isInvisible()) {
/*     */         
/*  99 */         GL11.glDepthMask(false);
/*     */       }
/*     */       else {
/*     */         
/* 103 */         GL11.glDepthMask(true);
/*     */       } 
/*     */       
/* 106 */       if (par2 == 1) {
/*     */         
/* 108 */         float var4 = par1EntityCreeper.ticksExisted + par3;
/*     */         
/* 110 */         bindTexture(this.textures[1]);
/* 111 */         GL11.glMatrixMode(5890);
/* 112 */         GL11.glLoadIdentity();
/* 113 */         float var5 = var4 * 0.01F;
/* 114 */         float var6 = var4 * 0.01F;
/* 115 */         GL11.glTranslatef(var5, var6, 0.0F);
/* 116 */         setRenderPassModel(this.creeperModel);
/* 117 */         GL11.glMatrixMode(5888);
/* 118 */         GL11.glEnable(3042);
/* 119 */         float var7 = 0.5F;
/* 120 */         GL11.glColor4f(var7, var7, var7, 1.0F);
/* 121 */         GL11.glDisable(2896);
/* 122 */         GL11.glBlendFunc(1, 1);
/* 123 */         return 1;
/*     */       } 
/*     */       
/* 126 */       if (par2 == 2) {
/*     */         
/* 128 */         GL11.glMatrixMode(5890);
/* 129 */         GL11.glLoadIdentity();
/* 130 */         GL11.glMatrixMode(5888);
/* 131 */         GL11.glEnable(2896);
/* 132 */         GL11.glDisable(3042);
/*     */       } 
/*     */     } 
/*     */     
/* 136 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_77061_b(EntityCreeper par1EntityCreeper, int par2, float par3) {
/* 141 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getCreeperTextures(EntityCreeper par1EntityCreeper) {
/* 147 */     return this.textures[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
/* 156 */     updateCreeperScale((EntityCreeper)par1EntityLivingBase, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getColorMultiplier(EntityLivingBase par1EntityLivingBase, float par2, float par3) {
/* 164 */     return updateCreeperColorMultiplier((EntityCreeper)par1EntityLivingBase, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
/* 172 */     return renderCreeperPassModel((EntityCreeper)par1EntityLivingBase, par2, par3);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int inheritRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
/* 177 */     return func_77061_b((EntityCreeper)par1EntityLivingBase, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 185 */     return getCreeperTextures((EntityCreeper)par1Entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSubtypeName() {
/* 190 */     return "creeper";
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderCreeper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */