/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class BlockDeadBush
/*     */   extends BlockFlower
/*     */ {
/*   8 */   public static final String[] types = new String[] { "deadbush", "witherwood" };
/*   9 */   private Icon[] icons = new Icon[types.length];
/*     */ 
/*     */   
/*     */   protected BlockDeadBush(int par1) {
/*  13 */     super(par1, Material.vine);
/*  14 */     float var2 = 0.4F;
/*  15 */     setBlockBoundsForAllThreads((0.5F - var2), 0.0D, (0.5F - var2), (0.5F + var2), 0.800000011920929D, (0.5F + var2));
/*     */     
/*  17 */     setCushioning(0.2F);
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
/*     */   public boolean canOccurAt(World world, int x, int y, int z, int metadata) {
/*  31 */     if (!isWitherwood(metadata) && !world.canBlockSeeTheSky(x, y, z)) {
/*  32 */       return false;
/*     */     }
/*  34 */     return super.canOccurAt(world, x, y, z, metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
/*  39 */     return isWitherwood(metadata) ? BlockGravel.isNetherGravel(block_below, block_below_metadata) : ((block_below == sand));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinAllowedLightValue() {
/*  44 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isWitherwood(int metadata) {
/*  50 */     return isWitherwood(this, metadata);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isWitherwood(Block block, int metadata) {
/*  56 */     return (block == deadBush && block.getBlockSubtype(metadata) == 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadataNotes() {
/*  61 */     return "0=Regular, 1=Witherwood";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidMetadata(int metadata) {
/*  66 */     return (metadata >= 0 && metadata < 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockSubtypeUnchecked(int metadata) {
/*  71 */     return metadata & 0x1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/*  82 */     for (int i = 0; i < types.length; i++) {
/*  83 */       this.icons[i] = par1IconRegister.registerIcon(types[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public Icon getIcon(int side, int metadata) {
/*  88 */     return this.icons[getBlockSubtype(metadata)];
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
/*     */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/*     */     float chance_of_stick;
/* 136 */     if (isWitherwood(info.getMetadata())) {
/*     */       
/* 138 */       if (info.wasNotLegal() || info.wasSelfDropped()) {
/* 139 */         return super.dropBlockAsEntityItem(info);
/*     */       }
/* 141 */       chance_of_stick = 0.5F;
/*     */     }
/*     */     else {
/*     */       
/* 145 */       if (info.wasNotLegal()) {
/* 146 */         info.world.destroyBlock(info, false);
/*     */       }
/* 148 */       chance_of_stick = 0.05F;
/*     */     } 
/*     */     
/* 151 */     return dropBlockAsEntityItem(info, Item.stick.itemID, 0, 1, chance_of_stick);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
/* 158 */     if (par1World.isWorldServer() && par5Entity instanceof EntityLivingBase) {
/*     */       
/* 160 */       int metadata = par1World.getBlockMetadata(par2, par3, par4);
/*     */       
/* 162 */       if (isWitherwood(metadata)) {
/*     */ 
/*     */ 
/*     */         
/* 166 */         PotionEffect potion_effect = par5Entity.getAsEntityLivingBase().getActivePotionEffect(Potion.wither);
/*     */         
/* 168 */         if (potion_effect == null) {
/*     */           
/* 170 */           par5Entity.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.wither.id, 200, 0));
/*     */         }
/* 172 */         else if (potion_effect.getAmplifier() < 1) {
/*     */           
/* 174 */           if (potion_effect.getDuration() + potion_effect.getEffectInterval() <= 200) {
/* 175 */             potion_effect.setDuration(potion_effect.getDuration() + potion_effect.getEffectInterval());
/*     */           }
/* 177 */           potion_effect.setAmplifier(Math.max(potion_effect.getAmplifier(), 0));
/*     */           
/* 179 */           if (par5Entity.isEntityPlayer()) {
/* 180 */             par5Entity.getAsPlayer().sendPacket((new Packet85SimpleSignal(EnumSignal.update_potion_effect)).setByte(potion_effect.getPotionID()).setShort(potion_effect.getAmplifier()).setInteger(potion_effect.getDuration()));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void addWitherEffect(EntityLivingBase entity_living_base) {
/* 188 */     entity_living_base.addPotionEffect(new PotionEffect(Potion.wither.id, 200, 0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World world, int x, int y, int z, Random random) {
/* 193 */     if (!isWitherwood(world.getBlockMetadata(x, y, z))) {
/*     */       return;
/*     */     }
/* 196 */     spawnWitherwoodParticles(world, x, y, z, random);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void spawnWitherwoodParticles(World world, int x, int y, int z, Random random) {
/* 201 */     Random var5 = world.rand;
/*     */     
/* 203 */     int num = var5.nextInt(3) + 1;
/*     */     
/* 205 */     for (int i = 0; i < num; i++)
/* 206 */       world.spawnParticle(EnumParticle.sacred, (x + var5.nextFloat()), (y + var5.nextFloat()), (z + var5.nextFloat()), 0.0D, 0.0D, 0.0D); 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockDeadBush.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */