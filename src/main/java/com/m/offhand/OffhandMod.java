package com.m.offhand;

import com.m.offhand.event.FishEventListen;
import com.m.offhand.network.SwapOffhandC2SPacket;
import com.m.offhand.network.SyncOffhandS2CPacket;
import com.m.offhand.network.UseOffhandC2SPacket;
import com.m.offhand.offhand.OffhandKeybindListener;
import com.m.offhand.offhand.OffhandPlayerEventListener;
import net.fabricmc.api.ModInitializer;
import net.xiaoyu233.fml.ModResourceManager;
import net.xiaoyu233.fml.reload.event.MITEEvents;

@SuppressWarnings("unused")
public class OffhandMod implements ModInitializer {
//    public static final String MOD_ID = "mite_offhand";
    public static final String NameSpace = "offhand";

    public void onInitialize() {
        MITEEvents.MITE_EVENT_BUS.register(new FishEventListen());
        ModResourceManager.addResourcePackDomain(NameSpace);

        // 注册客户端到服务器（C2S）的数据包读取器（通过 Packet250CustomPayload 实现 RIC 网络桥接）
        moddedmite.rustedironcore.network.PacketReader.registerServerPacketReader(
                SwapOffhandC2SPacket.CHANNEL,
                SwapOffhandC2SPacket::new
        );
        moddedmite.rustedironcore.network.PacketReader.registerServerPacketReader(
               UseOffhandC2SPacket.CHANNEL,
               UseOffhandC2SPacket::new
        );

        // 服务端：在玩家登录时，将副手物品同步给客户端
        moddedmite.rustedironcore.api.event.Handlers.PlayerEvent.register(new OffhandPlayerEventListener());

        // 仅客户端：注册数据包读取器 + 快捷键绑定 + 游戏刻监听器
        if (!moddedmite.rustedironcore.api.util.FabricUtil.isServer()) {
            moddedmite.rustedironcore.network.PacketReader.registerClientPacketReader(
                    SyncOffhandS2CPacket.CHANNEL,
                    SyncOffhandS2CPacket::new
            );
            moddedmite.rustedironcore.api.event.Handlers.Keybinding.register(OffhandKeybindListener.INSTANCE);
            moddedmite.rustedironcore.api.event.Handlers.Tick.register(OffhandKeybindListener.INSTANCE);
        }
    }
}
