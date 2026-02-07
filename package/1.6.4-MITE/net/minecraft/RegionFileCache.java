/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.zip.DeflaterOutputStream;
/*    */ 
/*    */ 
/*    */ public class RegionFileCache
/*    */ {
/* 15 */   private static final Map regionsByFilename = new HashMap<Object, Object>();
/*    */   
/*    */   public static RegionFileChunkBuffer chunk_buffer_to_close;
/*    */   
/*    */   public static DeflaterOutputStream deflater_output_stream_to_close;
/*    */   
/*    */   public static synchronized RegionFile createOrLoadRegionFile(File par0File, int par1, int par2) {
/* 22 */     File var3 = new File(par0File, "region");
/* 23 */     File var4 = new File(var3, "r." + (par1 >> 5) + "." + (par2 >> 5) + ".mca");
/* 24 */     RegionFile var5 = (RegionFile)regionsByFilename.get(var4);
/*    */     
/* 26 */     if (var5 != null)
/*    */     {
/* 28 */       return var5;
/*    */     }
/*    */ 
/*    */     
/* 32 */     if (!var3.exists())
/*    */     {
/* 34 */       var3.mkdirs();
/*    */     }
/*    */     
/* 37 */     if (regionsByFilename.size() >= 256)
/*    */     {
/* 39 */       clearRegionFileReferences();
/*    */     }
/*    */     
/* 42 */     RegionFile var6 = new RegionFile(var4);
/* 43 */     regionsByFilename.put(var4, var6);
/* 44 */     return var6;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static synchronized void clearRegionFileReferences() {
/* 55 */     Iterator<RegionFile> var0 = regionsByFilename.values().iterator();
/*    */     
/* 57 */     while (var0.hasNext()) {
/*    */       
/* 59 */       RegionFile var1 = var0.next();
/*    */ 
/*    */       
/*    */       try {
/* 63 */         if (var1 != null)
/*    */         {
/* 65 */           var1.close();
/*    */         }
/*    */       }
/* 68 */       catch (IOException var3) {
/*    */         
/* 70 */         var3.printStackTrace();
/*    */       } 
/*    */     } 
/*    */     
/* 74 */     regionsByFilename.clear();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static DataInputStream getChunkInputStream(File par0File, int par1, int par2) {
/* 82 */     RegionFile var3 = createOrLoadRegionFile(par0File, par1, par2);
/* 83 */     return var3.getChunkDataInputStream(par1 & 0x1F, par2 & 0x1F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static DataOutputStream getChunkOutputStream(File par0File, int par1, int par2) {
/* 91 */     RegionFile var3 = createOrLoadRegionFile(par0File, par1, par2);
/*    */ 
/*    */ 
/*    */     
/* 95 */     DataOutputStream data_output_stream = var3.getChunkDataOutputStream(par1 & 0x1F, par2 & 0x1F);
/*    */     
/* 97 */     chunk_buffer_to_close = var3.chunk_buffer_to_close;
/* 98 */     deflater_output_stream_to_close = var3.deflater_output_stream_to_close;
/* 99 */     return data_output_stream;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RegionFileCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */