/*    */ package net.minecraft;
/*    */ 
/*    */ import argo.jdom.JdomParser;
/*    */ import argo.jdom.JsonNode;
/*    */ import argo.jdom.JsonRootNode;
/*    */ import argo.saj.InvalidSyntaxException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class WorldTemplateList
/*    */   extends ValueObject
/*    */ {
/*    */   public List field_110736_a;
/*    */   
/*    */   public static WorldTemplateList func_110735_a(String string) {
/* 16 */     WorldTemplateList worldTemplateList = new WorldTemplateList();
/* 17 */     worldTemplateList.field_110736_a = new ArrayList();
/*    */     
/* 19 */     try { JsonRootNode jsonRootNode = (new JdomParser()).parse(string);
/* 20 */       if (jsonRootNode.isArrayNode(new Object[] { "templates" })) {
/* 21 */         for (JsonNode jsonNode : jsonRootNode.getArrayNode(new Object[] { "templates" })) {
/* 22 */           worldTemplateList.field_110736_a.add(WorldTemplate.func_110730_a(jsonNode));
/*    */         }
/*    */       } }
/* 25 */     catch (InvalidSyntaxException invalidSyntaxException) {  }
/* 26 */     catch (IllegalArgumentException illegalArgumentException) {}
/* 27 */     return worldTemplateList;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldTemplateList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */