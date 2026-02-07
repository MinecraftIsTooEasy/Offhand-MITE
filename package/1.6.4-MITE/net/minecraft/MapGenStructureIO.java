/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MapGenStructureIO
/*    */ {
/* 14 */   private static Map field_143040_a = new HashMap<Object, Object>();
/* 15 */   private static Map field_143038_b = new HashMap<Object, Object>();
/*    */   
/* 17 */   private static Map field_143039_c = new HashMap<Object, Object>();
/* 18 */   private static Map field_143037_d = new HashMap<Object, Object>();
/*    */   
/*    */   private static void func_143034_b(Class<?> class_, String string) {
/* 21 */     field_143040_a.put(string, class_);
/* 22 */     field_143038_b.put(class_, string);
/*    */   }
/*    */   
/*    */   static void func_143031_a(Class<?> class_, String string) {
/* 26 */     field_143039_c.put(string, class_);
/* 27 */     field_143037_d.put(class_, string);
/*    */   }
/*    */   
/*    */   static {
/* 31 */     func_143034_b(StructureMineshaftStart.class, "Mineshaft");
/* 32 */     func_143034_b(StructureVillageStart.class, "Village");
/* 33 */     func_143034_b(StructureNetherBridgeStart.class, "Fortress");
/* 34 */     func_143034_b(StructureStrongholdStart.class, "Stronghold");
/* 35 */     func_143034_b(StructureScatteredFeatureStart.class, "Temple");
/*    */     
/* 37 */     StructureMineshaftPieces.func_143048_a();
/* 38 */     StructureVillagePieces.func_143016_a();
/* 39 */     StructureNetherBridgePieces.func_143049_a();
/* 40 */     StructureStrongholdPieces.func_143046_a();
/* 41 */     ComponentScatteredFeaturePieces.func_143045_a();
/*    */   }
/*    */   
/*    */   public static String func_143033_a(StructureStart structureStart) {
/* 45 */     return (String)field_143038_b.get(structureStart.getClass());
/*    */   }
/*    */   
/*    */   public static String func_143036_a(StructureComponent structureComponent) {
/* 49 */     return (String)field_143037_d.get(structureComponent.getClass());
/*    */   }
/*    */   
/*    */   public static StructureStart func_143035_a(NBTTagCompound nBTTagCompound, World world) {
/* 53 */     StructureStart structureStart = null;
/*    */     
/*    */     try {
/* 56 */       Class<StructureStart> clazz = (Class)field_143040_a.get(nBTTagCompound.getString("id"));
/* 57 */       if (clazz != null) structureStart = clazz.newInstance();
/*    */     
/* 59 */     } catch (Exception exception) {
/* 60 */       world.getWorldLogAgent().logWarning("Failed Start with id " + nBTTagCompound.getString("id"));
/* 61 */       exception.printStackTrace();
/*    */     } 
/* 63 */     if (structureStart != null) {
/* 64 */       structureStart.func_143020_a(world, nBTTagCompound);
/*    */     } else {
/* 66 */       world.getWorldLogAgent().logWarning("Skipping Structure with id " + nBTTagCompound.getString("id"));
/*    */     } 
/* 68 */     return structureStart;
/*    */   }
/*    */   
/*    */   public static StructureComponent func_143032_b(NBTTagCompound nBTTagCompound, World world) {
/* 72 */     StructureComponent structureComponent = null;
/*    */     
/*    */     try {
/* 75 */       Class<StructureComponent> clazz = (Class)field_143039_c.get(nBTTagCompound.getString("id"));
/* 76 */       if (clazz != null) structureComponent = clazz.newInstance();
/*    */     
/* 78 */     } catch (Exception exception) {
/* 79 */       world.getWorldLogAgent().logWarning("Failed Piece with id " + nBTTagCompound.getString("id"));
/* 80 */       exception.printStackTrace();
/*    */     } 
/* 82 */     if (structureComponent != null) {
/* 83 */       structureComponent.func_143009_a(world, nBTTagCompound);
/*    */     } else {
/* 85 */       world.getWorldLogAgent().logWarning("Skipping Piece with id " + nBTTagCompound.getString("id"));
/*    */     } 
/* 87 */     return structureComponent;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MapGenStructureIO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */