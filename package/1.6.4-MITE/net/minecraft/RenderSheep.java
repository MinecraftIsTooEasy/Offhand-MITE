/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderSheep
/*    */   extends RenderLiving
/*    */ {
/*    */   public static final int body_texture = 0;
/*    */   public static final int fur_texture = 1;
/*    */   public static final int body_texture_sick = 2;
/*    */   
/*    */   public RenderSheep(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3) {
/* 17 */     super(par1ModelBase, par3);
/* 18 */     setRenderPassModel(par2ModelBase);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setTextures() {
/* 23 */     setTexture(0, "textures/entity/sheep/sheep");
/* 24 */     setTexture(1, "textures/entity/sheep/sheep_fur");
/* 25 */     setTexture(2, "textures/entity/sheep/sick");
/*    */   }
/*    */ 
/*    */   
/*    */   protected int setWoolColorAndRender(EntitySheep par1EntitySheep, int par2, float par3) {
/* 30 */     if (par2 == 0 && !par1EntitySheep.getSheared()) {
/*    */ 
/*    */       
/* 33 */       bindTexture(this.textures[1]);
/* 34 */       float var4 = 1.0F;
/* 35 */       int var5 = par1EntitySheep.getFleeceColor();
/* 36 */       GL11.glColor3f(var4 * EntitySheep.fleeceColorTable[var5][0], var4 * EntitySheep.fleeceColorTable[var5][1], var4 * EntitySheep.fleeceColorTable[var5][2]);
/* 37 */       return 1;
/*    */     } 
/*    */ 
/*    */     
/* 41 */     return -1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_110883_a(EntitySheep par1EntitySheep) {
/* 52 */     if (par1EntitySheep.isWell()) {
/* 53 */       return this.textures[0];
/*    */     }
/* 55 */     return this.textures[2];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
/* 63 */     return setWoolColorAndRender((EntitySheep)par1EntityLivingBase, par2, par3);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 71 */     return func_110883_a((EntitySheep)par1Entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderSheep.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */