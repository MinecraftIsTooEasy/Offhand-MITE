/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet80LongDistanceSound
/*    */   extends Packet62LevelSound
/*    */ {
/*    */   public Packet80LongDistanceSound() {}
/*    */   
/*    */   public Packet80LongDistanceSound(String par1Str, double par2, double par4, double par6, float par8, float par9) {
/* 11 */     super(par1Str, par2, par4, par6, par8, par9);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler par1NetHandler) {
/* 16 */     par1NetHandler.handleLongDistanceSound(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet80LongDistanceSound.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */