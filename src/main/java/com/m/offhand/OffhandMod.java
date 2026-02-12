package com.m.offhand;

import com.m.offhand.config.OffhandConfig;
import com.m.offhand.network.OffhandPacketHandler;
import com.m.offhand.offhand.OffhandKeybindListener;
import net.fabricmc.api.ModInitializer;
import net.xiaoyu233.fml.ModResourceManager;
import net.xiaoyu233.fml.config.ConfigRegistry;

import java.util.Optional;

public class OffhandMod implements ModInitializer {
    public static final String MOD_NAME = "mite_offhand";
    public static final String MITEOFFHAND = "offhand";

    @Override
    public Optional<ConfigRegistry> createConfig() {
        return Optional.of(new ConfigRegistry(OffhandConfig.ROOT, OffhandConfig.CONFIG_FILE));
    }

    @Override
    public void onInitialize() {
        OffhandConfig.init();
        
        ModResourceManager.addResourcePackDomain(MITEOFFHAND);
        OffhandPacketHandler.register();

        if (!moddedmite.rustedironcore.api.util.FabricUtil.isServer()) {
            moddedmite.rustedironcore.api.event.Handlers.Keybinding.register(OffhandKeybindListener.INSTANCE);
            moddedmite.rustedironcore.api.event.Handlers.Tick.register(OffhandKeybindListener.INSTANCE);
        }
    }
}
