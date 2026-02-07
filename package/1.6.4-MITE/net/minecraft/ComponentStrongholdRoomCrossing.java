/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ComponentStrongholdRoomCrossing
/*     */   extends ComponentStronghold
/*     */ {
/*  11 */   private static final WeightedRandomChestContent[] strongholdRoomCrossingChestContents = new WeightedRandomChestContent[] { new WeightedRandomChestContent(Item.ingotIron.itemID, 0, 1, 5, 10), new WeightedRandomChestContent(Item.ingotGold.itemID, 0, 1, 3, 5), new WeightedRandomChestContent(Item.redstone.itemID, 0, 4, 9, 5), new WeightedRandomChestContent(Item.coal.itemID, 0, 3, 8, 10), new WeightedRandomChestContent(Item.bread.itemID, 0, 1, 3, 15), new WeightedRandomChestContent(Item.appleRed.itemID, 0, 1, 3, 15), new WeightedRandomChestContent(Item.pickaxeIron.itemID, 0, 1, 1, 1) };
/*     */   
/*     */   protected int roomType;
/*     */   
/*     */   public ComponentStrongholdRoomCrossing() {}
/*     */   
/*     */   public ComponentStrongholdRoomCrossing(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4) {
/*  18 */     super(par1);
/*  19 */     this.coordBaseMode = par4;
/*  20 */     this.field_143013_d = getRandomDoor(par2Random);
/*  21 */     this.boundingBox = par3StructureBoundingBox;
/*  22 */     this.roomType = par2Random.nextInt(5);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
/*  27 */     super.func_143012_a(par1NBTTagCompound);
/*  28 */     par1NBTTagCompound.setInteger("Type", this.roomType);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
/*  33 */     super.func_143011_b(par1NBTTagCompound);
/*  34 */     this.roomType = par1NBTTagCompound.getInteger("Type");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {
/*  42 */     getNextComponentNormal((ComponentStrongholdStairs2)par1StructureComponent, par2List, par3Random, 4, 1);
/*  43 */     getNextComponentX((ComponentStrongholdStairs2)par1StructureComponent, par2List, par3Random, 1, 4);
/*  44 */     getNextComponentZ((ComponentStrongholdStairs2)par1StructureComponent, par2List, par3Random, 1, 4);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ComponentStrongholdRoomCrossing findValidPlacement(List par0List, Random par1Random, int par2, int par3, int par4, int par5, int par6) {
/*  49 */     StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, -4, -1, 0, 11, 7, 11, par5);
/*  50 */     return (canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(par0List, var7) == null) ? new ComponentStrongholdRoomCrossing(par6, par1Random, var7, par5) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
/*     */     int var4;
/*  59 */     if (isLiquidInStructureBoundingBox(par1World, par3StructureBoundingBox))
/*     */     {
/*  61 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  65 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 10, 6, 10, true, par2Random, StructureStrongholdPieces.getStrongholdStones());
/*  66 */     placeDoor(par1World, par2Random, par3StructureBoundingBox, this.field_143013_d, 4, 1, 0);
/*  67 */     fillWithBlocks(par1World, par3StructureBoundingBox, 4, 1, 10, 6, 3, 10, 0, 0, false);
/*  68 */     fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 4, 0, 3, 6, 0, 0, false);
/*  69 */     fillWithBlocks(par1World, par3StructureBoundingBox, 10, 1, 4, 10, 3, 6, 0, 0, false);
/*     */ 
/*     */     
/*  72 */     switch (this.roomType) {
/*     */       
/*     */       case 0:
/*  75 */         placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 5, 1, 5, par3StructureBoundingBox);
/*  76 */         placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 5, 2, 5, par3StructureBoundingBox);
/*  77 */         placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 5, 3, 5, par3StructureBoundingBox);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  83 */         placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 4, 3, 5, 2, par3StructureBoundingBox);
/*  84 */         placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 6, 3, 5, 1, par3StructureBoundingBox);
/*  85 */         placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 5, 3, 4, 3, par3StructureBoundingBox);
/*  86 */         placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 5, 3, 6, 4, par3StructureBoundingBox);
/*     */         
/*  88 */         placeBlockAtCurrentPosition(par1World, Block.stoneSingleSlab.blockID, 0, 4, 1, 4, par3StructureBoundingBox);
/*  89 */         placeBlockAtCurrentPosition(par1World, Block.stoneSingleSlab.blockID, 0, 4, 1, 5, par3StructureBoundingBox);
/*  90 */         placeBlockAtCurrentPosition(par1World, Block.stoneSingleSlab.blockID, 0, 4, 1, 6, par3StructureBoundingBox);
/*  91 */         placeBlockAtCurrentPosition(par1World, Block.stoneSingleSlab.blockID, 0, 6, 1, 4, par3StructureBoundingBox);
/*  92 */         placeBlockAtCurrentPosition(par1World, Block.stoneSingleSlab.blockID, 0, 6, 1, 5, par3StructureBoundingBox);
/*  93 */         placeBlockAtCurrentPosition(par1World, Block.stoneSingleSlab.blockID, 0, 6, 1, 6, par3StructureBoundingBox);
/*  94 */         placeBlockAtCurrentPosition(par1World, Block.stoneSingleSlab.blockID, 0, 5, 1, 4, par3StructureBoundingBox);
/*  95 */         placeBlockAtCurrentPosition(par1World, Block.stoneSingleSlab.blockID, 0, 5, 1, 6, par3StructureBoundingBox);
/*     */         break;
/*     */       
/*     */       case 1:
/*  99 */         for (var4 = 0; var4 < 5; var4++) {
/*     */           
/* 101 */           placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 3, 1, 3 + var4, par3StructureBoundingBox);
/* 102 */           placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 7, 1, 3 + var4, par3StructureBoundingBox);
/* 103 */           placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 3 + var4, 1, 3, par3StructureBoundingBox);
/* 104 */           placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 3 + var4, 1, 7, par3StructureBoundingBox);
/*     */         } 
/*     */         
/* 107 */         placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 5, 1, 5, par3StructureBoundingBox);
/* 108 */         placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 5, 2, 5, par3StructureBoundingBox);
/* 109 */         placeBlockAtCurrentPosition(par1World, Block.stoneBrick.blockID, 0, 5, 3, 5, par3StructureBoundingBox);
/* 110 */         placeBlockAtCurrentPosition(par1World, Block.waterMoving.blockID, 0, 5, 4, 5, par3StructureBoundingBox);
/*     */         break;
/*     */       
/*     */       case 2:
/* 114 */         for (var4 = 1; var4 <= 9; var4++) {
/*     */           
/* 116 */           placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 1, 3, var4, par3StructureBoundingBox);
/* 117 */           placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 9, 3, var4, par3StructureBoundingBox);
/*     */         } 
/*     */         
/* 120 */         for (var4 = 1; var4 <= 9; var4++) {
/*     */           
/* 122 */           placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, var4, 3, 1, par3StructureBoundingBox);
/* 123 */           placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, var4, 3, 9, par3StructureBoundingBox);
/*     */         } 
/*     */         
/* 126 */         placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 5, 1, 4, par3StructureBoundingBox);
/* 127 */         placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 5, 1, 6, par3StructureBoundingBox);
/* 128 */         placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 5, 3, 4, par3StructureBoundingBox);
/* 129 */         placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 5, 3, 6, par3StructureBoundingBox);
/* 130 */         placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 4, 1, 5, par3StructureBoundingBox);
/* 131 */         placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 6, 1, 5, par3StructureBoundingBox);
/* 132 */         placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 4, 3, 5, par3StructureBoundingBox);
/* 133 */         placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 6, 3, 5, par3StructureBoundingBox);
/*     */         
/* 135 */         for (var4 = 1; var4 <= 3; var4++) {
/*     */           
/* 137 */           placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 4, var4, 4, par3StructureBoundingBox);
/* 138 */           placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 6, var4, 4, par3StructureBoundingBox);
/* 139 */           placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 4, var4, 6, par3StructureBoundingBox);
/* 140 */           placeBlockAtCurrentPosition(par1World, Block.cobblestone.blockID, 0, 6, var4, 6, par3StructureBoundingBox);
/*     */         } 
/*     */ 
/*     */         
/* 144 */         placeBlockRelativeWithDefaultMetadata(par1World, Block.torchWood, 5, 3, 5, par3StructureBoundingBox);
/*     */         
/* 146 */         for (var4 = 2; var4 <= 8; var4++) {
/*     */           
/* 148 */           placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 2, 3, var4, par3StructureBoundingBox);
/* 149 */           placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 3, 3, var4, par3StructureBoundingBox);
/*     */           
/* 151 */           if (var4 <= 3 || var4 >= 7) {
/*     */             
/* 153 */             placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 4, 3, var4, par3StructureBoundingBox);
/* 154 */             placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 5, 3, var4, par3StructureBoundingBox);
/* 155 */             placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 6, 3, var4, par3StructureBoundingBox);
/*     */           } 
/*     */           
/* 158 */           placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 7, 3, var4, par3StructureBoundingBox);
/* 159 */           placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 8, 3, var4, par3StructureBoundingBox);
/*     */         } 
/*     */         
/* 162 */         placeBlockAtCurrentPosition(par1World, Block.ladder.blockID, getMetadataWithOffset(Block.ladder.blockID, 4), 9, 1, 3, par3StructureBoundingBox);
/* 163 */         placeBlockAtCurrentPosition(par1World, Block.ladder.blockID, getMetadataWithOffset(Block.ladder.blockID, 4), 9, 2, 3, par3StructureBoundingBox);
/* 164 */         placeBlockAtCurrentPosition(par1World, Block.ladder.blockID, getMetadataWithOffset(Block.ladder.blockID, 4), 9, 3, 3, par3StructureBoundingBox);
/*     */         
/* 166 */         generateStructureChestContents(par1World, par3StructureBoundingBox, par2Random, 3, 4, 8, Block.chest.blockID, WeightedRandomChestContent.func_92080_a(strongholdRoomCrossingChestContents, new WeightedRandomChestContent[] { Item.enchantedBook.func_92114_b(par2Random) }), 1 + par2Random.nextInt(4), (float[])null, EnumDirection.SOUTH);
/*     */         break;
/*     */     } 
/* 169 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentStrongholdRoomCrossing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */