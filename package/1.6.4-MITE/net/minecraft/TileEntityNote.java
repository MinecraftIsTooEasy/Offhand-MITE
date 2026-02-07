/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileEntityNote
/*    */   extends TileEntity
/*    */ {
/*    */   public byte note;
/*    */   public boolean previousRedstoneState;
/*    */   
/*    */   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
/* 16 */     super.writeToNBT(par1NBTTagCompound);
/* 17 */     par1NBTTagCompound.setByte("note", this.note);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 25 */     super.readFromNBT(par1NBTTagCompound);
/* 26 */     this.note = par1NBTTagCompound.getByte("note");
/*    */     
/* 28 */     if (this.note < 0)
/*    */     {
/* 30 */       this.note = 0;
/*    */     }
/*    */     
/* 33 */     if (this.note > 24)
/*    */     {
/* 35 */       this.note = 24;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void changePitch() {
/* 44 */     this.note = (byte)((this.note + 1) % 25);
/* 45 */     onInventoryChanged();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void triggerNote(World par1World, int par2, int par3, int par4) {
/* 53 */     if (par1World.getBlockMaterial(par2, par3 + 1, par4) == Material.air) {
/*    */       
/* 55 */       Material var5 = par1World.getBlockMaterial(par2, par3 - 1, par4);
/* 56 */       byte var6 = 0;
/*    */ 
/*    */       
/* 59 */       if (var5 == Material.stone)
/*    */       {
/* 61 */         var6 = 1;
/*    */       }
/*    */       
/* 64 */       if (var5 == Material.sand)
/*    */       {
/* 66 */         var6 = 2;
/*    */       }
/*    */       
/* 69 */       if (var5 == Material.glass)
/*    */       {
/* 71 */         var6 = 3;
/*    */       }
/*    */       
/* 74 */       if (var5 == Material.wood)
/*    */       {
/* 76 */         var6 = 4;
/*    */       }
/*    */       
/* 79 */       par1World.addBlockEvent(par2, par3, par4, Block.music.blockID, var6, this.note);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityNote.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */