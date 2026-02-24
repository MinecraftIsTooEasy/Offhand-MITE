package com.m.offhand.mixin.font;

import net.minecraft.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FontRenderer.class)
public abstract class MixinFontRenderer {

    /**
     * 拦截所有字符，把非法 Unicode 折叠为安全 ASCII
     */
    @ModifyVariable(
            method = "renderCharAtPos",
            at = @At("HEAD"),
            ordinal = 0
    )
    private int sanitizeChar(int c) {

        // 原版只支持 0–255
        if (c < 0 || c >= 256) {
            return 63; // '?' 兜底字符
        }

        return c;
    }
}

