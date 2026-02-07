/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class WorldGenDungeons
/*     */   extends WorldGenerator
/*     */ {
/*   8 */   private static final WeightedRandomChestContent[] field_111189_a = new WeightedRandomChestContent[] { new WeightedRandomChestContent(Item.bread.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Item.carrot.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.potato.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.onion.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.wheat.itemID, 0, 1, 4, 10), new WeightedRandomChestContent(Item.appleGold.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.coinCopper.itemID, 0, 1, 4, 10), new WeightedRandomChestContent(Item.coinSilver.itemID, 0, 1, 2, 2), new WeightedRandomChestContent(Item.coinGold.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.saddle.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Item.gunpowder.itemID, 0, 1, 4, 10), new WeightedRandomChestContent(Item.silk.itemID, 0, 1, 4, 10), new WeightedRandomChestContent(Item.bowlEmpty.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.bucketCopperEmpty.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.bucketIronEmpty.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.record13.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.recordCat.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.nameTag.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Item.horseArmorGold.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.horseArmorCopper.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.horseArmorIron.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.shearsRustedIron.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.fishingRodFlint.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.fishingRodCopper.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.fishingRodIron.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.shovelWood.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.shovelRustedIron.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.shovelCopper.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.hoeRustedIron.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.hoeCopper.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.mattockRustedIron.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.mattockCopper.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.daggerRustedIron.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.daggerCopper.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.daggerSilver.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.swordRustedIron.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.swordCopper.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.battleAxeRustedIron.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.battleAxeCopper.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.warHammerRustedIron.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.warHammerCopper.itemID, 0, 1, 1, 1) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   private static final WeightedRandomChestContent[] chest_contents_for_underworld = new WeightedRandomChestContent[] { new WeightedRandomChestContent(Item.ancientMetalNugget.itemID, 0, 1, 4, 10), new WeightedRandomChestContent(Item.ingotAncientMetal.itemID, 0, 1, 4, 10), new WeightedRandomChestContent(Item.gunpowder.itemID, 0, 1, 4, 10), new WeightedRandomChestContent(Item.silk.itemID, 0, 1, 4, 10), new WeightedRandomChestContent(Item.bowlEmpty.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.bucketAncientMetalEmpty.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.coinAncientMetal.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.recordUnderworld.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.recordDescent.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.recordWanderer.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.recordLegends.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.nameTag.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.horseArmorAncientMetal.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.fishingRodAncientMetal.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.pickaxeAncientMetal.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.shovelAncientMetal.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.daggerAncientMetal.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.swordAncientMetal.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.bowAncientMetal.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.bootsChainAncientMetal.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.legsChainAncientMetal.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.plateChainAncientMetal.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.helmetChainAncientMetal.itemID, 0, 1, 1, 2) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static WeightedRandomChestContent[] getChestContentsForWorld(World world) {
/* 104 */     return world.isUnderworld() ? chest_contents_for_underworld : field_111189_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
/* 109 */     byte var6 = 3;
/* 110 */     int var7 = par2Random.nextInt(2) + 2;
/* 111 */     int var8 = par2Random.nextInt(2) + 2;
/* 112 */     int var9 = 0;
/*     */ 
/*     */     
/*     */     int var10;
/*     */     
/* 117 */     for (var10 = par3 - var7 - 1; var10 <= par3 + var7 + 1; var10++) {
/*     */       
/* 119 */       for (int var11 = par4 - 1; var11 <= par4 + var6 + 1; var11++) {
/*     */         
/* 121 */         for (int var12 = par5 - var8 - 1; var12 <= par5 + var8 + 1; var12++) {
/*     */           
/* 123 */           Material var13 = par1World.getBlockMaterial(var10, var11, var12);
/*     */           
/* 125 */           if (var11 == par4 - 1 && !var13.isSolid())
/*     */           {
/* 127 */             return false;
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 135 */           if (var11 == par4 + var6 + 1) {
/*     */             
/* 137 */             if (!var13.isSolid()) {
/* 138 */               return false;
/*     */             }
/* 140 */             Block block = par1World.getBlock(var10, var11, var12);
/*     */ 
/*     */             
/* 143 */             if (block instanceof BlockFalling) {
/* 144 */               return false;
/*     */             }
/*     */           } 
/* 147 */           if ((var10 == par3 - var7 - 1 || var10 == par3 + var7 + 1 || var12 == par5 - var8 - 1 || var12 == par5 + var8 + 1) && var11 == par4 && par1World.isAirBlock(var10, var11, var12) && par1World.isAirBlock(var10, var11 + 1, var12))
/*     */           {
/* 149 */             var9++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 155 */     if (var9 >= 1 && var9 <= 5) {
/*     */       
/* 157 */       for (var10 = par3 - var7 - 1; var10 <= par3 + var7 + 1; var10++) {
/*     */         
/* 159 */         for (int var11 = par4 + var6; var11 >= par4 - 1; var11--) {
/*     */           
/* 161 */           for (int var12 = par5 - var8 - 1; var12 <= par5 + var8 + 1; var12++) {
/*     */             
/* 163 */             if (var10 != par3 - var7 - 1 && var11 != par4 - 1 && var12 != par5 - var8 - 1 && var10 != par3 + var7 + 1 && var11 != par4 + var6 + 1 && var12 != par5 + var8 + 1) {
/*     */               
/* 165 */               par1World.setBlockToAir(var10, var11, var12);
/*     */             }
/* 167 */             else if (var11 >= 0 && !par1World.getBlockMaterial(var10, var11 - 1, var12).isSolid()) {
/*     */               
/* 169 */               par1World.setBlockToAir(var10, var11, var12);
/*     */             }
/* 171 */             else if (par1World.getBlockMaterial(var10, var11, var12).isSolid()) {
/*     */               
/* 173 */               if (var11 == par4 - 1 && par2Random.nextInt(4) != 0) {
/*     */                 
/* 175 */                 par1World.setBlock(var10, var11, var12, Block.cobblestoneMossy.blockID, 0, 2);
/*     */               }
/*     */               else {
/*     */                 
/* 179 */                 par1World.setBlock(var10, var11, var12, Block.cobblestone.blockID, 0, 2);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 186 */       var10 = 0;
/*     */       
/* 188 */       while (var10 < 2) {
/*     */         
/* 190 */         int var11 = 0;
/*     */ 
/*     */ 
/*     */         
/* 194 */         while (var11 < 3) {
/*     */ 
/*     */ 
/*     */           
/* 198 */           int var12 = par3 + par2Random.nextInt(var7 * 2 + 1) - var7;
/* 199 */           int var14 = par5 + par2Random.nextInt(var8 * 2 + 1) - var8;
/*     */           
/* 201 */           if (par1World.isAirBlock(var12, par4, var14)) {
/*     */             
/* 203 */             int var15 = 0;
/* 204 */             EnumDirection direction = null;
/*     */             
/* 206 */             if (par1World.getBlockMaterial(var12 - 1, par4, var14).isSolid()) {
/*     */               
/* 208 */               var15++;
/* 209 */               direction = EnumDirection.EAST;
/*     */             } 
/*     */             
/* 212 */             if (par1World.getBlockMaterial(var12 + 1, par4, var14).isSolid()) {
/*     */               
/* 214 */               var15++;
/* 215 */               direction = EnumDirection.WEST;
/*     */             } 
/*     */             
/* 218 */             if (par1World.getBlockMaterial(var12, par4, var14 - 1).isSolid()) {
/*     */               
/* 220 */               var15++;
/* 221 */               direction = EnumDirection.SOUTH;
/*     */             } 
/*     */             
/* 224 */             if (par1World.getBlockMaterial(var12, par4, var14 + 1).isSolid()) {
/*     */               
/* 226 */               var15++;
/* 227 */               direction = EnumDirection.NORTH;
/*     */             } 
/*     */             
/* 230 */             if (var15 == 1) {
/*     */ 
/*     */               
/* 233 */               par1World.setBlock(var12, par4, var14, Block.chest.blockID, Block.chest.getMetadataForDirectionFacing(0, direction), 2);
/*     */               
/* 235 */               WeightedRandomChestContent[] var16 = WeightedRandomChestContent.func_92080_a(getChestContentsForWorld(par1World), new WeightedRandomChestContent[] { Item.enchantedBook.func_92114_b(par2Random) });
/* 236 */               TileEntityChest var17 = (TileEntityChest)par1World.getBlockTileEntity(var12, par4, var14);
/*     */               
/* 238 */               if (var17 != null)
/*     */               {
/*     */                 
/* 241 */                 WeightedRandomChestContent.generateChestContents(par1World, par4, par2Random, var16, var17, 8, (float[])null);
/*     */               }
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */           
/* 248 */           var11++;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 253 */         var10++;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 258 */       if (!par1World.isOverworld()) {
/* 259 */         Debug.println("Dungeon at " + StringHelper.getCoordsAsString(par3, par4, par5));
/*     */       }
/* 261 */       par1World.setBlock(par3, par4, par5, Block.mobSpawner.blockID, 0, 2);
/* 262 */       TileEntityMobSpawner var18 = (TileEntityMobSpawner)par1World.getBlockTileEntity(par3, par4, par5);
/*     */       
/* 264 */       if (var18 != null) {
/*     */ 
/*     */         
/* 267 */         var18.getSpawnerLogic().setMobID(pickMobSpawner(par1World, par2Random, par4));
/*     */       }
/*     */       else {
/*     */         
/* 271 */         System.err.println("Failed to fetch mob spawner entity at (" + par3 + ", " + par4 + ", " + par5 + ")");
/*     */       } 
/*     */       
/* 274 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 278 */     return false;
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
/*     */   private String pickMobSpawner(World world, Random par1Random, int y) {
/*     */     int danger;
/* 293 */     if (world.isUnderworld()) {
/* 294 */       return (par1Random.nextInt(6) == 0) ? "LongdeadGuardian" : "Longdead";
/*     */     }
/*     */ 
/*     */     
/* 298 */     if (par1Random.nextInt(2) == 0) {
/* 299 */       danger = par1Random.nextInt(4);
/*     */     } else {
/* 301 */       danger = (int)Math.max(1.0F - y / 64.0F, 0.0F) * 4 + par1Random.nextInt(3) - par1Random.nextInt(3);
/*     */     } 
/* 303 */     if (danger < 0) {
/* 304 */       danger = par1Random.nextInt(4);
/*     */     }
/* 306 */     switch (danger) {
/*     */       
/*     */       case 0:
/* 309 */         return "Zombie";
/*     */       case 1:
/* 311 */         return "Ghoul";
/*     */       case 2:
/* 313 */         return "Skeleton";
/*     */       case 3:
/* 315 */         return "Spider";
/*     */       case 4:
/* 317 */         return "Wight";
/*     */       case 5:
/* 319 */         return "DemonSpider";
/*     */     } 
/* 321 */     return "Hellhound";
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenDungeons.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */