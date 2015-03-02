package com.richlosardo.swing.library;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.richlosardo.swing.options.OptionsHandler;

public class LibraryRenderer extends DefaultTableCellRenderer {
	
	public LibraryRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		label.setBackground(OptionsHandler.getInstance().getOptionsObject().getBackgroundColor());
		label.setForeground(Color.black);
		return label;
	}
}
