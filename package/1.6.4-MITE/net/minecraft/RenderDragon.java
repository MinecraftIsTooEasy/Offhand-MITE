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
/*     */ 
/*     */ 
/*     */ public class RenderDragon
/*     */   extends RenderLiving
/*     */ {
/*     */   public static final int texture_body = 0;
/*     */   public static final int texture_beam = 1;
/*     */   public static final int texture_exploding = 2;
/*     */   protected ModelDragon modelDragon;
/*     */   
/*     */   public RenderDragon() {
/*  22 */     super(new ModelDragon(0.0F), 0.5F);
/*  23 */     this.modelDragon = (ModelDragon)this.mainModel;
/*  24 */     setRenderPassModel(this.mainModel);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setTextures() {
/*  29 */     setTexture(0, "textures/entity/enderdragon/dragon");
/*  30 */     setTexture(1, "textures/entity/endercrystal/endercrystal_beam");
/*  31 */     setTexture(2, "textures/entity/enderdragon/dragon_exploding");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void rotateDragonBody(EntityDragon par1EntityDragon, float par2, float par3, float par4) {
/*  39 */     float var5 = (float)par1EntityDragon.getMovementOffsets(7, par4)[0];
/*  40 */     float var6 = (float)(par1EntityDragon.getMovementOffsets(5, par4)[1] - par1EntityDragon.getMovementOffsets(10, par4)[1]);
/*  41 */     GL11.glRotatef(-var5, 0.0F, 1.0F, 0.0F);
/*  42 */     GL11.glRotatef(var6 * 10.0F, 1.0F, 0.0F, 0.0F);
/*  43 */     GL11.glTranslatef(0.0F, 0.0F, 1.0F);
/*     */     
/*  45 */     if (par1EntityDragon.deathTime > 0) {
/*     */       
/*  47 */       float var7 = (par1EntityDragon.deathTime + par4 - 1.0F) / 20.0F * 1.6F;
/*  48 */       var7 = MathHelper.sqrt_float(var7);
/*     */       
/*  50 */       if (var7 > 1.0F)
/*     */       {
/*  52 */         var7 = 1.0F;
/*     */       }
/*     */       
/*  55 */       GL11.glRotatef(var7 * getDeathMaxRotation(par1EntityDragon), 0.0F, 0.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderDragonModel(EntityDragon par1EntityDragon, float par2, float par3, float par4, float par5, float par6, float par7) {
/*  64 */     boolean render_as_dying = (par1EntityDragon.deathTicks > 0);
/*     */ 
/*     */     
/*  67 */     if (render_as_dying) {
/*     */       
/*  69 */       GL11.glPushAttrib(2932);
/*  70 */       float var8 = par1EntityDragon.deathTicks / 200.0F;
/*  71 */       GL11.glDepthFunc(515);
/*  72 */       GL11.glEnable(3008);
/*  73 */       GL11.glAlphaFunc(516, var8);
/*     */       
/*  75 */       bindTexture(this.textures[2]);
/*  76 */       this.mainModel.render(par1EntityDragon, par2, par3, par4, par5, par6, par7);
/*  77 */       GL11.glAlphaFunc(516, 0.1F);
/*  78 */       GL11.glDepthFunc(514);
/*     */     } 
/*     */     
/*  81 */     bindEntityTexture(par1EntityDragon);
/*  82 */     this.mainModel.render(par1EntityDragon, par2, par3, par4, par5, par6, par7);
/*     */     
/*  84 */     renderModelGlowing(par1EntityDragon, par2, par3, par4, par5, par6, par7);
/*     */     
/*  86 */     if (render_as_dying) {
/*  87 */       GL11.glPopAttrib();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  92 */     if (par1EntityDragon.hurtTime > 0) {
/*     */       
/*  94 */       GL11.glDepthFunc(514);
/*  95 */       GL11.glDisable(3553);
/*  96 */       GL11.glEnable(3042);
/*  97 */       GL11.glBlendFunc(770, 771);
/*  98 */       GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.5F);
/*  99 */       this.mainModel.render(par1EntityDragon, par2, par3, par4, par5, par6, par7);
/* 100 */       GL11.glEnable(3553);
/* 101 */       GL11.glDisable(3042);
/* 102 */       GL11.glDepthFunc(515);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderDragon(EntityDragon par1EntityDragon, double par2, double par4, double par6, float par8, float par9) {
/* 111 */     BossStatus.setBossStatus(par1EntityDragon, false);
/* 112 */     super.doRenderLiving(par1EntityDragon, par2, par4, par6, par8, par9);
/*     */     
/* 114 */     if (par1EntityDragon.healingEnderCrystal != null) {
/*     */       
/* 116 */       float var10 = par1EntityDragon.healingEnderCrystal.innerRotation + par9;
/* 117 */       float var11 = MathHelper.sin(var10 * 0.2F) / 2.0F + 0.5F;
/* 118 */       var11 = (var11 * var11 + var11) * 0.2F;
/* 119 */       float var12 = (float)(par1EntityDragon.healingEnderCrystal.posX - par1EntityDragon.posX - (par1EntityDragon.prevPosX - par1EntityDragon.posX) * (1.0F - par9));
/* 120 */       float var13 = (float)(var11 + par1EntityDragon.healingEnderCrystal.posY - 1.0D - par1EntityDragon.posY - (par1EntityDragon.prevPosY - par1EntityDragon.posY) * (1.0F - par9));
/* 121 */       float var14 = (float)(par1EntityDragon.healingEnderCrystal.posZ - par1EntityDragon.posZ - (par1EntityDragon.prevPosZ - par1EntityDragon.posZ) * (1.0F - par9));
/* 122 */       float var15 = MathHelper.sqrt_float(var12 * var12 + var14 * var14);
/* 123 */       float var16 = MathHelper.sqrt_float(var12 * var12 + var13 * var13 + var14 * var14);
/* 124 */       GL11.glPushMatrix();
/* 125 */       GL11.glTranslatef((float)par2, (float)par4 + 2.0F, (float)par6);
/* 126 */       GL11.glRotatef((float)-Math.atan2(var14, var12) * 180.0F / 3.1415927F - 90.0F, 0.0F, 1.0F, 0.0F);
/* 127 */       GL11.glRotatef((float)-Math.atan2(var15, var13) * 180.0F / 3.1415927F - 90.0F, 1.0F, 0.0F, 0.0F);
/* 128 */       Tessellator var17 = Tessellator.instance;
/* 129 */       RenderHelper.disableStandardItemLighting();
/* 130 */       GL11.glDisable(2884);
/*     */       
/* 132 */       bindTexture(this.textures[1]);
/* 133 */       GL11.glShadeModel(7425);
/* 134 */       float var18 = 0.0F - (par1EntityDragon.ticksExisted + par9) * 0.01F;
/* 135 */       float var19 = MathHelper.sqrt_float(var12 * var12 + var13 * var13 + var14 * var14) / 32.0F - (par1EntityDragon.ticksExisted + par9) * 0.01F;
/* 136 */       var17.startDrawing(5);
/* 137 */       byte var20 = 8;
/*     */       
/* 139 */       for (int var21 = 0; var21 <= var20; var21++) {
/*     */         
/* 141 */         float var22 = MathHelper.sin((var21 % var20) * 3.1415927F * 2.0F / var20) * 0.75F;
/* 142 */         float var23 = MathHelper.cos((var21 % var20) * 3.1415927F * 2.0F / var20) * 0.75F;
/* 143 */         float var24 = (var21 % var20) * 1.0F / var20;
/* 144 */         var17.setColorOpaque_I(0);
/* 145 */         var17.addVertexWithUV((var22 * 0.2F), (var23 * 0.2F), 0.0D, var24, var19);
/* 146 */         var17.setColorOpaque_I(16777215);
/* 147 */         var17.addVertexWithUV(var22, var23, var16, var24, var18);
/*     */       } 
/*     */       
/* 150 */       var17.draw();
/* 151 */       GL11.glEnable(2884);
/* 152 */       GL11.glShadeModel(7424);
/* 153 */       RenderHelper.enableStandardItemLighting();
/* 154 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEnderDragonTextures(EntityDragon par1EntityDragon) {
/* 161 */     return this.textures[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderDragonDying(EntityDragon par1EntityDragon, float par2) {
/* 169 */     super.renderEquippedItems(par1EntityDragon, par2);
/* 170 */     Tessellator var3 = Tessellator.instance;
/*     */     
/* 172 */     if (par1EntityDragon.deathTicks > 0) {
/*     */       
/* 174 */       RenderHelper.disableStandardItemLighting();
/* 175 */       float var4 = (par1EntityDragon.deathTicks + par2) / 200.0F;
/* 176 */       float var5 = 0.0F;
/*     */       
/* 178 */       if (var4 > 0.8F)
/*     */       {
/* 180 */         var5 = (var4 - 0.8F) / 0.2F;
/*     */       }
/*     */       
/* 183 */       Random var6 = new Random(432L);
/* 184 */       GL11.glDisable(3553);
/* 185 */       GL11.glShadeModel(7425);
/* 186 */       GL11.glEnable(3042);
/* 187 */       GL11.glBlendFunc(770, 1);
/* 188 */       GL11.glDisable(3008);
/* 189 */       GL11.glEnable(2884);
/* 190 */       GL11.glDepthMask(false);
/* 191 */       GL11.glPushMatrix();
/* 192 */       GL11.glTranslatef(0.0F, -1.0F, -2.0F);
/*     */       
/* 194 */       for (int var7 = 0; var7 < (var4 + var4 * var4) / 2.0F * 60.0F; var7++) {
/*     */         
/* 196 */         GL11.glRotatef(var6.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
/* 197 */         GL11.glRotatef(var6.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
/* 198 */         GL11.glRotatef(var6.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
/* 199 */         GL11.glRotatef(var6.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
/* 200 */         GL11.glRotatef(var6.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
/* 201 */         GL11.glRotatef(var6.nextFloat() * 360.0F + var4 * 90.0F, 0.0F, 0.0F, 1.0F);
/* 202 */         var3.startDrawing(6);
/* 203 */         float var8 = var6.nextFloat() * 20.0F + 5.0F + var5 * 10.0F;
/* 204 */         float var9 = var6.nextFloat() * 2.0F + 1.0F + var5 * 2.0F;
/* 205 */         var3.setColorRGBA_I(16777215, (int)(255.0F * (1.0F - var5)));
/* 206 */         var3.addVertex(0.0D, 0.0D, 0.0D);
/* 207 */         var3.setColorRGBA_I(16711935, 0);
/* 208 */         var3.addVertex(-0.866D * var9, var8, (-0.5F * var9));
/* 209 */         var3.addVertex(0.866D * var9, var8, (-0.5F * var9));
/* 210 */         var3.addVertex(0.0D, var8, (1.0F * var9));
/* 211 */         var3.addVertex(-0.866D * var9, var8, (-0.5F * var9));
/* 212 */         var3.draw();
/*     */       } 
/*     */       
/* 215 */       GL11.glPopMatrix();
/* 216 */       GL11.glDepthMask(true);
/* 217 */       GL11.glDisable(2884);
/* 218 */       GL11.glDisable(3042);
/* 219 */       GL11.glShadeModel(7424);
/* 220 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 221 */       GL11.glEnable(3553);
/* 222 */       GL11.glEnable(3008);
/* 223 */       RenderHelper.enableStandardItemLighting();
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
/*     */   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
/* 263 */     renderDragon((EntityDragon)par1EntityLiving, par2, par4, par6, par8, par9);
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
/* 276 */     renderDragonDying((EntityDragon)par1EntityLivingBase, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void rotateCorpse(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
/* 281 */     rotateDragonBody((EntityDragon)par1EntityLivingBase, par2, par3, par4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7) {
/* 289 */     renderDragonModel((EntityDragon)par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
/* 294 */     renderDragon((EntityDragon)par1EntityLivingBase, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 302 */     return getEnderDragonTextures((EntityDragon)par1Entity);
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
/* 313 */     renderDragon((EntityDragon)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderDragon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */