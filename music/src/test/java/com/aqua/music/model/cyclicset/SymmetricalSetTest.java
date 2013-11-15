package com.aqua.music.model.cyclicset;

import org.junit.Test;

import com.aqua.music.bo.audio.manager.AudioPlayConfig;

public class SymmetricalSetTest {
	// @Test
	public void testKafi() {
		CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet(SymmetricalSet.THAAT_KAFI).play(AudioPlayConfig.SYNCHRONOUS_STATIC_PLAYER);
	}

	@Test
	public void testKafiWithPattern() {
		CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet(SymmetricalSet.THAAT_KAFI).play(AudioPlayConfig.ASYNCHRONOUS_DYNAMIC_PLAYER);
		CyclicFrequencySet freqSeq = CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySetAndPermutation(SymmetricalSet.THAAT_KAFI,new int[] { 1, 4, 3 });
		freqSeq.play(AudioPlayConfig.SYNCHRONOUS_STATIC_PLAYER);
	}
}