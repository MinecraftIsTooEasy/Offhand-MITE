/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockOre
/*     */   extends Block
/*     */ {
/*     */   public Material vein_material;
/*     */   
/*     */   public BlockOre(int par1, Material vein_material, int min_harvest_level) {
/*  13 */     super(par1, Material.stone, new BlockConstants());
/*  14 */     this.vein_material = vein_material;
/*  15 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */     
/*  17 */     setMinHarvestLevel(min_harvest_level);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  22 */     return "0=Natural, 1=Placed";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  27 */     return (metadata >= 0 && metadata < 2);
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
/*     */   public void onBlockAboutToBeBroken(BlockBreakInfo info) {
/* 117 */     if (this == oreCopper && (info.wasHarvested() || info.wasExploded())) {
/*     */       
/* 119 */       Chunk chunk = info.getChunkIfItExists();
/*     */       
/* 121 */       if (chunk != null && chunk.doAllNeighborsExist(1, false, false)) {
/*     */         
/* 123 */         World world = info.world;
/*     */         
/* 125 */         for (int dx = -3; dx <= 3; dx++) {
/*     */           
/* 127 */           for (int dy = -3; dy <= 3; dy++) {
/*     */             
/* 129 */             for (int dz = -3; dz <= 3; dz++) {
/*     */               
/* 131 */               int x = info.x + dx;
/* 132 */               int y = info.y + dy;
/* 133 */               int z = info.z + dz;
/*     */               
/* 135 */               Block block = world.getBlock(x, y, z);
/*     */               
/* 137 */               if (block == silverfish) {
/* 138 */                 BlockSilverfish.updateSilverfishType(world, x, y, z);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 154 */     int id_dropped, metadata_dropped = -1;
/* 155 */     int quantity_dropped = 1;
/*     */     
/* 157 */     if (info.wasExploded()) {
/*     */       
/* 159 */       if (this == oreEmerald)
/*     */       {
/* 161 */         id_dropped = Item.shardEmerald.itemID;
/*     */       }
/* 163 */       else if (this == oreDiamond)
/*     */       {
/* 165 */         id_dropped = Item.shardDiamond.itemID;
/*     */       }
/* 167 */       else if (this == oreLapis)
/*     */       {
/* 169 */         id_dropped = Item.dyePowder.itemID;
/* 170 */         metadata_dropped = 4;
/* 171 */         quantity_dropped = 3 + info.world.rand.nextInt(3);
/*     */       }
/* 173 */       else if (this == oreNetherQuartz)
/*     */       {
/* 175 */         id_dropped = Item.shardNetherQuartz.itemID;
/*     */       }
/* 177 */       else if (this == oreCoal)
/*     */       {
/* 179 */         id_dropped = -1;
/*     */       }
/*     */       else
/*     */       {
/* 183 */         id_dropped = this.blockID;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 188 */       if (info.wasHarvestedByPlayer() && (info.getResponsiblePlayer()).worldObj.areSkillsEnabled() && !info.getResponsiblePlayer().hasSkill(Skill.MINING)) {
/* 189 */         return super.dropBlockAsEntityItem(info);
/*     */       }
/* 191 */       if (this == oreCoal) {
/*     */         
/* 193 */         id_dropped = Item.coal.itemID;
/*     */       }
/* 195 */       else if (this == oreDiamond) {
/*     */         
/* 197 */         id_dropped = Item.diamond.itemID;
/*     */       }
/* 199 */       else if (this == oreLapis) {
/*     */         
/* 201 */         id_dropped = Item.dyePowder.itemID;
/* 202 */         metadata_dropped = 4;
/* 203 */         quantity_dropped = 3 + info.world.rand.nextInt(3);
/*     */       }
/* 205 */       else if (this == oreEmerald) {
/*     */         
/* 207 */         id_dropped = Item.emerald.itemID;
/*     */       }
/* 209 */       else if (this == oreNetherQuartz) {
/*     */         
/* 211 */         id_dropped = Item.netherQuartz.itemID;
/*     */       }
/*     */       else {
/*     */         
/* 215 */         id_dropped = this.blockID;
/*     */       } 
/*     */     } 
/*     */     
/* 219 */     if (metadata_dropped == -1) {
/* 220 */       metadata_dropped = (id_dropped == this.blockID) ? getItemSubtype(info.getMetadata()) : 0;
/*     */     }
/*     */     
/* 223 */     boolean suppress_fortune = (id_dropped == this.blockID && BitHelper.isBitSet(info.getMetadata(), 1));
/*     */     
/* 225 */     if (id_dropped != -1 && info.getMetadata() == 0) {
/* 226 */       DedicatedServer.incrementTournamentScoringCounter(info.getResponsiblePlayer(), Item.getItem(id_dropped));
/*     */     }
/* 228 */     float chance = suppress_fortune ? 1.0F : (1.0F + info.getHarvesterFortune() * 0.1F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 233 */     return dropBlockAsEntityItem(info, id_dropped, metadata_dropped, quantity_dropped, chance);
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
/*     */   public void addItemBlockMaterials(ItemBlock item_block) {
/* 259 */     item_block.addMaterial(new Material[] { this.blockMaterial, this.vein_material });
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
/* 264 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockOre.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */