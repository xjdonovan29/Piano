package org.xjdonovan.piano.core;

public enum Notes {
	C(24, 0, false, false, 'a'),
	CSHARP(25, 0, false, true, 'w'),
	D(26, 1, false, false, 's'),
	DSHARP(27, 1, false, true, 'e'),
	E(28, 2, false, false, 'd'),
	F(29, 3, false, false, 'f'),
	FSHARP(30, 3, false, true, 't'),
	G(31, 4, false, false, 'g'),
	GSHARP(32, 4, false, true, 'y'),
	A(33, 5, false, false, 'h'),
	ASHARP(34, 5, false, true, 'u'),
	B(35, 6, false, false, 'j'),
	C2(24, 7, true, false, 'k'),
	CSHARP2(25, 7, true, true, 'o'),
	D2(26, 8, true, false, 'l'),
	DSHARP2(27, 8, true, true, 'p'),
	E2(28, 9, true, false, ';'),
	F2(29, 10, true, false, '\'');
	
	public final Integer baseNoteNumber;
	public final Integer xPos;
	public final Boolean secondOctave;
	public final Boolean isBlack;
	public final Character binding;
	
	private Notes(Integer baseNoteNumber, Integer xPos, Boolean secondOctave, Boolean isBlack, Character binding) {
		this.baseNoteNumber = baseNoteNumber;
		this.xPos = xPos;
		this.secondOctave = secondOctave;
		this.isBlack = isBlack;
		this.binding = binding;
	}
	
}
