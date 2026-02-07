/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BlockUnderminable
/*     */   extends BlockFalling
/*     */ {
/*     */   protected BlockUnderminable(int par1, Material material, BlockConstants constants) {
/*  12 */     super(par1, material, constants.setUseNewSandPhysics());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  17 */     return "Bit 1 set if block has been undermined and should fall next tick";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  22 */     return (metadata >= 0 && metadata < 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {}
/*     */   
/*     */   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
/*  29 */     if (Minecraft.allow_new_sand_physics) {
/*     */       
/*  31 */       Block block_above = world.getBlock(x, y + 1, z);
/*     */       
/*  33 */       if (block_above != null && block_above.usesNewSandPhysics()) {
/*  34 */         block_above.checkIfNotLegal(world, x, y + 1, z);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  40 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockUnderminedBit() {
/*  45 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/*  50 */     int metadata = par1World.getBlockMetadata(par2, par3, par4);
/*     */     
/*  52 */     int undermined_bit = getBlockUnderminedBit();
/*     */     
/*  54 */     if ((metadata & undermined_bit) != 0) {
/*     */       
/*  56 */       onUnderminedByPlayer(par1World, (EntityPlayer)null, par2, par3, par4);
/*  57 */       par1World.setBlock(par2, par3, par4, this.blockID, metadata ^ undermined_bit, 0);
/*     */       
/*  59 */       return true;
/*     */     } 
/*     */     
/*  62 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUnderminedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
/*  67 */     if (player != null && player.capabilities.isCreativeMode) {
/*     */       return;
/*     */     }
/*  70 */     if (tryToFall(world, x, y, z)) {
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
/*  85 */       int[] dx = { 0, 1, 0, -1 };
/*  86 */       int[] dz = { -1, 0, 1, 0 };
/*     */       
/*  88 */       for (int i = 0; i < dx.length; i++) {
/*     */         
/*  90 */         int block_id = world.getBlockId(x + dx[i], y, z + dz[i]);
/*     */         
/*  92 */         if (Block.blocksList[block_id] != null && Block.blocksList[block_id] instanceof BlockUnderminable) {
/*  93 */           scheduleUndermine(world, x + dx[i], y, z + dz[i]);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void scheduleUndermine(World world, int x, int y, int z) {
/* 100 */     int block_id = world.getBlockId(x, y, z);
/*     */     
/* 102 */     if (block_id == 0) {
/*     */       return;
/*     */     }
/* 105 */     Block block = Block.getBlock(block_id);
/*     */     
/* 107 */     if (!(block instanceof BlockUnderminable)) {
/*     */       return;
/*     */     }
/* 110 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 112 */     metadata |= ((BlockUnderminable)block).getBlockUnderminedBit();
/*     */     
/* 114 */     world.setBlock(x, y, z, block_id, metadata, 0);
/* 115 */     world.scheduleBlockUpdate(x, y, z, block_id, tickRate(world));
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
/*     */   public boolean onBlockPlacedMITE(World world, int x, int y, int z, int metadata, Entity placer, boolean test_only) {
/* 128 */     if (!test_only && placer instanceof EntityPlayer && tryToFall(world, x, y, z)) {
/* 129 */       return true;
/*     */     }
/* 131 */     return super.onBlockPlacedMITE(world, x, y, z, metadata, placer, test_only);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
/* 136 */     onUnderminedByPlayer(world, (EntityPlayer)null, x, y, z);
/*     */     
/* 138 */     super.onEntityWalking(world, x, y, z, entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
/* 143 */     onUnderminedByPlayer(world, (EntityPlayer)null, x, y, z);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockUnderminable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */