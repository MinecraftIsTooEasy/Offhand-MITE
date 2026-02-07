package net.minecraft;

import java.util.List;

public interface ICrafting {
  void sendContainerAndContentsToPlayer(Container paramContainer, List paramList);
  
  void sendSlotContents(Container paramContainer, int paramInt, ItemStack paramItemStack);
  
  void sendProgressBarUpdate(Container paramContainer, int paramInt1, int paramInt2);
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ICrafting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */