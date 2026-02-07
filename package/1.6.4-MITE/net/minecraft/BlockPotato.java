/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockPotato
/*     */   extends BlockCrops
/*     */ {
/*     */   public BlockPotato(int block_id) {
/*  69 */     super(block_id, 4);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getSeedItem() {
/*  74 */     return Item.potato.itemID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getCropItem() {
/*  84 */     return Item.potato.itemID;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getDeadCropBlockId() {
/*  89 */     return Block.potatoDead.blockID;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getMatureYield() {
/*  94 */     return (Math.random() < 0.25D) ? 3 : 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 108 */     int metadata = info.getMetadata();
/*     */     
/* 110 */     if (isBlighted(metadata) || (getGrowth(metadata) > 0 && !isMature(metadata))) {
/*     */       
/* 112 */       if (info.wasSnowedUpon()) {
/* 113 */         playCropPopSound(info);
/*     */       }
/* 115 */       return dropBlockAsEntityItem(info, Item.poisonousPotato);
/*     */     } 
/*     */ 
/*     */     
/* 119 */     return super.dropBlockAsEntityItem(info);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float chanceOfBlightPerRandomTick() {
/* 125 */     return super.chanceOfBlightPerRandomTick() * 2.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getGrowthStage(int metadata) {
/* 130 */     int growth = getGrowth(metadata);
/*     */     
/* 132 */     if (growth == 6) {
/* 133 */       growth = 5;
/*     */     }
/* 135 */     return growth / 2;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockPotato.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */