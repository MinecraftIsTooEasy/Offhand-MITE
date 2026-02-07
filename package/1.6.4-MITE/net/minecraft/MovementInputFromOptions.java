/*    */ package net.minecraft;
/*    */ 
/*    */ public class MovementInputFromOptions
/*    */   extends MovementInput
/*    */ {
/*    */   private GameSettings gameSettings;
/*    */   
/*    */   public MovementInputFromOptions(GameSettings par1GameSettings) {
/*  9 */     this.gameSettings = par1GameSettings;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updatePlayerMoveState() {
/* 14 */     this.moveStrafe = 0.0F;
/* 15 */     this.moveForward = 0.0F;
/*    */     
/* 17 */     if (Minecraft.theMinecraft.thePlayer.isGhost()) {
/*    */       return;
/*    */     }
/* 20 */     if (this.gameSettings.keyBindForward.pressed)
/*    */     {
/* 22 */       this.moveForward++;
/*    */     }
/*    */     
/* 25 */     if (this.gameSettings.keyBindBack.pressed)
/*    */     {
/* 27 */       this.moveForward--;
/*    */     }
/*    */     
/* 30 */     if (this.gameSettings.keyBindLeft.pressed)
/*    */     {
/* 32 */       this.moveStrafe++;
/*    */     }
/*    */     
/* 35 */     if (this.gameSettings.keyBindRight.pressed)
/*    */     {
/* 37 */       this.moveStrafe--;
/*    */     }
/*    */     
/* 40 */     this.jump = this.gameSettings.keyBindJump.pressed;
/*    */     
/* 42 */     this.sneak = this.gameSettings.keyBindSneak.pressed;
/*    */     
/* 44 */     if (this.sneak) {
/*    */       
/* 46 */       this.moveStrafe = (float)(this.moveStrafe * 0.3D);
/* 47 */       this.moveForward = (float)(this.moveForward * 0.3D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MovementInputFromOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */