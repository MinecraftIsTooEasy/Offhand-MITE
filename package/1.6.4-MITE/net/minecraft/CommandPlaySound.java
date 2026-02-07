/*     */ package net.minecraft;
/*     */ 
/*     */ public class CommandPlaySound
/*     */   extends CommandBase
/*     */ {
/*     */   public String getCommandName() {
/*   7 */     return "playsound";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  15 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender par1ICommandSender) {
/*  20 */     return "commands.playsound.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/*  25 */     if (par2ArrayOfStr.length < 2)
/*     */     {
/*  27 */       throw new WrongUsageException(getCommandUsage(par1ICommandSender), new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  31 */     byte var3 = 0;
/*  32 */     int var36 = var3 + 1;
/*  33 */     String var4 = par2ArrayOfStr[var3];
/*  34 */     ServerPlayer var5 = getPlayer(par1ICommandSender, par2ArrayOfStr[var36++]);
/*  35 */     double var6 = (var5.getPlayerCoordinates()).posX;
/*  36 */     double var8 = (var5.getPlayerCoordinates()).posY;
/*  37 */     double var10 = (var5.getPlayerCoordinates()).posZ;
/*  38 */     double var12 = 1.0D;
/*  39 */     double var14 = 1.0D;
/*  40 */     double var16 = 0.0D;
/*     */     
/*  42 */     if (par2ArrayOfStr.length > var36)
/*     */     {
/*     */ 
/*     */       
/*  46 */       var6 = func_110666_a(par1ICommandSender, var6, par2ArrayOfStr[var36++], var5.worldObj.min_block_xz, var5.worldObj.max_block_xz);
/*     */     }
/*     */     
/*  49 */     if (par2ArrayOfStr.length > var36)
/*     */     {
/*  51 */       var8 = func_110665_a(par1ICommandSender, var8, par2ArrayOfStr[var36++], 0, 0);
/*     */     }
/*     */     
/*  54 */     if (par2ArrayOfStr.length > var36)
/*     */     {
/*     */ 
/*     */       
/*  58 */       var10 = func_110666_a(par1ICommandSender, var10, par2ArrayOfStr[var36++], var5.worldObj.min_block_xz, var5.worldObj.max_block_xz);
/*     */     }
/*     */     
/*  61 */     if (par2ArrayOfStr.length > var36)
/*     */     {
/*  63 */       var12 = func_110661_a(par1ICommandSender, par2ArrayOfStr[var36++], 0.0D, 3.4028234663852886E38D);
/*     */     }
/*     */     
/*  66 */     if (par2ArrayOfStr.length > var36)
/*     */     {
/*  68 */       var14 = func_110661_a(par1ICommandSender, par2ArrayOfStr[var36++], 0.0D, 2.0D);
/*     */     }
/*     */     
/*  71 */     if (par2ArrayOfStr.length > var36)
/*     */     {
/*  73 */       var16 = func_110661_a(par1ICommandSender, par2ArrayOfStr[var36++], 0.0D, 1.0D);
/*     */     }
/*     */     
/*  76 */     double var18 = (var12 > 1.0D) ? (var12 * 16.0D) : 16.0D;
/*  77 */     double var20 = var5.getDistance(var6, var8, var10);
/*     */     
/*  79 */     if (var20 > var18) {
/*     */       
/*  81 */       if (var16 <= 0.0D)
/*     */       {
/*  83 */         throw new CommandException("commands.playsound.playerTooFar", new Object[] { var5.getEntityName() });
/*     */       }
/*     */       
/*  86 */       double var22 = var6 - var5.posX;
/*  87 */       double var24 = var8 - var5.posY;
/*  88 */       double var26 = var10 - var5.posZ;
/*  89 */       double var28 = Math.sqrt(var22 * var22 + var24 * var24 + var26 * var26);
/*  90 */       double var30 = var5.posX;
/*  91 */       double var32 = var5.posY;
/*  92 */       double var34 = var5.posZ;
/*     */       
/*  94 */       if (var28 > 0.0D) {
/*     */         
/*  96 */         var30 += var22 / var28 * 2.0D;
/*  97 */         var32 += var24 / var28 * 2.0D;
/*  98 */         var34 += var26 / var28 * 2.0D;
/*     */       } 
/*     */       
/* 101 */       var5.playerNetServerHandler.sendPacketToPlayer(new Packet62LevelSound(var4, var30, var32, var34, (float)var16, (float)var14));
/*     */     }
/*     */     else {
/*     */       
/* 105 */       var5.playerNetServerHandler.sendPacketToPlayer(new Packet62LevelSound(var4, var6, var8, var10, (float)var12, (float)var14));
/*     */     } 
/*     */     
/* 108 */     notifyAdmins(par1ICommandSender, "commands.playsound.success", new Object[] { var4, var5.getEntityName() });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUsernameIndex(String[] par1ArrayOfStr, int par2) {
/* 117 */     return (par2 == 1);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandPlaySound.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */