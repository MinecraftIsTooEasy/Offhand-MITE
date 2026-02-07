/*     */ package net.minecraft;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class RenderHorse
/*     */   extends RenderLiving {
/*   9 */   private static final Map field_110852_a = Maps.newHashMap();
/*     */ 
/*     */   
/*     */   public static final int body_texture_horse = 0;
/*     */   
/*     */   public static final int body_texture_donkey = 1;
/*     */   
/*     */   public static final int body_texture_mule = 2;
/*     */   
/*     */   public static final int body_texture_zombie = 3;
/*     */   
/*     */   public static final int body_texture_skeleton = 4;
/*     */ 
/*     */   
/*     */   public RenderHorse(ModelBase par1ModelBase, float par2) {
/*  24 */     super(par1ModelBase, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setTextures() {
/*  31 */     setTexture(0, "textures/entity/horse/horse_white");
/*  32 */     setTexture(1, "textures/entity/horse/donkey");
/*  33 */     setTexture(2, "textures/entity/horse/mule");
/*  34 */     setTexture(3, "textures/entity/horse/horse_zombie");
/*  35 */     setTexture(4, "textures/entity/horse/horse_skeleton");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_110847_a(EntityHorse par1EntityHorse, float par2) {
/*  40 */     float var3 = 1.0F;
/*  41 */     int var4 = par1EntityHorse.getHorseType();
/*     */     
/*  43 */     if (var4 == 1) {
/*     */       
/*  45 */       var3 *= 0.87F;
/*     */     }
/*  47 */     else if (var4 == 2) {
/*     */       
/*  49 */       var3 *= 0.92F;
/*     */     } 
/*     */     
/*  52 */     GL11.glScalef(var3, var3, var3);
/*  53 */     super.preRenderCallback(par1EntityHorse, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_110846_a(EntityHorse par1EntityHorse, float par2, float par3, float par4, float par5, float par6, float par7) {
/*  58 */     if (par1EntityHorse.isInvisible()) {
/*     */       
/*  60 */       this.mainModel.setRotationAngles(par2, par3, par4, par5, par6, par7, par1EntityHorse);
/*     */     }
/*     */     else {
/*     */       
/*  64 */       bindEntityTexture(par1EntityHorse);
/*  65 */       this.mainModel.render(par1EntityHorse, par2, par3, par4, par5, par6, par7);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110849_a(EntityHorse par1EntityHorse) {
/*  71 */     if (!par1EntityHorse.func_110239_cn()) {
/*     */       
/*  73 */       switch (par1EntityHorse.getHorseType()) {
/*     */ 
/*     */ 
/*     */         
/*     */         default:
/*  78 */           return this.textures[0];
/*     */ 
/*     */         
/*     */         case 1:
/*  82 */           return this.textures[1];
/*     */ 
/*     */         
/*     */         case 2:
/*  86 */           return this.textures[2];
/*     */ 
/*     */         
/*     */         case 3:
/*  90 */           return this.textures[3];
/*     */         case 4:
/*     */           break;
/*     */       } 
/*  94 */       return this.textures[4];
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  99 */     return func_110848_b(par1EntityHorse);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ResourceLocation func_110848_b(EntityHorse par1EntityHorse) {
/* 105 */     String var2 = par1EntityHorse.getHorseTexture();
/* 106 */     ResourceLocation var3 = (ResourceLocation)field_110852_a.get(var2);
/*     */     
/* 108 */     if (var3 == null) {
/*     */ 
/*     */       
/* 111 */       var3 = new ResourceLocation(var2, false);
/* 112 */       Minecraft.getMinecraft().getTextureManager().loadTexture(var3, new LayeredTexture(par1EntityHorse.getVariantTexturePaths()));
/* 113 */       field_110852_a.put(var2, var3);
/*     */     } 
/*     */     
/* 116 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
/* 125 */     func_110847_a((EntityHorse)par1EntityLivingBase, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7) {
/* 133 */     func_110846_a((EntityHorse)par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 141 */     return func_110849_a((EntityHorse)par1Entity);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderHorse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */