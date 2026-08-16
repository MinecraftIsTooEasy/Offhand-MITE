package moddedmite.offhand.api.compat;

import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;

public interface IOffhandActionFilter {

    default boolean cancelOffhandRightClick(
        EntityPlayer player,
        ItemStack mainhand,
        ItemStack offhand) {
        return false;
    }

    default boolean cancelOffhandSwapKey(
        EntityPlayer player,
        ItemStack mainhand,
        ItemStack offhand) {
        return false;
    }

    default boolean cancelOffhandSwapPacket(
        EntityPlayer player,
        ItemStack mainhand,
        ItemStack offhand) {
        return false;
    }
}
