/*    */ package net.minecraft;
/*    */ 
/*    */ public class RegistryDefaulted extends RegistrySimple {
/*    */   private final Object defaultObject;
/*    */   
/*    */   public RegistryDefaulted(Object object) {
/*  7 */     this.defaultObject = object;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getObject(Object object) {
/* 12 */     Object object1 = super.getObject(object);
/* 13 */     return (object1 == null) ? this.defaultObject : object1;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RegistryDefaulted.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */