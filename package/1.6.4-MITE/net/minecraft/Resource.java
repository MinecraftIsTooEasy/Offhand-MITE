package net.minecraft;

import java.io.InputStream;

public interface Resource {
  InputStream getInputStream();
  
  boolean hasMetadata();
  
  MetadataSection getMetadata(String paramString);
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Resource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */