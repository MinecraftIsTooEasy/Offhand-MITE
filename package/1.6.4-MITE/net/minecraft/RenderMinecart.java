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
/*     */ public class RenderMinecart
/*     */   extends Render
/*     */ {
/*  16 */   private static final ResourceLocation minecartTextures = new ResourceLocation("textures/entity/minecart.png");
/*  17 */   protected ModelBase modelMinecart = new ModelMinecart();
/*     */   protected final RenderBlocks field_94145_f;
/*     */   
/*     */   public RenderMinecart() {
/*  21 */     this.shadowSize = 0.5F;
/*  22 */     this.field_94145_f = new RenderBlocks();
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderTheMinecart(EntityMinecart entityMinecart, double d, double e, double f, float g, float h) {
/*  27 */     GL11.glPushMatrix();
/*     */     
/*  29 */     bindEntityTexture(entityMinecart);
/*     */     
/*  31 */     long l = entityMinecart.entityId * 493286711L;
/*  32 */     l = l * l * 4392167121L + l * 98761L;
/*     */     
/*  34 */     float f1 = (((float)(l >> 16L & 0x7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
/*  35 */     float f2 = (((float)(l >> 20L & 0x7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
/*  36 */     float f3 = (((float)(l >> 24L & 0x7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
/*     */     
/*  38 */     GL11.glTranslatef(f1, f2, f3);
/*     */     
/*  40 */     double d1 = entityMinecart.lastTickPosX + (entityMinecart.posX - entityMinecart.lastTickPosX) * h;
/*  41 */     double d2 = entityMinecart.lastTickPosY + (entityMinecart.posY - entityMinecart.lastTickPosY) * h;
/*  42 */     double d3 = entityMinecart.lastTickPosZ + (entityMinecart.posZ - entityMinecart.lastTickPosZ) * h;
/*     */     
/*  44 */     double d4 = 0.30000001192092896D;
/*     */     
/*  46 */     Vec3 vec3 = entityMinecart.func_70489_a(d1, d2, d3);
/*     */     
/*  48 */     float f4 = entityMinecart.prevRotationPitch + (entityMinecart.rotationPitch - entityMinecart.prevRotationPitch) * h;
/*     */     
/*  50 */     if (vec3 != null) {
/*  51 */       Vec3 vec31 = entityMinecart.func_70495_a(d1, d2, d3, d4);
/*  52 */       Vec3 vec32 = entityMinecart.func_70495_a(d1, d2, d3, -d4);
/*  53 */       if (vec31 == null) vec31 = vec3; 
/*  54 */       if (vec32 == null) vec32 = vec3;
/*     */       
/*  56 */       d += vec3.xCoord - d1;
/*  57 */       e += (vec31.yCoord + vec32.yCoord) / 2.0D - d2;
/*  58 */       f += vec3.zCoord - d3;
/*     */       
/*  60 */       Vec3 vec33 = vec32.addVector(-vec31.xCoord, -vec31.yCoord, -vec31.zCoord);
/*  61 */       if (vec33.lengthVector() != 0.0D) {
/*  62 */         vec33 = vec33.normalize();
/*  63 */         g = (float)(Math.atan2(vec33.zCoord, vec33.xCoord) * 180.0D / Math.PI);
/*  64 */         f4 = (float)(Math.atan(vec33.yCoord) * 73.0D);
/*     */       } 
/*     */     } 
/*  67 */     GL11.glTranslatef((float)d, (float)e, (float)f);
/*     */     
/*  69 */     GL11.glRotatef(180.0F - g, 0.0F, 1.0F, 0.0F);
/*  70 */     GL11.glRotatef(-f4, 0.0F, 0.0F, 1.0F);
/*  71 */     float f5 = entityMinecart.getRollingAmplitude() - h;
/*  72 */     float f6 = entityMinecart.getDamage() - h;
/*  73 */     if (f6 < 0.0F) f6 = 0.0F; 
/*  74 */     if (f5 > 0.0F) {
/*  75 */       GL11.glRotatef(MathHelper.sin(f5) * f5 * f6 / 10.0F * entityMinecart.getRollingDirection(), 1.0F, 0.0F, 0.0F);
/*     */     }
/*  77 */     int i = entityMinecart.getDisplayTileOffset();
/*  78 */     Block block = entityMinecart.getDisplayTile();
/*  79 */     int j = entityMinecart.getDisplayTileData();
/*     */     
/*  81 */     if (block != null) {
/*  82 */       GL11.glPushMatrix();
/*     */       
/*  84 */       bindTexture(TextureMap.locationBlocksTexture);
/*  85 */       float f7 = 0.75F;
/*     */       
/*  87 */       GL11.glScalef(f7, f7, f7);
/*  88 */       GL11.glTranslatef(0.0F, i / 16.0F, 0.0F);
/*  89 */       renderBlockInMinecart(entityMinecart, h, block, j);
/*     */       
/*  91 */       GL11.glPopMatrix();
/*  92 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  93 */       bindEntityTexture(entityMinecart);
/*     */     } 
/*     */     
/*  96 */     GL11.glScalef(-1.0F, -1.0F, 1.0F);
/*  97 */     this.modelMinecart.render(entityMinecart, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*  98 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation getMinecartTextures(EntityMinecart entityMinecart) {
/* 103 */     return minecartTextures;
/*     */   }
/*     */   
/*     */   protected void renderBlockInMinecart(EntityMinecart entityMinecart, float f, Block block, int i) {
/* 107 */     float f1 = entityMinecart.getBrightness(f);
/*     */     
/* 109 */     GL11.glPushMatrix();
/* 110 */     this.field_94145_f.renderBlockAsItem(block, i, f1);
/* 111 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderMinecart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */