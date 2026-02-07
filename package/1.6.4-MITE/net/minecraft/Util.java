/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Util
/*    */ {
/*    */   public static EnumOS getOSType() {
/* 13 */     String str = System.getProperty("os.name").toLowerCase();
/* 14 */     if (str.contains("win")) return EnumOS.WINDOWS; 
/* 15 */     if (str.contains("mac")) return EnumOS.MACOS; 
/* 16 */     if (str.contains("solaris")) return EnumOS.SOLARIS; 
/* 17 */     if (str.contains("sunos")) return EnumOS.SOLARIS; 
/* 18 */     if (str.contains("linux")) return EnumOS.LINUX; 
/* 19 */     if (str.contains("unix")) return EnumOS.LINUX; 
/* 20 */     return EnumOS.UNKNOWN;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */