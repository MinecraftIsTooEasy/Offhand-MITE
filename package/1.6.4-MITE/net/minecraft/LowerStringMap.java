/*    */ package net.minecraft;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class LowerStringMap implements Map {
/*  6 */   private final Map internalMap = new LinkedHashMap<Object, Object>();
/*    */ 
/*    */   
/*    */   public int size() {
/* 10 */     return this.internalMap.size();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEmpty() {
/* 15 */     return this.internalMap.isEmpty();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean containsKey(Object object) {
/* 20 */     return this.internalMap.containsKey(object.toString().toLowerCase());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean containsValue(Object object) {
/* 25 */     return this.internalMap.containsKey(object);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object get(Object object) {
/* 30 */     return this.internalMap.get(object.toString().toLowerCase());
/*    */   }
/*    */ 
/*    */   
/*    */   public Object putLower(String string, Object object) {
/* 35 */     return this.internalMap.put(string.toLowerCase(), object);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object remove(Object object) {
/* 40 */     return this.internalMap.remove(object.toString().toLowerCase());
/*    */   }
/*    */ 
/*    */   
/*    */   public void putAll(Map map) {
/* 45 */     for (Map.Entry entry : map.entrySet()) {
/* 46 */       putLower((String)entry.getKey(), entry.getValue());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void clear() {
/* 52 */     this.internalMap.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set keySet() {
/* 57 */     return this.internalMap.keySet();
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection values() {
/* 62 */     return this.internalMap.values();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set entrySet() {
/* 67 */     return this.internalMap.entrySet();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\LowerStringMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */