/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderArrow
/*     */   extends Render
/*     */ {
/*     */   private static ResourceLocation[] textures;
/*     */   
/*     */   public RenderArrow() {
/*  14 */     if (textures == null) {
/*  15 */       addTextures();
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
/*     */   public void addTextures() {
/*  27 */     textures = new ResourceLocation[ItemArrow.material_types.length];
/*     */     
/*  29 */     for (int i = 0; i < textures.length; i++) {
/*  30 */       textures[i] = new ResourceLocation("textures/entity/arrows/" + (ItemArrow.material_types[i]).name + ".png");
/*     */     }
/*     */   }
/*     */   
/*     */   public void renderArrow(EntityArrow par1EntityArrow, double par2, double par4, double par6, float par8, float par9) {
/*  35 */     bindEntityTexture(par1EntityArrow);
/*  36 */     GL11.glPushMatrix();
/*  37 */     GL11.glTranslatef((float)par2, (float)par4, (float)par6);
/*  38 */     GL11.glRotatef(par1EntityArrow.prevRotationYaw + (par1EntityArrow.rotationYaw - par1EntityArrow.prevRotationYaw) * par9 - 90.0F, 0.0F, 1.0F, 0.0F);
/*  39 */     GL11.glRotatef(par1EntityArrow.prevRotationPitch + (par1EntityArrow.rotationPitch - par1EntityArrow.prevRotationPitch) * par9, 0.0F, 0.0F, 1.0F);
/*  40 */     Tessellator var10 = Tessellator.instance;
/*  41 */     byte var11 = 0;
/*  42 */     float var12 = 0.0F;
/*  43 */     float var13 = 0.5F;
/*  44 */     float var14 = (0 + var11 * 10) / 32.0F;
/*  45 */     float var15 = (5 + var11 * 10) / 32.0F;
/*  46 */     float var16 = 0.0F;
/*  47 */     float var17 = 0.15625F;
/*  48 */     float var18 = (5 + var11 * 10) / 32.0F;
/*  49 */     float var19 = (10 + var11 * 10) / 32.0F;
/*  50 */     float var20 = 0.05625F;
/*  51 */     GL11.glEnable(32826);
/*  52 */     float var21 = par1EntityArrow.arrowShake - par9;
/*     */     
/*  54 */     if (var21 > 0.0F) {
/*     */       
/*  56 */       float var22 = -MathHelper.sin(var21 * 3.0F) * var21;
/*  57 */       GL11.glRotatef(var22, 0.0F, 0.0F, 1.0F);
/*     */     } 
/*     */     
/*  60 */     GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
/*  61 */     GL11.glScalef(var20, var20, var20);
/*  62 */     GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
/*  63 */     GL11.glNormal3f(var20, 0.0F, 0.0F);
/*  64 */     var10.startDrawingQuads();
/*  65 */     var10.addVertexWithUV(-7.0D, -2.0D, -2.0D, var16, var18);
/*  66 */     var10.addVertexWithUV(-7.0D, -2.0D, 2.0D, var17, var18);
/*  67 */     var10.addVertexWithUV(-7.0D, 2.0D, 2.0D, var17, var19);
/*  68 */     var10.addVertexWithUV(-7.0D, 2.0D, -2.0D, var16, var19);
/*  69 */     var10.draw();
/*  70 */     GL11.glNormal3f(-var20, 0.0F, 0.0F);
/*  71 */     var10.startDrawingQuads();
/*  72 */     var10.addVertexWithUV(-7.0D, 2.0D, -2.0D, var16, var18);
/*  73 */     var10.addVertexWithUV(-7.0D, 2.0D, 2.0D, var17, var18);
/*  74 */     var10.addVertexWithUV(-7.0D, -2.0D, 2.0D, var17, var19);
/*  75 */     var10.addVertexWithUV(-7.0D, -2.0D, -2.0D, var16, var19);
/*  76 */     var10.draw();
/*     */     
/*  78 */     for (int var23 = 0; var23 < 4; var23++) {
/*     */       
/*  80 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*  81 */       GL11.glNormal3f(0.0F, 0.0F, var20);
/*  82 */       var10.startDrawingQuads();
/*  83 */       var10.addVertexWithUV(-8.0D, -2.0D, 0.0D, var12, var14);
/*  84 */       var10.addVertexWithUV(8.0D, -2.0D, 0.0D, var13, var14);
/*  85 */       var10.addVertexWithUV(8.0D, 2.0D, 0.0D, var13, var15);
/*  86 */       var10.addVertexWithUV(-8.0D, 2.0D, 0.0D, var12, var15);
/*  87 */       var10.draw();
/*     */     } 
/*     */     
/*  90 */     GL11.glDisable(32826);
/*  91 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getArrowTextures(EntityArrow par1EntityArrow) {
/*  97 */     return textures[par1EntityArrow.item_arrow.getArrowIndex()];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 105 */     return getArrowTextures((EntityArrow)par1Entity);
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
/* 116 */     renderArrow((EntityArrow)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderArrow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */