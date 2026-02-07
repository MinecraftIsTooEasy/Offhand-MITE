/*    */ package net.minecraft;
/*    */ 
/*    */ public class CommandException extends RuntimeException {
/*    */   private Object[] errorObjects;
/*    */   
/*    */   public CommandException(String string, Object... objects) {
/*  7 */     super(string);
/*    */     
/*  9 */     this.errorObjects = objects;
/*    */   }
/*    */   
/*    */   public Object[] getErrorOjbects() {
/* 13 */     return this.errorObjects;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */