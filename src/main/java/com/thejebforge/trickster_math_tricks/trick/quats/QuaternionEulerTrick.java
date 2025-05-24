package com.thejebforge.trickster_math_tricks.trick.quats;

import com.thejebforge.trickster_math_tricks.fragment.ModFragmentTypes;
import com.thejebforge.trickster_math_tricks.fragment.QuaternionFragment;
import com.thejebforge.trickster_math_tricks.trick.base.MathDistortTrick;
import dev.enjarai.trickster.spell.Fragment;
import dev.enjarai.trickster.spell.Pattern;
import dev.enjarai.trickster.spell.SpellContext;
import dev.enjarai.trickster.spell.blunder.BlunderException;
import dev.enjarai.trickster.spell.fragment.VectorFragment;
import dev.enjarai.trickster.spell.type.Signature;
import org.joml.Vector3d;

import java.util.List;

public class QuaternionEulerTrick extends MathDistortTrick<QuaternionEulerTrick> {
    public QuaternionEulerTrick() {
        super(Pattern.of(3, 6, 7, 4, 1, 2, 5, 4, 3), Signature.of(ModFragmentTypes.QUATERNION, QuaternionEulerTrick::euler));
    }

    public Fragment euler(SpellContext ctx, QuaternionFragment quaternionFragment) throws BlunderException {
        return new VectorFragment(quaternionFragment.quaternion().getEulerAnglesYXZ(new Vector3d()));
    }
}
