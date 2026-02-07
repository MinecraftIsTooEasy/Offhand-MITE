/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ComponentStrongholdStraight
/*     */   extends ComponentStronghold
/*     */ {
/*     */   private boolean expandsX;
/*     */   private boolean expandsZ;
/*     */   
/*     */   public ComponentStrongholdStraight() {}
/*     */   
/*     */   public ComponentStrongholdStraight(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4) {
/*  15 */     super(par1);
/*  16 */     this.coordBaseMode = par4;
/*  17 */     this.field_143013_d = getRandomDoor(par2Random);
/*  18 */     this.boundingBox = par3StructureBoundingBox;
/*  19 */     this.expandsX = (par2Random.nextInt(2) == 0);
/*  20 */     this.expandsZ = (par2Random.nextInt(2) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
/*  25 */     super.func_143012_a(par1NBTTagCompound);
/*  26 */     par1NBTTagCompound.setBoolean("Left", this.expandsX);
/*  27 */     par1NBTTagCompound.setBoolean("Right", this.expandsZ);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
/*  32 */     super.func_143011_b(par1NBTTagCompound);
/*  33 */     this.expandsX = par1NBTTagCompound.getBoolean("Left");
/*  34 */     this.expandsZ = par1NBTTagCompound.getBoolean("Right");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {
/*  42 */     getNextComponentNormal((ComponentStrongholdStairs2)par1StructureComponent, par2List, par3Random, 1, 1);
/*     */     
/*  44 */     if (this.expandsX)
/*     */     {
/*  46 */       getNextComponentX((ComponentStrongholdStairs2)par1StructureComponent, par2List, par3Random, 1, 2);
/*     */     }
/*     */     
/*  49 */     if (this.expandsZ)
/*     */     {
/*  51 */       getNextComponentZ((ComponentStrongholdStairs2)par1StructureComponent, par2List, par3Random, 1, 2);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static ComponentStrongholdStraight findValidPlacement(List par0List, Random par1Random, int par2, int par3, int par4, int par5, int par6) {
/*  57 */     StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, -1, -1, 0, 5, 5, 7, par5);
/*  58 */     return (canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(par0List, var7) == null) ? new ComponentStrongholdStraight(par6, par1Random, var7, par5) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
/*  67 */     if (isLiquidInStructureBoundingBox(par1World, par3StructureBoundingBox))
/*     */     {
/*  69 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  73 */     fillWithRandomizedBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 4, 4, 6, true, par2Random, StructureStrongholdPieces.getStrongholdStones());
/*  74 */     placeDoor(par1World, par2Random, par3StructureBoundingBox, this.field_143013_d, 1, 1, 0);
/*  75 */     placeDoor(par1World, par2Random, par3StructureBoundingBox, EnumDoor.OPENING, 1, 1, 6);
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
/*  93 */     placeBlockWithChanceAndDefaultMetadata(par1World, par3StructureBoundingBox, par2Random, 0.1F, 1, 2, 1, Block.torchWood.blockID, false);
/*  94 */     placeBlockWithChanceAndDefaultMetadata(par1World, par3StructureBoundingBox, par2Random, 0.1F, 3, 2, 1, Block.torchWood.blockID, false);
/*  95 */     placeBlockWithChanceAndDefaultMetadata(par1World, par3StructureBoundingBox, par2Random, 0.1F, 1, 2, 5, Block.torchWood.blockID, false);
/*  96 */     placeBlockWithChanceAndDefaultMetadata(par1World, par3StructureBoundingBox, par2Random, 0.1F, 3, 2, 5, Block.torchWood.blockID, false);
/*     */     
/*  98 */     if (this.expandsX)
/*     */     {
/* 100 */       fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 2, 0, 3, 4, 0, 0, false);
/*     */     }
/*     */     
/* 103 */     if (this.expandsZ)
/*     */     {
/* 105 */       fillWithBlocks(par1World, par3StructureBoundingBox, 4, 1, 2, 4, 3, 4, 0, 0, false);
/*     */     }
/*     */     
/* 108 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentStrongholdStraight.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */