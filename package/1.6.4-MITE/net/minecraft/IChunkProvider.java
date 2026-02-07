package net.minecraft;

import java.util.List;

public interface IChunkProvider {
  boolean chunkExists(int paramInt1, int paramInt2);
  
  Chunk getChunkIfItExists(int paramInt1, int paramInt2);
  
  Chunk provideChunk(int paramInt1, int paramInt2);
  
  Chunk loadChunk(int paramInt1, int paramInt2);
  
  void populate(IChunkProvider paramIChunkProvider, int paramInt1, int paramInt2);
  
  boolean saveChunks(boolean paramBoolean, IProgressUpdate paramIProgressUpdate);
  
  boolean unloadQueuedChunks();
  
  boolean canSave();
  
  String makeString();
  
  List getPossibleCreatures(EnumCreatureType paramEnumCreatureType, int paramInt1, int paramInt2, int paramInt3);
  
  ChunkPosition findClosestStructure(World paramWorld, String paramString, int paramInt1, int paramInt2, int paramInt3);
  
  int getLoadedChunkCount();
  
  void recreateStructures(int paramInt1, int paramInt2);
  
  void saveExtraData();
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\IChunkProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */