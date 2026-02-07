/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.UUID;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SharedMonsterAttributes
/*    */ {
/* 12 */   public static final Attribute maxHealth = (new RangedAttribute("generic.maxHealth", 20.0D, 0.0D, Double.MAX_VALUE)).func_111117_a("Max Health").setShouldWatch(true);
/* 13 */   public static final Attribute followRange = (new RangedAttribute("generic.followRange", 32.0D, 0.0D, 2048.0D)).func_111117_a("Follow Range");
/* 14 */   public static final Attribute knockbackResistance = (new RangedAttribute("generic.knockbackResistance", 0.0D, 0.0D, 1.0D)).func_111117_a("Knockback Resistance");
/* 15 */   public static final Attribute movementSpeed = (new RangedAttribute("generic.movementSpeed", 0.699999988079071D, 0.0D, Double.MAX_VALUE)).func_111117_a("Movement Speed").setShouldWatch(true);
/* 16 */   public static final Attribute attackDamage = new RangedAttribute("generic.attackDamage", 2.0D, 0.0D, Double.MAX_VALUE);
/*    */   
/*    */   public static NBTTagList func_111257_a(BaseAttributeMap baseAttributeMap) {
/* 19 */     NBTTagList nBTTagList = new NBTTagList();
/*    */     
/* 21 */     for (AttributeInstance attributeInstance : baseAttributeMap.getAllAttributes()) {
/* 22 */       nBTTagList.appendTag(func_111261_a(attributeInstance));
/*    */     }
/*    */     
/* 25 */     return nBTTagList;
/*    */   }
/*    */   
/*    */   private static NBTTagCompound func_111261_a(AttributeInstance attributeInstance) {
/* 29 */     NBTTagCompound nBTTagCompound = new NBTTagCompound();
/* 30 */     Attribute attribute = attributeInstance.func_111123_a();
/*    */     
/* 32 */     nBTTagCompound.setString("Name", attribute.getAttributeUnlocalizedName());
/* 33 */     nBTTagCompound.setDouble("Base", attributeInstance.getBaseValue());
/*    */     
/* 35 */     Collection collection = attributeInstance.func_111122_c();
/*    */     
/* 37 */     if (collection != null && !collection.isEmpty()) {
/* 38 */       NBTTagList nBTTagList = new NBTTagList();
/*    */       
/* 40 */       for (AttributeModifier attributeModifier : collection) {
/* 41 */         if (attributeModifier.isSaved()) {
/* 42 */           nBTTagList.appendTag(func_111262_a(attributeModifier));
/*    */         }
/*    */       } 
/*    */       
/* 46 */       nBTTagCompound.setTag("Modifiers", nBTTagList);
/*    */     } 
/*    */     
/* 49 */     return nBTTagCompound;
/*    */   }
/*    */   
/*    */   private static NBTTagCompound func_111262_a(AttributeModifier attributeModifier) {
/* 53 */     NBTTagCompound nBTTagCompound = new NBTTagCompound();
/*    */     
/* 55 */     nBTTagCompound.setString("Name", attributeModifier.getName());
/* 56 */     nBTTagCompound.setDouble("Amount", attributeModifier.getAmount());
/* 57 */     nBTTagCompound.setInteger("Operation", attributeModifier.getOperation());
/* 58 */     nBTTagCompound.setLong("UUIDMost", attributeModifier.getID().getMostSignificantBits());
/* 59 */     nBTTagCompound.setLong("UUIDLeast", attributeModifier.getID().getLeastSignificantBits());
/*    */     
/* 61 */     return nBTTagCompound;
/*    */   }
/*    */   
/*    */   public static void func_111260_a(BaseAttributeMap baseAttributeMap, NBTTagList nBTTagList, ILogAgent iLogAgent) {
/* 65 */     for (byte b = 0; b < nBTTagList.tagCount(); b++) {
/* 66 */       NBTTagCompound nBTTagCompound = (NBTTagCompound)nBTTagList.tagAt(b);
/* 67 */       AttributeInstance attributeInstance = baseAttributeMap.getAttributeInstanceByName(nBTTagCompound.getString("Name"));
/*    */       
/* 69 */       if (attributeInstance != null) {
/* 70 */         func_111258_a(attributeInstance, nBTTagCompound);
/* 71 */       } else if (iLogAgent != null) {
/* 72 */         iLogAgent.logWarning("Ignoring unknown attribute '" + nBTTagCompound.getString("Name") + "'");
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private static void func_111258_a(AttributeInstance attributeInstance, NBTTagCompound nBTTagCompound) {
/* 78 */     attributeInstance.setAttribute(nBTTagCompound.getDouble("Base"));
/*    */     
/* 80 */     if (nBTTagCompound.hasKey("Modifiers")) {
/* 81 */       NBTTagList nBTTagList = nBTTagCompound.getTagList("Modifiers");
/*    */       
/* 83 */       for (byte b = 0; b < nBTTagList.tagCount(); b++) {
/* 84 */         AttributeModifier attributeModifier1 = func_111259_a((NBTTagCompound)nBTTagList.tagAt(b));
/* 85 */         AttributeModifier attributeModifier2 = attributeInstance.getModifier(attributeModifier1.getID());
/* 86 */         if (attributeModifier2 != null) attributeInstance.removeModifier(attributeModifier2); 
/* 87 */         attributeInstance.applyModifier(attributeModifier1);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static AttributeModifier func_111259_a(NBTTagCompound nBTTagCompound) {
/* 93 */     UUID uUID = new UUID(nBTTagCompound.getLong("UUIDMost"), nBTTagCompound.getLong("UUIDLeast"));
/* 94 */     return new AttributeModifier(uUID, nBTTagCompound.getString("Name"), nBTTagCompound.getDouble("Amount"), nBTTagCompound.getInteger("Operation"));
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SharedMonsterAttributes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */