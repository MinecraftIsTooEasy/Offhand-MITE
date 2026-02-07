/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderWolf
/*    */   extends RenderLiving
/*    */ {
/*    */   public static final int neutral_texture = 0;
/*    */   public static final int tamed_texture = 1;
/*    */   public static final int angry_texture = 2;
/*    */   public static final int collar_texture = 3;
/*    */   
/*    */   public RenderWolf(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3) {
/* 19 */     super(par1ModelBase, par3);
/* 20 */     setRenderPassModel(par2ModelBase);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setTextures() {
/* 25 */     setTexture(0, "textures/entity/wolf/wolf");
/* 26 */     setTexture(1, "textures/entity/wolf/wolf_tame");
/* 27 */     setTexture(2, "textures/entity/wolf/wolf_angry");
/* 28 */     setTexture(3, "textures/entity/wolf/wolf_collar");
/*    */   }
/*    */ 
/*    */   
/*    */   protected float getTailRotation(EntityWolf par1EntityWolf, float par2) {
/* 33 */     return par1EntityWolf.getTailRotation();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int func_82447_a(EntityWolf par1EntityWolf, int par2, float par3) {
/* 40 */     if (par2 == 0 && par1EntityWolf.getWolfShaking()) {
/*    */       
/* 42 */       float var4 = par1EntityWolf.getBrightness(par3) * par1EntityWolf.getShadingWhileShaking(par3);
/*    */ 
/*    */       
/* 45 */       bindTexture(func_110914_a(par1EntityWolf));
/* 46 */       GL11.glColor3f(var4, var4, var4);
/* 47 */       return 1;
/*    */     } 
/* 49 */     if (par2 == 1 && par1EntityWolf.isTamed()) {
/*    */ 
/*    */       
/* 52 */       bindTexture(this.textures[3]);
/* 53 */       float var4 = 1.0F;
/* 54 */       int var5 = par1EntityWolf.getCollarColor();
/* 55 */       GL11.glColor3f(var4 * EntitySheep.fleeceColorTable[var5][0], var4 * EntitySheep.fleeceColorTable[var5][1], var4 * EntitySheep.fleeceColorTable[var5][2]);
/* 56 */       return 1;
/*    */     } 
/*    */ 
/*    */     
/* 60 */     return -1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_110914_a(EntityWolf par1EntityWolf) {
/* 67 */     return par1EntityWolf.isTamed() ? this.textures[1] : (par1EntityWolf.looksAngry() ? this.textures[2] : this.textures[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
/* 75 */     return func_82447_a((EntityWolf)par1EntityLivingBase, par2, par3);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected float handleRotationFloat(EntityLivingBase par1EntityLivingBase, float par2) {
/* 83 */     return getTailRotation((EntityWolf)par1EntityLivingBase, par2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 91 */     return func_110914_a((EntityWolf)par1Entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderWolf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */