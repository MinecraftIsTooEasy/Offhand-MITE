/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderEntity
/*    */   extends Render
/*    */ {
/*    */   public void doRender(Entity entity, double d, double e, double f, float g, float h) {
/* 12 */     GL11.glPushMatrix();
/* 13 */     renderOffsetAABB(entity.boundingBox, d - entity.lastTickPosX, e - entity.lastTickPosY, f - entity.lastTickPosZ);
/* 14 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity entity) {
/* 19 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */