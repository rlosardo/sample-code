package com.richlosardo.swing.search;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.richlosardo.swing.SampleSwingFrame;
import com.richlosardo.swing.components.TransparentPanel;
import com.richlosardo.swing.library.MusicObject;

class AlbumPanel extends TransparentPanel {
	
	private String imageName;
	private String albumName;
	private String artistName;
	private String genre;
	private Integer year;
	private String smallImageName;
	private String[] trackList;
	
	public AlbumPanel(String imageName, String smallImageName, String albumName, String artistName, String[] trackList, String genre, Integer year) {
		this.imageName = imageName;
		this.smallImageName = smallImageName;
		this.albumName = albumName;
		this.artistName = artistName;
		this.trackList = trackList;
		this.genre = genre;
		this.year = year;
		initAlbumGUI();
	}
	
	private void initAlbumGUI() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		if (imageName != null) {
			URL url = getClass().getClassLoader().getResource(imageName);
			ImageIcon icon = new ImageIcon(url);
			JLabel imageLabel = new JLabel(icon);
			imageLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			add(imageLabel, gbc);
			gbc.gridy++;
		}
		add(getLabeledPanel(artistName, true), gbc);
		gbc.gridy++;
		add(getLabeledPanel(albumName, true), gbc);
		gbc.gridy++;
		JPanel trackPanel = new TransparentPanel(new GridLayout(trackList.length, 1));
		for (int i=0; i<=trackList.length-1; i++) {
			trackPanel.add(getTrackComponent(trackList[i], i+1));
		}
		JPanel trackPanelParent = new TransparentPanel();
		trackPanelParent.add(trackPanel);
		add(trackPanelParent, gbc);
		gbc.gridy++;
	}
	
	private JComponent getTrackComponent(final String trackName, final int trackNumber) {
		JPanel trackPanel = new TransparentPanel(new BorderLayout());
		JLabel trackNumberLabel = new JLabel(trackNumber+". ");
		if (trackNumberLabel.getText().length() == 3) {
			trackNumberLabel.setText(" " + trackNumberLabel.getText());
		}
		final JLabel trackLabel = new JLabel("<HTML><U>"+trackName+"</U></HTML>");
		trackLabel.setForeground(Color.blue);
		trackLabel.setFont(trackLabel.getFont().deriveFont(14f));
		trackLabel.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {	
			}

			public void mouseEntered(MouseEvent e) {
				trackLabel.setForeground(Color.red);
				trackLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				trackLabel.setForeground(Color.blue);
				trackLabel.setCursor(Cursor.getDefaultCursor());
			}

			public void mousePressed(MouseEvent e) {
				trackLabel.setForeground(Color.orange);				
				SampleSwingFrame frame = (SampleSwingFrame) SwingUtilities.getWindowAncestor(AlbumPanel.this);
				MusicObject musicObject = new MusicObject(artistName, trackName, albumName, genre, trackNumber, year, smallImageName);
				frame.getDownloadsScrollPane().addFile(musicObject, (int)(Math.random()*150));
				frame.flashDownloadsTab();
			}

			public void mouseReleased(MouseEvent e) {
				trackLabel.setForeground(Color.blue);
			}
			
		});
		trackPanel.add(trackNumberLabel, BorderLayout.WEST);
		trackPanel.add(trackLabel, BorderLayout.CENTER);
		return trackPanel;
	}
	
	private JPanel getLabeledPanel(String labelText, boolean bold) {
		JPanel panel = new TransparentPanel();
		JLabel label = new JLabel(labelText);
		if (bold) {
			label.setFont(label.getFont().deriveFont(14f).deriveFont(Font.BOLD));
		}
		panel.add(label);
		return panel;
	}
}