package moddedmite.offhand.api.core;

public final class OffhandDamageCalculator {

    private OffhandDamageCalculator() {
    }

    public static float calculateBonus(
        float baseMeleeDamage,
        float enchantmentDamage,
        boolean critical,
        boolean suspendedInLiquid,
        boolean silverAgainstUndead,
        boolean crushingAgainstSkeleton) {
        float modifiedBaseDamage = baseMeleeDamage;
        if (critical && modifiedBaseDamage > 0.0F) {
            modifiedBaseDamage *= 1.5F;
        }
        if (silverAgainstUndead) {
            modifiedBaseDamage *= 1.25F;
        }
        if (crushingAgainstSkeleton) {
            modifiedBaseDamage *= 2.0F;
        }

        float bonus = modifiedBaseDamage + enchantmentDamage;
        return suspendedInLiquid ? bonus * 0.5F : bonus;
    }
}
