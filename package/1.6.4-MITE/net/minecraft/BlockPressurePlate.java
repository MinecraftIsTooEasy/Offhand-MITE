/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class BlockPressurePlate
/*    */   extends BlockBasePressurePlate
/*    */ {
/*    */   private EnumMobType triggerMobType;
/*    */   
/*    */   protected BlockPressurePlate(int par1, String par2Str, Material par3Material, EnumMobType par4EnumMobType) {
/* 13 */     super(par1, par2Str, par3Material);
/* 14 */     this.triggerMobType = par4EnumMobType;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMetadataNotes() {
/* 19 */     return "Bit 1 set if triggered";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValidMetadata(int metadata) {
/* 24 */     return (metadata >= 0 && metadata < 2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getMetaFromWeight(int par1) {
/* 32 */     return (par1 > 0) ? 1 : 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getPowerSupply(int par1) {
/* 40 */     return (par1 == 1) ? 15 : 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getPlateState(World par1World, int par2, int par3, int par4) {
/* 49 */     List var5 = null;
/*    */     
/* 51 */     if (this.triggerMobType == EnumMobType.everything)
/*    */     {
/* 53 */       var5 = par1World.getEntitiesWithinAABBExcludingEntity((Entity)null, getSensitiveAABB(par2, par3, par4));
/*    */     }
/*    */     
/* 56 */     if (this.triggerMobType == EnumMobType.mobs)
/*    */     {
/* 58 */       var5 = par1World.getEntitiesWithinAABB(EntityLivingBase.class, getSensitiveAABB(par2, par3, par4));
/*    */     }
/*    */     
/* 61 */     if (this.triggerMobType == EnumMobType.players)
/*    */     {
/* 63 */       var5 = par1World.getEntitiesWithinAABB(EntityPlayer.class, getSensitiveAABB(par2, par3, par4));
/*    */     }
/*    */     
/* 66 */     if (var5 != null && !var5.isEmpty()) {
/*    */       
/* 68 */       Iterator<Entity> var6 = var5.iterator();
/*    */       
/* 70 */       while (var6.hasNext()) {
/*    */         
/* 72 */         Entity var7 = var6.next();
/*    */         
/* 74 */         if (!var7.doesEntityNotTriggerPressurePlate())
/*    */         {
/* 76 */           return 15;
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 81 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 86 */     return this.blockMaterial.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockPressurePlate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */