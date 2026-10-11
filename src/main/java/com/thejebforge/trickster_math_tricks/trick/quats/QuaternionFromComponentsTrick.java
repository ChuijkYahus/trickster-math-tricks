package com.thejebforge.trickster_math_tricks.trick.quats;

import com.thejebforge.trickster_math_tricks.fragment.ModFragmentTypes;
import com.thejebforge.trickster_math_tricks.fragment.QuaternionFragment;
import com.thejebforge.trickster_math_tricks.trick.base.MathDistortTrick;
import dev.enjarai.trickster.spell.Pattern;
import dev.enjarai.trickster.spell.SpellContext;
import dev.enjarai.trickster.spell.exception.TricksterEngineException;
import dev.enjarai.trickster.spell.fragment.FragmentType;
import dev.enjarai.trickster.spell.fragment.NumberFragment;
import dev.enjarai.trickster.spell.type.Signature;
import org.joml.Quaterniond;

public class QuaternionFromComponentsTrick extends MathDistortTrick<QuaternionFromComponentsTrick> {
    public QuaternionFromComponentsTrick() {
        super(
                Pattern.of(3, 4, 5, 1, 3, 7, 4, 1),
                Signature.of(
                        FragmentType.NUMBER,
                        FragmentType.NUMBER,
                        FragmentType.NUMBER,
                        FragmentType.NUMBER,
                        QuaternionFromComponentsTrick::construct,
                        ModFragmentTypes.QUATERNION)
        );
    }

    public QuaternionFragment construct(SpellContext ctx, NumberFragment x, NumberFragment y, NumberFragment z, NumberFragment w) throws TricksterEngineException {
        return new QuaternionFragment(new Quaterniond(x.number(), y.number(), z.number(), w.number()));
    }
}
