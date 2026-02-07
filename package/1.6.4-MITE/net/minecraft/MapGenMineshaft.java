/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class MapGenMineshaft
/*    */   extends MapGenStructure
/*    */ {
/*  9 */   private double field_82673_e = 0.01D;
/*    */ 
/*    */   
/*    */   public MapGenMineshaft() {}
/*    */   
/*    */   public String func_143025_a() {
/* 15 */     return "Mineshaft";
/*    */   }
/*    */ 
/*    */   
/*    */   public MapGenMineshaft(Map par1Map) {
/* 20 */     Iterator<Map.Entry> var2 = par1Map.entrySet().iterator();
/*    */     
/* 22 */     while (var2.hasNext()) {
/*    */       
/* 24 */       Map.Entry var3 = var2.next();
/*    */       
/* 26 */       if (((String)var3.getKey()).equals("chance"))
/*    */       {
/* 28 */         this.field_82673_e = MathHelper.parseDoubleWithDefault((String)var3.getValue(), this.field_82673_e);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean canSpawnStructureAtCoords(int chunk_x, int chunk_z) {
/* 40 */     if (this.rand.nextFloat() >= 0.005F) {
/* 41 */       return false;
/*    */     }
/* 43 */     if (!this.worldObj.getBiomeGenForCoords(chunk_x * 16, chunk_z * 16).canHaveMineshafts()) {
/* 44 */       return false;
/*    */     }
/* 46 */     boolean ret = (this.rand.nextInt(80) < Math.max(Math.abs(chunk_x), Math.abs(chunk_z)));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 51 */     return ret;
/*    */   }
/*    */ 
/*    */   
/*    */   protected StructureStart getStructureStart(int par1, int par2) {
/* 56 */     return new StructureMineshaftStart(this.worldObj, this.rand, par1, par2);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MapGenMineshaft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */