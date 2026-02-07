package net.minecraft;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Set;

public interface ResourcePack {
  InputStream getInputStream(ResourceLocation paramResourceLocation);
  
  boolean resourceExists(ResourceLocation paramResourceLocation);
  
  Set getResourceDomains();
  
  MetadataSection getPackMetadata(MetadataSerializer paramMetadataSerializer, String paramString);
  
  BufferedImage getPackImage();
  
  String getPackName();
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ResourcePack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */