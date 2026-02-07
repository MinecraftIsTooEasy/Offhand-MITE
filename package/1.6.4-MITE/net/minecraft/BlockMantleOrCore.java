/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockMantleOrCore
/*     */   extends Block
/*     */   implements IBlockWithSubtypes
/*     */ {
/*     */   public static final int METADATA_MANTLE = 0;
/*     */   public static final int METADATA_CORE = 1;
/*     */   public static final int SUBTYPE_MANTLE = 0;
/*     */   public static final int SUBTYPE_CORE = 1;
/*     */   private BlockSubtypes subtypes;
/*     */   
/*     */   public BlockMantleOrCore(int id, Material material, BlockConstants constants) {
/*  19 */     super(id, material, constants);
/*     */     
/*  21 */     this.subtypes = new BlockSubtypes(new String[] { "mantle", "core" });
/*     */     
/*  23 */     setTickRandomly(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeCarried() {
/*  28 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  33 */     return "0=Mantle, 1=Core";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  38 */     return (metadata >= 0 && metadata < 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockSubtypeUnchecked(int metadata) {
/*  43 */     return metadata;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMantle(int metadata) {
/*  48 */     return isMantle(this, metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isMantle(Block block, int metadata) {
/*  53 */     return (block == mantleOrCore && block.getBlockSubtype(metadata) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCore(int metadata) {
/*  58 */     return isCore(this, metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isCore(Block block, int metadata) {
/*  63 */     return (block == mantleOrCore && block.getBlockSubtype(metadata) == 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/*  68 */     this.subtypes.setIcons(registerIcons(par1IconRegister, getTextures()));
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getIcon(int side, int metadata) {
/*  73 */     return this.subtypes.getIcon(getBlockSubtype(metadata));
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getTextures() {
/*  78 */     return this.subtypes.getTextures();
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getNames() {
/*  83 */     return this.subtypes.getNames();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/*  88 */     int var6 = par5Random.nextInt(3);
/*     */     
/*     */     int var7;
/*     */     
/*  92 */     for (var7 = 0; var7 < var6; var7++) {
/*     */       
/*  94 */       par2 += par5Random.nextInt(3) - 1;
/*  95 */       par3++;
/*  96 */       par4 += par5Random.nextInt(3) - 1;
/*  97 */       int var8 = par1World.getBlockId(par2, par3, par4);
/*     */       
/*  99 */       if (var8 == 0) {
/*     */         
/* 101 */         if (BlockStationary.isFlammable(par1World, par2 - 1, par3, par4) || BlockStationary.isFlammable(par1World, par2 + 1, par3, par4) || BlockStationary.isFlammable(par1World, par2, par3, par4 - 1) || BlockStationary.isFlammable(par1World, par2, par3, par4 + 1) || BlockStationary.isFlammable(par1World, par2, par3 - 1, par4) || BlockStationary.isFlammable(par1World, par2, par3 + 1, par4))
/*     */         {
/* 103 */           par1World.setBlock(par2, par3, par4, Block.fire.blockID);
/* 104 */           return false;
/*     */         }
/*     */       
/* 107 */       } else if (getBlock(var8).isSolid(par1World, par2, par3, par4)) {
/*     */         
/* 109 */         return false;
/*     */       } 
/*     */     } 
/*     */     
/* 113 */     if (var6 == 0) {
/*     */       
/* 115 */       var7 = par2;
/* 116 */       int var8 = par4;
/*     */       
/* 118 */       for (int var9 = 0; var9 < 3; var9++) {
/*     */         
/* 120 */         par2 = var7 + par5Random.nextInt(3) - 1;
/* 121 */         par4 = var8 + par5Random.nextInt(3) - 1;
/*     */         
/* 123 */         if (par1World.isAirBlock(par2, par3 + 1, par4) && BlockStationary.isFlammable(par1World, par2, par3, par4))
/*     */         {
/* 125 */           par1World.setBlock(par2, par3 + 1, par4, Block.fire.blockID);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 130 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockMantleOrCore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */