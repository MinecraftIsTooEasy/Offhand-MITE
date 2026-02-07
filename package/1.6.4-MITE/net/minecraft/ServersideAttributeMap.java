/*    */ package net.minecraft;
/*    */ 
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.Collection;
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class ServersideAttributeMap
/*    */   extends BaseAttributeMap {
/* 12 */   private final Set field_111162_d = Sets.newHashSet();
/* 13 */   protected final Map field_111163_c = new LowerStringMap();
/*    */ 
/*    */   
/*    */   public ModifiableAttributeInstance func_111159_c(Attribute par1Attribute) {
/* 17 */     return (ModifiableAttributeInstance)super.getAttributeInstance(par1Attribute);
/*    */   }
/*    */ 
/*    */   
/*    */   public ModifiableAttributeInstance func_111158_b(String par1Str) {
/* 22 */     AttributeInstance var2 = super.getAttributeInstanceByName(par1Str);
/*    */     
/* 24 */     if (var2 == null)
/*    */     {
/* 26 */       var2 = (AttributeInstance)this.field_111163_c.get(par1Str);
/*    */     }
/*    */     
/* 29 */     return (ModifiableAttributeInstance)var2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AttributeInstance register(Attribute par1Attribute) {
/* 35 */     if (this.attributesByName.containsKey(par1Attribute.getAttributeUnlocalizedName()))
/*    */     {
/* 37 */       throw new IllegalArgumentException("Attribute is already registered!");
/*    */     }
/*    */ 
/*    */     
/* 41 */     ModifiableAttributeInstance var2 = new ModifiableAttributeInstance(this, par1Attribute);
/* 42 */     this.attributesByName.put(par1Attribute.getAttributeUnlocalizedName(), var2);
/*    */     
/* 44 */     if (par1Attribute instanceof RangedAttribute && ((RangedAttribute)par1Attribute).func_111116_f() != null)
/*    */     {
/* 46 */       this.field_111163_c.put(((RangedAttribute)par1Attribute).func_111116_f(), var2);
/*    */     }
/*    */     
/* 49 */     this.attributes.put(par1Attribute, var2);
/* 50 */     return var2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_111149_a(ModifiableAttributeInstance par1ModifiableAttributeInstance) {
/* 56 */     if (par1ModifiableAttributeInstance.func_111123_a().getShouldWatch())
/*    */     {
/* 58 */       this.field_111162_d.add(par1ModifiableAttributeInstance);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public Set func_111161_b() {
/* 64 */     return this.field_111162_d;
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection func_111160_c() {
/* 69 */     HashSet<AttributeInstance> var1 = Sets.newHashSet();
/* 70 */     Iterator<AttributeInstance> var2 = getAllAttributes().iterator();
/*    */     
/* 72 */     while (var2.hasNext()) {
/*    */       
/* 74 */       AttributeInstance var3 = var2.next();
/*    */       
/* 76 */       if (var3.func_111123_a().getShouldWatch())
/*    */       {
/* 78 */         var1.add(var3);
/*    */       }
/*    */     } 
/*    */     
/* 82 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public AttributeInstance getAttributeInstanceByName(String par1Str) {
/* 87 */     return func_111158_b(par1Str);
/*    */   }
/*    */ 
/*    */   
/*    */   public AttributeInstance getAttributeInstance(Attribute par1Attribute) {
/* 92 */     return func_111159_c(par1Attribute);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ServersideAttributeMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */