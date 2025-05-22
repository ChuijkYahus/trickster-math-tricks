package com.thejebforge.trickster_math_tricks.trick.base;

import dev.enjarai.trickster.spell.EvaluationResult;
import dev.enjarai.trickster.spell.Fragment;
import dev.enjarai.trickster.spell.Pattern;
import dev.enjarai.trickster.spell.SpellContext;
import dev.enjarai.trickster.spell.blunder.BlunderException;
import dev.enjarai.trickster.spell.type.Signature;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class MathDistortTrick<T extends MathDistortTrick<T>> extends MathTrick<T> {
    private final Map<Fragment[], Fragment> cache = new LinkedHashMap<>() {
        @Override
        protected boolean removeEldestEntry(Map.Entry<Fragment[], Fragment> eldest) {
            return size() > 20;
        }
    };

    public MathDistortTrick(Pattern pattern) {
        super(pattern);
    }

    public MathDistortTrick(Pattern pattern, Signature<T> primary) {
        super(pattern, primary);
    }

    public MathDistortTrick(Pattern pattern, List<Signature<T>> handlers) {
        super(pattern, handlers);
    }

    @Override
    public EvaluationResult activate(SpellContext ctx, List<Fragment> fragments) throws BlunderException {
        var fragmentArray = fragments.toArray(new Fragment[0]);
        EvaluationResult result = cache.get(fragmentArray);

        if (result == null) {
            result = super.activate(ctx, fragments);

            if (result instanceof Fragment fragment) {
                cache.put(fragmentArray, fragment);
            }

        }

        return result;
    }
}