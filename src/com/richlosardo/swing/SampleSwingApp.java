package com.richlosardo.swing;

import javax.swing.SwingUtilities;

public class SampleSwingApp {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
		        SampleSwingFrame frame = new SampleSwingFrame();
		        frame.setVisible(true);
			}
		});
    }
	

}
