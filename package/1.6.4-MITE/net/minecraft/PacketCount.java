/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class PacketCount
/*    */ {
/*    */   public static boolean allowCounting = true;
/* 10 */   private static final Map packetCountForID = new HashMap<Object, Object>();
/* 11 */   private static final Map sizeCountForID = new HashMap<Object, Object>();
/*    */   
/* 13 */   private static final Object lock = new Object();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void countPacket(int i, long l) {
/* 44 */     if (!allowCounting)
/*    */       return; 
/* 46 */     synchronized (lock) {
/*    */       
/* 48 */       if (packetCountForID.containsKey(Integer.valueOf(i))) {
/* 49 */         packetCountForID.put(Integer.valueOf(i), Long.valueOf(((Long)packetCountForID.get(Integer.valueOf(i))).longValue() + 1L));
/* 50 */         sizeCountForID.put(Integer.valueOf(i), Long.valueOf(((Long)sizeCountForID.get(Integer.valueOf(i))).longValue() + l));
/*    */       } else {
/* 52 */         packetCountForID.put(Integer.valueOf(i), Long.valueOf(1L));
/* 53 */         sizeCountForID.put(Integer.valueOf(i), Long.valueOf(l));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PacketCount.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */