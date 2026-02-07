/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ public class RenderMooshroom
/*    */   extends RenderLiving
/*    */ {
/*    */   public static final int body_texture = 0;
/*    */   
/*    */   public RenderMooshroom(ModelBase par1ModelBase, float par2) {
/* 12 */     super(par1ModelBase, par2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setTextures() {
/* 17 */     setTexture(0, "textures/entity/cow/mooshroom");
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderLivingMooshroom(EntityMooshroom par1EntityMooshroom, double par2, double par4, double par6, float par8, float par9) {
/* 22 */     super.doRenderLiving(par1EntityMooshroom, par2, par4, par6, par8, par9);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getMooshroomTextures(EntityMooshroom par1EntityMooshroom) {
/* 28 */     return this.textures[0];
/*    */   }
/*    */ 
/*    */   
/*    */   protected void renderMooshroomEquippedItems(EntityMooshroom par1EntityMooshroom, float par2) {
/* 33 */     super.renderEquippedItems(par1EntityMooshroom, par2);
/*    */     
/* 35 */     if (!par1EntityMooshroom.isChild()) {
/*    */       
/* 37 */       bindTexture(TextureMap.locationBlocksTexture);
/* 38 */       GL11.glEnable(2884);
/* 39 */       GL11.glPushMatrix();
/* 40 */       GL11.glScalef(1.0F, -1.0F, 1.0F);
/* 41 */       GL11.glTranslatef(0.2F, 0.4F, 0.5F);
/* 42 */       GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);
/* 43 */       this.renderBlocks.renderBlockAsItem(Block.mushroomRed, 0, 1.0F);
/* 44 */       GL11.glTranslatef(0.1F, 0.0F, -0.6F);
/* 45 */       GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);
/* 46 */       this.renderBlocks.renderBlockAsItem(Block.mushroomRed, 0, 1.0F);
/* 47 */       GL11.glPopMatrix();
/* 48 */       GL11.glPushMatrix();
/* 49 */       ((ModelQuadruped)this.mainModel).head.postRender(0.0625F);
/* 50 */       GL11.glScalef(1.0F, -1.0F, 1.0F);
/* 51 */       GL11.glTranslatef(0.0F, 0.75F, -0.2F);
/* 52 */       GL11.glRotatef(12.0F, 0.0F, 1.0F, 0.0F);
/* 53 */       this.renderBlocks.renderBlockAsItem(Block.mushroomRed, 0, 1.0F);
/* 54 */       GL11.glPopMatrix();
/* 55 */       GL11.glDisable(2884);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
/* 61 */     renderLivingMooshroom((EntityMooshroom)par1EntityLiving, par2, par4, par6, par8, par9);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2) {
/* 66 */     renderMooshroomEquippedItems((EntityMooshroom)par1EntityLivingBase, par2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
/* 71 */     renderLivingMooshroom((EntityMooshroom)par1EntityLivingBase, par2, par4, par6, par8, par9);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 79 */     return getMooshroomTextures((EntityMooshroom)par1Entity);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
/* 90 */     renderLivingMooshroom((EntityMooshroom)par1Entity, par2, par4, par6, par8, par9);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderMooshroom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */