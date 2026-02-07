/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderSnowMan
/*    */   extends RenderLiving
/*    */ {
/*    */   public static final int body_texture = 0;
/*    */   private ModelSnowMan snowmanModel;
/*    */   
/*    */   public RenderSnowMan() {
/* 15 */     super(new ModelSnowMan(), 0.5F);
/* 16 */     this.snowmanModel = (ModelSnowMan)this.mainModel;
/* 17 */     setRenderPassModel(this.snowmanModel);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setTextures() {
/* 22 */     setTexture(0, "textures/entity/snowman");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void renderSnowmanPumpkin(EntitySnowman par1EntitySnowman, float par2) {
/* 30 */     super.renderEquippedItems(par1EntitySnowman, par2);
/* 31 */     ItemStack var3 = new ItemStack(Block.pumpkin, 1);
/*    */     
/* 33 */     if (var3 != null && (var3.getItem()).itemID < 256) {
/*    */       
/* 35 */       GL11.glPushMatrix();
/* 36 */       this.snowmanModel.head.postRender(0.0625F);
/*    */       
/* 38 */       if (RenderBlocks.renderItemIn3d(Block.blocksList[var3.itemID].getRenderType())) {
/*    */         
/* 40 */         float var4 = 0.625F;
/* 41 */         GL11.glTranslatef(0.0F, -0.34375F, 0.0F);
/* 42 */         GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 43 */         GL11.glScalef(var4, -var4, var4);
/*    */       } 
/*    */       
/* 46 */       this.renderManager.itemRenderer.renderItem(par1EntitySnowman, var3, 0);
/* 47 */       GL11.glPopMatrix();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getSnowManTextures(EntitySnowman par1EntitySnowman) {
/* 54 */     return this.textures[0];
/*    */   }
/*    */ 
/*    */   
/*    */   protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2) {
/* 59 */     renderSnowmanPumpkin((EntitySnowman)par1EntityLivingBase, par2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 67 */     return getSnowManTextures((EntitySnowman)par1Entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderSnowMan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */