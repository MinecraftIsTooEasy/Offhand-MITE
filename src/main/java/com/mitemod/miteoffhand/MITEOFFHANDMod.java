package com.mitemod.miteoffhand;

import com.mitemod.miteoffhand.event.EventListeners;
import net.fabricmc.api.ModInitializer;
import net.xiaoyu233.fml.ModResourceManager;

@SuppressWarnings("unused")
public class MITEOFFHANDMod implements ModInitializer {
//    public static final String MOD_ID = "mite_offhand";
    public static final String OffhandNameSpace = "mite_offhand";

    public void onInitialize() {
        EventListeners.registerAllEvents();
        ModResourceManager.addResourcePackDomain("miteassistant");

        // 注册客户端到服务器（C2S）的数据包读取器（通过 Packet250CustomPayload 实现 RIC 网络桥接）
        moddedmite.rustedironcore.network.PacketReader.registerServerPacketReader(
                com.mitemod.miteoffhand.network.SwapOffhandC2SPacket.CHANNEL,
                com.mitemod.miteoffhand.network.SwapOffhandC2SPacket::new
        );
        moddedmite.rustedironcore.network.PacketReader.registerServerPacketReader(
                com.mitemod.miteoffhand.network.UseOffhandC2SPacket.CHANNEL,
                com.mitemod.miteoffhand.network.UseOffhandC2SPacket::new
        );

        // 服务端：在玩家登录时，将副手物品同步给客户端
        moddedmite.rustedironcore.api.event.Handlers.PlayerEvent.register(new com.mitemod.miteoffhand.offhand.OffhandPlayerEventListener());

        // 仅客户端：注册数据包读取器 + 快捷键绑定 + 游戏刻监听器
        if (!moddedmite.rustedironcore.api.util.FabricUtil.isServer()) {
            moddedmite.rustedironcore.network.PacketReader.registerClientPacketReader(
                    com.mitemod.miteoffhand.network.SyncOffhandS2CPacket.CHANNEL,
                    com.mitemod.miteoffhand.network.SyncOffhandS2CPacket::new
            );
            moddedmite.rustedironcore.api.event.Handlers.Keybinding.register(com.mitemod.miteoffhand.offhand.OffhandKeybindListener.INSTANCE);
            moddedmite.rustedironcore.api.event.Handlers.Tick.register(com.mitemod.miteoffhand.offhand.OffhandKeybindListener.INSTANCE);
        }
    }
}
