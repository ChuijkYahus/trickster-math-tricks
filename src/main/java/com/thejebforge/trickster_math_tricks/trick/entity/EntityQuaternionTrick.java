package com.thejebforge.trickster_math_tricks.trick.entity;

import com.thejebforge.trickster_math_tricks.fragment.ModFragmentTypes;
import com.thejebforge.trickster_math_tricks.fragment.QuaternionFragment;
import com.thejebforge.trickster_math_tricks.trick.base.MathDistortTrick;
import dev.enjarai.trickster.spell.Pattern;
import dev.enjarai.trickster.spell.SpellContext;
import dev.enjarai.trickster.spell.exception.TricksterEngineException;
import dev.enjarai.trickster.spell.exception.blunder.IncompatibleSourceBlunder;
import dev.enjarai.trickster.spell.fragment.EntityFragment;
import dev.enjarai.trickster.spell.fragment.FragmentType;
import dev.enjarai.trickster.spell.type.Signature;

import static com.thejebforge.trickster_math_tricks.MathUtils.getEntityRotation;

public class EntityQuaternionTrick extends MathDistortTrick<EntityQuaternionTrick> {
    public EntityQuaternionTrick() {
        super(
                Pattern.of(0, 1, 2, 5, 4, 3, 6, 7, 8),
                Signature.of(FragmentType.ENTITY, EntityQuaternionTrick::headRotation, ModFragmentTypes.QUATERNION)
        );
    }

    public QuaternionFragment headRotation(SpellContext ctx, EntityFragment entityFragment) throws TricksterEngineException {
        var entity = entityFragment.getEntity(ctx)
                .orElseThrow(IncompatibleSourceBlunder::new);

        return new QuaternionFragment(getEntityRotation(entity));
    }
}
