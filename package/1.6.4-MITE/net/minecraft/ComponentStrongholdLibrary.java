/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ComponentStrongholdLibrary
/*     */   extends ComponentStronghold
/*     */ {
/*   9 */   private static final WeightedRandomChestContent[] strongholdLibraryChestContents = new WeightedRandomChestContent[] { new WeightedRandomChestContent(Item.book.itemID, 0, 1, 3, 20), new WeightedRandomChestContent(Item.paper.itemID, 0, 2, 7, 20), new WeightedRandomChestContent(Item.emptyMap.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.compass.itemID, 0, 1, 1, 1) };
/*     */   
/*     */   private boolean isLargeRoom;
/*     */   
/*     */   public ComponentStrongholdLibrary() {}
/*     */   
/*     */   public ComponentStrongholdLibrary(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4) {
/*  16 */     super(par1);
/*  17 */     this.coordBaseMode = par4;
/*  18 */     this.field_143013_d = getRandomDoor(par2Random);
/*  19 */     this.boundingBox = par3StructureBoundingBox;
/*  20 */     this.isLargeRoom = (par3StructureBoundingBox.getYSize() > 6);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
/*  25 */     super.func_143012_a(par1NBTTagCompound);
/*  26 */     par1NBTTagCompound.setBoolean("Tall", this.isLargeRoom);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
/*  31 */     super.func_143011_b(par1NBTTagCompound);
/*  32 */     this.isLargeRoom = par1NBTTagCompound.getBoolean("Tall");
/*     */   }
/*     */ 
/*     */   
/*     */   public static ComponentStrongholdLibrary findValidPlacement(List par0List, Random par1Random, int par2, int par3, int par4, int par5, int par6) {
/*  37 */     StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, -4, -1, 0, 14, 11, 15, par5);
/*     */     
/*  39 */     if (!canStrongholdGoDeeper(var7) || StructureComponent.findIntersecting(par0List, var7) != null) {
/*     */       
/*  41 */       var7 = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, -4, -1, 0, 14, 6, 15, par5);
/*     */       
/*  43 */       if (!canStrongholdGoDeeper(var7) || StructureComponent.findIntersecting(par0List, var7) != null)
/*     */       {
/*  45 */         return null;
/*     */       }
/*     */     } 
/*     */     
/*  49 */     return new ComponentStrongholdLibrary(par6, par1Random, var7, par5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
/*  58 */     if (isLiquidInStructureBoundingBox(par1World, par3StructureBoundingBox))
/*     */     {
/*  60 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  64 */     byte var4 = 11;
/*     */     
/*  66 */     if (!this.isLargeRoom)
/*     */     {
/*  68 */       var4 = 6;
/*     */     }
/*     */     
/*  71 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 13, var4 - 1, 14, true, par2Random, StructureStrongholdPieces.getStrongholdStones());
/*  72 */     placeDoor(par1World, par2Random, par3StructureBoundingBox, this.field_143013_d, 4, 1, 0);
/*  73 */     randomlyFillWithBlocks(par1World, par3StructureBoundingBox, par2Random, 0.07F, 2, 1, 1, 11, 4, 13, Block.web.blockID, Block.web.blockID, false);
/*  74 */     boolean var5 = true;
/*  75 */     boolean var6 = true;
/*     */     
/*     */     int var7;
/*  78 */     for (var7 = 1; var7 <= 13; var7++) {
/*     */       
/*  80 */       if ((var7 - 1) % 4 == 0) {
/*     */         
/*  82 */         fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, var7, 1, 4, var7, Block.planks.blockID, Block.planks.blockID, false);
/*  83 */         fillWithBlocks(par1World, par3StructureBoundingBox, 12, 1, var7, 12, 4, var7, Block.planks.blockID, Block.planks.blockID, false);
/*     */ 
/*     */ 
/*     */         
/*  87 */         placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 2, 3, var7, 1, par3StructureBoundingBox);
/*  88 */         placeBlockRelativeWithAdjustedMetadata(par1World, Block.torchWood, 11, 3, var7, 2, par3StructureBoundingBox);
/*     */         
/*  90 */         if (this.isLargeRoom)
/*     */         {
/*  92 */           fillWithBlocks(par1World, par3StructureBoundingBox, 1, 6, var7, 1, 9, var7, Block.planks.blockID, Block.planks.blockID, false);
/*  93 */           fillWithBlocks(par1World, par3StructureBoundingBox, 12, 6, var7, 12, 9, var7, Block.planks.blockID, Block.planks.blockID, false);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/*  98 */         fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, var7, 1, 4, var7, Block.bookShelf.blockID, Block.bookShelf.blockID, false);
/*  99 */         fillWithBlocks(par1World, par3StructureBoundingBox, 12, 1, var7, 12, 4, var7, Block.bookShelf.blockID, Block.bookShelf.blockID, false);
/*     */         
/* 101 */         if (this.isLargeRoom) {
/*     */           
/* 103 */           fillWithBlocks(par1World, par3StructureBoundingBox, 1, 6, var7, 1, 9, var7, Block.bookShelf.blockID, Block.bookShelf.blockID, false);
/* 104 */           fillWithBlocks(par1World, par3StructureBoundingBox, 12, 6, var7, 12, 9, var7, Block.bookShelf.blockID, Block.bookShelf.blockID, false);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 109 */     for (var7 = 3; var7 < 12; var7 += 2) {
/*     */       
/* 111 */       fillWithBlocks(par1World, par3StructureBoundingBox, 3, 1, var7, 4, 3, var7, Block.bookShelf.blockID, Block.bookShelf.blockID, false);
/* 112 */       fillWithBlocks(par1World, par3StructureBoundingBox, 6, 1, var7, 7, 3, var7, Block.bookShelf.blockID, Block.bookShelf.blockID, false);
/* 113 */       fillWithBlocks(par1World, par3StructureBoundingBox, 9, 1, var7, 10, 3, var7, Block.bookShelf.blockID, Block.bookShelf.blockID, false);
/*     */     } 
/*     */     
/* 116 */     if (this.isLargeRoom) {
/*     */       
/* 118 */       fillWithBlocks(par1World, par3StructureBoundingBox, 1, 5, 1, 3, 5, 13, Block.planks.blockID, Block.planks.blockID, false);
/* 119 */       fillWithBlocks(par1World, par3StructureBoundingBox, 10, 5, 1, 12, 5, 13, Block.planks.blockID, Block.planks.blockID, false);
/* 120 */       fillWithBlocks(par1World, par3StructureBoundingBox, 4, 5, 1, 9, 5, 2, Block.planks.blockID, Block.planks.blockID, false);
/* 121 */       fillWithBlocks(par1World, par3StructureBoundingBox, 4, 5, 12, 9, 5, 13, Block.planks.blockID, Block.planks.blockID, false);
/* 122 */       placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 9, 5, 11, par3StructureBoundingBox);
/* 123 */       placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 8, 5, 11, par3StructureBoundingBox);
/* 124 */       placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, 9, 5, 10, par3StructureBoundingBox);
/* 125 */       fillWithBlocks(par1World, par3StructureBoundingBox, 3, 6, 2, 3, 6, 12, Block.fence.blockID, Block.fence.blockID, false);
/* 126 */       fillWithBlocks(par1World, par3StructureBoundingBox, 10, 6, 2, 10, 6, 10, Block.fence.blockID, Block.fence.blockID, false);
/* 127 */       fillWithBlocks(par1World, par3StructureBoundingBox, 4, 6, 2, 9, 6, 2, Block.fence.blockID, Block.fence.blockID, false);
/* 128 */       fillWithBlocks(par1World, par3StructureBoundingBox, 4, 6, 12, 8, 6, 12, Block.fence.blockID, Block.fence.blockID, false);
/* 129 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 9, 6, 11, par3StructureBoundingBox);
/* 130 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 8, 6, 11, par3StructureBoundingBox);
/* 131 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, 9, 6, 10, par3StructureBoundingBox);
/* 132 */       var7 = getMetadataWithOffset(Block.ladder.blockID, 3);
/* 133 */       placeBlockAtCurrentPosition(par1World, Block.ladder.blockID, var7, 10, 1, 13, par3StructureBoundingBox);
/* 134 */       placeBlockAtCurrentPosition(par1World, Block.ladder.blockID, var7, 10, 2, 13, par3StructureBoundingBox);
/* 135 */       placeBlockAtCurrentPosition(par1World, Block.ladder.blockID, var7, 10, 3, 13, par3StructureBoundingBox);
/* 136 */       placeBlockAtCurrentPosition(par1World, Block.ladder.blockID, var7, 10, 4, 13, par3StructureBoundingBox);
/* 137 */       placeBlockAtCurrentPosition(par1World, Block.ladder.blockID, var7, 10, 5, 13, par3StructureBoundingBox);
/* 138 */       placeBlockAtCurrentPosition(par1World, Block.ladder.blockID, var7, 10, 6, 13, par3StructureBoundingBox);
/* 139 */       placeBlockAtCurrentPosition(par1World, Block.ladder.blockID, var7, 10, 7, 13, par3StructureBoundingBox);
/* 140 */       byte var8 = 7;
/* 141 */       byte var9 = 7;
/* 142 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, var8 - 1, 9, var9, par3StructureBoundingBox);
/* 143 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, var8, 9, var9, par3StructureBoundingBox);
/* 144 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, var8 - 1, 8, var9, par3StructureBoundingBox);
/* 145 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, var8, 8, var9, par3StructureBoundingBox);
/* 146 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, var8 - 1, 7, var9, par3StructureBoundingBox);
/* 147 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, var8, 7, var9, par3StructureBoundingBox);
/* 148 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, var8 - 2, 7, var9, par3StructureBoundingBox);
/* 149 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, var8 + 1, 7, var9, par3StructureBoundingBox);
/* 150 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, var8 - 1, 7, var9 - 1, par3StructureBoundingBox);
/* 151 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, var8 - 1, 7, var9 + 1, par3StructureBoundingBox);
/* 152 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, var8, 7, var9 - 1, par3StructureBoundingBox);
/* 153 */       placeBlockAtCurrentPosition(par1World, Block.fence.blockID, 0, var8, 7, var9 + 1, par3StructureBoundingBox);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 161 */       placeBlockAtCurrentPosition(par1World, Block.torchWood.blockID, 5, var8 - 2, 8, var9, par3StructureBoundingBox);
/* 162 */       placeBlockAtCurrentPosition(par1World, Block.torchWood.blockID, 5, var8 + 1, 8, var9, par3StructureBoundingBox);
/* 163 */       placeBlockAtCurrentPosition(par1World, Block.torchWood.blockID, 5, var8 - 1, 8, var9 - 1, par3StructureBoundingBox);
/* 164 */       placeBlockAtCurrentPosition(par1World, Block.torchWood.blockID, 5, var8 - 1, 8, var9 + 1, par3StructureBoundingBox);
/* 165 */       placeBlockAtCurrentPosition(par1World, Block.torchWood.blockID, 5, var8, 8, var9 - 1, par3StructureBoundingBox);
/* 166 */       placeBlockAtCurrentPosition(par1World, Block.torchWood.blockID, 5, var8, 8, var9 + 1, par3StructureBoundingBox);
/*     */     } 
/*     */     
/* 169 */     float[] chances_of_artifact = new float[1];
/* 170 */     chances_of_artifact[0] = 0.5F;
/*     */ 
/*     */     
/* 173 */     generateStructureChestContents(par1World, par3StructureBoundingBox, par2Random, 3, 3, 5, Block.chest.blockID, WeightedRandomChestContent.func_92080_a(strongholdLibraryChestContents, new WeightedRandomChestContent[] { Item.enchantedBook.func_92112_a(par2Random, 1, 5, 2) }), 1 + par2Random.nextInt(4), chances_of_artifact, EnumDirection.NORTH);
/*     */     
/* 175 */     if (this.isLargeRoom) {
/*     */       
/* 177 */       placeBlockAtCurrentPosition(par1World, 0, 0, 12, 9, 1, par3StructureBoundingBox);
/*     */       
/* 179 */       generateStructureChestContents(par1World, par3StructureBoundingBox, par2Random, 12, 8, 1, Block.chest.blockID, WeightedRandomChestContent.func_92080_a(strongholdLibraryChestContents, new WeightedRandomChestContent[] { Item.enchantedBook.func_92112_a(par2Random, 1, 5, 2) }), 1 + par2Random.nextInt(4), chances_of_artifact, EnumDirection.WEST);
/*     */     } 
/*     */     
/* 182 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentStrongholdLibrary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */