package org.xjdonovan.piano.core;

import javax.sound.midi.MidiChannel;

public class Note {
	private Notes type;
	private final MidiChannel channel;
	private Boolean pressed = false;
	
	public Note(Notes type, MidiChannel channel) {
		this.type = type;
		this.channel = channel;
		}
	
	public Integer xPos() {
		return type.xPos;
	}

	public Character getBinding() {
		return type.binding;
	}
	
	public Boolean isBlack() {
		return type.isBlack;
	}

	public MidiChannel getChannel() {
		return channel;
	}

	public Boolean getPressed() {
		return pressed;
	}

	private int findOctaveOffset(int octave) {
		return 12 * (type.secondOctave ? octave + 1 : octave);
	}
	
	public void on(int octave) {
		channel.noteOn(type.baseNoteNumber + findOctaveOffset(octave), 100);
		pressed = true;
	}
	
	public void off(int octave) {
		channel.noteOff(type.baseNoteNumber + findOctaveOffset(octave));
		pressed = false;
	}
	
}
