/*    */ package net.minecraft;
/*    */ 
/*    */ import argo.jdom.JsonNode;
/*    */ 
/*    */ public class WorldTemplate
/*    */   extends ValueObject {
/*    */   public String field_110734_a;
/*    */   public String field_110732_b;
/*    */   public String field_110733_c;
/*    */   public String field_110731_d;
/*    */   
/*    */   public static WorldTemplate func_110730_a(JsonNode jsonNode) {
/* 13 */     WorldTemplate worldTemplate = new WorldTemplate();
/*    */     try {
/* 15 */       worldTemplate.field_110734_a = jsonNode.getNumberValue(new Object[] { "id" });
/* 16 */       worldTemplate.field_110732_b = jsonNode.getStringValue(new Object[] { "name" });
/* 17 */       worldTemplate.field_110733_c = jsonNode.getStringValue(new Object[] { "version" });
/* 18 */       worldTemplate.field_110731_d = jsonNode.getStringValue(new Object[] { "author" });
/* 19 */     } catch (IllegalArgumentException illegalArgumentException) {}
/* 20 */     return worldTemplate;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldTemplate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */