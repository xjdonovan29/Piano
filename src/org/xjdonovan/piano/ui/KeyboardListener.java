package org.xjdonovan.piano.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import org.xjdonovan.piano.Main;
import org.xjdonovan.piano.core.Note;

public class KeyboardListener implements KeyListener {
	private MainWindow window;
	private PianoKey key;
	
	public KeyboardListener(MainWindow window) {
		this.window = window;
	}
	
	private Note findNote(char c) {
		for (int i = 0; i < window.getNotes().size(); i++) {
			var note = window.getNotes().get(i);
			if (note.getBinding() == c) {
				key = window.getKeys().get(i);
				return note;
			}
		}
		return null;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	    if (e.getKeyChar() == '-') {
			window.decreaseOctave();
		} else if (e.getKeyChar() == '=') {
			window.increaseOctave();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		var note = findNote(e.getKeyChar());
		if (note != null && !note.getPressed()) {
			note.on(window.getOctave());	
			window.changeKeyColor(key);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		var note = findNote(e.getKeyChar());
		if (note != null && note.getPressed()) {
			note.off(window.getOctave());
			window.changeKeyColor(key);
		}
	}
}
