/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemDoor
/*     */   extends Item
/*     */ {
/*     */   private Material door_material;
/*     */   
/*     */   public ItemDoor(int par1, Material par2Material) {
/*  11 */     super(par1, par2Material, "doors/" + par2Material.name);
/*  12 */     this.door_material = par2Material;
/*     */ 
/*     */     
/*  15 */     setMaxStackSize(1);
/*  16 */     setCreativeTab(CreativeTabs.tabRedstone);
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getBlock() {
/*  21 */     if (this.door_material == Material.wood)
/*  22 */       return Block.doorWood; 
/*  23 */     if (this.door_material == Material.copper)
/*  24 */       return Block.doorCopper; 
/*  25 */     if (this.door_material == Material.silver)
/*  26 */       return Block.doorSilver; 
/*  27 */     if (this.door_material == Material.gold)
/*  28 */       return Block.doorGold; 
/*  29 */     if (this.door_material == Material.iron)
/*  30 */       return Block.doorIron; 
/*  31 */     if (this.door_material == Material.mithril)
/*  32 */       return Block.doorMithril; 
/*  33 */     if (this.door_material == Material.adamantium)
/*  34 */       return Block.doorAdamantium; 
/*  35 */     if (this.door_material == Material.ancient_metal) {
/*  36 */       return Block.doorAncientMetal;
/*     */     }
/*  38 */     return null;
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
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 100 */     RaycastCollision rc = player.getSelectedObject(partial_tick, true);
/*     */     
/* 102 */     if (rc == null || !rc.isBlock()) {
/* 103 */       return false;
/*     */     }
/* 105 */     return player.tryPlaceHeldItemAsBlock(rc, getBlock());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void placeDoorBlock(World par0World, int par1, int par2, int par3, int par4, Block par5Block) {
/* 110 */     byte var6 = 0;
/* 111 */     byte var7 = 0;
/*     */     
/* 113 */     if (par4 == 0)
/*     */     {
/* 115 */       var7 = 1;
/*     */     }
/*     */     
/* 118 */     if (par4 == 1)
/*     */     {
/* 120 */       var6 = -1;
/*     */     }
/*     */     
/* 123 */     if (par4 == 2)
/*     */     {
/* 125 */       var7 = -1;
/*     */     }
/*     */     
/* 128 */     if (par4 == 3)
/*     */     {
/* 130 */       var6 = 1;
/*     */     }
/*     */     
/* 133 */     int var8 = (par0World.isBlockNormalCube(par1 - var6, par2, par3 - var7) ? 1 : 0) + (par0World.isBlockNormalCube(par1 - var6, par2 + 1, par3 - var7) ? 1 : 0);
/* 134 */     int var9 = (par0World.isBlockNormalCube(par1 + var6, par2, par3 + var7) ? 1 : 0) + (par0World.isBlockNormalCube(par1 + var6, par2 + 1, par3 + var7) ? 1 : 0);
/* 135 */     boolean var10 = (par0World.getBlockId(par1 - var6, par2, par3 - var7) == par5Block.blockID || par0World.getBlockId(par1 - var6, par2 + 1, par3 - var7) == par5Block.blockID);
/* 136 */     boolean var11 = (par0World.getBlockId(par1 + var6, par2, par3 + var7) == par5Block.blockID || par0World.getBlockId(par1 + var6, par2 + 1, par3 + var7) == par5Block.blockID);
/* 137 */     boolean var12 = false;
/*     */     
/* 139 */     if (var10 && !var11) {
/*     */       
/* 141 */       var12 = true;
/*     */     }
/* 143 */     else if (var9 > var8) {
/*     */       
/* 145 */       var12 = true;
/*     */     } 
/*     */     
/* 148 */     par0World.setBlock(par1, par2, par3, par5Block.blockID, par4, 2);
/* 149 */     par0World.setBlock(par1, par2 + 1, par3, par5Block.blockID, 0x8 | (var12 ? 1 : 0), 2);
/* 150 */     par0World.notifyBlocksOfNeighborChange(par1, par2, par3, par5Block.blockID);
/* 151 */     par0World.notifyBlocksOfNeighborChange(par1, par2 + 1, par3, par5Block.blockID);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemDoor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */