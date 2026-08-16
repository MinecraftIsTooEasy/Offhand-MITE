package moddedmite.offhand.api.core;

import moddedmite.offhand.Offhand;
import net.minecraft.Block;
import net.minecraft.EntityPlayer;
import net.minecraft.Item;
import net.minecraft.ItemCudgel;
import net.minecraft.ItemStack;
import net.minecraft.ItemSword;

public final class OffhandActionHelper {

    private OffhandActionHelper() {
    }

    public static boolean hasUsableStack(ItemStack stack) {
        return stack != null && stack.stackSize > 0 && stack.getItem() != null;
    }

    public static boolean hasDifferentUsableOffhand(EntityPlayer player, ItemStack mainhand, ItemStack offhand) {
        if (player == null || !hasUsableStack(offhand) || offhand == mainhand) {
            return false;
        }

        return !Offhand.isOffhandBlacklisted(offhand);
    }

    public static boolean canDualMineWithTools(EntityPlayer player, Block block, int metadata, ItemStack mainhand, ItemStack offhand) {
        if (block == null || !hasDifferentUsableOffhand(player, mainhand, offhand)) {
            return false;
        }

        return isMiningTool(mainhand, block, metadata) && isMiningTool(offhand, block, metadata);
    }

    public static boolean canOffhandAttack(EntityPlayer player, ItemStack mainhand, ItemStack offhand) {
        return hasDifferentUsableOffhand(player, mainhand, offhand) && isMeleeAttackItem(offhand);
    }

    public static void recordDualMiningBlock(
        EntityPlayer player,
        int x,
        int y,
        int z,
        Block block,
        int metadata,
        ItemStack mainhand,
        ItemStack offhand) {
        if (canDualMineWithTools(player, block, metadata, mainhand, offhand)) {
            IOffhandActionState state = (IOffhandActionState) player;
            DualMiningBlock current = state.getDualMiningBlock();
            if (current != null
                && current.x == x
                && current.y == y
                && current.z == z
                && current.block == block
                && current.metadata == metadata
                && current.offhand == offhand) {
                return;
            }
            state.setDualMiningBlock(new DualMiningBlock(x, y, z, block, metadata, offhand));
        }
    }

    public static DualMiningBlock consumeDualMiningBlock(
        EntityPlayer player,
        int x,
        int y,
        int z,
        ItemStack offhand) {
        if (player == null) {
            return null;
        }

        IOffhandActionState state = (IOffhandActionState) player;
        DualMiningBlock block = state.getDualMiningBlock();
        state.setDualMiningBlock(null);
        if (block == null
            || block.x != x
            || block.y != y
            || block.z != z
            || block.offhand != offhand) {
            return null;
        }

        return block;
    }

    private static boolean isMiningTool(ItemStack stack, Block block, int metadata) {
        if (!hasUsableStack(stack)) {
            return false;
        }

        Item item = stack.getItem();
        return !(item instanceof ItemSword)
            && !(item instanceof ItemCudgel)
            && stack.getStrVsBlock(block, metadata) > 1.0F;
    }

    private static boolean isMeleeAttackItem(ItemStack stack) {
        return hasUsableStack(stack) && stack.getMeleeDamageBonus() > 0.0F;
    }

    public static final class DualMiningBlock {
        public final int x;
        public final int y;
        public final int z;
        public final Block block;
        public final int metadata;
        private final ItemStack offhand;

        private DualMiningBlock(int x, int y, int z, Block block, int metadata, ItemStack offhand) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.block = block;
            this.metadata = metadata;
            this.offhand = offhand;
        }
    }
}
