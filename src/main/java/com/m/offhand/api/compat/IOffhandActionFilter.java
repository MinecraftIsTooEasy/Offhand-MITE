package com.m.offhand.api.compat;

import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;

/**
 * Allows other mods to veto specific offhand actions before they are executed.
 *
 * <p>Register an implementation via
 * {@link OffhandCompatRegistry#setActionFilter(IOffhandActionFilter)}.
 * All methods return {@code false} by default (i.e. nothing is blocked).</p>
 *
 * <h3>Covered action points</h3>
 * <ul>
 *   <li><b>offhand right-click use</b> – client-side, inside
 *       {@code MixinMinecraft.offhand$tryUseOffhandRightClick()}; cancelling here
 *       prevents the offhand item from being used on right-click.</li>
 *   <li><b>swap key</b> – client-side, inside
 *       {@code OffhandKeyHandler.onClientTick()}; cancelling here prevents the
 *       swap-key packet from being sent to the server.</li>
 *   <li><b>swap packet (server)</b> – server-side, inside
 *       {@code OffhandSwapRequestPacket.apply()}; cancelling here prevents the
 *       server from actually executing the inventory swap even if the packet arrived.</li>
 * </ul>
 */
public interface IOffhandActionFilter {

    /**
     * Called on the <b>client</b> just before the offhand item attempts a right-click use.
     *
     * @param player       the local client player
     * @param mainhand     the item currently in the main hand (may be {@code null})
     * @param offhand      the item currently in the offhand slot (never {@code null})
     * @return {@code true} to <em>cancel</em> the offhand right-click, {@code false} to allow it
     */
    default boolean cancelOffhandRightClick(EntityPlayer player, ItemStack mainhand, ItemStack offhand) {
        return false;
    }

    /**
     * Called on the <b>client</b> just before the swap-key packet is sent to the server.
     *
     * @param player       the local client player
     * @param mainhand     the item currently in the main hand (may be {@code null})
     * @param offhand      the item currently in the offhand slot (may be {@code null})
     * @return {@code true} to <em>cancel</em> the swap-key action, {@code false} to allow it
     */
    default boolean cancelOffhandSwapKey(EntityPlayer player, ItemStack mainhand, ItemStack offhand) {
        return false;
    }

    /**
     * Called on the <b>server</b> inside the swap-request packet handler, just before the
     * actual inventory swap is performed.
     *
     * @param player       the server-side player who sent the swap packet
     * @param mainhand     the item currently in the main hand (may be {@code null})
     * @param offhand      the item currently in the offhand slot (may be {@code null})
     * @return {@code true} to <em>cancel</em> the server-side swap, {@code false} to allow it
     */
    default boolean cancelOffhandSwapPacket(EntityPlayer player, ItemStack mainhand, ItemStack offhand) {
        return false;
    }
}