package com.thejebforge.trickster_math_tricks;

import com.thejebforge.trickster_math_tricks.fragment.ModFragmentTypes;
import com.thejebforge.trickster_math_tricks.revision.ModRevisions;
import com.thejebforge.trickster_math_tricks.trick.ModTricks;
import com.thejebforge.trickster_math_tricks.trick.VectorOverloads;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TricksterMathTricks implements ModInitializer {
	public static final String MOD_ID = "trickster-math-tricks";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModFragmentTypes.register();
		ModTricks.register();
		VectorOverloads.register();
		ModRevisions.register();
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}