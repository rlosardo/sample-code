package com.richlosardo.swing.library;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.richlosardo.swing.options.OptionsHandler;
import com.richlosardo.swing.options.OptionsListener;
import com.richlosardo.swing.options.OptionsObject;

public class LibraryTableModel extends AbstractTableModel implements OptionsListener {

	private static final int ARTIST_COL= 0;
	public static final int TITLE_COL = 1;
	public static final int ALBUM_COL = 2;
	private static final int GENRE_COL = 3;
	private static final int TRACK_COL = 4;
	private static final int YEAR_COL = 5;
	private static final String ARTIST = "Artist";
	private static final String TITLE = "Title";
	private static final String ALBUM = "Album";
	private static final String GENRE = "Genre";
	private static final String TRACK = "Track";
	private static final String YEAR = "Year";
	
	private List<MusicObject> objectList;
		
	public LibraryTableModel() {
		objectList = new ArrayList<MusicObject>();
		OptionsHandler.getInstance().addOptionsListener(this);
	}

	@Override
	public int getRowCount() {
		return objectList.size();
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		MusicObject musicObject = objectList.get(rowIndex);
		Object value = null;
		if (musicObject != null) {
			switch (columnIndex) {
			case ARTIST_COL:
				value = musicObject.getArtist();
				break;
			case TITLE_COL:
				value = musicObject.getTitle();
				break;
			case ALBUM_COL:
				value = musicObject;
				break;
			case GENRE_COL:
				value = musicObject.getGenre();
				break;
			case TRACK_COL:
				value = musicObject.getTrack();
				break;
			case YEAR_COL:
				value = musicObject.getYear();
				break;
			}
		}
		return value;
	}

	@Override
	public String getColumnName(int column) {
		String value = null;
		switch (column) {
		case ARTIST_COL:
			value = ARTIST;
			break;
		case TITLE_COL:
			value = TITLE;
			break;
		case ALBUM_COL:
			value = ALBUM;
			break;
		case GENRE_COL:
			value = GENRE;
			break;
		case TRACK_COL:
			value = TRACK;
			break;
		case YEAR_COL:
			value = YEAR;
			break;
		}
		return value;
	}

	public List<MusicObject> getObjectList() {
		return objectList;
	}

	@Override
	public void optionsChanged(OptionsObject obj) {
		fireTableDataChanged();
	}

}
