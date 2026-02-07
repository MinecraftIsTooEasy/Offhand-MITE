/*    */ package net.minecraft;
/*    */ 
/*    */ import java.net.URL;
/*    */ 
/*    */ public class SoundPoolEntry {
/*    */   private final String soundName;
/*    */   private final URL soundUrl;
/*    */   
/*    */   public SoundPoolEntry(String string, URL uRL) {
/* 10 */     this.soundName = string;
/* 11 */     this.soundUrl = uRL;
/*    */   }
/*    */   
/*    */   public String getSoundName() {
/* 15 */     return this.soundName;
/*    */   }
/*    */   
/*    */   public URL getSoundUrl() {
/* 19 */     return this.soundUrl;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SoundPoolEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */