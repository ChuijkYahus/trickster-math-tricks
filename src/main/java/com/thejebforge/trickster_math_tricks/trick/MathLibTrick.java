package com.thejebforge.trickster_math_tricks.trick;

import com.thejebforge.trickster_math_tricks.trick.blunder.UnknownMathTrickBlunder;
import dev.enjarai.trickster.spell.*;
import dev.enjarai.trickster.spell.exception.blunder.IncompatibleTypesBlunder;
import dev.enjarai.trickster.spell.fragment.FragmentType;
import dev.enjarai.trickster.spell.trick.Trick;
import dev.enjarai.trickster.spell.type.ArgType;
import dev.enjarai.trickster.spell.type.RetType;
import dev.enjarai.trickster.spell.type.Signature;

import java.util.List;
import java.util.NoSuchElementException;

public class MathLibTrick extends Trick<MathLibTrick> {
    public MathLibTrick() {
        super(
                Pattern.of(6, 4, 3, 6, 7, 4, 8, 7),
                Signature.of(
                        FragmentType.PATTERN,
                        ArgType.ANY.variadicOfArg(),
                        MathLibTrick::run,
                        RetType.ANY.thisFunctionExistsSolelyForMessageListeningOnItemsBecauseWeAlreadyHadAnAbstractionForItAndWeReallyDontWantToReworkItSoThisWillHaveToDoHonestly()
                )
        );
    }

    public EvaluationResult run(SpellContext ctx, PatternGlyph first, List<Fragment> fragments) {
        try {
            var mathTrick = ModTricks.lookup(first.pattern());
            if (mathTrick == null) throw new UnknownMathTrickBlunder();

            return mathTrick.activate(ctx, fragments);
        } catch (NoSuchElementException e) {
            throw new IncompatibleTypesBlunder();
        }
    }
}
