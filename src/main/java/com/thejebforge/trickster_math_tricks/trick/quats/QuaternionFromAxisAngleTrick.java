package com.thejebforge.trickster_math_tricks.trick.quats;

import com.thejebforge.trickster_math_tricks.fragment.QuaternionFragment;
import com.thejebforge.trickster_math_tricks.trick.base.MathDistortTrick;
import dev.enjarai.trickster.spell.Fragment;
import dev.enjarai.trickster.spell.Pattern;
import dev.enjarai.trickster.spell.SpellContext;
import dev.enjarai.trickster.spell.blunder.BlunderException;
import dev.enjarai.trickster.spell.fragment.FragmentType;
import dev.enjarai.trickster.spell.fragment.NumberFragment;
import dev.enjarai.trickster.spell.fragment.VectorFragment;
import dev.enjarai.trickster.spell.type.Signature;
import org.joml.AxisAngle4d;
import org.joml.Quaterniond;

import java.util.List;

public class QuaternionFromAxisAngleTrick extends MathDistortTrick<QuaternionFromAxisAngleTrick> {
    public QuaternionFromAxisAngleTrick() {
        super(Pattern.of( 6, 4, 3, 6, 7, 4, 2), Signature.of(FragmentType.VECTOR, FragmentType.NUMBER, QuaternionFromAxisAngleTrick::axisAngle));
    }

    public Fragment axisAngle(SpellContext spellContext, VectorFragment axis, NumberFragment angle) throws BlunderException {
        return new QuaternionFragment(new Quaterniond(new AxisAngle4d(angle.number(), axis.vector())));
    }
}
