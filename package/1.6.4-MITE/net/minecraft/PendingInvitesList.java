/*    */ package net.minecraft;
/*    */ 
/*    */ import argo.jdom.JdomParser;
/*    */ import argo.jdom.JsonNode;
/*    */ import argo.jdom.JsonRootNode;
/*    */ import argo.saj.InvalidSyntaxException;
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PendingInvitesList
/*    */   extends ValueObject
/*    */ {
/* 13 */   public List field_130096_a = Lists.newArrayList();
/*    */   
/*    */   public static PendingInvitesList func_130095_a(String string) {
/* 16 */     PendingInvitesList pendingInvitesList = new PendingInvitesList();
/*    */     try {
/* 18 */       JsonRootNode jsonRootNode = (new JdomParser()).parse(string);
/* 19 */       if (jsonRootNode.isArrayNode(new Object[] { "invites" })) {
/* 20 */         for (JsonNode jsonNode : jsonRootNode.getArrayNode(new Object[] { "invites" })) {
/* 21 */           pendingInvitesList.field_130096_a.add(PendingInvite.func_130091_a(jsonNode));
/*    */         }
/*    */       }
/* 24 */     } catch (InvalidSyntaxException invalidSyntaxException) {}
/* 25 */     return pendingInvitesList;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PendingInvitesList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */