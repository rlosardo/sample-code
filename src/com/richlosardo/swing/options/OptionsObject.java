package com.richlosardo.swing.options;

import java.awt.Color;

public class OptionsObject {
	
	private Color backgroundColor;
	
	public OptionsObject() {
		backgroundColor = Color.white;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
}
