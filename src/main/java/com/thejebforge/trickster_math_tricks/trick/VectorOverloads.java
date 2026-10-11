package com.thejebforge.trickster_math_tricks.trick;

import com.thejebforge.trickster_math_tricks.fragment.ModFragmentTypes;
import com.thejebforge.trickster_math_tricks.fragment.QuaternionFragment;
import dev.enjarai.trickster.spell.SpellContext;
import dev.enjarai.trickster.spell.exception.TricksterEngineException;
import dev.enjarai.trickster.spell.fragment.FragmentType;
import dev.enjarai.trickster.spell.fragment.NumberFragment;
import dev.enjarai.trickster.spell.trick.Tricks;
import dev.enjarai.trickster.spell.trick.vector.*;
import dev.enjarai.trickster.spell.type.Signature;
import org.joml.Quaterniond;

public class VectorOverloads {
    public static NumberFragment extractXQuat(ExtractXTrick trick, SpellContext spellContext, QuaternionFragment quaternion) {
        return new NumberFragment(quaternion.quaternion().x());
    }

    public static NumberFragment extractYQuat(ExtractYTrick trick, SpellContext spellContext, QuaternionFragment quaternion) {
        return new NumberFragment(quaternion.quaternion().y());
    }

    public static NumberFragment extractZQuat(ExtractZTrick trick, SpellContext spellContext, QuaternionFragment quaternion) {
        return new NumberFragment(quaternion.quaternion().z());
    }

    public static NumberFragment magnitudeQuat(LengthTrick trick, SpellContext spellContext, QuaternionFragment quaternion) {
        return new NumberFragment(
                Math.sqrt(quaternion.quaternion().lengthSquared())
        );
    }

    public static QuaternionFragment normalizeQuat(NormalizeTrick trick, SpellContext spellContext, QuaternionFragment quaternion) throws TricksterEngineException {
        return new QuaternionFragment(quaternion.quaternion().normalize(new Quaterniond()));
    }

    public static void register() {
        Tricks.EXTRACT_X.overload(Signature.of(ModFragmentTypes.QUATERNION, VectorOverloads::extractXQuat, FragmentType.NUMBER));
        Tricks.EXTRACT_Y.overload(Signature.of(ModFragmentTypes.QUATERNION, VectorOverloads::extractYQuat, FragmentType.NUMBER));
        Tricks.EXTRACT_Z.overload(Signature.of(ModFragmentTypes.QUATERNION, VectorOverloads::extractZQuat, FragmentType.NUMBER));
        Tricks.LENGTH.overload(Signature.of(ModFragmentTypes.QUATERNION, VectorOverloads::magnitudeQuat, FragmentType.NUMBER));
        Tricks.NORMALIZE.overload(Signature.of(ModFragmentTypes.QUATERNION, VectorOverloads::normalizeQuat, ModFragmentTypes.QUATERNION));
    }
}
