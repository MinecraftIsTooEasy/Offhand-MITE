/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class BlockOperation
/*    */ {
/*    */   public final EnumBlockOperation type;
/*    */   public final int x;
/*    */   public final int y;
/*    */   public final int z;
/*    */   public final long tick;
/*    */   public final Object object;
/*    */   
/*    */   public BlockOperation(EnumBlockOperation type, int x, int y, int z, long tick, Object object) {
/* 15 */     this.type = type;
/* 16 */     this.x = x;
/* 17 */     this.y = y;
/* 18 */     this.z = z;
/* 19 */     this.tick = tick;
/*    */     
/* 21 */     this.object = object;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockOperation(EnumBlockOperation type, int x, int y, int z, long tick) {
/* 26 */     this(type, x, y, z, tick, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDuplicate(EnumBlockOperation type, int x, int y, int z, long tick) {
/* 31 */     return (type == this.type && x == this.x && y == this.y && z == this.z && tick == this.tick);
/*    */   }
/*    */ 
/*    */   
/*    */   public void perform(WorldServer world) {
/* 36 */     Block block = world.getBlock(this.x, this.y, this.z);
/*    */     
/* 38 */     if (this.type == EnumBlockOperation.try_extinguish_by_items) {
/*    */       
/* 40 */       if (block == Block.fire) {
/* 41 */         ((BlockFire)block).tryExtinguishByItems(world, this.x, this.y, this.z);
/*    */       }
/* 43 */     } else if (this.type == EnumBlockOperation.pumpkin_lantern_flooded) {
/*    */       
/* 45 */       if (block == Block.pumpkinLantern)
/*    */       {
/* 47 */         block.dropBlockAsEntityItem((new BlockBreakInfo(world, this.x, this.y, this.z)).setFlooded(null), Block.torchWood);
/* 48 */         world.setBlock(this.x, this.y, this.z, Block.pumpkin.blockID, world.getBlockMetadata(this.x, this.y, this.z), 3);
/*    */         
/* 50 */         world.playSoundEffect((this.x + 0.5F), (this.y + 0.5F), (this.z + 0.5F), "random.pop", 0.05F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*    */       }
/*    */     
/*    */     }
/* 54 */     else if (this.type == EnumBlockOperation.spawn_silverfish) {
/*    */       
/* 56 */       if (block == Block.silverfish) {
/*    */         
/* 58 */         world.watchAnimal(-1, this.x, this.y, this.z, -2);
/*    */         
/* 60 */         int metadata = world.getBlockMetadata(this.x, this.y, this.z);
/*    */         
/* 62 */         world.destroyBlock((new BlockBreakInfo(world, this.x, this.y, this.z)).setSilverfish((EntityPlayer)this.object), false);
/* 63 */         BlockSilverfish.spawnSilverfishEntity(world, this.x, this.y, this.z, metadata, (EntityPlayer)this.object);
/*    */         
/* 65 */         Block block_above = world.getBlock(this.x, this.y + 1, this.z);
/*    */         
/* 67 */         if (block_above instanceof BlockUnderminable) {
/* 68 */           ((BlockUnderminable)block_above).tryToFall(world, this.x, this.y + 1, this.z);
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean isFlushedOnExit() {
/* 75 */     return (this.type == EnumBlockOperation.spawn_silverfish);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockOperation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */