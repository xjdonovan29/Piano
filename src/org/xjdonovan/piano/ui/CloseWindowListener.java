package org.xjdonovan.piano.ui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import org.xjdonovan.piano.Main;
import org.xjdonovan.piano.core.Note;
import org.xjdonovan.piano.core.Notes;

public class CloseWindowListener implements WindowListener {
	private MainWindow window;
	
	public CloseWindowListener(MainWindow window) {
		this.window = window;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		window.disableAllNotes();
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		window.disableAllNotes();
	}

}
