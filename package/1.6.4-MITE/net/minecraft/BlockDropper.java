/*    */ package net.minecraft;
/*    */ 
/*    */ public class BlockDropper
/*    */   extends BlockDispenser {
/*  5 */   private final IBehaviorDispenseItem dropperDefaultBehaviour = new BehaviorDefaultDispenseItem();
/*    */ 
/*    */   
/*    */   protected BlockDropper(int par1) {
/*  9 */     super(par1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerIcons(IconRegister par1IconRegister) {
/* 18 */     this.blockIcon = par1IconRegister.registerIcon("furnace_side");
/* 19 */     this.furnaceTopIcon = par1IconRegister.registerIcon("furnace_top");
/* 20 */     this.furnaceFrontIcon = par1IconRegister.registerIcon(getTextureName() + "_front_horizontal");
/* 21 */     this.field_96473_e = par1IconRegister.registerIcon(getTextureName() + "_front_vertical");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected IBehaviorDispenseItem getBehaviorForItemStack(ItemStack par1ItemStack) {
/* 29 */     return this.dropperDefaultBehaviour;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TileEntity createNewTileEntity(World par1World) {
/* 37 */     return new TileEntityDropper();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void dispense(World par1World, int par2, int par3, int par4) {
/* 42 */     BlockSourceImpl var5 = new BlockSourceImpl(par1World, par2, par3, par4);
/* 43 */     TileEntityDispenser var6 = (TileEntityDispenser)var5.getBlockTileEntity();
/*    */     
/* 45 */     if (var6 != null) {
/*    */       
/* 47 */       int var7 = var6.getRandomStackFromInventory();
/*    */       
/* 49 */       if (var7 < 0) {
/*    */         
/* 51 */         par1World.playAuxSFX(1001, par2, par3, par4, 0);
/*    */       }
/*    */       else {
/*    */         
/* 55 */         ItemStack var11, var8 = var6.getStackInSlot(var7);
/* 56 */         int var9 = par1World.getBlockMetadata(par2, par3, par4) & 0x7;
/* 57 */         IInventory var10 = TileEntityHopper.getInventoryAtLocation(par1World, (par2 + Facing.offsetsXForSide[var9]), (par3 + Facing.offsetsYForSide[var9]), (par4 + Facing.offsetsZForSide[var9]));
/*    */ 
/*    */         
/* 60 */         if (var10 != null) {
/*    */           
/* 62 */           var11 = TileEntityHopper.insertStack(var10, var8.copy().splitStack(1), Facing.oppositeSide[var9]);
/*    */           
/* 64 */           if (var11 == null)
/*    */           {
/* 66 */             var11 = var8.copy();
/*    */             
/* 68 */             if (--var11.stackSize == 0)
/*    */             {
/* 70 */               var11 = null;
/*    */             }
/*    */           }
/*    */           else
/*    */           {
/* 75 */             var11 = var8.copy();
/*    */           }
/*    */         
/*    */         } else {
/*    */           
/* 80 */           var11 = this.dropperDefaultBehaviour.dispense(var5, var8);
/*    */           
/* 82 */           if (var11 != null && var11.stackSize == 0)
/*    */           {
/* 84 */             var11 = null;
/*    */           }
/*    */         } 
/*    */         
/* 88 */         var6.setInventorySlotContents(var7, var11);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean playerSwingsOnBlockActivated(boolean empty_handed) {
/* 95 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockDropper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */