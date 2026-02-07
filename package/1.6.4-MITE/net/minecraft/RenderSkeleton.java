/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderSkeleton
/*    */   extends RenderBiped
/*    */ {
/*    */   public static final int body_texture = 0;
/*    */   public static final int body_texture_wither = 1;
/*    */   public static final int texture_longdead = 2;
/*    */   public static final int texture_longdead_guardian = 3;
/*    */   public static final int texture_bone_lord = 4;
/*    */   
/*    */   public RenderSkeleton() {
/* 18 */     super(new ModelSkeleton(), 0.5F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setTextures() {
/* 23 */     setTexture(0, "textures/entity/skeleton/skeleton");
/* 24 */     setTexture(1, "textures/entity/skeleton/wither_skeleton");
/* 25 */     setTexture(2, "textures/entity/skeleton/longdead");
/* 26 */     setTexture(3, "textures/entity/skeleton/longdead_guardian");
/* 27 */     setTexture(4, "textures/entity/skeleton/bone_lord");
/*    */   }
/*    */ 
/*    */   
/*    */   protected void scaleSkeleton(EntitySkeleton par1EntitySkeleton, float par2) {
/* 32 */     if (par1EntitySkeleton.getSkeletonType() == 1)
/*    */     {
/* 34 */       GL11.glScalef(1.2F, 1.2F, 1.2F);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_82422_c() {
/* 40 */     GL11.glTranslatef(0.09375F, 0.1875F, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_110860_a(EntitySkeleton par1EntitySkeleton) {
/* 45 */     if (par1EntitySkeleton.isLongdead())
/* 46 */       return this.textures[par1EntitySkeleton.isLongdeadGuardian() ? 3 : 2]; 
/* 47 */     if (par1EntitySkeleton.isBoneLord()) {
/* 48 */       return this.textures[par1EntitySkeleton.isAncientBoneLord() ? 3 : 4];
/*    */     }
/*    */     
/* 51 */     return (par1EntitySkeleton.getSkeletonType() == 1) ? this.textures[1] : this.textures[0];
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving) {
/* 56 */     return func_110860_a((EntitySkeleton)par1EntityLiving);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
/* 65 */     scaleSkeleton((EntitySkeleton)par1EntityLivingBase, par2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 73 */     return func_110860_a((EntitySkeleton)par1Entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderSkeleton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */