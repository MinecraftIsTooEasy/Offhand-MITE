/*    */ package net.minecraft;
/*    */ 
/*    */ import argo.jdom.JdomParser;
/*    */ import argo.jdom.JsonNode;
/*    */ import argo.jdom.JsonRootNode;
/*    */ import argo.saj.InvalidSyntaxException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ValueObjectList
/*    */   extends ValueObject
/*    */ {
/*    */   public List field_96430_d;
/*    */   
/*    */   public static ValueObjectList func_98161_a(String string) {
/* 16 */     ValueObjectList valueObjectList = new ValueObjectList();
/* 17 */     valueObjectList.field_96430_d = new ArrayList();
/*    */     
/* 19 */     try { JsonRootNode jsonRootNode = (new JdomParser()).parse(string);
/* 20 */       if (jsonRootNode.isArrayNode(new Object[] { "servers" })) {
/* 21 */         for (JsonNode jsonNode : jsonRootNode.getArrayNode(new Object[] { "servers" })) {
/* 22 */           valueObjectList.field_96430_d.add(McoServer.func_98163_a(jsonNode));
/*    */         }
/*    */       } }
/* 25 */     catch (InvalidSyntaxException invalidSyntaxException) {  }
/* 26 */     catch (IllegalArgumentException illegalArgumentException) {}
/* 27 */     return valueObjectList;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ValueObjectList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */