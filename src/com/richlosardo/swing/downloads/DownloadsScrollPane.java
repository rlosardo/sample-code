package com.richlosardo.swing.downloads;

import javax.swing.JScrollPane;

import com.richlosardo.swing.components.DragDropTabComponent;
import com.richlosardo.swing.library.MusicObject;

public class DownloadsScrollPane extends JScrollPane implements
		DragDropTabComponent {

	public static final String TITLE = "Downloads";
	
	private DownloadsPanel downloadsPanel;
	
	public DownloadsScrollPane() {
		super(new DownloadsPanel());
		downloadsPanel = (DownloadsPanel) getViewport().getView();
	}
	
	@Override
	public String getTitle() {
		return TITLE;
	}
	
	public void addFile(MusicObject musicObject, long sleepTime) {
		downloadsPanel.addFile(musicObject, sleepTime);
	}
	
	public void setFileDownloadPanelsVisible() {
		downloadsPanel.setFileDownloadPanelsVisible();
	}

}
