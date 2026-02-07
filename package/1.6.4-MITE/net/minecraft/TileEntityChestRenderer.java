/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class TileEntityChestRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*   9 */   private static final ResourceLocation RES_TRAPPED_DOUBLE = new ResourceLocation("textures/entity/chest/trapped_double.png");
/*  10 */   private static final ResourceLocation RES_CHRISTMAS_DOUBLE = new ResourceLocation("textures/entity/chest/christmas_double.png");
/*  11 */   private static final ResourceLocation RES_NORMAL_DOUBLE = new ResourceLocation("textures/entity/chest/normal_double.png");
/*  12 */   private static final ResourceLocation RES_TRAPPED_SINGLE = new ResourceLocation("textures/entity/chest/trapped.png");
/*  13 */   private static final ResourceLocation RES_CHRISTMAS_SINGLE = new ResourceLocation("textures/entity/chest/christmas.png");
/*  14 */   private static final ResourceLocation RES_NORMAL_SINGLE = new ResourceLocation("textures/entity/chest/normal.png");
/*     */   
/*  16 */   private static final ResourceLocation RES_COPPER_SINGLE = new ResourceLocation("textures/entity/chest/copper_single.png");
/*  17 */   private static final ResourceLocation RES_SILVER_SINGLE = new ResourceLocation("textures/entity/chest/silver_single.png");
/*  18 */   private static final ResourceLocation RES_GOLD_SINGLE = new ResourceLocation("textures/entity/chest/gold_single.png");
/*  19 */   private static final ResourceLocation RES_IRON_SINGLE = new ResourceLocation("textures/entity/chest/iron_single.png");
/*  20 */   private static final ResourceLocation RES_MITHRIL_SINGLE = new ResourceLocation("textures/entity/chest/mithril_single.png");
/*  21 */   private static final ResourceLocation RES_ADAMANTIUM_SINGLE = new ResourceLocation("textures/entity/chest/adamantium_single.png");
/*  22 */   private static final ResourceLocation RES_ANCIENT_METAL_SINGLE = new ResourceLocation("textures/entity/chest/ancient_metal_single.png");
/*     */ 
/*     */   
/*  25 */   private ModelChest chestModel = new ModelChest();
/*     */ 
/*     */   
/*  28 */   private ModelChest largeChestModel = new ModelLargeChest();
/*     */ 
/*     */   
/*     */   private boolean isChristmas;
/*     */ 
/*     */   
/*     */   public TileEntityChestRenderer() {
/*  35 */     Calendar var1 = Calendar.getInstance();
/*     */     
/*  37 */     if (var1.get(2) + 1 == 12 && var1.get(5) >= 24 && var1.get(5) <= 26)
/*     */     {
/*  39 */       this.isChristmas = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderTileEntityChestAt(TileEntityChest par1TileEntityChest, double par2, double par4, double par6, float par8) {
/*     */     int var9;
/*  48 */     if (par1TileEntityChest.hasWorldObj() && par1TileEntityChest.getBlockType() == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  53 */     if (!par1TileEntityChest.hasWorldObj()) {
/*     */       
/*  55 */       var9 = 0;
/*     */     }
/*     */     else {
/*     */       
/*  59 */       Block var10 = par1TileEntityChest.getBlockType();
/*  60 */       var9 = par1TileEntityChest.getBlockMetadata();
/*     */ 
/*     */       
/*  63 */       if (var10 instanceof BlockChest && var9 == 0 && !(var10 instanceof BlockStrongbox)) {
/*     */         
/*  65 */         ((BlockChest)var10).tryAlignWithNeighboringChest(par1TileEntityChest.getWorldObj(), par1TileEntityChest.xCoord, par1TileEntityChest.yCoord, par1TileEntityChest.zCoord);
/*  66 */         var9 = par1TileEntityChest.getBlockMetadata();
/*     */       } 
/*     */       
/*  69 */       par1TileEntityChest.checkForAdjacentChests();
/*     */     } 
/*     */     
/*  72 */     if (par1TileEntityChest.adjacentChestZNeg == null && par1TileEntityChest.adjacentChestXNeg == null) {
/*     */       ModelChest var14;
/*     */ 
/*     */       
/*  76 */       if (par1TileEntityChest.adjacentChestXPos == null && par1TileEntityChest.adjacentChestZPosition == null) {
/*     */         
/*  78 */         var14 = this.chestModel;
/*     */ 
/*     */         
/*  81 */         if (par1TileEntityChest.getChestType() == EnumChestType.trapped) {
/*     */           
/*  83 */           bindTexture(RES_TRAPPED_SINGLE);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  95 */           Material material = par1TileEntityChest.getBlockMaterial();
/*     */           
/*  97 */           if (material == Material.copper) {
/*  98 */             bindTexture(RES_COPPER_SINGLE);
/*  99 */           } else if (material == Material.silver) {
/* 100 */             bindTexture(RES_SILVER_SINGLE);
/* 101 */           } else if (material == Material.gold) {
/* 102 */             bindTexture(RES_GOLD_SINGLE);
/* 103 */           } else if (material == Material.iron) {
/* 104 */             bindTexture(RES_IRON_SINGLE);
/* 105 */           } else if (material == Material.mithril) {
/* 106 */             bindTexture(RES_MITHRIL_SINGLE);
/* 107 */           } else if (material == Material.adamantium) {
/* 108 */             bindTexture(RES_ADAMANTIUM_SINGLE);
/* 109 */           } else if (material == Material.ancient_metal) {
/* 110 */             bindTexture(RES_ANCIENT_METAL_SINGLE);
/*     */           } else {
/* 112 */             bindTexture(this.isChristmas ? RES_CHRISTMAS_SINGLE : RES_NORMAL_SINGLE);
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         
/* 117 */         var14 = this.largeChestModel;
/*     */ 
/*     */         
/* 120 */         if (par1TileEntityChest.getChestType() == EnumChestType.trapped) {
/*     */           
/* 122 */           bindTexture(RES_TRAPPED_DOUBLE);
/*     */         }
/* 124 */         else if (this.isChristmas) {
/*     */           
/* 126 */           bindTexture(RES_CHRISTMAS_DOUBLE);
/*     */         }
/*     */         else {
/*     */           
/* 130 */           bindTexture(RES_NORMAL_DOUBLE);
/*     */         } 
/*     */       } 
/*     */       
/* 134 */       GL11.glPushMatrix();
/* 135 */       GL11.glEnable(32826);
/* 136 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 137 */       GL11.glTranslatef((float)par2, (float)par4 + 1.0F, (float)par6 + 1.0F);
/* 138 */       GL11.glScalef(1.0F, -1.0F, -1.0F);
/* 139 */       GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/* 140 */       short var11 = 0;
/*     */       
/* 142 */       if (var9 == 2)
/*     */       {
/* 144 */         var11 = 180;
/*     */       }
/*     */       
/* 147 */       if (var9 == 3)
/*     */       {
/* 149 */         var11 = 0;
/*     */       }
/*     */       
/* 152 */       if (var9 == 4)
/*     */       {
/* 154 */         var11 = 90;
/*     */       }
/*     */       
/* 157 */       if (var9 == 5)
/*     */       {
/* 159 */         var11 = -90;
/*     */       }
/*     */       
/* 162 */       if (var9 == 2 && par1TileEntityChest.adjacentChestXPos != null)
/*     */       {
/* 164 */         GL11.glTranslatef(1.0F, 0.0F, 0.0F);
/*     */       }
/*     */       
/* 167 */       if (var9 == 5 && par1TileEntityChest.adjacentChestZPosition != null)
/*     */       {
/* 169 */         GL11.glTranslatef(0.0F, 0.0F, -1.0F);
/*     */       }
/*     */       
/* 172 */       GL11.glRotatef(var11, 0.0F, 1.0F, 0.0F);
/* 173 */       GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 174 */       float var12 = par1TileEntityChest.prevLidAngle + (par1TileEntityChest.lidAngle - par1TileEntityChest.prevLidAngle) * par8;
/*     */ 
/*     */       
/* 177 */       if (par1TileEntityChest.adjacentChestZNeg != null) {
/*     */         
/* 179 */         float var13 = par1TileEntityChest.adjacentChestZNeg.prevLidAngle + (par1TileEntityChest.adjacentChestZNeg.lidAngle - par1TileEntityChest.adjacentChestZNeg.prevLidAngle) * par8;
/*     */         
/* 181 */         if (var13 > var12)
/*     */         {
/* 183 */           var12 = var13;
/*     */         }
/*     */       } 
/*     */       
/* 187 */       if (par1TileEntityChest.adjacentChestXNeg != null) {
/*     */         
/* 189 */         float var13 = par1TileEntityChest.adjacentChestXNeg.prevLidAngle + (par1TileEntityChest.adjacentChestXNeg.lidAngle - par1TileEntityChest.adjacentChestXNeg.prevLidAngle) * par8;
/*     */         
/* 191 */         if (var13 > var12)
/*     */         {
/* 193 */           var12 = var13;
/*     */         }
/*     */       } 
/*     */       
/* 197 */       var12 = 1.0F - var12;
/* 198 */       var12 = 1.0F - var12 * var12 * var12;
/* 199 */       var14.chestLid.rotateAngleX = -(var12 * 3.1415927F / 2.0F);
/* 200 */       var14.renderAll();
/* 201 */       GL11.glDisable(32826);
/* 202 */       GL11.glPopMatrix();
/* 203 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
/* 209 */     renderTileEntityChestAt((TileEntityChest)par1TileEntity, par2, par4, par6, par8);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityChestRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */