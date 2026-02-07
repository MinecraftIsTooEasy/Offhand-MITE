/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ class GuiScreenTemporaryResourcePackSelectSelectionList
/*     */   extends GuiSlot
/*     */ {
/*     */   private final ResourcePackRepository field_110511_b;
/*     */   private ResourceLocation field_110513_h;
/*     */   final GuiScreenTemporaryResourcePackSelect field_110512_a;
/*     */   
/*     */   public GuiScreenTemporaryResourcePackSelectSelectionList(GuiScreenTemporaryResourcePackSelect par1GuiScreenTemporaryResourcePackSelect, ResourcePackRepository par2ResourcePackRepository) {
/*  16 */     super(GuiScreenTemporaryResourcePackSelect.func_110344_a(par1GuiScreenTemporaryResourcePackSelect), par1GuiScreenTemporaryResourcePackSelect.width, par1GuiScreenTemporaryResourcePackSelect.height, 32, par1GuiScreenTemporaryResourcePackSelect.height - 55 + 4, 36);
/*  17 */     this.field_110512_a = par1GuiScreenTemporaryResourcePackSelect;
/*  18 */     this.field_110511_b = par2ResourcePackRepository;
/*  19 */     par2ResourcePackRepository.updateRepositoryEntriesAll();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getSize() {
/*  27 */     return 1 + this.field_110511_b.getRepositoryEntriesAll().size();
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
/*     */   protected void elementClicked(int par1, boolean par2) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isSelected(int par1) {
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getContentHeight() {
/*  73 */     return getSize() * 36;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawBackground() {
/*  78 */     this.field_110512_a.drawDefaultBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator) {
/*  83 */     TextureManager var6 = GuiScreenTemporaryResourcePackSelect.func_110340_f(this.field_110512_a).getTextureManager();
/*     */     
/*  85 */     if (par1 == 0) {
/*     */ 
/*     */       
/*     */       try {
/*  89 */         ResourcePack var12 = this.field_110511_b.rprDefaultResourcePack;
/*  90 */         PackMetadataSection var13 = (PackMetadataSection)var12.getPackMetadata(this.field_110511_b.rprMetadataSerializer, "pack");
/*     */         
/*  92 */         if (this.field_110513_h == null)
/*     */         {
/*  94 */           this.field_110513_h = var6.getDynamicTextureLocation("texturepackicon", new DynamicTexture(var12.getPackImage()));
/*     */         }
/*     */         
/*  97 */         var6.bindTexture(this.field_110513_h);
/*  98 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  99 */         par5Tessellator.startDrawingQuads();
/* 100 */         par5Tessellator.setColorOpaque_I(16777215);
/* 101 */         par5Tessellator.addVertexWithUV(par2, (par3 + par4), 0.0D, 0.0D, 1.0D);
/* 102 */         par5Tessellator.addVertexWithUV((par2 + 32), (par3 + par4), 0.0D, 1.0D, 1.0D);
/* 103 */         par5Tessellator.addVertexWithUV((par2 + 32), par3, 0.0D, 1.0D, 0.0D);
/* 104 */         par5Tessellator.addVertexWithUV(par2, par3, 0.0D, 0.0D, 0.0D);
/* 105 */         par5Tessellator.draw();
/* 106 */         this.field_110512_a.drawString(GuiScreenTemporaryResourcePackSelect.func_130017_g(this.field_110512_a), "Default", par2 + 32 + 2, par3 + 1, 16777215);
/* 107 */         this.field_110512_a.drawString(GuiScreenTemporaryResourcePackSelect.func_130016_h(this.field_110512_a), var13.getPackDescription(), par2 + 32 + 2, par3 + 12 + 10, 8421504);
/*     */       }
/* 109 */       catch (IOException var11) {}
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 116 */       ResourcePackRepositoryEntry var7 = this.field_110511_b.getRepositoryEntriesAll().get(par1 - 1);
/* 117 */       var7.bindTexturePackIcon(var6);
/* 118 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 119 */       par5Tessellator.startDrawingQuads();
/* 120 */       par5Tessellator.setColorOpaque_I(16777215);
/* 121 */       par5Tessellator.addVertexWithUV(par2, (par3 + par4), 0.0D, 0.0D, 1.0D);
/* 122 */       par5Tessellator.addVertexWithUV((par2 + 32), (par3 + par4), 0.0D, 1.0D, 1.0D);
/* 123 */       par5Tessellator.addVertexWithUV((par2 + 32), par3, 0.0D, 1.0D, 0.0D);
/* 124 */       par5Tessellator.addVertexWithUV(par2, par3, 0.0D, 0.0D, 0.0D);
/* 125 */       par5Tessellator.draw();
/* 126 */       String var8 = var7.getResourcePackName();
/*     */       
/* 128 */       if (var8.length() > 32)
/*     */       {
/* 130 */         var8 = var8.substring(0, 32).trim() + "...";
/*     */       }
/*     */       
/* 133 */       this.field_110512_a.drawString(GuiScreenTemporaryResourcePackSelect.func_110337_i(this.field_110512_a), var8, par2 + 32 + 2, par3 + 1, 16777215);
/* 134 */       List<String> var9 = GuiScreenTemporaryResourcePackSelect.func_110335_j(this.field_110512_a).listFormattedStringToWidth(var7.getTexturePackDescription(), 183);
/*     */       
/* 136 */       for (int var10 = 0; var10 < 2 && var10 < var9.size(); var10++)
/*     */       {
/* 138 */         this.field_110512_a.drawString(GuiScreenTemporaryResourcePackSelect.func_110338_k(this.field_110512_a), var9.get(var10), par2 + 32 + 2, par3 + 12 + 10 * var10, 8421504);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static ResourcePackRepository func_110510_a(GuiScreenTemporaryResourcePackSelectSelectionList par0GuiScreenTemporaryResourcePackSelectSelectionList) {
/* 145 */     return par0GuiScreenTemporaryResourcePackSelectSelectionList.field_110511_b;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenTemporaryResourcePackSelectSelectionList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */