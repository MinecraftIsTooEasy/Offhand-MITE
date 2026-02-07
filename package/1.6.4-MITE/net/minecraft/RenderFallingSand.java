/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class RenderFallingSand
/*     */   extends Render {
/*   7 */   private final RenderBlocks sandRenderBlocks = new RenderBlocks();
/*     */ 
/*     */   
/*     */   public RenderFallingSand() {
/*  11 */     this.shadowSize = 0.5F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRenderFallingSand(EntityFallingSand par1EntityFallingSand, double par2, double par4, double par6, float par8, float par9) {
/*  20 */     World var10 = par1EntityFallingSand.worldObj;
/*  21 */     Block var11 = Block.blocksList[par1EntityFallingSand.blockID];
/*     */     
/*  23 */     if (var10.getBlockId(MathHelper.floor_double(par1EntityFallingSand.posX), MathHelper.floor_double(par1EntityFallingSand.posY), MathHelper.floor_double(par1EntityFallingSand.posZ)) != par1EntityFallingSand.blockID) {
/*     */       
/*  25 */       GL11.glPushMatrix();
/*  26 */       GL11.glTranslatef((float)par2, (float)par4, (float)par6);
/*  27 */       bindEntityTexture(par1EntityFallingSand);
/*  28 */       GL11.glDisable(2896);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  36 */       int x = par1EntityFallingSand.getBlockPosX();
/*  37 */       int y = par1EntityFallingSand.getBlockPosY();
/*  38 */       int z = par1EntityFallingSand.getBlockPosZ();
/*     */       
/*  40 */       int previous_metadata = var10.getBlockMetadata(x, y, z);
/*  41 */       var10.setBlockMetadataWithNotify(x, y, z, par1EntityFallingSand.metadata, 4);
/*     */ 
/*     */ 
/*     */       
/*  45 */       if (var11 instanceof BlockAnvil && var11.getRenderType() == 35) {
/*     */         
/*  47 */         this.sandRenderBlocks.blockAccess = var10;
/*  48 */         Tessellator var12 = Tessellator.instance;
/*  49 */         var12.startDrawingQuads();
/*  50 */         var12.setTranslation((-MathHelper.floor_double(par1EntityFallingSand.posX) - 0.5F), (-MathHelper.floor_double(par1EntityFallingSand.posY) - 0.5F), (-MathHelper.floor_double(par1EntityFallingSand.posZ) - 0.5F));
/*  51 */         this.sandRenderBlocks.renderBlockAnvilMetadata((BlockAnvil)var11, MathHelper.floor_double(par1EntityFallingSand.posX), MathHelper.floor_double(par1EntityFallingSand.posY), MathHelper.floor_double(par1EntityFallingSand.posZ), par1EntityFallingSand.metadata);
/*  52 */         var12.setTranslation(0.0D, 0.0D, 0.0D);
/*  53 */         var12.draw();
/*     */       }
/*  55 */       else if (var11.getRenderType() == 27) {
/*     */         
/*  57 */         this.sandRenderBlocks.blockAccess = var10;
/*  58 */         Tessellator var12 = Tessellator.instance;
/*  59 */         var12.startDrawingQuads();
/*  60 */         var12.setTranslation((-MathHelper.floor_double(par1EntityFallingSand.posX) - 0.5F), (-MathHelper.floor_double(par1EntityFallingSand.posY) - 0.5F), (-MathHelper.floor_double(par1EntityFallingSand.posZ) - 0.5F));
/*  61 */         this.sandRenderBlocks.renderBlockDragonEgg((BlockDragonEgg)var11, MathHelper.floor_double(par1EntityFallingSand.posX), MathHelper.floor_double(par1EntityFallingSand.posY), MathHelper.floor_double(par1EntityFallingSand.posZ));
/*  62 */         var12.setTranslation(0.0D, 0.0D, 0.0D);
/*  63 */         var12.draw();
/*     */       }
/*  65 */       else if (var11 != null) {
/*     */ 
/*     */ 
/*     */         
/*  69 */         if (var11.isAlwaysStandardFormCube()) {
/*  70 */           this.sandRenderBlocks.setRenderBoundsForStandardFormBlock();
/*     */         } else {
/*  72 */           this.sandRenderBlocks.setRenderBoundsForNonStandardFormBlock(var11);
/*     */         } 
/*  74 */         this.sandRenderBlocks.renderBlockSandFalling(var11, var10, MathHelper.floor_double(par1EntityFallingSand.posX), MathHelper.floor_double(par1EntityFallingSand.posY), MathHelper.floor_double(par1EntityFallingSand.posZ), par1EntityFallingSand.metadata);
/*     */       } 
/*     */       
/*  77 */       var10.setBlockMetadataWithNotify(x, y, z, previous_metadata, 4);
/*     */       
/*  79 */       GL11.glEnable(2896);
/*  80 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation getFallingSandTextures(EntityFallingSand par1EntityFallingSand) {
/*  86 */     return TextureMap.locationBlocksTexture;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/*  94 */     return getFallingSandTextures((EntityFallingSand)par1Entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
/* 105 */     doRenderFallingSand((EntityFallingSand)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderFallingSand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */