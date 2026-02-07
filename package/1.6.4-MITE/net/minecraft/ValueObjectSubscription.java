/*    */ package net.minecraft;
/*    */ 
/*    */ import argo.jdom.JdomParser;
/*    */ import argo.jdom.JsonRootNode;
/*    */ import argo.saj.InvalidSyntaxException;
/*    */ 
/*    */ public class ValueObjectSubscription
/*    */   extends ValueObject {
/*    */   public long field_98171_a;
/*    */   public int field_98170_b;
/*    */   
/*    */   public static ValueObjectSubscription func_98169_a(String string) {
/* 13 */     ValueObjectSubscription valueObjectSubscription = new ValueObjectSubscription();
/*    */     try {
/* 15 */       JsonRootNode jsonRootNode = (new JdomParser()).parse(string);
/* 16 */       valueObjectSubscription.field_98171_a = Long.parseLong(jsonRootNode.getNumberValue(new Object[] { "startDate" }));
/* 17 */       valueObjectSubscription.field_98170_b = Integer.parseInt(jsonRootNode.getNumberValue(new Object[] { "daysLeft" }));
/* 18 */     } catch (InvalidSyntaxException invalidSyntaxException) {
/*    */     
/* 20 */     } catch (IllegalArgumentException illegalArgumentException) {}
/* 21 */     return valueObjectSubscription;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ValueObjectSubscription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */