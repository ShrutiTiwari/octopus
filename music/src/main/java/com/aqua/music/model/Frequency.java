package com.aqua.music.model;

public interface Frequency {
	public String fileCode();

	public float frequencyInHz();

	public String prettyPrint();

	public String western();

	enum ClassicalNote implements Frequency {
		DHA("F#4", 369.99F),
		DHA_("F4", 349.23F),
		GA("C#4", 277.18F),
		GA_("C4", 261.63F),
		HIGH_GA("C#5", 554.37F),
		HIGH_GA_("C5", 523.25F),
		HIGH_MA("D5", 587.33F),
		HIGH_MA_("D#5", 622.25F),
		HIGH_PA("E5", 659.26F),
		HIGH_RE("B4", 493.88F),
		HIGH_RE_("A#4", 466.16F),
		HIGH_SA("A4", 440F),
		LOW_DHA("F#3", 185.00F),
		LOW_DHA_("F3", 174.61F),
		LOW_GA("C#3", 138.59F),
		LOW_GA_("C3", 130.81F),
		LOW_MA("D3", 146.83F),
		LOW_MA_("D#3", 155.56F),
		LOW_NI("G#3", 207.65F),
		LOW_NI_("G3", 196.00F),
		LOW_PA("E3", 164.81F),
		MA("D4", 293.66F),
		MA_("D#4", 311.13F),
		NI("G#4", 415.30F),
		NI_("G4", 392.00F),
		PA("E4", 329.63F),
		RE("B3", 246.94F),
		RE_("A#3", 233.08F),
		SA("A3", 220F);

		private final String fileCode;
		private final float frequencyInHz;
		private String prettyPrintText;
		private final String western;

		ClassicalNote(String western, float frequencyInHz) {
			this.western = western;
			this.frequencyInHz = frequencyInHz;
			this.fileCode = name().toLowerCase();
			this.prettyPrintText = camelCase();
		}

		public String fileCode() {
			return fileCode;
		}

		public float frequencyInHz() {
			return frequencyInHz;
		}

		@Override
		public String prettyPrint() {
			return prettyPrintText;
		}

		public String western() {
			return western;
		}

		private String camelCase() {
			String lowerCase = name().toLowerCase();
			String camelCase = ("" + lowerCase.charAt(0)).toUpperCase() + lowerCase.substring(1);
			return camelCase;
		}
	}
}