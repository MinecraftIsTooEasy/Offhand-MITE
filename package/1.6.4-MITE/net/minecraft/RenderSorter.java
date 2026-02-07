/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class RenderSorter
/*    */   implements Comparator
/*    */ {
/*    */   private EntityLivingBase baseEntity;
/*    */   
/*    */   public RenderSorter(EntityLivingBase par1EntityLivingBase) {
/* 13 */     this.baseEntity = par1EntityLivingBase;
/*    */   }
/*    */ 
/*    */   
/*    */   public int doCompare(WorldRenderer par1WorldRenderer, WorldRenderer par2WorldRenderer) {
/* 18 */     if (par1WorldRenderer.isInFrustum && !par2WorldRenderer.isInFrustum)
/*    */     {
/* 20 */       return 1;
/*    */     }
/* 22 */     if (par2WorldRenderer.isInFrustum && !par1WorldRenderer.isInFrustum)
/*    */     {
/* 24 */       return -1;
/*    */     }
/*    */ 
/*    */     
/* 28 */     double var3 = par1WorldRenderer.distanceToEntitySquared(this.baseEntity);
/* 29 */     double var5 = par2WorldRenderer.distanceToEntitySquared(this.baseEntity);
/* 30 */     return (var3 < var5) ? 1 : ((var3 > var5) ? -1 : ((par1WorldRenderer.chunkIndex < par2WorldRenderer.chunkIndex) ? 1 : -1));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int compare(Object par1Obj, Object par2Obj) {
/* 36 */     return doCompare((WorldRenderer)par1Obj, (WorldRenderer)par2Obj);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderSorter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */