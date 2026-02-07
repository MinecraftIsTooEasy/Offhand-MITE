/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class BlockComparator
/*     */   extends BlockRedstoneLogic
/*     */   implements ITileEntityProvider {
/*     */   public BlockComparator(int par1, boolean par2) {
/*   9 */     super(par1, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  15 */     return "Bits 1 and 2 used for orientation, bit 4 set if toggled, and bit 8 set if powered";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  20 */     return (metadata >= 0 && metadata < 16);
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
/*     */ 
/*     */   
/*     */   public int idPicked(World par1World, int par2, int par3, int par4) {
/*  36 */     return Item.comparator.itemID;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_94481_j_(int par1) {
/*  41 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockRedstoneLogic func_94485_e() {
/*  46 */     return Block.redstoneComparatorActive;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockRedstoneLogic func_94484_i() {
/*  51 */     return Block.redstoneComparatorIdle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/*  59 */     return 37;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  67 */     boolean var3 = (this.isRepeaterPowered || (par2 & 0x8) != 0);
/*  68 */     return (par1 == 0) ? (var3 ? Block.torchRedstoneActive.getBlockTextureFromSide(par1) : Block.torchRedstoneIdle.getBlockTextureFromSide(par1)) : ((par1 == 1) ? (var3 ? Block.redstoneComparatorActive.blockIcon : this.blockIcon) : Block.stoneDoubleSlab.getBlockTextureFromSide(1));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_96470_c(int par1) {
/*  73 */     return (this.isRepeaterPowered || (par1 & 0x8) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_94480_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/*  78 */     return getTileEntityComparator(par1IBlockAccess, par2, par3, par4).getOutputSignal();
/*     */   }
/*     */ 
/*     */   
/*     */   private int getOutputStrength(World par1World, int par2, int par3, int par4, int par5) {
/*  83 */     return !func_94490_c(par5) ? getInputStrength(par1World, par2, par3, par4, par5) : Math.max(getInputStrength(par1World, par2, par3, par4, par5) - func_94482_f(par1World, par2, par3, par4, par5), 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_94490_c(int par1) {
/*  88 */     return ((par1 & 0x4) == 4);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isGettingInput(World par1World, int par2, int par3, int par4, int par5) {
/*  93 */     int var6 = getInputStrength(par1World, par2, par3, par4, par5);
/*     */     
/*  95 */     if (var6 >= 15)
/*     */     {
/*  97 */       return true;
/*     */     }
/*  99 */     if (var6 == 0)
/*     */     {
/* 101 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 105 */     int var7 = func_94482_f(par1World, par2, par3, par4, par5);
/* 106 */     return (var7 == 0) ? true : ((var6 >= var7));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getInputStrength(World par1World, int par2, int par3, int par4, int par5) {
/* 115 */     int var6 = super.getInputStrength(par1World, par2, par3, par4, par5);
/* 116 */     int var7 = j(par5);
/* 117 */     int var8 = par2 + Direction.offsetX[var7];
/* 118 */     int var9 = par4 + Direction.offsetZ[var7];
/* 119 */     int var10 = par1World.getBlockId(var8, par3, var9);
/*     */     
/* 121 */     if (var10 > 0)
/*     */     {
/* 123 */       if (Block.blocksList[var10].hasComparatorInputOverride()) {
/*     */         
/* 125 */         var6 = Block.blocksList[var10].getComparatorInputOverride(par1World, var8, par3, var9, Direction.rotateOpposite[var7]);
/*     */       }
/* 127 */       else if (var6 < 15 && Block.isNormalCube(var10)) {
/*     */         
/* 129 */         var8 += Direction.offsetX[var7];
/* 130 */         var9 += Direction.offsetZ[var7];
/* 131 */         var10 = par1World.getBlockId(var8, par3, var9);
/*     */         
/* 133 */         if (var10 > 0 && Block.blocksList[var10].hasComparatorInputOverride())
/*     */         {
/* 135 */           var6 = Block.blocksList[var10].getComparatorInputOverride(par1World, var8, par3, var9, Direction.rotateOpposite[var7]);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 140 */     return var6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntityComparator getTileEntityComparator(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 148 */     return (TileEntityComparator)par1IBlockAccess.getBlockTileEntity(par2, par3, par4);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 169 */     if (player.onServer()) {
/*     */       
/* 171 */       int metadata = world.getBlockMetadata(x, y, z);
/*     */       
/* 173 */       int i = this.isRepeaterPowered | (((metadata & 0x8) != 0) ? 1 : 0);
/* 174 */       boolean var12 = !func_94490_c(metadata);
/*     */       
/* 176 */       int var13 = var12 ? 4 : 0;
/* 177 */       var13 |= (i != 0) ? 8 : 0;
/*     */       
/* 179 */       world.playSoundAtBlock(x, y, z, "random.click", 0.3F, var12 ? 0.55F : 0.5F);
/* 180 */       world.setBlockMetadataWithNotify(x, y, z, var13 | metadata & 0x3, 2);
/*     */       
/* 182 */       func_96476_c(world, x, y, z, world.rand);
/*     */     } 
/*     */     
/* 185 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_94479_f(World par1World, int par2, int par3, int par4, int par5) {
/* 190 */     if (!par1World.isBlockTickScheduledThisTick(par2, par3, par4, this.blockID)) {
/*     */       
/* 192 */       int var6 = par1World.getBlockMetadata(par2, par3, par4);
/* 193 */       int var7 = getOutputStrength(par1World, par2, par3, par4, var6);
/* 194 */       int var8 = getTileEntityComparator(par1World, par2, par3, par4).getOutputSignal();
/*     */       
/* 196 */       if (var7 != var8 || func_96470_c(var6) != isGettingInput(par1World, par2, par3, par4, var6))
/*     */       {
/* 198 */         if (func_83011_d(par1World, par2, par3, par4, var6)) {
/*     */           
/* 200 */           par1World.scheduleBlockUpdateWithPriority(par2, par3, par4, this.blockID, func_94481_j_(0), -1);
/*     */         }
/*     */         else {
/*     */           
/* 204 */           par1World.scheduleBlockUpdateWithPriority(par2, par3, par4, this.blockID, func_94481_j_(0), 0);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean func_96476_c(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 213 */     int var6 = par1World.getBlockMetadata(par2, par3, par4);
/* 214 */     int var7 = getOutputStrength(par1World, par2, par3, par4, var6);
/* 215 */     int var8 = getTileEntityComparator(par1World, par2, par3, par4).getOutputSignal();
/* 216 */     getTileEntityComparator(par1World, par2, par3, par4).setOutputSignal(var7);
/*     */     
/* 218 */     if (var8 != var7 || !func_94490_c(var6)) {
/*     */       
/* 220 */       boolean changed_state, var9 = isGettingInput(par1World, par2, par3, par4, var6);
/* 221 */       boolean var10 = (this.isRepeaterPowered || (var6 & 0x8) != 0);
/*     */ 
/*     */ 
/*     */       
/* 225 */       if (var10 && !var9) {
/*     */         
/* 227 */         par1World.setBlockMetadataWithNotify(par2, par3, par4, var6 & 0xFFFFFFF7, 2);
/* 228 */         changed_state = true;
/*     */       }
/* 230 */       else if (!var10 && var9) {
/*     */         
/* 232 */         par1World.setBlockMetadataWithNotify(par2, par3, par4, var6 | 0x8, 2);
/* 233 */         changed_state = true;
/*     */       }
/*     */       else {
/*     */         
/* 237 */         changed_state = false;
/*     */       } 
/*     */       
/* 240 */       func_94483_i_(par1World, par2, par3, par4);
/*     */       
/* 242 */       return changed_state;
/*     */     } 
/*     */     
/* 245 */     return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean updateTick(World world, int x, int y, int z, Random random) {
/* 266 */     if (this.isRepeaterPowered) {
/*     */       
/* 268 */       int metadata = world.getBlockMetadata(x, y, z);
/* 269 */       world.setBlock(x, y, z, (func_94484_i()).blockID, metadata | 0x8, 4);
/*     */       
/* 271 */       return true;
/*     */     } 
/*     */     
/* 274 */     return func_96476_c(world, x, y, z, random);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
/* 282 */     super.onBlockAdded(par1World, par2, par3, par4);
/* 283 */     par1World.setBlockTileEntity(par2, par3, par4, createNewTileEntity(par1World));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
/* 293 */     super.breakBlock(par1World, par2, par3, par4, par5, par6);
/* 294 */     par1World.removeBlockTileEntity(par2, par3, par4);
/* 295 */     func_94483_i_(par1World, par2, par3, par4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6) {
/* 304 */     super.onBlockEventReceived(par1World, par2, par3, par4, par5, par6);
/* 305 */     TileEntity var7 = par1World.getBlockTileEntity(par2, par3, par4);
/* 306 */     return (var7 != null) ? var7.receiveClientEvent(par5, par6) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World par1World) {
/* 314 */     return new TileEntityComparator();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeCarried() {
/* 319 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 324 */     return dropBlockAsEntityItem(info, Item.comparator);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 329 */     return this.isRepeaterPowered ? "active" : "idle";
/*     */   }
/*     */ 
/*     */   
/*     */   public final EnumDirection getDirectionFacing(int metadata) {
/* 334 */     return getDirectionFacingStandard4(metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
/* 339 */     metadata &= 0xFFFFFFFC;
/* 340 */     metadata |= direction.isSouth() ? 0 : (direction.isWest() ? 1 : (direction.isNorth() ? 2 : (direction.isEast() ? 3 : -1)));
/*     */     
/* 342 */     return metadata;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */