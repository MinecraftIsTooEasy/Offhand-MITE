/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderLightningBolt
/*     */   extends Render
/*     */ {
/*     */   public void doRenderLightningBolt(EntityLightningBolt par1EntityLightningBolt, double par2, double par4, double par6, float par8, float par9) {
/*  13 */     if (Minecraft.theMinecraft.raining_strength_for_render_view_entity < 0.5F) {
/*     */       return;
/*     */     }
/*  16 */     Tessellator var10 = Tessellator.instance;
/*  17 */     GL11.glDisable(3553);
/*  18 */     GL11.glDisable(2896);
/*  19 */     GL11.glEnable(3042);
/*  20 */     GL11.glBlendFunc(770, 1);
/*  21 */     double[] var11 = new double[8];
/*  22 */     double[] var12 = new double[8];
/*  23 */     double var13 = 0.0D;
/*  24 */     double var15 = 0.0D;
/*  25 */     Random var17 = new Random(par1EntityLightningBolt.boltVertex);
/*     */     
/*  27 */     for (int var18 = 7; var18 >= 0; var18--) {
/*     */       
/*  29 */       var11[var18] = var13;
/*  30 */       var12[var18] = var15;
/*  31 */       var13 += (var17.nextInt(11) - 5);
/*  32 */       var15 += (var17.nextInt(11) - 5);
/*     */     } 
/*     */     
/*  35 */     for (int var45 = 0; var45 < 4; var45++) {
/*     */       
/*  37 */       Random var46 = new Random(par1EntityLightningBolt.boltVertex);
/*     */       
/*  39 */       for (int var19 = 0; var19 < 3; var19++) {
/*     */         
/*  41 */         int var20 = 7;
/*  42 */         int var21 = 0;
/*     */         
/*  44 */         if (var19 > 0)
/*     */         {
/*  46 */           var20 = 7 - var19;
/*     */         }
/*     */         
/*  49 */         if (var19 > 0)
/*     */         {
/*  51 */           var21 = var20 - 2;
/*     */         }
/*     */         
/*  54 */         double var22 = var11[var20] - var13;
/*  55 */         double var24 = var12[var20] - var15;
/*     */         
/*  57 */         for (int var26 = var20; var26 >= var21; var26--) {
/*     */           
/*  59 */           double var27 = var22;
/*  60 */           double var29 = var24;
/*     */           
/*  62 */           if (var19 == 0) {
/*     */             
/*  64 */             var22 += (var46.nextInt(11) - 5);
/*  65 */             var24 += (var46.nextInt(11) - 5);
/*     */           }
/*     */           else {
/*     */             
/*  69 */             var22 += (var46.nextInt(31) - 15);
/*  70 */             var24 += (var46.nextInt(31) - 15);
/*     */           } 
/*     */           
/*  73 */           var10.startDrawing(5);
/*  74 */           float var31 = 0.5F;
/*  75 */           var10.setColorRGBA_F(0.9F * var31, 0.9F * var31, 1.0F * var31, 0.3F);
/*  76 */           double var32 = 0.1D + var45 * 0.2D;
/*     */           
/*  78 */           if (var19 == 0)
/*     */           {
/*  80 */             var32 *= var26 * 0.1D + 1.0D;
/*     */           }
/*     */           
/*  83 */           double var34 = 0.1D + var45 * 0.2D;
/*     */           
/*  85 */           if (var19 == 0)
/*     */           {
/*  87 */             var34 *= (var26 - 1) * 0.1D + 1.0D;
/*     */           }
/*     */           
/*  90 */           for (int var36 = 0; var36 < 5; var36++) {
/*     */             
/*  92 */             double var37 = par2 + 0.5D - var32;
/*  93 */             double var39 = par6 + 0.5D - var32;
/*     */             
/*  95 */             if (var36 == 1 || var36 == 2)
/*     */             {
/*  97 */               var37 += var32 * 2.0D;
/*     */             }
/*     */             
/* 100 */             if (var36 == 2 || var36 == 3)
/*     */             {
/* 102 */               var39 += var32 * 2.0D;
/*     */             }
/*     */             
/* 105 */             double var41 = par2 + 0.5D - var34;
/* 106 */             double var43 = par6 + 0.5D - var34;
/*     */             
/* 108 */             if (var36 == 1 || var36 == 2)
/*     */             {
/* 110 */               var41 += var34 * 2.0D;
/*     */             }
/*     */             
/* 113 */             if (var36 == 2 || var36 == 3)
/*     */             {
/* 115 */               var43 += var34 * 2.0D;
/*     */             }
/*     */             
/* 118 */             var10.addVertex(var41 + var22, par4 + (var26 * 16), var43 + var24);
/* 119 */             var10.addVertex(var37 + var27, par4 + ((var26 + 1) * 16), var39 + var29);
/*     */           } 
/*     */           
/* 122 */           var10.draw();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 127 */     GL11.glDisable(3042);
/* 128 */     GL11.glEnable(2896);
/* 129 */     GL11.glEnable(3553);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110805_a(EntityLightningBolt par1EntityLightningBolt) {
/* 134 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 142 */     return func_110805_a((EntityLightningBolt)par1Entity);
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
/* 153 */     doRenderLightningBolt((EntityLightningBolt)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderLightningBolt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */