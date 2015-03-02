package com.richlosardo.swing.library;

import java.awt.Component;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;

public class AlbumRenderer extends LibraryRenderer {

	private Map<String, ImageIcon> iconMap;
	
	public AlbumRenderer() {
		iconMap = new HashMap<String, ImageIcon>();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel label = (JLabel)  super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		label.setText(null);
		label.setIcon(null);
		if (value instanceof MusicObject) {
			MusicObject obj = (MusicObject) value;
			ImageIcon icon = iconMap.get(obj.getSmallImageName());
			if (icon == null) {
				URL url = getClass().getClassLoader().getResource(obj.getSmallImageName());
				icon = new ImageIcon(url);
				iconMap.put(obj.getSmallImageName(), icon);
			}
			label.setIcon(icon);
			label.setText(obj.getAlbum());
		}
		return label;
	}

}
