package com.thejebforge.trickster_math_tricks.trick.caster;

import com.thejebforge.trickster_math_tricks.fragment.ModFragmentTypes;
import com.thejebforge.trickster_math_tricks.fragment.QuaternionFragment;
import com.thejebforge.trickster_math_tricks.trick.base.MathTrick;
import dev.enjarai.trickster.spell.Pattern;
import dev.enjarai.trickster.spell.SpellContext;
import dev.enjarai.trickster.spell.exception.TricksterEngineException;
import dev.enjarai.trickster.spell.exception.blunder.IncompatibleSourceBlunder;
import dev.enjarai.trickster.spell.type.Signature;

import static com.thejebforge.trickster_math_tricks.MathUtils.getEntityRotation;

public class CasterQuaternionTrick extends MathTrick<CasterQuaternionTrick> {
    public CasterQuaternionTrick() {
        super(Pattern.of(1, 0, 3, 6, 4, 2, 5, 8, 7), Signature.of(CasterQuaternionTrick::run, ModFragmentTypes.QUATERNION));
    }

    public QuaternionFragment run(SpellContext spellContext) throws TricksterEngineException {
        var caster = spellContext.source().getCaster().orElseThrow(IncompatibleSourceBlunder::new);

        return new QuaternionFragment(getEntityRotation(caster));
    }
}
