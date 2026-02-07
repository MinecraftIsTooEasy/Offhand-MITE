/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ abstract class ComponentNetherBridgePiece
/*     */   extends StructureComponent
/*     */ {
/*  10 */   protected static final WeightedRandomChestContent[] field_111019_a = new WeightedRandomChestContent[] { new WeightedRandomChestContent(Item.shardDiamond.itemID, 0, 1, 3, 5), new WeightedRandomChestContent(Item.ingotGold.itemID, 0, 1, 3, 15), new WeightedRandomChestContent(Item.swordGold.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.plateGold.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.flintAndSteel.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.netherStalkSeeds.itemID, 0, 3, 7, 5), new WeightedRandomChestContent(Item.saddle.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Item.horseArmorGold.itemID, 0, 1, 1, 8), new WeightedRandomChestContent(Item.horseArmorCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.horseArmorIron.itemID, 0, 1, 1, 3) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentNetherBridgePiece() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ComponentNetherBridgePiece(int par1) {
/*  28 */     super(par1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {}
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {}
/*     */   
/*     */   private int getTotalWeight(List par1List) {
/*  37 */     boolean var2 = false;
/*  38 */     int var3 = 0;
/*     */ 
/*     */     
/*  41 */     for (Iterator<StructureNetherBridgePieceWeight> var4 = par1List.iterator(); var4.hasNext(); var3 += var5.field_78826_b) {
/*     */       
/*  43 */       StructureNetherBridgePieceWeight var5 = var4.next();
/*     */       
/*  45 */       if (var5.field_78824_d > 0 && var5.field_78827_c < var5.field_78824_d)
/*     */       {
/*  47 */         var2 = true;
/*     */       }
/*     */     } 
/*     */     
/*  51 */     return var2 ? var3 : -1;
/*     */   }
/*     */ 
/*     */   
/*     */   private ComponentNetherBridgePiece getNextComponent(ComponentNetherBridgeStartPiece par1ComponentNetherBridgeStartPiece, List par2List, List par3List, Random par4Random, int par5, int par6, int par7, int par8, int par9) {
/*  56 */     int var10 = getTotalWeight(par2List);
/*  57 */     boolean var11 = (var10 > 0 && par9 <= 30);
/*  58 */     int var12 = 0;
/*     */     
/*  60 */     while (var12 < 5 && var11) {
/*     */       
/*  62 */       var12++;
/*  63 */       int var13 = par4Random.nextInt(var10);
/*  64 */       Iterator<StructureNetherBridgePieceWeight> var14 = par2List.iterator();
/*     */       
/*  66 */       while (var14.hasNext()) {
/*     */         
/*  68 */         StructureNetherBridgePieceWeight var15 = var14.next();
/*  69 */         var13 -= var15.field_78826_b;
/*     */         
/*  71 */         if (var13 < 0) {
/*     */           
/*  73 */           if (!var15.func_78822_a(par9) || (var15 == par1ComponentNetherBridgeStartPiece.theNetherBridgePieceWeight && !var15.field_78825_e)) {
/*     */             break;
/*     */           }
/*     */ 
/*     */           
/*  78 */           ComponentNetherBridgePiece var16 = StructureNetherBridgePieces.createNextComponent(var15, par3List, par4Random, par5, par6, par7, par8, par9);
/*     */           
/*  80 */           if (var16 != null) {
/*     */             
/*  82 */             var15.field_78827_c++;
/*  83 */             par1ComponentNetherBridgeStartPiece.theNetherBridgePieceWeight = var15;
/*     */             
/*  85 */             if (!var15.func_78823_a())
/*     */             {
/*  87 */               par2List.remove(var15);
/*     */             }
/*     */             
/*  90 */             return var16;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  96 */     return ComponentNetherBridgeEnd.func_74971_a(par3List, par4Random, par5, par6, par7, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private StructureComponent getNextComponent(ComponentNetherBridgeStartPiece par1ComponentNetherBridgeStartPiece, List<ComponentNetherBridgePiece> par2List, Random par3Random, int par4, int par5, int par6, int par7, int par8, boolean par9) {
/* 104 */     if (Math.abs(par4 - (par1ComponentNetherBridgeStartPiece.getBoundingBox()).minX) <= 112 && Math.abs(par6 - (par1ComponentNetherBridgeStartPiece.getBoundingBox()).minZ) <= 112) {
/*     */       
/* 106 */       List var10 = par1ComponentNetherBridgeStartPiece.primaryWeights;
/*     */       
/* 108 */       if (par9)
/*     */       {
/* 110 */         var10 = par1ComponentNetherBridgeStartPiece.secondaryWeights;
/*     */       }
/*     */       
/* 113 */       ComponentNetherBridgePiece var11 = getNextComponent(par1ComponentNetherBridgeStartPiece, var10, par2List, par3Random, par4, par5, par6, par7, par8 + 1);
/*     */       
/* 115 */       if (var11 != null) {
/*     */         
/* 117 */         par2List.add(var11);
/* 118 */         par1ComponentNetherBridgeStartPiece.field_74967_d.add(var11);
/*     */       } 
/*     */       
/* 121 */       return var11;
/*     */     } 
/*     */ 
/*     */     
/* 125 */     return ComponentNetherBridgeEnd.func_74971_a(par2List, par3Random, par4, par5, par6, par7, par8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected StructureComponent getNextComponentNormal(ComponentNetherBridgeStartPiece par1ComponentNetherBridgeStartPiece, List par2List, Random par3Random, int par4, int par5, boolean par6) {
/* 134 */     switch (this.coordBaseMode) {
/*     */       
/*     */       case 0:
/* 137 */         return getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX + par4, this.boundingBox.minY + par5, this.boundingBox.maxZ + 1, this.coordBaseMode, getComponentType(), par6);
/*     */       
/*     */       case 1:
/* 140 */         return getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY + par5, this.boundingBox.minZ + par4, this.coordBaseMode, getComponentType(), par6);
/*     */       
/*     */       case 2:
/* 143 */         return getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX + par4, this.boundingBox.minY + par5, this.boundingBox.minZ - 1, this.coordBaseMode, getComponentType(), par6);
/*     */       
/*     */       case 3:
/* 146 */         return getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY + par5, this.boundingBox.minZ + par4, this.coordBaseMode, getComponentType(), par6);
/*     */     } 
/*     */     
/* 149 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected StructureComponent getNextComponentX(ComponentNetherBridgeStartPiece par1ComponentNetherBridgeStartPiece, List par2List, Random par3Random, int par4, int par5, boolean par6) {
/* 158 */     switch (this.coordBaseMode) {
/*     */       
/*     */       case 0:
/* 161 */         return getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 1, getComponentType(), par6);
/*     */       
/*     */       case 1:
/* 164 */         return getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.minZ - 1, 2, getComponentType(), par6);
/*     */       
/*     */       case 2:
/* 167 */         return getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 1, getComponentType(), par6);
/*     */       
/*     */       case 3:
/* 170 */         return getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.minZ - 1, 2, getComponentType(), par6);
/*     */     } 
/*     */     
/* 173 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected StructureComponent getNextComponentZ(ComponentNetherBridgeStartPiece par1ComponentNetherBridgeStartPiece, List par2List, Random par3Random, int par4, int par5, boolean par6) {
/* 182 */     switch (this.coordBaseMode) {
/*     */       
/*     */       case 0:
/* 185 */         return getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 3, getComponentType(), par6);
/*     */       
/*     */       case 1:
/* 188 */         return getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.maxZ + 1, 0, getComponentType(), par6);
/*     */       
/*     */       case 2:
/* 191 */         return getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 3, getComponentType(), par6);
/*     */       
/*     */       case 3:
/* 194 */         return getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.maxZ + 1, 0, getComponentType(), par6);
/*     */     } 
/*     */     
/* 197 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean isAboveGround(StructureBoundingBox par0StructureBoundingBox) {
/* 206 */     return (par0StructureBoundingBox != null && par0StructureBoundingBox.minY > 10);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentNetherBridgePiece.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */