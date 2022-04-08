package org.xjdonovan.piano.ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.xjdonovan.piano.core.Note;

public class MainWindow extends JFrame implements MouseListener, ActionListener{
	private static final int MAX_OCTAVE = 6;
	private static final int MIN_OCTAVE = 0;
	private List<Note> notes;
	private List<PianoKey> keys;
	private Instrument[] instrument;
	private JComboBox instrumentCB;
	private JButton octaveDown, octaveUp;
	private MidiChannel channel;
	private int octave = 3;
	
	public MainWindow(List<Note> notes, Instrument[] intrument, MidiChannel channel) {
		super("Piano");
		this.notes = notes;
		this.instrument = intrument;
		this.channel = channel;
		keys = new ArrayList<PianoKey>(); 
	}
	
	public void init() {
		setSize(1000, 800);
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(20,20);
		setVisible(true);
		setLayout(new GridBagLayout());
				
		for (Note note : notes) {
			PianoKey key;
			if (note.isBlack()) {
				key = new PianoKey(note, this);
			} else {
				key = new PianoKey(note, this);
			}
			keys.add(key);
			add(key, key.getConstraints());
		}
		JPanel panel = new JPanel();
		String[] instrumentNames = new String[instrument.length];
		int length = 0;
		for (Instrument name : instrument) {
			instrumentNames[length] = name.getName();
			length++;
		}
		instrumentCB = new JComboBox(instrumentNames);
		instrumentCB.setSelectedIndex(0);
		instrumentCB.addActionListener(this);
		panel.add(instrumentCB);
		octaveDown = new JButton("Octave Down");
		octaveDown.addActionListener(this);
		panel.add(octaveDown);
		octaveUp = new JButton("Octave Up");
		octaveUp.addActionListener(this);
		panel.add(octaveUp);
		var instrumentConstraints = new GridBagConstraints();
		instrumentConstraints.gridy = 2;
		instrumentConstraints.fill = GridBagConstraints.BOTH;
		instrumentConstraints.gridwidth = 10;
		add(panel, instrumentConstraints);
		addKeyListener(new KeyboardListener(this));
		addWindowListener(new CloseWindowListener(this));
		requestFocus();
	}

	public List<Note> getNotes() {
		return notes;
	}

	public List<PianoKey> getKeys() {
		return keys;
	}

	public int getOctave() {
		return octave;
	}
	
	public void increaseOctave() {
		if (octave < MAX_OCTAVE) {
			resetKeys();
			octave++;
		}
	}
	
	public void decreaseOctave() {
		if (octave > MIN_OCTAVE) {
			resetKeys();
			octave--;
		}
	}
	
	public void disableAllNotes() {
		for (Note note : notes) {
			note.off(octave);
		}
	}
	
	public void changeKeyColor(PianoKey k) {
		k.setColor();
	}
	
	public void resetAllKeyColor() {
		for (PianoKey key : keys) {
			key.resetColor();
		}
	}
	
	public void resetKeys() {
		disableAllNotes();
		resetAllKeyColor();
	}

	@Override
	public void mouseClicked(MouseEvent e) {	
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		PianoKey k = (PianoKey) e.getSource();
		k.getNote().on(octave);
		changeKeyColor(k);
		requestFocus();
		}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		PianoKey k = (PianoKey) e.getSource();
		k.getNote().off(octave);
		changeKeyColor(k);
		requestFocus();
		}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == instrumentCB) {
			int pos = instrumentCB.getSelectedIndex();
			channel.programChange(pos);
			resetKeys();
			requestFocus();
		} else if (e.getSource() == octaveDown) {
			decreaseOctave();
			requestFocus();
		} else {
			increaseOctave();
			requestFocus();
		}
	}
}
