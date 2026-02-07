/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class EnchantmentNameParts {
/*  6 */   public static final EnchantmentNameParts instance = new EnchantmentNameParts();
/*  7 */   private Random rand = new Random();
/*    */   
/*  9 */   private String[] wordList = "the elder scrolls klaatu berata niktu xyzzy bless curse light darkness fire air earth water hot dry cold wet ignite snuff embiggen twist shorten stretch fiddle destroy imbue galvanize enchant free limited range of towards inside sphere cube self other ball mental physical grow shrink demon elemental spirit animal creature beast humanoid undead fresh stale ".split(" ");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String generateRandomEnchantName() {
/* 34 */     int i = this.rand.nextInt(2) + 3;
/* 35 */     String str = "";
/* 36 */     for (byte b = 0; b < i; b++) {
/* 37 */       if (b > 0) str = str + " "; 
/* 38 */       str = str + this.wordList[this.rand.nextInt(this.wordList.length)];
/*    */     } 
/* 40 */     return str;
/*    */   }
/*    */   
/*    */   public void setRandSeed(long l) {
/* 44 */     this.rand.setSeed(l);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnchantmentNameParts.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */