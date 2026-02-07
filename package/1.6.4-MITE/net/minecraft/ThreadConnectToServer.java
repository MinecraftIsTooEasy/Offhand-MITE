/*    */ package net.minecraft;
/*    */ 
/*    */ import java.net.ConnectException;
/*    */ import java.net.UnknownHostException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class ThreadConnectToServer
/*    */   extends Thread
/*    */ {
/*    */   ThreadConnectToServer(GuiConnecting guiConnecting, String string, int i) {}
/*    */   
/*    */   public void run() {
/*    */     try {
/* 42 */       GuiConnecting.setNetClientHandler(this.connectingGui, new NetClientHandler(GuiConnecting.func_74256_a(this.connectingGui), this.ip, this.port));
/* 43 */       if (GuiConnecting.isCancelled(this.connectingGui))
/* 44 */         return;  GuiConnecting.getNetClientHandler(this.connectingGui).addToSendQueue(new Packet2ClientProtocol(78, GuiConnecting.func_74254_c(this.connectingGui).getSession().getUsername(), this.ip, this.port));
/* 45 */     } catch (UnknownHostException unknownHostException) {
/* 46 */       if (GuiConnecting.isCancelled(this.connectingGui))
/* 47 */         return;  GuiConnecting.func_74250_f(this.connectingGui).displayGuiScreen(new GuiDisconnected(GuiConnecting.func_98097_e(this.connectingGui), "connect.failed", "disconnect.genericReason", new Object[] { "Unknown host '" + this.ip + "'" }));
/* 48 */     } catch (ConnectException connectException) {
/* 49 */       if (GuiConnecting.isCancelled(this.connectingGui))
/* 50 */         return;  GuiConnecting.func_74251_g(this.connectingGui).displayGuiScreen(new GuiDisconnected(GuiConnecting.func_98097_e(this.connectingGui), "connect.failed", "disconnect.genericReason", new Object[] { connectException.getMessage() }));
/* 51 */     } catch (Exception exception) {
/* 52 */       if (GuiConnecting.isCancelled(this.connectingGui))
/* 53 */         return;  exception.printStackTrace();
/* 54 */       GuiConnecting.func_98096_h(this.connectingGui).displayGuiScreen(new GuiDisconnected(GuiConnecting.func_98097_e(this.connectingGui), "connect.failed", "disconnect.genericReason", new Object[] { exception.toString() }));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ThreadConnectToServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */