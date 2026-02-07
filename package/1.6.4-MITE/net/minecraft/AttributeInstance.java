package net.minecraft;

import java.util.Collection;
import java.util.UUID;

public interface AttributeInstance {
  Attribute func_111123_a();
  
  double getBaseValue();
  
  void setAttribute(double paramDouble);
  
  Collection func_111122_c();
  
  AttributeModifier getModifier(UUID paramUUID);
  
  void applyModifier(AttributeModifier paramAttributeModifier);
  
  void removeModifier(AttributeModifier paramAttributeModifier);
  
  void func_142049_d();
  
  double getAttributeValue();
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\AttributeInstance.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */