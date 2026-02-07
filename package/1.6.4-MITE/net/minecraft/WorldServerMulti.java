/*    */ package net.minecraft;
/*    */ 
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ public class WorldServerMulti
/*    */   extends WorldServer
/*    */ {
/*    */   public WorldServerMulti(MinecraftServer par1MinecraftServer, ISaveHandler par2ISaveHandler, String par3Str, int par4, WorldSettings par5WorldSettings, WorldServer par6WorldServer, Profiler par7Profiler, ILogAgent par8ILogAgent) {
/*  9 */     super(par1MinecraftServer, par2ISaveHandler, par3Str, par4, par5WorldSettings, par7Profiler, par8ILogAgent);
/* 10 */     this.mapStorage = par6WorldServer.mapStorage;
/* 11 */     this.worldScoreboard = par6WorldServer.getScoreboard();
/*    */ 
/*    */     
/* 14 */     this.worldInfo = new WorldInfo(par6WorldServer.getWorldInfo(), par4);
/*    */   }
/*    */   
/*    */   protected void saveLevel() throws MinecraftException {}
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldServerMulti.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */