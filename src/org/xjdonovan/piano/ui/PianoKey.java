package org.xjdonovan.piano.ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;

import org.xjdonovan.piano.core.Note;

public class PianoKey extends JButton {
	private Note note;
	private GridBagConstraints c;
	private Color color;

	public PianoKey(Note note, MouseListener action) {
		super(String.valueOf(note.getBinding()));
		this.note = note;
		c = new GridBagConstraints();
		c.weightx = 0.5;
		c.gridx = note.xPos();
		if (!note.isBlack()) {
			color = Color.WHITE;
			c.gridy = 1;
			c.ipady = 100;
			c.fill = GridBagConstraints.HORIZONTAL;
		} else {
			color = Color.BLACK;
			c.gridy = 0;
			c.ipadx = 40;
			c.ipady = 100;
			c.gridwidth = 2;
			c.anchor = GridBagConstraints.NORTH;
		}
		addMouseListener(action);
		setBackground(color);
		setForeground(Color.GRAY);
		setFocusPainted(false);
		setMnemonic(note.getBinding());
	}
	
	public GridBagConstraints getConstraints() {
		return c;
	}

	public Color getColor() {
		return color;
	}

	public void setColor() {
		if (note.getPressed()) {
			setBackground(Color.CYAN);
		} else {
			setBackground(color);
		}
	}
	
	public void resetColor() {
		setBackground(color);
	}
	
	public Note getNote() {
		return note;
	}
}
