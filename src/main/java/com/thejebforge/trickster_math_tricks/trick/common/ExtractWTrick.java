package com.thejebforge.trickster_math_tricks.trick.common;

import com.thejebforge.trickster_math_tricks.fragment.ModFragmentTypes;
import com.thejebforge.trickster_math_tricks.fragment.QuaternionFragment;
import dev.enjarai.trickster.spell.Pattern;
import dev.enjarai.trickster.spell.SpellContext;
import dev.enjarai.trickster.spell.fragment.FragmentType;
import dev.enjarai.trickster.spell.fragment.NumberFragment;
import dev.enjarai.trickster.spell.trick.DistortionTrick;
import dev.enjarai.trickster.spell.type.Signature;

public class ExtractWTrick extends DistortionTrick<ExtractWTrick> {
    public ExtractWTrick() {
        super(Pattern.of(0, 2, 5), Signature.of(ModFragmentTypes.QUATERNION, ExtractWTrick::extract, FragmentType.NUMBER));
    }

    public NumberFragment extract(SpellContext spellContext, QuaternionFragment quat) {
        return new NumberFragment(quat.quaternion().w());
    }
}
