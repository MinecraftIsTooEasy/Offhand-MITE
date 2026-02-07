/*     */ package net.minecraft;
/*     */ 
/*     */ public class BlockRailPowered
/*     */   extends BlockRailBase
/*     */ {
/*     */   protected Icon theIcon;
/*     */   
/*     */   protected BlockRailPowered(int par1) {
/*   9 */     super(par1, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  14 */     return "0=Flat NS, 1=Flat EW, 2=Inclined East, 3=Inclined West, 4=Inclined North, 5=Inclined South, bit 8 set if powered";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  19 */     return ((metadata >= 0 && metadata < 6) || (metadata >= 8 && metadata < 14));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIcon(int par1, int par2) {
/*  27 */     return ((par2 & 0x8) == 0) ? this.blockIcon : this.theIcon;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/*  36 */     super.registerIcons(par1IconRegister);
/*  37 */     this.theIcon = par1IconRegister.registerIcon(getTextureName() + "_powered");
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_94360_a(World par1World, int par2, int par3, int par4, int par5, boolean par6, int par7) {
/*  42 */     if (par7 >= 8)
/*     */     {
/*  44 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  48 */     int var8 = par5 & 0x7;
/*  49 */     boolean var9 = true;
/*     */     
/*  51 */     switch (var8) {
/*     */       
/*     */       case 0:
/*  54 */         if (par6) {
/*     */           
/*  56 */           par4++;
/*     */           
/*     */           break;
/*     */         } 
/*  60 */         par4--;
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/*  66 */         if (par6) {
/*     */           
/*  68 */           par2--;
/*     */           
/*     */           break;
/*     */         } 
/*  72 */         par2++;
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/*  78 */         if (par6) {
/*     */           
/*  80 */           par2--;
/*     */         }
/*     */         else {
/*     */           
/*  84 */           par2++;
/*  85 */           par3++;
/*  86 */           var9 = false;
/*     */         } 
/*     */         
/*  89 */         var8 = 1;
/*     */         break;
/*     */       
/*     */       case 3:
/*  93 */         if (par6) {
/*     */           
/*  95 */           par2--;
/*  96 */           par3++;
/*  97 */           var9 = false;
/*     */         }
/*     */         else {
/*     */           
/* 101 */           par2++;
/*     */         } 
/*     */         
/* 104 */         var8 = 1;
/*     */         break;
/*     */       
/*     */       case 4:
/* 108 */         if (par6) {
/*     */           
/* 110 */           par4++;
/*     */         }
/*     */         else {
/*     */           
/* 114 */           par4--;
/* 115 */           par3++;
/* 116 */           var9 = false;
/*     */         } 
/*     */         
/* 119 */         var8 = 0;
/*     */         break;
/*     */       
/*     */       case 5:
/* 123 */         if (par6) {
/*     */           
/* 125 */           par4++;
/* 126 */           par3++;
/* 127 */           var9 = false;
/*     */         }
/*     */         else {
/*     */           
/* 131 */           par4--;
/*     */         } 
/*     */         
/* 134 */         var8 = 0;
/*     */         break;
/*     */     } 
/* 137 */     return func_94361_a(par1World, par2, par3, par4, par6, par7, var8) ? true : ((var9 && func_94361_a(par1World, par2, par3 - 1, par4, par6, par7, var8)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_94361_a(World par1World, int par2, int par3, int par4, boolean par5, int par6, int par7) {
/* 143 */     int var8 = par1World.getBlockId(par2, par3, par4);
/*     */     
/* 145 */     if (var8 == this.blockID) {
/*     */       
/* 147 */       int var9 = par1World.getBlockMetadata(par2, par3, par4);
/* 148 */       int var10 = var9 & 0x7;
/*     */       
/* 150 */       if (par7 == 1 && (var10 == 0 || var10 == 4 || var10 == 5))
/*     */       {
/* 152 */         return false;
/*     */       }
/*     */       
/* 155 */       if (par7 == 0 && (var10 == 1 || var10 == 2 || var10 == 3))
/*     */       {
/* 157 */         return false;
/*     */       }
/*     */       
/* 160 */       if ((var9 & 0x8) != 0) {
/*     */         
/* 162 */         if (par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
/*     */         {
/* 164 */           return true;
/*     */         }
/*     */         
/* 167 */         return func_94360_a(par1World, par2, par3, par4, var9, par5, par6 + 1);
/*     */       } 
/*     */     } 
/*     */     
/* 171 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_94358_a(World par1World, int par2, int par3, int par4, int par5, int par6, int par7) {
/* 176 */     boolean var8 = par1World.isBlockIndirectlyGettingPowered(par2, par3, par4);
/* 177 */     var8 = (var8 || func_94360_a(par1World, par2, par3, par4, par5, true, 0) || func_94360_a(par1World, par2, par3, par4, par5, false, 0));
/* 178 */     boolean var9 = false;
/*     */     
/* 180 */     if (var8 && (par5 & 0x8) == 0) {
/*     */       
/* 182 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, par6 | 0x8, 3);
/* 183 */       var9 = true;
/*     */     }
/* 185 */     else if (!var8 && (par5 & 0x8) != 0) {
/*     */       
/* 187 */       par1World.setBlockMetadataWithNotify(par2, par3, par4, par6, 3);
/* 188 */       var9 = true;
/*     */     } 
/*     */     
/* 191 */     if (var9) {
/*     */       
/* 193 */       par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
/*     */       
/* 195 */       if (par6 == 2 || par6 == 3 || par6 == 4 || par6 == 5)
/*     */       {
/* 197 */         par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, this.blockID);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItemBlockMaterials(ItemBlock item_block) {
/* 204 */     item_block.addMaterial(new Material[] { Material.gold, Material.wood, Material.redstone });
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockRailPowered.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */