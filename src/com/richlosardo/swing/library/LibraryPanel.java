package com.richlosardo.swing.library;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.richlosardo.swing.components.DragDropTabComponent;
import com.richlosardo.swing.options.OptionsHandler;
import com.richlosardo.swing.options.OptionsListener;
import com.richlosardo.swing.options.OptionsObject;

public class LibraryPanel extends JPanel implements DragDropTabComponent, OptionsListener {

	public static final String TITLE = "Library";

	private LibraryTableModel libraryTableModel;
	
	public LibraryPanel() {
		initComponents();
		OptionsHandler.getInstance().addOptionsListener(this);
	}
	
	protected void initComponents() {
		setLayout(new BorderLayout());
		setBackground(OptionsHandler.getInstance().getOptionsObject().getBackgroundColor());
		add(getTableComponent(), BorderLayout.CENTER);
	}
	
	protected JComponent getTableComponent() {
		libraryTableModel = new LibraryTableModel();
		libraryTableModel.getObjectList().addAll(createObjectList());
		JTable table = new LibraryTable(libraryTableModel);	
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		return scrollPane;
	}
	
	@Override
	public String getTitle() {
		return TITLE;
	}
	
	protected List<MusicObject> createObjectList() {
		List<MusicObject> objectList = new ArrayList<MusicObject>();
		objectList.add(new MusicObject("The Beatles", "Two Of Us", "Let It Be", "Oldies", 1, 1970, "images/letitbesmall.jpg"));
		objectList.add(new MusicObject("The Beatles", "Dig A Pony", "Let It Be", "Oldies", 2, 1970, "images/letitbesmall.jpg"));
		objectList.add(new MusicObject("The Beatles", "Across The Universe", "Let It Be", "Oldies", 3, 1970, "images/letitbesmall.jpg"));
		objectList.add(new MusicObject("The Beatles", "I Me Mine", "Let It Be", "Oldies", 4, 1970, "images/letitbesmall.jpg"));
		objectList.add(new MusicObject("The Beatles", "Dig It", "Let It Be", "Oldies",5, 1970, "images/letitbesmall.jpg"));
		objectList.add(new MusicObject("The Beatles", "Let It Be", "Let It Be", "Oldies", 6, 1970, "images/letitbesmall.jpg"));
		objectList.add(new MusicObject("The Beatles", "Maggie Mae", "Let It Be", "Oldies", 7, 1970, "images/letitbesmall.jpg"));
		objectList.add(new MusicObject("The Beatles", "I've Got A Feeling", "Let It Be", "Oldies", 8, 1970, "images/letitbesmall.jpg"));
		objectList.add(new MusicObject("The Beatles", "One After 909", "Let It Be", "Oldies", 9, 1970, "images/letitbesmall.jpg"));
		objectList.add(new MusicObject("The Beatles", "The Long And Winding Road", "Let It Be", "Oldies", 10, 1970, "images/letitbesmall.jpg"));
		objectList.add(new MusicObject("The Beatles", "For You Blue", "Let It Be", "Oldies", 11, 1970, "images/letitbesmall.jpg"));
		objectList.add(new MusicObject("The Beatles", "Get Back", "Let It Be", "Oldies", 12, 1970, "images/letitbesmall.jpg"));
		return objectList;
	}
	
	public void addMusicObject(MusicObject musicObject) {
		libraryTableModel.getObjectList().add(musicObject);
		Collections.sort(libraryTableModel.getObjectList());
		libraryTableModel.fireTableDataChanged();
	}
	
	public void optionsChanged(OptionsObject obj) {
		setBackground(obj.getBackgroundColor());
	}
}
