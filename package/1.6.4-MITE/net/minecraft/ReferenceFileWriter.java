/*      */ package net.minecraft;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.FileWriter;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import org.apache.commons.io.FileUtils;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ReferenceFileWriter
/*      */ {
/*   16 */   private static String newline = new String(System.getProperty("line.separator").getBytes());
/*      */   
/*      */   public static boolean running;
/*      */ 
/*      */   
/*      */   private static String formatFloat(float f) {
/*   22 */     return StringHelper.formatFloat(f);
/*      */   }
/*      */ 
/*      */   
/*      */   private static String getBlockMaterialString(Block block, ItemStack item_stack, boolean as_subtype) {
/*   27 */     StringBuilder sb = new StringBuilder();
/*      */     
/*   29 */     sb.append("Block[" + block.blockID + "]");
/*   30 */     sb.append(as_subtype ? ("[" + item_stack.getItemSubtype() + "] ") : " ");
/*   31 */     sb.append(item_stack.getNameForReferenceFile() + ": " + block.blockMaterial.name);
/*   32 */     sb.append(newline);
/*      */     
/*   34 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeBlockMaterialFile(File dir) throws Exception {
/*   39 */     FileWriter fw = new FileWriter(dir.getPath() + "/block_material.txt");
/*      */     
/*   41 */     StringBuffer sb = new StringBuffer();
/*      */     
/*   43 */     sb.append("Block Material" + newline);
/*   44 */     sb.append("--------------" + newline);
/*      */     
/*   46 */     for (int i = 0; i < 256; i++) {
/*      */       
/*   48 */       Block block = Block.blocksList[i];
/*      */       
/*   50 */       if (block != null) {
/*      */         
/*   52 */         List<ItemStack> list = block.getItemStacks();
/*      */         
/*   54 */         ItemStack item_stack = list.get(0);
/*      */         
/*   56 */         if (list.size() == 1 && item_stack.getItemSubtype() == 0) {
/*      */           
/*   58 */           sb.append(getBlockMaterialString(block, item_stack, false));
/*      */         }
/*      */         else {
/*      */           
/*   62 */           Iterator<ItemStack> iterator = list.iterator();
/*      */           
/*   64 */           while (iterator.hasNext()) {
/*      */             
/*   66 */             item_stack = iterator.next();
/*   67 */             sb.append(getBlockMaterialString(block, item_stack, true));
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*   73 */     fw.write(sb.toString());
/*   74 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static String getBlockConstantsString(Block block) {
/*   79 */     StringBuilder sb = new StringBuilder();
/*      */     
/*   81 */     sb.append("Block[" + block.blockID + "] ");
/*   82 */     sb.append(block.getNameForReferenceFile(0, true) + ": ");
/*      */     
/*   84 */     String[] tags = new String[16];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   89 */     if (block.is_always_opaque_standard_form_cube) {
/*   90 */       StringHelper.addToStringArray("ALWAYS_OPAQUE_STANDARD_FORM_CUBE", tags);
/*   91 */     } else if (block.is_always_standard_form_cube) {
/*   92 */       StringHelper.addToStringArray("ALWAYS_STANDARD_FORM_CUBE", tags);
/*   93 */     } else if (block.is_never_standard_form_cube) {
/*   94 */       StringHelper.addToStringArray("NEVER_STANDARD_FORM_CUBE", tags);
/*      */     } else {
/*   96 */       StringHelper.addToStringArray("NOT_ALWAYS_STANDARD_FORM_CUBE", tags);
/*      */     } 
/*   98 */     if (block.is_always_solid) {
/*   99 */       StringHelper.addToStringArray("ALWAYS_SOLID", tags);
/*  100 */     } else if (block.is_never_solid) {
/*  101 */       StringHelper.addToStringArray("NEVER_SOLID", tags);
/*      */     } else {
/*  103 */       StringHelper.addToStringArray("SOMETIMES_SOLID", tags);
/*      */     } 
/*  105 */     if (block.never_hides_adjacent_faces) {
/*  106 */       StringHelper.addToStringArray("NEVER_HIDES_ADJACENT_FACES", tags);
/*      */     }
/*  108 */     if (MITEConstant.useNewPrecipitationHeightDetermination())
/*      */     {
/*  110 */       if (block.always_blocks_precipitation) {
/*  111 */         StringHelper.addToStringArray("ALWAYS_BLOCKS_PRECIPITATION", tags);
/*  112 */       } else if (block.never_blocks_precipitation) {
/*  113 */         StringHelper.addToStringArray("NEVER_BLOCKS_PRECIPITATION", tags);
/*      */       } else {
/*  115 */         StringHelper.addToStringArray("SOMETIMES_BLOCKS_PRECIPITATION", tags);
/*      */       } 
/*      */     }
/*  118 */     if (block.always_blocks_fluids) {
/*  119 */       StringHelper.addToStringArray("ALWAYS_BLOCKS_FLUIDS", tags);
/*  120 */     } else if (block.never_blocks_fluids) {
/*  121 */       StringHelper.addToStringArray("NEVER_BLOCKS_FLUIDS", tags);
/*      */     } else {
/*  123 */       StringHelper.addToStringArray("SOMETIMES_BLOCKS_FLUIDS", tags);
/*      */     } 
/*  125 */     if (block.connects_with_fence) {
/*  126 */       StringHelper.addToStringArray("ALWAYS_CONNECTS_WITH_FENCE", tags);
/*      */     } else {
/*  128 */       StringHelper.addToStringArray("NEVER_CONNECTS_WITH_FENCE", tags);
/*      */     } 
/*  130 */     if (block.is_always_legal) {
/*  131 */       StringHelper.addToStringArray("ALWAYS_LEGAL", tags);
/*      */     } else {
/*  133 */       StringHelper.addToStringArray("NOT_ALWAYS_LEGAL", tags);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  141 */     if (block.is_always_immutable) {
/*  142 */       StringHelper.addToStringArray("ALWAYS_IMMUTABLE", tags);
/*      */     }
/*  144 */     if (!block.canBeCarried()) {
/*  145 */       StringHelper.addToStringArray("NEVER_CARRIED", tags);
/*      */     }
/*  147 */     sb.append(StringHelper.implode(tags, ", ", false, false));
/*      */     
/*  149 */     sb.append(newline);
/*      */     
/*  151 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeBlockConstantsFile(File dir) throws Exception {
/*  156 */     FileWriter fw = new FileWriter(dir.getPath() + "/block_constants.txt");
/*      */     
/*  158 */     StringBuffer sb = new StringBuffer();
/*      */     
/*  160 */     sb.append("Block Constants" + newline);
/*  161 */     sb.append("---------------" + newline);
/*      */     
/*  163 */     for (int i = 0; i < 256; i++) {
/*      */       
/*  165 */       Block block = Block.blocksList[i];
/*      */       
/*  167 */       if (block != null)
/*      */       {
/*      */         
/*  170 */         sb.append(getBlockConstantsString(block));
/*      */       }
/*      */     } 
/*  173 */     fw.write(sb.toString());
/*  174 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static String getBlockHardnessString(Block block, ItemStack item_stack, boolean as_subtype) {
/*  179 */     StringBuilder sb = new StringBuilder();
/*      */     
/*  181 */     int subtype = item_stack.getItemSubtype();
/*      */     
/*  183 */     sb.append("Block[" + block.blockID + "]");
/*  184 */     sb.append(as_subtype ? ("[" + subtype + "] ") : " ");
/*      */     
/*  186 */     float hardness = block.getBlockHardness((block instanceof BlockCrops) ? 1 : subtype);
/*      */     
/*  188 */     sb.append(item_stack.getNameForReferenceFile() + ": " + ((hardness == -1.0F) ? "Infinite" : (String)Integer.valueOf((int)(hardness * 100.0F))));
/*      */     
/*  190 */     if (block.isPortable(null, null, 0, 0, 0)) {
/*  191 */       sb.append(" {Portable}");
/*      */     }
/*  193 */     sb.append(newline);
/*      */     
/*  195 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeBlockHardnessFile(File dir) throws Exception {
/*  200 */     FileWriter fw = new FileWriter(dir.getPath() + "/block_hardness.txt");
/*      */     
/*  202 */     StringBuffer sb = new StringBuffer();
/*      */     
/*  204 */     sb.append("Block Hardness" + newline);
/*  205 */     sb.append("--------------" + newline);
/*      */     
/*  207 */     for (int i = 0; i < 256; i++) {
/*      */       
/*  209 */       Block block = Block.blocksList[i];
/*      */       
/*  211 */       if (block != null) {
/*      */         
/*  213 */         List<ItemStack> list = block.getItemStacks();
/*      */         
/*  215 */         ItemStack item_stack = list.get(0);
/*      */         
/*  217 */         if (list.size() == 1 && item_stack.getItemSubtype() == 0) {
/*      */           
/*  219 */           sb.append(getBlockHardnessString(block, item_stack, false));
/*      */         }
/*      */         else {
/*      */           
/*  223 */           Iterator<ItemStack> iterator = list.iterator();
/*      */           
/*  225 */           while (iterator.hasNext()) {
/*      */             
/*  227 */             item_stack = iterator.next();
/*  228 */             sb.append(getBlockHardnessString(block, item_stack, true));
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  234 */     fw.write(sb.toString());
/*  235 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static String getBlockMetadataString(Block block) {
/*  240 */     StringBuilder sb = new StringBuilder();
/*      */     
/*  242 */     sb.append("Block[" + block.blockID + "] ");
/*      */     
/*  244 */     sb.append(block.getNameForReferenceFile(0, true) + ": ");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  288 */     boolean previous_was_valid = false;
/*  289 */     int first_valid = -1;
/*  290 */     int last_valid = -1;
/*      */     
/*  292 */     for (int i = 0; i < 16; i++) {
/*      */       
/*  294 */       if (block.isValidMetadata(i)) {
/*      */         
/*  296 */         if (previous_was_valid) {
/*      */           
/*  298 */           last_valid = i;
/*      */         }
/*      */         else {
/*      */           
/*  302 */           first_valid = last_valid = i;
/*      */           
/*  304 */           previous_was_valid = true;
/*      */         } 
/*      */         
/*  307 */         if (i == 15) {
/*  308 */           sb.append("[" + ((last_valid == first_valid) ? (String)Integer.valueOf(first_valid) : (first_valid + "-" + last_valid)) + "]");
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  313 */       else if (previous_was_valid) {
/*      */ 
/*      */         
/*  316 */         sb.append("[" + ((last_valid == first_valid) ? (String)Integer.valueOf(first_valid) : (first_valid + "-" + last_valid)) + "]");
/*      */ 
/*      */         
/*  319 */         previous_was_valid = false;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  327 */     String notes = block.getMetadataNotes();
/*      */     
/*  329 */     if (notes != null) {
/*  330 */       sb.append(" {" + notes + "}");
/*      */     }
/*  332 */     sb.append(newline);
/*      */     
/*  334 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeBlockMetadataFile(File dir) throws Exception {
/*  339 */     FileWriter fw = new FileWriter(dir.getPath() + "/block_metadata.txt");
/*      */     
/*  341 */     StringBuffer sb = new StringBuffer();
/*      */     
/*  343 */     sb.append("Block Metadata" + newline);
/*  344 */     sb.append("--------------" + newline);
/*      */     
/*  346 */     for (int i = 0; i < 256; i++) {
/*      */       
/*  348 */       Block block = Block.blocksList[i];
/*      */       
/*  350 */       if (block != null)
/*      */       {
/*      */         
/*  353 */         sb.append(getBlockMetadataString(block));
/*      */       }
/*      */     } 
/*  356 */     fw.write(sb.toString());
/*  357 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static String getBlockDissolveTimeString(Block block, ItemStack item_stack, boolean as_subtype) {
/*  362 */     StringBuilder sb = new StringBuilder();
/*      */     
/*  364 */     int subtype = item_stack.getItemSubtype();
/*      */     
/*  366 */     sb.append("Block[" + block.blockID + "]");
/*  367 */     sb.append(as_subtype ? ("[" + subtype + "] ") : " ");
/*      */     
/*  369 */     int ticks_for_pepsin = block.getDissolvePeriod(subtype, DamageSource.pepsin);
/*  370 */     int ticks_for_acid = block.getDissolvePeriod(subtype, DamageSource.acid);
/*      */     
/*  372 */     sb.append(item_stack.getNameForReferenceFile() + ": " + ((ticks_for_pepsin < 0) ? "never" : ((ticks_for_pepsin == 0) ? "instant" : (String)Integer.valueOf(ticks_for_pepsin))));
/*  373 */     sb.append(", " + ((ticks_for_acid < 0) ? "never" : ((ticks_for_acid == 0) ? "instant" : (String)Integer.valueOf(ticks_for_acid))));
/*      */     
/*  375 */     sb.append(newline);
/*      */     
/*  377 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeBlockDissolveTimeFile(File dir) throws Exception {
/*  382 */     FileWriter fw = new FileWriter(dir.getPath() + "/block_dissolve_time.txt");
/*      */     
/*  384 */     StringBuffer sb = new StringBuffer();
/*      */     
/*  386 */     sb.append("Number of ticks for a small gelatinous cube to dissolve the block (pepsin, acid)." + newline + newline);
/*      */     
/*  388 */     sb.append("Block Dissolve Time" + newline);
/*  389 */     sb.append("-------------------" + newline);
/*      */     
/*  391 */     for (int i = 0; i < 256; i++) {
/*      */       
/*  393 */       Block block = Block.blocksList[i];
/*      */       
/*  395 */       if (block != null) {
/*      */         
/*  397 */         List<ItemStack> list = block.getItemStacks();
/*      */         
/*  399 */         ItemStack item_stack = list.get(0);
/*      */         
/*  401 */         if (list.size() == 1 && item_stack.getItemSubtype() == 0) {
/*      */           
/*  403 */           sb.append(getBlockDissolveTimeString(block, item_stack, false));
/*      */         }
/*      */         else {
/*      */           
/*  407 */           Iterator<ItemStack> iterator = list.iterator();
/*      */           
/*  409 */           while (iterator.hasNext()) {
/*      */             
/*  411 */             item_stack = iterator.next();
/*  412 */             sb.append(getBlockDissolveTimeString(block, item_stack, true));
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  418 */     fw.write(sb.toString());
/*  419 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static String getSilkHarvestString(Block block, ItemStack item_stack, boolean as_subtype) {
/*  424 */     StringBuilder sb = new StringBuilder();
/*      */     
/*  426 */     int subtype = item_stack.getItemSubtype();
/*      */     
/*  428 */     sb.append("Block[" + block.blockID + "]");
/*  429 */     sb.append(as_subtype ? ("[" + subtype + "] ") : " ");
/*      */     
/*  431 */     sb.append(item_stack.getNameForReferenceFile() + ": " + StringHelper.yesOrNo(block.canSilkHarvest(subtype)));
/*      */     
/*  433 */     sb.append(newline);
/*      */     
/*  435 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeSilkHarvestFile(File dir) throws Exception {
/*  440 */     FileWriter fw = new FileWriter(dir.getPath() + "/silk_harvest.txt");
/*      */     
/*  442 */     StringBuffer sb = new StringBuffer();
/*      */     
/*  444 */     sb.append("Can Silk Harvest" + newline);
/*  445 */     sb.append("----------------" + newline);
/*      */     
/*  447 */     for (int i = 0; i < 256; i++) {
/*      */       
/*  449 */       Block block = Block.blocksList[i];
/*      */       
/*  451 */       if (block != null) {
/*      */         
/*  453 */         List<ItemStack> list = block.getItemStacks();
/*      */         
/*  455 */         ItemStack item_stack = list.get(0);
/*      */         
/*  457 */         if (list.size() == 1 && item_stack.getItemSubtype() == 0) {
/*      */           
/*  459 */           sb.append(getSilkHarvestString(block, item_stack, false));
/*      */         }
/*      */         else {
/*      */           
/*  463 */           Iterator<ItemStack> iterator = list.iterator();
/*      */           
/*  465 */           while (iterator.hasNext()) {
/*      */             
/*  467 */             item_stack = iterator.next();
/*  468 */             sb.append(getSilkHarvestString(block, item_stack, true));
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  474 */     fw.write(sb.toString());
/*  475 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static String getBlockHarvestLevelString(Block block, ItemStack item_stack, boolean as_subtype) {
/*  480 */     int subtype = item_stack.getItemSubtype();
/*      */     
/*  482 */     int harvest_level = block.getMinHarvestLevel(subtype);
/*      */     
/*  484 */     if (harvest_level == 0) {
/*  485 */       return "";
/*      */     }
/*  487 */     StringBuilder sb = new StringBuilder();
/*      */     
/*  489 */     sb.append("Block[" + block.blockID + "]");
/*  490 */     sb.append(as_subtype ? ("[" + subtype + "] ") : " ");
/*  491 */     sb.append(item_stack.getNameForReferenceFile() + ": " + harvest_level);
/*  492 */     sb.append(newline);
/*      */     
/*  494 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   private static String getToolHarvestLevelString(ItemTool item, ItemStack item_stack, boolean as_subtype) {
/*  499 */     int subtype = item_stack.getItemSubtype();
/*      */     
/*  501 */     int harvest_level = item.getMaterialHarvestLevel();
/*      */     
/*  503 */     if (harvest_level == 0) {
/*  504 */       return "";
/*      */     }
/*  506 */     StringBuilder sb = new StringBuilder();
/*      */     
/*  508 */     sb.append("Item[" + item_stack.itemID + "]");
/*  509 */     sb.append(as_subtype ? ("[" + subtype + "] ") : " ");
/*  510 */     sb.append(item_stack.getNameForReferenceFile());
/*  511 */     sb.append(": " + harvest_level);
/*  512 */     sb.append(newline);
/*      */     
/*  514 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeHarvestLevelFile(File dir) throws Exception {
/*  519 */     FileWriter fw = new FileWriter(dir.getPath() + "/harvest_level.txt");
/*      */     
/*  521 */     StringBuffer sb = new StringBuffer();
/*      */     
/*  523 */     sb.append("Only blocks and tools with a harvest level greater than 0 are listed." + newline + newline);
/*      */     
/*  525 */     sb.append("Block Harvest Level" + newline);
/*  526 */     sb.append("-------------------" + newline);
/*      */     int i;
/*  528 */     for (i = 0; i < 256; i++) {
/*      */       
/*  530 */       Block block = Block.blocksList[i];
/*      */       
/*  532 */       if (block != null) {
/*      */         
/*  534 */         List<ItemStack> list = block.getItemStacks();
/*      */         
/*  536 */         ItemStack item_stack = list.get(0);
/*      */         
/*  538 */         if (list.size() == 1 && item_stack.getItemSubtype() == 0) {
/*      */           
/*  540 */           sb.append(getBlockHarvestLevelString(block, item_stack, false));
/*      */         }
/*      */         else {
/*      */           
/*  544 */           Iterator<ItemStack> iterator = list.iterator();
/*      */           
/*  546 */           while (iterator.hasNext()) {
/*      */             
/*  548 */             item_stack = iterator.next();
/*  549 */             sb.append(getBlockHarvestLevelString(block, item_stack, true));
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  555 */     sb.append(newline);
/*      */     
/*  557 */     sb.append("Tool Harvest Level" + newline);
/*  558 */     sb.append("------------------" + newline);
/*      */     
/*  560 */     for (i = 0; i < Item.itemsList.length; i++) {
/*      */       
/*  562 */       Item item = Item.getItem(i);
/*      */       
/*  564 */       if (item instanceof ItemTool) {
/*      */         
/*  566 */         ItemTool tool = item.getAsTool();
/*      */         
/*  568 */         List<ItemStack> sub_items = item.getSubItems();
/*      */         
/*  570 */         ItemStack item_stack = sub_items.get(0);
/*      */         
/*  572 */         if (sub_items.size() == 1 && item_stack.getItemSubtype() == 0) {
/*      */           
/*  574 */           sb.append(getToolHarvestLevelString(tool, item_stack, false));
/*      */         }
/*      */         else {
/*      */           
/*  578 */           Iterator<ItemStack> iterator = sub_items.iterator();
/*      */           
/*  580 */           while (iterator.hasNext()) {
/*      */             
/*  582 */             item_stack = iterator.next();
/*  583 */             sb.append(getToolHarvestLevelString(tool, item_stack, true));
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  589 */     fw.write(sb.toString());
/*  590 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static String getComponentString(ItemStack item_stack) {
/*  595 */     Item item = item_stack.getItem();
/*      */     
/*  597 */     StringBuffer sb = new StringBuffer();
/*      */     
/*  599 */     sb.append(item_stack.getNameForReferenceFile() + ": difficulty = ");
/*      */ 
/*      */     
/*  602 */     sb.append((item_stack.getCraftingDifficultyAsComponent() < 0.0F) ? "Not set!" : formatFloat(item_stack.getCraftingDifficultyAsComponent()));
/*      */     
/*  604 */     List<ItemStack> products = item.getCraftingProductsThisIsComponentOf(item_stack.getItemSubtype());
/*      */     
/*  606 */     if (products.size() > 0) {
/*      */       
/*  608 */       sb.append(", {");
/*      */       
/*  610 */       for (int i = 0; i < products.size(); i++) {
/*      */         
/*  612 */         ItemStack output = products.get(i);
/*      */         
/*  614 */         sb.append(output.getNameForReferenceFile());
/*      */         
/*  616 */         if (i < products.size() - 1) {
/*  617 */           sb.append(", ");
/*      */         }
/*      */       } 
/*  620 */       sb.append("}");
/*      */     } 
/*      */     
/*  623 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeRecipeComponentsFile(File dir) throws Exception {
/*  628 */     FileWriter fw = new FileWriter(dir.getPath() + "/recipe_components.txt");
/*      */     
/*  630 */     StringBuffer sb = new StringBuffer();
/*      */     
/*  632 */     sb.append("The difficulty of a crafting recipe is equal to the sum of the difficulty of its components. Therefore each recipe has its own difficulty (see item_recipes.txt for more information)." + newline + newline);
/*      */     
/*  634 */     sb.append("Recipe Components" + newline);
/*  635 */     sb.append("-----------------" + newline);
/*      */     
/*  637 */     for (int i = 0; i < Item.itemsList.length; i++) {
/*      */       
/*  639 */       Item item = Item.getItem(i);
/*      */       
/*  641 */       if (item != null)
/*      */       {
/*  643 */         if (item.getNumSubtypes() == 0 && item.isCraftingComponent(0)) {
/*      */           
/*  645 */           sb.append("Item[" + i + "] ");
/*      */           
/*  647 */           sb.append(getComponentString(new ItemStack(item)));
/*  648 */           sb.append(newline);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  665 */           List sub_items = item.getSubItems();
/*      */           
/*  667 */           Iterator<ItemStack> iterator = sub_items.iterator();
/*      */           
/*  669 */           while (iterator.hasNext()) {
/*      */             
/*  671 */             ItemStack item_stack = iterator.next();
/*      */             
/*  673 */             if (item.isCraftingComponent(item_stack.getItemSubtype())) {
/*      */               
/*  675 */               sb.append("Item[" + i + "][" + ((item instanceof ItemMap) ? "*" : (String)Integer.valueOf(item_stack.getItemSubtype())) + "] ");
/*      */               
/*  677 */               sb.append(getComponentString(item_stack));
/*  678 */               sb.append(newline);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  685 */     fw.write(sb.toString());
/*  686 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeItemSubtypesFile(File dir) throws Exception {
/*  691 */     FileWriter fw = new FileWriter(dir.getPath() + "/item_subtypes.txt");
/*      */     
/*  693 */     StringBuffer sb = new StringBuffer();
/*      */     
/*  695 */     sb.append("Item Subtypes" + newline);
/*  696 */     sb.append("-------------" + newline);
/*      */     
/*  698 */     for (int i = 0; i < Item.itemsList.length; i++) {
/*      */       
/*  700 */       Item item = Item.getItem(i);
/*      */       
/*  702 */       if (item != null && item.getHasSubtypes()) {
/*      */         
/*  704 */         sb.append("Item[" + i + "] ");
/*      */         
/*  706 */         sb.append(item.getNameForReferenceFile());
/*      */         
/*  708 */         if (item instanceof ItemBlock) {
/*      */           
/*  710 */           ItemBlock item_block = (ItemBlock)item;
/*      */           
/*  712 */           if (item_block.getBlock().getNumSubBlocks() != item.getNumSubtypes()) {
/*  713 */             System.out.println("Number of subtypes discrepency for " + item + ", " + item_block.getBlock().getNumSubBlocks() + " vs " + item.getNumSubtypes());
/*      */           }
/*      */         } 
/*  716 */         if (item instanceof ItemMap) {
/*  717 */           sb.append(": * {");
/*      */         } else {
/*  719 */           sb.append(": " + item.getNumSubtypes() + " {");
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  739 */         List list = new ArrayList();
/*      */         
/*  741 */         item.getSubItems(item.itemID, null, list);
/*      */         
/*  743 */         Iterator<ItemStack> iterator = list.iterator();
/*      */         
/*  745 */         while (iterator.hasNext()) {
/*      */           
/*  747 */           ItemStack item_stack = iterator.next();
/*      */           
/*  749 */           sb.append(item_stack.getNameForReferenceFile());
/*      */           
/*  751 */           if (iterator.hasNext()) {
/*  752 */             sb.append(", ");
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/*  757 */         sb.append("}" + newline);
/*      */       } 
/*      */     } 
/*      */     
/*  761 */     fw.write(sb.toString());
/*  762 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static String getItemMaterialsString(ItemStack item_stack, boolean as_subtype) {
/*  767 */     StringBuilder sb = new StringBuilder();
/*      */     
/*  769 */     sb.append("Item[" + item_stack.itemID + "]");
/*  770 */     sb.append(as_subtype ? ("[" + item_stack.getItemSubtype() + "] ") : " ");
/*  771 */     sb.append(item_stack.getNameForReferenceFile());
/*      */     
/*  773 */     Item item = item_stack.getItem();
/*      */     
/*  775 */     sb.append(": " + ((item.materials.size() == 0) ? "null" : StringHelper.getCommaSeparatedList(Material.getMaterialNames(item.materials))));
/*  776 */     sb.append(newline);
/*      */     
/*  778 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeItemMaterialFile(File dir) throws Exception {
/*  783 */     FileWriter fw = new FileWriter(dir.getPath() + "/item_material.txt");
/*      */     
/*  785 */     StringBuffer sb = new StringBuffer();
/*      */     
/*  787 */     sb.append("Some items have more than one material." + newline + newline);
/*      */     
/*  789 */     sb.append("Item Material" + newline);
/*  790 */     sb.append("-------------" + newline);
/*      */     
/*  792 */     for (int i = 0; i < Item.itemsList.length; i++) {
/*      */       
/*  794 */       Item item = Item.getItem(i);
/*      */       
/*  796 */       if (item != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  803 */         List<ItemStack> sub_items = item.getSubItems();
/*      */         
/*  805 */         ItemStack item_stack = sub_items.get(0);
/*      */         
/*  807 */         if (sub_items.size() == 1 && item_stack.getItemSubtype() == 0) {
/*      */           
/*  809 */           sb.append(getItemMaterialsString(item_stack, false));
/*      */         }
/*      */         else {
/*      */           
/*  813 */           Iterator<ItemStack> iterator = sub_items.iterator();
/*      */           
/*  815 */           while (iterator.hasNext()) {
/*      */             
/*  817 */             item_stack = iterator.next();
/*  818 */             sb.append(getItemMaterialsString(item_stack, true));
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  824 */     fw.write(sb.toString());
/*  825 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeItemDurabilityFile(File dir) throws Exception {
/*  830 */     FileWriter fw = new FileWriter(dir.getPath() + "/item_durability.txt");
/*      */     
/*  832 */     StringBuffer sb = new StringBuffer();
/*      */     
/*  834 */     sb.append("Tools have their own specific decay rates vs blocks and entities. See tool decay rate files for more information." + newline + newline);
/*      */     
/*  836 */     sb.append("Item Durability" + newline);
/*  837 */     sb.append("---------------" + newline);
/*      */     
/*  839 */     for (int i = 0; i < Item.itemsList.length; i++) {
/*      */       
/*  841 */       Item item = Item.getItem(i);
/*      */       
/*  843 */       if (item != null && item.isDamageable()) {
/*      */         
/*  845 */         sb.append("Item[" + i + "] ");
/*      */         
/*  847 */         sb.append(item.getNameForReferenceFile());
/*      */         
/*  849 */         if (item.hasQuality()) {
/*  850 */           sb.append(" (" + item.getDefaultQuality().getUnlocalizedName() + ")" + ": " + item.getMaxDamage(item.getDefaultQuality()));
/*      */         } else {
/*  852 */           sb.append(": " + item.getMaxDamage(EnumQuality.average));
/*      */         } 
/*  854 */         sb.append(newline);
/*      */       } 
/*      */     } 
/*      */     
/*  858 */     fw.write(sb.toString());
/*  859 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeItemBurnTimeFile(File dir) throws Exception {
/*  864 */     FileWriter fw = new FileWriter(dir.getPath() + "/item_burn_time.txt");
/*      */     
/*  866 */     StringBuffer sb = new StringBuffer();
/*      */     
/*  868 */     sb.append("Only items that can be burned in a furnace are listed." + newline + newline);
/*      */     
/*  870 */     sb.append("Item Burn Time" + newline);
/*  871 */     sb.append("--------------" + newline);
/*      */     
/*  873 */     for (int i = 0; i < Item.itemsList.length; i++) {
/*      */       
/*  875 */       Item item = Item.getItem(i);
/*      */       
/*  877 */       if (item != null && item.getBurnTime(null) > 0) {
/*      */         
/*  879 */         int num_subtypes = item.getNumSubtypes();
/*      */         
/*  881 */         if (item instanceof ItemMap) {
/*      */           
/*  883 */           ItemStack item_stack = new ItemStack(item, 1, 0);
/*      */           
/*  885 */           sb.append("Item[" + i + "][*] ");
/*  886 */           sb.append(item_stack.getNameForReferenceFile());
/*  887 */           sb.append(": " + item.getBurnTime(item_stack) + " ticks @ heat level " + item.getHeatLevel(item_stack));
/*  888 */           sb.append(newline);
/*      */         }
/*  890 */         else if (num_subtypes == 0) {
/*      */           
/*  892 */           sb.append("Item[" + i + "] ");
/*  893 */           sb.append(item.getNameForReferenceFile());
/*  894 */           sb.append(": " + item.getBurnTime(null) + " ticks @ heat level " + item.getHeatLevel(null));
/*  895 */           sb.append(newline);
/*      */         }
/*      */         else {
/*      */           
/*  899 */           for (int subtype = 0; subtype < num_subtypes; subtype++) {
/*      */             
/*  901 */             ItemStack item_stack = new ItemStack(item, 1, subtype);
/*      */             
/*  903 */             sb.append("Item[" + i + "][" + subtype + "] ");
/*  904 */             sb.append(item_stack.getNameForReferenceFile());
/*  905 */             sb.append(": " + item.getBurnTime(item_stack) + " ticks @ heat level " + item.getHeatLevel(item_stack));
/*  906 */             sb.append(newline);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  912 */     fw.write(sb.toString());
/*  913 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static String getItemCompostingLine(ItemStack item_stack) {
/*  918 */     String line = new String();
/*      */     
/*  920 */     line = line + "Item[" + item_stack.itemID + "]";
/*      */     
/*  922 */     line = line + ((item_stack.getItem() instanceof ItemMap) ? "[*] " : (item_stack.getHasSubtypes() ? ("[" + item_stack.getItemSubtype() + "] ") : " "));
/*      */     
/*  924 */     line = line + StringHelper.repeat(" ", 16 - line.length());
/*      */     
/*  926 */     line = line + item_stack.getNameForReferenceFile();
/*      */     
/*  928 */     line = line + StringHelper.repeat(" ", 42 - line.length());
/*      */     
/*  930 */     line = line + StringHelper.formatFloat(item_stack.getItem().getCompostingValue(), 1, 1);
/*      */     
/*  932 */     Item item = item_stack.getItem().getCompostingRemains(item_stack);
/*      */     
/*  934 */     if (item != null) {
/*  935 */       line = line + " + " + item.getNameForReferenceFile();
/*      */     }
/*  937 */     if (item_stack.itemID == Block.pumpkinLantern.blockID) {
/*  938 */       line = line + " + " + Item.getItem(Block.torchWood).getNameForReferenceFile();
/*      */     }
/*  940 */     line = line + newline;
/*      */     
/*  942 */     return line;
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeItemCompostingFile(File dir) throws Exception {
/*  947 */     FileWriter fw = new FileWriter(dir.getPath() + "/item_composting.txt");
/*      */     
/*  949 */     StringBuilder sb = new StringBuilder();
/*      */     
/*  951 */     sb.append("Only items that can be composted by worms are listed." + newline + newline);
/*      */     
/*  953 */     sb.append("Item ID         Description               Composting Value" + newline);
/*  954 */     sb.append("-------         -----------               ----------------" + newline);
/*      */     
/*  956 */     for (int i = 0; i < Item.itemsList.length; i++) {
/*      */       
/*  958 */       Item item = Item.getItem(i);
/*      */       
/*  960 */       if (item != null) {
/*      */         
/*  962 */         List sub_items = item.getSubItems();
/*      */         
/*  964 */         if (item instanceof ItemMap) {
/*      */           
/*  966 */           ItemStack item_stack = new ItemStack(item, 1, 0);
/*      */           
/*  968 */           if (item_stack.canBeCompostedByWorms()) {
/*  969 */             sb.append(getItemCompostingLine(item_stack).toString());
/*      */           }
/*  971 */         } else if (sub_items.size() == 0) {
/*      */           
/*  973 */           ItemStack item_stack = new ItemStack(item);
/*      */           
/*  975 */           if (item_stack.canBeCompostedByWorms()) {
/*  976 */             sb.append(getItemCompostingLine(item_stack).toString());
/*      */           }
/*      */         } else {
/*      */           
/*  980 */           Iterator<ItemStack> iterator = sub_items.iterator();
/*      */           
/*  982 */           while (iterator.hasNext()) {
/*      */             
/*  984 */             ItemStack item_stack = iterator.next();
/*      */             
/*  986 */             if (item_stack.canBeCompostedByWorms()) {
/*  987 */               sb.append(getItemCompostingLine(item_stack).toString());
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  993 */     fw.write(sb.toString());
/*  994 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static String getRecipeString(Item item, int recipe_index) {
/*  999 */     IRecipe recipe = item.recipes[recipe_index];
/*      */     
/* 1001 */     if (recipe == null) {
/* 1002 */       return "null";
/*      */     }
/* 1004 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 1006 */     ItemStack output = recipe.getRecipeOutput();
/*      */     
/* 1008 */     sb.append(output.getNameForReferenceFile() + ((output.stackSize == 1) ? "" : (" x" + output.stackSize)) + ": {");
/*      */     
/* 1010 */     ItemStack[] components = recipe.getComponents();
/*      */     
/* 1012 */     for (int i = 0; i < components.length; i++) {
/*      */       
/* 1014 */       if (components[i] != null) {
/*      */         
/* 1016 */         if (components[i].getItem().doesSubtypeMatterForProduct(output) || components[i].getItem() instanceof ItemCoal) {
/* 1017 */           sb.append(components[i].getNameForReferenceFile());
/*      */         } else {
/* 1019 */           sb.append(components[i].getItem().getNameForReferenceFile());
/*      */         } 
/* 1021 */         sb.append(", ");
/*      */       } 
/*      */     } 
/*      */     
/* 1025 */     String s = sb.toString();
/*      */     
/* 1027 */     sb = new StringBuffer(s.substring(0, s.length() - 2) + "}, difficulty = " + formatFloat(recipe.getUnmodifiedDifficulty()));
/*      */     
/* 1029 */     if (output.stackSize > 1) {
/* 1030 */       sb.append(" (" + formatFloat(recipe.getUnmodifiedDifficulty() / output.stackSize) + " per unit)");
/*      */     }
/* 1032 */     if (recipe.getSkillsets() != null) {
/* 1033 */       sb.append(" (" + Skill.getSkillsetsString(recipe.getSkillsets(), false) + (output.getItem().hasQuality() ? " for average quality or better" : "") + ")");
/*      */     }
/* 1035 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeItemRecipesFile(File dir) throws Exception {
/* 1040 */     FileWriter fw = new FileWriter(dir.getPath() + "/item_recipes.txt");
/*      */     
/* 1042 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 1044 */     sb.append("Crafting difficulty is the number of ticks taken to craft an item from start to finish." + newline + newline);
/* 1045 */     sb.append("If crafting difficulty is higher than 100 then ticks required is fitted to the curve:" + newline + newline);
/* 1046 */     sb.append("   crafting_ticks = ((crafting_difficulty - 100) ^ 0.8) + 100" + newline + newline);
/* 1047 */     sb.append("Twenty ticks are performed each second." + newline + newline);
/*      */     
/* 1049 */     sb.append("Item Recipes" + newline);
/* 1050 */     sb.append("------------" + newline);
/*      */     
/* 1052 */     for (int i = 0; i < Item.itemsList.length; i++) {
/*      */       
/* 1054 */       Item item = Item.getItem(i);
/*      */       
/* 1056 */       if (item != null && item.isCraftingProduct()) {
/*      */         
/* 1058 */         sb.append("Item[" + i + "] ");
/*      */         
/* 1060 */         if (item.num_recipes == 1) {
/*      */           
/* 1062 */           sb.append(getRecipeString(item, 0));
/*      */         }
/*      */         else {
/*      */           
/* 1066 */           sb.append("Has " + item.num_recipes + " recipes:");
/*      */           
/* 1068 */           for (int recipe_index = 0; recipe_index < item.num_recipes; recipe_index++) {
/*      */             
/* 1070 */             sb.append(newline + "  Recipe[" + recipe_index + "] " + getRecipeString(item, recipe_index));
/*      */             
/* 1072 */             IRecipe recipe = item.recipes[recipe_index];
/*      */             
/* 1074 */             float difficulty = recipe.getUnmodifiedDifficulty() / (recipe.getRecipeOutput()).stackSize;
/*      */           } 
/*      */           
/* 1077 */           if (item.isRepairable()) {
/* 1078 */             sb.append(newline + "  Difficulty used for repairs = " + formatFloat(item.getLowestCraftingDifficultyToProduce()));
/*      */           }
/*      */         } 
/* 1081 */         sb.append(newline);
/*      */       } 
/*      */     } 
/*      */     
/* 1085 */     fw.write(sb.toString());
/* 1086 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeFurnaceRecipesFile(File dir) throws Exception {
/* 1091 */     FileWriter fw = new FileWriter(dir.getPath() + "/furnace_recipes.txt");
/*      */     
/* 1093 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 1095 */     sb.append("Furnace Recipes" + newline);
/* 1096 */     sb.append("---------------" + newline);
/*      */     
/* 1098 */     boolean sandstone_done = false;
/*      */     
/* 1100 */     for (int i = 0; i < Item.itemsList.length; i++) {
/*      */       
/* 1102 */       ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(i), -1);
/*      */       
/* 1104 */       if (i == Block.sand.blockID) {
/* 1105 */         result = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(i, sandstone_done ? 4 : 4), sandstone_done ? 2 : 1);
/*      */       }
/* 1107 */       if (result != null) {
/*      */         
/* 1109 */         Item item = Item.getItem(i);
/*      */         
/* 1111 */         sb.append(item);
/*      */         
/* 1113 */         if (i == Block.sand.blockID && !sandstone_done) {
/* 1114 */           sb.append(" x4");
/*      */         }
/* 1116 */         if (i == Block.sand.blockID && sandstone_done) {
/* 1117 */           sb.append(" x4");
/*      */         }
/* 1119 */         sb.append(" @ ");
/* 1120 */         sb.append(TileEntityFurnace.getHeatLevelRequired(i) + ((i == Block.sand.blockID && sandstone_done) ? 1 : 0));
/* 1121 */         sb.append(" = ");
/* 1122 */         sb.append(result.getNameForReferenceFile());
/*      */         
/* 1124 */         if (result.stackSize != 1) {
/* 1125 */           sb.append(" x" + result.stackSize);
/*      */         }
/* 1127 */         int[] skillsets = TileEntityFurnace.getSkillsetsThatCanSmelt(item);
/*      */         
/* 1129 */         if (skillsets != null) {
/* 1130 */           sb.append(" (" + Skill.getSkillsetsString(skillsets, false) + ")");
/*      */         }
/* 1132 */         sb.append(newline);
/*      */         
/* 1134 */         if (i == Block.sand.blockID && !sandstone_done) {
/*      */           
/* 1136 */           sandstone_done = true;
/* 1137 */           i--;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1142 */     fw.write(sb.toString());
/* 1143 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static String getXpCostForQualityString(Item item) {
/* 1148 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 1150 */     for (int i = EnumQuality.fine.ordinal(); i <= item.getMaxQuality().ordinal(); i++) {
/*      */       int cost;
/* 1152 */       EnumQuality quality = EnumQuality.get(i);
/*      */       
/* 1154 */       sb.append(StringHelper.capitalize(quality.getUnlocalizedName()) + "=");
/*      */ 
/*      */ 
/*      */       
/* 1158 */       if (quality.isAverageOrLower()) {
/*      */         
/* 1160 */         cost = 0;
/*      */       }
/*      */       else {
/*      */         
/* 1164 */         float quality_adjusted_crafting_difficulty = CraftingResult.getQualityAdjustedDifficulty(item.getLowestCraftingDifficultyToProduce(), quality);
/* 1165 */         cost = Math.round(quality_adjusted_crafting_difficulty / 5.0F);
/*      */       } 
/*      */       
/* 1168 */       sb.append(cost);
/* 1169 */       sb.append(", ");
/*      */     } 
/*      */     
/* 1172 */     String s = sb.toString();
/*      */     
/* 1174 */     return s.isEmpty() ? s : StringHelper.left(s, -2);
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeItemXpReqsFile(File dir) throws Exception {
/* 1179 */     FileWriter fw = new FileWriter(dir.getPath() + "/item_xp_reqs.txt");
/*      */     
/* 1181 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 1183 */     sb.append("Experience Requirements for Item Crafting & Repairing" + newline);
/* 1184 */     sb.append("-----------------------------------------------------" + newline);
/*      */     
/* 1186 */     for (int i = 0; i < Item.itemsList.length; i++) {
/*      */       
/* 1188 */       Item item = Item.getItem(i);
/*      */       
/* 1190 */       if (item != null && item.isCraftingProduct() && item.hasQuality() && item.getMaxQuality().isHigherThan(EnumQuality.average)) {
/*      */         
/* 1192 */         sb.append("Item[" + i + "] ");
/* 1193 */         sb.append(item.getNameForReferenceFile() + ": ");
/* 1194 */         sb.append(getXpCostForQualityString(item));
/* 1195 */         sb.append(newline);
/*      */       } 
/*      */     } 
/*      */     
/* 1199 */     fw.write(sb.toString());
/* 1200 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeFoodValueFile(File dir) throws Exception {
/* 1205 */     FileWriter fw = new FileWriter(dir.getPath() + "/food_value.txt");
/*      */     
/* 1207 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 1209 */     sb.append("Food Value (satiation, nutrition, protein, phytonutrients, IR=Insulin Response)" + newline);
/* 1210 */     sb.append("----------" + newline);
/*      */     
/* 1212 */     for (int i = 0; i < Item.itemsList.length; i++) {
/*      */       
/* 1214 */       Item item = Item.getItem(i);
/*      */       
/* 1216 */       if (item != null && item.isIngestable(0)) {
/*      */         
/* 1218 */         sb.append("Item[" + i + "] ");
/* 1219 */         sb.append(item.getNameForReferenceFile() + ": " + item.getSatiation(null) + ", " + item.getNutrition());
/* 1220 */         sb.append(", " + item.getProtein() + ", " + item.getPhytonutrients());
/*      */         
/* 1222 */         int insulin_response = item.getInsulinResponse();
/*      */         
/* 1224 */         if (insulin_response > 0) {
/* 1225 */           sb.append(" IR=" + insulin_response);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1245 */         sb.append(newline);
/*      */       } 
/*      */     } 
/*      */     
/* 1249 */     fw.write(sb.toString());
/* 1250 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeItemReachFile(File dir) throws Exception {
/* 1255 */     FileWriter fw = new FileWriter(dir.getPath() + "/item_reach.txt");
/*      */     
/* 1257 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 1259 */     sb.append("The player has a base reach of " + StringHelper.formatFloat(2.75F, 1, 2) + " vs blocks and " + StringHelper.formatFloat(1.5F, 1, 2) + " vs entities." + newline + newline);
/*      */     
/* 1261 */     sb.append("Only items that have a reach bonus are listed." + newline + newline);
/*      */     
/* 1263 */     sb.append("Reach Bonus" + newline);
/* 1264 */     sb.append("-----------" + newline);
/*      */     
/* 1266 */     for (int i = 0; i < Item.itemsList.length; i++) {
/*      */       
/* 1268 */       Item item = Item.getItem(i);
/*      */       
/* 1270 */       if (item == null) {
/*      */         continue;
/*      */       }
/* 1273 */       String name = item.getNameForReferenceFile();
/*      */       
/* 1275 */       if (item instanceof ItemTool) {
/*      */         
/* 1277 */         ItemTool tool = (ItemTool)item;
/*      */         
/* 1279 */         if (tool.getToolMaterial() != Material.iron && !(tool instanceof ItemCudgel)) {
/*      */           continue;
/*      */         }
/* 1282 */         name = name.substring((tool.getToolMaterial() == Material.iron) ? 5 : 7);
/*      */       } 
/*      */       
/* 1285 */       float reach_bonus = item.getReachBonus();
/*      */       
/* 1287 */       if (reach_bonus > 0.0F) {
/*      */         
/* 1289 */         sb.append("Item[" + i + "] ");
/* 1290 */         sb.append(name + ": +" + StringHelper.formatFloat(reach_bonus, 1, 3));
/* 1291 */         sb.append(newline);
/*      */       } 
/*      */       continue;
/*      */     } 
/* 1295 */     fw.write(sb.toString());
/* 1296 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeDamageVsEntityFile(File dir) throws Exception {
/* 1301 */     FileWriter fw = new FileWriter(dir.getPath() + "/damage_vs_entity.txt");
/*      */     
/* 1303 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 1305 */     sb.append("Damage vs Entity" + newline);
/* 1306 */     sb.append("----------------" + newline);
/*      */     
/* 1308 */     for (int i = 0; i < Item.itemsList.length; i++) {
/*      */       
/* 1310 */       Item item = Item.getItem(i);
/*      */       
/* 1312 */       if (item instanceof ItemTool) {
/*      */         
/* 1314 */         ItemTool item_tool = (ItemTool)item;
/*      */         
/* 1316 */         sb.append("Item[" + i + "] ");
/* 1317 */         sb.append(item.getNameForReferenceFile() + ": +" + (int)item_tool.getCombinedDamageVsEntity());
/* 1318 */         sb.append(newline);
/*      */       } 
/*      */     } 
/*      */     
/* 1322 */     fw.write(sb.toString());
/* 1323 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeArmorProtectionFile(File dir) throws Exception {
/* 1328 */     FileWriter fw = new FileWriter(dir.getPath() + "/armor_protection.txt");
/*      */     
/* 1330 */     StringBuffer sb = new StringBuffer();
/*      */ 
/*      */     
/* 1333 */     sb.append("A total armor score of 20 is considered complete protection. Note that non-zero damage always results in at least half a heart of damage." + newline + newline);
/*      */     
/* 1335 */     sb.append("Armor begins to lose its protection value after falling below 50% durability." + newline + newline);
/*      */     
/* 1337 */     sb.append("Protection Values" + newline);
/* 1338 */     sb.append("-----------------" + newline);
/*      */     
/* 1340 */     for (int i = 0; i < Item.itemsList.length; i++) {
/*      */       
/* 1342 */       Item item = Item.getItem(i);
/*      */       
/* 1344 */       if (item instanceof ItemArmor) {
/*      */         
/* 1346 */         ItemArmor armor = (ItemArmor)item;
/*      */         
/* 1348 */         sb.append("Item[" + i + "] ");
/* 1349 */         sb.append(item.getNameForReferenceFile() + ": +" + StringHelper.formatFloat(armor.getMultipliedProtection(null), 2, 2));
/*      */         
/* 1351 */         sb.append(newline);
/*      */       } 
/*      */     } 
/*      */     
/* 1355 */     fw.write(sb.toString());
/* 1356 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeItemEnchantmentsFile(File dir) throws Exception {
/* 1361 */     FileWriter fw = new FileWriter(dir.getPath() + "/item_enchantments.txt");
/*      */     
/* 1363 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 1365 */     sb.append("Item Enchantments" + newline);
/* 1366 */     sb.append("-----------------" + newline);
/*      */     
/* 1368 */     for (int i = 0; i < Item.itemsList.length; i++) {
/*      */       
/* 1370 */       Item item = Item.getItem(i);
/*      */       
/* 1372 */       if (item == null || item.getItemEnchantability() <= 0) {
/*      */         continue;
/*      */       }
/* 1375 */       String item_name = item.getNameForReferenceFile();
/*      */       
/* 1377 */       if (item instanceof ItemTool) {
/*      */         
/* 1379 */         ItemTool tool = (ItemTool)item;
/*      */         
/* 1381 */         if (tool instanceof ItemCudgel) {
/*      */           
/* 1383 */           item_name = StringHelper.capitalizeEachWord(tool.getToolType().replaceAll("_", " "));
/*      */         }
/*      */         else {
/*      */           
/* 1387 */           if (tool.getToolMaterial() != Material.iron) {
/*      */             continue;
/*      */           }
/*      */           
/* 1391 */           if (tool instanceof ItemKnife || tool instanceof ItemHatchet || tool instanceof ItemAxe) {
/* 1392 */             item_name = StringHelper.capitalizeEachWord((tool.getToolType() + " (metal)").replaceAll("_", " "));
/*      */           } else {
/* 1394 */             item_name = StringHelper.capitalizeEachWord(tool.getToolType().replaceAll("_", " "));
/*      */           } 
/*      */         } 
/* 1397 */       } else if (item instanceof ItemArmor) {
/*      */         
/* 1399 */         ItemArmor armor = (ItemArmor)item;
/*      */         
/* 1401 */         Material material = armor.getArmorMaterial();
/*      */         
/* 1403 */         if (material != Material.leather && material != Material.iron) {
/*      */           continue;
/*      */         }
/* 1406 */         String disambiguation = (material == Material.leather) ? "leather" : (armor.isChainMail() ? "chain" : "metal");
/*      */         
/* 1408 */         item_name = StringHelper.capitalizeEachWord((armor.getArmorType() + " (" + disambiguation + ")").replaceAll("_", " "));
/*      */       } 
/* 1410 */       if (item instanceof ItemBow) {
/*      */         
/* 1412 */         if (item != Item.bowMithril) {
/*      */           continue;
/*      */         }
/* 1415 */         item_name = StringHelper.capitalizeEachWord("Bow");
/*      */       } 
/* 1417 */       if (item instanceof ItemFishingRod) {
/*      */         
/* 1419 */         ItemFishingRod rod = (ItemFishingRod)item;
/*      */         
/* 1421 */         if (rod.getHookMaterial() != Material.iron) {
/*      */           continue;
/*      */         }
/* 1424 */         String disambiguation = "metal";
/*      */         
/* 1426 */         item_name = StringHelper.capitalizeEachWord(("Fishing Rod (" + disambiguation + ")").replaceAll("_", " "));
/*      */       } 
/*      */       
/* 1429 */       List<Enchantment> possible_enchantments = new ArrayList();
/*      */       
/* 1431 */       for (int enchantment_index = 0; enchantment_index < Enchantment.enchantmentsList.length; enchantment_index++) {
/*      */         
/* 1433 */         Enchantment enchantment = Enchantment.get(enchantment_index);
/*      */         
/* 1435 */         if (enchantment != null && enchantment.canEnchantItem(item)) {
/* 1436 */           possible_enchantments.add(enchantment);
/*      */         }
/*      */       } 
/* 1439 */       if (possible_enchantments.size() > 0) {
/*      */         
/* 1441 */         sb.append("Item[" + i + "] ");
/*      */         
/* 1443 */         String[] names = new String[possible_enchantments.size()];
/*      */         
/* 1445 */         for (int j = 0; j < possible_enchantments.size(); j++) {
/* 1446 */           names[j] = ((Enchantment)possible_enchantments.get(j)).getTranslatedName(item);
/*      */         }
/* 1448 */         sb.append(item_name + ": " + StringHelper.getCommaSeparatedList(names));
/*      */         
/* 1450 */         sb.append(newline);
/*      */       } 
/*      */       continue;
/*      */     } 
/* 1454 */     fw.write(sb.toString());
/* 1455 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeEnchantmentsFile(File dir) throws Exception {
/* 1460 */     FileWriter fw = new FileWriter(dir.getPath() + "/enchantments.txt");
/*      */     
/* 1462 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 1464 */     sb.append("Enchantments" + newline);
/* 1465 */     sb.append("------------" + newline);
/*      */     
/* 1467 */     for (int i = 0; i < Enchantment.enchantmentsList.length; i++) {
/*      */       
/* 1469 */       Enchantment enchantment = Enchantment.get(i);
/*      */       
/* 1471 */       if (enchantment != null) {
/*      */         
/* 1473 */         sb.append("Enchantment[" + i + "] ");
/*      */         
/* 1475 */         sb.append(enchantment + ": " + enchantment.rarity + ", difficulty = " + enchantment.difficulty);
/*      */         
/* 1477 */         if (enchantment.hasLevels()) {
/* 1478 */           sb.append(", " + enchantment.getNumLevels() + " levels");
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1493 */         sb.append(newline);
/*      */       } 
/*      */     } 
/*      */     
/* 1497 */     fw.write(sb.toString());
/* 1498 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static String getStackLimitString(ItemStack item_stack, boolean as_subtype) {
/* 1503 */     StringBuilder sb = new StringBuilder();
/*      */     
/* 1505 */     sb.append("Item[" + item_stack.itemID + "]");
/* 1506 */     sb.append(as_subtype ? ("[" + item_stack.getItemSubtype() + "] ") : " ");
/* 1507 */     sb.append(item_stack.getNameForReferenceFile());
/* 1508 */     sb.append(": " + item_stack.getMaxStackSize());
/* 1509 */     sb.append(newline);
/*      */     
/* 1511 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeStackLimitsFile(File dir) throws Exception {
/* 1516 */     FileWriter fw = new FileWriter(dir.getPath() + "/stack_limits.txt");
/*      */     
/* 1518 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 1520 */     sb.append("Stack Limits" + newline);
/* 1521 */     sb.append("------------" + newline);
/*      */     
/* 1523 */     for (int i = 0; i < Item.itemsList.length; i++) {
/*      */       
/* 1525 */       Item item = Item.getItem(i);
/*      */       
/* 1527 */       if (item != null) {
/*      */ 
/*      */         
/* 1530 */         boolean allowed_exception = (item == Item.map || item == Item.writtenBook || item == Item.firework || item == Item.enchantedBook);
/*      */         
/* 1532 */         if (item.getCreativeTab() != null || allowed_exception) {
/*      */ 
/*      */           
/* 1535 */           List<ItemStack> sub_items = item.getSubItems();
/*      */           
/* 1537 */           ItemStack item_stack = sub_items.get(0);
/*      */           
/* 1539 */           if (sub_items.size() == 1 && item_stack.getItemSubtype() == 0) {
/*      */             
/* 1541 */             sb.append(getStackLimitString(item_stack, false));
/*      */           }
/*      */           else {
/*      */             
/* 1545 */             Iterator<ItemStack> iterator = sub_items.iterator();
/*      */             
/* 1547 */             while (iterator.hasNext()) {
/*      */               
/* 1549 */               item_stack = iterator.next();
/* 1550 */               sb.append(getStackLimitString(item_stack, true));
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/* 1555 */     }  fw.write(sb.toString());
/* 1556 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeMaterialDurabilityFile(File dir) throws Exception {
/* 1561 */     FileWriter fw = new FileWriter(dir.getPath() + "/material_durability.txt");
/*      */     
/* 1563 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 1565 */     sb.append("Material Durability" + newline);
/* 1566 */     sb.append("-------------------" + newline);
/*      */     
/* 1568 */     for (int i = 0; i < Material.num_materials; i++) {
/*      */       
/* 1570 */       Material material = Material.materials[i];
/*      */       
/* 1572 */       if (material != null && material.durability > 0.0F) {
/*      */         
/* 1574 */         sb.append("Material[" + i + "] ");
/* 1575 */         sb.append(material.getCapitalizedName() + ": " + material.durability);
/* 1576 */         sb.append(newline);
/*      */       } 
/*      */     } 
/*      */     
/* 1580 */     fw.write(sb.toString());
/* 1581 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeMaterialMaxQualityFile(File dir) throws Exception {
/* 1586 */     FileWriter fw = new FileWriter(dir.getPath() + "/material_max_quality.txt");
/*      */     
/* 1588 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 1590 */     sb.append("Material Max Quality" + newline);
/* 1591 */     sb.append("--------------------" + newline);
/*      */     
/* 1593 */     for (int i = 0; i < Material.num_materials; i++) {
/*      */       
/* 1595 */       Material material = Material.materials[i];
/*      */       
/* 1597 */       if (material != null && (material.getMaxQuality().isLowerThan(EnumQuality.legendary) || material == Material.mithril || material == Material.adamantium)) {
/*      */         
/* 1599 */         sb.append("Material[" + i + "] ");
/* 1600 */         sb.append(material.getCapitalizedName() + ": " + StringHelper.getFirstWord(material.getMaxQuality().getDescriptor()));
/* 1601 */         sb.append(newline);
/*      */       } 
/*      */     } 
/*      */     
/* 1605 */     fw.write(sb.toString());
/* 1606 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeMaterialEnchantabilityFile(File dir) throws Exception {
/* 1611 */     FileWriter fw = new FileWriter(dir.getPath() + "/material_enchantability.txt");
/*      */     
/* 1613 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 1615 */     sb.append("Materials not listed here are not enchantable." + newline + newline);
/*      */     
/* 1617 */     sb.append("Material Enchantability" + newline);
/* 1618 */     sb.append("-----------------------" + newline);
/*      */     
/* 1620 */     for (int i = 0; i < Material.num_materials; i++) {
/*      */       
/* 1622 */       Material material = Material.materials[i];
/*      */       
/* 1624 */       if (material != null && material.enchantability > 0) {
/*      */         
/* 1626 */         sb.append("Material[" + i + "] ");
/* 1627 */         sb.append(material.getCapitalizedName() + ": " + material.enchantability);
/* 1628 */         sb.append(newline);
/*      */       } 
/*      */     } 
/*      */     
/* 1632 */     fw.write(sb.toString());
/* 1633 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static String getToolDecayRateString(ItemTool item_tool, ItemStack item_stack, boolean as_subtype) {
/* 1638 */     Block block = item_stack.getItem().getAsItemBlock().getBlock();
/*      */     
/* 1640 */     StringBuilder sb = new StringBuilder();
/*      */     
/* 1642 */     int subtype = item_stack.getItemSubtype();
/*      */     
/* 1644 */     sb.append("Block[" + block.blockID + "]");
/* 1645 */     sb.append(as_subtype ? ("[" + subtype + "] ") : " ");
/* 1646 */     sb.append(item_stack.getNameForReferenceFile());
/*      */     
/* 1648 */     sb.append(": " + item_tool.getToolDecayFromBreakingBlock(new BlockBreakInfo(block.blockID, (block instanceof BlockCrops) ? 1 : subtype)));
/* 1649 */     sb.append(newline);
/*      */     
/* 1651 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeToolDecayRateFiles(File dir) throws Exception {
/* 1656 */     dir = new File(dir, "tool_decay_rates");
/*      */     
/* 1658 */     if (!dir.exists()) {
/* 1659 */       dir.mkdir();
/*      */     }
/* 1661 */     for (int i = 0; i < Item.itemsList.length; i++) {
/*      */       
/* 1663 */       Item item = Item.getItem(i);
/*      */       
/* 1665 */       if (item instanceof ItemTool) {
/*      */         
/* 1667 */         ItemTool item_tool = (ItemTool)item;
/*      */         
/* 1669 */         if (item_tool.getToolMaterial() == Material.adamantium) {
/*      */           
/* 1671 */           FileWriter fw = new FileWriter(dir.getPath() + "/" + item_tool.getToolType() + ".txt");
/*      */           
/* 1673 */           StringBuffer sb = new StringBuffer();
/*      */           
/* 1675 */           sb.append("Only the blocks that this tool is effective against are listed." + newline + newline);
/*      */           
/* 1677 */           sb.append("Decay Rate vs Entity" + newline);
/* 1678 */           sb.append("--------------------" + newline);
/* 1679 */           sb.append("General Factor: x" + formatFloat(item_tool.getBaseDecayRateForAttackingEntity(null)) + newline);
/* 1680 */           sb.append("All: " + item_tool.getToolDecayFromAttackingEntity(null, null));
/* 1681 */           sb.append(newline + newline);
/*      */           
/* 1683 */           sb.append("Decay Rate vs Block" + newline);
/* 1684 */           sb.append("-------------------" + newline);
/* 1685 */           sb.append("General Factor: x" + formatFloat(item_tool.getBaseDecayRateForBreakingBlock(null)) + newline);
/*      */           
/* 1687 */           for (int block_index = 0; block_index < 256; block_index++) {
/*      */             
/* 1689 */             Block block = Block.blocksList[block_index];
/*      */             
/* 1691 */             if (block != null && item_tool.isEffectiveAgainstBlock(block, 0)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1697 */               List<ItemStack> list = block.getItemStacks();
/*      */               
/* 1699 */               ItemStack item_stack = list.get(0);
/*      */               
/* 1701 */               if (list.size() == 1 && item_stack.getItemSubtype() == 0) {
/*      */                 
/* 1703 */                 sb.append(getToolDecayRateString(item_tool, item_stack, false));
/*      */               }
/*      */               else {
/*      */                 
/* 1707 */                 Iterator<ItemStack> iterator = list.iterator();
/*      */                 
/* 1709 */                 while (iterator.hasNext()) {
/*      */                   
/* 1711 */                   item_stack = iterator.next();
/*      */                   
/* 1713 */                   sb.append(getToolDecayRateString(item_tool, item_stack, true));
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */           
/* 1719 */           fw.write(sb.toString());
/* 1720 */           fw.close();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static String getToolHarvestEfficiencyString(ItemTool item_tool, ItemStack item_stack, boolean as_subtype) {
/* 1728 */     Block block = item_stack.getItem().getAsItemBlock().getBlock();
/*      */     
/* 1730 */     StringBuilder sb = new StringBuilder();
/*      */     
/* 1732 */     sb.append("Block[" + block.blockID + "]");
/* 1733 */     sb.append(as_subtype ? ("[" + item_stack.getItemSubtype() + "] ") : " ");
/* 1734 */     sb.append(item_stack.getNameForReferenceFile());
/* 1735 */     sb.append(": " + item_tool.getBaseHarvestEfficiency(block));
/* 1736 */     sb.append(newline);
/*      */     
/* 1738 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeToolHarvestEfficiencyFiles(File dir) throws Exception {
/* 1743 */     dir = new File(dir, "tool_harvest_efficiency");
/*      */     
/* 1745 */     if (!dir.exists()) {
/* 1746 */       dir.mkdir();
/*      */     }
/* 1748 */     for (int i = 0; i < Item.itemsList.length; i++) {
/*      */       
/* 1750 */       Item item = Item.getItem(i);
/*      */       
/* 1752 */       if (item instanceof ItemTool) {
/*      */         
/* 1754 */         ItemTool item_tool = (ItemTool)item;
/*      */         
/* 1756 */         if (item_tool.getToolMaterial() == Material.adamantium) {
/*      */           
/* 1758 */           FileWriter fw = new FileWriter(dir.getPath() + "/" + item_tool.getToolType() + ".txt");
/*      */           
/* 1760 */           StringBuffer sb = new StringBuffer();
/*      */           
/* 1762 */           sb.append("Only the blocks that this tool is effective against are listed." + newline + newline);
/*      */           
/* 1764 */           sb.append("Harvest Efficiency" + newline);
/* 1765 */           sb.append("------------------" + newline);
/*      */           
/* 1767 */           for (int block_index = 0; block_index < 256; block_index++) {
/*      */             
/* 1769 */             Block block = Block.blocksList[block_index];
/*      */             
/* 1771 */             if (block != null && item_tool.isEffectiveAgainstBlock(block, 0)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1777 */               List<ItemStack> list = block.getItemStacks();
/*      */               
/* 1779 */               ItemStack item_stack = list.get(0);
/*      */               
/* 1781 */               if (list.size() == 1 && item_stack.getItemSubtype() == 0) {
/*      */                 
/* 1783 */                 sb.append(getToolHarvestEfficiencyString(item_tool, item_stack, false));
/*      */               }
/*      */               else {
/*      */                 
/* 1787 */                 Iterator<ItemStack> iterator = list.iterator();
/*      */                 
/* 1789 */                 while (iterator.hasNext()) {
/*      */                   
/* 1791 */                   item_stack = iterator.next();
/* 1792 */                   sb.append(getToolHarvestEfficiencyString(item_tool, item_stack, true));
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */           
/* 1798 */           fw.write(sb.toString());
/* 1799 */           fw.close();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writePlayerLevelsFile(File dir) throws Exception {
/* 1807 */     FileWriter fw = new FileWriter(dir.getPath() + "/player_levels.txt");
/*      */     
/* 1809 */     StringBuffer sb = new StringBuffer();
/*      */ 
/*      */ 
/*      */     
/* 1813 */     sb.append("The modifier shown in the last column applies to harvesting speed, melee damage, and crafting time reduction." + newline + newline);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1818 */     sb.append("Level          Experience          Stats          Harvesting          Crafting          Melee Dmg" + newline);
/* 1819 */     sb.append("-----          ----------          -----          ----------          --------          ---------" + newline);
/*      */     
/* 1821 */     for (int level = -40; level <= EntityPlayer.getHighestPossibleLevel(); level++) {
/*      */       
/* 1823 */       String line = new String();
/*      */       
/* 1825 */       line = line + level;
/*      */       
/* 1827 */       line = line + StringHelper.repeat(" ", 15 - line.length());
/*      */       
/* 1829 */       line = line + EntityPlayer.getExperienceRequired(level);
/*      */       
/* 1831 */       line = line + StringHelper.repeat(" ", 35 - line.length());
/*      */       
/* 1833 */       line = line + EntityPlayer.getHealthLimit(level);
/*      */       
/* 1835 */       line = line + StringHelper.repeat(" ", 50 - line.length());
/*      */       
/* 1837 */       int level_modifier = Math.round(EntityPlayer.getLevelModifier(level, EnumLevelBonus.HARVESTING) * 100.0F);
/*      */       
/* 1839 */       if (level_modifier > 0) {
/* 1840 */         line = line + "+";
/*      */       }
/* 1842 */       line = line + level_modifier + "%";
/*      */ 
/*      */ 
/*      */       
/* 1846 */       line = line + StringHelper.repeat(" ", 70 - line.length());
/*      */       
/* 1848 */       level_modifier = Math.round(EntityPlayer.getLevelModifier(level, EnumLevelBonus.CRAFTING) * 100.0F);
/*      */       
/* 1850 */       if (level_modifier > 0) {
/* 1851 */         line = line + "+";
/*      */       }
/* 1853 */       line = line + level_modifier + "%";
/*      */       
/* 1855 */       line = line + StringHelper.repeat(" ", 88 - line.length());
/*      */       
/* 1857 */       level_modifier = Math.round(EntityPlayer.getLevelModifier(level, EnumLevelBonus.MELEE_DAMAGE) * 100.0F);
/*      */       
/* 1859 */       if (level_modifier > 0) {
/* 1860 */         line = line + "+";
/*      */       }
/* 1862 */       line = line + level_modifier + "%";
/*      */ 
/*      */ 
/*      */       
/* 1866 */       sb.append(line);
/* 1867 */       sb.append(newline);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1893 */     fw.write(sb.toString());
/* 1894 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeProfessionsFile(File dir) throws Exception {
/* 1899 */     FileWriter fw = new FileWriter(dir.getPath() + "/professions.txt");
/*      */     
/* 1901 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 1903 */     sb.append("Player professions are only relevant in worlds that have professions enabled." + newline + newline);
/*      */     
/* 1905 */     sb.append("Profession     Skill                Description" + newline);
/* 1906 */     sb.append("----------     -----                -----------" + newline);
/*      */     
/* 1908 */     for (int i = 0; i < Skill.getNumSkills(); i++) {
/*      */       
/* 1910 */       Skill skill = Skill.list[i];
/*      */       
/* 1912 */       String line = new String();
/*      */       
/* 1914 */       line = line + skill.getLocalizedName(true);
/*      */       
/* 1916 */       line = line + StringHelper.repeat(" ", 15 - line.length());
/*      */       
/* 1918 */       line = line + skill.getLocalizedName(false);
/*      */       
/* 1920 */       line = line + StringHelper.repeat(" ", 36 - line.length());
/*      */       
/* 1922 */       line = line + skill.getLocalizedDescription() + ".";
/*      */       
/* 1924 */       sb.append(line);
/* 1925 */       sb.append(newline);
/*      */     } 
/*      */     
/* 1928 */     fw.write(sb.toString());
/* 1929 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeMobExperienceFile(File dir) throws Exception {
/* 1934 */     FileWriter fw = new FileWriter(dir.getPath() + "/mob_experience.txt");
/*      */     
/* 1936 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 1938 */     sb.append("Only mobs that drop experience are listed here." + newline + newline);
/*      */     
/* 1940 */     Iterator<EntityListEntry> i = EntityList.entries.iterator();
/*      */     
/* 1942 */     while (i.hasNext()) {
/*      */       
/* 1944 */       EntityListEntry entry = i.next();
/*      */       
/* 1946 */       if (EntityLiving.class.isAssignableFrom(entry._class) && !Modifier.isAbstract(entry._class.getModifiers())) {
/*      */         
/* 1948 */         EntityLiving entity_living = entry._class.getConstructor(new Class[] { World.class }).newInstance(new Object[] { null });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1956 */         if (entity_living instanceof EntityCubic) {
/* 1957 */           ((EntityCubic)entity_living).setSize(4);
/*      */         }
/* 1959 */         int experience_value = entity_living.getExperienceValue();
/*      */         
/* 1961 */         if (experience_value > 0) {
/*      */           
/* 1963 */           sb.append("Entity[" + entry.id + "] " + entity_living.getEntityName() + ": " + experience_value);
/*      */           
/* 1965 */           sb.append(newline);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1970 */     fw.write(sb.toString());
/* 1971 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeCommandsFile(File dir) throws Exception {
/* 1976 */     FileWriter fw = new FileWriter(dir.getPath() + "/commands.txt");
/*      */     
/* 1978 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 1980 */     sb.append("These commands are available to all players." + newline + newline);
/*      */     
/* 1982 */     for (int i = 0; i < (EnumCommand.values()).length; i++) {
/*      */       
/* 1984 */       EnumCommand command = EnumCommand.values()[i];
/* 1985 */       sb.append("/" + command.text + StringHelper.repeat(" ", 20 - command.text.length()) + command.description + newline);
/*      */     } 
/*      */     
/* 1988 */     fw.write(sb.toString());
/* 1989 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void write() {
/* 1994 */     running = true;
/*      */ 
/*      */     
/*      */     try {
/* 1998 */       FileUtils.deleteDirectory(new File("reference"));
/* 1999 */       FileUtils.deleteDirectory(new File("MITE/reference"));
/*      */     }
/* 2001 */     catch (Exception e) {}
/*      */ 
/*      */     
/*      */     try {
/* 2005 */       File dir = new File("MITE/reference");
/*      */       
/* 2007 */       if (!dir.exists()) {
/* 2008 */         dir.mkdir();
/*      */       }
/* 2010 */       writeBlockMaterialFile(dir);
/* 2011 */       writeBlockConstantsFile(dir);
/* 2012 */       writeBlockHardnessFile(dir);
/* 2013 */       writeBlockMetadataFile(dir);
/* 2014 */       writeBlockDissolveTimeFile(dir);
/* 2015 */       writeSilkHarvestFile(dir);
/* 2016 */       writeHarvestLevelFile(dir);
/* 2017 */       writeRecipeComponentsFile(dir);
/* 2018 */       writeItemSubtypesFile(dir);
/* 2019 */       writeItemMaterialFile(dir);
/* 2020 */       writeItemDurabilityFile(dir);
/* 2021 */       writeItemBurnTimeFile(dir);
/* 2022 */       writeItemCompostingFile(dir);
/* 2023 */       writeItemRecipesFile(dir);
/* 2024 */       writeFurnaceRecipesFile(dir);
/* 2025 */       writeItemXpReqsFile(dir);
/* 2026 */       writeFoodValueFile(dir);
/* 2027 */       writeItemReachFile(dir);
/* 2028 */       writeDamageVsEntityFile(dir);
/* 2029 */       writeArmorProtectionFile(dir);
/* 2030 */       writeEnchantmentsFile(dir);
/* 2031 */       writeItemEnchantmentsFile(dir);
/* 2032 */       writeStackLimitsFile(dir);
/* 2033 */       writeMaterialDurabilityFile(dir);
/* 2034 */       writeMaterialMaxQualityFile(dir);
/* 2035 */       writeMaterialEnchantabilityFile(dir);
/* 2036 */       writeToolDecayRateFiles(dir);
/* 2037 */       writeToolHarvestEfficiencyFiles(dir);
/* 2038 */       writePlayerLevelsFile(dir);
/* 2039 */       writeProfessionsFile(dir);
/* 2040 */       writeMobExperienceFile(dir);
/* 2041 */       writeCommandsFile(dir);
/*      */       
/* 2043 */       if (Minecraft.inDevMode()) {
/*      */         
/* 2045 */         writeBlockOpacityFile(dir);
/*      */         
/* 2047 */         writeIsOpaqueStandardFormCubeFile(dir);
/*      */ 
/*      */         
/* 2050 */         writeNormalCubeFile(dir);
/* 2051 */         writeBlockMetadataToSubtypeFile(dir);
/* 2052 */         writeAllowsGrassBeneathFile(dir);
/* 2053 */         writeUseNeighborBrightnessFile(dir);
/* 2054 */         writeBlockRenderTypeFile(dir);
/*      */       } 
/*      */       
/* 2057 */       System.out.println("Writing reference files... [ok]\n");
/*      */     }
/* 2059 */     catch (Exception e) {
/*      */       
/* 2061 */       System.out.println("Writing reference files... [failed]\n");
/*      */       
/* 2063 */       if (Minecraft.inDevMode()) {
/* 2064 */         e.printStackTrace();
/*      */       }
/*      */     } 
/* 2067 */     running = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void writeBlockOpacityFile(File dir) throws Exception {
/* 2074 */     FileWriter fw = new FileWriter(dir.getPath() + "/block_opacity.txt");
/*      */     
/* 2076 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 2078 */     sb.append("Block Opacity" + newline);
/* 2079 */     sb.append("-------------" + newline);
/*      */     
/* 2081 */     for (int i = 0; i < 256; i++) {
/*      */       
/* 2083 */       Block block = Block.blocksList[i];
/*      */       
/* 2085 */       if (block != null) {
/*      */ 
/*      */         
/* 2088 */         sb.append("Block[" + block.blockID + "] ");
/* 2089 */         sb.append(block.getNameForReferenceFile(0, true) + ": " + Block.lightOpacity[block.blockID]);
/* 2090 */         sb.append(newline);
/*      */       } 
/*      */     } 
/* 2093 */     fw.write(sb.toString());
/* 2094 */     fw.close();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void writeIsOpaqueStandardFormCubeFile(File dir) throws Exception {
/* 2124 */     FileWriter fw = new FileWriter(dir.getPath() + "/is_opaque_standard_form_cube.txt");
/*      */     
/* 2126 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 2128 */     sb.append("Is Opaque Standard Form Cube" + newline);
/* 2129 */     sb.append("----------------------------" + newline);
/*      */     
/* 2131 */     for (int i = 0; i < 256; i++) {
/*      */       
/* 2133 */       Block block = Block.blocksList[i];
/*      */       
/* 2135 */       if (block != null) {
/*      */ 
/*      */         
/* 2138 */         sb.append("Block[" + block.blockID + "] ");
/* 2139 */         sb.append(block.getNameForReferenceFile(0, true) + ": " + block.isAlwaysOpaqueStandardFormCube());
/*      */         
/* 2141 */         sb.append(newline);
/*      */       } 
/*      */     } 
/* 2144 */     fw.write(sb.toString());
/* 2145 */     fw.close();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void writeNormalCubeFile(File dir) throws Exception {
/* 2201 */     FileWriter fw = new FileWriter(dir.getPath() + "/normal_cube.txt");
/*      */     
/* 2203 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 2205 */     sb.append("Normal Cube" + newline);
/* 2206 */     sb.append("-----------" + newline);
/*      */     
/* 2208 */     for (int i = 0; i < 256; i++) {
/*      */       
/* 2210 */       Block block = Block.blocksList[i];
/*      */       
/* 2212 */       if (block != null) {
/*      */ 
/*      */         
/* 2215 */         sb.append("Block[" + block.blockID + "] ");
/* 2216 */         sb.append(block.getNameForReferenceFile(0, true) + ": " + block.is_normal_cube);
/* 2217 */         sb.append(newline);
/*      */       } 
/*      */     } 
/* 2220 */     fw.write(sb.toString());
/* 2221 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeBlockMetadataToSubtypeFile(File dir) throws Exception {
/* 2226 */     FileWriter fw = new FileWriter(dir.getPath() + "/block_metadata_to_subtype.txt");
/*      */     
/* 2228 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 2230 */     sb.append("Metadata to Subtype" + newline);
/* 2231 */     sb.append("-------------------" + newline);
/*      */     
/* 2233 */     for (int i = 0; i < 256; i++) {
/*      */       
/* 2235 */       Block block = Block.blocksList[i];
/*      */       
/* 2237 */       if (block != null)
/*      */       {
/*      */         
/* 2240 */         for (int metadata = 0; metadata < 16; metadata++) {
/*      */           
/* 2242 */           if (block.isValidMetadata(metadata)) {
/*      */ 
/*      */             
/* 2245 */             sb.append("Block[" + block.blockID + "][" + metadata + "] ");
/*      */             
/* 2247 */             int block_subtype = block.getBlockSubtype(metadata);
/* 2248 */             int item_subtype = block.getItemSubtype(metadata);
/*      */             
/* 2250 */             if (block_subtype == item_subtype) {
/* 2251 */               sb.append(block.getNameForReferenceFile(0, true) + ": " + block_subtype);
/*      */             } else {
/* 2253 */               sb.append(block.getNameForReferenceFile(0, true) + ": " + block_subtype + " vs " + item_subtype);
/*      */             } 
/* 2255 */             sb.append(newline);
/*      */           } 
/*      */         }  } 
/*      */     } 
/* 2259 */     fw.write(sb.toString());
/* 2260 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeAllowsGrassBeneathFile(File dir) throws Exception {
/* 2265 */     FileWriter fw = new FileWriter(dir.getPath() + "/allows_grass_beneath.txt");
/*      */     
/* 2267 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 2269 */     sb.append("Allows Grass Beneath" + newline);
/* 2270 */     sb.append("--------------------" + newline);
/*      */     
/* 2272 */     for (int i = 0; i < 256; i++) {
/*      */       
/* 2274 */       Block block = Block.blocksList[i];
/*      */       
/* 2276 */       if (block != null) {
/*      */ 
/*      */         
/* 2279 */         sb.append("Block[" + block.blockID + "] ");
/* 2280 */         sb.append(block.getNameForReferenceFile(0, true) + ": " + Block.canHaveLightValue[block.blockID]);
/* 2281 */         sb.append(newline);
/*      */       } 
/*      */     } 
/* 2284 */     fw.write(sb.toString());
/* 2285 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeUseNeighborBrightnessFile(File dir) throws Exception {
/* 2290 */     FileWriter fw = new FileWriter(dir.getPath() + "/use_neighbor_brightness.txt");
/*      */     
/* 2292 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 2294 */     sb.append("Use Neighbor Brightness" + newline);
/* 2295 */     sb.append("-----------------------" + newline);
/*      */     
/* 2297 */     for (int i = 0; i < 256; i++) {
/*      */       
/* 2299 */       Block block = Block.blocksList[i];
/*      */       
/* 2301 */       if (block != null) {
/*      */ 
/*      */         
/* 2304 */         sb.append("Block[" + block.blockID + "] ");
/* 2305 */         sb.append(block.getNameForReferenceFile(0, true) + ": " + Block.useNeighborBrightness[block.blockID]);
/* 2306 */         sb.append(newline);
/*      */       } 
/*      */     } 
/* 2309 */     fw.write(sb.toString());
/* 2310 */     fw.close();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void writeBlockRenderTypeFile(File dir) throws Exception {
/* 2315 */     FileWriter fw = new FileWriter(dir.getPath() + "/block_render_type.txt");
/*      */     
/* 2317 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 2319 */     sb.append("Block Render Type" + newline);
/* 2320 */     sb.append("-----------------" + newline);
/*      */     
/* 2322 */     for (int i = 0; i < 256; i++) {
/*      */       
/* 2324 */       Block block = Block.blocksList[i];
/*      */       
/* 2326 */       if (block != null) {
/*      */ 
/*      */         
/* 2329 */         sb.append("Block[" + block.blockID + "] ");
/* 2330 */         sb.append(block.getNameForReferenceFile(0, true) + ": " + block.getRenderType());
/*      */         
/* 2332 */         if (block.renderAsNormalBlock()) {
/* 2333 */           sb.append(" as Normal Block");
/*      */         }
/* 2335 */         sb.append(", Pass " + block.getRenderBlockPass());
/*      */         
/* 2337 */         sb.append(newline);
/*      */       } 
/*      */     } 
/* 2340 */     fw.write(sb.toString());
/* 2341 */     fw.close();
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ReferenceFileWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */