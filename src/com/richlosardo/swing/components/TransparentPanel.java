package com.richlosardo.swing.components;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public class TransparentPanel extends JPanel {
	
	public TransparentPanel() {
		setOpaque(false);
	}
	
	public TransparentPanel(LayoutManager manager) {
		super(manager);
		setOpaque(false);
	}
	
}
