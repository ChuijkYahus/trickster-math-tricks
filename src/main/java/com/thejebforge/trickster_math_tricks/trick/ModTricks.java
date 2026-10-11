package com.thejebforge.trickster_math_tricks.trick;

import com.mojang.serialization.Lifecycle;
import com.thejebforge.trickster_math_tricks.TricksterMathTricks;
import com.thejebforge.trickster_math_tricks.trick.caster.CasterQuaternionTrick;
import com.thejebforge.trickster_math_tricks.trick.common.*;
import com.thejebforge.trickster_math_tricks.trick.entity.EntityQuaternionTrick;
import com.thejebforge.trickster_math_tricks.trick.quats.QuaternionEulerTrick;
import com.thejebforge.trickster_math_tricks.trick.quats.QuaternionFromAxisAngleTrick;
import com.thejebforge.trickster_math_tricks.trick.quats.QuaternionFromComponentsTrick;
import dev.enjarai.trickster.spell.Pattern;
import dev.enjarai.trickster.spell.trick.Trick;
import dev.enjarai.trickster.spell.trick.Tricks;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryInfo;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ModTricks {
    private static final Map<Pattern, Trick<?>> LOOKUP = new HashMap<>();

    public static final RegistryKey<Registry<Trick<?>>> REGISTRY_KEY = RegistryKey.ofRegistry(TricksterMathTricks.id("trick"));
    public static final Registry<Trick<?>> REGISTRY = FabricRegistryBuilder.from(new SimpleRegistry<>(REGISTRY_KEY, Lifecycle.stable()) {
        @Override
        public RegistryEntry.Reference<Trick<?>> add(RegistryKey<Trick<?>> key, Trick<?> value, RegistryEntryInfo info) {
            if (LOOKUP.containsKey(value.getPattern())) {
                TricksterMathTricks.LOGGER.warn(
                        "WARNING: Pattern like that is already defined! ({} overrode {})",
                        key.getValue(), getId(LOOKUP.get(value.getPattern()))
                );
            }

            LOOKUP.put(value.getPattern(), value);
            return super.add(key, value, info);
        }
    }).buildAndRegister();

    // Registered with normal registry
    public static final MathLibTrick MAIN_TRICK = Registry.register(Tricks.REGISTRY, TricksterMathTricks.id("math_lib"), new MathLibTrick());
    public static final ExtractWTrick EXTRACT_W = Registry.register(Tricks.REGISTRY, TricksterMathTricks.id("extract_w"), new ExtractWTrick());
    public static final SquaredMagnitudeTrick SQUARED_MAGNITUDE_TRICK = Registry.register(Tricks.REGISTRY, TricksterMathTricks.id("squared_magnitude"), new SquaredMagnitudeTrick());


    // Custom registry
    public static final QuaternionFromAxisAngleTrick QUATERNION_FROM_EULER = register("quat_from_axis", new QuaternionFromAxisAngleTrick());
    public static final QuaternionFromComponentsTrick QUATERNION_FROM_COMPONENTS_TRICK = register("quat_from_comp", new QuaternionFromComponentsTrick());
    public static final QuaternionEulerTrick QUATERNION_EULER_TRICK = register("quat_euler", new QuaternionEulerTrick());
    public static final CasterQuaternionTrick CASTER_QUATERNION_TRICK = register("caster_quaternion", new CasterQuaternionTrick());
    public static final EntityQuaternionTrick ENTITY_QUATERNION_TRICK = register("entity_quaternion", new EntityQuaternionTrick());

    private static <T extends Trick<?>> T register(String path, T trick) {
        return Registry.register(REGISTRY, TricksterMathTricks.id(path), trick);
    }

    @Nullable
    public static Trick<?> lookup(Pattern pattern) {
        return LOOKUP.get(pattern);
    }

    public static void register() {
        // init
    }
}
