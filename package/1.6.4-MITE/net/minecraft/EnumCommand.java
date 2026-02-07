/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumCommand
/*    */ {
/*  7 */   chunks("chunks", "Shows the number of chunks loaded"),
/*  8 */   commands("commands", "Lists all MITE commands"),
/*  9 */   day("day", "Shows the age of the world, in days"),
/* 10 */   ground("ground", "Rescues player from bouncing between floor and ceiling bug"),
/* 11 */   load("load", "Shows the current processing load on the server"),
/* 12 */   mem("mem", "Shows the amount of memory the server is currently using as well as the total amount that has been allocated to the JVM"),
/* 13 */   rendering("rendering", "Shows the current rendering scheme"),
/* 14 */   skills("skills", "Lists all available skills if professions are enabled"),
/* 15 */   stats("stats", "Writes character stats to plain text file"),
/* 16 */   syncpos("syncpos", "Syncs player's position on client exactly with position on server"),
/* 17 */   tournament("tournament", "Shows the tournament objective, if applicable"),
/* 18 */   version("version", "Shows the release number of MITE you are playing"),
/* 19 */   versions("versions", "Shows the range of releases the current world has been played with"),
/* 20 */   villages("villages", "Shows prerequisites for unlocking villages"),
/* 21 */   xp("xp", "Shows how many experience points you have");
/*    */   
/*    */   String text;
/*    */   
/*    */   String description;
/*    */   
/*    */   EnumCommand(String text, String description) {
/* 28 */     this.text = text;
/* 29 */     this.description = description;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 34 */     return this.text;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean matches(String text) {
/* 39 */     return (text != null && text.equalsIgnoreCase(this.text));
/*    */   }
/*    */ 
/*    */   
/*    */   static EnumCommand get(String text) {
/* 44 */     if (text == null) {
/* 45 */       return null;
/*    */     }
/* 47 */     if (text.startsWith("/")) {
/* 48 */       text = text.substring(1);
/*    */     }
/* 50 */     for (int i = 0; i < (values()).length; i++) {
/*    */       
/* 52 */       if (values()[i].matches(text)) {
/* 53 */         return values()[i];
/*    */       }
/*    */     } 
/* 56 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   static EnumCommand get(int ordinal) {
/* 61 */     return values()[ordinal];
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */