package net.minecraft;

public interface IChunkLoader {
  Chunk loadChunk(World paramWorld, int paramInt1, int paramInt2);
  
  void saveChunk(World paramWorld, Chunk paramChunk);
  
  void saveExtraChunkData(World paramWorld, Chunk paramChunk);
  
  void chunkTick();
  
  void saveExtraData();
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\IChunkLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */