/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BlockGravel
/*     */   extends BlockFalling
/*     */   implements IBlockWithSubtypes
/*     */ {
/*     */   private BlockSubtypes subtypes;
/*     */   
/*     */   public BlockGravel(int par1) {
/*  20 */     super(par1, Material.sand, (new BlockConstants()).setUseNewSandPhysics());
/*     */     
/*  22 */     this.subtypes = new BlockSubtypes(new String[] { "gravel", "nether_gravel" });
/*     */   }
/*     */ 
/*     */   
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/*  27 */     if (info.getMetadata() == 1) {
/*  28 */       return super.dropBlockAsEntityItem(info);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  36 */     if (info.wasExploded() || !info.wasHarvestedByPlayer()) {
/*  37 */       return super.dropBlockAsEntityItem(info);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  99 */     int fortune = info.getHarvesterFortune();
/*     */     
/* 101 */     if (fortune > 3) {
/* 102 */       fortune = 3;
/*     */     }
/* 104 */     Random rand = info.world.rand;
/*     */     
/* 106 */     if (rand.nextInt(12 - fortune * 2) > 2) {
/* 107 */       return super.dropBlockAsEntityItem(info);
/*     */     }
/* 109 */     int id_dropped = -1;
/*     */     
/* 111 */     if (rand.nextInt(3) > 0) {
/*     */ 
/*     */       
/* 114 */       if (rand.nextInt(16) == 0) {
/* 115 */         id_dropped = info.wasExploded() ? Item.chipFlint.itemID : Item.flint.itemID;
/*     */       } else {
/*     */         
/* 118 */         if (info.wasExploded()) {
/* 119 */           return super.dropBlockAsEntityItem(info);
/*     */         }
/* 121 */         id_dropped = Item.chipFlint.itemID;
/*     */       } 
/* 123 */     } else if (rand.nextInt(3) > 0) {
/*     */       
/* 125 */       id_dropped = Item.copperNugget.itemID;
/*     */     }
/* 127 */     else if (rand.nextInt(3) > 0) {
/*     */       
/* 129 */       id_dropped = Item.silverNugget.itemID;
/*     */     }
/* 131 */     else if (rand.nextInt(3) > 0) {
/*     */       
/* 133 */       id_dropped = Item.goldNugget.itemID;
/*     */     }
/* 135 */     else if (rand.nextInt(3) > 0) {
/*     */       
/* 137 */       id_dropped = info.wasExploded() ? -1 : Item.shardObsidian.itemID;
/*     */     }
/* 139 */     else if (rand.nextInt(3) > 0) {
/*     */       
/* 141 */       id_dropped = info.wasExploded() ? -1 : Item.shardEmerald.itemID;
/*     */     }
/* 143 */     else if (rand.nextInt(3) > 0) {
/*     */       
/* 145 */       id_dropped = info.wasExploded() ? -1 : Item.shardDiamond.itemID;
/*     */     }
/* 147 */     else if (rand.nextInt(3) > 0) {
/*     */       
/* 149 */       id_dropped = Item.mithrilNugget.itemID;
/*     */     }
/*     */     else {
/*     */       
/* 153 */       id_dropped = Item.adamantiumNugget.itemID;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 159 */     if (isNetherGravel(info.getMetadata()))
/*     */     {
/* 161 */       if (id_dropped == Item.copperNugget.itemID || id_dropped == Item.silverNugget.itemID || id_dropped == Item.mithrilNugget.itemID || id_dropped == Item.adamantiumNugget.itemID) {
/* 162 */         id_dropped = Item.goldNugget.itemID;
/* 163 */       } else if (id_dropped == Item.shardObsidian.itemID || id_dropped == Item.shardEmerald.itemID || id_dropped == Item.shardDiamond.itemID) {
/* 164 */         id_dropped = Item.shardNetherQuartz.itemID;
/*     */       } 
/*     */     }
/* 167 */     if (id_dropped != -1) {
/* 168 */       DedicatedServer.incrementTournamentScoringCounter(info.getResponsiblePlayer(), Item.getItem(id_dropped));
/*     */     }
/* 170 */     if (info.wasHarvestedByPlayer() && (id_dropped == Item.chipFlint.itemID || id_dropped == Item.flint.itemID)) {
/* 171 */       info.getResponsiblePlayer().triggerAchievement(AchievementList.flintFinder);
/*     */     }
/* 173 */     return dropBlockAsEntityItem(info, id_dropped);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/* 178 */     return "0=Gravel, 1=Village Road, 2=Nether Gravel";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/* 184 */     return (metadata >= 0 && metadata < 3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBlockSubtypeUnchecked(int metadata) {
/* 190 */     return (metadata == 2) ? 1 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getItemSubtype(int metadata) {
/* 195 */     return (getBlockSubtype(metadata) == 1) ? 2 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNetherGravel(int metadata) {
/* 200 */     return isNetherGravel(this, metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isNetherGravel(Block block, int metadata) {
/* 205 */     return (block == gravel && block.getBlockSubtype(metadata) == 1);
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
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 220 */     this.subtypes.setIcons(registerIcons(par1IconRegister, getTextures()));
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getIcon(int side, int metadata) {
/* 225 */     return this.subtypes.getIcon(getBlockSubtype(metadata));
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getTextures() {
/* 230 */     return this.subtypes.getTextures();
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getNames() {
/* 235 */     return this.subtypes.getNames();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockGravel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */