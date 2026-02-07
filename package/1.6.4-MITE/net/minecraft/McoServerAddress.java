/*    */ package net.minecraft;
/*    */ 
/*    */ import argo.jdom.JdomParser;
/*    */ import argo.jdom.JsonRootNode;
/*    */ import argo.saj.InvalidSyntaxException;
/*    */ 
/*    */ public class McoServerAddress extends ValueObject {
/*    */   public String field_96417_a;
/*    */   
/*    */   public static McoServerAddress func_98162_a(String string) {
/* 11 */     McoServerAddress mcoServerAddress = new McoServerAddress();
/*    */     try {
/* 13 */       JsonRootNode jsonRootNode = (new JdomParser()).parse(string);
/* 14 */       mcoServerAddress.field_96417_a = jsonRootNode.getStringValue(new Object[] { "address" });
/* 15 */     } catch (InvalidSyntaxException invalidSyntaxException) {
/*    */     
/* 17 */     } catch (IllegalArgumentException illegalArgumentException) {}
/*    */     
/* 19 */     return mcoServerAddress;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\McoServerAddress.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */