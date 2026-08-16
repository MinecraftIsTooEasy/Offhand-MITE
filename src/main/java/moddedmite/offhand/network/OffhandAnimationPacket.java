package moddedmite.offhand.network;

import moddedmite.offhand.api.core.IOffhandPlayer;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.Entity;
import net.minecraft.EntityPlayer;
import net.minecraft.ResourceLocation;

public class OffhandAnimationPacket implements Packet {

    public static final ResourceLocation CHANNEL = new ResourceLocation("offhand", "animation");

    private final boolean compatible;
    private int entityId;

    public OffhandAnimationPacket(PacketByteBuf buf) {
        int protocolVersion = buf.readInt();
        this.compatible = OffhandPacketHandler.isCompatible(protocolVersion);
        if (!this.compatible) {
            this.entityId = -1;
            OffhandPacketHandler.warnProtocolMismatch(CHANNEL.toString(), protocolVersion);
            return;
        }
        this.entityId = buf.readInt();
    }

    public OffhandAnimationPacket(int entityId) {
        this.compatible = true;
        this.entityId = entityId;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeInt(OffhandPacketHandler.PROTOCOL_VERSION);
        buf.writeInt(this.entityId);
    }

    @Override
    public void apply(EntityPlayer player) {
        if (!this.compatible || player == null || player.worldObj == null) {
            return;
        }

        Entity target = player.worldObj.getEntityByID(this.entityId);
        if (target instanceof EntityPlayer) {
            ((IOffhandPlayer) target).swingOffItem();
        }
    }

    @Override
    public ResourceLocation getChannel() {
        return CHANNEL;
    }

    @Environment(EnvType.SERVER)
    public static void sendToTracking(EntityPlayer target) {
        if (target != null) {
            OffhandNetDispatch.sendToTracking(target, new OffhandAnimationPacket(target.entityId));
        }
    }
}
