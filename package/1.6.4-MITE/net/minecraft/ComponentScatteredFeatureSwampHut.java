/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public class ComponentScatteredFeatureSwampHut
/*     */   extends ComponentScatteredFeature
/*     */ {
/*     */   private boolean hasWitch;
/*     */   protected boolean has_made_chest;
/*  11 */   protected static final WeightedRandomChestContent[] chest_contents = new WeightedRandomChestContent[] { new WeightedRandomChestContent(Item.glassBottle.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Block.mushroomRed.blockID, 0, 1, 4, 8), new WeightedRandomChestContent(Block.mushroomBrown.blockID, 0, 1, 3, 5), new WeightedRandomChestContent(Item.bowlEmpty.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.reed.itemID, 0, 1, 5, 8), new WeightedRandomChestContent(Item.chipFlint.itemID, 0, 1, 3, 5), new WeightedRandomChestContent(Item.knifeFlint.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.hatchetFlint.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.stick.itemID, 0, 1, 3, 5), new WeightedRandomChestContent(Item.rottenFlesh.itemID, 0, 1, 3, 5), new WeightedRandomChestContent(Item.silk.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Item.chickenRaw.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Item.feather.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Item.leather.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Block.cloth.blockID, 0, 1, 2, 3), new WeightedRandomChestContent(Item.bone.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Item.sugar.itemID, 0, 1, 3, 5), new WeightedRandomChestContent(Item.pumpkinSeeds.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Item.appleRed.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Item.carrot.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Item.potato.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Item.onion.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Block.plantYellow.blockID, 0, 1, 2, 3), new WeightedRandomChestContent(Block.plantRed.blockID, 2, 1, 2, 3), new WeightedRandomChestContent(Item.potion.itemID, 8227, 1, 1, 1), new WeightedRandomChestContent(Item.potion.itemID, 8261, 1, 1, 1), new WeightedRandomChestContent(Item.potion.itemID, 16388, 1, 1, 1), new WeightedRandomChestContent(Item.potion.itemID, 16424, 1, 1, 1), new WeightedRandomChestContent(Item.potion.itemID, 16426, 1, 1, 1), new WeightedRandomChestContent(Item.potion.itemID, 16460, 1, 1, 1) };
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
/*     */   public ComponentScatteredFeatureSwampHut() {}
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
/*     */   public ComponentScatteredFeatureSwampHut(Random par1Random, int par2, int par3) {
/*  49 */     super(par1Random, par2, 64, par3, 7, 5, 9);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
/*  54 */     super.func_143012_a(par1NBTTagCompound);
/*  55 */     par1NBTTagCompound.setBoolean("Witch", this.hasWitch);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
/*  60 */     super.func_143011_b(par1NBTTagCompound);
/*  61 */     this.hasWitch = par1NBTTagCompound.getBoolean("Witch");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
/*  70 */     if (!func_74935_a(par1World, par3StructureBoundingBox, 0))
/*     */     {
/*  72 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  76 */     fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 1, 1, 5, 1, 7, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
/*  77 */     fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 4, 2, 5, 4, 7, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
/*  78 */     fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 1, 0, 4, 1, 0, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
/*  79 */     fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 2, 2, 3, 3, 2, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
/*  80 */     fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 1, 2, 3, 1, 3, 6, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
/*  81 */     fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 5, 2, 3, 5, 3, 6, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
/*  82 */     fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 2, 7, 4, 3, 7, Block.planks.blockID, 1, Block.planks.blockID, 1, false);
/*  83 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 2, 1, 3, 2, Block.wood.blockID, Block.wood.blockID, false);
/*  84 */     fillWithBlocks(par1World, par3StructureBoundingBox, 5, 0, 2, 5, 3, 2, Block.wood.blockID, Block.wood.blockID, false);
/*  85 */     fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 7, 1, 3, 7, Block.wood.blockID, Block.wood.blockID, false);
/*  86 */     fillWithBlocks(par1World, par3StructureBoundingBox, 5, 0, 7, 5, 3, 7, Block.wood.blockID, Block.wood.blockID, false);
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
/* 160 */     placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 2, 3, 2, par3StructureBoundingBox);
/* 161 */     placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 3, 3, 7, par3StructureBoundingBox);
/* 162 */     placeBlockAtCurrentPosition(par1World, 0, 0, 1, 3, 4, par3StructureBoundingBox);
/* 163 */     placeBlockAtCurrentPosition(par1World, 0, 0, 5, 3, 4, par3StructureBoundingBox);
/* 164 */     placeBlockAtCurrentPosition(par1World, 0, 0, 5, 3, 5, par3StructureBoundingBox);
/* 165 */     placeBlockAtCurrentPosition(par1World, Block.flowerPot.blockID, 7, 1, 3, 5, par3StructureBoundingBox);
/*     */     
/* 167 */     placeBlockAtCurrentPosition(par1World, Block.cauldron.blockID, 0, 4, 2, 6, par3StructureBoundingBox);
/* 168 */     placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 1, 2, 1, par3StructureBoundingBox);
/* 169 */     placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 5, 2, 1, par3StructureBoundingBox);
/* 170 */     int var4 = getMetadataWithOffset(Block.stairsWoodOak.blockID, 3);
/* 171 */     int var5 = getMetadataWithOffset(Block.stairsWoodOak.blockID, 1);
/* 172 */     int var6 = getMetadataWithOffset(Block.stairsWoodOak.blockID, 0);
/* 173 */     int var7 = getMetadataWithOffset(Block.stairsWoodOak.blockID, 2);
/* 174 */     fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 0, 4, 1, 6, 4, 1, Block.stairsWoodSpruce.blockID, var4, Block.stairsWoodSpruce.blockID, var4, false);
/* 175 */     fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 0, 4, 2, 0, 4, 7, Block.stairsWoodSpruce.blockID, var6, Block.stairsWoodSpruce.blockID, var6, false);
/* 176 */     fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 4, 2, 6, 4, 7, Block.stairsWoodSpruce.blockID, var5, Block.stairsWoodSpruce.blockID, var5, false);
/* 177 */     fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 0, 4, 8, 6, 4, 8, Block.stairsWoodSpruce.blockID, var7, Block.stairsWoodSpruce.blockID, var7, false);
/*     */ 
/*     */ 
/*     */     
/* 181 */     if (!this.has_made_chest)
/*     */     {
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
/* 193 */       this.has_made_chest = generateStructureChestContents(par1World, par3StructureBoundingBox, par2Random, 2, 2, 6, Block.chest.blockID, chest_contents, 4 + par2Random.nextInt(5), (float[])null, EnumDirection.SOUTH);
/*     */     }
/*     */ 
/*     */     
/*     */     int var8;
/*     */     
/* 199 */     for (var8 = 2; var8 <= 7; var8 += 5) {
/*     */       
/* 201 */       for (int var9 = 1; var9 <= 5; var9 += 4)
/*     */       {
/* 203 */         fillCurrentPositionBlocksDownwards(par1World, Block.wood.blockID, 0, var9, -1, var8, par3StructureBoundingBox);
/*     */       }
/*     */     } 
/*     */     
/* 207 */     if (!this.hasWitch) {
/*     */       
/* 209 */       var8 = getXWithOffset(2, 5);
/* 210 */       int var9 = getYWithOffset(2);
/* 211 */       int var10 = getZWithOffset(2, 5);
/*     */       
/* 213 */       if (par3StructureBoundingBox.isVecInside(var8, var9, var10)) {
/*     */         
/* 215 */         this.hasWitch = true;
/* 216 */         EntityWitch var11 = new EntityWitch(par1World);
/* 217 */         var11.setLocationAndAngles(var8 + 0.5D, var9, var10 + 0.5D, 0.0F, 0.0F);
/* 218 */         var11.onSpawnWithEgg((EntityLivingData)null);
/* 219 */         par1World.spawnEntityInWorld(var11);
/*     */       } 
/*     */     } 
/*     */     
/* 223 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentScatteredFeatureSwampHut.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */