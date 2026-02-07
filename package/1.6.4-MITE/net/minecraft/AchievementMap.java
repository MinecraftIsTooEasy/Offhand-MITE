/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStreamReader;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class AchievementMap {
/*  9 */   public static AchievementMap instance = new AchievementMap();
/*    */   
/* 11 */   private Map guidMap = new HashMap<Object, Object>();
/*    */   
/*    */   private AchievementMap() {
/*    */     try {
/* 15 */       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(AchievementMap.class.getResourceAsStream("/achievement/map.txt")));
/*    */       String str;
/* 17 */       while ((str = bufferedReader.readLine()) != null) {
/* 18 */         String[] arrayOfString = str.split(",");
/* 19 */         int i = Integer.parseInt(arrayOfString[0]);
/* 20 */         this.guidMap.put(Integer.valueOf(i), arrayOfString[1]);
/*    */       } 
/* 22 */       bufferedReader.close();
/* 23 */     } catch (Exception exception) {
/* 24 */       exception.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static String getGuid(int i) {
/* 29 */     return (String)instance.guidMap.get(Integer.valueOf(i));
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\AchievementMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */