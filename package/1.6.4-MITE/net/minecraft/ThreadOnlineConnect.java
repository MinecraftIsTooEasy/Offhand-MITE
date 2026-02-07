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
/*    */ class ThreadOnlineConnect
/*    */   extends Thread
/*    */ {
/*    */   ThreadOnlineConnect(TaskOnlineConnect taskOnlineConnect, String string, int i) {}
/*    */   
/*    */   public void run() {
/*    */     try {
/* 85 */       TaskOnlineConnect.func_96583_a(this.field_96594_c, new NetClientHandler(this.field_96594_c.getMinecraft(), this.field_96595_a, this.field_96593_b, TaskOnlineConnect.func_98172_a(this.field_96594_c)));
/* 86 */       if (this.field_96594_c.wasScreenClosed())
/* 87 */         return;  this.field_96594_c.setMessage(I18n.getString("mco.connect.authorizing"));
/* 88 */       TaskOnlineConnect.func_96580_a(this.field_96594_c).addToSendQueue(new Packet2ClientProtocol(78, this.field_96594_c.getMinecraft().getSession().getUsername(), this.field_96595_a, this.field_96593_b));
/* 89 */     } catch (UnknownHostException unknownHostException) {
/* 90 */       if (this.field_96594_c.wasScreenClosed())
/* 91 */         return;  this.field_96594_c.getMinecraft().displayGuiScreen(new GuiScreenDisconnectedOnline(TaskOnlineConnect.func_98172_a(this.field_96594_c), "connect.failed", "disconnect.genericReason", new Object[] { "Unknown host '" + this.field_96595_a + "'" }));
/* 92 */     } catch (ConnectException connectException) {
/* 93 */       if (this.field_96594_c.wasScreenClosed())
/* 94 */         return;  this.field_96594_c.getMinecraft().displayGuiScreen(new GuiScreenDisconnectedOnline(TaskOnlineConnect.func_98172_a(this.field_96594_c), "connect.failed", "disconnect.genericReason", new Object[] { connectException.getMessage() }));
/* 95 */     } catch (Exception exception) {
/* 96 */       if (this.field_96594_c.wasScreenClosed())
/* 97 */         return;  exception.printStackTrace();
/* 98 */       this.field_96594_c.getMinecraft().displayGuiScreen(new GuiScreenDisconnectedOnline(TaskOnlineConnect.func_98172_a(this.field_96594_c), "connect.failed", "disconnect.genericReason", new Object[] { exception.toString() }));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ThreadOnlineConnect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */