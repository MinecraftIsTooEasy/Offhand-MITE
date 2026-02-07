/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public class ItemEnderEye
/*     */   extends Item
/*     */ {
/*     */   public ItemEnderEye(int par1) {
/*   8 */     super(par1, new Material[] { Material.ender_pearl, Material.blaze }, "ender_eye");
/*   9 */     setCreativeTab(CreativeTabs.tabMisc);
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
/*     */   private boolean tryInsertEyeIntoFrame(World world, int x, int y, int z) {
/* 150 */     Block block = world.getBlock(x, y, z);
/*     */     
/* 152 */     if (block != Block.endPortalFrame) {
/* 153 */       return false;
/*     */     }
/* 155 */     int metadata = world.getBlockMetadata(x, y, z);
/*     */     
/* 157 */     if (BlockEndPortalFrame.isEnderEyeInserted(metadata)) {
/* 158 */       return false;
/*     */     }
/* 160 */     if (world.isRemote) {
/* 161 */       return true;
/*     */     }
/* 163 */     world.setBlockMetadataWithNotify(x, y, z, metadata + 4, 2);
/* 164 */     world.func_96440_m(x, y, z, Block.endPortalFrame.blockID);
/*     */     
/*     */     int var13;
/*     */     
/* 168 */     for (var13 = 0; var13 < 16; var13++) {
/*     */       
/* 170 */       double var14 = (x + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F);
/* 171 */       double var16 = (y + 0.8125F);
/* 172 */       double var18 = (z + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F);
/* 173 */       double var20 = 0.0D;
/* 174 */       double var22 = 0.0D;
/* 175 */       double var24 = 0.0D;
/*     */       
/* 177 */       world.spawnParticle(EnumParticle.smoke, var14, var16, var18, var20, var22, var24);
/*     */     } 
/*     */     
/* 180 */     var13 = metadata & 0x3;
/* 181 */     int var26 = 0;
/* 182 */     int var15 = 0;
/* 183 */     boolean var27 = false;
/* 184 */     boolean var17 = true;
/* 185 */     int var28 = Direction.rotateRight[var13];
/*     */ 
/*     */ 
/*     */     
/*     */     int var19;
/*     */ 
/*     */     
/* 192 */     for (var19 = -2; var19 <= 2; var19++) {
/*     */       
/* 194 */       int var29 = x + Direction.offsetX[var28] * var19;
/* 195 */       int var21 = z + Direction.offsetZ[var28] * var19;
/* 196 */       int var30 = world.getBlockId(var29, y, var21);
/*     */       
/* 198 */       if (var30 == Block.endPortalFrame.blockID) {
/*     */         
/* 200 */         int var23 = world.getBlockMetadata(var29, y, var21);
/*     */         
/* 202 */         if (!BlockEndPortalFrame.isEnderEyeInserted(var23)) {
/*     */           
/* 204 */           var17 = false;
/*     */           
/*     */           break;
/*     */         } 
/* 208 */         var15 = var19;
/*     */         
/* 210 */         if (!var27) {
/*     */           
/* 212 */           var26 = var19;
/* 213 */           var27 = true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 218 */     if (var17 && var15 == var26 + 2) {
/*     */       
/* 220 */       for (var19 = var26; var19 <= var15; var19++) {
/*     */         
/* 222 */         int var29 = x + Direction.offsetX[var28] * var19;
/* 223 */         int var21 = z + Direction.offsetZ[var28] * var19;
/* 224 */         var29 += Direction.offsetX[var13] * 4;
/* 225 */         var21 += Direction.offsetZ[var13] * 4;
/* 226 */         int var30 = world.getBlockId(var29, y, var21);
/* 227 */         int var23 = world.getBlockMetadata(var29, y, var21);
/*     */         
/* 229 */         if (var30 != Block.endPortalFrame.blockID || !BlockEndPortalFrame.isEnderEyeInserted(var23)) {
/*     */           
/* 231 */           var17 = false;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 236 */       for (var19 = var26 - 1; var19 <= var15 + 1; var19 += 4) {
/*     */         
/* 238 */         for (int var29 = 1; var29 <= 3; var29++) {
/*     */           
/* 240 */           int var21 = x + Direction.offsetX[var28] * var19;
/* 241 */           int var30 = z + Direction.offsetZ[var28] * var19;
/* 242 */           var21 += Direction.offsetX[var13] * var29;
/* 243 */           var30 += Direction.offsetZ[var13] * var29;
/* 244 */           int var23 = world.getBlockId(var21, y, var30);
/* 245 */           int var31 = world.getBlockMetadata(var21, y, var30);
/*     */           
/* 247 */           if (var23 != Block.endPortalFrame.blockID || !BlockEndPortalFrame.isEnderEyeInserted(var31)) {
/*     */             
/* 249 */             var17 = false;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 255 */       if (var17)
/*     */       {
/* 257 */         for (var19 = var26; var19 <= var15; var19++) {
/*     */           
/* 259 */           for (int var29 = 1; var29 <= 3; var29++) {
/*     */             
/* 261 */             int var21 = x + Direction.offsetX[var28] * var19;
/* 262 */             int var30 = z + Direction.offsetZ[var28] * var19;
/* 263 */             var21 += Direction.offsetX[var13] * var29;
/* 264 */             var30 += Direction.offsetZ[var13] * var29;
/* 265 */             world.setBlock(var21, y, var30, Block.endPortal.blockID, 0, 2);
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 271 */     return true;
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
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 315 */     RaycastCollision rc = player.getSelectedObject(partial_tick, false);
/*     */     
/* 317 */     if (rc != null && rc.isBlock())
/*     */     {
/* 319 */       if (rc.getBlockHit() == Block.endPortalFrame && !BlockEndPortalFrame.isEnderEyeInserted(rc.block_hit_metadata) && tryInsertEyeIntoFrame(player.worldObj, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z)) {
/*     */         
/* 321 */         if (player.onClient()) {
/* 322 */           player.swingArm();
/* 323 */         } else if (!player.inCreativeMode()) {
/* 324 */           player.convertOneOfHeldItem((ItemStack)null);
/*     */         } 
/* 326 */         return true;
/*     */       } 
/*     */     }
/*     */     
/* 330 */     if (player.worldObj.provider.dimensionId != 0) {
/* 331 */       return false;
/*     */     }
/* 333 */     if (player.onClient()) {
/*     */       
/* 335 */       player.swingArm();
/*     */     }
/*     */     else {
/*     */       
/* 339 */       WorldServer world = player.getWorldServer();
/*     */       
/* 341 */       ChunkPosition chunk_pos = world.findClosestStructure("Stronghold", (int)player.posX, (int)player.posY, (int)player.posZ);
/*     */       
/* 343 */       if (chunk_pos != null) {
/*     */ 
/*     */         
/* 346 */         EntityEnderEye eye = new EntityEnderEye(world, player.posX, player.posY + 1.62D - player.yOffset, player.posZ);
/* 347 */         eye.moveTowards(chunk_pos.x, chunk_pos.y, chunk_pos.z);
/* 348 */         world.spawnEntityInWorld(eye);
/* 349 */         world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
/* 350 */         world.playAuxSFXAtEntity((EntityPlayer)null, 1002, (int)player.posX, (int)player.posY, (int)player.posZ, 0);
/*     */         
/* 352 */         if (!player.inCreativeMode()) {
/* 353 */           player.convertOneOfHeldItem((ItemStack)null);
/*     */         }
/*     */       } 
/*     */     } 
/* 357 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemEnderEye.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */