/*    */ package net.minecraft;
/*    */ 
/*    */ import argo.jdom.JsonNode;
/*    */ 
/*    */ public class PendingInvite
/*    */   extends ValueObject {
/*    */   public String field_130094_a;
/*    */   public String field_130092_b;
/*    */   public String field_130093_c;
/*    */   
/*    */   public static PendingInvite func_130091_a(JsonNode jsonNode) {
/* 12 */     PendingInvite pendingInvite = new PendingInvite();
/*    */     try {
/* 14 */       pendingInvite.field_130094_a = jsonNode.getStringValue(new Object[] { "invitationId" });
/* 15 */       pendingInvite.field_130092_b = jsonNode.getStringValue(new Object[] { "worldName" });
/* 16 */       pendingInvite.field_130093_c = jsonNode.getStringValue(new Object[] { "worldOwnerName" });
/* 17 */     } catch (Exception exception) {}
/* 18 */     return pendingInvite;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PendingInvite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */