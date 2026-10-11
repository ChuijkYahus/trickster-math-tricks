package com.thejebforge.trickster_math_tricks.trick.common;

import com.thejebforge.trickster_math_tricks.fragment.ModFragmentTypes;
import com.thejebforge.trickster_math_tricks.fragment.QuaternionFragment;
import dev.enjarai.trickster.spell.Pattern;
import dev.enjarai.trickster.spell.SpellContext;
import dev.enjarai.trickster.spell.exception.TricksterEngineException;
import dev.enjarai.trickster.spell.fragment.FragmentType;
import dev.enjarai.trickster.spell.fragment.NumberFragment;
import dev.enjarai.trickster.spell.fragment.VectorFragment;
import dev.enjarai.trickster.spell.trick.DistortionTrick;
import dev.enjarai.trickster.spell.type.Signature;

public class SquaredMagnitudeTrick extends DistortionTrick<SquaredMagnitudeTrick> {
    public SquaredMagnitudeTrick() {
        super(
                Pattern.of(3, 4, 5, 2, 4, 1, 0, 4),
                Signature.of(FragmentType.VECTOR, SquaredMagnitudeTrick::sqrMagnitudeVec, FragmentType.NUMBER)
        );
        overload(Signature.of(ModFragmentTypes.QUATERNION, SquaredMagnitudeTrick::sqrMagnitudeQuat, FragmentType.NUMBER));
    }

    public NumberFragment sqrMagnitudeQuat(SpellContext spellContext, QuaternionFragment quaternion) throws TricksterEngineException {
        return new NumberFragment(quaternion.quaternion().lengthSquared());
    }

    public NumberFragment sqrMagnitudeVec(SpellContext spellContext, VectorFragment vector) throws TricksterEngineException {
        return new NumberFragment(vector.vector().lengthSquared());
    }
}
