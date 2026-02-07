/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Hashtable;
/*    */ import javax.naming.directory.Attributes;
/*    */ import javax.naming.directory.InitialDirContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServerAddress
/*    */ {
/*    */   private final String ipAddress;
/*    */   private final int serverPort;
/*    */   
/*    */   private ServerAddress(String string, int i) {
/* 15 */     this.ipAddress = string;
/* 16 */     this.serverPort = i;
/*    */   }
/*    */   
/*    */   public String getIP() {
/* 20 */     return this.ipAddress;
/*    */   }
/*    */   
/*    */   public int getPort() {
/* 24 */     return this.serverPort;
/*    */   }
/*    */   
/*    */   public static ServerAddress func_78860_a(String string) {
/* 28 */     if (string == null) return null;
/*    */     
/* 30 */     String[] arrayOfString = string.split(":");
/*    */     
/* 32 */     if (string.startsWith("[")) {
/* 33 */       int j = string.indexOf("]");
/* 34 */       if (j > 0) {
/* 35 */         String str1 = string.substring(1, j);
/* 36 */         String str2 = string.substring(j + 1).trim();
/* 37 */         if (str2.startsWith(":") && str2.length() > 0) {
/* 38 */           str2 = str2.substring(1);
/* 39 */           arrayOfString = new String[2];
/* 40 */           arrayOfString[0] = str1;
/* 41 */           arrayOfString[1] = str2;
/*    */         } else {
/* 43 */           arrayOfString = new String[1];
/* 44 */           arrayOfString[0] = str1;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 49 */     if (arrayOfString.length > 2) {
/* 50 */       arrayOfString = new String[1];
/* 51 */       arrayOfString[0] = string;
/*    */     } 
/*    */     
/* 54 */     String str = arrayOfString[0];
/* 55 */     int i = (arrayOfString.length > 1) ? parseIntWithDefault(arrayOfString[1], 25565) : 25565;
/*    */     
/* 57 */     if (i == 25565) {
/* 58 */       String[] arrayOfString1 = getServerAddress(str);
/* 59 */       str = arrayOfString1[0];
/* 60 */       i = parseIntWithDefault(arrayOfString1[1], 25565);
/*    */     } 
/*    */     
/* 63 */     return new ServerAddress(str, i);
/*    */   }
/*    */   
/*    */   private static String[] getServerAddress(String string) {
/*    */     try {
/* 68 */       String str = "com.sun.jndi.dns.DnsContextFactory";
/*    */       
/* 70 */       Class.forName("com.sun.jndi.dns.DnsContextFactory");
/*    */       
/* 72 */       Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
/* 73 */       hashtable.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
/* 74 */       hashtable.put("java.naming.provider.url", "dns:");
/* 75 */       hashtable.put("com.sun.jndi.dns.timeout.retries", "1");
/* 76 */       InitialDirContext initialDirContext = new InitialDirContext(hashtable);
/* 77 */       Attributes attributes = initialDirContext.getAttributes("_minecraft._tcp." + string, new String[] { "SRV" });
/* 78 */       String[] arrayOfString = attributes.get("srv").get().toString().split(" ", 4);
/* 79 */       return new String[] { arrayOfString[3], arrayOfString[2] };
/* 80 */     } catch (Throwable throwable) {
/* 81 */       return new String[] { string, Integer.toString(25565) };
/*    */     } 
/*    */   }
/*    */   
/*    */   private static int parseIntWithDefault(String string, int i) {
/*    */     try {
/* 87 */       return Integer.parseInt(string.trim());
/* 88 */     } catch (Exception exception) {
/*    */ 
/*    */       
/* 91 */       return i;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ServerAddress.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */