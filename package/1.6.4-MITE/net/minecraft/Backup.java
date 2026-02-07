/*    */ package net.minecraft;
/*    */ 
/*    */ import argo.jdom.JsonNode;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ public class Backup
/*    */   extends ValueObject
/*    */ {
/*    */   public String field_110727_a;
/*    */   public Date field_110725_b;
/*    */   public long field_110726_c;
/*    */   
/*    */   public static Backup func_110724_a(JsonNode jsonNode) {
/* 15 */     Backup backup = new Backup();
/*    */     try {
/* 17 */       backup.field_110727_a = jsonNode.getStringValue(new Object[] { "backupId" });
/* 18 */       backup.field_110725_b = new Date(Long.parseLong(jsonNode.getNumberValue(new Object[] { "lastModifiedDate" })));
/* 19 */       backup.field_110726_c = Long.parseLong(jsonNode.getNumberValue(new Object[] { "size" }));
/* 20 */     } catch (IllegalArgumentException illegalArgumentException) {}
/* 21 */     return backup;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Backup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */