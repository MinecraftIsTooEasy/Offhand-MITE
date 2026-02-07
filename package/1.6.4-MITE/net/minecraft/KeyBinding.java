/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class KeyBinding
/*    */ {
/*  9 */   public static List keybindArray = new ArrayList();
/* 10 */   public static IntHashMap hash = new IntHashMap(); public String keyDescription; public int keyCode;
/*    */   
/*    */   public static void onTick(int i) {
/* 13 */     KeyBinding keyBinding = (KeyBinding)hash.lookup(i);
/* 14 */     if (keyBinding != null) keyBinding.pressTime++; 
/*    */   }
/*    */   public boolean pressed; public int pressTime;
/*    */   public static void setKeyBindState(int i, boolean bl) {
/* 18 */     KeyBinding keyBinding = (KeyBinding)hash.lookup(i);
/* 19 */     if (keyBinding != null) keyBinding.pressed = bl; 
/*    */   }
/*    */   
/*    */   public static void unPressAllKeys() {
/* 23 */     for (KeyBinding keyBinding : keybindArray) {
/* 24 */       keyBinding.unpressKey();
/*    */     }
/*    */   }
/*    */   
/*    */   public static void resetKeyBindingArrayAndHash() {
/* 29 */     hash.clearMap();
/* 30 */     for (KeyBinding keyBinding : keybindArray) {
/* 31 */       hash.addKey(keyBinding.keyCode, keyBinding);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public KeyBinding(String string, int i) {
/* 41 */     this.keyDescription = string;
/* 42 */     this.keyCode = i;
/* 43 */     keybindArray.add(this);
/* 44 */     hash.addKey(i, this);
/*    */   }
/*    */   
/*    */   public boolean isPressed() {
/* 48 */     if (this.pressTime == 0) return false; 
/* 49 */     this.pressTime--;
/* 50 */     return true;
/*    */   }
/*    */   
/*    */   private void unpressKey() {
/* 54 */     this.pressTime = 0;
/* 55 */     this.pressed = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\KeyBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */