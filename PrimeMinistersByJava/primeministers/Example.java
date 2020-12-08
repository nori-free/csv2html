package primeministers;

import java.util.EnumSet;

public class Example {
	private static enum Mode {
		PrimeMinisters,
		TokugawaShogunate
	}

	public static void main(String... args) {
		EnumSet.allOf(Mode.class).forEach(anItem -> {
			Translator aTranslator = new Translator(anItem);
			aTranslator.execute();
		});
		return;
	}
}
