package com.richlosardo.swing.library;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class LibraryTable extends JTable {

	private AlbumRenderer albumRenderer;
	private LibraryRenderer libraryRenderer;
	
	public LibraryTable(TableModel dm) {
		super(dm);
		albumRenderer = new AlbumRenderer();
		libraryRenderer = new LibraryRenderer();
		setRowHeight(80);
		getTableHeader().setReorderingAllowed(false);
		getColumnModel().getColumn(LibraryTableModel.TITLE_COL).setPreferredWidth(150);
		getColumnModel().getColumn(LibraryTableModel.ALBUM_COL).setPreferredWidth(150);
	}
	
	public TableCellRenderer getCellRenderer(int row, int column) {
		if (column ==  LibraryTableModel.ALBUM_COL) {
			return albumRenderer;
		}
		return libraryRenderer;
	}

}
