/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ public class ItemLeash
/*     */   extends Item
/*     */ {
/*     */   public ItemLeash(int par1) {
/*  11 */     super(par1, new Material[] { Material.silk, Material.slime }, "lead");
/*  12 */     setCreativeTab(CreativeTabs.tabTools);
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean tryEntityInteraction(Entity entity, EntityPlayer player, ItemStack item_stack) {
/*  43 */     if (entity instanceof EntityLiving) {
/*     */       
/*  45 */       EntityLiving entity_living = entity.getAsEntityLiving();
/*     */       
/*  47 */       if (entity_living.allowLeashing() && !entity_living.getLeashed()) {
/*     */         
/*  49 */         boolean leashing_prevented = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  59 */         if (!leashing_prevented) {
/*     */           
/*  61 */           if (player.onClient()) {
/*     */             
/*  63 */             player.suppressNextArmSwing();
/*     */           }
/*     */           else {
/*     */             
/*  67 */             entity_living.setLeashedToEntity(player, true);
/*     */             
/*  69 */             if (!player.inCreativeMode()) {
/*  70 */               player.convertOneOfHeldItem((ItemStack)null);
/*     */             }
/*     */           } 
/*  73 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  78 */     return false;
/*     */   }
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
/*     */   public static boolean tryTieingLeashedEntitiesToBlock(EntityPlayer player, int x, int y, int z) {
/* 163 */     List leashed_entities = getEntitiesThatAreLeashedToEntity(player);
/*     */     
/* 165 */     if (leashed_entities.size() == 0) {
/* 166 */       return false;
/*     */     }
/* 168 */     if (player.onClient()) {
/* 169 */       return true;
/*     */     }
/* 171 */     World world = player.getWorld();
/*     */     
/* 173 */     EntityLeashKnot knot = EntityLeashKnot.getKnotForBlock(world, x, y, z);
/*     */     
/* 175 */     if (knot == null) {
/* 176 */       knot = EntityLeashKnot.func_110129_a(world, x, y, z);
/*     */     }
/* 178 */     return leashEntitiesToEntity(leashed_entities, knot, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static List getEntitiesThatAreLeashedToEntity(Entity entity) {
/* 183 */     World world = entity.worldObj;
/*     */     
/* 185 */     float radius = 7.0F;
/* 186 */     List nearby_living_entities = world.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getAABBPool().getAABB(entity.posX - radius, entity.posY - radius, entity.posZ - radius, entity.posX + radius, entity.posY + radius, entity.posZ + radius));
/*     */     
/* 188 */     if (nearby_living_entities == null) {
/* 189 */       return null;
/*     */     }
/* 191 */     Iterator<EntityLiving> i = nearby_living_entities.iterator();
/*     */     
/* 193 */     while (i.hasNext()) {
/*     */       
/* 195 */       EntityLiving entity_living = i.next();
/*     */       
/* 197 */       if (!entity_living.getLeashed() || entity_living.getLeashedToEntity() != entity) {
/* 198 */         i.remove();
/*     */       }
/*     */     } 
/* 201 */     return nearby_living_entities;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean unleashEntitiesThatAreLeashedToEntity(Entity entity, boolean drop_leash_items, boolean send_packet_to_tracking_players) {
/* 206 */     List<EntityLiving> leashed_entities = getEntitiesThatAreLeashedToEntity(entity);
/*     */     
/* 208 */     if (entity.onServer())
/*     */     {
/* 210 */       for (int i = 0; i < leashed_entities.size(); i++) {
/*     */         
/* 212 */         EntityLiving entity_living = leashed_entities.get(i);
/* 213 */         entity_living.clearLeashed(drop_leash_items, send_packet_to_tracking_players);
/*     */       } 
/*     */     }
/*     */     
/* 217 */     return (leashed_entities.size() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean leashEntitiesToEntity(List<EntityLiving> leashed_entities, Entity entity, boolean send_packet_to_tracking_players) {
/* 223 */     if (entity.onServer())
/*     */     {
/* 225 */       for (int i = 0; i < leashed_entities.size(); i++) {
/*     */         
/* 227 */         EntityLiving entity_living = leashed_entities.get(i);
/* 228 */         entity_living.setLeashedToEntity(entity, send_packet_to_tracking_players);
/*     */       } 
/*     */     }
/*     */     
/* 232 */     return (leashed_entities.size() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean transferLeashedEntitiesToAnotherEntity(Entity source_entity, Entity target_entity, boolean send_packet_to_tracking_players) {
/* 237 */     return leashEntitiesToEntity(getEntitiesThatAreLeashedToEntity(source_entity), target_entity, send_packet_to_tracking_players);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemLeash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */