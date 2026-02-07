/*    */ package net.minecraft;
/*    */ 
/*    */ import argo.jdom.JdomParser;
/*    */ import argo.jdom.JsonNode;
/*    */ import argo.jdom.JsonRootNode;
/*    */ import argo.saj.InvalidSyntaxException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class BackupList
/*    */ {
/*    */   public List field_111223_a;
/*    */   
/*    */   public static BackupList func_111222_a(String string) {
/* 16 */     BackupList backupList = new BackupList();
/* 17 */     backupList.field_111223_a = new ArrayList();
/*    */     
/* 19 */     try { JsonRootNode jsonRootNode = (new JdomParser()).parse(string);
/* 20 */       if (jsonRootNode.isArrayNode(new Object[] { "backups" })) {
/* 21 */         for (JsonNode jsonNode : jsonRootNode.getArrayNode(new Object[] { "backups" })) {
/* 22 */           backupList.field_111223_a.add(Backup.func_110724_a(jsonNode));
/*    */         }
/*    */       } }
/* 25 */     catch (InvalidSyntaxException invalidSyntaxException) {  }
/* 26 */     catch (IllegalArgumentException illegalArgumentException) {}
/* 27 */     return backupList;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BackupList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */