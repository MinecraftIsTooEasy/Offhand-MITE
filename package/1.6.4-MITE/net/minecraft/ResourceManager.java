package net.minecraft;

import java.util.List;
import java.util.Set;

public interface ResourceManager {
  Set getResourceDomains();
  
  Resource getResource(ResourceLocation paramResourceLocation);
  
  List getAllResources(ResourceLocation paramResourceLocation);
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ResourceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */