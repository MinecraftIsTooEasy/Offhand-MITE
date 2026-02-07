/*    */ package net.minecraft;
/*    */ 
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ public class DemoWorldServer
/*    */   extends WorldServer {
/*  7 */   private static final long demoWorldSeed = "North Carolina".hashCode();
/*    */   
/*  9 */   public static final WorldSettings demoWorldSettings = (new WorldSettings(demoWorldSeed, EnumGameType.SURVIVAL, true, false, WorldType.DEFAULT, false)).enableBonusChest();
/*    */ 
/*    */   
/*    */   public DemoWorldServer(MinecraftServer par1MinecraftServer, ISaveHandler par2ISaveHandler, String par3Str, int par4, Profiler par5Profiler, ILogAgent par6ILogAgent) {
/* 13 */     super(par1MinecraftServer, par2ISaveHandler, par3Str, par4, demoWorldSettings, par5Profiler, par6ILogAgent);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DemoWorldServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */