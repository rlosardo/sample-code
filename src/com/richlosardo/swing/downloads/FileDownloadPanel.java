package com.richlosardo.swing.downloads;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import com.richlosardo.swing.SampleSwingFrame;
import com.richlosardo.swing.components.TransparentPanel;
import com.richlosardo.swing.library.MusicObject;

public class FileDownloadPanel extends TransparentPanel {
	
	private JLabel fileNameLabel;
	private JProgressBar progressBar;
	private MusicObject musicObject;
	private boolean finished;
	
	public FileDownloadPanel(MusicObject musicObject) {
		this.musicObject = musicObject;
		StringBuffer sb = new StringBuffer(musicObject.getArtist() + " - " + musicObject.getTitle());
		fileNameLabel = new JLabel(sb.toString());
		fileNameLabel.setFont(fileNameLabel.getFont().deriveFont(16f).deriveFont(Font.BOLD));
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(900, 50));
		progressBar = new JProgressBar(0, 100);
		add(fileNameLabel, BorderLayout.NORTH);
		add(progressBar, BorderLayout.CENTER);
	}
	
	public void setFinished() {
		JLabel finishedLabel = new JLabel("   DOWNLOAD COMPLETE   ");
		finishedLabel.setOpaque(true);
		finishedLabel.setBackground(Color.green.brighter().brighter());
		finishedLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		add(finishedLabel, BorderLayout.EAST);
		revalidate();
		SampleSwingFrame frame = (SampleSwingFrame) SwingUtilities.getWindowAncestor(this);
		frame.getLibraryPanel().addMusicObject(musicObject);
		frame.flashLibraryTab();
		finished = true;
		frame.getDownloadsScrollPane().setFileDownloadPanelsVisible();
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public boolean isFinished() {
		return finished;
	}

}
