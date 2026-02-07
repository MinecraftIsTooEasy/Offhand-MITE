/*     */ package net.minecraft.client.main;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.net.Authenticator;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.Proxy;
/*     */ import java.util.List;
/*     */ import joptsimple.ArgumentAcceptingOptionSpec;
/*     */ import joptsimple.NonOptionArgumentSpec;
/*     */ import joptsimple.OptionParser;
/*     */ import joptsimple.OptionSet;
/*     */ import joptsimple.OptionSpec;
/*     */ import net.minecraft.Entity;
/*     */ import net.minecraft.MainProxyAuthenticator;
/*     */ import net.minecraft.MainShutdownHook;
/*     */ import net.minecraft.Minecraft;
/*     */ import net.minecraft.NetHandler;
/*     */ import net.minecraft.NetServerHandler;
/*     */ import net.minecraft.Packet;
/*     */ import net.minecraft.Packet0KeepAlive;
/*     */ import net.minecraft.Packet13PlayerLookMove;
/*     */ import net.minecraft.Packet1Login;
/*     */ import net.minecraft.Packet254ServerPing;
/*     */ import net.minecraft.Packet255KickDisconnect;
/*     */ import net.minecraft.Packet2ClientProtocol;
/*     */ import net.minecraft.Packet3Chat;
/*     */ import net.minecraft.Packet4UpdateTime;
/*     */ import net.minecraft.Packet85SimpleSignal;
/*     */ import net.minecraft.Session;
/*     */ import net.minecraft.StringHelper;
/*     */ import net.minecraft.TextureManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Main
/*     */ {
/*     */   static {
/*  58 */     TextureManager.unloadTextures();
/*     */   }
/*     */   
/*  61 */   private static final String[] option_ds = new String[] { "ds", "MITE Dedicated Server mode enabled" };
/*  62 */   private static final String[] option_packet52 = new String[] { "packet52", "regular Packet52 chunk updating will be used" };
/*  63 */   private static final String[] option_ntf = new String[] { "ntf", "no time forwarding" };
/*  64 */   private static final String[] option_drum2 = new String[] { "drum2", "renderUpdateMethod2 disabled" };
/*  65 */   private static final String[] option_ndp = new String[] { "ndp", "no downtime processing" };
/*     */   
/*     */   public static boolean is_MITE_DS;
/*     */   
/*     */   public static boolean no_time_forwarding;
/*     */   
/*     */   public static boolean disable_render_update_method_2;
/*     */   
/*     */   public static boolean no_downtime_processing;
/*     */   public static final String MITE_DS_username = "Dedicated_Server";
/*  75 */   public static final Class[] packets_that_MITE_DS_client_player_can_send_or_receive = new Class[] { Packet0KeepAlive.class, Packet1Login.class, Packet2ClientProtocol.class, Packet3Chat.class, Packet4UpdateTime.class, Packet13PlayerLookMove.class, Packet85SimpleSignal.class, Packet254ServerPing.class, Packet255KickDisconnect.class };
/*     */ 
/*     */   
/*     */   private static void printOption(String[] option) {
/*  79 */     System.out.println("OPTION SPECIFIED: " + option[0] + " (" + option[1] + ")");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] par0ArrayOfStr) {
/*     */     Session var31;
/*  86 */     if (Minecraft.inDevMode()) {
/*  87 */       System.out.println("Command line arguments: " + StringHelper.implode(par0ArrayOfStr, " "));
/*     */     }
/*  89 */     System.setProperty("java.net.preferIPv4Stack", "true");
/*  90 */     OptionParser var1 = new OptionParser();
/*  91 */     var1.allowsUnrecognizedOptions();
/*  92 */     var1.accepts("demo");
/*  93 */     var1.accepts("fullscreen");
/*  94 */     var1.accepts(option_ds[0]);
/*  95 */     var1.accepts(option_packet52[0]);
/*  96 */     var1.accepts(option_ntf[0]);
/*  97 */     var1.accepts(option_drum2[0]);
/*  98 */     var1.accepts(option_ndp[0]);
/*  99 */     ArgumentAcceptingOptionSpec var2 = var1.accepts("server").withRequiredArg();
/* 100 */     ArgumentAcceptingOptionSpec var3 = var1.accepts("port").withRequiredArg().ofType(Integer.class).defaultsTo(Integer.valueOf(25565), (Object[])new Integer[0]);
/* 101 */     ArgumentAcceptingOptionSpec var4 = var1.accepts("gameDir").withRequiredArg().ofType(File.class).defaultsTo(new File("."), (Object[])new File[0]);
/* 102 */     ArgumentAcceptingOptionSpec var5 = var1.accepts("assetsDir").withRequiredArg().ofType(File.class);
/* 103 */     ArgumentAcceptingOptionSpec var6 = var1.accepts("resourcePackDir").withRequiredArg().ofType(File.class);
/* 104 */     ArgumentAcceptingOptionSpec var7 = var1.accepts("proxyHost").withRequiredArg();
/* 105 */     ArgumentAcceptingOptionSpec var8 = var1.accepts("proxyPort").withRequiredArg().defaultsTo("8080", (Object[])new String[0]).ofType(Integer.class);
/* 106 */     ArgumentAcceptingOptionSpec var9 = var1.accepts("proxyUser").withRequiredArg();
/* 107 */     ArgumentAcceptingOptionSpec var10 = var1.accepts("proxyPass").withRequiredArg();
/* 108 */     ArgumentAcceptingOptionSpec var11 = var1.accepts("username").withRequiredArg().defaultsTo("Player" + (Minecraft.getSystemTime() % 1000L), (Object[])new String[0]);
/*     */     
/* 110 */     ArgumentAcceptingOptionSpec var12 = var1.accepts("session").withRequiredArg();
/* 111 */     ArgumentAcceptingOptionSpec var13 = var1.accepts("version").withRequiredArg().required();
/* 112 */     ArgumentAcceptingOptionSpec var14 = var1.accepts("width").withRequiredArg().ofType(Integer.class).defaultsTo(Integer.valueOf(854), (Object[])new Integer[0]);
/* 113 */     ArgumentAcceptingOptionSpec var15 = var1.accepts("height").withRequiredArg().ofType(Integer.class).defaultsTo(Integer.valueOf(480), (Object[])new Integer[0]);
/* 114 */     ArgumentAcceptingOptionSpec access_token_arg = var1.accepts("accessToken").withRequiredArg();
/* 115 */     ArgumentAcceptingOptionSpec uuid_arg = var1.accepts("uuid").withRequiredArg();
/* 116 */     NonOptionArgumentSpec var16 = var1.nonOptions();
/* 117 */     OptionSet var17 = var1.parse(par0ArrayOfStr);
/* 118 */     List var18 = var17.valuesOf((OptionSpec)var16);
/* 119 */     String var19 = (String)var17.valueOf((OptionSpec)var7);
/* 120 */     Proxy var20 = Proxy.NO_PROXY;
/*     */     
/* 122 */     if (var19 != null) {
/*     */       
/*     */       try {
/*     */         
/* 126 */         var20 = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(var19, ((Integer)var17.valueOf((OptionSpec)var8)).intValue()));
/*     */       }
/* 128 */       catch (Exception var34) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 134 */     String var21 = (String)var17.valueOf((OptionSpec)var9);
/* 135 */     String var22 = (String)var17.valueOf((OptionSpec)var10);
/*     */     
/* 137 */     if (!var20.equals(Proxy.NO_PROXY) && func_110121_a(var21) && func_110121_a(var22))
/*     */     {
/* 139 */       Authenticator.setDefault((Authenticator)new MainProxyAuthenticator(var21, var22));
/*     */     }
/*     */     
/* 142 */     int var23 = ((Integer)var17.valueOf((OptionSpec)var14)).intValue();
/* 143 */     int var24 = ((Integer)var17.valueOf((OptionSpec)var15)).intValue();
/* 144 */     boolean var25 = var17.has("fullscreen");
/* 145 */     boolean var26 = var17.has("demo");
/* 146 */     String var27 = (String)var17.valueOf((OptionSpec)var13);
/* 147 */     File var28 = (File)var17.valueOf((OptionSpec)var4);
/* 148 */     File var29 = var17.has((OptionSpec)var5) ? (File)var17.valueOf((OptionSpec)var5) : new File(var28, "assets/");
/* 149 */     File var30 = var17.has((OptionSpec)var6) ? (File)var17.valueOf((OptionSpec)var6) : new File(var28, "resourcepacks/");
/*     */     
/* 151 */     String session = (String)var12.value(var17);
/*     */     
/* 153 */     if (session == null || (!session.equals("-") && !session.contains("token:"))) {
/*     */       
/* 155 */       if (Minecraft.inDevMode()) {
/* 156 */         System.out.println("Missing argument: session");
/*     */       }
/* 158 */       String access_token = (String)access_token_arg.value(var17);
/* 159 */       String uuid = (String)uuid_arg.value(var17);
/*     */       
/* 161 */       if (access_token != null && !access_token.isEmpty() && uuid != null && !uuid.isEmpty()) {
/* 162 */         session = "token:" + access_token + ":" + uuid;
/*     */       }
/* 164 */       if (Minecraft.inDevMode()) {
/* 165 */         System.out.println("Attempting to construct \"session\" argument. Result: session=" + ((session == null) ? session : ("\"" + session + "\"")));
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 172 */     if (is_MITE_DS = var17.has(option_ds[0])) {
/* 173 */       printOption(option_ds);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 178 */     if (no_time_forwarding = var17.has(option_ntf[0])) {
/* 179 */       printOption(option_ntf);
/*     */     }
/* 181 */     if (disable_render_update_method_2 = var17.has(option_drum2[0])) {
/* 182 */       printOption(option_drum2);
/*     */     }
/* 184 */     if (no_downtime_processing = var17.has(option_ndp[0])) {
/* 185 */       printOption(option_ndp);
/*     */     }
/* 187 */     TextureManager.preloadTextures();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 193 */     if (is_MITE_DS) {
/*     */ 
/*     */       
/* 196 */       var31 = new Session("Dedicated_Server", session);
/* 197 */       var25 = false;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 202 */       var31 = new Session((String)var11.value(var17), session);
/*     */     } 
/*     */     
/* 205 */     Minecraft var32 = new Minecraft(var31, var23, var24, var25, var26, var28, var29, var30, var20, var27);
/* 206 */     String var33 = (String)var17.valueOf((OptionSpec)var2);
/*     */     
/* 208 */     if (var33 != null)
/*     */     {
/* 210 */       var32.setServer(var33, ((Integer)var17.valueOf((OptionSpec)var3)).intValue());
/*     */     }
/*     */     
/* 213 */     Runtime.getRuntime().addShutdownHook((Thread)new MainShutdownHook());
/*     */     
/* 215 */     if (!var18.isEmpty())
/*     */     {
/* 217 */       System.out.println("Completely ignored arguments: " + var18);
/*     */     }
/*     */     
/* 220 */     Thread.currentThread().setName("Minecraft main thread");
/* 221 */     Minecraft.client_thread = Thread.currentThread();
/* 222 */     var32.run();
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean func_110121_a(String par0Str) {
/* 227 */     return (par0Str != null && !par0Str.isEmpty());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isPacketThatMITEDSClientPlayerCanSendOrReceive(Packet packet) {
/* 232 */     Class<?> packet_class = packet.getClass();
/*     */     
/* 234 */     for (int i = 0; i < packets_that_MITE_DS_client_player_can_send_or_receive.length; i++) {
/*     */       
/* 236 */       if (packets_that_MITE_DS_client_player_can_send_or_receive[i] == packet_class) {
/* 237 */         return true;
/*     */       }
/*     */     } 
/* 240 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isPacketIgnored(NetHandler net_handler, Packet packet) {
/* 245 */     if (net_handler instanceof net.minecraft.NetClientHandler) {
/*     */       
/* 247 */       if (is_MITE_DS) {
/* 248 */         return !isPacketThatMITEDSClientPlayerCanSendOrReceive(packet);
/*     */       }
/* 250 */     } else if (net_handler instanceof NetServerHandler) {
/*     */       
/* 252 */       if (((NetServerHandler)net_handler).playerEntity.isGhost()) {
/* 253 */         return !isPacketThatMITEDSClientPlayerCanSendOrReceive(packet);
/*     */       }
/* 255 */     } else if (net_handler instanceof net.minecraft.NetLoginHandler) {
/*     */     
/*     */     } 
/*     */ 
/*     */     
/* 260 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 265 */     Entity.resetEntityIds();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\client\main\Main.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */