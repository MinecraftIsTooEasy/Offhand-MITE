/*   */ package net.minecraft;
/*   */ 
/*   */ public class NumberInvalidException extends CommandException {
/*   */   public NumberInvalidException() {
/* 5 */     this("commands.generic.num.invalid", new Object[0]);
/*   */   }
/*   */   
/*   */   public NumberInvalidException(String string, Object... objects) {
/* 9 */     super(string, objects);
/*   */   }
/*   */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\NumberInvalidException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */