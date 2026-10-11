package com.thejebforge.trickster_math_tricks.revision;

import com.mojang.serialization.Lifecycle;
import com.thejebforge.trickster_math_tricks.TricksterMathTricks;
import com.thejebforge.trickster_math_tricks.fragment.QuaternionFragment;
import dev.enjarai.trickster.spell.Pattern;
import dev.enjarai.trickster.spell.fragment.NumberFragment;
import dev.enjarai.trickster.spell.revision.ConstantRevision;
import dev.enjarai.trickster.spell.revision.Revision;
import dev.enjarai.trickster.spell.revision.Revisions;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryInfo;
import org.joml.Quaterniond;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ModRevisions {
    private static final Map<Pattern, Revision> LOOKUP = new HashMap<>();

    public static final RegistryKey<Registry<Revision>> REGISTRY_KEY = RegistryKey.ofRegistry(TricksterMathTricks.id("revision"));
    public static final Registry<Revision> REGISTRY = FabricRegistryBuilder.from(new SimpleRegistry<>(REGISTRY_KEY, Lifecycle.stable()) {
        @Override
        public RegistryEntry.Reference<Revision> add(RegistryKey<Revision> key, Revision value, RegistryEntryInfo info) {
            if (LOOKUP.containsKey(value.pattern())) {
                TricksterMathTricks.LOGGER.warn(
                        "WARNING: A mod is overriding a pattern that is already defined! This may result in one of the tricks being unusable. ({} overrode {})",
                        key.getValue(), getId(LOOKUP.get(value.pattern()))
                );
            }

            LOOKUP.put(value.pattern(), value);
            return super.add(key, value, info);
        }
    }).buildAndRegister();

    public static Revision MAIN_REVISION = Revisions.register(
            TricksterMathTricks.id("main_revision"), new MathLibRevision()
    );

    public static Revision PI_CONSTANT = register("pi", new ConstantRevision(
            Pattern.of(6, 0, 2, 8, 5),
            new NumberFragment(Math.PI)
    ));
    public static Revision IDENTITY_QUATERNION = register("quat_ident", new ConstantRevision(
            Pattern.of(3, 4, 8, 7, 6, 3),
            new QuaternionFragment(new Quaterniond())
    ));

    public static Revision register(String path, Revision revision) {
        return Registry.register(REGISTRY, TricksterMathTricks.id(path), revision);
    }

    public static Optional<Revision> lookup(Pattern pattern) {
        return Optional.ofNullable(LOOKUP.get(pattern));
    }

    public static void register() {
        // init!
    }
}
