package moddedmite.offhand.api.core;

public interface IOffhandClientState {

    void markMainhandRenderSuppressed(int ticks);

    boolean shouldSuppressMainhandRender();

    void markOffhandAttackAnimation(int ticks);

    boolean shouldAnimateOffhandAttack();

    void markOffhandMiningAnimation(int ticks);

    boolean shouldAnimateOffhandMining();
}
