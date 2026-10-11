package com.thejebforge.trickster_math_tricks.revision;

import dev.enjarai.trickster.spell.Pattern;
import dev.enjarai.trickster.spell.PatternGlyph;
import dev.enjarai.trickster.spell.SpellView;
import dev.enjarai.trickster.spell.revision.Revision;
import dev.enjarai.trickster.spell.revision.RevisionContext;

public class MathLibRevision implements Revision {
    public static final Pattern PATTERN = Pattern.of(3, 4, 0, 3, 6, 4, 7, 6);

    @Override
    public Pattern pattern() {
        return PATTERN;
    }

    @Override
    public void apply(RevisionContext revisionContext, SpellView view) {
        if (view.part.glyph instanceof PatternGlyph(Pattern pattern)) {
            var revision = ModRevisions.lookup(pattern);

            revision.ifPresent(value -> value.apply(revisionContext, view));
        }
    }
}
