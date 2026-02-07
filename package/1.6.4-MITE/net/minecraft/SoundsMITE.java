/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SoundsMITE
/*     */ {
/*     */   private SoundManager sndManager;
/*  17 */   private List<String> sounds = new ArrayList<String>();
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
/*     */   public boolean loaded = false;
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
/*     */   public SoundsMITE(SoundManager sndManager) {
/*  59 */     this.sndManager = sndManager;
/*     */     
/*  61 */     add("sound/imported/liquid/block_splash.ogg");
/*     */     
/*  63 */     add("sound/imported/random/sizzle.ogg");
/*  64 */     add("sound/imported/random/boil.ogg");
/*  65 */     add("sound/imported/random/level_drain.ogg");
/*  66 */     add("sound/imported/random/camera.ogg");
/*  67 */     add("sound/imported/random/chest_locked.ogg");
/*     */     
/*  69 */     add("sound/imported/random/book_open.ogg");
/*  70 */     add("sound/imported/random/book_page.ogg");
/*  71 */     add("sound/imported/random/book_close.ogg");
/*     */     
/*  73 */     add("sound/imported/random/cow_alarm1.ogg");
/*  74 */     add("sound/imported/random/cow_alarm2.ogg");
/*     */     
/*  76 */     add("sound/imported/random/gunshot.ogg");
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
/*  88 */     add("sound/imported/mob/wolf/howl1.ogg");
/*  89 */     add("sound/imported/mob/wolf/howl2.ogg");
/*  90 */     add("sound/imported/mob/wolf/howl3.ogg");
/*     */     
/*  92 */     add("sound/imported/mob/demonspider/death.ogg");
/*  93 */     add("sound/imported/mob/demonspider/hurt1.ogg");
/*  94 */     add("sound/imported/mob/demonspider/hurt2.ogg");
/*  95 */     add("sound/imported/mob/demonspider/say1.ogg");
/*  96 */     add("sound/imported/mob/demonspider/say2.ogg");
/*  97 */     add("sound/imported/mob/demonspider/say3.ogg");
/*     */     
/*  99 */     add("sound/imported/mob/ghoul/death.ogg");
/* 100 */     add("sound/imported/mob/ghoul/hurt1.ogg");
/* 101 */     add("sound/imported/mob/ghoul/hurt2.ogg");
/* 102 */     add("sound/imported/mob/ghoul/say1.ogg");
/* 103 */     add("sound/imported/mob/ghoul/say2.ogg");
/*     */     
/* 105 */     add("sound/imported/mob/hellhound/death.ogg");
/* 106 */     add("sound/imported/mob/hellhound/hurt1.ogg");
/* 107 */     add("sound/imported/mob/hellhound/hurt2.ogg");
/* 108 */     add("sound/imported/mob/hellhound/say1.ogg");
/* 109 */     add("sound/imported/mob/hellhound/say2.ogg");
/* 110 */     add("sound/imported/mob/hellhound/say3.ogg");
/* 111 */     add("sound/imported/mob/hellhound/breath.ogg");
/*     */     
/* 113 */     add("sound/imported/mob/invisiblestalker/death.ogg");
/* 114 */     add("sound/imported/mob/invisiblestalker/hurt1.ogg");
/* 115 */     add("sound/imported/mob/invisiblestalker/hurt2.ogg");
/* 116 */     add("sound/imported/mob/invisiblestalker/say1.ogg");
/* 117 */     add("sound/imported/mob/invisiblestalker/say2.ogg");
/* 118 */     add("sound/imported/mob/invisiblestalker/say3.ogg");
/*     */     
/* 120 */     add("sound/imported/mob/wight/death.ogg");
/* 121 */     add("sound/imported/mob/wight/hurt1.ogg");
/* 122 */     add("sound/imported/mob/wight/hurt2.ogg");
/* 123 */     add("sound/imported/mob/wight/say1.ogg");
/* 124 */     add("sound/imported/mob/wight/say2.ogg");
/*     */     
/* 126 */     add("sound/imported/mob/shadow/death1.ogg");
/* 127 */     add("sound/imported/mob/shadow/death2.ogg");
/* 128 */     add("sound/imported/mob/shadow/hurt1.ogg");
/* 129 */     add("sound/imported/mob/shadow/hurt2.ogg");
/* 130 */     add("sound/imported/mob/shadow/say1.ogg");
/* 131 */     add("sound/imported/mob/shadow/say2.ogg");
/* 132 */     add("sound/imported/mob/shadow/say3.ogg");
/*     */     
/* 134 */     add("sound/imported/mob/witch/death.ogg");
/* 135 */     add("sound/imported/mob/witch/hurt.ogg");
/* 136 */     add("sound/imported/mob/witch/cackle1.ogg");
/* 137 */     add("sound/imported/mob/witch/cackle2.ogg");
/* 138 */     add("sound/imported/mob/witch/cackle3.ogg");
/*     */     
/* 140 */     add("sound/imported/portal/runegate.ogg");
/*     */     
/* 142 */     add("records/imported/underworld.ogg");
/* 143 */     add("records/imported/descent.ogg");
/* 144 */     add("records/imported/wanderer.ogg");
/* 145 */     add("records/imported/legends.ogg");
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean add(String path) {
/* 150 */     return this.sounds.add(path);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean load() {
/* 155 */     if (this.loaded) {
/*     */       
/* 157 */       System.err.println();
/* 158 */       System.err.println("SoundsMITE: sounds have already been loaded!");
/* 159 */       return true;
/*     */     } 
/*     */     
/* 162 */     System.out.println();
/* 163 */     System.out.print("SoundsMITE: Loading sounds...");
/*     */     
/* 165 */     boolean errors = false;
/*     */ 
/*     */     
/* 168 */     ResourcePack MITE_resource_pack = Minecraft.MITE_resource_pack;
/*     */     
/* 170 */     if (MITE_resource_pack == null) {
/*     */       
/* 172 */       Minecraft.setErrorMessage("\nSoundsMITE: MITE Resource Pack 1.6.4 needs to be loaded!");
/* 173 */       errors = true;
/*     */     }
/*     */     else {
/*     */       
/* 177 */       Iterator<String> i = this.sounds.iterator();
/*     */       
/* 179 */       while (i.hasNext()) {
/*     */         
/* 181 */         String sound = i.next();
/*     */         
/* 183 */         if (MITE_resource_pack.resourceExists(new ResourceLocation(sound))) {
/*     */           
/* 185 */           loadMITESound(sound);
/*     */           
/*     */           continue;
/*     */         } 
/* 189 */         if (!errors) {
/* 190 */           System.err.println();
/*     */         }
/* 192 */         Minecraft.setErrorMessage("SoundsMITE: sound " + sound + " not found in MITE Resource Pack " + "1.6.4" + "!");
/*     */         
/* 194 */         errors = true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 199 */     if (errors) {
/* 200 */       System.out.println("SoundsMITE: Finished loading sounds with errors.");
/*     */     } else {
/* 202 */       System.out.println(" [ok]");
/*     */     } 
/* 204 */     return this.loaded;
/*     */   }
/*     */ 
/*     */   
/*     */   private void loadMITESound(String path) {
/* 209 */     int var3 = path.indexOf("/");
/*     */     
/* 211 */     if (var3 != -1) {
/*     */       
/* 213 */       String var4 = path.substring(0, var3);
/* 214 */       path = path.substring(var3 + 1);
/*     */       
/* 216 */       if ("sound".equalsIgnoreCase(var4)) {
/*     */         
/* 218 */         this.sndManager.addSound(path);
/*     */       }
/* 220 */       else if ("records".equalsIgnoreCase(var4)) {
/*     */         
/* 222 */         this.sndManager.addStreaming(path);
/*     */       }
/* 224 */       else if ("music".equalsIgnoreCase(var4)) {
/*     */         
/* 226 */         this.sndManager.addMusic(path);
/*     */       } 
/*     */     } 
/*     */     
/* 230 */     this.loaded = true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SoundsMITE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */