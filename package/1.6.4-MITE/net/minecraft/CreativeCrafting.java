/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CreativeCrafting
/*    */   implements ICrafting
/*    */ {
/*    */   private final Minecraft mc;
/*    */   
/*    */   public CreativeCrafting(Minecraft minecraft) {
/* 14 */     this.mc = minecraft;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void sendContainerAndContentsToPlayer(Container container, List list) {}
/*    */ 
/*    */   
/*    */   public void sendSlotContents(Container container, int i, ItemStack itemStack) {
/* 23 */     this.mc.playerController.sendSlotPacket(itemStack, i);
/*    */   }
/*    */   
/*    */   public void sendProgressBarUpdate(Container container, int i, int j) {}
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CreativeCrafting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */