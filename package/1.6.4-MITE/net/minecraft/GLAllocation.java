/*    */ package net.minecraft;
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.ByteOrder;
/*    */ import java.nio.FloatBuffer;
/*    */ import java.nio.IntBuffer;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class GLAllocation {
/* 12 */   private static final Map field_74531_a = new HashMap<Object, Object>();
/* 13 */   private static final List field_74530_b = new ArrayList();
/*    */   
/*    */   public static synchronized int generateDisplayLists(int i) {
/* 16 */     int j = GL11.glGenLists(i);
/* 17 */     field_74531_a.put(Integer.valueOf(j), Integer.valueOf(i));
/*    */     
/* 19 */     return j;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static synchronized void deleteDisplayLists(int i) {
/* 29 */     GL11.glDeleteLists(i, ((Integer)field_74531_a.remove(Integer.valueOf(i))).intValue());
/*    */   }
/*    */   
/*    */   public static synchronized void func_98302_b() {
/* 33 */     for (byte b = 0; b < field_74530_b.size(); b++) {
/* 34 */       GL11.glDeleteTextures(((Integer)field_74530_b.get(b)).intValue());
/*    */     }
/* 36 */     field_74530_b.clear();
/*    */   }
/*    */   
/*    */   public static synchronized void deleteTexturesAndDisplayLists() {
/* 40 */     for (Map.Entry entry : field_74531_a.entrySet()) {
/* 41 */       GL11.glDeleteLists(((Integer)entry.getKey()).intValue(), ((Integer)entry.getValue()).intValue());
/*    */     }
/* 43 */     field_74531_a.clear();
/*    */     
/* 45 */     func_98302_b();
/*    */   }
/*    */   
/*    */   public static synchronized ByteBuffer createDirectByteBuffer(int i) {
/* 49 */     return ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static IntBuffer createDirectIntBuffer(int i) {
/* 61 */     return createDirectByteBuffer(i << 2).asIntBuffer();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static FloatBuffer createDirectFloatBuffer(int i) {
/* 69 */     return createDirectByteBuffer(i << 2).asFloatBuffer();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GLAllocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */