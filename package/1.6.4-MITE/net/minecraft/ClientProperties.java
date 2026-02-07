/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.File;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClientProperties
/*    */ {
/*    */   private static ClientProperties instance;
/*    */   private int rendering_scheme;
/*    */   private boolean opportunity_chunk_rendering;
/*    */   private int forced_chunk_rendering_distance;
/*    */   private String public_servers_update_url;
/*    */   private static final int DEFAULT_RENDERING_SCHEME = 1;
/*    */   private static final boolean DEFAULT_OPPORTUNITY_CHUNK_RENDERING = true;
/*    */   private static final int DEFAULT_FORCED_CHUNK_RENDERING_DISTANCE = 1;
/*    */   private static final String DEFAULT_PUBLIC_SERVERS_UPDATE_URL = "http://minecraft-is-too-easy.com/public_servers/public_servers.txt";
/*    */   
/*    */   public ClientProperties(String filepath, ILogAgent log_agent) {
/* 23 */     readPropertiesFromFile(filepath, log_agent);
/*    */     
/* 25 */     if (instance != null) {
/*    */       
/* 27 */       Minecraft.setErrorMessage("ClientProperties: instance already exists");
/* 28 */       (new Exception()).printStackTrace();
/*    */     } 
/*    */     
/* 31 */     instance = this;
/*    */   }
/*    */ 
/*    */   
/*    */   private void readPropertiesFromFile(String filepath, ILogAgent log_agent) {
/* 36 */     File file = new File(filepath);
/*    */     
/* 38 */     if (!file.exists()) {
/*    */       
/*    */       try {
/*    */         
/* 42 */         file.createNewFile();
/*    */       }
/* 44 */       catch (Exception e) {
/*    */         
/* 46 */         log_agent.logSevere("Unable to create " + filepath + " file!");
/*    */       } 
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 52 */     log_agent.logInfo("Loading " + filepath);
/* 53 */     PropertyManager settings = new PropertyManager(file, log_agent, "Minecraft client properties");
/*    */     
/* 55 */     RenderingScheme.setCurrent(this.rendering_scheme = settings.getIntProperty("rendering-scheme", 1));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 69 */     this.opportunity_chunk_rendering = false;
/* 70 */     this.forced_chunk_rendering_distance = 1;
/*    */     
/* 72 */     this.public_servers_update_url = settings.getProperty("public-servers-update-url", "http://minecraft-is-too-easy.com/public_servers/public_servers.txt");
/*    */     
/* 74 */     settings.saveProperties();
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isOpportunityChunkRenderingEnabled() {
/* 79 */     return instance.opportunity_chunk_rendering;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static int getForcedChunkRenderingDistance() {
/* 85 */     return instance.forced_chunk_rendering_distance;
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getPublicServersUpdateURL() {
/* 90 */     return instance.public_servers_update_url;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ClientProperties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */