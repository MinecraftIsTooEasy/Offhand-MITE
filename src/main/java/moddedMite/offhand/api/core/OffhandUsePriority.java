package moddedMite.offhand.api.core;

public final class OffhandUsePriority {

    private OffhandUsePriority() {
    }

    public static boolean shouldTryOffhand(
        boolean mainhandWasAttempted,
        boolean mainhandWasCancelled,
        boolean mainhandAllowedNoActions) {
        return mainhandWasAttempted && !mainhandWasCancelled && mainhandAllowedNoActions;
    }
}
