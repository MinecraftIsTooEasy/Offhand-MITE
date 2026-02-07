/*    */ package net.minecraft;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class RegistrySimple
/*    */   implements IRegistry {
/*  9 */   protected final Map registryObjects = func_111054_a();
/*    */   
/*    */   protected HashMap func_111054_a() {
/* 12 */     return Maps.newHashMap();
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getObject(Object object) {
/* 17 */     return this.registryObjects.get(object);
/*    */   }
/*    */ 
/*    */   
/*    */   public void putObject(Object object, Object object2) {
/* 22 */     this.registryObjects.put(object, object2);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RegistrySimple.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */