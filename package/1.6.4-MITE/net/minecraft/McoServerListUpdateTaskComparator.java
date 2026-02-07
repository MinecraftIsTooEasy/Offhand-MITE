/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class McoServerListUpdateTaskComparator
/*     */   implements Comparator
/*     */ {
/*     */   private final String field_140069_b;
/*     */   
/*     */   private McoServerListUpdateTaskComparator(McoServerListUpdateTask mcoServerListUpdateTask, String string) {
/* 166 */     this.field_140069_b = string;
/*     */   }
/*     */   
/*     */   public int func_140068_a(McoServer mcoServer, McoServer mcoServer2) {
/* 170 */     if (mcoServer.field_96405_e.equals(mcoServer2.field_96405_e)) {
/* 171 */       if (mcoServer.field_96408_a < mcoServer2.field_96408_a) return 1; 
/* 172 */       if (mcoServer.field_96408_a > mcoServer2.field_96408_a) return -1; 
/* 173 */       return 0;
/* 174 */     }  if (mcoServer.field_96405_e.equals(this.field_140069_b)) return -1; 
/* 175 */     if (mcoServer2.field_96405_e.equals(this.field_140069_b)) return 1;
/*     */     
/* 177 */     if (mcoServer.field_96404_d.equals("CLOSED") || mcoServer2.field_96404_d.equals("CLOSED")) {
/* 178 */       if (mcoServer.field_96404_d.equals("CLOSED")) return 1; 
/* 179 */       if (mcoServer2.field_96404_d.equals("CLOSED")) return 0; 
/*     */     } 
/* 181 */     if (mcoServer.field_96408_a < mcoServer2.field_96408_a) return 1; 
/* 182 */     if (mcoServer.field_96408_a > mcoServer2.field_96408_a) return -1; 
/* 183 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\McoServerListUpdateTaskComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */