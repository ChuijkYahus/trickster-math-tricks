package com.thejebforge.trickster_math_tricks;

import com.thejebforge.trickster_math_tricks.revision.ModRevisions;
import com.thejebforge.trickster_math_tricks.trick.ModTricks;
import dev.enjarai.trickster.screen.owo.GlyphComponent;
import dev.enjarai.trickster.screen.owo.TrickOverviewComponent;
import dev.enjarai.trickster.spell.Pattern;
import dev.enjarai.trickster.spell.mana.type.Mana;
import dev.enjarai.trickster.spell.mana.type.Manae;
import io.wispforest.owo.ui.parsing.UIModelParsingException;
import io.wispforest.owo.ui.parsing.UIParsing;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.text.Text;
import org.w3c.dom.Element;

import java.util.Arrays;

public class TricksterMathTricksClient implements ClientModInitializer {
	public static GlyphComponent parseMathTrick(Element element) {
		UIParsing.expectAttributes(element, "trick-id");
		UIParsing.expectAttributes(element, "size");

		var trickId = UIParsing.parseIdentifier(element.getAttributeNode("trick-id"));
		var trick = ModTricks.REGISTRY.get(trickId);

		if (trick == null) {
			throw new UIModelParsingException("Not a valid trick: " + trickId);
		}

		var size = UIParsing.parseUnsignedInt(element.getAttributeNode("size"));

		return new GlyphComponent(trick.getPattern(), size);
	}

	public static GlyphComponent parseMathPattern(Element element) {
		UIParsing.expectAttributes(element, "pattern");
		UIParsing.expectAttributes(element, "size");

		var patternString = element.getAttributeNode("pattern").getTextContent();

		var pattern = Pattern.from(
				Arrays.stream(patternString.split(","))
						.map(s -> Byte.valueOf(s, 10)).toList()
		);

		var size = UIParsing.parseUnsignedInt(element.getAttributeNode("size"));

		return new GlyphComponent(pattern, size);
	}

	public static TrickOverviewComponent parseTrickOverview(Element element) {
		UIParsing.expectAttributes(element, "texture");
		var texture = UIParsing.parseIdentifier(element.getAttributeNode("texture"));

		var trickIdAttribute = element.getAttributeNode("trick-id");
		if (trickIdAttribute != null) {
			var trickId = UIParsing.parseIdentifier(trickIdAttribute);
			var trick = ModTricks.REGISTRY.get(trickId);

			if (trick == null) {
				throw new UIModelParsingException("Not a valid trick: " + trickId);
			}

			String costCalculation = null;
			if (element.hasAttribute("cost")) {
				costCalculation = element.getAttribute("cost");
			}

			Mana mana = Manae.TRADITIONAL;
			if (element.hasAttribute("mana-type")) {
				var manaId = UIParsing.parseIdentifier(element.getAttributeNode("mana-type"));
				mana = Manae.REGISTRY.get(manaId);
				if (mana == null) {
					throw new UIModelParsingException("Not a valid mana type: " + manaId);
				}
			}

			return TrickOverviewComponent.of(trick, costCalculation, mana, texture);
		}

		var revisionIdAttribute = element.getAttributeNode("revision-id");
		if (revisionIdAttribute != null) {
			var revisionId = UIParsing.parseIdentifier(revisionIdAttribute);
			var revision = ModRevisions.REGISTRY.get(revisionId);

			if (revision == null) {
				throw new UIModelParsingException("Not a valid revision: " + revisionId);
			}

			return TrickOverviewComponent.of(revision, texture);
		}

		UIParsing.expectAttributes(element, "pattern", "title");
		var title = Text.literal(element.getAttributeNode("title").getTextContent());
		var patternString = element.getAttributeNode("pattern").getTextContent();
		var pattern = Pattern.from(
				Arrays.stream(patternString.split(","))
						.map(s -> Byte.valueOf(s, 10)).toList()
		);
		var content = Text.literal(element.getAttributeNode("content").getTextContent());

		return TrickOverviewComponent.of(pattern, title, content, null, texture);
	}

	@Override
	public void onInitializeClient() {
		UIParsing.registerFactory(TricksterMathTricks.id("glyph"), TricksterMathTricksClient::parseMathTrick);
		UIParsing.registerFactory(TricksterMathTricks.id("pattern"), TricksterMathTricksClient::parseMathPattern);
		UIParsing.registerFactory(TricksterMathTricks.id("trick"), TricksterMathTricksClient::parseTrickOverview);
	}
}