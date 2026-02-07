package net.minecraft;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public abstract class PacketComponent {
  public abstract void writeData(DataOutput paramDataOutput) throws IOException;
  
  public abstract void readData(DataInput paramDataInput) throws IOException;
  
  public abstract int getSize();
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PacketComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */