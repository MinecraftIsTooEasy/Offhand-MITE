/*    */ package net.minecraft;
/*    */ 
/*    */ import com.google.common.collect.Multimap;
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public abstract class BaseAttributeMap
/*    */ {
/* 12 */   protected final Map attributes = new HashMap<Object, Object>();
/* 13 */   protected final Map attributesByName = new LowerStringMap();
/*    */ 
/*    */   
/*    */   public AttributeInstance getAttributeInstance(Attribute par1Attribute) {
/* 17 */     return (AttributeInstance)this.attributes.get(par1Attribute);
/*    */   }
/*    */ 
/*    */   
/*    */   public AttributeInstance getAttributeInstanceByName(String par1Str) {
/* 22 */     return (AttributeInstance)this.attributesByName.get(par1Str);
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract AttributeInstance register(Attribute paramAttribute);
/*    */ 
/*    */   
/*    */   public Collection getAllAttributes() {
/* 30 */     return this.attributesByName.values();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_111149_a(ModifiableAttributeInstance par1ModifiableAttributeInstance) {}
/*    */   
/*    */   public void removeAttributeModifiers(Multimap par1Multimap) {
/* 37 */     Iterator<Map.Entry> var2 = par1Multimap.entries().iterator();
/*    */     
/* 39 */     while (var2.hasNext()) {
/*    */       
/* 41 */       Map.Entry var3 = var2.next();
/* 42 */       AttributeInstance var4 = getAttributeInstanceByName((String)var3.getKey());
/*    */       
/* 44 */       if (var4 != null)
/*    */       {
/* 46 */         var4.removeModifier((AttributeModifier)var3.getValue());
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyAttributeModifiers(Multimap par1Multimap) {
/* 53 */     Iterator<Map.Entry> var2 = par1Multimap.entries().iterator();
/*    */     
/* 55 */     while (var2.hasNext()) {
/*    */       
/* 57 */       Map.Entry var3 = var2.next();
/* 58 */       AttributeInstance var4 = getAttributeInstanceByName((String)var3.getKey());
/*    */       
/* 60 */       if (var4 != null) {
/*    */         
/* 62 */         var4.removeModifier((AttributeModifier)var3.getValue());
/* 63 */         var4.applyModifier((AttributeModifier)var3.getValue());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BaseAttributeMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */