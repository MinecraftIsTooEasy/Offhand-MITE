/*     */ package net.minecraft;
/*     */ 
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.util.glu.GLU;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ActiveRenderInfo
/*     */ {
/*     */   public static float objectX;
/*     */   public static float objectY;
/*     */   public static float objectZ;
/*  21 */   private static IntBuffer viewport = GLAllocation.createDirectIntBuffer(16);
/*     */ 
/*     */   
/*  24 */   private static FloatBuffer modelview = GLAllocation.createDirectFloatBuffer(16);
/*     */ 
/*     */   
/*  27 */   private static FloatBuffer projection = GLAllocation.createDirectFloatBuffer(16);
/*     */ 
/*     */   
/*  30 */   private static FloatBuffer objectCoords = GLAllocation.createDirectFloatBuffer(3);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float rotationX;
/*     */ 
/*     */ 
/*     */   
/*     */   public static float rotationXZ;
/*     */ 
/*     */ 
/*     */   
/*     */   public static float rotationZ;
/*     */ 
/*     */ 
/*     */   
/*     */   public static float rotationYZ;
/*     */ 
/*     */ 
/*     */   
/*     */   public static float rotationXY;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updateRenderInfo(EntityPlayer par0EntityPlayer, boolean par1) {
/*  57 */     GL11.glGetFloat(2982, modelview);
/*  58 */     GL11.glGetFloat(2983, projection);
/*  59 */     GL11.glGetInteger(2978, viewport);
/*     */     
/*  61 */     float var2 = ((viewport.get(0) + viewport.get(2)) / 2);
/*  62 */     float var3 = ((viewport.get(1) + viewport.get(3)) / 2);
/*  63 */     GLU.gluUnProject(var2, var3, 0.0F, modelview, projection, viewport, objectCoords);
/*  64 */     objectX = objectCoords.get(0);
/*  65 */     objectY = objectCoords.get(1);
/*  66 */     objectZ = objectCoords.get(2);
/*  67 */     int var4 = par1 ? 1 : 0;
/*  68 */     float var5 = par0EntityPlayer.rotationPitch;
/*  69 */     float var6 = par0EntityPlayer.rotationYaw;
/*  70 */     rotationX = MathHelper.cos(var6 * 3.1415927F / 180.0F) * (1 - var4 * 2);
/*  71 */     rotationZ = MathHelper.sin(var6 * 3.1415927F / 180.0F) * (1 - var4 * 2);
/*  72 */     rotationYZ = -rotationZ * MathHelper.sin(var5 * 3.1415927F / 180.0F) * (1 - var4 * 2);
/*  73 */     rotationXY = rotationX * MathHelper.sin(var5 * 3.1415927F / 180.0F) * (1 - var4 * 2);
/*  74 */     rotationXZ = MathHelper.cos(var5 * 3.1415927F / 180.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vec3 projectViewFromEntity(EntityLivingBase par0EntityLivingBase, double par1) {
/*  82 */     double var3 = par0EntityLivingBase.prevPosX + (par0EntityLivingBase.posX - par0EntityLivingBase.prevPosX) * par1;
/*  83 */     double var5 = par0EntityLivingBase.prevPosY + (par0EntityLivingBase.posY - par0EntityLivingBase.prevPosY) * par1 + par0EntityLivingBase.getEyeHeight();
/*  84 */     double var7 = par0EntityLivingBase.prevPosZ + (par0EntityLivingBase.posZ - par0EntityLivingBase.prevPosZ) * par1;
/*  85 */     double var9 = var3 + (objectX * 1.0F);
/*  86 */     double var11 = var5 + (objectY * 1.0F);
/*  87 */     double var13 = var7 + (objectZ * 1.0F);
/*  88 */     return par0EntityLivingBase.worldObj.getWorldVec3Pool().getVecFromPool(var9, var11, var13);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getBlockIdAtEntityViewpoint(World par0World, EntityLivingBase par1EntityLivingBase, float par2) {
/*  97 */     Vec3 var3 = projectViewFromEntity(par1EntityLivingBase, par2);
/*  98 */     ChunkPosition var4 = new ChunkPosition(var3);
/*  99 */     int var5 = par0World.getBlockId(var4.x, var4.y, var4.z);
/*     */     
/* 101 */     if (var5 != 0 && (Block.blocksList[var5]).blockMaterial.isLiquid()) {
/*     */       
/* 103 */       float var6 = BlockFluid.getFluidHeightPercent(par0World.getBlockMetadata(var4.x, var4.y, var4.z)) - 0.11111111F;
/* 104 */       float var7 = (var4.y + 1) - var6;
/*     */       
/* 106 */       if (var3.yCoord >= var7)
/*     */       {
/* 108 */         var5 = par0World.getBlockId(var4.x, var4.y + 1, var4.z);
/*     */       }
/*     */     } 
/*     */     
/* 112 */     return var5;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ActiveRenderInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */