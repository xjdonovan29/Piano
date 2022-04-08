package org.xjdonovan.piano;

import java.util.ArrayList;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import javax.swing.SwingUtilities;

import org.xjdonovan.piano.core.Note;
import org.xjdonovan.piano.core.Notes;
import org.xjdonovan.piano.ui.CloseWindowListener;
import org.xjdonovan.piano.ui.KeyboardListener;
import org.xjdonovan.piano.ui.MainWindow;

import java.util.List;

public class Main {
	
	public static void startUI(List<Note> notes, Instrument[] instrument, MidiChannel channel) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {	
				new MainWindow(notes, instrument, channel).init();
			}
		});
	}
	
	public static void main(String[] args) {
		try {
			Synthesizer synth = MidiSystem.getSynthesizer();
			Instrument[] instrument = synth.getDefaultSoundbank().getInstruments();
			MidiChannel channel = synth.getChannels()[0];
			synth.open();
			
			List<Note> notes = new ArrayList<Note>();
			for (Notes noteData : Notes.values()) {
				notes.add(new Note(noteData, channel));
			}
			startUI(notes, instrument, channel);
			
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					synth.close();
				}
			});
			
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
