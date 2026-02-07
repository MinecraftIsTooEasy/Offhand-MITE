/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import javax.swing.JList;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ public class PlayerListComponentMITE
/*    */   extends JList
/*    */   implements IUpdatePlayerListBox
/*    */ {
/*    */   private MinecraftServer field_120015_a;
/*    */   private int field_120014_b;
/*    */   
/*    */   public PlayerListComponentMITE(MinecraftServer par1MinecraftServer) {
/* 16 */     this.field_120015_a = par1MinecraftServer;
/* 17 */     par1MinecraftServer.func_82010_a(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 25 */     if (this.field_120014_b++ % 20 == 0) {
/*    */       
/* 27 */       Vector<String> var1 = new Vector();
/*    */       
/* 29 */       for (int var2 = 0; var2 < (this.field_120015_a.getConfigurationManager()).playerEntityList.size(); var2++)
/*    */       {
/* 31 */         var1.add(((ServerPlayer)(this.field_120015_a.getConfigurationManager()).playerEntityList.get(var2)).getCommandSenderName());
/*    */       }
/*    */       
/* 34 */       setListData((Vector)var1);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PlayerListComponentMITE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */