/*     */ package net.minecraft;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class ModifiableAttributeInstance implements AttributeInstance {
/*     */   private final BaseAttributeMap field_111138_a;
/*  10 */   private final Map field_111137_c = Maps.newHashMap(); private final Attribute field_111136_b;
/*  11 */   private final Map field_111134_d = Maps.newHashMap();
/*  12 */   private final Map field_111135_e = Maps.newHashMap();
/*     */   private double baseValue;
/*     */   private boolean field_111133_g = true;
/*     */   private double field_111139_h;
/*     */   
/*     */   public ModifiableAttributeInstance(BaseAttributeMap baseAttributeMap, Attribute attribute) {
/*  18 */     this.field_111138_a = baseAttributeMap;
/*  19 */     this.field_111136_b = attribute;
/*  20 */     this.baseValue = attribute.getDefaultValue();
/*     */     
/*  22 */     for (byte b = 0; b < 3; b++) {
/*  23 */       this.field_111137_c.put(Integer.valueOf(b), new HashSet());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Attribute func_111123_a() {
/*  29 */     return this.field_111136_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getBaseValue() {
/*  34 */     return this.baseValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAttribute(double d) {
/*  39 */     if (d == getBaseValue())
/*  40 */       return;  this.baseValue = d;
/*  41 */     func_111131_f();
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection func_111130_a(int i) {
/*  46 */     return (Collection)this.field_111137_c.get(Integer.valueOf(i));
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection func_111122_c() {
/*  51 */     HashSet hashSet = new HashSet();
/*     */     
/*  53 */     for (byte b = 0; b < 3; b++) {
/*  54 */       hashSet.addAll(func_111130_a(b));
/*     */     }
/*     */     
/*  57 */     return hashSet;
/*     */   }
/*     */ 
/*     */   
/*     */   public AttributeModifier getModifier(UUID uUID) {
/*  62 */     return (AttributeModifier)this.field_111135_e.get(uUID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void applyModifier(AttributeModifier attributeModifier) {
/*  74 */     if (getModifier(attributeModifier.getID()) != null) throw new IllegalArgumentException("Modifier is already applied on this attribute!");
/*     */     
/*  76 */     Set<AttributeModifier> set = (Set)this.field_111134_d.get(attributeModifier.getName());
/*     */     
/*  78 */     if (set == null) {
/*  79 */       set = new HashSet();
/*  80 */       this.field_111134_d.put(attributeModifier.getName(), set);
/*     */     } 
/*     */     
/*  83 */     ((Set<AttributeModifier>)this.field_111137_c.get(Integer.valueOf(attributeModifier.getOperation()))).add(attributeModifier);
/*  84 */     set.add(attributeModifier);
/*  85 */     this.field_111135_e.put(attributeModifier.getID(), attributeModifier);
/*     */     
/*  87 */     func_111131_f();
/*     */   }
/*     */   
/*     */   private void func_111131_f() {
/*  91 */     this.field_111133_g = true;
/*  92 */     this.field_111138_a.func_111149_a(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeModifier(AttributeModifier attributeModifier) {
/*  97 */     for (byte b = 0; b < 3; b++) {
/*  98 */       Set set1 = (Set)this.field_111137_c.get(Integer.valueOf(b));
/*  99 */       set1.remove(attributeModifier);
/*     */     } 
/*     */     
/* 102 */     Set set = (Set)this.field_111134_d.get(attributeModifier.getName());
/*     */     
/* 104 */     if (set != null) {
/* 105 */       set.remove(attributeModifier);
/*     */       
/* 107 */       if (set.isEmpty()) {
/* 108 */         this.field_111134_d.remove(attributeModifier.getName());
/*     */       }
/*     */     } 
/*     */     
/* 112 */     this.field_111135_e.remove(attributeModifier.getID());
/* 113 */     func_111131_f();
/*     */   }
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
/*     */   public void func_142049_d() {
/* 141 */     Collection<?> collection = func_111122_c();
/* 142 */     if (collection == null)
/*     */       return; 
/* 144 */     collection = new ArrayList(collection);
/*     */     
/* 146 */     for (AttributeModifier attributeModifier : collection) {
/* 147 */       removeModifier(attributeModifier);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public double getAttributeValue() {
/* 153 */     if (this.field_111133_g) {
/* 154 */       this.field_111139_h = func_111129_g();
/* 155 */       this.field_111133_g = false;
/*     */     } 
/*     */     
/* 158 */     return this.field_111139_h;
/*     */   }
/*     */   
/*     */   private double func_111129_g() {
/* 162 */     double d1 = getBaseValue();
/*     */     
/* 164 */     for (AttributeModifier attributeModifier : func_111130_a(0)) {
/* 165 */       d1 += attributeModifier.getAmount();
/*     */     }
/*     */     
/* 168 */     double d2 = d1;
/*     */     
/* 170 */     for (AttributeModifier attributeModifier : func_111130_a(1)) {
/* 171 */       d2 += d1 * attributeModifier.getAmount();
/*     */     }
/*     */     
/* 174 */     for (AttributeModifier attributeModifier : func_111130_a(2)) {
/* 175 */       d2 *= 1.0D + attributeModifier.getAmount();
/*     */     }
/*     */     
/* 178 */     return this.field_111136_b.clampValue(d2);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModifiableAttributeInstance.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */