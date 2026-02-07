/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class GuiBrewingStand
/*    */   extends GuiContainer {
/*  7 */   private static final ResourceLocation brewingStandGuiTextures = new ResourceLocation("textures/gui/container/brewing_stand.png");
/*    */ 
/*    */   
/*    */   private TileEntityBrewingStand brewingStand;
/*    */ 
/*    */   
/*    */   public GuiBrewingStand(EntityPlayer player, TileEntityBrewingStand par2TileEntityBrewingStand) {
/* 14 */     super(new ContainerBrewingStand(player, par2TileEntityBrewingStand));
/* 15 */     this.brewingStand = par2TileEntityBrewingStand;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
/* 23 */     String var3 = this.brewingStand.hasCustomName() ? this.brewingStand.getCustomNameOrUnlocalized() : I18n.getString(this.brewingStand.getCustomNameOrUnlocalized());
/*    */ 
/*    */ 
/*    */     
/* 27 */     this.fontRenderer.drawString(var3, this.xSize / 2 - this.fontRenderer.getStringWidth(var3) / 2, 6, 2631720);
/* 28 */     this.fontRenderer.drawString(I18n.getString("container.inventory"), 7, this.ySize - 96 + 3, 2631720);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
/* 36 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 37 */     this.mc.getTextureManager().bindTexture(brewingStandGuiTextures);
/* 38 */     int var4 = (this.width - this.xSize) / 2;
/* 39 */     int var5 = (this.height - this.ySize) / 2;
/* 40 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
/* 41 */     int var6 = this.brewingStand.getBrewTime();
/*    */     
/* 43 */     if (var6 > 0) {
/*    */       
/* 45 */       int var7 = (int)(28.0F * (1.0F - var6 / 400.0F));
/*    */       
/* 47 */       if (var7 > 0)
/*    */       {
/* 49 */         drawTexturedModalRect(var4 + 97, var5 + 16, 176, 0, 9, var7);
/*    */       }
/*    */       
/* 52 */       int var8 = var6 / 2 % 7;
/*    */       
/* 54 */       switch (var8) {
/*    */         
/*    */         case 0:
/* 57 */           var7 = 29;
/*    */           break;
/*    */         
/*    */         case 1:
/* 61 */           var7 = 24;
/*    */           break;
/*    */         
/*    */         case 2:
/* 65 */           var7 = 20;
/*    */           break;
/*    */         
/*    */         case 3:
/* 69 */           var7 = 16;
/*    */           break;
/*    */         
/*    */         case 4:
/* 73 */           var7 = 11;
/*    */           break;
/*    */         
/*    */         case 5:
/* 77 */           var7 = 6;
/*    */           break;
/*    */         
/*    */         case 6:
/* 81 */           var7 = 0;
/*    */           break;
/*    */       } 
/* 84 */       if (var7 > 0)
/*    */       {
/* 86 */         drawTexturedModalRect(var4 + 65, var5 + 14 + 29 - var7, 185, 29 - var7, 12, var7);
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiBrewingStand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */