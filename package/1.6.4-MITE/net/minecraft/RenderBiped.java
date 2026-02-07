/*     */ package net.minecraft;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public abstract class RenderBiped
/*     */   extends RenderLiving
/*     */ {
/*     */   protected ModelBiped modelBipedMain;
/*     */   protected float field_77070_b;
/*     */   protected ModelBiped field_82423_g;
/*     */   protected ModelBiped field_82425_h;
/*  14 */   private static final Map field_110859_k = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderBiped(ModelBiped par1ModelBiped, float par2) {
/*  22 */     this(par1ModelBiped, par2, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public RenderBiped(ModelBiped par1ModelBiped, float par2, float par3) {
/*  27 */     super(par1ModelBiped, par2);
/*  28 */     this.modelBipedMain = par1ModelBiped;
/*  29 */     this.field_77070_b = par3;
/*  30 */     func_82421_b();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_82421_b() {
/*  35 */     this.field_82423_g = new ModelBiped(1.0F);
/*  36 */     this.field_82425_h = new ModelBiped(0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ResourceLocation func_110857_a(ItemArmor par0ItemArmor, int par1) {
/*  41 */     return func_110858_a(par0ItemArmor, par1, (String)null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ResourceLocation func_110858_a(ItemArmor par0ItemArmor, int par1, String par2Str) {
/*  47 */     String var3 = String.format("textures/models/armor/%s_layer_%d%s.png", new Object[] { par0ItemArmor.getTextureFilenamePrefix(), Integer.valueOf((par1 == 2) ? 2 : 1), (par2Str == null) ? "" : String.format("_%s", new Object[] { par2Str }) });
/*  48 */     ResourceLocation var4 = (ResourceLocation)field_110859_k.get(var3);
/*     */     
/*  50 */     if (var4 == null) {
/*     */       
/*  52 */       var4 = new ResourceLocation(var3);
/*  53 */       field_110859_k.put(var3, var4);
/*     */     } 
/*     */     
/*  56 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_130006_a(EntityLiving par1EntityLiving, int par2, float par3) {
/*  61 */     ItemStack var4 = par1EntityLiving.func_130225_q(3 - par2);
/*     */     
/*  63 */     if (var4 != null) {
/*     */       
/*  65 */       Item var5 = var4.getItem();
/*     */       
/*  67 */       if (var5 instanceof ItemArmor) {
/*     */         
/*  69 */         ItemArmor var6 = (ItemArmor)var5;
/*  70 */         bindTexture(func_110857_a(var6, par2));
/*  71 */         ModelBiped var7 = (par2 == 2) ? this.field_82425_h : this.field_82423_g;
/*  72 */         var7.bipedHead.showModel = (par2 == 0);
/*  73 */         var7.bipedHeadwear.showModel = (par2 == 0);
/*  74 */         var7.bipedBody.showModel = (par2 == 1 || par2 == 2);
/*  75 */         var7.bipedRightArm.showModel = (par2 == 1);
/*  76 */         var7.bipedLeftArm.showModel = (par2 == 1);
/*  77 */         var7.bipedRightLeg.showModel = (par2 == 2 || par2 == 3);
/*  78 */         var7.bipedLeftLeg.showModel = (par2 == 2 || par2 == 3);
/*  79 */         setRenderPassModel(var7);
/*  80 */         var7.onGround = this.mainModel.onGround;
/*  81 */         var7.isRiding = this.mainModel.isRiding;
/*  82 */         var7.isChild = this.mainModel.isChild;
/*  83 */         float var8 = 1.0F;
/*     */ 
/*     */         
/*  86 */         if (var6.getArmorMaterial() == Material.leather) {
/*     */           
/*  88 */           int var9 = var6.getColor(var4);
/*  89 */           float var10 = (var9 >> 16 & 0xFF) / 255.0F;
/*  90 */           float var11 = (var9 >> 8 & 0xFF) / 255.0F;
/*  91 */           float var12 = (var9 & 0xFF) / 255.0F;
/*  92 */           GL11.glColor3f(var8 * var10, var8 * var11, var8 * var12);
/*     */           
/*  94 */           if (var4.isItemEnchanted())
/*     */           {
/*  96 */             return 31;
/*     */           }
/*     */           
/*  99 */           return 16;
/*     */         } 
/*     */         
/* 102 */         GL11.glColor3f(var8, var8, var8);
/*     */         
/* 104 */         if (var4.isItemEnchanted())
/*     */         {
/* 106 */           return 15;
/*     */         }
/*     */         
/* 109 */         return 1;
/*     */       } 
/*     */     } 
/*     */     
/* 113 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_130013_c(EntityLiving par1EntityLiving, int par2, float par3) {
/* 118 */     ItemStack var4 = par1EntityLiving.func_130225_q(3 - par2);
/*     */     
/* 120 */     if (var4 != null) {
/*     */       
/* 122 */       Item var5 = var4.getItem();
/*     */       
/* 124 */       if (var5 instanceof ItemArmor) {
/*     */         
/* 126 */         bindTexture(func_110858_a((ItemArmor)var5, par2, "overlay"));
/* 127 */         float var6 = 1.0F;
/* 128 */         GL11.glColor3f(var6, var6, var6);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
/* 135 */     float var10 = 1.0F;
/* 136 */     GL11.glColor3f(var10, var10, var10);
/* 137 */     ItemStack var11 = par1EntityLiving.getHeldItemStack();
/* 138 */     func_82420_a(par1EntityLiving, var11);
/* 139 */     double var12 = par4 - par1EntityLiving.yOffset;
/*     */     
/* 141 */     if (par1EntityLiving.isSneaking())
/*     */     {
/* 143 */       var12 -= 0.125D;
/*     */     }
/*     */     
/* 146 */     super.doRenderLiving(par1EntityLiving, par2, var12, par6, par8, par9);
/* 147 */     this.modelBipedMain.aimedBow = false;
/* 148 */     this.modelBipedMain.isSneak = false;
/* 149 */     this.modelBipedMain.heldItemRight = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving) {
/* 154 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_82420_a(EntityLiving par1EntityLiving, ItemStack par2ItemStack) {
/* 159 */     this.modelBipedMain.heldItemRight = (par2ItemStack != null) ? 1 : 0;
/* 160 */     this.modelBipedMain.isSneak = par1EntityLiving.isSneaking();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_130005_c(EntityLiving par1EntityLiving, float par2) {
/* 165 */     float var3 = 1.0F;
/* 166 */     GL11.glColor3f(var3, var3, var3);
/* 167 */     super.renderEquippedItems(par1EntityLiving, par2);
/* 168 */     ItemStack var4 = par1EntityLiving.getHeldItemStack();
/* 169 */     ItemStack var5 = par1EntityLiving.func_130225_q(3);
/*     */ 
/*     */     
/* 172 */     if (var5 != null) {
/*     */       
/* 174 */       GL11.glPushMatrix();
/* 175 */       this.modelBipedMain.bipedHead.postRender(0.0625F);
/*     */       
/* 177 */       if ((var5.getItem()).itemID < 256) {
/*     */         
/* 179 */         if (RenderBlocks.renderItemIn3d(Block.blocksList[var5.itemID].getRenderType())) {
/*     */           
/* 181 */           float var6 = 0.625F;
/* 182 */           GL11.glTranslatef(0.0F, -0.25F, 0.0F);
/* 183 */           GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 184 */           GL11.glScalef(var6, -var6, -var6);
/*     */         } 
/*     */         
/* 187 */         this.renderManager.itemRenderer.renderItem(par1EntityLiving, var5, 0);
/*     */       }
/* 189 */       else if ((var5.getItem()).itemID == Item.skull.itemID) {
/*     */         
/* 191 */         float var6 = 1.0625F;
/* 192 */         GL11.glScalef(var6, -var6, -var6);
/* 193 */         String var7 = "";
/*     */         
/* 195 */         if (var5.hasTagCompound() && var5.getTagCompound().hasKey("SkullOwner"))
/*     */         {
/* 197 */           var7 = var5.getTagCompound().getString("SkullOwner");
/*     */         }
/*     */         
/* 200 */         TileEntitySkullRenderer.skullRenderer.func_82393_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, var5.getItemSubtype(), var7);
/*     */       } 
/*     */       
/* 203 */       GL11.glPopMatrix();
/*     */     } 
/*     */     
/* 206 */     if (var4 != null) {
/*     */       
/* 208 */       GL11.glPushMatrix();
/*     */       
/* 210 */       if (this.mainModel.isChild) {
/*     */         
/* 212 */         float var6 = 0.5F;
/* 213 */         GL11.glTranslatef(0.0F, 0.625F, 0.0F);
/* 214 */         GL11.glRotatef(-20.0F, -1.0F, 0.0F, 0.0F);
/* 215 */         GL11.glScalef(var6, var6, var6);
/*     */       } 
/*     */       
/* 218 */       this.modelBipedMain.bipedRightArm.postRender(0.0625F);
/* 219 */       GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
/*     */       
/* 221 */       if (var4.itemID < 256 && RenderBlocks.renderItemIn3d(Block.blocksList[var4.itemID].getRenderType())) {
/*     */         
/* 223 */         float var6 = 0.5F;
/* 224 */         GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
/* 225 */         var6 *= 0.75F;
/* 226 */         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/* 227 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 228 */         GL11.glScalef(-var6, -var6, var6);
/*     */       
/*     */       }
/* 231 */       else if (var4.getItem() instanceof ItemBow) {
/*     */         
/* 233 */         float var6 = 0.625F;
/* 234 */         GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
/* 235 */         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
/* 236 */         GL11.glScalef(var6, -var6, var6);
/* 237 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 238 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/* 240 */       else if (Item.itemsList[var4.itemID].isFull3D()) {
/*     */         
/* 242 */         float var6 = 0.625F;
/*     */         
/* 244 */         if (Item.itemsList[var4.itemID].shouldRotateAroundWhenRendering()) {
/*     */           
/* 246 */           GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 247 */           GL11.glTranslatef(0.0F, -0.125F, 0.0F);
/*     */         } 
/*     */         
/* 250 */         func_82422_c();
/* 251 */         GL11.glScalef(var6, -var6, var6);
/* 252 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 253 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/*     */       else {
/*     */         
/* 257 */         float var6 = 0.375F;
/* 258 */         GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
/* 259 */         GL11.glScalef(var6, var6, var6);
/* 260 */         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
/* 261 */         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 262 */         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
/*     */       } 
/*     */       
/* 265 */       this.renderManager.itemRenderer.renderItem(par1EntityLiving, var4, 0);
/*     */       
/* 267 */       if (var4.getItem().requiresMultipleRenderPasses())
/*     */       {
/* 269 */         this.renderManager.itemRenderer.renderItem(par1EntityLiving, var4, 1);
/*     */       }
/*     */       
/* 272 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_82422_c() {
/* 278 */     GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_82408_c(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
/* 283 */     func_130013_c((EntityLiving)par1EntityLivingBase, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
/* 291 */     return func_130006_a((EntityLiving)par1EntityLivingBase, par2, par3);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2) {
/* 296 */     func_130005_c((EntityLiving)par1EntityLivingBase, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
/* 301 */     doRenderLiving((EntityLiving)par1EntityLivingBase, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 309 */     return func_110856_a((EntityLiving)par1Entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
/* 320 */     doRenderLiving((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderBiped.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */