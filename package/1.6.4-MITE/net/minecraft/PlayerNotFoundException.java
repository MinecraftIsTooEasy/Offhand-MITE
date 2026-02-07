/*   */ package net.minecraft;
/*   */ 
/*   */ public class PlayerNotFoundException extends CommandException {
/*   */   public PlayerNotFoundException() {
/* 5 */     this("commands.generic.player.notFound", new Object[0]);
/*   */   }
/*   */   
/*   */   public PlayerNotFoundException(String string, Object... objects) {
/* 9 */     super(string, objects);
/*   */   }
/*   */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PlayerNotFoundException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */