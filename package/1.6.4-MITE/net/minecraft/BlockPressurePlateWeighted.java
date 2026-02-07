/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ public class BlockPressurePlateWeighted
/*    */   extends BlockBasePressurePlate
/*    */ {
/*    */   private final int maxItemsWeighted;
/*    */   
/*    */   protected BlockPressurePlateWeighted(int par1, String par2Str, Material par3Material, int par4) {
/* 12 */     super(par1, par2Str, par3Material);
/* 13 */     this.maxItemsWeighted = par4;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMetadataNotes() {
/* 18 */     return "All bits used for output signal strength";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValidMetadata(int metadata) {
/* 23 */     return (metadata >= 0 && metadata < 16);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getPlateState(World par1World, int par2, int par3, int par4) {
/* 32 */     int var5 = 0;
/* 33 */     Iterator<EntityItem> var6 = par1World.getEntitiesWithinAABB(EntityItem.class, getSensitiveAABB(par2, par3, par4)).iterator();
/*    */     
/* 35 */     while (var6.hasNext()) {
/*    */       
/* 37 */       EntityItem var7 = var6.next();
/* 38 */       var5 += (var7.getEntityItem()).stackSize;
/*    */       
/* 40 */       if (var5 >= this.maxItemsWeighted) {
/*    */         break;
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 46 */     if (var5 <= 0)
/*    */     {
/* 48 */       return 0;
/*    */     }
/*    */ 
/*    */     
/* 52 */     float var8 = Math.min(this.maxItemsWeighted, var5) / this.maxItemsWeighted;
/* 53 */     return MathHelper.ceiling_float_int(var8 * 15.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getPowerSupply(int par1) {
/* 62 */     return par1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getMetaFromWeight(int par1) {
/* 70 */     return par1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int tickRate(World par1World) {
/* 78 */     return 10;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockPressurePlateWeighted.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */