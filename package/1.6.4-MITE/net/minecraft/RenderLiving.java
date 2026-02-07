/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public abstract class RenderLiving
/*     */   extends RendererLivingEntity
/*     */ {
/*     */   public RenderLiving(ModelBase par1ModelBase, float par2) {
/*   9 */     super(par1ModelBase, par2);
/*     */     
/*  11 */     setTextures();
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract void setTextures();
/*     */   
/*     */   protected boolean func_130007_b(EntityLiving par1EntityLiving) {
/*  18 */     return (super.func_110813_b(par1EntityLiving) && (par1EntityLiving.getAlwaysRenderNameTagForRender() || (par1EntityLiving.hasCustomNameTag() && par1EntityLiving == this.renderManager.field_96451_i)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
/*  23 */     if (par1EntityLiving instanceof EntityCreeper) {
/*  24 */       par4 -= 0.11999999731779099D;
/*     */     }
/*  26 */     super.doRenderLiving(par1EntityLiving, par2, par4, par6, par8, par9);
/*  27 */     func_110827_b(par1EntityLiving, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */   
/*     */   private double func_110828_a(double par1, double par3, double par5) {
/*  32 */     return par1 + (par3 - par1) * par5;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_110827_b(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
/*  37 */     Entity var10 = par1EntityLiving.getLeashedToEntity();
/*     */     
/*  39 */     if (var10 != null) {
/*     */       float height_adjustment;
/*  41 */       par4 -= (1.6D - par1EntityLiving.height) * 0.5D;
/*     */ 
/*     */       
/*  44 */       boolean is_child = par1EntityLiving.isChild();
/*     */       
/*  46 */       if (par1EntityLiving instanceof EntityChicken) {
/*  47 */         height_adjustment = is_child ? -0.45F : -0.35F;
/*  48 */       } else if (par1EntityLiving instanceof EntitySheep) {
/*  49 */         height_adjustment = is_child ? -0.3F : -0.1F;
/*  50 */       } else if (par1EntityLiving instanceof EntityPig) {
/*  51 */         height_adjustment = is_child ? -0.4F : -0.3F;
/*  52 */       } else if (par1EntityLiving instanceof EntityCow) {
/*  53 */         height_adjustment = is_child ? -0.25F : -0.0F;
/*  54 */       } else if (par1EntityLiving instanceof EntityWolf) {
/*  55 */         height_adjustment = is_child ? -0.45F : -0.2F;
/*  56 */       } else if (par1EntityLiving instanceof EntityOcelot) {
/*  57 */         height_adjustment = is_child ? -0.55F : -0.45F;
/*     */       } else {
/*  59 */         height_adjustment = 0.0F;
/*     */       } 
/*  61 */       Tessellator var11 = Tessellator.instance;
/*  62 */       double var12 = func_110828_a(var10.prevRotationYaw, var10.rotationYaw, (par9 * 0.5F)) * 0.01745329238474369D;
/*  63 */       double var14 = func_110828_a(var10.prevRotationPitch, var10.rotationPitch, (par9 * 0.5F)) * 0.01745329238474369D;
/*  64 */       double var16 = Math.cos(var12);
/*  65 */       double var18 = Math.sin(var12);
/*  66 */       double var20 = Math.sin(var14);
/*     */       
/*  68 */       if (var10 instanceof EntityHanging) {
/*     */         
/*  70 */         var16 = 0.0D;
/*  71 */         var18 = 0.0D;
/*  72 */         var20 = -1.0D;
/*     */       } 
/*     */       
/*  75 */       double var22 = Math.cos(var14);
/*  76 */       double var24 = func_110828_a(var10.prevPosX, var10.posX, par9) - var16 * 0.7D - var18 * 0.5D * var22;
/*  77 */       double var26 = func_110828_a(var10.prevPosY + var10.getEyeHeight() * 0.7D, var10.posY + var10.getEyeHeight() * 0.7D, par9) - var20 * 0.5D - 0.25D;
/*  78 */       double var28 = func_110828_a(var10.prevPosZ, var10.posZ, par9) - var18 * 0.7D + var16 * 0.5D * var22;
/*  79 */       double var30 = func_110828_a(par1EntityLiving.prevRenderYawOffset, par1EntityLiving.renderYawOffset, par9) * 0.01745329238474369D + 1.5707963267948966D;
/*  80 */       var16 = Math.cos(var30) * par1EntityLiving.width * 0.4D;
/*  81 */       var18 = Math.sin(var30) * par1EntityLiving.width * 0.4D;
/*  82 */       double var32 = func_110828_a(par1EntityLiving.prevPosX, par1EntityLiving.posX, par9) + var16;
/*  83 */       double var34 = func_110828_a(par1EntityLiving.prevPosY, par1EntityLiving.posY, par9);
/*  84 */       double var36 = func_110828_a(par1EntityLiving.prevPosZ, par1EntityLiving.posZ, par9) + var18;
/*  85 */       par2 += var16;
/*  86 */       par6 += var18;
/*  87 */       double var38 = (float)(var24 - var32);
/*  88 */       double var40 = (float)(var26 - var34);
/*  89 */       double var42 = (float)(var28 - var36);
/*  90 */       GL11.glDisable(3553);
/*  91 */       GL11.glDisable(2896);
/*  92 */       GL11.glDisable(2884);
/*  93 */       boolean var44 = true;
/*  94 */       double var45 = 0.025D;
/*  95 */       var11.startDrawing(5);
/*     */       
/*     */       int var47;
/*     */       
/*  99 */       for (var47 = 0; var47 <= 24; var47++) {
/*     */         
/* 101 */         if (var47 % 2 == 0) {
/*     */           
/* 103 */           var11.setColorRGBA_F(0.5F, 0.4F, 0.3F, 1.0F);
/*     */         }
/*     */         else {
/*     */           
/* 107 */           var11.setColorRGBA_F(0.35F, 0.28F, 0.21000001F, 1.0F);
/*     */         } 
/*     */         
/* 110 */         float var48 = var47 / 24.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 115 */         float height_adjustment_fraction = 1.0F - var48;
/* 116 */         double y_animal = (height_adjustment * height_adjustment_fraction) + par4 + var40 * (var48 * var48 + var48) * 0.5D + ((24.0F - var47) / 18.0F + 0.125F);
/*     */         
/* 118 */         var11.addVertex(par2 + var38 * var48 + 0.0D, y_animal, par6 + var42 * var48);
/* 119 */         var11.addVertex(par2 + var38 * var48 + 0.025D, y_animal + 0.025D, par6 + var42 * var48);
/*     */       } 
/*     */       
/* 122 */       var11.draw();
/* 123 */       var11.startDrawing(5);
/*     */       
/* 125 */       for (var47 = 0; var47 <= 24; var47++) {
/*     */         
/* 127 */         if (var47 % 2 == 0) {
/*     */           
/* 129 */           var11.setColorRGBA_F(0.5F, 0.4F, 0.3F, 1.0F);
/*     */         }
/*     */         else {
/*     */           
/* 133 */           var11.setColorRGBA_F(0.35F, 0.28F, 0.21000001F, 1.0F);
/*     */         } 
/*     */         
/* 136 */         float var48 = var47 / 24.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 141 */         float height_adjustment_fraction = 1.0F - var48;
/* 142 */         double y_animal = (height_adjustment * height_adjustment_fraction) + par4 + var40 * (var48 * var48 + var48) * 0.5D + ((24.0F - var47) / 18.0F + 0.125F);
/*     */         
/* 144 */         var11.addVertex(par2 + var38 * var48 + 0.0D, y_animal + 0.025D, par6 + var42 * var48);
/* 145 */         var11.addVertex(par2 + var38 * var48 + 0.025D, y_animal, par6 + var42 * var48 + 0.025D);
/*     */       } 
/*     */       
/* 148 */       var11.draw();
/* 149 */       GL11.glEnable(2896);
/* 150 */       GL11.glEnable(3553);
/* 151 */       GL11.glEnable(2884);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_110813_b(EntityLivingBase par1EntityLivingBase) {
/* 157 */     return func_130007_b((EntityLiving)par1EntityLivingBase);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
/* 162 */     doRenderLiving((EntityLiving)par1EntityLivingBase, par2, par4, par6, par8, par9);
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
/* 173 */     doRenderLiving((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderLiving.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */